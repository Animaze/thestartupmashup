package com.startupmashup.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.startupmashup.bean.SkillBean;
import com.startupmashup.dao.SkillDao;
import com.startupmashup.service.SkillService;

@Service
public class SkillServiceImpl implements SkillService{

	@Autowired
	SkillDao skillDao;
	
	public void saveSkillInfo(SkillBean skillBean){
		skillDao.saveSkillInfo(skillBean);
	}

	@Override
	public List<SkillBean> getSkills() {
		return skillDao.getSkills();
	}

	@Override
	public void updateSkills(SkillBean skillBean) {
		skillDao.updateSkills(skillBean);
	}

	@Override
	public List<SkillBean> getSkillsById(List<Integer> mappedSkillIds) {
		
		return skillDao.getSkillsById(mappedSkillIds);
	}
	
}
