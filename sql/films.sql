--drop table person_films;
--drop table films;
--drop table gender;
--drop table person;

create table gender(
	id serial primary key,
	gender varchar(50)
);

insert into gender(gender) values('женский');
insert into gender(gender) values('мужчкой');

create table films(
	id serial primary key,
	name varchar(50)
);

insert into films(name) values('Пираты Карибского моря');
insert into films(name) values('Матрица: Революция');
insert into films(name) values('Человек, который все изменил');
insert into films(name) values('Интерстеллар');

/*
create table person(
	id serial primary key,
	fullname varchar(50),
	address text,
	gender_id integer references gender(id)
);
*/

create table person(
	id serial primary key,
	fullname varchar(50),
	address text,
	gender varchar(50)
);

insert into person(fullname, address, gender) values ('Ольга Егорова', '1ый Казанский пер, д.14', 'Женский');
insert into person(fullname, address, gender) values ('Иванов Сергей', 'ул. Центральная, д.40, кв.74', 'Мужской');
insert into person(fullname, address, gender) values ('Иванов Сергей', 'ул. Ленина, д.7, кв.130', 'Мужской');

create table person_films(
	id serial primary key,
	person_id integer references person(id),
	films_id integer references films(id)
);

insert into person_films(person_id, films_id) values (1, 1);
insert into person_films(person_id, films_id) values (1, 2);
insert into person_films(person_id, films_id) values (2, 3);
insert into person_films(person_id, films_id) values (2, 4);
insert into person_films(person_id, films_id) values (3, 2);

select * from person;
