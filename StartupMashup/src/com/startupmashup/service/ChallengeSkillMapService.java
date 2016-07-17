package com.startupmashup.service;

import java.util.List;

import com.startupmashup.bean.ChallengeSkillMapBean;

public interface ChallengeSkillMapService {

	public void saveMappingDetails(int challengeId, List<String> skillId);

	public List<Integer> getMappedSkillIds(int challengeId);

	public void editMappingDetails(int id, List<String> selectedSkillList);

	public List<ChallengeSkillMapBean> getChallengeSkillMapList();

	 
	
}
