package com.startupmashup.service;

import java.util.ArrayList;

import com.startupmashup.bean.HackathonChallengeMapBean;

public interface HackathonChallengeMapService {

	public void saveMappingDetails(int hackathonId, ArrayList<String> challengeId);

	public ArrayList<HackathonChallengeMapBean> getHackathonChallengeMapList();

	public void updateMappingDetails(int parseInt, ArrayList<String> challengeIdList);



}
