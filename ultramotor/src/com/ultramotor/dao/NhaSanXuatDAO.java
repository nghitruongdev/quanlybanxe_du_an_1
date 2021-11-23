package com.ultramotor.dao;

import com.ultramotor.entity.LoaiHang;
import com.ultramotor.entity.NhaSanXuat;
import com.ultramotor.entity.NhanVien;
import com.ultramotor.util.XJdbc;
import com.ultramotor.util.XJdbcServer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.CachedRowSet;

public class NhaSanXuatDAO extends UltraDAO<NhaSanXuat, String> {

    final String TABLE_NAME = "NhaSanXuat";
    final String SELECT_BY_ID_SQL = "SELECT * FROM NhaSanXuat where id_NSX =?  ";
    final String SELECT_ALL_SQL = "SELECT * FROM NhaSanXuat";
    final String INSERT_SQL = "insert into NhaSanXuat(id_NSX,TenNSX) VALUES (?,?)";
    final String UPDATE_SQL = "UPDATE NhaSanXuat SET tenNSX = ? WHERE ID_NSX=?";
    final String DELETE_SQL = "DELETE FROM NhaSanXuat WHERE ID_NSX = ?";
    final String Select_NSX = "select tenNSX from NhaSanXuat";

    @Override
    public void insert(NhaSanXuat e) {
        XJdbcServer.update(INSERT_SQL,
                e.getIdNSX(), e.getTenNSX());
    }

    @Override
    public void update(NhaSanXuat e) {
        XJdbcServer.update(UPDATE_SQL, e.getTenNSX(), e.getIdNSX());

    }

    @Override
    public void delete(String id) {
        XJdbcServer.update(DELETE_SQL, id);

    }

    @Override
    public List<NhaSanXuat> selectAll() {
        return this.selectBySQL(SELECT_ALL_SQL);
    }
    public NhaSanXuat selectByID(String id) {
        System.out.println(id);
        List<NhaSanXuat> list = this.selectBySQL(SELECT_BY_ID_SQL,id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }


    @Override
    public List<NhaSanXuat> selectBySQL(String sql, Object... args) {
        List<NhaSanXuat> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.query(sql, args)) {
            while (rs.next()) {
                list.add(new NhaSanXuat(
                        rs.getString(1),
                        rs.getString(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhaSanXuatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

//    @Override
    public CachedRowSet getRowSet() {
        return XJdbc.getRowSet(SELECT_ALL);
    }

//    public List<NhaSanXuat> selectByKeyword(String keyWord) {
//        String sql = "SELECT * FROM NhaSanXuat WHERE tenNSX like ? or ID_NSX like ? ";
//        return this.selectBySQL(sql, "%"+keyWord+"%","%"+keyWord+"%");
//    }
    public List<NhaSanXuat> selectByKeyword(String keyWord){
        String sql= "SELECT * FROM NhaSanXuat WHERE tenNSX like ? or ID_NSX like ? ";
        return this.selectBySQL(sql, "%"+keyWord+"%", "%"+keyWord+"%");
    }

}
