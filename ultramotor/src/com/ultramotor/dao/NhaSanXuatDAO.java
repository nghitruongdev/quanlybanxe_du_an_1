package com.ultramotor.dao;

import com.ultramotor.entity.NhaSanXuat;
import com.ultramotor.util.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.CachedRowSet;

public class NhaSanXuatDAO extends UltraDAO<NhaSanXuat, String> {

    {
        TABLE_NAME = "NhaSanXuat";
        SELECT_BY_ID = String.format("select * from %s where %s = ?", TABLE_NAME, "id_NSX");
        SELECT_ALL = String.format("select * from %s", TABLE_NAME);
    }

    @Override
    public void insert(NhaSanXuat e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(NhaSanXuat e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<NhaSanXuat> selectBySQL(String sql, Object... args) {
        List<NhaSanXuat> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.query(sql, args)) {
            while (rs.next()) {
                list.add(new NhaSanXuat(
                        rs.getString(1),
                        rs.getString(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhaSanXuatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

//    @Override
//    public CachedRowSet getRowSet() {
//        return XJdbc.getRowSet(SELECT_ALL);
//    }

}
