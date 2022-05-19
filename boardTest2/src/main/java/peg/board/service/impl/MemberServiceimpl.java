package peg.board.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import peg.board.mapper.MemberMapper;
import peg.board.service.MemberService;
import peg.board.vo.MemberVO;

@Service
public class MemberServiceimpl implements MemberService{

	@Autowired
	MemberMapper mapper;
	
	@Override
	public MemberVO memberLogin(MemberVO member) {
		MemberVO vo = mapper.memberLogin(member);
		return vo;
	}

}
