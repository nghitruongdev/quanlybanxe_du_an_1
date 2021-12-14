package com.ultramotor.dao;

import com.microsoft.sqlserver.jdbc.SQLServerDataTable;
import com.ultramotor.entity.ChiTietHoaDon;
import com.ultramotor.entity.HoaDon;
import com.ultramotor.util.XJdbc;
import com.ultramotor.util.XJdbcServer;
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

    String INSERT_SQL = "INSERT INTO HoaDon(idHD, thoiGian, giamGia, trangThai, id_NV, idKH)VALUES(?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE HoaDon SET thoiGian=?,giamGia=?,trangThai=?,id_NV=?,idKH=? WHERE idHD=?";
    String DELETE_SQL = "DELETE FROM HoaDon WHERE idHD=?";
    String SELECT_BY_KHACH_HANG_SQL = "SELECT * FROM HOADON WHERE idKH = ?";

    static ChiTietHoaDonDAO dao = new ChiTietHoaDonDAO();

    @Override
    public int insert(HoaDon e) {
        return XJdbcServer.update(INSERT_SQL,
                e.getIdHD(), e.getThoiGian(), e.getGiamGia(), e.getTrangThai(), e.getIdNV(), e.getIdKH());
    }

    @Override
    public int update(HoaDon e) {
        return XJdbcServer.update(UPDATE_SQL,
                e.getThoiGian(), e.getGiamGia(), e.getTrangThai(), e.getIdNV(), e.getIdKH(), e.getIdHD());
    }

    @Override
    public int delete(String id) {
        return XJdbcServer.update(DELETE_SQL, id);
    }

    public List<HoaDon> selectByTime(String time) {
        String SQL = "SELECT * FROM HoaDon WHERE thoiGian=?";
        return this.selectBySQL(SQL, time);
    }

    public List<HoaDon> selectByKhachHang(String idKH) {
        return selectBySQL(SELECT_BY_KHACH_HANG_SQL, idKH);
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
                HoaDon hd = new HoaDon(
                        rs.getString(1),
                        rs.getDate(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6));
                hd.setListCTHD(dao.selectByHoaDon(hd.getIdHD()));
                list.add(hd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhaSanXuatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    // tìm kiếm năm để thống kê doanh thu theo năm
    public List<Integer> selectYears() {
        String sql = "SELECT DISTINCT year(thoiGian) Year FROM HoaDon ORDER BY Year DESC";
        List<Integer> list = new ArrayList<>();
        try (ResultSet rs = XJdbcServer.query(sql)) {
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;

    }

    public void insertWithChiTiet(HoaDon hd, SQLServerDataTable chiTiet) throws SQLException {
        insert(hd);
        dao.insert(chiTiet);
    }
}

class ChiTietHoaDonDAO {

    String INSERT_SQL = "INSERT INTO ChiTietHoaDon(id_CTHD,SKU,idHD)VALUES(?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE ChiTietHoaDon SET SKU=?,idHD=? WHERE id_CTHD=?";
    String DELETE_SQL = "DELETE FROM ChiTietHoaDon WHERE id_CTHD=?";
    String INSERT_MULTIPLE_CHITIET = "exec usp_insert_ChiTietHoaDon ?";
    String SELECT_BY_HOADON_SQL = "SELECT ct.id_CTHD, ct.idHD, ct.SKU,  sp.giaTien FROM ChiTietHoaDon ct JOIN SanPham sp ON ct.SKU = sp.SKU WHERE ct.idHD = ?";

    public List<ChiTietHoaDon> selectByHoaDon(String idHD) {
        return selectBySQL(SELECT_BY_HOADON_SQL, idHD);
    }

    public List<ChiTietHoaDon> selectBySQL(String sql, Object... args) {
        List<ChiTietHoaDon> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.query(sql, args)) {
            while (rs.next()) {
                ChiTietHoaDon ct = new ChiTietHoaDon(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3));
                ct.setDonGia(rs.getDouble(4));
                list.add(ct);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhaSanXuatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void insert(SQLServerDataTable table) throws SQLException {
        XJdbcServer.update(INSERT_MULTIPLE_CHITIET, new String[]{"ChiTietHoaDonType"}, table);
    }
}
