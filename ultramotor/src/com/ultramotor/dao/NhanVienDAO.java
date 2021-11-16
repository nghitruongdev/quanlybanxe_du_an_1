package com.ultramotor.dao;

import com.ultramotor.entity.NhanVien;
import com.ultramotor.entity.NhanVienBanHang;
import com.ultramotor.entity.NhanVienKho;
import com.ultramotor.entity.TruongPhong;
import com.ultramotor.util.XJdbc;
import java.util.List;
import javax.sql.rowset.CachedRowSet;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NhanVienDAO extends UltraDAO<NhanVien, String> {

    {
        TABLE_NAME = "NhanVien";
        SELECT_BY_ID = String.format("select * from %s where %s = ?", TABLE_NAME, "id_NV");
        SELECT_ALL = String.format("select * from %s", TABLE_NAME);
    }

    @Override
    public void insert(NhanVien e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(NhanVien e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<NhanVien> selectBySQL(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.query(sql, args)) {
            while (rs.next()) {
                NhanVien nv = null;
                String vaiTro = rs.getString("VaiTro");
                switch (vaiTro) {
                    case "Trưởng Phòng":
                        nv = new TruongPhong();
                        break;
                    case "Nhân Viên Bán Hàng":
                        nv = new NhanVienBanHang();
                        break;
                    case "Nhân Viên Kho":
                        nv = new NhanVienKho();
                        break;
                }
                nv.setIdNV(rs.getString(1));
                nv.setHoNV(rs.getString(2));
                nv.setTenNV(rs.getString(3));
                nv.setNgaySinh(rs.getDate(4));
                nv.setGioiTinh(rs.getBoolean(5));
                nv.setDiaChi(rs.getString(6));
                nv.setSdt(rs.getString(7));
                nv.setEmail(rs.getString(8));
                nv.setLuong(rs.getDouble(9));
                nv.setHinh(rs.getString(10));
                nv.setVaiTro(rs.getString(11));
                nv.setMatKhau(rs.getString(12));
                nv.setGhiChu(rs.getString(13));
                list.add(nv);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

//    @Override
    public CachedRowSet getRowSet() {
//        return XJdbc.getRowSet("Select * from NhanVien");
        return XJdbc.getRowSet(SELECT_ALL);
    }

}
