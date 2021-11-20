package com.ultramotor.dao;

import com.ultramotor.entity.NhanVien;
import com.ultramotor.entity.NhanVienBanHang;
import com.ultramotor.entity.NhanVienKho;
import com.ultramotor.entity.TruongPhong;
import com.ultramotor.util.XJdbc;
import com.ultramotor.util.XJdbcServer;
import java.util.List;
import javax.sql.rowset.CachedRowSet;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NhanVienDAO extends UltraDAO<NhanVien, String> {

    final String tableName = "NHANVIEN";
    Map<String, String> map = getColumnMap();

//    final String SELECT_BY_ID = String.format("select * from %s where %s = ?", tableName, map.get("maNV")); không hiểu cách này (Tú)
    final String SQL_SELECT_BY_EMAIL = "SELECT * FROM NhanVien WHERE Email = ?";
    final String SELECT_BY_ID_SQL = "SELECT * FROM NhanVien WHERE id_NV=?";
    final String SELECT_ALL_SQL = "SELECT * FROM NhanVien";
    final String INSERT_SQL = "INSERT INTO NhanVien(id_NV,HONV,TENNV,NGAYSINH,GIOITINH,DIACHI,SDT,EMAIL,LUONG,HINH,VAITRO,MATKHAU,GHICHU)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE NhanVien SET HONV=?,TENNV=?,NGAYSINH=?,GIOITINH=?,DIACHI=?,SDT=?,EMAIL=?,LUONG=?,HINH=?,VAITRO=?,MATKHAU=?,GHICHU=? WHERE id_NV=?";
    final String DELETE_SQL = "DELETE FROM NhanVien WHERE id_NV=?";

    @Override
    public void insert(NhanVien e) {
        XJdbcServer.update(INSERT_SQL,
                e.getIdNV(), e.getHoNV(), e.getTenNV(), e.getNgaySinh(), e.getGioiTinh(), e.getDiaChi(),
                e.getSdt(), e.getEmail(), e.getLuong(), e.getHinh(), e.getVaiTro(), e.getMatKhau(), e.getGhiChu());
    }

    @Override
    public void update(NhanVien e) {
        XJdbcServer.update(UPDATE_SQL,
                e.getHoNV(), e.getTenNV(), e.getNgaySinh(), e.getGioiTinh(), e.getDiaChi(),
                e.getSdt(), e.getEmail(), e.getLuong(), e.getHinh(), e.getVaiTro(), e.getMatKhau(), e.getGhiChu(), e.getIdNV());
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

    public NhanVien selectByEmail(String email) {
        List<NhanVien> list = selectBySQL(SQL_SELECT_BY_EMAIL, email);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<NhanVien> selectBySQL(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<NhanVien>();
        try {
            ResultSet rs = XJdbc.query(sql, args);
            while (rs.next()) {
                NhanVien entity = new NhanVien();
                entity.setIdNV(rs.getString("id_NV"));
                entity.setHoNV(rs.getString("HONV"));
                entity.setTenNV(rs.getString("TENNV"));
                entity.setNgaySinh(rs.getDate("NGAYSINH"));
                entity.setGioiTinh(rs.getBoolean("GIOITINH"));
                entity.setDiaChi(rs.getString("DIACHI"));
                entity.setSdt(rs.getString("SDT"));
                entity.setEmail(rs.getString("EMAIL"));
                entity.setLuong(rs.getFloat("LUONG"));
                entity.setHinh(rs.getString("HINH"));
                entity.setVaiTro(rs.getString("VAITRO"));
                entity.setMatKhau(rs.getString("MATKHAU"));
                entity.setGhiChu(rs.getString("GHICHU"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

//    @Override
//    public List<NhanVien> selectBySQL(String sql, Object... args) {
//        List<NhanVien> list = new ArrayList<>();
//        try (ResultSet rs = query(sql, args)) {
//            while (rs.next()) {
//                NhanVien nv = null;
//                String vaiTro = rs.getString(map.get("vaiTro"));
//                switch (vaiTro) {
//                    case "Trưởng Phòng":
//                        nv = new TruongPhong();
//                        break;
////                    case ""
////                        list.add(new NhanVien(maNV, hoNV, tenNV, ngaySinh, gioiTinh, diaChi, sdt, email, luong, hinh, vaiTro, matKhau, ghiChu));
//                }
//                String maNV = rs.getString(map.get("maNV"));
//                String hoNV = rs.getString(map.get("hoNV"));
//                String tenNV = rs.getString(map.get("tenNV"));
//                Date ngaySinh = rs.getDate(map.get("ngaySinh"));
//                boolean gioiTinh = rs.getBoolean(map.get("gioiTinh"));
//                String diaChi = rs.getString(map.get("diaChi"));
//                String sdt = rs.getString(map.get("sdt"));
//                String email = rs.getString(map.get("email"));
//                Double luong = rs.getDouble(map.get("luong"));
//                String hinh = rs.getString(map.get("hinh"));
//                String matKhau = rs.getString(map.get("matKhau"));
//                String ghiChu = rs.getString(map.get("ghiChu"));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return list;
//    }
    @Override
    public CachedRowSet getRowSet() {
//        return XJdbc.getRowSet("Select * from NhanVien");
        return XJdbc.getRowSet(SELECT_ALL);
    }
    public List<NhanVien> selectByKeyword(String keyWord){
        String sql= "SELECT * FROM NhanVien WHERE HONV LIKE ? OR TENNV LIKE ? OR id_NV like ? OR SDT like ? ";
        return this.selectBySQL(sql, "%"+keyWord+"%", "%"+keyWord+"%", "%"+keyWord+"%","%"+keyWord+"%");
    }
    
}
