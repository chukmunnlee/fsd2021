drop database if exists todos;

-- Create a database call todos
create database todos;

use todos;

-- Create a table call tasks
-- tid - auto increment, primary key
-- username - 64 chars, non null
-- task_name - 256 chars, non null
-- priority - enum low, med, high, default to low
-- due_date - date
create table task (
    tid int auto_increment not null,
    username varchar(64) not null,
    task_name varchar(256) not null,
    priority enum('low', 'med', 'high') default 'low',
    due_date date,
    primary key(tid)
);

create table user (
    username varchar(64) not null,
    password varchar(512) not null,
    primary key(username)
);