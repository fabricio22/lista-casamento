-- Insere dados de mesas
insert into mesa(id, quantidade_cadeiras, quantidade_cadeiras_disponiveis)
values(1, 8, 8);

insert into mesa(id, quantidade_cadeiras, quantidade_cadeiras_disponiveis)
values(2, 8, 8);

insert into mesa(id, quantidade_cadeiras, quantidade_cadeiras_disponiveis)
values(3, 8, 8);
commit;

-- Insere dados de grupo
insert into grupo(id, descricao) values(1, 'Familia-Noiva');
insert into grupo(id, descricao) values(2, 'Familia-Noivo');
insert into grupo(id, descricao) values(3, 'Amigos-Noiva');
insert into grupo(id, descricao) values(4, 'Amigos-Noivo');
insert into grupo(id, descricao) values(5, 'Trabalho-Noiva');
insert into grupo(id, descricao) values(6, 'Trabalho-Noivo');
insert into grupo(id, descricao) values(7, 'Amigos-em-comum');
commit;