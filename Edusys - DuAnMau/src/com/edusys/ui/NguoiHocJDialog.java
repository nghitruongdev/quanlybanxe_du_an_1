package com.edusys.ui;

import com.edusys.dao.EduSysDAO;
import com.edusys.entity.NguoiHoc;
import com.edusys.util.Auth;
import com.edusys.util.MsgBox;
import com.edusys.util.MyVerifier;
import com.edusys.util.XDate;
import com.edusys.util.XListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.event.ChangeEvent;
import javax.swing.text.JTextComponent;

/**
 *
 * @author nghipc
 */
public class NguoiHocJDialog extends MyDialogQuanLy<NguoiHoc> {

    /**
     * Creates new form QuanLyJDialog
     */
    public NguoiHocJDialog() {
//        super(parent, modal);
        initComponents();
        init();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bngGioiTinh = new javax.swing.ButtonGroup();
        pnlBackground = new javax.swing.JPanel();
        pnlTitleBar = new com.edusys.components.MyTitleBar();
        lblLogo = new javax.swing.JLabel();
        tabs = new javax.swing.JTabbedPane();
        pnlEdit = new javax.swing.JPanel();
        lblMaNH = new javax.swing.JLabel();
        lblHoTen = new javax.swing.JLabel();
        lblNgaySinh = new javax.swing.JLabel();
        lblDienThoai = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblGhiChu = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        pnlGioiTinh = new javax.swing.JPanel();
        rdoMale = new javax.swing.JRadioButton();
        rdoFemale = new javax.swing.JRadioButton();
        txtMaNH = new com.edusys.components.MyTextField();
        txtHoTen = new com.edusys.components.MyTextField();
        txtNgaySinh = new com.edusys.components.MyTextField();
        txtDienThoai = new com.edusys.components.MyTextField();
        txtEmail = new com.edusys.components.MyTextField();
        lblErrorMaNH = new javax.swing.JLabel();
        lblErrorHoTen = new javax.swing.JLabel();
        lblErrorNgaySinh = new javax.swing.JLabel();
        lblErrorDienThoai = new javax.swing.JLabel();
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
        lblSearch = new javax.swing.JLabel();
        pnlSearch = new javax.swing.JPanel();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblList = new com.edusys.components.MyTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1024, 648));
        setUndecorated(true);

        pnlBackground.setBackground(new java.awt.Color(250, 250, 250));
        pnlBackground.setBorder(new javax.swing.border.LineBorder(pnlTitleBar.getBackground(), 1, true));

        pnlTitleBar.setTitle("EDUSYS - QUẢN LÝ NGƯỜI HỌC");

        lblLogo.setBackground(new java.awt.Color(255, 255, 255));
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/img/log_poly_50px.png"))); // NOI18N
        lblLogo.setOpaque(true);

        tabs.setBackground(new java.awt.Color(102, 102, 102));
        tabs.setForeground(new java.awt.Color(51, 51, 51));
        tabs.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        pnlEdit.setBackground(new java.awt.Color(255, 255, 255));

        lblMaNH.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblMaNH.setText("Mã Người Học");

        lblHoTen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblHoTen.setText("Họ Và Tên");

        lblNgaySinh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNgaySinh.setText("Ngày Sinh");

        lblDienThoai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDienThoai.setText("Điện Thoại");

        lblEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblEmail.setText("Email");

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

        pnlGioiTinh.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Giới Tính", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N
        pnlGioiTinh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pnlGioiTinh.setOpaque(false);
        pnlGioiTinh.setPreferredSize(new java.awt.Dimension(293, 100));

        bngGioiTinh.add(rdoMale);
        rdoMale.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdoMale.setSelected(true);
        rdoMale.setText("Nam");
        rdoMale.setOpaque(false);

        bngGioiTinh.add(rdoFemale);
        rdoFemale.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdoFemale.setText("Nữ");
        rdoFemale.setOpaque(false);

        javax.swing.GroupLayout pnlGioiTinhLayout = new javax.swing.GroupLayout(pnlGioiTinh);
        pnlGioiTinh.setLayout(pnlGioiTinhLayout);
        pnlGioiTinhLayout.setHorizontalGroup(
            pnlGioiTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGioiTinhLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(rdoMale, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(rdoFemale, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(108, Short.MAX_VALUE))
        );
        pnlGioiTinhLayout.setVerticalGroup(
            pnlGioiTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGioiTinhLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlGioiTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoMale, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rdoFemale))
                .addContainerGap())
        );

        txtMaNH.setError(lblErrorMaNH);
        txtMaNH.setLabel(lblMaNH);

        txtHoTen.setError(lblErrorHoTen);
        txtHoTen.setLabel(lblHoTen);

        txtNgaySinh.removeKeyListener(txtNgaySinh.getKeyListeners()[0]);
        txtNgaySinh.setError(lblErrorNgaySinh);
        txtNgaySinh.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);
        txtNgaySinh.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
        txtNgaySinh.setLabel(lblNgaySinh);
        txtNgaySinh.setPlaceholder("DD/MM/YYYY");

        txtDienThoai.removeKeyListener(txtDienThoai.getKeyListeners()[0]);
        txtDienThoai.setError(lblErrorDienThoai);
        txtDienThoai.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        try {
            javax.swing.text.MaskFormatter mf = new javax.swing.text.MaskFormatter("####-###-###");
            mf.setPlaceholderCharacter('#');
            txtDienThoai.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(mf));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtDienThoai.setLabel(lblDienThoai);
        txtDienThoai.setName("Số điện thoại"); // NOI18N

        txtEmail.removeKeyListener(txtEmail.getKeyListeners()[0]);
        txtEmail.setError(lblErrorEmail);
        txtEmail.setLabel(lblEmail);
        txtEmail.setPlaceholder("@");

        lblErrorMaNH.setForeground(new java.awt.Color(255, 255, 255));
        lblErrorMaNH.setText("jLabel1");

        lblErrorHoTen.setForeground(new java.awt.Color(255, 255, 255));
        lblErrorHoTen.setText("jLabel1");

        lblErrorNgaySinh.setForeground(new java.awt.Color(255, 255, 255));
        lblErrorNgaySinh.setText("jLabel1");

        lblErrorDienThoai.setForeground(new java.awt.Color(255, 255, 255));
        lblErrorDienThoai.setText("jLabel1");

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
                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEditLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(pnlGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDienThoai)
                            .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblErrorDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(25, 25, 25)
                        .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEmail)
                            .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblErrorEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pnlEditLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(lblGhiChu))
                    .addGroup(pnlEditLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 925, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEditLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMaNH)
                            .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblErrorMaNH, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtMaNH, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(33, 33, 33)
                        .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblHoTen)
                            .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblErrorHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(25, 25, 25)
                        .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblErrorNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblNgaySinh))))
                .addContainerGap(22, Short.MAX_VALUE))
            .addGroup(pnlEditLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(pnlNav, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlBtnMain, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        pnlEditLayout.setVerticalGroup(
            pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEditLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMaNH)
                    .addComponent(lblHoTen)
                    .addComponent(lblNgaySinh))
                .addGap(11, 11, 11)
                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEditLayout.createSequentialGroup()
                        .addComponent(txtMaNH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblErrorMaNH))
                    .addGroup(pnlEditLayout.createSequentialGroup()
                        .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblErrorHoTen))
                    .addGroup(pnlEditLayout.createSequentialGroup()
                        .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblErrorNgaySinh)))
                .addGap(13, 13, 13)
                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEditLayout.createSequentialGroup()
                        .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDienThoai)
                            .addComponent(lblEmail))
                        .addGap(11, 11, 11)
                        .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlEditLayout.createSequentialGroup()
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblErrorEmail))
                            .addGroup(pnlEditLayout.createSequentialGroup()
                                .addComponent(txtDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblErrorDienThoai))))
                    .addComponent(pnlGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(lblGhiChu)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlBtnMain, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlNav, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        tabs.addTab("CẬP NHẬT", new javax.swing.ImageIcon(getClass().getResource("/com/edusys/img/icon/edit_property_24px.png")), pnlEdit, ""); // NOI18N

        lblSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/img/icon/search_client_24px.png"))); // NOI18N

        pnlSearch.setBackground(new java.awt.Color(255, 255, 255));
        pnlSearch.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        txtSearch.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSearch.setBorder(null);
        txtSearch.setPreferredSize(new java.awt.Dimension(0, 28));

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/img/icon/search_black_24px.png"))); // NOI18N
        btnSearch.setBorderPainted(false);
        btnSearch.setContentAreaFilled(false);

        javax.swing.GroupLayout pnlSearchLayout = new javax.swing.GroupLayout(pnlSearch);
        pnlSearch.setLayout(pnlSearchLayout);
        pnlSearchLayout.setHorizontalGroup(
            pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSearchLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlSearchLayout.setVerticalGroup(
            pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSearchLayout.createSequentialGroup()
                .addGroup(pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        tblList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "MÃ NGƯỜI HỌC", "HỌ VÀ TÊN", "GIỚI TÍNH", "NGÀY SINH", "ĐIỆN THOẠI", "EMAIL", "MÃ NHÂN VIÊN", "NGÀY THÊM"
            }
        ));
        jScrollPane3.setViewportView(tblList);
        tblList.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        javax.swing.GroupLayout pnlLlistLayout = new javax.swing.GroupLayout(pnlLlist);
        pnlLlist.setLayout(pnlLlistLayout);
        pnlLlistLayout.setHorizontalGroup(
            pnlLlistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLlistLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlLlistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3)
                    .addGroup(pnlLlistLayout.createSequentialGroup()
                        .addComponent(lblSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(20, 20, 20))
        );
        pnlLlistLayout.setVerticalGroup(
            pnlLlistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLlistLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlLlistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addContainerGap())
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
            java.util.logging.Logger.getLogger(NguoiHocJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NguoiHocJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NguoiHocJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NguoiHocJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                NguoiHocJDialog dialog = new NguoiHocJDialog();
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
    private javax.swing.ButtonGroup bngGioiTinh;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnInsert;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblDienThoai;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblErrorDienThoai;
    private javax.swing.JLabel lblErrorEmail;
    private javax.swing.JLabel lblErrorHoTen;
    private javax.swing.JLabel lblErrorMaNH;
    private javax.swing.JLabel lblErrorNgaySinh;
    private javax.swing.JLabel lblGhiChu;
    private javax.swing.JLabel lblHoTen;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblMaNH;
    private javax.swing.JLabel lblNgaySinh;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JPanel pnlBackground;
    private javax.swing.JPanel pnlBtnMain;
    private javax.swing.JPanel pnlEdit;
    private javax.swing.JPanel pnlGioiTinh;
    private javax.swing.JPanel pnlLlist;
    private javax.swing.JPanel pnlNav;
    private javax.swing.JPanel pnlSearch;
    private com.edusys.components.MyTitleBar pnlTitleBar;
    private javax.swing.JRadioButton rdoFemale;
    private javax.swing.JRadioButton rdoMale;
    private javax.swing.JTabbedPane tabs;
    private com.edusys.components.MyTable tblList;
    private com.edusys.components.MyTextField txtDienThoai;
    private com.edusys.components.MyTextField txtEmail;
    private javax.swing.JTextArea txtGhiChu;
    private com.edusys.components.MyTextField txtHoTen;
    private com.edusys.components.MyTextField txtMaNH;
    private com.edusys.components.MyTextField txtNgaySinh;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables

    @Override
    protected void init() {
        setLocationRelativeTo(null);
        super.tblList = tblList;
        super.tabs = tabs;
        super.fields = new JTextComponent[]{txtMaNH, txtHoTen, txtNgaySinh, txtDienThoai, txtEmail, txtGhiChu};
        super.dao = EduSysDAO.NGUOI_HOC_DAO;
        super.verifier = MyVerifier.NGUOI_HOC_VERIFIER;
        
        setFieldName(); //đặt tên cho field
        addListeners(); //thêm listener
        updateStatus(); //cập nhật trạng thái  form
      
        //set InputVerifier cho field
        for (JTextComponent field : fields) {
            field.setInputVerifier(verifier);
        }

    }

    @Override
    protected void addListeners() {
        addBtnListeners("main", btnNew, btnInsert, btnUpdate, btnDelete);
        addBtnListeners("nav", btnFirst, btnPrev, btnNext, btnLast);
        XListener.addRadioFocus("Giới Tính", rdoMale, rdoFemale);
        addTableListener();
        
        txtSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                fillTable(-1);
            }
        });
        
        tabs.addChangeListener((ChangeEvent e) -> {
            txtSearch.setText("");
            fillTable(-1);
        });
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent we) {
                if (we.getOppositeWindow() instanceof JDialog) {
                    return;
                }
                fillTable(0);
                txtMaNH.requestFocus();
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
        rdoMale.doClick();
    }

    @Override
    protected NguoiHoc readForm() {
        String maNH = txtMaNH.getText();
        String hoTen = txtHoTen.getText();
        Date ngaySinh = XDate.parse(txtNgaySinh.getText());
        Boolean gioiTinh = rdoMale.isSelected();
        String dienThoai = txtDienThoai.getText().replaceAll("-", "");
        String email = txtEmail.getText();
        String ghiChu = txtGhiChu.getText();
        String maNV = Auth.user != null ? Auth.user.getMaNV() : "";
        Date ngayDK = new Date();
        return new NguoiHoc(maNH, hoTen, ngaySinh, gioiTinh, dienThoai, email, ghiChu, maNV, ngayDK);
    }

    @Override
    protected void setForm(NguoiHoc nh) {
        txtMaNH.setText(nh.getMaNH());
        txtHoTen.setText(nh.getHoTen());
        txtNgaySinh.setText(XDate.toString(nh.getNgaySinh()));
        rdoMale.setSelected(nh.getGioiTinh());
        rdoFemale.setSelected(!nh.getGioiTinh());
        txtDienThoai.setText(nh.getDienThoai());
        txtEmail.setText(nh.getEmail());
        txtGhiChu.setText(nh.getGhiChu());
        updateStatus();
    }

    @Override
    protected Object[] getInfo(NguoiHoc nh) {
        return new Object[]{
            nh.getMaNH(),
            nh.getHoTen(),
            nh.getGioiTinh() ? "Nam" : "Nữ",
            XDate.toString(nh.getNgaySinh()),
            nh.getDienThoai(),
            nh.getEmail(),
            nh.getMaNV(),
            XDate.toString(nh.getNgayDK())
        };
    }

    @Override
    protected List<Object[]> getInfo() {
        String keyword = txtSearch.getText().trim().toLowerCase();
        List<NguoiHoc> listNH = dao.selectAll();
        List<Object[]> listObject = new ArrayList<>();
        List<NguoiHoc> subList = new ArrayList<>();
        if (keyword.equals("")) {
            subList.addAll(listNH);
        } else {
            listNH.forEach((nh) -> {
                boolean maNH = nh.getMaNH().toLowerCase().contains(keyword);
                boolean tenNH = nh.getHoTen().toLowerCase().contains(keyword);
                boolean maNV = nh.getMaNV().toLowerCase().contains(keyword);
                boolean email = nh.getEmail().toLowerCase().contains(keyword);
                boolean phone = nh.getDienThoai().contains(keyword);
                boolean date = XDate.toString(nh.getNgayDK()).contains(keyword);
                if (maNH || tenNH || maNV || email || phone || date) {
                    subList.add(nh);
                }
            });
        }
        subList.forEach((nh) -> {
            listObject.add(getInfo(nh));
        });
        return listObject;
    }

    private void setFieldName() {
        txtDienThoai.setName("Số điện thoại");
        txtEmail.setName("Email");
        txtHoTen.setName("Họ tên");
        txtMaNH.setName("Mã người học");
        txtNgaySinh.setName("Ngày sinh");
        txtGhiChu.setName("Ghi chú");
        txtSearch.setName("Tìm kiếm");
    }

    @Override
    protected boolean isValidated(String action) {
        String maNH = txtMaNH.getText();
        NguoiHoc nh;
        switch (action) {
            case "insert":
                if (!verifyField(fields)) {
                    return false;
                }
                nh = (NguoiHoc) dao.selectById(maNH);
                if (nh != null) {
                    MsgBox.error("Đã tồn tại mã người học trong hệ thống");
                    return false;
                }
                break;
            case "update":
                if (!verifyField(fields)) {
                    return false;
                }
                nh = (NguoiHoc) dao.selectById(maNH);
                if (nh == null) {
                    MsgBox.error("Không tìm thấy mã người học trong hệ thống");
                    return false;
                }
                break;
            case "delete":
                if (!verifyField(txtMaNH)) {
                    return false;
                }
                nh = (NguoiHoc) dao.selectById(maNH);
                if (nh == null) {
                    MsgBox.error("Không tìm thấy mã người học trong hệ thống");
                    return false;
                }
                break;
        }
        return true;
    }

}
