package com.ultramotor.ui.hoadon;

import com.ultramotor.entity.KhachHang;
import java.awt.Graphics;

public class ThanhVienCard extends javax.swing.JPanel {

    public enum Membership {
        NORMAL, SILVER, GOLD
    }
    private KhachHang kh;

    public ThanhVienCard() {
        initComponents();
        init();
    }

    private void init() {

    }

    public KhachHang getThanhvien() {
        return kh;
    }

    public void setThanhvien(KhachHang thanhvien) {
        this.kh = thanhvien;
    }

    public Membership getRank() {
        double sum = kh.getTongTien();
        if (!kh.getThanhVien()) {
            return null;
        }
        if (sum > 100_000_000) {
            return Membership.GOLD;
        }
        if (sum > 50_000_000) {
            return Membership.SILVER;
        }
        return Membership.NORMAL;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        jLabel1.setText("THÀNH VIÊN VÀNG");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(105, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
