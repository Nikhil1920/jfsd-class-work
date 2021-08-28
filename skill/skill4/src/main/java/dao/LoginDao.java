package dao;

import java.sql.*;

public class LoginDao {
	public boolean check(String mail, String password) {

		String dbUserName = "system";

		String dbUserPassword = "nikhil4u";

		String oracleUrl = "jdbc:oracle:thin:@localhost:1521:XE";

		String query = "SELECT * FROM skill4Login WHERE username=? and password=?";

		boolean status = false;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			Connection con = DriverManager.getConnection(oracleUrl, dbUserName, dbUserPassword);

			PreparedStatement prepst = con.prepareStatement(query);

			prepst.setString(1, mail);

			prepst.setString(2, password);

			ResultSet rs = prepst.executeQuery();

			status = rs.next();

			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public String getUserRole(String username) {
		String dbUserName = "system";

		String dbUserPassword = "nikhil4u";

		String oracleUrl = "jdbc:oracle:thin:@localhost:1521:XE";

		String query = "SELECT role FROM skill4Login WHERE username=?";

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			Connection con = DriverManager.getConnection(oracleUrl, dbUserName, dbUserPassword);

			PreparedStatement prepst = con.prepareStatement(query);

			prepst.setString(1, username);

			ResultSet rs = prepst.executeQuery();

			rs.next();

			return rs.getString(1);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Error";
	}
}
