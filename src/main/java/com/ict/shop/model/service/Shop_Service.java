package com.ict.shop.model.service;

import java.util.List;
import com.ict.shop.model.vo.CartVO;
import com.ict.shop.model.vo.ShopVO;

public interface Shop_Service {
	public List<ShopVO> getShopList(String category) throws Exception; 
	public ShopVO getShopOneList(String idx) throws Exception;
	public List<CartVO> getCartList(String m_id) throws Exception;
	public ShopVO getProductOneList(String idx) throws Exception;
	public CartVO getCartOneList(String m_id, String p_num) throws Exception;
	public int getCartInsert(CartVO cartVO) throws Exception;
	public int getCartUpdate(CartVO cartVO) throws Exception;
	public int getCartEdit(CartVO cartVO) throws Exception;
	public int getCartDelete(String idx) throws Exception;
	public int getProductInsert(ShopVO svo) throws Exception;
}
