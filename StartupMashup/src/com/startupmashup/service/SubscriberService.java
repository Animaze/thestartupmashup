package com.startupmashup.service;

import java.util.ArrayList;

import com.startupmashup.bean.SubscriberBean;

public interface SubscriberService {

	void saveSubscriberInfo(String emailId);

	ArrayList<SubscriberBean> getSubscribersList();

}
