<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page import="com.startupmashup.bean.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html lang="en">
<head>
  <title>Startup Mashup | Mashup List</title>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="shortcut icon" type="image/png" href="/StartupMashup/resources/images/icon.png"/>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
 <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script> 
    
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"> 
   <link rel="stylesheet" href="<c:url value='/resources/css/custom.css'/>">
     
<link rel="stylesheet" href="<c:url value='/resources/css/commonheader.css'/>">
  <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
  <link rel="stylesheet" href="<c:url value='/resources/css/form.css'/>">
  <script src="<c:url value='/resources/js/login_validations.js'/>"></script>
   <script src="/StartupMashup/resources/js/subscriber.js">
</script>
 <script
	src="<c:url value='/resources/js/refer_details_validations.js'/>"></script>
  <style>
   body{
   background-color:#eee;
  } 
  .gallery_button{
	color:#fff;
	border-color:#fff;
	width:20%;
	padding:2px;
	margin:5px;
	display:inline-block;
	text-align:center;
	border-radius:2px;
	
  }
  .gallery_button:hover{
	width:20%;
	background-color:#fff;
	color:#f44;
	display:inline-block;
	text-align:center;
	border-radius:2px;
	
  }
  .gallery_footer{
  background-color:#F44;
  border-top:2px solid #ccc;

  
  
  }
  .gallery_header{
  background-color:#000;
  opacity:0.2;
  text-align:left;
  color:#fff;
  font-size:15px;
  position:absolute;
  top:0;
  left:0;
  width:100%;
  height:20%;
  padding:5px;
  }
  .gallery{
  position:relative;
  margin-top:20px;
  box-shadow:2px 0px #ccc;
  
  
  }
  .gallery_button_old{
	color:#fff;
	border-color:#fff;
	width:30%;
	padding:2px;
	margin:5px;
	display:inline-block;
	
	border-radius:2px;
	text-align:center;
    
  
  }

  .gallery_footer_old{
  
  background-color:#e3beoo;
  border-top:2px solid #ccc;
  
  }
  </style>

<script type="text/javascript">
var hackathonId="0";
function register(thisButton){
	
	var hackathonId=$(thisButton).attr('id').split('_')[1];
	
	//window.location.href="/StartupMashup/view-startup-details?hackathonId="+hackathonId;
	window.open(
			"/StartupMashup/view-startup-details?hackathonId="+hackathonId,
			  "_blank" 
			);
	
};



function saveHackathonId(thisButton){
	hackathonId=$(thisButton).attr('id').split('_')[1];

	document.getElementById("referredHackathonId").value=hackathonId;

};

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
	<div class="container" style="margin-top: 120px;">
		<h2>Mashup Details</h2>


		<form>

			<c:forEach items="${listOfHackathons}" var="hackathon">
				<c:if test="${hackathon.status== 'live'}">
					<div class="col-md-4" style="margin-top: 20px;">
						<div class="thumbnail gallery">
						<div style="color:#fff;position:absolute;top:0;z-index:3;">${hackathon.name}</div>
							<div class="gallery_header"></div>
							<img
								src="/StartupMashup/resources/hackathonImageUpload/${hackathon.name }.png"
								alt="Pulpit Rock" class="img-responsive">
							<div class="gallery_footer">
								<div class="gallery_button" data-toggle="modal"
									data-target="#referal_form">
									<a id="ref_${hackathon.id}" href="#"
										onclick="saveHackathonId(this);">Refer</a>
								</div>
								<div class="gallery_button" style="float: right;">
									<a id="reg_${hackathon.id}" href="#" onclick="register(this);">Apply</a>
								</div>
							</div>
						</div>

					</div>
				</c:if>
				<c:if test="${hackathon.status != 'live'}">
					<div class="col-md-4" style="margin-top: 20px;">
						<div class="thumbnail gallery">
						<div style="color:#fff;position:absolute;top:0;z-index:3;">${hackathon.name}</div>
							<div class="gallery_header"></div>
							<img
								src="/StartupMashup/resources/hackathonImageUpload/${hackathon.name}.png"
								alt="Pulpit Rock" class="img-responsive">
							<div class="gallery_footer_old"  style="background-color:#e3be00;">

								<div class="gallery_button_old" >Closed</div>
								<div class="gallery_button" style="float: right; width: 30%;">
									<a id="reg_${hackathon.id}" href="#" onclick="register(this);">See
										Details</a>
								</div>
							</div>
						</div>

					</div>
				</c:if>
			</c:forEach>


		</form>


	</div>
		<div id="referal_form" class="modal fade" role="dialog">
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

				
					
					<form:form action="/StartupMashup/save-refer-details?flag=stay"
						method="post" modelAttribute="referDetailsBean"
						onSubmit="return validateReferralForm()" name="ReferDetails"
						role="form">
						
						<div class="row">
							<div class="col-sm-6">
								<span id="errorMsg" style="color: red; font-size: 13px">All
									fields are mandatory.</span>
								<h3 class="green">Your Details</h3>
								<input type="hidden" id="referredHackathonId" name="referredHackathonId"
									value="${hackathonId}">
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
<div class="container-fluid credit"><div class="row"><br>
		<p>&copy; 2015 The Startup Mashup</p>
	</div>
	</div>
</body>
</html>