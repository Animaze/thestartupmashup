package com.startupmashup.serviceimpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.startupmashup.bean.ReferDetailsBean;
import com.startupmashup.dao.ReferDetailsDao;
import com.startupmashup.service.ReferDetailsService;

@Service
public class ReferDetailsServiceImpl implements ReferDetailsService{

	@Autowired
	ReferDetailsDao referDetailsDAO;
	
	public void saveReferDetailsInfo(ReferDetailsBean referDetails){
		referDetailsDAO.saveReferDetailsInfo(referDetails);
	}

	@Override
	public ArrayList<ReferDetailsBean> getReferDetailsList() {
		return referDetailsDAO.getReferDetailsList();
	}
	
}
