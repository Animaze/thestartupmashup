package com.startupmashup.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.startupmashup.bean.HackathonBean;
import com.startupmashup.bean.ParticipantHackathonMapBean;
import com.startupmashup.dao.ParticipantHackathonMapDao;
import com.startupmashup.service.ParticipantHackathonMapService;
@Service
public class ParticipantHackathonMapServiceImpl implements ParticipantHackathonMapService{
	
	@Autowired ParticipantHackathonMapDao participantHackathonMapDao;

	@Override
	public ArrayList<ParticipantHackathonMapBean> getParticipantsHackathonMapList() {
		
		return participantHackathonMapDao.getParticipantsHackathonMapList();
	}

	@Override
	public void saveMappingDetails(int hackathonId, int participantId) {
		participantHackathonMapDao.saveMappingDetails(hackathonId,participantId);
		
	}
	
	@Override
	public List<HackathonBean> getMappedHackathons(int participantId) {
		return participantHackathonMapDao.getMappedHackathons(participantId);
	}


	@Override
	public String checkIfAlreadyRegistered(int hackathonId, Integer participantId) {
		
		return participantHackathonMapDao.checkIfAlreadyRegistered(hackathonId,participantId);
	}

}
