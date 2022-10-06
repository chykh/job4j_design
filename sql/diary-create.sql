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