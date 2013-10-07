<%@page import="java.util.List"%>
<%@page import="br.com.controller.ProjetoController"%>
<%@page import="br.com.model.TipoProjeto"%>
<%@page import="br.com.model.StatusProjeto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" href="<c:url value="/recursos/css/style.css"/>" />


        <!-- inicio multiselect -->
        <!--<link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/ui-lightness/jquery-ui.css" /> -->
        <!--<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.js"></script>-->
        <!--<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1/jquery-ui.min.js"></script>-->        

        <!-- inicio opção de carregamento das bibliotecas acima setadas que são remotas -->
        <link rel="stylesheet" href="<c:url value="/recursos/js/jquery/jquery-ui.css"/>" />
        <script type="text/javascript" src="<c:url value="recursos/js/jquery/jquery2.js"/>" ></script>
        <script type="text/javascript" src="<c:url value="recursos/js/jquery/jquery-ui.min.js"/>" ></script>
        <!-- fim opção de carregamento das bibliotecas acima setadas que são remotas -->

        <script type="text/javascript" src="<c:url value="recursos/js/multiselect/src/jquery.multiselect.js"/>" ></script>
        <script type="text/javascript" src="<c:url value="recursos/js/multiselect/src/jquery.multiselect.filter.js"/>" ></script>

        <link rel="stylesheet" href="<c:url value="recursos/js/multiselect/jquery.multiselect.css"/>" />        
        <script type="text/javascript">
            $(function() {

                $("#status_projeto").multiselect({
                    multiple: false,
                    header: "Selecione um status",
                    noneSelectedText: "Selecione um status",
                });


            });
        </script>
        <!-- fim multiselect -->

        <!-- INICIO JQUERY TOAST MESSAGE-->
        <script type="text/javascript" src="<c:url value="recursos/js/jqueryToastMessage/jquery.toastmessage-min.js"/>" ></script>
        <script type="text/javascript" src="<c:url value="recursos/js/jqueryToastMessage/funcoes.js"/>" ></script> 
        <link rel="stylesheet" href="<c:url value="recursos/js/jqueryToastMessage/css/jquery.toastmessage-min.css"/>" />        
        <!-- FIM JQUERY TOAST MESSAGE-->
        
        

        <script type="text/javascript" src="<c:url value="recursos/js/funcoes.js"/>" ></script>


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


                <form:form action="projeto_altera_status" modelAttribute="projeto" id="form_addprojeto" enctype="multipart/form-data">
                    <h1>Alterar status do projeto</h1>                                                                                                  

                    <fieldset> 
                        
                        <%                             
                            if(request.getAttribute("sucesso") != null){
                                
                               out.println("<span class=\"sucesso\">" + request.getAttribute("sucesso") + "</span>");
                            }else if(request.getAttribute("inconsistencias") != null){
                                
                                List<String> inconsistencias = (List<String>) request.getAttribute("inconsistencias");
                                out.println("<ul>");
                                for(String inconsistencia: inconsistencias){
                                    out.println("<li>" + inconsistencia + "</li>");
                                }
                                out.println("</ul>");
                            }
                            
                        %>

                        <!-- PRESTAR ATENÇÃO AQUI, DEVE SER RECUPERADO O ID DO PROFESSOR QUE ESTARÁ NA SESSÃO -->
                        <input type="hidden" name="id" value=${projeto.id} />       


                        <label>Título:</label>

                        <input style="width: 500px" type="text" disabled="true" name="titulo" value="${projeto.titulo}" />       

                        <span style="clear: both; display: block;"></span>

                        <span class="bloco">
                            <label  for="nome">Status projeto:</label><br/><br/> 
                            <select id="status_projeto" name="status">
                                <option <c:if test="${projeto.status eq 'CRIADO'}">selected="selected"</c:if> value="<%=StatusProjeto.CRIADO%>">Não homologado</option>
                                <option <c:if test="${projeto.status eq 'SUBMETIDO_HOMOLOGACAO'}">selected="selected"</c:if> value="<%=StatusProjeto.SUBMETIDO_HOMOLOGACAO%>">Submetido para homologação</option>
                                <option <c:if test="${projeto.status eq 'HOMOLOGADO'}">selected="selected"</c:if> value="<%=StatusProjeto.HOMOLOGADO%>">Homologado</option>
                                <option <c:if test="${projeto.status eq 'INSCRITO'}">selected="selected"</c:if> value="<%=StatusProjeto.INSCRITO%>">Inscrito</option>                                
                            </select>
                         </span>

                            
                         <span style="clear: both; display: block;"></span>
                            <span class="bloco">
                            <label for="nome">Anexo (respaldo da avaliação):</label>
                            <input style="font-size: 12px; font-weight: normal; border: none; cursor: pointer" type="file" name="arquivo_xx"/> 
                         </span>

                            
                        <span style="clear: both; display: block;"></span>

                            <input class="enviar_form" type="submit" value="Alterar status"/>
                        </fieldset>
                </form:form>    




            </div>

        </div>

        <div id="rodape"></div>

    </body>
</html>

