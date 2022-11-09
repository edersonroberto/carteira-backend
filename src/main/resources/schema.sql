create table administradora (
	id serial primary key,
	nome varchar(50) not null,
	razaoSocial varchar(50)
	email varchar(50),
	cnpj varchar(11) not null unique
);

create table ativo (
	id int auto_increment primary key,
	administradora_id int not null,
	nome varchar(50) not null,
	ticket varchar(6),
	cnpj varchar(11)
);

create table nota (
	id int auto_increment primary key,
	id_ativo int not null,
	data date not null,
	tipo_operacao char(1) not null,
	quantidade number(2) not null,
	preco number(5,2) not null,
	taxa number(5,2) not null,
	total_custo number (6,2) not null
);