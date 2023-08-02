package com.ict.model.service;

import java.util.List;

import com.ict.model.vo.GuestBookVO;

public interface GuestBookService {
	// 전체보기
	List<GuestBookVO> getGuestBookList();
	
	// 상세보기
	GuestBookVO getGuestBookOneList(String idx);
	
	// 삽입
	int getGuestBookInsert(GuestBookVO gvo);
	
	// 수정
	int getGuestBookUpdate(GuestBookVO gvo);
	
	// 삭제
	int getGuestBookDelete(String idx);
}
