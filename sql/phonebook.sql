create table phonebook (
	id serial primary key,
	surname varchar(50),
	name varchar(50),
	phone varchar
);

insert into phonebook (surname, name, phone) VALUES ('Князев', 'Никита', '123-45-67');


start transaction isolation level read committed;
insert into phonebook (surname, name, phone) VALUES ('Селезнева', 'Алиса', '115-18-22');
insert into phonebook (surname, name, phone) VALUES ('Власов', 'Олег', '003-25-14');

savepoint first_sp;

select * from phonebook;

delete from phonebook where name = 'Алиса';

insert into phonebook (surname, name, phone) VALUES ('Рязанский', 'Никита', '987-65-43');

drop table phonebook;

rollback to first_sp;
select * from phonebook;

rollback transaction;
select * from phonebook;

start transaction;
insert into phonebook (surname, name, phone) VALUES ('Уиллис', 'Брюс', '105-00-22');
insert into phonebook (surname, name, phone) VALUES ('Бонд', 'Джеймс', '007-50-07');

commit;

select * from phonebook;