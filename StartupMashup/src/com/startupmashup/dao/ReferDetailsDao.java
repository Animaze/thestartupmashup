package com.startupmashup.dao;

import java.util.ArrayList;

import com.startupmashup.bean.ReferDetailsBean;

public interface ReferDetailsDao {

	public void saveReferDetailsInfo(ReferDetailsBean referDetails);

	ArrayList<ReferDetailsBean> getReferDetailsList();
	
}
