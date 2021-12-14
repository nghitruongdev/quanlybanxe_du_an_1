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
        SELECT_BY_ID_SQL = String.format("select * from %s where %s = ?", TABLE_NAME, "id_NSX");
        SELECT_ALL_SQL = String.format("select * from %s", TABLE_NAME);
    }
    final String INSERT_SQL = String.format("exec usp_insert_%s ?, ?", TABLE_NAME);
    final String DELETE_SQL = String.format("DELETE FROM %s WHERE %s = ?", TABLE_NAME, "id_NSX");

    @Override
    public int insert(NhaSanXuat e) {
        return XJdbc.update(INSERT_SQL, e.getIdNSX(), e.getTenNSX());
    }

    @Override
    public int update(NhaSanXuat e) {
        return insert(e);
    }

    @Override
    public int delete(String id) {
        return XJdbc.update(DELETE_SQL, id);
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

}
