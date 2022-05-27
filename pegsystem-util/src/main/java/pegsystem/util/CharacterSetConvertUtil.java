package pegsystem.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.rte.psl.dataaccess.util.EgovMap;


/**
 * @Class Name	: CharacterSetConvertUtil.java
 * @Description : DB의 캐릭터셋이 US-ASCII(7비트)인 경우 한글이 깨지기 떄문에 캐릭터셋을 변경하기 위한 Util Class
 *                context-common.xml에 bean으로 등록할때 in, out 캐릭터셋을 반드시 생성자 argument로 지정해야 한다. 
 * 
 * @author	박진우(jwpark@pegsystem.co.kr)
 * @since	2018.04.04
 * @version	1.0
 * @see
 * Copyright (C) PEGSYSTEM <http://www.pegsystem.co.kr>
 * 
 * 
 * @Modification Information
 *	  Date		  Modifier		Comment
 *  ----------   ----------    -------------------
 *  2018.04.04	  박진우   		최초생성
 */
public class CharacterSetConvertUtil {

	private static final Logger logger = LoggerFactory.getLogger(CharacterSetConvertUtil.class);
	private String IN_CHARACTER_SET;
	private String OUT_CHARACTER_SET;
	private boolean CHARACTER_SET_CHECK;

	
	
	
	/**
	 * 생성자 : 캐릭터셋이 UTF-8이 아닌 경우에만 캐릭터셋을 변환한다.
	 * @param in_character_set
	 * @param out_character_set
	 */
	public CharacterSetConvertUtil(String in_character_set, String out_character_set) {
		this.IN_CHARACTER_SET = in_character_set;
		this.OUT_CHARACTER_SET = out_character_set;
		this.CHARACTER_SET_CHECK = "UTF-8".equalsIgnoreCase(in_character_set) ? false : true;
	}
	
	
	/**
	 * Parameter 변환 : Map<String, Object>
	 * @param map
	 * @return HashMap<String, Object>
	 */
	public Map<String, Object> convertParameter(Map<String, Object> map) {
		if(CHARACTER_SET_CHECK) {
			Map<String, Object> retMap = new HashMap<String, Object>();
			Iterator<Entry<String, Object>> iter = map.entrySet().iterator();
			while(iter.hasNext()) {
				Entry<String, Object> entry = (Entry<String, Object>) iter.next();
				try {
					Object str = entry.getValue();
					if(str != null) {
						retMap.put(entry.getKey(), new String(str.toString().getBytes(OUT_CHARACTER_SET), IN_CHARACTER_SET));
					}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
					logger.info(e.getMessage());
				}
			}
			
			return retMap;
		}
		
		return map;
	}
	
	
	/**
	 * Parameter 변환 : String
	 * @param str
	 * @return String
	 */
	public String convertParameter(String str) {
		if(CHARACTER_SET_CHECK) {
			String retVal = "";
			try {
				if(str != null) retVal = new String(str.getBytes(OUT_CHARACTER_SET), IN_CHARACTER_SET); 
			} catch (Exception e) {
				e.printStackTrace();
				logger.info(e.getMessage());
			}
			return retVal;
		}
		
		return str;
	}
	
	
	/**
	 * ResultSet 변환 : EgovMap
	 * @param egovMap
	 * @return EgovMap
	 */
	@SuppressWarnings("unchecked")
	public EgovMap convertResultSet(EgovMap egovMap) {
		if(egovMap != null) {
			if(CHARACTER_SET_CHECK) {
				EgovMap retMap = new EgovMap();
				Set<Entry<String, Object>> entrySet = egovMap.entrySet();
				if(entrySet != null) {
					Iterator<Entry<String, Object>> iter = entrySet.iterator();
					if(iter != null) {
						while(iter.hasNext()) {
							Entry<String, Object> entry = (Entry<String, Object>) iter.next();
							try {
								retMap.put(entry.getKey(), new String(entry.getValue().toString().getBytes(IN_CHARACTER_SET), OUT_CHARACTER_SET));
							} catch (UnsupportedEncodingException e) {
								e.printStackTrace();
								logger.info(e.getMessage());
							}
						}
					}
				}
				return retMap;
			}
		}
		
		return egovMap;
	}
	
	
	/**
	 * ResultSet 변환 : String
	 * @param str
	 * @return String
	 */
	public String convertResultSet(String str) {
		if(CHARACTER_SET_CHECK) {
			String retVal = "";
			try {
				if(str != null) retVal = new String(str.getBytes(IN_CHARACTER_SET), OUT_CHARACTER_SET); 
			} catch (Exception e) {
				e.printStackTrace();
				logger.info(e.getMessage());
			}
			return retVal;
		}
		
		return str;
	}
	
	
	/**
	 * ResultSet 변환 : List<EgovMap>
	 * @param list
	 * @return List<EgovMap>
	 */
	public List<EgovMap> convertResultSet(List<EgovMap> list) {
		if(CHARACTER_SET_CHECK) {
			List<EgovMap> retList = new ArrayList<EgovMap>();
			for(int i=0; i<list.size(); i++) {
				EgovMap temp = list.get(i);
				if(temp != null) {
					retList.add(convertResultSet(list.get(i)));
				}
			}
			
			return retList;
		}
		
		return list;
	}
	
	
	/**
	 * IN_CHARACTER_SET return
	 * @return
	 */
	public String getInCharacterSet() {
		return this.IN_CHARACTER_SET;
	}
	
	
	/**
	 * OUT_CHARACTER_SET return
	 * @return
	 */
	public String getOutCharacterSet() {
		return this.OUT_CHARACTER_SET;
	}
	
	
	
	
	
	
}
