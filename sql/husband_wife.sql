create table husband(
    id serial primary key,
	name varchar(255)
 );

create table wife(
    id serial primary key,
	name varchar(255)
 );

create table husband_wife(
    id serial primary key,
    husband_id int references husband(id) unique,
    wife_id int references wife(id) unique,
	marriageCertificate int
);

insert into husband(name) values ('Ken');
insert into husband(name) values ('Ted');
insert into husband(name) values ('Fred');

insert into wife(name) values ('Barby');
insert into wife(name) values ('Olga');
insert into wife(name) values ('Nikolay');

insert into husband_wife(husband_id, wife_id, marriageCertificate) values (1, 2 , 10122);
insert into husband_wife(husband_id, wife_id, marriageCertificate) values (2, 1 , 15135);
insert into husband_wife(husband_id, wife_id, marriageCertificate) values (3, 3 , null);

select * from wife where id in (select wife_id from husband_wife)
