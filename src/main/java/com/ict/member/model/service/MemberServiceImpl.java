package com.ict.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.member.model.dao.MemberDAO;
import com.ict.member.model.vo.MemberVO;


@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Override
	public int getMemberAdd(MemberVO m2vo) {
		return memberDAO.getMemberAdd(m2vo);
	}

	@Override
	public String getMemberPwd(String m_id) {
		return memberDAO.getMemberPwd(m_id);
	}
	
	
	
}
