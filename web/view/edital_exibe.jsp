<%-- 
    Document   : edital_exibe
    Created on : 23/09/2013, 01:18:06
    Author     : rafael
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<c:url value="/recursos/css/style.css"/>"> 
        <title>GIPA - Exibir ${edital == null ? 'Exibir Edital' : 'edital.getTitulo()'}</title>
    </head>
    <body>
        <h1>Exibir Edital${edital == null ? '' : ' - edital.getTitulo()'}</h1>
        
        <c:choose>
            <c:if test="${not empty inconsistencias}">
                <div class="inconsistencias">
                    <ul>
                        <c:forEach var="inconsistencia" items="${inconsistencias}">
                            <li>${inconsistencia}</li>
                        </c:forEach>
                    </ul>
                </div>
            </c:if>
        </c:choose>
        

        <div class="conteudo">

            <c:choose>
                <c:when test="${not empty edital}">
                    <thead>
                        <tr>
                            <th>Título</th>
                            <th>Início</th>
                            <th>Fim</th>
                            <th>Tipo</th>
                            <th>Edital/Retificações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>${edital.titulo}</td>
                            <td><fmt:formatDate type="date" value="${edital.prazoInicial}"/></td>
                            <td><fmt:formatDate type="date" value="${edital.prazoFinal}"/></td>
                            <td>${edital.tipo}</td>
                            <td>${edital.arquivo.titulo}</td>
                        </tr>
                    </tbody>
                </c:when>
            </c:choose>
        </div>
    </body>
</html>
