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

                <h1>Detalhes do Projeto</h1>                                                                                                  

                <table id="exibe_projeto">
                    <tr>
                        <td class="titulo"><b>Titulo:</b> ${projeto.titulo}</td>
                        <td class="palavras_chave"><b>Palavras chave:</b> ${projeto.palavrasChave}</td>
                    </tr>
                    <tr>
                        <td class="resumo"><b>Resumo:</b> ${projeto.resumo}</td>
                    </tr>
                    <tr>
                        <td class="area_conhecimento"><b>Área de conhecimento:</b> ${projeto.areaConhecimento.area}</td>
                        <td class="tipo_projeto"><b>Tipo de Projeto:</b> ${projeto.tipoProjeto}</td>
                    </tr>
                    <tr>
                        <td class="subtitulo">
                            PARTICIPANTES
                        </td>
                    </tr>
                    <tr>

                        <td class="part_alunos"><b>Participantes(aluno):</b>
                            <c:choose>
                                <c:when test="${projeto.participantesAluno != null}">
                                    <br/>
                                    <c:forEach items="${projeto.participantesAluno}" var="aluno">
                                        ${aluno.nome}<br/>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    Nenhum participante (aluno) esta relacionado a este projeto
                                </c:otherwise>
                            </c:choose>
                        </td>

                        <td class="part_professores"><b>Participantes(professor):</b> 
                            <c:choose>
                                <c:when test="${projeto.participantesProfessor != null}">
                                    <br/>
                                    <c:forEach items="${projeto.participantesProfessor}" var="professor">
                                        ${professor.nome}<br/>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    Nenhum participante (professor) esta relacionado a este projeto
                                </c:otherwise>
                            </c:choose>
                        </td>

                        <td class="part_externo"><b>Participantes(externo):</b> 
                            <c:choose>
                                <c:when test="${projeto.participantesExterno != null}">
                                    <br/>
                                    <c:forEach items="${projeto.participantesExterno}" var="externo">
                                        ${externo.nome}<br/>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    Nenhum participante (externo) esta relacionado a este projeto
                                </c:otherwise>
                            </c:choose>
                        </td>                        

                    </tr>
                    <tr>
                        <td class="subtitulo">
                            CUSTOS (CAPITAL)
                        </td>
                    </tr>

                    <tr>
                        <c:forEach items="${custos}" var="custoDB_X">
                            <c:if test="${custoDB_X.tipoCusto eq 'CAPITAL'}">
                                <c:set var="countCapitais" value="1"></c:set>
                            </c:if>
                        </c:forEach>                      



                        <c:choose>
                            <c:when test="${countCapitais > 0}">
                            <br/>

                            <c:forEach items="${custos}" var="custoDB">
                                <c:if test="${custoDB.tipoCusto eq 'CAPITAL'}">
                                    <td class="custo_cap_valor"><b>Valor:</b> R$ ${custoDB.valor}</td>    
                                    <td class="custo_cap_desc"><b>Descrição:</b>${custoDB.descricao}</td>
                                </c:if>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <td class="custo_cap_valor" style="color: red">Nenhum custo (capital) cadastrado</td>                                
                        </c:otherwise>
                    </c:choose>                   
                    </tr>

                    <tr>
                        <td class="subtitulo">
                            CUSTOS (CUSTEIO)
                        </td>
                    </tr>

                    <tr>
                        <c:forEach items="${custos}" var="custoDB">
                            <c:if test="${custoDB.tipoCusto eq 'CUSTEIO'}">
                                <c:set var="countCusteios" value="1"></c:set>
                            </c:if>
                        </c:forEach>                        
                        <c:choose>
                            <c:when test="${countCusteios > 0}">
                            <br/>
                            <c:forEach items="${custos}" var="custoDB">
                                <c:if test="${custoDB.tipoCusto eq 'CUSTEIO'}">
                                    <td class="custo_cust_valor"><b>Valor:</b> R$ ${custoDB.valor}</td>    
                                    <td class="custo_cust_desc"><b>Descrição:</b>${custoDB.descricao}</td>
                                </c:if>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <td class="custo_cust_valor" style="color: red">Nenhum custo (custeio) cadastrado</td>                                
                        </c:otherwise>
                    </c:choose>                                   
                    </tr>
                    <tr>
                        <c:choose>
                            <c:when test="${projeto.arquivo}">                                
                                <td class="arquivo"><b>Arquivo (.pdf):</b><a title="Clique aqui para visualizar o arquivo" style="color: darkgreen; text-decoration: none; font-size: 14px; font-weight: bold" href="<c:url value="/arquivos/${projeto.id }/${projeto.id}.pdf"/>"> Clique aqui para visualizar o arquivo</a></td>        
                            </c:when>
                            <c:otherwise>
                                <td class="arquivo"><b>Arquivo (.pdf):</b> Nenhum arquivo armazenado</td>
                            </c:otherwise>
                        </c:choose>

                        <c:choose>
                            <c:when test="${projeto.sigilo}">
                                <td class="sigilo"><b>Projeto em sigilo:</b> Sim</td>
                            </c:when>
                            <c:otherwise>
                                <td class="sigilo"><b>Projeto em sigilo:</b> Não</td>
                            </c:otherwise>
                        </c:choose>

                    </tr>


                </table>

                <a id="excluir_projeto" href="projeto_exclui?id=${projeto.id}" >Deletar projeto</a>


            </div>

        </div>

        <div id="rodape"></div>

    </body>
</html>

