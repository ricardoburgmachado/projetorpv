<%@page import="br.com.model.TipoEdital"%>
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
            
            var $a = jQuery.noConflict();            
            $a(function() {
                $a("#tipo_edital").multiselect({
                    multiple: false,
                    header: "Selecione um tipo",
                    noneSelectedText: "Selecione um tipo",
                });


            });
        </script>
        <!-- fim multiselect -->

      

        <!-- INICIO JQUERY TOAST MESSAGE-->
        <script type="text/javascript" src="<c:url value="recursos/js/jqueryToastMessage/jquery.toastmessage-min.js"/>" ></script>
        <script type="text/javascript" src="<c:url value="recursos/js/jqueryToastMessage/funcoes.js"/>" ></script> 
        <link rel="stylesheet" href="<c:url value="recursos/js/jqueryToastMessage/css/jquery.toastmessage-min.css"/>" />        
        <!-- FIM JQUERY TOAST MESSAGE-->


        <!-- inicio importação biblioteca Jquery calendário -->
        
        <link rel="stylesheet" href="<c:url value="/recursos/js/jquery-ui-1.10.3.custom/css/ui-lightness/jquery-ui-1.10.3.custom.css"/>" />
        <link rel="stylesheet" href="<c:url value="/recursos/js/jquery-ui-1.10.3.custom/css/ui-lightness/jquery-ui-1.10.3.custom.min.css"/>" />
        <link rel="stylesheet" href="recursos/js/jquery-ui-1.10.3.custom/development-bundle/themes/base/jquery.ui.all.css">        
        <script type="text/javascript" src="<c:url value="recursos/js/jquery-ui-1.10.3.custom/js/jquery-ui-1.10.3.custom.js"/>" ></script>       
        <script type="text/javascript" src="<c:url value="recursos/js/jquery-ui-1.10.3.custom/js/jquery-ui-1.10.3.custom.min.js"/>" ></script>        
        <script type="text/javascript" src="<c:url value="recursos/js/jquery-ui-1.10.3.custom/js/jquery-1.9.1.js"/>" ></script>
        
        <script type="text/javascript" src="<c:url value="recursos/js/jquery-ui-1.10.3.custom/development-bundle/ui/jquery.ui.datepicker.js"/>" ></script>
        <script type="text/javascript" src="<c:url value="recursos/js/jquery-ui-1.10.3.custom/development-bundle/ui/jquery.ui.core.js"/>" ></script>
        <script type="text/javascript" src="<c:url value="recursos/js/jquery-ui-1.10.3.custom/development-bundle/ui/jquery.ui.widget.js"/>" ></script>
        <script type="text/javascript" src="<c:url value="recursos/js/jquery-ui-1.10.3.custom/development-bundle/ui/jquery.ui.datepicker.js"/>" ></script>
        <script type="text/javascript" src="<c:url value="recursos/js/jquery-ui-1.10.3.custom/js/calendario.js"/>" ></script>
        <!-- fim importação biblioteca Jquery calendário -->




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


                <form:form action="edital_adiciona" modelAttribute="edital" id="form_addedital" enctype="multipart/form-data">
                    <h1>Adicionar Edital</h1>                                                                                                  

                    <fieldset> 

                        <!-- PRESTAR ATENÇÃO AQUI, DEVE SER RECUPERADO O ID DO PRO-REITOR QUE ESTARÁ NA SESSÃO -->


                        <label>Título:</label>
                        <input type="text" name="titulo" />       

                        <span style="clear: both; display: block;"></span>

                        <span class="bloco">
                            <label  for="nome">Tipo edital</label><br/><br/>                             
                            <select id="tipo_edital" name="tipoEdital">
                                <option selected="selected" value="">Selecione</option>
                                <option value="<%=TipoEdital.PESQUISA%>">Pesquisa</option>
                                <option value="<%=TipoEdital.ENSINO%>">Ensino</option>
                                <option value="<%=TipoEdital.EXTENSAO%>">Extensão</option>                                
                            </select>
                        </span>

                        <span style="clear: both; display: block;"></span>

                        <span class="bloco">
                            <label for="dataxx">Prazo Inicial:</label>
                            <input class="prazo" type="text" name="prazoInicial" id="prazoInicial" value="" />
                        </span>
                            
                        <span class="bloco">
                            <label for="dataxx">Prazo Final:</label>
                            <input class="prazo" type="text" name="prazoFinal" id="prazoFinal" value="" />
                        </span>    

                        <span style="clear: both; display: block;"></span>


                        <span class="bloco">
                            <label for="nome">Anexo (arquivo .PDF):</label>
                            <input style="font-size: 12px; font-weight: normal; border: none; cursor: pointer" type="file" name="arquivo_xx"/> 
                        </span>


                        <span style="clear: both; display: block;"></span>



                        <span style="clear: both; display: block"></span>

                        <input class="enviar_form" type="submit" value="Adicionar"/>



                    </fieldset>
                </form:form>                          


            </div>

        </div>

        <div id="rodape"></div>

    </body>
</html>

