<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Startup Mashup | About</title>
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


<script src="<c:url value='/resources/js/about_validations.js'/>"></script>



<link rel="stylesheet" href="<c:url value='/resources/css/form.css'/>" />
<link rel="stylesheet" href="<c:url value='/resources/css/custom.css'/>" />
<link href='http://fonts.googleapis.com/css?family=Roboto'
	rel='stylesheet' type='text/css'>

</head>


<style>
.header_about {
	background: url('/StartupMashup/resources/images/about.png') center
		center;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	background-size: cover;
	margin: 0;
	padding: 0;
	font-color: white;
	font-weight: bold;
	font-family: 'Roboto', sans-serif;
	padding-top: 100px;
	min-height: 800px;
	margin-top: 0px;
}

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

.about_text_heading {
	color: #6c6868;
	font-weight: bold;
}

.about_text {
	text-align: justify;
	color: #6c6868;
}

.team_page {
	clear: both;
	background-color: #ececec;
	margin: 0 auto;
}

.team {
	background: url('/StartupMashup/resources/images/teamback.png') center
		center no-repeat;
	width: 180px;
	color: #636161;
	height: 300px;
	margin-top: 20px;
	margin-bottom: 20px;
	box-sizing: border-box;
	display: inline-block;
	padding: 20px;
}

@media only screen and (min-device-width : 320px) and (max-device-width
	: 480px) {
	.about_text_heading {
		font-size: 75%;
	}
}
/* Smartphones (landscape) ----------- */
@media only screen and (min-width : 321px) {
	.about_text_heading {
		font-size: 120%;
	}
}
/* Smartphones (portrait) ----------- */
@media only screen and (max-width : 320px) {
	.about_text_heading {
		font-size: 120%;
	}
}

/* Desktops and laptops ----------- */
@media only screen and (min-width : 1224px) {
	.about_text_heading {
		font-size: 206%;
	}
}
/* Large screens ----------- */
@media only screen and (min-width : 1824px) {
	.about_text_heading {
		font-size: 250%;
	}
}

@media only screen and (max-width: 767px) {
	.home_text {
		font-size: 3em;
	}
	.welcome {
		font-size: 2em;
	}
}

@media only screen and (max-width: 320px) {
	.home_text {
		font-size: 1.7em;
	}
	.welcome {
		font-size: 1em;
	}
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

	<div class="container-fluid header_about">


		<div class="header">

			<div class="row">
				<br> <br> <br>
				<h1 class="home_text">
					<span class="about_text_hello">Hello</span> <br>
					<p style="font-size: 60%">WELCOME, WE ARE,</p>
					AWENGERS
				</h1>
			</div>

		</div>
	</div>

	<div class="container base_container">

		<div class="row center">
			<h1 style="text-align: left; color: #6c6868;">ABOUT</h1>
			<p class="about_text_heading">"We wanted to solve this big
				question that every startup had, of how to find the best talent?
				That's why TheStartupMashup!"</p>
			<p class="about_text">A day long hackathon with gruelling
				challenges in many domains, the winners will walk away with
				trophies, certificates and dream opportunity.</p>

		</div>
	</div>



	<a name="team"></a>
	<div class="container-fluid team_page center">
		<div class="container center">
			<br> <br>
			<h1 style="text-align: left; color: #6c6868;">OUR TEAM</h1>
			<div class="row center">

				<div class="col-sm-3">
					<div class="team">
						<img src="/StartupMashup/resources/images/Lalit.png"
							width="110px" height="110px"></img><br> <br>
						<h4 style="color: #f55353">
							<b>Lalit Bhagia</b>
						</h4>
						<h5>
							<i>Co-Founder & CEO</i>
						</h5>
						<h5>MyRefers</h5>
					</div>
				</div>
				<div class="col-sm-3 ">
					<div class="team">
						<img src="/StartupMashup/resources/images/kashish.png"
							width="110px" height="110px"></img> <br> <br>
						<h4 style="color: #f55353">
							<b>Kashish Bhagia</b>
						</h4>
						<h5>
							<i>Head-Ops</i>
						</h5>
						<h5>MyRefers</h5>
					</div>
				</div>
				<div class="col-sm-3 ">
					<div class="team">
						<img src="/StartupMashup/resources/images/akshat.png"
							width="110px" height="110px"></img> <br> <br>
						<h4 style="color: #f55353">
							<b>Akshat Goel</b>
						</h4>
						<h5>
							<i>Product Executive</i>
						</h5>
						<h5>MyRefers</h5>
					</div>
				</div>
				<div class="col-sm-3 ">
					<div class="team">
						<img src="/StartupMashup/resources/images/Yash.png" width="110px"
							height="110px"></img> <br> <br>
						<h4 style="color: #f55353">
							<b>Yash Saxena</b>
						</h4>
						<h5>
							<i>Digital Marketing</i>
						</h5>
						<h5>MyRefers</h5>
					</div>
				</div>
			</div>


			<div class="row center">
				<div class="col-sm-3">
					<div class="team">
						<img src="/StartupMashup/resources/images/Akshara-1.png"
							width="110px" height="110px"></img> <br> <br>
						<h4 style="color: #f55353">
							<b>Aakanksha Sethi</b>
						</h4>
						<h5>
							<i>Web Developer</i>
						</h5>
						<h5>MyRefers</h5>
					</div>
				</div>
				<div class="col-sm-3 ">
					<div class="team">
						<img src="/StartupMashup/resources/images/anupa.png"
							width="110px" height="110px"></img> <br> <br>
						<h4 style="color: #f55353">
							<b>Anupam Sharma</b>
						</h4>
						<h5>
							<i>Web Developer</i>
						</h5>
						<h5>MyRefers</h5>

					</div>
				</div>
				<div class="col-sm-3 ">
					<div class="team">
						<img src="/StartupMashup/resources/images/Anirudh.png"
							width="110px" height="110px"></img> <br> <br>
						<h4 style="color: #f55353">
							<b>Anirudh Aggarwal</b>
						</h4>
						<h5>
							<i>Web Developer</i>
						</h5>
						<h5>MyRefers</h5>
					</div>
				</div>
				<div class="col-sm-3 ">
					<div class="team">
						<img src="/StartupMashup/resources/images/Akansha.png"
							width="110px" height="110px"></img> <br> <br>
						<h4 style="color: #f55353">
							<b>Akshara Bansal</b>
						</h4>
						<h5>
							<i>Web Developer</i>
						</h5>
						<h5>MyRefers</h5>
					</div>
				</div>
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