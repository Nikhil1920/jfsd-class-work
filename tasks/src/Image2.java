import java.sql.*;
import java.io.*;

public class Image2 {
    public static void main(String[] args) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "admin");

            PreparedStatement ps = con.prepareStatement("select * from JFSDIMG2");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {// now on 1st row

                Blob b = rs.getBlob(2);// 2 means 2nd column data
                byte barr[] = b.getBytes(1, (int) b.length());// 1 means first image

                FileOutputStream fout = new FileOutputStream("d:\\Jerry1.jpg");
                fout.write(barr);

                fout.close();
            } // end of if
            System.out.println("Image Copied Successfully");

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}