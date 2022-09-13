drop database if exists quan_li_ban_hang;
create database quan_li_ban_hang;
use quan_li_ban_hang;


-- tao cac bang
create table customer(
	customer_id int auto_increment primary key,
    customer_name varchar(45),
    customer_age int
);

create table orders(
	oder_id int auto_increment primary key,
    customer_id int,
    order_date date,
    total_price double
);

create table product(
	product_id int auto_increment primary key,
    product_name varchar(45),
    product_price double
);

create table order_detail(
	oder_id int,
    product_id int,
    quanlity int
);

-- tao cac quan he cho cac bang
alter table order_detail
add foreign key(oder_id) references orders(oder_id),
add foreign key(product_id) references product(product_id);

alter table orders
add foreign key(customer_id) references customer(customer_id);