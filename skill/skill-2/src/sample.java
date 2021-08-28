import java.sql.*;
import java.util.Scanner;

class sample {

    public static void main(String[] args) throws Exception {

        Connection con = getConnection();
        Scanner sc = new Scanner(System.in);
        Statement stmt = con.createStatement();
        try {
            stmt.executeUpdate("create table test(dob Date)");
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        System.out.println("Enter date");
        String dob_str = sc.nextLine();
        Date dob = Date.valueOf(dob_str);
        PreparedStatement pstmt = con.prepareStatement("insert into test(dob) values(?)");
        pstmt.setDate(1, dob);
        pstmt.executeUpdate();
        con.close();
        sc.close();
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {

        Class.forName("oracle.jdbc.driver.OracleDriver");

        String dbUserName = "system";

        String dbUserPassword = "nikhil";

        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", dbUserName, dbUserPassword);

        return con;

    }

}
