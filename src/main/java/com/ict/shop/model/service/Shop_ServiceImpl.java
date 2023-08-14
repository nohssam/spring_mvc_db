package com.ict.shop.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.shop.model.dao.Shop_DAO;
import com.ict.shop.model.vo.CartVO;
import com.ict.shop.model.vo.ShopVO;

@Service
public class Shop_ServiceImpl implements Shop_Service{
	@Autowired
	private Shop_DAO shop_DAO;
	
	@Override
	public List<ShopVO> getShopList(String category) throws Exception {
		return shop_DAO.getShopList(category);
	}

	@Override
	public ShopVO getShopOneList(String idx) throws Exception {
		return shop_DAO.getShopOneList(idx);
	}

	@Override
	public List<CartVO> getCartList(String m_id) throws Exception {
		return shop_DAO.getCartList(m_id);
	}

	@Override
	public ShopVO getProductOneList(String idx) throws Exception {
		return shop_DAO.getProductOneList(idx);
	}

	@Override
	public CartVO getCartOneList(String m_id, String p_num) throws Exception {
		return shop_DAO.getCartOneList(m_id, p_num);
	}

	@Override
	public int getCartInsert(CartVO cartVO) throws Exception {
		return shop_DAO.getCartInsert(cartVO);
	}

	@Override
	public int getCartUpdate(CartVO cartVO) throws Exception {
		return shop_DAO.getCartUpdate(cartVO);
	}

	@Override
	public int getCartEdit(CartVO cartVO) throws Exception {
		return shop_DAO.getCartEdit(cartVO);
	}

	@Override
	public int getCartDelete(String idx) throws Exception {
		return shop_DAO.getCartDelete(idx);
	}

	@Override
	public int getProductInsert(ShopVO svo) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
