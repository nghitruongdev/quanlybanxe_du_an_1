package com.ultramotor.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

/**
 *
 * @author nghipc
 */
public class XJdbc {

    private static Connection con;
    private static RowSetFactory factory;
    private static final String URL = "jdbc:sqlserver://localhost;databaseName=HONDA;username=sa;password=songlong";

    static {
        try {
            factory = RowSetProvider.newFactory();
        } catch (SQLException ex) {
            Logger.getLogger(XJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected static PreparedStatement getStmt(String sql, Object... args) throws SQLException {
        con = getCon();
        PreparedStatement pstmt;
        if (sql.trim().startsWith("{")) {
            pstmt = con.prepareCall(sql);
        } else {
            pstmt = con.prepareStatement(sql);
        }
        for (int i = 0; i < args.length; i++) {
            pstmt.setObject(i + 1, args[i]);
        }
        return pstmt;
    }

    public static ResultSet query(String sql, Object... args) {
         ResultSet rs = null;
        try {
            PreparedStatement pstmt = getStmt(sql, args);
            rs = pstmt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(XJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public static Object value(String sql, Object... args) {
        Object value = null;
        try (ResultSet rs = query(sql, args)) {
            if (rs.next()) {
                value = rs.getObject(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeCon();
        }
        return value;
    }

    public static CachedRowSet getRowSet(String sql, Object... args) {
        CachedRowSet crs = null;
        try (ResultSet rs = query(sql, args)) {
            crs = factory.createCachedRowSet();
            crs.populate(rs);
        } catch (SQLException ex) {
            Logger.getLogger(XJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return crs;
    }

    public static int update(String sql, Object... args) {
        int count = 0;
        try (PreparedStatement pstmt = getStmt(sql, args)) {
            count = pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeCon();
        }
        return count;
    }

    public static Connection getCon() {
        try {
            if (con == null || con.isClosed()) {
                con = DriverManager.getConnection(URL);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return con;
    }

    public static void closeCon() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static RowSetFactory getFactory() {
        return factory;
    }

}
