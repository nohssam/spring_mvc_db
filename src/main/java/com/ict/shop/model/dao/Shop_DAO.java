package com.ict.shop.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.shop.model.vo.CartVO;
import com.ict.shop.model.vo.ShopVO;

@Repository
public class Shop_DAO {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public List<ShopVO> getShopList(String category) throws Exception {
		return sqlSessionTemplate.selectList("shop.list", category);
	}

	public ShopVO getShopOneList(String idx) throws Exception {
		return sqlSessionTemplate.selectOne("shop.onelist", idx);
	}

	public List<CartVO> getCartList(String m_id) throws Exception {
		return sqlSessionTemplate.selectList("shop.cartlist", m_id);
	}

	
	public ShopVO getProductOneList(String idx) throws Exception {
		return sqlSessionTemplate.selectOne("shop.onelist", idx);
	}


	public CartVO getCartOneList(String m_id, String p_num) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		map.put("m_id", m_id);
		map.put("p_num", p_num);
		return sqlSessionTemplate.selectOne("shop.cartonelist", map);
	}

	
	public int getCartInsert(CartVO cartVO) throws Exception {
		return sqlSessionTemplate.insert("shop.cartinsert", cartVO);
	}

	public int getCartUpdate(CartVO cartVO) throws Exception {
		return sqlSessionTemplate.update("shop.cartupdate", cartVO);
	}


	public int getCartEdit(CartVO cartVO) throws Exception {
		return sqlSessionTemplate.update("shop.cartedit", cartVO);
	}

	
	public int getCartDelete(String idx) throws Exception {
		return sqlSessionTemplate.delete("shop.cartdelete", idx);
	}


	public int getProductInsert(ShopVO svo) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
}
