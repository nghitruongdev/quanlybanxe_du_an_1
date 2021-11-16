package com.ultramotor.dao;

import com.ultramotor.entity.LoaiHang;
import com.ultramotor.util.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoaiHangDAO extends UltraDAO<LoaiHang, String> {

    {
        TABLE_NAME = "LoaiHang";
        SELECT_BY_ID = String.format("select * from %s where %s = ?", TABLE_NAME, "id_LH");
        SELECT_ALL = String.format("select * from %s", TABLE_NAME);
    }

    @Override
    public void insert(LoaiHang e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(LoaiHang e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<LoaiHang> selectBySQL(String sql, Object... args) {
        List<LoaiHang> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.query(sql, args)) {
            while (rs.next()) {
                list.add(new LoaiHang(
                        rs.getString(1),
                        rs.getString(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhaSanXuatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}
