package com.startupmashup.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.startupmashup.bean.ChallengeSkillMapBean;
import com.startupmashup.dao.ChallengeSkillMapDao;

@Component
public class ChallengeSkillMapDaoImpl extends BaseDaoImpl implements ChallengeSkillMapDao{

	private String sqlQuery;
	Connection connection;
	PreparedStatement preparedStatement;
	ResultSet resultSet;
	
	@Override
	public void saveMappingDetails(int challengeId, List<String> skillId) {
		try{
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession();
			
			String createdBy = (String)session.getAttribute("adminUsername");
			Date createdDate = new Date();
			
			connection = getConnection();
			
			sqlQuery = "insert into challenge_skill_map_table values (null,?,?,?,?, null, null)";
			
			preparedStatement = connection.prepareStatement(sqlQuery);
			
			for(String str : skillId){
				preparedStatement.setInt(1, challengeId);
				preparedStatement.setInt(2, Integer.parseInt(str));
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
	public List<Integer> getMappedSkillIds(int challengeId) {
		List<Integer> mappedSkillIds=new ArrayList<Integer>();

		try {
			connection = getConnection();
			
			sqlQuery = "select ref_skill_id from challenge_skill_map_table "
					+ "where "
					+ "ref_challenge_id=?";
			
			preparedStatement = connection
					.prepareStatement(sqlQuery);			
			
			preparedStatement.setInt(1, challengeId);
			
			resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next()){
				mappedSkillIds.add(resultSet.getInt(1));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return mappedSkillIds;
	}

	@Override
	public void editMappingDetails(int challengeId, List<String> selectedSkillList) {
		try {
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession();
			
			String updatedBy = (String)session.getAttribute("adminUsername");
			Date updatedDate = new Date();
			
			String createdBy="";
			Date createdDate=null;
			
			connection = getConnection();

			sqlQuery = "select created_by, created_date from challenge_skill_map_table "
					+ "where ref_challenge_id = ? ";

			preparedStatement = connection.prepareStatement(sqlQuery);
			
			preparedStatement.setInt(1, challengeId);
			
			resultSet = preparedStatement.executeQuery();
			
			/*if(resultSet.next()){
				createdBy = resultSet.getString(1);
				createdDate = (resultSet.getDate(2));
			}*/
			
			createdBy = "Anupam";
			createdDate = updatedDate;
			
			sqlQuery = "delete from challenge_skill_map_table "
					+ "where ref_challenge_id = ?";
			
			preparedStatement = connection.prepareStatement(sqlQuery);
			
			preparedStatement.setInt(1, challengeId);
			
			preparedStatement.executeUpdate();
			
			sqlQuery = "insert into challenge_skill_map_table values (null, ?, ?, ?, ?, ?, ?)";
			
			preparedStatement = connection.prepareStatement(sqlQuery);
			
			for(String str : selectedSkillList){
				preparedStatement.setInt(1, challengeId);
				preparedStatement.setInt(2, Integer.parseInt(str));
				preparedStatement.setString(3, 	createdBy);
				preparedStatement.setDate(4, new java.sql.Date(createdDate.getTime()));
				preparedStatement.setString(5, updatedBy);
				preparedStatement.setDate(6, new java.sql.Date(updatedDate.getTime()));
				
				preparedStatement.executeUpdate();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public List<ChallengeSkillMapBean> getChallengeSkillMapList() {
		List<ChallengeSkillMapBean> listOfChallengeSkillMapBeans = new ArrayList<ChallengeSkillMapBean>();
		ChallengeSkillMapBean challengeSkillMapBean = null;
		
		try {
			connection = getConnection();

			sqlQuery = "select * from challenge_skill_map_table";
			preparedStatement = connection
					.prepareStatement(sqlQuery);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				challengeSkillMapBean = new ChallengeSkillMapBean();
				challengeSkillMapBean.setChallengeId(resultSet.getInt("ref_challenge_id"));
				challengeSkillMapBean.setSkillId(resultSet.getInt("ref_skill_id"));
				
				listOfChallengeSkillMapBeans.add(challengeSkillMapBean);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return listOfChallengeSkillMapBeans;

	
	}

}
