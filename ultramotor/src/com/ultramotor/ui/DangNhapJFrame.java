/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ultramotor.ui;

import com.ultramotor.dao.NhanVienDAO;
import com.ultramotor.entity.NhanVien;
import com.ultramotor.util.Auth;
import com.ultramotor.util.MsgBox;
import com.ultramotor.util.XMail;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import javax.swing.JTextField;

public class DangNhapJFrame extends javax.swing.JFrame {

    //
    public DangNhapJFrame() {
        initComponents();
        init();
        setLocationRelativeTo(null);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnDangNhap1 = new javax.swing.JButton();
        pnlBackground = new javax.swing.JPanel();
        pnlAnh = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        pnlMain = new javax.swing.JPanel();
        pnlDangNhap = new javax.swing.JPanel();
        lblUser = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        lblQuenMatKhau = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();
        btnDangNhap = new com.swingx.Button();
        btnThoat = new com.swingx.Button();
        txtTenDangNhap = new com.swingx.TextField();
        pwdMatKhau = new com.swingx.PasswordField();
        pnlNhapEmail = new javax.swing.JPanel();
        lblTitleEmail = new javax.swing.JLabel();
        btnSend = new com.swingx.Button();
        jLabel13 = new javax.swing.JLabel();
        lblBackToSignIn1 = new javax.swing.JLabel();
        txtEmail = new com.swingx.TextField();
        pnlOTP = new javax.swing.JPanel();
        lblTitleOTP = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblBackToSignIn2 = new javax.swing.JLabel();
        pnlNhapOTP = new com.ultramotor.ui.login.PanelOTP();
        pnlDatLaiMK = new javax.swing.JPanel();
        lblTiltleDLMK = new javax.swing.JLabel();
        btnDLMK = new com.swingx.Button();
        jLabel10 = new javax.swing.JLabel();
        lblBackToSignIn3 = new javax.swing.JLabel();
        pwdMatKhau2 = new com.swingx.PasswordField();
        pwdMatKhau1 = new com.swingx.PasswordField();

        btnDangNhap1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnDangNhap1.setText("Đăng nhập");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("UltraMotor - Đăng Nhập");

        pnlBackground.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/logo1.png"))); // NOI18N

        javax.swing.GroupLayout pnlAnhLayout = new javax.swing.GroupLayout(pnlAnh);
        pnlAnh.setLayout(pnlAnhLayout);
        pnlAnhLayout.setHorizontalGroup(
            pnlAnhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAnhLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(171, Short.MAX_VALUE))
        );
        pnlAnhLayout.setVerticalGroup(
            pnlAnhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 490, Short.MAX_VALUE)
        );

        pnlBackground.add(pnlAnh, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 490));

        pnlMain.setLayout(new java.awt.CardLayout());

        pnlDangNhap.setBackground(new java.awt.Color(255, 255, 255));

        lblUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/person-icon (1).png"))); // NOI18N

        lblPassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/lock-icon.png"))); // NOI18N

        lblQuenMatKhau.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblQuenMatKhau.setForeground(new java.awt.Color(0, 204, 204));
        lblQuenMatKhau.setText("Quên mật khẩu?");

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(102, 102, 102));
        lblTitle.setText("ĐĂNG NHẬP");

        btnDangNhap.setBackground(new java.awt.Color(0, 153, 255));
        btnDangNhap.setText("Đăng nhập");

        btnThoat.setBackground(new java.awt.Color(51, 153, 255));
        btnThoat.setText("Thoát");

        txtTenDangNhap.setLabelText("Tên đăng nhập");

        pwdMatKhau.setLabelText("Mật khẩu");

        javax.swing.GroupLayout pnlDangNhapLayout = new javax.swing.GroupLayout(pnlDangNhap);
        pnlDangNhap.setLayout(pnlDangNhapLayout);
        pnlDangNhapLayout.setHorizontalGroup(
            pnlDangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDangNhapLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(pnlDangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDangNhapLayout.createSequentialGroup()
                        .addGroup(pnlDangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblPassword)
                            .addComponent(lblUser))
                        .addGroup(pnlDangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlDangNhapLayout.createSequentialGroup()
                                .addGap(189, 189, 189)
                                .addComponent(lblQuenMatKhau))
                            .addGroup(pnlDangNhapLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txtTenDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDangNhapLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(pnlDangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pwdMatKhau, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDangNhapLayout.createSequentialGroup()
                                        .addComponent(btnDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6))))))
                    .addGroup(pnlDangNhapLayout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addComponent(lblTitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        pnlDangNhapLayout.setVerticalGroup(
            pnlDangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDangNhapLayout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlDangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDangNhapLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(lblUser))
                    .addGroup(pnlDangNhapLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtTenDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPassword)
                    .addComponent(pwdMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblQuenMatKhau)
                .addGap(37, 37, 37)
                .addGroup(pnlDangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(114, Short.MAX_VALUE))
        );

        pnlMain.add(pnlDangNhap, "DangNhap");

        pnlNhapEmail.setBackground(new java.awt.Color(255, 255, 255));

        lblTitleEmail.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitleEmail.setForeground(new java.awt.Color(0, 153, 255));
        lblTitleEmail.setText("Đặt lại mật khẩu");

        btnSend.setBackground(new java.awt.Color(0, 153, 255));
        btnSend.setText("Xác nhận");
        btnSend.setActionCommand("Cài lại");
        btnSend.setAlignmentX(0.5F);
        btnSend.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Bạn có thể quay lại");

        lblBackToSignIn1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblBackToSignIn1.setForeground(new java.awt.Color(0, 153, 255));
        lblBackToSignIn1.setText("Đăng nhập");

        txtEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtEmail.setLabelText("Nhập email");

        javax.swing.GroupLayout pnlNhapEmailLayout = new javax.swing.GroupLayout(pnlNhapEmail);
        pnlNhapEmail.setLayout(pnlNhapEmailLayout);
        pnlNhapEmailLayout.setHorizontalGroup(
            pnlNhapEmailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNhapEmailLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pnlNhapEmailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlNhapEmailLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblBackToSignIn1)))
                .addGap(93, 93, 93))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNhapEmailLayout.createSequentialGroup()
                .addContainerGap(131, Short.MAX_VALUE)
                .addGroup(pnlNhapEmailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNhapEmailLayout.createSequentialGroup()
                        .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(157, 157, 157))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNhapEmailLayout.createSequentialGroup()
                        .addComponent(lblTitleEmail)
                        .addGap(112, 112, 112))))
        );
        pnlNhapEmailLayout.setVerticalGroup(
            pnlNhapEmailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNhapEmailLayout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addComponent(lblTitleEmail)
                .addGap(61, 61, 61)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(pnlNhapEmailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(lblBackToSignIn1))
                .addContainerGap(112, Short.MAX_VALUE))
        );

        pnlMain.add(pnlNhapEmail, "NhapEmail");

        pnlOTP.setBackground(new java.awt.Color(255, 255, 255));
        pnlOTP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblTitleOTP.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitleOTP.setForeground(new java.awt.Color(0, 153, 255));
        lblTitleOTP.setText("Đặt lại mật khẩu");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Bạn có thể quay lại");

        lblBackToSignIn2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblBackToSignIn2.setForeground(new java.awt.Color(0, 153, 255));
        lblBackToSignIn2.setText("Đăng nhập");

        javax.swing.GroupLayout pnlOTPLayout = new javax.swing.GroupLayout(pnlOTP);
        pnlOTP.setLayout(pnlOTPLayout);
        pnlOTPLayout.setHorizontalGroup(
            pnlOTPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOTPLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(pnlOTPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlOTPLayout.createSequentialGroup()
                        .addGroup(pnlOTPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlNhapOTP, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlOTPLayout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(lblBackToSignIn2)))
                        .addGap(30, 30, 30))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlOTPLayout.createSequentialGroup()
                        .addComponent(lblTitleOTP)
                        .addGap(116, 116, 116))))
        );
        pnlOTPLayout.setVerticalGroup(
            pnlOTPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlOTPLayout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addComponent(lblTitleOTP)
                .addGap(30, 30, 30)
                .addComponent(pnlNhapOTP, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlOTPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lblBackToSignIn2))
                .addGap(152, 152, 152))
        );

        pnlMain.add(pnlOTP, "NhapOTP");

        pnlDatLaiMK.setBackground(new java.awt.Color(255, 255, 255));

        lblTiltleDLMK.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTiltleDLMK.setForeground(new java.awt.Color(0, 153, 255));
        lblTiltleDLMK.setText("Đặt lại mật khẩu");

        btnDLMK.setBackground(new java.awt.Color(0, 153, 255));
        btnDLMK.setText("Xác nhận");
        btnDLMK.setActionCommand("Cài lại");
        btnDLMK.setAlignmentX(0.5F);
        btnDLMK.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Bạn có thể quay lại");

        lblBackToSignIn3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblBackToSignIn3.setForeground(new java.awt.Color(0, 153, 255));
        lblBackToSignIn3.setText("Đăng nhập");

        pwdMatKhau2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pwdMatKhau2.setLabelText("Nhập lại mật khẩu mới");

        pwdMatKhau1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pwdMatKhau1.setLabelText("Nhập mật khẩu mới");

        javax.swing.GroupLayout pnlDatLaiMKLayout = new javax.swing.GroupLayout(pnlDatLaiMK);
        pnlDatLaiMK.setLayout(pnlDatLaiMKLayout);
        pnlDatLaiMKLayout.setHorizontalGroup(
            pnlDatLaiMKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDatLaiMKLayout.createSequentialGroup()
                .addContainerGap(129, Short.MAX_VALUE)
                .addGroup(pnlDatLaiMKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDatLaiMKLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(btnDLMK, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlDatLaiMKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(pnlDatLaiMKLayout.createSequentialGroup()
                            .addComponent(jLabel10)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblBackToSignIn3))
                        .addComponent(pwdMatKhau2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblTiltleDLMK)
                        .addComponent(pwdMatKhau1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(110, 110, 110))
        );
        pnlDatLaiMKLayout.setVerticalGroup(
            pnlDatLaiMKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatLaiMKLayout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(lblTiltleDLMK)
                .addGap(18, 18, 18)
                .addComponent(pwdMatKhau1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pwdMatKhau2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDLMK, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlDatLaiMKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(lblBackToSignIn3))
                .addContainerGap(111, Short.MAX_VALUE))
        );

        pnlMain.add(pnlDatLaiMK, "DatLaiMK");

        pnlBackground.add(pnlMain, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 0, 430, 490));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(pnlBackground, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(pnlBackground, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DangNhapJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DangNhapJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DangNhapJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DangNhapJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DangNhapJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.swingx.Button btnDLMK;
    private com.swingx.Button btnDangNhap;
    private javax.swing.JButton btnDangNhap1;
    private com.swingx.Button btnSend;
    private com.swingx.Button btnThoat;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel lblBackToSignIn1;
    private javax.swing.JLabel lblBackToSignIn2;
    private javax.swing.JLabel lblBackToSignIn3;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblQuenMatKhau;
    private javax.swing.JLabel lblTiltleDLMK;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTitleEmail;
    private javax.swing.JLabel lblTitleOTP;
    private javax.swing.JLabel lblUser;
    private javax.swing.JPanel pnlAnh;
    private javax.swing.JPanel pnlBackground;
    private javax.swing.JPanel pnlDangNhap;
    private javax.swing.JPanel pnlDatLaiMK;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlNhapEmail;
    private com.ultramotor.ui.login.PanelOTP pnlNhapOTP;
    private javax.swing.JPanel pnlOTP;
    private com.swingx.PasswordField pwdMatKhau;
    private com.swingx.PasswordField pwdMatKhau1;
    private com.swingx.PasswordField pwdMatKhau2;
    private com.swingx.TextField txtEmail;
    private com.swingx.TextField txtTenDangNhap;
    // End of variables declaration//GEN-END:variables
    NhanVienDAO dao = new NhanVienDAO();

    private void init() {
        this.setLocationRelativeTo(null);
        addLabelListeners();
        addBtnListeners();
    }

    void dangNhap() {
        new Thread(() -> {
            String manv = txtTenDangNhap.getText();
            String matKhau = new String(pwdMatKhau.getPassword());
            try {
                NhanVien nhanVien = dao.selectByID(manv);
                if (nhanVien != null) {
                    String matKhau2 = nhanVien.getMatKhau();
                    if (matKhau.equals(matKhau2)) {
                        Auth.user = nhanVien;
                        MsgBox.inform("Đăng nhập thành công!");
                        dispose();
                        MainFrame main = new MainFrame();
                        main.setVisible(true);
                    } else {
                        MsgBox.inform("Sai mật khẩu!");
                    }
                } else {
                    MsgBox.inform("Sai tên đăng nhập!");
                }
            } catch (Exception e) {
                MsgBox.inform("Đăng nhập thất bại!");
            }
        }).start();
    }

    void ketThuc() {
        if (MsgBox.confirm("Bạn muốn kết thúc ứng dụng?", false) == 0) {
            System.exit(0);
        }
    }

    //hiển thị card theo card name
    public void showCard(String name) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    Thread.sleep(300);
                    ((java.awt.CardLayout) pnlMain.getLayout()).show(pnlMain, name);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }).start();
        reset((JComponent) this.getContentPane());
//        if (!isVisible()) {
//            setVisible(true);
//        }
    }

    //thêm listeners cho các button
    private void addBtnListeners() {

        btnThoat.addActionListener((ActionEvent e) -> {
            Auth.close();
        });

        btnDangNhap.addActionListener((ActionEvent e) -> {
            dangNhap();
        });

        btnSend.addActionListener((ActionEvent e) -> {
            sendEmail();
        });
        btnDLMK.addActionListener((ActionEvent e) -> {
            changePW();
        });
        pnlNhapOTP.getBtnConfirm().addActionListener((ActionEvent e) -> {
            checkOTP();
        });
    }

    //thêm listener cho label
    private void addLabelListeners() {
        lblQuenMatKhau.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                showCard("NhapEmail");
            }
        });

        lblBackToSignIn1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                showCard("DangNhap");
            }
        });
        lblBackToSignIn2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                showCard("DangNhap");
            }
        });
        lblBackToSignIn3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                showCard("DangNhap");
            }
        });

    }

    //gửi mail khi người dùng quên mật khẩu
    private void sendEmail() {
        //kiểm tra email trong database
        String email = txtEmail.getText();
        NhanVien nv = ((NhanVienDAO) dao).selectByEmail(email);

        if (nv == null) { //không tìm thấy
            MsgBox.error("Không tìm thấy địa chỉ Email");
        } else {
            Auth.user = nv; //lưu thông tin user
        Auth.forgotPW = true; //đánh dấu là user quên pass
        //soạn nội dung mail với mã OTP
        String mailContent = "<div><p>Xin chào,</p>"
                + "<p>Bạn nhận được email này vì bạn hoặc ai đó đã yêu cầu "
                + "thay đổi mật khẩu cho tài khoản của bạn trong LapTrinhCity.</p>"
                + "<p>Email này hoàn toàn có thể bỏ qua nếu bạn không yêu cầu thay đổi mật khẩu.</p>"
                + "<p>Mã OTP của bạn là: " + String.format("<span style =\"color: red\">%s</span>", Auth.getNewOTP()) + "</p>"
                + "<p>Vui lòng không chia sẻ mã OTP cho bất cứ ai.</p>"
                + "<p>UltraMotor System Team.</p></div>";

        pnlNhapOTP.getBtnResend().addActionListener((ActionEvent e) -> {
            XMail.sendMail(email, mailContent, "[UltraMotor] KHÔI PHỤC MẬT KHẨU"); //gửi mail
        });
        showCard("NhapOTP");
        pnlNhapOTP.getBtnResend().doClick();
        }
    }

    private void checkOTP() {
        if (Auth.getOTP().equals(pnlNhapOTP.getText())) {
            showCard("DatLaiMK");
            MsgBox.inform("Xác thực OTP thành công!");
        } else {
            MsgBox.error("Mã OTP không trùng khớp! Vui lòng thử lại");
//            showCard("DangNhap");
        }
    }

    //thay đổi password của user
    private void changePW() {
        String pw = String.valueOf(pwdMatKhau1.getPassword()); //lấy pw 
        String verify = String.valueOf(pwdMatKhau2.getPassword()); //lấy xác nhận pw

        if (!pw.equals(verify)) { // nếu không trùng khớp, thông báo lỗi
            MsgBox.error("Mật khẩu không trùng khớp! Vui lòng kiểm tra lại.");
        } else {
            Auth.user.setMatKhau(verify); //thay đổi pw user
            new NhanVienDAO().update(Auth.user); //thay đổi trong CSDL
            MsgBox.inform("Cập nhật mật khẩu thành công"); //thông báo 
            showCard("DangNhap"); //mở form đăng nhập, hiện card signin
        }

    }

    private void reset(JComponent comp) {
        for (Component c : comp.getComponents()) {
            if (c instanceof JTextField) {
                ((JTextField) c).setText("");
            } else if (c instanceof JComponent) {
                reset((JComponent) c);
            }
        }
    }

}
