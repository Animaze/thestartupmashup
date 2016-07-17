package com.startupmashup.dao;

import java.util.ArrayList;
import java.util.List;

import com.startupmashup.bean.HackathonChallengeMapBean;


public interface HackathonChallengeMapDao {

	public void saveMappingDetails(int hackathonId, ArrayList<String> challengeId);

	public ArrayList<HackathonChallengeMapBean> getHackathonChallengeMapList();

	public void updateMappingDetails(int parseInt, ArrayList<String> challengeIdList);

}
