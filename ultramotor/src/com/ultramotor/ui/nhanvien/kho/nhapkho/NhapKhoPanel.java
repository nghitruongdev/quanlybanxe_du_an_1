package com.ultramotor.ui.nhanvien.kho.nhapkho;

import com.microsoft.sqlserver.jdbc.SQLServerDataTable;
import com.ultramotor.component.table.ModelView;
import com.ultramotor.dao.NhapKhoDAO;
import com.ultramotor.entity.ChiTietNhapKho;
import com.ultramotor.entity.PhieuNhapKho;
import com.ultramotor.util.MsgBox;
import com.ultramotor.util.XDate;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class NhapKhoPanel extends javax.swing.JPanel {

    private static NhapKhoDAO dao;
    private DefaultTableModel modelNhapKho;
    private ActionListener viewLs;
    static List<PhieuNhapKho> nhapKhoList = new ArrayList<>();

    public NhapKhoPanel() {
        initComponents();
        init();
    }

    private void init() {
        dao = new NhapKhoDAO();
        nhapKhoList = dao.selectAll();
        viewLs = ((ActionEvent e) -> {
            int row = tblPhieuNhap.getSelectedRow();
            int column = tblPhieuNhap.getColumnCount() - 1;
            PhieuNhapKho pnk = (PhieuNhapKho) ((ModelView) tblPhieuNhap.getValueAt(row, column)).getEntity();
            pnlChiTiet.setPhieuNhapKho(pnk);
        });
        initTablePhieuNhap();
        fillTablePhieuNhap();
        addListeners();
    }

    private void initTablePhieuNhap() {
        String[] columns = {"STT", "Mã Phiếu", "Ngày Nhập", "Nhân Viên Nhập", "Tổng tiền", "Actions"};
        modelNhapKho = new DefaultTableModel(columns, 5);
        tblPhieuNhap.setModel(modelNhapKho);
        tblPhieuNhap.fixTable(scrollPhieuNhap);
    }

    private void fillTablePhieuNhap() {
        modelNhapKho.setRowCount(0);
        for (PhieuNhapKho pnk : nhapKhoList) {
            modelNhapKho.addRow(getInfo(pnk));
        }
    }

    Object[] getInfo(PhieuNhapKho pnk) {
        return new Object[]{
            tblPhieuNhap.getRowCount() + 1,
            pnk.getIdPN(),
            XDate.toString(pnk.getNgayNhap(), "dd-MM-yyyy"),
            pnk.getIdNV(),
            pnk.getTongTien(),
            new ModelView(pnk, viewLs)
        };
    }

    Object[] getInfo(ChiTietNhapKho ct) {
        return new Object[]{
            ct.getIdCTNK(),
            ct.getSKU(),
            ct.getSoLuong(),
            ct.getGiaNhap(),
            ct.getIdPN()
        };
    }

    private void insert(PhieuNhapKho pnk) {
        if (pnk == null) {
            return;
        }
        try {
            SQLServerDataTable chiTietTable = ChiTietNhapKho.getDataServerTable();
            for (ChiTietNhapKho ct : pnk.getChiTietNhapKhoList()) {
                chiTietTable.addRow(getInfo(ct));
            }

            //tiến hành thêm vào cơ sở dữ liệu
            dao.insertWithChiTiet(pnk, chiTietTable);
            nhapKhoList.add(pnk);
            modelNhapKho.addRow(getInfo(pnk));
            pnlChiTiet.reset();

        } catch (SQLException ex) {
            dao.delete(pnk.getIdPN());
            MsgBox.error(ex.getMessage());
            Logger.getLogger(NhapKhoPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addListeners() {
        pnlChiTiet.setSaveListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PhieuNhapKho pnk = pnlChiTiet.getPhieuNhapKho();
                insert(pnk);

            }
        });
    }

    private void sleepThread(int milisec) {
        try {
            Thread.sleep(milisec);
        } catch (InterruptedException e) {

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Date = new com.raven.datechooser.DateChooser();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblTime = new javax.swing.JLabel();
        cboMaNhap = new javax.swing.JComboBox<>();
        scrollPhieuNhap = new javax.swing.JScrollPane();
        tblPhieuNhap = new com.ultramotor.component.table.Table();
        btnGuiEmail = new com.swingx.Button();
        btnInDanhSach = new com.swingx.Button();
        txtDate = new com.swingx.TextField();
        pnlChiTiet = new com.ultramotor.ui.nhanvien.kho.nhapkho.ChiTietNhapKhoPanel();
        jSeparator1 = new javax.swing.JSeparator();

        Date.setTextRefernce(txtDate);

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 174, 114));
        jLabel1.setText("DANH SÁCH PHIẾU NHẬP");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        lblTime.setText("Ngày Nhập");

        cboMaNhap.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã nhập", "Item 2", "Item 3", "Item 4" }));

        tblPhieuNhap.setModel(new javax.swing.table.DefaultTableModel(
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
        scrollPhieuNhap.setViewportView(tblPhieuNhap);

        btnGuiEmail.setBackground(new java.awt.Color(0, 174, 114));
        btnGuiEmail.setForeground(new java.awt.Color(255, 255, 255));
        btnGuiEmail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ultramotor/img/icon/icons8-sent.png"))); // NOI18N
        btnGuiEmail.setText("Gửi Mail");

        btnInDanhSach.setBackground(new java.awt.Color(0, 174, 114));
        btnInDanhSach.setForeground(new java.awt.Color(255, 255, 255));
        btnInDanhSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ultramotor/img/icon/print_25px.png"))); // NOI18N
        btnInDanhSach.setText("In danh sách");

        txtDate.setAnimateLabel(false);
        txtDate.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtDate.setDragEnabled(false);
        txtDate.setEnabled(false);
        txtDate.setLabelText("Ngày");
        txtDate.setOnlyField(true);
        txtDate.setRoundBorder(false);
        txtDate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtDateMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblTime)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(254, 254, 254)
                        .addComponent(btnGuiEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnInDanhSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cboMaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(scrollPhieuNhap))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboMaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuiEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInDanhSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTime))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(scrollPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlChiTiet, javax.swing.GroupLayout.DEFAULT_SIZE, 1209, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(pnlChiTiet, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtDateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDateMouseClicked
        // TODO add your handling code here:
        Date.showPopup();
    }//GEN-LAST:event_txtDateMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.datechooser.DateChooser Date;
    private com.swingx.Button btnGuiEmail;
    private com.swingx.Button btnInDanhSach;
    private javax.swing.JComboBox<String> cboMaNhap;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblTime;
    private com.ultramotor.ui.nhanvien.kho.nhapkho.ChiTietNhapKhoPanel pnlChiTiet;
    private javax.swing.JScrollPane scrollPhieuNhap;
    private com.ultramotor.component.table.Table tblPhieuNhap;
    private com.swingx.TextField txtDate;
    // End of variables declaration//GEN-END:variables
}
