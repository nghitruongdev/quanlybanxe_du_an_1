package com.ultramotor.ui.nhanvien;

import com.swingx.MyScrollBar;
import com.swingx.PasswordField;
import com.swingx.TextField;
import com.ultramotor.dao.NhanVienDAO;
import com.ultramotor.entity.NhanVien;
import com.ultramotor.util.Auth;
import com.ultramotor.util.MsgBox;
import com.ultramotor.util.MyVerifier;
import com.ultramotor.util.XDate;
import com.ultramotor.util.XImage;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.JTextComponent;

public class NhanVienInfoPanel extends javax.swing.JPanel {

    private NhanVien nv;
    private final File path = Paths.get("logos", "nhanvien").toFile();
    private final File defaultFile = new File(path, "default.png");

    public NhanVienInfoPanel() {
        initComponents();

        fixTextPane(jScrollPane2);
        fixRadioPanel();
        setFieldName();
        addListeners();
    }

    private void updateStatus() {
        boolean isNew = nv == null;
        boolean manager = Auth.isManager();
        btnGuiMail.setVisible(!isNew);
        btnCapNhat.setText(isNew ? "Thêm mới" : "Cập nhật");
        cboVaiTro.setEditable(manager);
        txtMaNV.setEditable(isNew);
    }

    void setForm() {
        if (nv == null) {
            reset();
            return;
        }
        txtMaNV.setText(nv.getIdNV());
        txtHoNV.setText(nv.getHoNV());
        txtTenNV.setText(nv.getTenNV());
        txtNgaySinh.setText(XDate.toString(nv.getNgaySinh(), "dd-MM-yyyy"));
        txtDiaChi.setText(nv.getDiaChi());
        txtSDT.setText(nv.getSdt());
        txtEmail.setText(nv.getEmail());
        txtLuong.setText(String.valueOf(nv.getLuong()));
        cboVaiTro.setSelectedItem(nv.getVaiTro());
        XImage.setIcon(new File(path, nv.getHinh()), lblHinh, defaultFile);
        txtGhiChu.setText(nv.getGhiChu());
        rdoNam.setSelected(nv.getGioiTinh());
        rdoNu.setSelected(!nv.getGioiTinh());
        cboVaiTro.setEnabled(Auth.isManager());
        txtLuong.setEditable(Auth.isManager());
    }

    private void reset() {
        for (Component c : pnlMain.getComponents()) {
            if (c instanceof JTextField) {
                ((JTextField) c).setText("");
            }
        }
        rdoNam.setSelected(true);
        if (cboVaiTro.getItemCount() > 0) {
            cboVaiTro.setSelectedIndex(0);
        }
        XImage.setIcon(null, lblHinh, defaultFile);
        txtMaNV.requestFocus();
        if (nv != null) {
            setForm();
        }
    }

    public NhanVien getForm() {
        if (!validateField(txtMaNV, txtHoNV, txtTenNV, txtNgaySinh, txtDiaChi, txtSDT, txtEmail, txtLuong)) {
            return null;
        }
        try {
            NhanVien nv = new NhanVien();
            nv.setIdNV(txtMaNV.getText());
            nv.setHoNV(txtHoNV.getText());
            nv.setTenNV(txtTenNV.getText());
            nv.setNgaySinh(XDate.parse(txtNgaySinh.getText(), "dd-MM-yyyy"));
            nv.setDiaChi(txtDiaChi.getText());
            nv.setSdt(txtSDT.getText());
            nv.setEmail(txtEmail.getText());
            nv.setLuong(((Number)txtLuong.getValue()).doubleValue());
            nv.setVaiTro((String) cboVaiTro.getSelectedItem());
            nv.setHinh(lblHinh.getToolTipText());
            nv.setGhiChu(txtGhiChu.getText());
            nv.setGioiTinh(rdoNam.isSelected());
            return nv;
        } catch (ParseException ex) {
            Logger.getLogger(NhanVienInfoPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void setForm(NhanVien nv) {
        this.nv = nv;
        setForm();
        updateStatus();
    }

    private void fillComboVaiTro() {
        HashSet<String> set = new HashSet();
        new NhanVienDAO().selectAll().forEach((nv) -> {
            set.add(nv.getVaiTro());
        });
        cboVaiTro.setModel(new DefaultComboBoxModel(set.toArray()));
    }

    private void addListeners() {
        this.addPropertyChangeListener("ancestor", (PropertyChangeEvent e) -> {
            reset();
            fillComboVaiTro();
        });

        btnReset.addActionListener((ActionEvent e) -> {
            reset();
        });

        if (lblHinh.getMouseListeners().length > 0) {
            lblHinh.removeMouseListener(lblHinh.getMouseListeners()[0]);
        }
        lblHinh.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if (SwingUtilities.isLeftMouseButton(me) && me.getClickCount() >= 2) {
                    XImage.uploadIcon((Container) getTopLevelAncestor(), lblHinh, defaultFile);
                }
            }
        });
    }

    private boolean validateField(JTextField... fields) {
        for (JTextField field : fields) {
            if (!MyVerifier.NHAN_VIEN_VERIFIER.verify(field)) {
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
        txtMaNV.setName("Mã NV");
        txtHoNV.setName("Họ NV");
        txtTenNV.setName("Tên NV");
        txtNgaySinh.setName("Ngày sinh NV");
        txtDiaChi.setName("Địa chỉ NV");
        txtEmail.setName("Email NV");
        txtSDT.setName("Số điện thoại NV");
        txtLuong.setName("Lương NV");
        setFieldVerifier(txtMaNV, txtHoNV, txtTenNV, txtNgaySinh, txtDiaChi, txtEmail, txtSDT, txtLuong);
    }

    private void setFieldVerifier(JTextComponent... comp) {
        for (JTextComponent field : comp) {
            field.setInputVerifier(MyVerifier.NHAN_VIEN_VERIFIER);
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
                scroll.setBorder(BorderFactory.createLineBorder(txtMaNV.getLineColor(), 1));
            }

        });
    }

    private void fixRadioPanel() {
        MouseAdapter adt = new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent me) {
                pnlGioiTinh.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Giới Tính", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 11), new java.awt.Color(109, 109, 109))); // NOI18N
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                pnlGioiTinh.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(3, 155, 216)), "Giới Tính", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 11), new java.awt.Color(109, 109, 109))); // NOI18N
            }
        };

        for (Component component : pnlGioiTinh.getComponents()) {
            component.addMouseListener(adt);
        }

        pnlGioiTinh.addMouseListener(adt);
    }

    public void setMailListener(ActionListener mailListener) {
        btnGuiMail.addActionListener(mailListener);
    }

    public void setUpdateListener(ActionListener updateListener) {
        btnCapNhat.addActionListener(updateListener);
    }

    public void setFieldFocus(FocusAdapter adapter) {
        txtMaNV.addFocusListener(adapter);
    }

    public NhanVien getNhanVien() {
        return nv;
    }

    public void removeMailButton() {
        pnlButton.remove(btnGuiMail);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        bgrGioiTinh = new javax.swing.ButtonGroup();
        pnlMain = new javax.swing.JPanel();
        pnlGioiTinh = new javax.swing.JPanel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        lblGhiChu = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        txtMaNV = new com.swingx.TextField();
        txtHoNV = new com.swingx.TextField();
        txtTenNV = new com.swingx.TextField();
        txtNgaySinh = new com.swingx.TextField();
        txtDiaChi = new com.swingx.TextField();
        txtEmail = new com.swingx.TextField();
        txtLuong = new com.swingx.TextField();
        lblHinh = new com.swingx.ImageAvatar();
        cboVaiTro = new com.swingx.ComboBoxSuggestion();
        pnlButton = new javax.swing.JPanel();
        btnGuiMail = new com.swingx.Button();
        btnCapNhat = new com.swingx.Button();
        btnReset = new com.swingx.Button();
        lblVaiTro = new javax.swing.JLabel();
        txtSDT = new com.swingx.TextField();

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));
        java.awt.GridBagLayout pnlMainLayout = new java.awt.GridBagLayout();
        pnlMainLayout.columnWidths = new int[] {0, 25, 0, 25, 0};
        pnlMainLayout.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        pnlMain.setLayout(pnlMainLayout);

        pnlGioiTinh.setBackground(new java.awt.Color(255, 255, 255));
        pnlGioiTinh.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Giới Tính", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 11), new java.awt.Color(109, 109, 109))); // NOI18N
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 25, 5);
        flowLayout1.setAlignOnBaseline(true);
        pnlGioiTinh.setLayout(flowLayout1);

        rdoNam.setBackground(new java.awt.Color(255, 255, 255));
        bgrGioiTinh.add(rdoNam);
        rdoNam.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        rdoNam.setForeground(new java.awt.Color(102, 102, 102));
        rdoNam.setSelected(true);
        rdoNam.setText("Nam");
        rdoNam.setOpaque(false);
        pnlGioiTinh.add(rdoNam);

        rdoNu.setBackground(new java.awt.Color(255, 255, 255));
        bgrGioiTinh.add(rdoNu);
        rdoNu.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        rdoNu.setForeground(new java.awt.Color(102, 102, 102));
        rdoNu.setText("Nữ");
        rdoNu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        rdoNu.setFocusPainted(false);
        rdoNu.setOpaque(false);
        pnlGioiTinh.add(rdoNu);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlMain.add(pnlGioiTinh, gridBagConstraints);

        lblGhiChu.setForeground(new java.awt.Color(102, 102, 102));
        lblGhiChu.setText("Ghi chú");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        pnlMain.add(lblGhiChu, gridBagConstraints);

        txtGhiChu.setColumns(20);
        txtGhiChu.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtGhiChu.setLineWrap(true);
        txtGhiChu.setRows(5);
        txtGhiChu.setWrapStyleWord(true);
        txtGhiChu.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jScrollPane2.setViewportView(txtGhiChu);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.weighty = 1.0;
        pnlMain.add(jScrollPane2, gridBagConstraints);

        txtMaNV.setAnimateLabel(false);
        txtMaNV.setDrawLine(false);
        txtMaNV.setLabelText("Mã nhân viên");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 70;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        pnlMain.add(txtMaNV, gridBagConstraints);

        txtHoNV.setLabelText("Họ nhân viên");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 70;
        pnlMain.add(txtHoNV, gridBagConstraints);

        txtTenNV.setLabelText("Tên nhân viên");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 135;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlMain.add(txtTenNV, gridBagConstraints);

        txtNgaySinh.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd-MM-yyyy"))));
        txtNgaySinh.setLabelText("Ngày sinh");
        txtNgaySinh.setPlaceholder("dd-MM-YYYY");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 200;
        pnlMain.add(txtNgaySinh, gridBagConstraints);

        txtDiaChi.setLabelText("Địa chỉ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 200;
        pnlMain.add(txtDiaChi, gridBagConstraints);

        txtEmail.setLabelText("Email");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 250;
        pnlMain.add(txtEmail, gridBagConstraints);

        txtLuong.setLabelText("Lương");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 200;
        pnlMain.add(txtLuong, gridBagConstraints);

        lblHinh.setForeground(new java.awt.Color(109, 109, 109));
        lblHinh.setToolTipText("");
        lblHinh.setBorderSize(2);
        lblHinh.setOpaque(true);

        javax.swing.GroupLayout lblHinhLayout = new javax.swing.GroupLayout(lblHinh);
        lblHinh.setLayout(lblHinhLayout);
        lblHinhLayout.setHorizontalGroup(
            lblHinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        lblHinhLayout.setVerticalGroup(
            lblHinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.ipady = 150;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 15, 0);
        pnlMain.add(lblHinh, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 190;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weighty = 0.1;
        pnlMain.add(cboVaiTro, gridBagConstraints);

        pnlButton.setOpaque(false);
        pnlButton.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        btnGuiMail.setBackground(new java.awt.Color(0, 174, 114));
        btnGuiMail.setForeground(new java.awt.Color(255, 255, 255));
        btnGuiMail.setText("Gửi mail");
        btnGuiMail.setBorderPainted(false);
        btnGuiMail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pnlButton.add(btnGuiMail);

        btnCapNhat.setBackground(new java.awt.Color(0, 174, 114));
        btnCapNhat.setForeground(new java.awt.Color(255, 255, 255));
        btnCapNhat.setText("Cập nhật");
        btnCapNhat.setBorderPainted(false);
        btnCapNhat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pnlButton.add(btnCapNhat);

        btnReset.setBackground(new java.awt.Color(0, 174, 114));
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setText("Đặt lại");
        btnReset.setBorderPainted(false);
        btnReset.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pnlButton.add(btnReset);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.ipadx = 35;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        pnlMain.add(pnlButton, gridBagConstraints);

        lblVaiTro.setForeground(new java.awt.Color(102, 102, 102));
        lblVaiTro.setText("Vai Trò");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 10, 0, 0);
        pnlMain.add(lblVaiTro, gridBagConstraints);

        txtSDT.setLabelText("Số Điện Thoại");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 250;
        pnlMain.add(txtSDT, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgrGioiTinh;
    private com.swingx.Button btnCapNhat;
    private com.swingx.Button btnGuiMail;
    private com.swingx.Button btnReset;
    private com.swingx.ComboBoxSuggestion cboVaiTro;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblGhiChu;
    private com.swingx.ImageAvatar lblHinh;
    private javax.swing.JLabel lblVaiTro;
    private javax.swing.JPanel pnlButton;
    private javax.swing.JPanel pnlGioiTinh;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private com.swingx.TextField txtDiaChi;
    private com.swingx.TextField txtEmail;
    private javax.swing.JTextArea txtGhiChu;
    private com.swingx.TextField txtHoNV;
    private com.swingx.TextField txtLuong;
    private com.swingx.TextField txtMaNV;
    private com.swingx.TextField txtNgaySinh;
    private com.swingx.TextField txtSDT;
    private com.swingx.TextField txtTenNV;
    // End of variables declaration//GEN-END:variables

}
