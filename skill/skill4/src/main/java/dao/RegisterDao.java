package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RegisterDao {
	public boolean register(String uname, String password, String role) {

		String dbUserName = "root";

		String dbUserPassword = "nikhil4u";

		String oracleUrl = "jdbc:oracle:thin:@localhost:1521:XE";

		String query = "INSERT INTO skill4Login (username, password, role) VALUES (?, ?, ?)";

		String checkquery = "SELECT * FROM skill4Login WHERE username=?";

		boolean status = false;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			Connection con = DriverManager.getConnection(oracleUrl, dbUserName, dbUserPassword);

			PreparedStatement checkst = con.prepareStatement(checkquery);

			checkst.setString(1, uname);

			ResultSet rs = checkst.executeQuery();

			if (rs.next())
				return false;

			rs.close();

			PreparedStatement prepst = con.prepareStatement(query);

			prepst.setString(1, uname);

			prepst.setString(2, password);

			prepst.setString(3, role);

			int result = prepst.executeUpdate();

			if (result == 1) {
				return true;
			}

			return false;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}
}
