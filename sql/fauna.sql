create table fauna (
	id serial primary key,
	name text,
	avg_age int,
	discovery_date date
);

insert into fauna(name, avg_age, discovery_date) values ('cat', 5000, '200-01-01');
insert into fauna(name, avg_age, discovery_date) values ('dog', 4000, '300-01-01');
insert into fauna(name, avg_age, discovery_date) values ('blackfish', 300, '1800-11-01');
insert into fauna(name, avg_age, discovery_date) values ('elephant', 12000, '100-03-30');
insert into fauna(name, avg_age, discovery_date) values ('eagle', 15000, '1600-11-21');
insert into fauna(name, avg_age, discovery_date) values ('tiger', 2000, null);
insert into fauna(name, avg_age, discovery_date) values ('butterfly', 500, '1990-06-01');
insert into fauna(name, avg_age, discovery_date) values ('crocodile', 51000, null);
insert into fauna(name, avg_age, discovery_date) values ('fishfrog', 10, '1999-01-01');

select * from fauna where name LIKE '%fish%';
select * from fauna where avg_age > 10000 and avg_age < 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '1950-01-01';