<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>
<%@page import="com.startupmashup.bean.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript"
	src="/StartupMashup/resources/js/jquery-1.10.2.js"></script>
<html>
<head>
<link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>">
<script type="text/javascript">
	var newText="";
			$(function(){
				$(".editable").dblclick(function replaceHtml() {
					oldText = $(this).html();
					/* alert(oldText);
					alert(this);
					 */$(this).addClass("noPad").html(
							"<input type='text' value='"+oldText+"'/>").unbind("dblclick",replaceHtml);
							
							$(this).children().keypress(function(myEvent){
								
								var code = (myEvent.keyCode ? myEvent.keyCode : myEvent.which);
								if(code == 13){
									newText=$(this).val();
									/* alert(newText); */
									$(this).parent().text(newText).bind("dblclick",replaceHtml);
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
				});

				function saveChanges(thisButton) {

					var buttonId = $(thisButton).attr('id');
					var skillId = buttonId.split('_')[1];

					var skillName = $('#name_' + skillId).text();
					alert($('#name_' + skillId).text());

					$.ajax({
						url : "/StartupMashup/admin/edit-skill",
						data : {
							skillId : skillId,
							skillName : skillName
						},
						method : "POST",
						dataType : "json",

						success : function(result) {
					
						}
					});
				}

				

			
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hackathon List</title>
</head>
<jsp:include page="adminCredentials.jsp"></jsp:include>
<body>

	<h4><a href="/StartupMashup/adminHomePage">Back to Main Menu</a></h4>

	<!-- <form action="/StartupMashup/admin/admin-home"> -->
		<table>

			<tr>
				<th>Id
				<td>||
				<th>Name
				<td>||
				<th>Created By
				<td>||
				<th>Created Date
				<td>||
				<th>Updated By
				<td>||
				<th>Updated Date
			</tr>

			<c:forEach items="${listOfSkills}" var="skill">
				<tr>
					<td>${skill.id}</td>
					<td>||
					<td  class="editable" id="name_${skill.id}">${skill.name}</td>
					<td>||
					<td>${skill.createdBy}</td>
					<td>||
					<td>${skill.createdDate}</td>
					<td>||
					<td>${skill.updatedBy}</td>
					<td>||
					<td>${skill.updatedDate}</td>
					<td><input type="button" id="butId_${skill.id}"
						onclick="saveChanges(this);" value="UPDATE"></td>
				</tr>

			</c:forEach>

		</table>
		<!-- <input type="submit" value="DONE"> -->
	<!-- </form> -->
</body>
</html>