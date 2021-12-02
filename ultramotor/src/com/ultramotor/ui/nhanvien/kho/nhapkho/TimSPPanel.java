package com.ultramotor.ui.nhanvien.kho.nhapkho;

import com.ultramotor.util.MsgBox;
import com.ultramotor.util.SearchFilter;
import com.ultramotor.util.XJdbc;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.RowSet;
import javax.sql.rowset.FilteredRowSet;
import javax.sql.rowset.Predicate;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class TimSPPanel extends javax.swing.JPanel {

    private FilteredRowSet rs;
    private Collection spSet;

    public TimSPPanel() {
        initComponents();
        addListeners();
    }

    private void addListeners() {
        this.addPropertyChangeListener("ancestor", (PropertyChangeEvent e) -> {
            initRowSet();
            fillCboNSX();
        });

        txtSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {

            }

        });

        cboNSX.addActionListener((ActionEvent e) -> {
            fillCboLoaiXe();
        });

        cboLoaiXe.addActionListener((ActionEvent e) -> {
            fillCboDongXe();
        });

        cboDongXe.addActionListener((ActionEvent e) -> {
            fillCboDoiXe();
        });

        cboDoiXe.addActionListener((ActionEvent e) -> {
            fillCboPhanKhoi();
        });

        cboPhanKhoi.addActionListener((ActionEvent e) -> {
            fillCboMauSac();
        });

        cboMauSac.addActionListener((ActionEvent e) -> {
            setFilter(rs, new SearchFilter(cboMauSac.getSelectedItem(), "mauSac"));
            Collection<String> col = findItem("SKU");
            txtMaSKU.setText(col.size() > 0 ? ((String) col.toArray()[0]) : "");
        });

    }

    private void fillCboNSX() {
        fillComboBox(cboNSX, findItem("TenNSX"), true);
    }

    private void fillCboLoaiXe() {
        setFilter(rs, new SearchFilter(cboNSX.getSelectedItem(), "TenNSX"));
        fillComboBox(cboLoaiXe, findItem("TenLoaiHang"), true);
    }

    private void fillCboDongXe() {
        setFilter(rs, new SearchFilter(cboLoaiXe.getSelectedItem(), "TenLoaiHang"));
        fillComboBox(cboDongXe, findItem("tenDongSP"), true);
    }

    private void fillCboDoiXe() {
        setFilter(rs, new SearchFilter(cboDongXe.getSelectedItem(), "tenDongSP"));
        fillComboBox(cboDoiXe, findItem("doiXe"), true);

    }

    private void fillCboPhanKhoi() {
        setFilter(rs, new SearchFilter(cboDoiXe.getSelectedItem(), "doiXe"));
        fillComboBox(cboPhanKhoi, findItem("phanKhoi"), true);

    }

    private void fillCboMauSac() {
        setFilter(rs, new SearchFilter(cboPhanKhoi.getSelectedItem(), "phanKhoi"));
        fillComboBox(cboMauSac, findItem("mauSac"), false);
    }

    private void fillComboBox(JComboBox cbo, Collection list, boolean autoSelected) {
        cbo.setModel(new DefaultComboBoxModel(list.toArray()));
        if (list.size() > 0 && autoSelected) {
            cbo.setSelectedIndex(0);
        }
    }

    public String getMaSP() {
        String maSKU = txtMaSKU.getText();
        String err = "";
        if (maSKU.equals("")) {
            err = "Mã sản phẩm không được để trống";
        } else if (!spSet.contains(maSKU.toUpperCase())) {
            err = "Mã sản phẩm không được để trống";

        }
        if (!err.equals("")) {
            MsgBox.error(err);
            throw new RuntimeException(err);
        }
        return maSKU;
    }

    public void reset() {
        txtMaSKU.setText("");
        txtSearch.setText("");
        txtMaSKU.requestFocus();
    }

    private void initRowSet() {
        try {
            rs = XJdbc.getFactory().createFilteredRowSet();
            rs.populate(XJdbc.query("select SKU,tenSP, TenNSX, TenLoaiHang, tenDongSP, doiXe, phanKhoi, mauSac  from \n"
                    + "	SanPham sp  join DongSanPham dsp on sp.id_DongSP = dsp.id_DongSP\n"
                    + "             join NhaSanXuat nsx on dsp.id_NSX = nsx.id_NSX\n"
                    + "             join LoaiHang lh on dsp.id_LH = lh.id_LH"));

            spSet = rs.toCollection("SKU");
        } catch (SQLException ex) {
            Logger.getLogger(TimSPPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Collection<String> findItem(String columnName) {
        Set<String> set = new HashSet();
        try {
            rs.beforeFirst();
            while (rs.next()) {
                set.add(rs.getString(columnName));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TimSPPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return set;
    }

    private void setFilter(FilteredRowSet rs, Predicate filter) {
        try {
            rs.setFilter(filter);
        } catch (SQLException ex) {
            Logger.getLogger(TimSPPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    private void printResultSet() {
//        try {
//            rs.beforeFirst();
//            int count = rs.getMetaData().getColumnCount();
//            while (rs.next()) {
//                for (int i = 1; i < count + 1; i++) {
//                    System.out.println(rs.getObject(i));
//
//                }
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(TimSPPanel.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public void setDoneListener(ActionListener ls) {
        btnDone.addActionListener(ls);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        comboBoxSuggestion5 = new com.swingx.ComboBoxSuggestion();
        lblNSX = new javax.swing.JLabel();
        cboLoaiXe = new com.swingx.ComboBoxSuggestion();
        cboNSX = new com.swingx.ComboBoxSuggestion();
        lblLoaiXe = new javax.swing.JLabel();
        cboDongXe = new com.swingx.ComboBoxSuggestion();
        lblDongXe = new javax.swing.JLabel();
        lblDoiXe = new javax.swing.JLabel();
        cboDoiXe = new com.swingx.ComboBoxSuggestion();
        cboPhanKhoi = new com.swingx.ComboBoxSuggestion();
        lblPhanKhoi = new javax.swing.JLabel();
        cboMauSac = new com.swingx.ComboBoxSuggestion();
        lblMauSac = new javax.swing.JLabel();
        txtSearch = new com.swingx.SearchTextField();
        txtMaSKU = new com.swingx.TextField();
        btnDone = new com.swingx.Button();

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Loại Xe");

        setBackground(new java.awt.Color(250, 250, 250));

        lblNSX.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblNSX.setText("Nhà Sản Xuất");

        lblLoaiXe.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblLoaiXe.setText("Loại Xe");

        lblDongXe.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblDongXe.setText("Dòng Xe");

        lblDoiXe.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblDoiXe.setText("Đời Xe");

        lblPhanKhoi.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblPhanKhoi.setText("Phân Khối");

        lblMauSac.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblMauSac.setText("Màu Sắc");

        txtMaSKU.setOnlyField(true);
        txtMaSKU.setPlaceholder("Mã SKU");

        btnDone.setBackground(new java.awt.Color(55, 161, 98));
        btnDone.setForeground(new java.awt.Color(255, 255, 255));
        btnDone.setText("XONG");
        btnDone.setToolTipText("");
        btnDone.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDone.setRadius(10);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDongXe)
                            .addComponent(cboDongXe, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDoiXe)
                            .addComponent(cboDoiXe, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPhanKhoi)
                            .addComponent(cboPhanKhoi, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMauSac)
                            .addComponent(cboMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cboNSX, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                            .addComponent(lblNSX)
                            .addComponent(txtMaSKU, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSearch, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboLoaiXe, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblLoaiXe))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(20, 20, 20))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDone, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(157, 157, 157))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtMaSKU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLoaiXe)
                    .addComponent(lblNSX))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboNSX, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboLoaiXe, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDongXe)
                        .addGap(8, 8, 8)
                        .addComponent(cboDongXe, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDoiXe)
                        .addGap(8, 8, 8)
                        .addComponent(cboDoiXe, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPhanKhoi)
                        .addGap(8, 8, 8)
                        .addComponent(cboPhanKhoi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblMauSac)
                        .addGap(8, 8, 8)
                        .addComponent(cboMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(btnDone, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.swingx.Button btnDone;
    private com.swingx.ComboBoxSuggestion cboDoiXe;
    private com.swingx.ComboBoxSuggestion cboDongXe;
    private com.swingx.ComboBoxSuggestion cboLoaiXe;
    private com.swingx.ComboBoxSuggestion cboMauSac;
    private com.swingx.ComboBoxSuggestion cboNSX;
    private com.swingx.ComboBoxSuggestion cboPhanKhoi;
    private com.swingx.ComboBoxSuggestion comboBoxSuggestion5;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lblDoiXe;
    private javax.swing.JLabel lblDongXe;
    private javax.swing.JLabel lblLoaiXe;
    private javax.swing.JLabel lblMauSac;
    private javax.swing.JLabel lblNSX;
    private javax.swing.JLabel lblPhanKhoi;
    private com.swingx.TextField txtMaSKU;
    private com.swingx.SearchTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}


