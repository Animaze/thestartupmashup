package com.startupmashup.dao;

import java.util.ArrayList;



import java.util.List;

import com.startupmashup.bean.HackathonBean;
import com.startupmashup.bean.ParticipantHackathonMapBean;

public interface ParticipantHackathonMapDao {

	ArrayList<ParticipantHackathonMapBean> getParticipantsHackathonMapList();

	void saveMappingDetails(int hackathonId, int participantId);

	String checkIfAlreadyRegistered(int hackathonId, Integer participantId);

	List<HackathonBean> getMappedHackathons(int participantId);

}
