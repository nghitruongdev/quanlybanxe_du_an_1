/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ultramotor.dao;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author nghipc
 */
public abstract class UltraDAO<Entity, ID> {

    public abstract void insert(Entity e);

    public abstract void update(Entity e);

    public abstract void delete(ID id);

    public abstract List<Entity> selectBySQL(String sql, Object... args);

    public Entity selectByID(ID id) {
        List<Entity> list = selectBySQL(SELECT_BY_ID, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public List<Entity> selectAll() {
        return selectBySQL(SELECT_ALL);
    }

    public ResultSetMetaData meta;
    protected String TABLE_NAME;
    protected String SELECT_BY_ID;
    protected String SELECT_ALL;

//    public abstract CachedRowSet getRowSet();
//    protected void init(){
//        try {
//            meta = getRowSet().getMetaData();
//            System.out.println(meta.getTableName(1));
//            int count = meta.getColumnCount();
//            for (int i = 1; i <= count; i++) {
//                System.out.println("Label: " + meta.getColumnLabel(i));
//                System.out.println("Name: " + meta.getColumnName(i));
//                System.out.println("Class Name: " + meta.getColumnClassName(i));
//                System.out.println("Column Type Name: " + meta.getColumnTypeName(i));
//                System.out.println("Column Type: " + meta.getColumnType(i));
//                System.out.println("=========================");
//
//            }
//            System.out.println(meta.getColumnCount());
//        } catch (SQLException ex) {
//            Logger.getLogger(UltraDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    public ResultSetMetaData getMetaData() throws SQLException {
//        if (meta == null) {
//            try (CachedRowSet rs = getRowSet()) {
//                meta = rs.getMetaData();
//            }
//        }
//        return meta;
//    }
}
