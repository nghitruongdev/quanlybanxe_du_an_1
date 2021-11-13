package com.edusys.components;

import com.edusys.util.MyConstants;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

/**
 *
 * @author nghipc
 */
public class MyTable extends JTable {

    boolean editable;

    public MyTable() {
        super();
        JTableHeader header = this.getTableHeader();
        header.setOpaque(false);
        header.setForeground(new java.awt.Color(255, 255, 255));
        header.setBackground(MyConstants.PURPLE_COLOR);

        ((javax.swing.table.DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(javax.swing.JLabel.CENTER);
        header.setFont(MyConstants.DEFAULT_FONT);
        this.setFont(MyConstants.DEFAULT_FONT);
        this.setSelectionBackground(new java.awt.Color(153, 153, 153));
        this.setGridColor(new java.awt.Color(204, 204, 204));
        this.setFillsViewportHeight(true);
        this.setRowHeight(24);
        this.setShowVerticalLines(false);
    }

    @Override
    public boolean isCellEditable(int i, int i1) {
        if (editable) {
            return super.isCellEditable(i, i1);
        }
        return false;
    }

    public int findIndexValue(String value, int atCol) {
        for (int i = 0; i < getRowCount(); i++) {
            if (value.equals(getValueAt(i, atCol))) {
                return i;
            }
        }
        return -1;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

}
