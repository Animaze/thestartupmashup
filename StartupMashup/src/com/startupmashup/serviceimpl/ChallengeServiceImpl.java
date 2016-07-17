package com.startupmashup.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.startupmashup.bean.ChallengeBean;
import com.startupmashup.dao.ChallengeDao;
import com.startupmashup.dao.MapDao;
import com.startupmashup.service.ChallengeService;

@Service
public class ChallengeServiceImpl implements ChallengeService{
	
	@Autowired ChallengeDao challengeDao;
	
	@Autowired MapDao mapDao;

	@Override
	public void saveChallengeInfo(ChallengeBean challengeBean,List<String> selectedSkillList) {
		
		int challengeId=challengeDao.saveChallengeInfo(challengeBean);
		
		mapDao.mapChallengeSkill(challengeId,selectedSkillList);
	}

	@Override
	public List<ChallengeBean> getChallengesListByHackathonId(int hackathonId) {
		return challengeDao.getChallengesListByHackathonId(hackathonId);
	}

	@Override
	public List<ChallengeBean> getChallenges() {
		return challengeDao.getChallenges();
	}

	@Override
	public ChallengeBean getDetailsById(int challengeId) {
		return challengeDao.getDetailsById(challengeId);
	}

	@Override
	public void updateChallengeInfo(ChallengeBean challengeBean,
			List<String> selectedSkillList) {
	
			challengeDao.updateChallengeInfo(challengeBean,selectedSkillList);
		
		
	}

	@Override
	public List<ChallengeBean> getChallengesList(List<Integer> challengeIdList) {
		return challengeDao.getChallengesList(challengeIdList);
	}

}
