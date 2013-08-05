
CREATE SEQUENCE public.area_conhecimento_id_area_seq_1;

CREATE TABLE public.area_conhecimento (
                id_area INTEGER NOT NULL DEFAULT nextval('public.area_conhecimento_id_area_seq_1'),
                area VARCHAR(50),
                CONSTRAINT area_conhecimento_pk PRIMARY KEY (id_area)
);


ALTER SEQUENCE public.area_conhecimento_id_area_seq_1 OWNED BY public.area_conhecimento.id_area;

CREATE SEQUENCE public.campus_id_campus_seq_1;

CREATE TABLE public.campus (
                id_campus INTEGER NOT NULL DEFAULT nextval('public.campus_id_campus_seq_1'),
                nome VARCHAR(50),
                CONSTRAINT id_campus_pk PRIMARY KEY (id_campus)
);


ALTER SEQUENCE public.campus_id_campus_seq_1 OWNED BY public.campus.id_campus;

CREATE SEQUENCE public.tipo_projeto_id_tipo_proj_seq_1;

CREATE TABLE public.tipo_projeto (
                id_tipo_proj INTEGER NOT NULL DEFAULT nextval('public.tipo_projeto_id_tipo_proj_seq_1'),
                descricao VARCHAR(40) NOT NULL,
                CONSTRAINT id_tipo_proj_pk PRIMARY KEY (id_tipo_proj)
);


ALTER SEQUENCE public.tipo_projeto_id_tipo_proj_seq_1 OWNED BY public.tipo_projeto.id_tipo_proj;

CREATE SEQUENCE public.permissao_id_perm_seq;

CREATE TABLE public.permissao (
                id_perm INTEGER NOT NULL DEFAULT nextval('public.permissao_id_perm_seq'),
                descricao VARCHAR(30) NOT NULL,
                CONSTRAINT id_perm_pk PRIMARY KEY (id_perm)
);


ALTER SEQUENCE public.permissao_id_perm_seq OWNED BY public.permissao.id_perm;

CREATE TABLE public.usuario (
                login VARCHAR(30) NOT NULL,
                senha VARCHAR(16) NOT NULL,
                nome VARCHAR(50) NOT NULL,
                id_perm INTEGER NOT NULL,
                id_campus INTEGER NOT NULL,
                CONSTRAINT login_pk PRIMARY KEY (login)
);


CREATE TABLE public.coordenador (
                login VARCHAR(30) NOT NULL,
                CONSTRAINT coordenador_login_ok PRIMARY KEY (login)
);


CREATE TABLE public.professor (
                LOGIN_PROFESSOR VARCHAR(30) NOT NULL,
                CONSTRAINT professor_login_pk PRIMARY KEY (LOGIN_PROFESSOR)
);


CREATE SEQUENCE public.projeto_id_proj_seq;

CREATE TABLE public.projeto (
                id_proj INTEGER NOT NULL DEFAULT nextval('public.projeto_id_proj_seq'),
                id_tipo_proj INTEGER NOT NULL,
                TITULO VARCHAR(100),
                PALAVRAS_CHAVE VARCHAR(100),
                RESUMO VARCHAR(2000),
                LOGIN_PROFESSOR VARCHAR(30) NOT NULL,
                sigilo BOOLEAN DEFAULT false NOT NULL,
                id_area INTEGER NOT NULL,
                CONSTRAINT id_pk PRIMARY KEY (id_proj)
);


ALTER SEQUENCE public.projeto_id_proj_seq OWNED BY public.projeto.id_proj;

CREATE SEQUENCE public.custo_id_seq;

CREATE TABLE public.Custo (
                ID INTEGER NOT NULL DEFAULT nextval('public.custo_id_seq'),
                TIPO VARCHAR NOT NULL,
                VALOR DOUBLE PRECISION NOT NULL,
                DESCRICAO VARCHAR(200) NOT NULL,
                id_proj INTEGER NOT NULL,
                CONSTRAINT id PRIMARY KEY (ID)
);


ALTER SEQUENCE public.custo_id_seq OWNED BY public.Custo.ID;

CREATE TABLE public.participante (
                id_particip INTEGER NOT NULL,
                login VARCHAR(30) NOT NULL,
                CONSTRAINT part_proj_pk PRIMARY KEY (id_particip, login)
);


CREATE TABLE public.aluno (
                login VARCHAR(30) NOT NULL,
                CONSTRAINT aluno_login_pk PRIMARY KEY (login)
);


CREATE TABLE public.pro_reitor (
                login VARCHAR(30) NOT NULL,
                CONSTRAINT pr_reitor_login_pk PRIMARY KEY (login)
);


ALTER TABLE public.projeto ADD CONSTRAINT area_conhecimento_projeto_fk
FOREIGN KEY (id_area)
REFERENCES public.area_conhecimento (id_area)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.usuario ADD CONSTRAINT campus_usuario_fk
FOREIGN KEY (id_campus)
REFERENCES public.campus (id_campus)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.projeto ADD CONSTRAINT tipo_projeto_projeto_fk
FOREIGN KEY (id_tipo_proj)
REFERENCES public.tipo_projeto (id_tipo_proj)
ON DELETE RESTRICT
ON UPDATE RESTRICT
NOT DEFERRABLE;

ALTER TABLE public.usuario ADD CONSTRAINT tipo_autorizacao_usuario_fk
FOREIGN KEY (id_perm)
REFERENCES public.permissao (id_perm)
ON DELETE RESTRICT
ON UPDATE RESTRICT
NOT DEFERRABLE;

ALTER TABLE public.pro_reitor ADD CONSTRAINT usuario_pro_reitor_fk
FOREIGN KEY (login)
REFERENCES public.usuario (login)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE public.aluno ADD CONSTRAINT usuario_aluno_fk
FOREIGN KEY (login)
REFERENCES public.usuario (login)
ON DELETE CASCADE
ON UPDATE CASCADE
DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE public.professor ADD CONSTRAINT usuario_professor_fk
FOREIGN KEY (LOGIN_PROFESSOR)
REFERENCES public.usuario (login)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE public.coordenador ADD CONSTRAINT usuario_coordenador_fk
FOREIGN KEY (login)
REFERENCES public.usuario (login)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE public.participante ADD CONSTRAINT usuario_participante_fk
FOREIGN KEY (login)
REFERENCES public.usuario (login)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.projeto ADD CONSTRAINT professor_projeto_fk
FOREIGN KEY (LOGIN_PROFESSOR)
REFERENCES public.professor (LOGIN_PROFESSOR)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.participante ADD CONSTRAINT projeto_participante_fk
FOREIGN KEY (id_particip)
REFERENCES public.projeto (id_proj)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Custo ADD CONSTRAINT projeto_custo_fk
FOREIGN KEY (id_proj)
REFERENCES public.projeto (id_proj)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;
