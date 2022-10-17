create table if not exists books
(
    id                  bigserial primary key,
    tittle              varchar(50),
    author_id           bigint references authors (id),
    year_of_publication integer,
    quantity            integer,
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
