<%@page import="br.com.model.TipoProjeto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<c:url value="/recursos/css/style.css"/>" />

        <script type="text/javascript" src="<c:url value="recursos/js/funcoes.js"/>" ></script>

        <!-- inicio opção de carregamento das bibliotecas acima setadas que são remotas -->
        <link rel="stylesheet" href="<c:url value="/recursos/js/jquery/jquery-ui.css"/>" />
        <script type="text/javascript" src="<c:url value="recursos/js/jquery/jquery2.js"/>" ></script>
        <script type="text/javascript" src="<c:url value="recursos/js/jquery/jquery-ui.min.js"/>" ></script>
        <!-- fim opção de carregamento das bibliotecas acima setadas que são remotas -->

        <!-- INICIO JQUERY TOAST MESSAGE-->
        <script type="text/javascript" src="<c:url value="recursos/js/jqueryToastMessage/jquery.toastmessage-min.js"/>" ></script>
        <script type="text/javascript" src="<c:url value="recursos/js/jqueryToastMessage/funcoes.js"/>" ></script> 
        <link rel="stylesheet" href="<c:url value="recursos/js/jqueryToastMessage/css/jquery.toastmessage-min.css"/>" />        
        <!-- FIM JQUERY TOAST MESSAGE-->

        <!--[if IE]>
            <link rel="stylesheet" type="text/css" href="<c:url value="/recursos/css/iehacks.css"/>" />
        <![endif]-->    

        <title>JSP Page</title>
    </head>

    <c:choose>
        <c:when test="${not empty mensagem}">
            <body onload="javascript:showErrorToast('${mensagem}');">
            </c:when>
            <c:otherwise>
            <body>
            </c:otherwise>
        </c:choose> 

        <div id="topo">
            <div id="logo_brasil"></div> 
            <c:import url="sub_topo.jsp"/>
        </div>

        <div id="all">

            <div id="menu">
                <c:import url="menu.jsp"/>
            </div>     


            <div id="conteudo_interno">


                <form:form action="edital_edita" modelAttribute="edital" id="form_addedital" enctype="multipart/form-data">
                    <h1>Retificar Edital</h1>                                                                                                  

                    <fieldset> 

                        <!-- PRESTAR ATENÇÃO AQUI, DEVE SER RECUPERADO O ID DO PRO-REITOR QUE ESTARÁ NA SESSÃO -->

                        <input type="hidden" name="id" value="${edital.id}" /> 

                        <label>Título:</label>
                        <input type="text" name="titulo" value="${edital.titulo}" />       

                        <span style="clear: both; display: block;"></span>
                        
                        <span class="bloco">
                            <label for="dataxx">Prazo Inicial:</label>
                            <input style="width: 175px" class="prazo" type="date" name="prazoInicial_xx" id="prazoInicialxx" value="${edital.prazoInicial}" />
                        </span>
                            
                        <span class="bloco">
                            <label for="dataxx">Prazo Final:</label>
                            <input style="width: 175px" class="prazo" type="date" name="prazoFinal_xx" id="prazoFinalxx" value="${edital.prazoFinal}" />
                        </span>    

                        <span style="clear: both; display: block;"></span>

                        <span class="bloco">
                            <label for="nome">Anexo (arquivo .PDF):</label>
                            <input style="font-size: 12px; font-weight: normal; border: none; cursor: pointer" type="file" name="arquivo_xx"/> 
                        </span>

                        <span style="clear: both; display: block"></span>
                        
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

                        <input class="enviar_form" type="submit" value="Editar"/>



                    </fieldset>
                </form:form>                          


            </div>

        </div>

        <div id="rodape"></div>

    </body>
</html>
