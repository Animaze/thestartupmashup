package com.startupmashup.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.startupmashup.bean.CompanyChallengeMapBean;
import com.startupmashup.dao.CompanyChallengeMapDao;

@Component
public class CompanyChallengeMapDaoImpl extends BaseDaoImpl implements CompanyChallengeMapDao{
	
	String sqlQuery;
	Connection connection;
	PreparedStatement preparedStatement;
	ResultSet resultSet;
	

	@Override
	public void saveMappingDetails(int companyId, ArrayList<Integer> challengeId) {

		try{
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession();
			
			String createdBy = (String)session.getAttribute("adminUsername");
			Date createdDate = new Date();
			
			connection = getConnection();
			
			sqlQuery = "insert into company_challenge_map_table values (null, ?, ?, ?, ?, null, null)";
			
			preparedStatement = connection.prepareStatement(sqlQuery);
			
			for(int chId:challengeId){
				
				preparedStatement.setInt(1,chId);
				preparedStatement.setInt(2, companyId);
				preparedStatement.setString(3, createdBy);
				preparedStatement.setDate(4, new java.sql.Date(createdDate.getTime()));
				
				preparedStatement.executeUpdate();
			
			}
			
		}catch(Exception e){
			e.toString();
			try{
				connection.close();
			}catch(Exception ex){
				ex.toString();
			}
		}
		
		
	}


	@Override
	public ArrayList<CompanyChallengeMapBean> findCompanyChallengeMapBeanByCompanyId(int id) {
		CompanyChallengeMapBean companyChallengeMapBean = null;
		ArrayList<CompanyChallengeMapBean> companyChallengeMapList=new ArrayList<CompanyChallengeMapBean>();
		try{
			connection = getConnection();
			
			sqlQuery="SELECT distinct  ref_company_id,ref_challenge_id FROM `company_challenge_map_table` WHERE ref_company_id=?";	
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, id);
		
			ResultSet resultSet = preparedStatement.executeQuery();
			
			
			while(resultSet.next()){
				
				
				companyChallengeMapBean = new CompanyChallengeMapBean();
		
				/*companyChallengeMapBean.setId(resultSet.getInt("company_challenge_map_id"));*/
				companyChallengeMapBean.setChallengeId(resultSet.getInt("ref_challenge_id"));
				companyChallengeMapBean.setCompanyId(resultSet.getInt("ref_company_id"));
				companyChallengeMapList.add(companyChallengeMapBean);
				
			}
			
		
		}catch(Exception e){
			e.toString();
			try{
				connection.close();
			}catch(Exception ex){
				ex.toString();
			}
		}
		
		return companyChallengeMapList;
		
	}


	@Override
	public void updateMappingDetails(int companyId,
			ArrayList<Integer> challengeIds) {
		try{
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession();
			
			String updatedBy = (String)session.getAttribute("adminUsername");
			Date updatedDate = new Date();
			
			String createdBy = "";
			Date createdDate = null;
			
			connection = getConnection();
			
			sqlQuery = "select created_by, created_date from company_challenge_map_table "
					+ "where ref_company_id = ? ";

			preparedStatement = connection.prepareStatement(sqlQuery);
			
			preparedStatement.setInt(1, companyId);
			
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()){
				createdBy = resultSet.getString(1);
				createdDate = resultSet.getDate(2);
			}
			
			sqlQuery="delete from company_challenge_map_table where ref_company_id = ?";
			
			PreparedStatement deleteStatement = connection.prepareStatement(sqlQuery);
			
			deleteStatement.setInt(1, companyId);
			
			deleteStatement.executeUpdate();
			
			sqlQuery = "insert into company_challenge_map_table values (null, ?, ?, ?, ?, ?, ?)";
			
			preparedStatement = connection.prepareStatement(sqlQuery);

			for(int chId:challengeIds){
				
				preparedStatement.setInt(1,chId);				
				preparedStatement.setInt(2, companyId);
				preparedStatement.setString(3, 	createdBy);
				preparedStatement.setDate(4, new java.sql.Date(createdDate.getTime()));
				preparedStatement.setString(5, updatedBy);
				preparedStatement.setDate(6, new java.sql.Date(updatedDate.getTime()));
				
				preparedStatement.executeUpdate();
				
			}
			
		}catch(Exception e){
			e.toString();
			try{
				connection.close();
			}catch(Exception ex){
				ex.toString();
			}
		}
		
		
	}

		
	

}
