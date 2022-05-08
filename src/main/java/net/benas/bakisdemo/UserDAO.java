/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.benas.bakisdemo;

import net.benas.klientas.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 *
 * @author Benas
 */
class UserDAO{
    
    public User checkLogin(String username, String password) throws SQLException,
            ClassNotFoundException {
        String jdbcURL = "jdbc:postgresql://localhost:5432/demosvetaine";
        String dbUser = "postgres";
        String dbPassword = "extreme007";
        
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
        String sql = "SELECT * FROM users WHERE user_name = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
       
 
        ResultSet result = statement.executeQuery();
 
        User user = null;
        
        if (result.next()) {
            if(BCrypt.checkpw(password, result.getString("password"))){
            user = new User(result.getString("user_name"),result.getString("email"),result.getBoolean("2FAenabled"),result.getString("password"));
            }
        }
 
        connection.close();
 
        return user;
    }
    
    
    
}
