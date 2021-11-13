package com.edusys.ui;

import com.edusys.dao.EduSysDAO;
import com.edusys.entity.ChuyenDe;
import com.edusys.util.MsgBox;
import com.edusys.util.MyVerifier;
import com.edusys.util.XFile;
import com.edusys.util.XImage;
import com.edusys.util.XLog;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
public class ChuyenDeJDialog extends MyDialogQuanLy<ChuyenDe> {

    /**
     * Creates new form QuanLyJDialog
     */
    public ChuyenDeJDialog() {
//        super(parent, modal);
        initComponents();
        init();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTitileBar = new com.edusys.components.MyTitleBar();
        pnlBackground = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        tabs = new javax.swing.JTabbedPane();
        pnlEdit = new javax.swing.JPanel();
        lblHinh = new javax.swing.JLabel();
        lblMaCD = new javax.swing.JLabel();
        lblThoiLuong = new javax.swing.JLabel();
        lblTenCD = new javax.swing.JLabel();
        lblHocPhi = new javax.swing.JLabel();
        lblMoTa = new javax.swing.JLabel();
        pnlBtnMain = new javax.swing.JPanel();
        btnNew = new javax.swing.JButton();
        btnInsert = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        pnlNav = new javax.swing.JPanel();
        btnFirst = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        txtMaCD = new com.edusys.components.MyTextField();
        txtTenCD = new com.edusys.components.MyTextField();
        txtThoiLuong = new com.edusys.components.MyTextField();
        txtHocPhi = new com.edusys.components.MyTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        lblErrorMaCD = new javax.swing.JLabel();
        lblErrorTenCD = new javax.swing.JLabel();
        lblErrorThoiLuong = new javax.swing.JLabel();
        lblErrorHocPhi = new javax.swing.JLabel();
        pnlLlist = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblList = new com.edusys.components.MyTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        pnlTitileBar.setTitle("EDUSYS - QUẢN LÝ CHUYÊN ĐỀ");

        pnlBackground.setBackground(new java.awt.Color(250, 250, 250));

        lblLogo.setBackground(new java.awt.Color(255, 255, 255));
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/img/log_poly_50px.png"))); // NOI18N
        lblLogo.setOpaque(true);

        tabs.setBackground(new java.awt.Color(102, 102, 102));
        tabs.setForeground(new java.awt.Color(51, 51, 51));
        tabs.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        pnlEdit.setBackground(new java.awt.Color(255, 255, 255));

        lblHinh.setBackground(new java.awt.Color(149, 59, 195));
        lblHinh.setFont(new java.awt.Font("Segoe UI Light", 0, 10)); // NOI18N
        lblHinh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHinh.setIcon(new javax.swing.ImageIcon("D:\\Projects\\NetBeansProjects\\Edusys\\logos\\default.png")); // NOI18N
        lblHinh.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("CheckBox.shadow")));
        lblHinh.setMaximumSize(new java.awt.Dimension(155, 200));
        lblHinh.setMinimumSize(new java.awt.Dimension(155, 200));
        lblHinh.setPreferredSize(new java.awt.Dimension(150, 200));

        lblMaCD.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblMaCD.setText("Mã Chuyên Đề");

        lblThoiLuong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblThoiLuong.setText("Thời Lượng (Giờ)");

        lblTenCD.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTenCD.setText("Tên Chuyên Đề");

        lblHocPhi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblHocPhi.setText("Học Phí");

        lblMoTa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblMoTa.setText("Mô Tả Chuyên Đề");

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

        txtMaCD.setError(lblErrorMaCD);
        txtMaCD.setLabel(lblMaCD);

        txtTenCD.setError(lblErrorTenCD);
        txtTenCD.setLabel(lblTenCD);

        txtThoiLuong.setError(lblErrorThoiLuong);
        txtThoiLuong.setLabel(lblThoiLuong);

        txtHocPhi.setError(lblErrorHocPhi);
        txtHocPhi.setLabel(lblHocPhi);

        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBar(null);

        txtMoTa.setColumns(20);
        txtMoTa.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtMoTa.setLineWrap(true);
        txtMoTa.setRows(5);
        txtMoTa.setWrapStyleWord(true);
        txtMoTa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jScrollPane2.setViewportView(txtMoTa);

        lblErrorMaCD.setForeground(new java.awt.Color(255, 255, 255));
        lblErrorMaCD.setText("jLabel1");

        lblErrorTenCD.setForeground(new java.awt.Color(255, 255, 255));
        lblErrorTenCD.setText("jLabel1");

        lblErrorThoiLuong.setForeground(new java.awt.Color(255, 255, 255));
        lblErrorThoiLuong.setText("jLabel1");

        lblErrorHocPhi.setForeground(new java.awt.Color(255, 255, 255));
        lblErrorHocPhi.setText("jLabel1");

        javax.swing.GroupLayout pnlEditLayout = new javax.swing.GroupLayout(pnlEdit);
        pnlEdit.setLayout(pnlEditLayout);
        pnlEditLayout.setHorizontalGroup(
            pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEditLayout.createSequentialGroup()
                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlEditLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(lblHinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(89, 89, 89)
                        .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlEditLayout.createSequentialGroup()
                                .addComponent(lblMoTa)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(pnlEditLayout.createSequentialGroup()
                                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblThoiLuong)
                                    .addComponent(txtThoiLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEditLayout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(lblErrorThoiLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlEditLayout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(lblErrorHocPhi, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lblHocPhi)
                                    .addComponent(txtHocPhi, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEditLayout.createSequentialGroup()
                                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblMaCD)
                                    .addComponent(txtMaCD, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                                    .addGroup(pnlEditLayout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(lblErrorMaCD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtTenCD, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblTenCD)
                                    .addGroup(pnlEditLayout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(lblErrorTenCD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                    .addGroup(pnlEditLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(pnlNav, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnlBtnMain, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(59, 59, 59))
        );
        pnlEditLayout.setVerticalGroup(
            pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEditLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEditLayout.createSequentialGroup()
                        .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMaCD)
                            .addComponent(lblTenCD))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblErrorMaCD)
                            .addComponent(lblErrorTenCD))
                        .addGap(24, 24, 24)
                        .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlEditLayout.createSequentialGroup()
                                .addComponent(lblThoiLuong)
                                .addGap(39, 39, 39))
                            .addGroup(pnlEditLayout.createSequentialGroup()
                                .addComponent(lblHocPhi)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtThoiLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtHocPhi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblErrorThoiLuong)
                            .addComponent(lblErrorHocPhi)))
                    .addComponent(lblHinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMoTa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81)
                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlBtnMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlNav, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38))
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
                "MÃ CHUYÊN ĐỀ", "TÊN CHUYÊN ĐỀ", "HỌC PHÍ", "THỜI LƯỢNG", "HÌNH"
            }
        ));
        jScrollPane3.setViewportView(tblList);
        tblList.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        javax.swing.GroupLayout pnlLlistLayout = new javax.swing.GroupLayout(pnlLlist);
        pnlLlist.setLayout(pnlLlistLayout);
        pnlLlistLayout.setHorizontalGroup(
            pnlLlistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLlistLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 983, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlLlistLayout.setVerticalGroup(
            pnlLlistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLlistLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabs.addTab("DANH SÁCH", new javax.swing.ImageIcon(getClass().getResource("/com/edusys/img/icon/playlist_24px.png")), pnlLlist, ""); // NOI18N

        javax.swing.GroupLayout pnlBackgroundLayout = new javax.swing.GroupLayout(pnlBackground);
        pnlBackground.setLayout(pnlBackgroundLayout);
        pnlBackgroundLayout.setHorizontalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabs)
                .addGap(16, 16, 16))
            .addComponent(lblLogo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlBackgroundLayout.setVerticalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tabs)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlTitileBar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlTitileBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
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
            java.util.logging.Logger.getLogger(ChuyenDeJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChuyenDeJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChuyenDeJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChuyenDeJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ChuyenDeJDialog dialog = new ChuyenDeJDialog();
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblErrorHocPhi;
    private javax.swing.JLabel lblErrorMaCD;
    private javax.swing.JLabel lblErrorTenCD;
    private javax.swing.JLabel lblErrorThoiLuong;
    private javax.swing.JLabel lblHinh;
    private javax.swing.JLabel lblHocPhi;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblMaCD;
    private javax.swing.JLabel lblMoTa;
    private javax.swing.JLabel lblTenCD;
    private javax.swing.JLabel lblThoiLuong;
    private javax.swing.JPanel pnlBackground;
    private javax.swing.JPanel pnlBtnMain;
    private javax.swing.JPanel pnlEdit;
    private javax.swing.JPanel pnlLlist;
    private javax.swing.JPanel pnlNav;
    private com.edusys.components.MyTitleBar pnlTitileBar;
    private javax.swing.JTabbedPane tabs;
    private com.edusys.components.MyTable tblList;
    private com.edusys.components.MyTextField txtHocPhi;
    private com.edusys.components.MyTextField txtMaCD;
    private javax.swing.JTextArea txtMoTa;
    private com.edusys.components.MyTextField txtTenCD;
    private com.edusys.components.MyTextField txtThoiLuong;
    // End of variables declaration//GEN-END:variables

    @Override
    protected void init() {
        setLocationRelativeTo(null);
        //khởi tạo các thành phần khác
        super.tblList = tblList;
        super.tabs = tabs;
        super.fields = new JTextComponent[]{txtMaCD, txtTenCD, txtHocPhi, txtThoiLuong, txtMoTa};
        super.dao = EduSysDAO.CHUYEN_DE_DAO;
        super.verifier = MyVerifier.CHUYEN_DE_VERIFIER;

        setFieldName(); //setName cho field
        addListeners(); //thêm listener cho các thành phần
        updateStatus(); //cập nhật trạng thái form

        //set InputVerifier cho các field
        for (JTextComponent field : fields) {
            field.setInputVerifier(verifier);
        }
    }

    @Override
    protected void addListeners() {
        addBtnListeners("main", btnNew, btnInsert, btnUpdate, btnDelete);
        addBtnListeners("nav", btnFirst, btnPrev, btnNext, btnLast);
        addTableListener();

        lblHinh.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                XImage.uploadIcon(lblHinh);
            }
        });
        txtMaCD.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                if (txtMaCD.getText().length() == 5) {
                    ke.consume();
                }
            }

        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent we) {
                if (we.getOppositeWindow() instanceof JDialog) {
                    return;
                }
                fillTable(0);
                txtMaCD.requestFocus();
                updateStatus();
            }

        });
    }

    @Override
    protected void updateStatus() {
        updateStatus(btnNew, btnInsert, btnUpdate, btnDelete, btnFirst, btnPrev, btnNext, btnLast);
    }

    @Override
    protected void clearForm() {
        super.clearForm();
        clearHinh();
    }

    @Override
    protected ChuyenDe readForm() {
        String maCD = txtMaCD.getText();
        String tenCD = txtTenCD.getText();
        Double hocPhi = Double.parseDouble(txtHocPhi.getText());
        Integer thoiLuong = Integer.parseInt(txtThoiLuong.getText());
        String hinh = renameToMaCD();
        String moTa = txtMoTa.getText();
        XFile.save(lblHinh.getToolTipText(), txtMaCD.getText());
        return new ChuyenDe(maCD, tenCD, hocPhi, thoiLuong, hinh, moTa);
    }

    @Override
    protected void setForm(ChuyenDe cd) {
        txtMaCD.setText(cd.getMaCD());
        txtTenCD.setText(cd.getTenCD());
        txtHocPhi.setText(cd.getHocPhi().toString());
        txtThoiLuong.setText(cd.getThoiLuong().toString());
        XImage.setIcon(XImage.read(cd.getHinh()), lblHinh);
        txtMoTa.setText(cd.getMoTa());
        updateStatus();
    }

    @Override
    protected Object[] getInfo(ChuyenDe cd) {
        return new Object[]{
            cd.getMaCD(),
            cd.getTenCD(),
            cd.getHocPhi(),
            cd.getThoiLuong(),
            cd.getHinh()
        };
    }

    @Override
    protected List<Object[]> getInfo() {
        List<Object[]> list = new ArrayList<>();
        dao.selectAll().forEach((cd) -> {
            list.add(getInfo((ChuyenDe) cd));
        });
        return list;
    }

    @Override
    protected boolean isValidated(String action) {
        String maCD = txtMaCD.getText();
        ChuyenDe cd;
        switch (action) {
            case "insert":
                if (!verifyField(fields)) {
                    return false;
                }
                cd = (ChuyenDe) dao.selectById(maCD);
                if (cd != null) {
                    MsgBox.error("Đã tồn tại mã chuyên đề trong hệ thống");
                    return false;
                }
                break;
            case "update":
                if (!verifyField(fields)) {
                    return false;
                }
                cd = (ChuyenDe) dao.selectById(maCD);
                if (cd == null) {
                    MsgBox.error("Không tìm thấy mã chuyên đề trong hệ thống");
                    return false;
                }
                break;
            case "delete":
                if (!verifyField(txtMaCD)) {
                    return false;
                }
                cd = (ChuyenDe) dao.selectById(maCD);
                if (cd == null) {
                    MsgBox.error("Không tìm thấy mã chuyên đề trong hệ thống");
                    return false;
                }
                break;
        }
        return true;
    }

    private void clearHinh() {
        XImage.setIcon(XImage.read("default.png"), lblHinh); //setIcon cho label
        lblHinh.setToolTipText("default.png"); //đặt fileName vào toolTipText
    }

    //đổi tên file hình thành maCD khi lưu file
    private String renameToMaCD() {
        String text = lblHinh.getToolTipText();
        try {
            if (text.contains(".")) {
                String ext = text.substring(text.lastIndexOf("."));
                return txtMaCD.getText() + ext;
            }
        } catch (Exception e) {
            XLog.saveLog(e.getMessage());
        }
        return "";
    }

    //đặt tên cho field
    private void setFieldName() {
        txtMaCD.setName("Mã chuyên đề");
        txtTenCD.setName("Tên chuyên đề");
        txtThoiLuong.setName("Thời lượng");
        txtHocPhi.setName("Học phí");
        txtMoTa.setName("Mô tả");
    }
}
