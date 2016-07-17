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

import com.startupmashup.bean.ChallengeBean;
import com.startupmashup.dao.ChallengeDao;

@Component
public class ChallengeDaoImpl extends BaseDaoImpl implements ChallengeDao {

	Connection connection = null;

	@Override
	public int  saveChallengeInfo(ChallengeBean challengeBean) {
		int challengeId=0;
		try {
			
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession();
			
			String createdBy = (String)session.getAttribute("adminUsername");
			Date createdDate = new Date();
			
			connection = getConnection();

			String query = "insert into challenge values(null,?,?,?,?,null,null)";
			String sqlQuery = "select LAST_INSERT_ID()";
			
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, challengeBean.getName());
			preparedStatement.setString(2, challengeBean.getDescription());
			preparedStatement.setString(3, createdBy);
			preparedStatement.setDate(4, new java.sql.Date(createdDate.getTime()));
			
			preparedStatement.executeUpdate();
			
			preparedStatement = connection.prepareStatement(sqlQuery);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				challengeId = rs.getInt(1);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}return challengeId ;
	
	}

	@Override
	public List<ChallengeBean> getChallengesListByHackathonId(int hackathonId) {

		List<ChallengeBean> listOfChallenges = new ArrayList<ChallengeBean>();

		try {
			connection = getConnection();
			ChallengeBean challengeBean = null;

			String query = "select ref_challenge_id,challenge_name from challenge , hackathon_challenge_map_table "
					+ "where "
					+ "ref_hackathon_id=? and challenge_id=ref_challenge_id";

			PreparedStatement preparedStatement = connection
					.prepareStatement(query);

			preparedStatement.setInt(1, hackathonId);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				challengeBean = new ChallengeBean();
				challengeBean.setId(resultSet.getInt(1));
				challengeBean.setName(resultSet.getString(2));

				listOfChallenges.add(challengeBean);
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

		return listOfChallenges;
	}

	@Override
	public List<ChallengeBean> getChallenges() {

		ChallengeBean challengeBean = null;
		List<ChallengeBean> listOfChallenges = new ArrayList<ChallengeBean>();

		try {
			connection = getConnection();

			String query = "select * from challenge";

			PreparedStatement preparedStatement = connection
					.prepareStatement(query);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				challengeBean = new ChallengeBean();
				challengeBean.setId(resultSet.getInt("challenge_id"));
				challengeBean.setName(resultSet.getString("challenge_name"));
				challengeBean.setDescription(resultSet.getString("challenge_description"));
				challengeBean.setCreatedBy(resultSet.getString("created_by"));
				challengeBean.setCreatedDate(resultSet.getDate("created_date"));
				challengeBean.setUpdatedBy(resultSet.getString("updated_by"));
				challengeBean.setUpdatedDate(resultSet.getDate("updated_date"));

				listOfChallenges.add(challengeBean);
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

		return listOfChallenges;
	}

	@Override
	public ChallengeBean getDetailsById(int challengeId) {

		ChallengeBean challengeBean = null;

		try {
			connection = getConnection();

			String query = "select challenge_name,challenge_description from challenge "
					+ "where " + "challenge_id=?";

			PreparedStatement preparedStatement = connection
					.prepareStatement(query);

			preparedStatement.setInt(1, challengeId);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				challengeBean = new ChallengeBean();
				challengeBean.setId(challengeId);
				challengeBean.setName(resultSet.getString(1));
				challengeBean.setDescription(resultSet.getString(2));
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

		return challengeBean;
	}

	@Override
	public void updateChallengeInfo(ChallengeBean challengeBean,
			List<String> selectedSkillList) {

		try {
			
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession();
			
			String updatedBy = (String)session.getAttribute("adminUsername");
			Date updatedDate = new Date();
			
			connection = getConnection();

			String query = "update challenge set challenge_name = ? , "
					+ "challenge_description = ?, updated_by = ?, updated_date = ? "
					+ "where challenge_id = ?";

			PreparedStatement preparedStatement = connection
					.prepareStatement(query);

			preparedStatement.setString(1, challengeBean.getName());
			preparedStatement.setString(2, challengeBean.getDescription());
			preparedStatement.setString(3, updatedBy);
			preparedStatement.setDate(4, new java.sql.Date(updatedDate.getTime()));
			preparedStatement.setInt(5, challengeBean.getId());
			
			preparedStatement.executeUpdate();

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
	
	
// Useless
	@Override
	public List<ChallengeBean> getChallengesList(List<Integer> challengeIdList) {
List<ChallengeBean> challengeBeanList = null;
		
		try{
			
			connection = getConnection();

			String sqlQuery = "select * from challenge";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			challengeBeanList = new ArrayList<ChallengeBean>();
			ChallengeBean challengeBean;
			
			while(resultSet.next()){
				challengeBean = new ChallengeBean();
				challengeBean.setId(resultSet.getInt("challenge_id"));
				challengeBean.setName(resultSet.getString("challenge_name"));
				challengeBean.setDescription(resultSet.getString("challenge_description"));
				challengeBean.setCreatedBy(resultSet.getString("created_by"));
				challengeBean.setCreatedDate(resultSet.getDate("created_date"));
				challengeBean.setUpdatedBy(resultSet.getString("updated_by"));
				challengeBean.setUpdatedDate(resultSet.getDate("updated_date"));
				
				challengeBeanList.add(challengeBean);
			}
			
		}catch(Exception e){
			e.toString();
		}finally{
			try{
				connection.close();
			}catch(Exception e){
				System.out.println(e.toString());
			}
		}
		
		return challengeBeanList;
	}
	
	

}
