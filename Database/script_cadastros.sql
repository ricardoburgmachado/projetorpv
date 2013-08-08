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
INSERT INTO papel(id_papel, descricao)VALUES(2, 'CORDENADOR');
INSERT INTO papel(id_papel, descricao)VALUES(3, 'PROFESSOR');
INSERT INTO papel(id_papel, descricao)VALUES(4, 'ALUNO');
INSERT INTO papel(id_papel, descricao)VALUES(5, 'EXTERNO');
/***************************** FIM cadastro PAPEL **********************/

/***************************** INICIO cadastro PERMISSÃO**********************/
INSERT INTO permissao(id_perm, descricao)VALUES(1, 'x');
INSERT INTO permissao(id_perm, descricao)VALUES(2, 'y');
/***************************** FIM cadastro PERMISSÃO **********************/


/***************************** INICIO cadastro PAPEL PERMISSÃO**********************/
/**INSERT INTO papel_permissao(id_perm, id_papel)VALUES(1, ); **/
/***************************** FIM cadastro PAPEL PERMISSÃO **********************/

/***************************** INICIO cadastro usuarios **********************/
/** o atributo id é auto increment **/

INSERT INTO usuario(login, senha, nome, campus)VALUES('joao', 'joao', 'joão da silva','ALEGRETE');
INSERT INTO usuario(login, senha, nome, campus)VALUES('maria', 'maria', 'maria da silva','ALEGRETE');
INSERT INTO usuario(login, senha, nome, campus)VALUES('alfredo', 'alfredo', 'alfredo picareta','ALEGRETE');
INSERT INTO usuario(login, senha, nome, campus)VALUES('joaquim', 'joaquim', 'joaquim santos','ALEGRETE');
INSERT INTO usuario(login, senha, nome, campus)VALUES('rafael', 'rafael', 'rafael santos','ALEGRETE');
INSERT INTO usuario(login, senha, nome, campus)VALUES('rogerio', 'rogerio', 'rogerio','ALEGRETE');
INSERT INTO usuario(login, senha, nome, campus)VALUES('ivone', 'ivone', 'ivone','ALEGRETE');
INSERT INTO usuario(login, senha, nome, campus)VALUES('ricardo', 'ricardo', 'ricardo machado','ALEGRETE');
INSERT INTO usuario(login, senha, nome, campus)VALUES('fabio', 'fabio', 'fábio','ALEGRETE');
INSERT INTO usuario(login, senha, nome, campus)VALUES('wolmir', 'wolmir', 'wolmir','ALEGRETE');
INSERT INTO usuario(login, senha, nome, campus)VALUES('jose', 'jose', 'José','ALEGRETE');
INSERT INTO usuario(login, senha, nome, campus)VALUES('paulo', 'paulo', 'Paulo','ALEGRETE');
INSERT INTO usuario(login, senha, nome, campus)VALUES('carlos', 'carlos', 'Carlos','ALEGRETE');

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
INSERT INTO usuario_papel(id_papel, id_usuario)VALUES(4, 12);
INSERT INTO usuario_papel(id_papel, id_usuario)VALUES(4, 11);
INSERT INTO usuario_papel(id_papel, id_usuario)VALUES(4, 10);
INSERT INTO usuario_papel(id_papel, id_usuario)VALUES(5, 9);
INSERT INTO usuario_papel(id_papel, id_usuario)VALUES(5, 8);
INSERT INTO usuario_papel(id_papel, id_usuario)VALUES(5, 7);
INSERT INTO usuario_papel(id_papel, id_usuario)VALUES(5, 6);
INSERT INTO usuario_papel(id_papel, id_usuario)VALUES(5, 3);

/***************************** FIM cadastro usuario papel **********************/

/***************************** INICIO cadastro projeto **********************/
/**INSERT INTO projeto(id_proj, tipo_proj, titulo, palavras_chave, resumo, sigilo, id_area)VALUES();**/
/***************************** FIM cadastro projeto **********************/


