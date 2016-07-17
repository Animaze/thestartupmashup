package com.startupmashup.service;

import java.util.List;

import com.startupmashup.bean.SkillBean;

public interface SkillService {

	public void saveSkillInfo(SkillBean skillBean);
	
	public List<SkillBean> getSkills();
	
	public void updateSkills(SkillBean skillBean);

	public List<SkillBean> getSkillsById(List<Integer> mappedSkillIds);
	
}
