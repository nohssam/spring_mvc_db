package com.ict.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.model.vo.GuestBookVO;

@Repository
public class GuestBookDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	// 서비스에서 DB 처리하는 메서드를 모두 받아 줘야 한다.
	// 리스트
	public List<GuestBookVO> getGuestBookList() {
		return sqlSessionTemplate.selectList("guestbook.list");
	}

	// 방명록 쓰기
	public int getGuestBookInsert(GuestBookVO gvo) {
		return sqlSessionTemplate.insert("guestbook.insert", gvo);
	}
	
	// 상세보기
	public GuestBookVO getGuestBookOneList(String idx) {
		return sqlSessionTemplate.selectOne("guestbook.onelist", idx);
	}
	
	// 삭제
	public int getGuestBookDelete(String idx) {
		return sqlSessionTemplate.delete("guestbook.delete", idx);
	}
	
	// 수정
	public int getGuestBookUpdate(GuestBookVO gvo) {
		return sqlSessionTemplate.update("guestbook.update", gvo);
	}
}










