<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Startup Mashup | Forgot Password</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" type="image/png"
	href="/StartupMashup/resources/images/icon.png" />
    <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"> 
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="<c:url value='/resources/js/subscriber.js'/>"></script>
<script src="<c:url value='/resources/js/login_validations.js'/>"></script>
<link rel="stylesheet" href="<c:url value='/resources/css/form.css'/>" />
<link rel="stylesheet" href="<c:url value='/resources/css/form.css'/>" />
<link rel="stylesheet" href="<c:url value='/resources/css/custom.css'/>" />

</head>

<script>
function checkPassword(){
	
	$('input[type="password"]').css('border', '0px solid white');
	
	var form = document.forms['forgotPasswordForm'];
var newPassword=$("#newPassword").val();

var reTypedNewPassword=$("#reTypedNewPassword").val();	

var flag=true;
var errorMessage="";
var count=0;

if(newPassword!=reTypedNewPassword){
	flag=false;
	count++;
	form.newPassword.style.border = "2px solid red";
	form.reTypedNewPassword.style.border = "2px solid red";
	errorMessage="Both passwords do not match";
}
if (newPassword == null || newPassword == "") {
	errorMessage = "New Password cannot be left vacant!!";
	count++;
	form.newPassword.style.border = "2px solid red";
	flag = false;
}
if (newPassword.length < 8) {
	errorMessage = "Password must contain more than 8 letters!!";
	count++;
	form.newPassword.style.border = "2px solid red";
	flag = false;
}

if(count>1){
	errorMessage="Fill the details properly !!!!";

}
if(count==0){
	errorMessage="";
flag=true;
}
document.getElementById("errorMessage").innerHTML = errorMessage;
return flag;
}
</script>
<body>
	<nav class="navbar navbar-default navbar-fixed-top past ">
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

									<a href="#" onclick="return sendEmail();">Forgot password</a> <img
										src="/StartupMashup/resources/images/processing.gif"
										style="display: none;" id="loading_image"> <br> <br>
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
	<div class="container" style="margin-top: 150px;">
		<c:if test="${errorMsg != null }">
			<p class="center grey" style="font-size: 30px;">Failure!</p>
			<div class="col-sm-12">
				<h3 align="center" class="green">${errorMsg}</h3>
			</div>

		</c:if>
		<c:if test="${errorMsg == null }">
			<p class="center grey" style="font-size: 30px;">Forgot Password</p>
			<hr>
			<div class="col-sm-12">
				<h3 class="green">Details</h3>
			</div>

			<div id="new_candidate_form">
				<form:form method="post" modelAttribute="participantBean"
					action="/StartupMashup/update-password"
					onsubmit="return checkPassword()" name="forgotPasswordForm">
					<div class="row">
						<div class="col-sm-4"></div>
						<div class="col-sm-4">

							<div class="form-group">
								<input type="hidden" name="participantId"
									value="${participantBean.id}">
								<h3>
									<span id="errorMessage" style="color: red; font-size: 13px"></span>
								</h3>
								<input type="password" class="form-control input-grey"
									id="newPassword" name="newPassword"
									placeholder="Type New Password">
							</div>

							<div class="form-group">
								<input type="Password" class="form-control input-grey"
									id="reTypedNewPassword" name="reTypedNewPassword"
									placeholder="Retype New Password">

							</div>




						</div>
						<div class="col-sm-4"></div>
						<div class="col-sm-8">
							<br>

						</div>
						<div class="col-sm-4">
							<button type="submit"
								class="btn btn-default pull-right refer-button ">Submit</button>
						</div>


					</div>

				</form:form>
			</div>
		</c:if>

	</div>
	<br>


	</div>
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
				<div class="center">
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
						HQ<br> 12/25 East Patel Nagar, <br> New Delhi, 110008<br>
						thestartupmashup@myrefers.com
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
