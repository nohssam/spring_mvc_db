package com.ict.model.service;

import java.util.List;

import com.ict.model.vo.GuestBook2VO;

public interface GuestBook2Service {
	// 전체보기
	List<GuestBook2VO> getGuestBook2List();
	
	// 글쓰기
	int getGuestBook2Insert(GuestBook2VO g2vo);
	
	// 상세보기
	GuestBook2VO getGuestBook2OneList(String idx);
	
	// 삭제
	int getGuestBook2Delete(String idx);
	
	// 수정
	int getGuestBook2Update(GuestBook2VO g2vo);
}
