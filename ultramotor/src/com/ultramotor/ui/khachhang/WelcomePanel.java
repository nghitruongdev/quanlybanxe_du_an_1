package com.ultramotor.ui.khachhang;

import java.awt.Container;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class WelcomePanel extends javax.swing.JPanel implements Multilang {

    final String LANG_SELECT_EN = "Plsease choose a language";
    final String LANG_SELECT_VN = "Vui lòng chọn ngôn ngữ";
    final String CONTINUE_EN = "Continue";
    final String CONTINUE_VN = "Tiếp tục";
    final DefaultComboBoxModel LANG_MODEL_EN = new DefaultComboBoxModel(new String[]{"Vietnamese", "English"});
    final DefaultComboBoxModel LANG_MODEL_VN = new DefaultComboBoxModel(new String[]{"Tiếng Việt", "Tiếng Anh"});

    public WelcomePanel() {
        initComponents();
        init();
    }

    private void init() {
        pnlSlideshow.addImages(
                new ImageIcon(getClass().getResource("/com/raven/icon/slide1.jpg")),
                new ImageIcon(getClass().getResource("/com/raven/icon/slide2.jpeg")),
                new ImageIcon(getClass().getResource("/com/raven/icon/slide3.jpg")));
        pnlSlideshow.setAuto(2000);

        cboLang.setModel(LANG_MODEL_VN);
        addListeners();
    }

    @Override
    public void setLang(String lang) {
        if ("Vietnamese".equalsIgnoreCase(lang) || "Tiếng Việt".equalsIgnoreCase(lang)) {
            lblLang.setText(LANG_SELECT_VN);
            btnContinue.setText(CONTINUE_VN);
            cboLang.setModel(LANG_MODEL_VN);
            cboLang.setSelectedIndex(0);
        } else {
            lblLang.setText(LANG_SELECT_EN);
            btnContinue.setText(CONTINUE_EN);
            cboLang.setModel(LANG_MODEL_EN);
            cboLang.setSelectedIndex(1);
        }
    }

    private void addListeners() {
        cboLang.addActionListener((ActionEvent e) -> {
            if (cboLang.getSelectedIndex() == 0) {
                setLang("Vietnamese");
            } else {
                setLang("English");
            }
        });

        btnContinue.addActionListener((ActionEvent e) -> {
            Container con = this.getTopLevelAncestor();
            if (con instanceof KhachHangFrame) {
                KhachHangController.navigateCard((JPanel)this.getParent(), true);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlSlideshow = new com.swingx.SlideShowPanel();
        pnlLang = new javax.swing.JPanel();
        lblLang = new javax.swing.JLabel();
        cboLang = new com.swingx.ComboBoxSuggestion();
        btnContinue = new com.swingx.Button();

        setBackground(new java.awt.Color(204, 204, 204));
        setLayout(new java.awt.GridLayout(1, 0));
        add(pnlSlideshow);

        pnlLang.setBackground(new java.awt.Color(250, 250, 250));

        lblLang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblLang.setForeground(new java.awt.Color(102, 102, 102));
        lblLang.setText("Vui lòng chọn ngôn ngữ");

        cboLang.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cboLang.setPreferredSize(new java.awt.Dimension(116, 31));

        btnContinue.setBackground(new java.awt.Color(102, 102, 255));
        btnContinue.setForeground(new java.awt.Color(255, 255, 255));
        btnContinue.setText("Tiếp Tục");
        btnContinue.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnContinue.setRadius(75);

        javax.swing.GroupLayout pnlLangLayout = new javax.swing.GroupLayout(pnlLang);
        pnlLang.setLayout(pnlLangLayout);
        pnlLangLayout.setHorizontalGroup(
            pnlLangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLangLayout.createSequentialGroup()
                .addGroup(pnlLangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlLangLayout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(cboLang, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlLangLayout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addComponent(btnContinue, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 74, Short.MAX_VALUE))
            .addGroup(pnlLangLayout.createSequentialGroup()
                .addGap(181, 181, 181)
                .addComponent(lblLang)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlLangLayout.setVerticalGroup(
            pnlLangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLangLayout.createSequentialGroup()
                .addGap(141, 141, 141)
                .addComponent(lblLang, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cboLang, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(btnContinue, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(201, Short.MAX_VALUE))
        );

        add(pnlLang);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.swingx.Button btnContinue;
    private com.swingx.ComboBoxSuggestion cboLang;
    private javax.swing.JLabel lblLang;
    private javax.swing.JPanel pnlLang;
    private com.swingx.SlideShowPanel pnlSlideshow;
    // End of variables declaration//GEN-END:variables

}
