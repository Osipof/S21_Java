drop schema if exists tests cascade;
drop table if exists tests.product;

create schema if not exists tests;

create table if not exists tests.product
(
    identifier integer     not null,
    name       varchar(20) not null,
    price      integer     not null
);