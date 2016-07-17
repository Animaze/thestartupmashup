package com.startupmashup.dao;

import com.startupmashup.bean.AdminBean;

public interface AdminDao extends BaseDao {

	public void saveAdminInfo(AdminBean adminBean);
	
		
	
	public boolean authoriseAdmin(String username, String password) ;
	
	

}
