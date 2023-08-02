package com.ict.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.model.vo.MembersVO;

@Repository
public class MembersDAO {
	// 실제 Mapper를 호출하는 클래스
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	// Members 리스트 
	public List<MembersVO> membersList(){
		List<MembersVO> list = sqlSessionTemplate.selectList("members.list");
		return list;
	}
	
	// member 삽입
	public int memberAdd(MembersVO mvo) {
		int result = sqlSessionTemplate.insert("members.insert", mvo);
		// sqlSessionTemplate는 autocommit이다.
		return result;
	}
	
}








