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
drop proc if exists usp_insert_ChiTietNhapKho
go

create proc usp_insert_ChiTietNhapKho
	(@source ChiTietNhapKhoType READONLY)
AS 
BEGIN
	insert into ChiTietNhapKho
	select * from @source
END
GO