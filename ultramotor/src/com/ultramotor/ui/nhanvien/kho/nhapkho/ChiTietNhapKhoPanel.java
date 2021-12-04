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

//        btnThem.addActionListener((ActionEvent e) -> {
//
//        });
//
//        btnThem.addActionListener((ActionEvent e) -> {
//
//        });
//
//        btnThem.addActionListener((ActionEvent e) -> {
//
//        });

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

        lblSoluong = new javax.swing.JLabel();
        scrollChiTiet = new javax.swing.JScrollPane();
        tblChiTiet = new com.ultramotor.component.table.Table();
        spnSoLuong = new javax.swing.JSpinner();
        btnThem = new com.swingx.Button();
        btnReset = new com.swingx.Button();
        btnImport = new com.swingx.Button();
        btnSave = new com.swingx.Button();
        btnXoa = new com.swingx.Button();
        txtMaPhieuNhap = new com.swingx.TextField();
        txtMaSKU = new com.swingx.TextField();
        txtGiaNhap = new com.swingx.TextField();
        txtTenSP = new com.swingx.TextField();

        setBackground(new java.awt.Color(255, 255, 255));

        lblSoluong.setForeground(new java.awt.Color(128, 128, 128));
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
        spnSoLuong.setName(""); // NOI18N

        btnThem.setBackground(new java.awt.Color(0, 174, 114));
        btnThem.setText("Thêm");
        btnThem.setRadius(5);

        btnReset.setBackground(new java.awt.Color(0, 174, 114));
        btnReset.setText("Đặt lại");
        btnReset.setRadius(5);

        btnImport.setBackground(new java.awt.Color(0, 174, 114));
        btnImport.setText("Import Excel");
        btnImport.setRadius(5);

        btnSave.setBackground(new java.awt.Color(0, 174, 114));
        btnSave.setText("Lưu");
        btnSave.setRadius(5);

        btnXoa.setBackground(new java.awt.Color(0, 174, 114));
        btnXoa.setText("Xoá");
        btnXoa.setRadius(5);

        txtMaPhieuNhap.setLabelText("Mã Phiếu Nhập");

        txtMaSKU.setLabelText("Mã SKU");

        txtGiaNhap.setLabelText("Giá Nhập");

        txtTenSP.setLabelText("Tên Sản Phẩm");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrollChiTiet))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(txtMaSKU, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(lblSoluong, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(spnSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 274, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(btnImport, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtMaPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSoluong)
                        .addComponent(spnSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(txtMaSKU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnImport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.swingx.Button btnImport;
    private com.swingx.Button btnReset;
    private com.swingx.Button btnSave;
    private com.swingx.Button btnThem;
    private com.swingx.Button btnXoa;
    private javax.swing.JLabel lblSoluong;
    private javax.swing.JScrollPane scrollChiTiet;
    private javax.swing.JSpinner spnSoLuong;
    private com.ultramotor.component.table.Table tblChiTiet;
    private com.swingx.TextField txtGiaNhap;
    private com.swingx.TextField txtMaPhieuNhap;
    private com.swingx.TextField txtMaSKU;
    private com.swingx.TextField txtTenSP;
    // End of variables declaration//GEN-END:variables
}
