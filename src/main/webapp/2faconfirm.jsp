<%-- 
    Document   : 2faconfirm
    Created on : Apr 7, 2022, 8:48:21 PM
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
                <div class="imgcontainer">

                    <img src="${pageContext.request.contextPath}/qrcode?qrstring=${qrkodas}">

                </div>
                <br><br>
                <b class="pastel">${totp}</b>
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