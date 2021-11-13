package com.edusys.dao;

import java.util.List;
import com.edusys.entity.NhanVien;
import com.edusys.util.XJdbc;
import com.edusys.util.XLog;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NhanVienDAO extends EduSysDAO<NhanVien, String> {

    final String SQL_INSERT = "INSERT INTO NhanVien (MaNV, MatKhau, HoTen, Email, VaiTro) values (?, ?, ?, ?, ?)";
    final String SQL_UPDATE = "UPDATE NhanVien SET MatKhau = ?, HoTen = ?, Email = ?, VaiTro = ? WHERE MaNV = ?";
    final String SQL_DELETE = "DELETE FROM NhanVien WHERE MaNV = ?";
    final String SQL_SELECT_ALL = "SELECT * FROM NhanVien";
    final String SQL_SELECT_BY_ID = "SELECT * FROM NhanVien WHERE MaNV = ?";
    final String SQL_SELECT_BY_EMAIL = "SELECT * FROM NhanVien WHERE Email = ?";

    @Override
    public int insert(NhanVien e) {
        return XJdbc.update(SQL_INSERT,
                e.getMaNV(),
                e.getMatKhau(),
                e.getHoTen(),
                e.getEmail(),
                e.getVaiTro());
    }

    @Override
    public int update(NhanVien e) {
        return XJdbc.update(SQL_UPDATE,
                e.getMatKhau(),
                e.getHoTen(),
                e.getEmail(),
                e.getVaiTro(),
                e.getMaNV());
    }

    @Override
    public int delete(String id) {
        return XJdbc.update(SQL_DELETE, id);
    }

    @Override
    public NhanVien selectById(String id) {
        List<NhanVien> list = selectBySQL(SQL_SELECT_BY_ID, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<NhanVien> selectAll() {
        return selectBySQL(SQL_SELECT_ALL);
    }

    @Override
    protected List<NhanVien> selectBySQL(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.query(sql, args)) {
            while (rs.next()) {
                String maNV = rs.getString("MaNV");
                String hoTen = rs.getString("HoTen");
                String matKhau = rs.getString("MatKhau");
                String email = rs.getString("Email");
                Boolean vaiTro = rs.getBoolean("VaiTro");
                NhanVien nv = new NhanVien(maNV, hoTen, matKhau, email, vaiTro);
                list.add(nv);
            }
        } catch (SQLException ex) {
            XLog.saveLog(ex.getMessage());
        } finally {
            XJdbc.closeCon();
        }
        return list;
    }

    public NhanVien selectByEmail(String email) {
        List<NhanVien> list = selectBySQL(SQL_SELECT_BY_EMAIL, email);
        return list.isEmpty() ? null : list.get(0);
    }
}
