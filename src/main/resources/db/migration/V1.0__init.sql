create table book
(
    id                uuid PRIMARY KEY,
    title             varchar(50)                           not null,
    create_date       date             default CURRENT_DATE not null,
    author_first_name varchar(50)                           not null,
    author_last_name  varchar(50)                           not null
);