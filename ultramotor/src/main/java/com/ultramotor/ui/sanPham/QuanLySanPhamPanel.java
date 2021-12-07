package com.ultramotor.ui.sanPham;

import com.swingx.Button;
import com.swingx.ComboBoxSuggestion;
import com.swingx.TextField;
import com.swingx.table.ModelAction;
import com.swingx.table.ModelEvent;
import com.swingx.table.Table;
import com.ultramotor.dao.DongSanPhamDAO;
import com.ultramotor.dao.LoaiHangDAO;
import com.ultramotor.dao.NhaSanXuatDAO;
import com.ultramotor.dao.SanPhamDAO;
import com.ultramotor.entity.DongSanPham;
import com.ultramotor.entity.Entity;
import com.ultramotor.entity.LoaiHang;
import com.ultramotor.entity.NhaSanXuat;
import com.ultramotor.entity.SanPham;
import com.ultramotor.util.MsgBox;
import com.ultramotor.util.XDialog;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.CellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.miginfocom.swing.MigLayout;

public class QuanLySanPhamPanel extends javax.swing.JPanel {

    private LoaiHangDAO daoLH;
    private DongSanPhamDAO daoDongSP;
    private NhaSanXuatDAO daoNSX;
    private SanPhamDAO daoSP;

    private List<LoaiHang> listLH;
    private List<NhaSanXuat> listNSX;
    private List<DongSanPham> listDongSP;
    private List<SanPham> listSP;

    private ModelEvent modelEvent;
    private MyPanel panel = null;

    public QuanLySanPhamPanel() {
        initComponents();
        init();
    }

    void init() {
        daoLH = new LoaiHangDAO();
        daoDongSP = new DongSanPhamDAO();
        daoNSX = new NhaSanXuatDAO();
        daoSP = new SanPhamDAO();

        initTable();
        addListeners();
        refresh(false);

    }

    private void addListeners() {
        modelEvent = new ModelEvent() {
            @Override
            public void update(Object e) {
                showPanel((Entity) e);
            }

            @Override
            public void delete(Object e) {
                deleteRow((Entity) e);
            }

        };
//        addBtnAddListener(btnAddLH, tblLoaiHang, new LoaiHang());
//        addBtnAddListener(btnAddNSX, tblNSX, new NhaSanXuat());
//        addBtnAddListener(btnAddDongSP, tblDongSP, new DongSanPham());
        addBtnAddListener(btnAddSP, tblSP, new SanPham());

        btnRefresh.addActionListener(event -> {
            refresh(true);
        });
    }

    private void addBtnAddListener(JButton btn, Table table, Entity e) {
        btn.addActionListener(event -> {
            table.clearSelection();
            showPanel(e);
        });
    }

    private void initTable() {
//        String[] colsLH = {"id_LH", "Tên Loại Hàng", "Actions"};
//        String[] colsNSX = {"id_NSX", "Tên Nhà Sản Xuất", "Actions"};
//        String[] colsDongSP = {"id_DongSP", "Tên Dòng Sản Phẩm", "Tên NSX", "Tên Loại Hàng", "Actions"};
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

    private void refresh(boolean notify) {
        listLH = daoLH.selectAll();
        listNSX = daoNSX.selectAll();
        listDongSP = daoDongSP.selectAll();
        listSP = daoSP.selectAll();
        fillTable(tblSP, listSP);
        txtSearchSP.setText("");
        if (notify) {
            MsgBox.inform("Đã cập nhật lại toàn bộ dữ liệu");
        }
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
            case "LoaiHang":
                daoLH.delete(((LoaiHang) e).getIdLH());
//                deleteRow(tblLoaiHang);
                listLH.remove((LoaiHang) e);
                break;
            case "NhaSanXuat":
                daoNSX.delete(((NhaSanXuat) e).getIdNSX());
//                deleteRow(tblNSX);
                listNSX.remove((NhaSanXuat) e);
                break;
            case "DongSanPham":
                daoDongSP.delete(((DongSanPham) e).getIdDongSP());
//                deleteRow(tblDongSP);
                listDongSP.remove((DongSanPham) e);
                break;
            case "SanPham":
                daoSP.delete(((SanPham) e).getSku());
                deleteRow(tblSP);
                listSP.remove((SanPham) e);
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

    private void showPanel(Entity e) {
        switch (e.getClass().getSimpleName()) {
            case "LoaiHang":
                panel = new PanelLoaiHang(listLH);
                panel.setDoneListener(event -> {
                    Entity entity = (Entity) panel.getForm();
                    if (entity != null) {
                        int count = daoLH.insert((LoaiHang) entity);
                        if (count < 0) {
                            MsgBox.error("Cập nhật dữ liệu thất bại");
                            return;
                        }
//                        insertRow(tblLoaiHang, entity);
                        listLH.add((LoaiHang) e);
                        ((JDialog) panel.getTopLevelAncestor()).dispose();
                    }
                });
                break;
            case "NhaSanXuat":
                panel = new PanelNhaSanXuat(listNSX);
                panel.setDoneListener(event -> {
                    Entity entity = (Entity) panel.getForm();
                    if (entity != null) {
                        int count = daoNSX.insert((NhaSanXuat) entity);
                        if (count < 0) {
                            MsgBox.error("Cập nhật dữ liệu thất bại");
                            return;
                        }
//                        insertRow(tblNSX, entity);
                        listNSX.add((NhaSanXuat) e);
                        ((JDialog) panel.getTopLevelAncestor()).dispose();
                    }
                });
                break;
            case "DongSanPham":
                panel = new PanelDongSanPham(listDongSP, listNSX, listLH);
                panel.setDoneListener(event -> {
                    Entity entity = (Entity) panel.getForm();
                    if (entity != null) {
                        int count = daoDongSP.insert((DongSanPham) entity);
                        if (count < 0) {
                            MsgBox.error("Cập nhật dữ liệu thất bại");
                            return;
                        }
//                        insertRow(tblDongSP, entity);
                        ((JDialog) panel.getTopLevelAncestor()).dispose();
                    }
                });
                break;
            case "SanPham":
                panel = new SanPhamPanel(listSP,listNSX, listDongSP);
                panel.setSize(800, getHeight());
                break;
        }
        if (panel != null) {
            panel.setEntity(e);
            XDialog.getDialog((JFrame) this.getTopLevelAncestor(), panel).setVisible(true);
        }
    }

    private Object[] getInfo(Entity e) {
        if (e instanceof SanPham) {
            DecimalFormat format = new DecimalFormat("#,##0.00 VND");
            SanPham sp = (SanPham) e;
            return new Object[]{
                sp.getSku(),
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

    private NhaSanXuat findNSX(String idNSX) {
        return listNSX.stream()
                .filter(nsx -> nsx.getIdNSX().equals(idNSX))
                .findFirst().orElse(null);
    }

    private LoaiHang findLH(String idLH) {
        return listLH.stream()
                .filter(lh -> lh.getIdLH().equals(idLH))
                .findFirst().orElse(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnRefresh = new com.swingx.Button();
        pnlMain = new javax.swing.JPanel();
        txtSearchSP = new com.swingx.SearchTextField();
        btnAddSP = new com.swingx.Button();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblSP = new com.swingx.table.Table();
        jLabel1 = new javax.swing.JLabel();

        btnRefresh.setBackground(new java.awt.Color(51, 204, 0));
        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ultramotor/icon/refresh_25px.png"))); // NOI18N

        btnAddSP.setForeground(new java.awt.Color(255, 255, 255));
        btnAddSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ultramotor/icon/edit.png"))); // NOI18N

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

        jLabel1.setFont(new java.awt.Font("Serif", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 174, 114));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Quản Lý Sản Phẩm");

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(btnAddSP, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 501, Short.MAX_VALUE)
                        .addComponent(txtSearchSP, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4))
                .addContainerGap())
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddSP, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearchSP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE)
                .addContainerGap())
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

//  if (e instanceof LoaiHang) {
//            LoaiHang lh = (LoaiHang) e;
//            return new Object[]{
//                lh.getIdLH(),
//                lh.getTenLoaiHang(),
//                new ModelAction(lh, modelEvent)
//            };
//        }
//        if (e instanceof NhaSanXuat) {
//            NhaSanXuat nsx = (NhaSanXuat) e;
//            return new Object[]{
//                nsx.getIdNSX(),
//                nsx.getTenNSX(),
//                new ModelAction(nsx, modelEvent)
//            };
//        }
//        if (e instanceof DongSanPham) {
//            DongSanPham dsp = (DongSanPham) e;
//            NhaSanXuat nsx = findNSX(dsp.getIdNSX());
//            LoaiHang lh = findLH(dsp.getIdLH());
//            return new Object[]{
//                dsp.getIdDongSP(),
//                dsp.getTenDongSP(),
//                nsx == null ? "" : nsx.getTenNSX(),
//                lh == null ? "" : lh.getTenLoaiHang(),
//                new ModelAction(dsp, modelEvent)
//            };
//        }

