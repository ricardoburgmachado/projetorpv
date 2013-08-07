--inserindo permissoes--
insert into permissao values (1, 'CRUD_PROJETO');
------------------------

--inserindo papeis--
insert into papel values (1, 'professor');
--------------------

--inserindo usuarios--
insert into usuario values ('professor', 'professor', 'professor', 'ALEGRETE');
----------------------

--associando papeis e permissoes--
insert into papel_permissao values (1, 1);
--------------------------------------

--associando usuario e papeis--
insert into usuario_papel values (1, 'professor');
-------------------------------

