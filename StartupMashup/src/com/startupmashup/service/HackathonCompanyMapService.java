package com.startupmashup.service;

import java.util.ArrayList;
import java.util.List;

import com.startupmashup.bean.CompanyBean;
import com.startupmashup.bean.HackathonCompanyMapBean;

public interface HackathonCompanyMapService {

	void saveMappingDetails(int companyId, int hackathonId);

	ArrayList<HackathonCompanyMapBean> findHackathonCompanyMapBeanByCompanyId(int id);

	void updateMappingDetails(int companyId, ArrayList<Integer> hackathonIds);

	List<CompanyBean> getCompanyMappedToHackathon(int hackathonId);

	List<HackathonCompanyMapBean> getList();

}
