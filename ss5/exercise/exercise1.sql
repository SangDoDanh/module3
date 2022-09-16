Drop database if exists demo;
create database demo;
use demo;

create table products(
	id int auto_increment primary key,
    product_code varchar(30),
    product_Name varchar(45),
    product_Price float,
    product_amount int,
    product_description varchar(150),
    product_status bit
);
select * from products;
insert into products(product_code, product_Name, product_Price, product_amount, product_description, product_status) 
values
('p1', 'tao', 10, 2, 'tao ngon lam', 1),
('p2', 'buoi', 10, 2, 'buoi ngon lam', 0),
('p3', 'mit', 10, 2, 'mit ngon lam', 1),
('p4', 'xoai', 10, 2, 'xoai ngon lam', 0),
('p5', 'cam', 10, 2, 'cam ngon lam', 1);

-- Tạo Unique Index trên bảng Products (sử dụng cột productCode để tạo chỉ mục)
alter table products
add unique index i_product_code(product_code);
-- Tạo Composite Index trên bảng Products (sử dụng 2 cột productName và productPrice)
alter table products
add index i_product_name_price(product_name, product_price);

-- Sử dụng câu lệnh EXPLAIN để biết được câu lệnh SQL của bạn thực thi như nào
explain select * from products where product_name like 'xoai';

-- So sánh câu truy vấn trước và sau khi tạo index
	-- co index 
explain select * from products where product_name like 'xoai';
	-- ko co index
drop index i_product_name_price on products;
explain select * from products where product_name like 'xoai';
