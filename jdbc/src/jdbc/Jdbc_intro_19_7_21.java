package jdbc;

// Step 1 import java.sql package
import java.sql.*;

public class Jdbc_intro_19_7_21 {
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
            
            //create a new table
            String create_table_query = "CREATE TABLE employees (id INTEGER not NULL, name VARCHAR(255), phone INTEGER)";
            stmt.executeUpdate(create_table_query);
            System.out.println("employees table created succesfully");
            
            //Insert data into table
            String[] employee_names = {"nikhil", "kishan", "vyshnav", "mukund"};
            int[] employee_id = {2,4,6,8};
            int[] employee_phone = {69,55,45,55};
            
            String employee_insert_query = "INSERT INTO employees (id, name, phone) VALUES (?,?,?)";
            
            PreparedStatement pstmt = con.prepareStatement(employee_insert_query);

            for(int i = 0; i < 4; i++) {
            	pstmt.setInt(1, employee_id[i]);
            	pstmt.setString(2, employee_names[i]);
            	pstmt.setInt(3, employee_phone[i]);
            	pstmt.executeUpdate();
            	System.out.println("Inserted "+ employee_names[i] + " data into table");
            }
            System.out.println("Inserted employees data succesfully");
            print_data_to_console(stmt);
            
            //Delete employee with id 3
            String delete_employee_query = "DELETE FROM employees WHERE id = 6";
            stmt.executeUpdate(delete_employee_query);
            System.out.println("employee with id 3 deleted succesfully");
            print_data_to_console(stmt);
            
            //update employee with id 4
            String update_employee_query = "UPDATE employees SET name='bharadwaj' WHERE id=8";
            stmt.executeUpdate(update_employee_query);
            System.out.println("employee with id 4 updated succesfully");
            print_data_to_console(stmt);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
	
	public static void print_data_to_console(Statement stmt) {
		try {
			// prints data to console
			ResultSet rs = stmt.executeQuery("select * from employees");
			while (rs.next())
				System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getInt(3));
		} catch (Exception e) {
            System.out.println("Error: " + e);
        }
	}
}
