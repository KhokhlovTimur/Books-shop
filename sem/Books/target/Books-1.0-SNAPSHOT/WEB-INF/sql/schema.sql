create table if not exists authors
(
    id         bigserial primary key,
    name       varchar(20),
    surname    varchar(30),
    birth_year integer
);

create table if not exists books
(
    id                  bigserial primary key,
    title               varchar(50),
    author_id           bigint references authors (id),
    year_of_publication integer,
    price               integer,
    description varchar
);

create table if not exists users
(
    id         bigserial primary key,
    login      varchar(30),
    password   varchar(50),
    role       varchar(15),
    email varchar(40) default 'Не указан',
    balance integer
);

create table if not exists cart
(
    id      bigint,
    user_id bigint references users (id) on delete cascade,
    book_id bigint references books (id) on delete cascade
);

drop table if exists orders;

create table if not exists orders
(
    id      bigserial primary key,
    price   bigint,
    user_id bigint references users (id) on delete no action
);

create table if not exists order_book
(
    book_id  bigint references books (id) on delete cascade,
    order_id bigint references orders (id) on delete cascade
);

