-- drop database if exists
drop database if exists mysocialmedia;

create database mysocialmedia;

use mysocialmedia;

create table post (
    post_id int not null auto_increment,
    photo mediumblob, 
    comment mediumtext,
    poster varchar(64),
    mediatype varchar(256),

    primary key(post_id)
);