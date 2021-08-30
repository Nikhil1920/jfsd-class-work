<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
	response.setHeader("Cache-Control", "no-store, max-age=0");
	if (session.getAttribute("userrole") == null || !session.getAttribute("userrole").equals("admin")) {
		response.sendRedirect("index.html");
	}
	%>

	<h1>Welcome admin</h1>
	<h2>You have logged in successfully</h2>
	<br>
	<br>
	<a href="LogoutServlet">Logout</a>
</body>
</html>