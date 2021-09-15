<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<title>Home page</title>
</head>

<body>

	<%
	response.setHeader("Cache-Control", "no-store, max-age=0");
	if (session.getAttribute("username") == null) {
		response.sendRedirect("skill5q2.jsp");
	}
	%>
	<h1>
		Welcome
		<%
	if (session.getAttribute("username") != null) {
		String username = session.getAttribute("username").toString();
		out.println(username);
	}
	%>
	</h1>
	<br>
	<h2>You have logged in successfully</h2>
	<br>
	<br>
	<h2>Change your password</h2>
	<form action="handlePasswordChange.jsp" method="post">
		New Password:<input type="password" name="password" /><br />
		<br /> <input type="submit" value="changePassword" />
	</form>
</body>

</html>