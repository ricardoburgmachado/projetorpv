
CREATE SEQUENCE public.papel_id_papel_seq;

CREATE TABLE public.papel (
                id_papel INTEGER NOT NULL DEFAULT nextval('public.papel_id_papel_seq'),
                descricao VARCHAR(30) NOT NULL,
                CONSTRAINT papel_pk PRIMARY KEY (id_papel)
);


ALTER SEQUENCE public.papel_id_papel_seq OWNED BY public.papel.id_papel;

CREATE SEQUENCE public.area_conhecimento_id_area_seq_1;

CREATE TABLE public.area_conhecimento (
                id_area INTEGER NOT NULL DEFAULT nextval('public.area_conhecimento_id_area_seq_1'),
                area VARCHAR(50),
                CONSTRAINT area_conhecimento_pk PRIMARY KEY (id_area)
);


ALTER SEQUENCE public.area_conhecimento_id_area_seq_1 OWNED BY public.area_conhecimento.id_area;

CREATE SEQUENCE public.tipo_projeto_id_tipo_proj_seq_1;

CREATE TABLE public.tipo_projeto (
                id_tipo_proj INTEGER NOT NULL DEFAULT nextval('public.tipo_projeto_id_tipo_proj_seq_1'),
                descricao VARCHAR(40) NOT NULL,
                CONSTRAINT id_tipo_proj_pk PRIMARY KEY (id_tipo_proj)
);


ALTER SEQUENCE public.tipo_projeto_id_tipo_proj_seq_1 OWNED BY public.tipo_projeto.id_tipo_proj;

CREATE SEQUENCE public.projeto_id_proj_seq;

CREATE TABLE public.projeto (
                id_proj INTEGER NOT NULL DEFAULT nextval('public.projeto_id_proj_seq'),
                id_tipo_proj INTEGER NOT NULL,
                TITULO VARCHAR(100),
                PALAVRAS_CHAVE VARCHAR(100),
                RESUMO VARCHAR(2000),
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

CREATE SEQUENCE public.permissao_id_perm_seq;

CREATE TABLE public.permissao (
                id_perm INTEGER NOT NULL DEFAULT nextval('public.permissao_id_perm_seq'),
                descricao VARCHAR(30) NOT NULL,
                CONSTRAINT id_perm_pk PRIMARY KEY (id_perm)
);


ALTER SEQUENCE public.permissao_id_perm_seq OWNED BY public.permissao.id_perm;

CREATE TABLE public.papel_permissao (
                id_perm INTEGER NOT NULL,
                id_papel INTEGER NOT NULL,
                CONSTRAINT papel_permissao_pk PRIMARY KEY (id_perm, id_papel)
);


CREATE TABLE public.usuario (
                login VARCHAR(30) NOT NULL,
                senha VARCHAR(16) NOT NULL,
                nome VARCHAR(50) NOT NULL,
                campus VARCHAR(30) NOT NULL,
                CONSTRAINT login_pk PRIMARY KEY (login)
);


CREATE TABLE public.usuario_papel (
                id_papel INTEGER NOT NULL,
                login VARCHAR(30) NOT NULL,
                CONSTRAINT usuario_papel_pk PRIMARY KEY (id_papel, login)
);


CREATE TABLE public.participante (
                id_particip INTEGER NOT NULL,
                login VARCHAR(30) NOT NULL,
                CONSTRAINT part_proj_pk PRIMARY KEY (id_particip, login)
);


ALTER TABLE public.papel_permissao ADD CONSTRAINT papel_papel_permissao_fk
FOREIGN KEY (id_papel)
REFERENCES public.papel (id_papel)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.usuario_papel ADD CONSTRAINT papel_usuario_papel_fk
FOREIGN KEY (id_papel)
REFERENCES public.papel (id_papel)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.projeto ADD CONSTRAINT area_conhecimento_projeto_fk
FOREIGN KEY (id_area)
REFERENCES public.area_conhecimento (id_area)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.projeto ADD CONSTRAINT tipo_projeto_projeto_fk
FOREIGN KEY (id_tipo_proj)
REFERENCES public.tipo_projeto (id_tipo_proj)
ON DELETE RESTRICT
ON UPDATE RESTRICT
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

ALTER TABLE public.papel_permissao ADD CONSTRAINT permissao_papel_permissao_fk
FOREIGN KEY (id_perm)
REFERENCES public.permissao (id_perm)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.participante ADD CONSTRAINT usuario_participante_fk
FOREIGN KEY (login)
REFERENCES public.usuario (login)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.usuario_papel ADD CONSTRAINT usuario_usuario_papel_fk
FOREIGN KEY (login)
REFERENCES public.usuario (login)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;
