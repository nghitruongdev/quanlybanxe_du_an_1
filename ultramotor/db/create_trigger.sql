--tạo trigger xoá hoá đơn 
--tạo trigger xoá khách hàng
--tạo trigger xoá

USE HONDA
go

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
	DELETE FROM ChiTietHoaDon WHERE idHD IN (SELECT idHD FROM deleted)

	DELETE FROM HoaDon WHERE idHD IN (SELECT idHD FROM deleted)
END
GO
