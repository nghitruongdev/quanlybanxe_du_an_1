package com.ultramotor.ui.nhanvien.kho.nhapkho;

import com.swingx.table.ModelAction;
import com.swingx.table.ModelEvent;
import com.ultramotor.dao.SanPhamDAO;
import com.ultramotor.entity.ChiTietNhapKho;
import com.ultramotor.entity.PhieuNhapKho;
import com.ultramotor.util.Auth;
import com.ultramotor.util.MsgBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner.NumberEditor;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class ChiTietNhapKhoPanel extends javax.swing.JPanel {

    private Map<String, String> spMap;
    private PhieuNhapKho pnk;
    private ModelEvent event;
    private DefaultTableModel model;
    private KeyAdapter numberKeyAdapter;
//    public List<PhieuNhapKho> list = new ArrayList<>();
    private boolean viewOnly = false;
    private DecimalFormat numberFormat;

    public ChiTietNhapKhoPanel() {
        initComponents();
        init();
    }

    private void init() {
        new Thread(()->{
            spMap = new SanPhamDAO().getMaVaTenSP();
            numberFormat = new DecimalFormat("#,###.##");
            initListeners();
            initComboMaSP();
            initTableChiTiet();
            reset();
        }).start();
    }

    private void initComboMaSP() {
        List<String> listMaSP = new ArrayList<>(spMap.keySet());
        Collections.sort(listMaSP);
        cboMaSP.setModel(new DefaultComboBoxModel(listMaSP.toArray()));
        AutoCompleteDecorator.decorate(cboMaSP);
        JTextComponent editor = (JTextComponent) cboMaSP.getEditor().getEditorComponent();
        cboMaSP.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                txtTenSP.setText(timTenSP(editor.getText()));
            }
        });

    }

    private void initListeners() {
        cboMaSP.addActionListener(evt -> {
            txtTenSP.setText(timTenSP((String) cboMaSP.getSelectedItem()));
        });

        btnReset.addActionListener((ActionEvent e) -> {
            reset();
        });

        btnThem.addActionListener((ActionEvent e) -> {
            insert();
        });

        btnXoa.addActionListener((ActionEvent e) -> {
            resetForm();
        });

        event = new ModelEvent<ChiTietNhapKho>() {
            @Override
            public void update(ChiTietNhapKho e) {
                new Thread(() -> {
                    sleepThread(300);
                    removeRow();
                    setForm(e);
                }).start();
            }

            @Override
            public void delete(ChiTietNhapKho e) {
                new Thread(() -> {
                    sleepThread(300);
                    removeRow();
                }).start();
            }
        };
        numberKeyAdapter = new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int keychar = e.getKeyChar();
                if (!Character.isDigit(keychar)) {
                    e.consume();
                }
            }
        };

        NumberEditor editor = (NumberEditor) spnSoLuong.getEditor();
        editor.getTextField().addKeyListener(numberKeyAdapter);
        txtGiaNhap.addKeyListener(numberKeyAdapter);
        tblChiTiet.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = tblChiTiet.getSelectedRow();
                    int column = model.getColumnCount() - 1;
                    setForm((ChiTietNhapKho) ((ModelAction) model.getValueAt(row, column)).getEntity());
                }
            }
        });
    }

    private void initTableChiTiet() {
        String[] columns = {"STT", "Mã phiếu", "Mã Sản Phẩm", "Tên Sản Phẩm", "Số Lượng", "Giá Nhập", "Actions"};
        model = new DefaultTableModel(columns, 0);
        tblChiTiet.setModel(model);
        tblChiTiet.setShowVerticalLines(true);
        tblChiTiet.getColumnModel().getColumn(0).setMaxWidth(50);
        tblChiTiet.getColumnModel().getColumn(tblChiTiet.getColumnCount() - 1).setMaxWidth(100);
        tblChiTiet.fixTable(scrollChiTiet);
    }

    private void fillTableChiTiet() {
        if (pnk == null) {
            return;
        }
        model.setRowCount(0);
        pnk.getChiTietNhapKhoList().forEach(ct -> {
            model.addRow(getInfo(ct));
        });

        if (tblChiTiet.getColumnCount() == model.getColumnCount()) {
            tblChiTiet.removeColumn(tblChiTiet.getColumn("Actions"));
        }
        if (!pnk.getChiTietNhapKhoList().isEmpty()) {
            setForm(pnk.getChiTietNhapKhoList().get(0));
        }
    }

    public void reset() {
        SwingUtilities.invokeLater(() -> {
            tblChiTiet.stopCellEditor();
            model.setRowCount(0);
            tblChiTiet.createDefaultColumnsFromModel();
            tblChiTiet.getColumnModel().getColumn(tblChiTiet.getColumnCount() - 1).setMaxWidth(100);
        });
        resetForm();
        pnk = null;
        viewOnly = false;
    }

    private void resetForm() {
        txtTenSP.setText("");
        txtGiaNhap.setText("");
        spnSoLuong.setValue(0);
        cboMaSP.requestFocus();
    }

    private void setForm(ChiTietNhapKho e) {
        if (e == null) {
            return;
        }
        txtTenSP.setText(timTenSP(e.getSKU()));
        spnSoLuong.setValue(e.getSoLuong());
        txtGiaNhap.setText(String.format("%.2f", e.getGiaNhap()));
    }

    public void insert() {
        if (viewOnly) {
            MsgBox.warning("Không thể thay đổi phiếu nhập kho");
            return;
        }
        if (!validateForm()) {
            return;
        }
        if (pnk == null) {
            pnk = new PhieuNhapKho(getAutoID(), null, Auth.user == null ? "NV01" : Auth.user.getIdNV());
        }

        String maSku = (String) cboMaSP.getSelectedItem();
        int soLuong = (int) spnSoLuong.getValue();
        double giaNhap = Double.parseDouble(txtGiaNhap.getText());
        ChiTietNhapKho ct = new ChiTietNhapKho(model.getRowCount() + 1, maSku, soLuong, giaNhap, pnk.getIdPN());
        pnk.getChiTietNhapKhoList().add(ct);

        model.addRow(getInfo(ct));
        int index = model.getRowCount() - 1;
        tblChiTiet.setRowSelectionInterval(index, index); //đật hàng chọn trên bảng
        tblChiTiet.scrollRectToVisible(new java.awt.Rectangle(tblChiTiet.getCellRect(index, 0, true))); //di chuyển thanh lăn tới vị trí hàng chọn
        resetForm();
    }

    private boolean validateForm() {
        String s = (String) cboMaSP.getSelectedItem();
        if (s == null || s.isEmpty()) {
            MsgBox.error("Không tìm thấy sản phẩm!");
            return false;
        }
        if (((Number) spnSoLuong.getValue()).intValue() <= 0) {
            spnSoLuong.requestFocus();
            MsgBox.error("Nhập số lượng sản phẩm!");
            return false;
        }
        if (txtGiaNhap.getText().isEmpty() || Double.parseDouble(txtGiaNhap.getText()) <= 0) {
            txtGiaNhap.requestFocus();
            MsgBox.error("Nhập giá sản phẩm!");
            return false;
        }
        return true;
    }

    private void removeRow() {
        int row = tblChiTiet.getSelectedRow();
        tblChiTiet.stopCellEditor();
        pnk.getChiTietNhapKhoList().remove(row);
        if (model.getRowCount() == 1) {
            reset();
        } else {
            model.removeRow(row);
        }
    }

    private Object[] getInfo(ChiTietNhapKho ct) {
        return new Object[]{
            ct.getIdCTNK(),
            ct.getIdPN(),
            ct.getSKU(),
            timTenSP(ct.getSKU()),
            ct.getSoLuong(),
            numberFormat.format(ct.getGiaNhap()),
            new ModelAction(ct, event)
        };
    }

    public PhieuNhapKho getPhieuNhapKho() {
        return pnk;
    }

    public void setPhieuNhapKho(PhieuNhapKho pnk) {
        this.pnk = pnk;
        fillTableChiTiet();
        if (pnk != null) {
            viewOnly = true;
        }
    }

    public void addSaveListener(ActionListener ls) {
        btnSave.addActionListener(ls);
    }

    private String getAutoID() {
        return String.format("PNK%06d", NhapKhoPanel.nhapKhoList.size() + 1);
    }

    private void sleepThread(int milisec) {
        try {
            Thread.sleep(milisec);
        } catch (InterruptedException e) {
        }
    }

    private String timTenSP(String sku) {
        return spMap.getOrDefault(sku.toUpperCase().trim(), "");
    }

//    public void setList(List<PhieuNhapKho> list) {
//        this.list = list;
//    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnXoa = new com.swingx.Button();
        scrollChiTiet = new javax.swing.JScrollPane();
        tblChiTiet = new com.swingx.table.Table();
        btnReset = new com.swingx.Button();
        btnSave = new com.swingx.Button();
        jPanel1 = new javax.swing.JPanel();
        txtGiaNhap = new com.swingx.TextField();
        btnThem = new com.swingx.Button();
        jLabel2 = new javax.swing.JLabel();
        spnSoLuong = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        cboMaSP = new com.swingx.ComboBoxSuggestion();
        txtTenSP = new com.swingx.TextField();
        jLabel1 = new javax.swing.JLabel();
        lblSoluong = new javax.swing.JLabel();

        btnXoa.setBackground(new java.awt.Color(0, 174, 114));
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ultramotor/icon/waste_25px.png"))); // NOI18N
        btnXoa.setText("Xoá");
        btnXoa.setRadius(5);

        setBackground(new java.awt.Color(255, 255, 255));

        scrollChiTiet.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        scrollChiTiet.setViewportBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        scrollChiTiet.setOpaque(false);

        tblChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblChiTiet.setOpaque(false);
        scrollChiTiet.setViewportView(tblChiTiet);

        btnReset.setBackground(new java.awt.Color(0, 174, 114));
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ultramotor/icon/refresh_25px.png"))); // NOI18N
        btnReset.setText("Tạo Mới");
        btnReset.setRadius(5);

        btnSave.setBackground(new java.awt.Color(0, 174, 114));
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ultramotor/icon/icons8-save.png"))); // NOI18N
        btnSave.setText("Lưu");
        btnSave.setRadius(5);

        jPanel1.setOpaque(false);

        txtGiaNhap.setLabelText("Giá Nhập");
        txtGiaNhap.setOnlyField(true);

        btnThem.setBackground(new java.awt.Color(0, 174, 114));
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ultramotor/icon/icons8-add 2.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.setRadius(5);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Tên Sản Phẩm");
        jLabel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0));

        spnSoLuong.setModel(new javax.swing.SpinnerNumberModel(0, 0, 1000, 1));
        spnSoLuong.setAutoscrolls(true);
        spnSoLuong.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        spnSoLuong.setName(""); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Giá Nhập");
        jLabel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0));

        txtTenSP.setEditable(false);
        txtTenSP.setLabelText("Tên Sản Phẩm");
        txtTenSP.setOnlyField(true);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Mã Sản Phẩm");
        jLabel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0));

        lblSoluong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblSoluong.setText("Số lượng");
        lblSoluong.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(cboMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblSoluong, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(spnSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(104, 104, 104))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblSoluong)
                        .addComponent(jLabel3))
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(cboMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spnSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(6, 6, 6))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cboMaSP, spnSoLuong, txtGiaNhap});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollChiTiet)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnReset, btnSave});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollChiTiet, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.swingx.Button btnReset;
    private com.swingx.Button btnSave;
    private com.swingx.Button btnThem;
    private com.swingx.Button btnXoa;
    private com.swingx.ComboBoxSuggestion cboMaSP;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblSoluong;
    private javax.swing.JScrollPane scrollChiTiet;
    private javax.swing.JSpinner spnSoLuong;
    private com.swingx.table.Table tblChiTiet;
    private com.swingx.TextField txtGiaNhap;
    private com.swingx.TextField txtTenSP;
    // End of variables declaration//GEN-END:variables
}
