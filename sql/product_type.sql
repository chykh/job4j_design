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

select * from product join type on product.type_id = type.id where type.name = 'Сыр';
select * from product where product.name like '%мороженое%';
select * from product where product.expired_date < CURRENT_DATE;
select * from product order by price desc limit 1;
select type.name, count(*) from product join type on product.type_id = type.id group by type.name;
select * from product join type on product.type_id = type.id where type.name = 'Сыр' or type.name = 'Молоко';
select type.name from product join type on product.type_id = type.id group by type.name  having count(*) < 10;
select * from product join type on product.type_id = type.id;
