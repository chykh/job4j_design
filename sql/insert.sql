insert into role(name) values('Admin');
insert into role(name) values('User');
insert into role(name) values('Guest');

insert into users(name, role_id) values('Oleg', 1);
insert into users(name, role_id) values('Maks', 2);
insert into users(name, role_id) values('Stepan', 2);
insert into users(name, role_id) values('Evgenii', 3);

insert into rules(name) values('All');
insert into rules(name) values('No_delete');
insert into rules(name) values('No_rename');
insert into rules(name) values('No_save');
insert into rules(name) values('No_internet');
insert into rules(name) values('No_install');

insert into role_rules(role_id, rules_id) values(1, 1);
insert into role_rules(role_id, rules_id) values(2, 2);
insert into role_rules(role_id, rules_id) values(2, 3);
insert into role_rules(role_id, rules_id) values(2, 5);
insert into role_rules(role_id, rules_id) values(3, 2);
insert into role_rules(role_id, rules_id) values(3, 3);
insert into role_rules(role_id, rules_id) values(3, 4);
insert into role_rules(role_id, rules_id) values(3, 5);
insert into role_rules(role_id, rules_id) values(3, 6);

insert into category(name) values('Urgent');
insert into category(name) values('Regular');

insert into state(name) values('Done');
insert into state(name) values('Not_done');

insert into comments(name) values('Who deleted all the files?');
insert into comments(name) values('When are we going to have a corporate party?');

insert into attachs(name) values('drawing.dwg');
insert into attachs(name) values('text.txt');
insert into attachs(name) values('calender2022.jpg');

insert into item(name, users_id, сategory_id, state_id) values('Complete project #4', 1, 1 , 2);
insert into item(name, users_id, сategory_id, state_id) values('Look throught new CVs', 1, 1 , 2);
insert into item(name, users_id, сategory_id, state_id) values('Fix the email agent', 2, 2 , 1);
insert into item(name, users_id, сategory_id, state_id) values('Clean the reestr', 3, 1 , 1);
insert into item(name, users_id, сategory_id, state_id) values('Check for updates of MS office', 3, 1 , 1);
insert into item(name, users_id, сategory_id, state_id) values('Take a sleep in the conference room', 4, 2 , 2);