drop database if exists quan_li_vat_tu;
create database quan_li_vat_tu;
use quan_li_vat_tu;

-- tao cac thuc the
create table phieu_xuat(
	ma_phieu_xuat int auto_increment primary key,
    ngay_xuat date
);

create table phieu_nhap(
	ma_phieu_nhap int auto_increment primary key,
    ngay_nhap date
);
create table vat_tu(
	ma_vat_tu int auto_increment primary key,
    ten_vat_tu varchar(45)
);

create table don_dat_hang(
	so_dat_hang int auto_increment primary key,
    ngay_dat_hang date,
    ma_ncc int
);

create table nha_cung_cap(
	ma_ncc int auto_increment primary key,
    ten_ncc varchar(45),
    dia_chi varchar(50),
    so_dien_thoai int
);

create table so_dien_thoai(
	ma_sdt int auto_increment primary key,
    so_dien_thoai varchar(45)
);

create table chi_tiet_phieu_xuat(
	ma_vat_tu int,
    ma_phieu_xuat int,
    don_gia_xuat double,
    so_luong_xuat int
);



create table chi_tiet_phieu_nhap(
	ma_vat_tu int,
    ma_phieu_nhap int,
    don_gia_nhap double,
    so_luong_nhap int
);

create table chi_tiet_don_dat_hang(
	ma_vat_tu int,
    ma_don_dat_hang int,
    so_luong_vat_tu int,
    primary key(ma_vat_tu, ma_don_dat_hang)
);

-- tao cac moi quan he
-- tao quan he cho chi_tiet_phieu_xuat
alter table chi_tiet_phieu_xuat
add foreign key(ma_phieu_xuat) references phieu_xuat(ma_phieu_xuat),
add foreign key(ma_vat_tu) references vat_tu(ma_vat_tu);

-- tao quan he cho chi_tiet_phieu_nhap
alter table chi_tiet_phieu_nhap
add foreign key(ma_phieu_nhap) references phieu_nhap(ma_phieu_nhap),
add foreign key(ma_vat_tu) references vat_tu(ma_vat_tu);

-- tao quan he cho chi_tiet_don_hang
alter table chi_tiet_don_dat_hang
add foreign key(ma_vat_tu) references vat_tu(ma_vat_tu),
add foreign key(ma_don_dat_hang) references don_dat_hang(so_dat_hang);

-- tao quan he cho don_dat_hang
alter table don_dat_hang
add foreign key(ma_ncc) references nha_cung_cap(ma_ncc);

-- tao quan he cho nha_cung_cap
alter table nha_cung_cap
add foreign key(so_dien_thoai) references so_dien_thoai(ma_sdt);

