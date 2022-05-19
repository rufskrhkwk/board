package peg.project.sample.web;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import peg.project.sample.service.SampleService;
import peg.project.sample.vo.SampleVO;


/**
 * @Class Name	: SampleController.java
 * @Description : 
 
 * @Modification Information
 *	  Date		  Modifier		Comment
 *  ----------   ----------    -------------------
 *  2018.03.21	  박진우 		최초생성
 *
 * @author	박진우(jwpark@pegsystem.co.kr)
 * @since	2018.03.21
 * @version	1.0
 * @see
 * Copyright (C) PEGSYSTEM <http://www.pegsystem.co.kr>
 */
@Controller
public class SampleController {

	
	
	
	
	@Resource(name="sampleService")
	private SampleService service;
	
	
	
	/**
	 * 조회조건에 해당하는 코드 리스트 조회 후 반환
	 * @param	param: 조회조건(String cd_nm, String use_yn)
	 * @return	list : 코드리스트(List<EgovMap>)
	 * @return
	 */
	@RequestMapping(value="/sample")
	public String sample(@RequestParam Map<String, Object> param, Model model) {
		model.addAttribute("sample", service.selectCodeList(param));
		model.addAttribute("param", param);
		return "sample/main";
	}
	

	
	@RequestMapping(value="/aaaaa")
	public String aaaaa(@ModelAttribute("sampleVO")SampleVO sampeVO, Model model) {
		model.addAttribute("sample", service.selectCodeList2(sampeVO));
		model.addAttribute("param", sampeVO);
		return "sample/aaaaa";
	}
	
}
