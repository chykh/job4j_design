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
    wife_id int references wife(id) unique
 );
 
 create table marriageCertificate(
 	id serial primary key,
	number int,
	husband_id int references husband(id),
    wife_id int references wife(id)
 );

insert into husband(name) values ('Ken');
insert into husband(name) values ('Ted');
insert into husband(name) values ('Fred');

insert into wife(name) values ('Barby');
insert into wife(name) values ('Olga');
insert into wife(name) values ('Nikolay');

insert into husband_wife(husband_id, wife_id) values (1, 2);
insert into husband_wife(husband_id, wife_id) values (2, 1);
insert into husband_wife(husband_id, wife_id) values (3, 3);

select * from wife where id in (select wife_id from husband_wife);

insert into marriageCertificate(number, husband_id, wife_id) values (100, 1, 2);
insert into marriageCertificate(number, husband_id, wife_id) values (200, 2, 1);
insert into marriageCertificate(number, husband_id, wife_id) values (null, 3, 3);

select * from marriageCertificate;