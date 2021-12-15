/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ultramotor.ui.thongke;

import com.swingx.Button;
import com.swingx.PopupMenuItem;
import com.ultramotor.dao.HoaDonDAO;
import com.ultramotor.dao.NhapKhoDAO;
import com.ultramotor.dao.ThongKeDAO;
import com.ultramotor.util.MsgBox;
import com.ultramotor.util.UltraExporter;
import com.ultramotor.util.XExcel;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author BRAVO
 */
public class ThongKePanel extends javax.swing.JPanel {

    public ThongKePanel() {
        initComponents();
        init();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        lblQLNV = new javax.swing.JLabel();
        tpnlThongKe = new javax.swing.JTabbedPane();
        pnlDoanhThu = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblDoanhThuChiTiet = new com.swingx.table.Table();
        cboNamDT = new com.swingx.ComboBoxSuggestion();
        lblNam = new javax.swing.JLabel();
        btnSave = new com.swingx.Button();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblDoanhThuHang = new com.swingx.table.Table();
        lbltitleTongDT = new javax.swing.JLabel();
        lblTongDT = new javax.swing.JLabel();
        pnlSanPham = new javax.swing.JPanel();
        btnSave1 = new com.swingx.Button();
        lblNam1 = new javax.swing.JLabel();
        cboNamSP = new com.swingx.ComboBoxSuggestion();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblSanPhamBanChay = new com.swingx.table.Table();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblSanPhamBanCham = new com.swingx.table.Table();
        pnlKho = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblSanPhamNhapKho = new com.swingx.table.Table();
        btnSave2 = new com.swingx.Button();
        lblNam2 = new javax.swing.JLabel();
        cboNamNK = new com.swingx.ComboBoxSuggestion();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblSanPhamTonKho = new com.swingx.table.Table();
        jLabel4 = new javax.swing.JLabel();
        pnlNhanVien = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblNhanVien = new com.swingx.table.Table();
        cboNamNV = new com.swingx.ComboBoxSuggestion();
        lblNam3 = new javax.swing.JLabel();
        btnSave3 = new com.swingx.Button();
        jLabel6 = new javax.swing.JLabel();
        pnlKhachHang = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblKhachHang = new com.swingx.table.Table();
        cboNamKH = new com.swingx.ComboBoxSuggestion();
        lblNam4 = new javax.swing.JLabel();
        btnSave4 = new com.swingx.Button();
        jLabel5 = new javax.swing.JLabel();

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 671, Short.MAX_VALUE)
        );

        setOpaque(false);

        lblQLNV.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblQLNV.setForeground(new java.awt.Color(0, 174, 114));
        lblQLNV.setText("QUẢN LÝ THỐNG KÊ");

        tpnlThongKe.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        tpnlThongKe.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
        tpnlThongKe.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        pnlDoanhThu.setOpaque(false);

        tblDoanhThuChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sản phẩm", "Màu sắc", "Phân khối", "Số lượng", "Doanh thu"
            }
        ));
        jScrollPane4.setViewportView(tblDoanhThuChiTiet);

        lblNam.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblNam.setText("Năm");

        btnSave.setBackground(new java.awt.Color(0, 174, 114));
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setText("Xuất danh sách");
        btnSave.setActionCommand("DoanhThu");
        btnSave.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSave.setRadius(10);

        tblDoanhThuHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nhà sản xuất", "Số lượng", "Tổng doanh thu"
            }
        ));
        jScrollPane11.setViewportView(tblDoanhThuHang);

        lbltitleTongDT.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbltitleTongDT.setText("Tổng doanh thu của năm:");

        javax.swing.GroupLayout pnlDoanhThuLayout = new javax.swing.GroupLayout(pnlDoanhThu);
        pnlDoanhThu.setLayout(pnlDoanhThuLayout);
        pnlDoanhThuLayout.setHorizontalGroup(
            pnlDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDoanhThuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlDoanhThuLayout.createSequentialGroup()
                        .addComponent(lbltitleTongDT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTongDT, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDoanhThuLayout.createSequentialGroup()
                                .addComponent(lblNam)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cboNamDT, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnSave, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlDoanhThuLayout.createSequentialGroup()
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlDoanhThuLayout.setVerticalGroup(
            pnlDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDoanhThuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboNamDT, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDoanhThuLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(pnlDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTongDT, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbltitleTongDT))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDoanhThuLayout.createSequentialGroup()
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(301, Short.MAX_VALUE))
                    .addGroup(pnlDoanhThuLayout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(21, 21, 21)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))))
        );

        tpnlThongKe.addTab("DOANH THU", pnlDoanhThu);

        pnlSanPham.setOpaque(false);

        btnSave1.setBackground(new java.awt.Color(0, 174, 114));
        btnSave1.setForeground(new java.awt.Color(255, 255, 255));
        btnSave1.setText("Xuất danh sách");
        btnSave1.setActionCommand("SanPham");
        btnSave1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSave1.setRadius(10);

        lblNam1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblNam1.setText("Năm");

        jPanel1.setOpaque(false);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Top 10 sản phẩm bán chạy nhất năm");

        tblSanPhamBanChay.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sản phẩm", "Màu sắc", "Phân khối", "Số lượng"
            }
        ));
        jScrollPane6.setViewportView(tblSanPhamBanChay);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 255, Short.MAX_VALUE))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(16, 16, 16)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE))
        );

        jPanel2.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Top 10 sản phẩm bán chậm nhất năm");

        tblSanPhamBanCham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sản phẩm", "Màu sắc", "Phân khối", "Số lượng"
            }
        ));
        jScrollPane9.setViewportView(tblSanPhamBanCham);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 244, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)))
                .addGap(0, 0, 0))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(16, 16, 16)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlSanPhamLayout = new javax.swing.GroupLayout(pnlSanPham);
        pnlSanPham.setLayout(pnlSanPhamLayout);
        pnlSanPhamLayout.setHorizontalGroup(
            pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSanPhamLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(32, 32, 32)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSanPhamLayout.createSequentialGroup()
                        .addComponent(lblNam1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboNamSP, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnSave1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        pnlSanPhamLayout.setVerticalGroup(
            pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboNamSP, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNam1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20)
                .addComponent(btnSave1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        tpnlThongKe.addTab("SẢN PHẨM", pnlSanPham);

        pnlKho.setOpaque(false);

        tblSanPhamNhapKho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sản phẩm", "Màu sắc", "Phân khối", "Số lượng nhập kho"
            }
        ));
        jScrollPane7.setViewportView(tblSanPhamNhapKho);

        btnSave2.setBackground(new java.awt.Color(0, 174, 114));
        btnSave2.setForeground(new java.awt.Color(255, 255, 255));
        btnSave2.setText("Xuất danh sách");
        btnSave2.setActionCommand("Kho");
        btnSave2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSave2.setRadius(10);
        btnSave2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave2ActionPerformed(evt);
            }
        });

        lblNam2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblNam2.setText("Năm");

        tblSanPhamTonKho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sản phẩm", "Màu sắc", "Phân khối", "Số lượng tồn kho"
            }
        ));
        jScrollPane8.setViewportView(tblSanPhamTonKho);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Các sản phẩm tồn kho");

        javax.swing.GroupLayout pnlKhoLayout = new javax.swing.GroupLayout(pnlKho);
        pnlKho.setLayout(pnlKhoLayout);
        pnlKhoLayout.setHorizontalGroup(
            pnlKhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKhoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlKhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlKhoLayout.createSequentialGroup()
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
                        .addGap(62, 62, 62)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlKhoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSave2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlKhoLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblNam2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboNamNK, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlKhoLayout.setVerticalGroup(
            pnlKhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKhoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlKhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboNamNK, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNam2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(pnlKhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(20, 20, 20)
                .addComponent(btnSave2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        tpnlThongKe.addTab("KHO", pnlKho);

        pnlNhanVien.setOpaque(false);

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nhân viên", "Tên nhân viên", "Ngày sinh", "Giới tính", "Số lượng bán"
            }
        ));
        jScrollPane5.setViewportView(tblNhanVien);

        lblNam3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblNam3.setText("Năm");

        btnSave3.setBackground(new java.awt.Color(0, 174, 114));
        btnSave3.setForeground(new java.awt.Color(255, 255, 255));
        btnSave3.setText("Xuất danh sách");
        btnSave3.setActionCommand("NhanVien");
        btnSave3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSave3.setRadius(10);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Danh sách nhân viên bán hàng tiêu biểu");

        javax.swing.GroupLayout pnlNhanVienLayout = new javax.swing.GroupLayout(pnlNhanVien);
        pnlNhanVien.setLayout(pnlNhanVienLayout);
        pnlNhanVienLayout.setHorizontalGroup(
            pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNhanVienLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1080, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNhanVienLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSave3, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNhanVienLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblNam3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboNamNV, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlNhanVienLayout.setVerticalGroup(
            pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNhanVienLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboNamNV, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNam3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6))
                .addGap(15, 15, 15)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                .addGap(20, 20, 20)
                .addComponent(btnSave3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        tpnlThongKe.addTab("NHÂN VIÊN", pnlNhanVien);

        pnlKhachHang.setOpaque(false);

        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã khách hàng", "Tên khách hàng", "Ngày sinh", "Giới tính", "SDT", "Email", "Số lượng mua"
            }
        ));
        jScrollPane10.setViewportView(tblKhachHang);

        lblNam4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblNam4.setText("Năm");

        btnSave4.setBackground(new java.awt.Color(0, 174, 114));
        btnSave4.setForeground(new java.awt.Color(255, 255, 255));
        btnSave4.setText("Xuất danh sách");
        btnSave4.setActionCommand("KhachHang");
        btnSave4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSave4.setRadius(10);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Danh sách khách hàng mua hàng tiêu biểu");

        javax.swing.GroupLayout pnlKhachHangLayout = new javax.swing.GroupLayout(pnlKhachHang);
        pnlKhachHang.setLayout(pnlKhachHangLayout);
        pnlKhachHangLayout.setHorizontalGroup(
            pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1080, Short.MAX_VALUE)
                    .addComponent(btnSave4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlKhachHangLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblNam4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboNamKH, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlKhachHangLayout.setVerticalGroup(
            pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlKhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboNamKH, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNam4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5))
                .addGap(15, 15, 15)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                .addGap(20, 20, 20)
                .addComponent(btnSave4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        tpnlThongKe.addTab("KHÁCH HÀNG", pnlKhachHang);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblQLNV)
                    .addComponent(tpnlThongKe))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblQLNV)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tpnlThongKe)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSave2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSave2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.swingx.Button btnSave;
    private com.swingx.Button btnSave1;
    private com.swingx.Button btnSave2;
    private com.swingx.Button btnSave3;
    private com.swingx.Button btnSave4;
    private com.swingx.ComboBoxSuggestion cboNamDT;
    private com.swingx.ComboBoxSuggestion cboNamKH;
    private com.swingx.ComboBoxSuggestion cboNamNK;
    private com.swingx.ComboBoxSuggestion cboNamNV;
    private com.swingx.ComboBoxSuggestion cboNamSP;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel lblNam;
    private javax.swing.JLabel lblNam1;
    private javax.swing.JLabel lblNam2;
    private javax.swing.JLabel lblNam3;
    private javax.swing.JLabel lblNam4;
    private javax.swing.JLabel lblQLNV;
    private javax.swing.JLabel lblTongDT;
    private javax.swing.JLabel lbltitleTongDT;
    private javax.swing.JPanel pnlDoanhThu;
    private javax.swing.JPanel pnlKhachHang;
    private javax.swing.JPanel pnlKho;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlNhanVien;
    private javax.swing.JPanel pnlSanPham;
    private com.swingx.table.Table tblDoanhThuChiTiet;
    private com.swingx.table.Table tblDoanhThuHang;
    private com.swingx.table.Table tblKhachHang;
    private com.swingx.table.Table tblNhanVien;
    private com.swingx.table.Table tblSanPhamBanCham;
    private com.swingx.table.Table tblSanPhamBanChay;
    private com.swingx.table.Table tblSanPhamNhapKho;
    private com.swingx.table.Table tblSanPhamTonKho;
    private javax.swing.JTabbedPane tpnlThongKe;
    // End of variables declaration//GEN-END:variables
    ThongKeDAO tkdao = new ThongKeDAO();
    HoaDonDAO hddao = new HoaDonDAO();
    NhapKhoDAO nkdao = new NhapKhoDAO();

    private void init() {
        addListeners();
        refresh();
    }

    private void refresh() {
        fillComboBoxNam();
        fillComboBoxNamNhapKho();
        fillTableDoanhThu();
        fillTableDoanhThuHang();
        fillTableSanPhamBanCham();
        fillTableSanPhamBanChay();
        fillTableSanPhamTonKho();
        fillTableSanPhamNhapKho();
        fillTableNhanVien();
        fillTableKhachHang();
        fillLabelTongDoanhThu();
    }
    


    public void fillComboBoxNam() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboNamDT.getModel();
        DefaultComboBoxModel model2 = (DefaultComboBoxModel) cboNamSP.getModel();
        DefaultComboBoxModel model3 = (DefaultComboBoxModel) cboNamNV.getModel();
        DefaultComboBoxModel model4 = (DefaultComboBoxModel) cboNamKH.getModel();
        model.removeAllElements();
        model2.removeAllElements();
        model3.removeAllElements();
        model4.removeAllElements();
        List<Integer> list = hddao.selectYears();
        for (Integer year : list) {
            model.addElement(year);
            model2.addElement(year);
            model3.addElement(year);
            model4.addElement(year);
        }
    }

    public void fillComboBoxNamNhapKho() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboNamNK.getModel();
        model.removeAllElements();
        List<Integer> list = nkdao.selectYears();
        for (Integer year : list) {
            model.addElement(year);
        }
    }

    public void fillTableDoanhThu() {
        DefaultTableModel model = (DefaultTableModel) tblDoanhThuChiTiet.getModel();
        model.setRowCount(0);
        DecimalFormat numberFormat = new DecimalFormat("#,##0.00");
        if (cboNamDT.getSelectedItem() != null) {
            int nam = Integer.valueOf(cboNamDT.getSelectedItem().toString());
            List<Object[]> list = tkdao.getDoanhThu(nam);
            for (Object[] row : list) {
                row[4] = numberFormat.format(row[4]);
                model.addRow(row);
            }
        }
    }
    
    public void fillTableDoanhThuHang() {
        DefaultTableModel model = (DefaultTableModel) tblDoanhThuHang.getModel();
        model.setRowCount(0);
        DecimalFormat numberFormat = new DecimalFormat("#,##0.00");
        if (cboNamDT.getSelectedItem() != null) {
            int nam = Integer.valueOf(cboNamDT.getSelectedItem().toString());
            List<Object[]> list = tkdao.getDoanhThuHang(nam);
            for (Object[] row : list) {
                row[2] = numberFormat.format(row[2]);
                model.addRow(row);
            }
        }
    }
    
    public void fillLabelTongDoanhThu(){
        double tongDT = 0;
        for(int i=0;i< tblDoanhThuHang.getRowCount();i++){
            
            tongDT += Double.parseDouble( String.valueOf(tblDoanhThuHang.getValueAt(i, 2)).replaceAll(",","") ) ;
        }
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String str1 = currencyVN.format(tongDT);
        lblTongDT.setText(str1);
    }

    public void fillTableSanPhamBanChay() {
        DefaultTableModel model = (DefaultTableModel) tblSanPhamBanChay.getModel();
        model.setRowCount(0);
        if (cboNamSP.getSelectedItem() != null) {
            int nam = Integer.valueOf(cboNamSP.getSelectedItem().toString());
            List<Object[]> list = tkdao.getSanPhamBanChay(nam);
            for (Object[] row : list) {
                model.addRow(row);
            }
        }
    }

    public void fillTableSanPhamBanCham() {
        DefaultTableModel model = (DefaultTableModel) tblSanPhamBanCham.getModel();
        model.setRowCount(0);
        if (cboNamSP.getSelectedItem() != null) {
            int nam = Integer.valueOf(cboNamSP.getSelectedItem().toString());
            List<Object[]> list = tkdao.getSanPhamBanCham(nam);
            for (Object[] row : list) {
                model.addRow(row);
            }
        }
    }

    public void fillTableSanPhamTonKho() {
        DefaultTableModel model = (DefaultTableModel) tblSanPhamTonKho.getModel();
        model.setRowCount(0);
        List<Object[]> list = tkdao.getSanPhamTonKho();
        for (Object[] row : list) {
            model.addRow(row);
        }
    }

    public void fillTableSanPhamNhapKho() {
        DefaultTableModel model = (DefaultTableModel) tblSanPhamNhapKho.getModel();
        model.setRowCount(0);
        if (cboNamNK.getSelectedItem() != null) {
            int nam = Integer.valueOf(cboNamNK.getSelectedItem().toString());
            List<Object[]> list = tkdao.getSanPhamNhapKho(nam);
            for (Object[] row : list) {
                model.addRow(row);
            }
        }
    }

    public void fillTableNhanVien() {
        DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
        model.setRowCount(0);
        if (cboNamNV.getSelectedItem() != null) {
            int nam = Integer.valueOf(cboNamNV.getSelectedItem().toString());
            List<Object[]> list = tkdao.getNhanVien(nam);
            list.stream().map(row -> {
                row[3] = (Boolean) row[3] ? "Nam" : "Nũ";
                return row;
            }).forEachOrdered(row -> {
                model.addRow(row);
            });
        }
    }

    public void fillTableKhachHang() {
        DefaultTableModel model = (DefaultTableModel) tblKhachHang.getModel();
        model.setRowCount(0);
        if (cboNamKH.getSelectedItem() != null) {
            int nam = Integer.valueOf(cboNamKH.getSelectedItem().toString());
            List<Object[]> list = tkdao.getKhachHang(nam);
            for (Object[] row : list) {
                row[3] = (Boolean) row[3] ? "Nam" : "Nũ";
                model.addRow(row);
            }
        }
    }

    private void addExportListener(Button... btns) {
        ActionListener ls = (e) -> {
            new Thread(() -> {
                switch (e.getActionCommand()) {
                    case "DoanhThu":
                        export(e.getActionCommand());
                        break;
                    case "SanPham":
                        getPopupSanPhamBan().show((Component) e.getSource(), -35, -85);
                        break;
                    case "Kho":
                        getPopupKho().show((Component) e.getSource(), -35, -85);
                        break;
                    case "NhanVien":
                        export(e.getActionCommand());
                        break;
                    case "KhachHang":
                        export(e.getActionCommand());
                        break;
                }
            }).start();
        };

        for (Button btn : btns) {
            btn.addActionListener(ls);
        }
    }

    private void export(String name) {
        File file = XExcel.showSaveDialog((JFrame) this.getTopLevelAncestor(), "ThongKe_Ultramotor.xlsx");
        if (file == null) {
            return;
        }

        switch (name) {
            case "DoanhThu":
                UltraExporter.exportDoanhThu(file, hddao.selectYears().stream().toArray(Integer[]::new));
                break;
            case "BanChay":
                UltraExporter.exportSanPhamBan(file, true, hddao.selectYears().stream().toArray(Integer[]::new));
                break;
            case "BanCham":
                UltraExporter.exportSanPhamBan(file, false, hddao.selectYears().stream().toArray(Integer[]::new));
                break;
            case "NhanVien":
                UltraExporter.exportNhanVienBan(file, hddao.selectYears().stream().toArray(Integer[]::new));
                break;
            case "KhachHang":
                UltraExporter.exportKhachHangMua(file, hddao.selectYears().stream().toArray(Integer[]::new));
                break;
            case "TonKho":
                UltraExporter.exportTonKho(file, false, nkdao.selectYears().stream().toArray(Integer[]::new));
                break;
            case "NhapKho":
                UltraExporter.exportTonKho(file, true, nkdao.selectYears().stream().toArray(Integer[]::new));
                break;
        }
        if (MsgBox.confirm("Xuất danh sách thành công! Bạn có muốn mở file?", false) == 0) {
            try {
                Desktop.getDesktop().open(file);
            } catch (IOException ex) {
            }
        }

    }

    private JPopupMenu getPopupSanPhamBan() {
        JPopupMenu popup = new JPopupMenu();
        popup.add(new PopupMenuItem("Sản Phẩm Bán Chạy", null, null, e -> export("BanChay")));
        popup.add(new PopupMenuItem("Sản Phẩm Bán Chậm", null, null, e -> export("BanCham")));
        return popup;
    }

    private JPopupMenu getPopupKho() {
        JPopupMenu popup = new JPopupMenu();
        popup.add(new PopupMenuItem("Sản Phẩm Tồn Kho", null, null, e -> export("TonKho")));
        popup.add(new PopupMenuItem("Sản Phẩm Nhập Kho", null, null, e -> export("NhapKho")));
        return popup;
    }

    private void addListeners() {
        cboNamDT.addActionListener((e) -> {
            fillTableDoanhThu();
            fillTableDoanhThuHang();
            fillLabelTongDoanhThu();
        });

        cboNamSP.addActionListener((e) -> {
            fillTableSanPhamBanCham();
            fillTableSanPhamBanChay();
        });

        cboNamNK.addActionListener((e) -> {
            fillTableSanPhamNhapKho();
        });

        cboNamNV.addActionListener((e) -> {
            fillTableNhanVien();
        });

        cboNamKH.addActionListener((e) -> {
            fillTableKhachHang();
        });

        addExportListener(btnSave, btnSave1, btnSave2, btnSave3, btnSave4);

        this.addAncestorListener(new AncestorListener() {
            @Override
            public void ancestorAdded(AncestorEvent event) {
                new Thread(() -> refresh()).start();
            }

            @Override
            public void ancestorRemoved(AncestorEvent event) {
            }

            @Override
            public void ancestorMoved(AncestorEvent event) {
            }
        });
//        this.getParent().addContainerListener(new ContainerListener() {
//            @Override
//            public void componentAdded(ContainerEvent e) {
//                System.out.println("Added");
//            }
//
//            @Override
//            public void componentRemoved(ContainerEvent e) {
//                System.out.println("Removed");
//            }
//
//        });

    }



}
