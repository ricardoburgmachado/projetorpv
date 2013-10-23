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
        <title>GIPA - Contemplação de Inscrição</title>

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

                <h1>Contemplação de Inscrições</h1>

                <c:if test="${not empty inconsistencias}">
                    <ul class="inconsistencias">
                        <c:forEach var="inconsistencia" items="${inconsistencias}">
                            <li>${inconsistencia}</li>
                            </c:forEach>
                    </ul>
                </c:if>

                <div class="flexbox">
                    <div class="round-border">
                        <h2>Projeto</h2>
                        <ul>
                            <li><span class="title">Título: </span>${inscricao.projeto.titulo}</li>
                            <li><span class="title">Palavras-chave: </span>${inscricao.projeto.palavrasChave}</li>
                            <li><span class="title">Arquivo: </span><a class="arquivo" href="" title="Download do arquivo" type="${inscricao.projeto.arquivo.extensao}">${inscricao.projeto.arquivo.nomeArquivo}</a></li>
                        </ul>

                        <a class="botao botao-vertical" href="">Visualizar</a>
                    </div>

                    <div class="round-border">
                        <h2>Edital</h2>
                        <ul>
                            <li><span class="title">Título: </span>${inscricao.edital.titulo}</li>
                            <li><span class="title">Prazo Final: </span><fmt:formatDate type="date" dateStyle="medium" value="${inscricao.edital.prazoFinal}" /></li>
                            <li><span class="title">Arquivo: </span><a class="arquivo" href="" title="Download do arquivo" type="${inscricao.edital.arquivo.extensao}">${inscricao.edital.arquivo.nomeArquivo}</a></li>
                        </ul>

                        <a class="botao botao-vertical" href="">Visualizar</a>
                    </div>
                </div>

                <div class="round-border">
                    
                    <h2>Formulário de contemplação</h2>
                    <form title="Formulário de contemplação da inscrição" method="post">
                        
                        <label for="recado">Recado: </label>
                        <textarea id="recado" name="recado"></textarea>
                        <input class="botao round-border" type="submit" value="Contemplar" onclick="this.form.action='inscricao_contempla';"/>
                        <input class="botao round-border" type="submit" value="Não Contemplar" onclick="this.form.action='inscricao_nao_contempla';"/>
                    </form>
                </div>

            </div>

        </div>

        <div id="rodape"></div>
    </body>
</html>
