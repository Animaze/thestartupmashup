package com.startupmashup.dao;

import java.util.List;

import com.startupmashup.bean.ChallengeSkillMapBean;

public interface ChallengeSkillMapDao {

	public void saveMappingDetails(int challengeId, List<String> skillId);

	public List<Integer> getMappedSkillIds(int challengeId);

	public void editMappingDetails(int id, List<String> selectedSkillList);

	public List<ChallengeSkillMapBean> getChallengeSkillMapList();
	
}
