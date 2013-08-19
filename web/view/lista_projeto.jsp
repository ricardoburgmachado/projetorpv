<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" href="<c:url value="/recursos/css/style.css"/>" />
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

                <h1>Listar Projetos</h1>


                <table id="lista_projetos">
                    <c:choose>
                        <c:when test="${projetos != null}">

                            <tr class="sub_titulo">
                                <td class="titulo">TITULO</td>
                                <td class="tipo">TIPO</td>
                                <td class="status">STATUS</td>
                                <td class="funcoes">AÇÕES</td>
                            </tr>
                            <c:forEach items="${projetos}" var="projeto">

                                <tr class="linealt">
                                <a>
                                    <td class="titulo">${projeto.titulo}</td>
                                    <td class="tipo" >${projeto.tipoProjeto}</td>
                                    <td class="status">NÃO HOMOLOGADO</td>
                                    <td class="edita"><a title="Clique aqui para editar Projeto" href="projeto_edita_show?id=${projeto.id}"></a></td>
                                    <td class="exibe"><a title="Clique aqui para exibir detalhes do projeto" href="projeto_exibe?id=${projeto.id}"></a></td>
                                </a>
                                </tr>
                            </c:forEach>


                        </c:when>
                        <c:otherwise>
                            Não existe nenhum projeto cadastrado!
                        </c:otherwise>
                    </c:choose>

                </table>




            </div>

        </div>

        <div id="rodape"></div>

    </body>


</html>
