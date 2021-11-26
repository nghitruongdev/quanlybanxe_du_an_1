package com.ultramotor.ui.nhanvien.kho.nhapkho;

import com.ultramotor.component.table.ModelAction;
import com.ultramotor.component.table.ModelEvent;
import com.ultramotor.entity.ChiTietNhapKho;
import com.ultramotor.entity.PhieuNhapKho;
import com.ultramotor.util.Auth;
import com.ultramotor.util.MsgBox;
import com.ultramotor.util.XDate;
import com.ultramotor.util.XJdbc;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.CachedRowSet;
import javax.swing.JSpinner.NumberEditor;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class ChiTietNhapKhoPanel extends javax.swing.JPanel {

    private Map<String, String> spMap;
    private PhieuNhapKho pnk;
    private ModelEvent event;
    private DefaultTableModel model;
    private KeyAdapter numberKeyAdapter;
    private boolean viewOnly = false;

    public ChiTietNhapKhoPanel() {
        initComponents();
        init();
    }

    private void init() {
        initMap();
        initListeners();
        initTableChiTiet();
        reset();
    }

    private void updateStatus() {
        boolean exists = NhapKhoPanel.nhapKhoList.contains(pnk) && pnk != null;
        System.out.println(exists);
        System.out.println("View " + viewOnly);
        btnThem.setEnabled(!exists && !viewOnly);
        btnSave.setEnabled(!exists);
    }

    private void initListeners() {
        btnReset.addActionListener((ActionEvent e) -> {
            reset();
        });

        btnThem.addActionListener((ActionEvent e) -> {
            insert();
        });

        btnXoa.addActionListener((ActionEvent e) -> {
            resetForm();
        });

        btnThem.addActionListener((ActionEvent e) -> {

        });

        btnThem.addActionListener((ActionEvent e) -> {

        });

        btnThem.addActionListener((ActionEvent e) -> {

        });

        event = new ModelEvent<ChiTietNhapKho>() {
            @Override
            public void update(ChiTietNhapKho e) {
                new Thread(() -> {
                    sleepThread(300);
                    tblChiTiet.getCellEditor().stopCellEditing();
                    removeRow();
                    setForm(e);
                    txtMaSKU.requestFocus();
                    viewOnly = false;
                    updateStatus();
                }).start();
            }

            @Override
            public void delete(ChiTietNhapKho e) {
                new Thread(() -> {
                    sleepThread(300);
                    tblChiTiet.getCellEditor().stopCellEditing();
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

        txtMaSKU.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                txtTenSP.setText(timTenSP(txtMaSKU.getText()));
            }
        });

        tblChiTiet.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = tblChiTiet.getSelectedRow();
                    int column = model.getColumnCount() - 1;
                    setForm((ChiTietNhapKho) ((ModelAction) model.getValueAt(row, column)).getEntity());
                }
                viewOnly = true;
                updateStatus();
            }
        });

        //add FocusEvent check Mã Nhập Kho có tồn tại trong Database
        txtMaPhieuNhap.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (NhapKhoPanel.nhapKhoList.contains(new PhieuNhapKho(txtMaPhieuNhap.getText()))) {
                    MsgBox.error("Mã phiếu nhập kho đã tồn tại");
                    txtMaPhieuNhap.setText("");
                    txtMaPhieuNhap.requestFocus();
                }
            }

        });

        txtMaPhieuNhap.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {

            }

        });
    }

    private String timTenSP(String sku) {
        return spMap.getOrDefault(sku.toUpperCase().trim(), "");
    }

    private void initTableChiTiet() {
        String[] columns = {"STT", "Mã phiếu", "Mã Sản Phẩm", "Tên Sản Phẩm", "Số Lượng", "Giá Nhập", "Actions"};
        model = new DefaultTableModel(columns, 0);
        tblChiTiet.setModel(model);
        tblChiTiet.setShowVerticalLines(true);
        tblChiTiet.getColumnModel().getColumn(0).setMaxWidth(50);
        tblChiTiet.getColumnModel().getColumn(tblChiTiet.getColumnCount() - 1).setMaxWidth(200);
        tblChiTiet.fixTable(scrollChiTiet);
    }

    private void fillTableChiTiet() {
        if (pnk == null) {
            return;
        }
        model.setRowCount(0);
        System.out.println("List is " + pnk.getChiTietNhapKhoList());
        for (ChiTietNhapKho ct : pnk.getChiTietNhapKhoList()) {
            model.addRow(getInfo(ct));
        }

        if (tblChiTiet.getColumnCount() == model.getColumnCount()) {
            tblChiTiet.removeColumn(tblChiTiet.getColumn("Actions"));
        }
        if (!pnk.getChiTietNhapKhoList().isEmpty()) {
            setForm(pnk.getChiTietNhapKhoList().get(0));
        }

        updateStatus();
    }

    public void reset() {
        model.setRowCount(0);
        tblChiTiet.createDefaultColumnsFromModel();
        resetForm();

        txtMaPhieuNhap.setText(getMaPhieuNhap());
        txtMaPhieuNhap.setEditable(true);
        txtMaPhieuNhap.requestFocus();
        pnk = null;
        updateStatus();

    }

    private void resetForm() {
        txtMaSKU.setText("");
        txtTenSP.setText("");
        txtGiaNhap.setText("");

        spnSoLuong.setValue(0);
        txtMaSKU.requestFocus();
        viewOnly = false;
        updateStatus();
    }

    private void setForm(ChiTietNhapKho e) {
        if (e == null) {
            return;
        }
        txtMaPhieuNhap.setText(e.getIdPN());
        txtMaSKU.setText(e.getSKU());
        txtTenSP.setText(timTenSP(e.getSKU()));
        spnSoLuong.setValue(e.getSoLuong());
        txtGiaNhap.setText(String.format("%.2f", e.getGiaNhap()));
    }

    public void insert() {
        String idPhieu = txtMaPhieuNhap.getText();
        String maSku = txtMaSKU.getText();
        int soLuong = (int) spnSoLuong.getValue();
        double giaNhap = ((Number) txtGiaNhap.getValue()).doubleValue();
        ChiTietNhapKho ct = new ChiTietNhapKho(model.getRowCount() + 1, maSku, soLuong, giaNhap, idPhieu);
        model.insertRow(0, getInfo(ct));

        //Auth.user still null
        if (pnk == null) {
            pnk = new PhieuNhapKho(idPhieu, new Date(), Auth.user == null ? "NV01" : Auth.user.getIdNV());
        }
        pnk.getChiTietNhapKhoList().add(ct);

        txtMaPhieuNhap.setEditable(false);
        resetForm();
    }

    private void removeRow() {
        int row = tblChiTiet.getSelectedRow();
        pnk.getChiTietNhapKhoList().remove(row);
        model.removeRow(row);
    }

    private Object[] getInfo(ChiTietNhapKho ct) {
        return new Object[]{
            ct.getIdCTNK(),
            ct.getIdPN(),
            ct.getSKU(),
            timTenSP(ct.getSKU()),
            ct.getSoLuong(),
            ct.getGiaNhap(),
            new ModelAction(ct, event)
        };
    }

    public PhieuNhapKho getPhieuNhapKho() {
        return pnk;
    }

    public void setPhieuNhapKho(PhieuNhapKho pnk) {
        this.pnk = pnk;
        fillTableChiTiet();
    }

    public void setSaveListener(ActionListener ls) {
        btnSave.addActionListener(ls);
    }

    private String getMaPhieuNhap() {
        return String.format("PNK%s", XDate.toString(new Date(), "yyyyMMddhhmmss"));
    }

    private void sleepThread(int milisec) {
        try {
            Thread.sleep(milisec);
        } catch (InterruptedException e) {

        }
    }

    private void initMap() {
        CachedRowSet rs;
        spMap = new HashMap<>();
        try {
            rs = XJdbc.query("SELECT SKU, TenSP FROM SanPham");
            while (rs.next()) {
                spMap.put(rs.getString(1).toUpperCase(), rs.getString(2));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietNhapKhoPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblMaSKU = new javax.swing.JLabel();
        lblMaPhieuNhap = new javax.swing.JLabel();
        lbtGiaNhap = new javax.swing.JLabel();
        txtMaPhieuNhap = new javax.swing.JTextField();
        lblSoluong = new javax.swing.JLabel();
        txtMaSKU = new javax.swing.JTextField();
        scrollChiTiet = new javax.swing.JScrollPane();
        tblChiTiet = new com.ultramotor.component.table.Table();
        spnSoLuong = new javax.swing.JSpinner();
        btnThem = new com.swingx.Button();
        txtGiaNhap = new javax.swing.JFormattedTextField();
        btnReset = new com.swingx.Button();
        lblTenSP = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        btnImport = new com.swingx.Button();
        btnSave = new com.swingx.Button();
        btnXoa = new com.swingx.Button();

        lblMaSKU.setText("Mã SKU");

        lblMaPhieuNhap.setText("Mã phiếu nhập");

        lbtGiaNhap.setText("Giá nhập");

        txtMaPhieuNhap.setEditable(false);
        txtMaPhieuNhap.setBackground(new java.awt.Color(255, 255, 255));

        lblSoluong.setText("Số lượng");

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
        scrollChiTiet.setViewportView(tblChiTiet);

        spnSoLuong.setModel(new javax.swing.SpinnerNumberModel(0, 0, 1000, 1));
        spnSoLuong.setAutoscrolls(true);
        spnSoLuong.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        spnSoLuong.setOpaque(false);

        btnThem.setBackground(new java.awt.Color(74, 164, 72));
        btnThem.setText("Thêm");
        btnThem.setRadius(5);

        txtGiaNhap.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));

        btnReset.setBackground(new java.awt.Color(74, 164, 72));
        btnReset.setText("Đặt lại");
        btnReset.setRadius(5);

        lblTenSP.setText("Tên SP");

        btnImport.setBackground(new java.awt.Color(74, 164, 72));
        btnImport.setText("Import Excel");
        btnImport.setRadius(5);

        btnSave.setBackground(new java.awt.Color(74, 164, 72));
        btnSave.setText("Lưu");
        btnSave.setRadius(5);

        btnXoa.setBackground(new java.awt.Color(74, 164, 72));
        btnXoa.setText("Xoá");
        btnXoa.setRadius(5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollChiTiet, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 791, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblMaPhieuNhap)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtMaPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblTenSP)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblMaSKU)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtMaSKU, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnImport, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblSoluong)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(spnSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lbtGiaNhap)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(281, 281, 281)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnImport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMaPhieuNhap)
                            .addComponent(txtMaPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMaSKU)
                            .addComponent(txtMaSKU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTenSP)
                            .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbtGiaNhap))
                            .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSoluong)
                            .addComponent(spnSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(scrollChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.swingx.Button btnImport;
    private com.swingx.Button btnReset;
    private com.swingx.Button btnSave;
    private com.swingx.Button btnThem;
    private com.swingx.Button btnXoa;
    private javax.swing.JLabel lblMaPhieuNhap;
    private javax.swing.JLabel lblMaSKU;
    private javax.swing.JLabel lblSoluong;
    private javax.swing.JLabel lblTenSP;
    private javax.swing.JLabel lbtGiaNhap;
    private javax.swing.JScrollPane scrollChiTiet;
    private javax.swing.JSpinner spnSoLuong;
    private com.ultramotor.component.table.Table tblChiTiet;
    private javax.swing.JFormattedTextField txtGiaNhap;
    private javax.swing.JTextField txtMaPhieuNhap;
    private javax.swing.JTextField txtMaSKU;
    private javax.swing.JTextField txtTenSP;
    // End of variables declaration//GEN-END:variables
}
