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


                <form:form action="projeto_adiciona" modelAttribute="projeto" id="form_addprojeto" enctype="multipart/form-data">
                    <h1>Adicionar Projeto</h1>                                                                                                  

                    <fieldset> 

                        <!-- PRESTAR ATENÇÃO AQUI, DEVE SER RECUPERADO O ID DO PROFESSOR QUE ESTARÁ NA SESSÃO -->
                        <input type="hidden" name="id" value=1 />       


                        <label>Título:</label>
                        <!--<span class="obrigatorio">*</span>-->
                        <input type="text" name="titulo" />       

                        <label>Palavras chave:</label>
                        <textarea name=palavrasChave cols=35 rows=3></textarea>

                        <label>Resumo:</label>
                        <textarea style="height: 85px; width: 500px" name=resumo cols=35 rows=5></textarea>



                        <span style="clear: both; display: block;"></span>

                        <span class="bloco">
                            <label for="nome">Área de conhecimento:</label><br/><br/>                             
                            <select id="area_conhecimento" name="areaConhecimento_x">
                                <option selected="selected" value="">Selecione</option>
                                <c:forEach items="${area_conhecimento}" var="area">                               
                                    <option value=${area.id}>${area.area}</option>
                                </c:forEach>
                            </select>
                        </span>

                        <span class="bloco">
                            <label  for="nome">Tipo projeto:</label><br/><br/>                             
                            <select id="tipo_projeto" name="tipoProjeto">
                                <option selected="selected" value="">Selecione</option>
                                <option value="<%=TipoProjeto.PESQUISA%>">Pesquisa</option>
                                <option value="<%=TipoProjeto.ENSINO%>">Ensino</option>
                                <option value="<%=TipoProjeto.EXTENSAO%>">Extensão</option>                                
                            </select>
                        </span>




                        <span style="clear: both; display: block"></span>

                        <span class="bloco">
                            <label>Participantes (alunos):</label><br/><br/>                           
                            <select id="partipantes_aluno" name="participantes_aluno" multiple="multiple">
                                <c:forEach items="${participantes_aluno}" var="aluno">                               
                                    <option value=${aluno.id}>${aluno.nome}</option>
                                </c:forEach>
                            </select>
                        </span>

                        <span class="bloco">
                            <label for="nome">Participantes (professores):</label><br/><br/>                                                 
                            <select id="partipantes_professor" name="participantes_professor" multiple="multiple">
                                <c:forEach items="${participantes_professor}" var="professor">                               
                                    <option value=${professor.id}>${professor.nome}</option>
                                </c:forEach>
                            </select>
                        </span>

                        <span class="bloco">
                            <label for="nome">Participantes (Externos):</label><br/><br/>                      
                            <select id="partipantes_externo" name="participantes_externo" multiple="multiple">
                                <c:forEach items="${participantes_externo}" var="externo">                               
                                    <option value=${externo.id}>${externo.nome}</option>
                                </c:forEach>
                            </select>
                        </span>

                        <span class="bloco">
                            <label for="nome">Anexo (arquivo .PDF):</label>
                            <input style="font-size: 12px; font-weight: normal; border: none; cursor: pointer" type="file" name="arquivo_xx"/> 
                        </span>


                        <span style="clear: both; display: block;"></span>


                        <span class="bloco">
                            <label for="capitais">Custo (capital):</label>                            
                            <div id="campo_novo_capital"></div>
                            <a href="javascript:;" onclick="javascript:AddCapital()" style="display: block; clear: both; text-decoration: none; color: black" href="#" class="adicionarCapital">Adicionar Capital</a>
                        </span>


                        <span class="bloco">
                            <label for="capitais">Custo (custeio):</label>                            
                            <div id="campo_novo_custeio"></div>
                            <a href="javascript:;" onclick="javascript:AddCusteio()" style="display: block; clear: both; text-decoration: none; color: black" href="#" class="adicionarCapital">Adicionar Custeio</a>
                        </span>

                        <span style="clear: both; display: block; "></span>

                        <label for="nome">Quero que este projeto permaneça em sigilo:</label>
                        <input style=" margin-top: 10px; height: 10px; width: 20px; cursor: pointer" type="radio" name="sigilo" value="true"><span style="display: block; float: left; margin-top: 10px; ">Sim</span>
                        <input style=" margin-top: 10px; margin-left: 25px; clear: none; height: 10px; width: 20px; cursor: pointer" type="radio" name="sigilo" value="false" checked="checked"><span style="display: block; float: left; margin-top: 10px; ">Não</span>


                        <span style="clear: both; display: block"></span>

                        <input class="enviar_form" type="submit" value="Adicionar"/>



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

