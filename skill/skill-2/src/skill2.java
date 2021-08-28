import java.io.FileInputStream;
import java.sql.*;
import java.sql.Date;
import java.util.*;

class skill2 {
    public static void main(String[] args) throws Exception {
        Connection con = getConnection();

        Scanner sc = new Scanner(System.in);

        boolean exit = false;

        create_hostel_table(con);

        while (!exit) {
            System.out.println("1. Add students data to hostel database");
            System.out.println("2. View all data in hostel database");
            System.out.println("3. Delete student data from hostel database");
            System.out.println("4. Exit the program");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    insert_into_hostel_table(con, sc);
                    break;

                case 2:
                    viewData(con);
                    break;

                case 3:
                    System.out.println("Enter the id of the student to be deleted");
                    int tmp_id = sc.nextInt();
                    delete_student_data(con, tmp_id);
                    break;

                case 4:
                    exit = true;
                    break;

                default:
                    System.out.println("Please Try Again");
                    break;
            }
        }

        sc.close();
        con.close();
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {

        Class.forName("oracle.jdbc.driver.OracleDriver");

        String dbUserName = "system";

        String dbUserPassword = "nikhil";

        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", dbUserName, dbUserPassword);

        return con;

    }

    public static void create_hostel_table(Connection con) {
        try {
            String create_hostel_table_query = "CREATE TABLE hostel(id INTEGER PRIMARY KEY, first_name VARCHAR(255) NOT NULL, last_name VARCHAR(255) NOT NULL, year_of_study INTEGER NOT NULL, gender VARCHAR(8) NOT NULL, department VARCHAR(3) NOT NULL, mobile VARCHAR(10) NOT NULL, dob DATE NOT NULL, parent_name VARCHAR(255) NOT NULL, parent_mobile_number VARCHAR(10) NOT NULL, address VARCHAR(255) NOT NULL, id_card BLOB NOT NULL, payment_receipet BLOB NOT NULL)";

            Statement stmt = con.createStatement();

            stmt.executeUpdate(create_hostel_table_query);

            System.out.println("Hostel database created successfully");

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public static void insert_into_hostel_table(Connection con, Scanner sc) {
        try {
            String hostel_insert_query = "INSERT INTO hostel(id, first_name, last_name, year_of_study, gender, department, mobile, dob, parent_name, parent_mobile_number, address, id_card, payment_receipet) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

            System.out.println("Enter your id number");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.println("Enter your first name");
            String first_name = sc.nextLine();

            System.out.println("Enter your last name");
            String last_name = sc.nextLine();

            System.out.println("Enter your year of study");
            int year_of_study = sc.nextInt();

            System.out.println("Enter your gender");
            String gender = sc.next();

            System.out.println("Enter your Department");
            String department = sc.next();

            System.out.println("Enter your mobile number");
            String mobile = sc.next();

            System.out.println("Enter your date of birth in yyyy-mm-dd format");
            String dob_str = sc.next();
            Date dob = Date.valueOf(dob_str);
            sc.nextLine();

            System.out.println("Enter your parent name");
            String parent_name = sc.nextLine();

            System.out.println("Enter your parent's mobile number");
            String parent_mobile = sc.nextLine();

            System.out.println("Enter your address");
            String address = sc.nextLine();

            System.out.println("Enter the location of kl id photo in your file system");
            String id_card = sc.nextLine();

            System.out.println("Enter the location of payment receipet in your file system");
            String payment_receipet = sc.nextLine();

            PreparedStatement pstmt = con.prepareStatement(hostel_insert_query);

            pstmt.setInt(1, id);
            pstmt.setString(2, first_name);
            pstmt.setString(3, last_name);
            pstmt.setInt(4, year_of_study);
            pstmt.setString(5, gender);
            pstmt.setString(6, department);
            pstmt.setString(7, mobile);
            pstmt.setDate(8, dob);
            pstmt.setString(9, parent_name);
            pstmt.setString(10, parent_mobile);
            pstmt.setString(11, address);

            FileInputStream id_fin = new FileInputStream(id_card);
            pstmt.setBinaryStream(12, id_fin, id_fin.available());

            FileInputStream payment_receipt_fin = new FileInputStream(payment_receipet);
            pstmt.setBinaryStream(13, payment_receipt_fin, payment_receipt_fin.available());

            pstmt.executeUpdate();

            System.out.println("Data inserted successfully");

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public static void viewData(Connection con) {
        try {
            String view_students_query = "SELECT id, first_name, last_name FROM hostel";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(view_students_query);
            if (!rs.isBeforeFirst()) {
                System.out.println("No data");
            } else {
                System.out.println("Id number  |  First name  |  Last name");
                while (rs.next())
                    System.out.println(rs.getInt(1) + "  " + rs.getString(2) + " " + rs.getString(3));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public static void delete_student_data(Connection con, int id) {
        try {
            String delete_query = "DELETE FROM hostel WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(delete_query);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Deleted student with id " + id + " succesfully");
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
