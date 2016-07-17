<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
<head>
  <title>Startup Mashup | Profiles</title>
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
<link rel="stylesheet" href="<c:url value='/resources/css/commonheader.css'/>" />
  <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>


<style>
body{
	color:#7c8682;
}
.header_banner{
background: url('/StartupMashup/resources/images/profilebg.jpg') center center; 
-webkit-background-size: cover;
-moz-background-size: cover;
-o-background-size: cover;
background-size: cover;
margin:0;
padding:0;
font-color:white;
font-weight:bold;
font-family: 'Roboto', sans-serif;
height: 400px;

}



.profile_button:hover{
	border: 4px solid #e4de32;
	background-color:#e4de32;
	color:#ffffff;
	font-weight: bold;
	height:50px;
	width: 350px;
	padding:5px;
	display: inline;
	box-shadow: 1px 1px 1px #e4de32;
	border-radius:4px;
	
	

}
.profile_button{
	border: 4px solid #e4de32;
	background-color:#e4de32;
	color:#ffffff;
	font-weight: bold;
	height:50px;
	width: 300px;
	padding:5px;
	display: inline;
	border-radius:4px;

	
	

}
.base_container{
color:#000;
font-family: 'Roboto', sans-serif;
margin:0 auto;
}
.mashup_footer{
	background-color:#ff4444;
	position:absolute;
	bottom:0;
	height:15%;
	width:100%;

}

.mashup_header{
  background-color:#000;
  opacity:0.2;
  color:#fff;
  font-size:15px;
  position:absolute;
  top:0;
  width:100%;
  height:20%;
  padding:5px;
  }


.mashup_button{
	color:#fff;
	margin:auto 0;
	text-align:center;
	
	
}
.mashup{
	position:relative;
	margin-bottom:35px;
	color:#2d262a;
	
}
.mashup_registered{
	margin:0 auto;
	background-color:white;
	/*padding:40px 90px;*/
	clear:both;
	margin:0 auto;
	color:#2d262a;
	

}
hr {
   
    height:2px;
    border: none;
    color: #ccc; /* old IE */
    background-color: #ccc; /* Modern Browsers */
    
}
@media (min-width: 21px) {
			
			.mashup_footer{
			 font-size:7px;
			
			}			
}

@media all and (min-width: 341px) {
			 .mashup_footer{
			 font-size:18px;
			
			}
		
}

@media all and (min-width: 769px) {
			.mashup_footer{
			 font-size:20px;
			
			}
		
}
@media all and (min-width: 1025px) {
			.mashup_footer{
			 font-size:20px;
			
			}
		
}
</style>
<script type="text/javascript">
function getHackathon(clickedButton) {
	var buttonId = $(clickedButton).attr('id');
	var hackathonId = buttonId.split('_')[1];
	window.location.href = "/StartupMashup/view-startup-details?hackathonId="
			+ hackathonId;
}
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
<div class="container-fluid header_banner">


		
</div>
<div class="container base_container">
	
	<div class="row">
		<h2>Hi ${participantBean.firstName} ${participantBean.lastName}!!!!</h2>
	</div>
	<div class="refer_button_container" style= "text-align:right;"><div class="profile_button"><a href="/StartupMashup/edit-profile" style="text-decoration:none;">Edit Profile</a></div></div>
	<div class="row" style="font-size:20px;">
	 
			<div class="row">
				<div class="col-sm-4">Full Name</div>
				<div class="col-sm-4"><b>${participantBean.firstName} &nbsp ${participantBean.lastName}</b></div><br><br>
			</div>
			
			<div class="row">
				<div class="col-sm-4">Email-id</div>
				<div class="col-sm-4"><b>${participantBean.emailId}</b></div><br><br>
			</div>
			
			<div class="row">
				<div class="col-sm-4">User Name</div>
				<div class="col-sm-4"><b>${participantBean.userName}</b></div><br><br>
			</div>
			
			<div class="row">
				<div class="col-sm-4">Contact Number</div>
				<div class="col-sm-4"><b>${participantBean.phoneNumber}</b></div><br><br>
			</div>
			
			<div class="row">
				<div class="col-sm-4">Current Package</div>
				<div class="col-sm-4"><b>${participantBean.ctc}</b></div><br><br>
			</div>
			
			<div class="row">
				<div class="col-sm-4">Current Company</div>
				<div class="col-sm-4"><b>${participantBean.companyName}</b></div><br><br>
			</div>
			
			<div class="row">
				<div class="col-sm-4">Resume</div>
				<div class="col-sm-4"><a href="${participantBean.resume}" target="_blank">CLICK TO OPEN</a></div><br><br>
			</div>
			
	</div>
	
	
</div>



	<!---div for common Question starts here-->
<div class="container mashup_registered">
	
	  <hr> 
		<h2>Registered Mashup</h2>
		
<div class="row">
    
	<c:forEach items="${listOfRegisteredHackathons}" var="hackathon" varStatus="counter">
	
    <div class="col-md-6" style="margin-top:20px;">
      <div class="mashup">
	 
				 <div style="color:#fff;position:absolute;top:0;z-index:3;"><h2>${hackathon.name}</h2></div>
				<div class="mashup_header">
					
				</div>
	 
        <img src="/StartupMashup/resources/images/2.jpg" alt="" class="img-responsive">
				<div class="mashup_footer">
						<div class="mashup_button"><a href="#" id="btn_${hackathon.id}"
									onclick="getHackathon(this)">View Details</a></div>
						
				</div>
	  </div>

    </div>
    <c:if test="${counter.count %2==0}">
	<div class="row"></div>
	</c:if>
	  </c:forEach>
	  
	  
	  
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
<div class="container-fluid credit">
	
	<div class="row"><br><div class="col-sm-1"></div>
		<p>&copy; 2015 The Startup Mashup</p>
	</div>
</div>
</body>
</html>
