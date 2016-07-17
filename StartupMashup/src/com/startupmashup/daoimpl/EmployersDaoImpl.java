package com.startupmashup.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.startupmashup.bean.EmployersBean;
import com.startupmashup.dao.EmployersDao;

@Component
public class EmployersDaoImpl extends BaseDaoImpl implements EmployersDao {
	Connection connection;

	@Override
	public void saveEmployersInfo(EmployersBean employersBean) {
	
		try {
			Date createdDate = new Date();
			
			connection = getConnection();

			String query = "insert into employers values(null, ?, ?, ?, ?, ?, ?, null, null)";
			
			PreparedStatement preparedStatement = connection
					.prepareStatement(query);
			
			preparedStatement.setString(1, employersBean.getCompanyName());
			preparedStatement.setString(2, employersBean.getEmployerName());
			preparedStatement.setString(3, employersBean.getPhoneNumber());
			preparedStatement.setString(4, employersBean.getEmailId());
			preparedStatement.setString(5, employersBean.getEmployerName());
			preparedStatement.setDate(6, new java.sql.Date(createdDate.getTime()));

			
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
