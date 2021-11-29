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
        SELECT_BY_ID_SQL = String.format("select * from %s where %s = ?", TABLE_NAME, "id_DongSP");
        SELECT_ALL_SQL = String.format("select * from %s", TABLE_NAME);
    }
    final String INSERT_SQL = String.format("exec usp_insert_%s ?, ?, ?, ?", TABLE_NAME);
    final String DELETE_SQL = String.format("DELETE FROM %s WHERE %s = ?", TABLE_NAME, "id_DongSP");

    @Override
    public int insert(DongSanPham e) {
        return XJdbc.update(INSERT_SQL, e.getIdDongSP(), e.getTenDongSP(), e.getIdLH(), e.getIdNSX());
    }

    @Override
    public int update(DongSanPham e) {
        return insert(e);
    }

    @Override
    public int delete(String id) {
        return XJdbc.update(DELETE_SQL, id);
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
        String idNSX = (nsx != null && nsx.getIdNSX() != null) ? nsx.getIdNSX() : "";
        String idLH = (lh != null && lh.getIdLH() != null) ? lh.getIdLH() : "";
        return selectBySQL(sql, getLikeSQL(idNSX), getLikeSQL(idLH));
    }

    private String getLikeSQL(String s) {
        return "%" + s + "%";
    }
}
