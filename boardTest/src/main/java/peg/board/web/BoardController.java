package peg.board.web;

import java.io.File;
import java.net.URLEncoder;
import java.security.Principal;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springmodules.validation.commons.DefaultBeanValidator;

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
	
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator validator;
	
	//@PreAuthorize("hasRole('ROLE_ADMIN')") 관리자 권한을 가진 사용자만 접근 가능
	@PreAuthorize("isAuthenticated()") //인증된 사용자는 모두 접근할 수 있음.
	@RequestMapping(value = "/list", method = RequestMethod.GET)
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
	public ModelAndView boardContents(@PathVariable("idx") int idx, Model model, Principal principal) {
		
				
		BoardVO vo= service.boardContents(idx);
		boolean matchResult = service.matchWriter(vo.getGroupno(), principal);
		if(vo.isSecret()) { //비밀글일때 확인
			if(!matchResult) {//false인 경우 글목록으로 돌려보냄.
				ModelAndView mav = new ModelAndView("alert");
				mav.addObject("msg", "접근 권한이 없습니다.");
				mav.addObject("url", "/list");
				return mav;
			}
		}
		ModelAndView mav = new ModelAndView("boardcontent");

		List<String> file = service.filelist(idx);
		
		mav.addObject("vo",vo);
		mav.addObject("file",file);
		mav.addObject("matchResult",matchResult);
		
		service.countUpadte(idx);//게시글의 조회수 +1
		
		return mav;
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/boardInsertForm", method = RequestMethod.GET)
	public String boardInsertForm(@ModelAttribute("boardvo") BoardVO boardvo) {
		return "boardInsertForm";
	}
	
	//MultipartHttpServletRequest : ServletRequest를 상속받아 구현된 인터페이스. 업로드된 파일을 처리하기 위한 메소드 제공
	//								클라이언트의 HTTP요청 메세지 자체를 컨트롤 하는 것.
	//multipartFile 파라미터로 받아서 컨트롤. file 데이터를 직접 다룰 수 있다.
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/boardInsert", method = RequestMethod.POST)
	public String boardInsert(@ModelAttribute("boardVO") BoardVO boardVO, MultipartHttpServletRequest multipartRequest,
			BindingResult bindingResult, Model model) throws Exception {
		
		MultipartFile files = multipartRequest.getFile("tempfile");
		
		//title 또는 contents가 비어있으면 입력되지않고 다시 insertform으로 이동한다.
		//+) long count = files.stream().filter(t -> !t.isEmpty()).count();이 2 초과하면 다시 돌려보내기.
		validator.validate(boardVO, bindingResult);
		if(bindingResult.hasErrors()) {
			model.addAttribute("boardvo",boardVO);
			return "/boardInsertForm";
		}

		
		service.boardInsert(boardVO, multipartRequest);
		return "redirect:/list";
	}
	
	
	//글 작성자와 관리자만 접근할 수 있어야한다.
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/boardDelete", method = RequestMethod.POST)
	public String boardDelete(BoardVO vo) {
		service.boardDelete(vo.getIdx());
		return "redirect:/list";
	}
	
	
	//글 수정은 작성자만 가능
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
	
	
	//답글쓰기 기능
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/replyInsert", method = RequestMethod.GET)
	public String replyInsertForm(int idx, Model model) {
		BoardVO vo = service.boardContents(idx);
		model.addAttribute("vo",vo);
		return "replyInsertForm";
	}
	
	//답글에 있는 비밀글을 읽을 권한이 있는지 check = 원글 작성자
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/secretAuth", method = RequestMethod.POST)
	@ResponseBody //RestController로 분리?
	public boolean secretAuth(int groupno, Principal principal) {
		return service.matchWriter(groupno, principal);
	}
}
