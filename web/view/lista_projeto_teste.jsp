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
        <link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/ui-lightness/jquery-ui.css" />
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.js"></script>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1/jquery-ui.min.js"></script>        

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


        <script type="text/javascript" src="<c:url value="recursos/js/funcoes.js"/>" ></script>


        <!--[if IE]>
            <link rel="stylesheet" type="text/css" href="<c:url value="/recursos/css/iehacks.css"/>" />
        <![endif]-->    

        <title>JSP Page</title>
    </head>
    <body>

        <div id="topo">
            <div id="logo_brasil"></div> 

        </div>

        <div id="all">

            <div id="menu">
                <c:import url="menu.jsp"/>
            </div>     


            <div id="conteudo_interno">

                <h1>Lista de Projetos(simulação)</h1>
                <table>
                    <tr>
                        <td><a href="projeto_edita_show?id=1">projeto 1</a></td>
                    </tr>
                    <br/><br/>
                    <tr>
                        <td><a href="projeto_edita_show?id=2">projeto 2</a></td>
                    </tr>
                    <br/><br/>
                    <tr>
                        <td><a href="projeto_edita_show?id=3">projeto 3</a></td>
                    </tr>
                    <br/><br/>
                    <tr>
                        <td><a href="projeto_edita_show?id=25">projeto 25</a></td><br/><br/>                        
                    </tr>
                    <br/><br/>
                    <tr>
                        <td><a href="projeto_edita_show?id=26">projeto 26</a></td><br/><br/>                        
                    </tr>
                </table>






            </div>

        </div>

        <div id="rodape"></div>

    </body>
</html>

