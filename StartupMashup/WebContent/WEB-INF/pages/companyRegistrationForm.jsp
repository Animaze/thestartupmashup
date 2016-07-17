<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>


<title>Enter Company Details</title>
</head>

<jsp:include page="adminCredentials.jsp"></jsp:include>

<body>

	<h4><a href="/StartupMashup/adminHomePage">Back to Main Menu</a></h4>
	

	<br>
	<h4>Enter Company Details</h4>


	<br>
	<br>

	<table>
		<form:form action="/StartupMashup/admin/save-company-details"
			modelAttribute="companyBean" method="POST" enctype="multipart/form-data">



			<tr>
				<td>Name :
				<td><form:input path="name" /> 
			<tr>
				<td>About your Company :
				<td><form:input path="description" /> 
			<tr>
				<td>Upload your logo :
				<td><input type="file" name="myLogo" />
				
				<tr><td><td>(Size of logo must be 700px X 400px)</td>
			<tr>
				<td>
				<td><input type="submit" value="SUBMIT">
		</form:form>
	</table>




</body>
</html>