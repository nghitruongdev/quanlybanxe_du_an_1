package com.ultramotor.ui.khachhang;

import java.awt.Container;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

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
        revalidate();
    }

    private void addListeners() {
        cboLang.addActionListener((ActionEvent e) -> {
            System.out.println(cboLang.getSelectedIndex());
            if (cboLang.getSelectedIndex() == 0) {
                setLang("Vietnamese");
            } else {
                setLang("English");
            }
        });
        
        btnContinue.addActionListener((ActionEvent e)->{
            Container con = this.getTopLevelAncestor();
            if(con instanceof KhachHangFrame){
                ((KhachHangFrame)con).navigateCard(true);
            }
        });
    }
    


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlSlideshow = new com.swingx.SlideShowPanel();
        jPanel1 = new javax.swing.JPanel();
        lblLang = new javax.swing.JLabel();
        cboLang = new com.swingx.ComboBoxSuggestion();
        btnContinue = new com.swingx.Button();

        setBackground(new java.awt.Color(204, 204, 204));
        setLayout(new java.awt.GridLayout());
        add(pnlSlideshow);

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(cboLang, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addComponent(btnContinue, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 119, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(181, 181, 181)
                .addComponent(lblLang)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(141, 141, 141)
                .addComponent(lblLang, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cboLang, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(btnContinue, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(257, Short.MAX_VALUE))
        );

        add(jPanel1);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.swingx.Button btnContinue;
    private com.swingx.ComboBoxSuggestion cboLang;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblLang;
    private com.swingx.SlideShowPanel pnlSlideshow;
    // End of variables declaration//GEN-END:variables

}
