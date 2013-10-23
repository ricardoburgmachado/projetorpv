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
INSERT INTO permissao(id_perm, descricao)VALUES(4, 'INSCRICAO_EDITAL');
INSERT INTO permissao(id_perm, descricao)VALUES(5, 'EXIBE_EDITAL');
INSERT INTO permissao(id_perm, descricao)VALUES(6, 'SUBMISSAO_HOMOLOGACAO');
INSERT INTO permissao(id_perm, descricao)VALUES(7, 'LISTAGEM_SUBMETIDOS');
INSERT INTO permissao(id_perm, descricao)VALUES(8, 'CANCELAMENTO_INSCRICAO');
INSERT INTO permissao(id_perm, descricao)VALUES(9, 'LISTAGEM_INSCRICOES');
INSERT INTO permissao(id_perm, descricao)VALUES(10, 'PRESTAR_CONTAS');
INSERT INTO permissao(id_perm, descricao)VALUES(11, 'DOWNLOAD_EDITAL');
INSERT INTO permissao(id_perm, descricao)VALUES(12, 'ALTERAR_STATUS_PROJETO');
INSERT INTO permissao(id_perm, descricao)VALUES(13, 'CONTEMPLACAO_EDITAL');
INSERT INTO permissao(id_perm, descricao)VALUES(14, 'ACESSAR_PROJ_REGISTRADOS');/** projetos inscritos na tabela inscrito **/
/***************************** FIM cadastro PERMISSÃO **********************/


/***************************** INICIO cadastro PAPEL PERMISSÃO**********************/
INSERT INTO papel_permissao(id_perm, id_papel) VALUES (2, 1);  /** ACESSO -> PRO_REITOR **/
INSERT INTO papel_permissao(id_perm, id_papel) VALUES (2, 2);  /** ACESSO -> COORDENADOR **/
INSERT INTO papel_permissao(id_perm, id_papel) VALUES (2, 3);  /** ACESSO -> PROFESSOR **/
INSERT INTO papel_permissao(id_perm, id_papel) VALUES (2, 4);  /** ACESSO -> ALUNO **/
INSERT INTO papel_permissao(id_perm, id_papel) VALUES (2, 5);  /** ACESSO -> EXTERNO **/
INSERT INTO papel_permissao(id_perm, id_papel) VALUES (1, 3);  /** CRUD_PROJETO -> PROFESSOR **/
INSERT INTO papel_permissao(id_perm, id_papel) VALUES (3, 1);  /** CRUD_EDITAL -> PRO_REITOR **/
INSERT INTO papel_permissao(id_perm, id_papel) VALUES (4, 3);  /** INSCRICAO_EDITAL -> PROFESSOR **/
INSERT INTO papel_permissao(id_perm, id_papel) VALUES (5, 3);  /** EXIBE_EDITAL -> PROFESSOR **/
INSERT INTO papel_permissao(id_perm, id_papel) VALUES (6, 3);  /** SUBMISSAO_HOMOLOGACAO ->  PROFESSOR **/
INSERT INTO papel_permissao(id_perm, id_papel) VALUES (7, 3);  /** LISTAGEM_SUBMETIDOS -> PROFESSOR **/
INSERT INTO papel_permissao(id_perm, id_papel) VALUES (8, 3);  /** CANCELAMENTO_INSCRICAO -> PROFESSOR **/
INSERT INTO papel_permissao(id_perm, id_papel) VALUES (9, 3);  /** LISTAGEM_INSCRICOES -> PROFESSOR **/
INSERT INTO papel_permissao(id_perm, id_papel) VALUES (1, 1);  /** CRUD_PROJETO -> PRO REITOR **/
INSERT INTO papel_permissao(id_perm, id_papel) VALUES (10, 3); /** PRESTAR_CONTAS -> PROFESSOR **/
INSERT INTO papel_permissao(id_perm, id_papel) VALUES (11, 3); /** DOWNLOAD_EDITAL -> PROFESSOR **/	
INSERT INTO papel_permissao(id_perm, id_papel) VALUES (12, 2); /** ALTERAR_STATUS_PROJETO -> COORDENADOR **/	
INSERT INTO papel_permissao(id_perm, id_papel) VALUES (13, 1); /** CONTEMPLACAO_EDITAL -> PRO REITOR **/
INSERT INTO papel_permissao(id_perm, id_papel) VALUES (14, 1); /** ACESSAR_PROJ_REGISTRADOS -> PRO REITOR **/
/***************************** FIM cadastro PAPEL PERMISSÃO **********************/

/***************************** INICIO cadastro usuarios **********************/
INSERT INTO usuario VALUES (1, 'joao', 'joao', 'joão da silva','ALEGRETE', 'ENSINO');
INSERT INTO usuario VALUES (2, 'maria', 'maria', 'maria da silva','ALEGRETE', 'PESQUISA');
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
INSERT INTO usuario VALUES (14, 'cristiano', 'cristiano', 'cristiano moreira','ALEGRETE', 'EXTENSAO');
INSERT INTO usuario VALUES (15 'rubia', 'rubia', 'Rubia','ALEGRETE');
INSERT INTO usuario VALUES (16 'francisco', 'francisco', 'francisco','ALEGRETE');
/***************************** FIM cadastro usuarios **********************/

/***************************** INICIO cadastro participantes **********************/
/**
INSERT INTO participante(id_particip , id_usuario)VALUES(1, 1);
INSERT INTO participante(id_particip , id_usuario)VALUES(2, 5);
**/
/***************************** FIM cadastro participantes **********************/



/***************************** INICIO cadastro usuario papel **********************/
INSERT INTO usuario_papel(id_papel, id_usuario)VALUES(2, 1);  /** COORDENADOR (ENSINO) -> JOAO **/
INSERT INTO usuario_papel(id_papel, id_usuario)VALUES(2, 2);  /** COORDENADOR (PESQUISA)-> MARIA **/
INSERT INTO usuario_papel(id_papel, id_usuario)VALUES(3, 5);  /** PROFESSOR -> RAFAEL **/
INSERT INTO usuario_papel(id_papel, id_usuario)VALUES(3, 4);  /** PROFESSOR -> JOAQUIM **/
INSERT INTO usuario_papel(id_papel, id_usuario)VALUES(3, 13); /** PROFESSOR -> CARLOS **/
INSERT INTO usuario_papel(id_papel, id_usuario)VALUES(3, 12); /** PROFESSOR -> PAULO **/
INSERT INTO usuario_papel(id_papel, id_usuario)VALUES(4, 11); /** ALUNO -> JOSE **/
INSERT INTO usuario_papel(id_papel, id_usuario)VALUES(4, 10); /** ALUNO -> WOLMIR **/
INSERT INTO usuario_papel(id_papel, id_usuario)VALUES(5, 9);  /** EXTERNO -> FABIO **/
INSERT INTO usuario_papel(id_papel, id_usuario)VALUES(1, 8);  /** PRO REITOR (PESQUISA)-> RICARDO **/
INSERT INTO usuario_papel(id_papel, id_usuario)VALUES(1, 7);  /** PRO REITOR (EXTENSAO)-> IVONE **/
INSERT INTO usuario_papel(id_papel, id_usuario)VALUES(1, 6);  /** PRO REITOR (ENSINO)-> ROGERIO **/
INSERT INTO usuario_papel(id_papel, id_usuario)VALUES(5, 3);  /** EXTERNO -> ALFREDO **/
INSERT INTO usuario_papel(id_papel, id_usuario)VALUES(2, 14); /** COORDENADOR (EXTENSAO) -> CRISTIANO **/
INSERT INTO usuario_papel(id_papel, id_usuario)VALUES(4, 15); /** ALUNO -> RUBIA **/
INSERT INTO usuario_papel(id_papel, id_usuario)VALUES(4, 16); /** ALUNO -> FRANCISCO **/
/***************************** FIM cadastro usuario papel **********************/

/***************************** INICIO cadastro projeto **********************/
/**
insert into projeto (id_proj, titulo, palavras_chave, resumo, sigilo, id_area, tipo_proj, status, is_arquivo, id_responsavel) 
values (1,'Analisador Morfossintático', 'tagger, palavras, viterbi', 'Desenvolver um analidor morfossintático', true, 1, 'PESQUISA', 'CRIADO', false, 5);
insert into projeto (id_proj, titulo, palavras_chave, resumo, sigilo, id_area, tipo_proj, status, is_arquivo, id_responsavel) 
values (2,'Novo Analisador Morfossintático para o idioma português', 'tagger, palavras, viterbi', 'Desenvolver um analidor morfossintático', true, 1, 'PESQUISA', 'CRIADO', false, 5);
insert into projeto (id_proj, titulo, palavras_chave, resumo, sigilo, id_area, tipo_proj, status, is_arquivo, id_responsavel) 
values (3,'Análise de Solos para Construção Civíl', 'solos, mapas', 'Desenvolvimento de um sistema que registre a análise de solos', false, 3, 'EXTENSAO', 'CRIADO', false, 4);
**/
/***************************** FIM cadastro projeto **********************/

/***** INICIO cadastro participantes ******/
/**
insert into participante (id_usuario, id_proj) values (1, 1);
insert into participante (id_usuario, id_proj) values (2, 1);
insert into participante (id_usuario, id_proj) values (10, 2);
insert into participante (id_usuario, id_proj) values (11, 2);
**/
/***** FIM cadastro participantes *******/

/*** INICIO cadastro arquivos *****/
/**insert into arquivo(nome_arquivo, extensao, dados) values ('arquivo', 'txt', '[B@451cf2d');**/
/*** FIM cadastro arquivos *****/

/**** INICIO cadastro editais *****/
/**insert into edital (prazo_inicial, prazo_final, titulo, id_usuario, tipo_edital, id_arquivo) values ('10/10/2013', '10/08/2013', 'PBDA', 1, 'PESQUISA', 1);**/
/**** FIM cadastro editais *****/


