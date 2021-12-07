package com.ultramotor.ui.login;

import com.swingx.Button;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;
import javax.swing.Timer;

public class PanelOTP extends javax.swing.JPanel {

    private ActionListener confirmListener;
    private ActionListener resendListener;
    private Timer timer;
    private int count;
    private String infoOTP = "<html>Vui lòng nhập mã OTP đã được gửi đến email của bạn.<br><center> Bạn có thể gửi lại mail sau <span style=\"color:red;\">%ds.</center></html>";
    private String resendInfo = "<html>Bạn vẫn chưa nhận được mã OTP?<br><center></center></html>";

    public PanelOTP() {
        initComponents();
        init();
    }

    private void init() {
        btnResend.setVisible(false);
        timer = new Timer(1000, (ActionEvent e) -> {
            btnResend.setVisible(false);
            lblInfo.setText(String.format(infoOTP, 60 - count));
            if (count == 60) {
                timer.stop();
                count = 0;
                lblInfo.setText(resendInfo);
                btnResend.setVisible(true);
            }
            count++;
        });

        for (Component comp : this.getComponents()) {
            if (comp instanceof JTextField) {
                JTextField field = (JTextField) comp;
                field.setHorizontalAlignment(JTextField.CENTER);
                field.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyTyped(KeyEvent ke) {
                        char c = ke.getKeyChar();
                        if (field.getSelectedText() == null && field.getText().length() != 0) {
                            ke.consume();
                        } else if (!Character.isDigit(c)) {
                            ke.consume();
                        } else {
                            if (!"last".equals(field.getName())) {
                                field.transferFocus();
                            }
                        }
                    }

                    @Override
                    public void keyPressed(KeyEvent ke) {
                        int kc = ke.getKeyCode();
                        //luôn cho phép nút Backspace và Delete
                        if (kc == KeyEvent.VK_BACK_SPACE || kc == KeyEvent.VK_DELETE) {
                            if (field.getText().equals("") && !"first".equals(field.getName())) {
                                field.transferFocusBackward();
                            }
                        }
                        if (kc == KeyEvent.VK_ENTER && "last".equals(field.getName())) {
                            btnConfirm.doClick();
                        }
                    }
                });

                field.addFocusListener(new FocusAdapter() {
                    @Override
                    public void focusGained(FocusEvent fe) {
                        if (field.getText().length() != 0) {
                            field.setSelectionStart(0);
                            field.setSelectionEnd(1);
                        }
                    }
                });
            }
        }
        btnConfirm.addActionListener(confirmListener);
        btnResend.addActionListener(resendListener);
        btnResend.addActionListener((ActionEvent e) -> {
            if (timer.isRunning()) {
                timer.stop();
            }
            count = 0;
            timer.start();
        });
    }

    public String getText() {
        return txt1.getText() + txt2.getText() + txt3.getText() + txt4.getText() + txt5.getText() + txt6.getText();
    }

    public Button getBtnConfirm() {
        return btnConfirm;
    }

    public Button getBtnResend() {
        return btnResend;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        txt1 = new com.swingx.TextField();
        txt2 = new com.swingx.TextField();
        txt3 = new com.swingx.TextField();
        txt4 = new com.swingx.TextField();
        txt5 = new com.swingx.TextField();
        txt6 = new com.swingx.TextField();
        btnConfirm = new com.swingx.Button();
        lblInfo = new javax.swing.JLabel();
        btnResend = new com.swingx.Button();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setOpaque(false);
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWidths = new int[] {0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0};
        layout.rowHeights = new int[] {0, 10, 0, 10, 0, 10, 0, 10, 0};
        setLayout(layout);

        txt1.setDrawLine(true);
        txt1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt1.setLabelText("");
        txt1.setName("first"); // NOI18N
        txt1.setOnlyField(true);
        txt1.setPlaceholder("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.insets = new java.awt.Insets(15, 0, 0, 0);
        add(txt1, gridBagConstraints);

        txt2.setDrawLine(true);
        txt2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt2.setLabelText("");
        txt2.setOnlyField(true);
        txt2.setPlaceholder("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.insets = new java.awt.Insets(15, 0, 0, 0);
        add(txt2, gridBagConstraints);

        txt3.setDrawLine(true);
        txt3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt3.setLabelText("");
        txt3.setOnlyField(true);
        txt3.setPlaceholder("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.insets = new java.awt.Insets(15, 0, 0, 0);
        add(txt3, gridBagConstraints);

        txt4.setDrawLine(true);
        txt4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt4.setLabelText("");
        txt4.setOnlyField(true);
        txt4.setPlaceholder("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.insets = new java.awt.Insets(15, 0, 0, 0);
        add(txt4, gridBagConstraints);

        txt5.setDrawLine(true);
        txt5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt5.setLabelText("");
        txt5.setOnlyField(true);
        txt5.setPlaceholder("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 16;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.insets = new java.awt.Insets(15, 0, 0, 0);
        add(txt5, gridBagConstraints);

        txt6.setDrawLine(true);
        txt6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt6.setLabelText("");
        txt6.setName("last"); // NOI18N
        txt6.setOnlyField(true);
        txt6.setPlaceholder("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 20;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.insets = new java.awt.Insets(15, 0, 0, 0);
        add(txt6, gridBagConstraints);

        btnConfirm.setBackground(new java.awt.Color(46, 211, 151));
        btnConfirm.setForeground(new java.awt.Color(255, 255, 255));
        btnConfirm.setText("Xác nhận");
        btnConfirm.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 21;
        gridBagConstraints.gridheight = 5;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.insets = new java.awt.Insets(21, 0, 20, 0);
        add(btnConfirm, gridBagConstraints);

        lblInfo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblInfo.setForeground(java.awt.SystemColor.controlDkShadow);
        lblInfo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInfo.setText("<html>Vui lòng nhập mã OTP đã được gửi đến email của bạn.<br><center> Mã OTP sẽ hết hạn sau <span style=\\\"color:red;\\\">%ds.</center></html>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 21;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
        add(lblInfo, gridBagConstraints);

        btnResend.setBackground(new java.awt.Color(50, 165, 35));
        btnResend.setForeground(new java.awt.Color(255, 255, 255));
        btnResend.setText("Gửi lại ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 16;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        add(btnResend, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.swingx.Button btnConfirm;
    private com.swingx.Button btnResend;
    private javax.swing.JLabel lblInfo;
    private com.swingx.TextField txt1;
    private com.swingx.TextField txt2;
    private com.swingx.TextField txt3;
    private com.swingx.TextField txt4;
    private com.swingx.TextField txt5;
    private com.swingx.TextField txt6;
    // End of variables declaration//GEN-END:variables
}
