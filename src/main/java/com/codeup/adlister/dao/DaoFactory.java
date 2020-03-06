package com.codeup.adlister.dao;

import java.sql.SQLException;

public class DaoFactory {
    private static Ads adsDao;
    private static Users usersDao;
    private static Config config;

    static {
        try {
            config = new Config();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static Ads getAdsDao(){
        if (adsDao == null) {
            adsDao = new MySQLAdsDao(config);
        }
        return adsDao;
    }

    public static Users getUsersDao(){
        if (usersDao == null){
            usersDao = new MySQLUserDao(config);
        }
        return usersDao;
    }
}
