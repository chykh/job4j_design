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

select p.name as Имя, avg(d.price) as Средняя_цена 
from devices_people as d_p
join people as p
on d_p.people_id = p.id
join devices as d
on d.id = d_p.device_id
group by p.name;

select p.name as Имя, avg(d.price) as Средняя_цена 
from devices_people as d_p
join people as p
on d_p.people_id = p.id
join devices as d
on d.id = d_p.device_id
group by p.name
having avg(d.price) > 5000;