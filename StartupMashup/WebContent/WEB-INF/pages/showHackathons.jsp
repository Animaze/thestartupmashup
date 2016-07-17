<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>

<%@page import="com.startupmashup.bean.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hackathon List</title>
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
<style>
table td tr {
	background:#fcc;
	color:#000;
	border:1px;
}
</style>
<jsp:include page="adminCredentials.jsp"></jsp:include>
<body>

	<h4><a href="/StartupMashup/adminHomePage">Back to Main Menu</a></h4>
	
	
	<form action="/StartupMashup/admin/edit-hackathon" method="get" onsubmit="return validateRadio()">
		<table>

			<tr>
				<th>Select</th>
				<td>||</td>
				<th>Id</th>
				<td>||</td>
				<th>Name</th>
				<td>||</td>
				<th>Status</th>
				<td>||</td>
				<th>Venue</th>
				<td>||</td>
				<th>Date</th>
				<td>||</td>
				<th>No of Participants</th>
				<td>||</td>
				<th>Image</th>
				<td>||</td>
				<th>Selected Challenge(s)
				<td>||
				<th>Registered Companies
				<td>||
				<th>Created By</th>
				<td>||</td>
				<th>Created Date</th>
				<td>||</td>
				<th>Updated By</th>
				<td>||</td>
				<th>Updated Date</th>
			</tr>

			<c:forEach items="${listOfHackathons}" var="hackathon">
				<tr>
					<td>
						<input type="radio" id="hackathonId" name="hackathonId" value="${hackathon.id}">
					</td>
					<td>||</td>
					<td>${hackathon.id}</td>
					<td>||</td>
					<td>${hackathon.name}</td>
					<td>||</td>
					<td>${hackathon.status}</td>
					<td>||</td>
					<td>${hackathon.venue}</td>
					<td>||</td>
					<td>${hackathon.date}</td>
					<td>||</td>
					<td>${hackathon.participantCount}</td>
					<td>||</td>
					<td><img  src="${hackathon.image}" height="50px" width="50px">  <a href="${hackathon.image}" target="_blank">${hackathon.image}</a></td>
					<td>||</td>
					<td>
						<select name="mappedChallenges" size=1>
							<c:forEach items="${mapBeanList }" var="hackathonChallengeMap">
								<c:forEach items="${challengeBeanList }" var="challenge">
									<c:if test="${hackathon.id == hackathonChallengeMap.hackathonId && hackathonChallengeMap.challengeId == challenge.id }">
										<option>${challenge.name }</option>
									</c:if>
								</c:forEach>
							</c:forEach>
						</select>
					</td>
					<td>||
					<td>
						<select name="mappedCompanies" size=1>
							<c:forEach items="${mapBeanList2 }" var="hackathonCompanyMap">
								<c:forEach items="${companyBeanList }" var="company">
									<c:if test="${hackathon.id == hackathonCompanyMap.hackathonId && hackathonCompanyMap.companyId == company.id }">
										<option>${company.name }</option>
									</c:if>
								</c:forEach>
							</c:forEach>
						</select>
					</td>
					<td>||
					<td>${hackathon.createdBy}</td>
					<td>||</td>
					<td>${hackathon.createdDate}</td>
					<td>||</td>
					<td>${hackathon.updatedBy}</td>
					<td>||</td>
					<td>${hackathon.updatedDate}</td>
				</tr>

			</c:forEach>
			
			<tr>
				<td>
					<input type="submit" value="Edit">
				</td>
			</tr>
			

		</table>
	</form>
</body>
</html>