package com.startupmashup.serviceimpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.startupmashup.bean.HackathonChallengeMapBean;
import com.startupmashup.dao.HackathonChallengeMapDao;
import com.startupmashup.service.HackathonChallengeMapService;

@Service
public class HackathonChallengeMapServiceImpl implements HackathonChallengeMapService{

	@Autowired HackathonChallengeMapDao hackathonChallengeMapDao;
	
	@Override
	public void saveMappingDetails(int hackathonId, ArrayList<String> challengeId) {
		hackathonChallengeMapDao.saveMappingDetails(hackathonId, challengeId);
		
	}

	@Override
	public ArrayList<HackathonChallengeMapBean> getHackathonChallengeMapList() {
		// TODO Auto-generated method stub
		return hackathonChallengeMapDao.getHackathonChallengeMapList();
	}

	@Override
	public void updateMappingDetails(int hackathonId, ArrayList<String> challengeIdList) {
		hackathonChallengeMapDao.updateMappingDetails(hackathonId,challengeIdList);
	}



	
	
}
