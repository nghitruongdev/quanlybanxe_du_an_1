/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ultramotor.dao;

import com.ultramotor.util.XJdbcServer;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author BRAVO
 */
public class ThongKeDAO {

    private List<Object[]> getListOfArray(String sql, String[] cols, Object... args) {
        List<Object[]> list = new ArrayList<>();
        try (ResultSet rs = XJdbcServer.query(sql, args)) {
            while (rs.next()) {
                Object[] vals = new Object[cols.length];
                for (int i = 0; i < cols.length; i++) {
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;

    }

    public List<Object[]> getDoanhThu(int nam) {
        String sql = "{CALL sp_DoanhThu(?)}";
        String[] cols = {"SanPham", "MauSac", "PhanKhoi", "SoLuong", "DoanhThu"};
        return this.getListOfArray(sql, cols, nam);
    }

    public List<Object[]> getSanPhamBanChay(int nam) {
        String sql = "{CALL sp_SanPhamBanChay(?)}";
        String[] cols = {"SanPham", "MauSac", "PhanKhoi", "SoLuong"};
        return this.getListOfArray(sql, cols, nam);
    }

    public List<Object[]> getSanPhamBanCham(int nam) {
        String sql = "{CALL sp_SanPhamBanCham(?)}";
        String[] cols = {"SanPham", "MauSac", "PhanKhoi", "SoLuong"};
        return this.getListOfArray(sql, cols, nam);
    }

    public List<Object[]> getSanPhamTonKho() {
        String sql = "{CALL sp_SanPhamTonKho}";
        String[] cols = {"SanPham", "MauSac", "PhanKhoi", "SoLuong"};
        return this.getListOfArray(sql, cols);
    }

    public List<Object[]> getSanPhamNhapKho(int nam) {
        String sql = "{CALL sp_SanPhamNhapKho(?)}";
        String[] cols = {"SanPham", "MauSac", "PhanKhoi", "SoLuong"};
        return this.getListOfArray(sql, cols, nam);
    }

    public List<Object[]> getNhanVien(int nam) {
        String sql = "{CALL sp_NhanVienTieuBieu(?)}";
        String[] cols = {"MaNV", "TenNV", "NgaySinh", "GioiTinh", "SoLuong"};
        return this.getListOfArray(sql, cols, nam);
    }

    public List<Object[]> getKhachHang(int nam) {
        String sql = "{CALL sp_KhachHangTieuBieu(?)}";
        String[] cols = {"MaKH", "TenKH", "NgaySinh", "GioiTinh", "SDT", "Email", "SoLuong"};
        return this.getListOfArray(sql, cols, nam);
    }
}
