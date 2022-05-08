package net.benas.bakisdemo;

import net.benas.klientas.ComUtils;
import net.benas.klientas.User;
import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/login")
public class UserLoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public UserLoginServlet() {
        super();
    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDAO userDao = new UserDAO();

        try {
            User user = userDao.checkLogin(username, password);
            String destPage = "login.jsp";

            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                destPage = "home.jsp";
                if (user.enabled2fa == true) {
                    ComUtils comUtils = new ComUtils();
                    comUtils.KeyExchange();
                    comUtils.println(":2faConfirm:");
                    comUtils.println(user.getUsername() + ":splitwww.demosvetaine.lt:split"+user.getPass()+":splitDemoSvetaine2022");

                    if (comUtils.readLine() != null) {
                        destPage = "2fatest.jsp";
                        session.setAttribute("comUtils", comUtils);  
                    }else{
                        destPage = "login.jsp";
                        request.setAttribute("message", "nera priejimo prie autentifikacijos serverio");
                        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
                        dispatcher.forward(request, response);
                    } 
                }
                response.sendRedirect(destPage);
            } else {
                request.setAttribute("message", "Invalid username/password");
                RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
                dispatcher.forward(request, response);
            }

            

        } catch (SQLException ex) {
            throw new ServletException(ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserLoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConnectException crf) {

            String destPage = "login.jsp";
            request.setAttribute("message", "nera priejimo prie autentifikacijos serverio");
            RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(UserLoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
