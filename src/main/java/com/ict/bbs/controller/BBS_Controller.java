package com.ict.bbs.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ict.bbs.model.vo.BBS_VO;
import com.ict.bbs.model.vo.Comment_VO;
import com.ict.bbs.service.BBS_Service;
import com.ict.common.Paging;

@Controller
public class BBS_Controller {
	@Autowired
	private BBS_Service bBS_Service;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private Paging paging;

	@RequestMapping("/bbs_list.do")
	public ModelAndView bbsList(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("bbs/list");
		
		// 전체 게시물의 수
		int count = bBS_Service.getTotalCount();
		paging.setTotalRecord(count);
		
		// 전체 페이지의 수
		if(paging.getTotalRecord() <= paging.getNumPerPage()) {
			paging.setTotalPage(1);
		}else {
			paging.setTotalPage(paging.getTotalRecord()/paging.getNumPerPage());
			if(paging.getTotalRecord()%paging.getNumPerPage() != 0) {
				paging.setTotalPage(paging.getTotalPage() + 1);
			}
		}
		// 현재 페이지 
		String cPage = request.getParameter("cPage");
		if(cPage == null) {
			paging.setNowPage(1);
		}else {
			paging.setNowPage(Integer.parseInt(cPage));
		}
		
		// begin, end 대시 limit, offset
		// limit = paging.getNumPerPage()
		
		// offset = limit * (현재페이지-1);
		paging.setOffset(paging.getNumPerPage() * (paging.getNowPage()-1));
		
		// 시작블록과 끝블록 구하기
		paging.setBeginBlock((int)((paging.getNowPage()-1) / paging.getPagePerBlock()) 
				* paging.getPagePerBlock() + 1);
		
		paging.setEndBlock(paging.getBeginBlock() + paging.getPagePerBlock() - 1);
		
		// 주의사항 
		if(paging.getEndBlock() > paging.getTotalPage()) {
			paging.setEndBlock(paging.getTotalPage());
		}
		
		List<BBS_VO> bbs_list = bBS_Service.getList(paging.getOffset(), paging.getNumPerPage());
		mv.addObject("bbs_list", bbs_list);
		mv.addObject("paging", paging);
		return mv;
	}

	@GetMapping("/bbs_insertForm.do")
	public ModelAndView bbsInsertForm(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("bbs/write");
		return mv;
	}

	@PostMapping("/bbs_insert.do")
	public ModelAndView bbsInsert(BBS_VO bvo, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("redirect:/bbs_list.do");
		try {
			String path = request.getSession().getServletContext().getRealPath("/resources/images");
			MultipartFile file = bvo.getFile();
			if (file.isEmpty()) {
				bvo.setF_name("");
			} else {
				// 같은 이름의 파일 없도록 UUID 사용
				UUID uuid = UUID.randomUUID();
				String f_name = uuid.toString() + "_" + bvo.getFile().getOriginalFilename();
				bvo.setF_name(f_name);

				// 이미지 저장
				byte[] in = bvo.getFile().getBytes();
				File out = new File(path, f_name);
				FileCopyUtils.copy(in, out);
			}
			// 패스워드 암호화
			bvo.setPwd(passwordEncoder.encode(bvo.getPwd()));
			int res = bBS_Service.getInsert(bvo);
			if (res > 0) {
				return mv;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	@GetMapping("/bbs_onelist.do")
	public ModelAndView bbsOneList(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("bbs/onelist");
		String b_idx = request.getParameter("b_idx");
		String cPage = request.getParameter("cPage");
		
		// 조회수 업데이트 
		int res = bBS_Service.getHitUpdate(b_idx);
		
		// 상세보기 
		BBS_VO bvo = bBS_Service.getOneList(b_idx);
		
		// 댓글 가져오기 
		List<Comment_VO> c_list = bBS_Service.getCommList(b_idx);
		
		mv.addObject("c_list", c_list);
		mv.addObject("bvo", bvo);
		mv.addObject("cPage", cPage);
		return mv;
	}
	/*
	@PostMapping("/com_insert.do")
	public ModelAndView commentInsert(Comment_VO cvo, 
			HttpServletRequest request) {
		String cPage = request.getParameter("cPage");
		String b_idx = request.getParameter("b_idx");
		ModelAndView mv = new ModelAndView("redirect:/bbs_onelist.do?b_idx="+b_idx+"&cPage="+cPage);
		int result = bBS_Service.getCommInsert(cvo);
		return mv;
	}
	*/
	// @ModelAttribute("cPage")String cPage,
	// 파라미터 cPage를 받아서  model에 cPage라는 이름으로 저장된다.
	// 다음에 넘어갈 페이지에게 전달
	 @PostMapping("/com_insert.do")
	 public ModelAndView commentInsert(Comment_VO cvo, 
			@ModelAttribute("cPage")String cPage, @ModelAttribute("b_idx")String b_idx) {
		ModelAndView mv = new ModelAndView("redirect:/bbs_onelist.do");
		int result = bBS_Service.getCommInsert(cvo);
		return mv;
	}
	
}

















