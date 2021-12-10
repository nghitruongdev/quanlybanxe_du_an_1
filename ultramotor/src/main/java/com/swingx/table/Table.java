package com.swingx.table;

import com.swingx.scrollbar.ScrollBarCustom;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.CellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class Table extends JTable {

    private List<Integer> columnEditable;

    public Table() {
        super();
        JTableHeader header = this.getTableHeader();
        header.setOpaque(false);
        header.setForeground(new java.awt.Color(255, 255, 255));
        header.setBackground(Color.DARK_GRAY);
        header.setBorder(new EmptyBorder(10, 5, 10, 5));
        header.setReorderingAllowed(false);
        ((javax.swing.table.DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(javax.swing.JLabel.CENTER);
        header.setFont(new Font("Segoe UI", 0, 14));
//        this.setFont(MyConstants.DEFAULT_FONT);
        this.setSelectionBackground(new java.awt.Color(153, 153, 153));
        this.setGridColor(new java.awt.Color(204, 204, 204));
        this.setFillsViewportHeight(true);
        this.setRowHeight(35);
        this.setShowHorizontalLines(true);
        this.setShowVerticalLines(false);
        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean selected, boolean focus, int i, int i1) {
                if (o instanceof Boolean) {
                    TableCellRenderer tcr = getDefaultRenderer(Boolean.class);
                    JCheckBox cell = (JCheckBox) tcr;
                    cell.setBorder(null);
                    if (selected) {
                        cell.setBackground(new Color(239, 244, 255));
                    } else {
                        cell.setBackground(Color.WHITE);
                    }
                    cell.setSelected((boolean) o);
                    return cell;
                } else if (o instanceof StatusCell) {
                    return ((StatusCell) o);
                } else if (o instanceof ModelAction) {
                    ModelAction data = (ModelAction) o;
                    ActionCell cell = new ActionCell(data);
                    if (selected) {
                        cell.setBackground(new Color(239, 244, 255));
                    } else {
                        cell.setBackground(Color.WHITE);
                    }
                    return cell;
                } else if (o instanceof ModelView) {
                    ModelView data = (ModelView) o;
                    ViewCell cell = new ViewCell(data);
                    if (selected) {
                        cell.setBackground(new Color(239, 244, 255));
                    } else {
                        cell.setBackground(Color.WHITE);
                    }
                    return cell;
                } else {
                    Component com = super.getTableCellRendererComponent(jtable, o, selected, focus, i, i1);
                    setBorder(noFocusBorder);
                    com.setForeground(new Color(102, 102, 102));
                    if (selected) {
                        com.setBackground(new Color(239, 244, 255));
                    } else {
                        com.setBackground(Color.WHITE);
                    }
                    return com;
                }
            }
        });
    }
    
    public void initTable(String[] cols, Integer... columnEditable) {
        DefaultTableModel model = new DefaultTableModel(cols, 0);
        setModel(model);
        setShowVerticalLines(true);
//        getColumnModel().getColumn(0).setMaxWidth(200);
//        getColumnModel().getColumn(getColumnCount() - 1).setMaxWidth(200);
        JScrollPane pane = (JScrollPane) getParent().getParent();
        fixTable(pane);
        pane.setBorder(null);
        addColumnEditable(columnEditable);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CellEditor editor = getCellEditor();
                if (editor != null) {
                    getCellEditor().stopCellEditing();
                }
            }
        });
    }
    
    public void addRowSorter(JTextField field) {
        DefaultTableModel model = (DefaultTableModel) this.getModel();
        TableRowSorter<TableModel> sorter = new TableRowSorter(model);
        this.setRowSorter(sorter);
        field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String text = field.getText().trim();
                if (text.isEmpty()) {
                    sorter.setRowFilter(null);
                } else {
                    List<RowFilter<Object, Object>> filters = new ArrayList<>();
                    filters.add(RowFilter.regexFilter("(?i)" + text));
                    filters.add(RowFilter.regexFilter("(?i)" + text.toUpperCase()));
                    sorter.setRowFilter(RowFilter.orFilter(filters));
                }
            }
        });
    }

    public void stopCellEditor() {
        CellEditor editor = this.getCellEditor();
        if (editor != null) {
            editor.stopCellEditing();
        }
    }

    public void fixTable(JScrollPane scroll) {
        scroll.getViewport().setBackground(Color.WHITE);
        scroll.setVerticalScrollBar(new ScrollBarCustom());
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        scroll.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        scroll.setBorder(new EmptyBorder(5, 10, 5, 10));
        scroll.setViewportView(this);
    }

    @Override
    public TableCellEditor getCellEditor(int row, int col) {
        Object o = this.getValueAt(row, col);
        if (o instanceof ModelAction) {
            return new ActionCellEditor();
        } else if (o instanceof Boolean) {
            return super.getDefaultEditor(Boolean.class);
        } else if (o instanceof ModelView) {
            return new ViewCellEditor();
        }
        return super.getCellEditor(row, col);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        Object o = this.getValueAt(row, column);
        if (o == null) {
            return false;
        }
        Class c = o.getClass();
        return c == Boolean.class || c == ModelAction.class || c == ModelView.class || (this.columnEditable != null && this.columnEditable.contains(column));
    }

    public List<Integer> getColumnEditable() {
        return columnEditable;
    }

    public void addColumnEditable(Integer... column) {
        if (this.columnEditable == null) {
            this.columnEditable = new ArrayList<>();
        }
        this.columnEditable.addAll(Arrays.asList(column));
    }

}

class ActionCellEditor extends DefaultCellEditor {

    private ModelAction data;

    public ActionCellEditor() {
        super(new JCheckBox());
    }

    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int row, int column) {
        data = (ModelAction) o;
        ActionCell cell = new ActionCell(data);
        cell.setBackground(new Color(239, 244, 255));
        return cell;
    }

    @Override
    public Object getCellEditorValue() {
        return data;
    }
}

class ViewCellEditor extends DefaultCellEditor {

    private ModelView data;

    public ViewCellEditor() {
        super(new JCheckBox());
    }

    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int row, int column) {
        data = (ModelView) o;
        ViewCell cell = new ViewCell(data);
        cell.setBackground(new Color(239, 244, 255));
        return cell;
    }

    @Override
    public Object getCellEditorValue() {
        return data;
    }
    
     
}
