<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Startup Mashup | Contact </title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="shortcut icon" type="image/png" href="/StartupMashup/resources/images/icon.png"/>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"> 
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="<c:url value='/resources/js/subscriber.js'/>"></script>
<script src="<c:url value='/resources/js/login_validations.js'/>"></script>
<link rel="stylesheet" href="<c:url value='/resources/css/form.css'/>" />
	<script src="<c:url value='/resources/js/contact_validation.js'/>"></script>


<link rel="stylesheet" href="<c:url value='/resources/css/custom.css'/>" />
<link rel="stylesheet" href="<c:url value='/resources/css/form.css'/>" />
</head>
<style>
.about_text_hello {
	font-family: Brush Script MT, cursive;
	font-size: 100px;
	font-style: normal;
	font-variant: normal;
	font-weight: 500;
	line-height: 26.4px;
	color: #fff;
	text-align: center;
	margin: 0 auto;
}

.map_container {
	background: url('/StartupMashup/resources/images/contact-us.png')
		no-repeat center center;
	-webkit-background-size: cover;
	-moz-background-size: contain;
	-o-background-size: cover;
	background-size: cover;
	margin-top: 0px;
	padding: 100px 5%;
	font-color: white;
	font-weight: bold;
	font-family: 'Roboto', sans-serif;
}
</style>

<body>
	<nav class="navbar navbar-default navbar-fixed-top past ">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar" style="background-color:#fff;"></span>
        <span class="icon-bar" style="background-color:#fff;"></span>
        <span class="icon-bar" style="background-color:#fff;"></span>                        
      </button>
       <a class="navbar-brand" href="/StartupMashup"> <img src="/StartupMashup/resources/images/logo.png" class="img-responsive" style="margin-top:-15px;" ></img></a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      
      <ul class="nav navbar-nav navbar-right">
        <li class=""><a href="/StartupMashup/about">About</a></li>
        <li class=""><a href="/StartupMashup/employers">Employers</a></li>
        <li class=""><a href="/StartupMashup/contact-us">Contact Us</a></li>
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
							onsubmit="return authoriseUser()">
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
	</div>

	

	<div class="container-fluid map_container">
		<div class="row">

			<div class="col-sm-6 text ">
				<p style="font-size: 4vw;">GET IN TOUCH</p>
				<p style="font-size: 3.4vw;">Have a question ? Do not hesitate
					to contact us.</p>

				<p style="font-size: 15px;">
					12/25 East Patel Nagar, <br> New Delhi, 110008<br>
					thestartupmashup@myrefers.com
				</p>

			</div>
			<div class="col-sm-6">
				<form:form role="form" action="/StartupMashup/save-contact-details"
					method="post" modelAttribute="contactBean" name="contactForm"
					onSubmit="return validateForm()">

					<h3 class="text">Your Details</h3>
					<c:if test="${sessionScope.status == 'loggedIn'}">
					<div class="form-group">
					
						<span id="errorMsg" style="color: red; font-size: 13px">All
							fields are mandatory.</span> <input type="text"
							class="form-control input-grey" id="Name" name="name" 
							placeholder="Name" value="${participantBean.firstName}">
					</div>
					<div class="form-group">

						<input type="text" class="form-control input-grey"
							onkeydown="return ( event.ctrlKey || event.altKey 
										|| (47 < event.keyCode && event.keyCode < 58 && event.shiftKey==false)  
					                    || (95 < event.keyCode && event.keyCode < 106) 
					                    || (event.keyCode==8) || (event.keyCode==9) 
					                    || (event.keyCode > 34 && event.keyCode < 40)  
					                    || (event.keyCode==46) )"
							name="phoneNumber" id="Phone_Number" placeholder="Phone Number" value="${participantBean.phoneNumber}">
					</div>
					<div class="form-group">
						<input type="email" class="form-control input-grey" id="email"
							name="emailId" placeholder="Email-id" value="${participantBean.emailId}">

					</div>

					<div class="form-group">

						<textarea class="form-control input-grey" rows="5" id="comment"
							name="query" placeholder="Message"></textarea>


					</div>



					<input type="checkbox" id="confirmation_checkbox" name="terms" />
					<label for="confirmation_checkbox"> <span></span>


						<p style="font-size: 18px; font-weight: bold; color: #d1cece;">
							I agree with the <a href class="term_link green">Terms</a> and <a
								href class="term_link green">Condition and Privacy policy</a>
						</p>


						<button type="submit"
							class="btn btn-default pull-right refer-button">Submit</button>
						</c:if>	
	
	<c:if test="${sessionScope.status == 'loggedOut'}">
					<div class="form-group">
					
						<span id="errorMsg" style="color: red; font-size: 13px">All
							fields are mandatory.</span> <input type="text"
							class="form-control input-grey" id="Name" name="name" 
							placeholder="Name">
					</div>
					<div class="form-group">

						<input type="text" class="form-control input-grey"
							onkeydown="return ( event.ctrlKey || event.altKey 
										|| (47 < event.keyCode && event.keyCode < 58 && event.shiftKey==false)  
					                    || (95 < event.keyCode && event.keyCode < 106) 
					                    || (event.keyCode==8) || (event.keyCode==9) 
					                    || (event.keyCode > 34 && event.keyCode < 40)  
					                    || (event.keyCode==46) )"
							name="phoneNumber" id="Phone_Number" placeholder="Phone Number">
					</div>
					<div class="form-group">
						<input type="email" class="form-control input-grey" id="email"
							name="emailId" placeholder="Email-id">

					</div>

					<div class="form-group">

						<textarea class="form-control input-grey" rows="5" id="comment"
							name="query" placeholder="Message"></textarea>


					</div>



					<input type="checkbox" id="confirmation_checkbox" name="terms" />
					<label for="confirmation_checkbox"> <span></span>


						<p style="font-size: 18px; font-weight: bold; color: #d1cece;">
										By signing in you agree to our pedigree &nbsp My Refers<a
											href="https://drive.google.com/file/d/0B5TvdcdlnEjqS295TlkxSUt0aUNmcFlWcFlCUUt6QzlkSEdB/view?usp=sharing"
											class="term_link green" target="_blank"> Terms and
											Condition</a> and <a href="/StartupMashup/privacy-policy"
											class="term_link green" target="_blank">Privacy Policy</a>
									</p>


						<button type="submit"
							class="btn btn-default pull-right refer-button">Submit</button>
						</c:if>	
	
	
				</form:form>

			</div>
		</div>

		<br> <br> <br>


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
						target="_blank">My Refers</a><br> <a href="/StartupMashup/about">Team</a><br>
					<a href="/StartupMashup/press">Press</a><br>

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
				<p style="color:#838181;">HQ<br>
				12/25 East Patel Nagar, <br>
                  New Delhi, 110008<br>
				thestartupmashup@myrefers.com</p>
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
