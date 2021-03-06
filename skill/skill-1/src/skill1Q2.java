import java.sql.*;
import java.util.*;

class Skill1Q2 {
    public static void main(String[] args) throws Exception {
        Connection con = getConnection();

        Scanner scanner = new Scanner(System.in);

        boolean exit = false;

        create_students_table(con);

        while (!exit) {
            System.out.println("1. Update students data");
            System.out.println("2. View qualified students");
            System.out.println("3. Delete non qualified students");
            System.out.println("4. View all data in students table");
            System.out.println("5. Exit the program");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    update_student_data(con, scanner);
                    break;

                case 2:
                    print_qualified_students_data(con);
                    break;

                case 3:
                    delete_unqualified_students_data(con);
                    break;

                case 4:
                    print_all_students_data(con);
                    break;

                case 5:
                    exit = true;
                    break;

                default:
                    System.out.println("Please Try Again");
                    break;
            }

        }
        scanner.close();
        con.close();
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {

        Class.forName("oracle.jdbc.driver.OracleDriver");

        String dbUserName = "system";

        String dbUserPassword = "nikhil4u";

        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", dbUserName, dbUserPassword);

        return con;

    }

    public static void create_students_table(Connection con) {
        try {
            String create_students_table_query = "CREATE TABLE students (id INTEGER PRIMARY KEY, name VARCHAR(255) NOT NULL, gender VARCHAR(6), year_of_study INTEGER, department VARCHAR(3), kl_mail VARCHAR(255),cgpa DOUBLE PRECISION, num_of_backlogs INTEGER)";

            Statement stmt = con.createStatement();

            stmt.executeUpdate(create_students_table_query);
            System.out.println("students table created succesfully");

            int[] id = { 1920, 1925, 1927, 1943, 1988 };
            String[] names = { "nikhil", "goutham", "kishan", "vyshnav", "charan" };
            String[] gender = { "male", "male", "male", "male", "male" };
            int[] year = { 3, 3, 3, 3, 3 };
            String[] department = { "cse", "cse", "cse", "cse", "cse" };
            String[] kl_mail = { "1920@kluniversity.in", "1925@kluniversity.in", "1927@kluniversity.in",
                    "1943@kluniversity.in", "1988@kluniversity.in" };
            double[] cgpa = { 8.00, 7.26, 7.80, 7.50, 6.20 };
            int[] num_of_backlogs = { 0, 0, 1, 0, 1 };

            String students_insert_query = "insert into students(id, name, gender, year_of_study, department, kl_mail, cgpa, num_of_backlogs) values(?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement prepStmt = con.prepareStatement(students_insert_query);

            for (int i = 0; i < id.length; i++) {
                prepStmt.setInt(1, id[i]);
                prepStmt.setString(2, names[i]);
                prepStmt.setString(3, gender[i]);
                prepStmt.setInt(4, year[i]);
                prepStmt.setString(5, department[i]);
                prepStmt.setString(6, kl_mail[i]);
                prepStmt.setDouble(7, cgpa[i]);
                prepStmt.setInt(8, num_of_backlogs[i]);
                prepStmt.executeUpdate();
                System.out.println("Inserted " + id[i] + " data into students table");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public static void print_qualified_students_data(Connection con) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM students where cgpa > 7.5");
            if (!rs.isBeforeFirst()) {
                System.out.println("No data");
            } else {
                while (rs.next())
                    System.out.println(rs.getInt(1) + "  " + rs.getString(2) + " " + rs.getString(3) + " "
                            + rs.getInt(4) + " " + rs.getString(5) + " " + rs.getString(6) + " " + rs.getDouble(7) + " "
                            + rs.getInt(8));
            }
        } catch (Exception e) {

        }
    }

    public static void print_all_students_data(Connection con) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM students");
            if (!rs.isBeforeFirst()) {
                System.out.println("No data");
            } else {
                while (rs.next())
                    System.out.println(rs.getInt(1) + "  " + rs.getString(2) + " " + rs.getString(3) + " "
                            + rs.getInt(4) + " " + rs.getString(5) + " " + rs.getString(6) + " " + rs.getDouble(7) + " "
                            + rs.getInt(8));
            }
        } catch (Exception e) {

        }
    }

    public static void update_student_data(Connection con, Scanner scanner) {
        try {
            System.out.println("Enter the id of the student");
            int id = scanner.nextInt();
            System.out.println("Enter new number of backlogs");
            int num_of_backlogs = scanner.nextInt();
            System.out.println("Enter new cgpa");
            double cgpa = scanner.nextDouble();

            String students_table_update_query = "UPDATE students SET cgpa = ?, num_of_backlogs = ? WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(students_table_update_query);
            pstmt.setDouble(1, cgpa);
            pstmt.setInt(2, num_of_backlogs);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
            System.out.println("updated student data");
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

    }

    public static void delete_unqualified_students_data(Connection con) {
        try {
            String delete_unqualified_students_query = "DELETE FROM students WHERE cgpa <= 7.5";

            Statement stmt = con.createStatement();

            stmt.executeUpdate(delete_unqualified_students_query);

            System.out.println("Deleted unqualified student data succesfully");
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}