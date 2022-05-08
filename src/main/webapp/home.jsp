<%-- 
    Document   : home
    Created on : Apr 7, 2022, 7:40:58 PM
    Author     : Benas
--%>

<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Demosvetaine home</title>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="content">
            <div class="container">
                <h1>Demosvetainė home</h1>
                <b class="pastel">username ${user.username} email (${user.email}) </b>
                <br><br>
                <div class="pastel">${message}</div>
                <br><br>
                <a href="2FAenable"><button>Enable 2FA</button></a>
                <br><br>
                <a href="LogoutPagaliau"><button>Logout</button></a>
            </div>
        </div>
    </body>
</html>
<% session.setAttribute("message", "");%>