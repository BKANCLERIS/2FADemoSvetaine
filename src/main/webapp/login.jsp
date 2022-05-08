<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Bookshop Website</title>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="content">
            <div class="container">
                <h1 class="pastel">Admin Login</h1>
                <form action="login" method="post">
                    <label for="username">Username:</label>
                    <input name="username" type="text" size="30" maxlength="30"/>
                    <label for="password">Password:</label>
                    <input type="password" name="password" size="30" maxlength="30" />
                    <br><br>
                    <div class="pastel">${message}</div>
                    <br><br>           
                    <button type="submit">Login</button>

                </form>
                <a href="register.jsp"><button>Register</button></a>
            </div>
        </div>
    </body>
</html>