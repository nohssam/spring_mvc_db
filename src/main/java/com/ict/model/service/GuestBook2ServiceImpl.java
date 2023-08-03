package com.ict.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.model.dao.GuestBook2DAO;
import com.ict.model.vo.GuestBook2VO;

@Service
public class GuestBook2ServiceImpl implements GuestBook2Service{
	@Autowired
	private GuestBook2DAO guestBook2DAO;
	
	@Override
	public List<GuestBook2VO> getGuestBook2List() {
		return guestBook2DAO.getGuestBook2List();
	}

	@Override
	public int getGuestBook2Insert(GuestBook2VO g2vo) {
		return guestBook2DAO.getGuestBook2Insert(g2vo);
	}

	@Override
	public GuestBook2VO getGuestBook2OneList(String idx) {
		return guestBook2DAO.getGuestBook2OneList(idx);
	}

	@Override
	public int getGuestBook2Delete(String idx) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getGuestBook2Update(GuestBook2VO g2vo) {
		return guestBook2DAO.getGuestBook2Update(g2vo);
	}

}
