--учет продуктов в холодильниках
create table fridge(
	id serial primary key,
	name varchar(50)
);

create table section(
	id serial primary key,
	name varchar(50),
	fridge_id int references fridge(id)
);

create table products(
	id serial primary key,
	name varchar(50),
	price int,
	expired_date date,
	section_id int references section(id) 
);

insert into fridge (name) values ('Банкетная');
insert into fridge (name) values ('Кухня');
insert into fridge (name) values ('Гостевой дом');
insert into fridge (name) values ('Веранда');

insert into section (name, fridge_id) values ('Молочка', 1);
insert into section (name, fridge_id) values ('Сладости', 1);
insert into section (name, fridge_id) values ('Замороженное', 1);
insert into section (name, fridge_id) values ('Молочка', 2);
insert into section (name, fridge_id) values ('Мясное', 2);
insert into section (name, fridge_id) values ('Рыба', 3);
insert into section (name, fridge_id) values ('Замороженное', 3);

insert into products (name, price, expired_date, section_id) values ('Молоко', 30, '2022-08-08', 1);
insert into products (name, price, expired_date, section_id) values ('Йогурт', 300, '2022-08-06', 1);
insert into products (name, price, expired_date, section_id) values ('Торт', 1400, '2022-10-28', 2);
insert into products (name, price, expired_date, section_id) values ('Мороженое', 200, '2022-11-28', 3);
insert into products (name, price, expired_date, section_id) values ('Кефир', 60, '2022-11-08', 4);
insert into products (name, price, expired_date, section_id) values ('Сыр', 420, '2022-12-18', 4);
insert into products (name, price, expired_date, section_id) values ('Сосиски', 210, '2022-12-01', 5);
insert into products (name, price, expired_date, section_id) values ('Форель', 1520, '2023-02-01', 6);
insert into products (name, price, expired_date, section_id) values ('Шпроты', 120, '2023-02-07', 6);
insert into products (name, price, expired_date, section_id) values ('Ягоды', null, '2023-02-01', 7);


create view show_products as
	select s.name as Секция,
	p.name as Продукт,
	p.price as Цена,
	p.expired_date as Срок_годности
	from products as p
	left join section as s
	on p.section_id = s.id
	join fridge as f
	on s.fridge_id = f.id; 
	
select * from show_products;

select show_products.Продукт,
show_products.Цена
from show_products
group by show_products.Продукт,
show_products.Цена
having show_products.Цена < 100;

select show_products.Продукт,
show_products.Срок_годности
from show_products
group by show_products.Продукт,
show_products.Срок_годности
having show_products.Срок_годности
< CURRENT_DATE;

	
	