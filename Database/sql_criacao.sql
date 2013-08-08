
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
                CONSTRAINT id_pk PRIMARY KEY (id_proj)
);


ALTER SEQUENCE public.projeto_id_proj_seq OWNED BY public.projeto.id_proj;

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
                CONSTRAINT login_pk PRIMARY KEY (login)
);


CREATE TABLE public.participante (
                id_particip INTEGER NOT NULL,
                login VARCHAR(30) NOT NULL,
                CONSTRAINT part_proj_pk PRIMARY KEY (id_particip, login)
);


CREATE TABLE public.coordenador (
                login VARCHAR(30) NOT NULL,
                CONSTRAINT coordenador_login_ok PRIMARY KEY (login)
);


CREATE TABLE public.professor (
                login VARCHAR(30) NOT NULL,
                CONSTRAINT professor_login_pk PRIMARY KEY (login)
);


CREATE TABLE public.aluno (
                login VARCHAR(30) NOT NULL,
                CONSTRAINT aluno_login_pk PRIMARY KEY (login)
);


CREATE TABLE public.pro_reitor (
                login VARCHAR(30) NOT NULL,
                CONSTRAINT pr_reitor_login_pk PRIMARY KEY (login)
);


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
FOREIGN KEY (login)
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
