/***************************** INICIO cadastro area conhecimento **********************/
INSERT INTO area_conhecimento(id_area, area)VALUES(1, 'Engenharia de Software');
INSERT INTO area_conhecimento(id_area, area)VALUES(2, 'Engenharia Elétrica');
INSERT INTO area_conhecimento(id_area, area)VALUES(3, 'Engenharia Civil');
/***************************** FIM cadastro area conhecimento **********************/

/***************************** INICIO cadastro campus **********************/
/**
INSERT INTO campus(id_campus, nome)VALUES(1, 'ALEGRETE');
INSERT INTO campus(id_campus, nome)VALUES(2, 'URUGUAINA');
INSERT INTO campus(id_campus, nome)VALUES(3, 'CACAPAVA_DO_SUL');
INSERT INTO campus(id_campus, nome)VALUES(4, 'BAGE');
INSERT INTO campus(id_campus, nome)VALUES(5, 'DOM_PEDRITO');
INSERT INTO campus(id_campus, nome)VALUES(6, 'ITAQU');
INSERT INTO campus(id_campus, nome)VALUES(7, 'JAGUARAO');
INSERT INTO campus(id_campus, nome)VALUES(8, 'SANTANA_DO_LIVRAMENTO');
INSERT INTO campus(id_campus, nome)VALUES(9, 'SAO_BORJA');
INSERT INTO campus(id_campus, nome)VALUES(10, 'SAO_GABRIEL');
**/
/***************************** FIM cadastro campus **********************/

/***************************** INICIO cadastro CUSTO **********************/
/**
INSERT INTO custo(id, tipo, valor, descricao, id_proj)VALUES(1, 'CUSTEIO', 15.25, 'descrição custeio', 1);
INSERT INTO custo(id, tipo, valor, descricao, id_proj)VALUES(2, 'CAPITAL', 10.23, 'descrição custeio', 1);
**/
/***************************** FIM cadastro CUSTO **********************/

/***************************** INICIO cadastro PAPEL **********************/
INSERT INTO papel(id_papel, descricao)VALUES(1, 'PRO_REITOR');
INSERT INTO papel(id_papel, descricao)VALUES(2, 'COORDENADOR');
INSERT INTO papel(id_papel, descricao)VALUES(3, 'PROFESSOR');
INSERT INTO papel(id_papel, descricao)VALUES(4, 'ALUNO');
INSERT INTO papel(id_papel, descricao)VALUES(5, 'EXTERNO');
/***************************** FIM cadastro PAPEL **********************/

/***************************** INICIO cadastro PERMISSÃO**********************/
INSERT INTO permissao(id_perm, descricao)VALUES(1, 'CRUD_PROJETO');
INSERT INTO permissao(id_perm, descricao)VALUES(2, 'ACESSO');
INSERT INTO permissao(id_perm, descricao)VALUES(3, 'CRUD_EDITAL');
/***************************** FIM cadastro PERMISSÃO **********************/


/***************************** INICIO cadastro PAPEL PERMISSÃO**********************/
INSERT INTO papel_permissao VALUES (1, 3);
INSERT INTO papel_permissao VALUES (2, 3);
INSERT INTO papel_permissao VALUES (3, 1);
INSERT INTO papel_permissao VALUES (2, 1);
/***************************** FIM cadastro PAPEL PERMISSÃO **********************/

/***************************** INICIO cadastro usuarios **********************/
INSERT INTO usuario VALUES (1, 'joao', 'joao', 'joão da silva','ALEGRETE');
INSERT INTO usuario VALUES (2, 'maria', 'maria', 'maria da silva','ALEGRETE');
INSERT INTO usuario VALUES (3, 'alfredo', 'alfredo', 'alfredo picareta','ALEGRETE');
INSERT INTO usuario VALUES (4, 'joaquim', 'joaquim', 'joaquim santos','ALEGRETE');
INSERT INTO usuario VALUES (5, 'rafael', 'rafael', 'rafael santos','ALEGRETE');
INSERT INTO usuario VALUES (6, 'rogerio', 'rogerio', 'rogerio','ALEGRETE', 'ENSINO');
INSERT INTO usuario VALUES (7, 'ivone', 'ivone', 'ivone','ALEGRETE', 'EXTENSAO');
INSERT INTO usuario VALUES (8, 'ricardo', 'ricardo', 'ricardo machado','ALEGRETE', 'PESQUISA');
INSERT INTO usuario VALUES (9, 'fabio', 'fabio', 'fábio','ALEGRETE');
INSERT INTO usuario VALUES (10, 'wolmir', 'wolmir', 'wolmir','ALEGRETE');
INSERT INTO usuario VALUES (11, 'jose', 'jose', 'José','ALEGRETE');
INSERT INTO usuario VALUES (12, 'paulo', 'paulo', 'Paulo','ALEGRETE');
INSERT INTO usuario VALUES (13, 'carlos', 'carlos', 'Carlos','ALEGRETE');
/***************************** FIM cadastro usuarios **********************/

/***************************** INICIO cadastro participantes **********************/
/**
INSERT INTO participante(id_particip , id_usuario)VALUES(1, 1);
INSERT INTO participante(id_particip , id_usuario)VALUES(2, 5);
**/
/***************************** FIM cadastro participantes **********************/



/***************************** INICIO cadastro usuario papel **********************/
INSERT INTO usuario_papel(id_papel, id_usuario)VALUES(1, 1);
INSERT INTO usuario_papel(id_papel, id_usuario)VALUES(2, 2);
INSERT INTO usuario_papel(id_papel, id_usuario)VALUES(3, 5);
INSERT INTO usuario_papel(id_papel, id_usuario)VALUES(3, 4);
INSERT INTO usuario_papel(id_papel, id_usuario)VALUES(3, 13);
INSERT INTO usuario_papel(id_papel, id_usuario)VALUES(3, 12);
INSERT INTO usuario_papel(id_papel, id_usuario)VALUES(4, 11);
INSERT INTO usuario_papel(id_papel, id_usuario)VALUES(4, 10);
INSERT INTO usuario_papel(id_papel, id_usuario)VALUES(5, 9);
INSERT INTO usuario_papel(id_papel, id_usuario)VALUES(1, 8);
INSERT INTO usuario_papel(id_papel, id_usuario)VALUES(1, 7);
INSERT INTO usuario_papel(id_papel, id_usuario)VALUES(1, 6);
INSERT INTO usuario_papel(id_papel, id_usuario)VALUES(5, 3);

/***************************** FIM cadastro usuario papel **********************/

/***************************** INICIO cadastro projeto **********************/
insert into projeto (id_proj, titulo, palavras_chave, resumo, sigilo, id_area, tipo_proj, status, is_arquivo, id_responsavel) 
values (1,'Analisador Morfossintático', 'tagger, palavras, viterbi', 'Desenvolver um analidor morfossintático', true, 1, 'PESQUISA', 'CRIADO', false, 5);
insert into projeto (id_proj, titulo, palavras_chave, resumo, sigilo, id_area, tipo_proj, status, is_arquivo, id_responsavel) 
values (2,'Novo Analisador Morfossintático para o idioma português', 'tagger, palavras, viterbi', 'Desenvolver um analidor morfossintático', true, 1, 'PESQUISA', 'CRIADO', false, 5);
insert into projeto (id_proj, titulo, palavras_chave, resumo, sigilo, id_area, tipo_proj, status, is_arquivo, id_responsavel) 
values (3,'Análise de Solos para Construção Civíl', 'solos, mapas', 'Desenvolvimento de um sistema que registre a análise de solos', false, 3, 'EXTENSAO', 'CRIADO', false, 4);
/***************************** FIM cadastro projeto **********************/

/***** INICIO cadastro participantes ******/
insert into participante (id_usuario, id_proj) values (1, 1);
insert into participante (id_usuario, id_proj) values (2, 1);
insert into participante (id_usuario, id_proj) values (10, 2);
insert into participante (id_usuario, id_proj) values (11, 2);
/***** FIM cadastro participantes *******/

/*** INICIO cadastro arquivos *****/
/**insert into arquivo(nome_arquivo, extensao, dados) values ('arquivo', 'txt', '[B@451cf2d');**/
/*** FIM cadastro arquivos *****/

/**** INICIO cadastro editais *****/
/**insert into edital (prazo_inicial, prazo_final, titulo, id_usuario, tipo_edital, id_arquivo) values ('10/10/2013', '10/08/2013', 'PBDA', 1, 'PESQUISA', 1);**/
/**** FIM cadastro editais *****/


