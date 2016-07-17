package com.startupmashup.dao;

import java.util.ArrayList;

import com.startupmashup.bean.CompanyChallengeMapBean;


public interface CompanyChallengeMapDao {

	void saveMappingDetails(int companyId, ArrayList<Integer> challengeId);

	ArrayList<CompanyChallengeMapBean> findCompanyChallengeMapBeanByCompanyId(int id);

	void updateMappingDetails(int companyId, ArrayList<Integer> challengeIds);

}
