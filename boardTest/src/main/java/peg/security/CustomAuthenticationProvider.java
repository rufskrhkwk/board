package peg.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;

import peg.board.vo.MemberVO;

public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserDetailsService service;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		System.out.println("authentication : " + authentication);

		String loginId = String.valueOf(authentication.getPrincipal());
		String loginPw = String.valueOf(authentication.getCredentials());
		System.out.println("로그인 시켜줌 member info : " + loginId + loginPw);
		
		MemberVO vo = (MemberVO) service.loadUserByUsername(loginId);
		System.out.println("뭔데 왜 로그인이 되는 건데" + vo.getPw());
		
		if(!loginPw.equals(vo.getPw())) {
			throw new BadCredentialsException(loginId);
		}
		
		if(!vo.isEnabled()) {
			throw new BadCredentialsException(loginId);
		}
		
		return new UsernamePasswordAuthenticationToken(vo, vo, vo.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) { //token타입에 따라서 언제 provider를 사용할지 조건을 지정할 수 있다.
		// TODO Auto-generated method stub
		return true;
	}

}
