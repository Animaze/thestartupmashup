<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>

<%@page import="com.startupmashup.bean.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Subscriber List</title>
</head>

<body>

		<table>

			<tr>
				
				<th>Id</th>
				<th>||</th>
				
				<th>Email Id</th>
				
				<th>||</th>
				<th>Created By</th>
				<th>||</th>
				<th>Created Date</th>
				<th>||</th>
				<th>Updated By</th>
				<th>||</th>
				<th>Updated Date</th>
			</tr>

			<c:forEach items="${listOfsubscribers}" var="subscriber">
				<tr>
					
					
					<td>${subscriber.id}</td>
					<td>||</td>
					<td>${subscriber.emailId}</td>
					<td>||</td>
					
					<td>${subscriber.createdBy}</td>
					<td>||</td>
					<td>${subscriber.createdDate}</td>
					<td>||</td>
					<td>${subscriber.updatedBy}</td>
					<td>||</td>
					<td>${subscriber.updatedDate}</td>
				</tr>

			</c:forEach>
			
	
			

		</table>

</body>
</html>