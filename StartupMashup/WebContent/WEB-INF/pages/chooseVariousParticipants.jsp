<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js">
</script>

<script>

	function sendDetails(thisBtn){
		var btnId = $(thisBtn).attr('id');
		var btnIndex = btnId.split('_');
		
		var index = btnIndex[1];
		
		var hackId=$('#hackId_'+index).val();
		var drpValue=$('#drpdwn_'+index +' :selected').val();
		var drpText=$('#drpdwn_'+index + ' :selected').text();
		console.log(hackId);
		console.log(drpValue);
		console.log(drpText);
		window.location.href="/StartupMashup/admin/participant-list-hackathon-challenge?hackathonId="+hackId+"&challengeId="+drpValue;
		
	}
	
</script>

<jsp:include page="adminCredentials.jsp"></jsp:include>

<body>
	<h4><a href="/StartupMashup/adminHomePage">Back to Main Menu</a></h4>

	<div style="color: red; font-family:'Robot' "><h3>==> For ALL USERS in Startup Mashup. <a href="/StartupMashup/admin/participant-list">Click Here!</a>
	
	<br><br>==> For Hackathon wise Segregation, click the button corresponding to the Hackathon (Do Not select anything in the Challenge Name, keep the Select Challenge Selected).
	
	<br><br>==> For Challenge wise Segregation, choose the Challenge Name and, click the Find Button.</h3></div>
		
				
		
		<table>
			<tr>
				<th>Hackathon Name</th>
				<th>Challenge Name</th>
				<th>Submit</th>
			</tr>
			<c:forEach items="${hackathonBeanList }" var="hackathonBean" varStatus="HBList">
				<tr>
				
					<td>
						${hackathonBean.name }
						<input type="hidden" name="hackathonIndex" value="${hackathonBean.id }" id="hackId_${HBList.count}" />
					</td>
					<td>
						<select name="challengeId" id="drpdwn_${HBList.count}">
							<option value="-1">-- Select Challenge --</option>
							<c:forEach items="${hackathonChallengeMapBeanList }" var="mapBean">
								<c:forEach items="${challengeBeanList }" var="challengeBean">
									<c:if test="${hackathonBean.id == mapBean.hackathonId  &&  mapBean.challengeId == challengeBean.id }">	
										<option  value="${challengeBean.id }">${challengeBean.name }</option>
									</c:if>
								</c:forEach>
							</c:forEach>
						</select>
					</td>
					<td><input type="button" id="btn_${HBList.count }" onclick="sendDetails(this);" value="Find Participants"></td>
				
				</tr><br>
			</c:forEach>
		</table>
		

</body>
</html>
