<%-- 
    Document   : 2faRemove
    Created on : Apr 29, 2022, 2:09:36 PM
    Author     : Benas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ivesktie otp</title>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="content">
            <div class="container">
                <h2 class="pastel">Iveskite vienkartini koda</h2>
                <h1 class="important">SVARBU: Patvirtinus vienkartini koda, dvieju faktroiu autentifikacija bus isjungta</h1>
                <div class="important"> ivedus vienkartini koda panaikinkite autentifikatoriaus koda mobiliajame irenginije </div>
                <br><br>
                <div class="pastel">${errormessage}</div>
                <form action="patvirtintiotp">
                    <input name="otp" type="password" size="30" />
                    <button type="submit">patvirtinti</button>
                </form>
            </div>
        </div>
    </body>
</html>
<% 
                session.setAttribute("errormessage", "");
                %>