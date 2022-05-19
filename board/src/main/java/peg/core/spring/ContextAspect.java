package peg.core.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import peg.core.spring.aop.AopLogTransfer;
import pegsystem.web.aop.LogAop;


@Configuration
@EnableAspectJAutoProxy
public class ContextAspect {

	
	@Bean
	public LogAop logAop() {
		return new LogAop();
	}
	
	
	@Bean
	public AopLogTransfer aopLogTransfer(LogAop logAop) {
		AopLogTransfer aopLogTransfer = new AopLogTransfer();
//		aopLogTransfer.setLogAop(logAop);
		return aopLogTransfer;
	}
}
