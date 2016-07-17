package com.startupmashup.serviceimpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.startupmashup.bean.CompanyBean;
import com.startupmashup.dao.CompanyDao;
import com.startupmashup.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService{
	
	@Autowired CompanyDao companyDao;

	@Override
	public int saveCompanyInfo(CompanyBean companyBean) {
	
		return companyDao.saveCompanyInfo(companyBean);
	}

	@Override
	public ArrayList<CompanyBean> getCompanyList() {
		return companyDao.getCompanyList();
	}

	@Override
	public void updateCompanyInfo(CompanyBean companyBean) {
	companyDao.updateCompanyInfo(companyBean);
		
	}

	@Override
	public CompanyBean findCompanyById(int companyId) {
		return companyDao.findCompanyById(companyId);
	}

}
