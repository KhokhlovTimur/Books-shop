create table if not exists books
(
    id                  bigserial primary key,
    title              varchar(50),
    author_id           bigint references authors (id),
    year_of_publication integer,
    price integer
);

create table authors
(
    id         bigserial   primary key,
    name       varchar(20),
    surname    varchar(30),
    birth_year integer
);

create table users
(
    id         bigserial          primary key,
    session_id varchar(40),
    login      varchar(30),
    password   varchar(30),
    role       varchar(15)
);

create table cart(
    id bigint,
    user_id bigint references users(id) on delete cascade,
    book_id bigint references books(id) on  delete cascade
);

create table orders(
    id bigserial primary key,
    price bigint,
    user_id bigint references users(id) on delete no action
);

create table order_book(
    book_id bigint references books(id) on delete cascade,
    order_id bigint references orders(id) on delete cascade
);
