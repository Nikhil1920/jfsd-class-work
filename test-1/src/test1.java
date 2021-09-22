import java.util.Scanner;
import java.sql.*;

class test1 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        try {
            Connection con = get_Connection();
            System.out.println("Inserting data into table");
            System.out.println("Enter username");
            String username = sc.next();
            System.out.println("Enter password");
            String password = sc.next();

            insert_into_testlogin_table(username, password, con);

            System.out.println("Delete from testlogin table");
            System.out.println("Enter username");
            String del_username = sc.next();

            delete_from_table(con, del_username);

        } catch (Exception e) {
            System.out.println(e);
        }

        sc.close();
    }

    public static Connection get_Connection() throws ClassNotFoundException, SQLException {

        Class.forName("oracle.jdbc.driver.OracleDriver");

        String dbUserName = "system";

        String dbUserPassword = "nikhil4u";

        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", dbUserName, dbUserPassword);

        return con;

    }

    public static void insert_into_testlogin_table(String username, String password, Connection con) {
        try {
            String insert_query = "INSERT INTO testlogin(username, password) VALUES (?, ?)";

            PreparedStatement pstmt = con.prepareStatement(insert_query);

            pstmt.setString(1, username);

            pstmt.setString(2, password);

            int res = pstmt.executeUpdate();

            if (res > 0) {
                System.out.println("Inserted succesfully");
            } else {
                System.out.println("something went wrong");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void delete_from_table(Connection con, String username) {
        try {
            String delete_query = " DELETE FROM testlogin WHERE username = ?";

            PreparedStatement pstmt = con.prepareStatement(delete_query);

            pstmt.setString(1, username);
            int res = pstmt.executeUpdate();

            if (res > 0) {
                System.out.println("Deleted succesfully");
            } else {
                System.out.println("something went wrong");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}