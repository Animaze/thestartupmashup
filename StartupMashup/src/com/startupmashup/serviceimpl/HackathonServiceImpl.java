package com.startupmashup.serviceimpl;



import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.startupmashup.bean.HackathonBean;
import com.startupmashup.dao.HackathonDao;
import com.startupmashup.service.HackathonChallengeMapService;
import com.startupmashup.service.HackathonService;
import com.startupmashup.service.MapService;

@Service
public class HackathonServiceImpl implements HackathonService{
	
	@Autowired HackathonDao hackathonDao;
	@Autowired MapService mapService;
	@Autowired HackathonChallengeMapService hackathonChallengeMapService;

	@Override
	public void saveHackathonInfo(HackathonBean hackathonBean,ArrayList<String> challengeId) {
		
			
			int hackathonId =  hackathonDao.saveHackathonInfo(hackathonBean);
			hackathonChallengeMapService.saveMappingDetails(hackathonId, challengeId);
		
		
	}


	@Override
	public ArrayList<HackathonBean> getHackathonList() {
		
		return hackathonDao.getHackathonList();
	}

	@Override
	public HackathonBean getBean(String hackathonId) {
		
		return hackathonDao.getBean(hackathonId);
		
	}

	@Override
	public void editHackathonInfo(HackathonBean hackathonBean) {
		
		hackathonDao.editHackathonInfo(hackathonBean);
		
	}

	@Override
	public void incrementCount(int hackathonId) {
		hackathonDao.incrementCount(hackathonId);
	}

	/*@Override
	public void mapHackathonChallenge(ArrayList<Integer> idList) {
	
		hackathonDao.mapHackathonChallenge(idList);
	}*/

}
