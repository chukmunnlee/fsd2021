drop database if exists bank;
create database bank;
use bank;

create table account (
    acct_id int not null auto_increment,
    name varchar(64),
    balance float(10, 2) default 0.0,
    last_update timestamp 
        default current_timestamp
        on update current_timestamp,

    primary key(acct_id)
);