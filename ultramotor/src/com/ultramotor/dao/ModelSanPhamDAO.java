package com.ultramotor.dao;

import com.ultramotor.entity.ModelSanPham;
import com.ultramotor.util.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModelSanPhamDAO extends UltraDAO<ModelSanPham, String> {

    {
        TABLE_NAME = "ModelSanPham";
        SELECT_BY_ID = String.format("select * from %s where %s = ?", TABLE_NAME, "id_Model");
        SELECT_ALL = String.format("select * from %s", TABLE_NAME);
    }

    @Override
    public void insert(ModelSanPham e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(ModelSanPham e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<ModelSanPham> selectBySQL(String sql, Object... args) {
        List<ModelSanPham> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.query(sql, args)) {
            while (rs.next()) {
                list.add(new ModelSanPham(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhaSanXuatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
