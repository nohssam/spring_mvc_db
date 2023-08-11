package com.ict.board.model.service;

import java.util.List;
import java.util.Map;

import com.ict.board.model.vo.Board_VO;

public interface Board_Service {
	int getTotalCount();
	List<Board_VO> getList(int offset,int limit);
	int getInsert(Board_VO bv);
	int getBoardHit(String idx);
	Board_VO getBoardOneList(String idx);
	int getLevUpdate(Map<String, Integer> map);
	int getAnsInsert(Board_VO bv);
}
