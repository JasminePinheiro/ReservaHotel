drop database if exists hotel;

create database hotel;
use hotel;

create table clientes(
	cliente_id int primary key auto_increment,
	nome varchar(100) not null,
	telefone varchar(15) not null,
	email varchar(100) not null,
	senha varchar(50)
);

create table hotel(
	hotel_id int primary key,
    localizacao varchar(20)
);

create table quartos(
	quarto_id int primary key,
    descricao varchar(200),
    preco double
);

create table reservas(
	reserva_id int primary key auto_increment,
    cliente_id int not null,
    hotel_id int,
    quarto_id int,
    disponivel boolean not null,
    data_inicio date not null,
    data_fim date,
	FOREIGN KEY (cliente_id) REFERENCES clientes(cliente_id),
	FOREIGN KEY (hotel_id) REFERENCES hotel(hotel_id),
	FOREIGN KEY (quarto_id) REFERENCES quartos(quarto_id)
);

insert into quartos (quarto_id) values (1);
insert into quartos (quarto_id) values (2);

insert into hotel (hotel_id) values (1);
insert into hotel (hotel_id) values (2);
insert into hotel (hotel_id) values (3);


select * from clientes;
select * from reservas;