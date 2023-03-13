drop table if exists users cascade;
drop table if exists items cascade;
drop table if exists tags cascade;

create table users
(
    id                int generated by default as identity primary key,
    first_name        varchar(30),
    last_name         varchar(50),
    email             varchar(100),
    registration_date timestamp,
    state             varchar(8),
    date_of_birth     timestamp
);

create table items
(
    id            int generated by default as identity primary key,
    name          varchar(100),
    user_id       int references users,
    url           varchar(200),
    resolved_url  varchar,
    mime_type     varchar,
    title         varchar,
    has_image     boolean,
    has_video     boolean,
    date_resolved timestamp,
    unread        boolean
);

create table tags
(
    id      int generated by default as identity primary key,
    name    varchar(100),
    item_id int references items
);

CREATE TABLE IF NOT EXISTS users
(
    id                INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    email             varchar(320),
    first_name        varchar(100),
    last_name         varchar(100),
    registration_date timestamp,
    state             varchar(50),
    date_of_birth     timestamp
);

CREATE TABLE IF NOT EXISTS items
(
    id            INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id       INT,
    url           VARCHAR(1000),
    resolved_url  VARCHAR(1000),
    mime_type     VARCHAR(100),
    title         VARCHAR(255),
    has_image     BOOLEAN,
    has_video     BOOLEAN,
    unread        BOOLEAN,
    date_resolved TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT fk_items_to_users FOREIGN KEY (user_id) REFERENCES users (id),
    UNIQUE (user_id, resolved_url)
);

CREATE TABLE IF NOT EXISTS tags
(
    id      INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    item_id INT,
    name    VARCHAR(50),
    CONSTRAINT fk_tags_to_items FOREIGN KEY (item_id) REFERENCES items (id)
);