create table husband(
    id serial primary key,
	name varchar(255),
	marriageCertificate int
 );

create table wife(
    id serial primary key,
	name varchar(255),
 );
 
create table wife(
    id serial primary key,
	name varchar(255)
);

create table husband_wife(
    id serial primary key,
    husband_id int references husband(id) unique,
    wife_id int references wife(id) unique,
);

insert into husband(name, marriageCertificate) values ('Ken', 100);
insert into husband(name, marriageCertificate) values ('Ted', 120);
insert into husband(name, marriageCertificate) values ('Fred', null);

insert into wife(name) values ('Barby');
insert into wife(name) values ('Olga');
insert into wife(name) values ('Nikolay');

insert into husband_wife(husband_id, wife_id) values (1, 2);
insert into husband_wife(husband_id, wife_id) values (2, 1);
insert into husband_wife(husband_id, wife_id) values (3, 3);

select * from wife where id in (select wife_id from husband_wife)
