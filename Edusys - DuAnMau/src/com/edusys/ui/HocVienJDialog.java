package com.edusys.ui;

import com.edusys.dao.*;
import com.edusys.entity.*;
import com.edusys.util.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nghipc
 */
public class HocVienJDialog extends javax.swing.JDialog {

    /**
     * Creates new form QuanLyJDialog
     */
    public HocVienJDialog() {
        super(EduSysFrame.getFrame(), true);
        initComponents();
        init();
    }

    public HocVienJDialog(javax.swing.JFrame frame) {
        super(frame, true);
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
        lblKhoaHoc = new javax.swing.JLabel();
        cboKhoaHoc = new javax.swing.JComboBox<>();
        tabs = new javax.swing.JTabbedPane();
        pnlHocVien = new javax.swing.JPanel();
        btnXoaKhoiKH = new javax.swing.JButton();
        lblSearchHV = new javax.swing.JLabel();
        pnlSearchHV = new javax.swing.JPanel();
        txtSearchHV = new javax.swing.JTextField();
        btnSearchHV = new javax.swing.JButton();
        btnCapNhatDiem = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHocVien = new com.edusys.components.MyTable();
        chkAll = new javax.swing.JCheckBox();
        chkDaNhap = new javax.swing.JCheckBox();
        chkChuaNhap = new javax.swing.JCheckBox();
        btnSendMail = new javax.swing.JButton();
        pnlNguoiHoc = new javax.swing.JPanel();
        btnThemVaoKH = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblNguoiHoc = new com.edusys.components.MyTable();
        lblSearchNH = new javax.swing.JLabel();
        pnlSearchNH = new javax.swing.JPanel();
        txtSearchNH = new javax.swing.JTextField();
        btnSearchNH = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(null);
        setUndecorated(true);

        pnlBackground.setBackground(new java.awt.Color(250, 250, 250));

        pnlTitleBar.setTitle("EDUSYS - QUẢN LÝ HỌC VIÊN");

        lblLogo.setBackground(new java.awt.Color(255, 255, 255));
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/img/log_poly_50px.png"))); // NOI18N
        lblLogo.setOpaque(true);

        lblChuyenDe.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblChuyenDe.setText("CHUYÊN ĐỀ");

        cboChuyenDe.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        cboChuyenDe.setMaximumRowCount(5);
        cboChuyenDe.setAutoscrolls(true);
        cboChuyenDe.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        cboChuyenDe.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cboChuyenDe.setFocusable(false);

        lblKhoaHoc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblKhoaHoc.setText("KHOÁ HỌC");

        cboKhoaHoc.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        cboKhoaHoc.setMaximumRowCount(5);
        cboKhoaHoc.setAutoscrolls(true);
        cboKhoaHoc.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        cboKhoaHoc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cboKhoaHoc.setFocusable(false);

        tabs.setBackground(new java.awt.Color(102, 102, 102));
        tabs.setForeground(new java.awt.Color(51, 51, 51));
        tabs.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnXoaKhoiKH.setBackground(new java.awt.Color(150, 34, 200));
        btnXoaKhoiKH.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnXoaKhoiKH.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaKhoiKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/img/icon/delete_trash_24px.png"))); // NOI18N
        btnXoaKhoiKH.setText("Xoá Khỏi Khoá Học");
        btnXoaKhoiKH.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnXoaKhoiKH.setContentAreaFilled(false);
        btnXoaKhoiKH.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/img/icon/delete_trash_24px.png"))); // NOI18N
        btnXoaKhoiKH.setMinimumSize(new java.awt.Dimension(65, 27));
        btnXoaKhoiKH.setOpaque(true);
        btnXoaKhoiKH.setPreferredSize(new java.awt.Dimension(65, 27));

        lblSearchHV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/img/icon/search_client_24px.png"))); // NOI18N

        pnlSearchHV.setBackground(new java.awt.Color(255, 255, 255));
        pnlSearchHV.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        txtSearchHV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSearchHV.setBorder(null);
        txtSearchHV.setPreferredSize(new java.awt.Dimension(0, 28));

        btnSearchHV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/img/icon/search_black_24px.png"))); // NOI18N
        btnSearchHV.setBorderPainted(false);
        btnSearchHV.setContentAreaFilled(false);

        javax.swing.GroupLayout pnlSearchHVLayout = new javax.swing.GroupLayout(pnlSearchHV);
        pnlSearchHV.setLayout(pnlSearchHVLayout);
        pnlSearchHVLayout.setHorizontalGroup(
            pnlSearchHVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSearchHVLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSearchHV, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                .addComponent(btnSearchHV, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlSearchHVLayout.setVerticalGroup(
            pnlSearchHVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSearchHVLayout.createSequentialGroup()
                .addGroup(pnlSearchHVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnSearchHV, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(txtSearchHV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        btnCapNhatDiem.setBackground(new java.awt.Color(150, 34, 200));
        btnCapNhatDiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCapNhatDiem.setForeground(new java.awt.Color(255, 255, 255));
        btnCapNhatDiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/img/icon/renew_24px.png"))); // NOI18N
        btnCapNhatDiem.setText("Cập Nhật Điểm");
        btnCapNhatDiem.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnCapNhatDiem.setBorderPainted(false);
        btnCapNhatDiem.setContentAreaFilled(false);
        btnCapNhatDiem.setMinimumSize(new java.awt.Dimension(65, 27));
        btnCapNhatDiem.setOpaque(true);
        btnCapNhatDiem.setPreferredSize(new java.awt.Dimension(65, 27));

        tblHocVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TT", "MÃ HỌC VIÊN", "MÃ NGƯỜI HỌC", "HỌ VÀ TÊN ", "ĐIỂM", "SELECT"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHocVien.setEditable(true);
        jScrollPane2.setViewportView(tblHocVien);
        tblHocVien.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        chkAll.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chkAll.setSelected(true);
        chkAll.setText("Tất cả");

        chkDaNhap.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chkDaNhap.setSelected(true);
        chkDaNhap.setText("Đã nhập điểm");

        chkChuaNhap.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chkChuaNhap.setSelected(true);
        chkChuaNhap.setText("Chưa nhập điểm");

        btnSendMail.setBackground(new java.awt.Color(150, 34, 200));
        btnSendMail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSendMail.setForeground(new java.awt.Color(255, 255, 255));
        btnSendMail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/img/icon/mailing_30px.png"))); // NOI18N
        btnSendMail.setText("Gửi Bảng Điểm");
        btnSendMail.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnSendMail.setBorderPainted(false);
        btnSendMail.setContentAreaFilled(false);
        btnSendMail.setMinimumSize(new java.awt.Dimension(65, 27));
        btnSendMail.setOpaque(true);
        btnSendMail.setPreferredSize(new java.awt.Dimension(65, 27));

        javax.swing.GroupLayout pnlHocVienLayout = new javax.swing.GroupLayout(pnlHocVien);
        pnlHocVien.setLayout(pnlHocVienLayout);
        pnlHocVienLayout.setHorizontalGroup(
            pnlHocVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHocVienLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlHocVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHocVienLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(chkAll)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chkChuaNhap)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkDaNhap)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSendMail, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCapNhatDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXoaKhoiKH, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlHocVienLayout.createSequentialGroup()
                        .addComponent(lblSearchHV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlSearchHV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(10, 10, 10)))
                .addContainerGap())
        );
        pnlHocVienLayout.setVerticalGroup(
            pnlHocVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHocVienLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(pnlHocVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSearchHV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlSearchHV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(pnlHocVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoaKhoiKH, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapNhatDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkAll)
                    .addComponent(chkDaNhap)
                    .addComponent(chkChuaNhap)
                    .addComponent(btnSendMail, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tabs.addTab("HỌC VIÊN", new javax.swing.ImageIcon(getClass().getResource("/com/edusys/img/icon/read_online_24px.png")), pnlHocVien, ""); // NOI18N

        btnThemVaoKH.setBackground(new java.awt.Color(150, 34, 200));
        btnThemVaoKH.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnThemVaoKH.setForeground(new java.awt.Color(255, 255, 255));
        btnThemVaoKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/img/icon/new_copy_24px.png"))); // NOI18N
        btnThemVaoKH.setText("Thêm Vào Khoá Học");
        btnThemVaoKH.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnThemVaoKH.setContentAreaFilled(false);
        btnThemVaoKH.setMinimumSize(new java.awt.Dimension(65, 27));
        btnThemVaoKH.setOpaque(true);
        btnThemVaoKH.setPreferredSize(new java.awt.Dimension(65, 27));

        tblNguoiHoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MÃ NGƯỜI HỌC", "HỌ VÀ TÊN", "GIỚI TÍNH", "NGÀY SINH", "ĐIỆN THOẠI", "EMAIL", "SELECT"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNguoiHoc.setEditable(true);
        jScrollPane4.setViewportView(tblNguoiHoc);
        tblNguoiHoc.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        lblSearchNH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/img/icon/search_client_24px.png"))); // NOI18N

        pnlSearchNH.setBackground(new java.awt.Color(255, 255, 255));
        pnlSearchNH.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        txtSearchNH.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSearchNH.setBorder(null);
        txtSearchNH.setPreferredSize(new java.awt.Dimension(0, 28));

        btnSearchNH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/img/icon/search_black_24px.png"))); // NOI18N
        btnSearchNH.setBorderPainted(false);
        btnSearchNH.setContentAreaFilled(false);

        javax.swing.GroupLayout pnlSearchNHLayout = new javax.swing.GroupLayout(pnlSearchNH);
        pnlSearchNH.setLayout(pnlSearchNHLayout);
        pnlSearchNHLayout.setHorizontalGroup(
            pnlSearchNHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSearchNHLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSearchNH, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                .addComponent(btnSearchNH, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlSearchNHLayout.setVerticalGroup(
            pnlSearchNHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSearchNHLayout.createSequentialGroup()
                .addGroup(pnlSearchNHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnSearchNH, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(txtSearchNH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlNguoiHocLayout = new javax.swing.GroupLayout(pnlNguoiHoc);
        pnlNguoiHoc.setLayout(pnlNguoiHocLayout);
        pnlNguoiHocLayout.setHorizontalGroup(
            pnlNguoiHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNguoiHocLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlNguoiHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNguoiHocLayout.createSequentialGroup()
                        .addGap(0, 795, Short.MAX_VALUE)
                        .addComponent(btnThemVaoKH, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlNguoiHocLayout.createSequentialGroup()
                        .addComponent(lblSearchNH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlSearchNH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(pnlNguoiHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlNguoiHocLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane4)
                    .addContainerGap()))
        );
        pnlNguoiHocLayout.setVerticalGroup(
            pnlNguoiHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNguoiHocLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlNguoiHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSearchNH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlSearchNH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 361, Short.MAX_VALUE)
                .addComponent(btnThemVaoKH, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(pnlNguoiHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlNguoiHocLayout.createSequentialGroup()
                    .addGap(50, 50, 50)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(76, Short.MAX_VALUE)))
        );

        tabs.addTab("NGƯỜI HỌC", new javax.swing.ImageIcon(getClass().getResource("/com/edusys/img/icon/conference_24px.png")), pnlNguoiHoc, ""); // NOI18N

        javax.swing.GroupLayout pnlBackgroundLayout = new javax.swing.GroupLayout(pnlBackground);
        pnlBackground.setLayout(pnlBackgroundLayout);
        pnlBackgroundLayout.setHorizontalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(lblChuyenDe)
                        .addGap(12, 12, 12)
                        .addComponent(cboChuyenDe, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblKhoaHoc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboKhoaHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(pnlTitleBar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlBackgroundLayout.setVerticalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                .addComponent(pnlTitleBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboChuyenDe, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblChuyenDe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblKhoaHoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboKhoaHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(tabs)
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
            java.util.logging.Logger.getLogger(HocVienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HocVienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HocVienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HocVienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                HocVienJDialog dialog = new HocVienJDialog(new JFrame());
//                HocVienJDialog dialog = new HocVienJDialog();
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
    private javax.swing.JButton btnCapNhatDiem;
    private javax.swing.JButton btnSearchHV;
    private javax.swing.JButton btnSearchNH;
    private javax.swing.JButton btnSendMail;
    private javax.swing.JButton btnThemVaoKH;
    private javax.swing.JButton btnXoaKhoiKH;
    private javax.swing.JComboBox<String> cboChuyenDe;
    private javax.swing.JComboBox<String> cboKhoaHoc;
    private javax.swing.JCheckBox chkAll;
    private javax.swing.JCheckBox chkChuaNhap;
    private javax.swing.JCheckBox chkDaNhap;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblChuyenDe;
    private javax.swing.JLabel lblKhoaHoc;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblSearchHV;
    private javax.swing.JLabel lblSearchNH;
    private javax.swing.JPanel pnlBackground;
    private javax.swing.JPanel pnlHocVien;
    private javax.swing.JPanel pnlNguoiHoc;
    private javax.swing.JPanel pnlSearchHV;
    private javax.swing.JPanel pnlSearchNH;
    private com.edusys.components.MyTitleBar pnlTitleBar;
    private javax.swing.JTabbedPane tabs;
    private com.edusys.components.MyTable tblHocVien;
    private com.edusys.components.MyTable tblNguoiHoc;
    private javax.swing.JTextField txtSearchHV;
    private javax.swing.JTextField txtSearchNH;
    // End of variables declaration//GEN-END:variables

    private EduSysDAO chuyenDeDAO;
    private EduSysDAO khoaHocDAO;
    private EduSysDAO nguoiHocDAO;
    private EduSysDAO hocVienDAO;
    private List<ChuyenDe> listCD;
    private List<NguoiHoc> listNH;
    private HashMap<HocVien, NguoiHoc> map;

    //khởi tạo thành phần
    private void init() {
        chuyenDeDAO = EduSysDAO.CHUYEN_DE_DAO;
        khoaHocDAO = EduSysDAO.KHOA_HOC_DAO;
        nguoiHocDAO = EduSysDAO.NGUOI_HOC_DAO;
        hocVienDAO = EduSysDAO.HOC_VIEN_DAO;
        map = new HashMap<>(); 
       
        addListeners(); //thêm listener
        setLocationRelativeTo(null); //đặt vị trí form 
    }

    //thêm listener cho các thành phần trên form
    private void addListeners() {
        this.addWindowListener(XListener.dialogLs); //kiểm tra đăng nhập 
        
        //fill dữ liệu khi mở form
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent we) {
                if (we.getOppositeWindow() instanceof JDialog) {
                    return;
                }
                listNH = nguoiHocDAO.selectAll();
                listCD = chuyenDeDAO.selectAll();

                fillComboChuyenDe();
                fillComboKhoaHoc();
                fillTableNguoiHoc();
                chkAll.setSelected(false);
                chkAll.doClick();
            }
        });

        cboChuyenDe.addActionListener((ActionEvent e) -> {
            fillComboKhoaHoc();
        });
        
        cboKhoaHoc.addActionListener((ActionEvent e) -> {
            fillTables();
        });

        txtSearchHV.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                fillTableHocVien();
            }

        });

        txtSearchNH.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                fillTableNguoiHoc();
            }

        });

        btnThemVaoKH.addActionListener((ActionEvent e) -> {
            addToCourse();
        });
        
        btnXoaKhoiKH.addActionListener((ActionEvent e) -> {
            removeFromCourse();
        });
        
        btnCapNhatDiem.addActionListener((ActionEvent e) -> {
            updateDiem();
        });
        
        btnSendMail.addActionListener((ActionEvent e) -> {
            List<Integer> list = getSelectedRows(tblHocVien, tblHocVien.getColumnCount() - 1);
            if (list.isEmpty()) {
                MsgBox.error("Bạn chưa chọn học viên cần gửi mail");
            } else {
                sendMail(list);
            }
        });
        
        tblHocVien.addPropertyChangeListener((PropertyChangeEvent e) -> {
            if (e.getPropertyName().equals("tableCellEditor")) {
                Object grade = tblHocVien.getCellEditor(0, 4).getCellEditorValue();
                validateGrade(grade);
            }
        });
        
        addCheckBoxListeners(chkAll, chkDaNhap, chkChuaNhap);
        
    }

    //đổ dữ liệu comboChuyenDe
    private void fillComboChuyenDe() {
        //sắp xếp danh sách chuyên đề theo tên
        Collections.sort(listCD, (Object t, Object t1) -> ((ChuyenDe) t).getTenCD().compareTo(((ChuyenDe) t1).getTenCD())); 
        cboChuyenDe.setModel(new DefaultComboBoxModel(listCD.toArray())); //đổ dữ liệu
    }

    //đổ dữ liệu comboKhoaHoc
    private void fillComboKhoaHoc() {
        String maCD = ((ChuyenDe) cboChuyenDe.getSelectedItem()).getMaCD(); //lấy mã chuyên dề
        List<KhoaHoc> listKH = new ArrayList<>();
        ((List<KhoaHoc>) khoaHocDAO.selectAll()).forEach((kh) -> {
            if (maCD.equals(kh.getMaCD())) { //lấy khoá học theo mã chuyên đề
                listKH.add(kh);
            }
        });
        
        //sắp xếp khoá học theo ngày khai giảng
        Collections.sort(listKH, (Object t, Object t1) -> ((KhoaHoc) t).getNgayKG().compareTo(((KhoaHoc) t1).getNgayKG()));
        cboKhoaHoc.setModel(new DefaultComboBoxModel(listKH.toArray())); //đổ dữ liệu
        fillTables(); //fill các table trên form
    }

    //đổ dữ liệu lên bảng học viên
    private void fillTableHocVien() {
        DefaultTableModel model = (DefaultTableModel) tblHocVien.getModel();
        model.setRowCount(0);
        map.clear();
        
        KhoaHoc kh = ((KhoaHoc) cboKhoaHoc.getSelectedItem()); //lấy khoá học
        if (kh == null) {
            btnCapNhatDiem.setEnabled(false);
            btnXoaKhoiKH.setEnabled(false);
            return;
        }
        
        String keyword = txtSearchHV.getText().toLowerCase().trim(); //lấy kewword search
        ((List<HocVien>) hocVienDAO.selectAll()).forEach((hv) -> {
            if (hv.getMaKH().equals(kh.getMaKH())) {
                NguoiHoc nh = (NguoiHoc) nguoiHocDAO.selectById(hv.getMaNH());
                map.put(hv, nh); //thêm người học nào có đăng kí khoá học hiện tại vào map
                
                //kiểm tra thông tin học viên có chứa keyword
                boolean empty = keyword.isEmpty(); //lấy tất cả học viên
                boolean name = nh.getHoTen().toLowerCase().contains(keyword);
                boolean maNH = hv.getMaNH().toLowerCase().contains(keyword);
                boolean maHV = String.valueOf(hv.getMaHV()).contains(keyword);
                boolean searchRs = empty || name || maNH || maHV;

                //kiểm tra dữ liệu điểm học viên
                boolean diemNull = hv.getDiem() == null;
                //chkAll true: lấy tất cả/ chkChuaNhap true: điểm == null/ chkDaNhap true: điểm != null
                boolean checkRs = chkAll.isSelected() || (diemNull && chkChuaNhap.isSelected()) || (!diemNull && chkDaNhap.isSelected());
                
                if (searchRs && checkRs) {
                    model.addRow(new Object[]{
                        model.getRowCount() + 1,
                        hv.getMaHV(),
                        hv.getMaNH(),
                        nh.getHoTen(),
                        hv.getDiem()
                    });
                }
            }
        });
    }

    //đổ dữ liệu lên bảng người học
    private void fillTableNguoiHoc() {
        DefaultTableModel model = (DefaultTableModel) tblNguoiHoc.getModel();
        model.setRowCount(0);
        
        listNH.forEach((nh) -> {
            String keyword = txtSearchNH.getText().toLowerCase().trim(); //lấy keyword search
            boolean isHV = map.containsValue(nh); //kiểm tra người học có thuộc map hocvien, nếu có thì không thêm vào DS
            
            //kiểm tra thông tin người học có chứa keyword
            boolean empty = keyword.isEmpty(); //lấy tất cả học viên
            boolean name = nh.getHoTen().toLowerCase().contains(keyword);
            boolean maNH = nh.getMaNH().toLowerCase().contains(keyword);
            boolean birth = XDate.toString(nh.getNgaySinh()).contains(keyword);
            boolean phone = nh.getDienThoai().contains(keyword);
            boolean email = nh.getEmail().toLowerCase().contains(keyword);

            if (!isHV && (empty || name || maNH || birth || phone || email)) {
                model.addRow(new Object[]{
                    nh.getMaNH(),
                    nh.getHoTen(),
                    nh.getGioiTinh() ? "Nam" : "Nữ",
                    XDate.toString(nh.getNgaySinh()),
                    nh.getDienThoai(),
                    nh.getEmail(),
                });
            }
        });
    }

    //xoá học viên khỏi khoá học
    private void removeFromCourse() {
        //lấy danh sách chọn
        List<Integer> list = getSelectedRows(tblHocVien, tblHocVien.getColumnCount() - 1);
        
        //kiểm tra danh sách trống
        if (list.isEmpty()) {
            MsgBox.warning("Vui lòng chọn học viên cần xoá");
            return;
        }
        
        //hỏi xác nhận người dùng trước khi xoá
        int k = MsgBox.confirm("Bạn có chắc chắn muốn xoá học viên đã chọn?", false);
        if (k != 0) {
            return;
        }
        
        //xoá trong CSDL
        list.stream().map((i) -> Integer.parseInt(String.valueOf(tblHocVien.getValueAt(i, 1)))).forEachOrdered((maHV) -> {
            hocVienDAO.delete(maHV);
        });
        
        MsgBox.inform("Xoá học viên thành công"); //xuất thông báo
        fillTables(); //filltable bảng học viên và bảng người học
    }

    //thêm người học vào khoá học
    private void addToCourse() {
        int count = 0;
        //lấy danh sách được chọn trên bảng người học
        for (int index : getSelectedRows(tblNguoiHoc, tblNguoiHoc.getColumnCount() - 1)) {
            HocVien hv = readInfoTblNguoiHoc(index); //lấy thông tin học viên
            if (hv == null) {
                continue;
            }
            count += hocVienDAO.insert(hv); //thêm vào CSDL
        }
        if (count != 0) { //xuất thông báo kết quả, fillTables và chuyển về tab học viên
            MsgBox.inform("Thêm học viên thành công");
            fillTables();
            tabs.setSelectedIndex(0);
        }

    }

    //kiểm lỗi nhập điểm
    private void validateGrade(Object grade) {
        //check null
        if (grade == null) {
            return;
        }
        int index = tblHocVien.findIndexValue(String.valueOf(grade), 4); //lấy index tại vị trí đang sửa điểm
        
        //kiểm lỗi điểm và xuát thông báo
        if (!XValidate.validateGrade(String.valueOf(grade))) {
            MsgBox.error("Điểm không hợp lệ"); //xuất thông báo
            
            //lấy thông tin cũ của học viên trên CSDL
            HocVien hv = ((HocVien) hocVienDAO.selectById(Integer.parseInt(String.valueOf(tblHocVien.getValueAt(index, 1)))));
            //cập nhật lại thông tin sai trên bảng
            tblHocVien.setValueAt(hv.getDiem(), index, 4); 
        } else {
            tblHocVien.setValueAt(true, index, 5); //điểm hợp lệ, tự động tick chọn học viên
        }
    }

    //cập nhật điểm cho học viên
    private void updateDiem() {
        int count = 0;
        
        //kiểm tra bảng có đang edit điểm hay không, kiểm lỗi điểm, nếu không hợp lệ dừng chức năng
        if (tblHocVien.getCellEditor(0, 4).stopCellEditing()
                && !XValidate.validateGrade((String) tblHocVien.getCellEditor(0, 4).getCellEditorValue())) {
            return;
        }
        
        //lấy danh sách được chọn trên bảng
        List<Integer> list = getSelectedRows(tblHocVien, 5);
        //cập nhật điểm vào CSDL
        count = list.stream().map((index) -> readInfoTblHocVien(index)).filter((hv) -> !(hv == null)).map((hv) -> hocVienDAO.update(hv)).reduce(count, Integer::sum);
        
        if (count != 0) {//xuất thông báo kết quả
            MsgBox.inform("Cập nhật điểm học viên thành công");
            //hỏi xác nhận gửi mail
            int k = MsgBox.confirm("Bạn có muốn gửi mail bảng điểm cho sinh viên", false);
            if (k == 0) {//nếu đồng ý, tiến hành gửi mail cho tất cả học viên mà được cập nhật điểm
                sendMail(list);
            }
            fillTableHocVien(); //fill lại table học viên
        }

    }

    //gửi mail cho học viên theo danh sách được chọn trên bảng
    private void sendMail(List<Integer> list) {
        Map<String, String> mailMap = new HashMap<>();
        for (int index : list) {
            HocVien hv = readInfoTblHocVien(index); //lấy thông tin HocVien
            NguoiHoc nh = map.get(hv); //lấy thông tin hocVien từ NguoiHoc
            KhoaHoc kh = (KhoaHoc) khoaHocDAO.selectById(hv.getMaKH()); //lấy thông tin Khoá học
            String tenCD = ((ChuyenDe) chuyenDeDAO.selectById(kh.getMaCD())).getTenCD(); //lấy tên chuyên đề
            String ngayKG = XDate.toString(kh.getNgayKG()); //láy ngày khai giảng
            Double diem = hv.getDiem(); //lấy thông tin điểm
            
            //kiểm tra học viên đã có điểm hay chưa trước khi gửi bảng diểm và hỏi xác nhận
            if (diem == null) {
                int k = MsgBox.confirm(String.format("Học viên %s chưa có điểm! Bạn có chắc muốn gửi mail không?", hv.getMaHV()), false);
                if (k != 0) {
                    continue;
                }
            }
            
            //lấy địa chỉ email của học viên và soạn nội dung mail tương ứng
            String email = nh.getEmail();
            String content = String.format("<p><p>%s thân mến,</p>\n"
                    + "<div>Lời đầu tiên LapTrinhCity xin chân thành gửi lời"
                    + " chúc mừng đến bạn vì đã hoàn thành khoá học %s"
                    + " khai giảng vào ngày %s.</p>"
                    + "<p>Điểm cuối kì bạn đạt được: <span style=\"color:red; font-size:18px\">%.1f</span></p>"
                    + "<p>Nếu có thắc mắc hoặc cần hỗ trợ, hãy liên hệ ngay với LapTrinhCity nhé.</p>"
                    + "<p>Thank you for choosing LapTrinhCity!</p>"
                    + "<p>LapTrinhCity Team,</p></div>", nh.getHoTen(), tenCD, ngayKG, diem);
            mailMap.put(email, content);
        }
        XMail.sendMail(mailMap, "BẢNG ĐIỂM CUỐI KHOÁ", null); //gửi mail
        fillTableHocVien(); //fill lại table học viên
    }

    //lấy thông tin từ bảng người học
    private HocVien readInfoTblNguoiHoc(int index) {
        //kiểm tra index
        if (index < 0 || index > tblNguoiHoc.getRowCount()) {
            return null;
        }
        
        //lấy thông tin maKH, maNH
        Integer maKH = ((KhoaHoc) cboKhoaHoc.getSelectedItem()).getMaKH();
        String maNH = String.valueOf(tblNguoiHoc.getValueAt(index, 0));
        return new HocVien(maKH, maNH);
    }

    //lấy thông tin học viên từ bảng học viên
    private HocVien readInfoTblHocVien(int index) {
        //kiểm tra index
        if (index < 0 || index > tblNguoiHoc.getRowCount()) {
            return null;
        }
        //lấy thông tin maKH, maHV, maNH, diem
        Integer maKH = ((KhoaHoc) cboKhoaHoc.getSelectedItem()).getMaKH();
        Integer maHV = Integer.parseInt(String.valueOf(tblHocVien.getValueAt(index, 1)));
        String maNH = String.valueOf(tblHocVien.getValueAt(index, 2));
        Object diem = tblHocVien.getValueAt(index, 4);
        return new HocVien(maHV, maKH, maNH, diem);
    }

    //fill lại các table, tblHocVien -> tblNguoiHoc, cập nhật lại trạng thái form
    private void fillTables() {
        fillTableHocVien();
        fillTableNguoiHoc();
        updateStatus();
    }

    //cập nhật lại trạng thái cho form
    private void updateStatus() {
        boolean emptyKH = cboKhoaHoc.getItemCount() == 0;
        boolean emptyHV = tblHocVien.getRowCount() == 0;
        boolean emptyNH = tblNguoiHoc.getRowCount() == 0;

        chkAll.setEnabled(!emptyHV);
        chkChuaNhap.setEnabled(!emptyHV);
        chkDaNhap.setEnabled(!emptyHV);

        btnThemVaoKH.setEnabled(!emptyKH && !emptyNH);
        btnCapNhatDiem.setEnabled(!emptyHV);
        btnXoaKhoiKH.setEnabled(Auth.isManager() && !emptyHV);
        btnSendMail.setEnabled(!emptyHV);
        formatDisabledButton(btnThemVaoKH, btnCapNhatDiem, btnXoaKhoiKH, btnSendMail);
    }

    //định dạng button khi bị disabled
    private void formatDisabledButton(JButton... buttons) {
        for (JButton button : buttons) {
            if (!button.isEnabled()) {
                button.setDisabledIcon(button.getIcon());
                button.setBackground(MyConstants.GRAY_COLOR);
            } else {
                button.setBackground(MyConstants.PURPLE_COLOR);
            }
        }
    }

    //thêm listener cho các checkbox
    private void addCheckBoxListeners(JCheckBox... chks) {
        for (JCheckBox chk : chks) {
            chk.addActionListener((ActionEvent e) -> {
                switch (e.getActionCommand()) {
                    case "Tất cả": //nếu chkAll checked
                        chkChuaNhap.setSelected(true); //luôn luôn chọn chkChuaNhap
                        chkDaNhap.setSelected(chkAll.isSelected()); //thay đổi theo chkAll
                        break;
                    default:
                        //tự động chọn chkAll nếu cả 2 chk còn lại đều selected
                        chkAll.setSelected(chkChuaNhap.isSelected() && chkDaNhap.isSelected()); 
                }
                fillTableHocVien(); //fill lại table học viên
            });

        }
    }

    //lấy danh sách chọn trên bảng, index: vị trí cột boolean chọn theo mỗi bảng
    private List<Integer> getSelectedRows(JTable table, int index) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < table.getRowCount(); i++) {
            if ("true".equals(String.valueOf(table.getValueAt(i, index)))) {
                list.add(i);
            }
        }
        return list;
    }

}
