package peg.board.web;

import java.security.Principal;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import peg.board.common.MailHandler;
import peg.board.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	MemberService service;
	@Resource(name = "mailHandler")
	private MailHandler mailHandler;

	@RequestMapping(value = "/loginFail", method = RequestMethod.POST)
	public String memberLogin() throws Exception {
		return "errorTest";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	@RequestMapping(value = "/userPage/member", method = RequestMethod.GET)
	public String testuserpage(Principal principal) {
		String user_id = principal.getName();
		System.out.println("사용자 아이디 : " + user_id);
		return "onlymember";
	}

	@RequestMapping(value = "/adminPage/member", method = RequestMethod.GET)
	public String testadminpage() {
		System.out.println("메일 전송 테스트");
		String msg = mailHandler.sendMail();
		System.out.println("메일 전송 테스트 : " + msg);
		return "onlyadmin";
	}

	@RequestMapping(value = "/page/test", method = RequestMethod.GET)
	public String testpage() {
		return "anybodyok";
	}
}

/*
 * Spring security
 * 
 * pricipal을 ID, Credential을 비밀번호로 사용하는 인증방식 사용. 인증방식에 따라 다른 필터 사용
 * 
 * 로그인 요청 수신 -> AuthenticationFilter가 요청을 받아
 * UsernamePasswordAuthenticationToken을 생성 이 토큰은 해당 요청을 처리할 수 있는 provider을 찾는데
 * 사용. -> Authentication Manager는 List형태로 Provider를 가지고 있어 차례대로 탐색해면서 각
 * provider의 supperts메소드로 처리가 가능한 provider를 선택한다. -> UserDetailsService의
 * loadUserByUsername 메소드 수행. -> DB에서 찾은 user의 정보를 UserDetails형태로 반환. -> 인증 정보를
 * 인메모리 세션 저장소인 SecurityContextHolder에 세션 - 쿠키 방식으로 저장.
 */
