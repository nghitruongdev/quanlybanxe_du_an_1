package com.ultramotor.dao;

import com.ultramotor.entity.DichVu;
import com.ultramotor.util.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DichVuDAO extends UltraDAO<DichVu, String> {

    {
        TABLE_NAME = "DichVu";
        SELECT_BY_ID = String.format("select * from %s where %s = ?", TABLE_NAME, "idDV");
        SELECT_ALL = String.format("select * from %s", TABLE_NAME);
    }

    @Override
    public void insert(DichVu e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(DichVu e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<DichVu> selectBySQL(String sql, Object... args) {
        List<DichVu> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.query(sql, args)) {
            while (rs.next()) {
                list.add(new DichVu(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhaSanXuatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}
