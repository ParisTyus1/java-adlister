import java.sql.SQLException;

public class DaoFactory {
    private static Ads adsDao;


    public static Ads getAdsDao() throws SQLException {
        if (adsDao == null) {
            Config config = new Config();
            adsDao = new MySQLAdsDao(config);
        }
        return adsDao;
    }

//    public static Users getUsersDao() {
//        if (adsDao == null) {
//            adsDao = new MySQLAdsDao(config);
//        }
//        return usersDao;
//    }




}
