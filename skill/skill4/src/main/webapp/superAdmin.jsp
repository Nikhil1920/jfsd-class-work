<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="dao.DetailsDao"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
	response.setHeader("Cache-Control", "no-store, max-age=0");
	if (session.getAttribute("userrole") == null || !session.getAttribute("userrole").equals("superadmin")) {
		response.sendRedirect("index.html");
	}
	%>
	<h1>Welcome Super admin</h1>
	<br>
	<%
	DetailsDao details = new DetailsDao();
	ResultSet rs = details.getAdmins();
	if (!rs.isBeforeFirst()) {
		System.out.println("No admins available please add one");
	} else {
		while (rs.next()) {
	%>
	<table>
		<tr>
			<td>
				<h2>Admins list</h2>
			</td>
		</tr>
		<tr>
			<td>
				<%
				out.println(rs.getString("username"));
				%>
			</td>
		</tr>
		<%
		}
		}
		%>
	</table>
	<br>
	<h2>Add new admin</h2>
	<form class="form-signin" action="RegisterServlet" method="POST">
        <label for="inputUsername" class="sr-only">Username</label>
        <input name="username" id="inputUsername" class="form-control" placeholder="Username" required autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input name="password" type="password" id="inputPassword" class="form-control" placeholder="Password" required>
        <input type="hidden" id="role" name="role" value="admin">
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign up</button>
    </form>
	<br>
	<a href="LogoutServlet">Logout</a>
</body>
</html>