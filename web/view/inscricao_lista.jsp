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
        <title>GIPA - Lista de Incrições</title>

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

                <h1>Lista de Inscrições</h1>

                <c:if test="${not empty inconsistencias}">
                    <ul class="inconsistencias">
                        <c:forEach var="inconsistencia" items="${inconsistencias}">
                            <li>${inconsistencia}</li>
                            </c:forEach>
                    </ul>
                </c:if>

                <div class="listagem">
                    <c:forEach var="inscricao" items="${inscricoes}">
                        <c:choose>
                            <c:when test="${inscricao.aprovada}">
                                <div class="item-lista inscricao_aprovada">
                                </c:when>
                                <c:otherwise>
                                    <div class="item-lista item-lista-background">
                                    </c:otherwise>
                                </c:choose>

                                <ul title="Dados da inscrição">
                                    <li><span class="title">Projeto:</span> ${inscricao.projeto.titulo}</li>
                                    <li><span class="title">Edital:</span> ${inscricao.edital.titulo}</li>
                                        <c:choose>
                                            <c:when test="${empty inscricao.arquivo.dados}">
                                            <li><span class="title">Arquivo:</span></li>
                                            </c:when>
                                            <c:otherwise>
                                            <li><span class="title">Arquivo:</span><a class="arquivo" href="down_arquivo_inscricao?id_projeto=${inscricao.projeto.id}&id_edital=${inscricao.edital.id}" title="Download do arquivo" type="${inscricao.arquivo.extensao}"> ${inscricao.arquivo.nomeArquivo}</a></li>
                                            </c:otherwise>
                                        </c:choose>
                                    <li><span class="title">Prazo final do edital:</span> <fmt:formatDate type="date" dateStyle="medium" value="${inscricao.edital.prazoFinal}" /></li>
                                </ul>

                                <c:if test="${(now <= inscricao.edital.prazoFinal) && (not inscricao.aprovada)}">
                                    <a class="botao botao-vertical" title="Cancelar inscrição" href="inscricao_cancela?id_projeto=${inscricao.projeto.id}&id_edital=${inscricao.edital.id}">Cancelar</a>
                                </c:if>
                            </div>
                        </c:forEach>
                        <div class="legenda_listagem">
                            <dl>
                                <dt>Legenda:</dt>
                                <dd class="legenda_inscricao_aprovada" title="Cor verde">Inscrição aprovada</dd>
                            </dl>
                        </div>
                    </div>

                </div>

            </div>

            <div id="rodape"></div>
    </body>
</html>
