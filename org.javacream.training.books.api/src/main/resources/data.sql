drop table KEYS if exists
drop table AUDIT if exists


create table KEYS (COL_KEY Integer)
create table AUDIT (message varchar(256))

insert into BOOK (isbn, title, price) values ('TEST-ISBN', 'TEST-TITLE', 19.99)
insert into BOOK (isbn, title, price) values ('TEST-ISBN2', 'TEST-TITLE', 19.99)
insert into BOOK (isbn, title, price) values ('ISBN1', 'Spring', 9.99)
insert into BOOK (isbn, title, price) values ('ISBN2', 'Spring', 19.99)
insert into BOOK (isbn, title, price) values ('ISBN3', 'Spring', 29.99)

insert into KEYS values (42)
