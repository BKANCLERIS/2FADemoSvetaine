<%-- 
    Document   : home2faenabled
    Created on : Apr 7, 2022, 11:02:53 PM
    Author     : Benas
--%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Admin CPanel - Bookshop</title>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="content">
            <div class="container">
                <h1 class="pastel">Demosvetaine home</h1>
                <b class="pastel">Username: ${user.username} Email: (${user.email}) </b>
                <br><br>
                <div class="pastel">${homemessage}
                    <a href="Remove2fa"><button>Disable 2FA</button></a>
                    <br><br>
                    <a href="LogoutPagaliau"><button>Logout</button></a>
                </div>    
            </div>
    </body>
</html>