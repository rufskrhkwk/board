package peg.project.sample.service;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import peg.project.sample.vo.SampleVO;


/**
 * @Class Name	: SampleService.java
 * @Description : Sample 비즈니스로직 Interface
 
 * @Modification Information
 *	  Date		  Modifier		Comment
 *  ----------   ----------    -------------------
 *  2018.03.21	  박진우  		최초생성
 *
 * @author	박진우(jwpark@pegsystem.co.kr)
 * @since	2018.03.21
 * @version	1.0
 * @see
 * Copyright (C) PEGSYSTEM <http://www.pegsystem.co.kr>
 */
public interface SampleService {
	
	
	/**
	 * 조회조건에 해당하는 코드 리스트 조회 후 반환
	 * @param	param: 조회조건(String cd_nm, String use_yn)
	 * @return	list : 코드리스트(List<EgovMap>)
	 * @return
	 */	
	public List<EgovMap> selectCodeList(Map<String, Object> param);
	public List<EgovMap> selectCodeList2(SampleVO sampleVO);
}
