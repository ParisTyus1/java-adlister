import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import com.mysql.cj.jdbc.Driver;



public class MySQLAdsDao implements Ads {
    private Connection connection;

    public MySQLAdsDao(Config config){

    try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
            config.getUrl(),
            config.getUsername(),
            config.getPassword()
            );

        }catch (SQLException e){
        throw new RuntimeException("Error Connecting to the database!", e);

        }
    }

    @Override
    public long insert(Ad ad) {
        Long lastInsertId = 0L;
        String query = String.format(
                "INSERT INTO ads(user_id, title, description)"+ "VALUES (%d,'%s', '%s');",
                ad.getUserId(),
                ad.getTitle(),
                ad.getDescription()
        );

        try {
            Statement stmt = connection.createStatement();
            long id = (long)stmt.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()){
                lastInsertId = rs.getLong(1);
            }
        } catch (SQLException ex) {
            System.out.println("Error creating a new ad.");
        }
        return lastInsertId ;
    }

    @Override
    public List<Ad> all() {

//    String query = "INSERT"
    String query = "SELECT * FROM ads;";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            List<Ad> ads = new ArrayList<>();
            while (rs.next()){
                ads.add(new Ad(
                        rs.getLong(1),
                        rs.getLong(2),
                        rs.getString(3),
                        rs.getString(4)
                ));
            }
           return ads;
        }catch (SQLException e){
            System.out.println("Error retrieving all ads.");
        }
        return null;
    }

    @Override
    public boolean delete(long id) {
        try{
           Statement stmt = connection.createStatement();
           String query = "DELETE FROM ads WHERE id =" + id;
           int val = stmt.executeUpdate(query);
           return val > 0;
        }catch (SQLException ex){
            System.out.println("Error Deleting Ad");
        }
        return false;
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

    public Connection getConnection(){
        return this.connection;
    }

    public static void main(String[] args) throws SQLException {

        Config config = new Config();
        Ads adsDoa = new MySQLAdsDao(config);
        List<Ad> ads = adsDoa.all();
        for (Ad ad: ads){
            System.out.println(ad);
        }
//        dao.delete();
        Ad adToInsert = new Ad(1,"Gum","chewy");
        long lastInstertedId = adsDoa.insert(adToInsert);
        System.out.println(lastInstertedId);


    }
}
