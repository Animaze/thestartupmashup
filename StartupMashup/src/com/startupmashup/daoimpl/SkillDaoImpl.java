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

import com.startupmashup.bean.SkillBean;
import com.startupmashup.dao.SkillDao;

@Component
public class SkillDaoImpl extends BaseDaoImpl implements SkillDao {

	private String sqlQuery;
	Connection connection;
	PreparedStatement preparedStatement;
	ResultSet resultSet;

	@Override
	public void saveSkillInfo(SkillBean skillBean){
		try{
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession();
			
			String createdBy = (String)session.getAttribute("adminUsername");
			Date createdDate = new Date();
			
			connection = getConnection();
			
			sqlQuery = "insert into skill values (null, ?, ?, ?, null, null)";
			
			preparedStatement = connection.prepareStatement(sqlQuery);
			
			preparedStatement.setString(1, skillBean.getName());
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
	public List<SkillBean> getSkills() {
		ArrayList<SkillBean> skillList=new ArrayList<SkillBean>();
		try {
			connection = getConnection();

			String query = "select * from skill";
			PreparedStatement preparedStatement = connection
					.prepareStatement(query);

			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				SkillBean skill=new SkillBean();
				
				skill.setId(resultSet.getInt("skill_id"));
				skill.setName(resultSet.getString("skill_name"));
				
				
		
			
				skill.setCreatedBy(resultSet.getString("created_by"));
				skill.setCreatedDate(resultSet.getDate("created_date"));
				skill.setUpdatedBy(resultSet.getString("updated_by"));
				skill.setUpdatedDate(resultSet.getDate("updated_date"));
				
				skillList.add(skill);
				
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
		return skillList;
	}
	
	@Override
	public List<SkillBean> getSkillsById(List<Integer> mappedSkillIds){
		
		 List<SkillBean> skillBeanList = null;
	        try{
	            
	            connection = getConnection();

	            sqlQuery = "select * from skill where skill_id = ?";
	            
	            preparedStatement = connection.prepareStatement(sqlQuery);
	            
	            skillBeanList = new ArrayList<SkillBean>();
	            SkillBean skillBean;
	            for(int x : mappedSkillIds){
	                
	                preparedStatement.setInt(1, x);
	                resultSet = preparedStatement.executeQuery();
	                
	                if(resultSet.next()){
	                    
	                    skillBean = new SkillBean();
	                
	                    skillBean.setId(resultSet.getInt("skill_id"));
	                    skillBean.setName(resultSet.getString("skill_name"));
	                    skillBean.setCreatedBy(resultSet.getString("created_by"));
	                    skillBean.setCreatedDate(resultSet.getDate("created_date"));
	                    skillBean.setUpdatedBy(resultSet.getString("updated_by"));
	                    skillBean.setUpdatedDate(resultSet.getDate("updated_date"));
	                    
	                    skillBeanList.add(skillBean);
	                }
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
	        
		return skillBeanList;
	}

	@Override
	public void updateSkills(SkillBean skillBean) {
		try {
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession();
			
			String updatedBy = (String)session.getAttribute("adminUsername");
			Date updatedDate = new Date();

			connection = getConnection();

			sqlQuery = "update skill "
					+ "set "
					+ "skill_name=? , "
					+ "updated_by=? , "
					+ "updated_date=? "
					+ "where "
					+ "skill_id=?";

			preparedStatement = connection.prepareStatement(sqlQuery);

			preparedStatement.setString(1, skillBean.getName());
			preparedStatement.setString(2, updatedBy);
			
			preparedStatement.setDate(3, new java.sql.Date(updatedDate.getTime()));
			preparedStatement.setInt(4,skillBean.getId());
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
