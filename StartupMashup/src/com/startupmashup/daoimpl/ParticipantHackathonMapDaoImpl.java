package com.startupmashup.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.startupmashup.bean.HackathonBean;
import com.startupmashup.bean.ParticipantHackathonMapBean;
import com.startupmashup.dao.ParticipantHackathonMapDao;

@Controller
public class ParticipantHackathonMapDaoImpl extends BaseDaoImpl implements ParticipantHackathonMapDao{
	
	private String sqlQuery;
	Connection connection;
	PreparedStatement preparedStatement;
	ResultSet resultSet;

	@Override
	public void saveMappingDetails(int hackathonId, int participantId) {
		try {
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession();
			
			String createdBy = (String)session.getAttribute("adminUsername");
			Date createdDate = new Date();
			
			connection = getConnection();

			String query = "insert into participant_hackathon_map_table values(null, ?, ?, ?, ?, null, null)";
			
			PreparedStatement preparedStatement = connection
					.prepareStatement(query);
			
			preparedStatement.setInt(1, hackathonId);
			preparedStatement.setInt(2, participantId);	
			preparedStatement.setString(3, createdBy);
			preparedStatement.setDate(4, new java.sql.Date(createdDate.getTime()));

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
	public ArrayList<ParticipantHackathonMapBean> getParticipantsHackathonMapList() {
		ArrayList<ParticipantHackathonMapBean> ParticipantHackathonMapList = new ArrayList<ParticipantHackathonMapBean>();
		try{
			 connection = getConnection();
		
		String query = "select * from participant_hackathon_map_table" ;
		PreparedStatement prepareStatement = connection.prepareStatement(query);
		ResultSet rs = prepareStatement.executeQuery();
		while(rs.next())
		{
			ParticipantHackathonMapBean participantHackathonMapBean = new ParticipantHackathonMapBean();
			participantHackathonMapBean.setParticipantId(rs.getInt("ref_participant_Id"));
			participantHackathonMapBean.setHackathonId(rs.getInt("ref_hackathon_Id"));
			ParticipantHackathonMapList.add(participantHackathonMapBean);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}return ParticipantHackathonMapList ;

}
	
	public List<HackathonBean> getHackathonsByParticipantId(int participantId){
		List<HackathonBean> hackathonBeanList = new ArrayList<HackathonBean>();
		try{
			 connection = getConnection();
		
		sqlQuery = "select * from participant_hackathon_map_table" ;
		
		preparedStatement = connection.prepareStatement(sqlQuery);
		
		resultSet = preparedStatement.executeQuery();
		
		HackathonBean hackathonBean = null;
		
		while(resultSet.next())
		{
			/*private String name;
			private String status;
			private Date date;
			private String venue;
			private String participantCount;*/
			hackathonBean = new HackathonBean();
			
			hackathonBean.setName(resultSet.getString("hackathon_name"));
			hackathonBean.setStatus(resultSet.getString("hackathon_status"));
			hackathonBean.setDate(resultSet.getDate("hackathon_date"));
			hackathonBean.setVenue(resultSet.getString("hackathon_venue"));
			hackathonBean.setImage(resultSet.getString("hackathon_image"));
			
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
	}return hackathonBeanList ;
	}

	

	@Override
	public String checkIfAlreadyRegistered(int hackathonId,
			Integer participantId) {
		
		try {
			connection = getConnection();

			String query = "select * from participant_hackathon_map_table where "
					+ "ref_hackathon_id=? and ref_participant_id=?";
			
			PreparedStatement preparedStatement = connection
					.prepareStatement(query);
			
			preparedStatement.setInt(1, hackathonId);
			preparedStatement.setInt(2, participantId);			

			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				return "true";
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

		return "false";
	}

	@Override
	public List<HackathonBean> getMappedHackathons(int participantId) {
		Connection connection=null;
		HackathonBean hackathonBean=new HackathonBean();
		List<HackathonBean> listOfHackathons=new ArrayList<HackathonBean>();
		try {
			connection = getConnection();

			String sqlQuery = "select * from participant_hackathon_map_table where ref_participant_id=?";
			PreparedStatement preparedStatement = connection
					.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, participantId);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				String sqlQuery2="select * from hackathon where hackathon_id=?";
				PreparedStatement preparedStatement2 = connection
						.prepareStatement(sqlQuery2);
				preparedStatement2.setInt(1, resultSet.getInt("ref_hackathon_id"));
				ResultSet resultSet2 = preparedStatement2.executeQuery();
				if(resultSet2.next()){
					hackathonBean.setId(resultSet2.getInt("hackathon_id"));
					hackathonBean.setName(resultSet2.getString("hackathon_name"));
					hackathonBean
							.setStatus(resultSet2.getString("hackathon_status"));
					hackathonBean.setVenue(resultSet2.getString("hackathon_venue"));
					hackathonBean.setDate(resultSet2.getDate("hackathon_date"));
					hackathonBean.setParticipantCount(((Integer) resultSet2
							.getInt("hackathon_count_participants")).toString());
					hackathonBean.setCreatedBy("Aku");
					hackathonBean.setUpdatedBy("Aku");
					hackathonBean.setCreatedDate(new Date());
					hackathonBean.setUpdatedDate(new Date());
					hackathonBean.setImage(resultSet2.getString("hackathon_image"));

					listOfHackathons.add(hackathonBean);


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
		return listOfHackathons;
	}
}
