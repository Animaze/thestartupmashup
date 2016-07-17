package com.startupmashup.dao;

import java.util.List;

import com.startupmashup.bean.SkillBean;

public interface SkillDao {

	public void saveSkillInfo(SkillBean skillBean);
	
	public List<SkillBean> getSkills();	

	public void updateSkills(SkillBean skillBean);

	List<SkillBean> getSkillsById(List<Integer> mappedSkillIds);
	
}
