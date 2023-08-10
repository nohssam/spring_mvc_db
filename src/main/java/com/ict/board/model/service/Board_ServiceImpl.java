package com.ict.board.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.board.model.dao.Board_DAO;

@Service
public class Board_ServiceImpl implements Board_Service{
	@Autowired
	private Board_DAO board_DAO;
	
	
}
