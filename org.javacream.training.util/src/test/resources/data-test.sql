drop table KEYS if exists
drop table AUDIT if exists
create table KEYS (COL_KEY Integer)
create table AUDIT (message varchar(256))
insert into KEYS values (42)
