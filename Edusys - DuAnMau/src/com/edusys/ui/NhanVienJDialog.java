package com.edusys.ui;

import com.edusys.dao.EduSysDAO;
import com.edusys.entity.NhanVien;
import com.edusys.util.Auth;
import com.edusys.util.MsgBox;
import com.edusys.util.MyVerifier;
import com.edusys.util.XListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.text.JTextComponent;

/**
 *
 * @author nghipc
 */
public class NhanVienJDialog extends MyDialogQuanLy<NhanVien> {

    /**
     * Creates new form QuanLyJDialog
     */
    public NhanVienJDialog() {
//        super(parent, modal);
        initComponents();
        init();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgrRole = new javax.swing.ButtonGroup();
        pnlBackground = new javax.swing.JPanel();
        pnlTitleBar = new com.edusys.components.MyTitleBar();
        lblLogo = new javax.swing.JLabel();
        tabs = new javax.swing.JTabbedPane();
        pnlEdit = new javax.swing.JPanel();
        lblMaNV = new javax.swing.JLabel();
        lblHoTen = new javax.swing.JLabel();
        lblMatKhau = new javax.swing.JLabel();
        lblXacNhan = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        pnlRole = new javax.swing.JPanel();
        rdoTruongPhong = new javax.swing.JRadioButton();
        rdoNhanVien = new javax.swing.JRadioButton();
        txtMaNV = new com.edusys.components.MyTextField();
        txtHoTen = new com.edusys.components.MyTextField();
        txtMatKhau = new com.edusys.components.MyPasswordField();
        txtXacNhan = new com.edusys.components.MyPasswordField();
        txtEmail = new com.edusys.components.MyTextField();
        lblErrorMaNV = new javax.swing.JLabel();
        lblErrorHoTen = new javax.swing.JLabel();
        lblErrorMatKhau = new javax.swing.JLabel();
        lblErrorXacNhan = new javax.swing.JLabel();
        lblErrorEmail = new javax.swing.JLabel();
        pnlNav = new javax.swing.JPanel();
        btnFirst = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        pnlBtnMain = new javax.swing.JPanel();
        btnNew = new javax.swing.JButton();
        btnInsert = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        pnlLlist = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblList = new com.edusys.components.MyTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(null);
        setUndecorated(true);

        pnlBackground.setBackground(new java.awt.Color(250, 250, 250));
        pnlBackground.setBorder(new javax.swing.border.LineBorder(pnlTitleBar.getBackground(), 1, true));

        pnlTitleBar.setTitle("EDUSYS - QUẢN LÝ NHÂN VIÊN");

        lblLogo.setBackground(new java.awt.Color(255, 255, 255));
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/img/log_poly_50px.png"))); // NOI18N
        lblLogo.setOpaque(true);

        tabs.setBackground(new java.awt.Color(102, 102, 102));
        tabs.setForeground(new java.awt.Color(51, 51, 51));
        tabs.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        pnlEdit.setBackground(new java.awt.Color(255, 255, 255));

        lblMaNV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblMaNV.setText("Mã Nhân Viên");

        lblHoTen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblHoTen.setText("Họ Và Tên");

        lblMatKhau.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblMatKhau.setText("Mật Khẩu");

        lblXacNhan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblXacNhan.setText("Xác Nhận Mật Khẩu");

        lblEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblEmail.setText("Email");

        pnlRole.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Vai Trò", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N
        pnlRole.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pnlRole.setOpaque(false);

        bgrRole.add(rdoTruongPhong);
        rdoTruongPhong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdoTruongPhong.setText("Trưởng Phòng");
        rdoTruongPhong.setOpaque(false);

        bgrRole.add(rdoNhanVien);
        rdoNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdoNhanVien.setSelected(true);
        rdoNhanVien.setText("Nhân Viên");
        rdoNhanVien.setOpaque(false);

        javax.swing.GroupLayout pnlRoleLayout = new javax.swing.GroupLayout(pnlRole);
        pnlRole.setLayout(pnlRoleLayout);
        pnlRoleLayout.setHorizontalGroup(
            pnlRoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRoleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rdoNhanVien)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(rdoTruongPhong)
                .addGap(33, 33, 33))
        );
        pnlRoleLayout.setVerticalGroup(
            pnlRoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(rdoTruongPhong, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addComponent(rdoNhanVien))
        );

        txtMaNV.setError(lblErrorMaNV);
        txtMaNV.setLabel(lblMaNV);
        txtMaNV.setName("Mã nhân viên"); // NOI18N

        txtHoTen.setError(lblErrorHoTen);
        txtHoTen.setLabel(lblHoTen);
        txtHoTen.setName("Họ và tên"); // NOI18N

        txtMatKhau.setToolTipText("");
        txtMatKhau.setEchoChar('*');
        txtMatKhau.setError(lblErrorMatKhau);
        txtMatKhau.setLabel(lblMatKhau);
        txtMatKhau.setName("Mật khẩu"); // NOI18N

        txtXacNhan.setToolTipText("");
        txtXacNhan.setEchoChar('*');
        txtXacNhan.setError(lblErrorXacNhan);
        txtXacNhan.setFocusAccelerator('*');
        txtXacNhan.setLabel(lblXacNhan);
        txtXacNhan.setName("Mật khẩu"); // NOI18N

        txtEmail.setError(lblErrorEmail);
        txtEmail.setLabel(lblEmail);
        txtEmail.setName("Email"); // NOI18N

        lblErrorMaNV.setForeground(new java.awt.Color(255, 255, 255));
        lblErrorMaNV.setText("jLabel1");

        lblErrorHoTen.setForeground(new java.awt.Color(255, 255, 255));
        lblErrorHoTen.setText("jLabel1");

        lblErrorMatKhau.setForeground(new java.awt.Color(255, 255, 255));
        lblErrorMatKhau.setText("jLabel1");

        lblErrorXacNhan.setForeground(new java.awt.Color(255, 255, 255));
        lblErrorXacNhan.setText("jLabel1");

        lblErrorEmail.setForeground(new java.awt.Color(255, 255, 255));
        lblErrorEmail.setText("jLabel1");

        pnlNav.setBackground(new java.awt.Color(255, 255, 255));
        pnlNav.setLayout(new java.awt.GridLayout(1, 0));

        btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/img/icon/first_page_50px.png"))); // NOI18N
        btnFirst.setToolTipText("");
        btnFirst.setActionCommand("first");
        btnFirst.setContentAreaFilled(false);
        btnFirst.setName("First"); // NOI18N
        pnlNav.add(btnFirst);

        btnPrev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/img/icon/back_page_50px.png"))); // NOI18N
        btnPrev.setToolTipText("");
        btnPrev.setActionCommand("previous");
        btnPrev.setBorderPainted(false);
        btnPrev.setContentAreaFilled(false);
        btnPrev.setName("Previous"); // NOI18N
        pnlNav.add(btnPrev);

        btnNext.setBackground(new java.awt.Color(255, 255, 102));
        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/img/icon/next_page_50px.png"))); // NOI18N
        btnNext.setToolTipText("");
        btnNext.setActionCommand("next");
        btnNext.setBorderPainted(false);
        btnNext.setContentAreaFilled(false);
        btnNext.setName("Next"); // NOI18N
        pnlNav.add(btnNext);

        btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/img/icon/last_page_50px.png"))); // NOI18N
        btnLast.setToolTipText("");
        btnLast.setActionCommand("last");
        btnLast.setBorderPainted(false);
        btnLast.setContentAreaFilled(false);
        btnLast.setName("Last"); // NOI18N
        pnlNav.add(btnLast);

        pnlBtnMain.setBackground(new java.awt.Color(255, 255, 255));
        pnlBtnMain.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        btnNew.setBackground(new java.awt.Color(150, 34, 200));
        btnNew.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnNew.setForeground(new java.awt.Color(255, 255, 255));
        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/img/icon/new_copy_24px.png"))); // NOI18N
        btnNew.setText("Mới");
        btnNew.setActionCommand("new");
        btnNew.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnNew.setContentAreaFilled(false);
        btnNew.setOpaque(true);
        pnlBtnMain.add(btnNew);

        btnInsert.setBackground(new java.awt.Color(150, 34, 200));
        btnInsert.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnInsert.setForeground(new java.awt.Color(255, 255, 255));
        btnInsert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/img/icon/add_24px.png"))); // NOI18N
        btnInsert.setText("Thêm");
        btnInsert.setActionCommand("insert");
        btnInsert.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnInsert.setContentAreaFilled(false);
        btnInsert.setOpaque(true);
        pnlBtnMain.add(btnInsert);

        btnUpdate.setBackground(new java.awt.Color(150, 34, 200));
        btnUpdate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/img/icon/edit_24px.png"))); // NOI18N
        btnUpdate.setText("Cập nhật");
        btnUpdate.setActionCommand("update");
        btnUpdate.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnUpdate.setContentAreaFilled(false);
        btnUpdate.setOpaque(true);
        pnlBtnMain.add(btnUpdate);

        btnDelete.setBackground(new java.awt.Color(150, 34, 200));
        btnDelete.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/img/icon/delete_trash_24px.png"))); // NOI18N
        btnDelete.setText("Xoá");
        btnDelete.setActionCommand("delete");
        btnDelete.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnDelete.setContentAreaFilled(false);
        btnDelete.setOpaque(true);
        pnlBtnMain.add(btnDelete);

        javax.swing.GroupLayout pnlEditLayout = new javax.swing.GroupLayout(pnlEdit);
        pnlEdit.setLayout(pnlEditLayout);
        pnlEditLayout.setHorizontalGroup(
            pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEditLayout.createSequentialGroup()
                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlEditLayout.createSequentialGroup()
                            .addGap(40, 40, 40)
                            .addComponent(lblMaNV)
                            .addGap(392, 392, 392)
                            .addComponent(lblHoTen))
                        .addGroup(pnlEditLayout.createSequentialGroup()
                            .addGap(40, 40, 40)
                            .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(40, 40, 40)
                            .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlEditLayout.createSequentialGroup()
                            .addGap(50, 50, 50)
                            .addComponent(lblErrorMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(60, 60, 60)
                            .addComponent(lblErrorHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlEditLayout.createSequentialGroup()
                            .addGap(40, 40, 40)
                            .addComponent(lblMatKhau)
                            .addGap(420, 420, 420)
                            .addComponent(lblXacNhan))
                        .addGroup(pnlEditLayout.createSequentialGroup()
                            .addGap(40, 40, 40)
                            .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(40, 40, 40)
                            .addComponent(txtXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlEditLayout.createSequentialGroup()
                            .addGap(50, 50, 50)
                            .addComponent(lblErrorMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(60, 60, 60)
                            .addComponent(lblErrorXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlEditLayout.createSequentialGroup()
                            .addGap(40, 40, 40)
                            .addComponent(pnlRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(190, 190, 190)
                            .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblEmail)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(pnlEditLayout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addComponent(lblErrorEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(pnlEditLayout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(pnlNav, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnlBtnMain, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );
        pnlEditLayout.setVerticalGroup(
            pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEditLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMaNV)
                    .addComponent(lblHoTen))
                .addGap(10, 10, 10)
                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblErrorMaNV)
                    .addComponent(lblErrorHoTen))
                .addGap(26, 26, 26)
                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMatKhau)
                    .addComponent(lblXacNhan))
                .addGap(10, 10, 10)
                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblErrorMatKhau)
                    .addComponent(lblErrorXacNhan))
                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEditLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(pnlRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEditLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(lblEmail)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(lblErrorEmail)))
                .addGap(62, 62, 62)
                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlNav, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlBtnMain, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
        );

        tabs.addTab("CẬP NHẬT", new javax.swing.ImageIcon(getClass().getResource("/com/edusys/img/icon/edit_property_24px.png")), pnlEdit, ""); // NOI18N

        tblList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "MÃ NHÂN VIÊN", "HỌ VÀ TÊN", "MẬT KHẨU", "EMAIL", "VAI TRÒ"
            }
        ));
        tblList.setAutoscrolls(false);
        jScrollPane2.setViewportView(tblList);
        tblList.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        javax.swing.GroupLayout pnlLlistLayout = new javax.swing.GroupLayout(pnlLlist);
        pnlLlist.setLayout(pnlLlistLayout);
        pnlLlistLayout.setHorizontalGroup(
            pnlLlistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLlistLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        pnlLlistLayout.setVerticalGroup(
            pnlLlistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLlistLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabs.addTab("DANH SÁCH", new javax.swing.ImageIcon(getClass().getResource("/com/edusys/img/icon/playlist_24px.png")), pnlLlist, ""); // NOI18N

        javax.swing.GroupLayout pnlBackgroundLayout = new javax.swing.GroupLayout(pnlBackground);
        pnlBackground.setLayout(pnlBackgroundLayout);
        pnlBackgroundLayout.setHorizontalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlTitleBar, javax.swing.GroupLayout.DEFAULT_SIZE, 1022, Short.MAX_VALUE)
            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 985, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlBackgroundLayout.setVerticalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                .addComponent(pnlTitleBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
//        UIManager.put(args, args)
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NhanVienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NhanVienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NhanVienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NhanVienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                NhanVienJDialog dialog = new NhanVienJDialog();
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgrRole;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnInsert;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblErrorEmail;
    private javax.swing.JLabel lblErrorHoTen;
    private javax.swing.JLabel lblErrorMaNV;
    private javax.swing.JLabel lblErrorMatKhau;
    private javax.swing.JLabel lblErrorXacNhan;
    private javax.swing.JLabel lblHoTen;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblMaNV;
    private javax.swing.JLabel lblMatKhau;
    private javax.swing.JLabel lblXacNhan;
    private javax.swing.JPanel pnlBackground;
    private javax.swing.JPanel pnlBtnMain;
    private javax.swing.JPanel pnlEdit;
    private javax.swing.JPanel pnlLlist;
    private javax.swing.JPanel pnlNav;
    private javax.swing.JPanel pnlRole;
    private com.edusys.components.MyTitleBar pnlTitleBar;
    private javax.swing.JRadioButton rdoNhanVien;
    private javax.swing.JRadioButton rdoTruongPhong;
    private javax.swing.JTabbedPane tabs;
    private com.edusys.components.MyTable tblList;
    private com.edusys.components.MyTextField txtEmail;
    private com.edusys.components.MyTextField txtHoTen;
    private com.edusys.components.MyTextField txtMaNV;
    private com.edusys.components.MyPasswordField txtMatKhau;
    private com.edusys.components.MyPasswordField txtXacNhan;
    // End of variables declaration//GEN-END:variables

    @Override
    protected void init() {
        setLocationRelativeTo(null);
        super.tblList = tblList;
        super.tabs = tabs;
        super.fields = new JTextComponent[]{txtMaNV, txtHoTen, txtMatKhau, txtXacNhan, txtEmail};
        super.dao = EduSysDAO.NHAN_VIEN_DAO;
        super.verifier = MyVerifier.NHAN_VIEN_VERIFIER;
        
        addListeners(); //thêm listener
        updateStatus(); //cập nhật trạng thái
        
        //set InputVerifier cho field
        for (JTextComponent field : fields) {
            field.setInputVerifier(verifier);
        }
    }

    @Override
    protected void addListeners() {
        addBtnListeners("main", btnNew, btnInsert, btnUpdate, btnDelete);
        addBtnListeners("nav", btnFirst, btnPrev, btnNext, btnLast);
        XListener.addRadioFocus("Vai Trò", rdoTruongPhong, rdoNhanVien);
        addTableListener();
        XListener.addVerifyPasswordFocus(txtMatKhau, txtXacNhan);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent we) {
                if (we.getOppositeWindow() instanceof JDialog) {
                    return;
                }
                fillTable(0);
                txtMaNV.requestFocus();
                updateStatus();
            }
        });
    }

    @Override
    protected void updateStatus() {
        updateStatus(btnNew, btnInsert, btnUpdate, btnDelete, btnFirst, btnPrev, btnNext, btnLast);
        boolean isManager = Auth.isManager();

        btnNew.setEnabled(isManager);
        btnInsert.setEnabled(isManager);
        btnDelete.setEnabled(isManager);
        rdoTruongPhong.setEnabled(isManager);
        rdoNhanVien.setEnabled(isManager);
    }

    @Override
    protected void clearForm() {
        super.clearForm();
        rdoNhanVien.doClick();
    }

    @Override
    protected NhanVien readForm() {
        String maNV = txtMaNV.getText().toUpperCase();
        String hoTen = txtHoTen.getText();
        String matKhau = String.valueOf(txtMatKhau.getPassword());
        String email = txtEmail.getText();
        boolean vaiTro = rdoTruongPhong.isSelected();
        return new NhanVien(maNV, hoTen, matKhau, email, vaiTro);
    }

    @Override
    protected void setForm(NhanVien nv) {
        txtMaNV.setText(nv.getMaNV().toUpperCase());
        txtHoTen.setText(nv.getHoTen());
        txtMatKhau.setText(nv.getMatKhau());
        txtXacNhan.setText(nv.getMatKhau());
        txtEmail.setText(nv.getEmail());
        rdoTruongPhong.setSelected(nv.getVaiTro());
        rdoNhanVien.setSelected(!nv.getVaiTro());
        updateStatus();
    }

    @Override
    protected Object[] getInfo(NhanVien nv) {
        return new Object[]{
            nv.getMaNV(),
            nv.getHoTen(),
            nv.getMatKhau().replaceAll("\\w", "\\*"),
            nv.getEmail(),
            nv.getVaiTro() ? "Trưởng phòng" : "Nhân viên"
        };
    }

    @Override
    protected List<Object[]> getInfo() {
        List<Object[]> list = new ArrayList<>();
        dao.selectAll().forEach((nv) -> {
            list.add(getInfo((NhanVien) nv));
        });
        return list;
    }

    @Override
    protected boolean isValidated(String action) {
        String maNV = txtMaNV.getText();
        NhanVien nv;
        switch (action) {
            case "insert":
                if (!verifyField(fields)) {
                    return false;
                }
                nv = (NhanVien) dao.selectById(maNV);
                if (nv != null) {
                    MsgBox.error("Đã tồn tại mã nhân viên trong hệ thống");
                    return false;
                }
                break;
            case "update":
                if (!verifyField(fields)) {
                    return false;
                }
                nv = (NhanVien) dao.selectById(maNV);
                if (nv == null) {
                    MsgBox.error("Không tìm thấy mã nhân viên trong hệ thống");
                    return false;
                }
                if (!Auth.isManager()) {
                    if (!nv.getMaNV().equals(Auth.user.getMaNV())) {
                        MsgBox.error("Chỉ trưởng phòng có thể cập nhật thông tin nhân viên khác");
                        return false;
                    }
                }

                break;
            case "delete":
                if (!verifyField(txtMaNV)) {
                    return false;
                }
                nv = (NhanVien) dao.selectById(maNV);
                if (nv == null) {
                    MsgBox.error("Không tìm thấy mã nhân viên cần xoá");
                    return false;
                }
                if (nv.equals(Auth.user)) {
                    MsgBox.error("Bạn không thể xoá chính mình");
                    return false;
                }
                break;
        }
        return true;
    }

}
