create sequence order_id_seq start with 100 increment by 50;

create table orders
(
    id               bigint       not null default nextval('order_id_seq'),
    order_number     uuid         not null unique,
    customer_name    varchar(200) not null,
    customer_email   varchar(200) not null,
    customer_phone   varchar(50)  not null,
    delivery_address varchar(200) not null,
    status           varchar(50)  not null,
    comments         text,
    created_at       timestamp    not null default now(),
    updated_at       timestamp,
    primary key (id)
);

create sequence order_item_id_seq start with 100 increment by 50;

create table order_items
(
    id            bigint       not null default nextval('order_item_id_seq'),
    order_id      bigint       not null references orders (id),
    product_code  varchar(20)  not null,
    product_name  varchar(200) not null,
    product_price numeric      not null,
    quantity      int          not null,
    primary key (id)
);