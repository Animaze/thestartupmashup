<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html lang="en">
<head>
<title>Startup Mashup | Event-Details</title>
<meta charset="utf-8">
<!-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" type="image/png"
	href="/StartupMashup/resources/images/icon.png" />
<!-- <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

 -->

    <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"> 
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="<c:url value='/resources/js/subscriber.js'/>"></script>
<script src="<c:url value='/resources/js/login_validations.js'/>"></script>
<link rel="stylesheet" href="<c:url value='/resources/css/form.css'/>" />


<script src="<c:url value='/resources/js/refer_details_validation.js'/>"></script>
<script src="<c:url value='/resources/js/event_validations.js'/>"></script>




<link rel="stylesheet" href="<c:url value='/resources/css/custom.css'/>" />
<link rel="stylesheet"
	href="<c:url value='/resources/css/commonheader.css'/>" />
<link href='http://fonts.googleapis.com/css?family=Roboto'
	rel='stylesheet' type='text/css'>

<link rel="stylesheet" href="<c:url value='/resources/css/form.css'/>" />

</head>
<style>
body {
	color: #7c8682;
}

.header_event {
	background: url('/StartupMashup/resources/images/tsmbanner.jpg') no-repeat center center;
	font-color: white;
	font-weight: bold;
	font-family: 'Roboto', sans-serif;
	padding-top: 100px;
	max-height: 600px;
	margin-top: 0px;
}

.base_container{
	
	padding:20px;

}
.header{
	text-align:left;
	
}

.myrefers_button:hover{
	
	background-color:#17dc5f;
	border: 2px solid transparent;
}
.view_more{
	color:#ef8479;
}

.myrefers_button{
		background-color:#ff4444;
		color:#fff;
		font-weight:bold;
		border: 2px solid transparent;
		border-radius:2px;
		max-width:180px;
		height:auto;
		padding:5px;
		margin:0px 5px 5px 5px;
		/*margin:2px 30px 2px 30px;*/
		
}
.refer_button_container{
		text-align:left;
		padding-left:70px;
		

}
</style>
<style>
#new_candidate_form {
	display: block;
}

#registered_candidate_form {
	display: none;
}

.navbar li :hover {
	background-color: #000;
}

/*accordian menu style*/
/*----- Accordion -----*/
.accordion, .accordion * {
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
}

.accordion {
	overflow: hidden;
	box-shadow: 0px 1px 3px rgba(0, 0, 0, 0.25);
	border-radius: 3px;
	background: #ececec;
}

/*----- Section Titles -----*/
.accordion-section-title {
	width: 100%;
	padding: 15px;
	display: inline-block;
	border: 1px solid #ccc;
	background: #ececec;
	transition: all linear 0.15s;
	/* Type */
	font-size: 1.200em;
	color: #7f7b7b;
}

.accordion-section-title.active, .accordion-section-title:hover {
	background: #4c4c4c;
	/* Type */
	text-decoration: none;
}

.accordion-section:last-child .accordion-section-title {
	border-bottom: none;
}

/*----- Section Content -----*/
.accordion-section-content {
	padding: 15px;
	display: none;
}

/*accordian menu style*/
</style>
<script>
	$(function() {
		$('#new_candidate').click(function() {
			$('#new_candidate_form').fadeIn("slow");
			$('#registered_candidate_form').hide();
		});
	});
	$(function() {
		$('#registered_candidate').click(function() {
			$('#new_candidate_form').hide();
			$('#registered_candidate_form').fadeIn("slow");
		});
	});
</script>

<script>
	$(document).ready(
			function() {
				function close_accordion_section() {
					$('.accordion .accordion-section-title').removeClass(
							'active');
					$('.accordion .accordion-section-content').slideUp(300)
							.removeClass('open');
				}

				$('.accordion-section-title').click(
						function(e) {
							// Grab current anchor value
							var currentAttrValue = $(this).attr('href');

							if ($(e.target).is('.active')) {
								close_accordion_section();
							} else {
								close_accordion_section();

								// Add active class to section title
								$(this).addClass('active');
								// Open up the hidden content panel
								$('.accordion ' + currentAttrValue).slideDown(
										300).addClass('open');
							}
							e.preventDefault();
						});
			});
</script>

<body>

	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar" style="background-color: #fff;"></span> <span
						class="icon-bar" style="background-color: #fff;"></span> <span
						class="icon-bar" style="background-color: #fff;"></span>
				</button>
				<a class="navbar-brand" href="/StartupMashup"> <img
					src="/StartupMashup/resources/images/logo.png"
					class="img-responsive" style="margin-top: -15px;"></img></a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">

				<ul class="nav navbar-nav navbar-right">
					<li class=""><a href="/StartupMashup/about">About</a></li>
					<li class=""><a href="/StartupMashup/employers">Employers</a></li>
					<li class=""><a href="/StartupMashup/contact-us">Contact
							Us</a></li>
					<c:if test="${sessionScope.status == 'loggedOut'}">
						<li><a href="/StartupMashup/sign-up"><span
								class="glyphicon glyphicon-user"></span> Sign Up</a></li>
						<li data-toggle="modal" data-target="#signin_form"><a
							href="#"><span class="glyphicon glyphicon-log-in"></span>
								Login</a></li>

					</c:if>

					<c:if test="${sessionScope.status == 'loggedIn'}">
						<li><a href="/StartupMashup/view-profile"><span
								class="glyphicon glyphicon-user"></span>${sessionScope.userName}</a></li>
						<li><a href="/StartupMashup/logout"><span
								class="glyphicon glyphicon-log-out"></span> Logout</a></li>
					</c:if>


				</ul>
			</div>
		</div>
	</nav>
	<div id="signin_form" class="modal fade" role="dialog">
		<div class="modal-dialog modal-md">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<p class="center grey" style="font-size: 30px;">Please Enter
						your login-details</p>

				</div>
				<div class="modal-body">


					<div id="login_detail_form" style="width: 40%; margin: auto;">
						<form:form role="form" name="loginForm" method="get"
							onsubmit="return authoriseUser(${hackathonId})">
							<br>
							<div class="row">
								<div class="col-sm-12">
									<br> <br> <br>
									<div class="form-group">
										<h3>
											<span id="errorMsg" style="color: red; font-size: 13px"></span>
										</h3>
										<input type="text" class="form-control input-grey"
											id="First_Name" name="userName" placeholder="Username">

									</div>

									<div class="form-group">


										<input type="password" class="form-control input-grey"
											id="email" name="password" placeholder="Password">

									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-sm-12">

									<a href="#" onclick="return sendEmail();">Forgot password</a>
									<img src="/StartupMashup/resources/images/processing.gif" style="display: none;" id="loading_image">
									<br><br>
									<button type="submit"
										class="btn btn-default pull-right refer-button">Submit</button>
								</div>
							</div>
						</form:form>
					</div>


				</div>
				<div class="modal-footer"></div>
			</div>

		</div>
	</div>




	<div class="container header_event" style="margin-top: 85px;">


		<div class="header">
			<div class="row">
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>

				<p class="text" style="padding-left: 80px;">${hackathonBean.date}</p>
				<p class="text" style="padding-left: 80px;">${hackathonBean.venue}</p>
				<%-- <c:if test="${hackathonBean.status == 'live'}">
					<div class="row refer_button_container">
					
						<c:if test="${sessionScope.status == 'loggedIn'}">
							<div class="myrefers_button" data-toggle="modal"
								data-target="#login_form" style="display: inline-block;">
								<a href="javascript:void(0)" style="text-decoration: none;">Enter
									the Challenge</a>
							</div>

						</c:if>
						<c:if test="${sessionScope.status == 'loggedOut'}">
							<div class="myrefers_button" data-toggle="modal"
								data-target="#login_form" style="display: inline-block;">
								<a href="javascript:void(0)" style="text-decoration: none;">Enter
									the Challenge</a>
							</div>
						</c:if>
						<div class="myrefers_button" data-toggle="modal"
							data-target="#referal_form" style="display: inline-block;min-width:150px;text-align:center;">
							<a href="javascript:void(0)" style="text-decoration: none;">Refer</a>
						</div>
					  </div>
				</c:if> --%>
			</div>
		</div>
	</div>






	

	<!-- if condition to check whether the user is logged in. if logged in, then select challenge, otherwise, login or signup. -->
	<c:if test="${sessionScope.status == 'loggedIn'}">
		<div id="registration_form" class="modal fade" role="dialog">
			<div class="modal-dialog">


				<!-- Modal content-->
				<div class="modal-content">

					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<!-- hackathon's name ${hackathonBean.name}-->
						<h2 class="modal-title grey">${hackathonBean.name}</h2>
						<span id="errorMsg" style="color: red; font-size: 13px">All
							fields are mandatory.</span>
						<h3 class="green">Your Details</h3>

					</div>
					<div class="modal-body">
						<form:form role="form"
							action="/StartupMashup/save-hackathon-form-details" method="post"
							modelAttribute="participantBean" name="Participant"
							onSubmit="return checkChanges()">
							<div class="row">
								<div class="col-sm-6">

									<div class="form-group">

										<!-- value attribute in input tag, value="${participantBean.firstName}"-->
										<input type="text" class="form-control input-grey"
											id="First_Name" name="firstName"
											value="${participantBean.firstName}" name="firstName"
											placeholder="${participantBean.firstName}">
									</div>

									<div class="form-group">
										<!-- value attribute in input tag, value="${participantBean.emailId}"-->
										<input type="email" class="form-control input-grey" id="email"
											value="${participantBean.emailId}" name="emailId"
											placeholder="${participantBean.emailId}">

									</div>

									<div class="form-group drop_arrow">

										<!-- list from backend, ${challengeListByHackathonId}, iteration and value attribute in option tags-->

										<select name="selectedChallenge" id="selectedChallenge"
											class="form-control input-grey ">
											<option value="-1" selected="selected">-- Select a
												Challenge --</option>
											<c:forEach items="${listOfChallenges}" var="challenge">
												<option value="${challenge.id}">${challenge.name}</option>
											</c:forEach>
										</select> <input type="hidden" name="hackathonId"
											value="${hackathonId}">

									</div>



								</div>

								<div class="col-sm-6">


									<div class="form-group">

										<!-- value attribute in input tag, value="${participantBean.lastName}"-->
										<input type="text" class="form-control input-grey" id="Name"
											value="${participantBean.lastName}" name="lastName"
											placeholder="${participantBean.lastName}">
									</div>
									<div class="form-group">
										<!-- value attribute in input tag, value="${participantBean.phoneNumber}"-->
										<input type="text" class="form-control input-grey"
											id="Phone_Number" value="${participantBean.phoneNumber}"
											name="phoneNumber"
											placeholder="${participantBean.phoneNumber}">
									</div>


								</div>
								<div class="col-sm-8">
									<br>


								</div>
								<div class="col-sm-4">
									<button type="submit"
										class="btn btn-default pull-right refer-button">Submit</button>
								</div>
							</div>
						</form:form>
					</div>
					<div class="modal-footer"></div>
				</div>
			</div>
		</div>
	</c:if>


	<c:if test="${sessionScope.status == 'loggedOut'}">
		<div id="login_form" class="modal fade" role="dialog">
			<div class="modal-dialog modal-lg">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<!-- name of the hackathon ${hackathonBean.name} -->
						<p class="center grey" style="font-size: 30px;">${hackathonBean.name}
							Registration</p>
						<div class="row">
							<div class="col-sm-12">
								<form role="form">
									<div class="col-sm-6">
										<input type="radio" id="new_candidate" name="radio" /> <label
											for="new_candidate"><span></span>I am new here</label>
									</div>
									<div class="col-sm-6">
										<input type="radio" id="registered_candidate" name="radio" />
										<label for="registered_candidate"><span></span>I am a
											registered User</label>
									</div>
								</form>
							</div>

						</div>
					</div>
					<div class="modal-body">


						<div id="registered_candidate_form"
							style="width: 40%; margin: auto;">
							<form:form role="form" name="registeredLoginForm" method="get"
								onsubmit="return authoriseUser2(${hackathonId});">
								<br>
								<div class="row">
									<div class="col-sm-12">
										<br> <br> <br>
										<div class="form-group">

											<h3>
												<span id="errorMsg2" style="color: red; font-size: 13px"></span>
											</h3>
											<input type="text" class="form-control input-grey"
												id="First_Name" name="userName" placeholder="Username">
										</div>

										<div class="form-group">

											<input type="password" class="form-control input-grey"
												id="email" name="password" placeholder="Password">

										</div>
									</div>
								</div>

								<div class="row">
									<div class="col-sm-12">
									<a href="#" onclick="return sendEmail2();">Forgot password</a>
									<img src="/StartupMashup/resources/images/processing.gif" style="display: none;" id="loading_image">
									<br><br>
										<button type="submit"
											class="btn btn-default pull-right refer-button">Submit</button>
									</div>
								</div>
							</form:form>
						</div>

						<div id="new_candidate_form">
							<form role="form">
								<div class="row">

									<div class="col-sm-4">
										<!-- apply anchor tag here, which will take u sto SIGNUP Page.-->
										<button type="submit"
											class="btn btn-default pull-right refer-button">
											<a href="/StartupMashup/sign-up">Sign-up</a>
										</button>
									</div>
								</div>
							</form>
						</div>

					</div>
					<div class="modal-footer"></div>
				</div>
			</div>
		</div>
	</c:if>




	<%-- 	<div id="login_form" class="modal fade" role="dialog">
		<div class="modal-dialog modal-lg">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<!-- name of the hackathon ${hackathonBean.name} -->
					<p class="center grey" style="font-size: 30px;">Mobikwik Mashup
						Registration</p>
					<div class="row">
						<div class="col-sm-12">
							<form role="form">
								<div class="col-sm-6">
									<input type="radio" id="new_candidate" name="radio" /> <label
										for="new_candidate"><span></span>I am new here</label>
								</div>
								<div class="col-sm-6">
									<input type="radio" id="registered_candidate" name="radio" />
									<label for="registered_candidate"><span></span>I am a
										registered User</label>
								</div>
						</div>
						</form>
					</div>
				</div>
				<div class="modal-body">


					<div id="registered_candidate_form"
						style="width: 40%; margin: auto;">
						<form:form role="form" name="loginForm" method="get"
							onsubmit="return authoriseUser(${hackathonId})">

							<br>
							<div class="row">
								<div class="col-sm-12">
									<br> <br> <br>
									<div class="form-group">


										<input type="text" class="form-control input-grey"
											id="First_Name" name="userName" placeholder="Username">
									</div>

									<div class="form-group">

										<input type="password" class="form-control input-grey"
											id="email" name="password" placeholder="Password">

									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-sm-12">
									<button type="submit"
										class="btn btn-default pull-right refer-button">Submit</button>
								</div>
							</div>
						</form:form>
					</div>

					<div id="new_candidate_form">
						<form role="form">
							<div class="row">

								<div class="col-sm-4">
									<!-- apply anchor tag here, which will take u sto SIGNUP Page.-->
									<button type="submit"
										class="btn btn-default pull-right refer-button">Sign-up</button>
								</div>
							</div>
						</form>
					</div>

				</div>
				<div class="modal-footer"></div>
			</div>

		</div>
	</div>
	</div> --%>


<%-- 	<div id="referal_form" class="modal fade" role="dialog">
		<div class="modal-dialog modal-lg">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<img src="/StartupMashup/resources/images/good_karma.png"
						class="img-circle center-block" alt="Cinque Terre" width="150"
						height="150"></img>
					<h1 style="text-align: center">GOOD KARMA !</h1>

					<p class="center" style="width: 50%">Reference for The Startup
						Mashup. If your reference turns up for the event, both you and
						your reference will win a Flipkart voucher worth Rs 500/-. And if
						your reference lands up a job on the event,you will be rewarded Rs
						50k.</p>

				</div>
				<div class="modal-body">

					<form:form action="/StartupMashup/save-refer-details?flag="
						method="post" modelAttribute="referDetailsBean"
						onSubmit="return validateReferralForm()" name="ReferDetails"
						role="form">
						<div class="row">
							<div class="col-sm-6">
								<h3>
									<span id="errorMsg2" style="color: red; font-size: 13px">All
										fields are mandatory.</span>
								</h3>
								<h3 class="green">Your Details</h3>
								<input type="hidden" id="hackathonId" name="hackathonId"
									value="${hackathonId}">
								<!-- if condition to check whether the user is logged in or not. if logged in, then the details will be pre-filled, otherwise normal-->
								<c:if test="${sessionScope.userId != -1}">
									<div class="form-group">


										<input type="text" class="form-control input-grey" id="Name"
											name="refName" placeholder="${participantBean.firstName}"
											value="${participantBean.firstName}">
									</div>
									<div class="form-group">

										<input type="text" class="form-control input-grey"
											id="Phone_Number" name="refPhoneNumber"
											placeholder="${participantBean.phoneNumber}"
											value="${participantBean.phoneNumber}">
									</div>
									<div class="form-group">
										<input type="email" class="form-control input-grey" id="email"
											placeholder="${participantBean.emailId}" name="refEmailId"
											value="${participantBean.emailId}">

									</div>
								</c:if>
								<c:if test="${sessionScope.userId == -1}">
									<div class="form-group">


										<input type="text" class="form-control input-grey" id="Name"
											name="refName" placeholder="Name">
									</div>
									<div class="form-group">

										<input type="text" class="form-control input-grey"
											name="refPhoneNumber" id="Phone_Number"
											placeholder="Phone Number">
									</div>
									<div class="form-group">
										<input type="email" class="form-control input-grey" id="email"
											name="refEmailId" placeholder="Email-Id">

									</div>
								</c:if>

								<!-- if condition ends -->
							</div>

							<div class="col-sm-6">

								<h3 class="grey">Refer Details</h3>
								<div class="form-group">


									<input type="text" class="form-control input-grey" id="Name"
										name="name" placeholder="Name">
								</div>
								<div class="form-group">

									<input type="text" class="form-control input-grey"
										name="phoneNumber" id="Phone_Number"
										placeholder="Phone Number">
								</div>
								<div class="form-group">
									<input type="email" class="form-control input-grey" id="email"
										name="emailId" placeholder="Email-id">

								</div>
								<div class="form-group">
									<input type="text" class="form-control input-grey" id="college"
										name="collegeName" placeholder="College">

								</div>
							</div>

							<div class="col-sm-8">
								<br> <input type="checkbox" id="confirmation_checkbox"
									name="terms" /> <label for="confirmation_checkbox"> <span></span>
									<p style="font-size: 18px; font-weight: bold; color: #d1cece;">
										By signing in you agree to our pedigree &nbsp My Refers<a
											href="https://drive.google.com/file/d/0B5TvdcdlnEjqS295TlkxSUt0aUNmcFlWcFlCUUt6QzlkSEdB/view?usp=sharing"
											class="term_link green" target="_blank"> Terms and
											Condition</a> and <a href="/StartupMashup/privacy-policy"
											class="term_link green" target="_blank">Privacy Policy</a>
									</p>

								</label>
							</div>
							<div class="col-sm-4">
								<button type="submit"
									class="btn btn-default pull-right refer-button">Refer</button>
							</div>
						</div>
					</form:form>


				</div>
				<div class="modal-footer"></div>
			</div>

		</div>
	</div> --%>










	<div class="container base_container">
		<div class="row">
			<!-- company name by hackathon Id, ${companyBean.name} -->
			<c:if test="${fn:length(listOfCompanies) == 1}">
				<h2>About the Company</h2>
			</c:if>
			<c:if test="${fn:length(listOfCompanies) > 1}">
				<h2>About the Companies</h2>
			</c:if>

		</div>
		<!-- for each -->
		<c:forEach items="${listOfCompanies}" var="company">
			<div class="row">
				<div class="col-sm-3">
					<div class="center">
						<img
							src='${company.logo }'
							style="width: 100%;"></img>
					</div>

				</div>
				<div class="col-sm-9">
					<div class="center"
						style="text-align: left; color: #7c8682; font-size: 20px;">
						<!-- company description by hackathon Id, ${companyBean.description} -->
						<p style="font-weight: bold">${company.name}</p>
						<p>${company.description}</p>
					
						<!-- <p>
						<a href="" class="view_more">View More</a>
					</p> -->

					</div>
				</div>
			</div>
		</c:forEach>
		<!-- for each close -->
		<hr>
		<div class="row">
			<h2>Challenges</h2>
		</div>
		<c:forEach items="${listOfChallenges}" var="challenge">
			<div class="row" style="font-size: 20px; text-align: left;"
				class="visible-md visible-lg">


				<div class="row">
					<div class="col-sm-4">${challenge.name}</div>
					<div class="col-sm-4">
					<c:forEach items="${listOfSkills}" var="skill">
						<c:forEach items="${listOfChallengeSkillMapBeans}"
							var="challengeSkillMap">
							<c:if
								test="${challengeSkillMap.challengeId == challenge.id && challengeSkillMap.skillId == skill.id}">
								${skill.name} &nbsp &nbsp
									<!-- Skills,Skills,Skills,Skills,Skills -->
							
							</c:if>
						</c:forEach>
					</c:forEach>
						</div>
				</div>

				<!-- <div class="row">
				<div class="col-sm-4">Web Development</div>
				<div class="col-sm-4">Skills,Skills,Skills,Skills,Skills</div>
			</div>

			<div class="row">
				<div class="col-sm-4">Web Development</div>
				<div class="col-sm-4">Skills,Skills,Skills,Skills,Skills</div>
			</div>

			<div class="row">
				<div class="col-sm-4">Web Development</div>
				<div class="col-sm-4">Skills,Skills,Skills,Skills,Skills</div>
			</div> -->
			</div>
		</c:forEach>



		<hr>
		<!-- <div class="row">
			<h2>FAQ</h2>
		</div> 
		<div class="row" style="font-size: 15px; text-align: left;">
			<div class="col-sm-4">
				<div>
					<p>
						We will take care of everything.However if you have more
						questions,please write us to <span class="view_more">info@thestartupmashup.com</span>
					</p>
				</div>

			</div>
			<div class="col-sm-8">
				<div class="accordion">
					<div class="accordion-section">
						<a class="accordion-section-title" href="#accordion-1"> What
							will happen in the hackathons?<span
							class="glyphicon glyphicon-chevron-down pull-right"></span>
						</a>
						<div id="accordion-1" class="accordion-section-content">
							<p>Hackathons are some Fear factor level challenges which are
								either a problem being faced by a company in real time or some
								new development that they are trying to do.</p>
						</div>
						end .accordion-section-content
					</div>
					end .accordion-section

					<div class="accordion-section">
						<a class="accordion-section-title" href="#accordion-2"> Will I
							actually get 2X?<span
							class="glyphicon glyphicon-chevron-down pull-right"></span>
						</a>
						<div id="accordion-2" class="accordion-section-content">
							<p>Yes, may sound a bit illusory but it is true. If you are
								the winner of a Hackathon and are also selected in the interview
								rounds, then you are eligible for 2X. Have a look at what the 2X
								winners have to say.</p>
						</div>
						end .accordion-section-content
					</div>
					end .accordion-section

					<div class="accordion-section">
						<a class="accordion-section-title" href="#accordion-3"> What
							all do I need to bring at the event? <span
							class="glyphicon glyphicon-chevron-down pull-right"></span>
						</a>
						<div id="accordion-3" class="accordion-section-content">
							<p>You'll have to bring your laptop (mandatory) and 2 copies
								of your resume.</p>
						</div>
						end .accordion-section-content
					</div>
					<div class="accordion-section">
						<a class="accordion-section-title" href="#accordion-4"> Is
							there any registration fees?<span
							class="glyphicon glyphicon-chevron-down pull-right"></span>
						</a>
						<div id="accordion-4" class="accordion-section-content">
							<p>No there isn’t any registration fees. It’s completely
								free.</p>
						</div>
						end .accordion-section-content
					</div>
					<div class="accordion-section">
						<a class="accordion-section-title" href="#accordion-5"> Why
							should I register with The Startup Mashup?<span
							class="glyphicon glyphicon-chevron-down pull-right"></span>
						</a>
						<div id="accordion-5" class="accordion-section-content">
							<p>The Startup Mashup is an event where the hottest talent
								meets the hottest startups, in a format of Hackathons followed
								by face to face interviews after which the winners are given 2X
								their CTC, all this in a single day. Do we need to say more?</p>
						</div>
						end .accordion-section-content
					</div>
					<div class="accordion-section">
						<a class="accordion-section-title" href="#accordion-6"> I have
							forgotten my Password, how do I access my account?<span
							class="glyphicon glyphicon-chevron-down pull-right"></span>
						</a>
						<div id="accordion-6" class="accordion-section-content">
							<p>Don't worry; this happens to the best of us! Just click on
								the forgot password link on the sign-in page, provide your
								registered e-mail address and we will send you a mail with the
								steps to reset your password! Simple!</p>
						</div>
						end .accordion-section-content
					</div>
					<div class="accordion-section">
						<a class="accordion-section-title" href="#accordion-7"> When
							will I get the flipkart voucher?<span
							class="glyphicon glyphicon-chevron-down pull-right"></span>
						</a>
						<div id="accordion-7" class="accordion-section-content">
							<p>If you refer your friend for any Mashup and if he turns up
								on the event day, then you will get a Rs.500 flipkart voucher.
								Also, if both of you are shortlisted for the event then you both
								will get Rs.500 flipkart voucher each.</p>
						</div>
						end .accordion-section-content
					</div>
					<div class="accordion-section">
						<a class="accordion-section-title" href="#accordion-8">How am
							I eligible for Rs.50,000 Referral reward?<span
							class="glyphicon glyphicon-chevron-down pull-right"></span>
						</a>
						<div id="accordion-8" class="accordion-section-content">
							<p>If your referred friend is selected on the event day and
								is given an offer by the recruiting partner, then you will be
								eligible for the Rs.50,000 referral reward.</p>
						</div>
						end .accordion-section-content
					</div>

					<div class="accordion-section">
						<a class="accordion-section-title" href="#accordion-9">When
							will I get the referral reward?<span
							class="glyphicon glyphicon-chevron-down pull-right"></span>
						</a>
						<div id="accordion-9" class="accordion-section-content">
							<p>The Rs.50,000 referral reward will be received by you in
								45-60 days of your friends joining.</p>
						</div>
						end .accordion-section-content
					</div>
					<div class="accordion-section">
						<a class="accordion-section-title" href="#accordion-10">How
							can I give a feedback/complaint ?<span
							class="glyphicon glyphicon-chevron-down pull-right"></span>
						</a>
						<div id="accordion-10" class="accordion-section-content">
							<p>For any feedbacks/complaints, please write us at
								thestartupmashup@myrefers.com</p>
						</div>
						end .accordion-section-content
					</div>
					end .accordion-section
				</div>
				end .accordion
			</div>
		</div> -->
	</div>
	<c:if test="${hackathonBean.status == 'live'}">
		<div class="container-fluid base_container"
			style="background-color: #ff4444;">

			<div class="row">



				<div class="center">


					<a href="javascript:void(0)"><button type="button" class="btn btn-default myrefers_button"
						data-toggle="modal" data-target="#registration_form" style="border: 2px solid #fff;">
						Enter The Challenge
					</button></a>


				</div>




			</div>
		</div>
	</c:if>



	<!---div for footer starts here----->

	<div class="container-fluid footer">
		<div class="row">
			<br>
		</div>
		<div class="row">
			<br>
		</div>
		<div class="row">
			<div class="col-sm-1 "></div>
			<div class="col-sm-4 ">
				<div style="text-align: left;">
					About <br> <br> <a href="http://www.myrefers.com/"
						target="_blank">My Refers</a><br> <a
						href="/StartupMashup/about">Team</a><br> <a
						href="/StartupMashup/press">Press</a><br>

				</div>

			</div>
			<div class="col-sm-4 ">
				<div class="c">
					<p class="text">Get latest mashup and job opportunities</p>
					<br>
					<form>
						<input type="email" placeholder="Email" class="email"
							style="width: 180px;" id="emailId"></input> <a href="#"
							class="subscribe" onclick="subscribe()">Subscribe</a>


					</form>
				</div>


			</div>
			<div class="col-sm-3 ">
				<div class="center" style="text-align: left;">
					<p>About start up mashup</p>
					<p style="color: #838181;">
						HQ<br> 2/3 Asasf Ali Road,Stirring Mind <br> +91 900 123
						12345
					</p>
				</div>


			</div>

		</div>
		<div class="row">
			<br>
		</div>
		<div class="row">
			<br>
		</div>
	</div>

	<!---div for footer ends here----->
	<div class="container-fluid credit">

		<div class="row">
			<br>
			<p>&copy; 2015 The Startup Mashup</p>
		</div>
	</div>
</body>
</html>
