package com.swingx.table;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;

public class ActionCell extends javax.swing.JPanel {

    private ModelAction model;

    public ActionCell(ModelAction model) {
        initComponents();
        this.model = model;
        
        btnEdit.addActionListener((ActionEvent e)->{
            System.out.println(model.getEvent()==null);
            model.getEvent().update(model.getEntity());
        });
        
        btnDelete.addActionListener((ActionEvent e)->{
            model.getEvent().delete(model.getEntity());
        });
        
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

        setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));

        btnEdit.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/swingx/icon/edit.png"))); // NOI18N
        btnEdit.setMaximumSize(new java.awt.Dimension(25, 25));
        btnEdit.setMinimumSize(new java.awt.Dimension(25, 25));
        btnEdit.setPreferredSize(new java.awt.Dimension(25, 25));

        btnDelete.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/swingx/icon/delete.png"))); // NOI18N
        btnDelete.setMaximumSize(new java.awt.Dimension(25, 25));
        btnDelete.setMinimumSize(new java.awt.Dimension(25, 25));
        btnDelete.setPreferredSize(new java.awt.Dimension(25, 25));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.swingx.Button btnDelete;
    private com.swingx.Button btnEdit;
    // End of variables declaration//GEN-END:variables
}
