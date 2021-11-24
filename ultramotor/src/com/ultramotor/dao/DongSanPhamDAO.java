package com.ultramotor.dao;

import com.ultramotor.entity.DongSanPham;
import com.ultramotor.entity.LoaiHang;
import com.ultramotor.entity.NhaSanXuat;
import com.ultramotor.util.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DongSanPhamDAO extends UltraDAO<DongSanPham, String> {

    {
        TABLE_NAME = "DongSanPham";
        SELECT_BY_ID = String.format("select * from %s where %s = ?", TABLE_NAME, "id_DongSP");
        SELECT_ALL = String.format("select * from %s", TABLE_NAME);
    }

    @Override
    public void insert(DongSanPham e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(DongSanPham e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<DongSanPham> selectBySQL(String sql, Object... args) {
        List<DongSanPham> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.query(sql, args)) {
            while (rs.next()) {
                list.add(new DongSanPham(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhaSanXuatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<DongSanPham> getDongSPByNSXvaLoaiHang(NhaSanXuat nsx, LoaiHang lh) {
        String sql = "SELECT * FROM DongSanPham WHERE id_NSX LIKE ? AND id_LH LIKE ?";
        String idNSX = "";
        String idLH = "";
        if (nsx != null && nsx.getIdNSX() != null) {
            idNSX = nsx.getIdNSX();
        }
        if (lh != null && lh.getIdLH() != null) {
            idLH = lh.getIdLH();
        }
        return selectBySQL(sql, getLikeSQL(idNSX), getLikeSQL(idLH));
    }

    private String getLikeSQL(String s) {
        return "%" + s + "%";
    }
}
