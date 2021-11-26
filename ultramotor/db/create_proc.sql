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
	(@phieunhap PhieuNhapKhoType READONLY, @chitiet ChiTietNhapKhoType READONLY)
AS 
BEGIN
	--thêm dữ liệu bảng phiếu nhập kho
	INSERT INTO PhieuNhapKho
		SELECT * FROM @phieunhap;
	
	SET XACT_ABORT ON;
	BEGIN TRY
		BEGIN TRANSACTION;
			--thêm dữ liệu bảng chi tiết nhập kho
			INSERT INTO ChiTietNhapKho
				SELECT * FROM @chitiet;

		COMMIT TRANSACTION;
	END TRY
	BEGIN CATCH
		SELECT   
			 ERROR_NUMBER() AS ErrorNumber  
			,ERROR_SEVERITY() AS ErrorSeverity  
			,ERROR_STATE() AS ErrorState  
			,ERROR_LINE () AS ErrorLine  
			,ERROR_PROCEDURE() AS ErrorProcedure  
			,ERROR_MESSAGE() AS ErrorMessage;  

		IF (XACT_STATE()) = -1
		BEGIN
			PRINT  
				N'The transaction is in an uncommittable state.' +  
				'Rolling back transaction.'  
			ROLLBACK TRANSACTION;

			DELETE FROM PhieuNhapKho WHERE id_PN IN (SELECT id_PN FROM @phieunhap);

			RAISERROR (N'Cập nhật dữ liệu trong Database không thành công.', -- Message text.  
           10, -- Severity,  
           1)-- State,
           --N'number', -- First argument.  
           --5); -- Second argument.  
		-- The message text returned is: This is message number 5.
		END;

		IF (XACT_STATE()) = 1  
		BEGIN  
			PRINT  
				N'The transaction is committable.' +  
				'Committing transaction.'  
			COMMIT TRANSACTION;     
		END;

	END CATCH
END
GO

-- Tạo proc NhapKho -------------------------------------------------------------------------------
drop proc if exists usp_insert_NhapKho
go

create proc usp_insert_NhapKho
	(@chitiet ChiTietNhapKhoType READONLY)
AS 
BEGIN
	
	SET XACT_ABORT ON;
	BEGIN TRY
		BEGIN TRANSACTION;
			--thêm dữ liệu bảng chi tiết nhập kho
			INSERT INTO ChiTietNhapKho
				SELECT * FROM @chitiet;

		COMMIT TRANSACTION;
	END TRY
	BEGIN CATCH
		IF (XACT_STATE()) = -1
		BEGIN
			PRINT  
				N'The transaction is in an uncommittable state.' +  
				'Rolling back transaction.'  
			ROLLBACK TRANSACTION;

			DELETE FROM PhieuNhapKho WHERE id_PN IN (SELECT id_PN FROM @chitiet);

			RAISERROR (N'Cập nhật dữ liệu trong Database không thành công.', -- Message text.  
           10, -- Severity,  
           1)-- State,
           --N'number', -- First argument.  
           --5); -- Second argument.  
		-- The message text returned is: This is message number 5.
		END;

		IF (XACT_STATE()) = 1  
		BEGIN  
			PRINT  
				N'The transaction is committable.' +  
				'Committing transaction.'  
			COMMIT TRANSACTION;     
		END;

	END CATCH
END
GO