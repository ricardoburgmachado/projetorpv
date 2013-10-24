<%-- 
    Document   : inscricao_lista
    Created on : 07/10/2013, 21:07:59
    Author     : rafael
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="now" class="java.util.Date"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>GIPA - Recados do Projeto</title>

        <link rel="stylesheet" href="<c:url value="/recursos/css/style.css"/>" />
    </head>
    <body>

        <div id="topo">
            <div id="logo_brasil"></div> 
            <c:import url="sub_topo.jsp"/>
        </div>

        <div id="all">

            <div id="menu">
                <c:import url="menu.jsp"/>
            </div>     

            <div id="conteudo_interno">

                <h1>Recados do Projeto</h1>

                <c:if test="${not empty inconsistencias}">
                    <ul class="inconsistencias">
                        <c:forEach var="inconsistencia" items="${inconsistencias}">
                            <li>${inconsistencia}</li>
                            </c:forEach>
                    </ul>
                </c:if>

                <div class="inside-conteudo">
                    <c:forEach var="recado" items="${recados}">
                        <div class="item-lista-background lista-objeto" style="padding-left: 5px">
                            <p>${recado.conteudo}</p>
                            <ul class="flexbox flex-wrap" style="list-style: none;">
                                <li class="">
                                    <span class="title">Data de envio: </span><fmt:formatDate type="date" dateStyle="medium" value="${recado.dataEnvio}" />
                                </li>
                                <li class="">
                                    <span class="title">Remetente: </span>${recado.remetente.nome} (${recado.remetente.papel})
                                </li>
                            </ul>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>

        <div id="rodape"></div>
    </body>
</html>
