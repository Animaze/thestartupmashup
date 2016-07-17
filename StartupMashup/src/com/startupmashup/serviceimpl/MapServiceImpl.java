package com.startupmashup.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.startupmashup.bean.ChallengeSkillMapBean;
import com.startupmashup.bean.CompanyBean;
import com.startupmashup.bean.CompanyChallengeMapBean;
import com.startupmashup.bean.HackathonBean;
import com.startupmashup.bean.HackathonChallengeMapBean;
import com.startupmashup.dao.MapDao;
import com.startupmashup.service.MapService;

@Service
public class MapServiceImpl implements MapService {

	@Autowired
	MapDao mapDao;

	@Override
	public void mapParticpantAndHackathon(int hackathonId, int participantId) {
		mapDao.mapParticpantAndHackathon(hackathonId, participantId);
	}

	@Override
	public void mapParticipantChallenge(int challengeId, int participantId) {
		mapDao.mapParticipantChallenge(challengeId, participantId);
	}

	@Override
	public void mapChallengeSkill(int challengeId,
			List<String> selectedSkillList) {
		mapDao.mapChallengeSkill(challengeId, selectedSkillList);

	}

	@Override
	public List<Integer> getMappedSkillIds(int challengeId) {
		return mapDao.getMappedSkillIds(challengeId);
	}

	@Override
	public List<HackathonChallengeMapBean> getHackathonChallengeMapDetails() {
		return mapDao.getHackathonChallengeMapDetails();
	}

	@Override
	public List<Integer> getChallengeIds(String hackathonId) {
		return mapDao.getChallengeIds(hackathonId);
	}

	@Override
	public void editChallengeHackathonMappingDetails(int hackId,
			List<String> challengeIdList) {
		mapDao.editChallengeHackathonMappingDetails(hackId,challengeIdList);
	}

	@Override
	public void saveChallengeHackathonMappingDetails(int hackId,
			List<String> challengeIdList) {
		mapDao.saveChallengeHackathonMappingDetails(hackId, challengeIdList);
	}

	@Override
	public String checkIfAlreadyRegistered(int hackathonId, Integer participantId) {
		return mapDao.checkIfAlreadyRegistered(hackathonId,participantId);
	}

	@Override
	public ArrayList<CompanyChallengeMapBean> findCompanyChallengeMapBeanByCompanyId(
			int id) {
		return mapDao.findCompanyChallengeMapBeanByCompanyId(id);
		
	}

	@Override
	public ArrayList<HackathonChallengeMapBean> getHackathonChallengeMapList() {
		return mapDao.getHackathonChallengeMapList();
	}

	@Override
	public void saveCompanyChallengeMappingDetails(int companyId,
			ArrayList<Integer> challengeIds) {
		mapDao.saveCompanyChallengeMappingDetails(companyId,challengeIds);
	}

	@Override
	public void saveHackathonCompanyMappingDetails(int companyId,
			int hackathonId) {
		mapDao.saveHackathonCompanyMappingDetails(companyId,hackathonId);
		
	}

	@Override
	public void updateCompanyChallengeMappingDetails(int companyId,
			ArrayList<Integer> challengeIds) {
		mapDao.updateCompanyChallengeMappingDetails(companyId,challengeIds);
	}

	@Override
	public void updateHackathonCompanyMappingDetails(int companyId,
			ArrayList<Integer> hackathonIds) {
		mapDao.updateHackathonCompanyMappingDetails(companyId,hackathonIds);
		
	}

	@Override
	public List<CompanyBean> getCompanyMappedToHackathon(int hackathonId) {
		return mapDao.getCompanyMappedToHackathon(hackathonId);
	}

	@Override
	public List<ChallengeSkillMapBean> getChallengeSkillMapList() {
		return mapDao.getChallengeSkillMapList();
	}

	@Override
	public List<HackathonBean> getMappedHackathons(int participantId) {
		return mapDao.getMappedHackathons(participantId);
	}

	@Override
	public void saveMappingDetails(int hackathonId,
			ArrayList<String> challengeId) {
		mapDao.saveMappingDetails(hackathonId, challengeId);		
	}

	@Override
	public void updateHackathonChallengeMappingDetails(int hackathonId,
			ArrayList<String> challengeIdList) {
		mapDao.updateHackathonChallengeMappingDetails(hackathonId, challengeIdList);		
	}
}
