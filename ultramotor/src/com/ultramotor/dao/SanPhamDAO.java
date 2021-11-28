package com.ultramotor.dao;

import com.ultramotor.entity.HoaDon;
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

    {
        TABLE_NAME = "SanPham";
        SELECT_BY_ID = String.format("select * from %s where %s = ?", TABLE_NAME, "SKU");
        SELECT_ALL = String.format("select * from %s", TABLE_NAME);
    }
    String INSERT_SQL = "INSERT INTO SanPham(SKU,tenSP,hinh,mauSac,phanKhoi,doiXe,thoiGianBH,DiaChiSX,giaTien,moTa,tonKho,id_DongSP,id_NV)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE SanPham SET tenSP=?,hinh=?,mauSac=?,phanKhoi=?,doiXe=?,thoiGianBH=?,DiaChiSX=?,giaTien=?,moTa=?,tonKho=? ,id_DongSP=?,id_NV=? WHERE SKU=?";
    String DELETE_SQL = "DELETE FROM SanPham WHERE SKU=?";
    
    @Override
    public void insert(SanPham e) {
        XJdbcServer.update(INSERT_SQL,
                e.getSku(),e.getTenSP(),e.getHinh(),e.getMauSac(),e.getPhanKhoi(),e.getDoiXe(),e.getThoiGianBH(),
                e.getDiaChiSX(),e.getGiaTien(),e.getMoTa(),e.gettonKho(),e.getIdDongSP(),e.getIdNV());
    }

    @Override
    public void update(SanPham e) {
        XJdbcServer.update(UPDATE_SQL,
                e.getTenSP(),e.getHinh(),e.getMauSac(),e.getPhanKhoi(),e.getDoiXe(),e.getThoiGianBH(),
                e.getDiaChiSX(),e.getGiaTien(),e.getMoTa(),e.gettonKho(),e.getIdDongSP(),e.getIdNV(),e.getSku());
    }

    @Override
    public void delete(String id) {
        XJdbcServer.update(DELETE_SQL, id);
    }
    
        @Override
    public List<SanPham> selectAll(){
        return selectBySQL(SELECT_ALL);
    }
    
        @Override
    public SanPham selectByID(String key) {
        List<SanPham> list = this.selectBySQL(SELECT_BY_ID, key);
        if(list.isEmpty())
        {
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
                        rs.getInt(7),
                        rs.getString(8),
                        rs.getDouble(9),
                        rs.getString(10),
                        rs.getInt(11),
                        rs.getString(12),
                        rs.getString(13)
                        ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhaSanXuatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
