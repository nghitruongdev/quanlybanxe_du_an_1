package com.ultramotor.dao;

import com.ultramotor.entity.DongSanPham;
import com.ultramotor.entity.LoaiHang;
import com.ultramotor.entity.ModelSanPham;
import com.ultramotor.entity.NhaSanXuat;
import com.ultramotor.util.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.FilteredRowSet;

public class ModelSanPhamDAO {

    private DongSanPhamDAO daoDongSP = new DongSanPhamDAO();
    private SanPhamDAO daoSP = new SanPhamDAO();
    private FilteredRowSet frs;

    public List<ModelSanPham> selectAll() {
        return getModelByNSXvaLoaiHang(new NhaSanXuat(null), new LoaiHang(null));
    }

    public List<ModelSanPham> getModelByNSXvaLoaiHang(NhaSanXuat nsx, LoaiHang lh) {
        List<ModelSanPham> list = new ArrayList<>();
        List<DongSanPham> listDong = daoDongSP.getDongSPByNSXvaLoaiHang(nsx, lh);
        listDong.forEach(dong -> {
            list.addAll(getModelByDongSP(dong.getIdDongSP()));
        });
        return list;
    }

    public List<ModelSanPham> getModelByDongSP(String idDong) {
        String sql = "exec usp_select_modelSP ?";
        List<ModelSanPham> modelList = selectBySQL(sql, idDong);
        return modelList;
    }

    private List<ModelSanPham> selectBySQL(String sql, Object... args) {
        List<ModelSanPham> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.query(sql, args)) {
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
            Logger.getLogger(ModelSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    private void initRowSet() {
        try (ResultSet rs = XJdbc.query("exec usp_select_modelSP ?", "%%")) {
            frs = XJdbc.getFactory().createFilteredRowSet();
            frs.populate(rs);
        } catch (SQLException ex) {
            Logger.getLogger(ModelSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
