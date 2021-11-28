package com.ultramotor.dao;

import com.ultramotor.entity.DongSanPham;
import com.ultramotor.entity.LoaiHang;
import com.ultramotor.entity.ModelSanPham;
import com.ultramotor.entity.NhaSanXuat;
import com.ultramotor.util.SearchFilter;
import com.ultramotor.util.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.FilteredRowSet;

public class ModelSanPhamDAO {

    private SanPhamDAO daoSP = new SanPhamDAO();
    private FilteredRowSet frs;

    public ModelSanPhamDAO() {
        initRowSet();
    }

    public List<ModelSanPham> selectAll() {
//        return getModelByNSXvaLoaiHang(new NhaSanXuat(null), new LoaiHang(null));
        return selectByResultSet(frs);
    }
//

//    public List<ModelSanPham> getModelByNSXvaLoaiHang(NhaSanXuat nsx, LoaiHang lh) {
//        removeFilter(frs);
//        String tenNSX = "";
//        String tenLH = "";
//        if (nsx != null && nsx.getIdNSX() != null) {
//            setFilter(frs, nsx.getTenNSX(), "TenNSX");
//            printRs(frs);
//        }
//        FilteredRowSet another = null;
//        if (lh != null && lh.getIdLH() != null) {
//            try {
//                another = XJdbc.getFactory().createFilteredRowSet();
//                another.populate(frs.createCopy());
//                setFilter(another, lh.getTenLoaiHang(), "TenLoaiHang");
//                System.out.println("Set filter LoaiHang");
//                printRs(another);
//                return selectByResultSet(another);
//            } catch (SQLException ex) {
//                Logger.getLogger(ModelSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//        }
//        return selectByResultSet(frs);
//    }

//    public List<ModelSanPham> getModelByDongSP(String idDong) {
//        removeFilter();
//        setFilter(idDong, "id_DongSP");
//        return selectByResultSet(frs);
//    }
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

    private void initRowSet() {
        try (ResultSet rs = XJdbc.query("exec usp_select_modelSP ?", "%%")) {
            frs = XJdbc.getFactory().createFilteredRowSet();
            frs.populate(rs);
        } catch (SQLException ex) {
            Logger.getLogger(ModelSanPhamDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setFilter(FilteredRowSet rs, Object value, String columnName) {
        try {
            rs.setFilter(new SearchFilter(value, columnName));
        } catch (SQLException ex) {
            Logger.getLogger(ModelSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void removeFilter(FilteredRowSet rs) {
        try {
            rs.setFilter(null);
        } catch (SQLException ex) {
            Logger.getLogger(ModelSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void printRs(ResultSet rs) {
        try {
            rs.beforeFirst();
            System.out.println("---------Print result set------------");
            while (rs.next()) {
                System.out.println("Tên NSX: " + rs.getObject("TenNSX"));
                System.out.println("Tên Loại Hàng: " + rs.getObject("TenLoaiHang"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModelSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
