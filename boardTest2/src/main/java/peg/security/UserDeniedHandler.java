package peg.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

public class UserDeniedHandler implements AccessDeniedHandler{
	
	/* AccessDeniedHandler
	 * 권한이 없는 페이지에 접속할 시 발생.
	 */

	private String errorPage = "/WEB-INF/jsp/egovframework/example/sample/errorPage.jsp";
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		/*
		 * req.setAttribute("errMsg",ade.getMessage()); req.getRequestDispatcher(
		 * "/WEB-INF/jsp/egovframework/example/sample/errorPage.jsp").forward(request, response);
		 */


		String ajaxHeader = request.getHeader("X-Ajax-call");
		String result = "";

		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		response.setCharacterEncoding("UTF-8");

		// null로 받은 경우는 X-Ajax-call 헤더 변수가 없다는 의미이기 때문에
		// ajax가 아닌 일반적인 방법으로 접근했음을 의미한다.
		if(ajaxHeader == null) {

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Object principal = auth.getPrincipal();

			request.setAttribute("username", principal);
			request.setAttribute(("errormsg"), accessDeniedException);
			request.getRequestDispatcher(errorPage).forward(request, response);


			result = "{\"result\" : \"fail\", \"message\" : \"" + accessDeniedException.getMessage() + "\"}";
			System.out.println("result :::: " + result);

		}else {

			if("true".equals(ajaxHeader)) {
				// ajax로 접근
				result = "{\"result\" : \"fail\", \"message\" : \"" + accessDeniedException.getMessage() + "\"}";

			}else {
				// 헤더 변수는 있으나 값이 틀린 경우이므로 헤더값이 틀렸다는 의미로 돌려준다
				result = "{\"result\" : \"fail\", \"message\" : \"Access Denied(Header Value Mismatch)\"}";

			}

			response.getWriter().print(result);
			response.getWriter().flush();

		}

	}
	
	public void setErrorPage(String errorPage) {
		if ((errorPage != null) && !errorPage.startsWith("/")) {
			throw new IllegalArgumentException("errorPage must begin with '/'");
		}
		this.errorPage = errorPage;
	}
		
}


