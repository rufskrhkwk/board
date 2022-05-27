package pegsystem.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import egovframework.rte.psl.dataaccess.util.EgovMap;


/**
 * @Class Name	: ExcelUtil.java
 * @Description : Excel파일 생성에 필요한 Util Class
 * 
 * @author	박진우(jwpark@pegsystem.co.kr)
 * @since	2018.03.29
 * @version	1.0
 * @see
 * Copyright (C) PEGSYSTEM <http://www.pegsystem.co.kr>
 * 
 * 
 * @Modification Information
 *	  Date		  Modifier		Comment
 *  ----------   ----------    -------------------
 *  2018.03.29	  박진우   		최초생성
 */
public class ExcelUtil {

	public int makeExcel(List<EgovMap> dataList, String downloadPath) {
		// workbook, sheet, row, cell 생성
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet("test");
		XSSFRow row = null;
		XSSFCell cell = null;
					
		List<String> columnList = excelColumnList();
		List<String> nameList = excelGetterNameList();

		// 0번 row = 최상단 타이틀
		if(columnList.size() > 0) {
			row = sheet.createRow(0);
			for(int i=0; i<columnList.size(); i++) {
				cell = row.createCell(i);
				cell.setCellValue(columnList.get(i));
			}
		}
					
		// 데이타 초기화
		for(int i=0; i<dataList.size(); i++) {
			row = sheet.createRow(i+1);
			if(nameList != null && nameList.size() > 0) {
				for(int j=0; j<nameList.size(); j++) {
					cell = row.createCell(j);
					String column = nameList.get(j);
					if("rowCnt".equals(column)) cell.setCellValue(String.valueOf(dataList.size()));
					else cell.setCellValue(StringUtil.isNull(dataList.get(i).get(column)));
				}
			}
		}
					
		// 셀 넓이 auto
		for(int i=0; i<columnList.size(); i++) {
			sheet.autoSizeColumn(i);
		}
					
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(downloadPath);
			try {
				wb.write(fos);
			} catch (IOException e) {
				e.printStackTrace();
				return -9;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return -99;
		} finally {
			if(fos != null) try { fos.close(); } catch (IOException e) { e.printStackTrace(); }
			if(wb != null) try { wb.close(); } catch (IOException e) { e.printStackTrace(); }
		}
					
		return 1;
	}
	
	
	
	
	
	/**
     * 엑셀 타이틀 리스트(DB 컬럼과 일치하지 않기 떄문에 생성)
     * @return
     */
    private List<String> excelColumnList() {
    	List<String> columnList = new ArrayList<String>();
    	columnList.add("Source");
    	columnList.add("Product Type");
    	columnList.add("Album Ssrc Map Album Spid");
    	columnList.add("count");
    	columnList.add("Manifest Id");
    	columnList.add("앨범코드");
    	columnList.add("Album Album Nm");
    	columnList.add("Loc Album Nm");
    	columnList.add("Sigr Nm");
    	columnList.add("Loc Sigr Nm");
    	columnList.add("Genre Nm");
    	columnList.add("Volm Cnt");
    	columnList.add("Track Cnt");
    	columnList.add("Ssrc Map Album Cd");
    	columnList.add("Label");
    	columnList.add("Album Sale Dt");
    	columnList.add("Small Image");
    	columnList.add("Large Image");
    	columnList.add("Disk No");
    	columnList.add("Track No");
    	columnList.add("Sts");
    	columnList.add("Ssrc Cd");
    	columnList.add("Ssrc Nm");
    	columnList.add(" Loc Ssrc Nm");
    	columnList.add("Sigr Nm");
    	columnList.add("Loc Sigr Nm");
    	columnList.add("Genre Nm");
    	columnList.add("Ssrc Cd");
    	columnList.add("Label");
    	columnList.add("Lyrics Nm");
    	columnList.add("Composer Nm");
    	columnList.add("File Nm Mp3");
    	columnList.add("File Nm Flac");
    	columnList.add("Album Grid");
    	columnList.add("Ssrc Ssrc Grid");
    	columnList.add("Plinetext");
    	columnList.add("File Nm Flac24");
    	columnList.add("File Nm Wav");
    	columnList.add("File Nm Wav24");
    	
    	return columnList;
    }
    
    
    /**
     * egovMap에서 get() 네임이 타이틀과 다르기 때문에 별도로 초기화
     * @return
     */
    private List<String> excelGetterNameList() {
    	List<String> nameList = new ArrayList<String>();
    	nameList.add("source");
    	nameList.add("productType");
    	nameList.add("albumSpid");
    	nameList.add("rowCnt");
    	nameList.add("manifestId");
    	nameList.add("asmAlbumCd");
    	nameList.add("alAlbumNm");
    	nameList.add("alLocAlbumNm");
    	nameList.add("alSigrNm");
    	nameList.add("alLocSigrNm");
    	nameList.add("alGenreNm");
    	nameList.add("alVolmCnt");
    	nameList.add("alTrackCnt");
    	nameList.add("asmAlbumCd");
    	nameList.add("alLabel");
    	nameList.add("alSaleDt");
    	nameList.add("smallImage");
    	nameList.add("largeImage");
    	nameList.add("asmDiskNo");
    	nameList.add("asmTrackNo");
    	nameList.add("ssrcSts");
    	nameList.add("ssSsrcCd");
    	nameList.add("ssSsrcNm");
    	nameList.add("ssLocSsrcNm");
    	nameList.add("ssSigrNm");
    	nameList.add("ssLocSigrNm");
    	nameList.add("ssGenreNm");
    	nameList.add("ssSsrcCd");
    	nameList.add("alLabel");
    	nameList.add("ssWtlyNm");
    	nameList.add("ssWtmsNm");
    	nameList.add("fileNmMp3");
    	nameList.add("fileNmFlac");
    	nameList.add("alGrid");
    	nameList.add("ssSsrcGrid");
    	nameList.add("alPlinetext");
    	nameList.add("fileNmFlac24");
    	nameList.add("fileNmWav");
    	nameList.add("fileNmWav24");
    	
    	return nameList;
    }
}
