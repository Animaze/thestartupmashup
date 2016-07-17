package com.startupmashup.dao;

import java.util.ArrayList;
import java.util.List;

import com.startupmashup.bean.ParticipantBean;

public interface ParticipantDao {

	void saveParticipantInfo(ParticipantBean participantBean);

	ParticipantBean authoriseUser(String username, String password);
	public List<String> getUserNames();

	ArrayList<ParticipantBean> getParticipantList();
	
	public ParticipantBean getDetailsById(int userId);
	
	public void saveEditedProfile(ParticipantBean participantBean,int userId);

	List<ParticipantBean> getParticipantList(int hackathonId);

	List<ParticipantBean> getParticipantList(int hackathonId, int challengeId);

	void updateParticipant(int participantId, ParticipantBean participantBean);

	ParticipantBean getDetailsByUsername(String username);

	ParticipantBean getParticipantBeanByToken(String token);

	void updatePassword(String participantId, String newPassword);

}
