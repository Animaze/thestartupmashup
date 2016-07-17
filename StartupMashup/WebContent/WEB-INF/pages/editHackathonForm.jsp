<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<jsp:include page="adminCredentials.jsp"></jsp:include>

<body>
	<h4>
		<a href="/StartupMashup/adminHomePage">Back to Main Menu</a>
	</h4>
	<h4>
		<a href="/StartupMashup/admin/list-hackathons">Back to Previous
			Menu</a>
	</h4>

	<br>
	<div style="color: red">
		<h4>Enter Hackathon Details</h4>

		<br>Enter only those details, which are to be edited!!
	</div>


	<br>
	<br>

	<table>
		<form:form action="/StartupMashup/admin/save-edited-hackathon"
			modelAttribute="hackathonBean" method="POST"
			enctype="multipart/form-data">
			<tr>
				<td>Hackathon name :
				<td><input type="text" name="name"
					value="${hackathonBean.name }" /> <input type="hidden" name="id"
					value="${hackathonBean.id}"> <input type="hidden"
					name="participantCount" value="${hackathonBean.participantCount}" />
			<tr>
				<td>Hackathon Status :
				<td><input type="text" name="status"
					value="${hackathonBean.status }" />
			<tr>
				<td>Hackathon Venue :
				<td><input type="text" name="venue"
					value="${hackathonBean.venue }" />
			<tr>
				<td>Event Date :
				<td><select name="day">
						<option value="-1">- dd -</option>
						<option value="01">1</option>
						<option value="02">2</option>
						<option value="03">3</option>
						<option value="04">4</option>
						<option value="05">5</option>
						<option value="06">6</option>
						<option value="07">7</option>
						<option value="08">8</option>
						<option value="09">9</option>
						<option value="10">10</option>
						<option value="11">11</option>
						<option value="12">12</option>
						<option value="13">13</option>
						<option value="14">14</option>
						<option value="15">15</option>
						<option value="16">16</option>
						<option value="17">17</option>
						<option value="18">18</option>
						<option value="19">19</option>
						<option value="20">20</option>
						<option value="21">21</option>
						<option value="22">22</option>
						<option value="23">23</option>
						<option value="24">24</option>
						<option value="25">25</option>
						<option value="26">26</option>
						<option value="27">27</option>
						<option value="28">28</option>
						<option value="29">29</option>
						<option value="30">30</option>
						<option value="31">31</option>
				</select> <select name="month">
						<option>- mm -</option>
						<option value="01">1</option>
						<option value="02">2</option>
						<option value="03">3</option>
						<option value="04">4</option>
						<option value="05">5</option>
						<option value="06">6</option>
						<option value="07">7</option>
						<option value="08">8</option>
						<option value="09">9</option>
						<option value="10">10</option>
						<option value="11">11</option>
						<option value="12">12</option>
				</select> <select name="year">
						<option>- yyyy -</option>
						<option value=2015>2015</option>
						<option value="2016">2016</option>
						<option value="2017">2017</option>
						<option value="2018">2018</option>
						<option value="2019">2019</option>
						<option value="2020">2020</option>

				</select></td>
			<tr>
				<td>Previous Date was :</td>
				<td><input type="text" name="previousDate" readonly
					value="${hackathonBean.date }" />
			</tr>
			<tr>
				<td>Previous Image was :
				<td><img height="50" width="50" src="${hackathonBean.image }"><br>
				<a href="${hackathonBean.image }" target="_blank">View Large Image</a>
			<tr>
				<td>Change Hackathon Image :
				<td><input type="file" name="hackImage" /> <input
					type="hidden" name="oldHackImage" value="${hackathonBean.image }" />
					<br>(Size must be 700px X 400px)
			<tr>
				<td>Select Challenges :</td>
				<td><select name="challengeId" size="2" multiple="multiple">
						<option value="-1" selected="selected">-- Select
							Challenges --</option>
						<c:forEach items="${challengeBeanList }" var="challengeBean">
							<option value="${challengeBean.id }">${challengeBean.name }</option>
						</c:forEach>
				</select></td>

			</tr>
			<tr>
				<td><br>Previous Challenges were :</td>
				<td><c:forEach items="${selectedChallengeBeanList }"
						var="challenge">
						&nbsp&nbsp&nbsp&nbsp<br>${challenge.name }
					</c:forEach></td>
			</tr>

			<tr>
				<td>
				<td><input type="submit" value="Update">
		</form:form>
	</table>


</body>
</html>