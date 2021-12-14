package com.ultramotor.ui.hoadon;

import com.ultramotor.entity.KhachHang;
import com.ultramotor.util.XCodeHelper;
import com.ultramotor.util.XImage;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

public class ThanhVienCard extends javax.swing.JPanel {

    public enum Membership {
        NORMAL, SILVER, GOLD
    }
    private KhachHang kh;

    private Color colorGradient;

    public ThanhVienCard() {
        initComponents();
        init();
    }

    private void init() {
        setOpaque(false);
        setBackground(new Color(112, 69, 246));
        lblLogo.setIcon(XImage.resize(new ImageIcon(getClass().getResource("/ultramotor/icon/bike.png")), 80, 50));
        colorGradient = new Color(255, 255, 255);
    }

    public KhachHang getThanhvien() {
        return kh;
    }

    public void setThanhvien(KhachHang thanhvien) {
        this.kh = thanhvien;
        if (kh == null || !kh.getThanhVien()) {
            reset();
            return;
        }
        lblName.setText(kh.getHoTenKH().toUpperCase());
        BufferedImage image = XCodeHelper.createQRCode(kh.getIdKH(), 50, 50);
        lblQrCode.setIcon(XImage.resize(new ImageIcon(image), 40, 40));
        setColor(getRank());
    }

    private void reset() {
        lblName.setText("KHÔNG CÓ DỮ LIỆU");
        lblQrCode.setIcon(null);
        setColor(Membership.NORMAL);
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

    private void setColor(Membership rank) {
        switch (rank) {
            case SILVER:
                setBackground(new Color(153, 153, 153));
                setColorGradient(new Color(250, 250, 250));
                break;
            case GOLD:
                setBackground(new Color(255, 153, 51));
                setColorGradient(new Color(255, 255, 0));
                break;
            default:
                setBackground(new Color(102, 102, 102));
                setColorGradient(new Color(204, 204, 204));
        }
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gra = new GradientPaint(0, getHeight(), getBackground(), getWidth(), 0, colorGradient);
        g2.setPaint(gra);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        super.paintComponent(grphcs);
    }

    public Color getColorGradient() {
        return colorGradient;
    }

    public void setColorGradient(Color colorGradient) {
        this.colorGradient = colorGradient;
        repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblName = new javax.swing.JLabel();
        lblQrCode = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();

        setOpaque(false);

        lblName.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblName.setForeground(new java.awt.Color(255, 255, 255));
        lblName.setText("KHÔNG CÓ DỮ LIỆU");
        lblName.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 10, 1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblQrCode, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                        .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblQrCode, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblName))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblQrCode;
    // End of variables declaration//GEN-END:variables
}
