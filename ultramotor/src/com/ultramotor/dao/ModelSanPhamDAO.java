package com.ultramotor.dao;

import com.ultramotor.entity.DongSanPham;
import com.ultramotor.entity.ModelSanPham;
import com.ultramotor.util.XJdbc;
import com.ultramotor.util.XJdbcServer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModelSanPhamDAO extends UltraDAO<ModelSanPham, String> {

    final String TABLE_NAME = "ModelSanPham";
    final String SELECT_BY_ID_SQL = "SELECT * FROM ModelSanPham WHERE id_Model = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM ModelSanPham";
    final String INSERT_SQL = "insert into ModelSanPham(id_Model,tenModel,doiXe,id_DongSP) VALUES (?,?,?,?)";
    final String UPDATE_SQL = "UPDATE ModelSanPham SET tenModel = ?,doiXe= ?,id_DongSP= ?  WHERE id_Model=?";
    final String DELETE_SQL = "DELETE FROM ModelSanPham WHERE id_Model = ?";

    @Override
    public void insert(ModelSanPham e) {
         XJdbcServer.update(INSERT_SQL,
                e.getIdModel(), e.getTenModel(), e.getDoiXe(), e.getId_dongSP());
    }

    @Override
    public void update(ModelSanPham e) {
    XJdbcServer.update(UPDATE_SQL,
                e.getTenModel(), e.getDoiXe(), e.getId_dongSP(), e.getIdModel());
    }

    @Override
    public void delete(String id) {
        XJdbcServer.update(DELETE_SQL, id);        
    }
    @Override
    public List<ModelSanPham> selectAll() {
        return this.selectBySQL(SELECT_ALL_SQL);
    }
    @Override
    public ModelSanPham selectByID(String id) {
        System.out.println(id);
        List<ModelSanPham> list = this.selectBySQL(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<ModelSanPham> selectBySQL(String sql, Object... args) {
        List<ModelSanPham> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.query(sql, args)) {
            while (rs.next()) {
                list.add(new ModelSanPham(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhaSanXuatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<ModelSanPham> selectByKeyword(String keyWord) {
        String sql = "SELECT * FROM ModelSanPham WHERE id_Model like ? or tenModel like ? ";
        return this.selectBySQL(sql, "%" + keyWord + "%", "%" + keyWord + "%");
    }
}
