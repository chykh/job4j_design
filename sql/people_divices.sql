create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values('Телефон', 15000), ('Смарт весы', 3000), ('Приставка', 23000), ('Радио', 6000), ('Видеокамера', 34000);
insert into people(name) values('Иван'), ('Глеб'), ('Света'), ('Даша');
insert into devices_people(device_id, people_id) values(1, 1), (1, 3), (2, 1), (2,4), (2,2), (3, 3), (3,4), (4,1), (4, 4);

select avg(price) as "Средняя цена девайсов" from devices;

select devices_people.people_id, avg(devices.price) 
from devices_people join devices
on devices_people.device_id = devices.id 
group by devices_people.people_id;


select devices_people.people_id, avg(devices.price) 
from devices_people join devices
on devices_people.device_id = devices.id 
group by devices_people.people_id
having avg(devices.price) > 500;