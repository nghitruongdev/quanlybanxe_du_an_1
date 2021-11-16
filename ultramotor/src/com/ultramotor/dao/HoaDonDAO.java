package com.ultramotor.dao;

import com.ultramotor.entity.ChiTietHoaDon;
import com.ultramotor.entity.HoaDon;
import com.ultramotor.util.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HoaDonDAO extends UltraDAO<HoaDon, String> {

    {
        TABLE_NAME = "HoaDon";
        SELECT_BY_ID = String.format("select * from %s where %s = ?", TABLE_NAME, "idHD");
        SELECT_ALL = String.format("select * from %s", TABLE_NAME);
    }

    @Override
    public void insert(HoaDon e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(HoaDon e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<HoaDon> selectBySQL(String sql, Object... args) {
        List<HoaDon> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.query(sql, args)) {
            while (rs.next()) {
                list.add(new HoaDon(
                        rs.getString(1),
                        rs.getDate(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhaSanXuatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}

class ChiTietHoaDonDAO extends UltraDAO<ChiTietHoaDon, Integer> {

    {
        TABLE_NAME = "ChiTietHoaDon";
        SELECT_BY_ID = String.format("select * from %s where %s = ?", TABLE_NAME, "id_CTHD");
        SELECT_ALL = String.format("select * from %s", TABLE_NAME);
    }

    @Override
    public void insert(ChiTietHoaDon e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(ChiTietHoaDon e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<ChiTietHoaDon> selectBySQL(String sql, Object... args) {
        List<ChiTietHoaDon> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.query(sql, args)) {
            while (rs.next()) {
                list.add(new ChiTietHoaDon(
                        rs.getInt(1),
                        rs.getDouble(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhaSanXuatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
