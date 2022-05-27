package pegsystem.excel;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import pegsystem.util.FileUtil;
 
 
public class ExcelExtractor {
	
	private static final Logger logger = LoggerFactory.getLogger(ExcelExtractor.class);
	
	
	/**
	 * 엑셀파일의 데이터를 추출 한다.
	 * @param option
	 * @return
	 */
	public static List<Map<String, Object>> extraction(ExcelExtractorOption option) {
		Workbook wb = null;
		Sheet sheet = null;								// 엑셀 파일에서 옵션에 지정한 Sheet
		Row row = null;									// Sheet에서 rowIndex에 해당하는 Row(행)
		Cell cell = null;								// Row에서 cellIndex에 해당하는 Cell(열)
		Map<String, Object> resultMap = null;			// 행의 값을 저장할 Map
		List<Map<String, Object>> resultList = null;	// 추출한 행을 담을 리스트. 하나의 행을 Map으로 만들어 List에는 모든 행이 담긴다.
		int maxRowNumber = 0;							// Sheet에서 데이터가 있는 마지막 행의 번호(0부터 시작)
		int dataRowNumber = 0;							// Sheet에서 유효한(데이터가 있는) 행의 갯수
		String cellName = "";							// 현재 Cell의 이름(ex. A, B, C, D.....)
		int cellNumber = 0;								// 가져온 행의 cell 갯수
		
		MultipartFile file = option.getFile();
		if(file != null) wb = getWorkBook(file);
		if(wb != null) {
			logger.info("Sheet 이름 : " + wb.getSheetName(0)); 
			logger.info("데이터가 있는 Sheet의 수 : " + wb.getNumberOfSheets());
			sheet = wb.getSheetAt(option.getSheetNumber());
			if(sheet != null) {
				maxRowNumber = sheet.getLastRowNum();				
				
				// row만큼 반복한다.
				resultList = new ArrayList<Map<String, Object>>(); 
				for(int rowIndex=option.getStartRow(); rowIndex<=maxRowNumber; rowIndex++) {
					row = sheet.getRow(rowIndex);
					if(row != null) {
						resultMap = new HashMap<String, Object>();
						cellNumber = row.getLastCellNum();
						// cell의 수 만큼 반복한다.
						for(int cellIndex=0; cellIndex<cellNumber; cellIndex++) {
							cell = row.getCell(cellIndex);
							if(cell != null) {
								cellName = ExcelUtil.getCellName(cell, cellIndex);
								/**
								 * 추출 대상 컬럼인지 확인한다.
								 * 추출 대상 컬럼이 아니라면, for로 다시 올라 간다.
								 */
								if(!option.getOutputColumns().contains(cellName)) continue;
								resultMap.put(cellName, ExcelUtil.getCellValue(cell));	// map객체의 Cell의 이름을 키(Key)로 데이터를 담는다.
							} // cell
						} // end for
						
						resultList.add(resultMap);
					} // row
				} // end for.
				
				dataRowNumber = sheet.getPhysicalNumberOfRows();
				if(resultList.size() != dataRowNumber) {
					logger.info("엑셀 추출 실패 --> 엑셀파일 rownum : " + dataRowNumber + " / list rownum : " + resultList.size());
				}
				
				return resultList;
			} // sheet
		} // workbook
		
		return null;
	}



	/**
	 * 파일의 확장자에 따라서 workbook 객체를 생성한다.
	 * 엑셀 97~2003 : HSSF
	 * 엑셀 2007이상 : XSSF
	 * @param excel
	 * @return
	 */
	private static Workbook getWorkBook(MultipartFile excel) {
		Workbook wb = null;
		try {
			String fileName = excel.getOriginalFilename(); 
			File convertFile = FileUtil.multipartfileToFile(excel);
			FileInputStream fis = new FileInputStream(convertFile);
			
			if(fileName.toLowerCase().endsWith(".xls")) wb = new HSSFWorkbook(fis);
			else if(fileName.toLowerCase().endsWith(".xlsx")) wb = new XSSFWorkbook(fis);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return wb;
	}


	

}
