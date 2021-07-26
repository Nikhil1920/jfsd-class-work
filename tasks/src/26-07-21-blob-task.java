import java.sql.*;
import java.io.*;

class blobTask {
    public static void main(String[] args) throws Exception {
        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");

            String dbUserName = "system";

            String dbUserPassword = "nikhil";

            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", dbUserName,
                    dbUserPassword);

            Statement stmt = con.createStatement();

            String create_table_query = "CREATE TABLE blobtest(s_no INTEGER not NULL, txt_file BLOB)";
            stmt.executeUpdate(create_table_query);
            System.out.println("clobTest table created succesfully");

            PreparedStatement ps = con.prepareStatement("insert into blobtest values(?,?)");
            ps.setInt(1, 190031920);

            FileInputStream fin = new FileInputStream("C:/study/3-1/jfsd_sdp/tasks/src/190031920.txt");
            ps.setBinaryStream(2, fin, fin.available());
            int i = ps.executeUpdate();
            System.out.println(i + " records affected");

            ResultSet rs = stmt.executeQuery("select * from blobtest");
            if (rs.next()) {

                Blob b = rs.getBlob(2);
                byte barr[] = b.getBytes(1, (int) b.length());

                FileOutputStream fout = new FileOutputStream("C:/study/3-1/jfsd_sdp/tasks/src/nikhil.txt");
                fout.write(barr);

                fout.close();
            }
            System.out.println("text file Copied Successfully");

            con.close();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}