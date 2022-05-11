package peg.project.test.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import peg.project.test.vo.BoardVO;
import peg.project.test.vo.FileVO;
import peg.project.test.vo.Page;

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
