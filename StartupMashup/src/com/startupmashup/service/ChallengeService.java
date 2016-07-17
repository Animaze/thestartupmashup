package com.startupmashup.service;

import java.util.List;

import com.startupmashup.bean.ChallengeBean;

public interface ChallengeService {

	public void saveChallengeInfo(ChallengeBean challengeBean,List<String> selectedSkillList);
	
	List<ChallengeBean> getChallengesListByHackathonId(int hackathonId);
	
	List<ChallengeBean> getChallenges();

	public ChallengeBean getDetailsById(int challengeId);
	
	public void updateChallengeInfo(ChallengeBean challengeBean,List<String> selectedSkillList);
	
	public List<ChallengeBean> getChallengesList(List<Integer> challengeIdList);
	
}
