package peg.board.web;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;
import peg.board.common.PageMaker;
import peg.board.service.BoardService;
import peg.board.vo.BoardVO;
import peg.board.vo.FileVO;
import peg.board.vo.Page;

@Controller
@Slf4j
public class BoardController {
	
	@Autowired
	BoardService service;
	
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PreAuthorize("isAuthenticated()")
	@RequestMapping("list")
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

	
	@RequestMapping("/boardContent/{idx}")
	public ModelAndView boardContents(@PathVariable("idx") int idx, Model model) {
		ModelAndView mav = new ModelAndView("boardcontent");
		service.countUpadte(idx);
		BoardVO vo= service.boardContents(idx);
		List<String> file = service.filelist(idx);
		mav.addObject("vo",vo);
		mav.addObject("file",file);
		return mav;
	}
	
	@RequestMapping("/boardInsertForm")
	public String boardInsertForm() {
		return "boardInsertForm";
	}
	
	//MultipartHttpServletRequest : ServletRequest를 상속받아 구현된 인터페이스. 업로드된 파일을 처리하기 위한 메소드 제공
	@RequestMapping("/boardInsert")
	public String boardInsert(BoardVO vo, MultipartHttpServletRequest multipartRequest) throws Exception {
		service.boardInsert(vo, multipartRequest);
		return "redirect:/list";
	}
	
	@RequestMapping("/boardDelete")
	public String boardDelete(int idx) {
		service.boardDelete(idx);
		return "redirect:/list";
	}
	
	@RequestMapping("/boardUpdateForm/{idx}")
	public String boardUpdateGet(@PathVariable("idx") int idx, Model model) {
		BoardVO vo = service.boardContents(idx);
		model.addAttribute("vo",vo);
		return "boardUpdateForm";
	}
	
	@RequestMapping("/boardUpdate")
	public String boardUpdatePost(BoardVO vo) {
		service.boardUpdate(vo);
		return "redirect:/list";
	}
	
	@RequestMapping("filedownload/{filename}")
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
