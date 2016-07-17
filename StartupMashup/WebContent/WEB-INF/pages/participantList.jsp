<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>

<%@page import="com.startupmashup.bean.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Participant List</title>
</head>
<jsp:include page="adminCredentials.jsp"></jsp:include>
<body>
	<h4><a href="/StartupMashup/adminHomePage">Back to Main Menu</a></h4>
	<h4><a href="/StartupMashup/admin/list-users">Back to Previous Menu</a></h4>

	<table>

		<tr>
			<th>Id 
			<td>||</td>
			<th>First Name
			<td>||</td> 
			<th>Last Name
			<td>||</td>
			<th>Username
			<td>||</td>
			<th>Email Id
			<td>||</td> 
			<th>Phone Number
			<td>||</td> 
			<th>Designation
			<td>||</td> 
			<th>College Name
			<td>||</td>
			<th>CTC 
			<td>||</td>
			<th>Company Name
			<td>||</td>
			<th>Resume Location
			<td>||</td>
			<th>Created By
			<td>||</td> 
			<th>Created Date
			<td>||</td> 
			<th>Updated By
			<td>||</td> 
			<th>Updated Date
			<td>||</td>
			<th>MyRefer Flag
		</tr>

		<c:forEach items="${participantBeanList}" var="participant">
			<tr>
				<td>${participant.id}</td>
				<td>||</td>
				<td>${participant.firstName}</td>
				<td>||</td>
				<td>${participant.lastName}</td>
				<td>||</td>
				<td>${participant.userName}</td>
				<td>||</td>
				<td>${participant.emailId}</td>
				<td>||</td>
				<td>${participant.phoneNumber}</td>
				<td>||</td>
				<td>${participant.designation}</td>
				<td>||</td>
				<td>${participant.ctc}</td>
				<td>||</td>
				<td>${participant.companyName}</td>
				<td>||</td>
				<td>${participant.resume}</td>
				<td>||</td>
				<td>${participant.createdBy}</td>
				<td>||</td>
				<td>${participant.createdDate}</td>
				<td>||</td>
				<td>${participant.updatedBy}</td>
				<td>||</td>
				<td>${participant.updatedDate}</td>
				<td>||</td>
				<td>${participant.flag}</td>
			</tr>

		</c:forEach>

</table>
</body>
</html>