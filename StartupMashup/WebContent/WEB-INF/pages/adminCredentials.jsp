<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div >
		<div style="float : left; color:blue; font-size:x-large;">
			Hello ${sessionScope.adminUsername },
		</div>
		<div style="float : right; color:green; font-size:x-large; ">
			<a style="text-decoration: none;"href="/StartupMashup/j_spring_security_logout">logout</a>	
		</div>
	</div>
</body>
</html>