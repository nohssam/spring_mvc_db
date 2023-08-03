package com.ict.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
		
		System.out.println("변경전 : " + g2vo.getPwd());
		// 패스워드를 암호화 하자
		String pwd = passwordEncoder.encode(g2vo.getPwd());
		g2vo.setPwd(pwd);
		System.out.println("변경후 : " + g2vo.getPwd());
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
}















