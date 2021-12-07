package com.ultramotor.ui.login;

import com.swingx.PasswordField;
import com.swingx.TextField;
import com.ultramotor.dao.NhanVienDAO;
import com.ultramotor.entity.NhanVien;
import com.ultramotor.ui.MainFrame;
import com.ultramotor.util.Auth;
import com.ultramotor.util.MsgBox;
import com.ultramotor.util.MyVerifier;
import com.ultramotor.util.XMail;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

public class DangNhapJFrame extends javax.swing.JFrame {

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
        pictureBox1 = new com.swingx.PictureBox();
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
        btnSend = new com.swingx.Button();
        jLabel13 = new javax.swing.JLabel();
        lblBackToSignIn1 = new javax.swing.JLabel();
        txtEmail = new com.swingx.TextField();
        lblTiltleDLMK2 = new javax.swing.JLabel();
        pnlOTP = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        lblBackToSignIn2 = new javax.swing.JLabel();
        pnlNhapOTP = new com.ultramotor.ui.login.PanelOTP();
        lblTiltleDLMK1 = new javax.swing.JLabel();
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

        pictureBox1.setImage(new javax.swing.ImageIcon(getClass().getResource("/ultramotor/image/login.jpg"))); // NOI18N

        javax.swing.GroupLayout pnlAnhLayout = new javax.swing.GroupLayout(pnlAnh);
        pnlAnh.setLayout(pnlAnhLayout);
        pnlAnhLayout.setHorizontalGroup(
            pnlAnhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pictureBox1, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );
        pnlAnhLayout.setVerticalGroup(
            pnlAnhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pictureBox1, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
        );

        pnlMain.setLayout(new java.awt.CardLayout());

        pnlDangNhap.setBackground(new java.awt.Color(255, 255, 255));

        lblUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ultramotor/icon/icons8-user.png"))); // NOI18N

        lblPassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ultramotor/icon/icons8-lock.png"))); // NOI18N

        lblQuenMatKhau.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lblQuenMatKhau.setForeground(new java.awt.Color(46, 211, 151));
        lblQuenMatKhau.setText("FORGOT YOUR PASSWORD?");

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(46, 211, 151));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Sign In");

        btnDangNhap.setBackground(new java.awt.Color(46, 211, 151));
        btnDangNhap.setForeground(new java.awt.Color(255, 255, 255));
        btnDangNhap.setText("Sign in");

        btnThoat.setBackground(new java.awt.Color(46, 211, 151));
        btnThoat.setForeground(new java.awt.Color(255, 255, 255));
        btnThoat.setText("Exit");

        txtTenDangNhap.setDisabledTextColor(new java.awt.Color(125, 125, 125));
        txtTenDangNhap.setLabelText("Your ID");
        txtTenDangNhap.setLineColor(new java.awt.Color(46, 211, 151));

        pwdMatKhau.setDisabledTextColor(new java.awt.Color(125, 125, 125));
        pwdMatKhau.setLabelText("Password");
        pwdMatKhau.setLineColor(new java.awt.Color(46, 211, 151));

        javax.swing.GroupLayout pnlDangNhapLayout = new javax.swing.GroupLayout(pnlDangNhap);
        pnlDangNhap.setLayout(pnlDangNhapLayout);
        pnlDangNhapLayout.setHorizontalGroup(
            pnlDangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDangNhapLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlDangNhapLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(lblUser, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(txtTenDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlDangNhapLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(pnlDangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlDangNhapLayout.createSequentialGroup()
                        .addComponent(btnDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblQuenMatKhau)
                    .addGroup(pnlDangNhapLayout.createSequentialGroup()
                        .addComponent(lblPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(pwdMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        pnlDangNhapLayout.setVerticalGroup(
            pnlDangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDangNhapLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlDangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDangNhapLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(lblUser, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtTenDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(pnlDangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDangNhapLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(lblPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pwdMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(lblQuenMatKhau)
                .addGap(18, 18, 18)
                .addGroup(pnlDangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pnlMain.add(pnlDangNhap, "DangNhap");

        pnlNhapEmail.setBackground(new java.awt.Color(255, 255, 255));

        btnSend.setBackground(new java.awt.Color(46, 211, 151));
        btnSend.setForeground(new java.awt.Color(255, 255, 255));
        btnSend.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ultramotor/icon/icons8-sent.png"))); // NOI18N
        btnSend.setText("Send");
        btnSend.setActionCommand("Cài lại");
        btnSend.setAlignmentX(0.5F);
        btnSend.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Back to ?");

        lblBackToSignIn1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblBackToSignIn1.setForeground(new java.awt.Color(46, 211, 151));
        lblBackToSignIn1.setText("Sign in");

        txtEmail.setDisabledTextColor(new java.awt.Color(125, 125, 125));
        txtEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtEmail.setLabelText("Enter your email");
        txtEmail.setLineColor(new java.awt.Color(46, 211, 151));
        txtEmail.setName(""); // NOI18N

        lblTiltleDLMK2.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        lblTiltleDLMK2.setForeground(new java.awt.Color(46, 211, 151));
        lblTiltleDLMK2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTiltleDLMK2.setText("FORGOT YOUR PASSWORD?");

        javax.swing.GroupLayout pnlNhapEmailLayout = new javax.swing.GroupLayout(pnlNhapEmail);
        pnlNhapEmail.setLayout(pnlNhapEmailLayout);
        pnlNhapEmailLayout.setHorizontalGroup(
            pnlNhapEmailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNhapEmailLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93))
            .addGroup(pnlNhapEmailLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTiltleDLMK2, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE))
            .addGroup(pnlNhapEmailLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlNhapEmailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlNhapEmailLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(lblBackToSignIn1))
                    .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlNhapEmailLayout.setVerticalGroup(
            pnlNhapEmailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNhapEmailLayout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(lblTiltleDLMK2)
                .addGap(28, 28, 28)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(pnlNhapEmailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(lblBackToSignIn1))
                .addContainerGap(91, Short.MAX_VALUE))
        );

        pnlMain.add(pnlNhapEmail, "NhapEmail");

        pnlOTP.setBackground(new java.awt.Color(255, 255, 255));
        pnlOTP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Back to");

        lblBackToSignIn2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblBackToSignIn2.setForeground(new java.awt.Color(46, 211, 151));
        lblBackToSignIn2.setText("Sign in");

        lblTiltleDLMK1.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        lblTiltleDLMK1.setForeground(new java.awt.Color(46, 211, 151));
        lblTiltleDLMK1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTiltleDLMK1.setText("FORGOT YOUR PASSWORD?");

        javax.swing.GroupLayout pnlOTPLayout = new javax.swing.GroupLayout(pnlOTP);
        pnlOTP.setLayout(pnlOTPLayout);
        pnlOTPLayout.setHorizontalGroup(
            pnlOTPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTiltleDLMK1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlOTPLayout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addGroup(pnlOTPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlOTPLayout.createSequentialGroup()
                        .addComponent(pnlNhapOTP, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlOTPLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(lblBackToSignIn2)
                        .addGap(156, 156, 156))))
        );
        pnlOTPLayout.setVerticalGroup(
            pnlOTPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlOTPLayout.createSequentialGroup()
                .addContainerGap(126, Short.MAX_VALUE)
                .addComponent(lblTiltleDLMK1)
                .addGap(18, 18, 18)
                .addComponent(pnlNhapOTP, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlOTPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lblBackToSignIn2))
                .addGap(87, 87, 87))
        );

        pnlMain.add(pnlOTP, "NhapOTP");

        pnlDatLaiMK.setBackground(new java.awt.Color(255, 255, 255));

        lblTiltleDLMK.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        lblTiltleDLMK.setForeground(new java.awt.Color(46, 211, 151));
        lblTiltleDLMK.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTiltleDLMK.setText("FORGOT YOUR PASSWORD?");

        btnDLMK.setBackground(new java.awt.Color(46, 211, 151));
        btnDLMK.setForeground(new java.awt.Color(255, 255, 255));
        btnDLMK.setText("Confirm");
        btnDLMK.setActionCommand("Cài lại");
        btnDLMK.setAlignmentX(0.5F);
        btnDLMK.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Back to?");

        lblBackToSignIn3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblBackToSignIn3.setForeground(new java.awt.Color(46, 211, 151));
        lblBackToSignIn3.setText("Sign in");

        pwdMatKhau2.setDisabledTextColor(new java.awt.Color(125, 125, 125));
        pwdMatKhau2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pwdMatKhau2.setLabelText(" Confirm new password");
        pwdMatKhau2.setLineColor(new java.awt.Color(46, 211, 151));

        pwdMatKhau1.setDisabledTextColor(new java.awt.Color(125, 125, 125));
        pwdMatKhau1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pwdMatKhau1.setLabelText("Enter your new password");
        pwdMatKhau1.setLineColor(new java.awt.Color(46, 211, 151));

        javax.swing.GroupLayout pnlDatLaiMKLayout = new javax.swing.GroupLayout(pnlDatLaiMK);
        pnlDatLaiMK.setLayout(pnlDatLaiMKLayout);
        pnlDatLaiMKLayout.setHorizontalGroup(
            pnlDatLaiMKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatLaiMKLayout.createSequentialGroup()
                .addContainerGap(114, Short.MAX_VALUE)
                .addGroup(pnlDatLaiMKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlDatLaiMKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(pwdMatKhau1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                        .addComponent(pwdMatKhau2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlDatLaiMKLayout.createSequentialGroup()
                        .addGroup(pnlDatLaiMKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlDatLaiMKLayout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblBackToSignIn3))
                            .addComponent(btnDLMK, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(52, 52, 52)))
                .addContainerGap(111, Short.MAX_VALUE))
            .addComponent(lblTiltleDLMK, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
        );
        pnlDatLaiMKLayout.setVerticalGroup(
            pnlDatLaiMKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatLaiMKLayout.createSequentialGroup()
                .addContainerGap(78, Short.MAX_VALUE)
                .addComponent(lblTiltleDLMK)
                .addGap(50, 50, 50)
                .addComponent(pwdMatKhau1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pwdMatKhau2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDLMK, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlDatLaiMKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(lblBackToSignIn3))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        pnlMain.add(pnlDatLaiMK, "DatLaiMK");

        javax.swing.GroupLayout pnlBackgroundLayout = new javax.swing.GroupLayout(pnlBackground);
        pnlBackground.setLayout(pnlBackgroundLayout);
        pnlBackgroundLayout.setHorizontalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                .addComponent(pnlAnh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlBackgroundLayout.setVerticalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlAnh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

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
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblQuenMatKhau;
    private javax.swing.JLabel lblTiltleDLMK;
    private javax.swing.JLabel lblTiltleDLMK1;
    private javax.swing.JLabel lblTiltleDLMK2;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblUser;
    private com.swingx.PictureBox pictureBox1;
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
        setFieldName();
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
            if (validateField(txtTenDangNhap, pwdMatKhau)) {
                dangNhap();
            }
        });

        btnSend.addActionListener((ActionEvent e) -> {
            if (validateField(txtEmail)) {
                sendEmail();
            }
        });
        
        btnDLMK.addActionListener((ActionEvent e) -> {
            if (validateField(pwdMatKhau1, pwdMatKhau2)) {
                changePW();
            }
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

    private boolean validateField(JTextField... fields) {
        for (JTextField field : fields) {
            if (!MyVerifier.DANG_NHAP_VERIFIER.verify(field)) {
                String err = "Vui lòng kiểm tra lại dữ liệu";
                if (field instanceof TextField) {
                    err = ((TextField) field).getErrorText();
                } else if (field instanceof PasswordField) {
                    err = ((TextField) field).getErrorText();
                }
                MsgBox.error(err);
                return false;
            }
        }
        return true;
    }

    private void setFieldName() {
        txtEmail.setName("Email");
        txtTenDangNhap.setName("Tên đăng nhập");
        pwdMatKhau.setName("Mật Khẩu");
        pwdMatKhau1.setName("Mật Khẩu");
        pwdMatKhau2.setName("Xác nhận mật khẩu");
        setFieldVerifier(txtEmail, txtTenDangNhap, pwdMatKhau, pwdMatKhau1, pwdMatKhau2);
    }

    private void setFieldVerifier(JTextComponent... comp) {
        for (JTextComponent field : comp) {
            field.setInputVerifier(MyVerifier.DANG_NHAP_VERIFIER);
        }
    }
}
