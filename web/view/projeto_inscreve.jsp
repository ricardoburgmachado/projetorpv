<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" href="<c:url value="/recursos/css/style.css"/>" />
        <script type="text/javascript" src="<c:url value="recursos/js/funcoes.js"/>" ></script>
        <script type="text/javascript" src="<c:url value="recursos/js/ajax.js"/>" ></script>

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

        <title>Inscrever projeto em edital</title>
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

                <h1>Inscrição de projeto em edital</h1>

                <c:if test="${not empty inconsistencias}">
                    <div id="inconsistencias">
                        <ul>
                            <c:forEach var="inconsistencia" items="${inconsistencias}">
                                <li>${inconsistencia}</li>
                                </c:forEach>
                        </ul>
                    </div>
                </c:if>

                <div id="inscricao_edital">

                    <form action="inscreve_edital" method="POST" enctype="multipart/form-data">


                        <fieldset class="field">
                            <legend title="Projeto"></legend>
                            <label for="projetos">Projeto</label>

                            <select id="projetos" name="id_projeto" onchange="javascript:getEditaisAjax();">

                                <option value="0">Selecione um projeto...</option>
                                <c:forEach var="projeto" items="${projetos}">
                                    <option  value="${projeto.id}">${fn:length(projeto.titulo) > 40 ? fn:substring(projeto.titulo, 0, 37).concat('...') : projeto.titulo}</option>                               
                                </c:forEach>
                            </select>
                        </fieldset>

                        <fieldset class="field">
                            <legend title="Edital"></legend>
                            <label for="editais">Edital</label>

                            <select name="id_edital" id="editais"></select>
                        </fieldset>

                        <label for="file">Arquivo necessário</label>
                        <input type="file" id="file" name="file"><br>

                        <input type="submit" value="Inscrever" class="botao">                        
                    </form>
                </div>

            </div>

            <div id="rodape"></div>

    </body>


</html>
