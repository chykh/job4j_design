create table cars(
	id serial primary key,
	brand text,
	model varchar,
	crashed boolean,
	year smallint,
	run integer	
);
select * from cars;
insert into cars(brand, model, crashed, year, run) values ('Toyoata', 'Caldina', false, 1997, 200);
insert into cars(brand, model, crashed, year, run) values ('Citroen', 'C1', false, 2008, 120);
select * from cars;
update cars set run = 50;
select * from cars;
delete from cars;
select * from cars;