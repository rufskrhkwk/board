package peg.security;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	private String loginId;
	private String defaultUrl;
	private RequestCache requestCache = new HttpSessionRequestCache();
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		System.out.println("로그인 정보가 담겨있는지 확인 : "+authentication.getAuthorities().toString());
		ClearAuthenticationAttributes(request);
		resultRedirectStrategy(request, response, authentication);
	}
	
	public String getLoginId() {
		return loginId;
	}
	
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	
	public String getDefaultUrl() {
		return defaultUrl;
	}
	
	public void setDefaultUrl(String defaultUrl) {
		this.defaultUrl = defaultUrl;
	}
	
	/*
	 * 로그인을 하는 과정에서 로그인에 싱패를 한 경우 에러가 세션에 저장되어 남아있게 된다.
	 * 에러 세션을 지우는 작업을 해준다.
	 * 세션에 에러가 있는 경우에만 세션을 받아와 지우는 작업을 하는 메소드.
	 */
	protected void ClearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session==null) {
			return;
		}else {
			session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		}
	}

	
	/*
	 * 로그인에 성공한 사용자를 이동시킨다. 
	 * 단, 인증이 필요한 페이지에 접근한 경우와 로그인 페이지로 바로 접근한 경우를 분기 처리 해준다.
	 */
	
	protected void resultRedirectStrategy(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		
		String targetUrl = "/";
		String role = authentication.getAuthorities().toString();
		System.out.println("권한 확인 : " + role);
		//System.out.println("사용자 권한 : " + role);
		/*
		 * if(savedRequest!=null) { //인증 권한이 필요한 페이지에 접근했을 경우 //로그인 화면을 보기 전에 있던 URL정보를
		 * 가져온다. 
		 * //savedRequest.getRedirectUrl(); redirectStrategy.sendRedirect(request,
		 * response, targetUrl); } else { redirectStrategy.sendRedirect(request,
		 * response, defaultUrl); }
		 */
		if(role.equals("[ROLE_USER]")) {
			targetUrl = "/userPage/member";
			redirectStrategy.sendRedirect(request, response, targetUrl);
		}else if(role.equals("[ROLE_ADMIN]")){
			targetUrl = "/adminPage/member";
			redirectStrategy.sendRedirect(request, response, targetUrl);
		}
	}

}
