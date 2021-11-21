package com.ultramotor.component.table;

import com.ultramotor.entity.Entity;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionListener;

public class ActionCell extends javax.swing.JPanel {

    private ModelAction model;

    public ActionCell(ModelAction model) {
        initComponents();
        this.model = model;
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        grphcs.setColor(new Color(230, 230, 230));
        grphcs.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnEdit = new com.swingx.Button();
        btnDelete = new com.swingx.Button();

        btnEdit.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ultramotor/img/icon/edit.png"))); // NOI18N

        btnDelete.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ultramotor/img/icon/delete.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 45, Short.MAX_VALUE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.swingx.Button btnDelete;
    private com.swingx.Button btnEdit;
    // End of variables declaration//GEN-END:variables
}
