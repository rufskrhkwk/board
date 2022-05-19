package peg.project.test.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import peg.project.test.service.TestService;
import peg.project.test.vo.DemoVO;

@Controller
public class TestController {

	@Resource(name="testService")
	private TestService service;
	
	
	@RequestMapping(value="/test01")
	public ModelAndView test01() {
		return new ModelAndView("demo.tiles").addObject("example", service.selectCodeList());
	}
	
	
	@RequestMapping(value="/test02")
	public ModelAndView test02() {
		return new ModelAndView("demo").addObject("example", service.selectCodeList());
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
	@RequestMapping(value="/test03")
	public String test03(@ModelAttribute("test")DemoVO sample) {
		return "valid";
	}
}
