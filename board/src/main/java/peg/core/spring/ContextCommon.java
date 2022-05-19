package peg.core.spring;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;
import org.jasypt.spring31.properties.EncryptablePropertyPlaceholderConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import egovframework.rte.fdl.cmmn.trace.LeaveaTrace;
import egovframework.rte.fdl.cmmn.trace.handler.DefaultTraceHandler;
import egovframework.rte.fdl.cmmn.trace.handler.TraceHandler;
import egovframework.rte.fdl.cmmn.trace.manager.DefaultTraceHandleManager;
import egovframework.rte.fdl.cmmn.trace.manager.TraceHandlerService;
import pegsystem.util.CharacterSetConvertUtil;


@Configuration
@ComponentScan(basePackages="peg"
			  ,includeFilters={@ComponentScan.Filter(type=FilterType.ANNOTATION, value=Service.class)
			  				  ,@ComponentScan.Filter(type=FilterType.ANNOTATION, value=Repository.class)}
		   	  ,excludeFilters={@ComponentScan.Filter(type=FilterType.ANNOTATION, value=Controller.class)
		   	  				  ,@ComponentScan.Filter(type=FilterType.ANNOTATION, value=Configuration.class)}
			  )
public class ContextCommon {
	
	@Bean
	public ReloadableResourceBundleMessageSource messageSource(){
		ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource = new ReloadableResourceBundleMessageSource();
		reloadableResourceBundleMessageSource.setBasenames(new String[]{"classpath:/jf-core/message/message-common"
																	   ,"classpath:/egovframework/rte/fdl/idgnr/messages/idgnr"
																	   ,"classpath:/egovframework/rte/fdl/property/messages/properties"});
		reloadableResourceBundleMessageSource.setCacheSeconds(60);
		return reloadableResourceBundleMessageSource;
	}
	
	
	@Bean
	public LeaveaTrace leaveaTrace(DefaultTraceHandleManager traceHandlerService) {
		LeaveaTrace leaveaTrace = new LeaveaTrace();
		leaveaTrace.setTraceHandlerServices(new TraceHandlerService[]{traceHandlerService});
		return leaveaTrace;
	}
	
	
	@Bean
	public DefaultTraceHandleManager traceHandlerService(AntPathMatcher antPathMater, DefaultTraceHandler defaultTraceHandler){
		DefaultTraceHandleManager defaultTraceHandleManager = new DefaultTraceHandleManager();
		defaultTraceHandleManager.setReqExpMatcher(antPathMater);
		defaultTraceHandleManager.setPatterns(new String[]{"*"});
		defaultTraceHandleManager.setHandlers(new TraceHandler[]{defaultTraceHandler});
		return defaultTraceHandleManager;
	}
	
	
	@Bean
	public AntPathMatcher antPathMater(){
		AntPathMatcher antPathMatcher = new AntPathMatcher();
		return antPathMatcher;
	}
	
	
	@Bean
	public DefaultTraceHandler defaultTraceHandler(){
		DefaultTraceHandler defaultTraceHandler = new DefaultTraceHandler();
		return defaultTraceHandler;
	}
	
	
	@Bean
	 public StandardPBEStringEncryptor configurationEncryptor() {
		EnvironmentStringPBEConfig environmentVariablesConfiguration = new EnvironmentStringPBEConfig();
		environmentVariablesConfiguration.setAlgorithm("PBEWithMD5AndDES");
		environmentVariablesConfiguration.setPassword("PEG_JASYPT_PASS");
		
		StandardPBEStringEncryptor configurationEncryptor = new StandardPBEStringEncryptor();
		configurationEncryptor.setConfig(environmentVariablesConfiguration);
		return configurationEncryptor;
	}
	 
	
	@Bean
	public EncryptablePropertyPlaceholderConfigurer propertyConfigurer(StandardPBEStringEncryptor configurationEncryptor) {
		EncryptablePropertyPlaceholderConfigurer propertyConfigurer = new EncryptablePropertyPlaceholderConfigurer(configurationEncryptor);
		propertyConfigurer.setLocation(new ClassPathResource("/jf-core/props/core_config.properties"));
		return propertyConfigurer;
	}
	
	
	@Bean
	public CharacterSetConvertUtil characterSetConvertUtil(@Value("${in.charaterSet}")String in_character_set
														  ,@Value("${out.charaterSet}")String out_character_set) {
		return new CharacterSetConvertUtil(in_character_set, out_character_set);
	}
	
}
