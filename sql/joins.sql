--задача 1
create table departments(  
	id serial primary key,
	name varchar(255)
);

create table employees(
	id serial primary key,
	name varchar(255),
	department_id int references departments(id)
);

insert into departments(name) values ('Бухгалтерия');
insert into departments(name) values ('Юристы');
insert into departments(name) values ('Закупки');

insert into employees(name, department_id) values ('Павел', 1);
insert into employees(name, department_id) values ('Никита', 1);
insert into employees(name, department_id) values ('Маша', 3);
insert into employees(name, department_id) values ('Олег', 1);
insert into employees(name, department_id) values ('Гриша_Стажер', null);

--задача 2
select * from departments as dep
left join employees as emp
on emp.department_id = dep.id;

select * from departments as dep
right join employees as emp
on emp.department_id = dep.id;

select * from departments as dep
full join employees as emp
on emp.department_id = dep.id;

select * from departments cross join employees; 

--задача 3
select * from departments as dep
left join employees as emp
on emp.department_id = dep.id
where emp.department_id is null; 

--задача 4
select * from departments as dep
left join employees as emp 
on emp.department_id = dep.id
where emp.department_id is not null;

select * from departments as dep 
right join employees as emp
on emp.department_id = dep.id
where emp.department_id is not null;


--задача 5
create table teens(   
	id serial primary key,
	name varchar(255),
	gender varchar(255)
);

insert into teens(name, gender) values ('Павел', 'мужской');
insert into teens(name, gender) values ('Никита', 'мужской');
insert into teens(name, gender) values ('Оля', 'женский');
insert into teens(name, gender) values ('Маша', 'женский');
insert into teens(name, gender) values ('Света', 'женский');

select male.name, female.name
from teens male cross join teens female
where male.gender != female.gender;