package com.edusys.dao;

import com.edusys.entity.ChuyenDe;
import java.util.List;
import com.edusys.util.XJdbc;
import com.edusys.util.XLog;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ChuyenDeDAO extends EduSysDAO<ChuyenDe, String> {

    final String SQL_INSERT = "INSERT INTO ChuyenDe (MaCD, TenCD, HocPhi, ThoiLuong, Hinh, MoTa) VALUES (?, ?, ?, ?, ?, ?)";
    final String SQL_UPDATE = "UPDATE ChuyenDe SET TenCD = ?, HocPhi = ?, ThoiLuong = ?, Hinh = ?, MoTa = ? WHERE MaCD = ?";
    final String SQL_DELETE = "DELETE FROM ChuyenDe WHERE MaCD = ?";
    final String SQL_SELECT_ALL = "SELECT * FROM ChuyenDe";
    final String SQL_SELECT_BY_ID = "SELECT * FROM ChuyenDe WHERE MaCD = ?";

    @Override
    public int insert(ChuyenDe e) {
        return XJdbc.update(SQL_INSERT,
                e.getMaCD(),
                e.getTenCD(),
                e.getHocPhi(),
                e.getThoiLuong(),
                e.getHinh(),
                e.getMoTa());
    }

    @Override
    public int update(ChuyenDe e) {
        return XJdbc.update(SQL_UPDATE,
                e.getTenCD(),
                e.getHocPhi(),
                e.getThoiLuong(),
                e.getHinh(),
                e.getMoTa(),
                e.getMaCD());
    }

    @Override
    public int delete(String id) {
        return XJdbc.update(SQL_DELETE, id);
    }

    @Override
    public ChuyenDe selectById(String id) {
        List<ChuyenDe> list = selectBySQL(SQL_SELECT_BY_ID, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<ChuyenDe> selectAll() {
        return selectBySQL(SQL_SELECT_ALL);
    }

    @Override
    protected List<ChuyenDe> selectBySQL(String sql, Object... args) {
        List<ChuyenDe> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.query(sql, args)) {
            while (rs.next()) {
                ChuyenDe e = new ChuyenDe(
                        rs.getString("MaCD"),
                        rs.getString("TenCD"),
                        rs.getDouble("HocPhi"),
                        rs.getInt("ThoiLuong"),
                        rs.getString("Hinh"),
                        rs.getString("MoTa"));
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
