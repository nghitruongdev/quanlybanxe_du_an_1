package com.edusys.dao;

import java.util.List;
import com.edusys.entity.NguoiHoc;
import com.edusys.util.XJdbc;
import com.edusys.util.XLog;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NguoiHocDAO extends EduSysDAO<NguoiHoc, String> {

    final String SQL_INSERT = "INSERT INTO NguoiHoc (MaNH, HoTen, NgaySinh, GioiTinh, DienThoai, Email, GhiChu, MaNV, NgayDK) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    final String SQL_UPDATE = "UPDATE NguoiHoc SET HoTen = ?, NgaySinh = ?, GioiTinh = ?, DienThoai = ?, Email = ?, GhiChu = ?, MaNV = ?, NgayDK = ? WHERE MaNH = ?";
    final String SQL_DELETE = "DELETE FROM NguoiHoc WHERE MaNH = ?";
    final String SQL_SELECT_ALL = "SELECT * FROM NguoiHoc";
    final String SQL_SELECT_BY_ID = "SELECT * FROM NguoiHoc WHERE MaNH = ?";

    @Override
    public int insert(NguoiHoc e) {
        return XJdbc.update(SQL_INSERT,
                e.getMaNH(),
                e.getHoTen(),
                e.getNgaySinh(),
                e.getGioiTinh(),
                e.getDienThoai(),
                e.getEmail(),
                e.getGhiChu(),
                e.getMaNV(),
                e.getNgayDK());
    }

    @Override
    public int update(NguoiHoc e) {
        return XJdbc.update(SQL_UPDATE,
                e.getHoTen(),
                e.getNgaySinh(),
                e.getGioiTinh(),
                e.getDienThoai(),
                e.getEmail(),
                e.getGhiChu(),
                e.getMaNV(),
                e.getNgayDK(),
                e.getMaNH());
    }

    @Override
    public int delete(String id) {
        return XJdbc.update(SQL_DELETE, id);
    }

    @Override
    public NguoiHoc selectById(String id) {
        List<NguoiHoc> list = selectBySQL(SQL_SELECT_BY_ID, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<NguoiHoc> selectAll() {
        return selectBySQL(SQL_SELECT_ALL);
    }

    @Override
    protected List<NguoiHoc> selectBySQL(String sql, Object... args) {
        List<NguoiHoc> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.query(sql, args)) {
            while (rs.next()) {
                NguoiHoc e = new NguoiHoc(
                        rs.getString("MaNH"),
                        rs.getString("HoTen"),
                        rs.getDate("NgaySinh"),
                        rs.getBoolean("GioiTinh"),
                        rs.getString("DienThoai"),
                        rs.getString("Email"),
                        rs.getString("GhiChu"),
                        rs.getString("MaNV"),
                        rs.getDate("NgayDK"));
                list.add(e);
            }
        } catch (SQLException ex) {
            XLog.saveLog(ex.getMessage());
        } finally {
            XJdbc.closeCon();
        }
        return list;
    }

}
