package com.edusys.dao;

import com.edusys.entity.KhoaHoc;
import java.util.List;
import com.edusys.util.XJdbc;
import com.edusys.util.XLog;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class KhoaHocDAO extends EduSysDAO<KhoaHoc, Integer> {

    final String SQL_INSERT = "{Call sp_insert_KhoaHoc_Identity(?, ?, ?, ?, ?, ?, ?, ?)}";
    final String SQL_UPDATE = "UPDATE KhoaHoc SET MaCD = ?, HocPhi = ?, ThoiLuong = ?, NgayKG = ?, GhiChu = ?, MaNV = ?, NgayTao = ? WHERE MaKH = ?";
    final String SQL_DELETE = "DELETE FROM KhoaHoc WHERE MaKH = ?";
    final String SQL_SELECT_ALL = "SELECT * FROM KhoaHoc";
    final String SQL_SELECT_BY_ID = "SELECT * FROM KhoaHoc WHERE MaKH = ?";
    final String SQL_SELECT_YEARS = "SELECT DISTINCT YEAR(NgayKG) as Nam FROM KhoaHoc ORDER BY YEAR(NgayKG) DESC";

    @Override
    public int insert(KhoaHoc e) {
        return XJdbc.update(SQL_INSERT,
                e.getMaKH(),
                e.getMaCD(),
                e.getHocPhi(),
                e.getThoiLuong(),
                e.getNgayKG(),
                e.getGhiChu(),
                e.getMaNV(),
                e.getNgayTao());
    }

    @Override
    public int update(KhoaHoc e) {
        return XJdbc.update(SQL_UPDATE,
                e.getMaCD(),
                e.getHocPhi(),
                e.getThoiLuong(),
                e.getNgayKG(),
                e.getGhiChu(),
                e.getMaNV(),
                e.getNgayTao(),
                e.getMaKH());
    }

    @Override
    public int delete(Integer id) {
        return XJdbc.update(SQL_DELETE, id);
    }

    @Override
    public KhoaHoc selectById(Integer id) {
        List<KhoaHoc> list = selectBySQL(SQL_SELECT_BY_ID, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<KhoaHoc> selectAll() {
        return selectBySQL(SQL_SELECT_ALL);
    }

    @Override
    protected List<KhoaHoc> selectBySQL(String sql, Object... args) {
        List<KhoaHoc> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.query(sql, args)) {
            while (rs.next()) {
                KhoaHoc e = new KhoaHoc(
                        rs.getInt("MaKH"),
                        rs.getString("MaCD"),
                        rs.getDouble("HocPhi"),
                        rs.getInt("ThoiLuong"),
                        rs.getDate("NgayKG"),
                        rs.getString("GhiChu"),
                        rs.getString("MaNV"),
                        rs.getDate("NgayTao"));
                list.add(e);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            XJdbc.closeCon();
        }
        return list;
    }

    public List<Integer> selectAllYears() {
        List<Integer> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.query(SQL_SELECT_YEARS)) {
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
        } catch (SQLException ex) {
            XLog.saveLog(ex.getMessage());
        } finally {
            XJdbc.closeCon();
        }
        return list;
    }

}
