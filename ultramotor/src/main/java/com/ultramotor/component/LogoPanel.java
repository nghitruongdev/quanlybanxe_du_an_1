
package com.ultramotor.component;

import com.ultramotor.util.XImage;
import javax.swing.ImageIcon;

/**
 *
 * @author nghipc
 */
public class LogoPanel extends javax.swing.JPanel {

    public LogoPanel() {
        initComponents();
        lblLogo.setIcon(XImage.resize(new ImageIcon(getClass().getResource("/ultramotor/icon/logo_50px.png")), 50, 50));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblLogo = new javax.swing.JLabel();

        setBackground(new java.awt.Color(69, 220, 133));

        lblLogo.setBackground(new java.awt.Color(153, 153, 255));
        lblLogo.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        lblLogo.setForeground(new java.awt.Color(255, 255, 255));
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ultramotor/icon/logo_60px.png"))); // NOI18N
        lblLogo.setText("  ULTRAMOTOR");
        lblLogo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        lblLogo.setIconTextGap(0);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblLogo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblLogo, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblLogo;
    // End of variables declaration//GEN-END:variables
}
