package com.ultramotor.util;

import com.microsoft.sqlserver.jdbc.SQLServerCallableStatement;
import com.microsoft.sqlserver.jdbc.SQLServerDataTable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class XJdbcServer extends XJdbc {

    private static PreparedStatement getStmt(Connection con, String sql, String[] types, Object... args) throws SQLException {
        SQLServerCallableStatement stmt = (SQLServerCallableStatement) con.prepareCall(sql);
        if (types != null) {
            if (sql.startsWith("exec")) {
                for (int i = 0; i < types.length; i++) {
                    if (types[i] != null) {
                        if (args[i] instanceof SQLServerDataTable) {
                            stmt.setStructured(i + 1, types[i], (SQLServerDataTable) args[i]);
                        } else if (args[i] instanceof ResultSet) {
                            stmt.setStructured(i + 1, types[i], (ResultSet) args[i]);
                        }
                    } else {
                        stmt.setObject(i + 1, args[i]);
                    }
                }
                return stmt;
            }
        }
        return XJdbc.getStmt(con, sql, args);
    }

    public static int update(String sql, String[] types, Object... args) throws SQLException {
        try (Connection con = getCon()) {
            try (PreparedStatement stmt = getStmt(con, sql, types, args)) {
                return stmt.executeUpdate();
            }
        }
    }
}