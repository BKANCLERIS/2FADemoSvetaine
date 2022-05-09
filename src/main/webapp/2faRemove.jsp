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
        <title>Įveskite vienkartinį slaptažodį</title>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="content">
            <div class="container">
                <h2 class="pastel">Įveskite vienkartinį slaptažodį</h2>
                <h1>SVARBU: Patvirtinus vienkartinį slaptažodį, dviejų faktorių autentifikaciją, bus išjungtą</h1>
                <div> įvedus vienkartinį slaptažodį panaikinkite autentifikatoriaus kodą mobiliajame irenginije </div>
                <br><br>
                <div class="pastel">${errormessage}</div>
                <form action="patvirtintiotp">
                    <input name="otp" type="text" size="30" />
                    <button type="submit">patvirtinti</button>
                </form>
            </div>
        </div>
    </body>
</html>
<% 
                session.setAttribute("errormessage", "");
                %>