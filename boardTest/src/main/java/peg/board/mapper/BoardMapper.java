package peg.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import peg.board.vo.BoardVO;
import peg.board.vo.FileVO;
import peg.board.vo.Page;


@Mapper
public interface BoardMapper {
	
		public List<BoardVO> boardList(Page page);
		public int boardcount(Page page);
		public int boardInsert(BoardVO vo);
		public BoardVO boardContents(int idx);
		public void boardUpdate(BoardVO vo);
		public void boardDelete(int idx);		
		public void boardTWUpdateAjax(BoardVO vo);
		public void countUpdate(int idx);
		
		//첨부파일
		public void addfile(FileVO vo) throws Exception;
		public List<String> fileList(int idx);
		public void updatefile(String fullName, Integer article_no) throws Exception;
		public void deletefile(int idx) throws Exception;
		public void insertfile(List<FileVO> list) throws Exception;
		public FileVO selectfile(String filename);
		
		//원글 작성자 찾기
		public String findWriter(int groupno);
}
