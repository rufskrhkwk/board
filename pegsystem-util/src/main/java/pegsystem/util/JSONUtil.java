package pegsystem.util;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JSONUtil {

	
	
	
	
	
	/**
	 * List<EgovMap> 을 JSONArray 로 변환
	 * @param list
	 * @return
	 */
	public static JSONArray asdf(List<EgovMap> list) {
		JSONArray jsonArray = new JSONArray();
		for(EgovMap egovMap : list) {
			JSONObject json = convertEgovMapToJSONObject(egovMap);
			jsonArray.add(json);
		}
		return jsonArray;
	}
	
	
	/**
	 * EgovMap 을 JSONObject 로 변환
	 * javascript에서 따옴표 때문에 오류가 발생할 수 있기 때문에 HTML TAG로 치환한다. 
	 * @param egovMap
	 * @return
	 */
    public static JSONObject convertEgovMapToJSONObject(EgovMap egovMap) {
    	EgovMap replaceMap = new EgovMap();
    	if(egovMap != null) {
    		for(Object key : egovMap.keySet()) {
    			String value = StringUtil.isNull(egovMap.get(key));
    			if(value.indexOf("\"") > 0) { value = value.replaceAll("\"", "&quot;"); }
    			if(value.indexOf("'") > 0)  { value = value.replaceAll("'", "&#39;"); }
    			replaceMap.put(key, value);
    		}
    	}
    	JSONObject json = JSONObject.fromObject(replaceMap);
    	return json;
    }
    
    
    
	/**
	 * List<EgovMap> 을 JSONArray 로 변환
	 * @param list
	 * @return
	 */
	public static JSONArray convertListToJSONArray(List<EgovMap> list) {
		JSONArray jsonArray = new JSONArray();
		for(EgovMap egovMap : list) {
			JSONObject json = convertEgovMapToJSONObject(egovMap);
			jsonArray.add(json);
		}
		return jsonArray;
	}
	

	/**
	 * object를 json 형식의 string으로 변환
	 * object type : map, vo
	 * @param param
	 * @return
	 */
	public static String convertObjectToJson(Object param) {
		String result = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			result = mapper.writeValueAsString(param); 
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
}
