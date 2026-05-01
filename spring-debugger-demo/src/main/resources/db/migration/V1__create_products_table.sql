create sequence product_id_seq start with 100 increment by 50;

create table products
(
    id          bigint       not null default nextval('product_id_seq'),
    code        varchar(20)  not null unique,
    name        varchar(200) not null,
    image_url   varchar(500),
    description text,
    price       numeric      not null,
    primary key (id)
);
