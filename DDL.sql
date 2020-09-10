create schema springDB;

use springDB;

create user 'user'@'localhost' identified by 'alohomora';

grant select, insert, delete, update on springDB.* to user@'localhost';

create table usr_usuario (
  usr_id bigint unsigned not null auto_increment,
  usr_nome varchar(20) not null,
  usr_senha varchar(50) not null,
  primary key (usr_id),
  unique key uni_usuario_nome (usr_nome)
);

create table aut_autorizacao (
  aut_id bigint unsigned not null auto_increment,
  aut_nome varchar(20) not null,
  primary key (aut_id),
  unique key uni_aut_nome (aut_nome)
);

create table uau_usuario_autorizacao (
  usr_id bigint unsigned not null,
  aut_id bigint unsigned not null,
  primary key (usr_id, aut_id),
  foreign key aut_usuario_fk (usr_id) references usr_usuario (usr_id) on delete restrict on update cascade,
  foreign key aut_autorizacao_fk (aut_id) references aut_autorizacao (aut_id) on delete restrict on update cascade
);

create table liv_livro (
  liv_id bigint unsigned not null auto_increment,
  liv_nome varchar(50) not null,
  liv_autor varchar(20) not null,
  primary key (liv_id)
);

create table com_comentario (
  com_id bigint unsigned not null auto_increment,
  com_comentario varchar(100) not null,
  primary key (com_id)
);

create table cml_comentario_livro (
  liv_id bigint unsigned not null,
  com_id bigint unsigned not null,
  primary key (liv_id, com_id),
  foreign key cml_livro_fk (liv_id) references liv_livro (liv_id) on delete restrict on update cascade,
  foreign key cml_comentario_fk (com_id) references com_comentario (com_id) on delete restrict on update cascade
);

create table cmu_comentario_usuario (
  usr_id bigint unsigned not null,
  com_id bigint unsigned not null,
  primary key (usr_id, com_id),
  foreign key cmu_usuario_fk (usr_id) references usr_usuario (usr_id) on delete restrict on update cascade,
  foreign key cmu_comentario_fk (com_id) references com_comentario (com_id) on delete restrict on update cascade
);

insert into usr_usuario (usr_nome, usr_senha)
    values('Luna', 'Senha');
insert into aut_autorizacao (aut_nome)
    values('ROLE_ADMIN');
insert into uau_usuario_autorizacao (1, 1);
insert into liv_livro (liv_nome, liv_autor)
    values ('Harry Potter e o Prisioneiro de Azkaban', 'J.K Rowling');
insert into com_comentario(com_comentario)
    values ('Emocionante. Melhor livro da saga.');
insert into cmu_comentario_usuario(usr_id,com_id)
    values ('1','1');
insert into cml_comentario_livro(liv_id,com_id)
    values ('1','1');
