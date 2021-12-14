
package com.ultramotor.util;

import java.sql.SQLException;
import java.util.Objects;
import javax.sql.RowSet;
import javax.sql.rowset.Predicate;

public class SearchFilter implements Predicate {

    private Object value;
    private String columnName = null;
    private int column = -1;

    public SearchFilter(Object value, String columnName) {
        this.value = value;
        this.columnName = columnName;
    }

    public SearchFilter(Object value, int columnIndex) {
        this.value = value;
        this.column = columnIndex;
    }

    @Override
    public boolean evaluate(Object value, int column) throws SQLException {
        if (column == this.column) {
            if (this.value instanceof String && value instanceof String) {
                return ((String) this.value).equalsIgnoreCase(String.valueOf(value));
            } else {
                return Objects.equals(this.value, value);
            }
        }
        return false;
    }

    @Override
    public boolean evaluate(Object value, String columnName) throws SQLException {
        if (columnName.equalsIgnoreCase(this.columnName)) {
            if (this.value instanceof String) {
                return ((String) this.value).equalsIgnoreCase(String.valueOf(value));
            } else {
                return Objects.equals(this.value, value);
            }
        }
        return false;
    }

    @Override
    public boolean evaluate(RowSet rs) {
        if (rs == null) {
            return false;
        }
        Object val = null;
        try {
            if (this.column > 0) {
                val = rs.getObject(column);
            } else if (this.columnName != null) {
                val = rs.getObject(columnName);
            }
            if (this.value instanceof String) {
                return ((String) this.value).equalsIgnoreCase(String.valueOf(val));
            } else {
                return Objects.equals(this.value, val);
            }
        } catch (SQLException e) {
        }
        return false;

    }

}