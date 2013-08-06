<%-- 
    Document   : index
    Created on : 30/07/2013, 19:35:02
    Author     : Ricardo
--%>
<%@page import="java.io.File"%>
<%@page import="br.com.dao.ConnectionFactory"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>INDEX</h1>     
        
        <% 
            ConnectionFactory.createConnectionPostgres();
        %>
    </body>
</html>
