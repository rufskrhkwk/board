package peg.board.service;

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
		
		@Delete("delete from board where idx=#{idx}")
		public void boardDelete(int idx);		
		
		@Update("update board set contents=#{contents} where idx=#{idx}")
		public void boardContentUpdateAjax(BoardVO vo);
		
		@Update("update board set title=#{title}, writer=#{writer} where idx=#{idx}")
		public void boardTWUpdateAjax(BoardVO vo);
		
		@Update("update board set count = count + 1 where idx=#{idx}")
		public void countUpdate(int idx);
		
		//첨부파일
		public void addfile(FileVO vo) throws Exception;
		public List<String> fileList(int idx);
		public void updatefile(String fullName, Integer article_no) throws Exception;
		public void deletefile(int idx) throws Exception;
		public void insertfile(List<FileVO> list) throws Exception;
		public FileVO selectfile(String filename);
}
