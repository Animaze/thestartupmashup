<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>

<html lang="en">
<head>
  <title>Startup Mashup | Home</title>
  <meta charset="utf-8">
<!--   <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> -->
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="shortcut icon" type="image/png" href="images/icon.png"/>
  
    <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"> 
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="<c:url value='/resources/js/subscriber.js'/>"></script>
<script src="<c:url value='/resources/js/login_validations.js'/>"></script>
<link rel="stylesheet" href="<c:url value='/resources/css/form.css'/>" />




<link rel="stylesheet" href="/StartupMashup/resources/css/custom.css" />
<link rel="stylesheet"
	href="<c:url value='/resources/css/commonheader.css'/>" />
  
  
  


</head>
<style>
.navbar {
 background: url('/StartupMashup/resources/images/logo.png') no-repeat top left;  
 background-color: #e0382d;
 
  border: none;
  color: white;
  z-index: 100;
  margin-bottom: 120px;
  border-radius: 0;
  
}
.navbar{
transistion:12s;

}


/* Adjusting the brand and link colors */

.navbar-default .navbar-brand {
  color: white;
  font-weight: 500;
}
.navbar-default .navbar-brand:hover {
  color: #000;
}
.navbar-default .navbar-nav > li > a {
  color: white;
  font-weight: 500;
  font-size: 1em;
  
}
.navbar-default .navbar-nav > li > a:hover, .navbar-default .navbar-nav > li > a:active {
  color: #000;
  text-decoration: none;
}
.navbar-default .navbar-nav > li > a:visited, .navbar-default .navbar-nav > li > a:focus {
  color: white;
  text-decoration: none;
}
.icon-bar{
	
 background-color: #e0382d;
 color:#ffffff;
}
.icon-bar:hover{
	
 background-color: #fff;
 color:#fff;
}




body {
    padding-top: 40px; /* Required padding for .navbar-fixed-top. Change if height of navigation changes. */
}


footer {
    padding: 30px 0;
}




.event_container{
background: url('/StartupMashup/resources/images/event.png') no-repeat center top; 
-webkit-background-size: cover;
-moz-background-size: cover;
-o-background-size: cover;
background-size: cover;
margin:0;
padding:0;

font-family: 'Roboto', sans-serif;
min-height: 800px;
margin-top:60px;


}


	
@media only screen and (max-width: 767px) {

   .home_text {
      font-size: 3em;
   }
    ul.tabs li{
			background: none;
			color: #222;
			display: inline-block;
			padding: 10px 15px;
			cursor: pointer;
			font-size:12px;
		}

} 

@media only screen and (max-width: 320px) {

   .home_text {
      font-size: 1.7em;
   }
   ul.tabs li{
			background: none;
			color: #222;
			display: inline-block;
			padding: 10px 15px;
			cursor: pointer;
			font-size:12px;
		}
   
   
}   


/*--------------------Mashup Details --------------------*/

ul.tabs{
			margin: 0px;
			padding: 0px;
			list-style: none;
			
		}
		ul.tabs li{
			background: none;
			color: #222;
			display: inline-block;
			padding: 10px 15px;
			cursor: pointer;
			font-size:30px;
			border-bottom:2px solid white;
			
		}

		ul.tabs li.current{
			background: #fff;
			color: #000;
			border-top:2px solid white;
			border-radius:2px;
			
		}

		.tab-content{
			display: none;
			background:transparent;
			padding: 15px;
			
			
		}

		.tab-content.current{
			display: inherit;
		}

.myrefers_button:hover{

color:red;
background-color:#fff;

}
</style>

<script>
$(document).ready(function(){
	
	$('ul.tabs li').click(function(){
		var tab_id = $(this).attr('data-tab');

		$('ul.tabs li').removeClass('current');
		$('.tab-content').removeClass('current');

		$(this).addClass('current');
		$("#"+tab_id).addClass('current');
	})

})

</script>
<script> 
$(document).ready(function(){
    $("#flip").click(function(){
        $("#panel").slideToggle("slow");
		
    });
	
});
$(document).ready(function(){
    $("#flip1").click(function(){
        $("#panel").slideToggle("slow");
		
    });
	
});

function getHackathon(clickedButton) {
	var buttonId = $(clickedButton).attr('id');
	var hackathonId = buttonId.split('_')[1];
	window.location.href = "/StartupMashup/view-startup-details?hackathonId="
			+ hackathonId;
};
function saveHackathonId(thisButton){
	hackathonId=$(thisButton).attr('id').split('_')[1];

	document.getElementById("referredHackathonId").value=hackathonId;

};
</script>


<body>


<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar" style="background-color:#fff;"></span>
        <span class="icon-bar" style="background-color:#fff;"></span>
        <span class="icon-bar" style="background-color:#fff;"></span>                        
      </button>
      <a class="navbar-brand" href="javascript:void(0)" id="flip1"> <div class="header"><img src="/StartupMashup/resources/images/logo.png" class="img-responsive" style="margin-top:-15px;"></img></div></a>
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
									<br><br><button type="submit"
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


<div class="container-fluid header_container">


		<div class="header">
		
			<div class="row"><br><br><br><br><br><br><br><br>
				<p class="home_text">RESUME's ARE DEAD<br>PROVE YOURSELF</p>
			</div>
			<div class="row button_container">
				<div class="myrefers_button"><a href="/StartupMashup/list-hackathon">Attend Mashup</a></div>
				<div class="myrefers_button"><a href="/StartupMashup/list-hackathon">Refer Friends</a></div>
			</div>
		</div>
</div>
<div class="container-fluid base_container center">
  <div class="container">
	<div class="row" style="font-size:20px;margin:40px auto;">
		<div class="col-sm-4"><div class="center"><img src="/StartupMashup/resources/images/why.png"></img></div><br><br><p class="">The Best Startups Need To Connect With The Best Talent Which Is Really Difficult To Hire!</p></div>
		<div class="col-sm-4"><div class="center"><img src="/StartupMashup/resources/images/what.png"></img></div><br><br><p class="">Through the Mashup we connect the Hottest Talent to the Hottest Startups!</p></div>
		<div class="col-sm-4"><div class="center"><img src="/StartupMashup/resources/images/how.png"></img></div><br><br><p class="">The Gruelling Hackathons Gets the winner in Each Category 2X their Current Salary If They Get Hired By The Hottest
					Startups!</p></div>

	</div>
  </div>
</div>

<div class="container-fluid center event_container" style=" background-size: cover;">


 <div class="container" style="margin-top:20%;">

	<ul class="tabs">
		<li class="tab-link current" data-tab="tab-1">Upcoming Mashup</li>
		<li class="tab-link" data-tab="tab-2">Previous Mashup</li>
		
	</ul>

	<div id="tab-1" class="tab-content current">
					<c:if test="${liveHackathonCount==0 }">
				<div class="event">
			            <div class="row">
							<div class="col-sm-2 company_logo">
				<img src=''
									 class="img-responsive"></img>
							</div>
							<div class="col-sm-4" style="text-align:left;">
							<b style="font-size:20px;">No Hackathons available</b> 
							<br><br>
							<span></span>&nbsp;&nbsp;&nbsp;&nbsp;<span></span>
				
							</div>
<%-- 							<div class="col-sm-6" style="margin-top:15px;text-align:right;">
							<a href="javascript:void(0)" id="btn_${hackathon.id}"
									onclick="getHackathon(this)" class="register" >Register</a>&nbsp;&nbsp;&nbsp;&nbsp;
							
									<a  data-toggle="modal" id="ref_${hackathon.id}" href="#referal_form"
										onclick="saveHackathonId(this);" class="register" style="display:inline-block;" >Refer friends</a></div> --%>
							
					</div>
				</div>
	   
				
				</c:if>
	<c:if test="${liveHackathonCount > 0 }">
			<c:forEach items="${upcomingMashups}" var="hackathon" begin="0"
						end="1">
				
				  <div class="event">
			            <div class="row">
							<div class="col-sm-2 company_logo">
				<img src='${hackathon.image}'
									 class="img-responsive"></img>
							</div>
							<div class="col-sm-4" style="text-align:left;">
							<b style="font-size:20px;">${hackathon.name }</b> 
							<br><br>
							<span>${hackathon.date }</span>&nbsp;&nbsp;&nbsp;&nbsp;<span>${hackathon.venue }</span>
				
							</div>
							<div class="col-sm-6" style="margin-top:15px;text-align:right;">
							<a href="javascript:void(0)" id="btn_${hackathon.id}"
									onclick="getHackathon(this)" class="register" >Register</a>&nbsp;&nbsp;&nbsp;&nbsp;
							
									<a  data-toggle="modal" id="ref_${hackathon.id}" href="#referal_form"
										onclick="saveHackathonId(this);" class="register" style="display:inline-block;" >Refer friends</a></div>
							
					</div>
				</div>
	   
		</c:forEach>
	
</c:if>				
		</div>
	<div id="tab-2" class="tab-content">
	
	<c:forEach items="${previousMashups}" var="hackathon" begin="0"
						end="1">
		 <div class="event">
			            <div class="row">
							<div class="col-sm-2 company_logo">
			<img src='${hackathon.image}'
									 class="img-responsive"></img>
							</div>
							<div class="col-sm-4" style="text-align:left;">
							<b style="font-size:20px;">${hackathon.name }</b> 
							<br><br>
							<span>${hackathon.date }</span>&nbsp;&nbsp;&nbsp;&nbsp;<span>${hackathon.venue }</span>
				
							</div>
							<div class="col-sm-6" style="margin-top:15px;text-align:right;">
						
							<a href="javascript:void(0)"  id="btn_${hackathon.id}"
									onclick="getHackathon(this)" class="register">See Details</a>
							</div>
					</div>
				</div>
		</c:forEach>
	</div>
<a href="/StartupMashup/list-hackathon"><button type="button" class="btn btn-default myrefers_button" style="border:2px solid #fff;min-width:140px;box-shadow: 1px 1px 1px #c2c2c2;">SEE ALL</button></a>
 </div>

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
  

	<!---div for challenges starts here-->

<div class="container-fluid base_container center">
 <div class="container center" style="margin:40px auto;">
		<h1 style="color:#1db755;text-align:center;">Challenges at startup mashup</h1><br>
	<div class="row">
		<div class="col-sm-4" style="margin-top:10px;">
				<!--<div class="center" style="margin-top:10px;"> -->
				    <div class="challenge_icon"><img src="/StartupMashup/resources/images/developers.png"></img><p class="text" style="font-size:20px;">Developer</p></div>
				<!-- </div> -->
				 
		</div>
		<div class="col-sm-4" style="margin-top:10px;">
				<!-- <div class="center">  -->
				   <div class="challenge_icon"><img src="/StartupMashup/resources/images/product.png"></img><p class="text" style="font-size:20px;">Product Manager</p></div>
				<!-- </div> -->
				 
				
		</div>
		<div class="col-sm-4" style="margin-top:10px;">
				<!-- <div class="center" style="margin-top:10px;">  -->
					<div class="challenge_icon"><img src="/StartupMashup/resources/images/ui-designer.png"></img><p class="text" style="font-size:20px;">UI Designer</p></div>
				<!-- </div> -->
				 
				
		</div>
		
    </div>
  </div>
</div>	

	<!---div for challenges starts here----->
	
	
<div class="container-fluid share_banner"><br><br>
		
		<h2 style="color:#ffffff; display:inline-block;margin-top:10px;">Share, Get Good Karma Get More Karma.Repeat </h2>
		<div class="refer_button_container" style= "display:inline-block;margin-top:10px;"><div class="refer"><a href="/StartupMashup/list-hackathon" style="text-decoration:none;">Refer Friends</a></div></div>
	
        
</div>

<div class="container-fluid testimonial"><br><br>
		<c:forEach items="${listOfTestimonials}" var="testimonial" begin="0"
			end="1">
		<div class="refer_button_container" style= "display:inline-block"><img src="${testimonial.image }"height="50px" width="50px"></img></div>
		<h3 style="color:#a3a2a2; display:inline-block;">${testimonial.words}</h3>
        </c:forEach>

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
						target="_blank">My Refers</a><br> <a href="#team">Team</a><br>
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