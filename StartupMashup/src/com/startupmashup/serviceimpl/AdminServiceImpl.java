package com.startupmashup.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.startupmashup.bean.AdminBean;
import com.startupmashup.dao.AdminDao;
import com.startupmashup.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	AdminDao adminDao;

	@Override
	public void saveAdminInfo(AdminBean adminBean) {

		adminDao.saveAdminInfo(adminBean);
	}

	@Override
	public boolean authoriseAdmin(String username, String password) {
		return adminDao.authoriseAdmin(username, password);
	}
}
