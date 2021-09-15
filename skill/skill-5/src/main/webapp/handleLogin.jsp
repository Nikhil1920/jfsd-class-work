<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
	String username = request.getParameter("username");
	String password = request.getParameter("password");

	Class.forName("oracle.jdbc.driver.OracleDriver");

	String dbUserName = "system";

	String dbUserPassword = "nikhil4u";

	Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", dbUserName, dbUserPassword);

	Statement stmt = con.createStatement();

	String check_login_query = "SELECT username FROM skill5login where username=? and password=?";
	
	PreparedStatement pstmt = con.prepareStatement(check_login_query);
	
	pstmt.setString(1, username);
	pstmt.setString(2, password);
	
	ResultSet rs = pstmt.executeQuery();
	
	boolean status = rs.next();
	
	if(status){
		session.setAttribute("username", username);
		response.sendRedirect("changePassword.jsp");
	} else{
		out.print("Sorry, email or password error");
	}
	%>

</body>
</html>