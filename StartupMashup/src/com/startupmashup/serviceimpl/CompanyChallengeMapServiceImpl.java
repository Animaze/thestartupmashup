package com.startupmashup.serviceimpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.startupmashup.bean.CompanyChallengeMapBean;
import com.startupmashup.dao.CompanyChallengeMapDao;
import com.startupmashup.service.CompanyChallengeMapService;
@Service
public class CompanyChallengeMapServiceImpl implements CompanyChallengeMapService{
@Autowired CompanyChallengeMapDao companyChallengeMapDao;
	@Override
	public void saveMappingDetails(int companyId, ArrayList<Integer> challengeId) {
		
		
		
		companyChallengeMapDao.saveMappingDetails(companyId,challengeId);
	}
	@Override
	public ArrayList<CompanyChallengeMapBean> findCompanyChallengeMapBeanByCompanyId(int id) {
		
		return companyChallengeMapDao.findCompanyChallengeMapBeanByCompanyId(id);
	}
	@Override
	public void updateMappingDetails(int companyId,
			ArrayList<Integer> challengeIds) {
		companyChallengeMapDao.updateMappingDetails(companyId,challengeIds);
		
	}

}
