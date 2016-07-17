package com.startupmashup.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.startupmashup.bean.EmployersBean;
import com.startupmashup.dao.EmployersDao;
import com.startupmashup.service.EmployersService;

@Service
public class EmployersServiceImpl implements EmployersService {

	@Autowired
	EmployersDao employersDao;

	@Override
	public void saveEmployersInfo(EmployersBean employersBean) {

		employersDao.saveEmployersInfo(employersBean);
	}

}
