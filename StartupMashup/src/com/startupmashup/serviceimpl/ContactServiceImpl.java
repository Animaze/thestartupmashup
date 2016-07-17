package com.startupmashup.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.startupmashup.bean.ContactBean;
import com.startupmashup.dao.ContactDao;
import com.startupmashup.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	ContactDao contactDao;

	@Override
	public void saveContactInfo(ContactBean contactBean) {

		contactDao.saveContactInfo(contactBean);
	}

	

}
