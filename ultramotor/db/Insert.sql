




use HONDA
go

-- NHÂN VIÊN
insert into NhanVien values
('NV01', N'Nguyễn', N'Phong', '1999-01-01',1, 'TPHCM', '0902345678', 'phong@gmail.com', 5000000, 'phong.jpg', N'Nhân Viên Kho', '123', N'Không có gì'),
('NV02', N'Vũ', N'Nguyên', '1997-05-03',1, 'TPHCM', '0902468245', 'nguyen@gmail.com', 7500000, 'nguyen.jpg', N'Trưởng Phòng', '123', N'Không có gì'),
('NV03', N'Phạm', N'Đức', '1998-02-01',1, 'TPHCM', '0903456278', 'duc@gmail.com', 8000000, 'duc.jpg', N'Nhân Viên Bán Hàng', '123', N'Không có gì'),
('NV04', N'Lý', N'Hải', '1999-03-09',1, 'TPHCM', '0902846678', 'hai@gmail.com', 9500000, 'hai.jpg', N'Nhân Viên Bán Hàng', '123', N'Không có gì'),
('NV05', N'Trần', N'Phúc', '1998-03-07',1, 'TPHCM', '0903356788', 'phong@gmail.com', 6000000, 'phuc.jpg', N'Nhân Viên Kỹ Thuật', '123', N'Không có gì'),
('NV06', N'Lê', N'Giang', '1997-06-04',1, 'TPHCM', '0903945278', 'giang@gmail.com', 5000000, 'giang.jpg', N'Nhân Viên Kho', '123', N'Không có gì'),
('NV07', N'Huỳnh', N'Khang', '1996-02-06',1, 'TPHCM', '0902245878', 'khang@gmail.com', 8500000, 'khang.jpg', N'Nhân Viên', '123', N'Không có gì'),
('NV08', N'Hoàng', N'Nam', '1997-02-05',1, 'TPHCM', '0902945178', 'nam@gmail.com', 9000000, 'nam.jpg', N'Nhân Viên Bán Hàng', '123', N'Không có gì'),
('NV09', N'Võ', N'Trung', '1999-04-09',1, 'TPHCM', '0902142638', 'trung@gmail.com', 7000000, 'trung.jpg', N'Nhân Viên Kho', '123', N'Không có gì'),
('NV10', N'Phan', N'Anh', '1996-02-07',1, 'TPHCM', '0902947628', 'anh@gmail.com', 6500000, 'anh.jpg', N'Nhân Viên', '123', N'Không có gì');



-- KHÁCH HÀNG
insert into KhachHang values
('KH01', N'Trần', N'Đức', 1, '1990-04-09', N'TPHCM', '0902875623', 'duc@gmail.com', 1, N'Không có gì', 'NV01'),
('KH02', N'Lê', N'Thanh', 1, '1994-02-01', N'Bình Dương', '0902873621', 'thanh@gmail.com', 1, N'Không có gì', 'NV02'),
('KH03', N'Nguyễn', N'Trọng', 1, '1995-06-03', N'Đồng Nai', '0902273623', 'trong@gmail.com', 1, N'Không có gì', 'NV03'),
('KH04', N'Hoàng', N'Sang', 1, '1993-08-09', N'Hà Nội', '0902829123', 'sang@gmail.com', 1, N'Không có gì', 'NV04'),
('KH05', N'Trần', N'Khải', 1, '1992-03-04', N'Vùng Tàu', '0903752123', 'khai@gmail.com', 1, N'Không có gì', 'NV05'),
('KH06', N'Nguyễn', N'Tuân', 1, '1991-01-07', N'Lâm Đồng', '0903833623', 'tuan@gmail.com', 1, N'Không có gì', 'NV06'),
('KH07', N'Lê', N'An', 1, '1992-04-03', N'Kiên Giang', '0902862323', 'an@gmail.com', 1, N'Không có gì', 'NV07'),
('KH08', N'Hoàng', N'Sơn', 1, '1991-08-01', N'Khánh Hòa', '0902832623', 'son@gmail.com', 1, N'Không có gì', 'NV08'),
('KH09', N'Nguyễn', N'Quốc', 1, '1990-01-05', N'Bình Thuận', '0902812623', 'quoc@gmail.com', 1, N'Không có gì', 'NV09'),
('KH10', N'Vũ', N'Nhân', 1, '1990-04-05', N'Cần Thơ', '0902875699', 'nhan@gmail.com', 1, N'Không có gì', 'NV10');



-- LOẠI HÀNG
insert into LoaiHang values
('LH01', N'Xe ga'),
('LH02', N'Xe ga'),
('LH03', N'Xe ga'),
('LH04', N'Xe côn'),
('LH05', N'Xe côn'),
('LH06', N'Xe côn'),
('LH07', N'Xe số'),
('LH08', N'Xe số'),
('LH09', N'Xe số'),
('LH10', N'Xe số');



-- NHÀ SẢN XUẤT
insert into NhaSanXuat values
('NSX01', 'Honda'),
('NSX02', 'Honda'),
('NSX03', 'Yamaha'),
('NSX04', 'Yamaha'),
('NSX05', 'Suzuki'),
('NSX06', 'Suzuki'),
('NSX07', 'SYM'),
('NSX08', 'SYM'),
('NSX09', 'Piaggio'),
('NSX10', 'Piaggio');



-- DÒNG SẢN PHẨM
insert into DongSanPham values
('SP01', 'Air Blade', 'LH01', 'NSX01'),
('SP02', 'SH Mode', 'LH02', 'NSX02'),
('SP03', 'Jenus', 'LH03', 'NSX03'),
('SP04', 'Exciter', 'LH04', 'NSX04'),
('SP05', 'Satria', 'LH05', 'NSX05'),
('SP06', 'Raider', 'LH06', 'NSX06'),
('SP07', 'Angela', 'LH07', 'NSX07'),
('SP08', 'Galaxy', 'LH08', 'NSX08'),
('SP09', 'Liberty', 'LH01','NSX09'),
('SP10', 'Vespa', 'LH02', 'NSX10');



-- MODEL SẢN PHẨM
insert into ModelSanPham values
('MSP01', 'Air Blade 150', '2021', 'SP01'),
('MSP02', 'SH Mode 125', '2021', 'SP02'),
('MSP03', 'Jenus 125', '2021', 'SP03'),
('MSP04', 'Exciter 155', '2021', 'SP04'),
('MSP05', 'Satria 150', '2021', 'SP05'),
('MSP06', 'Raider 150', '2021', 'SP06'),
('MSP07', 'Angela 50', '2021', 'SP07'),
('MSP08', 'Galaxy 50', '2021', 'SP08'),
('MSP09', 'Liberty 125', '2021', 'SP09'),
('MSP10', 'Vespa 125', '2021', 'SP10');



-- SẢN PHẨM
insert into SanPham values
('SK01', 'Air Blade 150', 'ab.jpg', N'Đen', '150cc', '3', 'TPHCM', '4500000000', N'Honda Air Blade 150cc 2021 mang phong cách mạnh mẽ', 'MSP01', 'NV01'),
('SK02', 'SH Mode 125', 'sh.jpg', N'Đỏ đen', '125cc', '3', 'TPHCM',  '59000000', N'Honda SH Mode 125cc 2021 mang phong cách mạnh mẽ', 'MSP02', 'NV02'),
('SK03', 'Jenus 125', 'jenus.jpg', N'Trắng', '125cc', '3', 'TPHCM',  '29000000', N'Yamaha Jenus 125cc 2021 mang phong cách mạnh mẽ', 'MSP03', 'NV03'),
('SK04', 'Exciter 155', 'exciter.jpg', N'Xanh', '155cc', '3', 'TPHCM','55000000', N'Yamaha Exciter 155cc 2021 mang phong cách mạnh mẽ', 'MSP04', 'NV04'),
('SK05', 'Satria 150', 'satria.jpg', N'Đỏ đen', '150cc', '3', 'TPHCM', '60000000', N'Suzuki Satria 150cc 2021 đã được cải tiến và nâng cấp','MSP05', 'NV05'),
('SK06', 'Raider 150', 'raider.jpg', N'Đen', '150cc', '3', 'TPHCM', '50000000', N'Suzuki Raider 150cc 2021 đã được cải tiến và nâng cấp','MSP06', 'NV06'),
('SK07', 'Angela 50', 'angela.jpg', N'Trắng', '50cc', '3', 'TPHCM', '18000000', N'SYM Angela 50cc 2021 đã được cải tiến và nâng cấp','MSP07', 'NV08'),
('SK08', 'Galaxy 50', 'galaxy.jpg', N'Đỏ', '50cc', '3', 'TPHCM', '18000000', N'SYM Galaxy 50cc 2021 đã được cải tiến và nâng cấp','MSP08', 'NV08'),
('SK09', 'Liberty 125', 'liberty.jpg', N'Trắng', '125cc', '3', 'TPHCM', '60000000', N'Piaggio Liberty 125cc 2021 với vẻ bề ngoài lột xác','MSP09', 'NV09'),
('SK10', 'Vespa 125', 'vespa', N'Đỏ', '125cc', '3', 'TPHCM', '80000000', N'Piaggio Vespa 125cc 2021 với vẻ bề ngoài lột xác','MSP10', 'NV10');



-- DỊCH VỤ
insert into DichVu values
('DV01', N'Mua bán xe', '100', 'NV01'),
('DV02', N'Mua bán xe', '100', 'NV02'),
('DV03', N'Mua bán xe', '100', 'NV03'),
('DV04', N'Mua bán xe', '100', 'NV04'),
('DV05', N'Mua bán xe', '100', 'NV05'),
('DV06', N'Mua bán xe', '100', 'NV06'),
('DV07', N'Mua bán xe', '100', 'NV07'),
('DV08', N'Mua bán xe', '100', 'NV08'),
('DV09', N'Mua bán xe', '100', 'NV09'),
('DV10', N'Mua bán xe', '100', 'NV10');



-- HÓA ĐƠN
insert into HoaDon values
('HD01','2021-01-01', N'Trực tiếp', N'Đã TT', 'NV01','KH01'),
('HD02','2021-01-02', N'Trực tiếp', N'Đã TT', 'NV02','KH02'),
('HD03','2021-01-03', N'Trực tiếp', N'Đã TT', 'NV03','KH03'),
('HD04','2021-01-04', N'Trực tiếp', N'Đã TT', 'NV04','KH04'),
('HD05','2021-01-05', N'Trực tiếp', N'Đã TT', 'NV05','KH05'),
('HD06','2021-01-06', N'Trực tiếp', N'Đã TT', 'NV06','KH06'),
('HD07','2021-01-07', N'Trực tiếp', N'Đã TT', 'NV07','KH07'),
('HD08','2021-01-08', N'Trực tiếp', N'Đã TT', 'NV08','KH08'),
('HD09','2021-01-09', N'Trực tiếp', N'Đã TT', 'NV09','KH09'),
('HD10','2021-01-10', N'Trực tiếp', N'Đã TT', 'NV10','KH10');



--  CHI TIẾT HÓA ĐƠN
insert into ChiTietHoaDon values
('45000000', 'DV01', 'SK01', 'HD01'),
('45000000', 'DV02', 'SK02', 'HD02'),
('45000000', 'DV03', 'SK03', 'HD03'),
('45000000', 'DV04', 'SK04', 'HD04'),
('45000000', 'DV05', 'SK05', 'HD05'),
('45000000', 'DV06', 'SK06', 'HD06'),
('45000000', 'DV07', 'SK07', 'HD07'),
('45000000', 'DV08', 'SK08', 'HD08'),
('45000000', 'DV09', 'SK09', 'HD09'),
('45000000', 'DV10', 'SK10', 'HD10');



-- SỔ BẢO HÀNH
insert into SoBaoHanh values
('SBH01', 1),
('SBH02', 1),
('SBH03', 1),
('SBH04', 1),
('SBH05', 1),
('SBH06', 1),
('SBH07', 1),
('SBH08', 1),
('SBH09', 1),
('SBH10', 1);



-- CHI TIẾT BẢO HÀNH
insert into ChiTietBaoHanh values
('2021-01-01', N'Thay nhớt', 'NV01', 'SBH01'),
('2021-01-02', N'Thay nhớt', 'NV02', 'SBH02'),
('2021-01-03', N'Thay nhớt', 'NV03', 'SBH03'),
('2021-01-04', N'Thay nhớt', 'NV04', 'SBH04'),
('2021-01-05', N'Thay nhớt', 'NV05', 'SBH05'),
('2021-01-06', N'Thay nhớt', 'NV06', 'SBH06'),
('2021-01-07', N'Thay nhớt', 'NV07', 'SBH07'),
('2021-01-08', N'Thay nhớt', 'NV08', 'SBH08'),
('2021-01-09', N'Thay nhớt', 'NV09', 'SBH09'),
('2021-01-10', N'Thay nhớt', 'NV10', 'SBH10');



-- CHI TIẾT BẢO DƯỠNG
insert into ChiTietBaoDuong values
('2021-01-01', N'Bảo dưỡng toàn bộ, Vệ sinh kim phun', 'NV01', 'SBH01'),
('2021-01-02', N'Bảo dưỡng toàn bộ, Vệ sinh kim phun', 'NV02', 'SBH02'),
('2021-01-03', N'Bảo dưỡng toàn bộ, Vệ sinh kim phun', 'NV03', 'SBH03'),
('2021-01-04', N'Bảo dưỡng toàn bộ, Vệ sinh kim phun', 'NV04', 'SBH04'),
('2021-01-05', N'Bảo dưỡng toàn bộ, Vệ sinh kim phun', 'NV05', 'SBH05'),
('2021-01-06', N'Bảo dưỡng toàn bộ, Vệ sinh kim phun', 'NV06', 'SBH06'),
('2021-01-07', N'Bảo dưỡng toàn bộ, Vệ sinh kim phun', 'NV07', 'SBH07'),
('2021-01-08', N'Bảo dưỡng toàn bộ, Vệ sinh kim phun', 'NV08', 'SBH08'),
('2021-01-09', N'Bảo dưỡng toàn bộ, Vệ sinh kim phun', 'NV09', 'SBH09'),
('2021-01-10', N'Bảo dưỡng toàn bộ, Vệ sinh kim phun', 'NV10', 'SBH10');



-- PHIẾU NHẬP KHO
insert into PhieuNhapKho values
(1,'2021-01-01', 'NV01'),
(2,'2021-01-02', 'NV02'),
(3, '2021-01-03', 'NV03'),
(4, '2021-01-04', 'NV04'),
(5,'2021-01-05', 'NV05'),
(6, '2021-01-06', 'NV06'),
(7, '2021-01-07', 'NV07'),
(8, '2021-01-08', 'NV08'),
(9, '2021-01-09', 'NV09'),
(10, '2021-01-10', 'NV10');



-- CHI TIẾT NHẬP KHO
insert into ChiTietNhapKho values
('CTNK01', '100', '4500000000','SK01', 1),
('CTNK02', '100', '4500000000','SK02', 2),
('CTNK03', '100', '4500000000','SK03', 3),
('CTNK04', '100', '4500000000','SK04', 4),
('CTNK05', '100', '4500000000','SK05', 5),
('CTNK06', '100', '4500000000','SK06', 6),
('CTNK07', '100', '4500000000','SK07', 7),
('CTNK08', '100', '4500000000','SK08', 8),
('CTNK09', '100', '4500000000','SK09', 9),
('CTNK10', '100', '4500000000','SK10', 10);



-- PHIẾU XUẤT KHO
insert into PhieuXuatKho values
(1, '2021-01-01', 'NV01'),
(2, '2021-01-02', 'NV02'),
(3, '2021-01-03', 'NV03'),
(4, '2021-01-04', 'NV04'),
(5, '2021-01-05', 'NV05'),
(6, '2021-01-06', 'NV06'),
(7, '2021-01-07', 'NV07'),
(8, '2021-01-08', 'NV08'),
(9, '2021-01-09', 'NV09'),
(10, '2021-01-10', 'NV10');



-- CHI TIẾT XUẤT KHO
insert into ChiTietXuatKho values
('CTXK01', '100', 'SK01', 1),
('CTXK02', '100', 'SK02', 2),
('CTXK03', '100', 'SK03', 3),
('CTXK04', '100', 'SK04', 4),
('CTXK05', '100', 'SK05', 5),
('CTXK06', '100', 'SK06', 6),
('CTXK07', '100', 'SK07', 7),
('CTXK08', '100', 'SK08', 8),
('CTXK09', '100', 'SK09', 9),
('CTXK10', '100', 'SK10', 10);
