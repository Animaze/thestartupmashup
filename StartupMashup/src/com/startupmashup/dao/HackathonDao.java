package com.startupmashup.dao;



import java.util.ArrayList;

import com.startupmashup.bean.HackathonBean;

public interface HackathonDao {
	
	int saveHackathonInfo(HackathonBean hackathonBean);
	
	ArrayList<HackathonBean> getHackathonList();

	public HackathonBean getBean(String hackathonId);

	void editHackathonInfo(HackathonBean hackathonBean);

	void incrementCount(int hackathonId);

	

//ArrayList<ChallengeBean> getChallengeList();

//void mapHackathonChallenge(ArrayList<Integer> idList);

}
