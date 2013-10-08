<%-- 
    Document   : inscricao_lista
    Created on : 07/10/2013, 21:07:59
    Author     : rafael
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="now" class="java.util.Date"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>GIPA - Lista de Incrições</title>
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

                <h1>Lista de Inscrições</h1>

                <div class="listagem">
                    <c:forEach var="inscricao" items="${inscricoes}">
                        <div class="item-lista">

                            <ul title="Dados da inscrição">
                                <li>Projeto: ${inscricao.projeto.titulo}</li>
                                <li>Edital: ${inscricao.edital.titulo}</li>
                                <li>Arquivo: <span class="arquivo">${inscricao.arquivo.nomeArquivo}</span></li>
                                <li>Prazo final do edital: <fmt:formatDate type="date" dateStyle="medium" value="${inscricao.edital.prazoFinal}" /></li>
                            </ul>

                            <div class="botoes">
                                <c:if test="${now < inscricao.edital.prazoFinal}">
                                    <a title="Cancelar inscrição" href="inscricao_cancela?id_projeto=${inscricao.projeto.id}&${inscricao.edital.id}"<span class="botao cancela"></span></a>
                                </c:if>
                            </div>
                        </div>
                    </c:forEach>
                </div>

            </div>

        </div>

        <div id="rodape"></div>
    </body>
</html>
