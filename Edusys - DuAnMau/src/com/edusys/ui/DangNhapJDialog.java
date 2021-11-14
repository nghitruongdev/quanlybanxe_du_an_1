package com.edusys.ui;

import com.edusys.components.MyPasswordField;
import com.edusys.components.MyTextField;
import com.edusys.dao.EduSysDAO;
import com.edusys.dao.NhanVienDAO;
import com.edusys.entity.NhanVien;
import com.edusys.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.InputVerifier;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

/**
 *
 * @author nghipc
 */
public class DangNhapJDialog extends javax.swing.JDialog {

    /**
     * Creates new form DangNhapJDialog
     */
    public DangNhapJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        init();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlBackground = new javax.swing.JPanel();
        pnlAnh = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblAnh = new javax.swing.JLabel();
        lblInfo = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();
        pnlMain = new javax.swing.JPanel();
        pnlSignin = new javax.swing.JPanel();
        lblSignin = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        lblForgetPW = new javax.swing.JLabel();
        btnSignin = new javax.swing.JButton();
        lblShowPW = new javax.swing.JLabel();
        txtUsername = new com.edusys.components.MyTextField();
        txtPassword = new com.edusys.components.MyPasswordField();
        lblUsernameError = new javax.swing.JLabel();
        lblPasswordError = new javax.swing.JLabel();
        pnlForget = new javax.swing.JPanel();
        lblForget = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        btnSend = new javax.swing.JButton();
        lblIconForget = new javax.swing.JLabel();
        lblBackToSignIn = new javax.swing.JLabel();
        txtEmail = new com.edusys.components.MyTextField();
        lblEmailError = new javax.swing.JLabel();
        btnClose = new com.edusys.components.CloseJButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("EduSys - Đăng nhập hệ quản trị");
        setModal(true);
        setUndecorated(true);

        pnlBackground.setBackground(new java.awt.Color(255, 255, 255));
        pnlBackground.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(70, 56, 97)));
        pnlBackground.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pnlBackground.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlAnh.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/img/log_poly_50px.png"))); // NOI18N
        pnlAnh.add(lblLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 270, -1));

        lblAnh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/img/classroom_100px.png"))); // NOI18N
        pnlAnh.add(lblAnh, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, -1, -1));

        lblInfo.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N
        lblInfo.setForeground(new java.awt.Color(255, 255, 255));
        lblInfo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInfo.setText("E-Learning System Management");
        pnlAnh.add(lblInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 270, 30));

        lblBackground.setBackground(new java.awt.Color(46, 50, 66));
        lblBackground.setMaximumSize(new java.awt.Dimension(1000, 1000));
        lblBackground.setMinimumSize(new java.awt.Dimension(300, 300));
        lblBackground.setOpaque(true);
        lblBackground.setPreferredSize(new java.awt.Dimension(300, 300));
        pnlAnh.add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 270, 380));

        pnlBackground.add(pnlAnh, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 380));

        pnlMain.setPreferredSize(new java.awt.Dimension(350, 0));
        pnlMain.setLayout(new java.awt.CardLayout());

        pnlSignin.setBackground(new java.awt.Color(255, 255, 255));
        pnlSignin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblSignin.setFont(new java.awt.Font("Microsoft YaHei Light", 1, 36)); // NOI18N
        lblSignin.setForeground(new java.awt.Color(89, 34, 144));
        lblSignin.setText("Sign In");
        pnlSignin.add(lblSignin, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        lblUsername.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/img/icon/user_filled_24px.png"))); // NOI18N
        pnlSignin.add(lblUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, 30));

        lblPassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/img/icon/password_filled_24px.png"))); // NOI18N
        pnlSignin.add(lblPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, 30));

        lblForgetPW.setFont(new java.awt.Font("Microsoft YaHei Light", 0, 14)); // NOI18N
        lblForgetPW.setForeground(new java.awt.Color(95, 17, 173));
        lblForgetPW.setText("Forgot Password?");
        lblForgetPW.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlSignin.add(lblForgetPW, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, -1, 30));

        btnSignin.setBackground(new java.awt.Color(102, 0, 204));
        btnSignin.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N
        btnSignin.setForeground(new java.awt.Color(255, 255, 255));
        btnSignin.setText("Sign in");
        btnSignin.setBorder(null);
        btnSignin.setBorderPainted(false);
        btnSignin.setContentAreaFilled(false);
        btnSignin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSignin.setHideActionText(true);
        btnSignin.setOpaque(true);
        pnlSignin.add(btnSignin, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 260, 104, 39));

        lblShowPW.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/img/icon/eye_18px.png"))); // NOI18N
        lblShowPW.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlSignin.add(lblShowPW, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 150, -1, 30));

        txtUsername.setDrawLine(true);
        txtUsername.setError(lblUsernameError);
        txtUsername.setPlaceholder("Enter your ID");
        pnlSignin.add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 230, -1));

        txtPassword.removeKeyListener(txtPassword.getKeyListeners()[0]);
        txtPassword.setDrawLine(true);
        txtPassword.setError(lblPasswordError);
        txtPassword.setFocusAccelerator('\u25cf');
        txtPassword.setPlaceholder("Enter your password");
        pnlSignin.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 230, -1));

        lblUsernameError.setText("jLabel1");
        pnlSignin.add(lblUsernameError, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 126, 220, -1));

        lblPasswordError.setText("jLabel2");
        pnlSignin.add(lblPasswordError, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, 220, -1));

        pnlMain.add(pnlSignin, "Signin");

        pnlForget.setBackground(new java.awt.Color(255, 255, 255));

        lblForget.setFont(new java.awt.Font("Microsoft YaHei Light", 1, 20)); // NOI18N
        lblForget.setForeground(new java.awt.Color(89, 34, 144));
        lblForget.setText("FORGOT YOUR PASSWORD?");

        lblEmail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEmail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/img/mailing_24px.png"))); // NOI18N

        btnSend.setBackground(new java.awt.Color(102, 0, 204));
        btnSend.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N
        btnSend.setForeground(new java.awt.Color(255, 255, 255));
        btnSend.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/img/paper_plane_16px.png"))); // NOI18N
        btnSend.setText("Send");
        btnSend.setBorder(null);
        btnSend.setBorderPainted(false);
        btnSend.setContentAreaFilled(false);
        btnSend.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSend.setHideActionText(true);
        btnSend.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        btnSend.setIconTextGap(10);
        btnSend.setOpaque(true);

        lblIconForget.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/img/forgot_password_48px.png"))); // NOI18N

        lblBackToSignIn.setFont(new java.awt.Font("Microsoft YaHei", 0, 14)); // NOI18N
        lblBackToSignIn.setForeground(new java.awt.Color(89, 34, 144));
        lblBackToSignIn.setText("Back to sign-in");
        lblBackToSignIn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        txtEmail.removeKeyListener(txtEmail.getKeyListeners()[0]);
        txtEmail.setDrawLine(true);
        txtEmail.setError(lblEmailError);
        txtEmail.setPlaceholder("Enter your email");

        lblEmailError.setText("jLabel1");

        javax.swing.GroupLayout pnlForgetLayout = new javax.swing.GroupLayout(pnlForget);
        pnlForget.setLayout(pnlForgetLayout);
        pnlForgetLayout.setHorizontalGroup(
            pnlForgetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlForgetLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlForgetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlForgetLayout.createSequentialGroup()
                        .addComponent(lblForget)
                        .addContainerGap(65, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlForgetLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlForgetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlForgetLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(lblEmailError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(btnSend, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(64, 64, 64))))
            .addGroup(pnlForgetLayout.createSequentialGroup()
                .addGroup(pnlForgetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlForgetLayout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addComponent(lblIconForget))
                    .addGroup(pnlForgetLayout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addComponent(lblBackToSignIn)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlForgetLayout.setVerticalGroup(
            pnlForgetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlForgetLayout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(lblForget, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlForgetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlForgetLayout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlForgetLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblIconForget)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEmailError)
                .addGap(20, 20, 20)
                .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(lblBackToSignIn)
                .addContainerGap())
        );

        pnlMain.add(pnlForget, "ForgetPW");

        pnlBackground.add(pnlMain, new org.netbeans.lib.awtextra.AbsoluteConstraints(297, 30, 310, 340));

        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/img/icon/close_black_24px.png"))); // NOI18N
        pnlBackground.add(btnClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 10, 20, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DangNhapJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DangNhapJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DangNhapJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DangNhapJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DangNhapJDialog dialog = new DangNhapJDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.edusys.components.CloseJButton btnClose;
    private javax.swing.JButton btnSend;
    private javax.swing.JButton btnSignin;
    private javax.swing.JLabel lblAnh;
    private javax.swing.JLabel lblBackToSignIn;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEmailError;
    private javax.swing.JLabel lblForget;
    private javax.swing.JLabel lblForgetPW;
    private javax.swing.JLabel lblIconForget;
    private javax.swing.JLabel lblInfo;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblPasswordError;
    private javax.swing.JLabel lblShowPW;
    private javax.swing.JLabel lblSignin;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JLabel lblUsernameError;
    private javax.swing.JPanel pnlAnh;
    private javax.swing.JPanel pnlBackground;
    private javax.swing.JPanel pnlForget;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlSignin;
    private com.edusys.components.MyTextField txtEmail;
    private com.edusys.components.MyPasswordField txtPassword;
    private com.edusys.components.MyTextField txtUsername;
    // End of variables declaration//GEN-END:variables

    private static DangNhapJDialog dialog;
    private JTextComponent[] fields;
    private EduSysDAO dao;
    private InputVerifier verifier;

    //trả về hộp thoại đăng nhập mặc định
    public static DangNhapJDialog getDialog() {
        if (dialog == null) {
            dialog = new DangNhapJDialog(EduSysFrame.getFrame(), true);
        }
        return dialog;
    }

    //khởi tạo các thành phần khác
    private void init() {
        fields = new JTextComponent[]{txtUsername, txtPassword, txtEmail};
        dao = EduSysDAO.NHAN_VIEN_DAO;
        verifier = MyVerifier.DANG_NHAP_VERIFIER;

        addListeners(); //thêm listener cho các thành phần
        setFieldName(); //đặt tên cho field

        //set InputVerifier cho field
        for (JTextComponent field : fields) {
            field.setInputVerifier(verifier);
        }
    }

    //thêm listener cho các thành phần
    private void addListeners() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent we) {
                clearForm();
            }
        });

        addBtnListeners();
        addLabelListeners();
        addFieldListeners();
    }

    //hiển thị card theo card name
    public void showCard(String name) {
        ((java.awt.CardLayout) pnlMain.getLayout()).show(pnlMain, name);
        if (!isVisible()) {
            setVisible(true);
        }
    }

    //kiểm tra login
    private void login() {
        //kiểm tra dữ liệu trên field login
        if (!verifyField(txtUsername, txtPassword)) {
            return;
        }

        NhanVien nv = (NhanVien) dao.selectById(txtUsername.getText()); //thực hiện truy vấn dữ liệu
        if (nv == null) { //username k đúng
            MsgBox.error("Tên đăng nhập không tồn tại");
        } else if (!nv.getMatKhau().equals(String.valueOf(txtPassword.getPassword()))) { //password không trùng khớp
            MsgBox.error("Vui lòng kiểm tra lại mật khẩu");
        } else {
            Auth.user = nv; //lưu phiên đăng nhập
            MsgBox.inform("Đăng nhập thành công!"); //xuất thông báo
            this.dispose(); //đóng form
            EduSysFrame.getFrame().setVisible(true); //mở frame EduSys
        }
    }

    //thêm listeners cho các button
    private void addBtnListeners() {
        btnClose.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent me) {
                btnClose.setIcon(XImage.getIcon("close_black_24px.png"));
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                btnClose.setIcon(XImage.getIcon("close_window_24px.png"));
            }
        });
        btnClose.addActionListener((ActionEvent e) -> {
            Auth.close();
        });

        btnSignin.addActionListener((ActionEvent e) -> {
            login();
        });

        btnSend.addActionListener((ActionEvent e) -> {
            sendEmail();
        });
    }

    //thêm listener cho label
    private void addLabelListeners() {
        lblForgetPW.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                showCard("ForgetPW");
            }
        });

        lblBackToSignIn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                showCard("Signin");
            }
        });

        lblShowPW.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                txtPassword.setEchoChar((char) 0);
                lblShowPW.setIcon(XImage.getIcon("hide_18px.png"));
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                txtPassword.setEchoChar('\u25cf');
                lblShowPW.setIcon(XImage.getIcon("eye_18px.png"));
            }

        });

    }

    //thêm listener cho field
    private void addFieldListeners() {
        for (JTextComponent field : fields) {
            ((JTextField) field).addActionListener((ActionEvent e) -> {
                switch (field.getName()) {
                    case "Email":
                        btnSend.doClick();
                        break;
                    case "Mật khẩu":
                        btnSignin.doClick();
                        break;
                }
            });
        }
    }

    //gửi mail khi người dùng quên mật khẩu
    private void sendEmail() {
        //kiểm tra địa chỉ email
        if (!verifyField(txtEmail)) {
            return;
        }
        //kiểm tra email trong database
        String email = txtEmail.getText();
        NhanVien nv = ((NhanVienDAO) dao).selectByEmail(email);

        if (nv == null) { //không tìm thấy
            MsgBox.error("Không tìm thấy địa chỉ Email");
        } else {
            Auth.user = nv; //lưu thông tin user
            Auth.isForgotPW = true; //đánh dấu là user quên pass

            //soạn nội dung mail với mã OTP
            String mailContent = "<div><p>Xin chào,</p>"
                    + "<p>Bạn nhận được email này vì bạn hoặc ai đó đã yêu cầu "
                    + "thay đổi mật khẩu cho tài khoản của bạn trong LapTrinhCity.</p>"
                    + "<p>Email này hoàn toàn có thể bỏ qua nếu bạn không yêu cầu thay đổi mật khẩu.</p>"
                    + "<p>Mã OTP của bạn là: " + String.format("<span style =\"color: red\">%s</span>", Auth.getNewOTP()) + "</p>"
                    + "<p>Vui lòng không chia sẻ mã OTP cho bất cứ ai.</p>"
                    + "<p>LapTrinhCity Technical Team.</p></div>";

            XMail.sendMail(email, mailContent, "[LapTrinhCity] KHÔI PHỤC MẬT KHẨU"); //gửi mail
            this.dispose(); //đóng form
            openChangePW(); //mở dialog đổi mật khẩu
        }
    }

    //mở dialog đổi mật khẩu, hiện card xác nhận mã OTP sau khi gửi mail
    private void openChangePW() {
        ChangePWJDialog dlg = new ChangePWJDialog(null, true);
        dlg.showCard("VerifyOTP");
    }

    //đặt form về mặc định
    private void clearForm() {
        for (JTextComponent field : fields) {
            field.setText("");
        }
    }

    //đặt tên cho field
    private void setFieldName() {
        txtEmail.setName("Email");
        txtUsername.setName("Tên đăng nhập");
        txtPassword.setName("Mật khẩu");
    }

    //kiểm lỗi dữ liệu trên field
    private boolean verifyField(JTextField... fields) {
        boolean valid = true;
        for (JTextField field : fields) {
            valid = verifier.verify(field); //dùng InputVerifier để verify
            if (!valid) { //nếu không hợp lệ, xuất thông báo lỗi
                String err = "";
                if (field instanceof MyTextField) {
                    err = ((MyTextField) field).getError().getText(); //lấy dữ liệu lỗi
                } else if (field instanceof MyPasswordField) {
                    err = ((MyPasswordField) field).getError().getText();  //lấy dữ liệu lỗi
                }
                MsgBox.error(err); //xuất thông báo
                field.requestFocus();
                break;
            }
        }
        return valid;
    }
}
