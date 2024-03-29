package com.ultramotor.dao;

import com.ultramotor.entity.KhachHang;
import com.ultramotor.util.XJdbc;
import com.ultramotor.util.XJdbcServer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KhachHangDAO extends UltraDAO<KhachHang, String> {
    
    {
        TABLE_NAME = "KhachHang";
        SELECT_BY_ID_SQL = String.format("select * from %s where %s = ? ", TABLE_NAME, "idKH");
        SELECT_ALL_SQL = String.format("select * from %s", TABLE_NAME);
        
    }
    String INSERT_SQL = "exec usp_insert_KhachHang ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
    String DELETE_SQL = "DELETE FROM KhachHang WHERE idKH=?";
    String SQL_SELECT_BY_EMAIL = "SELECT * FROM KhachHang WHERE Email = ?";
    HoaDonDAO dao = new HoaDonDAO();
    
    @Override
    public int insert(KhachHang e) {
        return XJdbcServer.update(INSERT_SQL,
                e.getIdKH(), e.getHoKH(), e.getTenKH(), e.getGioiTinh(), e.getNgaySinh(), e.getDiaChi(),
                e.getSdt(), e.getEmail(), e.getThanhVien(), e.getGhiChu(), e.getMaNV());
    }
    
    @Override
    public int update(KhachHang e) {
        return insert(e);
    }
    
    @Override
    public int delete(String id) {
        return XJdbcServer.update(DELETE_SQL, id);
    }
    
    @Override
    public List<KhachHang> selectBySQL(String sql, Object... args) {
        List<KhachHang> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.query(sql, args)) {
            while (rs.next()) {
                KhachHang kh = new KhachHang(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getBoolean(4),
                        rs.getDate(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getBoolean(9),
                        rs.getString(10),
                        rs.getString(11));
                kh.setHoaDonList(dao.selectByKhachHang(kh.getIdKH()));
                list.add(kh);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhaSanXuatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
}
