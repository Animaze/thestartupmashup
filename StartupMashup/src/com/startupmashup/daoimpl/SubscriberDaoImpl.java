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

import com.startupmashup.bean.SubscriberBean;
import com.startupmashup.dao.SubscriberDao;
@Component
public class SubscriberDaoImpl extends BaseDaoImpl implements SubscriberDao{
	Connection connection;

	@Override
	public void saveSubscriberInfo(String emailId) {
		try {
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession();
			
			String createdBy = (String)session.getAttribute("adminUsername");
			Date createdDate = new Date();
			
			connection = getConnection();

			String query = "insert into subscriber values(null, ?, ?, ?, null, null)";
			
			PreparedStatement preparedStatement = connection
					.prepareStatement(query);
			
			preparedStatement.setString(1, emailId);
			preparedStatement.setString(2, createdBy);
			preparedStatement.setDate(3, new java.sql.Date(createdDate.getTime()));

			
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
	public ArrayList<SubscriberBean> getSubscribersList() {

		SubscriberBean subscriberBean=null;
		ArrayList<SubscriberBean> subscriberBeanList=new ArrayList<SubscriberBean>();
		try {
			connection = getConnection();

			String query = "SELECT * FROM `subscriber`";
			PreparedStatement preparedStatement = connection
					.prepareStatement(query);

			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				subscriberBean=new SubscriberBean();
				
				subscriberBean.setId(resultSet.getInt("subscriber_id"));
				subscriberBean.setEmailId(resultSet.getString("subscriber_email_id"));
				
				subscriberBean.setCreatedBy("Anirudh");
				subscriberBean.setUpdatedBy("Anirudh");
				subscriberBean.setCreatedDate(new Date());
				subscriberBean.setUpdatedDate(new Date());
				
				subscriberBeanList.add(subscriberBean);
				
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
		return subscriberBeanList;
	}

}
