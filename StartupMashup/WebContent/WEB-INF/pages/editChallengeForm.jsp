<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>

<%@page import="com.startupmashup.bean.*"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Challenge Editing</title>
</head>
<jsp:include page="adminCredentials.jsp"></jsp:include>
<body>

	<h4><a href="/StartupMashup/adminHomePage">Back to Main Menu</a></h4>
	<h4><a href="/StartupMashup/admin/list-challenges">Back to Previous Menu</a></h4>
	
	<br>
	<br>
	<h3>Challenge Details:</h3>
	<br>
	<br>

	<form action="/StartupMashup/admin/save-edited-challenge"
		method="post" modelAttribute="challengeBean">
		<table>
			<tr>
				<td>
				<td><input type="hidden" name="id" value="${challengeBean.id}">
			<tr>
				<td>Challenge Name :
				<td><input type="text" name="name"
					value="${challengeBean.name}" placeholder="${challengeBean.name}">
			<tr>
				<td>Challenge Description :
				<td><input type="text" value="${challengeBean.description}"
					name="description" placeholder="${challengeBean.description}"><br />
			<tr>
				<td>Select Skills :
				<td><select multiple name="selectedSkillList" size=2>
						<option value="-1" selected="selected">-Skills-</option>
						<c:forEach items="${skillBeanList}" var="skillBean">
							<option value="${skillBean.id}">${skillBean.name}</option>
						</c:forEach>

				</select>
				<tr>
				<td>Previous Selected Skills were :
				<td>
					<c:forEach items="${selectedSkillBeanList }" var="skill">
						${skill.name }<br/>	
					</c:forEach>	
				</td>
			<tr>
				<td>
				<td><input type="submit" value="SUBMIT">
		</table>
	</form>

</body>
</html>