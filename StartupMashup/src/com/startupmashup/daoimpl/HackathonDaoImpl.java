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

import com.startupmashup.bean.HackathonBean;
import com.startupmashup.dao.HackathonDao;

@Component
public class HackathonDaoImpl extends BaseDaoImpl implements HackathonDao {

	Connection connection;

	@Override
	public int saveHackathonInfo(HackathonBean hackathonBean) {
		int hackathonId = 0;

		try {
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession();
			
			String createdBy = (String)session.getAttribute("adminUsername");
			Date createdDate = new Date();
			
			connection = getConnection();

			String query = "insert into hackathon values(null, ?, ?, ?, ?, ?, ?, ?, ?, null, null)";
			
			PreparedStatement preparedStatement = connection
					.prepareStatement(query);
			
			preparedStatement.setString(1, hackathonBean.getName());
			preparedStatement.setString(2, hackathonBean.getStatus());
			preparedStatement.setString(3, hackathonBean.getVenue());
			preparedStatement.setObject(4, hackathonBean.getDate());
			preparedStatement.setString(5, hackathonBean.getParticipantCount());
			preparedStatement.setString(6, hackathonBean.getImage());
			preparedStatement.setString(7, createdBy);
			preparedStatement.setDate(8, new java.sql.Date(createdDate.getTime()));
			
			preparedStatement.executeUpdate();
			
            query = "select last_insert_id();";
			
			preparedStatement = connection.prepareStatement(query);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
				hackathonId = resultSet.getInt(1);
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
		return hackathonId;

	}


	@Override
	public ArrayList<HackathonBean> getHackathonList() {

		HackathonBean hackathonBean = null;
		ArrayList<HackathonBean> hackathonBeanList = new ArrayList<HackathonBean>();
		try {
			connection = getConnection();

			String query = "select * from hackathon order by hackathon_date";
			PreparedStatement preparedStatement = connection
					.prepareStatement(query);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				hackathonBean = new HackathonBean();

				hackathonBean.setId(resultSet.getInt("hackathon_id"));
				hackathonBean.setName(resultSet.getString("hackathon_name"));
				hackathonBean
						.setStatus(resultSet.getString("hackathon_status"));
				hackathonBean.setVenue(resultSet.getString("hackathon_venue"));
				hackathonBean.setDate(resultSet.getDate("hackathon_date"));
				hackathonBean.setParticipantCount(((Integer) resultSet
						.getInt("hackathon_count_participants")).toString());
				hackathonBean.setImage(resultSet.getString("hackathon_image"));
				hackathonBean.setCreatedBy(resultSet.getString("created_by"));
				hackathonBean.setCreatedDate(resultSet.getDate("created_date"));
				hackathonBean.setUpdatedBy(resultSet.getString("updated_by"));
				hackathonBean.setUpdatedDate(resultSet.getDate("updated_date"));

				hackathonBeanList.add(hackathonBean);

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
		return hackathonBeanList;
	}

	@Override
	public HackathonBean getBean(String hackathonId) {
		HackathonBean hackathonBean = null;

		try {
			connection = getConnection();

			String query = "select * from hackathon where hackathon_id=?";
			PreparedStatement preparedStatement = connection
					.prepareStatement(query);

			preparedStatement.setInt(1, Integer.parseInt(hackathonId));
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				hackathonBean = new HackathonBean();

				hackathonBean.setId(resultSet.getInt("hackathon_id"));
				hackathonBean.setName(resultSet.getString("hackathon_name"));
				hackathonBean
						.setStatus(resultSet.getString("hackathon_status"));
				hackathonBean.setVenue(resultSet.getString("hackathon_venue"));
				hackathonBean.setDate(resultSet.getDate("hackathon_date"));
				hackathonBean.setParticipantCount(((Integer) resultSet
						.getInt("hackathon_count_participants")).toString());
				hackathonBean.setImage(resultSet.getString("hackathon_image"));
				hackathonBean.setCreatedBy(resultSet.getString("created_by"));
				hackathonBean.setCreatedDate(resultSet.getDate("created_date"));
				hackathonBean.setUpdatedBy(resultSet.getString("updated_by"));
				hackathonBean.setUpdatedDate(resultSet.getDate("updated_date"));

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

		return hackathonBean;
	}
	
	

	@Override
	public void editHackathonInfo(HackathonBean hackathonBean) {
		try {
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession();
			
			String updatedBy = (String)session.getAttribute("adminUsername");
			Date updatedDate = new Date();
			
			connection = getConnection();
			PreparedStatement preparedStatement=null;
			String query = "";
		
			query = "update hackathon set hackathon_name = ?, hackathon_status = ?, "
				+ "hackathon_venue = ?, hackathon_date = ?, hackathon_image = ?, updated_by = ?, updated_date = ? "
				+ "where hackathon_id = ?";
			
			preparedStatement = connection.prepareStatement(query);
	 		
	 		preparedStatement.setString(1, hackathonBean.getName());
			preparedStatement.setString(2, hackathonBean.getStatus());
			preparedStatement.setString(3, hackathonBean.getVenue());
			preparedStatement.setObject(4, hackathonBean.getDate());
			preparedStatement.setString(5, hackathonBean.getImage());
			preparedStatement.setString(6, updatedBy);
			preparedStatement.setDate(7, new java.sql.Date(updatedDate.getTime()));
			preparedStatement.setInt(8, hackathonBean.getId());
			
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


	@Override
	public void incrementCount(int hackathonId) {
		PreparedStatement preparedStatement = null;
		try {
			connection = getConnection();

			String query = "update hackathon set hackathon_count_participants=hackathon_count_participants+1 "
					+ "where "
					+ "hackathon_id=?";

				preparedStatement = connection.prepareStatement(query);

				preparedStatement.setInt(1, hackathonId);

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

}
