package com.codeup.adlister.dao;

import com.codeup.adlister.models.User;
import com.mysql.cj.jdbc.Driver;
import java.sql.*;

public class MySQLUserDao implements Users {
   private Connection connection;

   public  MySQLUserDao(Config config){
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    config.getUrl(),
                    config.getUser(),
                    config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }

    @Override
    public User findByUsername(String username) {
        String query = "SELECT * FROM users WHERE username = ? LIMIT = 1";
       try {
           PreparedStatement stmt = connection.prepareStatement(query);
           stmt.setString(1,username);
           return extractUser(stmt.executeQuery());
       } catch (SQLException e) {
           throw new RuntimeException("Error retrieving all ads.", e);
       }

    }

    @Override
    public Long insert(User user) {
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO ads(user_id, title, description) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1,user.getUsername());
            stmt.setString(2,user.getEmail());
            stmt.setString(3,user.getPassword());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new ad.", e);
        }
    }

    public User extractUser(ResultSet rs)throws SQLException{
       if (! rs.next() ){
           return null;
       }
       return new User(
               rs.getLong("id"),
               rs.getString("username"),
               rs.getString("email"),
               rs.getString("password")
       );
    }
}
