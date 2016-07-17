package com.startupmashup.service;

import java.util.ArrayList;
import java.util.List;

import com.startupmashup.bean.HackathonBean;
import com.startupmashup.bean.ParticipantHackathonMapBean;



public interface ParticipantHackathonMapService {
	
	void saveMappingDetails(int hackathonId, int participantId);

	ArrayList<ParticipantHackathonMapBean> getParticipantsHackathonMapList();

	String checkIfAlreadyRegistered(int hackathonId, Integer participantId);

	public List<HackathonBean> getMappedHackathons(int participantId);
}
