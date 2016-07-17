<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script type = "text/javascript" >
    history.pushState(null, null, 'adminHomePage');
    window.addEventListener('popstate', function(event) {
    history.pushState(null, null, 'adminHomePage');
    });
    </script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Startup mashup</title>

</head>
<jsp:include page="adminCredentials.jsp"></jsp:include>
<body>
<h1 align="center" style="color:aqua;">Welcome to Startup Mashup!!!!!</h1>
<br>
<br>
<br>

<div style="color: red; font-size: large;" >Admin Functions</div>
<br>
<h2>Add NEW DATA.</h2>
<br>
<a href="/StartupMashup/admin/testimonial-registration">Testimonials</a>
<br>
<a href="/StartupMashup/admin/skill-registration">Skills</a>
<br>
<a href="/StartupMashup/admin/challenge-registration">Challenges</a>
<br>
<a href="/StartupMashup/admin/hackathon-registration">Hackthons</a>
<br>
<a href="/StartupMashup/admin/company-registration">Companies</a>
<br>
<h2>View and Edit OLD DATA.</h2>
<br>
<a href="/StartupMashup/admin/list-testimonials">List of Testimonials</a>
<br>
<a href="/StartupMashup/admin/list-skills">List of Skills</a>
<br>
<a href="/StartupMashup/admin/list-challenges">List of Challenges</a>
<br>
<a href="/StartupMashup/admin/list-hackathons">List of Hackthons</a>
<br>
<a href="/StartupMashup/admin/list-companies">List Companies</a>
<br>
<a href="/StartupMashup/admin/list-users">List of Participants</a>
<br>
<a href="/StartupMashup/admin/list-referrals">List of Refer details</a>
<br>
<a href="/StartupMashup/admin/list-subscriber">List of Subscribers</a>

<br>

<br>


</body>
</html>