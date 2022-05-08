/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.benas.bakisdemo;

import java.io.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.springframework.security.crypto.bcrypt.BCrypt;

@WebServlet("/register")
public class UserRegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public UserRegisterServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Statement stmt = null;

        try {
            String destPage = "register.jsp";
            Class.forName("org.postgresql.Driver");
            java.sql.Connection c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/demosvetaine", "postgres", "extreme007");
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM users WHERE user_name = '" + username + "';");
            while (rs.next()) {
                if (rs.getInt("count") != 0) {
                    String message = "Vartotojas su tokiu vardu jau egzistuoja";
                    request.setAttribute("message", message);
                } else {
                    String test = BCrypt.hashpw(password, BCrypt.gensalt());
                    String SQLString = "INSERT INTO users(\"user_name\", \"password\", \"email\") VALUES (?, ?, ?);";

                    PreparedStatement statement = c.prepareStatement(SQLString);
                    statement.setString(1, username);
                    statement.setString(2, test);
                    statement.setString(3, email);
                    statement.executeUpdate();
                    destPage = "login.jsp";
                    String message = "Jus sekmingai uzsiregistravote";
                    request.setAttribute("message", message);
                }
            }

            rs.close();
            stmt.close();
            c.close();

            RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } catch (ServletException e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

    }
}
