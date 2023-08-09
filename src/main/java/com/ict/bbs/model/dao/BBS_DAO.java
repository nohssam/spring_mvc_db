package com.ict.bbs.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.bbs.model.vo.BBS_VO;
import com.ict.bbs.model.vo.Comment_VO;

@Repository
public class BBS_DAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public int getTotalCount() {
		return sqlSessionTemplate.selectOne("bbs.count") ;
	}
	
	public List<BBS_VO> getList(int offset, int limit) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("limit", limit);
		map.put("offset", offset);
		return sqlSessionTemplate.selectList("bbs.list", map) ;
	}
	
	public List<BBS_VO> getList() {
		return sqlSessionTemplate.selectList("bbs.list");
	}
	public int getInsert(BBS_VO bvo) {
		return sqlSessionTemplate.insert("bbs.insert", bvo);
	}
	
	public int getHitUpdate(String b_idx) {
		return sqlSessionTemplate.update("bbs.hitup", b_idx);
	}
	public BBS_VO getOneList(String b_idx) {
		return sqlSessionTemplate.selectOne("bbs.onelist", b_idx);
	}
	public List<Comment_VO> getCommList(String b_idx) {
		return sqlSessionTemplate.selectList("bbs.comlist", b_idx);
	}
	public int getCommInsert(Comment_VO cvo) {
		return sqlSessionTemplate.insert("bbs.cominsert", cvo);
	}
	public int getCommDelete(String c_idx) {
		return sqlSessionTemplate.delete("bbs.comdelete",c_idx);
	}
	public int getDelete(String b_idx) {
		return sqlSessionTemplate.update("bbs.delete",b_idx);
	}
	public int getUpdate(BBS_VO bvo) {
		return sqlSessionTemplate.update("bbs.update", bvo);
	}
}









