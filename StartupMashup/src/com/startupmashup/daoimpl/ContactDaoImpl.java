package com.startupmashup.daoimpl;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.startupmashup.bean.ContactBean;
import com.startupmashup.dao.ContactDao;

@Component
public class ContactDaoImpl extends BaseDaoImpl implements ContactDao {

	private Connection connection;

	@Override
	public void saveContactInfo(ContactBean contactBean) {

		try {
			connection = getConnection();

			String query = "insert into contact values(null, ?, ?, ?, ?, ?, ?, null, null)";
			
			PreparedStatement preparedStatement = connection
					.prepareStatement(query);
			
			preparedStatement.setString(1, contactBean.getName());
         	preparedStatement.setString(2, contactBean.getEmailId());
			preparedStatement.setString(3, contactBean.getPhoneNumber());
			preparedStatement.setString(4, contactBean.getQuery());
			preparedStatement.setString(5, contactBean.getName());
			preparedStatement.setDate(6, new java.sql.Date(new Date().getTime()));

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
