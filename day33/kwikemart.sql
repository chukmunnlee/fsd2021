drop database if exists kwikemart;

create database kwikemart;

use kwikemart;

create table po (
    ord_id int auto_increment not null,
    name varchar(64) not null,
    email varchar(64),
    primary key(ord_id)
);

create table line_item (
    item_id int auto_increment not null,
    description varchar(128), 
    quantity int,
    ord_id int, -- foreign key
    unit_price float(7, 2),

    primary key(item_id),
    constraint fk_ord_id
        foreign key(ord_id)
        references po(ord_id)
);

create view po_total as 
	select p.ord_id, p.name, sum(li.unit_price * li.quantity) as total 
		from po as p
		join line_item as li
		on p.ord_id = li.ord_id
		group by p.ord_id, p.name;
