package peg.project.sample.service;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import peg.project.sample.vo.SampleVO;


/**
 * @Class Name	: SampleMapper.java
 * @Description : Sample 업무 데이터처리 Mapper Interface
 
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
@Mapper("sampleMapper")
public interface SampleMapper {
	
	
	/**
	 * p2t_cd_d 테이블 조회
	 * @param	param: 조회조건(String cd_nm, String use_yn)
	 * @return	list : 코드리스트(List<EgovMap>)
	 * @return
	 */	
	public List<EgovMap> selectCodeList(Map<String, Object> param);
	public List<EgovMap> selectCodeList2(SampleVO sampleVO);
}
