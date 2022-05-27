package pegsystem.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import egovframework.rte.psl.dataaccess.util.EgovMap;


/**
 * @Class Name	: XmlUtil.java
 * @Description : XML 생성 Util Class
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
public abstract class XmlUtil {
	
	/**
	 * xml 형식의 document 객체를 downloadPath에 물리적인 파일로 생성한다.
	 * @param dataList
	 * @param rootElementName
	 * @param topElementName
	 * @param characterSet
	 * @param downloadPath
	 * @return
	 */
	public int makeXml(List<EgovMap> dataList, String characterSet, String downloadPath) {
		Document doc = setXmlData(dataList);
		if(doc != null) {
			XMLOutputter xmlOut = new XMLOutputter();
			Format format = xmlOut.getFormat();
			format.setEncoding(characterSet);	// 한글인코딩
			format.setIndent("	");				// 들여쓰기
			format.setLineSeparator("\r\n");	// 줄바꿈
			format.setTextMode(Format.TextMode.TRIM);
			
			FileWriter fw = null;
			try {            
				fw = new FileWriter(downloadPath);
				xmlOut.setFormat(format);
				xmlOut.output(doc, fw);
			} catch (IOException e) {
				e.printStackTrace();
				return -999;
			} finally {
				try {
					if(fw != null) fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			return 0;
		}
        
        return 1;
	}
	
	
	/**
	 * 조회 된 데이터를 xml 형식의 document 객체로 리턴
	 * xml 구조가 다르기 때문에 반드시 구현해야 하는 abstract method 
	 * @param dataList
	 * @param rootElementName
	 * @param topElementName
	 * @return
	 */
	public abstract Document setXmlData(List<EgovMap> dataList);
}
