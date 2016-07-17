package com.startupmashup.service;

import java.util.ArrayList;

import com.startupmashup.bean.CompanyBean;



public interface CompanyService {
	
 int saveCompanyInfo(CompanyBean companyBean);

ArrayList<CompanyBean> getCompanyList();

public void updateCompanyInfo(CompanyBean companyBean) ;

CompanyBean findCompanyById(int companyId);
		
	}
