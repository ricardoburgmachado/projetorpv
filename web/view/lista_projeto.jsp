<%@page import="br.com.model.Projeto"%>
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
            <body onload="javascript:showSuccessToast('${mensagem}');">
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

                <h1>Listar Projetos</h1>

                <table id="lista_projetos">                    
                    <c:choose>
                        <c:when test="${projetos != null && projetos.size() > 0}">

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
                                    <td class="status">${projeto.status.descricao }</td>
                                    <td class="edita"><a title="Clique aqui para editar Projeto" href="projeto_edita_show?id=${projeto.id}"></a></td>
                                    <td class="exibe"><a title="Clique aqui para exibir detalhes do projeto" href="projeto_exibe?id=${projeto.id}"></a></td>                                    
                                    <td class="recados_icon"><a title="Clique aqui para visualizar os recados do projeto" href="recados_lista?id_projeto=${projeto.id}"></a></td>
                                    <c:if test="${ !projeto.isExecutando()}">                                        
                                    <td class="presta_contas"><a title="Clique aqui para realizar a prestação de contas" href="projeto_presta_contas_show?id=${projeto.id}"></a></td>
                                    </c:if>                                    
                                </a>
                                </tr>
                            </c:forEach>


                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td class="titulo">
                                    Não existe nenhum projeto cadastrado!
                                </td>
                            </tr>
                        </c:otherwise>
                    </c:choose>

                </table>

            </div>

        </div>

        <div id="rodape"></div>

    </body>


</html>
