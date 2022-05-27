package pegsystem.excel;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellReference;

public class ExcelUtil {
	/**
     * Cell에 해당하는 Column Name을 가지고 온다(A,B,C..)
     * 만약 Cell이 Null이라면 int cellIndex의 값으로 Column Name을 가져온다.
     * @param cell
     * @param cellIndex
     * @return
     */
    public static String getCellName(Cell cell, int cellIndex) {
        int cellNum = 0;
        if(cell != null) cellNum = cell.getColumnIndex();
        else cellNum = cellIndex;
        return CellReference.convertNumToColString(cellNum);
    }

    
    /**
     * cell type에 맞는 형식으로 return
     * @param cell
     * @return
     */
    @SuppressWarnings("deprecation")
	public static Object getCellValue(Cell cell) {
        Object value = null;
        if(cell != null) {
        	int cellType = cell.getCellType();
            if(cellType == Cell.CELL_TYPE_FORMULA)		value = cell.getCellFormula();
            else if(cellType == Cell.CELL_TYPE_NUMERIC) value = cell.getNumericCellValue();
            else if(cellType == Cell.CELL_TYPE_STRING)	value = cell.getStringCellValue();
            else if(cellType == Cell.CELL_TYPE_BOOLEAN) value = cell.getBooleanCellValue();
            else if(cellType == Cell.CELL_TYPE_ERROR)	value = cell.getErrorCellValue();
            else if(cellType == Cell.CELL_TYPE_BLANK)	value = "";
            else value = cell.getStringCellValue();
        }
        return value;
    }

    
    /**
     * 엑셀 파일의 셀 값의 타입에 맞는 형식으로 return
     * @param obj
     * @return
     */
    public static String getCellValue(Object obj) {
    	String result = "";
    	if(obj != null) {
    		DecimalFormat df = new DecimalFormat("#.#");
    		String str = String.valueOf(obj);
    		String objType = obj.getClass().getName();
    		if("java.lang.Integer".equals(objType)) {
    			int a = Integer.parseInt(str);
    			result = df.format(a);
    		} else if("java.lang.Double".equals(objType)) {
    			double a = Double.parseDouble(str);
    			result = df.format(a);
    		} else if("java.lang.Long".equals(objType)) {
    			long a = Long.parseLong(str);
    			result = df.format(a);
    		} else {
    			result = str;
    		}
    	}
    	return result;
    }
    
    
    /**
     * 셀의 갯수만큼 알파벳을 리스트에 담아서 return.
     * @param maxCellNumber
     * @return
     */
    public static List<String> generateCellNameList(int maxCellNumber) {
    	List<String> list = new ArrayList<String>();
    	int number = 26;
		for(int i=0; i<maxCellNumber; i++) {
			int temp = i + 65;
			if(i < number) list.add(String.valueOf((char) temp));
			else if(i < (number * 2))  list.add(String.valueOf("A" + (char) (temp - number)));
			else if(i < (number * 3))  list.add(String.valueOf("B" + (char) (temp - (number * 2))));
			else if(i < (number * 4))  list.add(String.valueOf("C" + (char) (temp - (number * 3))));
			else if(i < (number * 5))  list.add(String.valueOf("D" + (char) (temp - (number * 4))));
			else if(i < (number * 6))  list.add(String.valueOf("E" + (char) (temp - (number * 5))));
			else if(i < (number * 7))  list.add(String.valueOf("F" + (char) (temp - (number * 6))));
			else if(i < (number * 8))  list.add(String.valueOf("G" + (char) (temp - (number * 7))));
			else if(i < (number * 9))  list.add(String.valueOf("H" + (char) (temp - (number * 8))));
			else if(i < (number * 10))  list.add(String.valueOf("I" + (char) (temp - (number * 9))));
		}
		
		return list;
    }
}
