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

import com.startupmashup.bean.CompanyBean;
import com.startupmashup.bean.HackathonCompanyMapBean;
import com.startupmashup.dao.HackathonCompanyMapDao;

@Component
public class HackathonCompanyMapDaoImpl extends BaseDaoImpl implements HackathonCompanyMapDao{
	
	
Connection connection;
PreparedStatement preparedStatement;
String sqlQuery;
ResultSet resultSet;

	@Override
	public void saveMappingDetails(int companyId, int hackathonId) {
		try{
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession();
			
			String createdBy = (String)session.getAttribute("adminUsername");
			Date createdDate = new Date();
			
			connection = getConnection();
		
			sqlQuery="insert into hackathon_company_map_table values (null, ?, ?, ?, ?, null, null)" ;
			preparedStatement = connection.prepareStatement(sqlQuery);
			
			
			preparedStatement.setInt(1, hackathonId);
			preparedStatement.setInt(2, companyId);
			preparedStatement.setString(3, createdBy);
			preparedStatement.setDate(4, new java.sql.Date(createdDate.getTime()));
			
			preparedStatement.executeUpdate();
		
		}catch(Exception e){
			System.out.println(e.toString());
	try{
		connection.close();
	}catch(Exception ex){
		System.out.println(ex.toString());
	}
}

}

	@Override
	public ArrayList<HackathonCompanyMapBean> findHackathonCompanyMapBeanByCompanyId(int id) {
		HackathonCompanyMapBean hackathonCompanyMapBean = null;
		ArrayList<HackathonCompanyMapBean> hackathonCompanyMapBeanList=new ArrayList<HackathonCompanyMapBean>();
		try{
			connection = getConnection();
			
			String sqlQuery="select * from hackathon_company_map_table where ref_company_id=?";	
			
			PreparedStatement statement = connection.prepareStatement(sqlQuery);
			
			statement.setInt(1, id);
			
			ResultSet resultSet = statement.executeQuery();
			
			if(resultSet.first()){
				resultSet.previous();
			while(resultSet.next()){
				hackathonCompanyMapBean = new HackathonCompanyMapBean();
		
				hackathonCompanyMapBean.setId(resultSet.getInt("hackathon_company_map_id"));
				hackathonCompanyMapBean.setHackathonId(resultSet.getInt("ref_hackathon_id"));
				hackathonCompanyMapBean.setCompanyId(resultSet.getInt("ref_company_id"));
				
				hackathonCompanyMapBeanList.add(hackathonCompanyMapBean);
			}
			}
			else {
				hackathonCompanyMapBeanList=null;
			}
		
		}catch(Exception e){
			e.toString();
			try{
				connection.close();
			}catch(Exception ex){
				ex.toString();
			}
		}
		return hackathonCompanyMapBeanList;
		
	}

	@Override
	public void updateMappingDetails(int companyId, ArrayList<Integer> hackathonIds) {
		try{
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession();
			
			String updatedBy = (String)session.getAttribute("adminUsername");
			Date updatedDate = new Date();
			System.out.println(updatedBy);
			String createdBy="";
			Date createdDate=null;
			
			connection = getConnection();

			sqlQuery = "select created_by, created_date from hackathon_company_map_table "
					+ "where ref_company_id = ? ";

			preparedStatement = connection.prepareStatement(sqlQuery);
			
			preparedStatement.setInt(1, companyId);
			
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()){
				createdBy = resultSet.getString(1);
				createdDate = (resultSet.getDate(2));
				System.out.println(createdBy +"   "+createdDate);
			}
			
			sqlQuery="delete from hackathon_company_map_table where ref_company_id = ?";
			
			preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, companyId);
			preparedStatement.executeUpdate();
			
			sqlQuery="insert into hackathon_company_map_table values (null, ?, ?, ?, ?, ?, ?)" ;
			
			preparedStatement = connection.prepareStatement(sqlQuery);
			
			for(int hId:hackathonIds){
				preparedStatement.setInt(1, hId);
				preparedStatement.setInt(2, companyId);
				preparedStatement.setString(3, 	createdBy);
				preparedStatement.setObject(4, createdDate);
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

	@Override
	public List<CompanyBean> getCompanyMappedToHackathon(int hackathonId) {
		CompanyBean companyBean = null;
		List<CompanyBean> listOfCompanies = new ArrayList<CompanyBean>();
		Connection connection=null;
		try {
			connection = getConnection();

			String sqlQuery = "select ref_company_id from hackathon_company_map_table where ref_hackathon_id=? ";
			PreparedStatement preparedStatement = connection
					.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, hackathonId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				sqlQuery = "select * from company where company_id=?";
				preparedStatement = connection.prepareStatement(sqlQuery);
				preparedStatement.setInt(1, resultSet.getInt(1));
				ResultSet resultSet2 = preparedStatement.executeQuery();
				if (resultSet2.next()) {
					companyBean = new CompanyBean();
					companyBean.setId(resultSet2.getInt("company_id"));
					companyBean.setName(resultSet2.getString("company_name"));
					companyBean.setLogo(resultSet2.getString("company_logo"));
					companyBean.setDescription(resultSet2
							.getString("company_description"));
					listOfCompanies.add(companyBean);
				}

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

		return listOfCompanies;

	}
	
	@Override
	public List<HackathonCompanyMapBean> getList() {
		
		HackathonCompanyMapBean hackathonCompanyMapBean = null;
		List<HackathonCompanyMapBean> hackathonCompanyMapBeanList=new ArrayList<HackathonCompanyMapBean>();
		try{
			connection = getConnection();
			
			sqlQuery="select * from hackathon_company_map_table";	
			
			preparedStatement = connection.prepareStatement(sqlQuery);
			
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.first()){
				resultSet.previous();
			while(resultSet.next()){
				hackathonCompanyMapBean = new HackathonCompanyMapBean();
		
				hackathonCompanyMapBean.setHackathonId(resultSet.getInt("ref_hackathon_id"));
				hackathonCompanyMapBean.setCompanyId(resultSet.getInt("ref_company_id"));
				
				hackathonCompanyMapBeanList.add(hackathonCompanyMapBean);
			}
			}
			else {
				hackathonCompanyMapBeanList=null;
			}
		
		}catch(Exception e){
			e.toString();
			try{
				connection.close();
			}catch(Exception ex){
				ex.toString();
			}
		}
		return hackathonCompanyMapBeanList;
	}

}
