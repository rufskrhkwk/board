package peg.board.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import peg.board.common.FileUtils;
import peg.board.mapper.BoardMapper;
import peg.board.service.BoardService;
import peg.board.vo.BoardVO;
import peg.board.vo.FileVO;
import peg.board.vo.Page;

@Service
@Slf4j
public class BoardServiceImpl implements BoardService{

	@Autowired
	BoardMapper mapper;
	
	@Autowired
	private FileUtils fileUtils;
	
	@Override
	public List<BoardVO> boardlist(Page page) {
		List<BoardVO> list = mapper.boardList(page);
		return list;
	}

	@Override
	public int boardcount(Page page) {
		return mapper.boardcount(page);
	}

	@Override
	public BoardVO boardContents(int idx) {
		BoardVO vo = mapper.boardContents(idx);
		return vo;
	}

	@Override
	public void boardInsert(BoardVO vo, MultipartHttpServletRequest multipartRequest) throws Exception {
		mapper.boardInsert(vo);
		
		
		if(multipartRequest != null) {
			List<FileVO> list = fileUtils.paseFileInfo(vo.getIdx(), multipartRequest);			
			if(CollectionUtils.isEmpty(list)==false) {
				mapper.insertfile(list);
			}
		}
		
		/* 글을 올리기전에 파일 정보가 서버로 잘 넘어오는지 테스트
		 * if(multipartRequest != null) { Iterator<String> iterator =
		 * multipartRequest.getFileNames(); String name; while(iterator.hasNext()) {
		 * name = iterator.next(); log.debug("file name : "+ name); List<MultipartFile>
		 * list = multipartRequest.getFiles(name); for(MultipartFile mfile : list) {
		 * log.debug("file name : " + mfile.getOriginalFilename());
		 * log.debug("file size: " + mfile.getSize()); log.debug("file content Type: " +
		 * mfile.getContentType()); } } }
		 */
		
	}

	@Override
	public void boardDelete(int idx) {
		mapper.boardDelete(idx);
	}

	@Override
	public void boardUpdate(BoardVO vo) {
		mapper.boardUpdate(vo);
	}

	@Override
	public void countUpadte(int idx) {
		mapper.countUpdate(idx);
	}
	
	public List<String> filelist(int idx){
		List<String> list = mapper.fileList(idx);
		return list;
	}

	@Override
	public FileVO selectboardfile(String filename) {
		return mapper.selectfile(filename);
	}

}
