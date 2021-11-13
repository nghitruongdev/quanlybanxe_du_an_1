package com.edusys.ui;

import com.edusys.dao.NhanVienDAO;
import com.edusys.util.Auth;
import com.edusys.util.MsgBox;
import com.edusys.util.MyVerifier;
import com.edusys.util.XListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.InputVerifier;
import javax.swing.text.JTextComponent;

/**
 *
 * @author nghipc
 */
public class ChangePWJDialog extends javax.swing.JDialog {

    /**
     * Creates new form ChangeJDialog
     */
    public ChangePWJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        init();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTitleBar = new com.edusys.components.MyTitleBar();
        pnlMain = new javax.swing.JPanel();
        pnlChangePW = new javax.swing.JPanel();
        lblNewPW = new javax.swing.JLabel();
        lblVerifyPW = new javax.swing.JLabel();
        btnChange = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        txtMatKhau = new com.edusys.components.MyPasswordField();
        txtXacNhan = new com.edusys.components.MyPasswordField();
        lblErrorMatKhau = new javax.swing.JLabel();
        lblErrorXacNhan = new javax.swing.JLabel();
        pnlOTP = new com.edusys.components.MyOtpPanel();
        pnlCurrentPW = new javax.swing.JPanel();
        lblCurrent = new javax.swing.JLabel();
        btnVerifyCurrent = new javax.swing.JButton();
        lblIconCurrent = new javax.swing.JLabel();
        txtCurrent = new com.edusys.components.MyPasswordField();
        lblErrorCurrent = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setModal(true);
        setUndecorated(true);

        pnlTitleBar.setTitle("EDUSYS - MẬT KHẨU");

        pnlMain.setLayout(new java.awt.CardLayout());

        pnlChangePW.setBackground(new java.awt.Color(255, 255, 255));

        lblNewPW.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNewPW.setText("Mật Khẩu Mới");

        lblVerifyPW.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblVerifyPW.setText("Xác Nhận Mật Khẩu");

        btnChange.setBackground(new java.awt.Color(150, 34, 200));
        btnChange.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnChange.setForeground(new java.awt.Color(255, 255, 255));
        btnChange.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/img/icon/checked_24px.png"))); // NOI18N
        btnChange.setText("Xác nhận");
        btnChange.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnChange.setContentAreaFilled(false);
        btnChange.setOpaque(true);

        btnCancel.setBackground(new java.awt.Color(255, 255, 255));
        btnCancel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCancel.setForeground(new java.awt.Color(150, 34, 200));
        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/img/icon/cancel_purple_24px.png"))); // NOI18N
        btnCancel.setText("Huỷ bỏ");
        btnCancel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(150, 34, 200)));
        btnCancel.setContentAreaFilled(false);
        btnCancel.setOpaque(true);

        txtMatKhau.setError(lblErrorMatKhau);
        txtMatKhau.setLabel(lblNewPW);
        txtMatKhau.setName("Mật khẩu"); // NOI18N

        txtXacNhan.setError(lblErrorXacNhan);
        txtXacNhan.setLabel(lblVerifyPW);
        txtXacNhan.setName("Mật khẩu"); // NOI18N

        lblErrorMatKhau.setForeground(new java.awt.Color(255, 255, 255));
        lblErrorMatKhau.setText("jLabel1");

        lblErrorXacNhan.setForeground(new java.awt.Color(255, 255, 255));
        lblErrorXacNhan.setText("jLabel1");

        javax.swing.GroupLayout pnlChangePWLayout = new javax.swing.GroupLayout(pnlChangePW);
        pnlChangePW.setLayout(pnlChangePWLayout);
        pnlChangePWLayout.setHorizontalGroup(
            pnlChangePWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChangePWLayout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(pnlChangePWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlChangePWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlChangePWLayout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(lblErrorXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlChangePWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlChangePWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblNewPW)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlChangePWLayout.createSequentialGroup()
                                    .addComponent(lblVerifyPW)
                                    .addGap(218, 218, 218)))
                            .addComponent(txtMatKhau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtXacNhan, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                            .addComponent(lblErrorMatKhau, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlChangePWLayout.createSequentialGroup()
                        .addComponent(btnChange, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        pnlChangePWLayout.setVerticalGroup(
            pnlChangePWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChangePWLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lblNewPW)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblErrorMatKhau)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblVerifyPW)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblErrorXacNhan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlChangePWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChange, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );

        pnlMain.add(pnlChangePW, "ChangePW");
        pnlMain.add(pnlOTP, "VerifyOTP");

        pnlCurrentPW.setBackground(new java.awt.Color(255, 255, 255));

        lblCurrent.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCurrent.setText("Nhập mật khẩu hiện tại");

        btnVerifyCurrent.setBackground(new java.awt.Color(150, 34, 200));
        btnVerifyCurrent.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnVerifyCurrent.setForeground(new java.awt.Color(255, 255, 255));
        btnVerifyCurrent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/img/icon/checked_24px.png"))); // NOI18N
        btnVerifyCurrent.setText("Tiếp tục");
        btnVerifyCurrent.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnVerifyCurrent.setContentAreaFilled(false);
        btnVerifyCurrent.setOpaque(true);

        lblIconCurrent.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIconCurrent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/img/icon/lock_48px.png"))); // NOI18N

        txtCurrent.setEchoChar('*');
        txtCurrent.setError(lblErrorCurrent);
        txtCurrent.setLabel(lblCurrent);
        txtCurrent.setName("Mật khẩu"); // NOI18N

        lblErrorCurrent.setForeground(new java.awt.Color(255, 255, 255));
        lblErrorCurrent.setText("jLabel1");

        javax.swing.GroupLayout pnlCurrentPWLayout = new javax.swing.GroupLayout(pnlCurrentPW);
        pnlCurrentPW.setLayout(pnlCurrentPWLayout);
        pnlCurrentPWLayout.setHorizontalGroup(
            pnlCurrentPWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCurrentPWLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCurrentPWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCurrentPWLayout.createSequentialGroup()
                        .addComponent(lblIconCurrent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCurrentPWLayout.createSequentialGroup()
                        .addGap(0, 96, Short.MAX_VALUE)
                        .addGroup(pnlCurrentPWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblErrorCurrent, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlCurrentPWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblCurrent)
                                .addComponent(txtCurrent, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(66, 66, 66))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCurrentPWLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnVerifyCurrent, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(178, 178, 178))
        );
        pnlCurrentPWLayout.setVerticalGroup(
            pnlCurrentPWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCurrentPWLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lblIconCurrent)
                .addGap(13, 13, 13)
                .addComponent(lblCurrent)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtCurrent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblErrorCurrent)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnVerifyCurrent, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        pnlMain.add(pnlCurrentPW, "CurrentPW");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(pnlTitleBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(pnlTitleBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnChange;
    private javax.swing.JButton btnVerifyCurrent;
    private javax.swing.JLabel lblCurrent;
    private javax.swing.JLabel lblErrorCurrent;
    private javax.swing.JLabel lblErrorMatKhau;
    private javax.swing.JLabel lblErrorXacNhan;
    private javax.swing.JLabel lblIconCurrent;
    private javax.swing.JLabel lblNewPW;
    private javax.swing.JLabel lblVerifyPW;
    private javax.swing.JPanel pnlChangePW;
    private javax.swing.JPanel pnlCurrentPW;
    private javax.swing.JPanel pnlMain;
    private com.edusys.components.MyOtpPanel pnlOTP;
    private com.edusys.components.MyTitleBar pnlTitleBar;
    private com.edusys.components.MyPasswordField txtCurrent;
    private com.edusys.components.MyPasswordField txtMatKhau;
    private com.edusys.components.MyPasswordField txtXacNhan;
    // End of variables declaration//GEN-END:variables

    private JTextComponent[] fields;

    private void init() {
        this.setLocationRelativeTo(null);
        fields = new JTextComponent[]{txtCurrent, txtMatKhau, txtXacNhan};
        InputVerifier verifier = MyVerifier.CHANGE_PW_VERIFIER;

        //set InputVerifier cho filed
        for (JTextComponent field : fields) {
            field.setInputVerifier(verifier);
        }
        addListeners(); //thêm listeners cho các thành phần
    }

    private void addListeners() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent we) {
                reset(fields);
            }
        });
        XListener.addVerifyPasswordFocus(txtMatKhau, txtXacNhan);
        
        btnCancel.addActionListener((ActionEvent e) -> {
            cancel();
        });
       
        btnVerifyCurrent.addActionListener((ActionEvent e) -> {
            verifyCurrentPW();
        });
       
        pnlOTP.getVerifyBtn().addActionListener((ActionEvent e) -> {
            if (pnlOTP.verifyOTP()) {
                showCard("ChangePW");
            }
        });
        
        btnChange.addActionListener((ActionEvent e) -> {
            changePW();
        });
    }

    //hiển thị card theo cardName
    public void showCard(String name) {
        ((java.awt.CardLayout) pnlMain.getLayout()).show(pnlMain, name);
        if (!isVisible()) {
            setVisible(true);
        }
    }

    //xác nhận mật khẩu hiện tại
    private void verifyCurrentPW() {
        String pw = Auth.user.getMatKhau(); //lấy password hiện tại của user
        if (String.valueOf(txtCurrent.getPassword()).equals(pw)) { //lấy password trên form và so khớp
            showCard("ChangePW"); //nếu khớp, hiện card thay đổi password cho người dùng
            txtCurrent.setText(""); //xoá current pw
        } else {
            MsgBox.error("Mật khẩu không đúng! Vui lòng kiểm tra lại."); //thông báo lỗi nếu không khớp
        }
    }

    //thay đổi password của user
    private void changePW() {
        String pw = String.valueOf(txtMatKhau.getPassword()); //lấy pw 
        String verify = String.valueOf(txtXacNhan.getPassword()); //lấy xác nhận pw
        
        if (!pw.equals(verify)) { // nếu không trùng khớp, thông báo lỗi
            MsgBox.error("Mật khẩu không trùng khớp! Vui lòng kiểm tra lại.");
        } else {
            if (Auth.user == null) { //nếu không có người dùng đăng nhập, dừng chức năng
                return;
            }
            
            Auth.user.setMatKhau(verify); //thay đổi pw user
            new NhanVienDAO().update(Auth.user); //thay đổi trong CSDL
            MsgBox.inform("Cập nhật mật khẩu thành công"); //thông báo 
            if (Auth.isForgotPW) { //nếu người dùng chọn quên mật khẩu trước khi đổi pw
                Auth.clear(); // xoá thông tin hiện tại
                this.dispose(); //đóng form
                DangNhapJDialog.getDialog().showCard("Signin"); //mở form đăng nhập, hiện card signin
            } else {
                this.dispose(); //còn lại chỉ đóng form
            }
        }
    }

    //huỷ tác vụ hiện tại
    private void cancel() {
        if (Auth.isLogin()) { //nếu người dùng đăng nhập (đang ở EduSysFrame), chỉ đóng form
            this.dispose();
        } else { //nếu người dùng chưa đăng nhập, mở form đăng nhập cho người dùng và đóng form
            Auth.clear();
            DangNhapJDialog.getDialog().setVisible(true);
            this.dispose();
        }
    }

    //xoá trắng tất cả dữ liệu trên form
    private void reset(JTextComponent... fields) {
        for (JTextComponent field : fields) {
            field.setText("");
        }
        pnlOTP.clear();
    }
}
