package com.startupmashup.service;

import java.util.ArrayList;
import java.util.List;

import com.startupmashup.bean.ChallengeSkillMapBean;
import com.startupmashup.bean.CompanyBean;
import com.startupmashup.bean.CompanyChallengeMapBean;
import com.startupmashup.bean.HackathonBean;
import com.startupmashup.bean.HackathonChallengeMapBean;

public interface MapService {

	public void mapParticpantAndHackathon(int hackathonId,int participantId);
	
	public void mapParticipantChallenge(int challengeId,int participantId);
	
	public void mapChallengeSkill(int challengeId,List<String> selectedSkillList);
	
	public List<Integer> getMappedSkillIds(int challengeId);
	
	public List<HackathonChallengeMapBean> getHackathonChallengeMapDetails();

	public List<Integer> getChallengeIds(String hackathonId);

	public void editChallengeHackathonMappingDetails(int hackId,
			List<String> challengeIdList);

	void saveChallengeHackathonMappingDetails(int hackId,
			List<String> challengeIdList);

	public String checkIfAlreadyRegistered(int hackathonId, Integer participantId);

	public ArrayList<CompanyChallengeMapBean> findCompanyChallengeMapBeanByCompanyId(
			int id);

	public ArrayList<HackathonChallengeMapBean> getHackathonChallengeMapList();

	public void saveCompanyChallengeMappingDetails(int companyId,
			ArrayList<Integer> challengeIds);

	public void saveHackathonCompanyMappingDetails(int companyId,
			int hackathonId);

	public void updateCompanyChallengeMappingDetails(int companyId,
			ArrayList<Integer> challengeIds);

	public void updateHackathonCompanyMappingDetails(int companyId,
			ArrayList<Integer> hackathonIds);

	public List<CompanyBean> getCompanyMappedToHackathon(int hackathonId);

	public List<ChallengeSkillMapBean> getChallengeSkillMapList();

	public List<HackathonBean> getMappedHackathons(int participantId);

	public void saveMappingDetails(int hackathonId,
			ArrayList<String> challengeId);
	
	void updateHackathonChallengeMappingDetails(int hackathonId,
			ArrayList<String> challengeIdList);
}
