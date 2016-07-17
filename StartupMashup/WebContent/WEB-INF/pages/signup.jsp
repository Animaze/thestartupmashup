<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <link rel="shortcut icon" type="image/png" href="/StartupMashup/resources/images/icon.png"/>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Startup Mashup | Sign-up Form</title>
</head>
<link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>

    <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"> 
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="<c:url value='/resources/js/subscriber.js'/>"></script>
<script src="<c:url value='/resources/js/login_validations.js'/>"></script>
<link rel="stylesheet" href="<c:url value='/resources/css/form.css'/>" />
	
<script type="text/javascript" src="/StartupMashup/resources/js/signup_validations.js" ></script>


	


<link rel="stylesheet" href="/StartupMashup/resources/css/custom.css" />

<script>
$(document).on('change', '.btn-file :file', function() {
  var input = $(this),
      numFiles = input.get(0).files ? input.get(0).files.length : 1,
      label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
  input.trigger('fileselect', [numFiles, label]);
});

$(document).ready( function() {
    $('.btn-file :file').on('fileselect', function(event, numFiles, label) {
        
        var input = $(this).parents('.input-group').find(':text'),
            log = numFiles > 1 ? numFiles + ' files selected' : label;
        
        if( input.length ) {
            input.val(log);
        } else {
            if( log ) alert(log);
        }
        
    });
});
</script>
<script>
$(document).on('change', '.btn-file :file', function() {
  var input = $(this),
      numFiles = input.get(0).files ? input.get(0).files.length : 1,
      label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
  input.trigger('fileselect', [numFiles, label]);
});

$(document).ready( function() {
    $('.btn-file :file').on('fileselect', function(event, numFiles, label) {
        
        var input = $(this).parents('.input-group').find(':text'),
            log = numFiles > 1 ? numFiles + ' files selected' : label;
        
        if( input.length ) {
            input.val(log);
        } else {
            if( log ) alert(log);
        }
        
    });
});
</script>

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
	$(function() {
		$('#confirmation_checkbox').click(function() {
			$('button.disabled').removeClass('disbaled');

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
	<div class="container" style="margin-top: 150px;">

		<p class="center grey" style="font-size: 30px;">Startup Mashup
			Sign-up Form</p>
		<hr>
		<div class="col-sm-12">
			<h3 class="green">Your Details</h3>
		</div>
		
		<span id="errorMsgSignUp" style="color:red; font-size: 13px">All Fields are MANDATORY (except Last Name).</span>
		
		<div id="new_candidate_form">
			<form:form role="form"  action="/StartupMashup/save-signup-details" method="post" 
			modelAttribute="participantBean" name="Participant" enctype="multipart/form-data" 
			onSubmit="return validateFormParticipant()">
				<div class="row">
					<div class="col-sm-6">

						<div class="form-group">

							<input type="text" class="form-control input-grey"
								id="first_Name" name="firstName" placeholder="First Name">
						</div>
						
						<div class="form-group">

							<input type="text" class="form-control input-grey" id="username"
								name="userName" placeholder="User Name">
						</div>
						<div class="form-group">
							<input type="email" class="form-control input-grey" id="email"
								name="emailId" placeholder="Email-id">

						</div>
						<div class="form-group">

							<input type="text" class="form-control input-grey"
								id="company_Name" name="companyName" placeholder="Current Company Name">
						</div>

						<div class="form-group">

							<input type="text" class="form-control input-grey"
								id="designation" name="designation" placeholder="Current Designation">
						</div>
						
						<div class="input-group">
									<span class="input-group-btn">
										<span class="btn btn-primary btn-file">
											Attach Resume <input type="file" name="resumeFile"><span class="glyphicon glyphicon-paperclip"></span>
										</span>
										</span>
								<input type="text" class="form-control input-grey" readonly>
						</div>

					</div>

					<div class="col-sm-6">


						<div class="form-group">


							<input type="text" class="form-control input-grey" id="last_name"
								name="lastName" placeholder="Last Name">
						</div>
						<div class="form-group">

							<input type="password" class="form-control input-grey"
								id="password" name="password" placeholder="Password">
						</div>
						<div class="form-group">

							<input type="text" class="form-control input-grey"
							onkeydown="return ( event.ctrlKey || event.altKey 
										|| (47 < event.keyCode && event.keyCode < 58 && event.shiftKey==false)  
					                    || (95 < event.keyCode && event.keyCode < 106) 
					                    || (event.keyCode==8) || (event.keyCode==9) 
					                    || (event.keyCode > 34 && event.keyCode < 40)  
					                    || (event.keyCode==46) )"
								id="Phone_Number" name="phoneNumber" placeholder="10 Digit Phone Number">
						</div>

						<div class="form-group">

							<input type="text" class="form-control input-grey" 
							onkeydown="return ( event.ctrlKey || event.altKey 
										|| (47 < event.keyCode && event.keyCode < 58 && event.shiftKey==false)  
					                    || (95 < event.keyCode && event.keyCode < 106) 
					                    || (event.keyCode==8) || (event.keyCode==9) 
					                    || (event.keyCode > 34 && event.keyCode < 40)  
					                    || (event.keyCode==46) )"
							id="current_package" name="ctc" placeholder="Current Package">
						</div>

						<div class="form-group">

							<input type="text" class="form-control input-grey" id="college"
								name="collegeName" placeholder="College Name">
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
					<input type="submit"
						class="btn btn-default pull-right refer-button" value="Submit">
				</div>
			</form:form>
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