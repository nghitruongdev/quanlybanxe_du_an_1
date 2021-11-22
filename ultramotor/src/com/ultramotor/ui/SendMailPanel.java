package com.ultramotor.ui;

import com.swingx.MyScrollBar;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class SendMailPanel extends javax.swing.JPanel {

    public SendMailPanel() {
        initComponents();
        setLabelWithAsterisk();
        fixTextPane(jScrollPane1);

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
        btnHuyMail = new com.swingx.Button();
        txtChuDe = new com.swingx.TextField();
        lblNguoiNhan = new javax.swing.JLabel();
        lblChuDe = new javax.swing.JLabel();
        lblNoiDung = new javax.swing.JLabel();
        txtTo = new com.swingx.TextField();
        lblNguoiGui = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtNoiDung = new javax.swing.JEditorPane();
        comboBoxSuggestion1 = new com.swingx.ComboBoxSuggestion();
        lblChuDe1 = new javax.swing.JLabel();
        button1 = new com.swingx.Button();

        setBackground(new java.awt.Color(250, 250, 250));

        txtFrom.setOnlyField(true);
        txtFrom.setPlaceholder("Người gửi");

        btnGui.setBackground(new java.awt.Color(0, 153, 255));
        btnGui.setForeground(new java.awt.Color(255, 255, 255));
        btnGui.setText("Gửi");
        btnGui.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblTitleSendMail.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitleSendMail.setForeground(new java.awt.Color(102, 102, 255));
        lblTitleSendMail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitleSendMail.setText("SEND EMAIL");

        btnHuyMail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ultramotor/img/icon/close-icon.png"))); // NOI18N
        btnHuyMail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnHuyMail.setPreferredSize(new java.awt.Dimension(71, 31));
        btnHuyMail.setTransparent(true);
        btnHuyMail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyMailActionPerformed(evt);
            }
        });

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
        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        txtNoiDung.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtNoiDung.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtNoiDung.setOpaque(false);
        txtNoiDung.setSelectionColor(new java.awt.Color(3, 155, 216));
        jScrollPane1.setViewportView(txtNoiDung);

        lblChuDe1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblChuDe1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblChuDe1.setText("BC/BCC");

        button1.setBackground(new java.awt.Color(21, 127, 73));
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setText("Tải file đính kèm");
        button1.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitleSendMail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnHuyMail, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 22, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lblNguoiGui)
                                    .addComponent(lblChuDe)
                                    .addComponent(lblNguoiNhan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblChuDe1)
                                    .addComponent(lblNoiDung, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(comboBoxSuggestion1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(txtChuDe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtFrom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtTo, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(btnGui, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 62, Short.MAX_VALUE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {lblChuDe, lblChuDe1, lblNguoiGui, lblNguoiNhan, lblNoiDung});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnHuyMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(lblTitleSendMail)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblNguoiGui)
                        .addGap(18, 18, 18)
                        .addComponent(lblNguoiNhan)
                        .addGap(12, 12, 12)
                        .addComponent(lblChuDe)
                        .addGap(18, 18, 18)
                        .addComponent(lblChuDe1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(txtChuDe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboBoxSuggestion1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(lblNoiDung, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
                .addGap(25, 25, 25)
                .addComponent(btnGui, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lblChuDe, lblNguoiGui, lblNguoiNhan, txtTo});

    }// </editor-fold>//GEN-END:initComponents

    private void btnHuyMailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyMailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHuyMailActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.swingx.Button btnGui;
    private com.swingx.Button btnHuyMail;
    private com.swingx.Button button1;
    private com.swingx.ComboBoxSuggestion comboBoxSuggestion1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblChuDe;
    private javax.swing.JLabel lblChuDe1;
    private javax.swing.JLabel lblNguoiGui;
    private javax.swing.JLabel lblNguoiNhan;
    private javax.swing.JLabel lblNoiDung;
    private javax.swing.JLabel lblTitleSendMail;
    private com.swingx.TextField txtChuDe;
    private com.swingx.TextField txtFrom;
    private javax.swing.JEditorPane txtNoiDung;
    private com.swingx.TextField txtTo;
    // End of variables declaration//GEN-END:variables
}
