package com.edusys.dao;

import java.util.List;
import com.edusys.entity.HocVien;
import com.edusys.util.MsgBox;
import com.edusys.util.XJdbc;
import com.edusys.util.XLog;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HocVienDAO extends EduSysDAO<HocVien, Integer> {

    final String SQL_INSERT = "{Call sp_insert_HocVien_Identity(?, ?, ?, ?)}";
    final String SQL_UPDATE = "UPDATE HocVien SET MaKH = ?, MaNH = ?, Diem = ? WHERE MaHV = ?";
    final String SQL_DELETE = "DELETE FROM HocVien WHERE MaHV = ?";
    final String SQL_SELECT_ALL = "SELECT * FROM HocVien";
    final String SQL_SELECT_BY_ID = "SELECT * FROM HocVien WHERE MaHV = ?";
//    final String SQL_SELECT_BY_MAKH = "SELECT hv.MaHV, hv.MaNH, nh.HoTen, hv.Diem FROM HocVien hv JOIN NguoiHoc nh ON hv.MaNH = nh.MaNH WHERE MAKH = ?";

    @Override
    public int insert(HocVien e) {
        return XJdbc.update(SQL_INSERT,
                e.getMaHV(),
                e.getMaKH(),
                e.getMaNH(),
                e.getDiem());
    }

    @Override
    public int update(HocVien e) {
        return XJdbc.update(SQL_UPDATE,
                e.getMaKH(),
                e.getMaNH(),
                e.getDiem(),
                e.getMaHV());
    }

    @Override
    public int delete(Integer id) {
        return XJdbc.update(SQL_DELETE, id);
    }

    @Override
    public HocVien selectById(Integer id) {
        List<HocVien> list = selectBySQL(SQL_SELECT_BY_ID, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<HocVien> selectAll() {
        return selectBySQL(SQL_SELECT_ALL);
    }

    @Override
    protected List<HocVien> selectBySQL(String sql, Object... args) {
        List<HocVien> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.query(sql, args)) {
            while (rs.next()) {
                HocVien e = new HocVien(
                        rs.getInt("MaHV"),
                        rs.getInt("MaKH"),
                        rs.getString("MaNH"),
                        rs.getObject("Diem"));
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
