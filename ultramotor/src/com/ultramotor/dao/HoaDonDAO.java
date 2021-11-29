package com.ultramotor.dao;

import com.ultramotor.entity.ChiTietHoaDon;
import com.ultramotor.entity.HoaDon;
import com.ultramotor.util.XJdbc;
import com.ultramotor.util.XJdbcServer;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HoaDonDAO extends UltraDAO<HoaDon, String> {

    {
        TABLE_NAME = "HoaDon";
        SELECT_BY_ID_SQL = String.format("select * from %s where %s = ?", TABLE_NAME, "idHD");
        SELECT_ALL_SQL = String.format("select * from %s", TABLE_NAME);
    }
    String INSERT_SQL = "INSERT INTO HoaDon(idHD,thoiGian,loaiThanhToan,trangThai,id_NV,idKH)VALUES(?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE HoaDon SET thoiGian=?,loaiThanhToan=?,trangThai=?,id_NV=?,idKH=? WHERE idHD=?";
    String DELETE_SQL = "DELETE FROM HoaDon WHERE idHD=?";

    @Override
    public int insert(HoaDon e) {
        return XJdbcServer.update(INSERT_SQL,
                e.getIdHD(), e.getThoiGian(), e.getLoaiThanhToan(), e.getTrangThai(), e.getIdNV(), e.getIdKH());
    }

    @Override
    public int update(HoaDon e) {
        return XJdbcServer.update(UPDATE_SQL,
                e.getThoiGian(), e.getLoaiThanhToan(), e.getTrangThai(), e.getIdNV(), e.getIdKH(), e.getIdHD());
    }

    @Override
    public int delete(String id) {
        return XJdbcServer.update(DELETE_SQL, id);
    }

    public List<HoaDon> selectByTime(String time) {
        String SQL = "SELECT * FROM HoaDon WHERE thoiGian=?";
        return this.selectBySQL(SQL, time);
    }

    @Override
    public List<HoaDon> selectAll() {
        return selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public List<HoaDon> selectBySQL(String sql, Object... args) {
        List<HoaDon> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.query(sql, args)) {
            while (rs.next()) {
                list.add(new HoaDon(
                        rs.getString(1),
                        rs.getDate(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhaSanXuatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}

class ChiTietHoaDonDAO extends UltraDAO<ChiTietHoaDon, Integer> {

    {
        TABLE_NAME = "ChiTietHoaDon";
        SELECT_BY_ID_SQL = String.format("select * from %s where %s = ?", TABLE_NAME, "id_CTHD");
        SELECT_ALL_SQL = String.format("select * from %s", TABLE_NAME);
    }

    String INSERT_SQL = "INSERT INTO ChiTietHoaDon(id_CTHD,donGia,dichVu,SKU,idHD)VALUES(?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE ChiTietHoaDon SET donGia=?,dichVu=?,SKU=?,idHD=? WHERE id_CTHD=?";
    String DELETE_SQL = "DELETE FROM ChiTietHoaDon WHERE id_CTHD=?";

    @Override
    public int insert(ChiTietHoaDon e) {
        return XJdbcServer.update(INSERT_SQL,
                e.getIdHD(), e.getDonGia(), e.getSKU(), e.getIdHD());
    }

    @Override
    public int update(ChiTietHoaDon e) {
        return XJdbcServer.update(UPDATE_SQL,
                e.getDonGia(), e.getSKU(), e.getIdHD(), e.getIdCTHD());
    }

    @Override
    public int delete(Integer id) {
        return XJdbcServer.update(DELETE_SQL, id);
    }

    @Override
    public List<ChiTietHoaDon> selectBySQL(String sql, Object... args) {
        List<ChiTietHoaDon> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.query(sql, args)) {
            while (rs.next()) {
                list.add(new ChiTietHoaDon(
                        rs.getInt(1),
                        rs.getDouble(2),
                        rs.getString(3),
                        rs.getString(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhaSanXuatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}
