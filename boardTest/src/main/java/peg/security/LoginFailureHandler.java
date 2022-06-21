package peg.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class LoginFailureHandler implements AuthenticationFailureHandler{
	
	private String defaultUrl = "/";



	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String errorMsg = null;
		
		if(exception instanceof BadCredentialsException || exception instanceof InternalAuthenticationServiceException) {
			errorMsg = "아이디나 비밀번호가 맞지 않습니다. 다시 확인해주세요.";
		}else if(exception instanceof DisabledException) {
			errorMsg = "계정이 비활성화 상태입니다. 관리자에게 문의해주세요";
		}else {
			errorMsg = "로그인에 실패하였습니다. 관리자에게 문의해주세요.";		
		}
		
		System.out.println("에러 메세지 테스트 : " + errorMsg);
		
		ClearAuthenticationAttributes(request);
		
		request.setAttribute("errorMsg", errorMsg);
		request.getRequestDispatcher(defaultUrl).forward(request, response);	
	}
	
	protected void ClearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		session.invalidate();
	}

}
