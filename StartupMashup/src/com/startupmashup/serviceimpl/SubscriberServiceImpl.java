package com.startupmashup.serviceimpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.startupmashup.bean.SubscriberBean;
import com.startupmashup.dao.SubscriberDao;
import com.startupmashup.service.SubscriberService;

@Service
public class SubscriberServiceImpl implements SubscriberService {

	@Autowired
	SubscriberDao subscriberDao;

	@Override
	public void saveSubscriberInfo(String emailId) {
		subscriberDao.saveSubscriberInfo(emailId);

	}

	@Override
	public ArrayList<SubscriberBean> getSubscribersList() {

		return subscriberDao.getSubscribersList();
	}

}
