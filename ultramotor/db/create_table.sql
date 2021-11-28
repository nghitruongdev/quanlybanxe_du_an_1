﻿USE MASTER
GO

DROP DATABASE IF EXISTS HONDA
GO

CREATE DATABASE HONDA
go

use HONDA
go

drop table if exists NhanVien
go

CREATE TABLE NhanVien(
    id_NV NVARCHAR(10) PRIMARY KEY,
    HoNV NVARCHAR(20) NOT NULL,
    TenNV NVARCHAR(20) NOT NULL,
    NgaySinh DATE NOT NULL,
    GioiTinh BIT NOT NULL,
    DiaChi NVARCHAR(255) NOT NULL,
    SDT NVARCHAR(10) NOT NULL,
    Email NVARCHAR(50) NOT NULL,
    Luong FLOAT NOT NULL,
    Hinh NVARCHAR(50) NOT NULL,
    VaiTro NVARCHAR(20) NOT NULL,
    matKhau NVARCHAR(255) NOT NULL,
    GhiChu NVARCHAR(255)
)
GO


CREATE TABLE KhachHang(
    idKH NVARCHAR(10) PRIMARY KEY,
    HoKH NVARCHAR(20) NOT NULL,
    TenKH NVARCHAR(20) NOT NULL,
    GioiTinh BIT NOT NULL,
    NgaySinh DATETIME2 NOT NULL,
    DiaChi NVARCHAR(255),
    SDT NVARCHAR(20), 
    Email NVARCHAR(50),
    ThanhVien BIT DEFAULT 0,
    GhiChu NVARCHAR(255),
    id_NV NVARCHAR(10),
    CONSTRAINT fk_NhanVien_KhachHang FOREIGN KEY (id_NV) REFERENCES NhanVien(id_NV)
)
GO 

CREATE TABLE LoaiHang(
    id_LH VARCHAR(20) PRIMARY KEY,
    TenLoaiHang NVARCHAR(255) NOT NULL 
)
GO

CREATE TABLE NhaSanXuat(
    id_NSX VARCHAR(20) PRIMARY KEY,
    TenNSX NVARCHAR(255) NOT NULL 
)
GO

CREATE TABLE DongSanPham(
    id_DongSP VARCHAR(20) PRIMARY KEY,
    tenDongSP NVARCHAR(100) NOT NULL,
    id_LH VARCHAR(20) NOT NULL,
    id_NSX VARCHAR(20) NOT NULL,
    CONSTRAINT FK_LoaiHang_DongSanPham FOREIGN KEY (id_LH) REFERENCES LOAIHANG(id_LH),
    CONSTRAINT FK_NhaSanXuat_DongSanPham FOREIGN KEY (id_NSX) REFERENCES NHASANXUAT(id_NSX)
)
GO


CREATE TABLE SanPham(
    SKU NVARCHAR(20) PRIMARY KEY,
    tenSP NVARCHAR(100) NOT NULL,
    hinh NVARCHAR(20),
    mauSac NVARCHAR(20) NOT NULL,
    phanKhoi NVARCHAR(20) NOT NULL,
    doiXe INT NOT NULL,
    thoiGianBH INT,
    DiaChiSX NVARCHAR(255) NOT NULL,
    giaTien FLOAT NOT NULL,
    moTa NVARCHAR(255),
    id_DongSP VARCHAR(20) NOT NULL,
    id_NV NVARCHAR(10) NOT NULL,
    CONSTRAINT FK_SanPham_NhanVien FOREIGN KEY (id_NV) REFERENCES NhanVien(id_NV),
    CONSTRAINT FK_SanPham_Model FOREIGN KEY (id_DongSP) REFERENCES DongSanPham(id_DongSP)
)
GO

CREATE TABLE DichVu(
    idDV NVARCHAR(10) PRIMARY KEY,
    tenDV NVARCHAR(255) NOT NULL,
    donGia FLOAT NOT NULL,
    id_NV NVARCHAR(10) NOT NULL,
    CONSTRAINT FK_DichVu_NhanVien FOREIGN KEY (id_NV) REFERENCES NhanVien(id_NV)
)
GO

CREATE TABLE HoaDon(
    idHD NVARCHAR(15) PRIMARY KEY,
    thoiGian DATE NOT NULL,
    loaiThanhToan NVARCHAR(50) NOT NULL,
    trangThai NVARCHAR(10) NOT NULL,
    id_NV NVARCHAR(10) NOT NULL,
    idKH NVARCHAR(10) NULL,
    CONSTRAINT FK_HoaDon_NhanVien FOREIGN KEY (id_NV) REFERENCES NhanVien(id_NV),
    CONSTRAINT FK_HoaDon_KhachHang FOREIGN KEY (idKH) REFERENCES KHACHHANG(idKH)
)
GO

DROP TABLE IF EXISTS ChiTietHoaDon
GO

CREATE TABLE ChiTietHoaDon(
	id_CTHD INT IDENTITY(1,1) PRIMARY KEY,
	donGia FLOAT NOT NULL,
	idDV NVARCHAR(10) NOT NULL,
	SKU NVARCHAR(20) NULL,
    idHD NVARCHAR(15) NOT NULL,
    CONSTRAINT FK_ChiTietHoaDon_SanPham FOREIGN KEY (SKU) REFERENCES SanPham(SKU),
    CONSTRAINT FK_ChiTietHoaDon_DichVu FOREIGN KEY (idDV) REFERENCES DICHVU(idDV),
    CONSTRAINT FK_ChiTietHoaDon_HoaDon FOREIGN KEY (idHD) REFERENCES HOADON(idHD)
)
GO

DROP TABLE IF EXISTS SoBaoHanh
GO

CREATE TABLE SoBaoHanh(
    id_SBH NVARCHAR(10) PRIMARY KEY,
    id_CTHD INT NOT NULL,
    CONSTRAINT FK_SoBaoHanh_ChiTietHoaDon FOREIGN KEY (id_CTHD) REFERENCES CHITIETHOADON(id_CTHD)
)
GO 

DROP TABLE IF EXISTS ChiTietBaoHanh
GO

CREATE TABLE ChiTietBaoHanh(
    id INT IDENTITY(1,1) PRIMARY KEY,
    thoiGian DATE NOT NULL,
    noiDung NVARCHAR(255) NOT NULL,
    id_NV NVARCHAR(10) NOT NULL,
    id_SBH NVARCHAR(10) NOT NULL,
    CONSTRAINT FK_ChiTietBaoHanh_NhanVien FOREIGN KEY (id_NV) REFERENCES NhanVien(id_NV),
    CONSTRAINT FK_ChiTietBaoHanh_SoBaoHanh FOREIGN KEY (id_SBH) REFERENCES SOBAOHANH(id_SBH)
)
GO

DROP TABLE IF EXISTS ChiTietBaoDuong
GO

CREATE TABLE ChiTietBaoDuong(
    id INT IDENTITY(1,1) PRIMARY KEY,
    thoiGian DATE NOT NULL,
    noiDung NVARCHAR(255) NOT NULL,
    id_NV NVARCHAR(10) NOT NULL,
    id_SBH NVARCHAR(10) NOT NULL,
    CONSTRAINT FK_ChiTietBaoDuong_NhanVien FOREIGN KEY (id_NV) REFERENCES NhanVien(id_NV),
    CONSTRAINT FK_ChiTietBaoDuong_SoBaoHanh FOREIGN KEY (id_SBH) REFERENCES SOBAOHANH(id_SBH)
)
GO

DROP TABLE IF EXISTS PhieuNhapKho
GO

CREATE TABLE PhieuNhapKho(
    id_PN NVARCHAR(50) PRIMARY KEY,
	ngayNhap DATETIME2 NOT NULL DEFAULT GETDATE(),
    id_NV NVARCHAR(10) NOT NULL,
    CONSTRAINT FK_NhanVien_PhieuNhapKho FOREIGN KEY (id_NV) REFERENCES NhanVien(id_NV)
)
GO

DROP TABLE IF EXISTS ChiTietNhapKho
GO

CREATE TABLE ChiTietNhapKho(
    id_CTNK INT NOT NULL,
    SKU NVARCHAR(20) NOT NULL,
    soLuong INT NOT NULL,
    giaNhap FLOAT NOT NULL,
    id_PN NVARCHAR(50) NOT NULL,
	PRIMARY KEY (id_CTNK, id_PN),
    CONSTRAINT FK_ChiTietNhapKho_SanPham FOREIGN KEY (SKU) REFERENCES SANPHAM(SKU),
    CONSTRAINT FK_ChiTieuNhapKho_PhieuNhapKho FOREIGN KEY (id_PN) REFERENCES PHIEUNHAPKHO(id_PN)
)
GO

-- CREATE TABLE PhieuXuatKho(
--     id_PX NVARCHAR(10) PRIMARY KEY,
--     ngayXuat DATE NOT NULL DEFAULT GETDATE(),
--     id_NV NVARCHAR(10) NOT NULL,
--     CONSTRAINT FK_NhanVien_PhieuXuatKho FOREIGN KEY (id_NV) REFERENCES NhanVien(id_NV)
-- )
-- GO

-- CREATE TABLE ChiTietXuatKho(
--     ID_CTXK INT IDENTITY(1,1) PRIMARY KEY,
--     soLuong INT NOT NULL,
--     SKU NVARCHAR(20) NOT NULL,
--     id_PX NVARCHAR(10) NOT NULL,
--     CONSTRAINT FK_ChiTietXuatKho_SanPham FOREIGN KEY (SKU) REFERENCES SANPHAM(SKU),
--     CONSTRAINT FK_ChiTieuXuatKho_PhieuXuatKho FOREIGN KEY (id_PX) REFERENCES PHIEUXUATKHO(id_PX)
-- )
-- GO


-- SELECT DISTINCT dsp.id_DongSP, TenLoaiHang, TenNSX, DiaChiSX, TenDongSP, doiXe, phanKhoi, thoiGianBH, giaTien
-- from SanPham sp 
-- 	join DongSanPham dsp on sp.id_DongSP = dsp.id_DongSP
-- 	join NhaSanXuat nsx on dsp.id_NSX = dsp.id_NSX
-- 	join LoaiHang lh on lh.id_LH = dsp.id_LH
-- WHERE dsp.id_DongSP like '%%'

