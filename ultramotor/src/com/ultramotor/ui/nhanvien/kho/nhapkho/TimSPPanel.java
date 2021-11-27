package com.ultramotor.ui.nhanvien.kho.nhapkho;

import com.ultramotor.dao.LoaiHangDAO;
import com.ultramotor.dao.NhaSanXuatDAO;
import com.ultramotor.util.XJdbc;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.beans.PropertyChangeEvent;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.FilteredRowSet;
import javax.sql.rowset.Predicate;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class TimSPPanel extends javax.swing.JPanel {

    private NhaSanXuatDAO daoNSX = new NhaSanXuatDAO();
    private LoaiHangDAO daoLH = new LoaiHangDAO();
    private FilteredRowSet rs;

    public TimSPPanel() {
        initComponents();
        addListeners();
    }

    private void addListeners() {
        this.addPropertyChangeListener("ancestor", (PropertyChangeEvent e) -> {
            System.out.println("Ancestor changed");
            initRowSet();
            fillCboNSX();
        });
        cboNSX.addPropertyChangeListener((PropertyChangeEvent e) -> {
            System.out.println(e.getPropertyName());
        });
        cboNSX.addItemListener((ItemEvent e) -> {
            System.out.println("Hello");
            System.out.println(e.getItem());
        });

        cboNSX.addActionListener((ActionEvent e) -> {
            System.out.println("NSX Action performed");
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

    }

    private void fillCboNSX() {
        Collection<String> col = findItem("TenNSX");
        fillComboBox(cboNSX, col);
        if (col.size() > 0) {
            cboNSX.setSelectedIndex(0);
        }

    }

    private void fillCboLoaiXe() {
        setFilter(rs, new NameFilter(cboNSX.getSelectedItem(), "TenNSX"));
        Collection<String> col = findItem("TenLoaiHang");
        fillComboBox(cboLoaiXe, col);
        if (col.size() > 0) {
            cboLoaiXe.setSelectedIndex(0);
        }
    }

    private void fillCboDongXe() {
        Object loaiXe = cboLoaiXe.getSelectedItem();
        setFilter(rs, new NameFilter(loaiXe, "TenLoaiHang"));
        fillComboBox(cboDongXe, findItem("tenDongSP"));
    }

    private void fillCboDoiXe() {
        Object dongXe = cboDongXe.getSelectedItem();
        setFilter(rs, new NameFilter(dongXe, "tenDongSP"));
        fillComboBox(cboDoiXe, findItem("doiXe"));
    }

    private void fillCboPhanKhoi() {
        Object doiXe = cboDoiXe.getSelectedItem();
        setFilter(rs, new NameFilter(doiXe, "doiXe"));
        printResultSet();
        fillComboBox(cboPhanKhoi, findItem("phanKhoi"));

    }

    private void fillCboMauSac() {
        Object phanKhoi = cboPhanKhoi.getSelectedItem();
        setFilter(rs, new NameFilter(phanKhoi, "phanKhoi"));
        fillComboBox(cboMauSac, findItem("mauSac"));
    }

    private void fillComboBox(JComboBox cbo, Collection list) {
        cbo.setModel(new DefaultComboBoxModel(list.toArray()));
    }

    private void initRowSet() {
        try {
            rs = XJdbc.getFactory().createFilteredRowSet();
            rs.populate(XJdbc.query("select SKU, TenNSX, TenLoaiHang, tenDongSP, doiXe, phanKhoi, mauSac  from \n"
                    + "	SanPham sp  join DongSanPham dsp on sp.id_DongSP = dsp.id_DongSP\n"
                    + "             join NhaSanXuat nsx on dsp.id_NSX = nsx.id_NSX\n"
                    + "             join LoaiHang lh on dsp.id_LH = lh.id_LH"));

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

    private void printResultSet() {
        try {
            rs.beforeFirst();
            int count = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                for (int i = 1; i < count + 1; i++) {
                    System.out.println(rs.getObject(i));

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(TimSPPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
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

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Loại Xe");

        setBackground(new java.awt.Color(250, 250, 250));

        lblNSX.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNSX.setText("Nhà Sản Xuất");

        lblLoaiXe.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblLoaiXe.setText("Loại Xe");

        lblDongXe.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDongXe.setText("Dòng Xe");

        lblDoiXe.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDoiXe.setText("Đời Xe");

        lblPhanKhoi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPhanKhoi.setText("Phân Khối");

        lblMauSac.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblMauSac.setText("Màu Sắc");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
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
                            .addComponent(cboNSX, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNSX))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboLoaiXe, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblLoaiXe))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPhanKhoi)
                            .addComponent(cboPhanKhoi, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMauSac)
                            .addComponent(cboMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
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
    // End of variables declaration//GEN-END:variables
}

class NameFilter implements Predicate {

    private Object value;
    private String columnName = null;
    private int column = -1;

    public NameFilter(Object value, String columnName) {
        this.value = value;
        this.columnName = columnName;
    }

    public NameFilter(Object value, int columnIndex) {
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
