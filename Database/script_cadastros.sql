/***************************** INICIO cadastro area conhecimento **********************/
INSERT INTO area_conhecimento(id_area, area)VALUES(1, 'Engenharia de Software');
INSERT INTO area_conhecimento(id_area, area)VALUES(2, 'Engenharia Elétrica');
INSERT INTO area_conhecimento(id_area, area)VALUES(3, 'Engenharia Civil');
/***************************** FIM cadastro area conhecimento **********************/

/***************************** INICIO cadastro campus **********************/
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
INSERT INTO usuario(login, senha, nome, id_campus)VALUES('joao', 'joao', 'joão da silva',1);
INSERT INTO usuario(login, senha, nome, id_campus)VALUES('maria', 'maria', 'maria da silva',1);
INSERT INTO usuario(login, senha, nome, id_campus)VALUES('alfredo', 'alfredo', 'alfredo picareta',2);
INSERT INTO usuario(login, senha, nome, id_campus)VALUES('joaquim', 'joaquim', 'joaquim santos',1);
INSERT INTO usuario(login, senha, nome, id_campus)VALUES('rafael', 'rafael', 'rafael santos',1);
INSERT INTO usuario(login, senha, nome, id_campus)VALUES('rogerio', 'rogerio', 'rogerio',1);
INSERT INTO usuario(login, senha, nome, id_campus)VALUES('ivone', 'ivone', 'ivone',1);
INSERT INTO usuario(login, senha, nome, id_campus)VALUES('ricardo', 'ricardo', 'ricardo machado',1);
INSERT INTO usuario(login, senha, nome, id_campus)VALUES('fabio', 'fabio', 'fábio',1);
INSERT INTO usuario(login, senha, nome, id_campus)VALUES('wolmir', 'wolmir', 'wolmir',1);
INSERT INTO usuario(login, senha, nome, id_campus)VALUES('jose', 'jose', 'José',1);
INSERT INTO usuario(login, senha, nome, id_campus)VALUES('paulo', 'paulo', 'Paulo',1);
INSERT INTO usuario(login, senha, nome, id_campus)VALUES('carlos', 'carlos', 'Carlos',1);

/***************************** FIM cadastro usuarios **********************/

/***************************** INICIO cadastro participantes **********************/
/**
INSERT INTO participante(id_particip , login)VALUES(1, 'joao');
INSERT INTO participante(id_particip , login)VALUES(2, 'maria');
**/
/***************************** FIM cadastro participantes **********************/



/***************************** INICIO cadastro usuario papel **********************/
INSERT INTO usuario_papel(id_papel, login)VALUES(1, 'joao');
INSERT INTO usuario_papel(id_papel, login)VALUES(2, 'maria');
INSERT INTO usuario_papel(id_papel, login)VALUES(3, 'alfredo');
INSERT INTO usuario_papel(id_papel, login)VALUES(3, 'joaquim');
INSERT INTO usuario_papel(id_papel, login)VALUES(3, 'rafael');
INSERT INTO usuario_papel(id_papel, login)VALUES(3, 'rogerio');
INSERT INTO usuario_papel(id_papel, login)VALUES(4, 'ivone');
INSERT INTO usuario_papel(id_papel, login)VALUES(4, 'ricardo');
INSERT INTO usuario_papel(id_papel, login)VALUES(4, 'fabio');
INSERT INTO usuario_papel(id_papel, login)VALUES(5, 'wolmir');
INSERT INTO usuario_papel(id_papel, login)VALUES(5, 'jose');
INSERT INTO usuario_papel(id_papel, login)VALUES(5, 'paulo');
INSERT INTO usuario_papel(id_papel, login)VALUES(5, 'carlos');


/***************************** FIM cadastro usuario papel **********************/

/***************************** INICIO cadastro projeto **********************/
/**INSERT INTO projeto(id_proj, tipo_proj, titulo, palavras_chave, resumo, sigilo, id_area)VALUES();**/
/***************************** FIM cadastro projeto **********************/


