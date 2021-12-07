package com.ultramotor.ui.quanlykhachhang;

import com.ultramotor.ui.nhanvien.*;
import com.swingx.MyScrollBar;
import com.swingx.PasswordField;
import com.swingx.TextField;
import com.ultramotor.entity.KhachHang;
import com.ultramotor.util.Auth;
import com.ultramotor.util.MsgBox;
import com.ultramotor.util.MyVerifier;
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
import java.beans.PropertyChangeEvent;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

public class KhachHangInfoPanel extends javax.swing.JPanel {

    private KhachHang kh;

    public KhachHangInfoPanel() {
        initComponents();
//        fillComboVaiTro();
        fixTextPane(jScrollPane2);
        fixRadioPanel();
        setFieldName();
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
        if (kh == null) {
            reset();
            return;
        }
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
        if (!validateField(txtMaKH, txtHoKH, txtTenKH, txtNgaySinh, txtDiaChi, txtEmail, txtSDT )) {
            return null;
        }
        try {
           KhachHang kh = new KhachHang();
            kh.setIdKH(txtMaKH.getText());
            kh.setHoKH(txtHoKH.getText());
            kh.setTenKH(txtTenKH.getText());
            kh.setNgaySinh(XDate.parse(txtNgaySinh.getText(),"dd--MM--yyyy"));
            kh.setDiaChi(txtDiaChi.getText());
            kh.setSdt(txtSDT.getText());
            kh.setEmail(txtEmail.getText());
            kh.setGhiChu(txtGhiChu.getText());
            kh.setGioiTinh(rdoDaTV.isSelected());
            return kh;
        } catch (ParseException ex) {
            Logger.getLogger(NhanVienInfoPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void reset() {
       for (Component c : pnlMain.getComponents()) {
            if (c instanceof JTextField) {
                ((JTextField) c).setText("");
            }
        }
        rdoNam.setSelected(true);
        rdoChuaTV.setSelected(true);
        txtMaKH.requestFocus();
        if (kh != null) {
            setForm();
        }
    }

    public void setForm(KhachHang kh) {
        this.kh = kh;
        setForm();
        updateStatus();
    }

    private void addListeners() {
        this.addPropertyChangeListener("ancestor", (PropertyChangeEvent e) -> {
            reset();
        });
        btnReset.addActionListener((ActionEvent e) -> {
            System.out.println("reseting");
            reset();
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

    private boolean validateField(JTextField... fields) {
        for (JTextField field : fields) {
            if (!MyVerifier.KHACH_HANG_VERIFIER.verify(field)) {
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
        txtMaKH.setName("Mã KH");
        txtHoKH.setName("Họ KH");
        txtTenKH.setName("Tên KH");
        txtNgaySinh.setName("Ngày sinh KH");
        txtDiaChi.setName("Địa chỉ KH");
        txtEmail.setName("Email KH");
        txtSDT.setName("Số điện thoại KH");
        setFieldVerifier(txtMaKH, txtHoKH, txtTenKH, txtNgaySinh, txtDiaChi, txtEmail, txtSDT);
    }

    private void setFieldVerifier(JTextComponent... comp) {
        for (JTextComponent field : comp) {
            field.setInputVerifier(MyVerifier.KHACH_HANG_VERIFIER);
        }
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

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));

        pnlThanhVien.setBackground(new java.awt.Color(255, 255, 255));
        pnlThanhVien.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Thành viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 11), new java.awt.Color(109, 109, 109))); // NOI18N
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 25, 5);
        flowLayout1.setAlignOnBaseline(true);
        pnlThanhVien.setLayout(flowLayout1);

        rdoDaTV.setBackground(new java.awt.Color(255, 255, 255));
        bgrThanhVien.add(rdoDaTV);
        rdoDaTV.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        rdoDaTV.setText("Đã thành viên");
        pnlThanhVien.add(rdoDaTV);

        rdoChuaTV.setBackground(new java.awt.Color(255, 255, 255));
        bgrThanhVien.add(rdoChuaTV);
        rdoChuaTV.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        rdoChuaTV.setSelected(true);
        rdoChuaTV.setText("Chưa thành viên");
        rdoChuaTV.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        rdoChuaTV.setFocusPainted(false);
        rdoChuaTV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoChuaTVActionPerformed(evt);
            }
        });
        pnlThanhVien.add(rdoChuaTV);

        lblGhiChu.setForeground(new java.awt.Color(102, 102, 102));
        lblGhiChu.setText("Ghi chú");

        txtGhiChu.setColumns(20);
        txtGhiChu.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtGhiChu.setLineWrap(true);
        txtGhiChu.setRows(5);
        txtGhiChu.setWrapStyleWord(true);
        txtGhiChu.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jScrollPane2.setViewportView(txtGhiChu);

        txtMaKH.setAnimateLabel(false);
        txtMaKH.setDrawLine(false);
        txtMaKH.setFocusCycleRoot(true);
        txtMaKH.setLabelText("Mã khách hàng");

        txtTenKH.setLabelText("Tên khách hàng");
        txtTenKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenKHActionPerformed(evt);
            }
        });

        txtNgaySinh.setLabelText("Ngày sinh");
        txtNgaySinh.setPlaceholder("dd-MM-YYYY");

        txtDiaChi.setLabelText("Địa chỉ");
        txtDiaChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiaChiActionPerformed(evt);
            }
        });

        txtEmail.setLabelText("Email");

        txtSDT.setLabelText("Số điện thoại");

        pnlButton.setOpaque(false);
        pnlButton.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        btnGuiMail.setBackground(new java.awt.Color(0, 174, 114));
        btnGuiMail.setForeground(new java.awt.Color(255, 255, 255));
        btnGuiMail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ultramotor/icon/icons8-sent.png"))); // NOI18N
        btnGuiMail.setText("Gửi mail");
        btnGuiMail.setBorderPainted(false);
        btnGuiMail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnGuiMail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuiMailActionPerformed(evt);
            }
        });
        pnlButton.add(btnGuiMail);

        btnCapNhat.setBackground(new java.awt.Color(0, 174, 114));
        btnCapNhat.setForeground(new java.awt.Color(255, 255, 255));
        btnCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ultramotor/icon/profile_white_25px.png"))); // NOI18N
        btnCapNhat.setText("Cập nhật");
        btnCapNhat.setBorderPainted(false);
        btnCapNhat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });
        pnlButton.add(btnCapNhat);

        btnReset.setBackground(new java.awt.Color(0, 174, 114));
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ultramotor/icon/refresh_25px.png"))); // NOI18N
        btnReset.setText("Đặt lại");
        btnReset.setBorderPainted(false);
        btnReset.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        pnlButton.add(btnReset);

        txtHoKH.setLabelText("Họ khách hàng");

        pnlGioiTinh1.setBackground(new java.awt.Color(255, 255, 255));
        pnlGioiTinh1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Giới Tính", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 11), new java.awt.Color(109, 109, 109))); // NOI18N
        java.awt.FlowLayout flowLayout2 = new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 25, 5);
        flowLayout2.setAlignOnBaseline(true);
        pnlGioiTinh1.setLayout(flowLayout2);

        rdoNam.setBackground(new java.awt.Color(255, 255, 255));
        bgrGioiTinh.add(rdoNam);
        rdoNam.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        rdoNam.setSelected(true);
        rdoNam.setText("Nam");
        pnlGioiTinh1.add(rdoNam);

        rdoNu.setBackground(new java.awt.Color(255, 255, 255));
        bgrGioiTinh.add(rdoNu);
        rdoNu.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        rdoNu.setText("Nữ");
        rdoNu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        rdoNu.setFocusPainted(false);
        pnlGioiTinh1.add(rdoNu);

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtHoKH, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlThanhVien, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtMaKH, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlMainLayout.createSequentialGroup()
                            .addGap(130, 130, 130)
                            .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(txtSDT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pnlGioiTinh1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblGhiChu)
                        .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(5, 5, 5))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlButton, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(133, 133, 133))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(txtHoKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(76, 76, 76)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(pnlThanhVien, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(pnlGioiTinh1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlMainLayout.createSequentialGroup()
                                        .addGap(160, 160, 160)
                                        .addComponent(lblGhiChu))
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addGap(93, 93, 93)
                                .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

}
