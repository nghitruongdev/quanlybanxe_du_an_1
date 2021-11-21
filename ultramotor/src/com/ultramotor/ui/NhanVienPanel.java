package com.ultramotor.ui;

import com.ultramotor.dao.NhanVienDAO;
import com.ultramotor.entity.NhanVien;
import com.ultramotor.util.MsgBox;
import com.ultramotor.util.XDate;
import com.ultramotor.util.XImage;
import com.ultramotor.util.XMail;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;


public class NhanVienPanel extends javax.swing.JPanel {

    /**
     * Creates new form KhachKhangPanel
     */
    public NhanVienPanel() {
        initComponents();
        init();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btgGioiTinh = new javax.swing.ButtonGroup();
        jSeparator2 = new javax.swing.JSeparator();
        pnlMain = new javax.swing.JPanel();
        pnlQuanLyNV = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        lblQLNV = new javax.swing.JLabel();
        txtTimKiem = new com.swingx.TextField();
        btnXuat = new com.swingx.Button();
        btnThemMoi = new com.swingx.Button();
        jScrollPane4 = new javax.swing.JScrollPane();
        table1 = new com.ultramotor.component.table.Table();
        pnlThemNV = new javax.swing.JPanel();
        pnlGioiTinh = new javax.swing.JPanel();
        lblTitleGioiTinh = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        btnXoa = new com.swingx.Button();
        btnGuiMail = new com.swingx.Button();
        btnCapNhat = new com.swingx.Button();
        btnMoi = new com.swingx.Button();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        lblHinh = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        lblTitleHinh = new javax.swing.JLabel();
        txtMatKhau = new com.swingx.TextField();
        txtMaNV = new com.swingx.TextField();
        txtHoNV = new com.swingx.TextField();
        txtTenNV = new com.swingx.TextField();
        txtNgaySinh = new com.swingx.TextField();
        txtDiaChi = new com.swingx.TextField();
        txtSDT = new com.swingx.TextField();
        txtEmail = new com.swingx.TextField();
        txtLuong = new com.swingx.TextField();
        txtVaiTro = new com.swingx.TextField();
        btnHuy = new com.swingx.Button();
        btnThem = new com.swingx.Button();
        pnlGuiMail = new javax.swing.JPanel();
        btnGui = new com.swingx.Button();
        lblTitleSendMail = new javax.swing.JLabel();
        pnlSendMail = new javax.swing.JPanel();
        lblGuiDen = new javax.swing.JLabel();
        txtGuiDen = new javax.swing.JTextField();
        lblChuDe = new javax.swing.JLabel();
        txtChuDe = new javax.swing.JTextField();
        lblNoiDung = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtNoiDung = new javax.swing.JTextArea();
        btnHuyMail = new com.swingx.Button();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlMain.setLayout(new java.awt.CardLayout());

        pnlQuanLyNV.setBackground(new java.awt.Color(255, 255, 255));
        pnlQuanLyNV.setName("QLNV"); // NOI18N

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID_NV", "Họ NV", "Tên NV", "Ngày Sinh", "Giới tính", "Địa chỉ", "SĐT", "Email", "Lương", "Hình", "Vai Trò", "Mật khẩu", "Ghi Chú"
            }
        ));
        jScrollPane1.setViewportView(tblNhanVien);

        lblQLNV.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblQLNV.setForeground(new java.awt.Color(0, 153, 255));
        lblQLNV.setText("UltraMotor - Quản lý nhân viên");

        txtTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTimKiem.setLabelText("Tìm kiếm");
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        btnXuat.setBackground(new java.awt.Color(0, 153, 255));
        btnXuat.setForeground(new java.awt.Color(255, 255, 255));
        btnXuat.setText("Xuất");
        btnXuat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatActionPerformed(evt);
            }
        });

        btnThemMoi.setBackground(new java.awt.Color(0, 153, 255));
        btnThemMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnThemMoi.setText("Thêm mới");
        btnThemMoi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnThemMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemMoiActionPerformed(evt);
            }
        });

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(table1);

        javax.swing.GroupLayout pnlQuanLyNVLayout = new javax.swing.GroupLayout(pnlQuanLyNV);
        pnlQuanLyNV.setLayout(pnlQuanLyNVLayout);
        pnlQuanLyNVLayout.setHorizontalGroup(
            pnlQuanLyNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQuanLyNVLayout.createSequentialGroup()
                .addGroup(pnlQuanLyNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlQuanLyNVLayout.createSequentialGroup()
                        .addContainerGap(782, Short.MAX_VALUE)
                        .addComponent(btnThemMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlQuanLyNVLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(lblQLNV)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 311, Short.MAX_VALUE)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlQuanLyNVLayout.createSequentialGroup()
                        .addGap(0, 10, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlQuanLyNVLayout.setVerticalGroup(
            pnlQuanLyNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQuanLyNVLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlQuanLyNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblQLNV))
                .addGap(71, 71, 71)
                .addGroup(pnlQuanLyNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnlQuanLyNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlMain.add(pnlQuanLyNV, "QLNV");

        pnlThemNV.setBackground(new java.awt.Color(255, 255, 255));
        pnlThemNV.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Staff Information", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 24), new java.awt.Color(0, 153, 255))); // NOI18N
        pnlThemNV.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        pnlThemNV.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlGioiTinh.setBackground(new java.awt.Color(255, 255, 255));
        pnlGioiTinh.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lblTitleGioiTinh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTitleGioiTinh.setForeground(new java.awt.Color(102, 102, 102));
        lblTitleGioiTinh.setText("Giới tính");
        pnlGioiTinh.add(lblTitleGioiTinh);

        rdoNam.setBackground(new java.awt.Color(255, 255, 255));
        btgGioiTinh.add(rdoNam);
        rdoNam.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdoNam.setSelected(true);
        rdoNam.setText("Nam");
        pnlGioiTinh.add(rdoNam);

        rdoNu.setBackground(new java.awt.Color(255, 255, 255));
        btgGioiTinh.add(rdoNu);
        rdoNu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdoNu.setText("Nữ");
        rdoNu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        rdoNu.setFocusPainted(false);
        pnlGioiTinh.add(rdoNu);

        pnlThemNV.add(pnlGioiTinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 320, 210, 29));

        btnXoa.setBackground(new java.awt.Color(0, 153, 255));
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setText("Xóa");
        btnXoa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        pnlThemNV.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 530, 90, -1));

        btnGuiMail.setBackground(new java.awt.Color(0, 153, 255));
        btnGuiMail.setForeground(new java.awt.Color(255, 255, 255));
        btnGuiMail.setText("Gửi mail");
        btnGuiMail.setEnabled(false);
        btnGuiMail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnGuiMail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuiMailActionPerformed(evt);
            }
        });
        pnlThemNV.add(btnGuiMail, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 530, 90, -1));

        btnCapNhat.setBackground(new java.awt.Color(0, 153, 255));
        btnCapNhat.setForeground(new java.awt.Color(255, 255, 255));
        btnCapNhat.setText("Cập nhật");
        btnCapNhat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCapNhat.setPreferredSize(new java.awt.Dimension(71, 31));
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });
        pnlThemNV.add(btnCapNhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 530, 90, -1));

        btnMoi.setBackground(new java.awt.Color(0, 153, 255));
        btnMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnMoi.setText("Mới");
        btnMoi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnMoi.setPreferredSize(new java.awt.Dimension(71, 31));
        pnlThemNV.add(btnMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 530, 90, -1));
        pnlThemNV.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 200, -1, -1));

        jLabel1.setText("Ghi chú");
        pnlThemNV.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 370, -1, -1));

        lblHinh.setBackground(new java.awt.Color(255, 255, 255));
        lblHinh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHinh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlThemNV.add(lblHinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 70, 270, 240));

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        jScrollPane2.setViewportView(txtGhiChu);

        pnlThemNV.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 370, 270, 120));

        lblTitleHinh.setText("Hình ảnh");
        pnlThemNV.add(lblTitleHinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 70, -1, -1));

        txtMatKhau.setLabelText("Mật khẩu");
        pnlThemNV.add(txtMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 510, 350, -1));

        txtMaNV.setLabelText("Mã nhân viên");
        pnlThemNV.add(txtMaNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 350, -1));

        txtHoNV.setLabelText("Họ nhân viên");
        pnlThemNV.add(txtHoNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 350, -1));

        txtTenNV.setLabelText("Tên nhân viên");
        pnlThemNV.add(txtTenNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, 350, -1));

        txtNgaySinh.setLabelText("Ngày sinh DD/MM/YYYY");
        pnlThemNV.add(txtNgaySinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, 350, -1));

        txtDiaChi.setLabelText("Địa chỉ");
        pnlThemNV.add(txtDiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 260, 350, -1));

        txtSDT.setLabelText("Số điện thoại");
        pnlThemNV.add(txtSDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 310, 350, -1));

        txtEmail.setLabelText("Email");
        pnlThemNV.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 360, 350, -1));

        txtLuong.setLabelText("Lương");
        pnlThemNV.add(txtLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 410, 350, -1));

        txtVaiTro.setLabelText("Vai trò");
        pnlThemNV.add(txtVaiTro, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 460, 350, -1));

        btnHuy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ultramotor/img/icon/close-icon.png"))); // NOI18N
        btnHuy.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnHuy.setPreferredSize(new java.awt.Dimension(71, 31));
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });
        pnlThemNV.add(btnHuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 20, 30, 30));

        btnThem.setBackground(new java.awt.Color(0, 153, 255));
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setText("Thêm");
        btnThem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        pnlThemNV.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 530, 90, -1));

        pnlMain.add(pnlThemNV, "TNV");

        pnlGuiMail.setBackground(new java.awt.Color(255, 255, 255));

        btnGui.setBackground(new java.awt.Color(0, 153, 255));
        btnGui.setForeground(new java.awt.Color(255, 255, 255));
        btnGui.setText("Gửi");
        btnGui.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblTitleSendMail.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitleSendMail.setForeground(new java.awt.Color(0, 153, 255));
        lblTitleSendMail.setText("Send new E-mail");

        pnlSendMail.setBackground(new java.awt.Color(255, 255, 255));
        pnlSendMail.setBorder(new javax.swing.border.MatteBorder(null));

        lblGuiDen.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblGuiDen.setText("Gửi đến:");

        lblChuDe.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblChuDe.setText("Chủ đề:");

        lblNoiDung.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblNoiDung.setText("Nội dung:");

        txtNoiDung.setColumns(20);
        txtNoiDung.setRows(5);
        jScrollPane3.setViewportView(txtNoiDung);

        javax.swing.GroupLayout pnlSendMailLayout = new javax.swing.GroupLayout(pnlSendMail);
        pnlSendMail.setLayout(pnlSendMailLayout);
        pnlSendMailLayout.setHorizontalGroup(
            pnlSendMailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSendMailLayout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(pnlSendMailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblNoiDung)
                    .addComponent(lblChuDe)
                    .addComponent(lblGuiDen))
                .addGap(18, 18, 18)
                .addGroup(pnlSendMailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtGuiDen)
                    .addComponent(txtChuDe, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
                    .addComponent(jScrollPane3))
                .addContainerGap(84, Short.MAX_VALUE))
        );
        pnlSendMailLayout.setVerticalGroup(
            pnlSendMailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSendMailLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(pnlSendMailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblGuiDen)
                    .addComponent(txtGuiDen, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(pnlSendMailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblChuDe)
                    .addComponent(txtChuDe, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(pnlSendMailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNoiDung)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        btnHuyMail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ultramotor/img/icon/close-icon.png"))); // NOI18N
        btnHuyMail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnHuyMail.setPreferredSize(new java.awt.Dimension(71, 31));
        btnHuyMail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyMailActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlGuiMailLayout = new javax.swing.GroupLayout(pnlGuiMail);
        pnlGuiMail.setLayout(pnlGuiMailLayout);
        pnlGuiMailLayout.setHorizontalGroup(
            pnlGuiMailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGuiMailLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlGuiMailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlGuiMailLayout.createSequentialGroup()
                        .addComponent(btnGui, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(451, 451, 451))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlGuiMailLayout.createSequentialGroup()
                        .addComponent(lblTitleSendMail)
                        .addGap(376, 376, 376)
                        .addComponent(btnHuyMail, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(pnlGuiMailLayout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addComponent(pnlSendMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 83, Short.MAX_VALUE))
        );
        pnlGuiMailLayout.setVerticalGroup(
            pnlGuiMailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlGuiMailLayout.createSequentialGroup()
                .addGroup(pnlGuiMailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlGuiMailLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblTitleSendMail))
                    .addComponent(btnHuyMail, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(pnlSendMail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnGui, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );

        pnlMain.add(pnlGuiMail, "GuiMail");

        add(pnlMain, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1040, 620));
    }// </editor-fold>//GEN-END:initComponents

    private void btnXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXuatActionPerformed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnGuiMailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuiMailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuiMailActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnThemMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemMoiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemMoiActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnHuyMailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyMailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHuyMailActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgGioiTinh;
    private com.swingx.Button btnCapNhat;
    private com.swingx.Button btnGui;
    private com.swingx.Button btnGuiMail;
    private com.swingx.Button btnHuy;
    private com.swingx.Button btnHuyMail;
    private com.swingx.Button btnMoi;
    private com.swingx.Button btnThem;
    private com.swingx.Button btnThemMoi;
    private com.swingx.Button btnXoa;
    private com.swingx.Button btnXuat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblChuDe;
    private javax.swing.JLabel lblGuiDen;
    private javax.swing.JLabel lblHinh;
    private javax.swing.JLabel lblNoiDung;
    private javax.swing.JLabel lblQLNV;
    private javax.swing.JLabel lblTitleGioiTinh;
    private javax.swing.JLabel lblTitleHinh;
    private javax.swing.JLabel lblTitleSendMail;
    private javax.swing.JPanel pnlGioiTinh;
    private javax.swing.JPanel pnlGuiMail;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlQuanLyNV;
    private javax.swing.JPanel pnlSendMail;
    private javax.swing.JPanel pnlThemNV;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private com.ultramotor.component.table.Table table1;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTextField txtChuDe;
    private com.swingx.TextField txtDiaChi;
    private com.swingx.TextField txtEmail;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtGuiDen;
    private com.swingx.TextField txtHoNV;
    private com.swingx.TextField txtLuong;
    private com.swingx.TextField txtMaNV;
    private com.swingx.TextField txtMatKhau;
    private com.swingx.TextField txtNgaySinh;
    private javax.swing.JTextArea txtNoiDung;
    private com.swingx.TextField txtSDT;
    private com.swingx.TextField txtTenNV;
    private com.swingx.TextField txtTimKiem;
    private com.swingx.TextField txtVaiTro;
    // End of variables declaration//GEN-END:variables
    NhanVienDAO dao = new NhanVienDAO();
    int row = -1; //vị trí hàng được chọn trên table

    private void init() {
        this.fillTable();
        addBtnListeners();
        addLblListeners();
        addTblListeners();
        addTxtListeners();
    }

    void insert() throws ParseException {
//        if (!Auth.isManager()) {
//            MsgBox.inform("Bạn không có quyền thêm nhân viên!");
//        } else {
        NhanVien nv = getForm();
        if (nv.getMatKhau().length() < 3 || nv.getMatKhau().length() > 16) {
            MsgBox.inform("Vui lòng nhập kí tự mật khẩu từ 3 - 16 kí tự!");
            return;
        }
        try {
            dao.insert(nv);
            this.fillTable();
            this.clearForm();
            MsgBox.inform("Thêm mới thành công!");
        } catch (Exception e) {
            MsgBox.inform("Thêm mới thất bại!");
        }
//    }
    }

    void update() {
        NhanVien nv = getForm();
        try {
            dao.update(nv);
            this.fillTable();
            this.clearForm();
            MsgBox.inform("Cập nhật thành công!");
        } catch (Exception e) {
            MsgBox.inform("Cập nhật thất bại!");
        }
    }

    void delete() {
//        if (!Auth.isManager()) {
//            MsgBox.inform("Bạn không có quyền xóa nhân viên!");
//        } else {
        String manv = txtMaNV.getText();
//            if (manv.equals(Auth.user.getIdNV())) {
//                MsgBox.inform("Bạn không được xóa chính bạn!");
//            } else if (MsgBox.confirm("Bạn thực sự muốn xóa nhân viên này?", false) == 0) {
        try {
            dao.delete(manv);
            this.fillTable();
            this.clearForm();
            MsgBox.inform("Xóa thành công!");
        } catch (Exception e) {
            MsgBox.inform("Xóa thất bại!");
        }
//            }
//        }
    }

    void clearForm() {
        txtMaNV.setText("");
        txtHoNV.setText("");
        txtTenNV.setText("");
        txtNgaySinh.setText("");
        txtDiaChi.setText("");
        txtSDT.setText("");
        txtEmail.setText("");
        txtLuong.setText("");
        txtVaiTro.setText("");
        txtMatKhau.setText("");
        XImage.setIcon(XImage.read("default.png"), lblHinh);
        txtGhiChu.setText("");
        rdoNam.setSelected(true);
        this.row = -1;
    }

    void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
        model.setRowCount(0); // xóa tất cả các hàng trên jtable
        try {
            String keyword = txtTimKiem.getText();
            List<NhanVien> list = dao.selectByKeyword(keyword); // đọc dữ liệu từ csdl
            for (NhanVien nv : list) {
                String baoMat = nv.getMatKhau();
                baoMat = "***********";
                Object[] row = {nv.getIdNV(), nv.getHoNV(), nv.getTenNV(),
                    XDate.toString(nv.getNgaySinh(), "dd/MM/yyyy"),
                    nv.getGioiTinh() ? "Nam" : "Nữ", nv.getDiaChi(),
                    nv.getSdt(), nv.getEmail(), nv.getLuong(),
                    nv.getHinh(), nv.getVaiTro(), baoMat,
                    nv.getGhiChu()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.error("Lỗi truy vấn dữ liệu");
        }
    }


    // lấy dữ liệu từ form về entity
    NhanVien getForm() {
        NhanVien nv = new NhanVien();
        nv.setIdNV(txtMaNV.getText());
        nv.setHoNV(txtHoNV.getText());
        nv.setTenNV(txtTenNV.getText());
        try {
            nv.setNgaySinh(XDate.parse(txtNgaySinh.getText(), "dd/MM/yyyy"));
        } catch (ParseException ex) {
            Logger.getLogger(NhanVienPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        nv.setDiaChi(txtDiaChi.getText());
        nv.setSdt(txtSDT.getText());
        nv.setEmail(txtEmail.getText());
        nv.setLuong(Float.parseFloat(txtLuong.getText()));
        nv.setVaiTro(txtVaiTro.getText());
        nv.setMatKhau(txtMatKhau.getText());
        nv.setHinh(lblHinh.getToolTipText());
        nv.setGhiChu(txtGhiChu.getText());
        if (rdoNam.isSelected()) {
            nv.setGioiTinh(true);
        } else {
            nv.setGioiTinh(false);
        }
        return nv;
    }

    // lấy dữ liệu từ entity về form
    void setForm(NhanVien nv) {
        txtMaNV.setText(nv.getIdNV());
        txtHoNV.setText(nv.getHoNV());
        txtTenNV.setText(nv.getTenNV());
        txtNgaySinh.setText(XDate.toString(nv.getNgaySinh(), "dd/MM/yyyy"));
        txtDiaChi.setText(nv.getDiaChi());
        txtSDT.setText(nv.getSdt());
        txtEmail.setText(nv.getEmail());
        txtLuong.setText(String.valueOf(nv.getLuong()));
        txtVaiTro.setText(nv.getVaiTro());
        txtMatKhau.setText(nv.getMatKhau());
        XImage.setIcon(XImage.read(nv.getHinh()), lblHinh);
        txtGhiChu.setText(nv.getGhiChu());
        if (nv.getGioiTinh() == true) {
            rdoNam.setSelected(true);
        } else {
            rdoNu.setSelected(true);
        }
        return;

    }

    // lấy thông tin lên field khi click table
    void edit() {
        String idNV = (String) tblNhanVien.getValueAt(this.row, 0);
        NhanVien nv = dao.selectByID(idNV);
        this.setForm(nv);
        showCard("TNV");
    }

    //hiển thị card theo card name
    public void showCard(String name) {
        ((java.awt.CardLayout) pnlMain.getLayout()).show(pnlMain, name);
        if (!isVisible()) {
            setVisible(true);
        }
    }

    //thêm listeners cho các label
    private void addLblListeners() {
        lblHinh.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                XImage.uploadIcon(lblHinh);
            }
        });
    }

    //thêm listeners cho các table
    private void addTblListeners() {
        tblNhanVien.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                row = tblNhanVien.getSelectedRow();
                edit();
                txtMaNV.setEditable(false);
                txtMaNV.setForeground(Color.red);              
                btnGuiMail.setEnabled(true);
                btnThem.setEnabled(false);
                btnXoa.setEnabled(true);
                btnCapNhat.setEnabled(true);
                btnMoi.setEnabled(false);
                
            }
        });

    }
    
    // thêm listeners cho các textfield
    private void addTxtListeners(){
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                //code xử lý keyReleased ở đây.
                fillTable();
            }
        });
    }

    //thêm listeners cho các button 
    private void addBtnListeners() {

        btnThemMoi.addActionListener((ActionEvent e) -> {
            showCard("TNV");
            btnXoa.setEnabled(false);
            btnCapNhat.setEnabled(false);
            btnGuiMail.setEnabled(false);

        });
        btnCapNhat.addActionListener((ActionEvent e) -> {
            update();
            showCard("QLNV");

        });
        btnXoa.addActionListener((ActionEvent e) -> {
            delete();
            showCard("QLNV");

        });
        btnMoi.addActionListener((ActionEvent e) -> {
            clearForm();
            btnGuiMail.setEnabled(true);
            btnXoa.setEnabled(false);
            btnCapNhat.setEnabled(false);
            btnGuiMail.setEnabled(false);

        });
        btnHuy.addActionListener((ActionEvent e) -> {
            showCard("QLNV");

        });
        btnHuyMail.addActionListener((ActionEvent e) -> {
            showCard("QLNV");

        });
        btnThem.addActionListener((ActionEvent e) -> {
            try {
                insert();
                showCard("QLNV");
            } catch (ParseException ex) {
                Logger.getLogger(NhanVienPanel.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        btnGuiMail.addActionListener((ActionEvent e) -> {
            txtGuiDen.setText(txtEmail.getText());
            txtGuiDen.setEditable(false);
            txtGuiDen.setForeground(Color.red);    
            showCard("GuiMail");
              

        });
        btnGui.addActionListener((ActionEvent e) -> {
            sendEmail();

        });

    }
    
    //gửi mail cho nhân viên
    private void sendEmail() {
        //kiểm tra email trong database
        String email = txtGuiDen.getText();
        if (email == null) { //không tìm thấy
            MsgBox.error("Không tìm thấy địa chỉ Email");
        } else {
            //soạn nội dung mail 
            String mailContent = txtNoiDung.getText();
            XMail.sendMail(email, mailContent, txtChuDe.getText()); //gửi mail
            MsgBox.inform("Gửi mail thành công!");
        }
    }

}
