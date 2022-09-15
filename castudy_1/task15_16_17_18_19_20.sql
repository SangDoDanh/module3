use furuma_management;
-- task 11
-- Hiển thị thông tin các dịch vụ đi kèm đã được sử dụng bởi những khách hàng có 
-- ten_loai_khach là “Diamond” và có dia_chi ở “Vinh” hoặc “Quảng Ngãi”.
select dvk.ten_dich_vu_di_kem , lk.ten_loai_khach, kh.ho_ten, kh.dia_chi
from khach_hang kh join loai_khach lk on kh.ma_loai_khach = lk.ma_loai_khach
join hop_dong hd on hd.ma_khach_hang = kh.ma_khach_hang
join hop_dong_chi_tiet hdd on hd.ma_hop_dong = hdd.ma_hop_dong
join dich_vu_di_kem dvk on hdd.ma_dich_vu_di_kem = dvk.ma_dich_vu_di_kem
where lk.ten_loai_khach = 'Diamond' and (kh.dia_chi like '% vinh' or kh.dia_chi like '% Quảng Ngãi');

-- task 12 
-- Hiển thị thông tin ma_hop_dong, ho_ten (nhân viên), ho_ten (khách hàng), so_dien_thoai (khách hàng), 
-- ten_dich_vu, so_luong_dich_vu_di_kem (được tính dựa trên việc sum so_luong ở dich_vu_di_kem),
-- tien_dat_coc của tất cả các dịch vụ đã từng được khách hàng đặt vào
--  3 tháng cuối năm 2020 nhưng chưa từng được khách hàng đặt vào 6 tháng đầu năm 2021.
select  hd.ma_hop_dong, nv.ho_va_ten as ho_va_ten_NV, kh.ho_ten as ho_va_ten_KH, kh.so_dien_thoat, dv.ten_dich_vu,
hd.tien_dat_coc,
count(dk.ma_dich_vu_di_kem) as so_luong_dich_vu_di_kem,
hd.ngay_lam_hop_dong
from hop_dong hd
join nhan_vien nv on hd.ma_nhan_vien = nv.ma_nhan_vien
join khach_hang kh on hd.ma_khach_hang = kh.ma_khach_hang
join dich_vu dv on hd.ma_dich_vu = dv.ma_dich_vu
join hop_dong_chi_tiet hdd on hdd.ma_hop_dong = hd.ma_hop_dong
join dich_vu_di_kem dk on hdd.ma_dich_vu_di_kem = dk.ma_dich_vu_di_kem
group by ma_hop_dong
having (year(hd.ngay_lam_hop_dong) = 2020 and (month(hd.ngay_lam_hop_dong) in (10, 11, 12)))
or (year(hd.ngay_lam_hop_dong) = 2021 and (month(hd.ngay_lam_hop_dong) not in (1, 2, 3, 4, 5, 6)));

-- task 13 
drop view if exists max_so_luong;
create view max_so_luong as
select sum(hdd.so_luong) as so_luong_dich_vu_di_kem
from hop_dong hd 
join hop_dong_chi_tiet hdd on hd.ma_hop_dong = hdd.ma_hop_dong
join dich_vu_di_kem dk on hdd.ma_dich_vu_di_kem = dk.ma_dich_vu_di_kem
group by dk.ma_dich_vu_di_kem ;

select hd.ma_hop_dong, dk.ten_dich_vu_di_kem, sum(hdd.so_luong) as so_luong_dich_vu_di_kem
from hop_dong hd 
join hop_dong_chi_tiet hdd on hd.ma_hop_dong = hdd.ma_hop_dong
join dich_vu_di_kem dk on hdd.ma_dich_vu_di_kem = dk.ma_dich_vu_di_kem
group by dk.ma_dich_vu_di_kem 
having so_luong_dich_vu_di_kem = (select max(so_luong_dich_vu_di_kem) from max_so_luong);

-- task 14 
select hd.ma_hop_dong, dk.ten_dich_vu_di_kem, count(hdd.ma_dich_vu_di_kem) as so_lan_su_dung
from hop_dong hd 
join hop_dong_chi_tiet hdd on hd.ma_hop_dong = hdd.ma_hop_dong
join dich_vu_di_kem dk on hdd.ma_dich_vu_di_kem = dk.ma_dich_vu_di_kem
group by dk.ma_dich_vu_di_kem
having so_lan_su_dung = 1;

-- task 15

select nv.ma_nhan_vien, nv.ho_va_ten, td.ten_trinh_do, nv.so_dien_thoai, nv.dia_chi, count(hd.ma_hop_dong) as so_luong_hop_dong_da_lap
from nhan_vien nv join hop_dong hd on nv.ma_nhan_vien = hd.ma_nhan_vien
join trinh_do td on td.ma_trinh_do = nv.ma_trinh_do
where year(hd.ngay_lam_hop_dong) between 2020 and 2021
group by nv.ma_nhan_vien
having so_luong_hop_dong_da_lap <= 3;

-- task 16
delete from nhan_vien 
where ma_nhan_vien not in (select nv.ma_nhan_vien
							from nhan_vien nv 
							join hop_dong hd on nv.ma_nhan_vien = hd.ma_nhan_vien
							join trinh_do td on td.ma_trinh_do = nv.ma_trinh_do
							group by nv.ma_nhan_vien
							);

-- task 17
update khach_hang
set ma_loai_khach = 1
where ma_khach_hang IN (select k.ma_khach_hang
						from khach_hang k 
						join hop_dong hd on hd.ma_khach_hang = k.ma_khach_hang
						join dich_vu dv on dv.ma_dich_vu = hd.ma_dich_vu
						join hop_dong_chi_tiet hdd on hdd.ma_hop_dong = hd.ma_hop_dong
						join dich_vu_di_kem dk on dk.ma_dich_vu_di_kem = hdd.ma_dich_vu_di_kem
						join loai_khach lk on lk.ma_loai_khach = k.ma_loai_khach
						where lk.ten_loai_khach = 'Platinium' and year(hd.ngay_lam_hop_dong) = 2021
						group by k.ma_khach_hang
						having sum((dv.chi_phi_the + (hdd.so_luong * dk.gia))) > 1000000);
-- task 18 
drop view if exists khach_hang_2021;
create view khach_hang_2021 as 
select kh.ma_khach_hang
from 
khach_hang kh join hop_dong hd on kh.ma_khach_hang = hd.ma_hop_dong
where year(hd.ngay_lam_hop_dong) < 2021;

set SQL_SAFE_UPDATES = 0;
SET FOREIGN_KEY_CHECKS = 0;
delete from khach_hang
where ma_khach_hang in (select ma_khach_hang from khach_hang_2021);
select * from khach_hang;

select kh.ma_khach_hang,kh.ho_ten, hd.ngay_lam_hop_dong from 
khach_hang kh join hop_dong hd on kh.ma_khach_hang = hd.ma_hop_dong
where year(hd.ngay_lam_hop_dong) < 2021;
-- task 19
select hd.ma_hop_dong, dk.ma_dich_vu_di_kem, dk.ten_dich_vu_di_kem, hdd.so_luong
from hop_dong hd 
join hop_dong_chi_tiet hdd on hd.ma_hop_dong = hdd.ma_hop_dong
join dich_vu_di_kem dk on dk.ma_dich_vu_di_kem = hdd.ma_dich_vu_di_kem
group by dk.ma_dich_vu_di_kem;

-- task 20
select ma_nhan_vien as ma_so, ho_va_ten from nhan_vien
union all
select ma_khach_hang, ho_ten from khach_hang


