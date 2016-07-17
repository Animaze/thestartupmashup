package com.startupmashup.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.startupmashup.bean.ChallengeSkillMapBean;
import com.startupmashup.bean.CompanyBean;
import com.startupmashup.bean.CompanyChallengeMapBean;
import com.startupmashup.bean.HackathonBean;
import com.startupmashup.bean.HackathonChallengeMapBean;
import com.startupmashup.dao.MapDao;

@Component
public class MapDaoImpl extends BaseDaoImpl implements MapDao {

	private Connection connection;

	@Override
	public void mapParticpantAndHackathon(int hackathonId, int participantId) {
		try {
			connection = getConnection();

			String query = "insert into participant_hackathon_map_table values(null,?,?,null,null,null,null)";

			PreparedStatement preparedStatement = connection
					.prepareStatement(query);
			preparedStatement.setInt(1, hackathonId);

			preparedStatement.setInt(2, participantId);

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
	public void mapParticipantChallenge(int challengeId, int participantId) {

		try {
			connection = getConnection();

			String query = "insert into participant_challenge_map_table values(null,?,?,null,null,null,null)";

			PreparedStatement preparedStatement = connection
					.prepareStatement(query);
			preparedStatement.setInt(1, challengeId);

			preparedStatement.setInt(2, participantId);

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
	public void mapChallengeSkill(int challengeId,
			List<String> selectedSkillList) {

		try {
			connection = getConnection();
			String query = "insert into challenge_skill_map_table values(null,?,?,null,null,null,null)";
			PreparedStatement preparedStatement = connection
					.prepareStatement(query);

			for (String skillId : selectedSkillList) {

				preparedStatement.setInt(1, challengeId);

				preparedStatement.setInt(2, Integer.parseInt(skillId));

				preparedStatement.executeUpdate();

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

	}

	@Override
	public List<Integer> getMappedSkillIds(int challengeId) {

		List<Integer> mappedSkillIds = new ArrayList<Integer>();

		try {
			connection = getConnection();
			String query = "select ref_skill_id from challenge_skill_map_table "
					+ "where " + "ref_challenge_id=?";
			PreparedStatement preparedStatement = connection
					.prepareStatement(query);
			preparedStatement.setInt(1, challengeId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				mappedSkillIds.add(resultSet.getInt(1));
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

		return mappedSkillIds;
	}

	@Override
	public void deleteMappingOfChallengeSkill(int challengeId) {

		try {
			connection = getConnection();
			String query = "delete from challenge_skill_map_table " + "where "
					+ "ref_challenge_id=?";
			PreparedStatement preparedStatement = connection
					.prepareStatement(query);
			preparedStatement.setInt(1, challengeId);
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
	public List<HackathonChallengeMapBean> getHackathonChallengeMapDetails() {

		List<HackathonChallengeMapBean> listOfMapping = new ArrayList<HackathonChallengeMapBean>();
		HackathonChallengeMapBean mapBean = null;

		try {
			connection = getConnection();
			String query = "select * from hackathon_challenge_map_table;";
			PreparedStatement preparedStatement = connection
					.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				mapBean = new HackathonChallengeMapBean();
				mapBean.setId(resultSet.getInt("hackathon_challenge_map_id"));
				mapBean.setHackathonId(resultSet.getInt("ref_hackathon_id"));
				mapBean.setChallengeId(resultSet.getInt("ref_challenge_id"));

				listOfMapping.add(mapBean);
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
		return listOfMapping;
	}

	@Override
	public List<Integer> getChallengeIds(String hackathonId) {

		List<Integer> challengeIdList = new ArrayList<Integer>();

		try {
			connection = getConnection();

			String sqlQuery = "select ref_challenge_id from hackathon_challenge_map_table where ref_hackathon_id = ?;";

			PreparedStatement preparedStatement = connection
					.prepareStatement(sqlQuery);

			preparedStatement.setInt(1, Integer.parseInt(hackathonId));

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				challengeIdList.add(resultSet.getInt(1));

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
		return challengeIdList;
	}

	@Override
	public void editChallengeHackathonMappingDetails(int hackId,
			List<String> challengeIdList) {

		try {
			connection = getConnection();

			String sqlQuery = "delete from hackathon_challenge_map_table "
					+ "where ref_hackathon_id = ?";

			PreparedStatement preparedStatement = connection
					.prepareStatement(sqlQuery);

			preparedStatement.setInt(1, hackId);

			preparedStatement.executeUpdate();

			saveChallengeHackathonMappingDetails(hackId, challengeIdList);

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
	public void saveChallengeHackathonMappingDetails(int hackId,
			List<String> challengeIdList) {
		try {
			connection = getConnection();

			String sqlQuery = "insert into hackathon_challenge_map_table values(null,?,?,null,null,null,null);";

			PreparedStatement preparedStatement = connection
					.prepareStatement(sqlQuery);

			for (String str : challengeIdList) {
				preparedStatement.setInt(1, hackId);
				preparedStatement.setInt(2, Integer.parseInt(str));
				preparedStatement.executeUpdate();
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

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
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
	public ArrayList<CompanyChallengeMapBean> findCompanyChallengeMapBeanByCompanyId(
			int id) {
		CompanyChallengeMapBean companyChallengeMapBean = null;
		ArrayList<CompanyChallengeMapBean> companyChallengeMapList = new ArrayList<CompanyChallengeMapBean>();
		try {
			connection = getConnection();
			String sqlQuery = "select * from company_challenge_map_table where ref_company_id="
					+ id;
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlQuery);

			if (resultSet.first()) {
				resultSet.previous();
				while (resultSet.next()) {
					companyChallengeMapBean = new CompanyChallengeMapBean();

					companyChallengeMapBean.setId(resultSet
							.getInt("company_challenge_map_id"));
					companyChallengeMapBean.setChallengeId(resultSet
							.getInt("ref_challenge_id"));
					companyChallengeMapBean.setCompanyId(resultSet
							.getInt("ref_company_id"));
					companyChallengeMapList.add(companyChallengeMapBean);
				}
			} else {
				companyChallengeMapList = null;
			}

		} catch (Exception e) {
			e.toString();
			try {
				connection.close();
			} catch (Exception ex) {
				ex.toString();
			}
		}
		return companyChallengeMapList;

	}

	@Override
	public ArrayList<HackathonChallengeMapBean> getHackathonChallengeMapList() {
		ArrayList<HackathonChallengeMapBean> hackathonChallengeMapList = new ArrayList<HackathonChallengeMapBean>();
		try {
			connection = getConnection();

			String query = "select * from hackathon_challenge_map_table";
			PreparedStatement prepareStatement = connection
					.prepareStatement(query);
			ResultSet rs = prepareStatement.executeQuery();
			while (rs.next()) {
				HackathonChallengeMapBean hackathonChallengeMapBean = new HackathonChallengeMapBean();
				hackathonChallengeMapBean.setChallengeId(rs
						.getInt("ref_challenge_Id"));
				hackathonChallengeMapBean.setHackathonId(rs
						.getInt("ref_hackathon_Id"));
				hackathonChallengeMapList.add(hackathonChallengeMapBean);
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
		return hackathonChallengeMapList;

	}

	@Override
	public void saveCompanyChallengeMappingDetails(int companyId,
			ArrayList<Integer> challengeIds) {
		try {
			connection = getConnection();

			String sqlQuery = "insert into company_challenge_map_table values (null,?,?,null, null, null, null)";

			PreparedStatement preparedStatement = connection
					.prepareStatement(sqlQuery);

			for (int chId : challengeIds) {

				preparedStatement.setInt(1, chId);

				preparedStatement.setInt(2, companyId);

				preparedStatement.executeUpdate();

			}

		} catch (Exception e) {
			e.toString();
			try {
				connection.close();
			} catch (Exception ex) {
				ex.toString();
			}
		}

	}

	@Override
	public void saveHackathonCompanyMappingDetails(int companyId,
			int hackathonId) {
		try {

			connection = getConnection();

			String sqlQuery = "insert into hackathon_company_map_table values (null,?,?,null,null,null,null)";
			PreparedStatement preparedStatement = connection
					.prepareStatement(sqlQuery);

			preparedStatement.setInt(1, hackathonId);
			preparedStatement.setInt(2, companyId);
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.toString();
			try {
				connection.close();
			} catch (Exception ex) {
				ex.toString();
			}
		}

	}

	@Override
	public void updateCompanyChallengeMappingDetails(int companyId,
			ArrayList<Integer> challengeIds) {
		try {
			connection = getConnection();
			String sqlQuery = "delete from company_challenge_map_table where ref_company_id="
					+ companyId;
			Statement deleteStatement = connection.createStatement();
			deleteStatement.execute(sqlQuery);

			sqlQuery = "insert into company_challenge_map_table values (null,?,?,null, null, null, null)";

			PreparedStatement preparedStatement = connection
					.prepareStatement(sqlQuery);

			for (int chId : challengeIds) {

				preparedStatement.setInt(1, chId);

				preparedStatement.setInt(2, companyId);

				preparedStatement.executeUpdate();
			}
		} catch (Exception e) {
			e.toString();
			try {
				connection.close();
			} catch (Exception ex) {
				ex.toString();
			}
		}

	}

	@Override
	public void updateHackathonCompanyMappingDetails(int companyId,
			ArrayList<Integer> hackathonIds) {
		try {
			connection = getConnection();
			String sqlQuery = "delete from hackathon_company_map_table where ref_company_id="
					+ companyId;
			Statement deleteStatement = connection.createStatement();
			deleteStatement.execute(sqlQuery);
			sqlQuery = "insert into hackathon_company_map_table values (null,?,?,null,null,null,null)";
			PreparedStatement preparedStatement = connection
					.prepareStatement(sqlQuery);

			for (int hId : hackathonIds) {
				preparedStatement.setInt(1, hId);
				preparedStatement.setInt(2, companyId);
				preparedStatement.executeUpdate();
			}
		} catch (Exception e) {
			e.toString();
			try {
				connection.close();
			} catch (Exception ex) {
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
	public List<ChallengeSkillMapBean> getChallengeSkillMapList() {
		List<ChallengeSkillMapBean> listOfChallengeSkillMapBeans = new ArrayList<ChallengeSkillMapBean>();
		ChallengeSkillMapBean challengeSkillMapBean = null;
		Connection connection=null;
		try {
			connection = getConnection();

			String sqlQuery = "select * from challenge_skill_map_table";
			PreparedStatement preparedStatement = connection
					.prepareStatement(sqlQuery);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				challengeSkillMapBean = new ChallengeSkillMapBean();
				challengeSkillMapBean.setChallengeId(resultSet.getInt("ref_challenge_id"));
				challengeSkillMapBean.setSkillId(resultSet.getInt("ref_skill_id"));
				listOfChallengeSkillMapBeans.add(challengeSkillMapBean);
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

		return listOfChallengeSkillMapBeans;
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
	
	@Override
	public void saveMappingDetails(int hackathonId, ArrayList<String> challengeId) {
		try {
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession();
			
			String createdBy = (String)session.getAttribute("adminUsername");
			Date createdDate = new Date();
			
			connection = getConnection();

			String sqlQuery = "insert into hackathon_challenge_map_table values(null,?,?,null,null,null,null);";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			
			for(String str : challengeId){
				preparedStatement.setInt(1, hackathonId);
				preparedStatement.setInt(2, Integer.parseInt(str));
				preparedStatement.setString(3, createdBy);
				preparedStatement.setDate(4, new java.sql.Date(createdDate.getTime()));
				preparedStatement.executeUpdate();
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
		
	}

	@Override
	public void updateHackathonChallengeMappingDetails(int hackathonId, ArrayList<String> challengeIdList) {
		try {
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession();
			
			String updatedBy = (String)session.getAttribute("adminUsername");
			Date updatedDate = new Date();
			
			String createdBy="";
			Date createdDate=null;
			
			connection = getConnection();

			String sqlQuery = "select created_by, created_date from hackathon_challenge_map_table "
					+ "where ref_hackathon_id = ? ";

			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			
			preparedStatement.setInt(1, hackathonId);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()){
				createdBy = resultSet.getString(1);
				createdDate = (resultSet.getDate(2));
			}
			
			sqlQuery = "delete from hackathon_challenge_map_table "
					+ "where ref_hackathon_id = ?";
			
			preparedStatement = connection.prepareStatement(sqlQuery);
			
			preparedStatement.setInt(1, hackathonId);
			
			preparedStatement.executeUpdate();
			
			sqlQuery = "insert into hackathon_challenge_map_table values (null, ?, ?, ?, ?, ?, ?)";
			
			preparedStatement = connection.prepareStatement(sqlQuery);
			
			for(String str : challengeIdList){
				preparedStatement.setInt(1, hackathonId);
				preparedStatement.setInt(2, Integer.parseInt(str));
				preparedStatement.setString(3, 	createdBy);
				preparedStatement.setDate(4, new java.sql.Date(createdDate.getTime()));
				preparedStatement.setString(5, updatedBy);
				preparedStatement.setDate(6, new java.sql.Date(updatedDate.getTime()));
				
				preparedStatement.executeUpdate();
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
		
	

	

}



}
