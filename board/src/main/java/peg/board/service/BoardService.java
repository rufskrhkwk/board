package peg.board.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import peg.board.vo.BoardVO;
import peg.board.vo.FileVO;
import peg.board.vo.Page;



public interface BoardService {
	
	public List<BoardVO> boardlist(Page page);
	public int boardcount(Page page);
	public BoardVO boardContents(int idx);
	public void boardInsert(BoardVO vo, MultipartHttpServletRequest multipartRequest) throws Exception;
	public void boardDelete(int idx);
	public void boardUpdate(BoardVO vo);
	public void countUpadte(int idx);
	public List<String> filelist(int idx);
	public FileVO selectboardfile(String filename);
}
