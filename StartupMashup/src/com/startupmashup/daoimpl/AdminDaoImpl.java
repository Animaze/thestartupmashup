package com.startupmashup.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.startupmashup.bean.AdminBean;
import com.startupmashup.dao.AdminDao;

@Component
public class AdminDaoImpl extends BaseDaoImpl implements  AdminDao {
	
	private Connection connection;

	@Override
	public void saveAdminInfo(AdminBean adminBean) {

		try {
			connection = getConnection();

			String query = "insert into admin values(null,?,?,?,?,null,null,null,null)";
			PreparedStatement preparedStatement = connection
					.prepareStatement(query);
			preparedStatement.setString(1, adminBean.getFirstName());

			preparedStatement.setString(2, adminBean.getLastName());

			preparedStatement.setString(3, adminBean.getUserName());

			preparedStatement.setString(4, adminBean.getPassword());

			

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
	public boolean authoriseAdmin(String username, String password) {
		connection = getConnection();
System.out.println(password);
		String query = "select admin_username,admin_password from admin where admin_username=? and "
				+ "admin_password=?";
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement(query);

			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;
	}



	
	

}
