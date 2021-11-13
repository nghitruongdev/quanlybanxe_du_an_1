package com.edusys.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
import javax.swing.UIManager;

/**
 *
 * @author nghipc
 */
public class ChaoJDialog extends javax.swing.JDialog {

    /**
     * Creates new form ChaoJDialog
     */
    public ChaoJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        init();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlBackground = new javax.swing.JPanel();
        lblCompany = new javax.swing.JLabel();
        lblLoading = new javax.swing.JLabel();
        pgbLoading = new javax.swing.JProgressBar();
        lblLogo = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        pnlBackground.setBackground(new java.awt.Color(36, 39, 53));
        pnlBackground.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCompany.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        lblCompany.setForeground(new java.awt.Color(204, 204, 204));
        lblCompany.setText("LẬP TRÌNH CITY © 2021");
        pnlBackground.add(lblCompany, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 500, 250, 30));

        lblLoading.setFont(new java.awt.Font("Monospaced", 0, 11)); // NOI18N
        lblLoading.setForeground(new java.awt.Color(255, 255, 255));
        lblLoading.setText("Initializing....");
        lblLoading.setRequestFocusEnabled(false);
        lblLoading.setVerifyInputWhenFocusTarget(false);
        pnlBackground.add(lblLoading, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 510, 140, 20));

        pgbLoading.setFont(new java.awt.Font("Monospaced", 1, 8)); // NOI18N
        pgbLoading.setBorderPainted(false);
        pgbLoading.setStringPainted(true);
        pgbLoading.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                pgbLoadingStateChanged(evt);
            }
        });
        pnlBackground.add(pgbLoading, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 530, 900, 10));

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/img/logo_fpt_70px.png"))); // NOI18N
        pnlBackground.add(lblLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 20, -1, -1));

        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/img/background.gif"))); // NOI18N
        pnlBackground.add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 540));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlBackground, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBackground, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pgbLoadingStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_pgbLoadingStateChanged
        switch (pgbLoading.getValue()) {
            case 20:
                lblLoading.setText("Loading Components...");
                break;
            case 40:
                lblLoading.setText("Adding Modules...");
                break;
            case 60:
                lblLoading.setText("Loading Data...");
                break;
            case 80:
                lblLoading.setText("Almost Done...");
                break;
            case 100:
                t.stop();
                this.dispose();
        }
    }//GEN-LAST:event_pgbLoadingStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ChaoJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChaoJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChaoJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChaoJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

//        UIManager.put("ProgressBar.background", Color.BLACK);
        UIManager.put("ProgressBar.foreground", new Color(65,69,97));
        UIManager.put("ProgressBar.selectionBackground", new Color(58,120,188));
        UIManager.put("ProgressBar.selectionForeground", new Color(255, 255, 255));
        /* Create and display the dialog */
        ChaoJDialog dl = new ChaoJDialog(new javax.swing.JFrame(), true);
        dl.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblCompany;
    private javax.swing.JLabel lblLoading;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JProgressBar pgbLoading;
    private javax.swing.JPanel pnlBackground;
    // End of variables declaration//GEN-END:variables

    Timer t;

    private void init() {
        this.setLocationRelativeTo(null);
        //tạo đối tượng timer thay đổi value pgbBar
        t = new Timer(50, (ActionEvent e) -> {
            pgbLoading.setValue(pgbLoading.getValue() + 1);
        });
        t.start(); //bắt đầu timer
    }
}
