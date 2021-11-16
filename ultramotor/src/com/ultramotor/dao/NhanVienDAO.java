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

//    final String SELECT_BY_ID = String.format("select * from %s where %s = ?", tableName, map.get("maNV")); không hiểu cách này (Tú)
    final String SELECT_BY_ID_SQL = "SELECT * FROM NhanVien WHERE id_NV=?";
    final String SELECT_ALL_SQL = "SELECT * FROM NhanVien";
    final String INSERT_SQL = "INSERT INTO NhanVien(id_NV,HONV,TENNV,NGAYSINH,GIOITINH,DIACHI,SDT,EMAIL,LUONG,HINH,VAITRO,MATKHAU,GHICHU)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE NhanVien SET HONV=?,TENNV=?,NGAYSINH=?,GIOITINH=?,DIACHI=?,SDT=?,EMAIL=?,LUONG=?,HINH=?,VAITRO=?,MATKHAU=?,GHICHU=? WHERE id_NV=?";
    final String DELETE_SQL = "DELETE FROM NhanVien WHERE id_NV=?";
    @Override
    public void insert(NhanVien e) {
        XJdbcServer.update(INSERT_SQL,
                e.getIdNV(),e.getHoNV(),e.getTenNV(),e.getNgaySinh(),e.getGioiTinh(),e.getDiaChi(),
                e.getSdt(),e.getEmail(),e.getLuong(),e.getHinh(),e.getVaiTro(),e.getMatKhau(),e.getGhiChu());
    }

    @Override
    public void update(NhanVien e) {
        XJdbcServer.update(UPDATE_SQL,
                e.getHoNV(),e.getTenNV(),e.getNgaySinh(),e.getGioiTinh(),e.getDiaChi(),
                e.getSdt(),e.getEmail(),e.getLuong(),e.getHinh(),e.getVaiTro(),e.getMatKhau(),e.getGhiChu(),e.getIdNV());
    }

    @Override
    public void delete(String id) {
        XJdbcServer.update(DELETE_SQL, id);
   
    }

    @Override
    public NhanVien selectByID(String id) {
        System.out.println(id);
        List<NhanVien> list = this.selectBySQL(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<NhanVien> selectAll() {
        return this.selectBySQL(SELECT_ALL_SQL);
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
