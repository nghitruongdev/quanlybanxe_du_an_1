package com.ultramotor.dao;

import com.ultramotor.entity.ChiTietXuatKho;
import com.ultramotor.entity.PhieuXuatKho;
import com.ultramotor.util.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XuatKhoDAO extends UltraDAO<PhieuXuatKho, String> {

    {
        TABLE_NAME = "PhieuXuatKho";
        SELECT_BY_ID_SQL = String.format("select * from %s where %s = ?", TABLE_NAME, "id_PX");
        SELECT_ALL_SQL = String.format("select * from %s", TABLE_NAME);
    }

    @Override
    public int insert(PhieuXuatKho e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int update(PhieuXuatKho e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int delete(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<PhieuXuatKho> selectBySQL(String sql, Object... args) {
        List<PhieuXuatKho> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.query(sql, args)) {
            while (rs.next()) {
                list.add(new PhieuXuatKho(
                        rs.getString(1),
                        rs.getDate(2),
                        rs.getString(3)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhaSanXuatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}

class ChiTietXuatKhoDAO extends UltraDAO<ChiTietXuatKho, Integer> {

    {
        TABLE_NAME = "ChiTietXuatKho";
        SELECT_BY_ID_SQL = String.format("select * from %s where %s = ?", TABLE_NAME, "ID_CTXK");
        SELECT_ALL_SQL = String.format("select * from %s", TABLE_NAME);
    }

    @Override
    public int insert(ChiTietXuatKho e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int update(ChiTietXuatKho e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<ChiTietXuatKho> selectBySQL(String sql, Object... args) {
        List<ChiTietXuatKho> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.query(sql, args)) {
            while (rs.next()) {
                list.add(new ChiTietXuatKho(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhaSanXuatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
