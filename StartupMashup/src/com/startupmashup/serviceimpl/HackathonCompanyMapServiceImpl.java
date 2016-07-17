package com.startupmashup.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.startupmashup.bean.CompanyBean;
import com.startupmashup.bean.HackathonCompanyMapBean;
import com.startupmashup.dao.HackathonCompanyMapDao;
import com.startupmashup.service.HackathonCompanyMapService;


@Service
public class HackathonCompanyMapServiceImpl implements HackathonCompanyMapService{
	
	
@Autowired HackathonCompanyMapDao hackathonCompanyMapDao;
	@Override
	public void saveMappingDetails(int companyId, int hackathonId) {
		
		hackathonCompanyMapDao.saveMappingDetails(companyId,hackathonId);
	}
	@Override
	public ArrayList<HackathonCompanyMapBean> findHackathonCompanyMapBeanByCompanyId(int id) {

		return hackathonCompanyMapDao.findHackathonCompanyMapBeanByCompanyId(id);
	}
	@Override
	public void updateMappingDetails(int companyId, ArrayList<Integer> hackathonIds) {
		hackathonCompanyMapDao.updateMappingDetails(companyId,hackathonIds);
		
	}
	@Override
	public List<CompanyBean> getCompanyMappedToHackathon(int hackathonId) {
		return hackathonCompanyMapDao.getCompanyMappedToHackathon(hackathonId);
	}
	@Override
	public List<HackathonCompanyMapBean> getList() {
		
		return hackathonCompanyMapDao.getList();
	}

}
