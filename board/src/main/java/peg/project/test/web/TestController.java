package peg.project.test.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

	/*
	 * @Resource(name="testService") private TestService service;
	 */
	
	
	@RequestMapping(value="/test01")
	public String test01() {
		return "boardlist";
	}
	
	
	
	@RequestMapping(value="/validator")
	public String validator() {
		return "cmmn/validator";
	}
	
	
	
	/**
	 * @ModelAttribute의 value와 commandName이 일치해야 한다.
	 * @param sample
	 * @return
	 */
	/*
	 * @RequestMapping(value="/test03") public String
	 * test03(@ModelAttribute("test")DemoVO sample) { return "valid"; }
	 */
}
