use furuma_management;

-- task 2
select * from nhan_vien
where (ho_va_ten like 'h%' or ho_va_ten like 'k%' or ho_va_ten like 't%') and length(ho_va_ten) <= 15;

-- task 3
select *, year(CURDATE()) - year(ngay_sinh) as tuoi  from khach_hang
where  (year(CURDATE()) - year(ngay_sinh) between 18 and 50 ) and (dia_chi like '% Đà Nẵng' or dia_chi like '% Quảng Trị');

-- task 4 
select *, count(h.ma_khach_hang) as 'so_lan_dat_phong'
from khach_hang k join hop_dong h on k.ma_khach_hang = h.ma_khach_hang 
where ma_loai_khach = (select ma_loai_khach from loai_khach where ten_loai_khach = 'Diamond')
group by h.ma_khach_hang
order by so_lan_dat_phong;
 
-- task 5
select k.ma_khach_hang, k.ho_ten, l.ten_loai_khach, h.ma_hop_dong, h.ngay_lam_hop_dong, h.ngay_ket_thuc,
d.chi_phi_the + (dv.gia * ht.so_luong) as tong_tien
from  ((((khach_hang k join loai_khach l on l.ma_loai_khach = k.ma_loai_khach)
		left join hop_dong h on k.ma_khach_hang = h.ma_khach_hang)
        join hop_dong_chi_tiet ht on h.ma_hop_dong = ht .ma_hop_dong)
        join dich_vu_di_kem dv on dv.ma_dich_vu_di_kem = ht.ma_dich_vu_di_kem)
        join dich_vu d on d.ma_dich_vu = h.ma_dich_vu;
        
-- task 6
