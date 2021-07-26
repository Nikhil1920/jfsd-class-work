import java.sql.*;
import java.io.*;

public class Image1 {
    public static void main(String[] args) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "admin");

            PreparedStatement ps = con.prepareStatement("insert into JFSDIMG2 values(?,?)");
            ps.setString(1, "sonoo");

            FileInputStream fin = new FileInputStream("d:\\Tom.jpg");
            ps.setBinaryStream(2, fin, fin.available());
            int i = ps.executeUpdate();
            System.out.println(i + " records affected");

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}