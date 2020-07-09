truncate table depart;
truncate table depart_add;
truncate table hierarchy;

insert into depart
values (1, 'Отдел1');
insert into depart_add
values (1, 'CFO', 'D1');

insert into depart
values (2, 'Служба11');
insert into hierarchy
values (1, 2);

insert into depart
values (3, 'Группа111');
insert into hierarchy
values (2, 3);

insert into depart
values (4, 'Группа112');
insert into hierarchy
values (2, 4);

insert into depart
values (5, 'Служба12');
insert into depart_add
values (5, 'CFO', 'S12');
insert into hierarchy
values (1, 5);

insert into depart
values (6, 'Группа121');
insert into hierarchy
values (5, 6);

insert into depart
values (7, 'Группа122');
insert into depart_add
values (7, 'CFO', null);
insert into hierarchy
values (5, 7);

insert into depart
values (8, 'Группа123');
insert into depart_add
values (8, 'CFO', 'G123');
insert into hierarchy
values (5, 8);