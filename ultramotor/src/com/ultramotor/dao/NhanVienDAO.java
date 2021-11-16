package com.ultramotor.dao;

import com.ultramotor.entity.NhanVien;
import com.ultramotor.entity.TruongPhong;
import com.ultramotor.util.XJdbcServer;
import java.util.List;
import javax.sql.rowset.CachedRowSet;
import static com.ultramotor.util.XJdbcServer.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NhanVienDAO implements UltraDAO<NhanVien, String> {

    final String tableName = "NHANVIEN";
    Map<String, String> map = getColumnMap();

    final String SELECT_BY_ID = String.format("select * from %s where %s = ?", tableName, map.get("maNV"));

    @Override
    public void insert(NhanVien e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(NhanVien e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public NhanVien selectByID(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<NhanVien> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<NhanVien> selectBySQL(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try (ResultSet rs = query(sql, args)) {
            while (rs.next()) {
                NhanVien nv = null;
                String vaiTro = rs.getString(map.get("vaiTro"));
                switch (vaiTro) {
                    case "Trưởng Phòng":
                        nv = new TruongPhong();
                        break;
//                    case ""
//                        list.add(new NhanVien(maNV, hoNV, tenNV, ngaySinh, gioiTinh, diaChi, sdt, email, luong, hinh, vaiTro, matKhau, ghiChu));
                }
                String maNV = rs.getString(map.get("maNV"));
                String hoNV = rs.getString(map.get("hoNV"));
                String tenNV = rs.getString(map.get("tenNV"));
                Date ngaySinh = rs.getDate(map.get("ngaySinh"));
                boolean gioiTinh = rs.getBoolean(map.get("gioiTinh"));
                String diaChi = rs.getString(map.get("diaChi"));
                String sdt = rs.getString(map.get("sdt"));
                String email = rs.getString(map.get("email"));
                Double luong = rs.getDouble(map.get("luong"));
                String hinh = rs.getString(map.get("hinh"));
                String matKhau = rs.getString(map.get("matKhau"));
                String ghiChu = rs.getString(map.get("ghiChu"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public CachedRowSet getRowSet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Map<String, String> getColumnMap() {
        Map<String, String> columns = new HashMap<>();
        columns.put("maNV", "id_NV");
        columns.put("hoNV", "HoNV");
        columns.put("tenNV", "TenNV");
        columns.put("ngaySinh", "NgaySinh");
        columns.put("gioiTinh", "GioiTinh");
        columns.put("diaChi", "DiaChi");
        columns.put("sdt", "SDT");
        columns.put("email", "Email");
        columns.put("luong", "Luong");
        columns.put("hinh", "Hinh");
        columns.put("vaiTro", "VaiTro");
        columns.put("matKhau", "MatKhau");
        columns.put("ghiChu", "GhiChu");
        return columns;
    }

}
