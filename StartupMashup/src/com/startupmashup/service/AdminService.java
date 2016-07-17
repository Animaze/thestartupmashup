package com.startupmashup.service;

import com.startupmashup.bean.AdminBean;



public interface AdminService {
	
	void saveAdminInfo(AdminBean admintBean);
	
	boolean authoriseAdmin(String username, String password);

}
