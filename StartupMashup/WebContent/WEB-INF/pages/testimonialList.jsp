<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>
<%@page import="com.startupmashup.bean.*"%>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js">
</script>
<html>
<head>
<link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>">
<script type="text/javascript">
	var newText="";
	var newYear="";
	var newMonth="";
	var newDay="";
	
			$(function(){
				$(".editable").dblclick(function replaceHtml() {
					oldText = $(this).html();
					$(this).addClass("noPad").html(
							"<input type='text' value='"+oldText+"'/>").unbind("dblclick",replaceHtml);
							
							$(this).children().keypress(function(myEvent){
								
								var code = (myEvent.keyCode ? myEvent.keyCode : myEvent.which);
								if(code == 13){
									newText=$(this).val();
								    $(this).parent().text(newText).bind("dblclick",replaceHtml);
									$(this).blur();
									
								}
								
							});
					
							
							
							
				});
				
				$(".editableDate").dblclick(function replaceDate() {
					oldText = $(this).html();
					
					$(this).addClass("noPad").html("<select id='day'><option>- dd -</option><option value='01'>01</option><option value='02'>02</option><option value='03'>03</option><option value='04'>04</option><option value='05'>05</option><option value='06'>06</option><option value='07'>07</option><option value='08'>08</option><option value='09'>09</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select><select id='month'><option>- mm -</option><option value='01'>01</option><option value='02'>02</option><option value='03'>03</option><option value='04'>04</option><option value='05'>05</option><option value='06'>06</option><option value='07'>07</option><option value='08'>08</option><option value='09'>09</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option></select><select id='year'><option>- yyyy -</option><option value=2015>2015</option><option value='2016'>2016</option><option value='2017'>2017</option><option value='2018'>2018</option><option value='2019'>2019</option><option value='2020'>2020</option></select>").unbind("dblclick",replaceDate);
							
							$(this).children().keypress(function(myEvent){
								
								var code = (myEvent.keyCode ? myEvent.keyCode : myEvent.which);
								if(code == 13){
									newYear=$('#year').find(":selected").text();
									newMonth=$('#month').find(":selected").text();
									newDay=$('#day').find(":selected").text();
									
								    $(this).parent().text(newYear+'-'+newMonth+'-'+newDay).bind("dblclick",replaceDate);
									$(this).blur();
									
								}
								
							});
					
							
							
							
				});
				
				$(".editable").hover(
				function(){
					$(this).addClass("editHover");
				}		,
				function() {
					$(this).removeClass("editHover");
				}
				);
				
				$(".editableDate").hover(
						function(){
							$(this).addClass("editHover");
						}		,
						function() {
							$(this).removeClass("editHover");
						}
						);
				});
			
			

				function saveChanges(thisButton) {

					var buttonId = $(thisButton).attr('id');
					var testimonialId = buttonId.split('_')[1];

					var participantName = $('#participant_' + testimonialId).text();
					var challengeName = $('#challenge_' + testimonialId).text();
					var companyName = $('#company_' + testimonialId).text();
					var words= $('#words_' + testimonialId).text();
					var date = $('#date_' + testimonialId).text().split("-");
					var year = date[0];
					var month = date[1];
					var day = date[2];
					
					
					
					$.ajax({
						url : "/StartupMashup/admin/edit-testimonial",
						data : {
							testimonialId : testimonialId,
							participantName : participantName,
							challengeName : challengeName,
							companyName : companyName,
							words : words,
							day : day,
							month : month,
							year : year							
						},
						method : "POST",
						dataType : "json",

						success : function(result) {
					
						}
					});
				}

				
				function validateRadio(){
				    var radioStatus = $('input[type=radio]:checked').size() > 0;
				    
				    if(!radioStatus)
				        alert("Select some Radio first!");
				    return radioStatus;
				}
				</script>
			

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Testimonial List</title>
</head>
<jsp:include page="adminCredentials.jsp"></jsp:include>
<body>

	<h4><a href="/StartupMashup/adminHomePage">Back to Main Menu</a></h4>

	<form action="/StartupMashup/admin/edit-testimonial" method="post" onsubmit="return validateRadio()">
		<table>

			<tr>
				<th>Select
				<td>||
				<th>Id
				<td>||
				<th>Participant Name
				<td>||
				<th>Challenge Name
				<td>||
				<th>Company Name
				<td>||
				<th>Words
				<td>||
				<th>Dated
				<td>||
				 <th>Image
				 <td>||
				<th>Created By
				<td>||
				<th>Created Date
				<td>||
				<th>Updated By
				<td>||
				<th>Updated Date
			</tr>

			<c:forEach items="${listOfTestimonials}" var="testimonial">
				<tr>
					<td><input type="radio" id="testimonialId" name="testimonialId" value="${testimonial.id}"></td>
					<td>||
					<td>${testimonial.id}</td>
					<td>||
					<td>${testimonial.participantName}</td>
					<td>||
					<td>${ testimonial.challengeName}
					<td>||
					<td>${ testimonial.companyName}
					<td>||
					<td>${ testimonial.words}
					<td>||
					<td>${testimonial.date}
					<td>||
					<td><img  src="${testimonial.image}" height="50px" width="50px"><a href="${testimonial.image}" target="_blank">${testimonial.image}</a></td>
					<td>||
					<td>${ testimonial.createdBy}
					<td>||
					<td>${testimonial.createdDate}
					<td>||
					<td>${ testimonial.updatedBy}
					<td>||
					<td>${ testimonial.updatedDate}
				</tr>

			</c:forEach>
			<tr>
			<td><input type="submit" value="Edit"></td>
		</table>
		
	</form>
</body>
</html>

