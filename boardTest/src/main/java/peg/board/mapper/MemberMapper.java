package peg.board.mapper;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import peg.board.vo.MemberVO;

@Mapper
public interface MemberMapper {
	public MemberVO memberLogin(MemberVO member);
	public MemberVO getMemberById(String MemberId);
}
