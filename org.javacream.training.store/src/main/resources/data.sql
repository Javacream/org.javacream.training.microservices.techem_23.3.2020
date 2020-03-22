drop table STORE if exists
create table STORE (category varchar(256), item_id varchar(256), stock Integer, primary key (category, item_id))
insert into STORE (category, item_id, stock) values ('BOOKS', 'ISBN1', 10)
insert into STORE (category, item_id, stock) values ('BOOKS', 'ISBN2', 5)
insert into STORE (category, item_id, stock) values ('BOOKS', 'ISBN3', 0)
insert into STORE (category, item_id, stock) values ('TEST_CATEGORY', 'TEST_ID', 42)
