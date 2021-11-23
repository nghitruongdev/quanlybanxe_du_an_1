package com.ultramotor.dao;

import com.ultramotor.entity.HoaDon;
import com.ultramotor.entity.LoaiHang;
import com.ultramotor.entity.ModelSanPham;
import com.ultramotor.entity.SanPham;
import com.ultramotor.util.XJdbc;
import com.ultramotor.util.XJdbcServer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SanPhamDAO extends UltraDAO<SanPham, String> {

    final String TABLE_NAME = "SanPham";
    final String SELECT_BY_ID_SQL = "SELECT * FROM SanPham WHERE SKU = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM SanPham";
    final String INSERT_SQL = "insert into SanPham(SKU, tenSp, hinh, mauSac, phanKhoi, thoiGianBH,DiaChiSX,giaTien, moTa,id_Model, id_NV ) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE SanPham SET  tenSp = ?, hinh = ?, mauSac= ?, phanKhoi= ?, thoiGianBH = ?,DiaChiSX=? ,giaTien= ?, moTa= ?,id_Model = ?, id_NV = ? WHERE SKU=?";
    final String DELETE_SQL = "DELETE FROM SanPham WHERE SKU = ?";

    @Override
    public void insert(SanPham e) {
        XJdbcServer.update(INSERT_SQL,
                e.getSku(),
                e.getTenSP(),
                e.getHinh(),
                e.getMauSac(),
                e.getPhanKhoi(),
                e.getThoiGianBH(),
                e.getDiaChiSX(),
                e.getGiaTien(),
                e.getMoTa(),
                e.getIdModel(),
                e.getIdNV());
    }

    @Override
    public void update(SanPham e) {
        XJdbcServer.update(UPDATE_SQL, 
                e.getTenSP(),
                e.getHinh(),
                e.getMauSac(),
                e.getPhanKhoi(),
                e.getThoiGianBH(),
                e.getDiaChiSX(),
                e.getGiaTien(),
                e.getMoTa(),
                e.getIdModel(),
                e.getIdNV(),
                e.getSku());
    }

    @Override
    public void delete(String id) {
        XJdbcServer.update(DELETE_SQL, id);
    }
    @Override
    public List<SanPham> selectAll() {
        return this.selectBySQL(SELECT_ALL_SQL);
    }
    @Override
    public SanPham selectByID(String id) {
        System.out.println(id);
        List<SanPham> list = this.selectBySQL(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
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
                        rs.getString(7),
                        rs.getDouble(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhaSanXuatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public List<SanPham> selectByKeyword(String keyWord){
        String sql= "SELECT * FROM SanPham WHERE tenSP like ? or mauSac like ? ";
        return this.selectBySQL(sql, "%"+keyWord+"%","%"+keyWord+"%");
    }
}
