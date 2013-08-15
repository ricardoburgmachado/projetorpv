<%-- 
    Document   : Login
    Created on : 08/08/2013, 19:13:43
    Author     : rafael
--%>

<%@page import="Exceptions.PersistenciaException"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<c:url value="/recursos/css/login.css"/>"/>
        <title>GIPA - Login</title>
    </head>
    <body>
        <h1>GIPA - Login</h1>
        <div class="login">
            <form action="<c:url value="/autentica"/>" method="post">
                <ul>
                    <li>
                        <label for="usuario">Usu&aacute;rio</label>
                        <input type="text" id="usuario" name="login" autofocus="true"/>
                    </li>

                    <li>
                        <label for="senha">Senha</label>
                        <input type=password id="senha" name="senha"/>
                    </li>
                    
                    <%
                        if(request.getAttribute("exception") != null){
                            
                            PersistenciaException pe = (PersistenciaException) request.getAttribute("exception");
                            out.println("<li><span>" + pe.getMessage() + "</span></li>");
                        }
                    %>
                    
                    <button type="submit">Login</button>
                </ul>
            </form>
        </div>
    </body>
</html>