package peg.board.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Page {
	private int currentPage; //현재페이지 번호
	private int perPageNum; //한페이지당 보여줄 게시글의 수
	private String keyword; //검색어
	private String searchType; // 제목, 작성자, 내용 중 어떤 것으로 검색할지 
	
	public int getPageStart() { //페이지의 게시글 시작 번호
		return (this.currentPage-1)*perPageNum;
	}
	
	public Page() { //최초 게시판 접근시 기본 셋팅
		this.currentPage=1;
		this.perPageNum=5;
	}

	public void setPage(int page) { //페이지값이 음수가 되지 않게 설정
		if(page <= 0) {
			this.currentPage = 1;
		} else {
			this.currentPage = currentPage;
		}
	}

	public void setPerPageNum(int pageCount) { //페이지당 보여줄 게시글 수가 변하지 않도록 설정
		int cnt = this.perPageNum;
		if(pageCount != cnt) {
			this.perPageNum = cnt;
		} else {
			this.perPageNum = pageCount;
		}
	}
}
