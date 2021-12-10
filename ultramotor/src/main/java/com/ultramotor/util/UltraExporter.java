/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ultramotor.util;

import com.ultramotor.dao.NhanVienDAO;
import com.ultramotor.entity.NhanVien;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nghipc
 */
public class UltraExporter {


    public static void exportNhanVien(File file) throws IOException {
        List<Object[]> list = new ArrayList<>();
        new NhanVienDAO().selectAll().forEach(nv -> list.add(getInfo(nv)));
        XExcel.saveSingleSheet(list, getHeader("NhanVien"), file, "Ultramotor");
    }

    public static String[] getHeader(String name) {
        switch (name) {
            case "NhanVien":
                return new String[]{"Mã Nhân Viên", "Họ Và Tên", "Ngày Sinh", "Giới Tính", "Địa Chỉ", "Số Điện Thoại", "Email", "Lương", "Vai Trò"};
//                case "NhanVien":
//                return new String[]{"Mã Nhân Viên", "", "", "","", "", "", "", "", "", "", "", ""};
//                case "NhanVien":
//                return new String[]{"Mã Nhân Viên", "", "", "","", "", "", "", "", "", "", "", ""};
//                case "NhanVien":
//                return new String[]{"Mã Nhân Viên", "", "", "","", "", "", "", "", "", "", "", ""};
//                case "NhanVien":
//                return new String[]{"Mã Nhân Viên", "", "", "","", "", "", "", "", "", "", "", ""};
//                case "NhanVien":
//                return new String[]{"Mã Nhân Viên", "", "", "","", "", "", "", "", "", "", "", ""};
//                case "NhanVien":
//                return new String[]{"Mã Nhân Viên", "", "", "","", "", "", "", "", "", "", "", ""};
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
        return null;
    }
}
