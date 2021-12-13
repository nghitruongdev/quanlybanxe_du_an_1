package com.ultramotor.ui.sanPham;

import com.swingx.table.ModelAction;
import com.swingx.table.ModelEvent;
import com.swingx.table.Table;
import com.ultramotor.dao.NhanVienDAO;
import com.ultramotor.dao.SanPhamDAO;
import com.ultramotor.entity.Entity;
import com.ultramotor.entity.NhanVien;
import com.ultramotor.entity.SanPham;
import com.ultramotor.util.MsgBox;
import com.ultramotor.util.UltraExporter;
import com.ultramotor.util.XDialog;
import com.ultramotor.util.XExcel;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.CellEditor;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;

public class QuanLySanPhamPanel extends javax.swing.JPanel {

    private SanPhamDAO daoSP;
    private List<NhanVien> listNV;
    private ModelEvent modelEvent;
    private DecimalFormat numberFormat;

    public QuanLySanPhamPanel() {
        initComponents();
        init();
    }

    void init() {
        daoSP = new SanPhamDAO();
        listNV = new NhanVienDAO().selectAll();
        numberFormat = new DecimalFormat("#,##0.00");
        addListeners();
        initTable();
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
        btnExport.addActionListener(e->export());
        addBtnAddListener(btnAddSP, tblSP, new SanPham());
        this.addAncestorListener(new AncestorListener() {
            @Override
            public void ancestorAdded(AncestorEvent event) {
                listNV = new NhanVienDAO().selectAll();
                 fillTable(tblSP, daoSP.selectAll());
                 
            }

            @Override
            public void ancestorRemoved(AncestorEvent event) {
            }

            @Override
            public void ancestorMoved(AncestorEvent event) {
            }
        });
    }

    private void addBtnAddListener(JButton btn, Table table, SanPham e) {
        btn.addActionListener(event -> {
            table.clearSelection();
            showPanel(e);
        });
    }

    private void initTable() {
        String[] colsSP = {"Mã SKU", "Tên Sản Phẩm", "Màu Sắc", "Phân Khối", "Thời Gian BH", "Địa chỉ SX", "Giá Tiền", "Đời Xe", "Tồn Kho", "Dòng Sản Phẩm", "Nhân Viên", "Actions"};
        tblSP.initTable(colsSP);
        tblSP.addRowSorter(txtSearchSP);
        tblSP.getColumnModel().getColumn(tblSP.getColumnCount() - 1).setMaxWidth(100);
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
        if (daoSP.delete(((SanPham) e).getSku()) > 0) {
            deleteRow(tblSP);
            MsgBox.inform("Xoá thành công");
        } else {
            MsgBox.error("Xoá thất bại");
        }
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
            SanPham sp = (SanPham) e;
            return new Object[]{
                sp,
                sp.getTenSP(),
                sp.getMauSac(),
                sp.getPhanKhoi(),
                sp.getThoiGianBH() + " tháng",
                sp.getDiaChiSX(),
                numberFormat.format(sp.getGiaTien()),
                sp.getDoiXe(),
                sp.gettonKho(),
                sp.getIdDongSP(),
                findNhanVien(sp.getIdNV()).getHoTenNV(),
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

    private NhanVien findNhanVien(String idNV) {
        return listNV.stream().filter(nv -> nv.getIdNV().equalsIgnoreCase(idNV.trim())).findFirst().orElse(new NhanVien());
    }

    private void export(){
        File excel = XExcel.showSaveDialog((JFrame) this.getTopLevelAncestor(), "DanhSachSanPhamTonKho.xlsx");
        if(excel==null){
            return;
        }
        UltraExporter.exportSanPham(excel);
        if(MsgBox.confirm("Xuất danh sách thành công. Bạn có muốn mở file?", false)==0){
            try {
                Desktop.getDesktop().open(excel);
            } catch (IOException ex) {
            }
        }
        
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        btnAddSP = new com.swingx.Button();
        txtSearchSP = new com.swingx.SearchTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblSP = new com.swingx.table.Table();
        jLabel1 = new javax.swing.JLabel();
        btnExport = new com.swingx.Button();

        setBackground(new java.awt.Color(250, 250, 250));
        setOpaque(false);

        pnlMain.setOpaque(false);

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

        btnExport.setBackground(new java.awt.Color(0, 174, 114));
        btnExport.setForeground(new java.awt.Color(255, 255, 255));
        btnExport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ultramotor/icon/print_25px.png"))); // NOI18N
        btnExport.setText("Export");
        btnExport.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(20, 20, 20)
                        .addComponent(btnAddSP, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExport, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 254, Short.MAX_VALUE)
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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSearchSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAddSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnExport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
    private com.swingx.Button btnExport;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel pnlMain;
    private com.swingx.table.Table tblSP;
    private com.swingx.SearchTextField txtSearchSP;
    // End of variables declaration//GEN-END:variables

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            JFrame fr = new JFrame();
//            fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            fr.getContentPane().add(new QuanLySanPhamPanel());
//            fr.pack();
//            fr.setVisible(true);
//        });
//    }
}
