<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Skill 5 question 1</title>
<style>
table, th, td {
  border:1px solid black;
}
</style>
</head>
<body>
	<%
	Class.forName("oracle.jdbc.driver.OracleDriver");

	String dbUserName = "system";

	String dbUserPassword = "nikhil4u";

	Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", dbUserName, dbUserPassword);

	Statement stmt = con.createStatement();

	ResultSet rs = stmt.executeQuery("SELECT * FROM skill5books");

	if (!rs.isBeforeFirst()) {
		out.println("No books available please add one");
	} else {
	%>
	<table style="width:100%">
		<tr>
			<th>
				<h2>Book name</h2>
			</th>
			<th>
				<h2>Author</h2>

			</th>
			<th>
				<h2>Description</h2>
			</th>
			<th>
				<h2>Price</h2>
			</th>
			<th>
				<h2>Cover Image url</h2>
			</th>
		</tr>
		<%
		while (rs.next()) {
		%>
		<tr>
			<td><%=rs.getString(1)%></td>

			<td><%=rs.getString(2)%></td>

			<td><%=rs.getString(3)%></td>

			<td><%=rs.getInt(4)%></td>

			<td><%=rs.getString(5)%></td>
			
			<td><a href="#">Add to cart</a></td>
		</tr>
		<%
		}
		%>
	</table>
	<%
	}
	%>
</body>
</html>