package com.ultramotor.ui.khachhang;

import javax.swing.DefaultComboBoxModel;

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
        btnTiepTuc = new com.swingx.Button();
        pnlSearch = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        txtSearch = new com.swingx.SearchTextField();
        lblTimKiem = new javax.swing.JLabel();
        btnSearch = new com.swingx.Button();

        setBackground(new java.awt.Color(204, 204, 204));
        setLayout(new java.awt.GridLayout(1, 0));

        pnlCbo.setBackground(new java.awt.Color(250, 250, 250));
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

        cboNSX.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
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

        cboDongXe.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.insets = new java.awt.Insets(0, 50, 0, 50);
        pnlCbo.add(cboDongXe, gridBagConstraints);

        btnTiepTuc.setBackground(new java.awt.Color(158, 158, 158));
        btnTiepTuc.setText("Tiếp tục");
        btnTiepTuc.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(18, 31, 18, 31);
        pnlCbo.add(btnTiepTuc, gridBagConstraints);

        add(pnlCbo);

        pnlSearch.setBackground(new java.awt.Color(250, 250, 250));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        txtSearch.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtSearch.setPlaceholder("");

        lblTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTimKiem.setText("Tìm kiếm sản phẩm");

        btnSearch.setBackground(new java.awt.Color(158, 158, 158));
        btnSearch.setText("Tìm kiếm");
        btnSearch.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        javax.swing.GroupLayout pnlSearchLayout = new javax.swing.GroupLayout(pnlSearch);
        pnlSearch.setLayout(pnlSearchLayout);
        pnlSearchLayout.setHorizontalGroup(
            pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSearchLayout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSearchLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 18, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSearchLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlSearchLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        pnlSearchLayout.setVerticalGroup(
            pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSearchLayout.createSequentialGroup()
                .addGroup(pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSearchLayout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(lblTimKiem)
                        .addGap(18, 18, 18)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 296, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSearchLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1)))
                .addContainerGap())
            .addGroup(pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlSearchLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        add(pnlSearch);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.swingx.Button btnSearch;
    private com.swingx.Button btnTiepTuc;
    private com.swingx.ComboBoxSuggestion cboDongXe;
    private com.swingx.ComboBoxSuggestion cboNSX;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblDongXe;
    private javax.swing.JLabel lblNSX;
    private javax.swing.JLabel lblTimKiem;
    private javax.swing.JPanel pnlCbo;
    private javax.swing.JPanel pnlSearch;
    private com.swingx.SearchTextField txtSearch;
    // End of variables declaration//GEN-END:variables

}
