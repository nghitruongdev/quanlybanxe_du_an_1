
package com.swingx.table;

import java.awt.Color;

public class StatusCell extends javax.swing.JPanel {

    public StatusCell(String status) {
        initComponents();
        lblStatus.setText(status);
        if(status.equals("HOÀN TẤT")){
            lblStatus.setBackground(new Color(117, 196, 125));
        }else{
            lblStatus.setBackground(new Color(238, 187, 12));
        }
    }

    @Override
    public String toString() {
        return lblStatus.getText();
    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblStatus = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 204, 255));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setOpaque(false);

        lblStatus.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblStatus.setForeground(new java.awt.Color(255, 255, 255));
        lblStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStatus.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 5));
        lblStatus.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblStatus;
    // End of variables declaration//GEN-END:variables
}
