/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.benas.bakisdemo;

import net.benas.klientas.User;
import net.benas.klientas.ComUtils;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class patvirtintiOTP extends HttpServlet {


    public patvirtintiOTP(){
        super();
       }
    
    
     protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ComUtils comUtils = (ComUtils) session.getAttribute("comUtils");
        User user = (User) session.getAttribute("user");                   
            if (comUtils != null) {
                   comUtils.println(request.getParameter("otp"));                
            }            
                String line;
                String destination ="klaid.jsp";
                System.out.println(user.enabled2fa);
                System.out.println(user.option);
                
                if((line = comUtils.readLine()) != null) {
                    if(line.equals("valio")){
                        if(user.enabled2fa == false){
                           try{  
                               System.out.println("1");
                                Class.forName("org.postgresql.Driver");
                                Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/demosvetaine","postgres", "extreme007");
                                Statement stmt = c.createStatement();
                                user.enabled2fa=true;
                                destination = "home2faenabled.jsp";
                                session.setAttribute("homemessage", "2FA enabled");
                                stmt.executeQuery("UPDATE users SET \"2FAenabled\"='t' WHERE user_name = '"+user.username+"';");                     
                            c.close();
                            stmt.close();
                         }
                          catch (Exception e) {
                                e.printStackTrace();
                            } 
                        }else{
                            if(user.option.equals(":2faremove:")){
                                try{  
                                destination = "home.jsp";
                                user.enabled2fa= false;
                                Class.forName("org.postgresql.Driver");
                                session.setAttribute("homemessage", "2fa disabled");
                                Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/demosvetaine","postgres", "extreme007");
                                Statement stmt = c.createStatement();
                                stmt.executeQuery("UPDATE users SET \"2FAenabled\"='f' WHERE user_name = '"+user.username+"';");
                            c.close();
                            stmt.close();
                         }
                          catch (Exception e) {
                                e.printStackTrace();
                            } 
                            }else{
                        session.setAttribute("homemessage", "Sveiki");
                        destination = "home2faenabled.jsp";
                    }
                    
                    comUtils.Sclose();
                    }}else{if(line.equals("nevalio")){
                        session.setAttribute("errormessage", "kodas neteisingas");
                        if(user.enabled2fa == false){
                            destination = "2faconfirm.jsp";
                        }else{
                            if(user.option.equals(":2faremove:")){
                            destination = "2faRemove.jsp";
                            }else{
                            destination = "2fatest.jsp";
                            }
                        }
                    }
                        
                    }
                response.sendRedirect(destination);
                }
            
            
        }
    }
