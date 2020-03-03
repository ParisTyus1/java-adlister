import java.sql.Connection;
import java.sql.DriverManager;

public class DaoFactory {
    private static Ads adsDao;
    private static Users usersDao;
    private static Config config = new Config();

    public static Ads getAdsDao() {
        if (adsDao == null) {
            adsDao = new MySqlAdsDao(config);
        }
        return adsDao;
    }

    public static Users getUsersDao() {
        if (adsDao == null) {
            adsDao = new MySqlAdsDao(config);
        }
        return usersDao;
    }




}
