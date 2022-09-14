use furuma_management;
-- task 6
select d.ma_dich_vu, d.ten_dich_vu, d.dien_tich, d.so_nguoi_toi_da, d.chi_phi_the, l.ten_loai_dich_vu, h.ngay_lam_hop_dong
from dich_vu d join hop_dong h on d.ma_dich_vu = h.ma_dich_vu 
join loai_dich_vu l on d.ma_loai_dich_vu = l.ma_loai_dich_vu
where (year(h.ngay_lam_hop_dong) = 2021 and not (month(h.ngay_lam_hop_dong) in (1, 2, 3)));

-- task 7
select d.ma_dich_vu, d.ten_dich_vu, d.dien_tich, d.so_nguoi_toi_da, d.chi_phi_the, l.ten_loai_dich_vu, h.ngay_lam_hop_dong
from dich_vu d join hop_dong h on d.ma_dich_vu = h.ma_dich_vu 
join loai_dich_vu l on d.ma_loai_dich_vu = l.ma_loai_dich_vu
where year(h.ngay_lam_hop_dong) = 2020 or (year(h.ngay_lam_hop_dong) = 2021 and not (month(h.ngay_lam_hop_dong) in (1, 2, 3)));

-- task 8 
select ho_ten from khach_hang
group by ho_ten;

-- task 9
select * from khach_hang k join hop_dong h on k.ma_khach_hang = h.ma_khach_hang
where year(h.ngay_lam_hop_dong) = 2021
group by k.ma_khach_hang;
select * from hop_dong_chi_tiet;
-- task 10
select h.ma_hop_dong, h.ngay_lam_hop_dong, h.ngay_ket_thuc, h.tien_dat_coc, count(dk.ma_dich_vu_di_kem) as so_luong_dv_di_kem
 from hop_dong h join hop_dong_chi_tiet hd on h.ma_hop_dong = hd.ma_hop_dong
 join dich_vu_di_kem dk on hd.ma_dich_vu_di_kem = dk.ma_dich_vu_di_kem
 group by ma_hop_dong;
