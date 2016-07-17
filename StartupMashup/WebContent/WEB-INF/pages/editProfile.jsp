<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">




<html lang="en">
<head>
  <title>Startup Mashup | Edit Profile</title>
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
	     <script src="/StartupMashup/resources/js/edit_profile_validations.js"></script>
    

  <link rel="stylesheet" href="<c:url value='/resources/css/form.css'/>">
 <link rel="stylesheet" href="<c:url value='/resources/css/custom.css'/>">
 <script>
$(function(){
  $('#new_candidate').click(function(){
     $('#new_candidate_form').fadeIn("slow");
     $('#registered_candidate_form').hide(); 
  });
});
$(function(){
  $('#registered_candidate').click(function(){
     $('#new_candidate_form').hide();
     $('#registered_candidate_form').fadeIn("slow"); 
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
 <style>
#new_candidate_form{
display:block;
}
#registered_candidate_form{
display:none;
}
.disabled{
background-color:#ccc;
color:#000;
}
.disabled:hover{
background-color:#ccc;
color:#000;
}
.btn-file {
  position: relative;
  overflow: hidden;
}
.btn-file input[type=file] {
  position: absolute;
  top: 0;
  right: 0;
  min-width: 100%;
  min-height: 100%;
  font-size: 100px;
  text-align: right;
  filter: alpha(opacity=0);
  opacity: 0;
  background: red;
  cursor: inherit;
  display: block;
}
input[readonly] {
  background-color: white !important;
  cursor: text !important;
}
</style>
<script>
$(function(){
  $('#confirmation_checkbox').click(function(){
     $('button.disabled').removeClass('disbaled');
    
  });
});

</script>
</head>


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
						<form:form role="form" name="loginForm"
							onsubmit="return authoriseUser()" method="get">
							<br>
							<div class="row">
								<div class="col-sm-12">
									<br> <br> <br>
									<div class="form-group">
										<span id="errorMsg" style="color: red; font-size: 13px"></span>

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
										class="btn btn-default pull-right refer-button"
										style="width: 150px;">Submit</button>
								</div>
							</div>
						</form:form>
					</div>


				</div>
				<div class="modal-footer"></div>
			</div>

		</div>
	</div>

<div class="container" style="margin-top:150px;">
	 
  <p class="center grey" style="font-size:30px;">Edit Profile</p>
<hr>
<span id="errorMsg" style="color: red; font-size: 13px">All
					fields are mandatory!!</span>
	<div class="col-sm-12">
		<h3 class="green">Your Details</h3>
	</div>
	
<div id="new_candidate_form">
		<form:form  action="/StartupMashup/save-profile" method="post"
				modelAttribute="participantBean" name="EditProfile"
				enctype="multipart/form-data" role="form" onclick="return validateEditProfile()">
			<div class="row">
				<div class="col-sm-6">
						
								<div class="form-group">
	    
									<input type="text" class="form-control input-grey" name="firstName" value="${participantBean.firstName}" id="first_Name" placeholder="First Name">
								</div>
								<div class="form-group">
	    
									<input type="text" class="form-control input-grey" name="userName" readonly="readonly" value="${participantBean.userName}" id="username" placeholder="User Name" readonly>
								</div>
	
								<div class="form-group">
									<input type="email" class="form-control input-grey" name="emailId" value="${participantBean.emailId}" id="email" placeholder="Email-id">
      
								</div>
								<div class="form-group">
	    
									<input type="text" class="form-control input-grey" name="companyName" value="${participantBean.companyName}" id="company_Name" placeholder="Current company Name">
								</div>
								
								<div class="form-group">
	    
									<input type="text" class="form-control input-grey" name="designation" value="${participantBean.designation}" id="designation" placeholder="Current Designation">
								</div>
								
								<div class="input-group">
									<span class="input-group-btn">
									<input type="hidden" name="resume" value="${participantBean.resume}">
										<span class="btn btn-primary btn-file">
											Attach Resume <input type="file" name="resumeFile" ><span class="glyphicon glyphicon-paperclip"></span>
										</span>
										</span>
								<input type="text" class="form-control input-grey" readonly>
								</div>
    
				</div>
 
				<div class="col-sm-6">
  
								
								<div class="form-group">
	
     
									<input type="text" class="form-control input-grey" name="lastName" value="${participantBean.lastName}" id="last_name" placeholder="Last Name">
								</div>
								<div class="form-group">
								<div class="form-group">
	    
									<input type="password" class="form-control input-grey" name="newPassword"  id="password" placeholder="New Password">
								<input type="hidden"  name="password" value="${participantBean.password}">
								</div>
	    
									<input type="text" class="form-control input-grey" name="phoneNumber" value="${participantBean.phoneNumber}" id="Phone_Number" placeholder="Phone Number">
								</div>
								<div class="form-group">
								
								<div class="form-group">
	    
									<input type="text" class="form-control input-grey" name="ctc" value="${participantBean.ctc}" id="current_package" placeholder="Current Package">
								</div>
      
									<div class="form-group">
	    
									<input type="text" class="form-control input-grey"  name="collegeName" value="${participantBean.collegeName}" id="college" placeholder="College Name">
								</div>
								
				</div>
	 
	</div>
	<script type="text/javascript">

 

</script>
		
				<div class="col-sm-4">
						<button type="submit" class="btn btn-default pull-right refer-button ">Submit</button>
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
<script>
$('input[type=file]').bootstrapFileInput();
$('.file-inputs').bootstrapFileInput();

</script>
</html>
