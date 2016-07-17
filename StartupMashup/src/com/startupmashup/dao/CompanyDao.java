package com.startupmashup.dao;

import java.util.ArrayList;

import com.startupmashup.bean.CompanyBean;

public interface CompanyDao {
	
	int saveCompanyInfo(CompanyBean companyBean);

	ArrayList<CompanyBean> getCompanyList();

	void updateCompanyInfo(CompanyBean companyBean);

	CompanyBean findCompanyById(int companyId);



}
