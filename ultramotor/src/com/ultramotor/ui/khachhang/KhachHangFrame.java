package com.ultramotor.ui.khachhang;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KhachHangFrame extends javax.swing.JFrame {

    private KhachHangController ctrl;

    public KhachHangFrame() {
        initComponents();
        init();
    }

    private void init() {
        ctrl = new KhachHangController();
        getContentPane().setBackground(Color.white);
        initListeners();
        btnBack.setVisible(false);
        btnNext.setVisible(false);

        updateStatus();
    }

    private void updateStatus() {
        boolean first = pnlWelcome.isShowing();
        boolean second = pnlSearch.isShowing();
        boolean last = pnlDetails.isShowing();

        btnBack.setVisible(!first && !last);
        btnNext.setVisible(!first && !second && !last);
    }

    private void initListeners() {
        btnBack.addActionListener((ActionEvent e) -> {
            ctrl.navigateCard(pnlMain, false);
            updateStatus();
        });

        btnNext.addActionListener((ActionEvent e) -> {
            ctrl.navigateCard(pnlMain, true);
            updateStatus();
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlHeader = new javax.swing.JPanel();
        btnNext = new com.swingx.Button();
        btnBack = new com.swingx.Button();
        pnlMain = new javax.swing.JPanel();
        pnlWelcome = new com.ultramotor.ui.khachhang.WelcomePanel();
        pnlSearch = new com.ultramotor.ui.khachhang.SearchPanel();
        pnlProductList = new com.ultramotor.ui.khachhang.ProductListPanel();
        pnlDetails = new com.ultramotor.ui.khachhang.ProductDetailsPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        pnlHeader.setBackground(new java.awt.Color(255, 255, 255));

        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ultramotor/img/icon/right_50px.png"))); // NOI18N

        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ultramotor/img/icon/left_50px.png"))); // NOI18N

        javax.swing.GroupLayout pnlHeaderLayout = new javax.swing.GroupLayout(pnlHeader);
        pnlHeader.setLayout(pnlHeaderLayout);
        pnlHeaderLayout.setHorizontalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHeaderLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(956, Short.MAX_VALUE))
        );

        pnlHeaderLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnBack, btnNext});

        pnlHeaderLayout.setVerticalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pnlHeaderLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnBack, btnNext});

        pnlMain.setLayout(new java.awt.CardLayout());
        pnlMain.add(pnlWelcome, "card4");
        pnlMain.add(pnlSearch, "card4");
        pnlMain.add(pnlProductList, "card3");
        pnlMain.add(pnlDetails, "card5");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, 1115, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 540, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 75, Short.MAX_VALUE)
                    .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        setSize(new java.awt.Dimension(1131, 662));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(KhachHangFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KhachHangFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KhachHangFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KhachHangFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KhachHangFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.swingx.Button btnBack;
    private com.swingx.Button btnNext;
    private com.ultramotor.ui.khachhang.ProductDetailsPanel pnlDetails;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlMain;
    private com.ultramotor.ui.khachhang.ProductListPanel pnlProductList;
    private com.ultramotor.ui.khachhang.SearchPanel pnlSearch;
    private com.ultramotor.ui.khachhang.WelcomePanel pnlWelcome;
    // End of variables declaration//GEN-END:variables

}
