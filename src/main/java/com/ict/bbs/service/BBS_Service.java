package com.ict.bbs.service;

import java.util.List;

import com.ict.bbs.model.vo.BBS_VO;

public interface BBS_Service {
	// 리스트
	public List<BBS_VO> getList();
	// 상세보기
	public BBS_VO getOneList(String b_idx);
	// 조회수 업데이트
	public int getHitUpdate(String b_idx);
	// 삽입
	public int getInsert(BBS_VO bvo);
	
	
}
