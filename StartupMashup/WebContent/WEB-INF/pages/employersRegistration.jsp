<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html lang="en">
<head>
<title>Startup Mashup | Mashup Request Form</title>
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


<link rel="stylesheet" href="<c:url value='/resources/css/custom.css'/>" />


<script src="/StartupMashup/resources/js/employers_validations.js" ></script>


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
<style>
#new_candidate_form {
	display: block;
}

#registered_candidate_form {
	display: none;
}

.disabled {
	background-color: #ccc;
	color: #000;
}

.disabled:hover {
	background-color: #ccc;
	color: #000;
}
</style>
<script>
	$(function() {
		$('#confirmation_checkbox').click(function() {
			$('button.disabled').removeClass('disbaled');

		});
	});
</script>
</head>


<body>

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
	<div class="container" style="margin-top: 150px;">

		<p class="center grey" style="font-size: 30px;">Mashup Request
			Form</p>
		<hr>
		<div class="col-sm-12">
			<h3 class="green">Details</h3>
		</div>

		<div id="new_candidate_form">
			<%-- <form:form  role="form" name="sdd"> --%>
			<form:form role="form" action="/StartupMashup/save-employers-details"
				method="post" modelAttribute="employersBean" name="employersForm" onsubmit="return validateEmployersForm()">
				<span id="errorMsg" style="color: red; font-size: 13px">All fields are mandatory!!</span>
				<div class="row">
					<div class="col-sm-6">

						<div class="form-group">

							<input type="text" class="form-control input-grey"
								id="company_Name" placeholder="Company Name" name="companyName">
						</div>

						<div class="form-group">
							<input type="email" class="form-control input-grey" id="email"
								placeholder="Email-id" name="emailId">

						</div>




					</div>

					<div class="col-sm-6">


						<div class="form-group">


							<input type="text" class="form-control input-grey" id="last_name"
								placeholder="Contact Person" name="employerName">
						</div>
						<div class="form-group">

							<input type="text" class="form-control input-grey"
								id="contat_number" placeholder="Contact Number"
								name="phoneNumber">
						</div>



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
						class="btn btn-default pull-right refer-button ">Submit</button>
				</div>
			</form:form>
		</div>

	</div>



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
</body>
</html>
