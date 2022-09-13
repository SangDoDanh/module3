drop database if exists quan_li_diem_thi;
create database quan_li_diem_thi;
use quan_li_diem_thi;
-- tao bang lop_hoc
create table lop_hoc(
	ma_lop_hoc int primary key auto_increment ,
    ten_lop_hoc varchar(30)
);

-- tao bang hoc_sinh
create table hoc_sinh (
	ma_hoc_sinh int auto_increment primary key,
    ten_hoc_sinh varchar(45),
    ngay_sinh date,
    ma_lop_hoc int,
    gioi_tinh varchar(20),
    foreign key(ma_lop_hoc) references lop_hoc(ma_lop_hoc)
);

-- tao bang giao_vien
create table giao_vien(
	ma_giao_vien int auto_increment primary key,
    ten_giao_vien varchar(45),
    so_dien_thoai varchar(20)
);

-- tao bang mon_hoc
create table mon_hoc(
	ma_mon_hoc int auto_increment primary key,
    ten_mon_hoc varchar(20),
    ma_giao_vien int,
    foreign key(ma_giao_vien) references giao_vien(ma_giao_vien)
);

-- tao bang bang_diem
create table bang_diem(
	ma_hoc_sinh int,
    ma_mon_hoc int,
    diem_thi double,
    ngay_thi date,
    primary key(ma_hoc_sinh, ma_mon_hoc),
    foreign key(ma_hoc_sinh) references hoc_sinh(ma_hoc_sinh),
    foreign key(ma_mon_hoc) references mon_hoc(ma_mon_hoc)
);