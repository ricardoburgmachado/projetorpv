<%-- 
    Document   : edital_exibe
    Created on : 23/09/2013, 01:18:06
    Author     : rafael
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<c:url value="/recursos/css/style.css"/>"> 
        <title>GIPA - Exibir ${edital == null ? 'Edital' : edital.titulo}</title>
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

                <h1>Exibir Edital - ${edital == null ? '' : edital.titulo}</h1>

                <c:if test="${not empty inconsistencias}">
                    <div class="inconsistencias">
                        <ul>
                            <c:forEach var="inconsistencia" items="${inconsistencias}">
                                <li>${inconsistencia}</li>
                                </c:forEach>
                        </ul>
                    </div>
                </c:if>
                <c:if test="${not empty edital}">
                    <table>
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
                                <td>${edital.arquivo.nomeArquivo}</td>
                            </tr>
                            <c:forEach var="retificacao" items="${edital.retificacoes}">
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td><a href="arquivo_edital_download?id_edital=${edital.id}&id_arquivo=${retificacao.idArquivo}">${retificacao.nomeArquivo}</a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                        <div class="botoes">
                            <ul>
                                <li><span class="botao"><a href="edital_edita?id=${edital.id}">Editar</a></span></li>
                                <li><span class="botao"><a href="edital_exclui?id=${edital.id}">Excluir</a></span></li>
                            </ul>
                        </div>
                </c:if>
            </div>
        </div>
    </body>
</html>
