/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.benas.bakisdemo;

import net.benas.klientas.ComUtils;
import net.benas.klientas.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Benas
 */
public class Remove2fa extends HttpServlet {

    public Remove2fa() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        ComUtils comUtils = new ComUtils();

        try {
            comUtils.KeyExchange();
        } catch (Exception ex) {
            Logger.getLogger(enable2FA.class.getName()).log(Level.SEVERE, null, ex);
        }
        comUtils.println(":2faremove:");
        comUtils.println(user.getUsername() + ":splitwww.demosvetaine.lt:split"+user.getPass()+":splitDemoSvetaine2022");

        String line = "";
        if ((line = comUtils.readLine()) != null) {
            System.out.println(line);
            String[] split = line.split(":");
            user.changeoption(":2faremove:");
            session.setAttribute("user", user);
            session.setAttribute("comUtils", comUtils);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("2faRemove.jsp");
            dispatcher.forward(request, response);
        }
    }
    

}
