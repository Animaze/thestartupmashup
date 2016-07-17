package com.startupmashup.service;

import java.util.ArrayList;

import com.startupmashup.bean.CompanyChallengeMapBean;


public interface CompanyChallengeMapService {
	
	
	void saveMappingDetails(int companyId, ArrayList<Integer> challengeId);

	ArrayList<CompanyChallengeMapBean> findCompanyChallengeMapBeanByCompanyId(int id);

	void updateMappingDetails(int companyId, ArrayList<Integer> challengeIds);
	
}
