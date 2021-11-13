package com.edusys.ui;

import com.edusys.dao.EduSysDAO;
import com.edusys.dao.KhoaHocDAO;
import com.edusys.dao.ThongKeDAO;
import com.edusys.entity.KhoaHoc;
import com.edusys.io.EduSysExport;
import com.edusys.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterException;
import java.io.File;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.JTable.PrintMode;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nghipc
 */
public class ThongKeJDialog extends javax.swing.JDialog {

    /**
     * Creates new form QuanLyJDialog
     */
    public ThongKeJDialog() {
        super(EduSysFrame.getFrame(), true);
        initComponents();
        init();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTitleBar = new com.edusys.components.MyTitleBar();
        pnlBackground = new javax.swing.JPanel();
        btnExport = new javax.swing.JButton();
        btnEmail = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        lblLogo = new javax.swing.JLabel();
        tabs = new javax.swing.JTabbedPane();
        pnlBangDiem = new javax.swing.JPanel();
        lblKhoaHoc = new javax.swing.JLabel();
        cboKhoaHoc = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblBangDiem = new com.edusys.components.MyTable();
        pnlNguoiHoc = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblNguoiHoc = new com.edusys.components.MyTable();
        pnlDiemChuyenDe = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblDiemChuyenDe = new com.edusys.components.MyTable();
        pnlDoanhThu = new javax.swing.JPanel();
        lblYear = new javax.swing.JLabel();
        cboYear = new javax.swing.JComboBox<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblDoanhThu = new com.edusys.components.MyTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(null);
        setUndecorated(true);

        pnlTitleBar.setTitle("EDUSYS - TỔNG HỢP THỐNG KÊ");

        pnlBackground.setBackground(new java.awt.Color(250, 250, 250));
        pnlBackground.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnExport.setBackground(new java.awt.Color(255, 255, 255));
        btnExport.setForeground(new java.awt.Color(102, 102, 102));
        btnExport.setText("Export");
        btnExport.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        btnExport.setContentAreaFilled(false);
        btnExport.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnExport.setOpaque(true);
        pnlBackground.add(btnExport, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 20, 80, 40));

        btnEmail.setBackground(new java.awt.Color(255, 255, 255));
        btnEmail.setForeground(new java.awt.Color(102, 102, 102));
        btnEmail.setText("Email");
        btnEmail.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        btnEmail.setContentAreaFilled(false);
        btnEmail.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEmail.setOpaque(true);
        pnlBackground.add(btnEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 20, 80, 40));

        btnPrint.setBackground(new java.awt.Color(255, 255, 255));
        btnPrint.setForeground(new java.awt.Color(102, 102, 102));
        btnPrint.setText("Print");
        btnPrint.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        btnPrint.setContentAreaFilled(false);
        btnPrint.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPrint.setOpaque(true);
        pnlBackground.add(btnPrint, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 20, 80, 40));

        lblLogo.setBackground(new java.awt.Color(255, 255, 255));
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/img/log_poly_50px.png"))); // NOI18N
        lblLogo.setOpaque(true);
        pnlBackground.add(lblLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1052, 71));

        tabs.setBackground(new java.awt.Color(102, 102, 102));
        tabs.setForeground(new java.awt.Color(51, 51, 51));
        tabs.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblKhoaHoc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblKhoaHoc.setText("KHOÁ HỌC");

        cboKhoaHoc.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        cboKhoaHoc.setMaximumRowCount(5);
        cboKhoaHoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lập trình Java Cơ bản" }));
        cboKhoaHoc.setAutoscrolls(true);
        cboKhoaHoc.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        cboKhoaHoc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cboKhoaHoc.setFocusable(false);

        tblBangDiem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "MÃ NGƯỜI HỌC", "HỌ VÀ TÊN", "ĐIỂM", "XẾP LOẠI"
            }
        ));
        jScrollPane2.setViewportView(tblBangDiem);
        tblBangDiem.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        javax.swing.GroupLayout pnlBangDiemLayout = new javax.swing.GroupLayout(pnlBangDiem);
        pnlBangDiem.setLayout(pnlBangDiemLayout);
        pnlBangDiemLayout.setHorizontalGroup(
            pnlBangDiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBangDiemLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblKhoaHoc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cboKhoaHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 873, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
            .addComponent(jScrollPane2)
        );
        pnlBangDiemLayout.setVerticalGroup(
            pnlBangDiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBangDiemLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBangDiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboKhoaHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblKhoaHoc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE))
        );

        tabs.addTab("BẢNG ĐIỂM", new javax.swing.ImageIcon(getClass().getResource("/com/edusys/img/icon/student_male_BLACK_24px.png")), pnlBangDiem, ""); // NOI18N

        tblNguoiHoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "NĂM", "SỐ NGƯỜI HỌC", "NGÀY ĐĂNG KÍ SỚM NHẤT", "NGÀY ĐĂNG KÍ MUỘN NHẤT"
            }
        ));
        jScrollPane4.setViewportView(tblNguoiHoc);
        tblNguoiHoc.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        javax.swing.GroupLayout pnlNguoiHocLayout = new javax.swing.GroupLayout(pnlNguoiHoc);
        pnlNguoiHoc.setLayout(pnlNguoiHocLayout);
        pnlNguoiHocLayout.setHorizontalGroup(
            pnlNguoiHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNguoiHocLayout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1014, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlNguoiHocLayout.setVerticalGroup(
            pnlNguoiHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNguoiHocLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE))
        );

        tabs.addTab("LƯỢNG NGƯỜI HỌC", new javax.swing.ImageIcon(getClass().getResource("/com/edusys/img/icon/conference_24px.png")), pnlNguoiHoc, ""); // NOI18N

        tblDiemChuyenDe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "CHUYÊN ĐỀ", "SỐ LƯỢNG HỌC VIÊN", "ĐIỂM CAO NHẤT", "ĐIỂM THẤP NHẤT", "ĐIỂM TRUNG BÌNH"
            }
        ));
        jScrollPane5.setViewportView(tblDiemChuyenDe);
        tblDiemChuyenDe.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        javax.swing.GroupLayout pnlDiemChuyenDeLayout = new javax.swing.GroupLayout(pnlDiemChuyenDe);
        pnlDiemChuyenDe.setLayout(pnlDiemChuyenDeLayout);
        pnlDiemChuyenDeLayout.setHorizontalGroup(
            pnlDiemChuyenDeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1014, Short.MAX_VALUE)
        );
        pnlDiemChuyenDeLayout.setVerticalGroup(
            pnlDiemChuyenDeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDiemChuyenDeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE))
        );

        tabs.addTab("ĐIỂM CHUYÊN ĐỀ", new javax.swing.ImageIcon(getClass().getResource("/com/edusys/img/icon/check_24px.png")), pnlDiemChuyenDe, ""); // NOI18N

        lblYear.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblYear.setText("NĂM");

        cboYear.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        cboYear.setMaximumRowCount(5);
        cboYear.setAutoscrolls(true);
        cboYear.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        cboYear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cboYear.setFocusable(false);

        tblDoanhThu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "CHUYÊN ĐỀ", "SỐ KHOÁ HỌC", "SỐ HỌC VIÊN", "DOANH THU", "HỌC PHÍ CAO NHÂT", "HỌC PHÍ THẤP NHẤT", "HỌC PHÍ TRUNG BÌNH"
            }
        ));
        jScrollPane6.setViewportView(tblDoanhThu);
        tblDoanhThu.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        javax.swing.GroupLayout pnlDoanhThuLayout = new javax.swing.GroupLayout(pnlDoanhThu);
        pnlDoanhThu.setLayout(pnlDoanhThuLayout);
        pnlDoanhThuLayout.setHorizontalGroup(
            pnlDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDoanhThuLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(lblYear)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cboYear, javax.swing.GroupLayout.PREFERRED_SIZE, 854, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(80, Short.MAX_VALUE))
            .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        pnlDoanhThuLayout.setVerticalGroup(
            pnlDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDoanhThuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboYear, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblYear))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tabs.addTab("DOANH THU", new javax.swing.ImageIcon(getClass().getResource("/com/edusys/img/icon/cost_24px.png")), pnlDoanhThu, ""); // NOI18N

        pnlBackground.add(tabs, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 80, -1, 530));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTitleBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlBackground, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlTitleBar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBackground, javax.swing.GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE))
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
            java.util.logging.Logger.getLogger(ThongKeJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThongKeJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThongKeJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThongKeJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                ThongKeJDialog dialog = new ThongKeJDialog();
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
    private javax.swing.JButton btnEmail;
    private javax.swing.JButton btnExport;
    private javax.swing.JButton btnPrint;
    private javax.swing.JComboBox<String> cboKhoaHoc;
    private javax.swing.JComboBox<String> cboYear;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel lblKhoaHoc;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblYear;
    private javax.swing.JPanel pnlBackground;
    private javax.swing.JPanel pnlBangDiem;
    private javax.swing.JPanel pnlDiemChuyenDe;
    private javax.swing.JPanel pnlDoanhThu;
    private javax.swing.JPanel pnlNguoiHoc;
    private com.edusys.components.MyTitleBar pnlTitleBar;
    private javax.swing.JTabbedPane tabs;
    private com.edusys.components.MyTable tblBangDiem;
    private com.edusys.components.MyTable tblDiemChuyenDe;
    private com.edusys.components.MyTable tblDoanhThu;
    private com.edusys.components.MyTable tblNguoiHoc;
    // End of variables declaration//GEN-END:variables

    private ThongKeDAO thongKeDAO;
    private List<KhoaHoc> listKH;

    //khởi tạo thành phần khác
    private void init() {
        thongKeDAO = new ThongKeDAO();
        setLocationRelativeTo(null);
        addListeners(); //thêm listeners
    }

    //mở tab theo index
    public void showTab(int index) {
        tabs.setSelectedIndex(index);
        if (!isVisible()) {
            setVisible(true);
        }
    }

    //thêm listener cho các thành phần khác
    private void addListeners() {
        this.addWindowListener(XListener.dialogLs);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent we) {
                if (we.getOppositeWindow() instanceof JDialog) {
                    return;
                }
                listKH = EduSysDAO.KHOA_HOC_DAO.selectAll();
                if (!Auth.isManager()) {
                    tabs.remove(pnlDoanhThu);
                }

                fillComboKhoaHoc();
                fillComboYear();
                fillTable(tblBangDiem, getListBangDiem());
                fillTable(tblNguoiHoc, getListNguoiHoc());
                fillTable(tblDiemChuyenDe, getListDiemChuyenDe());
                fillTable(tblDoanhThu, getListDoanhThu());
            }

        });
        cboKhoaHoc.addActionListener((ActionEvent e) -> {
            fillTable(tblBangDiem, getListBangDiem());
        });

        cboYear.addActionListener((ActionEvent e) -> {
            fillTable(tblDoanhThu, getListDoanhThu());
        });
        btnExport.addActionListener((ActionEvent e) -> {
            exportList();
        });

        btnPrint.addActionListener((ActionEvent e) -> {
            printTable();
        });

        btnEmail.addActionListener((ActionEvent e) -> {
            sendMail();
        });

    }

    //đổ dữ liệu vào combo khoá học
    private void fillComboKhoaHoc() {
        Collections.sort(listKH, (Object t, Object t1) -> ((KhoaHoc) t).toString().compareTo(((KhoaHoc) t1).toString()));
        cboKhoaHoc.setModel(new DefaultComboBoxModel(listKH.toArray()));
    }

    //đổ dữ liệu vào combo year
    private void fillComboYear() {
        List<Integer> list = ((KhoaHocDAO) EduSysDAO.KHOA_HOC_DAO).selectAllYears();
        cboYear.setModel(new DefaultComboBoxModel(list.toArray()));
    }

    //đổ dữ liệu vào bảng table theo list
    private void fillTable(JTable table, List<Object[]> list) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        list.forEach((os) -> {
            model.addRow(os);
        });
    }

    //lấy danh sách bảng điểm
    private List<Object[]> getListBangDiem() {
        List<Object[]> list = new ArrayList<>();
        KhoaHoc kh = (KhoaHoc) cboKhoaHoc.getSelectedItem(); //lấy khoá học chọn
        if (kh != null) {
            list = thongKeDAO.getBangDiem(kh.getMaKH()); //lấy danh sách
        }
        return list;
    }

    //lấy dữ liệu người học theo năm
    private List<Object[]> getListNguoiHoc() {
        List<Object[]> list = thongKeDAO.getLuongNguoiHoc(); //lấy danh sách
        list.forEach((os) -> {
            os[2] = XDate.toString((Date) os[2]); //format lại ngày
            os[3] = XDate.toString((Date) os[3]); //format lại ngày
        });
        return list;
    }

    //lấy dữ liệu điểm chuyên đề
    private List<Object[]> getListDiemChuyenDe() {
        List<Object[]> list = thongKeDAO.getDiemChuyenDe(); //lấy danh sách
        list.forEach((os) -> {
            os[2] = String.format("%.1f", os[2]); //format lại điểm
            os[3] = String.format("%.1f", os[3]); //format lại điểm
            os[4] = String.format("%.1f", os[4]); //format lại điểm
        });
        return list;
    }

    //lấy dữ liệu doanh thu
    private List<Object[]> getListDoanhThu() {
        List<Object[]> list = new ArrayList<>();
        Integer year = (Integer) cboYear.getSelectedItem(); //lấy năm chọn
        if (year != null) {
            list = thongKeDAO.getDoanhThu(year); //lấy danh sách
            list.forEach((os) -> {
                os[3] = String.format("%.1f", os[3]); //format lại học phí
                os[4] = String.format("%.1f", os[4]); //format lại học phí
                os[5] = String.format("%.1f", os[5]); //format lại học phí
                os[6] = String.format("%.1f", os[6]); //format lại học phí

            });
        }
        return list;
    }

    //xuất dữ liệu ra file excel theo tab đang chọn
    private void exportList() {
        //tạo file excel chứa dữ liệu theo tên tab
        File file = EduSysExport.showSaveDialog(tabs.getTitleAt(tabs.getSelectedIndex()));
        if (file == null) {
            return;
        }

        //lấy và xuất dữ liệu theo tab đang chọn
        switch (tabs.getSelectedIndex()) {
            case 0:
                EduSysExport.exportList(getListBangDiem(), "Bảng Điểm", file, cboKhoaHoc.getSelectedItem().toString());
                break;
            case 1:
                EduSysExport.exportList(getListNguoiHoc(), "Lượng Người Học", file, "Số Lượng Người Học");
                break;
            case 2:
                EduSysExport.exportList(getListDiemChuyenDe(), "Điểm Chuyên Đề", file, "Điểm Chuyên Đề");
                break;
            case 3:
                EduSysExport.exportList(getListDoanhThu(), "Doanh Thu", file, "Năm " + cboYear.getSelectedItem());
                break;
        }
    }

    //mở hộp thoại in và in bảng dữ liệu theo tab đang chọn
    private void printTable() {
        try {
            switch (tabs.getSelectedIndex()) {
                case 0:
                    tblBangDiem.print(PrintMode.FIT_WIDTH, new MessageFormat("KHOÁ HỌC: " + cboKhoaHoc.getSelectedItem().toString()), new MessageFormat("Page - {0}"));
                    break;
                case 1:
                    tblNguoiHoc.print(PrintMode.FIT_WIDTH, new MessageFormat("LƯỢNG NGƯỜI HỌC THEO NĂM"), new MessageFormat("Page - {0}"));
                    break;
                case 2:
                    tblDiemChuyenDe.print(PrintMode.FIT_WIDTH, new MessageFormat("ĐIỂM CHUYÊN ĐỀ"), new MessageFormat("Page - {0}"));
                    break;
                case 3:
                    tblDoanhThu.print(PrintMode.FIT_WIDTH, new MessageFormat("DOANH THU NĂM " + cboYear.getSelectedItem()), new MessageFormat("Page - {0}"));
                    break;
            }
        } catch (PrinterException ex) {
            XLog.saveLog(ex.getMessage());
        }
    }

    //export dữ liệu và share qua mail
    private void sendMail() {
        //kiểm tra email của user
        if (Auth.user != null) {
            String email = Auth.user.getEmail();
            while (email == null || email.isEmpty() || !email.matches(MyConstants.EMAIL_PATTERN)) {
                email = MsgBox.input("Vui lòng nhập địa chỉ email: ");
            }

            //lấy dữ liệu theo tab đang chọn và gửi mail
            switch (tabs.getSelectedIndex()) {
                case 0:
                    EduSysExport.sendMail(getListBangDiem(), "Bảng Điểm", cboKhoaHoc.getSelectedItem().toString(), email);
                    break;
                case 1:
                    EduSysExport.sendMail(getListNguoiHoc(), "Lượng Người Học", "Lượng Người Học", email);
                    break;
                case 2:
                    EduSysExport.sendMail(getListDiemChuyenDe(), "Điểm Chuyên Đề", "Điểm Chuyên Đề", email);
                    break;
                case 3:
                    EduSysExport.sendMail(getListDoanhThu(), "Doanh Thu", cboYear.getSelectedItem().toString(), email);
                    break;
            }
        }
    }

}
