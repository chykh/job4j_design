create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

-- Задание, чаcть №1
insert into products (name, producer, count, price) values ('Докторская колбаса', 'Останкино', 20, 100);
insert into products (name, producer, count, price) values ('Аленка', 'Бабаевский', 14, 65);
insert into products (name, producer, count, price) values ('Чупа-чупс', 'Смерлинг комп', 314, 8);
insert into products (name, producer, count, price) values ('Икра красная', 'Санта-бремор', 30, 300);
insert into products (name, producer, count, price) values ('Бородинский хлеб', 'Макфа', 10, 20);


create or replace procedure delete(d_count integer, d_price integer)
language 'plpgsql'
as $$
	BEGIN
		delete from products
		where count < d_count;
		delete from products
		where price < d_price;
	end
$$;

call delete(20, 100);

select * from products;

drop procedure delete(d_count integer, d_price integer);

delete from products;

-- Задание, чаcть №2
insert into products (name, producer, count, price) values ('Докторская колбаса', 'Останкино', 20, 100);
insert into products (name, producer, count, price) values ('Аленка', 'Бабаевский', 14, 65);
insert into products (name, producer, count, price) values ('Чупа-чупс', 'Смерлинг комп', 314, 8);
insert into products (name, producer, count, price) values ('Икра красная', 'Санта-бремор', 30, 300);
insert into products (name, producer, count, price) values ('Бородинский хлеб', 'Макфа', 10, 20);

create or replace function f_delete(f_name varchar)
returns integer
language 'plpgsql'
as
$$
	DECLARE
		result integer;
	BEGIN
		select into result id from products where name = f_name;
		delete from products where name = f_name;
		return result;
	end;
$$;

select * from products;

select f_delete('Аленка');