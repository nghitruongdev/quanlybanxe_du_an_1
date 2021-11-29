package com.ultramotor.dao;

import com.ultramotor.entity.ChiTietBaoDuong;
import com.ultramotor.entity.ChiTietBaoHanh;
import com.ultramotor.entity.PhieuXuatKho;
import com.ultramotor.entity.SoBaoHanh;
import com.ultramotor.util.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SoBaoHanhDAO extends UltraDAO<SoBaoHanh, String> {

    {
        TABLE_NAME = "SoBaoHanh";
        SELECT_BY_ID_SQL = String.format("select * from %s where %s = ?", TABLE_NAME, "id_SBH");
        SELECT_ALL_SQL = String.format("select * from %s", TABLE_NAME);
    }

    @Override
    public int insert(SoBaoHanh e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int update(SoBaoHanh e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int delete(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<SoBaoHanh> selectBySQL(String sql, Object... args) {
        List<SoBaoHanh> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.query(sql, args)) {
            while (rs.next()) {
                list.add(new SoBaoHanh(rs.getString(1), rs.getInt(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhaSanXuatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}

class BaoHanhDAO extends UltraDAO<ChiTietBaoHanh, Integer> {

    {
        TABLE_NAME = "ChiTietBaoHanh";
        SELECT_BY_ID_SQL = String.format("select * from %s where %s = ?", TABLE_NAME, "id");
        SELECT_ALL_SQL = String.format("select * from %s", TABLE_NAME);
    }

    @Override
    public int insert(ChiTietBaoHanh e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int update(ChiTietBaoHanh e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<ChiTietBaoHanh> selectBySQL(String sql, Object... args) {
        List<ChiTietBaoHanh> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.query(sql, args)) {
            while (rs.next()) {
                list.add(new ChiTietBaoHanh(
                        rs.getInt(1),
                        rs.getDate(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhaSanXuatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}

class BaoDuongDAO extends UltraDAO<ChiTietBaoDuong, Integer> {

    {
        TABLE_NAME = "ChiTietBaoDuong";
        SELECT_BY_ID_SQL = String.format("select * from %s where %s = ?", TABLE_NAME, "id");
        SELECT_ALL_SQL = String.format("select * from %s", TABLE_NAME);
    }

    @Override
    public int insert(ChiTietBaoDuong e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int update(ChiTietBaoDuong e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<ChiTietBaoDuong> selectBySQL(String sql, Object... args) {
        List<ChiTietBaoDuong> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.query(sql, args)) {
            while (rs.next()) {
                list.add(new ChiTietBaoDuong(
                        rs.getInt(1),
                        rs.getDate(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhaSanXuatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}
