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

import com.startupmashup.bean.CompanyBean;
import com.startupmashup.dao.CompanyDao;


@Component
public class CompanyDaoImpl extends BaseDaoImpl implements CompanyDao  {
	
	private Connection connection;

	@Override
	public int saveCompanyInfo(CompanyBean companyBean) {
		int companyId=0;
		try {
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession();
			
			String createdBy = (String)session.getAttribute("adminUsername");
			Date createdDate = new Date();
			
			connection = getConnection();

		    String query = "insert into company values(null, ?, ?, ?, ?, ?, null, null)";
			
		    PreparedStatement preparedStatement = connection
					.prepareStatement(query);
			
			preparedStatement.setString(1, companyBean.getName());
			preparedStatement.setString(2, companyBean.getDescription());
			preparedStatement.setString(3, companyBean.getLogo());
			preparedStatement.setString(4, createdBy);
			preparedStatement.setDate(5, new java.sql.Date(createdDate.getTime()));

			preparedStatement.executeUpdate();
			
			String sqlQuery="select LAST_INSERT_ID()";
			
			PreparedStatement preparedStatement2 = connection
					.prepareStatement(sqlQuery);
			
			ResultSet resultSet = preparedStatement2.executeQuery();
			
			while(resultSet.next()){
				companyId = resultSet.getInt(1);
				
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

	return companyId;
		
	}

	@Override
	public ArrayList<CompanyBean> getCompanyList() {
		CompanyBean companyBean=null;
		ArrayList<CompanyBean> companyBeanList=new ArrayList<CompanyBean>();
		try {
			connection = getConnection();

			String query = "select * from company";
			PreparedStatement preparedStatement = connection
					.prepareStatement(query);

			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				companyBean=new CompanyBean();
				
				companyBean.setId(resultSet.getInt("company_id"));
				companyBean.setName(resultSet.getString("company_name"));
				companyBean.setDescription(resultSet.getString("company_description"));
				companyBean.setLogo(resultSet.getString("company_logo"));
		
			
				companyBean.setCreatedBy(resultSet.getString("created_by"));
				companyBean.setCreatedDate(resultSet.getDate("created_date"));
				companyBean.setUpdatedBy(resultSet.getString("updated_by"));
				companyBean.setUpdatedDate(resultSet.getDate("updated_date"));

				companyBeanList.add(companyBean);
				
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
		return companyBeanList;
	}

	@Override
	public void updateCompanyInfo(CompanyBean companyBean) {
		

		try {
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession();
			
			String updatedBy = (String)session.getAttribute("adminUsername");
			
			Date updatedDate = new Date();
			
			connection = getConnection();

		    String query = "UPDATE company "
		    		+ "SET company_name = ?, company_description = ?, company_logo = ?, "
		    		+ "updated_by = ?, updated_date = ? "
		    		+ "where company_id=?";
		    
			PreparedStatement preparedStatement = connection
					.prepareStatement(query);
			
			preparedStatement.setString(1, companyBean.getName());
			preparedStatement.setString(2, companyBean.getDescription());
			preparedStatement.setString(3, companyBean.getLogo());
			preparedStatement.setString(4, updatedBy);
			preparedStatement.setDate(5, new java.sql.Date(updatedDate.getTime()));
			preparedStatement.setInt(6, companyBean.getId());

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
	public CompanyBean findCompanyById(int id) {
CompanyBean companyBean=null;
		
		try {
			connection = getConnection();

			String query = "select * from company where company_id=?";
			PreparedStatement preparedStatement = connection
					.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				companyBean=new CompanyBean();
				
				companyBean.setId(id);
				companyBean.setName(resultSet.getString("company_name"));
				companyBean.setDescription(resultSet.getString("company_description"));
				companyBean.setLogo(resultSet.getString("company_logo"));
		

				companyBean.setCreatedBy(resultSet.getString("created_by"));
				companyBean.setCreatedDate(resultSet.getDate("created_date"));
				companyBean.setUpdatedBy(resultSet.getString("updated_by"));
				companyBean.setUpdatedDate(resultSet.getDate("updated_date"));

				
				
				
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
		return companyBean;

	}

}
