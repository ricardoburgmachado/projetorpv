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

                <div class="inside-conteudo">
                    <c:if test="${not empty inconsistencias}">
                        <ul class="inconsistencias">
                            <c:forEach var="inconsistencia" items="${inconsistencias}">
                                <li>${inconsistencia}</li>
                                </c:forEach>
                        </ul>
                    </c:if>

                    <div class="round-border div-exibe-objeto">
                        <h2>Projeto</h2>

                        <div class="flexbox flex-space">
                            <ul class="lista-objeto">
                                <li><span class="title">Título: </span>${inscricao.projeto.titulo}</li>
                                <li><span class="title">Palavras-chave: </span>${inscricao.projeto.palavrasChave}</li>
                                <li><span class="title">Arquivo: </span><c:if test="${inscricao.projeto.arquivo}"><a title="Clique aqui para visualizar o arquivo" href="<c:url value="/arquivos/${inscricao.projeto.id }/${inscricao.projeto.id}.pdf"/>">Clique aqui para visualizar o arquivo</a></c:if></li>
                                </ul>

                                <a class="botao botao-vertical" title="Visualizar dados do projeto" href="projeto_exibe?id=${inscricao.projeto.id}">Visualizar</a>
                            </div>
                        </div>

                        <div class="round-border div-exibe-objeto">
                            <h2>Edital</h2>

                            <div class="flexbox flex-space">
                                <ul class="lista-objeto">
                                    <li><span class="title">Título: </span>${inscricao.edital.titulo}</li>
                                <li><span class="title">Prazo Final: </span><fmt:formatDate type="date" dateStyle="medium" value="${inscricao.edital.prazoFinal}" /></li>
                                <li><span class="title">Arquivo: </span><a class="arquivo" href="arquivo_edital_download?id_edital=${inscricao.edital.id}&id_arquivo=${inscricao.edital.arquivo.idArquivo}" title="Download do arquivo" type="${inscricao.edital.arquivo.extensao}">${inscricao.edital.arquivo.nomeArquivo}</a></li>
                            </ul>

                            <a class="botao botao-vertical" title="Visualizar dados do edital" href="edital_exibe?id=${inscricao.edital.id}">Visualizar</a>
                        </div>
                    </div>

                    <div class="round-border lista-objeto" id="form-contempacao-container">

                        <h2>Formulário de contemplação</h2>
                        
                        <span class="title">Arquivo da inscrição: </span><a class="arquivo" href="down_arquivo_inscricao?id_projeto=${inscricao.projeto.id}&id_edital=${inscricao.edital.id}" title="Download do arquivo" type="${inscricao.arquivo.extensao}">${inscricao.arquivo.nomeArquivo}</a>
                        
                        <form id="form-contemplacao" name="form" title="Formulário de contemplação da inscrição" method="post">

                            <input type="hidden"  name="id_projeto" value="${inscricao.projeto.id}"/>
                            <input type="hidden"  name="id_edital" value="${inscricao.edital.id}"/>                                                 
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
