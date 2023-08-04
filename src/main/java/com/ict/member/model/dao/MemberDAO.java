package com.ict.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.member.model.vo.MemberVO;

@Repository
public class MemberDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public int getMemberAdd(MemberVO m2vo) {
		return sqlSessionTemplate.insert("member.insert", m2vo);
	}
	
	public String getMemberPwd(String m_id) {
		return sqlSessionTemplate.selectOne("member.selectPwd", m_id);
	}
}
