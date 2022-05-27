package pegsystem.excel;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class ExcelExtractorOption {

    private MultipartFile file;			// 추출할 엑셀파일
    private int sheetNumber;			// 추출할 시트 번호
    private List<String> outputColumns; // 추출할 컬럼명
    private int startRow;				// 추출을 시작할 행 번호
    
    
    
    public MultipartFile getFile() {
        return file;
    }
    
    public void setFile(MultipartFile file) {
        this.file = file;
    }
    
    public int getSheetNumber() {
    	return sheetNumber;
    }
    
    public void setSheetNumber(int sheetNumber) {
    	this.sheetNumber = sheetNumber;
    }
    
    public List<String> getOutputColumns() {
        return outputColumns;
    }
    
    public void setOutputColumns(List<String> outputColumns) {
        this.outputColumns = outputColumns;
    }
    
    public int getStartRow() {
        return startRow;
    }
    
    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }
    
    
    
    
}
