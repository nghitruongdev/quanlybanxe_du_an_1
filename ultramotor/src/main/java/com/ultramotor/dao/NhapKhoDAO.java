package com.ultramotor.dao;

import com.microsoft.sqlserver.jdbc.SQLServerDataTable;
import com.ultramotor.entity.ChiTietNhapKho;
import com.ultramotor.entity.PhieuNhapKho;
import com.ultramotor.util.XJdbc;
import com.ultramotor.util.XJdbcServer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NhapKhoDAO extends UltraDAO<PhieuNhapKho, String> {

    {
        TABLE_NAME = "PhieuNhapKho";
        SELECT_BY_ID_SQL = String.format("select * from %s where %s = ?", TABLE_NAME, "id_PN");
        SELECT_ALL_SQL = String.format("select * from %s", TABLE_NAME);
    }
    final String INSERT_SQL = String.format("INSERT INTO %s (%s, %s ,%s ) VALUES (?, ?, ?)", TABLE_NAME, "id_PN", "ngayNhap", "id_NV");
    final String DELETE_SQL = String.format("DELETE FROM %s WHERE %s = ?", TABLE_NAME, "id_PN");
    final String INSERT_CHITIET = "exec usp_insert_NhapKho (?, ?, ?, ?)";

    static ChiTietNhapKhoDAO dao = new ChiTietNhapKhoDAO();

    @Override
    public int insert(PhieuNhapKho e) {
        return XJdbc.update(INSERT_SQL, e.getIdPN(), e.getNgayNhap(), e.getIdNV());
    }

    @Override
    public int update(PhieuNhapKho e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int delete(String id) {
        return XJdbc.update(DELETE_SQL, id);
    }

    // tìm kiếm năm để thống kê sản phẩm nhập kho theo năm
    public List<Integer> selectYears() {
        String sql = "SELECT DISTINCT year(ngayNhap) Year FROM PhieuNhapKho ORDER BY Year DESC";
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

    @Override
    public List<PhieuNhapKho> selectBySQL(String sql, Object... args) {
        List<PhieuNhapKho> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.query(sql, args)) {
            while (rs.next()) {
                PhieuNhapKho pnk = new PhieuNhapKho(
                        rs.getString(1),
                        rs.getDate(2),
                        rs.getString(3));
                pnk.setChiTietNhapKhoList(dao.selectByPhieuNhap(rs.getString(1)));
                list.add(pnk);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhaSanXuatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void insertWithChiTiet(PhieuNhapKho pnk, SQLServerDataTable chiTiet) throws SQLException {
        insert(pnk);
        dao.insert(chiTiet);
    }
}

class ChiTietNhapKhoDAO extends UltraDAO<ChiTietNhapKho, Integer> {

    {
        TABLE_NAME = "ChiTietNhapKho";
        SELECT_BY_ID_SQL = String.format("select * from %s where %s = ?", TABLE_NAME, "id_CTNK");
        SELECT_ALL_SQL = String.format("select * from %s", TABLE_NAME);
    }
    final String SELECT_BY_PHIEU_NHAP = String.format("select * from %s where %s = ?", TABLE_NAME, "id_PN");
    final String INSERT_MULTIPLE_CHITIET = "exec usp_insert_NhapKho ?";

    @Override
    public int insert(ChiTietNhapKho e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int update(ChiTietNhapKho e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<ChiTietNhapKho> selectBySQL(String sql, Object... args) {
        List<ChiTietNhapKho> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.query(sql, args)) {
            while (rs.next()) {
                list.add(new ChiTietNhapKho(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getDouble(4),
                        rs.getString(5)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhaSanXuatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<ChiTietNhapKho> selectByPhieuNhap(String idPN) {
        return selectBySQL(SELECT_BY_PHIEU_NHAP, idPN);
    }

    public void insert(SQLServerDataTable table) throws SQLException {
        XJdbcServer.update(INSERT_MULTIPLE_CHITIET, new String[]{"ChiTietNhapKhoType"}, table);
    }
}
