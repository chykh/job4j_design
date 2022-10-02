create table taxiDrivers(
	id serial primary key,
	name varchar(255),
	city varchar(255),
	license int
);

create table passengers(
	id serial primary key,
	name varchar(255)
);

create table taxiDrivers_passengers(
	id serial primary key,
	taxiDriver_id int references taxiDrivers(id),
	passenger_id int references passengers(id)
);

insert into taxiDrivers(name, city, license) values ('Ivan', 'Moscow', 55);
insert into taxiDrivers(name, city, license) values ('Sasha', 'Rostov', 53);
insert into taxiDrivers(name, city, license) values ('Igor', 'Moscow', 65);

insert into passengers(name) values ('Ivanka');
insert into passengers(name) values ('Natasha');

insert into taxiDrivers_passengers(taxiDriver_id, passenger_id) values (1, 1);
insert into taxiDrivers_passengers(taxiDriver_id, passenger_id) values (3, 2);

select * from taxiDrivers where id in (select taxiDriver_id from taxiDrivers_passengers);