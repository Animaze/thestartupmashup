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

import com.startupmashup.bean.ParticipantBean;
import com.startupmashup.dao.ParticipantDao;

@Component
public class ParticipantDaoImpl extends BaseDaoImpl implements ParticipantDao {

	private Connection connection;

	@Override
	public List<ParticipantBean> getParticipantList(int hackathonId) {

		List<ParticipantBean> participantBeanList = new ArrayList<ParticipantBean>();

		try {
			connection = getConnection();

			String sqlQuery = "select participant.participant_id, participant.participant_firstname, participant.participant_lastname, participant.participant_username, "
					+ "participant.participant_password, participant.participant_email_id, participant.participant_phone_number, participant.participant_company_name, "
					+ "participant.participant_designation, participant.participant_college_name, participant.participant_ctc, participant.participant_resume, "
					+ "participant.participant_flag, participant.created_by, participant.created_date, participant.updated_by, participant.updated_date "
					+ "from participant, participant_hackathon_map_table "
					+ "where participant_hackathon_map_table.ref_hackathon_id = ? and "
					+ "participant.participant_id = participant_hackathon_map_table.ref_participant_id;";

			PreparedStatement preparedStatement = connection
					.prepareStatement(sqlQuery);

			preparedStatement.setInt(1, hackathonId);

			ResultSet resultSet = preparedStatement.executeQuery();

			ParticipantBean participantBean;
			while (resultSet.next()) {
				participantBean = new ParticipantBean();

				participantBean.setId(resultSet.getInt("participant_id"));
				participantBean.setFirstName(resultSet
						.getString("participant_firstname"));
				participantBean.setLastName(resultSet
						.getString("participant_lastname"));
				participantBean.setUserName(resultSet
						.getString("participant_username"));
				participantBean.setPassword(resultSet
						.getString("participant_password"));
				participantBean.setEmailId(resultSet
						.getString("participant_email_id"));
				participantBean.setPhoneNumber(resultSet
						.getString("participant_phone_number"));
				participantBean.setCompanyName(resultSet
						.getString("participant_company_name"));
				participantBean.setDesignation(resultSet
						.getString("participant_designation"));
				participantBean.setCollegeName(resultSet
						.getString("participant_college_name"));
				participantBean.setCtc(resultSet.getInt("participant_ctc"));
				participantBean.setResume(resultSet
						.getString("participant_resume"));
				participantBean.setFlag(resultSet
						.getBoolean("participant_firstname"));
				participantBean.setCreatedBy("Anupam");
				participantBean.setCreatedDate(new Date());
				participantBean.setUpdatedBy("Anupam");
				participantBean.setUpdatedDate(new Date());

				participantBeanList.add(participantBean);
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			try {
				connection.close();
			} catch (Exception ex) {
				System.out.println(ex.toString());
			}
		}

		return participantBeanList;
	}

	@Override
	public void saveParticipantInfo(ParticipantBean participantBean) {

		try {
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
					.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession();

			String createdBy = (String) session.getAttribute("adminUsername");
			Date createdDate = new Date();

			connection = getConnection();

			String query = "insert into participant values(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,null,null)";

			PreparedStatement preparedStatement = connection
					.prepareStatement(query);

			preparedStatement.setString(1, participantBean.getFirstName());
			preparedStatement.setString(2, participantBean.getLastName());
			preparedStatement.setString(3, participantBean.getUserName());
			preparedStatement.setString(4, participantBean.getPassword());
			preparedStatement.setString(5, participantBean.getEmailId());
			preparedStatement.setString(6, participantBean.getPhoneNumber());
			preparedStatement.setString(7, participantBean.getCompanyName());
			preparedStatement.setString(8, participantBean.getDesignation());
			preparedStatement.setString(9, participantBean.getCollegeName());
			preparedStatement.setInt(10, (participantBean.getCtc()));
			preparedStatement.setString(11, participantBean.getResume());
			if (participantBean.isFlag()) {
				preparedStatement.setInt(12, 1);
			} else {
				preparedStatement.setInt(12, 0);
			}
			
			preparedStatement.setString(13, createdBy);
			preparedStatement.setDate(14,
					new java.sql.Date(createdDate.getTime()));
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
	public ParticipantBean authoriseUser(String username, String password) {
		connection = getConnection();

		ParticipantBean participantBean = new ParticipantBean();
		String query = "select * from participant where participant_username=? and "
				+ "participant_password=?";
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement(query);

			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				participantBean.setId(resultSet.getInt("participant_id"));
				participantBean.setFirstName(resultSet
						.getString("participant_firstname"));
				participantBean.setLastName(resultSet
						.getString("participant_lastname"));
				participantBean.setUserName(resultSet
						.getString("participant_username"));
				participantBean.setPassword(resultSet
						.getString("participant_password"));
				participantBean.setEmailId(resultSet
						.getString("participant_email_id"));
				participantBean.setPhoneNumber((resultSet
						.getString("participant_phone_number")));
				participantBean.setCompanyName(resultSet
						.getString("participant_company_name"));
				participantBean.setDesignation((resultSet
						.getString("participant_designation")).toString());
				participantBean.setCollegeName(resultSet
						.getString("participant_college_name"));
				participantBean.setCtc((resultSet.getInt("participant_ctc")));
				participantBean.setResume(resultSet
						.getString("participant_resume"));
				participantBean.setFlag(resultSet
						.getBoolean("participant_flag"));

				participantBean.setCreatedBy(resultSet.getString("created_by"));
				participantBean.setUpdatedBy(resultSet.getString("updated_by"));
				participantBean.setCreatedDate(resultSet
						.getDate("created_date"));
				participantBean.setUpdatedDate(resultSet
						.getDate("updated_date"));

			} else {
				participantBean.setId(0);
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

		return participantBean;
	}

	public List<String> getUserNames() {

		List<String> userNamesList = null;

		try {

			connection = getConnection();

			String sqlQuery = "select participant_username from participant;";

			PreparedStatement preparedStatement = connection
					.prepareStatement(sqlQuery);

			ResultSet result = preparedStatement.executeQuery();

			userNamesList = new ArrayList<String>();
			while (result.next()) {
				userNamesList.add(result.getString("participant_username"));
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return userNamesList;
	}

	@Override
	public ArrayList<ParticipantBean> getParticipantList() {
		ParticipantBean participantBean = null;
		ArrayList<ParticipantBean> participantBeanList = new ArrayList<ParticipantBean>();
		try {
			connection = getConnection();

			String query = "select * from participant";
			PreparedStatement preparedStatement = connection
					.prepareStatement(query);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				participantBean = new ParticipantBean();

				participantBean.setId(resultSet.getInt("participant_id"));
				participantBean.setFirstName(resultSet
						.getString("participant_firstname"));
				participantBean.setLastName(resultSet
						.getString("participant_lastname"));
				participantBean.setUserName(resultSet
						.getString("participant_username"));
				participantBean.setPassword(resultSet
						.getString("participant_password"));
				participantBean.setEmailId(resultSet
						.getString("participant_email_id"));
				participantBean.setPhoneNumber((resultSet
						.getString("participant_phone_number")));
				participantBean.setCompanyName(resultSet
						.getString("participant_company_name"));
				participantBean.setDesignation((resultSet
						.getString("participant_designation")).toString());
				participantBean.setCollegeName(resultSet
						.getString("participant_college_name"));
				participantBean.setCtc((resultSet.getInt("participant_ctc")));
				participantBean.setResume(resultSet
						.getString("participant_resume"));
				participantBean.setFlag(resultSet
						.getBoolean("participant_flag"));

				participantBean.setCreatedBy(resultSet.getString("created_by"));
				participantBean.setUpdatedBy(resultSet.getString("updated_by"));
				participantBean.setCreatedDate(resultSet
						.getDate("created_date"));
				participantBean.setUpdatedDate(resultSet
						.getDate("updated_date"));

				participantBeanList.add(participantBean);

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
		return participantBeanList;
	}

	@Override
	public ParticipantBean getDetailsById(int userId) {

		ParticipantBean participantBean = new ParticipantBean();

		try {

			connection = getConnection();

			String sqlQuery = "select * from participant where participant_id=?;";

			PreparedStatement preparedStatement = connection
					.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, userId);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				participantBean.setId(resultSet.getInt("participant_id"));
				participantBean.setFirstName(resultSet
						.getString("participant_firstname"));
				participantBean.setLastName(resultSet
						.getString("participant_lastname"));
				participantBean.setUserName(resultSet
						.getString("participant_username"));
				participantBean.setPassword(resultSet
						.getString("participant_password"));
				participantBean.setEmailId(resultSet
						.getString("participant_email_id"));
				participantBean.setPhoneNumber((resultSet
						.getString("participant_phone_number")));
				participantBean.setCompanyName(resultSet
						.getString("participant_company_name"));
				participantBean.setDesignation((resultSet
						.getString("participant_designation")).toString());
				participantBean.setCollegeName(resultSet
						.getString("participant_college_name"));
				participantBean.setCtc((resultSet.getInt("participant_ctc")));
				participantBean.setResume(resultSet
						.getString("participant_resume"));
				participantBean.setFlag(resultSet
						.getBoolean("participant_flag"));

				participantBean.setChallengeName("");

				participantBean.setCreatedBy(resultSet.getString("created_by"));
				participantBean.setUpdatedBy(resultSet.getString("updated_by"));
				participantBean.setCreatedDate(resultSet
						.getDate("created_date"));
				participantBean.setUpdatedDate(resultSet
						.getDate("updated_date"));

			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return participantBean;
	}

	@Override
	public void saveEditedProfile(ParticipantBean participantBean, int userId) {

		try {

			connection = getConnection();

			String sqlQuery = "update participant " + "set "
					+ "participant_firstname=? , "
					+ "participant_lastname=? , " + "participant_password=? , "
					+ "participant_email_id=? , "
					+ "participant_phone_number=? , "
					+ "participant_company_name=? , "
					+ "participant_designation=? , "
					+ "participant_college_name=? , " + "participant_ctc=? , "
					+ "participant_resume=?, " + "updated_by = ?, "
					+ "updated_date = ?" + "where " + "participant_id=?";

			String updatedBy = participantBean.getFirstName() + " "
					+ participantBean.getLastName();

			PreparedStatement preparedStatement = connection
					.prepareStatement(sqlQuery);

			preparedStatement = connection.prepareStatement(sqlQuery);

			preparedStatement.setString(1, participantBean.getFirstName());
			preparedStatement.setString(2, participantBean.getLastName());
			preparedStatement.setString(3, participantBean.getPassword());
			preparedStatement.setString(4, participantBean.getEmailId());
			preparedStatement.setString(5, participantBean.getPhoneNumber());
			preparedStatement.setString(6, participantBean.getCompanyName());
			preparedStatement.setString(7, participantBean.getDesignation());
			preparedStatement.setString(8, participantBean.getCollegeName());
			preparedStatement.setInt(9, (participantBean.getCtc()));
			preparedStatement.setString(10, participantBean.getResume());
			preparedStatement.setString(11, updatedBy);
			preparedStatement.setDate(12,
					new java.sql.Date(new Date().getTime()));
			preparedStatement.setInt(13, userId);

			preparedStatement.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	@Override
	public List<ParticipantBean> getParticipantList(int hackathonId,
			int challengeId) {

		List<ParticipantBean> participantBeanList = new ArrayList<ParticipantBean>();

		try {
			connection = getConnection();

			String sqlQuery = "select participant.participant_id, participant.participant_firstname, participant.participant_lastname, participant.participant_username, "
					+ "participant.participant_password, participant.participant_email_id, participant.participant_phone_number, participant.participant_company_name, "
					+ "participant.participant_designation, participant.participant_college_name, participant.participant_ctc, participant.participant_resume, "
					+ "participant.participant_flag, participant.created_by, participant.created_date, participant.updated_by, participant.updated_date "
					+ "from participant, participant_challenge_map_table, hackathon_challenge_map_table "
					+ "where hackathon_challenge_map_table.ref_hackathon_id = ? and "
					+ "hackathon_challenge_map_table.ref_challenge_id = ? and "
					+ "participant_challenge_map_table.ref_challenge_id = ? and "
					+ "participant_challenge_map_table.ref_participant_id = participant.participant_id;";

			PreparedStatement preparedStatement = connection
					.prepareStatement(sqlQuery);

			preparedStatement.setInt(1, hackathonId);
			preparedStatement.setInt(2, challengeId);
			preparedStatement.setInt(3, challengeId);

			ResultSet resultSet = preparedStatement.executeQuery();

			ParticipantBean participantBean;
			while (resultSet.next()) {
				participantBean = new ParticipantBean();

				participantBean.setId(resultSet.getInt("participant_id"));
				participantBean.setFirstName(resultSet
						.getString("participant_firstname"));
				participantBean.setLastName(resultSet
						.getString("participant_lastname"));
				participantBean.setUserName(resultSet
						.getString("participant_username"));
				participantBean.setPassword(resultSet
						.getString("participant_password"));
				participantBean.setEmailId(resultSet
						.getString("participant_email_id"));
				participantBean.setPhoneNumber(resultSet
						.getString("participant_phone_number"));
				participantBean.setCompanyName(resultSet
						.getString("participant_company_name"));
				participantBean.setDesignation(resultSet
						.getString("participant_designation"));
				participantBean.setCollegeName(resultSet
						.getString("participant_college_name"));
				participantBean.setCtc(resultSet.getInt("participant_ctc"));
				participantBean.setResume(resultSet
						.getString("participant_resume"));
				participantBean.setFlag(resultSet
						.getBoolean("participant_firstname"));
				participantBean.setCreatedBy(resultSet.getString("created_by"));
				participantBean.setUpdatedBy(resultSet.getString("updated_by"));
				participantBean.setCreatedDate(resultSet
						.getDate("created_date"));
				participantBean.setUpdatedDate(resultSet
						.getDate("updated_date"));

				participantBeanList.add(participantBean);
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			try {
				connection.close();
			} catch (Exception ex) {
				System.out.println(ex.toString());
			}
		}

		return participantBeanList;
	}

	@Override
	public void updateParticipant(int participantId,
			ParticipantBean participantBean) {
		try {

			connection = getConnection();
			String updatedBy = participantBean.getFirstName() + " "
					+ participantBean.getLastName();

			String sqlQuery = "update participant " + "set "
					+ "participant_firstname=? , "
					+ "participant_lastname=? , " + "participant_email_id=? , "
					+ "participant_phone_number=? , " + "updated_by=? , "
					+ "updated_date=?  " + "where " + "participant_id=?";

			PreparedStatement preparedStatement = connection
					.prepareStatement(sqlQuery);
			preparedStatement = connection.prepareStatement(sqlQuery);

			preparedStatement.setString(1, participantBean.getFirstName());
			preparedStatement.setString(2, participantBean.getLastName());
			preparedStatement.setString(3, participantBean.getEmailId());
			preparedStatement.setString(4, participantBean.getPhoneNumber());
			preparedStatement.setString(5, updatedBy);
			preparedStatement.setDate(6,
					new java.sql.Date(new Date().getTime()));
			preparedStatement.setInt(7, participantId);
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	@Override
	public ParticipantBean getDetailsByUsername(String username) {
		ParticipantBean participantBean = new ParticipantBean();

		try {

			connection = getConnection();

			String sqlQuery = "select * from participant where participant_username=?;";

			PreparedStatement preparedStatement = connection
					.prepareStatement(sqlQuery);
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				participantBean.setId(resultSet.getInt("participant_id"));
				participantBean.setFirstName(resultSet
						.getString("participant_firstname"));
				participantBean.setLastName(resultSet
						.getString("participant_lastname"));
				participantBean.setUserName(resultSet
						.getString("participant_username"));
				participantBean.setPassword(resultSet
						.getString("participant_password"));
				participantBean.setEmailId(resultSet
						.getString("participant_email_id"));
				participantBean.setPhoneNumber((resultSet
						.getString("participant_phone_number")));
				participantBean.setCompanyName(resultSet
						.getString("participant_company_name"));
				participantBean.setDesignation((resultSet
						.getString("participant_designation")).toString());
				participantBean.setCollegeName(resultSet
						.getString("participant_college_name"));
				participantBean.setCtc((resultSet.getInt("participant_ctc")));
				participantBean.setResume(resultSet
						.getString("participant_resume"));
				participantBean.setFlag(resultSet
						.getBoolean("participant_flag"));

				participantBean.setCreatedBy(resultSet.getString("created_by"));
				participantBean.setUpdatedBy(resultSet.getString("updated_by"));
				participantBean.setCreatedDate(resultSet
						.getDate("created_date"));
				participantBean.setUpdatedDate(resultSet
						.getDate("updated_date"));

			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return participantBean;

	}

	@Override
	public ParticipantBean getParticipantBeanByToken(String token) {
		
		ParticipantBean participantBean = new ParticipantBean();

		try {

			connection = getConnection();

			String sqlQuery = "select * from participant where md5(participant_username) = ?  ";

			PreparedStatement preparedStatement = connection
					.prepareStatement(sqlQuery);
			preparedStatement.setString(1, token);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				participantBean.setId(resultSet.getInt("participant_id"));
				participantBean.setFirstName(resultSet
						.getString("participant_firstname"));
				participantBean.setLastName(resultSet
						.getString("participant_lastname"));
				participantBean.setUserName(resultSet
						.getString("participant_username"));
				participantBean.setPassword(resultSet
						.getString("participant_password"));
				participantBean.setEmailId(resultSet
						.getString("participant_email_id"));
				participantBean.setPhoneNumber((resultSet
						.getString("participant_phone_number")));
				participantBean.setCompanyName(resultSet
						.getString("participant_company_name"));
				participantBean.setDesignation((resultSet
						.getString("participant_designation")).toString());
				participantBean.setCollegeName(resultSet
						.getString("participant_college_name"));
				participantBean.setCtc((resultSet.getInt("participant_ctc")));
				participantBean.setResume(resultSet
						.getString("participant_resume"));
				participantBean.setFlag(resultSet
						.getBoolean("participant_flag"));

				participantBean.setCreatedBy(resultSet.getString("created_by"));
				participantBean.setUpdatedBy(resultSet.getString("updated_by"));
				participantBean.setCreatedDate(resultSet
						.getDate("created_date"));
				participantBean.setUpdatedDate(resultSet
						.getDate("updated_date"));

			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return participantBean;
	}

	@Override
	public void updatePassword(String participantId, String newPassword) {

		try {

			connection = getConnection();

			String sqlQuery = "update participant set participant_password=? where participant_id=?";

		
			PreparedStatement preparedStatement = connection
					.prepareStatement(sqlQuery);

			
			preparedStatement.setString(1, newPassword);
			preparedStatement.setInt(2, Integer.parseInt(participantId));
		
			preparedStatement.executeUpdate();

		} catch (Exception e) {
		e.printStackTrace();
		}

	
	}

}
