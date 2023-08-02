package com.ict.common;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class Summer_Controller {

	@PostMapping("/saveImg.do")
	@ResponseBody
	public Map<String, String> saveImg(ImgVO vo, HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		
		// 넘어온 파일 검증
		MultipartFile f = vo.getS_file();
		String fname = null;
		if (f.getSize() > 0) {
			// 첨부파일 위치
			String path = request.getSession().getServletContext().getRealPath("/resources/upload");
			
			UUID uuid = UUID.randomUUID();
			// 같은 파일 이름이 있으면 파일명을 수정해준다.
			fname = uuid.toString()+"_"+f.getOriginalFilename();
			
			try {
				// 업로드
				f.transferTo(new File(path, fname));
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 비동기식 요청이므로 저장된 파일의 경로와 파일명을 보내야 한다.
			
			map.put("fname", fname);
			map.put("path", "resources/upload");
			return map;
		}
		return null;
	}
}
