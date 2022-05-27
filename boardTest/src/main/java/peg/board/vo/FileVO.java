package peg.board.vo;

import lombok.Data;

@Data
public class FileVO {
	private int idx;		//게시글 번호
	private String originalName; //원래 파일 이름
	private String filename;	//서버에 저장된 파일의 고유한 이름
	private String filepath; //서버에 저장된 위치
	//삭제 여부 -> 파일 수정시 원본 파일은 삭제 시키지 않고 디비에서 확인할 수 있도록
}
