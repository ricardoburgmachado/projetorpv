<%-- 
    Document   : lista_projeto_coord
    Created on : 04/10/2013, 08:09:34
    Author     : rafael
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Projetos para Homologação</title>
        <link rel="stylesheet" href="<c:url value="/recursos/css/style.css"/>">
    </head>
    <body>
        <div id="all">

            <div id="menu">
                <c:import url="menu.jsp"/>
            </div>     

            <div id="conteudo_interno">
                <h1>Projetos para Homologação</h1>
                
                <table class="tabela-lista" title="Tabela de Projetos">
                    <thead>
                        <tr>
                            <th>Título do Projeto</th>
                            <th>Categoria</th>
                            <th>Arquivo de Projeto</th>
                            <th colspan="3">Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="projeto" items="${projetos}">
                        <tr>
                            <td title="Título do projeto">${projeto.titulo}</td>
                            <td title="Categoria do projeto">${projeto.tipo}</td>
                            <td title="Arquivo do projeto"><a href="arq_proj_down?id_proj=${projeto.id}&id_arq=${projeto.arquivo.id}" type="${projeto.arquivo.extensao}">${projeto.arquivo.titulo}</a></td>
                            <td title="Ação para homologação"><a title="Clique para homologar o projeto" href="projeto_homologa?id_proj=${projeto.id}">homologar</a></td>
                            <td title="Ação para não homologação"><a title="Clique para não homologar o projeto" href="projeto_nao_homologa?id_proj=${projeto.id}">não homologar</a></td>
                            <td title="Ação para visualização"><a title="Clique para visualizar detalhes do projeto" href="projeto_exibe?id=${projeto.id}">detalhes</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

        </div>

        <div id="rodape"></div>
    </body>
</html>
