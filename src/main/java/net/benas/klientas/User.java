/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.benas.klientas;
import java.util.Random;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 *
 * @author Benas
 */
public class User {
    public String username;
    public String email;
    public boolean enabled2fa;
    public String option;
    private String pass;

    public User(String username,String email,boolean enabled2fa, String pass){
        this.username=username;
        this.email=email;
        this.enabled2fa= enabled2fa;
        this.pass = pass;
        option = "";
    }

    void setUsername(String string) {
         username=string;
    }

    void setEmail(String email) {
        this.email= email;
    }
    void setenabled2fa(boolean enabled2fa){
        this.enabled2fa = enabled2fa;
    }
    
    public String getUsername() {
        return username;
    }

    public String getEmail(){
        return email;
    }
    public void changeoption(String option){
        this.option= option;
    }
    public String getPass(){
        return pass;
    }
}
