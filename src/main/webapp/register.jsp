﻿<%@ page language="java" contentType="text/html; charset=utf-16"
         pageEncoding="utf-16"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Demosvetainė</title>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="content">
            <div class="container">
                <h1 class="pastel">Register</h1>
                <form action="register" method="post">
                    <label for="username">Username:</label>
                    <input type="text" name="username" size="30" minlength="5"  maxlength="30"/>
                    <label for="email">Email:</label>
                    <input type="email" name="email" size="30"  maxlength="30"/>
                    <label for="password">Password:</label>
                    <input type="password" name="password" size="30" minlength="5" maxlength="30" />
                    <br><br>
                    <div class="pastel"> ${message}</div>
                    <br><br>

                    <button type="submit">Register</button>

                </form> 


                <a href="login.jsp"><button>Login</button></a>
            </div>
        </div>
    </body>
</html>