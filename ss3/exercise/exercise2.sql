use quan_li_ban_hang;

-- Hiển thị các thông tin  gồm oID, oDate, oPrice của tất cả các hóa đơn trong bảng Orders
select oder_id, order_date, total_price from orders;

-- Hiển thị danh sách các khách hàng đã mua hàng, và danh sách sản phẩm được mua bởi các khách
select c.customer_name, o.order_date, p.product_name
from 
(orders o join order_detail od on o.oder_id = od.oder_id)
join product p on p.product_id = od.product_id join customer c on c.customer_id = o.customer_id;

-- Hiển thị tên những khách hàng không mua bất kỳ một sản phẩm nào
select * from customer c left join orders o on c.customer_id = o.customer_id
where o.oder_id is null;

-- Hiển thị mã hóa đơn, ngày bán và giá tiền của từng hóa đơn 
-- (giá một hóa đơn được tính bằng tổng giá bán của từng loại mặt hàng xuất hiện trong hóa đơn.
-- Giá bán của từng loại được tính = odQTY*pPrice)

select o.oder_id, sum((p.product_price * od.quanlity)) as totals, o.order_date, c.customer_name
from 
(orders o join order_detail od on o.oder_id = od.oder_id)
join product p on p.product_id = od.product_id join customer c on c.customer_id = o.customer_id
group by o.oder_id;
