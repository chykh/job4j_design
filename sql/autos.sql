create table car_bodies(
	id serial primary key,
	name varchar
);

create table car_engines(
	id serial primary key,
	name varchar
);

create table car_transmissions(
	id serial primary key,
	name varchar
);

create table cars(
	id serial primary key,
	name varchar,
	body_id int references car_bodies(id),
	engine_id int references car_engines(id),
	transmission_id int references car_transmissions(id)
);

insert into car_bodies(name) values('Седан');
insert into car_bodies(name) values('Универсал');
insert into car_bodies(name) values('Джип');
insert into car_bodies(name) values('Хэтчбэк');
insert into car_bodies(name) values('Грузовик');

insert into car_engines(name) values('E100');
insert into car_engines(name) values('E150');
insert into car_engines(name) values('E200');
insert into car_engines(name) values('E250');
insert into car_engines(name) values('E400');

insert into car_transmissions(name) values('Механика');
insert into car_transmissions(name) values('Автомат');
insert into car_transmissions(name) values('Робот');
insert into car_transmissions(name) values('Вариатор');

insert into cars(name, body_id, engine_id, transmission_id) values('Ford F50', 1, 4, null);
insert into cars(name, body_id, engine_id, transmission_id) values('Toyota T90', 3, null, 1);
insert into cars(name, body_id, engine_id, transmission_id) values('Scoda S20', 2, 3, 3);
insert into cars(name, body_id, engine_id, transmission_id) values('Volvo V80', null, 2, null);
insert into cars(name, body_id, engine_id, transmission_id) values('Lada L200', 4, 4, 2);
insert into cars(name, body_id, engine_id, transmission_id) values('Citroen C10', 4, null, 1);
insert into cars(name, body_id, engine_id, transmission_id) values('Peugeout P7', 2, 3, 1);
insert into cars(name, body_id, engine_id, transmission_id) values('Tavria T60', null, null, null);
insert into cars(name, body_id, engine_id, transmission_id) values('Opel O4', 3, 3, 3);

select c.name, c_b.name, c_e.name, c_t.name
from cars as c
left join car_bodies as c_b
on c.body_id = c_b.id
left join car_engines as c_e
on c.engine_id = c_e.id
left join car_transmissions as c_t
on c.transmission_id = c_t.id;

select c_b.name, c.name
from car_bodies as c_b
left join cars as c
on c.body_id = c_b.id
where c.name is null;

select c_e.name, c.name
from car_engines as c_e
left join cars as c
on c.engine_id = c_e.id
where c.name is null;

select c_t.name, c.name
from car_transmissions as c_t
left join cars as c
on c.transmission_id = c_t.id
where c.name is null;