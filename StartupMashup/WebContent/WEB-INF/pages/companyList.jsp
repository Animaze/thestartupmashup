<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>

<%@page import="com.startupmashup.bean.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Company List</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js">
</script>
<script>

function validateRadio(){
    var radioStatus = $('input[type=radio]:checked').size() > 0;
    
    if(!radioStatus)
        alert("Select some Radio first!");
    return radioStatus;
}
		
</script>
<jsp:include page="adminCredentials.jsp"></jsp:include>
<body>

	<h4>
		<a href="/StartupMashup/adminHomePage">Back to Main Menu</a>
	</h4>

	<form action="/StartupMashup/admin/edit-company-details" method="post" onsubmit="return validateRadio()">
		<table>
			<tr>
				<th>Select
				<td>||
				<th>Id
				<td>||
				<th>Name
				<td>||
				<th>Description
				<td>||
				<th>Company logo location
				<td>||
				<th>Selected Hackathon(s)
				<td>||
				<th>Created By
				<td>||
				<th>Created Date
				<td>||
				<th>Updated By
				<td>||
				<th>Updated Date
			</tr>

			<c:forEach items="${listOfCompany}" var="company">
				<tr>
					<td><input type="radio" id="companyRadio" name="companyId"
						value="${company.id}" />
						<td>||
					<td>${company.id}</td>
					<td>||
					<td>${company.name}</td>
					<td>||
					<td>${company.description}</td>
					<td>||
					<td><img src="${company.logo}"
						height="100px" width="100px"> <a href="${company.logo}"
						target="_blank">${company.logo}</a></td>
					<td>||
					<td>
						<select name="mappedHackathons" size=1>
							<c:forEach items="${mapBeanList }" var="hackathonCompanyMap">
								<c:forEach items="${hackathonBeanList }" var="hackathon">
									<c:if test="${company.id == hackathonCompanyMap.companyId && hackathonCompanyMap.hackathonId == hackathon.id }">
										<option>${hackathon.name }</option>
									</c:if>
								</c:forEach>
							</c:forEach>
						</select>
					</td>
					<td>||
					<td>${company.createdBy}</td>
					<td>||
					<td>${company.createdDate}</td>
					<td>||
					<td>${company.updatedBy}</td>
					<td>||
					<td>${company.updatedDate}</td>
				</tr>

			</c:forEach>
			<tr>
				<td><input type="submit" value="Edit" />
		</table>
	</form>

</body>
</html>