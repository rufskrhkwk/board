package pegsystem.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class PegInterceptor extends HandlerInterceptorAdapter {

	
	
	
	
	/**
	 * 컨트롤러 메소드 실행 직전에 수행됩니다.
	 * true 를 반환하면 계속 진행이 되고 false 를 리턴하면 실행 체인(다른 인터셉터, 컨트롤러 실행)이 중지되고 반환 됩니다.
	 * 필터의 응답 처리가 있다면 그것은 실행이 됩니다.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//		System.out.println(">>>>>>>>>>>>>>>> 1. PegInterceptor preHandle");
		return true;
	}

	
	
	/**
	 * 컨트롤러 메소드 실행 직후에 실행 됩니다.
	 * View 페이지가 렌더링 되기전에 ModelAndView 객체를 조작 할 수 있습니다.
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//		System.out.println(">>>>>>>>>>>>>>>> 2. PegInterceptor postHandle");
	}

	
	
	/**
	 * View 페이지가 렌더링 되고 난 후 실행됩니다.
	 * View 작업까지 완료된 후 Client에 응답하기 바로 전에 호출됩니다.
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//		System.out.println(">>>>>>>>>>>>>>>> 3. PegInterceptor afterCompletion");
	}

	
	
	/**
	 * Servlet 3.0 부터 비동기 요청이 가능해 졌습니다.
	 * 비동기 요청시 postHandle와 afterCompletion 은 실행되지 않고, 이 메소드가 실행됩니다.
	 */
//	@Override
//	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//		System.out.println(">>>>>>>>>>>>>>>> 4. PegInterceptor afterConcurrentHandlingStarted");
//	}
}
