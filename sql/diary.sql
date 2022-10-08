create table lessons(
	id serial primary key,
	name varchar(255)
);

create table school_diary(
	id serial primary key,
	date timestamp,
	score int,
	lessons_id int references lessons (id)
);

insert into lessons (name) values ('geography');
insert into lessons (name) values ('russian langauge');
insert into lessons (name) values ('math');
insert into lessons (name) values ('history');
insert into lessons (name) values ('sport');
insert into lessons (name) values ('singing');
insert into lessons (name) values ('drawing');

insert into school_diary (date, score, lessons_id) values ('2020-01-01', 3, 1);
insert into school_diary (date, score, lessons_id) values ('2020-01-02', 5, 2);
insert into school_diary (date, score, lessons_id) values ('2020-01-04', 1, 1);
insert into school_diary (date, score, lessons_id) values ('2020-01-14', 4, 4);
insert into school_diary (date, score, lessons_id) values ('2020-03-04', 5, 5);
insert into school_diary (date, score, lessons_id) values ('2020-01-16', 2, 2);
insert into school_diary (date, score, lessons_id) values ('2020-02-02', 3, 3);
insert into school_diary (date, score, lessons_id) values ('2020-04-04', 1, 4);
insert into school_diary (date, score, lessons_id) values ('2020-01-20', 4, 5);
insert into school_diary (date, score, lessons_id) values ('2020-03-04', 5, 5);

select l.id, l.name, sd.date, sd.score
from lessons as l join school_diary as sd
on sd.lessons_id = l.id
where sd.score < 3;

select l.id Номер, l.name Название, sd.date Дата, sd.score Оценка
from lessons as l join school_diary as sd
on sd.lessons_id = l.id;

select l.name as "Предметы для пересдачи", sd.score Неуд
from lessons as l join school_diary as sd
on sd.lessons_id = l.id
where sd.score = 1;