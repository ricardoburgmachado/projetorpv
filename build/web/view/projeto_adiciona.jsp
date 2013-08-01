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
        <script type="text/javascript" src="assets/prettify.js"></script>        
        <script type="text/javascript" src="<c:url value="recursos/js/multiselect/src/jquery.multiselect.js"/>" ></script>
        <script type="text/javascript" src="<c:url value="recursos/js/multiselect/src/jquery.multiselect.filter.js"/>" ></script>
        <script type="text/javascript" src="<c:url value="recursos/js/multiselect/assets/prettify.js"/>" ></script>
        <link rel="stylesheet" href="<c:url value="recursos/js/multiselect/jquery.multiselect.css"/>" />        
        <script type="text/javascript">
            $(function() {

                $("#partipantes_professor").multiselect().multiselectfilter();
                /*
                 $("#partipantes_professor").multiselect({
                 multiple: true,
                 header: "Selecione professor(es)",
                 noneSelectedText: "Selecione professor(es)",
                 selectedList: 1
                 });
                 */
                $("#partipantes_aluno").multiselect({
                    multiple: true,
                    header: "Selecione aluno(s)",
                    noneSelectedText: "Selecione aluno(s)",
                });
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

                <form:form action="projeto_adiciona" modelAttribute="projeto" id="form_addprojeto" enctype="multipart/form-data">
                    <h1>Adicionar Projeto</h1>                                                                                                  

                    <fieldset> 


                        <!-- PRESTAR ATENÇÃO AQUI, DEVE SER RECUPERADO O ID DO PROFESSOR QUE ESTARÁ NA SESSÃO -->
                        <input type="hidden" name="professor.id" />       

                        
                        <label for="nome">Título:</label>
                        <span class="obrigatorio">*Campo obrigatório</span>
                        <input type="text" name="titulo" required="required" />       

                        <label for="nome">Palavras chave:</label>
                        <textarea name=palavrasChave cols=35 rows=3></textarea>

                        <label for="nome">Resumo:</label>
                        <textarea style="height: 85px; width: 500px" name=resumo cols=35 rows=5></textarea>

                        <span style="clear: both; display: block"></span>

                        <label for="nome">Participantes:</label><br/><br/>                                                
                        <select id="partipantes_aluno" name="demo">
                            <option value="option1">Option 1</option>
                            <option value="option2">Option 2</option>
                            <option value="option3">Option 3</option>
                            <option value="option4">Option 4</option>
                            <option value="option5">Option 5</option>
                            <option value="option6">Option 6</option>
                            <option value="option7">Option 7</option>
                        </select>


                        <select id="partipantes_professor" name="demo">
                            <option value="option1">Option 1</option>
                            <option value="option2">Option 2</option>
                            <option value="option3">Option 3</option>
                            <option value="option4">Option 4</option>
                            <option value="option5">Option 5</option>
                            <option value="option6">Option 6</option>
                            <option value="option7">Option 7</option>
                        </select>

                        <span style="clear: both; display: block;"></span>

                        <span class="bloco">
                            <label for="nome">Área de conhecimento:</label><br/><br/> 
                            <select id="area_conhecimento" name="demo">
                                <option value="option1">Option 1</option>
                                <option value="option2">Option 2</option>
                                <option value="option3">Option 3</option>
                                <option value="option4">Option 4</option>
                                <option value="option5">Option 5</option>
                                <option value="option6">Option 6</option>
                                <option value="option7">Option 7</option>
                            </select>
                        </span>

                        <span class="bloco">
                            <label  for="nome">Tipo projeto:</label><br/><br/> 
                            <select id="tipo_projeto" name="demo">
                                <option value="option1">Option 1</option>
                                <option value="option2">Option 2</option>
                                <option value="option3">Option 3</option>                                
                            </select>
                        </span>




                        <span style="clear: both; display: block;"></span>

                        <span class="bloco">
                            <label for="custeios">Custo (custeio):</label>
                            <div class="custeios">
                                <p class="campoCusteio" style="display: block; float: left; border: none; width: 270px; ">
                                    <input style="width: 100px; display: block; float: left" type="text" name="custeio[]" />
                                    <a style="width: 150px; text-decoration: none; color: red; display: block; float: left; margin-top: 10px" href="#" class="removerCusteio">Remover</a>
                                </p>
                            </div>
                            <p>
                                <a style="display: block; clear: both; text-decoration: none; color: green" href="#" class="adicionarCusteio">Adicionar Custeio</a>
                            </p>
                        </span>

                        <span class="bloco">
                            <label for="capitais">Custo (capital):</label>
                            <div class="capitais">
                                <p class="campoCapital" style="display: block; float: left; border: none; width: 270px; ">
                                    <input style="width: 100px; display: block; float: left" type="text" name="capital[]" />
                                    <a style="width: 150px; text-decoration: none; color: red; display: block; float: left; margin-top: 10px" href="#" class="removerCapital">Remover</a>
                                </p>
                            </div>
                            <p>
                                <a style="display: block; clear: both; text-decoration: none; color: green" href="#" class="adicionarCapital">Adicionar Capital</a>
                            </p>
                        </span>






                        <label for="nome">Anexo (arquivo .PDF):</label>
                        <input style="font-size: 10px; font-weight: normal; border: none; cursor: pointer" type="file" name="arquivo"/> 

                        <span style="clear: both; display: block; "></span>


                        <label for="nome">Quero que este projeto permaneça em sigilo:</label>
                        <input style=" margin-top: 10px; height: 10px; width: 20px; cursor: pointer" type="radio" name="sigilo" value="true"><span style="display: block; float: left; margin-top: 10px; ">Sim</span>
                        <input style=" margin-top: 10px; margin-left: 25px; clear: none; height: 10px; width: 20px; cursor: pointer" type="radio" name="sigilo" value="false" checked="checked"><span style="display: block; float: left; margin-top: 10px; ">Não</span>


                        <span style="clear: both; display: block"></span>

                        <input class="enviar_form" type="submit" value="Adicionar"/>
                        <button type="reset" class="limpar_form">Limpar</button> 


                    </fieldset>
                </form:form>    




            </div>

        </div>

        <div id="rodape"></div>

    </body>
</html>

