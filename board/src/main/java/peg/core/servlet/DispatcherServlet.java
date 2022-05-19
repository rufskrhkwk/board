package peg.core.servlet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="peg"
			  ,includeFilters={@ComponentScan.Filter(type=FilterType.ANNOTATION, value=Controller.class)}
	  		  ,excludeFilters={@ComponentScan.Filter(type=FilterType.ANNOTATION, value=Service.class)
	  		  				  ,@ComponentScan.Filter(type=FilterType.ANNOTATION, value=Repository.class)
	  				  		  ,@ComponentScan.Filter(type=FilterType.ANNOTATION, value=Configuration.class)}
)
@ImportResource({"classpath:jf-core/spring/context-interceptor.xml"})
public class DispatcherServlet extends WebMvcConfigurerAdapter {
	
	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	

	@Bean
	public SessionLocaleResolver localeResolver() {
		return new SessionLocaleResolver();
	}
	
	
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
		interceptor.setParamName("language");
		return interceptor;
	}
	
	
	@Bean
	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
		RequestMappingHandlerMapping rmhm = new RequestMappingHandlerMapping();
		rmhm.setInterceptors(new Object[]{localeChangeInterceptor()});
		return rmhm;
	}
	
	
	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource rbms = new ResourceBundleMessageSource();
		rbms.setBasename("jf-core/message/message-common");
		return rbms;
	}
	
	
	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer config = new TilesConfigurer();
		config.setDefinitions("classpath:/jf-core/spring/context-tiles.xml");
		return config;
	}
	
	
	@Bean
	public UrlBasedViewResolver urlBasedViewResolver() {
		UrlBasedViewResolver viewResolber = new UrlBasedViewResolver();
		viewResolber.setViewClass(TilesView.class);
		viewResolber.setRequestContextAttribute("rc");
		viewResolber.setOrder(1);
		return viewResolber;
	}
	
	
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver viewResolber = new InternalResourceViewResolver();
		viewResolber.setPrefix("/WEB-INF/jsp/");
		viewResolber.setSuffix(".jsp");
		viewResolber.setRequestContextAttribute("rc");
		viewResolber.setOrder(2);
		return viewResolber;
	}
	
	
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		return new CommonsMultipartResolver();
	}
	
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
	  configurer.enable();
//	  configurer.enable("WorkerServlet");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
