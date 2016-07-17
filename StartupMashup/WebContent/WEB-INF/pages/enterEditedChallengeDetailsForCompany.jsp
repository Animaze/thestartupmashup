
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
	var cId=$("#cId").val();
	
	var hackId=$('#hackId_'+index).val();
	
 /*    var drpValue=$('#drpdwn_'+index).val();  */

 

	

	
	var drpText="";
 drpText=$('#drpdwn_'+index + ' :selected').text();

 	 if(drpText==""){
 		alert("select a challenge and then map!");
 	} else{
 		alert("Challenge mapped successfully!!!");
 	}
	var listBox= $('#drpdwn_'+index + ' option:selected');
	var listVal="";
	$.each(listBox, function(index,item){
		listVal+=',' +$(this).val();
	})
	 

	
	$.ajax({  
		url: "/StartupMashup/admin/map-edited-company-challenges-details", 
		data: {cId:cId,hackId:hackId,listVal:listVal,result:"false"},
		method: "POST",
		dataType :"json",
		success: function(result){
			alert("Challenge Mapped Successfully");}
	});
    

	
}

</script>
<jsp:include page="adminCredentials.jsp"></jsp:include>
<body>
	

<h2>Select the challenges the company wants to enter :</h2>

<div style="color: red;"> <h3>${editPageMsg }</div>
	
<form action="/StartupMashup/admin/map-edited-company-challenges-details" method="post">
<input type="hidden" value="${companyId }" id="cId"/>
<table>


			<tr><th align="left">Hackathon Id
				<th align="right">Hackathon Name</th>
				<th>Challenge Name</th>
				<th>Add Challenge</th>
			</tr>
			<c:forEach items="${listOfHackathons }" var="hackathonBean" varStatus="HBList">
				<tr>
				<td>${hackathonBean.id }
					<td>
						${hackathonBean.name }
						<input type="hidden" name="hackathonIndex" value="${hackathonBean.id }" id="hackId_${HBList.count}" />
					</td>
					<td>
						<select name="challengeId" id="drpdwn_${HBList.count}" multiple="multiple">
						
							<c:forEach items="${listOfHackathonChallengeMapBean }" var="mapBean">
								<c:forEach items="${listOfChallenges }" var="challengeBean">
									<c:if test="${hackathonBean.id == mapBean.hackathonId  &&  mapBean.challengeId == challengeBean.id }">	
										<option  value="${challengeBean.id }" >${challengeBean.id } &nbsp ${challengeBean.name }</option>
									</c:if>
									
									
								</c:forEach>
							</c:forEach>
						</select>
					</td>
					<td><input type="button" id="btn_${HBList.count }" onclick="sendDetails(this);" value="Map Challenge"></td>
				
				</tr><br>
			</c:forEach>
			<tr>
			<td><td><td><td><input type="hidden" value="true" name="result"/>
			<input type="hidden" value="0" name="cId"/>
			<input type="hidden" value="0" name="hackId"/>
			<input type="hidden" value="0" name="listVal"/>
			<input type="submit" value="Save Details"/>

			<tr>
				<td><div style="color: red;">
						Previously selected challenges for the company
						were :
					</div>
			<tr>
				<td>Hackathon Id
				<td>::
				<td>Challenge Id 
				<c:forEach items="${companyHackathonMapBean }"
						var="companyHackathonIds">
						<tr>
							<td>${companyHackathonIds.hackathonId }
							<td>::
							<td>
						<c:forEach items="${listOfHackathonChallengeMapBean }" var="hackathonChallengeIds">
						
							<c:forEach 	items="${companyChallengeMapBean }" var="companyChallengeIds">
							<c:if test="${companyHackathonIds.hackathonId == hackathonChallengeIds.hackathonId  &&  hackathonChallengeIds.challengeId == companyChallengeIds.challengeId }">
							${companyChallengeIds.challengeId } &nbsp 
							</c:if>
							
							
							
							</c:forEach>
							</c:forEach>
							</c:forEach>

			</table>
						
				
					
				
</form>
</body>
</html>
