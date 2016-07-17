package com.startupmashup.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.startupmashup.dao.ParticipantChallengeMapDao;
import com.startupmashup.service.ParticipantChallengeMapService;
@Service
public class ParticipantChallengeMapServiceImpl implements ParticipantChallengeMapService {
	
	@Autowired ParticipantChallengeMapDao participantChallengeMapDao;

	@Override
	public void saveMappingDetails(int challengeId, int participantId) {
		participantChallengeMapDao.saveMappingDetails(challengeId,participantId);
	}

}
