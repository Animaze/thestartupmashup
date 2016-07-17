    
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <!DOCTYPE html>
<html lang="en">
<head>
  <title>Startup Mashup | Press Page</title>
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


   
<link rel="stylesheet" href="<c:url value='/resources/css/custom.css'/>">
<link rel="stylesheet" href="<c:url value='/resources/css/commonheader.css'/>">
 
  <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
 



 
<style>
body{
	color:#7c8682;
}
.press_title{

	font-weight:bold;
	color:#909090;
	font-size:25px;


}

.press_title:hover{

	font-weight:bold;
	color:#4fd38d;
	font-size:25px;


}
.header_banner{
background: url('/StartupMashup/resources/images/press.png') center center; 
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
min-height: 800px;
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

.preposition{
	
	padding:2%;
	background: #ffffff;
	text-align: center;
	
}  
.mashup_request{
	border: 4px solid #e74c3c;
	border-radius: 8px;
	background-color:#e74c3c;
	color:#ffffff;
	font-weight: bold;
	height:40px;
	width: 250px;
	padding:5px;
	display: inline;



}
.mashup_request:hover{
	border: 4px solid #e74c3c;
	background-color:#e74c3c;
	color:#ffffff;
	font-weight: bold;
	height:40px;
	width: 250px;
	padding:5px;
	display: inline;
	box-shadow: 1px 1px 1px #fe5811;
	
	

}.mashup_request{
	border: 4px solid #e74c3c;
	border-radius: 8px;
	background-color:#e74c3c;
	color:#ffffff;
	font-weight: bold;
	height:40px;
	width: 250px;
	padding:5px;
	display: inline;



}
.mashup_request:hover{
	border: 4px solid #e74c3c;
	background-color:#e74c3c;
	color:#ffffff;
	font-weight: bold;
	height:40px;
	width: 250px;
	padding:5px;
	display: inline;
	box-shadow: 1px 1px 1px #fe5811;
	
	

}
@media only screen and (max-width: 1067px) {

   .home_text {
      font-size: 3em;
   }
    .press_2{
   font-size:5vw;
   }

} 
@media only screen and (max-width: 767px) {

   .home_text {
      font-size: 3em;
   }
    .press_2{
   font-size:5vw;
   }

} 

@media only screen and (max-width: 320px) {

   .home_text {
      font-size: 1.7em;
   }
   .press_2{
   font-size: 4vw;
   padding:auto 1%;
   }
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
	</div>





<div class="container-fluid header_banner">


		<div class="header">
		
			<div class="row"><br><br><br><br><br><br><br><br>	<p class="home_text">Pressroom</p>	
			<p class="text" style="font-size: 1.9vw;font-size:20px; margin:0 20%;">
			Working on a startup mashup story? Our press team loves working  with journalists around the world to share compelling, unique stories
			</p>		
					
			</div>
		
		
		</div>
</div>



	<!---div for common Question starts here-->
<div class="container-fluid common_questions">
	<div class="row">
	
	<h1 class="center">PRESS COVERAGE</h1><br>
	
	</div>
	<div class="row">
		<div class="col-sm-4">
			<div class="center" style="text-align:left;border-top:2px solid #dad9d9;">
							<img alt="Brand" src="/StartupMashup/resources/pressImages/financialexpress.png"/>
							<p style="font-weight:bold;color:#4fd38d;font-size:25px;"> <a target="_blank" class="press_title" href="http://www.financialexpress.com/article/industry/jobs/startup-mashup-the-solution-provider/60787/">Startup Mashup: The solution provider</a></p>
						<p class="black" style="font-size:18px;">28th March 2015</p>
			</div>
		</div>
	
		<div class="col-sm-4">
			<div class="center" style="text-align:left;border-top:2px solid #dad9d9;">
							<img alt="Brand" src="/StartupMashup/resources/pressImages/inspire2rise.png"/>
							<p style="font-weight:bold;color:#4fd38d;font-size:25px;"><a target="_blank" class="press_title" href="http://www.inspire2rise.com/the-startup-mashup-2015.html">The Startup Mashup 2015 goes live in 3 days</a></p>
						<p class="black" style="font-size:18px;">28th March 2015</p>
			</div>
		</div>
		<div class="col-sm-4">
			<div class="center" style="text-align:left;border-top:2px solid #dad9d9;">
							<img alt="Brand" src="/StartupMashup/resources/pressImages/bloggersideas.png"/>
							<p style="font-weight:bold;color:#4fd38d;font-size:25px;"><a target="blank" class="press_title" href="http://www.bloggersideas.com/first-edition-of-the-startup-mashup-in-iit-delhi-announced/">First Edition of The Startup Mashup in IIT Delhi announced</a> </p>
						<p class="black" style="font-size:18px;">28th March 2015</p>
			</div>
		</div>
	</div>
</div>



	<!---div for customers starts here----->
	<div class="container-fluid base_container">
	
	<div class="row">
		<h2 class="center">Press Kit</h2>
		
		
		Media affair representatives
		<p style="color:#5b5b5b;font-size:18px;">Archana: +91-9811838332</p>
		<p style="color:#e74c3c;font-size:18px;">press@thestartupmashup.com</p>
		
		
	
		
	</div>
</div>

<div class="container-fluid preposition">

	<div class="row">
		<h3 style="color:#686868; display:inline-block;">Need a hi-res version of the company logo and other assets?</h3>
		<div class="refer_button_container" style= "display:inline-block;"><div class="mashup_request"><a href="/StartupMashup/resources/downloadAssets/assets.zip" style="text-decoration:none;">Download the brand assets</a></div></div>

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
