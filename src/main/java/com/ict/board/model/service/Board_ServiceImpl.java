package com.ict.board.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.board.model.dao.Board_DAO;
import com.ict.board.model.vo.Board_VO;

@Service
public class Board_ServiceImpl implements Board_Service{
	@Autowired
	private Board_DAO board_DAO;

	@Override
	public int getTotalCount() {
		return board_DAO.getTotalCount();
	}

	@Override
	public List<Board_VO> getList(int offset, int limit) {
		return board_DAO.getList(offset, limit);
	}
	
	@Override
	public int getInsert(Board_VO bv) {
		return board_DAO.getInsert(bv);
	}
	
	@Override
	public int getBoardHit(String idx) {
		return board_DAO.getBoardHit(idx);
	}
	@Override
	public Board_VO getBoardOneList(String idx) {
		return board_DAO.getBoardOneList(idx);
	}
	
	@Override
	public int getLevUpdate(Map<String, Integer> map) {
		return board_DAO.getLevUpdate(map);
	}
	
	@Override
	public int getAnsInsert(Board_VO bv) {
		return board_DAO.getAnsInsert(bv);
	}
}












