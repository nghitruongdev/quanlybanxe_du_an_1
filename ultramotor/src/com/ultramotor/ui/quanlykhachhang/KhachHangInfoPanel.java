package com.ultramotor.ui.quanlykhachhang;

import com.ultramotor.ui.nhanvien.*;
import com.swingx.MyScrollBar;
import com.ultramotor.dao.NhanVienDAO;
import com.ultramotor.entity.KhachHang;
import com.ultramotor.entity.NhanVien;
import com.ultramotor.util.Auth;
import com.ultramotor.util.XDate;
import com.ultramotor.util.XImage;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.HashSet;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class KhachHangInfoPanel extends javax.swing.JPanel {

    private KhachHang kh;

    public KhachHangInfoPanel() {
        initComponents();
//        fillComboVaiTro();
        fixTextPane(jScrollPane2);
        fixRadioPanel();
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(ComponentEvent ce) {
                System.out.println("Component Hidden");
            }

            @Override
            public void componentShown(ComponentEvent ce) {
                System.out.println("Component Showned");
            }

            @Override
            public void componentResized(ComponentEvent ce) {
                System.out.println("Component Resized");
                addListeners();
            }

        });

    }

    private void updateStatus() {
        boolean isNew = kh == null;
        boolean manager = Auth.isManager();
        btnGuiMail.setVisible(!isNew);
        btnCapNhat.setText(isNew ? "Thêm mới" : "Cập nhật");
        txtMaKH.setEditable(isNew);
    }

    void setForm() {
        txtMaKH.setText(kh.getIdKH());
        txtHoKH.setText(kh.getHoKH());
        txtTenKH.setText(kh.getTenKH());
        txtNgaySinh.setText(XDate.toString(kh.getNgaySinh(), "dd-MM-yyyy"));
        txtDiaChi.setText(kh.getDiaChi());
        txtSDT.setText(kh.getSdt());
        txtEmail.setText(kh.getEmail());
        rdoNam.setSelected(kh.getGioiTinh());
        rdoNu.setSelected(!kh.getGioiTinh());
        rdoDaTV.setSelected(kh.getThanhVien());
        rdoChuaTV.setSelected(!kh.getThanhVien());
        txtGhiChu.setText(kh.getGhiChu());

    }

    public KhachHang getForm() {
        KhachHang kh = new KhachHang();
        kh.setIdKH(txtMaKH.getText());
        kh.setHoKH(txtHoKH.getText());
        kh.setTenKH(txtTenKH.getText());
        kh.setNgaySinh(XDate.parse(txtNgaySinh.getText()));
        kh.setDiaChi(txtDiaChi.getText());
        kh.setSdt(txtSDT.getText());
        kh.setEmail(txtEmail.getText());
        kh.setGhiChu(txtGhiChu.getText());
        kh.setGioiTinh(rdoDaTV.isSelected());
        return kh;
    }

    public void setForm(KhachHang kh) {
        this.kh = kh;
        setForm();
        updateStatus();
    }

//    private void fillComboVaiTro() {
//        HashSet<String> set = new HashSet();
//        new NhanVienDAO().selectAll().forEach((nv) -> {
//            set.add(nv.getVaiTro());
//        });
//        cboVaiTro.setModel(new DefaultComboBoxModel(set.toArray()));
//    }

    private void addListeners() {
        btnReset.addActionListener((ActionEvent e) -> {
            System.out.println("reseting");
            clearForm();
        });

        btnReset.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                System.out.println("I'm clicking reset button");
            }

        });
        btnCapNhat.addActionListener((ActionEvent e) -> {
            System.out.println("reseting");
        });

        System.out.println("Button udpate has " + btnCapNhat.getAncestorListeners().length);
//        for (Component c : pnlMain.getComponents()) {
//            if (c instanceof JTextField) {
//                ((JTextField) c).addFocusListener(new FocusAdapter(){
//                    @Override
//                    public void focusGained(FocusEvent fe) {
//                        System.out.println(c.getSize().width);
//                    }
//                    
//                    
//                });
//            }
//        }
        
    
    }

    private void fixTextPane(JScrollPane scroll) {
        scroll.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204), 1));
        scroll.getViewport().setBackground(Color.WHITE);
        scroll.setVerticalScrollBar(new MyScrollBar());
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        scroll.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        scroll.setViewportView(txtGhiChu);
        txtGhiChu.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent fe) {
                scroll.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204), 1));
            }

            @Override
            public void focusGained(FocusEvent fe) {
                scroll.setBorder(BorderFactory.createLineBorder(txtMaKH.getLineColor(), 1));
            }

        });
    }

    private void fixRadioPanel() {
        MouseAdapter adt = new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent me) {
                pnlThanhVien.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Giới Tính", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 11), new java.awt.Color(109, 109, 109))); // NOI18N
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                pnlThanhVien.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(3, 155, 216)), "Giới Tính", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 11), new java.awt.Color(109, 109, 109))); // NOI18N
            }
        };

        for (Component component : pnlThanhVien.getComponents()) {
            component.addMouseListener(adt);
        }

        pnlThanhVien.addMouseListener(adt);
    }

    public void setMailListener(ActionListener mailListener) {
        btnGuiMail.addActionListener(mailListener);
    }

    public void setUpdateListener(ActionListener updateListener) {
        btnCapNhat.addActionListener(updateListener);
    }

    public KhachHang getKhachHang() {
        return kh;
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgrGioiTinh = new javax.swing.ButtonGroup();
        bgrThanhVien = new javax.swing.ButtonGroup();
        pnlMain = new javax.swing.JPanel();
        pnlThanhVien = new javax.swing.JPanel();
        rdoDaTV = new javax.swing.JRadioButton();
        rdoChuaTV = new javax.swing.JRadioButton();
        lblGhiChu = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        txtMaKH = new com.swingx.TextField();
        txtTenKH = new com.swingx.TextField();
        txtNgaySinh = new com.swingx.TextField();
        txtDiaChi = new com.swingx.TextField();
        txtEmail = new com.swingx.TextField();
        txtSDT = new com.swingx.TextField();
        pnlButton = new javax.swing.JPanel();
        btnGuiMail = new com.swingx.Button();
        btnCapNhat = new com.swingx.Button();
        btnReset = new com.swingx.Button();
        txtHoKH = new com.swingx.TextField();
        pnlGioiTinh1 = new javax.swing.JPanel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();

        pnlMain.setBackground(new java.awt.Color(250, 250, 250));
        pnlMain.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.MatteBorder(null), "Thông tin khách hàng", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 18), new java.awt.Color(0, 153, 255))); // NOI18N
        pnlMain.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlThanhVien.setBackground(new java.awt.Color(250, 250, 250));
        pnlThanhVien.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Thành viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 11), new java.awt.Color(109, 109, 109))); // NOI18N
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 25, 5);
        flowLayout1.setAlignOnBaseline(true);
        pnlThanhVien.setLayout(flowLayout1);

        rdoDaTV.setBackground(new java.awt.Color(255, 255, 255));
        bgrThanhVien.add(rdoDaTV);
        rdoDaTV.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        rdoDaTV.setForeground(new java.awt.Color(102, 102, 102));
        rdoDaTV.setText("Đã thành viên");
        rdoDaTV.setOpaque(false);
        pnlThanhVien.add(rdoDaTV);

        rdoChuaTV.setBackground(new java.awt.Color(255, 255, 255));
        bgrThanhVien.add(rdoChuaTV);
        rdoChuaTV.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        rdoChuaTV.setForeground(new java.awt.Color(102, 102, 102));
        rdoChuaTV.setSelected(true);
        rdoChuaTV.setText("Chưa thành viên");
        rdoChuaTV.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        rdoChuaTV.setFocusPainted(false);
        rdoChuaTV.setOpaque(false);
        rdoChuaTV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoChuaTVActionPerformed(evt);
            }
        });
        pnlThanhVien.add(rdoChuaTV);

        pnlMain.add(pnlThanhVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 290, -1));

        lblGhiChu.setForeground(new java.awt.Color(102, 102, 102));
        lblGhiChu.setText("Ghi chú");
        pnlMain.add(lblGhiChu, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 280, -1, -1));

        txtGhiChu.setColumns(20);
        txtGhiChu.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtGhiChu.setLineWrap(true);
        txtGhiChu.setRows(5);
        txtGhiChu.setWrapStyleWord(true);
        txtGhiChu.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jScrollPane2.setViewportView(txtGhiChu);

        pnlMain.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 300, 290, 124));

        txtMaKH.setAnimateLabel(true);
        txtMaKH.setDrawLine(true);
        txtMaKH.setLabelText("Mã khách hàng");
        pnlMain.add(txtMaKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 285, -1));

        txtTenKH.setLabelText("Tên khách hàng");
        txtTenKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenKHActionPerformed(evt);
            }
        });
        pnlMain.add(txtTenKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, 155, -1));

        txtNgaySinh.setLabelText("Ngày sinh");
        txtNgaySinh.setPlaceholder("dd-MM-YYYY");
        pnlMain.add(txtNgaySinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 285, -1));

        txtDiaChi.setLabelText("Địa chỉ");
        txtDiaChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiaChiActionPerformed(evt);
            }
        });
        pnlMain.add(txtDiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 285, -1));

        txtEmail.setLabelText("Email");
        pnlMain.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 120, 285, -1));

        txtSDT.setLabelText("Số điện thoại");
        pnlMain.add(txtSDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 200, 285, -1));

        pnlButton.setOpaque(false);
        pnlButton.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        btnGuiMail.setBackground(new java.awt.Color(113, 118, 145));
        btnGuiMail.setForeground(new java.awt.Color(255, 255, 255));
        btnGuiMail.setText("Gửi mail");
        btnGuiMail.setBorderPainted(false);
        btnGuiMail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnGuiMail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuiMailActionPerformed(evt);
            }
        });
        pnlButton.add(btnGuiMail);

        btnCapNhat.setBackground(new java.awt.Color(113, 118, 145));
        btnCapNhat.setForeground(new java.awt.Color(255, 255, 255));
        btnCapNhat.setText("Cập nhật");
        btnCapNhat.setBorderPainted(false);
        btnCapNhat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });
        pnlButton.add(btnCapNhat);

        btnReset.setBackground(new java.awt.Color(113, 118, 145));
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setText("Đặt lại");
        btnReset.setBorderPainted(false);
        btnReset.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        pnlButton.add(btnReset);

        pnlMain.add(pnlButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 450, 260, 35));

        txtHoKH.setLabelText("Họ khách hàng");
        pnlMain.add(txtHoKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 105, -1));

        pnlGioiTinh1.setBackground(new java.awt.Color(250, 250, 250));
        pnlGioiTinh1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Giới Tính", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 11), new java.awt.Color(109, 109, 109))); // NOI18N
        java.awt.FlowLayout flowLayout2 = new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 25, 5);
        flowLayout2.setAlignOnBaseline(true);
        pnlGioiTinh1.setLayout(flowLayout2);

        rdoNam.setBackground(new java.awt.Color(255, 255, 255));
        bgrGioiTinh.add(rdoNam);
        rdoNam.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        rdoNam.setForeground(new java.awt.Color(102, 102, 102));
        rdoNam.setSelected(true);
        rdoNam.setText("Nam");
        rdoNam.setOpaque(false);
        pnlGioiTinh1.add(rdoNam);

        rdoNu.setBackground(new java.awt.Color(255, 255, 255));
        bgrGioiTinh.add(rdoNu);
        rdoNu.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        rdoNu.setForeground(new java.awt.Color(102, 102, 102));
        rdoNu.setText("Nữ");
        rdoNu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        rdoNu.setFocusPainted(false);
        rdoNu.setOpaque(false);
        pnlGioiTinh1.add(rdoNu);

        pnlMain.add(pnlGioiTinh1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 50, 285, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, 625, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtDiaChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiaChiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiaChiActionPerformed

    private void txtTenKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenKHActionPerformed

    private void btnGuiMailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuiMailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuiMailActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnResetActionPerformed

    private void rdoChuaTVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoChuaTVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoChuaTVActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgrGioiTinh;
    private javax.swing.ButtonGroup bgrThanhVien;
    private com.swingx.Button btnCapNhat;
    private com.swingx.Button btnGuiMail;
    private com.swingx.Button btnReset;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblGhiChu;
    private javax.swing.JPanel pnlButton;
    private javax.swing.JPanel pnlGioiTinh1;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlThanhVien;
    private javax.swing.JRadioButton rdoChuaTV;
    private javax.swing.JRadioButton rdoDaTV;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private com.swingx.TextField txtDiaChi;
    private com.swingx.TextField txtEmail;
    private javax.swing.JTextArea txtGhiChu;
    private com.swingx.TextField txtHoKH;
    private com.swingx.TextField txtMaKH;
    private com.swingx.TextField txtNgaySinh;
    private com.swingx.TextField txtSDT;
    private com.swingx.TextField txtTenKH;
    // End of variables declaration//GEN-END:variables

    private void clearForm() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
