package com.ultramotor.dao;

import com.ultramotor.entity.ModelSanPham;
import com.ultramotor.entity.SanPham;
import com.ultramotor.ui.nhanvien.kho.nhapkho.ChiTietNhapKhoPanel;
import com.ultramotor.util.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.CachedRowSet;

public class SanPhamDAO extends UltraDAO<SanPham, String> {

    {
        TABLE_NAME = "SanPham";
        SELECT_BY_ID_SQL = String.format("SELECT * FROM %s where %s = ?", "view_SanPhamTon", "SKU");
        SELECT_ALL_SQL = String.format("SELECT * FROM %s", "view_SanPhamTon");
    }

    final String INSERT_SQL = String.format("exec usp_insert_%s ?, ?, ?, ?, ?, ? , ?, ?, ?, ?, ?, ?", TABLE_NAME);
    final String DELETE_SQL = String.format("DELETE FROM %s WHERE %s = ?", TABLE_NAME, "SKU");
    final String CHECK_HANG_TON_SQL = String.format("SELECT SKU, dbo.fn_soLuongTonSp(SKU) FROM SanPham");
    final String SELECT_BY_MODEL = String.format("SELECT * FROM %s WHERE id_DongSP = ? AND DoiXe = ? AND PhanKhoi = ?", "view_SanPhamTon");

    @Override
    public int insert(SanPham e) {
        return XJdbc.update(INSERT_SQL,
                e.getSku(), e.getTenSP(), e.getHinh(), e.getMauSac(), e.getPhanKhoi(), e.getDoiXe(), e.getThoiGianBH(),
                e.getDiaChiSX(), e.getGiaTien(), e.getMoTa(), e.getIdDongSP(), e.getIdNV());
    }

    @Override
    public int update(SanPham e) {
        return insert(e);
    }

    @Override
    public int delete(String id) {
        return XJdbc.update(DELETE_SQL, id);
    }

    @Override
    public List<SanPham> selectAll() {
        return selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public SanPham selectByID(String key) {
        List<SanPham> list = this.selectBySQL(SELECT_BY_ID_SQL, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
    int error = 0;

    @Override
    public List<SanPham> selectBySQL(String sql, Object... args) {
        List<SanPham> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.query(sql, args)) {
            while (rs.next()) {
                SanPham sp = new SanPham(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getString(8),
                        rs.getFloat(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12));
                sp.settonKho(rs.getInt(14));
                list.add(sp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhaSanXuatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<SanPham> getListSP(ModelSanPham model) {
        return selectBySQL(SELECT_BY_MODEL, model.getId_dongSP(), model.getDoiXe(), model.getPhanKhoi());
    }

    public Set<String> checkHangTonSP(String... skus) {
        Set<String> set = new HashSet<>();
        StringBuilder sb = new StringBuilder(CHECK_HANG_TON_SQL).append(" WHERE SKU IN (");
        for (int i = 0; i < skus.length; i++) {
            if (i == skus.length - 1) {
                sb.append(String.format("'%s'", skus[i])).append(")");
                break;
            }
            sb.append(String.format("'%s'", skus[i])).append(", ");
        }
        try (ResultSet rs = XJdbc.query(sb.toString())) {
            while (rs.next()) {
                if (rs.getInt(2) <= 0) {
                    set.add(rs.getString(1));
                }
            }
        } catch (SQLException ex) {
        }
        return set;
    }

    public Map<String, String> getMaVaTenSP() {
        Map<String, String> map = new HashMap<>();
        try (CachedRowSet rs = XJdbc.query("SELECT SKU, TenSP FROM SanPham WHERE isDeleted = 0")) {
            while (rs.next()) {
                map.put(rs.getString(1).toUpperCase(), rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietNhapKhoPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return map;
    }
}
