package com.ultramotor.ui.khachhang;

import java.awt.Container;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

public class SearchPanel extends javax.swing.JPanel implements Multilang {

    final String LANG_SELECT_EN = "Plsease choose a language";
    final String LANG_SELECT_VN = "Vui lòng chọn ngôn ngữ";
    final String CONTINUE_EN = "Continue";
    final String CONTINUE_VN = "Tiếp tục";
    final DefaultComboBoxModel LANG_MODEL_EN = new DefaultComboBoxModel(new String[]{"Vietnamese", "English"});
    final DefaultComboBoxModel LANG_MODEL_VN = new DefaultComboBoxModel(new String[]{"Tiếng Việt", "Tiếng Anh"});

    public SearchPanel() {
        initComponents();
        init();
    }

    private void init() {
       
    }

    @Override
    public void setLang(String lang) {
       
    }

    private void addListeners() {
        
    }
    


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        pnlCbo = new javax.swing.JPanel();
        lblNSX = new javax.swing.JLabel();
        cboNSX = new com.swingx.ComboBoxSuggestion();
        lblDongXe = new javax.swing.JLabel();
        cboDongXe = new com.swingx.ComboBoxSuggestion();
        jPanel3 = new javax.swing.JPanel();
        textField1 = new com.swingx.TextField();

        setBackground(new java.awt.Color(204, 204, 204));
        setLayout(new java.awt.GridLayout(1, 0));

        pnlCbo.setBackground(new java.awt.Color(102, 255, 102));
        java.awt.GridBagLayout jPanel2Layout = new java.awt.GridBagLayout();
        jPanel2Layout.columnWidths = new int[] {0, 10, 0, 10, 0, 10, 0};
        jPanel2Layout.rowHeights = new int[] {0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0};
        pnlCbo.setLayout(jPanel2Layout);

        lblNSX.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblNSX.setText("Vui lòng chọn hãng sản xuất");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 50, 10, 10);
        pnlCbo.add(lblNSX, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.insets = new java.awt.Insets(0, 50, 0, 50);
        pnlCbo.add(cboNSX, gridBagConstraints);

        lblDongXe.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblDongXe.setText("Vui lòng chọn hãng sản xuất");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 50, 10, 10);
        pnlCbo.add(lblDongXe, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.insets = new java.awt.Insets(0, 50, 0, 50);
        pnlCbo.add(cboDongXe, gridBagConstraints);

        add(pnlCbo);

        jPanel3.setBackground(new java.awt.Color(255, 153, 0));

        textField1.setText("textField1");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(319, Short.MAX_VALUE))
        );

        add(jPanel3);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.swingx.ComboBoxSuggestion cboDongXe;
    private com.swingx.ComboBoxSuggestion cboNSX;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblDongXe;
    private javax.swing.JLabel lblNSX;
    private javax.swing.JPanel pnlCbo;
    private com.swingx.TextField textField1;
    // End of variables declaration//GEN-END:variables

}
