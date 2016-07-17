<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<jsp:include page="adminCredentials.jsp"></jsp:include>
<body>


	<a href="/StartupMashup/adminHomePage">Back to Previous Menu</a>

	
		<table>
			<tr>
				<th>Referral Id</th>
				<td>||</td>
				<th>Referred Name</th>
				<td>||</td>
				<th>Referred Phone Number</th>
				<td>||</td>
				<th>Referred Email Id</th>
				<td>||</td>
				<th>Referred College Name</th>
				<td>||</td>
				<th>Hackathon Id</th>
				<td>||</td>
				<th>Referee Name</th>
				<td>||</td>
				<th>Referee Phone Number</th>
				<td>||</td>
				<th>Referee Email Id</th>
				<td>||</td>
				<th>Created By</th>
				<td>||</td>
				<th>Created Date</th>
				<td>||</td>
				<th>Updated By</th>
				<td>||</td>
				<th>Updated Date</th>
			</tr>

			<c:forEach items="${referDetailsBeanList }" var="referDetailsBean">
				<tr>
					<td>${referDetailsBean.id}</td>
					<td>||</td>
					<td>${referDetailsBean.name}</td>
					<td>||</td>
					<td>${referDetailsBean.phoneNumber}</td>
					<td>||</td>
					<td>${referDetailsBean.emailId}</td>
					<td>||</td>
					<td>${referDetailsBean.collegeName}</td>
					<td>||</td>
					<td>${referDetailsBean.hackathonId}</td>
					<td>||</td>
					<td>${referDetailsBean.refName}</td>
					<td>||</td>
					<td>${referDetailsBean.refPhoneNumber}</td>
					<td>||</td>
					<td>${referDetailsBean.refEmailId}</td>
					<td>||</td>
					<td>${referDetailsBean.createdBy}</td>
					<td>||</td>
					<td>${referDetailsBean.createdDate}</td>
					<td>||</td>
					<td>${referDetailsBean.updatedBy}</td>
					<td>||</td>
					<td>${referDetailsBean.updatedDate}</td>
				</tr>
			</c:forEach>	
		</table>
	

</body>
</html>