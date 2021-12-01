package com.ultramotor.ui.khachhang;

import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class SearchPanel extends javax.swing.JPanel implements Multilang {

    final String NSX_VN = "Chọn hãng xe";
    final String NSX_EN = "Choose manufacturer";
    final String DONG_XE_VN = "Chọn loại xe";
    final String DONG_XE_EN = "Choose product type";
    final String CONTINUE_VN = "Tiếp tục";
    final String CONTINUE_EN = "Continue";
    final String SEARCH_VN = "Tìm kiếm";
    final String SEARCH_EN = "Search";

    private Lang lang;

    public SearchPanel() {
        initComponents();
        init();
        addListeners();
    }

    private void init() {
        this.addComponentListener(new ComponentAdapter(){
            @Override
            public void componentShown(ComponentEvent e) {
                 KhachHangController.fillComboNSX(cboNSX, lang);
            KhachHangController.fillComboLoaiHang(cboLoaiXe, lang);
            }
        });
        
        txtSearch.addKeyListener(new KeyAdapter(){
            @Override
            public void keyReleased(KeyEvent e) {
                int keycode = e.getExtendedKeyCode();
                if(keycode==KeyEvent.VK_ENTER){
                    btnSearch.doClick();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
            }

            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }
        
        });
        
    }

    @Override
    public void setLang(Lang lang) {
        this.lang = lang;
        if (lang.equals(Lang.VN)) {
            lblNSX.setText(NSX_VN);
            lblLoaiXe.setText(DONG_XE_VN);
            btnTiepTuc.setText(CONTINUE_VN);
            btnSearch.setText(SEARCH_VN);
            txtSearch.setLabelText(SEARCH_VN);
        } else {
            lblNSX.setText(NSX_EN);
            lblLoaiXe.setText(DONG_XE_EN);
            btnTiepTuc.setText(CONTINUE_EN);
            btnSearch.setText(SEARCH_EN);
            txtSearch.setLabelText(SEARCH_EN);
        }
        init();
    }

    private void addListeners() {
        btnTiepTuc.addActionListener((ActionEvent e) -> {
            new Thread(new Runnable() {
                @Override
                public void run() {
//                    KhachHangController.sleepThread(300);
                    KhachHangController.showProductList(getParent(), KhachHangController.search(cboNSX, cboLoaiXe));
                }
            }).start();

        });

        btnSearch.addActionListener((ActionEvent e) -> {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("SearchTHread: " + Thread.currentThread().getName());
                    List list = KhachHangController.search(txtSearch);
                    new Thread(() -> {
                        KhachHangController.showProductList(getParent(), list);
                    }).start();

                }
            }).start();

        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        pnlCbo = new javax.swing.JPanel();
        lblNSX = new javax.swing.JLabel();
        cboNSX = new com.swingx.ComboBoxSuggestion();
        lblLoaiXe = new javax.swing.JLabel();
        cboLoaiXe = new com.swingx.ComboBoxSuggestion();
        btnTiepTuc = new com.swingx.Button();
        pnlSearch = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        txtSearch = new com.swingx.SearchTextField();
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

        lblLoaiXe.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblLoaiXe.setText("Vui lòng chọn dòng xe");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 50, 10, 10);
        pnlCbo.add(lblLoaiXe, gridBagConstraints);

        cboLoaiXe.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.insets = new java.awt.Insets(0, 50, 0, 50);
        pnlCbo.add(cboLoaiXe, gridBagConstraints);

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

        jPanel1.setOpaque(false);

        txtSearch.setAnimateLabel(true);
        txtSearch.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtSearch.setLabelText("Tìm kiếm");
        txtSearch.setOnlyField(false);
        txtSearch.setPlaceholder("");

        btnSearch.setBackground(new java.awt.Color(158, 158, 158));
        btnSearch.setText("Tìm kiếm");
        btnSearch.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlSearchLayout = new javax.swing.GroupLayout(pnlSearch);
        pnlSearch.setLayout(pnlSearchLayout);
        pnlSearchLayout.setHorizontalGroup(
            pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSearchLayout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );
        pnlSearchLayout.setVerticalGroup(
            pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSearchLayout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(154, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSearchLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1))
        );

        add(pnlSearch);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.swingx.Button btnSearch;
    private com.swingx.Button btnTiepTuc;
    private com.swingx.ComboBoxSuggestion cboLoaiXe;
    private com.swingx.ComboBoxSuggestion cboNSX;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblLoaiXe;
    private javax.swing.JLabel lblNSX;
    private javax.swing.JPanel pnlCbo;
    private javax.swing.JPanel pnlSearch;
    private com.swingx.SearchTextField txtSearch;
    // End of variables declaration//GEN-END:variables

}
