package peg.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import peg.board.mapper.MemberMapper;
import peg.board.vo.MemberVO;

public class CustomUserDetailsService implements UserDetailsService {
	/* 유저의 정보를 가져오는 인터페이스.
	 */

	@Autowired
	MemberMapper mapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//유저의 정보를 불러와서 UserDetails로 리턴.
		MemberVO vo = mapper.getMemberById(username);

		if (vo == null) {
			throw new UsernameNotFoundException(username);
		}

		if (vo != null) {
			System.out.println("로그인 성공");
		}
		return vo;
	}

}
