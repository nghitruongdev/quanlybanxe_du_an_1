/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ultramotor.ui.thongke;

import com.ultramotor.dao.HoaDonDAO;
import com.ultramotor.dao.NhapKhoDAO;
import com.ultramotor.dao.ThongKeDAO;
import com.ultramotor.entity.ChiTietHoaDon;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author BRAVO
 */
public class ThongKePanel extends javax.swing.JPanel {

    /**
     * Creates new form ThongKePanel
     */
    public ThongKePanel() {
        initComponents();
        init();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblQLNV = new javax.swing.JLabel();
        pnlMain = new javax.swing.JPanel();
        tpnlThongKe = new javax.swing.JTabbedPane();
        pnlDoanhThu = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblDoanhThu = new com.ultramotor.component.table.Table();
        cboNamDT = new com.swingx.ComboBoxSuggestion();
        lblNam = new javax.swing.JLabel();
        btnSave = new com.swingx.Button();
        pnlSanPham = new javax.swing.JPanel();
        btnSave1 = new com.swingx.Button();
        lblNam1 = new javax.swing.JLabel();
        cboNamSP = new com.swingx.ComboBoxSuggestion();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblSanPhamBanChay = new com.ultramotor.component.table.Table();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblSanPhamBanCham = new com.ultramotor.component.table.Table();
        pnlKho = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblSanPhamNhapKho = new com.ultramotor.component.table.Table();
        btnSave2 = new com.swingx.Button();
        lblNam2 = new javax.swing.JLabel();
        cboNamNK = new com.swingx.ComboBoxSuggestion();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblSanPhamTonKho = new com.ultramotor.component.table.Table();
        jLabel4 = new javax.swing.JLabel();
        pnlNhanVien = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblNhanVien = new com.ultramotor.component.table.Table();
        cboNamNV = new com.swingx.ComboBoxSuggestion();
        lblNam3 = new javax.swing.JLabel();
        btnSave3 = new com.swingx.Button();
        jLabel6 = new javax.swing.JLabel();
        pnlNhanVien1 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblKhachHang = new com.ultramotor.component.table.Table();
        cboNamKH = new com.swingx.ComboBoxSuggestion();
        lblNam4 = new javax.swing.JLabel();
        btnSave4 = new com.swingx.Button();
        jLabel5 = new javax.swing.JLabel();

        lblQLNV.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblQLNV.setForeground(new java.awt.Color(0, 153, 255));
        lblQLNV.setText("UltraMotor - Thống kê");

        tblDoanhThu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sản phẩm", "Màu sắc", "Phân khối", "Số lượng", "Doanh thu"
            }
        ));
        jScrollPane4.setViewportView(tblDoanhThu);

        lblNam.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblNam.setText("Năm");

        btnSave.setBackground(new java.awt.Color(92, 167, 51));
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setText("Xuất danh sách");
        btnSave.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSave.setRadius(10);

        javax.swing.GroupLayout pnlDoanhThuLayout = new javax.swing.GroupLayout(pnlDoanhThu);
        pnlDoanhThu.setLayout(pnlDoanhThuLayout);
        pnlDoanhThuLayout.setHorizontalGroup(
            pnlDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDoanhThuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1152, Short.MAX_VALUE)
                    .addGroup(pnlDoanhThuLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlDoanhThuLayout.createSequentialGroup()
                                .addComponent(lblNam)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cboNamDT, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        pnlDoanhThuLayout.setVerticalGroup(
            pnlDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDoanhThuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboNamDT, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        tpnlThongKe.addTab("Doanh thu", pnlDoanhThu);

        btnSave1.setBackground(new java.awt.Color(92, 167, 51));
        btnSave1.setForeground(new java.awt.Color(255, 255, 255));
        btnSave1.setText("Xuất danh sách");
        btnSave1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSave1.setRadius(10);

        lblNam1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblNam1.setText("Năm");

        tblSanPhamBanChay.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sản phẩm", "Màu sắc", "Phân khối", "Số lượng"
            }
        ));
        jScrollPane6.setViewportView(tblSanPhamBanChay);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Top 5 sản phẩm bán chậm nhất năm");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Top 5 sản phẩm bán chạy nhất năm");

        tblSanPhamBanCham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sản phẩm", "Màu sắc", "Phân khối", "Số lượng"
            }
        ));
        jScrollPane9.setViewportView(tblSanPhamBanCham);

        javax.swing.GroupLayout pnlSanPhamLayout = new javax.swing.GroupLayout(pnlSanPham);
        pnlSanPham.setLayout(pnlSanPhamLayout);
        pnlSanPhamLayout.setHorizontalGroup(
            pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSanPhamLayout.createSequentialGroup()
                        .addGroup(pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlSanPhamLayout.createSequentialGroup()
                                .addComponent(lblNam1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlSanPhamLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(355, 355, 355)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)))
                        .addComponent(cboNamSP, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSanPhamLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSave1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSanPhamLayout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addGroup(pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addGap(16, 16, 16)
                .addGroup(pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnSave1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tpnlThongKe.addTab("Sản phẩm", pnlSanPham);

        tblSanPhamNhapKho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sản phẩm", "Màu sắc", "Phân khối", "Số lượng nhập kho"
            }
        ));
        jScrollPane7.setViewportView(tblSanPhamNhapKho);

        btnSave2.setBackground(new java.awt.Color(92, 167, 51));
        btnSave2.setForeground(new java.awt.Color(255, 255, 255));
        btnSave2.setText("Xuất danh sách");
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
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGroup(pnlKhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlKhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboNamNK, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNam2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlKhoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel4)))
                .addGap(18, 18, 18)
                .addGroup(pnlKhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSave2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(134, 134, 134))
        );

        tpnlThongKe.addTab("Kho", pnlKho);

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

        btnSave3.setBackground(new java.awt.Color(92, 167, 51));
        btnSave3.setForeground(new java.awt.Color(255, 255, 255));
        btnSave3.setText("Xuất danh sách");
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
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1152, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNhanVienLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnSave3, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlNhanVienLayout.createSequentialGroup()
                                .addComponent(lblNam3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cboNamNV, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pnlNhanVienLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlNhanVienLayout.setVerticalGroup(
            pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNhanVienLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboNamNV, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNam3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSave3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        tpnlThongKe.addTab("Nhân viên", pnlNhanVien);

        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nhân viên", "Tên nhân viên", "Ngày sinh", "Giới tính", "SDT", "Email", "Số lượng mua"
            }
        ));
        jScrollPane10.setViewportView(tblKhachHang);

        lblNam4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblNam4.setText("Năm");

        btnSave4.setBackground(new java.awt.Color(92, 167, 51));
        btnSave4.setForeground(new java.awt.Color(255, 255, 255));
        btnSave4.setText("Xuất danh sách");
        btnSave4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSave4.setRadius(10);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Danh sách khách hàng mua hàng tiêu biểu");

        javax.swing.GroupLayout pnlNhanVien1Layout = new javax.swing.GroupLayout(pnlNhanVien1);
        pnlNhanVien1.setLayout(pnlNhanVien1Layout);
        pnlNhanVien1Layout.setHorizontalGroup(
            pnlNhanVien1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNhanVien1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlNhanVien1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1152, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNhanVien1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSave4, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNhanVien1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblNam4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboNamKH, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlNhanVien1Layout.setVerticalGroup(
            pnlNhanVien1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNhanVien1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlNhanVien1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlNhanVien1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboNamKH, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNam4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNhanVien1Layout.createSequentialGroup()
                        .addGap(0, 48, Short.MAX_VALUE)
                        .addComponent(jLabel5)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(btnSave4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        tpnlThongKe.addTab("Khách hàng", pnlNhanVien1);

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tpnlThongKe)
                .addContainerGap())
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addComponent(tpnlThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 726, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblQLNV)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblQLNV)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
    private javax.swing.JScrollPane jScrollPane10;
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
    private javax.swing.JPanel pnlDoanhThu;
    private javax.swing.JPanel pnlKho;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlNhanVien;
    private javax.swing.JPanel pnlNhanVien1;
    private javax.swing.JPanel pnlSanPham;
    private com.ultramotor.component.table.Table tblDoanhThu;
    private com.ultramotor.component.table.Table tblKhachHang;
    private com.ultramotor.component.table.Table tblNhanVien;
    private com.ultramotor.component.table.Table tblSanPhamBanCham;
    private com.ultramotor.component.table.Table tblSanPhamBanChay;
    private com.ultramotor.component.table.Table tblSanPhamNhapKho;
    private com.ultramotor.component.table.Table tblSanPhamTonKho;
    private javax.swing.JTabbedPane tpnlThongKe;
    // End of variables declaration//GEN-END:variables
    ThongKeDAO tkdao = new ThongKeDAO();
    HoaDonDAO hddao = new HoaDonDAO();
    NhapKhoDAO nkdao = new NhapKhoDAO();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame fr = new JFrame();
            fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            fr.getContentPane().add(new ThongKePanel());
            fr.pack();
            fr.setVisible(true);
        });
    }

    private void init() {
        fillComboBoxNam();
        fillComboBoxNamNhapKho();
        fillTableDoanhThu();
        fillTableSanPhamBanCham();
        fillTableSanPhamBanChay();
        fillTableSanPhamTonKho();
        fillTableSanPhamNhapKho();
        fillTableNhanVien();
        fillTableKhachHang();
               
        addListeners();
    }

    public void fillComboBoxNam() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboNamDT.getModel();
        DefaultComboBoxModel model2 = (DefaultComboBoxModel) cboNamSP.getModel();
        DefaultComboBoxModel model3 = (DefaultComboBoxModel) cboNamNV.getModel();
        DefaultComboBoxModel model4 = (DefaultComboBoxModel) cboNamKH.getModel();
        model.removeAllElements();
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
        DefaultTableModel model = (DefaultTableModel) tblDoanhThu.getModel();
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
            for (Object[] row : list) {
                row[3]= (Boolean)row[3]?"Nam" : "Nũ";
                model.addRow(row);
            }
        }
    }
    
    public void fillTableKhachHang() {
        DefaultTableModel model = (DefaultTableModel) tblKhachHang.getModel();
        model.setRowCount(0);
        if (cboNamKH.getSelectedItem() != null) {
            int nam = Integer.valueOf(cboNamKH.getSelectedItem().toString());
            List<Object[]> list = tkdao.getKhachHang(nam);
            for (Object[] row : list) {
                row[3]= (Boolean)row[3]?"Nam" : "Nũ";
                model.addRow(row);
            }
        }
    }
    
    

    private void addListeners() {
        cboNamDT.addActionListener((e) -> {
            fillTableDoanhThu();
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
    }

}
