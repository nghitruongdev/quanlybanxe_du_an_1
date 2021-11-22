package com.ultramotor.ui.nhanvien;

import com.ultramotor.entity.NhanVien;
import com.ultramotor.util.Auth;
import com.ultramotor.util.XDate;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

public class InfoNhanVienPanel extends javax.swing.JPanel {

    private NhanVien nv;

    public InfoNhanVienPanel() {
        initComponents();
        fillComboVaiTro();
        addListeners();
    }

    private void fillComboVaiTro() {
        List<String> list = new ArrayList<>();
        list.add("Nhân viên bán hàng");
        list.add("Nhân viên kỹ thuật");
        list.add("Nhân viên kho");
        list.add("Trưởng phòng");
        cboVaiTro.setModel(new DefaultComboBoxModel(list.toArray()));
    }

    private void updateStatus() {
        boolean isNew = nv == null;
        boolean manager = Auth.isManager();
        btnGuiMail.setVisible(!isNew);
        btnCapNhat.setText(isNew ? "Thêm mới" : "Cập nhật");
        cboVaiTro.setEditable(manager);
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
//        XImage.setIcon(XImage.read(nv.getHinh()), lblHinh);
        txtGhiChu.setText(nv.getGhiChu());
        if (nv.getGioiTinh() == true) {
            rdoNam.setSelected(true);
        } else {
            rdoNu.setSelected(true);
        }
    }

    public void reset() {
        if (nv == null) {
            for (Component c : pnlMain.getComponents()) {
                if (c instanceof JTextField) {
                    ((JTextField) c).setText("");
                }
            }
            rdoNam.setSelected(true);
            if (cboVaiTro.getItemCount() > 0) {
                cboVaiTro.setSelectedIndex(0);
            }
        } else {
            setForm();
        }
    }

    public NhanVien getForm() {

        return nv;
    }

    public void setForm(NhanVien nv) {
        this.nv = nv;
        setForm();
        updateStatus();
    }

    private void addListeners() {
        btnReset.addActionListener((ActionEvent e) -> {
            reset();
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        bgrGioiTinh = new javax.swing.ButtonGroup();
        pnlMain = new javax.swing.JPanel();
        pnlGioiTinh = new javax.swing.JPanel();
        lblTitleGioiTinh = new javax.swing.JLabel();
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
        txtSDT = new com.swingx.TextField();
        cboVaiTro = new com.swingx.ComboBoxSuggestion();
        jPanel1 = new javax.swing.JPanel();
        btnGuiMail = new com.swingx.Button();
        btnCapNhat = new com.swingx.Button();
        btnReset = new com.swingx.Button();
        lblVaiTro = new javax.swing.JLabel();

        pnlMain.setBackground(new java.awt.Color(250, 250, 250));
        java.awt.GridBagLayout pnlMainLayout = new java.awt.GridBagLayout();
        pnlMainLayout.columnWidths = new int[] {0, 20, 0, 20, 0, 20, 0, 20, 0, 20, 0};
        pnlMainLayout.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        pnlMain.setLayout(pnlMainLayout);

        pnlGioiTinh.setBackground(new java.awt.Color(250, 250, 250));
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 5);
        flowLayout1.setAlignOnBaseline(true);
        pnlGioiTinh.setLayout(flowLayout1);

        lblTitleGioiTinh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTitleGioiTinh.setForeground(new java.awt.Color(102, 102, 102));
        lblTitleGioiTinh.setText("Giới tính");
        pnlGioiTinh.add(lblTitleGioiTinh);

        rdoNam.setBackground(new java.awt.Color(255, 255, 255));
        bgrGioiTinh.add(rdoNam);
        rdoNam.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdoNam.setSelected(true);
        rdoNam.setText("Nam");
        rdoNam.setOpaque(false);
        pnlGioiTinh.add(rdoNam);

        rdoNu.setBackground(new java.awt.Color(255, 255, 255));
        bgrGioiTinh.add(rdoNu);
        rdoNu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdoNu.setText("Nữ");
        rdoNu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        rdoNu.setFocusPainted(false);
        rdoNu.setOpaque(false);
        pnlGioiTinh.add(rdoNu);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlMain.add(pnlGioiTinh, gridBagConstraints);

        lblGhiChu.setText("Ghi chú");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        pnlMain.add(lblGhiChu, gridBagConstraints);

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        jScrollPane2.setViewportView(txtGhiChu);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.weighty = 1.0;
        pnlMain.add(jScrollPane2, gridBagConstraints);

        txtMaNV.setAnimateLabel(false);
        txtMaNV.setDrawLine(false);
        txtMaNV.setLabelText("Mã nhân viên");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        pnlMain.add(txtMaNV, gridBagConstraints);

        txtHoNV.setLabelText("Họ nhân viên");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 70;
        pnlMain.add(txtHoNV, gridBagConstraints);

        txtTenNV.setLabelText("Tên nhân viên");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 135;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlMain.add(txtTenNV, gridBagConstraints);

        txtNgaySinh.setLabelText("Ngày sinh");
        txtNgaySinh.setPlaceholder("dd-MM-YYYY");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 235;
        pnlMain.add(txtNgaySinh, gridBagConstraints);

        txtDiaChi.setLabelText("Địa chỉ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        pnlMain.add(txtDiaChi, gridBagConstraints);

        txtEmail.setLabelText("Email");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        pnlMain.add(txtEmail, gridBagConstraints);

        txtLuong.setLabelText("Lương");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        pnlMain.add(txtLuong, gridBagConstraints);

        lblHinh.setToolTipText("");
        lblHinh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ultramotor/img/sp/slide1.jpg"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.ipady = 150;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 15, 0);
        pnlMain.add(lblHinh, gridBagConstraints);

        txtSDT.setLabelText("Số điện thoại");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 215;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        pnlMain.add(txtSDT, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 190;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(16, 0, 0, 0);
        pnlMain.add(cboVaiTro, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        btnGuiMail.setBackground(new java.awt.Color(0, 153, 255));
        btnGuiMail.setForeground(new java.awt.Color(255, 255, 255));
        btnGuiMail.setText("Gửi mail");
        btnGuiMail.setEnabled(false);
        btnGuiMail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel1.add(btnGuiMail);

        btnCapNhat.setText("Cập Nhật");
        jPanel1.add(btnCapNhat);

        btnReset.setBackground(new java.awt.Color(0, 153, 255));
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setText("Đặt lại");
        btnReset.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnReset.setPreferredSize(new java.awt.Dimension(71, 31));
        jPanel1.add(btnReset);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        pnlMain.add(jPanel1, gridBagConstraints);

        lblVaiTro.setText("Vai Trò");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 10, 0, 0);
        pnlMain.add(lblVaiTro, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 559, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgrGioiTinh;
    private com.swingx.Button btnCapNhat;
    private com.swingx.Button btnGuiMail;
    private com.swingx.Button btnReset;
    private com.swingx.ComboBoxSuggestion cboVaiTro;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblGhiChu;
    private com.swingx.ImageAvatar lblHinh;
    private javax.swing.JLabel lblTitleGioiTinh;
    private javax.swing.JLabel lblVaiTro;
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
