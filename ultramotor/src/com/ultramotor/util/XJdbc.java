package com.ultramotor.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

    private static RowSetFactory factory;
    private static final String URL = "jdbc:sqlserver://localhost;databaseName=HONDA;username=sa;password=songlong";

    static {
        try {
            factory = RowSetProvider.newFactory();
        } catch (SQLException ex) {
            Logger.getLogger(XJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected static PreparedStatement getStmt(Connection con, String sql, Object... args) throws SQLException {
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

    public static CachedRowSet query(String sql, Object... args) throws SQLException {
        CachedRowSet cRs = factory.createCachedRowSet();
        try (Connection con = getCon()) {
            try (PreparedStatement pstmt = getStmt(con, sql, args)) {
                try (ResultSet rs = pstmt.executeQuery()) {
                    cRs.populate(rs);
                }
            }
        }
        return cRs;
    }

    public static Object value(String sql, Object... args) {
        List<Object> list = valueList(sql, args);
        return list.isEmpty() ? null : list.get(0);
    }

    public static List<Object> valueList(String sql, Object... args) {
        List<Object> list = new ArrayList<>();
        try (ResultSet rs = query(sql, args)) {
            while (rs.next()) {
                list.add(rs.getObject(1));
            }
        } catch (SQLException ex) {

        }
        return list;
    }

    public static int update(String sql, Object... args) throws SQLException {
        try (Connection con = getCon()) {
            try (PreparedStatement pstmt = getStmt(con, sql, args)) {
                return pstmt.executeUpdate();
            }
        }
    }

    public static Connection getCon() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static RowSetFactory getFactory() {
        return factory;
    }

}
