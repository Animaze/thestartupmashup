<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>

<%@page import="com.startupmashup.bean.*" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Challenge Registration</title>
</head>
<jsp:include page="adminCredentials.jsp"></jsp:include>
<body>

	<h4><a href="/StartupMashup/adminHomePage">Back to Main Menu</a></h4>

<br><br>
<h3>Challenge Details:</h3>
<br><br>
<table>
<form:form action="/StartupMashup/admin/save-challenge-details" method="post" modelAttribute="challengeBean">
<tr><td>Challenge Name :<td> <input type="text" name="name">
<tr><td>Challenge Description :<td> <input type="text" name="description"><br/>

<tr>
<td>Select Skills : 
<td><select multiple name="selectedSkillList" size=2> 

<c:forEach items="${skillBeanList}" var="skillBean">
<option value="${skillBean.id}">${skillBean.name}</option>
</c:forEach>

</select>
<tr><td><td><input type="submit" value="SUBMIT">
</form:form>
</table>
</body>
</html>