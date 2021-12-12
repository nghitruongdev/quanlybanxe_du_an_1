/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ultramotor.util;

import com.ultramotor.dao.KhachHangDAO;
import com.ultramotor.dao.NhanVienDAO;
import com.ultramotor.dao.SanPhamDAO;
import com.ultramotor.dao.ThongKeDAO;
import com.ultramotor.entity.KhachHang;
import com.ultramotor.entity.NhanVien;
import com.ultramotor.entity.SanPham;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author nghipc
 */
public class UltraExporter {

    public static void exportNhanVien(File file) throws IOException {
        List<Object[]> list = new ArrayList<>();
        new NhanVienDAO().selectAll().forEach(nv -> list.add(getInfo(nv)));
        XExcel.saveSingleSheet(list, getHeader("NhanVien"), file, "NHÂN VIÊN");
    }

    public static void exportSanPham(File file) {
        List<Object[]> list = new ArrayList<>();
        new SanPhamDAO().selectAll().forEach(sp -> list.add(getInfo(sp)));
        XExcel.saveSingleSheet(list, getHeader("SanPham"), file, "SẢN PHẨM");
    }

    public static void exportKhachHang(File file) {
        List<Object[]> list = new ArrayList<>();
        new KhachHangDAO().selectAll().forEach(kh -> list.add(getInfo(kh)));
        XExcel.saveSingleSheet(list, getHeader("KhachHang"), file, "KHÁCH HÀNG");
    }

    public static void exportDoanhThu(File file, Integer... years) {
        if (years == null) {
            return;
        }
        List<List<Object[]>> list = new ArrayList<>();
        ThongKeDAO dao = new ThongKeDAO();
        for (Integer year : years) {
            list.add(dao.getDoanhThu(year));
        }
        String[][] headers = new String[list.size()][];
        for (int i = 0; i < headers.length; i++) {
            headers[i] = getHeader("DoanhThu");

        }
        String[] sheets = Arrays.stream(years).map(String::valueOf).toArray(String[]::new);
        XExcel.saveMultipleSheet(sheets, headers, file, list.toArray(new List<?>[list.size()]));
    }

    public static void exportSanPhamBan(File file, boolean isBanChay, Integer... years) {
        if (years == null) {
            return;
        }
        List<List<Object[]>> list = new ArrayList<>();
        ThongKeDAO dao = new ThongKeDAO();
        if (isBanChay) {
            for (Integer year : years) {
                list.add(dao.getSanPhamBanChay(year));
            }
        } else {
            for (Integer year : years) {
                list.add(dao.getSanPhamBanCham(year));
            }
        }

        String[][] headers = new String[list.size()][];
        for (int i = 0; i < headers.length; i++) {
            headers[i] = getHeader("SanPhamBan");
        }
        String[] sheets = Arrays.stream(years).map(String::valueOf).toArray(String[]::new);
        XExcel.saveMultipleSheet(sheets, headers, file, list.toArray(new List<?>[list.size()]));
    }

    public static void exportTonKho(File file, boolean isNhapKho, Integer... years) {
        List<List<Object[]>> list = new ArrayList<>();
        ThongKeDAO dao = new ThongKeDAO();

        if (isNhapKho) {
            if (years == null) {
                return;
            }
            for (Integer year : years) {
                list.add(dao.getSanPhamNhapKho(year));
            }
        } else {
            list.add(dao.getSanPhamTonKho());
        }

        String[][] headers = new String[list.size()][];
        for (int i = 0; i < headers.length; i++) {
            headers[i] = getHeader("SanPhamBan");
        }
        String[] sheets = Arrays.stream(years).map(String::valueOf).toArray(String[]::new);
        XExcel.saveMultipleSheet(sheets, headers, file, list.toArray(new List<?>[list.size()]));
    }

    public static void exportNhanVienBan(File file, Integer... years) {
        List<List<Object[]>> list = new ArrayList<>();
        ThongKeDAO dao = new ThongKeDAO();
        if (years == null) {
            return;
        }
        for (Integer year : years) {
            List<Object[]> ls = dao.getNhanVien(year);
            ls.forEach(row -> {
                row[3] = (Boolean) row[3] ? "Nam" : "Nũ";
            });
            list.add(ls);
        }

        String[][] headers = new String[list.size()][];
        for (int i = 0; i < headers.length; i++) {
            headers[i] = getHeader("NhanVienTieuBieu");
        }
        String[] sheets = Arrays.stream(years).map(String::valueOf).toArray(String[]::new);
        XExcel.saveMultipleSheet(sheets, headers, file, list.toArray(new List<?>[list.size()]));
    }

    public static void exportKhachHangMua(File file, Integer... years) {
        List<List<Object[]>> list = new ArrayList<>();
        ThongKeDAO dao = new ThongKeDAO();
        if (years == null) {
            return;
        }
        for (Integer year : years) {
            List<Object[]> ls = dao.getKhachHang(year);
            ls.forEach(row -> {
                row[3] = (Boolean) row[3] ? "Nam" : "Nũ";
            });
            list.add(ls);
        }

        String[][] headers = new String[list.size()][];
        for (int i = 0; i < headers.length; i++) {
            headers[i] = getHeader("KhachHangMua");
        }
        String[] sheets = Arrays.stream(years).map(String::valueOf).toArray(String[]::new);
        XExcel.saveMultipleSheet(sheets, headers, file, list.toArray(new List<?>[list.size()]));

    }

    public static String[] getHeader(String name) {
        switch (name) {
            case "NhanVien":
                return new String[]{"Mã Nhân Viên", "Họ Và Tên", "Ngày Sinh", "Giới Tính", "Địa Chỉ", "Số Điện Thoại", "Email", "Lương", "Vai Trò"};
            case "SanPham":
                return new String[]{"Mã Sản Phẩm", "Tên Sản Phẩm", "Màu Sắc", "Phân Khối", "Thời Gian BH", "Địa Chỉ SX", "Giá Tiền", "Đời Xe", "Tồn Kho", "ID_DongSP", "ID_NV"};
            case "KhachHang":
                return new String[]{"Mã Khách Hàng", "Họ Và Tên", "Giới Tính", "Ngày Sinh", "Địa chỉ", "Số ĐT", "Email", "Thành viên"};
            case "DoanhThu":
                return new String[]{"Sản phẩm", "Màu sắc", "Phân khối", "Số lượng", "Doanh thu"};
            case "SanPhamBan":
                return new String[]{"Sản phẩm", "Màu sắc", "Phân khối", "Số lượng"};
            case "NhanVienTieuBieu":
                return new String[]{"Mã nhân viên", "Tên nhân viên", "Ngày sinh", "Giới tính", "Số lượng bán"};
            case "KhachHangMua":
                return new String[]{"Mã khách hàng", "Tên khách hàng", "Ngày sinh", "Giới tính", "SDT", "Email", "Số lượng mua"};
//                case "NhanVien":
//                return new String[]{"Mã Nhân Viên", "", "", "","", "", "", "", "", "", "", "", ""};

        }
        return null;
    }

    public static Object[] getInfo(Object e) {
        if (e instanceof NhanVien) {
            NhanVien nv = (NhanVien) e;
            return new Object[]{
                nv.getIdNV(),
                nv.getHoTenNV(),
                XDate.toString(nv.getNgaySinh(), "dd-MM-yyyy"),
                nv.getGioiTinh() ? "Nam" : "Nữ",
                nv.getDiaChi(),
                nv.getSdt(), nv.getEmail(), nv.getLuong(),
                nv.getVaiTro(),};
        }
        if (e instanceof SanPham) {
            SanPham sp = (SanPham) e;
            return new Object[]{
                sp.getSku(),
                sp.getTenSP(),
                sp.getMauSac(),
                sp.getPhanKhoi(),
                sp.getThoiGianBH(),
                sp.getDiaChiSX(),
                sp.getGiaTien(),
                sp.getDoiXe(),
                sp.gettonKho(),
                sp.getIdDongSP(),
                sp.getIdNV()
            };
        }
        if (e instanceof KhachHang) {
            KhachHang kh = (KhachHang) e;
            return new Object[]{
                kh.getIdKH(), kh.getHoTenKH(),
                kh.getGioiTinh() ? "Nam" : "Nữ", XDate.toString(kh.getNgaySinh(), "dd/MM/yyyy"),
                kh.getDiaChi(),
                kh.getSdt(), kh.getEmail(), kh.getThanhVien() ? "Thành Viên" : "",};
        }
        return null;
    }
}
