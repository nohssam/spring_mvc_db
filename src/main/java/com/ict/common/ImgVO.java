package com.ict.common;

import org.springframework.web.multipart.MultipartFile;

public class ImgVO {
	private MultipartFile  s_file;

	public MultipartFile getS_file() {
		return s_file;
	}

	public void setS_file(MultipartFile s_file) {
		this.s_file = s_file;
	}  
	
}
