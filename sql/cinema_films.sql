create table cinema(
 	id serial primary key,
	name varchar(255)
);

create table films(
	id serial primary key,
	name varchar(255),
	genre varchar(255),
	cinema_id int references cinema(id)
);

insert into cinema (name) values ('Пушкинский');
insert into cinema (name) values ('Юность');
insert into films (name, genre, cinema_id) values ('Рэмбо 3', 'боевик', 2);
insert into films (name, genre, cinema_id) values ('Красотка', 'мелодрамма', 2);
insert into films (name, genre, cinema_id) values ('Один дома', 'комедия', 1);

select * from cinema where id in (select cinema_id from films);