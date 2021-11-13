package com.edusys.ui;

import com.edusys.dao.EduSysDAO;
import com.edusys.entity.ChuyenDe;
import com.edusys.entity.KhoaHoc;
import com.edusys.util.Auth;
import com.edusys.util.MsgBox;
import com.edusys.util.MyVerifier;
import com.edusys.util.XDate;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.text.JTextComponent;

/**
 *
 * @author nghipc
 */
public class KhoaHocJDialog extends MyDialogQuanLy<KhoaHoc> {

    /**
     * Creates new form QuanLyJDialog
     */
    public KhoaHocJDialog() {
        initComponents();
        init();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlBackground = new javax.swing.JPanel();
        pnlTitleBar = new com.edusys.components.MyTitleBar();
        lblLogo = new javax.swing.JLabel();
        lblChuyenDe = new javax.swing.JLabel();
        cboChuyenDe = new javax.swing.JComboBox<>();
        tabs = new javax.swing.JTabbedPane();
        pnlEdit = new javax.swing.JPanel();
        lblTenCD = new javax.swing.JLabel();
        lblHocPhi = new javax.swing.JLabel();
        lblKhaiGiang = new javax.swing.JLabel();
        lblThoiLuong = new javax.swing.JLabel();
        lblNguoiTao = new javax.swing.JLabel();
        lblNgayTao = new javax.swing.JLabel();
        lblGhiChu = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        txtTenCD = new com.edusys.components.MyTextField();
        txtHocPhi = new com.edusys.components.MyTextField();
        txtKhaiGiang = new com.edusys.components.MyTextField();
        txtThoiLuong = new com.edusys.components.MyTextField();
        txtNguoiTao = new com.edusys.components.MyTextField();
        txtNgayTao = new com.edusys.components.MyTextField();
        lblErrorHocPhi = new javax.swing.JLabel();
        lblErrorKhaiGiang = new javax.swing.JLabel();
        lblErrorThoiLuong = new javax.swing.JLabel();
        lblErrorNguoiTao = new javax.swing.JLabel();
        lblErrorNgayTao = new javax.swing.JLabel();
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
        jScrollPane3 = new javax.swing.JScrollPane();
        tblList = new com.edusys.components.MyTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1024, 674));
        setUndecorated(true);

        pnlBackground.setBackground(new java.awt.Color(250, 250, 250));
        pnlBackground.setBorder(new javax.swing.border.LineBorder(pnlTitleBar.getBackground(), 1, true));

        pnlTitleBar.setTitle("EDUSYS - QUẢN LÝ KHOÁ HỌC");

        lblLogo.setBackground(new java.awt.Color(255, 255, 255));
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/img/log_poly_50px.png"))); // NOI18N
        lblLogo.setOpaque(true);

        lblChuyenDe.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblChuyenDe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/img/icon/course_32px.png"))); // NOI18N

        cboChuyenDe.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        cboChuyenDe.setMaximumRowCount(5);
        cboChuyenDe.setAutoscrolls(true);
        cboChuyenDe.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        cboChuyenDe.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cboChuyenDe.setFocusable(false);

        tabs.setBackground(new java.awt.Color(102, 102, 102));
        tabs.setForeground(new java.awt.Color(51, 51, 51));
        tabs.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        pnlEdit.setBackground(new java.awt.Color(255, 255, 255));

        lblTenCD.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTenCD.setText("Tên Chuyên Đề");

        lblHocPhi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblHocPhi.setText("Học Phí");

        lblKhaiGiang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblKhaiGiang.setText("Khai Giảng");

        lblThoiLuong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblThoiLuong.setText("Thời Lượng (Giờ)");

        lblNguoiTao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNguoiTao.setText("Người Tạo");

        lblNgayTao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNgayTao.setText("Ngày Tạo");

        lblGhiChu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblGhiChu.setText("Ghi Chú");

        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBar(null);

        txtGhiChu.setColumns(20);
        txtGhiChu.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtGhiChu.setRows(5);
        txtGhiChu.setWrapStyleWord(true);
        txtGhiChu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jScrollPane2.setViewportView(txtGhiChu);

        txtTenCD.setEditable(false);
        txtTenCD.setToolTipText("");
        txtTenCD.setLabel(lblTenCD);
        txtTenCD.setOptional(true);

        txtHocPhi.setError(lblErrorHocPhi);
        txtHocPhi.setLabel(lblHocPhi);

        txtKhaiGiang.setToolTipText("");
        txtKhaiGiang.setError(lblErrorKhaiGiang);
        txtKhaiGiang.setLabel(lblKhaiGiang);
        txtKhaiGiang.setPlaceholder("DD/MM/YYYY");
        txtKhaiGiang.removeKeyListener(txtKhaiGiang.getKeyListeners()[0]);

        txtThoiLuong.setError(lblErrorThoiLuong);
        txtThoiLuong.setLabel(lblThoiLuong);

        txtNguoiTao.setEditable(false);
        txtNguoiTao.setToolTipText("");
        txtNguoiTao.setError(lblErrorNguoiTao);
        txtNguoiTao.setLabel(lblNguoiTao);

        txtNgayTao.setEditable(false);
        txtNgayTao.setToolTipText("");
        txtNgayTao.setError(lblErrorNgayTao);
        txtNgayTao.setLabel(lblNgayTao);
        txtNgayTao.setPlaceholder("");
        txtNgayTao.removeKeyListener(txtNgayTao.getKeyListeners()[0]);

        lblErrorHocPhi.setText("jLabel1");

        lblErrorKhaiGiang.setText("jLabel1");

        lblErrorThoiLuong.setText("jLabel1");

        lblErrorNguoiTao.setText("jLabel1");

        lblErrorNgayTao.setText("jLabel1");

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEditLayout.createSequentialGroup()
                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlEditLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEditLayout.createSequentialGroup()
                                .addComponent(lblTenCD)
                                .addGap(232, 232, 232))
                            .addGroup(pnlEditLayout.createSequentialGroup()
                                .addComponent(txtTenCD, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)))
                        .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlEditLayout.createSequentialGroup()
                                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblHocPhi)
                                    .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lblErrorHocPhi, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtHocPhi, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlEditLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblKhaiGiang)
                                        .addGap(222, 222, 222))
                                    .addGroup(pnlEditLayout.createSequentialGroup()
                                        .addGap(25, 25, 25)
                                        .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lblErrorKhaiGiang, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtKhaiGiang, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(pnlEditLayout.createSequentialGroup()
                                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNguoiTao)
                                    .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lblErrorNguoiTao, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtNguoiTao, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNgayTao)
                                    .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lblErrorNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlEditLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addGroup(pnlEditLayout.createSequentialGroup()
                                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblGhiChu)
                                    .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lblErrorThoiLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtThoiLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lblThoiLuong))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(pnlEditLayout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(pnlNav, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pnlBtnMain, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11)))))
                .addGap(38, 38, 38))
        );
        pnlEditLayout.setVerticalGroup(
            pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEditLayout.createSequentialGroup()
                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEditLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(lblKhaiGiang))
                    .addGroup(pnlEditLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTenCD)
                            .addGroup(pnlEditLayout.createSequentialGroup()
                                .addComponent(lblHocPhi)
                                .addGap(10, 10, 10)
                                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlEditLayout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(txtTenCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(pnlEditLayout.createSequentialGroup()
                                            .addComponent(txtKhaiGiang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(lblErrorKhaiGiang))
                                        .addGroup(pnlEditLayout.createSequentialGroup()
                                            .addComponent(txtHocPhi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(lblErrorHocPhi))))
                                .addGap(1, 1, 1)
                                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlEditLayout.createSequentialGroup()
                                        .addGap(27, 27, 27)
                                        .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lblNgayTao)
                                            .addComponent(lblNguoiTao)))
                                    .addGroup(pnlEditLayout.createSequentialGroup()
                                        .addGap(29, 29, 29)
                                        .addComponent(lblThoiLuong)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pnlEditLayout.createSequentialGroup()
                                                .addComponent(txtNguoiTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblErrorNguoiTao))
                                            .addGroup(pnlEditLayout.createSequentialGroup()
                                                .addComponent(txtThoiLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblErrorThoiLuong))
                                            .addGroup(pnlEditLayout.createSequentialGroup()
                                                .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblErrorNgayTao)))))))))
                .addGap(27, 27, 27)
                .addComponent(lblGhiChu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlBtnMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlNav, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38))
        );

        tabs.addTab("CẬP NHẬT", new javax.swing.ImageIcon(getClass().getResource("/com/edusys/img/icon/edit_property_24px.png")), pnlEdit, ""); // NOI18N

        tblList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "MÃ KHOÁ HỌC", "THỜI LƯỢNG", "HỌC PHÍ", "KHAI GIẢNG", "TẠO BỞI", "NGÀY TẠO"
            }
        ));
        jScrollPane3.setViewportView(tblList);
        tblList.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        javax.swing.GroupLayout pnlLlistLayout = new javax.swing.GroupLayout(pnlLlist);
        pnlLlist.setLayout(pnlLlistLayout);
        pnlLlistLayout.setHorizontalGroup(
            pnlLlistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLlistLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 982, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlLlistLayout.setVerticalGroup(
            pnlLlistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLlistLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabs.addTab("DANH SÁCH", new javax.swing.ImageIcon(getClass().getResource("/com/edusys/img/icon/playlist_24px.png")), pnlLlist, ""); // NOI18N

        javax.swing.GroupLayout pnlBackgroundLayout = new javax.swing.GroupLayout(pnlBackground);
        pnlBackground.setLayout(pnlBackgroundLayout);
        pnlBackgroundLayout.setHorizontalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lblChuyenDe)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboChuyenDe, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tabs)))
                .addGap(16, 16, 16))
            .addComponent(lblLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlTitleBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlBackgroundLayout.setVerticalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                .addComponent(pnlTitleBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cboChuyenDe)
                    .addComponent(lblChuyenDe, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tabs)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBackground, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            java.util.logging.Logger.getLogger(KhoaHocJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KhoaHocJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KhoaHocJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KhoaHocJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                KhoaHocJDialog dialog = new KhoaHocJDialog();
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
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnInsert;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cboChuyenDe;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblChuyenDe;
    private javax.swing.JLabel lblErrorHocPhi;
    private javax.swing.JLabel lblErrorKhaiGiang;
    private javax.swing.JLabel lblErrorNgayTao;
    private javax.swing.JLabel lblErrorNguoiTao;
    private javax.swing.JLabel lblErrorThoiLuong;
    private javax.swing.JLabel lblGhiChu;
    private javax.swing.JLabel lblHocPhi;
    private javax.swing.JLabel lblKhaiGiang;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblNgayTao;
    private javax.swing.JLabel lblNguoiTao;
    private javax.swing.JLabel lblTenCD;
    private javax.swing.JLabel lblThoiLuong;
    private javax.swing.JPanel pnlBackground;
    private javax.swing.JPanel pnlBtnMain;
    private javax.swing.JPanel pnlEdit;
    private javax.swing.JPanel pnlLlist;
    private javax.swing.JPanel pnlNav;
    private com.edusys.components.MyTitleBar pnlTitleBar;
    private javax.swing.JTabbedPane tabs;
    private com.edusys.components.MyTable tblList;
    private javax.swing.JTextArea txtGhiChu;
    private com.edusys.components.MyTextField txtHocPhi;
    private com.edusys.components.MyTextField txtKhaiGiang;
    private com.edusys.components.MyTextField txtNgayTao;
    private com.edusys.components.MyTextField txtNguoiTao;
    private com.edusys.components.MyTextField txtTenCD;
    private com.edusys.components.MyTextField txtThoiLuong;
    // End of variables declaration//GEN-END:variables

    private List<ChuyenDe> listCD;
    private KhoaHoc currentKH;

    @Override
    protected void init() {
        setLocationRelativeTo(null);
        super.tblList = tblList;
        super.tabs = tabs;
        super.fields = new JTextComponent[]{txtHocPhi, txtThoiLuong, txtKhaiGiang, txtNgayTao, txtNguoiTao, txtGhiChu};
        super.verifier = MyVerifier.KHOA_HOC_VERIFIER;
        super.dao = EduSysDAO.KHOA_HOC_DAO;
        
        setFieldName(); //đặt tên cho field
        addListeners(); //thêm listener
        updateStatus(); //cập nhật trạng thái form
        
        //set InputVerifier cho field
        for (JTextComponent field : fields) {
            field.setInputVerifier(verifier);
        }
    }

    @Override
    protected void addListeners() {
        addBtnListeners("main", btnNew, btnInsert, btnUpdate, btnDelete);
        addBtnListeners("nav", btnFirst, btnPrev, btnNext, btnLast);
        addTableListener();
        
        txtNguoiTao.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                if (txtNguoiTao.getText().length() > 5) {
                    ke.consume();
                }
            }

        });
        
        cboChuyenDe.addActionListener((ActionEvent e) -> {
            fillTable(0);
            txtTenCD.setText(cboChuyenDe.getSelectedItem() != null ? String.valueOf(cboChuyenDe.getSelectedItem()) : "");
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent we) {
                if (we.getOppositeWindow() instanceof JDialog) {
                    return;
                }
                listCD = EduSysDAO.CHUYEN_DE_DAO.selectAll();
                fillComboBox();
                fillTable(0);
                txtTenCD.setText(cboChuyenDe.getSelectedItem() != null ? String.valueOf(cboChuyenDe.getSelectedItem()) : "");
                fields[0].requestFocus();
            }

        });

    }

    @Override
    protected void updateStatus() {
        updateStatus(btnNew, btnInsert, btnUpdate, btnDelete, btnFirst, btnPrev, btnNext, btnLast);
        fields[0].setEditable(true);
        if (listCD != null && listCD.isEmpty()) {
            btnNew.setEnabled(false);
            btnInsert.setEnabled(false);
            btnUpdate.setEnabled(false);
            btnDelete.setEnabled(false);
        }
    }

    @Override
    protected void delete() {
        int count = 0;
        if (!isValidated("delete")) {
            return;
        }
        int k = MsgBox.confirm("Bạn có chắc chắn muốn xoá?", false);
        if (k == MsgBox.YES_OPTION) {
            count = dao.delete(currentKH.getMaKH());
            outputResult("Xoá", count != 0);
        }
        if (count != 0) {
            fillTable(0);
            clearForm();
        }
    }

    @Override
    protected void clearForm() {
        super.clearForm();
        currentKH = null;
//        txtTenCD.setEditable(false);
        txtNgayTao.setText(XDate.toString(new Date()));
        txtNguoiTao.setText(Auth.isLogin() ? Auth.user.getMaNV() : "");
        ChuyenDe cd = ((ChuyenDe) cboChuyenDe.getSelectedItem());
        if (cd == null) {
            return;
        }
        txtTenCD.setText(cd.getTenCD());
        txtThoiLuong.setText(String.valueOf(cd.getThoiLuong()));
        txtHocPhi.setText(String.valueOf(cd.getHocPhi()));
    }

    @Override
    protected KhoaHoc readForm() {
        String maCD = ((ChuyenDe) cboChuyenDe.getSelectedItem()).getMaCD();
        Double hocPhi = Double.parseDouble(txtHocPhi.getText());
        Integer thoiLuong = Integer.parseInt(txtThoiLuong.getText());
        String ghiChu = txtGhiChu.getText();
        String maNV = txtNguoiTao.getText();
        Date ngayKG = XDate.parse(txtKhaiGiang.getText());
        Date ngayTao = XDate.parse(txtNgayTao.getText());
        if (currentKH == null) {
            return new KhoaHoc(maCD, hocPhi, thoiLuong, ngayKG, ghiChu, maNV, ngayTao);
        } else {
            return new KhoaHoc(currentKH.getMaKH(), maCD, hocPhi, thoiLuong, ngayKG, ghiChu, maNV, ngayTao);
        }
    }

    @Override
    protected void setForm(KhoaHoc kh) {
        currentKH = kh;
        ChuyenDe cd = (ChuyenDe) EduSysDAO.CHUYEN_DE_DAO.selectById(kh.getMaCD());
        cboChuyenDe.setSelectedItem(cd);
        txtTenCD.setText(String.valueOf(cd));
        txtThoiLuong.setText(String.valueOf(kh.getThoiLuong()));
        txtHocPhi.setText(String.valueOf(kh.getHocPhi()));
        txtKhaiGiang.setText(XDate.toString(kh.getNgayKG()));
        txtNgayTao.setText(XDate.toString(kh.getNgayTao()));
        txtNguoiTao.setText(String.valueOf(kh.getMaNV()));
        txtGhiChu.setText(String.valueOf(kh.getGhiChu()));
        updateStatus();
    }

    @Override
    protected Object[] getInfo(KhoaHoc kh) {
        return new Object[]{
            kh.getMaKH(),
            kh.getThoiLuong(),
            kh.getHocPhi(),
            XDate.toString(kh.getNgayKG()),
            kh.getMaNV(),
            XDate.toString(kh.getNgayTao())
        };
    }

    @Override
    protected List<Object[]> getInfo() {
        ChuyenDe cd = (ChuyenDe) cboChuyenDe.getSelectedItem();
        List<Object[]> list = new ArrayList<>();
        ((List<KhoaHoc>) dao.selectAll()).forEach((kh) -> {
            if (cd == null || kh.getMaCD().equals(cd.getMaCD())) {
                list.add(getInfo(kh));
            }
        });
        return list;
    }

    @Override
    protected boolean isValidated(String action) {
        if (cboChuyenDe.getSelectedItem() == null) {
            MsgBox.error("Vui lòng chọn một chuyên đề");
            return false;
        }

        switch (action) {
            case "insert":
                if (!verifyField(fields)) {
                    return false;
                }
                break;
            case "update":
                if (!verifyField(fields)) {
                    return false;
                }
            case "delete":
                if (currentKH == null) {
                    return false;
                }
                break;
        }
        return true;
    }

    private void fillComboBox() {
        //sắp xếp danh sách chuyên đề theo tên
        Collections.sort(listCD, (Object t, Object t1) -> ((ChuyenDe) t).getTenCD().compareTo(((ChuyenDe) t1).getTenCD()));
        cboChuyenDe.setModel(new DefaultComboBoxModel(listCD.toArray())); //đổ dữ liệu vào comboBox
    }

    //đặt tên cho field
    private void setFieldName() {
        txtTenCD.setName("Tên chuyên đề");
        txtHocPhi.setName("Học phí");
        txtThoiLuong.setName("Thời lượng");
        txtNguoiTao.setName("Người tạo");
        txtNgayTao.setName("Ngày tạo");
        txtKhaiGiang.setName("Ngày khai giảng");

    }
}
