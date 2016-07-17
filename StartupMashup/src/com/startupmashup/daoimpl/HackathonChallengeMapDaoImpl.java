package com.startupmashup.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.startupmashup.bean.HackathonChallengeMapBean;
import com.startupmashup.dao.HackathonChallengeMapDao;

@Component
public class HackathonChallengeMapDaoImpl extends BaseDaoImpl implements HackathonChallengeMapDao{

	private String sqlQuery;
	Connection connection;
	PreparedStatement preparedStatement;
	ResultSet resultSet;
	
	@Override
	public void saveMappingDetails(int hackathonId, ArrayList<String> challengeId) {
		try {
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession();
			
			String createdBy = (String)session.getAttribute("adminUsername");
			Date createdDate = new Date();
			
			connection = getConnection();

			sqlQuery = "insert into hackathon_challenge_map_table values (null,?,?,?,?,null,null);";
			
			preparedStatement = connection.prepareStatement(sqlQuery);
		
			for(String str : challengeId){
				
				preparedStatement.setInt(1, hackathonId);
				preparedStatement.setInt(2, Integer.parseInt(str.trim()));
				preparedStatement.setString(3, createdBy);
				preparedStatement.setDate(4, new java.sql.Date(createdDate.getTime()));
				
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
	public ArrayList<HackathonChallengeMapBean> getHackathonChallengeMapList() {
		
		ArrayList<HackathonChallengeMapBean> hackathonChallengeMapList = new ArrayList<HackathonChallengeMapBean>();
		try{
			 connection = getConnection();
		
		String query = "select * from hackathon_challenge_map_table" ;
		PreparedStatement prepareStatement = connection.prepareStatement(query);
		ResultSet rs = prepareStatement.executeQuery();
		while(rs.next())
		{
			HackathonChallengeMapBean hackathonChallengeMapBean = new HackathonChallengeMapBean();
			hackathonChallengeMapBean.setChallengeId(rs.getInt("ref_challenge_Id"));
			hackathonChallengeMapBean.setHackathonId(rs.getInt("ref_hackathon_Id"));
			hackathonChallengeMapList.add(hackathonChallengeMapBean);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}return hackathonChallengeMapList ;

	}

	@Override
	public void updateMappingDetails(int hackathonId, ArrayList<String> challengeIdList) {
		try {
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession();
			
			String updatedBy = (String)session.getAttribute("adminUsername");
			Date updatedDate = new Date();
			
			String createdBy="";
			Date createdDate=null;
			
			connection = getConnection();

			sqlQuery = "select created_by, created_date from hackathon_challenge_map_table "
					+ "where ref_hackathon_id = ? ";

			preparedStatement = connection.prepareStatement(sqlQuery);
			
			preparedStatement.setInt(1, hackathonId);
			
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()){
				createdBy = resultSet.getString(1);
				createdDate = (resultSet.getDate(2));
			}
			
			sqlQuery = "delete from hackathon_challenge_map_table "
					+ "where ref_hackathon_id = ?";
			
			preparedStatement = connection.prepareStatement(sqlQuery);
			
			preparedStatement.setInt(1, hackathonId);
			
			preparedStatement.executeUpdate();
			
			sqlQuery = "insert into hackathon_challenge_map_table values (null, ?, ?, ?, ?, ?, ?)";
			
			preparedStatement = connection.prepareStatement(sqlQuery);
			
			for(String str : challengeIdList){
				preparedStatement.setInt(1, hackathonId);
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

	

}
