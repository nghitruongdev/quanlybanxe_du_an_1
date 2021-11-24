package com.ultramotor.dao;

import com.ultramotor.entity.HoaDon;
import com.ultramotor.entity.ModelSanPham;
import com.ultramotor.entity.SanPham;
import com.ultramotor.util.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SanPhamDAO extends UltraDAO<SanPham, String> {

    {
        TABLE_NAME = "SanPham";
        SELECT_BY_ID = String.format("select * from %s where %s = ?", TABLE_NAME, "SKU");
        SELECT_ALL = String.format("select * from %s", TABLE_NAME);
    }

    @Override
    public void insert(SanPham e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(SanPham e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<SanPham> selectBySQL(String sql, Object... args) {
        List<SanPham> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.query(sql, args)) {
            while (rs.next()) {
                list.add(new SanPham(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getString(8), 
                        rs.getFloat(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhaSanXuatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<SanPham> getListSP(ModelSanPham model) {
        String sql = "SELECT * FROM SanPham WHERE id_DongSP = ? AND DoiXe = ? AND PhanKhoi = ?";
        return selectBySQL(sql, model.getId_dongSP(), model.getDoiXe(), model.getPhanKhoi());
    }
}
