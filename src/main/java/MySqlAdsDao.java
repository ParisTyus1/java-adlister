import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import com.mysql.cj.jdbc.Driver;



public class MySqlAdsDao implements Ads {
    private Connnection myConn = null;

    public MySqlAdsDao(Config config){

    try {
            DriverManager.registerDriver(new Driver());
           myConn = DriverManager.getConnection(
            confif.getUrl();
            confif.getUsername();
            confif.getPassword();
            );



        }catch (SQLException e){
        throw new RuntimeException("Error Conecting to the database!",e);


        }
    }

    @Override
    public List<Ad> all() {
        Statement stmt = null;
        try {
            stmt = myConn.createStatment();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ads",e);
            return createAdsFromResults(rs);
        }catch (SQLException e){
            throw new RuntimeException("Error retrieving all ads.", e);
        }
    }

    @Override
    public Long insert(Ad ad) {
        try {
            String sqlQuery = createInsertQuery(ad);
            PreparedStatement stmt = connection.preareStatment(sqlQuery,Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1,ad.getUserId());
            stmt.setString(2,ad.getTitle());
            stmt.setString(3,ad.getDescription());
            stmt.executeUpdate();
            ResultSet rs.getLong(1);
        }catch (SQLException e){
            throw new RuntimeException(("Error creating a new ad.",e)
        }
    }

    public String createInsertQuery(Ad ad){
        return "INSERT INTO ads(user_id, title, description) VALUES(?, ?, ?)";
    }

    private Ad extractAd(ResultSet rs) throws SQLException{
        return new Ad(
                rs.getLong("id"),
                rs.getLong("user_id"),
                rs.getString("title"),
                rs.getString("description")
        );
    }

    public List<Ad> createAdsFromResults(ResultSet rs)throws SQLException{
        List<Ad> ads = new ArrayList<>();
        while (rs.next()){
            ads.add(extractAd(rs));
        }
        return ads;
    }

    public Connnection getConnection(){
        return this.myConn;
    }

}
