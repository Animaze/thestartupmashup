<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<jsp:include page="adminCredentials.jsp"></jsp:include>
<body>

	<h4><a href="/StartupMashup/adminHomePage">Back to Main Menu</a></h4>

	<h3>Skill Details:</h3>
	<br>
	<br>
	<table>
	<form:form action="/StartupMashup/admin/save-skill-details" method="post" modelAttribute="skillBean">
		<tr><td>Skill Name:<td><input type="text" name="name" placeholder="Skill name" />
		<tr><td><td><input type="submit" name="submit" />
	</form:form>
</table>
</body>
</html>