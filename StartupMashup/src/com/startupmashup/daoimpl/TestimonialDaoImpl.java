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

import com.startupmashup.bean.TestimonialBean;
import com.startupmashup.dao.TestimonialDao;

@Component
public class TestimonialDaoImpl extends BaseDaoImpl implements TestimonialDao {

	private String sqlQuery;
	Connection connection;
	PreparedStatement preparedStatement;

	@Override
	public void saveTestimonialInfo(TestimonialBean testimonialBean) {

		try {
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
					.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession();

			String createdBy = (String) session.getAttribute("adminUsername");
			Date createdDate = new Date();

			connection = getConnection();

			sqlQuery = "insert into testimonial values (null,?,?,?,?,?,?,?,?, null, null)";

			preparedStatement = connection.prepareStatement(sqlQuery);

			preparedStatement
					.setString(1, testimonialBean.getParticipantName());
			preparedStatement.setString(2, testimonialBean.getChallengeName());
			preparedStatement.setString(3, testimonialBean.getCompanyName());
			preparedStatement.setString(4, testimonialBean.getWords());
			preparedStatement.setObject(5, testimonialBean.getDate());
			preparedStatement.setString(6, testimonialBean.getImage());
			preparedStatement.setString(7, createdBy);
			preparedStatement.setDate(8,
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
	public ArrayList<TestimonialBean> getTestimonialList() {

		ArrayList<TestimonialBean> testimonialBeanList = new ArrayList<TestimonialBean>();

		connection = getConnection();
		sqlQuery = "select * from testimonial order by testimonial_date";
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement(sqlQuery);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				TestimonialBean testimonialBean = new TestimonialBean();
				testimonialBean.setId(rs.getInt("testimonial_id"));
				testimonialBean.setParticipantName(rs
						.getString("testimonial_participant_name"));
				testimonialBean.setChallengeName(rs
						.getString("testimonial_challenge_name"));
				testimonialBean.setCompanyName(rs
						.getString("testimonial_company_name"));
				testimonialBean.setWords(rs.getString("testimonial_words"));
				testimonialBean.setDate(rs.getDate("testimonial_date"));
				testimonialBean.setImage(rs.getString("testimonial_image"));
				testimonialBean.setCreatedBy(rs.getString("created_by"));
				testimonialBean.setCreatedDate(rs.getDate("created_date"));
				testimonialBean.setUpdatedBy(rs.getString("updated_by"));
				testimonialBean.setUpdatedDate(rs.getDate("updated_date"));

				testimonialBeanList.add(testimonialBean);
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

		return testimonialBeanList;
	}

	@Override
	public void updateTestimonials(TestimonialBean testimonialBean) {
		try {
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession();
			
			String updatedBy = (String)session.getAttribute("adminUsername");
			Date updatedDate = new Date();

			connection = getConnection();

			sqlQuery = "update testimonial "
					+ "set "
					+ "testimonial_participant_name=? ,"
					+ "testimonial_challenge_name=? , "
					+ "testimonial_company_name=? , "
					+ "testimonial_words=? , "
					+ "testimonial_date=? , "
					+ "testimonial_image = ? , "
					+ "updated_by = ? , "
					+ "updated_date = ? "
					+ "where "
					+ "testimonial_id=?";

			preparedStatement = connection.prepareStatement(sqlQuery);

			preparedStatement.setString(1, testimonialBean.getParticipantName());
			preparedStatement.setString(2, testimonialBean.getChallengeName());
			preparedStatement.setString(3, testimonialBean.getCompanyName());
			preparedStatement.setString(4, testimonialBean.getWords());
			preparedStatement.setObject(5,testimonialBean.getDate());
			preparedStatement.setString(6, testimonialBean.getImage());
			preparedStatement.setString(7, updatedBy);
			preparedStatement.setDate(8, new java.sql.Date(updatedDate.getTime()));
			preparedStatement.setInt(9, testimonialBean.getId());

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
	public TestimonialBean getTestimonialById(int testimonialId) {
		TestimonialBean testimonialBean = null;

		try {

			connection = getConnection();
			sqlQuery = "select * from testimonial where testimonial_id = ?";

			preparedStatement = connection.prepareStatement(sqlQuery);

			preparedStatement.setInt(1, testimonialId);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				testimonialBean = new TestimonialBean();

				testimonialBean.setId(resultSet.getInt("testimonial_id"));
				testimonialBean.setParticipantName(resultSet
						.getString("testimonial_participant_name"));
				testimonialBean.setChallengeName(resultSet
						.getString("testimonial_challenge_name"));
				testimonialBean.setCompanyName(resultSet
						.getString("testimonial_company_name"));
				testimonialBean.setWords(resultSet
						.getString("testimonial_words"));
				testimonialBean.setDate(resultSet.getDate("testimonial_date"));
				testimonialBean.setImage(resultSet
						.getString("testimonial_image"));
				testimonialBean.setCreatedBy(resultSet.getString("created_by"));
				testimonialBean.setCreatedDate(resultSet
						.getDate("created_date"));
				testimonialBean.setUpdatedBy(resultSet.getString("updated_by"));
				testimonialBean.setUpdatedDate(resultSet
						.getDate("updated_date"));

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

		return testimonialBean;

	}

}
