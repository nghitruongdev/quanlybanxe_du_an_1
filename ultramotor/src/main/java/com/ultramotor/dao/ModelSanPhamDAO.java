package com.ultramotor.dao;

import com.ultramotor.entity.ModelSanPham;
import com.ultramotor.util.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModelSanPhamDAO {

    private final SanPhamDAO daoSP = new SanPhamDAO();

    public List<ModelSanPham> selectAll() {
        return selectBySQL("exec usp_select_modelSP ?", "%%");
    }

    private List<ModelSanPham> selectBySQL(String sql, Object... args) {
        List<ModelSanPham> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.query(sql, args)) {
            list = selectByResultSet(rs);
        } catch (SQLException ex) {
            Logger.getLogger(ModelSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    private List<ModelSanPham> selectByResultSet(ResultSet rs) {
        List<ModelSanPham> list = new ArrayList<>();
        try {
            rs.beforeFirst();
            while (rs.next()) {
                ModelSanPham model = new ModelSanPham(
                        rs.getString("id_DongSP"),
                        rs.getString("TenDongSP"),
                        rs.getString("phanKhoi"),
                        rs.getString("TenLoaiHang"),
                        rs.getString("TenNSX"),
                        rs.getString("DiaChiSX"),
                        rs.getInt("DoiXe"),
                        rs.getInt("ThoiGianBH"),
                        rs.getDouble("GiaTien"),
                        rs.getInt("SoLuongBan"));
                model.setSanPhamList(daoSP.getListSP(model));
                list.add(model);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModelSanPhamDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
