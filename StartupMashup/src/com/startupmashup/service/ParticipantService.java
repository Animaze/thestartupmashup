package com.startupmashup.service;

import java.util.ArrayList;
import java.util.List;

import com.startupmashup.bean.ParticipantBean;

public interface ParticipantService {

	void saveParticipantInfo(ParticipantBean participantBean);

	ParticipantBean authoriseUser(String username, String password);

	//String uploadResume(MultipartFile file, ParticipantBean participantBean);
	public List<String> getUserNames();

	ArrayList<ParticipantBean> getParticipantList();
	
	public ParticipantBean getDetailsById(int userId);
	
	public void saveEditedProfile(ParticipantBean participantBean,int userId);
	
	List<ParticipantBean> getParticipantList(int hackathonId);
	
	public List<ParticipantBean> getParticipantList(int hackathonId, int challengeId);

	public void updateParticipant(int participantId, ParticipantBean participantBean);

	ParticipantBean getDetailsByUsername(String username);

	ParticipantBean getParticipantBeanByToken(String token);

	void updatePassword(String participantId, String newPassword);
	
	
}
