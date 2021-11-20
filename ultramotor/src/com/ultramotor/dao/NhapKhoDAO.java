package com.ultramotor.dao;

import com.ultramotor.entity.ChiTietNhapKho;
import com.ultramotor.entity.PhieuNhapKho;
import com.ultramotor.util.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NhapKhoDAO extends UltraDAO<PhieuNhapKho, String> {

    {
        TABLE_NAME = "PhieuNhapKho";
        SELECT_BY_ID = String.format("select * from %s where %s = ?", TABLE_NAME, "id_PN");
        SELECT_ALL = String.format("select * from %s", TABLE_NAME);
    }

    @Override
    public void insert(PhieuNhapKho e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(PhieuNhapKho e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<PhieuNhapKho> selectBySQL(String sql, Object... args) {
        List<PhieuNhapKho> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.query(sql, args)) {
            while (rs.next()) {
                list.add(new PhieuNhapKho(
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

class ChiTietNhapKhoDAO extends UltraDAO<ChiTietNhapKho, Integer> {

    {
        TABLE_NAME = "ChiTietNhapKho";
        SELECT_BY_ID = String.format("select * from %s where %s = ?", TABLE_NAME, "id_CTNK");
        SELECT_ALL = String.format("select * from %s", TABLE_NAME);
    }

    @Override
    public void insert(ChiTietNhapKho e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(ChiTietNhapKho e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<ChiTietNhapKho> selectBySQL(String sql, Object... args) {
        List<ChiTietNhapKho> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.query(sql, args)) {
            while (rs.next()) {
                list.add(new ChiTietNhapKho(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getDouble(3),
                        rs.getString(4),
                        rs.getString(5)));                
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhaSanXuatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
