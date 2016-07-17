package com.startupmashup.service;



import java.util.ArrayList;

import com.startupmashup.bean.HackathonBean;



public interface HackathonService {

	void saveHackathonInfo(HackathonBean hackathonBean, ArrayList<String> challengeId);

	//ArrayList<ChallengeBean> getChallengeList();

	ArrayList<HackathonBean> getHackathonList();

	public HackathonBean getBean(String hackathonId);

	void editHackathonInfo(HackathonBean hackathonBean);

	void incrementCount(int hackathonId);

	
}
