package com.ultramotor.dao;

import com.ultramotor.entity.LoaiHang;
import com.ultramotor.entity.NhanVien;
import com.ultramotor.util.XJdbc;
import com.ultramotor.util.XJdbcServer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoaiHangDAO extends UltraDAO<LoaiHang, String> {

    
        final String  TABLE_NAME = "LoaiHang";
        final String SELECT_BY_ID_SQL = "SELECT * FROM LOAIHANG WHERE id_LH = ?";
        final String SELECT_ALL_SQL = "SELECT * FROM LOAIHANG";
        final String INSERT_SQL ="insert into LoaiHang(id_LH,TenLoaiHang) VALUES (?,?)";
        final String UPDATE_SQL ="UPDATE LOAIHANG SET TENLOAIHANG = ? WHERE ID_LH=?";
        final String DELETE_SQL ="DELETE FROM LOAIHANG WHERE ID_LH = ?";
    

    @Override
    public void insert(LoaiHang e) {
        XJdbcServer.update(INSERT_SQL,
                e.getIdLH(),e.getTenLoaiHang());
    }

    @Override
    public void update(LoaiHang e) {
        XJdbcServer.update(UPDATE_SQL, e.getTenLoaiHang(),e.getIdLH());
    }

    @Override
    public void delete(String id) {
        XJdbcServer.update(DELETE_SQL, id);
    }
    @Override
    public List<LoaiHang> selectAll() {
        return this.selectBySQL(SELECT_ALL_SQL);
    }
    @Override
    public LoaiHang selectByID(String id) {
        System.out.println(id);
        List<LoaiHang> list = this.selectBySQL(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }


    @Override
    public List<LoaiHang> selectBySQL(String sql, Object... args) {
        List<LoaiHang> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.query(sql, args)) {
            while (rs.next()) {
                list.add(new LoaiHang(
                        rs.getString(1),
                        rs.getString(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhaSanXuatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public List<LoaiHang> selectByKeyword(String keyWord){
        String sql= "SELECT * FROM LoaiHang WHERE TenLoaiHang like ? or id_LH like ? ";
        return this.selectBySQL(sql, "%"+keyWord+"%", "%"+keyWord+"%");
    }

}
