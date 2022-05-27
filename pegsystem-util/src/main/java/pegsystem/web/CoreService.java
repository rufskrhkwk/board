package pegsystem.web;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import pegsystem.util.StringUtil;


/**
 * @author	: pegsystem
 * @since	: 2018. 03.15
 * 
 * @Class Name	: CoreService.java
 * @Description	: Service 구현체에 공통을 정의한 class
 * 				  전자정부프레임워크 Service 구현체는 EgovAbstractServiceImpl을 상속 받아야 한다.
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2018.03.15  박진우      최초생성
 *
 */
public class CoreService extends EgovAbstractServiceImpl {

	
	/**
	 * EogvMap List를 HashMap으로 변환 후 return
	 * @param list
	 * @return
	 */
	protected HashMap<String, String> setListConvertToMap(List<EgovMap> list) {
		HashMap<String, String> resultMap = new HashMap<String, String>();
		for(int i=0; i<list.size(); i++) {
			String key = String.valueOf(list.get(i).get("key"));
			String value = String.valueOf(list.get(i).get("value"));
			resultMap.put(key, value);
		}
		
		return resultMap;
	}
	
	
	/**
	 * EgovMap List에서 key에 해당하는 값이 value와 같으면 map에서 삭제 후 return 
	 * @param map
	 * @param key
	 * @param value
	 * @return
	 */
	protected List<EgovMap> setEgovMapList(List<EgovMap> map, String key, String value) {
    	for(int i=0; i<map.size(); i++) {
    		Object tmp = map.get(i).get(key);
    		if(tmp != null && !"".equals(tmp)) {
    			String val = String.valueOf(tmp).toUpperCase();
    			if(val.equals(value)) map.remove(i);
    		}
    	}
    	
    	return map;
    }
	
	
	/**
	 * 여러건의 데이터를 담은 map을 나누어 List에 담아서 리턴 
	 * @param param
	 * @return
	 */
	protected List<Map<String, Object>> setParameterConvertToList(Map<String, Object> param) {
		String paramName = StringUtil.isNull(param.get("paramName"));	// input name의 prefix
		String dataName = StringUtil.isNull(param.get("dataName"));		// 추출할 데이타 컬럼명
		String indexArray = String.valueOf(param.get("indexArray"));
		String[] columnName = dataName.split(",");
		String[] idx = indexArray.split(",");
		Arrays.sort(idx);	// index 오름차순 정렬
		
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		for(int i=0; i<idx.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			for(int j=0; j<columnName.length; j++) {
				String name = columnName[j];
				Object value = param.get(paramName + "[" + idx[i] + "]." + name);
				map.put(name, StringUtil.isNull(value));
			}
			result.add(map);
		}
		
		return result;
	}
}
