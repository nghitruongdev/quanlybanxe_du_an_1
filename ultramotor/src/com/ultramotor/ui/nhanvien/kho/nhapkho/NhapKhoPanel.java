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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnGuiEmail = new javax.swing.JButton();
        lblTime = new javax.swing.JLabel();
        btnInDanhSach = new javax.swing.JButton();
        cboMaNhap = new javax.swing.JComboBox<>();
        scrollPhieuNhap = new javax.swing.JScrollPane();
        tblPhieuNhap = new com.ultramotor.component.table.Table();
        pnlChiTiet = new com.ultramotor.ui.nhanvien.kho.nhapkho.ChiTietNhapKhoPanel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("DANH SÁCH PHIẾU NHẬP");

        btnGuiEmail.setText("Gửi mail");

        lblTime.setText("Từ");

        btnInDanhSach.setText("In danh sách");

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(127, 127, 127)
                .addComponent(lblTime)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 470, Short.MAX_VALUE)
                .addComponent(btnGuiEmail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnInDanhSach)
                .addGap(18, 18, 18)
                .addComponent(cboMaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(118, 118, 118))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPhieuNhap)
                .addGap(24, 24, 24))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(15, Short.MAX_VALUE)
                        .addComponent(lblTime))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGuiEmail)
                            .addComponent(btnInDanhSach)
                            .addComponent(cboMaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(466, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(419, 419, 419))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlChiTiet, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuiEmail;
    private javax.swing.JButton btnInDanhSach;
    private javax.swing.JComboBox<String> cboMaNhap;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblTime;
    private com.ultramotor.ui.nhanvien.kho.nhapkho.ChiTietNhapKhoPanel pnlChiTiet;
    private javax.swing.JScrollPane scrollPhieuNhap;
    private com.ultramotor.component.table.Table tblPhieuNhap;
    // End of variables declaration//GEN-END:variables
}