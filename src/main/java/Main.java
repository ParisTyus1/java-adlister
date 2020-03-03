import java.sql.*;

public class Main {
    public static void main(String[] args) {


    String url = "jdbc:mysql://localhost/adlister_db?serverTimezone=UTC&useSSL=false";
    String user = "user";
    String password = "password123";
    try{
        Connection myConn = DriverManager.getConnection(url, user, password);
        Statement myStmt = myConn.createStatment();
        String sql = "select * from adlister_db.Contacts";
        ResultSet rs = myStmt.executeQuery(sql);

        while (rs.next())
            System.out.println(rs.getString("name"));

    }catch (SQLException e){
        e.printStackTrace();
    }
}

}



