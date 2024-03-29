package com.ultramotor.ui.nhanvien;

import com.swingx.MyScrollBar;
import com.ultramotor.util.MsgBox;
import com.ultramotor.util.XMail;
import com.ultramotor.util.XValidate;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.util.Arrays;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.text.JTextComponent;

public class SendMailPanel extends javax.swing.JPanel {

    private File fileDinhKem;

    public SendMailPanel() {
        initComponents();
        setLabelWithAsterisk();
        btnCancel.setVisible(false);
        fixTextPane(jScrollPane1);
        addListeners();
    }

    private void sendEmail() {
        String email = txtFrom.getText();
        String nguoiNhan = txtTo.getText();
        String subject = txtChuDe.getText();
        //soạn nội dung mail cho nhiều người
        String mailContent = txtNoiDung.getText();
        
        if (subject.isEmpty() && MsgBox.confirm("Chủ đề trống, bạn có muốn tiếp tục gửi mail?", false) != 0) {
            return;
        }
        
        if (mailContent.isEmpty() && MsgBox.confirm("Nội dung email trống, bạn có muốn tiếp tục gửi mail?", false) != 0) {
            return;
        }

        //file đính kèm
        XMail.sendMail(nguoiNhan, mailContent, subject, fileDinhKem);
        sleepThread(500);
        MsgBox.inform("Gửi mail thành công!");
        reset();
        ((JDialog) this.getTopLevelAncestor()).dispose();
    }

    private void uploadFile() {
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Tải lên file đính kèm");
        fc.setMultiSelectionEnabled(false);
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            fileDinhKem = fc.getSelectedFile();
            lblTepDinhKem.setText(fileDinhKem.getName());
            btnCancel.setVisible(true);
        }
    }

    private void reset() {
        JTextComponent[] fields = {txtFrom, txtTo, txtChuDe, txtNoiDung};
        for (JTextComponent field : fields) {
            field.setText("");
        }
        fileDinhKem = null;
        lblTepDinhKem.setText("");
    }

    private void addListeners() {
        btnUpload.addActionListener((ActionEvent e) -> {
            new Thread(() -> {
                sleepThread(300);
                uploadFile();
            }).start();
        });

        btnCancel.addActionListener((ActionEvent e) -> {
            fileDinhKem = null;
            lblTepDinhKem.setText("");
            btnCancel.setVisible(false);
        });

        btnGui.addActionListener((ActionEvent e) -> {
            new Thread(() -> {
                sleepThread(300);
                if (!validateField()) {
                    return;
                }
                sendEmail();
            }).start();
        });
    }

    private boolean validateField() {
        String from = txtFrom.getText().trim();
        if (from.isEmpty()) {
            MsgBox.error("Chưa nhập địa chỉ email người gửi");
            txtFrom.requestFocus();
            return false;
        }
        if (!validateEmails(from)) {
            return false;
        }
        String to = txtTo.getText().trim();
        if (to.isEmpty()) {
            MsgBox.error("Chưa nhập địa chỉ email người nhận");
            txtTo.requestFocus();
            return false;
        }
        return validateEmails(to.split(","));
    }

    private boolean validateEmails(String... emails) {
        for (String m : emails) {
            if (!XValidate.validateEmail(m)) {
                MsgBox.error("Địa chỉ email " + m + " không hợp lệ!");
                return false;
            }
        }
        return true;
    }

    private void sleepThread(int mili) {
        try {
            Thread.sleep(mili);
        } catch (InterruptedException e) {

        }
    }

    public void setNguoiNhan(String... emails) {
        txtTo.setText(Arrays.toString(emails).replaceAll("[\\[\\]]", ""));
    }

    public void setNguoiGui(String from) {
        txtFrom.setText(from);
    }

    private void fixTextPane(JScrollPane scroll) {
        scroll.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204), 1));
        scroll.getViewport().setBackground(Color.WHITE);
        scroll.setVerticalScrollBar(new MyScrollBar());
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        scroll.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        scroll.setViewportView(txtNoiDung);
        txtNoiDung.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent fe) {
                scroll.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204), 1));
            }

            @Override
            public void focusGained(FocusEvent fe) {
                scroll.setBorder(BorderFactory.createLineBorder(txtFrom.getLineColor(), 1));
            }

        });
    }

    private void setLabelWithAsterisk() {
        for (Component comp : this.getComponents()) {
            if (comp instanceof JLabel) {
                JLabel label = (JLabel) comp;
                String text = label.getText();
                if (text.contains("*")) {
                    label.setText(String.format("<html>%s<span style=\"color:red;\"> *</span></html>", label.getText().replace("*", "")));
                } else {
                    label.setText(label.getText() + "   ");
                }

            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtFrom = new com.swingx.TextField();
        btnGui = new com.swingx.Button();
        lblTitleSendMail = new javax.swing.JLabel();
        txtChuDe = new com.swingx.TextField();
        lblNguoiNhan = new javax.swing.JLabel();
        lblChuDe = new javax.swing.JLabel();
        lblNoiDung = new javax.swing.JLabel();
        txtTo = new com.swingx.TextField();
        lblNguoiGui = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtNoiDung = new javax.swing.JEditorPane();
        btnUpload = new com.swingx.Button();
        lblTepDinhKem = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        txtFrom.setOnlyField(true);
        txtFrom.setPlaceholder("Người gửi");

        btnGui.setBackground(new java.awt.Color(0, 174, 114));
        btnGui.setForeground(new java.awt.Color(255, 255, 255));
        btnGui.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ultramotor/icon/icons8-sent.png"))); // NOI18N
        btnGui.setText("Gửi");
        btnGui.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblTitleSendMail.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitleSendMail.setForeground(new java.awt.Color(0, 174, 114));
        lblTitleSendMail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitleSendMail.setText("SEND EMAIL");

        txtChuDe.setOnlyField(true);
        txtChuDe.setPlaceholder("Chủ Đề");

        lblNguoiNhan.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblNguoiNhan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNguoiNhan.setText("Người nhận*");

        lblChuDe.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblChuDe.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblChuDe.setText("Chủ đề");

        lblNoiDung.setBackground(new java.awt.Color(250, 250, 250));
        lblNoiDung.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblNoiDung.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNoiDung.setText("Nội dung");

        txtTo.setOnlyField(true);
        txtTo.setPlaceholder("Người nhận");

        lblNguoiGui.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblNguoiGui.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNguoiGui.setText("Người gửi*");

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(150, 150, 150)));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        txtNoiDung.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtNoiDung.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtNoiDung.setSelectionColor(new java.awt.Color(3, 155, 216));
        jScrollPane1.setViewportView(txtNoiDung);

        btnUpload.setBackground(new java.awt.Color(0, 174, 114));
        btnUpload.setForeground(new java.awt.Color(255, 255, 255));
        btnUpload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ultramotor/icon/icons8-file.png"))); // NOI18N
        btnUpload.setText("Tải file đính kèm");
        btnUpload.setToolTipText("");

        lblTepDinhKem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTepDinhKem.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTepDinhKem.setEnabled(false);

        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ultramotor/icon/close_window_red_20px.png"))); // NOI18N
        btnCancel.setBorderPainted(false);
        btnCancel.setContentAreaFilled(false);
        btnCancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancel.setFocusPainted(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitleSendMail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblNguoiGui)
                            .addComponent(lblChuDe)
                            .addComponent(lblNguoiNhan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblNoiDung, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtChuDe, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnUpload, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(55, 55, 55)
                                .addComponent(lblTepDinhKem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2))
                            .addComponent(txtTo, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnGui, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(4, 4, 4)))
                        .addGap(61, 61, 61)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {lblChuDe, lblNguoiGui, lblNguoiNhan, lblNoiDung});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lblTitleSendMail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblNguoiGui)
                        .addGap(18, 18, 18)
                        .addComponent(lblNguoiNhan)
                        .addGap(12, 12, 12)
                        .addComponent(lblChuDe)
                        .addGap(61, 61, 61))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(txtChuDe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTepDinhKem, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUpload, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(lblNoiDung, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGui, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lblChuDe, lblNguoiGui, lblNguoiNhan, txtTo});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnCancel, btnUpload, lblTepDinhKem});

    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private com.swingx.Button btnGui;
    private com.swingx.Button btnUpload;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblChuDe;
    private javax.swing.JLabel lblNguoiGui;
    private javax.swing.JLabel lblNguoiNhan;
    private javax.swing.JLabel lblNoiDung;
    private javax.swing.JLabel lblTepDinhKem;
    private javax.swing.JLabel lblTitleSendMail;
    private com.swingx.TextField txtChuDe;
    private com.swingx.TextField txtFrom;
    private javax.swing.JEditorPane txtNoiDung;
    private com.swingx.TextField txtTo;
    // End of variables declaration//GEN-END:variables
}
