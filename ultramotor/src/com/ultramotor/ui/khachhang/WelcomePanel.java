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
        cboLang = new javax.swing.JComboBox<>();
        lblLang = new javax.swing.JLabel();
        btnContinue = new com.swingx.Button();

        setBackground(new java.awt.Color(204, 204, 204));

        cboLang.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cboLang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiếng Việt", "Tiếng Anh" }));

        lblLang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblLang.setForeground(new java.awt.Color(102, 102, 102));
        lblLang.setText("Vui lòng chọn ngôn ngữ");

        btnContinue.setBackground(new java.awt.Color(102, 102, 255));
        btnContinue.setForeground(new java.awt.Color(255, 255, 255));
        btnContinue.setText("Tiếp Tục");
        btnContinue.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnContinue.setRadius(75);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlSlideshow, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblLang)
                            .addComponent(cboLang, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(95, 95, 95))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnContinue, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(203, 203, 203))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlSlideshow, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(152, 152, 152)
                .addComponent(lblLang, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cboLang, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(btnContinue, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.swingx.Button btnContinue;
    private javax.swing.JComboBox<String> cboLang;
    private javax.swing.JLabel lblLang;
    private com.swingx.SlideShowPanel pnlSlideshow;
    // End of variables declaration//GEN-END:variables

}
