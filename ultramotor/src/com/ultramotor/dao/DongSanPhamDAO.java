package com.ultramotor.dao;

import com.ultramotor.entity.DongSanPham;
import com.ultramotor.entity.LoaiHang;
import com.ultramotor.entity.NhaSanXuat;
import com.ultramotor.entity.NhanVien;
import com.ultramotor.util.XJdbc;
import com.ultramotor.util.XJdbcServer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DongSanPhamDAO extends UltraDAO<DongSanPham, String> {

    final String TABLE_NAME = "DongSanPham";
    final String SELECT_BY_ID_SQL = "SELECT * FROM DongSanPham WHERE id_DongSP = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM DongSanPham";
    final String INSERT_SQL = "insert into DongSanPham(id_DongSP,tenDongSP,id_LH,id_NSX) VALUES (?,?,?,?)";
    final String UPDATE_SQL = "UPDATE DongSanPham SET tenDongSP = ?,id_LH= ?,id_NSX= ?  WHERE id_DongSP=?";
    final String DELETE_SQL = "DELETE FROM DongSanPham WHERE id_DongSP = ?";

    @Override
    public void insert(DongSanPham e) {
        XJdbcServer.update(INSERT_SQL,
                e.getIdDongSP(), e.getTenDongSP(), e.getIdLH(), e.getIdNSX());
    }

    @Override
    public void update(DongSanPham e) {
        XJdbcServer.update(UPDATE_SQL,
                e.getTenDongSP(), e.getIdLH(), e.getIdNSX(), e.getIdDongSP());
    }

    @Override
    public void delete(String id) {
        XJdbcServer.update(DELETE_SQL, id);
    }

    @Override
    public List<DongSanPham> selectAll() {
        return this.selectBySQL(SELECT_ALL_SQL);
    }
    @Override
    public DongSanPham selectByID(String id) {
        System.out.println(id);
        List<DongSanPham> list = this.selectBySQL(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
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

    public List<DongSanPham> selectByKeyword(String keyWord) {
        String sql = "SELECT * FROM DongSanPham WHERE id_DongSP like ? or tenDongSP like ? ";
        return this.selectBySQL(sql, "%" + keyWord + "%", "%" + keyWord + "%");
    }


}
