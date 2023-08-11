package com.ict.board.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.board.model.vo.Board_VO;

@Repository
public class Board_DAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public int getTotalCount() {
		return sqlSessionTemplate.selectOne("board.count");
	}

	public List<Board_VO> getList(int offset, int limit) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("offset", offset);
		map.put("limit", limit);
		return sqlSessionTemplate.selectList("board.list", map);
	}

	public int getInsert(Board_VO bv) {
		int result = sqlSessionTemplate.insert("board.insert", bv);
		return result;
	}
	public int getBoardHit(String idx) {
	   int result = sqlSessionTemplate.update("board.hit", idx);
	   return result;
	}
	public Board_VO getBoardOneList(String idx) {
		Board_VO bv = sqlSessionTemplate.selectOne("board.onelist", idx);
		return bv;
	}
	public int getLevUpdate(Map<String, Integer> map) {
		return sqlSessionTemplate.update("board.levupdate", map);
	}
	public int getAnsInsert(Board_VO bv) {
		return sqlSessionTemplate.insert("board.ansinsert", bv);
	}
}







