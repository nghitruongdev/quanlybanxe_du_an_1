USE HONDA
GO

--Tạo proc thêm, xoá, sửa nhân viên
--Tạo proc thêm, xoá, sửa nhà sản xuất
--Tạo proc thêm, xoá, sửa loại hàng
--Tạo proc thêm, xoá, sửa dòng xe
--Tạo proc thêm, xoá, sửa 
--Tạo proc thêm, xoá, sửa khách hàng
--Tạo proc thêm, xoá, sửa sản phẩm
--Tạo proc thêm, xoá, sửa hoá đơn + hoá đơn chi tiết



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
	THEN DELETE;
END
GO

-- Tạo proc NhapKho -------------------------------------------------------------------------------
drop proc if exists usp_insert_NhapKho
go

create proc usp_insert_NhapKho
	(@chitiet ChiTietNhapKhoType READONLY)
AS
BEGIN
	-- BEGIN TRY
	-- 	BEGIN TRANSACTION;

		INSERT INTO ChiTietNhapKho
		SELECT * FROM @chitiet;

	-- 	COMMIT TRANSACTION;
	-- END TRY
	-- BEGIN CATCH
	-- 	Declare @msg VARCHAR(255) = ERROR_MESSAGE();
	-- 	ROLLBACK TRANSACTION;
	-- 	RAISERROR(N'Thoi toi da noi roi ma anh khong chiu nghe', 11, 1)
	-- END CATCH
	
END
GO


DROP FUNCTION IF EXISTS fn_soLuongBanSp
GO

CREATE FUNCTION fn_soLuongBanSp
(@sku NVARCHAR(20))
RETURNS INT
AS
BEGIN
	RETURN (SELECT COUNT(*) FROM ChiTietHoaDon WHERE SKU = @sku)
END;
GO

DROP FUNCTION IF EXISTS fn_soLuongTonSp
GO

CREATE FUNCTION fn_soLuongTonSp
(@sku NVARCHAR(20))
RETURNS INT
AS
BEGIN
	RETURN (
	(SELECT sum(soLuong) FROM ChiTietNhapKho  WHERE SKU = @sku GROUP BY SKU) 
	- (dbo.fn_soLuongBanSp(@sku)))
END
GO

DROP PROC IF EXISTS usp_select_modelSP
GO

--tạo proc xem sản phẩm theo model, chỉ bao gồm những sản phẩm còn hàng
CREATE PROC usp_select_modelSP
(@id_DongSP VARCHAR(20))
AS
BEGIN
	SELECT DISTINCT dsp.id_DongSP, TenLoaiHang, TenNSX, DiaChiSX, TenDongSP, doiXe, phanKhoi, thoiGianBH, giaTien, sum(dbo.fn_soLuongBanSp(SKU)) as N'SoLuongBan'
    FROM SanPham sp
        join DongSanPham dsp on sp.id_DongSP = dsp.id_DongSP
        join NhaSanXuat nsx on dsp.id_NSX = nsx.id_NSX
        join LoaiHang lh on lh.id_LH = dsp.id_LH
	WHERE dsp.id_DongSP like @id_DongSP --AND dbo.fn_soLuongTonSp(SKU)>0
	GROUP BY dsp.id_DongSP, TenLoaiHang, TenNSX, DiaChiSX, TenDongSP, doiXe, phanKhoi, thoiGianBH, giaTien
END
GO

exec  usp_select_modelSP '%%'


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
			INSERT INTO SanPham VALUES (@SKU, @tenSP, @hinh, @mauSac, @phanKhoi, @doiXe , @thoiGianBH, @DiaChiSX, @giaTien, @moTa, @id_DongSP, @id_NV)
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

DROP PROC IF EXISTS usp_insert_HoaDon
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