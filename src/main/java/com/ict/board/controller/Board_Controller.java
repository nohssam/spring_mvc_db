package com.ict.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ict.board.model.service.Board_Service;
import com.ict.board.model.vo.Board_VO;
import com.ict.common.Paging;

@Controller
public class Board_Controller {
	@Autowired
	private Board_Service board_Service;
	@Autowired
	private Paging paging;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@RequestMapping("/board_list.do")
	public ModelAndView boardList(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("board/board_list");
		// 페이징 기법
		int count = board_Service.getTotalCount();
		paging.setTotalRecord(count);
		
		if(paging.getTotalRecord() <= paging.getNumPerPage()) {
			paging.setTotalPage(1);
		}else {
			paging.setTotalPage(paging.getTotalRecord() / paging.getNumPerPage());
			if(paging.getTotalRecord() % paging.getNumPerPage() != 0) {
				paging.setTotalPage(paging.getTotalPage() +1);
			}
		}
		
		String cPage = request.getParameter("cPage");
		if(cPage == null) {
			paging.setNowPage(1);
		}else {
			paging.setNowPage(Integer.parseInt(cPage));
		}
		
		// begin, end 대신 limit, offset
		// limit = paging.getNumPerPage()
		// offset = limit * (paging.getNowPage()-1)
		paging.setOffset(paging.getNumPerPage() * (paging.getNowPage()-1));
		
		// 시작블록, 끝블록
		paging.setBeginBlock((int)((paging.getNowPage()-1)/paging.getPagePerBlock()) * paging.getPagePerBlock()+1);
		paging.setEndBlock(paging.getBeginBlock() + paging.getPagePerBlock() -1);
		
		// 주의 사항 (endblock 이 totalpage 보다 클때가 있다.)
		if(paging.getEndBlock() > paging.getTotalPage()) {
			paging.setEndBlock(paging.getTotalPage());
		}
		
		List<Board_VO> board_list = board_Service.getList(paging.getOffset(), paging.getNumPerPage());
		mv.addObject("board_list", board_list);
		mv.addObject("paging", paging);
		return mv;
	}
	
	@GetMapping("/board_insertForm.do")
	public ModelAndView boardInsertForm() {
		return new ModelAndView("board/board_write");
	}
	
	@PostMapping("/board_insert.do")
	public ModelAndView boardInsert(Board_VO bv, HttpServletRequest request) {
		ModelAndView mv= new ModelAndView("redirect:/board_list.do");
		try {
			String path = request.getSession().getServletContext().getRealPath("/resources/images");
			MultipartFile file = bv.getFile();
			if(file.isEmpty()) {
				bv.setF_name("");
			}else {
				// 같은이름 없도록 UUID 사용
				UUID uuid = UUID.randomUUID();
				String f_name = uuid.toString() + "_" + bv.getFile().getOriginalFilename();
				bv.setF_name(f_name);
				
				// 이미지 저장
				byte[] in = bv.getFile().getBytes();
				File out = new File(path, f_name);
				FileCopyUtils.copy(in, out);
			}
			
			// 패스워드 암호화 
			bv.setPwd(passwordEncoder.encode(bv.getPwd()));
			int res = board_Service.getInsert(bv);
			return mv;
		} catch (Exception e) {
		}
		return null;
	}
	
	
	@GetMapping("/board_onelist.do")
	public ModelAndView boardonelist(@ModelAttribute("cPage")String cPage,
			@ModelAttribute("idx")String idx) {
		ModelAndView mv = new ModelAndView("board/onelist");
		// hit 업데이트 
		int hit = board_Service.getBoardHit(idx);
		
		// 상세보기 
		Board_VO bv  = board_Service.getBoardOneList(idx);
		
		mv.addObject("bv", bv);
		return mv;
	}
	
	@GetMapping("/board_down.do")
	public void boardDown(@RequestParam("f_name")String f_name,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			String path = request.getSession().getServletContext().getRealPath("/resources/images/"+f_name);
			String r_path = URLEncoder.encode(path,"utf-8");
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition", "attachment; filename= " + r_path);
			
			File file = new File(new String(path.getBytes()));
			FileInputStream in = new FileInputStream(file);
			OutputStream out = response.getOutputStream();
			FileCopyUtils.copy(in, out);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@PostMapping("/board_ans_insertForm.do")
	public ModelAndView boardAnsInsertForm(@ModelAttribute("cPage")String cPage,
			@ModelAttribute("idx")String idx) {
		return new ModelAndView("board/board_ans_write");
	}
	
	@PostMapping("/board_ans_insert.do")
	public ModelAndView boardAnsInsert(@ModelAttribute("cPage")String cPage,
			@ModelAttribute("idx")String idx,
			Board_VO bv, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("redirect:/board_list.do");
		try {
			
			// 상세보기에서 groups, step, lev를 가져온다.
			Board_VO bvo = board_Service.getBoardOneList(idx);
			
			int groups = Integer.parseInt(bvo.getGroups());
			int step = Integer.parseInt(bvo.getStep());
			int lev = Integer.parseInt(bvo.getLev());
			
			// step, lev를 하나씩 올리자
			step ++ ;
			lev ++ ;
			
			// DB에 lev를 업데이트 하자.
			//** groups와 같은 원글을 찾아서 레벨이 같거나 크면 레벨 증가 
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("groups", groups);
			map.put("lev", lev);
			
			int result = board_Service.getLevUpdate(map);
			
			bv.setGroups(String.valueOf(groups));
			bv.setStep(String.valueOf(step));
			bv.setLev(String.valueOf(lev));
			
			// 첨부파일 처리 
			String path = request.getSession().getServletContext().getRealPath("/resources/images");
			MultipartFile file = bv.getFile();
			if(file.isEmpty()) {
				bv.setF_name("");
			}else {
				// 같은이름 없도록 UUID 사용
				UUID uuid = UUID.randomUUID();
				String f_name = uuid.toString() + "_" + bv.getFile().getOriginalFilename();
				bv.setF_name(f_name);
				
				// 이미지 저장
				byte[] in = bv.getFile().getBytes();
				File out = new File(path, f_name);
				FileCopyUtils.copy(in, out);
			}
			
			// 패스워드 암호화 
			bv.setPwd(passwordEncoder.encode(bv.getPwd()));
			// 삽입
			int res = board_Service.getAnsInsert(bv);
			
			return mv;
		} catch (Exception e) {
		}
		return null;
	}
	
}










