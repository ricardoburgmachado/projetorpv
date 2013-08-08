<%-- 
    Document   : Login
    Created on : 08/08/2013, 19:13:43
    Author     : rafael
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="../recursos/css/login.css"/>
        <title>GIPA - Login</title>
    </head>
    <body>
        <h1>GIPA - Login</h1>
        <div class="login">
            <form action="" method="post">
                <ul>
                    <li>
                        <label for="login">Usu&aacute;rio</label>
                        <input type="text" id="login" name="login"/>
                    </li>

                    <li>
                        <label for="senha">Senha</label>
                        <input type=password id="senha" name="senha"/>
                    </li>

                    <button type="submit">Login</button>
                </ul>
            </form>
        </div>
    </body>
</html>