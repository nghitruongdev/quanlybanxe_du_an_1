package com.ultramotor.dao;

import com.ultramotor.entity.KhachHang;
import com.ultramotor.entity.NhaSanXuat;
import com.ultramotor.util.XJdbc;
import com.ultramotor.util.XJdbcServer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KhachHangDAO extends UltraDAO<KhachHang, String> {

    {
        TABLE_NAME = "KhachHang";
        SELECT_BY_ID = String.format("select * from %s where %s = ?", TABLE_NAME, "idKH");
        SELECT_ALL = String.format("select * from %s", TABLE_NAME);
        
    }
    String INSERT_SQL = "INSERT INTO KhachHang(idKH,HoKH,TenKH,GioiTinh,NgaySinh,DiaChi,SDT,EMAIL,ThanhVien,GHICHU,id_NV)VALUES(?,?,?,?,?,?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE KhachHang SET HoKH=?,TenKH=?,GioiTinh=?,NgaySinh=?,DiaChi=?,SDT=?,EMAIL=?,ThanhVien=?,GHICHU=?,id_NV=? WHERE idKH=?";
    String DELETE_SQL = "DELETE FROM KhachHang WHERE idKH=?";
    String SQL_SELECT_BY_EMAIL = "SELECT * FROM KhachHang WHERE Email = ?";


    @Override
    public void insert(KhachHang e) {
        XJdbcServer.update(INSERT_SQL,
                e.getIdKH(), e.getHoKH(), e.getTenKH(), e.getGioiTinh(), e.getNgaySinh(), e.getDiaChi(),
                e.getSdt(), e.getEmail(), e.getThanhVien(), e.getGhiChu(),e.getMaNV());
    }

    @Override
    public void update(KhachHang e) {
        XJdbcServer.update(UPDATE_SQL,
                e.getHoKH(), e.getTenKH(), e.getGioiTinh(), e.getNgaySinh(), e.getDiaChi(),
                e.getSdt(), e.getEmail(), e.getThanhVien(), e.getGhiChu(),e.getMaNV(),e.getIdKH());
    }

    @Override
    public void delete(String id) {
        XJdbcServer.update(DELETE_SQL, id);
    }

    @Override
    public List<KhachHang> selectBySQL(String sql, Object... args) {
        List<KhachHang> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.query(sql, args)) {
            while (rs.next()) {
                list.add(new KhachHang(
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
                        rs.getString(11))
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhaSanXuatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
        public List<KhachHang> selectByKeyword(String keyWord) {
        String sql = "SELECT * FROM KhachHang WHERE HoKH LIKE ? OR TenKH LIKE ? OR idKH like ? OR SDT like ? ";
        return this.selectBySQL(sql, "%" + keyWord + "%", "%" + keyWord + "%", "%" + keyWord + "%", "%" + keyWord + "%");
    }


}
