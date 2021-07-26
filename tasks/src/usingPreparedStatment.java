
//Sum done on 24-07-2021
// Step 1 import java.sql package
import java.sql.*;

class usingPreparedStatment {

    public static void main(String[] args) throws Exception {
        try {
            // Step 2 load the driver class
            Class.forName("oracle.jdbc.driver.OracleDriver");

            String dbUserName = "system";// your user name goes here

            String dbUserPassword = "nikhil";// your password goes here

            // step 3 create the connection object
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", dbUserName,
                    dbUserPassword);

            // step 4 create the statement object
            Statement stmt = con.createStatement();

            /*
             * step 5 Now the connection has been established succesfully Now you can
             * execute your necessary commands here
             */

            // create a new table
            String create_table_query = "CREATE TABLE nikhil (s_no INTEGER not NULL, reg_no INTEGER not NULL, name VARCHAR(255), age INTEGER, phone INTEGER)";
            stmt.executeUpdate(create_table_query);
            System.out.println("nikhil table created succesfully");

            // Insert data into table
            int s_no = 1;
            String[] employee_names = { "nikhil", "kishan", "vyshnav", "mukund", "manoj" };
            int[] employee_reg_no = { 2, 4, 6, 8, 10 };
            int[] employee_age = { 22, 25, 21, 23, 44 };
            int[] employee_phone = { 648585848, 648585855, 648585845, 648585855, 648585865 };

            String employee_insert_query = "INSERT INTO nikhil (s_no, reg_no, name, age, phone) VALUES (?,?,?,?,?)";

            PreparedStatement pstmt = con.prepareStatement(employee_insert_query);

            for (int i = 0; i < 5; i++) {
                pstmt.setInt(1, s_no);
                pstmt.setInt(2, employee_reg_no[i]);
                pstmt.setString(3, employee_names[i]);
                pstmt.setInt(4, employee_age[i]);
                pstmt.setInt(5, employee_phone[i]);
                pstmt.executeUpdate();
                System.out.println("Inserted " + employee_names[i] + " data into table");
                s_no++;
            }
            System.out.println("Inserted nikhil data succesfully");
            print_data_to_console(stmt);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public static void print_data_to_console(Statement stmt) {
        try {
            // prints data to console
            ResultSet rs = stmt.executeQuery("select * from nikhil");
            while (rs.next())
                System.out.println(rs.getInt(1) + "  " + rs.getInt(2) + "  " + rs.getString(3) + "  " + rs.getInt(4)
                        + "  " + rs.getInt(5));
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

}
