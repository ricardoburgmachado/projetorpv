<%@page import="java.util.List"%>
<%@page import="br.com.controller.ProjetoController"%>
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

        <!-- INICIO JQUERY TOAST MESSAGE-->
        <script type="text/javascript" src="<c:url value="recursos/js/jqueryToastMessage/jquery.toastmessage-min.js"/>" ></script>
        <script type="text/javascript" src="<c:url value="recursos/js/jqueryToastMessage/funcoes.js"/>" ></script> 
        <link rel="stylesheet" href="<c:url value="recursos/js/jqueryToastMessage/css/jquery.toastmessage-min.css"/>" />        
        <!-- FIM JQUERY TOAST MESSAGE-->



        <script type="text/javascript" src="<c:url value="recursos/js/funcoes.js"/>" ></script>


        <!--[if IE]>
            <link rel="stylesheet" type="text/css" href="<c:url value="/recursos/css/iehacks.css"/>" />
        <![endif]-->    

        <title>Edição de Projeto em Execução</title>
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


                <form:form action="projeto_edita" modelAttribute="projeto" id="form_addprojeto" enctype="multipart/form-data">
                    <h1>Editar Projeto</h1>                                                                                                  

                    <fieldset> 

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

                        <!-- PRESTAR ATENÇÃO AQUI, DEVE SER RECUPERADO O ID DO PROFESSOR QUE ESTARÁ NA SESSÃO -->
                        <input type="hidden" name="id" value=${projeto.id} />            

                        <label>Palavras chave:</label>
                        <textarea name=palavrasChave cols=35 rows=3>${projeto.palavrasChave}</textarea>

                        <label>Resumo:</label>
                        <textarea style="height: 85px; width: 500px" name=resumo cols=35 rows=5>${projeto.resumo}</textarea>

                        <span style="clear: both; display: block"></span>

                        <span class="bloco">
                            <label>Participantes (alunos):</label><br/><br/>                           
                            <select id="partipantes_aluno" name="participantes_aluno" multiple="multiple">
                                <c:forEach items="${participantes_aluno}" var="aluno">                                        
                                    <option <c:forEach items="${projeto.participantesAluno}" var="alunoSel"><c:if test="${aluno.id == alunoSel.id}">selected="selected"</c:if></c:forEach>  value=${aluno.id}>${aluno.nome}</option>                                                                    
                                </c:forEach>
                            </select>
                        </span>

                        <span class="bloco">
                            <label for="nome">Participantes (professores):</label><br/><br/>                                                 
                            <select id="partipantes_professor" name="participantes_professor" multiple="multiple">
                                <c:forEach items="${participantes_professor}" var="professor">                                                                                                                         
                                    <option <c:forEach items="${projeto.participantesProfessor}" var="professorSel"> <c:if test="${professor.id == professorSel.id}">selected="selected"</c:if></c:forEach> value=${professor.id}>${professor.nome}</option>                                                                                                                                                                                                    
                                </c:forEach>                                
                            </select>
                        </span>

                        <span class="bloco">
                            <label for="nome">Participantes (Externos):</label><br/><br/>                      
                            <select id="partipantes_externo" name="participantes_externo" multiple="multiple">
                                <c:forEach items="${participantes_externo}" var="externo">                                                                 
                                    <option <c:forEach items="${projeto.participantesExterno}" var="externoSel"><c:if test="${externo.id == externoSel.id}">selected="selected"</c:if></c:forEach>  value=${externo.id}>${externo.nome}</option>
                                </c:forEach>
                            </select>
                        </span>

                        <span style="clear: both; display: block;"></span>
                        <input class="enviar_form" type="submit" value="Editar"/>
                    </fieldset>
                </form:form>
            </div>

        </div>

        <div id="rodape"></div>

    </body>
</html>

