package peg.board.vo;

import lombok.Data;

@Data
public class BoardVO {
	private int idx;//번호
	private String title;//제목
	private String contents;//내용
	private String writer;//작성자
	private String indate;//작성일
	private String count;//조회수
	private boolean secret;//비밀글 여부
	//비밀글 여부
	//private String filename;//첨부파일 1
	//private String filename2;//첨부파일 2
}
