package com.startupmashup.service;

import java.util.ArrayList;

import com.startupmashup.bean.ReferDetailsBean;

public interface ReferDetailsService {
	
	public void saveReferDetailsInfo(ReferDetailsBean referDetails);
	
	ArrayList<ReferDetailsBean> getReferDetailsList();
}
