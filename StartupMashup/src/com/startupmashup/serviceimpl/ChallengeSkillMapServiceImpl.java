package com.startupmashup.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.startupmashup.bean.ChallengeSkillMapBean;
import com.startupmashup.dao.ChallengeSkillMapDao;
import com.startupmashup.service.ChallengeSkillMapService;

@Service
public class ChallengeSkillMapServiceImpl implements ChallengeSkillMapService{

	@Autowired
	ChallengeSkillMapDao challengeSkillMapDao;
	
	@Override
	public void saveMappingDetails(int challengeId, List<String> skillId) {
		challengeSkillMapDao.saveMappingDetails(challengeId, skillId);
	}

	@Override
	public List<Integer> getMappedSkillIds(int challengeId) {

		return challengeSkillMapDao.getMappedSkillIds(challengeId);
	}

	@Override
	public void editMappingDetails(int id, List<String> selectedSkillList) {
		challengeSkillMapDao.editMappingDetails(id, selectedSkillList);
		
	}

	@Override
	public List<ChallengeSkillMapBean> getChallengeSkillMapList() {
		return challengeSkillMapDao.getChallengeSkillMapList();
	}

	
	
}
