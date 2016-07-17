<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employers</title>
</head>
<body>
click  <a href="/StartupMashup/employers-registration">here</a>
</body>
</html> -->
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Startup Mashup | Employers</title>
  <meta charset="utf-8">
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
    <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
  
  <link rel="stylesheet" href="<c:url value='/resources/css/custom.css'/>">
<link rel="stylesheet" href="<c:url value='/resources/css/commonheader.css'/>">
 


 
<style>
body{
	color:#7c8682;
}
.header_banner{
background: url('/StartupMashup/resources/images/banner.png') center center; 
-webkit-background-size: cover;
-moz-background-size: cover;
-o-background-size: cover;
background-size: cover;
margin:0;
padding:0;
font-color:white;
font-weight:bold;
font-family: 'Roboto', sans-serif;
padding-top:100px;
min-height: 700px;
margin-top:0px;
	
}

.base_container{
	
padding:20px 90px;
}
.banner_image{
background: url('/StartupMashup/resources/images/employers_banner_left.png') no-repeat center center; 
-webkit-background-size: cover;
-moz-background-size: cover;
-o-background-size: cover;
background-size: cover;

}
.employer_text_container{



}
@media only screen and (max-width: 767px) {

   .home_text {
      font-size: 3em;
   }
   

} 

@media only screen and (max-width: 320px) {

   .home_text {
      font-size: 1.7em;
   }
   
}   
.preposition{
	
	padding:5%;
	background: #ebf1f3;
	height: auto;
	text-align: center;
	border-top-style: solid;
    border-top-width: medium;
	border-top-color: #808384;
}  
.mashup_request{
	border: 4px solid #fe5811;
	border-radius: 2px;
	background-color:#fe581c;
	color:#ffffff;
	font-weight: bold;
	height:40px;
	width: 250px;
	padding:5px;
	display: inline;
	


}
.mashup_request:hover{
	border: 4px solid #fe581c;
	background-color:#fe5811;
	color:#ffffff;
	font-weight: bold;
	height:40px;
	width: 250px;
	padding:5px;
	display: inline;
	box-shadow: 1px 1px 1px #fe5811;
	
	

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

</style>

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

<div class="container-fluid header_banner">


		<div class="header">
		
			<div class="row"><br><br><br><br><br><br><br><br>	<p class="home_text">Hackathons- The new <br>age recruiting tool</p>	
					
					
			</div>
		<div class="myrefers_button" data-toggle="modal" data-target="#registration_form" style="display:inline-block;"><a href="/StartupMashup/employers-registration" style="text-decoration:none;">Request For a Mashup</a></div>
		
		</div>
</div>
<div class="container-fluid base_container">
	
	<div class="row">
		<div class="col-sm-6">
			<div class="banner_image">
				<img src="<c:url value='/resources/images/employers_banner_left.png'/>" class="img-responsive" style="width:120%;"></img>-
			</div>
		
		</div>
		<div class="col-sm-6">
			<div class="employer_text_container" style="text-align:left;">
				
				<p class="employer_text_head" style="font-size:bold;">Hire Better candidate, faster</p>
					<ul class="employer_text">
						<li>Target your ideal candidate</li>
						<li>Reduce Cost per hire by 75%</li>
						<li>Reduce time to hire by 90%</li>
						
					</ul>
			 
			</div>
		</div>
	</div>
</div>



	<!---div for common Question starts here-->
<div class="container-fluid common_questions">
	<div class="row">
	
	<h1>COMMON QUESTION</h1>
	
	</div>
	<div class="row">
		<div class="col-sm-4">
			<div class="center" style="text-align:left;">
							<p style="font-weight:bold;"> Why Startup Mashup?	</p>
						<p>We are powered by MyRefers 
							a peer to peer job referral marketplace. We have the expertise to attract, evaluate
				and engage tech professionals for you. It's free to request for a hackathon.</p>
			</div>
		</div>
		<div class="col-sm-4">
			<div class="center" style="text-align:left;">
				<p style="font-weight:bold;">How do you control quality?	 	</p>
				<p>Our team reviews each and every single registrant and we weed out those that don't 
				match your requirements. We'll study, test and sort the best candidates for you so 
				you don't waste time and money dealing with hundreds of unfit applications like you
				might on other jobs boards or professional networks.</p>
			</div>
		</div>
		<div class="col-sm-4">
			<div class="center" style="text-align:left;">
								<p style="font-weight:bold;"> How do you promote my hackathon?</p>
				<p>Our team can promote your hackathon through our network of affiliates,
				social media and other online channels.Also, our Premium Plan offers dedicated talent hunting and 
				account management which ensures you find those unicorns you hear so much about.</p>
			</div>
		</div>
	</div>
</div>



	<!---div for customers starts here----->
	<div class="container-fluid base_container">
	
	<div class="row">
		<h2>OUR AWESOME CUSTOMER</h2>
		<div class="col-sm-6">
			<div class="center">
				<div class="dp">
					<div class="row">
							<div class="col-sm-6">
								<img src="<c:url value='/resources/images/chetna.png'/>" style="width:50%;"></img>
							</div>
							<div class="col-sm-6" style="text-align:left;font-weight:bold;"><br><br>
							<span style="color:#8cc4da;font-weight:bold;">Chetna Gogia</span><br><span>Associate Director/ Head HR PayU</span>
							</div>
					</div>
				</div>
				<div class="dp">
					<p style="text-align:left;">The unique format got us to meet some super cool talent which we don’t find anywhere either on job boards or through the consultants. We hired Product Managers, Web Developers, UX, UI and Mobile Developers through the event.</p>
				</div>
				
			</div>
		
		</div>
		<div class="col-sm-6">
			<div class="center">
				<div class="dp">
					<div class="row">
							<div class="col-sm-6">
								<img src="<c:url value='/resources/images/garima.png'/>" style="width:50%;"></img>
							</div>
							<div class="col-sm-6" style="text-align:left;font-weight:bold;"><br><br>
							<span style="color:#8cc4da;">Garima Gulati Bhutani</span><br><span>Enabling people and Business HR MobiKwik</span>
							</div>
					</div>
				</div>
				<div class="dp">
					<p style="text-align:left;">The startupmashup event's setting was different and the whole idea of exciting passive candidates by posing talk business problems in front of them was lucrative enough for us to join in.We got good success in the form of candidate closures across product, web and mobile development and data science; and we got a good platform for branding MobiKwik in the right candidate community. In current crazy recruiting times we need innovative platforms like this. MyRefers can take me as a default participant for all such future events :)</p>
				</div>
				
			</div>
		</div>
	</div>
</div>

<div class="container-fluid preposition">

	<div class="row">
		<h3 style="color:#363636; display:inline-block;">A ONE LINE PREPOSITION GOES HERE</h3>
		<div class="refer_button_container" style= "display:inline-block;"><div class="mashup_request"><a href="/StartupMashup/employers-registration" style="text-decoration:none;">Request For a Mashup</a></div></div>
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
	
	<div class="row"><br>
		<p>&copy; 2015 The Startup Mashup</p>
	</div>
</div>
</body>
</html>
