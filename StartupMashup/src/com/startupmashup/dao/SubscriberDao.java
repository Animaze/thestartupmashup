package com.startupmashup.dao;

import java.util.ArrayList;

import com.startupmashup.bean.SubscriberBean;

public interface SubscriberDao {

	void saveSubscriberInfo(String emailId);

	ArrayList<SubscriberBean> getSubscribersList();

}
