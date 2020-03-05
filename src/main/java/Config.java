import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Config {

    public Config() throws SQLException {
    }

    public String getUrl() {
            return "jdbc:mysql://localhost/adlister_db?serverTimezone=UTC&useSSL=false";
        }
        public String getUsername() {
            return "root";
        }
        public String getPassword() {
            return "codeup";
        }



}
