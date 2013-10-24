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

                <div id="edital_exibe">
                    <h1>Exibir Edital</h1>

                    <c:if test="${not empty inconsistencias}">
                        <div id="inconsistencias">
                            <ul>
                                <c:forEach var="inconsistencia" items="${inconsistencias}">
                                    <li>${inconsistencia}</li>
                                    </c:forEach>
                            </ul>
                        </div>
                    </c:if>
                    
                    <c:if test="${not empty edital}">
                        
                        
                        <label>Título:</label>
                        <input disabled="true" type="text" name="titulo" value="${edital.titulo}" />  
                        
                        <label>Início:</label>
                        <input disabled="true" type="text" name="titulo" value="<fmt:formatDate type="date" value="${edital.prazoInicial}"/>" />  
                        
                        <label>Fim:</label>
                        <input disabled="true" type="text" name="titulo" value="<fmt:formatDate type="date" value="${edital.prazoFinal}"/>" />  
                        
                        <label>Tipo:</label>
                        <input disabled="true" type="text" name="titulo" value="${edital.tipo}" />  
                        
                        
                        <span style="clear: both; display: block;"></span>
 
                        <br/>
                        <a class="show_edital" href="arquivo_edital_download?id_edital=${edital.id}&id_arquivo=${edital.arquivo.idArquivo}" title="Clique aqui para visualizar o edital">
                            Visualizar Edital
                        </a>
                        <% int count = 1;%>    
                        <c:forEach var="retificacao" items="${edital.retificacoes}">
                            <a class="show_retificacao" href="arquivo_edital_download?id_edital=${edital.id}&id_arquivo=${retificacao.idArquivo}" title="Clique aqui para visualizar a retificação">
                                Visualizar Retificação <%=count%>
                            </a>        
                            <% count++;%>        
                        </c:forEach>

                        <span style="clear: both; display: block"></span>
                      
                    </c:if>
                </div>
            </div>    
        </div>
    </body>
</html>
