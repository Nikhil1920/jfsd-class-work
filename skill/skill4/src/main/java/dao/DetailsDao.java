package dao;

import java.sql.*;

public class DetailsDao  {
	public ResultSet getAdmins() {

		String dbUserName = "system";

		String dbUserPassword = "nikhil4u";

		String oracleUrl = "jdbc:oracle:thin:@localhost:1521:XE";

		String getAdminsQuerry  = "SELECT username FROM skill4Login WHERE role='admin'";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			Connection con = DriverManager.getConnection(oracleUrl, dbUserName, dbUserPassword);
			
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(getAdminsQuerry);
			
			return rs;
		}	catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
