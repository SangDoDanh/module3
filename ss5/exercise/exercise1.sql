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
('p2', 'buoi', 20, 2, 'buoi ngon lam', 0),
('p3', 'mit', 30, 2, 'mit ngon lam', 1),
('p4', 'xoai', 40, 2, 'xoai ngon lam', 0),
('p5', 'cam', 50, 2, 'cam ngon lam', 1);

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

-- Tạo view lấy về các thông tin: productCode, productName, productPrice, productStatus từ bảng products.
drop view if exists v_product;
create view v_product as 
select product_Name, product_Price, product_status from products;

-- Tiến hành sửa đổi view v_product
create or replace view v_product as 
select product_Name, product_Price, product_status from products
where product_price < 40;

select * from v_product;
-- Tiến hành xoá view
drop view if exists v_product;
-- Tạo store procedure lấy tất cả thông tin của tất cả các sản phẩm trong bảng product
DELIMITER //
create procedure sp_get_all_products()
begin
select * from products;
end //
DELIMITER ;

call sp_get_all_products();

-- Tạo store procedure thêm một sản phẩm mới
DELIMITER //
create procedure sp_add_new_product(
	product_code varchar(30),
    product_Name varchar(45),
    product_Price float,
    product_amount int,
    product_description varchar(150),
    product_status bit
)
begin
	insert into products(product_code, product_Name, product_Price, product_amount, product_description, product_status)
    values 
    (product_code, product_Name, product_Price, product_amount, product_description, product_status);
end //
DELIMITER ;

call sp_add_new_product('p6', 'cam', 100, 2, 'cam ngon lam', 0);
call sp_get_all_products();


-- Tạo store procedure sửa thông tin sản phẩm theo id
DELIMITER //
create procedure sp_edit_product( e_id int,
	e_product_code varchar(30),
    e_product_Name varchar(45),
    e_product_Price float,
    e_product_amount int,
    e_product_description varchar(150),
    e_product_status bit
)
begin
	update products
    set 
    product_code  = e_product_code, 
    product_Name = e_product_Name,
    product_Price = e_product_Price,
    product_amount = e_product_amount,
    product_description = e_product_description,
    product_status = e_product_status
    where id = e_id;
end //
DELIMITER ;

select * from products
where id = 2;
call sp_edit_product(2,'p22', 'buoi hehe', 200, 22, 'buoi ngon lam luon', 0);
select * from products
where id = 2;

-- Tạo store procedure xoá sản phẩm theo id
DELIMITER //
create procedure sp_del_prodduct_by_id(IN id_del int)
begin
	delete from products
    where id = id_del;
end //
DELIMITER ;

call sp_get_all_products();
call sp_del_prodduct_by_id(2);
call sp_get_all_products();