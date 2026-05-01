create table users
(
    id         bigserial primary key,
    email      text      not null unique,
    password   text      not null,
    name       text      not null,
    role       text      not null,
    created_at timestamp not null default now(),
    updated_at timestamp not null default now(),
    version    integer
);

create table books
(
    id         bigserial primary key,
    isbn       text      not null unique,
    title      text      not null,
    author     text      not null,
    created_by bigint    not null references users (id),
    created_at timestamp not null default now(),
    updated_at timestamp not null default now(),
    version    integer
);

create table reviews
(
    id         bigserial primary key,
    book_id    bigint    not null references books (id),
    user_id    bigint    not null references users (id),
    rating     integer   not null,
    review     text      not null,
    created_at timestamp not null default now(),
    updated_at timestamp not null default now(),
    version    integer,
    unique (book_id, user_id)
);
