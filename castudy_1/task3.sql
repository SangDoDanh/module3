-- them du lieu cho bang nhan_vien
use furuma_management;
insert into nhan_vien(ma_nhan_vien, ho_ten, ngay_sinh, so_cmnd,	luong, so_dien_thoai, email, dia_chi, ma_vi_tri, ma_trinh_do, ma_bo_phan)
values
(1,	'Nguyễn Văn An',	'1970-11-07',	'456231786'	,10000000,	'0901234121',	'annguyen@gmail.com',	'295 Nguyễn Tất Thành, Đà Nẵng',	1,	3,	1),
(2,	'Lê Văn Bình',	'1997-04-09'	,'654231234',	7000000,	'0934212314',	'binhlv@gmail.com',	'22 Yên Bái, Đà Nẵng',	1	,2	,2),
(3	,'Hồ Thị Yến'	,'1995-12-12'	,'999231723'	,14000000	,'0412352315'	,'thiyen@gmail.com'	,'K234/11 Điện Biên Phủ, Gia Lai'	,1	,3	,2),
(4	,'Võ Công Toản'	,'1980-04-04',	'123231365'	,17000000	,'0374443232'	,'toan0404@gmail.com'	,'77 Hoàng Diệu, Quảng Trị'	,1	,4	,4),
(5,	'Nguyễn Bỉnh Phát',	'1999-12-09',	'454363232',	6000000,	'0902341231',	'phatphat@gmail.com',	'43 Yên Bái, Đà Nẵng',	2,	1,	1),
(6,	'Khúc Nguyễn An Nghi',	'2000-11-08',	'964542311',	7000000	,'0978653213',	'annghi20@gmail.com',	'294 Nguyễn Tất Thành, Đà Nẵng',	2,	2,	3),
(7,	'Nguyễn Hữu Hà',	'1993-01-01',	'534323231',	8000000	,'0941234553',	'nhh0101@gmail.com',	'4 Nguyễn Chí Thanh, Huế',	2,	3,	2),
(8,	'Nguyễn Hà Đông',	'1989-09-03',	'234414123',	9000000,	'0642123111',	'donghanguyen@gmail.com',	'111 Hùng Vương, Hà Nội',	2,	4,	4),
(9,	'Tòng Hoang',	'1982-09-03',	'256781231',	6000000	,'0245144444',	'hoangtong@gmail.com',	'213 Hàm Nghi, Đà Nẵng',	2,	4,	4),
(10,	'Nguyễn Công Đạo',	'1994-01-08',	'755434343',	8000000	,'0988767111',	'nguyencongdao12@gmail.com',	'6 Hoà Khánh, Đồng Nai',	2,	3,	2);
 
 -- them du lieu cho bang vi_tri
 insert into vi_tri(ma_vi_tri, ten_vi_tri) 
 values
 (1, 'Quản lý'),
 (2, 'Nhân viên');
 
 -- thêm dữ liệu cho bảng trinh_do
 insert into trinh_do(ma_trinh_do, ten_trinh_do)
 values 
 (1, 'trung cấp'),
 (2, 'cao đẳng'),
 (3, 'đại học'),
 (4, 'sau đại học');
 
 -- thêm dữ liệu cho bảng bo_phan
 insert into bo_phan(ma_bo_phan, ten_bo_phan)
 values 
 (1, 'Sale-Marketing'),
 (2, 'Hành chính'),
 (3, 'Phục vụ'),
 (4, 'Quản lý');
 
 -- thêm dữ liệu cho bảng loai_khach
 
 insert into loai_khach(ten_loai_khach) 
 values
 ('Diamond'),
 ('Platinium'),
 ('Gold'),
 ('Sliver'),
 ('Member');
 
 select * from khach_hang