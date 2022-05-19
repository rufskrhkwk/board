package peg.core.spring;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springmodules.validation.commons.DefaultBeanValidator;
import org.springmodules.validation.commons.DefaultValidatorFactory;
import org.springmodules.validation.commons.ValidatorFactory;

@Configuration
public class ContextValidator {
	
	@Autowired
	private ApplicationContext ac;
	@Autowired
	private ServletContext sc;
	
	
	@Bean
	public DefaultBeanValidator beanValidator(ValidatorFactory validatorFactory){
		DefaultBeanValidator defaultBeanValidator = new DefaultBeanValidator();
		defaultBeanValidator.setValidatorFactory(validatorFactory);
		return defaultBeanValidator;
	}
	
	
	@Bean
	public DefaultValidatorFactory validatorFactory(){
		List<Resource> temp = new ArrayList<Resource>();
		temp.add(ac.getResource("classpath:jf-core/validator/com-rules.xml"));
		temp.add(ac.getResource("classpath:jf-core/validator/validator-rules.xml"));
		
		String rPath = sc.getRealPath("/WEB-INF/classes/jf-core/validator/let");
		File rFolder = new File(rPath);
		if(rFolder.exists()) {
			if(rFolder.isDirectory()) {
				File[] rFiles = rFolder.listFiles();
				if(rFiles.length > 0) {
					for(int i=0; i<rFiles.length; i++) {
						temp.add(ac.getResource("classpath:jf-core/validator/let/" + rFiles[i].getName()));
					}
				}
			}
		}
		
		Resource[] resources = new Resource[temp.size()];
		for(int i=0; i<temp.size(); i++) {
			resources[i] = temp.get(i);
		}
		
//		Resource[] resources = {
//								ac.getResource("classpath:jf-core/validator/com-rules.xml")
//							   ,ac.getResource("classpath:jf-core/validator/validator-rules.xml")
//							   ,ac.getResource("classpath:jf-core/validator/let/*.xml")
//							   };
		DefaultValidatorFactory defaultValidatorFactory = new DefaultValidatorFactory();
		defaultValidatorFactory.setValidationConfigLocations(resources);
		
		
		return defaultValidatorFactory;
	}
	
	
	
	
	
	
	
}