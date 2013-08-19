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


                <form:form action="projeto_edita" modelAttribute="projeto" id="form_addprojeto" enctype="multipart/form-data">
                    <h1>Editar Projeto</h1>                                                                                                  

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
                        <span class="obrigatorio">*Campo obrigatório</span>
                        <input type="text" name="titulo" value="${projeto.titulo}" required="required" />       

                        <label>Palavras chave:</label>
                        <textarea name=palavrasChave cols=35 rows=3>${projeto.palavrasChave}</textarea>

                        <label>Resumo:</label>
                        <textarea style="height: 85px; width: 500px" name=resumo cols=35 rows=5>${projeto.resumo}</textarea>


                        <span style="clear: both; display: block;"></span>

                        <span class="bloco">
                            <label for="nome">Área de conhecimento:</label><br/><br/>                             
                            <select id="area_conhecimento" name="areaConhecimento_x">                                
                                <c:forEach items="${area_conhecimento}" var="area">                                         
                                    <option  <c:if test="${projeto.areaConhecimento.getArea() eq area.getArea()}">selected="selected"</c:if> value=${area.id}>${area.area}</option>                                    
                                </c:forEach>
                            </select>
                        </span>

                        <span class="bloco">
                            <label  for="nome">Tipo projeto:</label><br/><br/> 
                            <select id="tipo_projeto" name="tipoProjeto">
                                <option <c:if test="${projeto.tipoProjeto eq 'PESQUISA'}">selected="selected"</c:if> value="<%=TipoProjeto.PESQUISA%>">Pesquisa</option>
                                <option <c:if test="${projeto.tipoProjeto eq 'ENSINO'}">selected="selected"</c:if> value="<%=TipoProjeto.ENSINO%>">Ensino</option>
                                <option <c:if test="${projeto.tipoProjeto eq 'EXTENSAO'}">selected="selected"</c:if> value="<%=TipoProjeto.EXTENSAO%>">Extensão</option>                                
                                </select>
                            </span>



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


                        <span class="bloco">
                            <label for="nome">Anexo (arquivo .PDF):</label>
                            <input style="font-size: 12px; font-weight: normal; border: none; cursor: pointer" type="file" name="arquivo_xx"/> 


                            <span style="clear: both; display: block; "></span>

                            <c:if test="${projeto.arquivo}">                                
                                <a title="Clique aqui para visualizar o arquivo" style="color: darkgreen; text-decoration: none; font-size: 14px; font-weight: bold" href="<c:url value="/arquivos/${projeto.id }/${projeto.id}.pdf"/>">Arquivo PDF (clique aqui para visualizar o arquivo)</a>        
                            </c:if>     
                        </span>    




                        <span style="clear: both; display: block;"></span>

                        <span class="bloco">
                            <label for="capitais">Custo (capital):</label>                           
                            <div id="campo_novo_capital">
                                <a href="javascript:;" onclick="javascript:AddCapital()" style="display: block; clear: both; text-decoration: none; color: black" href="#" class="adicionarCapital">Adicionar Capital</a>

                                <% int countCapitais_x = 0;%>
                                <c:forEach items="${custos}" var="custoBD">
                                    <c:if test="${custoBD.tipoCusto eq 'CAPITAL'}">
                                        <p class="campo_capital" id="campo_capital_<%=countCapitais_x%>">
                                           <span style="display:block;float:left; clear:both">
                                                <span style="display:block;float:left">Valor:</span><span style="display:block;float:left;margin-left:60px">Descrição:</span>
                                           </span>
                                            <input onkeydown="Mascara(this, Valor);" style="width: 75px; display: block; float: left" type="text" name="capital_val_x" value="${custoBD.valor}"/> 
                                            <textarea style="width: 150px; height: 35px; display: block; float: left; clear: none; margin-left: 10px; margin-top: 0px" name=capital_desc_x cols=35 rows=3>${custoBD.descricao}</textarea> 
                                            <a style="width:100px;text-decoration:none;color:black;display:block;float:left;margin-top:10px;margin-left:5px" href="#" onclick="deletarCapital('campo_capital_<%=countCapitais_x%>');
                                                    return false;">Remover Capital</a>
                                        </p>
                                        <% countCapitais_x++; %>
                                    </c:if>
                                </c:forEach>
                            </div>         
                        </span>

                        <span class="bloco">
                            <label for="capitais">Custo (custeio):</label>                           
                            <div id="campo_novo_custeio">
                                <a href="javascript:;" onclick="javascript:AddCusteio()" style="display: block; clear: both; text-decoration: none; color: black" href="#" class="adicionarCusteio">Adicionar Custeio</a>

                                <% int countCusteios_x = 0;%>
                                <c:forEach items="${custos}" var="custoBD">
                                    <c:if test="${custoBD.tipoCusto eq 'CUSTEIO'}">
                                        <p class="campo_custeio" id="campo_custeio_<%=countCusteios_x%>">
                                           <span style="display:block;float:left; clear:both">
                                                <span style="display:block;float:left">Valor:</span><span style="display:block;float:left;margin-left:60px">Descrição:</span>
                                           </span>
                                            <input onkeydown="Mascara(this, Valor);" style="width: 75px; display: block; float: left" type="text" name="custeio_val_x" value="${custoBD.valor}"/> 
                                            <textarea style="width: 150px; height: 35px; display: block; float: left; clear: none; margin-left: 10px; margin-top: 0px" name=custeio_desc_x cols=35 rows=3>${custoBD.descricao}</textarea> 
                                            <a style="width:100px;text-decoration:none;color:black;display:block;float:left;margin-top:10px;margin-left:5px" href="#" onclick="deletarCusteio('campo_custeio_<%=countCusteios_x%>');
                                                    return false;">Remover Custeio</a>
                                        </p>
                                        <% countCusteios_x++;%>
                                    </c:if>
                                </c:forEach>
                            </div>         
                        </span>





                        <span style="clear: both; display: block; "></span>


                        <label for="nome">Quero que este projeto permaneça em sigilo:</label>
                        <input <c:if test="${projeto.sigilo == true}">checked="checked"</c:if>  style=" margin-top: 10px; height: 10px; width: 20px; cursor: pointer" type="radio" name="sigilo" value="true"><span style="display: block; float: left; margin-top: 10px; ">Sim</span>
                        <input <c:if test="${projeto.sigilo == false}">checked="checked"</c:if> style=" margin-top: 10px; margin-left: 25px; clear: none; height: 10px; width: 20px; cursor: pointer" type="radio" name="sigilo" value="false" ><span style="display: block; float: left; margin-top: 10px; ">Não</span>


                            <span style="clear: both; display: block"></span>

                            <input class="enviar_form" type="submit" value="Editar"/>
                            <a href="submete_homologacao?id=${projeto.id}"><button type="button" class="submeter_form">Submeter</button></a>


                        </fieldset>
                </form:form>    




            </div>

        </div>

        <div id="rodape"></div>

    </body>
</html>

