package com.ultramotor.ui;

import com.swingx.PasswordField;
import com.swingx.TextField;
import com.ultramotor.dao.NhanVienDAO;
import com.ultramotor.entity.NhanVien;
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
        pnlMain = new javax.swing.JPanel();
        pictureBox1 = new com.swingx.PictureBox();
        btnDangNhap = new com.swingx.Button();
        btnThoat = new com.swingx.Button();
        lblQuenMatKhau = new javax.swing.JLabel();
        txtTenDangNhap = new com.swingx.TextField();
        pwdMatKhau = new com.swingx.PasswordField();
        pictureBox2 = new com.swingx.PictureBox();
        txtEmail = new com.swingx.TextField();
        jLabel13 = new javax.swing.JLabel();
        lblBackToSignIn1 = new javax.swing.JLabel();
        btnSend = new com.swingx.Button();
        pnlOTP = new javax.swing.JPanel();
        lblTitleOTP = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblBackToSignIn2 = new javax.swing.JLabel();
        pnlNhapOTP = new com.ultramotor.ui.login.PanelOTP();
        pictureBox3 = new com.swingx.PictureBox();
        pwdMatKhau1 = new com.swingx.PasswordField();
        pwdMatKhau2 = new com.swingx.PasswordField();
        jLabel10 = new javax.swing.JLabel();
        lblBackToSignIn3 = new javax.swing.JLabel();
        btnDLMK = new com.swingx.Button();

        btnDangNhap1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnDangNhap1.setText("Đăng nhập");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("UltraMotor - Đăng Nhập");

        pnlMain.setLayout(new java.awt.CardLayout());

        pictureBox1.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/ultramotor/img/LOGIN.jpg"))); // NOI18N

        btnDangNhap.setText("Đăng nhập");
        btnDangNhap.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangNhapActionPerformed(evt);
            }
        });
        pictureBox1.add(btnDangNhap);
        btnDangNhap.setBounds(360, 440, 121, 42);

        btnThoat.setText("Thoát");
        btnThoat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });
        pictureBox1.add(btnThoat);
        btnThoat.setBounds(510, 440, 119, 43);

        lblQuenMatKhau.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblQuenMatKhau.setText("Quên mật khẩu?");
        pictureBox1.add(lblQuenMatKhau);
        lblQuenMatKhau.setBounds(490, 390, 118, 17);

        txtTenDangNhap.setBackground(new java.awt.Color(255, 255, 255));
        txtTenDangNhap.setForeground(new java.awt.Color(46, 211, 151));
        txtTenDangNhap.setAnimateLabel(false);
        txtTenDangNhap.setDisabledTextColor(new java.awt.Color(46, 211, 151));
        txtTenDangNhap.setDrawLine(true);
        txtTenDangNhap.setLabelText("");
        txtTenDangNhap.setLineColor(new java.awt.Color(46, 211, 151));
        pictureBox1.add(txtTenDangNhap);
        txtTenDangNhap.setBounds(400, 190, 210, 80);

        pwdMatKhau.setBackground(new java.awt.Color(46, 211, 151));
        pwdMatKhau.setForeground(new java.awt.Color(255, 255, 255));
        pwdMatKhau.setToolTipText("");
        pwdMatKhau.setAnimateLabel(false);
        pwdMatKhau.setDrawLine(true);
        pwdMatKhau.setLabelText("");
        pwdMatKhau.setLineColor(new java.awt.Color(255, 255, 255));
        pictureBox1.add(pwdMatKhau);
        pwdMatKhau.setBounds(400, 310, 220, 80);

        pnlMain.add(pictureBox1, "DangNhap");

        pictureBox2.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/ultramotor/img/LOGIN2.jpg"))); // NOI18N

        txtEmail.setBackground(new java.awt.Color(46, 211, 151));
        txtEmail.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtEmail.setDrawLine(false);
        txtEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtEmail.setLabelText("Nhập email");
        txtEmail.setLineColor(new java.awt.Color(255, 255, 255));
        txtEmail.setSelectionColor(new java.awt.Color(255, 255, 255));
        pictureBox2.add(txtEmail);
        txtEmail.setBounds(370, 310, 260, 87);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Bạn có thể quay lại ?");
        pictureBox2.add(jLabel13);
        jLabel13.setBounds(380, 470, 150, 17);

        lblBackToSignIn1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblBackToSignIn1.setForeground(new java.awt.Color(255, 255, 255));
        lblBackToSignIn1.setText("Đăng nhập");
        pictureBox2.add(lblBackToSignIn1);
        lblBackToSignIn1.setBounds(540, 470, 75, 17);

        btnSend.setText("Xác nhận");
        btnSend.setActionCommand("Cài lại");
        btnSend.setAlignmentX(0.5F);
        btnSend.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pictureBox2.add(btnSend);
        btnSend.setBounds(440, 400, 107, 48);

        pnlMain.add(pictureBox2, "NhapEmail");

        pnlOTP.setBackground(new java.awt.Color(255, 255, 255));
        pnlOTP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblTitleOTP.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblTitleOTP.setForeground(new java.awt.Color(0, 153, 255));
        lblTitleOTP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitleOTP.setText("Đặt lại mật khẩu");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Bạn có thể quay lại");

        lblBackToSignIn2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblBackToSignIn2.setForeground(new java.awt.Color(0, 153, 255));
        lblBackToSignIn2.setText("Đăng nhập");

        pnlNhapOTP.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout pnlOTPLayout = new javax.swing.GroupLayout(pnlOTP);
        pnlOTP.setLayout(pnlOTPLayout);
        pnlOTPLayout.setHorizontalGroup(
            pnlOTPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOTPLayout.createSequentialGroup()
                .addContainerGap(319, Short.MAX_VALUE)
                .addGroup(pnlOTPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlNhapOTP, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlOTPLayout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(lblBackToSignIn2))
                    .addComponent(lblTitleOTP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(319, Short.MAX_VALUE))
        );
        pnlOTPLayout.setVerticalGroup(
            pnlOTPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOTPLayout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addComponent(lblTitleOTP)
                .addGap(18, 18, 18)
                .addComponent(pnlNhapOTP, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlOTPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lblBackToSignIn2))
                .addContainerGap(174, Short.MAX_VALUE))
        );

        pnlMain.add(pnlOTP, "NhapOTP");

        pictureBox3.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/ultramotor/img/LOGIN2.jpg"))); // NOI18N

        pwdMatKhau1.setBackground(new java.awt.Color(46, 211, 151));
        pwdMatKhau1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        pwdMatKhau1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pwdMatKhau1.setLabelText("Nhập mật khẩu mới");
        pictureBox3.add(pwdMatKhau1);
        pwdMatKhau1.setBounds(360, 310, 260, 87);

        pwdMatKhau2.setBackground(new java.awt.Color(46, 211, 151));
        pwdMatKhau2.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        pwdMatKhau2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pwdMatKhau2.setLabelText("Nhập lại mật khẩu mới");
        pictureBox3.add(pwdMatKhau2);
        pwdMatKhau2.setBounds(360, 410, 260, 87);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Bạn có thể quay lại");
        pictureBox3.add(jLabel10);
        jLabel10.setBounds(380, 570, 128, 17);

        lblBackToSignIn3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblBackToSignIn3.setForeground(new java.awt.Color(255, 255, 255));
        lblBackToSignIn3.setText("Đăng nhập");
        pictureBox3.add(lblBackToSignIn3);
        lblBackToSignIn3.setBounds(520, 570, 75, 17);

        btnDLMK.setText("Xác nhận");
        btnDLMK.setActionCommand("Cài lại");
        btnDLMK.setAlignmentX(0.5F);
        btnDLMK.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDLMK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDLMKActionPerformed(evt);
            }
        });
        pictureBox3.add(btnDLMK);
        btnDLMK.setBounds(430, 510, 107, 48);

        pnlMain.add(pictureBox3, "DatLaiMK");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThoatActionPerformed

    private void btnDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangNhapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDangNhapActionPerformed

    private void btnDLMKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDLMKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDLMKActionPerformed

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
    private javax.swing.JLabel lblQuenMatKhau;
    private javax.swing.JLabel lblTitleOTP;
    private com.swingx.PictureBox pictureBox1;
    private com.swingx.PictureBox pictureBox2;
    private com.swingx.PictureBox pictureBox3;
    private javax.swing.JPanel pnlMain;
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
