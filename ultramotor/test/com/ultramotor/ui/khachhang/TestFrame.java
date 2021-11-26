/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ultramotor.ui.khachhang;

import com.swingx.CloseButton;
import com.ultramotor.entity.NhanVien;
import com.ultramotor.util.XDate;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JDialog;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

public class TestFrame extends javax.swing.JFrame {
    
    public TestFrame() {
        initComponents();
        NhanVien nv = new NhanVien("PS1900",
                "Lê", "Thanh Tú", XDate.parse("24-10-1999"),
                true,
                "Hồ Chí Minh",
                "0921850113",
                "tultps18884@fpt.edu.vn",
                5000000d,
                "",
                "Nhân Viên",
                "",
                "");
//        btn1.addActionListener((ActionEvent e) -> {
////            JDialog dialog = getDialog(new SendMailPanel());
//            NhanVienInfoPanel panel = new NhanVienInfoPanel();
//            panel.setForm(nv);
//            JDialog dialog = getDialog(panel);
//
//            dialog.setVisible(true);
//        });

//        btn2.addActionListener((ActionEvent e) -> {
//            JDialog dialog = getDialog(new SendMailPanel());
//
//            dialog.setVisible(true);
//        });
//        pnlInfo.setForm(nv);
//        this.productListPanel1.setList(new ArrayList<>());
    }
    
    private JDialog getDialog(JPanel panel) {
        JDialog dialog = new JDialog(this, true);
        dialog.setUndecorated(true);
        dialog.setBackground(new Color(255, 255, 255, 0));
        JPanel con = new JPanel() {
            @Override
            public void paint(Graphics grphcs) {
                Graphics2D g2 = (Graphics2D) grphcs;
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), 5, 5);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                super.paint(grphcs);
            }
        };
        
        con.setOpaque(false);
        con.setBackground(new Color(250, 250, 250));
        con.setLayout(new MigLayout("inset 5 20 20 5", "[center]", "[20!][fill, center, grow]"));
        con.add(new CloseButton(), "al right, wrap");
        con.add(panel, "pushy, center, gapright 15");
//        dialog.setBounds(this.getWidth() / 2, this.getHeight(), this.getWidth() / 4, 0);

        dialog.setSize(this.getWidth() / 2, this.getHeight());
        dialog.getContentPane().add(con);
        dialog.pack();
        
        dialog.setLocation(this.getWidth() / 4, (this.getHeight() - dialog.getHeight()) / 2);
        dialog.setLocationRelativeTo(this);
        
        return dialog;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn3 = new javax.swing.JButton();
        nhapKhoPanel2 = new com.ultramotor.ui.nhanvien.kho.nhapkho.NhapKhoPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btn3.setText("Button 3");
        btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3ActionPerformed(evt);
            }
        });
        getContentPane().add(btn3);
        getContentPane().add(nhapKhoPanel2);

        setSize(new java.awt.Dimension(1165, 712));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn3ActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TestFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TestFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TestFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TestFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TestFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn3;
    private com.ultramotor.ui.nhanvien.kho.nhapkho.NhapKhoPanel nhapKhoPanel2;
    // End of variables declaration//GEN-END:variables
}
