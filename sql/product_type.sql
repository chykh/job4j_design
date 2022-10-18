create table product(
	id serial primary key,
	name text,
	type_id int,
	expired_date date,
	price int
);

create table type(
	id serial primary key,
	name text
);

insert into type (name) values ('Колбаса'), ('Молоко'), ('Хлеб'), ('Сладости'), ('Сыр');

insert into product (name, type_id, expired_date, price) values ('Докторская', 1, '2022-12-05', 300);
insert into product (name, type_id, expired_date, price) values ('Сальчичон', 1, '2023-12-05', 600);
insert into product (name, type_id, expired_date, price) values ('Домик в деревне', 2, '2022-10-06', 40);
insert into product (name, type_id, expired_date, price) values ('Савушкино', 2, '2022-11-06', 80);
insert into product (name, type_id, expired_date, price) values ('Филевское мороженое', 2, '2022-12-21', 180);
insert into product (name, type_id, expired_date, price) values ('Бородинский', 3, '2022-09-25', 20);
insert into product (name, type_id, expired_date, price) values ('Лаваш', 3, '2022-10-18', 35);
insert into product (name, type_id, expired_date, price) values ('Сникерс', 4, '2023-03-13', 47);
insert into product (name, type_id, expired_date, price) values ('Маасдам', 5, '2023-04-23', 550);

select * from product as p
join type as t 
on p.type_id = t.id
where t.name = 'Сыр';

select * from product 
where product.name like '%мороженое%';

select * from product
where product.expired_date < CURRENT_DATE;

select max(product.price) 
from product;

select * from product
where product.price = 600;

select t.name, count(*) 
from product as p join type as t 
on p.type_id = t.id
group by t.name;

select * from product as p
join type as t
on p.type_id = t.id
where t.name = 'Сыр'
or t.name = 'Молоко';

select t.name
from product as p
join type as t 
on p.type_id = t.id
group by t.name 
having count(*) < 10;

select * from product as p
join type as t
on p.type_id = t.id;
