package com.ict.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.model.vo.GuestBook2VO;

@Repository
public class GuestBook2DAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	// 리스트
	public List<GuestBook2VO> getGuestBook2List(){
		return sqlSessionTemplate.selectList("guestbook2.list");
	}
	// 글쓰기
	public int getGuestBook2Insert(GuestBook2VO g2vo) {
		return sqlSessionTemplate.insert("guestbook2.insert", g2vo);
	}
	// 상세보기 
	public GuestBook2VO getGuestBook2OneList(String idx) {
		return sqlSessionTemplate.selectOne("guestbook2.onelist", idx);
	}
	// 삭제
	
	// 수정
	public int getGuestBook2Update(GuestBook2VO g2vo) {
		return sqlSessionTemplate.update("guestbook2.update", g2vo);
	}
}
