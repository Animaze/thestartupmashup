<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>
<%@page import="com.startupmashup.bean.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Challenge List</title>
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

	<h4><a href="/StartupMashup/adminHomePage">Back to Main Menu</a></h4>
	
	<form action="/StartupMashup/admin/edit-challenges" onsubmit="return validateRadio()">
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
				<th>Selected Skill(s)
				<td>||
				<th>Created By
				<td>||
				<th>Created Date
				<td>||
				<th>Updated By
				<td>||
				<th>Updated Date
			</tr>

			<c:forEach items="${listOfChallenges}" var="challenge">
				<tr>
					<td><input type="radio" id="challengeId" name="challengeId" value="${challenge.id}">
					
					<td>||
					<td>${challenge.id}</td>
					<td>||
					<td>${challenge.name}</td>
					<td>||
					<td>${challenge.description}</td>
					<td>||
					<td>
						<select name="mappedSkills" size=1>
							<c:forEach items="${challengeSkillMapBeanList }" var="challengeSkillMap">
								<c:forEach items="${skillBeanList }" var="skill">
									<c:if test="${challenge.id == challengeSkillMap.challengeId && challengeSkillMap.skillId == skill.id }">
										<option>${skill.name }</option>
									</c:if>
								</c:forEach>
							</c:forEach>
						</select>
					</td>
					<td>||
					<td>${challenge.createdBy}</td>
					<td>||
					<td>${challenge.createdDate}</td>
					<td>||
					<td>${challenge.updatedBy}</td>
					<td>||
					<td>${challenge.updatedDate}</td>
				</tr>

			</c:forEach>

		</table>
		<input type="submit" value="Edit">
	</form>
</body>
</html>