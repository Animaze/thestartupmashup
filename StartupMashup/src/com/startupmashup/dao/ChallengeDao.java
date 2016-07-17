package com.startupmashup.dao;

import java.util.List;

import com.startupmashup.bean.ChallengeBean;

public interface ChallengeDao {

	public int saveChallengeInfo(ChallengeBean challengeBean);
	
	List<ChallengeBean> getChallengesListByHackathonId(int hackathonId);
	
	List<ChallengeBean> getChallenges();
	
	public void updateChallengeInfo(ChallengeBean challengeBean,List<String> selectedSkillList);

	public ChallengeBean getDetailsById(int challengeId);

	public List<ChallengeBean> getChallengesList(List<Integer> challengeIdList);

}
