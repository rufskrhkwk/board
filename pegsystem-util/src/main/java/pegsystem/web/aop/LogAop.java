package pegsystem.web.aop;

import org.aspectj.lang.ProceedingJoinPoint;

public class LogAop {
	 
    public Object loggerAop(ProceedingJoinPoint joinpoint) throws Throwable{
        // 공통 기능이 적용되는 메서드가 어떤 메서드인지 출력하기 위해 메서드명을 얻어옴
        String signatureStr = joinpoint.getSignature().toShortString();
        
        // 공통기능(전처리)
        System.out.println(signatureStr + " 시작.");
        System.out.println(System.currentTimeMillis());
        
        try {
        	// 메서드 실행(핵심기능)
            Object obj = joinpoint.proceed();
            return obj;
        } finally {
            //공통기능(후처리)
            System.out.println(System.currentTimeMillis());
            System.out.println(signatureStr + " 종료.");
        }
    }
}