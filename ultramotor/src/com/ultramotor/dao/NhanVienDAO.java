package com.ultramotor.dao;

import com.microsoft.sqlserver.jdbc.SQLServerDataTable;
import com.ultramotor.entity.NhanVien;
import com.ultramotor.entity.NhanVienBanHang;
import com.ultramotor.entity.NhanVienKho;
import com.ultramotor.entity.NhanVienKyThuat;
import com.ultramotor.entity.TruongPhong;
import com.ultramotor.util.XJdbc;
import com.ultramotor.util.XJdbcServer;
import java.util.List;
import javax.sql.rowset.CachedRowSet;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NhanVienDAO extends UltraDAO<NhanVien, String> {

    public NhanVienDAO() {
        TABLE_NAME = "NhanVien";
        SELECT_BY_ID_SQL = String.format("select * from %s where %s = ?", TABLE_NAME, "id_NV");
        SELECT_ALL_SQL = String.format("select * from %s", TABLE_NAME);
    }

    final String SQL_SELECT_BY_EMAIL = "SELECT * FROM NhanVien WHERE Email = ?";
//    final String SELECT_BY_ID_SQL = "SELECT * FROM NhanVien WHERE id_NV=?";
//    final String SELECT_ALL_SQL = "SELECT * FROM NhanVien";
    final String INSERT_SQL = "INSERT INTO NhanVien(id_NV,HONV,TENNV,NGAYSINH,GIOITINH,DIACHI,SDT,EMAIL,LUONG,HINH,VAITRO,MATKHAU,GHICHU)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE NhanVien SET HONV=?,TENNV=?,NGAYSINH=?,GIOITINH=?,DIACHI=?,SDT=?,EMAIL=?,LUONG=?,HINH=?,VAITRO=?,MATKHAU=?,GHICHU=? WHERE id_NV=?";
    final String DELETE_SQL = "DELETE FROM NhanVien WHERE id_NV=?";
    final String MERGE_SQL = "exec usp_updateNhanVien ?";

    @Override
    public int insert(NhanVien e) {
        return XJdbcServer.update(INSERT_SQL,
                e.getIdNV(), e.getHoNV(), e.getTenNV(), e.getNgaySinh(), e.getGioiTinh(), e.getDiaChi(),
                e.getSdt(), e.getEmail(), e.getLuong(), e.getHinh(), e.getVaiTro(), e.getMatKhau(), e.getGhiChu());
    }

    @Override
    public int update(NhanVien e) {
        return XJdbcServer.update(UPDATE_SQL,
                e.getHoNV(), e.getTenNV(), e.getNgaySinh(), e.getGioiTinh(), e.getDiaChi(),
                e.getSdt(), e.getEmail(), e.getLuong(), e.getHinh(), e.getVaiTro(), e.getMatKhau(), e.getGhiChu(), e.getIdNV());
    }

    @Override
    public int delete(String id) {
        return XJdbcServer.update(DELETE_SQL, id);
    }

    public NhanVien selectByEmail(String email) {
        List<NhanVien> list = selectBySQL(SQL_SELECT_BY_EMAIL, email);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<NhanVien> selectBySQL(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.query(sql, args)) {
            while (rs.next()) {
                NhanVien nv = null;
                String vaiTro = rs.getString("VaiTro");
                switch (vaiTro) {
                    case "Trưởng Phòng":
                        nv = new TruongPhong();
                        break;
                    case "Nhân Viên Bán Hàng":
                        nv = new NhanVienBanHang();
                        break;
                    case "Nhân Viên Kho":
                        nv = new NhanVienKho();
                        break;
                    case "Nhân Viên Kỹ Thuật":
                        nv = new NhanVienKyThuat();
                        break;
                    default:
                        nv = new NhanVien();
                }
                nv.setIdNV(rs.getString(1));
                nv.setHoNV(rs.getString(2));
                nv.setTenNV(rs.getString(3));
                nv.setNgaySinh(rs.getDate(4));
                nv.setGioiTinh(rs.getBoolean(5));
                nv.setDiaChi(rs.getString(6));
                nv.setSdt(rs.getString(7));
                nv.setEmail(rs.getString(8));
                nv.setLuong(rs.getDouble(9));
                nv.setHinh(rs.getString(10));
                nv.setVaiTro(rs.getString(11));
                nv.setMatKhau(rs.getString(12));
                nv.setGhiChu(rs.getString(13));
                list.add(nv);
            }
            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public CachedRowSet getRowSet() throws SQLException {
        return XJdbc.query(SELECT_ALL_SQL);
    }

    public List<NhanVien> selectByKeyword(String keyWord) {
        String sql = "SELECT * FROM NhanVien WHERE HONV LIKE ? OR TENNV LIKE ? OR id_NV like ? OR SDT like ? ";
        return this.selectBySQL(sql, "%" + keyWord + "%", "%" + keyWord + "%", "%" + keyWord + "%", "%" + keyWord + "%");
    }

    public void mergeTable(SQLServerDataTable table) throws SQLException {
        XJdbcServer.update(MERGE_SQL, new String[]{"NhanVienType"}, table);
    }
}
