package peg.board.web;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import peg.board.common.PageMaker;
import peg.board.service.BoardService;
import peg.board.vo.BoardVO;
import peg.board.vo.FileVO;
import peg.board.vo.Page;

@Controller
public class BoardController {
	/* 게시글 작성, 수정, 삭제 -> 기본적으로 인증이 된 사용자가 사용함
	 * controller에 하나하나 안 하는 방법?
	 *  -> url 패턴화하여 filter를 이용해 한번에 제어 ?
	 */
	@Autowired
	BoardService service;
	
	//@PreAuthorize("hasRole('ROLE_ADMIN')") 관리자 권한을 가진 사용자만 접근 가능
	@PreAuthorize("isAuthenticated()") //인증된 사용자는 모두 접근할 수 있음.
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ModelAndView boardList(Page page) {
		ModelAndView mav = new ModelAndView("boardlist");
		PageMaker pagemaker = new PageMaker();
		pagemaker.setPage(page);
		int boardcount = service.boardcount(page);
		pagemaker.setTotalCount(boardcount);
		
		List<BoardVO> list = service.boardlist(page);
		
		mav.addObject("list",list);
		mav.addObject("pageMaker", pagemaker);
		mav.addObject("keyword",page);
		return mav;
	}
	
	/*
	 * 게시글 조회시 비밀글 여부를 검사할 방법?
	 * 1. 게시글 클릭시 secret을 가져와서 검사
	 * 2. returnObject 검사 -> service에서 검사? -> https://syaku.tistory.com/292
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/boardContent/{idx}", method = RequestMethod.GET)
	public ModelAndView boardContents(@PathVariable("idx") int idx, Model model) {
		ModelAndView mav = new ModelAndView("boardcontent");
		service.countUpadte(idx);
		BoardVO vo= service.boardContents(idx);
		List<String> file = service.filelist(idx);
		mav.addObject("vo",vo);
		mav.addObject("file",file);
		return mav;
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/boardInsertForm", method = RequestMethod.GET)
	public String boardInsertForm() {
		return "boardInsertForm";
	}
	
	//MultipartHttpServletRequest : ServletRequest를 상속받아 구현된 인터페이스. 업로드된 파일을 처리하기 위한 메소드 제공
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/boardInsert", method = RequestMethod.POST)
	public String boardInsert(BoardVO vo, MultipartHttpServletRequest multipartRequest) throws Exception {
		service.boardInsert(vo, multipartRequest);
		return "redirect:/list";
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/boardDelete")
	public String boardDelete(int idx) {
		service.boardDelete(idx);
		return "redirect:/list";
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/boardUpdateForm/{idx}", method = RequestMethod.GET)
	public String boardUpdateGet(@PathVariable("idx") int idx, Model model) {
		BoardVO vo = service.boardContents(idx);
		model.addAttribute("vo",vo);
		return "boardUpdateForm";
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/boardUpdate", method = RequestMethod.POST)
	public String boardUpdatePost(BoardVO vo) {
		service.boardUpdate(vo);
		return "redirect:/list";
	}
	
	@RequestMapping(value = "filedownload/{filename}")
	public void downloadfile(@PathVariable("filename") String filename, HttpServletResponse response) throws Exception {
		FileVO filevo = service.selectboardfile(filename);
		if(filevo != null) {
			String fileName = filevo.getOriginalName();
			String filefullname = filevo.getFilepath();
			byte[] files = FileUtils.readFileToByteArray(new File(filefullname));
			
			response.setContentType("application/octet-stream");
			response.setContentLength(files.length);
			response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(fileName, "UTF-8")+"\";");
			
			response.getOutputStream().write(files);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		}
	}
}
