package com.startupmashup.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.startupmashup.dao.ParticipantChallengeMapDao;

@Component
public class ParticipantChallengeMapDaoImpl extends BaseDaoImpl implements ParticipantChallengeMapDao{
	
	Connection connection;

	@Override
	public void saveMappingDetails(int challengeId, int participantId) {
		try {
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession();
			
			String createdBy = (String)session.getAttribute("adminUsername");
			Date createdDate = new Date();
			
			connection = getConnection();

			String query = "insert into participant_challenge_map_table values(null, ?, ?, ?, ?, null, null)";
			
			PreparedStatement preparedStatement = connection
					.prepareStatement(query);
			
			preparedStatement.setInt(1, challengeId);
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

}
