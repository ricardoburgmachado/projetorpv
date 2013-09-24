
CREATE SEQUENCE public.arquivo_id_arquivo_seq_1_1;

CREATE TABLE public.arquivo (
                id_arquivo INTEGER NOT NULL DEFAULT nextval('public.arquivo_id_arquivo_seq_1_1'),
                nome_arquivo VARCHAR(100) NOT NULL,
                extensao VARCHAR(20) NOT NULL,
                dados BYTEA NOT NULL,
                CONSTRAINT arquivo_pk PRIMARY KEY (id_arquivo)
);


ALTER SEQUENCE public.arquivo_id_arquivo_seq_1_1 OWNED BY public.arquivo.id_arquivo;

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


CREATE SEQUENCE public.usuario_id_usuario_seq;

CREATE TABLE public.usuario (
                id_usuario INTEGER NOT NULL DEFAULT nextval('public.usuario_id_usuario_seq'),
                login VARCHAR(30),
                senha VARCHAR(16),
                nome VARCHAR(50) NOT NULL,
                campus VARCHAR(50),
                area VARCHAR(50),
                CONSTRAINT login_pk PRIMARY KEY (id_usuario)
);


ALTER SEQUENCE public.usuario_id_usuario_seq OWNED BY public.usuario.id_usuario;

CREATE SEQUENCE public.edital_id_edital_seq;

CREATE TABLE public.edital (
                id_edital INTEGER NOT NULL DEFAULT nextval('public.edital_id_edital_seq'),
                prazo_final DATE,
                prazo_inicial DATE,
                titulo VARCHAR(2000),
                id_usuario INTEGER NOT NULL,
                tipo_edital VARCHAR(50),
                id_arquivo INTEGER NOT NULL,
                CONSTRAINT edital_pk PRIMARY KEY (id_edital)
);


ALTER SEQUENCE public.edital_id_edital_seq OWNED BY public.edital.id_edital;

CREATE TABLE public.retificacao (
                id_arquivo INTEGER NOT NULL,
                id_edital INTEGER NOT NULL,
                CONSTRAINT retificacao_pk PRIMARY KEY (id_arquivo)
);


CREATE SEQUENCE public.projeto_id_proj_seq;

CREATE TABLE public.projeto (
                id_proj INTEGER NOT NULL DEFAULT nextval('public.projeto_id_proj_seq'),
                TITULO VARCHAR(100),
                PALAVRAS_CHAVE VARCHAR(100),
                RESUMO VARCHAR(2000),
                sigilo BOOLEAN DEFAULT false NOT NULL,
                id_area INTEGER,
                tipo_proj VARCHAR(50),
                status VARCHAR(20) NOT NULL,
                is_arquivo BOOLEAN DEFAULT false NOT NULL,
                id_responsavel INTEGER NOT NULL,
                CONSTRAINT id_pk PRIMARY KEY (id_proj)
);


ALTER SEQUENCE public.projeto_id_proj_seq OWNED BY public.projeto.id_proj;

CREATE TABLE public.inscricao (
                id_proj INTEGER NOT NULL,
                id_edital INTEGER NOT NULL,
                id_arquivo INTEGER,
                CONSTRAINT inscricao_pk PRIMARY KEY (id_proj, id_edital)
);


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

CREATE TABLE public.usuario_papel (
                id_usuario INTEGER NOT NULL,
                id_papel INTEGER NOT NULL,
                CONSTRAINT usuario_papel_pk PRIMARY KEY (id_usuario, id_papel)
);


CREATE TABLE public.participante (
                id_usuario INTEGER NOT NULL,
                id_proj INTEGER NOT NULL,
                CONSTRAINT part_proj_pk PRIMARY KEY (id_usuario, id_proj)
);


ALTER TABLE public.inscricao ADD CONSTRAINT arquivo_inscricao_fk
FOREIGN KEY (id_arquivo)
REFERENCES public.arquivo (id_arquivo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.edital ADD CONSTRAINT arquivo_edital_fk
FOREIGN KEY (id_arquivo)
REFERENCES public.arquivo (id_arquivo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.retificacao ADD CONSTRAINT arquivo_retificacao_fk
FOREIGN KEY (id_arquivo)
REFERENCES public.arquivo (id_arquivo)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

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

ALTER TABLE public.papel_permissao ADD CONSTRAINT permissao_papel_permissao_fk
FOREIGN KEY (id_perm)
REFERENCES public.permissao (id_perm)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.participante ADD CONSTRAINT usuario_participante_fk
FOREIGN KEY (id_usuario)
REFERENCES public.usuario (id_usuario)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE public.usuario_papel ADD CONSTRAINT usuario_usuario_papel_fk
FOREIGN KEY (id_usuario)
REFERENCES public.usuario (id_usuario)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.projeto ADD CONSTRAINT usuario_projeto_fk
FOREIGN KEY (id_responsavel)
REFERENCES public.usuario (id_usuario)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.edital ADD CONSTRAINT usuario_edital_fk
FOREIGN KEY (id_usuario)
REFERENCES public.usuario (id_usuario)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.inscricao ADD CONSTRAINT edital_inscricao_fk
FOREIGN KEY (id_edital)
REFERENCES public.edital (id_edital)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.retificacao ADD CONSTRAINT edital_retificacao_fk
FOREIGN KEY (id_edital)
REFERENCES public.edital (id_edital)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE public.participante ADD CONSTRAINT projeto_participante_fk
FOREIGN KEY (id_proj)
REFERENCES public.projeto (id_proj)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE public.Custo ADD CONSTRAINT projeto_custo_fk
FOREIGN KEY (id_proj)
REFERENCES public.projeto (id_proj)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE public.inscricao ADD CONSTRAINT projeto_inscricao_fk
FOREIGN KEY (id_proj)
REFERENCES public.projeto (id_proj)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;
