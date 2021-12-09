package com.ultramotor.ui.sanPham;

import com.swingx.table.ModelAction;
import com.swingx.table.ModelEvent;
import com.swingx.table.Table;
import com.ultramotor.dao.SanPhamDAO;
import com.ultramotor.entity.Entity;
import com.ultramotor.entity.SanPham;
import com.ultramotor.util.MsgBox;
import com.ultramotor.util.XDialog;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.CellEditor;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class QuanLySanPhamPanel extends javax.swing.JPanel {

    private SanPhamDAO daoSP;

    private ModelEvent modelEvent;

    public QuanLySanPhamPanel() {
        initComponents();
        init();
    }

    void init() {
        daoSP = new SanPhamDAO();
        addListeners();
        initTable();
        fillTable(tblSP, daoSP.selectAll());

    }

    private void addListeners() {
        modelEvent = new ModelEvent() {
            @Override
            public void update(Object e) {
                showPanel((SanPham) e);
            }

            @Override
            public void delete(Object e) {
                deleteRow((Entity) e);
            }

        };

        addBtnAddListener(btnAddSP, tblSP, new SanPham());

        btnRefresh.addActionListener(event -> {
//            refresh(true);
        });
    }

    private void addBtnAddListener(JButton btn, Table table, SanPham e) {
        btn.addActionListener(event -> {
            table.clearSelection();
            showPanel(e);
        });
    }

    private void initTable() {
        String[] colsSP = {"Mã SKU", "Tên Sản Phẩm", "Màu Sắc", "Phân Khối", "Thời Gian BH", "Địa chỉ SX", "Giá Tiền", "Đời Xe", "Hình", "id_DongSP", "id_NV", "Actions"};
        initTable(colsSP, tblSP);
        addRowSorter(tblSP, txtSearchSP);
    }

    private void initTable(String[] cols, Table table, Integer... columnEditable) {
        DefaultTableModel model = new DefaultTableModel(cols, 0);
        table.setModel(model);
        table.setShowVerticalLines(true);
        table.getColumnModel().getColumn(0).setMaxWidth(200);
        table.getColumnModel().getColumn(table.getColumnCount() - 1).setMaxWidth(200);
        JScrollPane pane = (JScrollPane) table.getParent().getParent();
        table.fixTable(pane);

        pane.setBorder(null);
        table.addColumnEditable(columnEditable);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CellEditor editor = table.getCellEditor();
                if (editor != null) {
                    table.getCellEditor().stopCellEditing();
                }
            }
        });
    }

    private void addRowSorter(Table table, JTextField field) {
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);
        field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String text = field.getText().trim();
                if (text.isEmpty()) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
        });
    }

    private void fillTable(Table table, List<? extends Entity> list) {
        if (list == null) {
            return;
        }
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        list.forEach(e -> {
            model.addRow(getInfo(e));
        });
    }

    private boolean insert(SanPham sp) {
        if (daoSP.insert(sp) > 0) {
            insertRow(tblSP, sp);
            return true;
        }
        return false;
    }

    private void insertRow(Table table, Entity e) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int index = table.getSelectedRow();
        if (index >= 0) {
            model.removeRow(index);
            model.insertRow(index, getInfo(e));
            MsgBox.inform("Cập nhật thành công");
        } else {
            model.addRow(getInfo(e));
            MsgBox.inform("Thêm mới thành công");
        }
    }

    private void deleteRow(Entity e) {
        int confirm = MsgBox.confirm("Bạn có thực sự muốn xoá?", false);
        if (confirm != 0) {
            return;
        }
        switch (e.getClass().getSimpleName()) {
            case "SanPham":
                daoSP.delete(((SanPham) e).getSku());
                deleteRow(tblSP);
                break;
        }
        MsgBox.inform("Xoá thành công");
    }

    private void deleteRow(Table table) {
        CellEditor editor = table.getCellEditor();
        if (editor != null) {
            table.getCellEditor().stopCellEditing();
        }
        int index = table.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        if (index >= 0) {
            model.removeRow(index);
        }
    }

    private void showPanel(SanPham e) {
        SanPhamPanel panel = new SanPhamPanel();
        panel.setDoneListener(event -> {
            SanPham sp = panel.getForm();
            if (sp == null) {
                return;
            }
            if (insert(sp)) {
                int index = getIndexSP(sp);
                index = index == -1 ? 0 : index;
                tblSP.setRowSelectionInterval(index, index); //đật hàng chọn trên bảng
                tblSP.scrollRectToVisible(new java.awt.Rectangle(tblSP.getCellRect(index, 0, true))); //di chuyển thanh lăn tới vị trí hàng chọn
                ((JDialog) panel.getTopLevelAncestor()).dispose();
            }
        });
        panel.setSanPham(e);
        XDialog.getDialog((JFrame) this.getTopLevelAncestor(), panel).setVisible(true);
    }

    private Object[] getInfo(Entity e) {
        if (e instanceof SanPham) {
            DecimalFormat format = new DecimalFormat("#,##0.00 VND");
            SanPham sp = (SanPham) e;
            return new Object[]{
                sp,
                sp.getTenSP(),
                sp.getMauSac(),
                sp.getPhanKhoi(),
                sp.getThoiGianBH() + " tháng",
                sp.getDiaChiSX(),
                format.format(sp.getGiaTien()),
                sp.getDoiXe(),
                sp.getHinh(),
                sp.getIdDongSP(),
                sp.getIdNV(),
                new ModelAction(sp, modelEvent)
            };
        }
        return null;
    }

    private int getIndexSP(SanPham sp) {
        for (int i = 0; i < tblSP.getRowCount(); i++) {
            if (tblSP.getValueAt(i, 0).equals(sp)) {
                return i;
            }
        }
        return -1;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnRefresh = new com.swingx.Button();
        pnlMain = new javax.swing.JPanel();
        btnAddSP = new com.swingx.Button();
        txtSearchSP = new com.swingx.SearchTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblSP = new com.swingx.table.Table();
        jLabel1 = new javax.swing.JLabel();
        btnThemMoi = new com.swingx.Button();

        btnRefresh.setBackground(new java.awt.Color(51, 204, 0));
        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ultramotor/icon/refresh_25px.png"))); // NOI18N

        btnAddSP.setBackground(new java.awt.Color(0, 174, 114));
        btnAddSP.setForeground(new java.awt.Color(255, 255, 255));
        btnAddSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ultramotor/icon/icons8-save.png"))); // NOI18N
        btnAddSP.setText("Thêm mới");
        btnAddSP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        tblSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "SKU", "Tên SP", "Màu Sắc", "Phân Khối", "Thời gian BH", "Địa chỉ SX", "Giá Tiền", "Mô tả", "Đời xe", "Hình", "Id_DSP", "Id_NV"
            }
        ));
        jScrollPane4.setViewportView(tblSP);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 174, 114));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("QUẢN LÝ SẢN PHẨM");

        btnThemMoi.setBackground(new java.awt.Color(0, 174, 114));
        btnThemMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnThemMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ultramotor/icon/icons8-save.png"))); // NOI18N
        btnThemMoi.setText("Thêm mới");
        btnThemMoi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAddSP, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 382, Short.MAX_VALUE)
                        .addComponent(txtSearchSP, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4))
                .addContainerGap())
            .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlMainLayout.createSequentialGroup()
                    .addGap(463, 463, 463)
                    .addComponent(btnThemMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(464, Short.MAX_VALUE)))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSearchSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAddSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlMainLayout.createSequentialGroup()
                    .addGap(314, 314, 314)
                    .addComponent(btnThemMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(315, 315, 315)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.swingx.Button btnAddSP;
    private com.swingx.Button btnRefresh;
    private com.swingx.Button btnThemMoi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel pnlMain;
    private com.swingx.table.Table tblSP;
    private com.swingx.SearchTextField txtSearchSP;
    // End of variables declaration//GEN-END:variables

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame fr = new JFrame();
            fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            fr.getContentPane().add(new QuanLySanPhamPanel());
            fr.pack();
            fr.setVisible(true);
        });
    }
}
