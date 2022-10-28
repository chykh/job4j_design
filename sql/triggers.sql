create table products(
	id serial primary key,
	name varchar(50),
	producer varchar(50),
	count integer default 0,
	price integer
);

-- задание 1
create or replace function tax1()
    returns trigger as
$$
    BEGIN
        update products
        set price = price * 1.2
        where id = (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';	

create trigger tax_trigger1
    after insert on products
    referencing new table as inserted
    for each statement
    execute procedure tax1();

insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 2, 20);
insert into products (name, producer, count, price) VALUES ('product_2', 'producer_2', 8, 64);
insert into products (name, producer, count, price) VALUES ('product_3', 'producer_3', 15, 115);
insert into products (name, producer, count, price) VALUES ('product_4', 'producer_4', 3, 50);

drop trigger tax_trigger1 on products;
select * from products;
delete from products;

-- задание 2
create or replace function tax2()
    returns trigger as
$$
    BEGIN
		new.price = new.price * 1.2;
		return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger2
    before insert
    on products
    for each row
    execute procedure tax2();

insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 2, 20);
insert into products (name, producer, count, price) VALUES ('product_2', 'producer_2', 8, 64);
insert into products (name, producer, count, price) VALUES ('product_3', 'producer_3', 15, 115);
insert into products (name, producer, count, price) VALUES ('product_4', 'producer_4', 3, 50);

select * from products;


--задание 3
create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

create or replace function fill_hop()
    returns trigger as
$$
    BEGIN
		insert into history_of_price(name, price, date) values(new.name, new.price, current_date);
		return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger fill_trigger
    after insert
    on products
    for each row
    execute procedure fill_hop();

insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 2, 20);
insert into products (name, producer, count, price) VALUES ('product_2', 'producer_2', 8, 64);
insert into products (name, producer, count, price) VALUES ('product_3', 'producer_3', 15, 115);
insert into products (name, producer, count, price) VALUES ('product_4', 'producer_4', 3, 50);

select * from history_of_price;