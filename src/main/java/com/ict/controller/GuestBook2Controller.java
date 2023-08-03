package com.ict.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ict.model.service.GuestBook2Service;
import com.ict.model.vo.GuestBook2VO;

@Controller
public class GuestBook2Controller {
  @Autowired
  private GuestBook2Service guestBook2Service;
  
  @Autowired
  private BCryptPasswordEncoder passwordEncoder;
  
  @GetMapping("/guestbook2_list.do")
  public ModelAndView getGuestBook2List() {
	  ModelAndView mv = new ModelAndView("guestbook2/list");
	  List<GuestBook2VO> list = guestBook2Service.getGuestBook2List();
	  mv.addObject("list", list);
	  return mv;
  }
  @GetMapping("/guestbook2_AddForm.do")
  public ModelAndView getGuestBook2Form() {
	  return new ModelAndView("guestbook2/write");
  }
  @PostMapping("/guestbook2_insert.do")
  public ModelAndView getGuestBook2Insert(GuestBook2VO g2vo, HttpServletRequest request) {
	  ModelAndView mv = new ModelAndView("redirect:/guestbook2_list.do");
	  try {
		String path = request.getSession().getServletContext().getRealPath("/resources/images");
		
		MultipartFile f_param = g2vo.getFile();
		
		if(f_param.isEmpty()) {
			g2vo.setF_name("");
		}else {
			// 파라미터로 받은 file을 이용해서 DB에 저장할 f_name을 채워주자 
			// 그러나 같은 이름의 파일이름이면 파일을 변경해야 되므로 UUID를 이용해서 
			// DB에 저장할 이름을 변경하자  
			UUID uuid = UUID.randomUUID();
			String f_name = uuid.toString()+"_"+ g2vo.getFile().getOriginalFilename();
			g2vo.setF_name(f_name);

			// 이미지 /resources/images 저장하기
			byte[] in = g2vo.getFile().getBytes();
			File out = new File(path, f_name);
			FileCopyUtils.copy(in, out);
		}
		
		// 패스워드를 암호화 하자
		String pwd = passwordEncoder.encode(g2vo.getPwd());
		g2vo.setPwd(pwd);
		// DB에 저장하기 
		int res = guestBook2Service.getGuestBook2Insert(g2vo);
		if(res >0) {
			 return mv;
		}else {
			return null;
		}
	} catch (Exception e) {
		System.out.println(e);
		return null;
	}
  }
  
  @GetMapping("/guestbook2_onelist.do")
  public ModelAndView getGuestBook2OneList(String idx) {
	  ModelAndView mv = new ModelAndView("guestbook2/onelist");
	  GuestBook2VO g2vo = guestBook2Service.getGuestBook2OneList(idx);
	  mv.addObject("g2vo", g2vo);
	  return mv;
  }
  @GetMapping("/guestbook2_down.do")
  public void getGuestBookOne2Down(String f_name,
		  HttpServletRequest request, HttpServletResponse response) {
	String path = request.getSession().getServletContext().getRealPath("/resources/images/"+f_name);
	try {
		String r_path = URLEncoder.encode(path, "utf-8");
		response.setContentType("application/x-msdownload");
		response.setHeader("Content-Disposition", "attachment; filename=" + r_path);
		
		File file = new File(new String(path.getBytes()));
		FileInputStream in = new FileInputStream(file);
		OutputStream out = response.getOutputStream();
		FileCopyUtils.copy(in, out);
	} catch (Exception e) {
	}
  }
  
  @PostMapping("/guestbook2_UpdateForm.do")
  public ModelAndView getGuestBook2UpdateForm(String idx) {
	  ModelAndView mv = new ModelAndView("guestbook2/update");
	  GuestBook2VO vo = guestBook2Service.getGuestBook2OneList(idx);
	  mv.addObject("vo", vo);
	  return mv;
  }
  @PostMapping("/guestbook2_DeleteForm.do")
  public ModelAndView getGuestBook2DeleteForm(String idx) {
	  ModelAndView mv = new ModelAndView("guestbook2/delete");
	  return mv;
  }
  @PostMapping("/guestbook2_update.do")
  public ModelAndView getGuestBook2Update(GuestBook2VO g2vo, HttpServletRequest request) {
	  ModelAndView mv = new ModelAndView();
	  // 비밀번호가 맞는지 틀린지 검사 하자.(비밀번호가 암호로 되어 있다.)
	  // jsp에서 암호 입력 한것 
	  String cpwd = g2vo.getPwd();
	  	  
	  // DB에서 암호 얻기 
	  GuestBook2VO vo = guestBook2Service.getGuestBook2OneList(g2vo.getIdx());
	  String dpwd = vo.getPwd();
	  
	  //passwordEncoder.matches(암호화되지 않은것, 암호화 된것 ) 
	  if(! passwordEncoder.matches(cpwd, dpwd)) {
		  mv.setViewName("guestbook2/update");
		  mv.addObject("pwchk", "fail");
		  mv.addObject("vo", vo);
		  return mv;
	  }else {
		  String path = request.getSession().getServletContext().getRealPath("/resources/images");
		  try {
			  MultipartFile f_param = g2vo.getFile();
			  String old_f_name = g2vo.getOld_f_name();
			  
			  if(f_param.isEmpty()) {
				  g2vo.setF_name(old_f_name);
			  }else {
				  UUID uuid = UUID.randomUUID();
				  String f_name = uuid.toString()+"_"+ g2vo.getFile().getOriginalFilename();
				  g2vo.setF_name(f_name);

				  // 이미지 /resources/images 저장하기
				  byte[] in = g2vo.getFile().getBytes();
				  File out = new File(path, f_name);
				  FileCopyUtils.copy(in, out);
			  }
			  int result = guestBook2Service.getGuestBook2Update(g2vo);
			  
			  mv.setViewName("redirect:/guestbook2_onelist.do?idx="+g2vo.getIdx());
			  return mv;
			
		} catch (Exception e) {
		}
		  return null;
	  }
	  
  }
}















