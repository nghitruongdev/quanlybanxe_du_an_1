/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ultramotor.util;

import com.microsoft.sqlserver.jdbc.SQLServerCallableStatement;
import com.microsoft.sqlserver.jdbc.SQLServerDataTable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nghipc
 */
public class XJdbcServer extends XJdbc {

    private static PreparedStatement getStmt(String sql, String[] types, Object... args) throws SQLException {
        if (types != null) {
            if (sql.startsWith("exec")) {
                SQLServerCallableStatement stmt = (SQLServerCallableStatement) getCon().prepareCall(sql);
                for (int i = 0; i < types.length; i++) {
                    if (args[i] instanceof SQLServerDataTable) {
                        stmt.setStructured(i + 1, types[i], (SQLServerDataTable) args[i]);
                    } else if (args[i] instanceof ResultSet) {
                        stmt.setStructured(i + 1, types[i], (ResultSet) args[i]);
                    }
                }
                return stmt;
            }
        }
        return XJdbc.getStmt(sql, args);
    }

    public static void update(String sql, String[] types, Object... args) {
        try {
            getStmt(sql, types, args).executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(XJdbcServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
