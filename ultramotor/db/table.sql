USE MASTER
GO

DROP DATABASE IF EXISTS HONDA
GO

CREATE DATABASE HONDA
go

use HONDA
go

/**
----------------------------------------------CREATE TABLE-------------------------------------------------------- 
**/

DROP TABLE IF EXISTS NhanVien
GO

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
    GhiChu NVARCHAR(255),
	isDeleted BIT DEFAULT 0
)
GO

DROP TABLE IF EXISTS KhachHang
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
    id_NV NVARCHAR(10)
    CONSTRAINT fk_NhanVien_KhachHang FOREIGN KEY (id_NV) REFERENCES NhanVien(id_NV)
)
GO 

DROP TABLE IF EXISTS LoaiHang
GO

CREATE TABLE LoaiHang(
    id_LH VARCHAR(20) PRIMARY KEY,
    TenLoaiHang NVARCHAR(255) NOT NULL 
)
GO

DROP TABLE IF EXISTS NhaSanXuat
GO

CREATE TABLE NhaSanXuat(
    id_NSX VARCHAR(20) PRIMARY KEY,
    TenNSX NVARCHAR(255) NOT NULL 
)
GO

DROP TABLE IF EXISTS DongSanPham
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

DROP TABLE IF EXISTS SanPham
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
	isDeleted BIT DEFAULT 0 
    CONSTRAINT FK_SanPham_NhanVien FOREIGN KEY (id_NV) REFERENCES NhanVien(id_NV),
    CONSTRAINT FK_SanPham_Model FOREIGN KEY (id_DongSP) REFERENCES DongSanPham(id_DongSP)
)
GO

DROP TABLE IF EXISTS HoaDon
GO

CREATE TABLE HoaDon(
    idHD NVARCHAR(15) PRIMARY KEY,
    thoiGian DATETIME2 NOT NULL,
	giamGia SMALLINT NULL DEFAULT 0,
	trangThai NVARCHAR(50) NOT NULL,
    id_NV NVARCHAR(10) NOT NULL,
    idKH NVARCHAR(10) NULL,
    CONSTRAINT FK_HoaDon_NhanVien FOREIGN KEY (id_NV) REFERENCES NhanVien(id_NV),
    CONSTRAINT FK_HoaDon_KhachHang FOREIGN KEY (idKH) REFERENCES KHACHHANG(idKH)
)
GO

DROP TABLE IF EXISTS ChiTietHoaDon
GO

CREATE TABLE ChiTietHoaDon(
	id_CTHD INT,
	SKU NVARCHAR(20) NULL,
    idHD NVARCHAR(15) NOT NULL,
	PRIMARY KEY (id_CTHD, idHD),
    CONSTRAINT FK_ChiTietHoaDon_SanPham FOREIGN KEY (SKU) REFERENCES SanPham(SKU),
    CONSTRAINT FK_ChiTietHoaDon_HoaDon FOREIGN KEY (idHD) REFERENCES HOADON(idHD)
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

/**
----------------------------------------------CREATE USER DEFINED TYPE---------------------------------------------
**/

DROP TYPE IF EXISTS NhanVienType
GO

CREATE TYPE NhanVienType AS TABLE(
    id_NV NVARCHAR(10) PRIMARY KEY,
    HoNV NVARCHAR(20) NOT NULL,
    TenNV NVARCHAR(20) NOT NULL,
    NgaySinh VARCHAR(50) NOT NULL,
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


DROP TYPE IF EXISTS ChiTietNhapKhoType
GO

CREATE TYPE ChiTietNhapKhoType AS TABLE
(
    id_CTNK INT NOT NULL,
	 SKU NVARCHAR(20) NOT NULL,
    soLuong INT NOT NULL,
    giaNhap FLOAT NOT NULL,
    id_PN NVARCHAR(50) NOT NULL,
	PRIMARY KEY (id_CTNK, id_PN)
)
GO


DROP TYPE IF EXISTS ChiTietHoaDonType
GO

CREATE TYPE ChiTietHoaDonType AS TABLE
(
	id_CTHD INT PRIMARY KEY,
	SKU NVARCHAR(20) NOT NULL,
    idHD NVARCHAR(15) NOT NULL
)
GO

----------------------------------------------CREATE FUNCTION---------------------------------------------------------
DROP FUNCTION IF EXISTS fn_soLuongBanSp
GO

CREATE FUNCTION fn_soLuongBanSp
(@sku NVARCHAR(20))
RETURNS INT
AS
BEGIN
	DECLARE @count int;
	SELECT @count = COUNT(*) FROM ChiTietHoaDon WHERE SKU = @sku;
	IF(@count is null)
		SELECT @count = 0;
	RETURN @count
END;
GO

DROP FUNCTION IF EXISTS fn_soLuongTonSp
GO

CREATE FUNCTION fn_soLuongTonSp
(@sku NVARCHAR(20))
RETURNS INT
AS
BEGIN
	DECLARE @count INT;
	SELECT @count = (sum(soLuong)- (dbo.fn_soLuongBanSp(SKU))) FROM ChiTietNhapKho  WHERE SKU = @sku GROUP BY SKU;
	IF(@count is null)
		SELECT @count = 0;
	RETURN @count;
	
END
GO
----------------------------------------------CREATE PROC---------------------------------------------------------

--Tạo proc thêm, xoá, sửa NhanVien--------------------------------------------------------------------------
DROP PROC IF EXISTS usp_updateNhanVien
GO

CREATE PROC usp_updateNhanVien
(@sourceTable NhanVienType readonly)
AS
BEGIN
	MERGE NhanVien as Target
	USING @sourceTable as Source
	ON Source.id_NV = Target.id_NV
	WHEN NOT MATCHED BY Target 
	THEN INSERT (id_NV, HoNV, TenNV, NgaySinh, GioiTinh, DiaChi, SDT, Email, Luong, Hinh, VaiTro, matKhau, GhiChu) 
	VALUES (Source.id_NV, Source.HoNV, Source.TenNV, Source.NgaySinh, Source.GioiTinh, Source.DiaChi, Source.SDT, Source.Email, Source.Luong, Source.Hinh, Source.VaiTro, Source.matKhau, Source.GhiChu)

	WHEN MATCHED
	THEN UPDATE SET 
		HoNV = Source.HoNV, 
		TenNV = Source.TenNV, 
		NgaySinh = CONVERT(DATE, Source.NgaySinh),
		GioiTinh = Source.GioiTinh,
		DiaChi = Source.DiaChi,
		SDT = Source.SDT, 
		Email = Source.Email,
		Luong = Source.Luong,
		Hinh = Source.Hinh, 
		VaiTro = Source.VaiTro,
		matKhau = Source.matKhau,
		GhiChu = Source.GhiChu

	WHEN NOT MATCHED BY Source
	THEN UPDATE SET
	isDeleted = 1;
END
GO

-- Tạo proc NhapKho -------------------------------------------------------------------------------
drop proc if exists usp_insert_NhapKho
go

create proc usp_insert_NhapKho
	(@chitiet ChiTietNhapKhoType READONLY)
AS
BEGIN
		INSERT INTO ChiTietNhapKho
		SELECT * FROM @chitiet;
END
GO


DROP PROC IF EXISTS usp_insert_DongSanPham
GO

CREATE PROC usp_insert_DongSanPham
(	
	@id_DongSP VARCHAR(20),
    @tenDongSP NVARCHAR(100),
    @id_LH VARCHAR(20),
    @id_NSX VARCHAR(20)
)
AS
BEGIN
	IF NOT EXISTS (SELECT 0 FROM DongSanPham WHERE id_DongSP = @id_DongSP)
			INSERT INTO DongSanPham VALUES (@id_DongSP, @tenDongSP, @id_LH, @id_NSX)
	ELSE
		UPDATE DongSanPham SET tenDongSP = @tenDongSP, id_LH = @id_LH, id_NSX = @id_NSX WHERE id_DongSP = @id_DongSP
END
GO

DROP PROC IF EXISTS usp_insert_SanPham
GO

CREATE PROC usp_insert_SanPham
(
	@SKU NVARCHAR(20),
    @tenSP NVARCHAR(100),
    @hinh NVARCHAR(20) = '',
    @mauSac NVARCHAR(20),
    @phanKhoi NVARCHAR(20),
    @doiXe INT,
    @thoiGianBH INT,
    @DiaChiSX NVARCHAR(255) = N'Vietnam',
    @giaTien FLOAT,
    @moTa NVARCHAR(255) = '',
    @id_DongSP VARCHAR(20),
    @id_NV NVARCHAR(10)
)
AS
BEGIN
	IF NOT EXISTS (SELECT 0 FROM SanPham WHERE SKU = @SKU)
			INSERT INTO SanPham (SKU, tenSP, hinh, mauSac, phanKhoi, doiXe , thoiGianBH, DiaChiSX, giaTien, moTa, id_DongSP, id_NV) VALUES (@SKU, @tenSP, @hinh, @mauSac, @phanKhoi, @doiXe , @thoiGianBH, @DiaChiSX, @giaTien, @moTa, @id_DongSP, @id_NV)
	ELSE
		UPDATE SanPham 
		SET 
			tenSP = @tenSP,
			hinh = @hinh,
			mauSac = @mauSac,
			phanKhoi = @phanKhoi,
			doiXe = @doiXe,
			thoiGianBH = @thoiGianBH,
			DiaChiSX = @DiaChiSX,
			giaTien = @giaTien,
			moTa = @moTa,
			id_DongSP = @id_DongSP,
			id_NV = @id_NV
		WHERE SKU = @SKU
END
GO

DROP PROC IF EXISTS usp_insert_KhachHang
GO

CREATE PROC usp_insert_KhachHang
(
	@idKH NVARCHAR(10),
	@HoKH NVARCHAR(20),
	@TenKH NVARCHAR(20),
	@GioiTinh BIT,
	@NgaySinh DATETIME2,
	@DiaChi NVARCHAR(255),
	@SDT NVARCHAR(20), 
	@Email NVARCHAR(50),
	@ThanhVien BIT = 0,
	@GhiChu NVARCHAR(255) = '',
	@id_NV NVARCHAR(10)
)
AS
BEGIN
	IF NOT EXISTS (SELECT 0 FROM KhachHang WHERE idKH = @idKH)
			INSERT INTO KhachHang VALUES (@idKH, @HoKH, @TenKH, @GioiTinh, @NgaySinh, @DiaChi, @SDT, @EMAIL, @ThanhVien, @GHICHU, @id_NV)
	ELSE
		UPDATE KhachHang 
		SET 
			HoKH = @HoKH,
			TenKH = @TenKH,
			GioiTinh = @GioiTinh,
			NgaySinh = @NgaySinh,
			DiaChi = @DiaChi,
			SDT = @SDT, 
			Email = @Email,
			ThanhVien = @ThanhVien,
			GhiChu= @GhiChu,
			id_NV = @id_NV
		WHERE idKH = @idKH
END
GO

DROP PROC IF EXISTS usp_insert_ChiTietHoaDon
GO

CREATE PROC usp_insert_ChiTietHoaDon
(
	@chitiet ChiTietHoaDonType READONLY
)
AS
BEGIN
	INSERT INTO ChiTietHoaDon
		SELECT * FROM @chitiet
END
GO

-- tạo proc cập nhật loại hàng

DROP PROC IF EXISTS usp_insert_LoaiHang
GO

CREATE PROC usp_insert_LoaiHang
(
	@id_LH VARCHAR(20),
    @TenLoaiHang NVARCHAR(255)
)
AS
BEGIN
	IF NOT EXISTS (SELECT 0 FROM LoaiHang WHERE id_LH = @id_LH)
			INSERT INTO LoaiHang VALUES (@id_LH, @TenLoaiHang)
	ELSE
		UPDATE LoaiHang SET TenLoaiHang = @TenLoaiHang WHERE id_LH = @id_LH
END
GO

DROP PROC IF EXISTS usp_insert_NhaSanXuat
GO

CREATE PROC usp_insert_NhaSanXuat
(
	@id_NSX VARCHAR(20),
    @TenNSX NVARCHAR(255)
)
AS
BEGIN
	IF NOT EXISTS (SELECT 0 FROM NhaSanXuat WHERE id_NSX = @id_NSX)
			INSERT INTO NhaSanXuat VALUES (@id_NSX, @TenNSX)
	ELSE
		UPDATE NhaSanXuat SET TenNSX = @TenNSX WHERE id_NSX = @id_NSX
END
GO

--tạo proc xem sản phẩm theo model, chỉ bao gồm những sản phẩm còn hàng
DROP PROC IF EXISTS usp_select_modelSP
GO

CREATE PROC usp_select_modelSP
(@id_DongSP VARCHAR(20))
AS
BEGIN
	SELECT DISTINCT dsp.id_DongSP, TenLoaiHang, TenNSX, DiaChiSX, TenDongSP, doiXe, phanKhoi, thoiGianBH, giaTien, sum(dbo.fn_soLuongBanSp(SKU)) as N'SoLuongBan'
    FROM SanPham sp
        join DongSanPham dsp on sp.id_DongSP = dsp.id_DongSP
        join NhaSanXuat nsx on dsp.id_NSX = nsx.id_NSX
        join LoaiHang lh on lh.id_LH = dsp.id_LH
	WHERE dsp.id_DongSP like @id_DongSP AND sp.isDeleted = 0 --AND dbo.fn_soLuongTonSp(SKU)>0
	GROUP BY dsp.id_DongSP, TenLoaiHang, TenNSX, DiaChiSX, TenDongSP, doiXe, phanKhoi, thoiGianBH, giaTien
END
GO

-- Tạo Proc thống kê doanh thu
DROP PROC IF EXISTS sp_DoanhThu
GO

Create PROC sp_DoanhThu(@Year int)
as begin
	select 
		sp.tenSP SanPham,
		sp.mauSac MauSac,
		sp.phanKhoi PhanKhoi,
		count(cthd.id_CTHD) SoLuong,
		Sum(sp.giaTien) DoanhThu
	from SanPham sp
		join ChiTietHoaDon cthd on sp.SKU = cthd.SKU
		join HoaDon hd on hd.idHD = cthd.idHD
	where Year(hd.thoiGian) = @Year
	group by sp.tenSP,sp.mauSac,sp.phanKhoi
end
go

-- tạo Proc thống kê top 5 sản phẩm bán chạy nhất năm
DROP PROC IF EXISTS sp_SanPhamBanChay
GO

Create PROC sp_SanPhamBanChay(@Year int)
as begin
	select top(5)
		tenSP SanPham,
		sp.mauSac MauSac,
		sp.phanKhoi PhanKhoi,
		count(cthd.id_CTHD) SoLuong
	from SanPham sp
		join ChiTietHoaDon cthd on sp.SKU = cthd.SKU
		join HoaDon hd on hd.idHD = cthd.idHD
	where Year(hd.thoiGian) = @Year
	group by tenSP , cthd.SKU , sp.mauSac,sp.phanKhoi
end
go

-- tạo Proc thống kê top 5 sản phẩm bán chậm nhất năm
DROP PROC IF EXISTS sp_SanPhamBanCham
GO

Create PROC sp_SanPhamBanCham(@Year int)
as begin
	select top(5)
		tenSP SanPham,
		sp.mauSac MauSac,
		sp.phanKhoi PhanKhoi,
		count(cthd.id_CTHD) SoLuong
	from SanPham sp
		join ChiTietHoaDon cthd on sp.SKU = cthd.SKU
		join HoaDon hd on hd.idHD = cthd.idHD
	where Year(hd.thoiGian) = @Year
	group by tenSP , cthd.SKU , sp.mauSac,sp.phanKhoi
	order by cthd.SKU desc
end
go

-- tạo Proc thống kê sản phẩm tồn kho
DROP PROC IF EXISTS sp_SanPhamTonKho
Go

Create PROC sp_SanPhamTonKho
as begin
	select 
		tenSP SanPham,
		sp.mauSac MauSac,
		sp.phanKhoi PhanKhoi,
		(ctnk.soLuong - count(cthd.SKU)) SoLuong
	from ChiTietNhapKho ctnk
		join ChiTietHoaDon cthd on ctnk.SKU = cthd.SKU
		join SanPham sp on sp.SKU = ctnk.SKU
	group by tenSP , SoLuong , sp.mauSac,sp.phanKhoi
end
go

-- tạo proc thống kê số hàng đã nhập kho theo năm
DROP PROC IF EXISTS sp_SanPhamNhapKho
Go

Create PROC sp_SanPhamNhapKho(@Year int)
as begin
	select 
		tenSP SanPham,
		sp.mauSac MauSac,
		sp.phanKhoi PhanKhoi,
		ctnk.soLuong SoLuong
	from ChiTietNhapKho ctnk
	join PhieuNhapKho pnk on pnk.id_PN = ctnk.id_PN
	join SanPham sp on sp.SKU = ctnk.SKU
	where year(pnk.ngayNhap) = @Year
	group by tenSP , SoLuong , sp.mauSac,sp.phanKhoi
end
go

-- tạo proc thống kê danh sách nhân viên tiêu biểu bán nhiều sản phẩm theo năm
drop proc if exists sp_NhanVienTieuBieu
go

Create proc sp_NhanVienTieuBieu(@Year int)
as begin
	select 
		nv.id_NV MaNV,
		(nv.HoNV + ' ' + nv.TenNV) TenNV,
		nv.NgaySinh NgaySinh,
		nv.GioiTinh GioiTinh,
		count(distinct hd.idHD) SoLuong
	from NhanVien nv 
	join HoaDon hd on hd.id_NV = nv.id_NV
	join ChiTietHoaDon cthd on cthd.idHD = cthd.idHD
	where Year(hd.thoiGian) = @Year
	group by nv.id_NV ,HoNV, TenNV , NgaySinh, GioiTinh
	order by SoLuong desc
end
go

-- tạo proc thống kê danh sách nhân viên tiêu biểu bán nhiều sản phẩm theo năm
drop proc if exists sp_KhachHangTieuBieu
go

Create proc sp_KhachHangTieuBieu(@Year int)
as begin
	select 
		kh.idKH MaKH,
		(kh.hoKH + ' ' + kh.TenKH) TenKH,
		kh.NgaySinh NgaySinh,
		kh.GioiTinh GioiTinh,
		kh.SDT SDT,
		kh.Email Email,
		count(distinct hd.idHD) SoLuong
	from KhachHang kh 
	join HoaDon hd on hd.idKH = kh.idKH
	join ChiTietHoaDon cthd on cthd.idHD = cthd.idHD
	where Year(hd.thoiGian) = @Year
	group by kh.idKH ,kh.hoKH, kh.TenKH , NgaySinh, GioiTinh, kh.SDT, kh.Email
	order by SoLuong desc
end
go

exec sp_KhachHangTieuBieu 2021

----------------------------------------------CREATE TRIGGER---------------------------------------------------------

--tạo trigger xoá nhập kho
DROP TRIGGER IF EXISTS trg_delete_phieuNhap
go

CREATE TRIGGER trg_delete_phieuNhap
ON PhieuNhapKho
INSTEAD OF DELETE
NOT FOR REPLICATION
AS
BEGIN
	DELETE FROM ChiTietNhapKho WHERE id_PN IN (SELECT id_PN FROM deleted)

	DELETE FROM PhieuNhapKho WHERE id_PN IN (SELECT id_PN FROM deleted)
END
GO

DROP TRIGGER IF EXISTS trg_delete_HoaDon
GO

CREATE TRIGGER trg_delete_HoaDon
ON HoaDon
INSTEAD OF DELETE
NOT FOR REPLICATION
AS
BEGIN
	DELETE FROM ChiTietHoaDon WHERE idHD IN (SELECT idHD FROM deleted);

	DELETE FROM HoaDon WHERE idHD IN (SELECT idHD FROM deleted);
END
GO

DROP TRIGGER IF EXISTS trg_delete_nhanVien
GO

CREATE TRIGGER trg_delete_nhanVien
ON NhanVien
INSTEAD OF DELETE
NOT FOR REPLICATION
AS UPDATE NhanVien SET isDeleted = 1 WHERE id_NV in (select id_NV from deleted)

DROP TRIGGER IF EXISTS trg_delete_KhachHang
GO

CREATE TRIGGER trg_delete_KhachHang
ON KhachHang
INSTEAD OF DELETE
NOT FOR REPLICATION
AS 
BEGIN
	UPDATE HoaDon SET idKH = NULL WHERE idKH IN (SELECT idKH FROM deleted)
	DELETE FROM KhachHang WHERE idKH IN (SELECT idKH FROM deleted)
END
GO

DROP TRIGGER IF EXISTS trg_delete_SanPham
GO

CREATE TRIGGER trg_delete_SanPham
ON SanPham
INSTEAD OF DELETE
NOT FOR REPLICATION
AS UPDATE SanPham set isDeleted = 1 WHERE SKU in (select SKU from deleted)


