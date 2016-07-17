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
<h4><a href="/StartupMashup/admin/list-companies">Back to Previous Menu</a></h4>
	

	<br>
	<h4>Enter the Details you want to change</h4>


	<br>
	<br>

	<table>
		<form:form action="/StartupMashup/admin/save-edited-company-details"
			modelAttribute="companyBean" method="POST" enctype="multipart/form-data">



			<tr>
				<td>Name :<input type="hidden" name="id" value="${companyBean.id }"/>
				<td><input type="text" name="name" value="${companyBean.name }"/> 
			<tr>
				<td>About your Company :
				<td><input type="text" name="description" value="${companyBean.description }"/>
			<tr>
				<td>Previous Logo was :
				<td><img height="50" width="50" src="${companyBean.logo }"><br>
				<a href="${companyBean.logo }" target="_blank">View Large Image</a>
			<tr>
				<td>Upload New Logo :
				<td><input type="file" name="myLogo" />
				<input type="hidden" name="oldLogo" value="${companyBean.logo }"/> 
				<br>(Size must be 700px X 400px)
				
			<tr>
				<td>
				<td><input type="submit" value="SUBMIT">
		</form:form>
	</table>




</body>
</html>