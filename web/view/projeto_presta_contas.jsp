<%@page import="java.util.List"%>
<%@page import="br.com.model.TipoProjeto"%>
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

                $("#partipantes_professor").multiselect({
                    multiple: true,
                    header: "Selecione professor(es)",
                    noneSelectedText: "Selecione professor(es)"
                }).multiselectfilter();
                $("#partipantes_aluno").multiselect({
                    multiple: true,
                    header: "Selecione aluno(s)",
                    noneSelectedText: "Selecione aluno(s)",
                }).multiselectfilter();
                $("#partipantes_externo").multiselect({
                    multiple: true,
                    header: "Selecione",
                    noneSelectedText: "Selecione",
                }).multiselectfilter();

                $("#area_conhecimento").multiselect({
                    multiple: false,
                    header: "Selecione uma área ",
                    noneSelectedText: "Selecione uma área",
                });
                $("#tipo_projeto").multiselect({
                    multiple: false,
                    header: "Selecione um tipo",
                    noneSelectedText: "Selecione um tipo",
                });


            });
        </script>
        <!-- fim multiselect -->

        <!-- INICIO JQUERY MONEY -->
        <script type="text/javascript" src="<c:url value="recursos/js/jquery_price/jquery.price_format.1.8.js"/>" ></script>
        <script type="text/javascript">
            jQuery(function() {
                $('#mascaraCusteio').priceFormat({
                    prefix: '',
                    thousandsSeparator: ''
                });
                $('#mascaraCapital').priceFormat({
                    prefix: '',
                    thousandsSeparator: ''
                });
            });
        </script>
        <!-- FIM JQUERY MONEY -->

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

                <%
                    if (request.getAttribute("sucesso") != null) {

                        out.println("<span class=\"sucesso\">" + request.getAttribute("sucesso") + "</span>");
                    } else if (request.getAttribute("inconsistencias") != null) {

                        List<String> inconsistencias = (List<String>) request.getAttribute("inconsistencias");
                        out.println("<ul>");
                        for (String inconsistencia : inconsistencias) {
                            out.println("<li>" + inconsistencia + "</li>");
                        }
                        out.println("</ul>");
                    }
                %>


                <form:form action="projeto_presta_contas" modelAttribute="projeto" id="form_addprojeto" enctype="multipart/form-data">
                    <h1>Prestar contas do projeto</h1>                                                                                                  

                    <fieldset> 

                        <!-- PRESTAR ATENÇÃO AQUI, DEVE SER RECUPERADO O ID DO PROFESSOR QUE ESTARÁ NA SESSÃO -->
                        <input type="hidden" name="id" value=1 />       


                        <label>Título:</label>
                        <!--<span class="obrigatorio">*</span>-->
                        <input type="text" disabled="true" name="titulo" value="${projeto.titulo}" />       

                       <span style="clear: both; display: block"></span>
                       
                        <span class="bloco">
                            <label for="nome">Anexo (arquivo .PDF):</label>
                            <input accept="application/pdf" style="font-size: 12px; font-weight: normal; border: none; cursor: pointer" type="file" name="arquivo_xx"/> 
                        </span>

                        <span style="clear: both; display: block"></span>

                        <input class="enviar_form" type="submit" value="Enviar"/>



                    </fieldset>
                </form:form>    

                <!--
                <p>
                    <a href="javascript:showSuccessToast();">notsticky</a>|
                    <a href="javascript:showStickySuccessToast();">sticky</a>
                </p>
                -->


            </div>

        </div>

        <div id="rodape"></div>

    </body>
</html>

