package com.ict.member.model.service;

import com.ict.member.model.vo.MemberVO;

public interface MemberService {
	// 회원가입 
	int getMemberAdd(MemberVO m2vo);
	
	// 로그인 
	// 1. id로 패스워드 맞는지 검사 
	MemberVO getMemberPwd(String m_id);
	
	// 아이디 찾기
	
	// 비밀번호 찾기
}
