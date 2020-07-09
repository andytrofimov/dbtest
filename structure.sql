--docker run -d -p 49161:1521 --name oracle -e ORACLE_ALLOW_REMOTE=true wnameless/oracle-xe-11g-r2

create user test identified by test default tablespace USERS;
alter user test quota unlimited on users;
alter session set current_schema = TEST;

create table depart
(
    depart_id   NUMBER PRIMARY KEY,
    depart_name VARCHAR2(100)
);

create table depart_add
(
    depart_id NUMBER PRIMARY KEY,
    add_type  VARCHAR2(100),
    add_value VARCHAR2(100)
);

create table hierarchy
(
    parent_id NUMBER not null,
    child_id  NUMBER not null
);

create table dept_cfo (
    depart_id NUMBER not null,
    cfo VARCHAR2(100)
);

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

-- процедура
CREATE PROCEDURE calc_dept_cfo
as
BEGIN
    execute immediate 'truncate table DEPT_CFO';
    insert into DEPT_CFO (depart_id, cfo)
    select dep.DEPART_ID,
           coalesce(da.ADD_VALUE, (select tmp.ADD_VALUE
                                   from (select d.*, h.PARENT_ID, da.ADD_VALUE
                                         from DEPART d
                                                  left join HIERARCHY h on d.DEPART_ID = h.CHILD_ID
                                                  left join (select * from DEPART_ADD da where ADD_TYPE = 'CFO') da
                                                            on da.DEPART_ID = d.DEPART_ID
                                        ) tmp
                                   where tmp.ADD_VALUE is not null
                                     and ROWNUM = 1
                                   connect by tmp.DEPART_ID = prior tmp.PARENT_ID
                                   start with tmp.DEPART_ID = dep.DEPART_ID)) cfo
    from DEPART dep
             left join (select * from DEPART_ADD da where ADD_TYPE = 'CFO') da
                       on da.DEPART_ID = dep.DEPART_ID
    order by dep.DEPART_ID;
END;

