<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 
 <!DOCTYPE html>
<html lang="en">
<head>
  <title>Startup Mashup | Error</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
    <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"> 
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="<c:url value='/resources/js/subscriber.js'/>"></script>

<link rel="stylesheet" href="<c:url value='/resources/css/custom.css'/>" />
<link href='http://fonts.googleapis.com/css?family=Roboto'
	rel='stylesheet' type='text/css'>
</head>
<style>
.header_error{
background: url('/StartupMashup/resources/images/error.png') center center; 
-webkit-background-size: cover;
-moz-background-size: cover;
-o-background-size: cover;
background-size: cover;
margin:0;
padding:0;
font-color:white;
font-weight:bold;
font-family: 'Roboto', sans-serif;
text-align:left;
min-height: 800px;

	
}

@media only screen and (max-width: 767px) {

   .home_text {
      font-size: 3em;
   }
   .welcome{
   font-size: 2em;
   }
   

} 

@media only screen and (max-width: 320px) {

   .home_text {
      font-size: 1.7em;
   }
   .welcome{
   font-size: 1em;
   
   }
}     
</style>
<body>


<div class="container-fluid header_error">

			<div class="header">
		
				<div class="row"><br><br><br>
				<h1 class="home_text"><span class="about_text_hello">Oops!</span> <br><p style="font-size:60%">Something went wrong . We can't find the page you are looking for.Try Reloading the Page  or </p>Go back to homepage</h1>
				<button type="button" class="btn btn-default myrefers_button" style="background-color:transparent;"><a href="/StartupMashup/">Back to Home</a></button>
			</div>

		</div>
		
</div>


</body>
</html>
 