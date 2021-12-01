package com.ultramotor.ui.khachhang;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

public class KhachHangFrame extends javax.swing.JFrame {

    private KhachHangController ctrl;
    private Lang lang = Lang.VN;
    
    public KhachHangFrame() {
        initComponents();
        init();
        addListeners();
    }

    private void init() {
        ctrl = new KhachHangController();
        getContentPane().setBackground(Color.black);
        btnBack.setVisible(false);
        btnNext.setVisible(false);
    }

    private void updateStatus() {
        btnBack.setVisible(!pnlWelcome.isShowing() );
//        btnNext.setVisible(!first && !second && !last);
    }

    private void addListeners() {
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowOpened(WindowEvent e) {
                KhachHangFrame.this.setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
            
        });
        btnBack.addActionListener((ActionEvent e) -> {
            ctrl.navigateCard(pnlMain, false);
            updateStatus();
        });

        btnNext.addActionListener((ActionEvent e) -> {
            ctrl.navigateCard(pnlMain, true);
            updateStatus();
        });
        
        for (Component comp : pnlMain.getComponents()) {
            comp.addComponentListener(new ComponentAdapter(){
                @Override
                public void componentShown(ComponentEvent ce) {
                    updateStatus();
                }

                @Override
                public void componentHidden(ComponentEvent ce) {
                    updateStatus();
                }
                
                
                
            });
        }
    }

    public Lang getLang() {
        return lang;
    }

    public void setLang(Lang lang) {
        this.lang = lang;
        pnlSearch.setLang(lang);
        pnlProductList.setLang(lang);
        pnlDetails.setLang(lang);
    }

    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlHeader = new javax.swing.JPanel();
        btnNext = new com.swingx.Button();
        btnBack = new com.swingx.Button();
        jSeparator1 = new javax.swing.JSeparator();
        pnlMain = new javax.swing.JPanel();
        pnlWelcome = new com.ultramotor.ui.khachhang.WelcomePanel();
        pnlSearch = new com.ultramotor.ui.khachhang.SearchPanel();
        pnlProductList = new com.ultramotor.ui.khachhang.ProductListPanel();
        pnlDetails = new com.ultramotor.ui.khachhang.ProductDetailsPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlHeader.setBackground(new java.awt.Color(255, 255, 255));
        pnlHeader.setMaximumSize(new java.awt.Dimension(32767, 80));
        pnlHeader.setPreferredSize(new java.awt.Dimension(1115, 80));

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
                .addContainerGap(1243, Short.MAX_VALUE))
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pnlHeaderLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnBack, btnNext});

        pnlHeaderLayout.setVerticalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlHeaderLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnBack, btnNext});

        getContentPane().add(pnlHeader, java.awt.BorderLayout.NORTH);

        pnlMain.setMinimumSize(new java.awt.Dimension(500, 355));
        pnlMain.setLayout(new java.awt.CardLayout());

        pnlWelcome.setName("ProductList"); // NOI18N
        pnlMain.add(pnlWelcome, "card4");
        pnlMain.add(pnlSearch, "card4");
        pnlMain.add(pnlProductList, "ProductList");
        pnlMain.add(pnlDetails, "ProductDetails");

        getContentPane().add(pnlMain, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(1416, 662));
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
                if ("Windows".equals(info.getName())) {
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
    private javax.swing.JSeparator jSeparator1;
    private com.ultramotor.ui.khachhang.ProductDetailsPanel pnlDetails;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlMain;
    private com.ultramotor.ui.khachhang.ProductListPanel pnlProductList;
    private com.ultramotor.ui.khachhang.SearchPanel pnlSearch;
    private com.ultramotor.ui.khachhang.WelcomePanel pnlWelcome;
    // End of variables declaration//GEN-END:variables

}
