package com.startupmashup.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.startupmashup.bean.ReferDetailsBean;
import com.startupmashup.dao.ReferDetailsDao;

@Component
public class ReferDetailsDaoImpl extends BaseDaoImpl implements ReferDetailsDao {

	private String sqlQuery;
	Connection connection;
	PreparedStatement preparedStatement;

	@Override
	public void saveReferDetailsInfo(ReferDetailsBean referDetailsBean) {

		try {
			/*ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
					.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession();

			String createdBy = (String) session.getAttribute("adminUsername");
			
*/
			connection = getConnection();
			Date createdDate = new Date();

			sqlQuery = "insert into refer_details values (null, ?, ?, ?, ?, ?, ?, ?,?,?,?,null, null)";

			preparedStatement = connection.prepareStatement(sqlQuery);

			preparedStatement.setString(1, referDetailsBean.getName());
			preparedStatement.setString(2, referDetailsBean.getPhoneNumber());
			preparedStatement.setString(3, referDetailsBean.getEmailId());
			preparedStatement.setString(4, referDetailsBean.getCollegeName());
			preparedStatement.setInt(5, referDetailsBean.getHackathonId());
			preparedStatement.setString(6,referDetailsBean.getRefName());
			
			preparedStatement.setString(7, referDetailsBean.getRefEmailId());
			preparedStatement
					.setString(8, referDetailsBean.getRefPhoneNumber());
		
			preparedStatement.setString(9, referDetailsBean.getRefName());
			preparedStatement.setDate(10,
					new java.sql.Date(createdDate.getTime()));
			
			
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			try {
				connection.close();
			} catch (Exception ex) {
				System.out.println(ex.toString());
			}
		}
	}

	@Override
	public ArrayList<ReferDetailsBean> getReferDetailsList() {

		ArrayList<ReferDetailsBean> referDetailsBeanList = null;

		try {

			connection = getConnection();

			sqlQuery = "select * from refer_details";

			preparedStatement = connection.prepareStatement(sqlQuery);

			ResultSet resultSet = preparedStatement.executeQuery();

			referDetailsBeanList = new ArrayList<ReferDetailsBean>();
			ReferDetailsBean referDetailsBean;

			while (resultSet.next()) {
				referDetailsBean = new ReferDetailsBean();

				referDetailsBean.setId(resultSet.getInt("refer_details_id"));
				referDetailsBean.setName(resultSet.getString("referred_name"));
				referDetailsBean.setPhoneNumber(resultSet
						.getString("referred_phone_number"));
				referDetailsBean.setEmailId(resultSet
						.getString("referred_email_id"));
				referDetailsBean.setCollegeName(resultSet
						.getString("referred_college_name"));
				referDetailsBean.setHackathonId((resultSet
						.getInt("ref_hackathon_id")));
				referDetailsBean.setRefName(resultSet.getString("ref_name"));
				referDetailsBean.setRefPhoneNumber(resultSet
						.getString("ref_phone_number"));
				referDetailsBean.setRefEmailId(resultSet
						.getString("ref_email_id"));
				referDetailsBean
						.setCreatedBy(resultSet.getString("created_by"));
				referDetailsBean.setCreatedDate(resultSet
						.getDate("created_date"));
				referDetailsBean
						.setUpdatedBy(resultSet.getString("updated_by"));
				referDetailsBean.setUpdatedDate(resultSet
						.getDate("updated_date"));

				referDetailsBeanList.add(referDetailsBean);
			}

		} catch (Exception e) {
			e.toString();
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}

		return referDetailsBeanList;

	}

}
