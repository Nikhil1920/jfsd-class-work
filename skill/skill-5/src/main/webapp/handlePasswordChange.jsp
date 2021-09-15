<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<title>Change Password</title>
</head>

<body>
	<%
	response.setHeader("Cache-Control", "no-store, max-age=0");
	if (session.getAttribute("username") == null) {
		response.sendRedirect("skill5q2.jsp");
	}
	if (session.getAttribute("username") != null) {
		String username = session.getAttribute("username").toString();
		String password = request.getParameter("password");
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String dbUserName = "system";
		String dbUserPassword = "nikhil4u";
		
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", dbUserName, dbUserPassword);
		
		Statement stmt = con.createStatement();
		
		String change_password_query = "UPDATE skill5login SET password=? where username=?";
		
		PreparedStatement pstmt = con.prepareStatement(change_password_query);
		pstmt.setString(1, password);
		pstmt.setString(2, username);
		int i = pstmt.executeUpdate();
		out.print(i + " records changed");
	}
	%>

</body>

</html>