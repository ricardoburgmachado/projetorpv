
CREATE TABLE tipo_projeto (
                id_tipo_proj INTEGER NOT NULL,
                descricao VARCHAR(40) NOT NULL,
                CONSTRAINT id_tipo_proj_pk PRIMARY KEY (id_tipo_proj)
);


CREATE TABLE projeto (
                id_proj INTEGER NOT NULL,
                id_tipo_proj INTEGER NOT NULL,
                CONSTRAINT id_pk PRIMARY KEY (id_proj)
);


CREATE TABLE permissao (
                id_perm INTEGER NOT NULL,
                descricao VARCHAR(30) NOT NULL,
                CONSTRAINT id_perm_pk PRIMARY KEY (id_perm)
);


CREATE TABLE usuario (
                login VARCHAR(30) NOT NULL,
                senha VARCHAR(16) NOT NULL,
                nome VARCHAR(50) NOT NULL,
                id INTEGER NOT NULL,
                CONSTRAINT login_pk PRIMARY KEY (login)
);


CREATE TABLE participante (
                id INTEGER NOT NULL,
                login VARCHAR(30) NOT NULL,
                CONSTRAINT part_proj_pk PRIMARY KEY (id, login)
);


CREATE TABLE coordenador (
                login VARCHAR(30) NOT NULL,
                CONSTRAINT login_pk PRIMARY KEY (login)
);


CREATE TABLE professor (
                login VARCHAR(30) NOT NULL,
                CONSTRAINT login_pk PRIMARY KEY (login)
);


CREATE TABLE aluno (
                login VARCHAR(30) NOT NULL,
                CONSTRAINT login_pk PRIMARY KEY (login)
);


CREATE TABLE pro_reitor (
                login VARCHAR(30) NOT NULL,
                CONSTRAINT login_pk PRIMARY KEY (login)
);


ALTER TABLE projeto ADD CONSTRAINT tipo_projeto_projeto_fk
FOREIGN KEY (id_tipo_proj)
REFERENCES tipo_projeto (id_tipo_proj)
ON DELETE RESTRICT
ON UPDATE RESTRICT
NOT DEFERRABLE;

ALTER TABLE participante ADD CONSTRAINT projeto_participante_fk
FOREIGN KEY (id)
REFERENCES projeto (id_proj)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE usuario ADD CONSTRAINT tipo_autorizacao_usuario_fk
FOREIGN KEY (id)
REFERENCES permissao (id_perm)
ON DELETE RESTRICT
ON UPDATE RESTRICT
NOT DEFERRABLE;

ALTER TABLE pro_reitor ADD CONSTRAINT usuario_pro_reitor_fk
FOREIGN KEY (login)
REFERENCES usuario (login)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE aluno ADD CONSTRAINT usuario_aluno_fk
FOREIGN KEY (login)
REFERENCES usuario (login)
ON DELETE CASCADE
ON UPDATE CASCADE
DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE professor ADD CONSTRAINT usuario_professor_fk
FOREIGN KEY (login)
REFERENCES usuario (login)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE coordenador ADD CONSTRAINT usuario_coordenador_fk
FOREIGN KEY (login)
REFERENCES usuario (login)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE participante ADD CONSTRAINT usuario_participante_fk
FOREIGN KEY (login)
REFERENCES usuario (login)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;
