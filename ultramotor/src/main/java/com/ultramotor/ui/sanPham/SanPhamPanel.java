package com.ultramotor.ui.sanPham;

import com.swingx.Button;
import com.swingx.ComboBoxSuggestion;
import com.swingx.TextField;
import com.ultramotor.dao.DongSanPhamDAO;
import com.ultramotor.dao.LoaiHangDAO;
import com.ultramotor.dao.NhaSanXuatDAO;
import com.ultramotor.dao.SanPhamDAO;
import com.ultramotor.dao.UltraDAO;
import com.ultramotor.entity.DongSanPham;
import com.ultramotor.entity.Entity;
import com.ultramotor.entity.LoaiHang;
import com.ultramotor.entity.NhaSanXuat;
import com.ultramotor.entity.SanPham;
import com.ultramotor.util.Auth;
import com.ultramotor.util.MsgBox;
import com.ultramotor.util.XDialog;
import com.ultramotor.util.XImage;
import com.ultramotor.util.XValidate;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;
import net.miginfocom.swing.MigLayout;

public class SanPhamPanel extends MyPanel<SanPham> {

    private LoaiHangDAO daoLH;
    private NhaSanXuatDAO daoNSX;
    private DongSanPhamDAO daoDongSP;
    private SanPhamDAO daoSP;
    private List<LoaiHang> listLH;
    private List<DongSanPham> listDongSP;
    private List<NhaSanXuat> listNSX;
    private List<SanPham> listSP;
    private final File parent = Paths.get("logos", "sp").toFile();
    private final File defaultFile = new File(parent, "default.png");

    public SanPhamPanel() {
//        super(list);
        initComponents();
        init();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pblSP = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        txtMaSKU = new com.swingx.TextField();
        txtTenSP = new com.swingx.TextField();
        lblMota = new javax.swing.JLabel();
        lblHinh = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnSave = new com.swingx.Button();
        btnReset = new com.swingx.Button();
        txtDiaChiSX = new com.swingx.TextField();
        txtDoiXe = new com.swingx.TextField();
        cboBaoHanh = new com.swingx.ComboBoxSuggestion();
        cboPhanKhoi = new com.swingx.ComboBoxSuggestion();
        jPanel2 = new javax.swing.JPanel();
        cboNSX = new com.swingx.ComboBoxSuggestion();
        lblVaiTro = new javax.swing.JLabel();
        lblLH = new javax.swing.JLabel();
        cboLH = new com.swingx.ComboBoxSuggestion();
        lblNSX = new javax.swing.JLabel();
        cboDongSP = new com.swingx.ComboBoxSuggestion();
        btnAddDongSP = new com.swingx.Button();
        btnAddLH = new com.swingx.Button();
        btnAddNSX = new com.swingx.Button();
        btnDeleteNSX = new com.swingx.Button();
        btnDeleteLH = new com.swingx.Button();
        btnDeleteDongSP = new com.swingx.Button();
        lblPhanKhoi = new javax.swing.JLabel();
        lblBaoHanh = new javax.swing.JLabel();
        txtGiaTien = new com.swingx.TextField();
        txtMauSac = new com.swingx.TextField();

        pblSP.setBackground(new java.awt.Color(250, 250, 250));

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        txtMoTa.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jScrollPane1.setViewportView(txtMoTa);

        txtMaSKU.setLabelText("Mã Sản Phẩm");

        txtTenSP.setLabelText("Tên SP");

        lblMota.setForeground(new java.awt.Color(102, 102, 102));
        lblMota.setText("Mô tả");
        lblMota.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0));

        lblHinh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHinh.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 102), 1, true));
        lblHinh.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jPanel1.setOpaque(false);

        btnSave.setBackground(new java.awt.Color(0, 174, 114));
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setText("Lưu");

        btnReset.setBackground(new java.awt.Color(0, 174, 114));
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setText("Reset");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        txtDiaChiSX.setLabelText("Địa chỉ SX");

        txtDoiXe.setLabelText("Đời xe");

        cboBaoHanh.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12 tháng", "24 tháng", "36 tháng" }));
        cboBaoHanh.setSelectedIndex(2);

        cboPhanKhoi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "50cc", "75cc", "100cc", "125cc", "150cc" }));

        jPanel2.setOpaque(false);

        cboNSX.setEditable(true);
        cboNSX.setName("NhaSanXuat"); // NOI18N

        lblVaiTro.setForeground(new java.awt.Color(102, 102, 102));
        lblVaiTro.setText("Dòng Sản Phẩm");
        lblVaiTro.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0));

        lblLH.setForeground(new java.awt.Color(102, 102, 102));
        lblLH.setText("Loại Hàng");
        lblLH.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0));

        cboLH.setEditable(true);
        cboLH.setName("LoaiHang"); // NOI18N

        lblNSX.setForeground(new java.awt.Color(102, 102, 102));
        lblNSX.setText("Nhà Sản Xuất");
        lblNSX.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0));

        cboDongSP.setEditable(true);
        cboDongSP.setName("DongSanPham"); // NOI18N

        btnAddDongSP.setBorder(null);
        btnAddDongSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ultramotor/icon/edit.png"))); // NOI18N
        btnAddDongSP.setActionCommand("DongSanPham");
        btnAddDongSP.setName(""); // NOI18N

        btnAddLH.setBorder(null);
        btnAddLH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ultramotor/icon/edit.png"))); // NOI18N
        btnAddLH.setActionCommand("LoaiHang");

        btnAddNSX.setBorder(null);
        btnAddNSX.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ultramotor/icon/edit.png"))); // NOI18N
        btnAddNSX.setActionCommand("NhaSanXuat");

        btnDeleteNSX.setBorder(null);
        btnDeleteNSX.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ultramotor/icon/delete.png"))); // NOI18N
        btnDeleteNSX.setActionCommand("NhaSanXuat");

        btnDeleteLH.setBorder(null);
        btnDeleteLH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ultramotor/icon/delete.png"))); // NOI18N
        btnDeleteLH.setActionCommand("LoaiHang");

        btnDeleteDongSP.setBorder(null);
        btnDeleteDongSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ultramotor/icon/delete.png"))); // NOI18N
        btnDeleteDongSP.setActionCommand("DongSanPham");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblNSX)
                    .addComponent(cboNSX, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                    .addComponent(lblLH)
                    .addComponent(cboLH, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblVaiTro)
                    .addComponent(cboDongSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnAddNSX, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDeleteNSX, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnAddDongSP, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDeleteDongSP, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnAddLH, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnDeleteLH, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cboDongSP, cboLH, cboNSX});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNSX, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboNSX, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAddNSX, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblLH)
                        .addGap(3, 3, 3))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnDeleteNSX, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboLH, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAddLH, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addComponent(lblVaiTro))
                    .addComponent(btnDeleteLH, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboDongSP, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(btnAddDongSP, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeleteDongSP, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(106, 106, 106))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cboDongSP, cboLH, cboNSX});

        lblPhanKhoi.setForeground(new java.awt.Color(102, 102, 102));
        lblPhanKhoi.setText("Phân Khối");
        lblPhanKhoi.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0));

        lblBaoHanh.setForeground(new java.awt.Color(102, 102, 102));
        lblBaoHanh.setText("Thời Gian Bảo Hành");
        lblBaoHanh.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0));

        txtGiaTien.setToolTipText("");
        txtGiaTien.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        txtGiaTien.setLabelText("Giá Tiền");

        txtMauSac.setLabelText("Màu sắc");

        javax.swing.GroupLayout pblSPLayout = new javax.swing.GroupLayout(pblSP);
        pblSP.setLayout(pblSPLayout);
        pblSPLayout.setHorizontalGroup(
            pblSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pblSPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pblSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pblSPLayout.createSequentialGroup()
                        .addComponent(lblHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pblSPLayout.createSequentialGroup()
                        .addGroup(pblSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(pblSPLayout.createSequentialGroup()
                                .addComponent(txtMaSKU, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtGiaTien, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pblSPLayout.createSequentialGroup()
                                .addGroup(pblSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtDoiXe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblPhanKhoi)
                                    .addComponent(cboPhanKhoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblBaoHanh)
                                    .addComponent(cboBaoHanh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(pblSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(pblSPLayout.createSequentialGroup()
                                        .addComponent(txtDiaChiSX, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtMauSac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblMota)))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(20, Short.MAX_VALUE))))
        );

        pblSPLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtDiaChiSX, txtDoiXe, txtMaSKU, txtTenSP});

        pblSPLayout.setVerticalGroup(
            pblSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pblSPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pblSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pblSPLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lblHinh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pblSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaSKU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGiaTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pblSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDoiXe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiaChiSX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(pblSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPhanKhoi)
                    .addComponent(lblMota))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pblSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pblSPLayout.createSequentialGroup()
                        .addComponent(cboPhanKhoi, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblBaoHanh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboBaoHanh, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        pblSPLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtDiaChiSX, txtDoiXe, txtGiaTien, txtMaSKU, txtMauSac, txtTenSP});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pblSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pblSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.swingx.Button btnAddDongSP;
    private com.swingx.Button btnAddLH;
    private com.swingx.Button btnAddNSX;
    private com.swingx.Button btnDeleteDongSP;
    private com.swingx.Button btnDeleteLH;
    private com.swingx.Button btnDeleteNSX;
    private com.swingx.Button btnReset;
    private com.swingx.Button btnSave;
    private com.swingx.ComboBoxSuggestion cboBaoHanh;
    private com.swingx.ComboBoxSuggestion cboDongSP;
    private com.swingx.ComboBoxSuggestion cboLH;
    private com.swingx.ComboBoxSuggestion cboNSX;
    private com.swingx.ComboBoxSuggestion cboPhanKhoi;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBaoHanh;
    private javax.swing.JLabel lblHinh;
    private javax.swing.JLabel lblLH;
    private javax.swing.JLabel lblMota;
    private javax.swing.JLabel lblNSX;
    private javax.swing.JLabel lblPhanKhoi;
    private javax.swing.JLabel lblVaiTro;
    private javax.swing.JPanel pblSP;
    private com.swingx.TextField txtDiaChiSX;
    private com.swingx.TextField txtDoiXe;
    private com.swingx.TextField txtGiaTien;
    private com.swingx.TextField txtMaSKU;
    private com.swingx.TextField txtMauSac;
    private javax.swing.JTextArea txtMoTa;
    private com.swingx.TextField txtTenSP;
    // End of variables declaration//GEN-END:variables

    void init() {
        daoLH = new LoaiHangDAO();
        daoNSX = new NhaSanXuatDAO();
        daoDongSP = new DongSanPhamDAO();
        daoSP = new SanPhamDAO();
        this.listNSX = daoNSX.selectAll();
        this.listLH = daoLH.selectAll();
        this.listDongSP = daoDongSP.selectAll();
        this.listSP = daoSP.selectAll();
        addListeners();
        fillComboBox();
    }

    private void addListeners() {
        lblHinh.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    XImage.uploadIcon(getTopLevelAncestor(), lblHinh, defaultFile);
                }
            }
        });

        btnReset.addActionListener(event -> {
            reset();
        });

        cboNSX.addActionListener(event -> {
            fillComboBoxDongSP();
        });

        cboLH.addActionListener(event -> {
            fillComboBoxDongSP();
        });

        cboNSX.getEditor().getEditorComponent().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {

            }
        });

        ActionListener insertLs = (event) -> {
            insertItem(event.getActionCommand());
        };
        ActionListener deleteLs = (event) -> {
            deleteItem(event.getActionCommand());
        };

        addComboBtnListener(insertLs, btnAddNSX, btnAddLH, btnAddDongSP);
        addComboBtnListener(deleteLs, btnDeleteNSX, btnDeleteLH, btnDeleteDongSP);
    }

    private void addComboBtnListener(ActionListener ls, Button... btns) {
        for (Button btn : btns) {
            btn.addActionListener(ls);
        }
    }

    private boolean validateNSX(String action) {
        switch (action) {
            case "insert":
                if (!XValidate.validateName((JTextComponent) cboNSX.getEditor().getEditorComponent())) {
                    MsgBox.error("Tên nhà sản xuất không hợp lệ!");
                    return false;
                }
                if (findNSX((String) cboNSX.getSelectedItem()) != null) {
                    MsgBox.error("Nhà sản xuất đã tồn tại trong hệ thống");
                    return false;
                }
                break;
            case "delete":
                if (containsNSX((NhaSanXuat) cboNSX.getSelectedItem())) {
                    MsgBox.error("Nhà sản xuất vẫn còn sản phẩm trong hệ thống");
                    return false;
                }
                break;
        }
        return true;
    }

    private boolean validateLH(String action) {
        switch (action) {
            case "insert":
                if (!XValidate.validateName((JTextComponent) cboLH.getEditor().getEditorComponent())) {
                    MsgBox.error("Tên loại hàng không hợp lệ!");
                    return false;
                }
                if (findLH((String) cboLH.getSelectedItem()) != null) {
                    MsgBox.error("Loại hàng đã tồn tại trong hệ thống");
                    return false;
                }
                break;

            case "delete":
                if (containsLH((LoaiHang) cboLH.getSelectedItem())) {
                    MsgBox.error("Loại hàng vẫn còn sản phẩm trong hệ thống");
                    return false;
                }
                break;

        }
        return true;
    }

    private boolean validateDongSP(String action) {
        switch (action) {
            case "insert":
                if (!XValidate.validateName((JTextComponent) cboDongSP.getEditor().getEditorComponent())) {
                    MsgBox.error("Tên dòng sản phẩm không hợp lệ!");
                    return false;
                }
                if (findDongSP((String) cboDongSP.getSelectedItem()) != null) {
                    MsgBox.error("Dòng sản phẩm đã tồn tại trong hệ thống");
                    return false;
                }
                Object nsx = cboNSX.getSelectedItem();
                if (nsx == null || !(nsx instanceof NhaSanXuat)) {
                    MsgBox.error("Vui lòng kiểm tra lại thông tin nhà sản xuất");
                    return false;
                }
                Object lh = cboLH.getSelectedItem();
                if (lh == null || !(lh instanceof LoaiHang)) {
                    MsgBox.error("Vui lòng kiểm tra lại thông tin loại hàng");
                    return false;
                }
                break;
            case "delete":
                if (containsDongSP((DongSanPham) cboDongSP.getSelectedItem())) {
                    MsgBox.error("Dòng sản phẩm vẫn còn sản phẩm trong hệ thống");
                    return false;
                }
                break;
        }

        return true;
    }

    private void insertItem(String name) {
        JComboBox cbo = getComboBox(name);
        if (cbo == null) {
            return;
        }

        Object o = cbo.getSelectedItem();
        if (o instanceof Entity) {
            return;
        }
        Entity e = null;
        UltraDAO dao = null;
        switch (name) {
            case "NhaSanXuat":
                e = !validateNSX("insert") ? null : new NhaSanXuat(getAutoNSX(), (String) cbo.getSelectedItem());
                dao = daoNSX;
                break;
            case "LoaiHang":
                e = !validateLH("insert") ? null : new LoaiHang(getAutoLH(), (String) cbo.getSelectedItem());
                dao = daoLH;
                break;
            case "DongSanPham":
                e = !validateDongSP("insert") ? null : new DongSanPham(getAutoDongSP(), (String) cbo.getSelectedItem(), ((LoaiHang) cboLH.getSelectedItem()).getIdLH(), ((NhaSanXuat) cboNSX.getSelectedItem()).getIdNSX());
                dao = daoDongSP;
                break;
        }
        if (e == null) {
            return;
        }
        if (dao.insert(e) > 0) {
            cbo.addItem(e);
            MsgBox.inform("Thêm mới thành công");
        }
    }

    private void deleteItem(String name) {
        if (MsgBox.confirm("Bạn có muốn xoá?", false) == 0) {
            JComboBox cbo = getComboBox(name);
            if (cbo == null) {
                return;
            }

            Object o = cbo.getSelectedItem();
            boolean result = false;
            if (o instanceof Entity) {
            } else {
                cbo.setSelectedIndex(0);
                return;
            }
            if (o instanceof NhaSanXuat) {
                if (validateNSX("delete")) {
                    result = daoNSX.delete(((NhaSanXuat) o).getIdNSX()) > 0;
                }
            } else if (o instanceof LoaiHang) {
                if (validateLH("delete")) {
                    result = daoLH.delete(((LoaiHang) o).getIdLH()) > 0;
                }
            } else if (o instanceof DongSanPham) {
                if (validateDongSP("delete")) {
                    result = daoDongSP.delete(((DongSanPham) o).getIdDongSP()) > 0;
                }
            }
            if (result) {
                cbo.removeItem(o);
                MsgBox.inform("Xoá thành công!");
            }
        }
    }

    private String getAutoNSX() {
        return "NSX" + listNSX.size();
    }

    private String getAutoLH() {
        return "LH" + listLH.size();
    }

    private String getAutoDongSP() {
        return "DSP" + listDongSP.size();
    }

    private JComboBox getComboBox(String name) {
        switch (name) {
            case "NhaSanXuat":
                return cboNSX;
            case "LoaiHang":
                return cboLH;
            case "DongSanPham":
                return cboDongSP;
        }
        return null;
    }

    private void fillComboBox() {
        fillComboBoxNSX();
        fillComboLH();
    }

    private void fillComboBoxNSX() {
        fillComboBox(cboNSX, daoNSX.selectAll().toArray());
    }

    private void fillComboLH() {
        fillComboBox(cboLH, daoLH.selectAll().toArray());
    }

    private void fillComboBoxDongSP() {
        cboDongSP.removeAllItems();
        NhaSanXuat nsx;
        LoaiHang lh;
        try {
            nsx = (NhaSanXuat) cboNSX.getSelectedItem();
            lh = (LoaiHang) cboLH.getSelectedItem();
            if (nsx == null || lh == null) {
                return;
            }
        } catch (ClassCastException ex) {
            return;
        }

        List<DongSanPham> newList = daoDongSP.selectAll().stream()
                .filter(dsp -> dsp.getIdNSX().equals(nsx.getIdNSX()) && dsp.getIdLH().equals(lh.getIdLH()))
                .collect(Collectors.toList());
        fillComboBox(cboDongSP, newList.toArray());
    }

    private void fillComboBox(JComboBox cbo, Object[] os) {
        cbo.setModel(new DefaultComboBoxModel(os));
        System.out.println(os.length);
        if (os.length > 0) {
            cbo.setSelectedIndex(0);
        }
    }

    private void validateLH() {

    }

    private void validateDongSP() {

    }

    private void reset() {
        if (entity != null) {
            setForm(entity);
            return;
        }
        for (Component comp : this.getComponents()) {
            if (comp instanceof JTextComponent) {
                ((JTextComponent) comp).setText("");
            }
        }
        if (cboLH.getItemCount() > 0) {
            cboLH.setSelectedIndex(0);
        }
        XImage.setIcon(null, lblHinh, defaultFile);
    }

    @Override
    public void setForm(SanPham sp) {
        if (sp == null || sp.getIdDongSP() == null) {
            return;
        }
        txtMaSKU.setText(sp.getSku());
        txtTenSP.setText(sp.getTenSP());
        txtMauSac.setText(sp.getMauSac());
        cboPhanKhoi.setSelectedItem(sp.getPhanKhoi());
        cboBaoHanh.setSelectedItem(String.format("%d tháng", sp.getThoiGianBH()));
        txtDiaChiSX.setText(sp.getDiaChiSX());
        txtGiaTien.setText(new DecimalFormat("#,##0.00").format(sp.getGiaTien()));
        txtMoTa.setText(sp.getMoTa());
        txtDoiXe.setText(String.valueOf(sp.getDoiXe()));
        if (sp.getHinh() != null) {
            XImage.setIcon(new File(parent, sp.getHinh()), lblHinh, defaultFile);
        }
        DongSanPham dsp = findDongSP(sp.getIdDongSP());
        if (dsp != null) {
            cboNSX.setSelectedItem(findNSX(dsp.getIdNSX()));
            cboLH.setSelectedItem(dsp);
        }
        cboDongSP.setSelectedItem(sp.getIdDongSP());
        txtMaSKU.setEditable(sp.getSku() == null);
    }

    @Override
    public SanPham getForm() {
        if (validateForm()) {
            SanPham sp = new SanPham();
            sp.setSku(txtMaSKU.getText());
            sp.setTenSP(txtTenSP.getText());
            sp.setMauSac(txtMauSac.getText());
            sp.setPhanKhoi((String) cboPhanKhoi.getSelectedItem());
            sp.setThoiGianBH(Integer.parseInt(((String) cboBaoHanh.getSelectedItem()).replaceAll("[^0-9]", "")));
            sp.setDiaChiSX(txtDiaChiSX.getText());
            sp.setGiaTien(Double.valueOf(txtGiaTien.getText()));
            sp.setMoTa(txtMoTa.getText());
            sp.setDoiXe(Integer.parseInt(txtDoiXe.getText()));
            sp.setHinh(lblHinh.getToolTipText());
            sp.setIdDongSP(((DongSanPham) cboDongSP.getSelectedItem()).getIdDongSP());
            sp.setIdNV(Auth.user == null ? "NV01" : Auth.user.getIdNV());
            return sp;
        }
        return null;
    }

    @Override
    public void setDoneListener(ActionListener doneListener) {
        btnSave.addActionListener(doneListener);
    }

    public boolean validateForm() {
        if (validate(txtMaSKU, txtTenSP, txtGiaTien, txtDiaChiSX)) {
            if (entity == null) {
//                boolean exists = list.stream().anyMatch(sp -> sp.getSku().equals(txtMaSKU.getText()));
//                if (exists) {
//                    MsgBox.error("Đã tồn tại mã sản phẩm");
//                    return false;
//                }
            }
            return true;
        }
        return false;
    }

    private NhaSanXuat findNSX(String name) {
        return listNSX.stream().filter(nsx -> nsx.getIdNSX().equalsIgnoreCase(name.trim()) || nsx.getTenNSX().equalsIgnoreCase(name.trim())).findFirst().orElse(null);
    }

    private LoaiHang findLH(String name) {
        return listLH.stream().filter(lh -> lh.getIdLH().equalsIgnoreCase(name.trim()) || lh.getTenLoaiHang().equalsIgnoreCase(name.trim())).findFirst().orElse(null);
    }

    private DongSanPham findDongSP(String idDongSP) {
        return listDongSP.stream().filter(dsp -> dsp.getIdDongSP().equals(idDongSP)).findFirst().orElse(null);
    }

    private boolean containsNSX(NhaSanXuat nsx) {
        return listDongSP.stream().anyMatch(dsp -> dsp.getIdNSX().equalsIgnoreCase(nsx.getIdNSX()));
    }

    private boolean containsLH(LoaiHang lh) {
        return listDongSP.stream().anyMatch(dsp -> dsp.getIdLH().equalsIgnoreCase(lh.getIdLH()));
    }

    private boolean containsDongSP(DongSanPham dsp) {
        return listSP.stream().anyMatch(sp -> sp.getIdDongSP().equalsIgnoreCase(dsp.getIdDongSP()));
    }
}

abstract class MyPanel<Entity> extends JPanel {

    protected Entity entity;

//    protected List<? extends Entity> list;
//    public MyPanel(List<? extends Entity> list) {
//        this.list = list;
//    }
    public abstract void setForm(Entity e);

    public abstract Entity getForm();

    public abstract void setDoneListener(ActionListener doneListener);

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
        if (entity != null) {
            setForm(entity);
        }
    }

    protected boolean validate(JTextField... fields) {
        for (JTextField field : fields) {
            if (field.getText().isEmpty()) {
                MsgBox.error(String.format("Không được để trống %s", field.getName()));
                field.requestFocus();
                return false;
            }
        }
        return true;
    }
}

class PanelLoaiHang extends MyPanel<LoaiHang> {

    private final JLabel lblTitle;
    private final JLabel lblMaLH;
    private final JLabel lblTenLH;

    private final TextField txtMaLH;
    private final TextField txtTenLH;

    private final Button btn;

    public PanelLoaiHang(List<LoaiHang> list) {
//        super(list);
        setLayout(new MigLayout("insets 40 60 40 60, fillx, wrap 1", "[center, fill]", "10[center]10[center]10"));
        lblTitle = new JLabel("LOẠI HÀNG");
        lblTitle.setFont(new Font("Segoe UI", 0, 24));

        lblMaLH = new JLabel("Mã Loại Hàng");
        lblTenLH = new JLabel("Tên Loại Hàng");

        txtMaLH = new TextField();
        txtTenLH = new TextField();

        txtMaLH.setOnlyField(true);
        txtTenLH.setOnlyField(true);

        txtMaLH.setName("mã loại hàng");
        txtTenLH.setName("tên loại hàng");

        btn = new Button();
        btn.setText("Xong");
        btn.setRadius(15);
        btn.setBackground(new Color(200, 200, 200));
        this.add(lblTitle, " w 200!, gapbottom 10");
        this.add(lblMaLH, "w 200!");
        this.add(txtMaLH, "w 200!");
        this.add(lblTenLH, "w 200!");
        this.add(txtTenLH, "w 200!");
        this.add(btn, "w 100!, h 40!, gaptop 20, gapbottom 20");
        this.setBackground(new Color(250, 250, 250));
        this.setSize(200, 200);
    }

    @Override
    public void setForm(LoaiHang e) {
        txtMaLH.setText(e.getIdLH());
        txtTenLH.setText(e.getTenLoaiHang());
        txtMaLH.setEditable(e.getIdLH() == null);
    }

    @Override
    public LoaiHang getForm() {
        if (validateForm()) {
            return new LoaiHang(txtMaLH.getText(), txtTenLH.getText());
        }
        return null;
    }

    public boolean validateForm() {
        if (validate(txtMaLH, txtTenLH)) {
            if (entity.getIdLH() == null) {
//                boolean exists = list.stream().anyMatch(lh -> lh.getIdLH().equals(txtMaLH.getText()));
//                if (exists) {
//                    MsgBox.error("Mã loại hàng đã tồn tại!");
//                    return false;
//                }
            }
            return true;
        }
        return false;
    }

    @Override
    public void setDoneListener(ActionListener doneListener) {
        btn.addActionListener(doneListener);
    }
}

class PanelNhaSanXuat extends MyPanel<NhaSanXuat> {

    private final JLabel lblTitle;
    private final JLabel lblMaNSX;
    private final JLabel lblTenNSX;

    private final TextField txtMaNSX;
    private final TextField txtTenNSX;

    private final Button btn;

    public PanelNhaSanXuat(List<NhaSanXuat> list) {
//        super(list);
        setLayout(new MigLayout("insets 40 60 40 60, fillx, wrap 1", "[center, fill]", "10[center]10[center]10"));
        lblTitle = new JLabel("NHÀ SẢN XUẤT");
        lblTitle.setFont(new Font("Segoe UI", 0, 24));

        lblMaNSX = new JLabel("Mã Nhà Sản Xuất");
        lblTenNSX = new JLabel("Tên Nhà Sản Xuất");

        txtMaNSX = new TextField();
        txtTenNSX = new TextField();

        txtMaNSX.setOnlyField(true);
        txtTenNSX.setOnlyField(true);

        btn = new Button();
        btn.setText("Xong");
        btn.setRadius(15);
        btn.setBackground(new Color(200, 200, 200));
        this.add(lblTitle, " w 200!, gapbottom 10");
        this.add(lblMaNSX, "w 200!");
        this.add(txtMaNSX, "w 200!");
        this.add(lblTenNSX, "w 200!");
        this.add(txtTenNSX, "w 200!");
        this.add(btn, "w 100!, h 40!, gaptop 20, gapbottom 20");
        this.setBackground(new Color(250, 250, 250));
        this.setSize(200, 200);
    }

    @Override
    public void setForm(NhaSanXuat e) {
        txtMaNSX.setText(e.getIdNSX());
        txtTenNSX.setText(e.getTenNSX());
        txtMaNSX.setEditable(e.getIdNSX() == null);
    }

    @Override
    public NhaSanXuat getForm() {
        if (validateForm()) {
            return new NhaSanXuat(txtMaNSX.getText(), txtTenNSX.getText());
        }
        return null;
    }

    public boolean validateForm() {
        if (validate(txtMaNSX, txtTenNSX)) {
            if (entity.getIdNSX() == null) {
//                boolean exists = list.stream().anyMatch(nsx -> nsx.getIdNSX().equals(txtMaNSX.getText()));
//                if (exists) {
//                    MsgBox.error("Mã nhà sản xuất đã tồn tại!");
//                    return false;
//                }
            }
            return true;
        }
        return false;
    }

    @Override
    public void setDoneListener(ActionListener doneListener) {
        btn.addActionListener(doneListener);
    }
}

class PanelDongSanPham extends MyPanel<DongSanPham> {

    private final JLabel lblTitle;
    private final JLabel lblMaDongSP;
    private final JLabel lblTenDongSP;
    private final JLabel lblNSX;
    private final JLabel lblLH;
    private final TextField txtMaDongSP;
    private final TextField txtTenDongSP;
    private final ComboBoxSuggestion cboNSX;
    private final ComboBoxSuggestion cboLH;
    private List<NhaSanXuat> listNSX;
    private List<LoaiHang> listLH;
    private final Button btn;

    public PanelDongSanPham(List<DongSanPham> list) {
//        super(list);
        setLayout(new MigLayout("insets 40 60 40 60, fillx, wrap 2", "[center, fill]30[center, fill]", "10[center]10[center]10"));
        lblTitle = new JLabel("DÒNG SẢN PHẨM");
        lblTitle.setFont(new Font("Segoe UI", 0, 24));

        lblMaDongSP = new JLabel("Mã Dòng Sản Phẩm");
        lblTenDongSP = new JLabel("Tên Dòng Sản Phẩm");

        txtMaDongSP = new TextField();
        txtTenDongSP = new TextField();

        txtMaDongSP.setOnlyField(true);
        txtTenDongSP.setOnlyField(true);

        lblNSX = new JLabel("Nhà Sản Xuất");
        lblLH = new JLabel("Loại Hàng");

        cboNSX = new ComboBoxSuggestion();
        cboLH = new ComboBoxSuggestion();

        cboNSX.setSize(txtMaDongSP.getSize());
        cboLH.setSize(txtMaDongSP.getSize());

        btn = new Button();
        btn.setText("Xong");
        btn.setRadius(15);
        btn.setBackground(new Color(200, 200, 200));
        this.add(lblTitle, " w 200!, gapbottom 10, spanx 2");

        this.add(lblMaDongSP, "w 200!");
        this.add(lblNSX, "w 200!");
        this.add(txtMaDongSP, "w 200!");
        this.add(cboNSX, "w 200!, h " + txtMaDongSP.getPreferredSize().height);

        this.add(lblTenDongSP, "w 200!");
        this.add(lblLH, "w 200!");
        this.add(txtTenDongSP, "w 200!");
        this.add(cboLH, "w 200!, h " + txtMaDongSP.getPreferredSize().height);

        this.add(btn, "w 100!, h 40!, gaptop 20, gapbottom 20, spanx 2");
        this.setBackground(new Color(250, 250, 250));
        this.setSize(200, 200);
    }

    public PanelDongSanPham(List<DongSanPham> list, List<NhaSanXuat> listNSX, List<LoaiHang> listLH) {
        this(list);
        this.listNSX = listNSX;
        this.listLH = listLH;
        fillComboBox();

    }

    private void fillComboBox() {
        cboNSX.setModel(new DefaultComboBoxModel(listNSX.toArray()));
        cboLH.setModel(new DefaultComboBoxModel(listLH.toArray()));
    }

    @Override
    public void setForm(DongSanPham e) {
        txtMaDongSP.setText(e.getIdNSX());
        txtTenDongSP.setText(e.getTenDongSP());
        txtMaDongSP.setEditable(e.getIdNSX() == null);
        if (listNSX != null) {
            cboNSX.setSelectedItem(
                    listNSX
                            .stream()
                            .filter(nsx -> nsx.getIdNSX().equals(e.getIdNSX()))
                            .findFirst()
                            .orElse(listNSX.get(0)));
        }
        if (listLH != null) {
            cboLH.setSelectedItem(
                    listLH
                            .stream()
                            .filter(lh -> lh.getIdLH().equals(e.getIdLH()))
                            .findFirst()
                            .orElse(listLH.get(0)));
        }
    }

    @Override
    public DongSanPham getForm() {
        if (validateForm()) {
            return new DongSanPham(
                    txtMaDongSP.getText(),
                    txtTenDongSP.getText(),
                    ((NhaSanXuat) cboNSX.getSelectedItem()).getIdNSX(),
                    ((LoaiHang) cboLH.getSelectedItem()).getIdLH());
        }
        return null;
    }

    public boolean validateForm() {
        if (validate(txtMaDongSP, txtTenDongSP)) {
//            if (entity.getIdDongSP() == null) {
//                boolean exists = list.stream().anyMatch(dsp -> dsp.getIdDongSP().equals(txtMaDongSP.getText()));
//                if (exists) {
//                    MsgBox.error("Mã dòng sản phẩm đã tồn tại!");
//                    return false;
//                }
//            }
            return true;
        }
        return false;
    }

    @Override
    public void setDoneListener(ActionListener doneListener) {
        btn.addActionListener(doneListener);
    }
}
//    private void showPanel(Entity e) {
//        switch (e.getClass().getSimpleName()) {
////            case "LoaiHang":
////                panel = new PanelLoaiHang(listLH);
////                panel.setDoneListener(event -> {
////                    Entity entity = (Entity) panel.getForm();
////                    if (entity != null) {
////                        int count = daoLH.insert((LoaiHang) entity);
////                        if (count < 0) {
////                            MsgBox.error("Cập nhật dữ liệu thất bại");
////                            return;
////                        }
//////                        insertRow(tblLoaiHang, entity);
////                        listLH.add((LoaiHang) e);
////                        ((JDialog) panel.getTopLevelAncestor()).dispose();
////                    }
////                });
////                break;
////            case "NhaSanXuat":
////                panel = new PanelNhaSanXuat(listNSX);
////                panel.setDoneListener(event -> {
////                    Entity entity = (Entity) panel.getForm();
////                    if (entity != null) {
////                        int count = daoNSX.insert((NhaSanXuat) entity);
////                        if (count < 0) {
////                            MsgBox.error("Cập nhật dữ liệu thất bại");
////                            return;
////                        }
//////                        insertRow(tblNSX, entity);
////                        listNSX.add((NhaSanXuat) e);
////                        ((JDialog) panel.getTopLevelAncestor()).dispose();
////                    }
////                });
////                break;
////            case "DongSanPham":
////                panel = new PanelDongSanPham(listDongSP, listNSX, listLH);
////                panel.setDoneListener(event -> {
////                    Entity entity = (Entity) panel.getForm();
////                    if (entity != null) {
////                        int count = daoDongSP.insert((DongSanPham) entity);
////                        if (count < 0) {
////                            MsgBox.error("Cập nhật dữ liệu thất bại");
////                            return;
////                        }
//////                        insertRow(tblDongSP, entity);
////                        ((JDialog) panel.getTopLevelAncestor()).dispose();
////                    }
////                });
////                break;
//            case "SanPham":
//                panel = new SanPhamPanel(listSP,listNSX, listDongSP);
//                panel.setSize(800, getHeight());
//                break;
//        }
//        if (panel != null) {
//            panel.setEntity(e);
//            XDialog.getDialog((JFrame) this.getTopLevelAncestor(), panel).setVisible(true);
//        }
//    }

//    private void deleteRow(Entity e) {
//        int confirm = MsgBox.confirm("Bạn có thực sự muốn xoá?", false);
//        if (confirm != 0) {
//            return;
//        }
//        switch (e.getClass().getSimpleName()) {
//            case "LoaiHang":
//                daoLH.delete(((LoaiHang) e).getIdLH());
////                deleteRow(tblLoaiHang);
//                listLH.remove((LoaiHang) e);
//                break;
//            case "NhaSanXuat":
//                daoNSX.delete(((NhaSanXuat) e).getIdNSX());
////                deleteRow(tblNSX);
//                listNSX.remove((NhaSanXuat) e);
//                break;
//            case "DongSanPham":
//                daoDongSP.delete(((DongSanPham) e).getIdDongSP());
////                deleteRow(tblDongSP);
//                listDongSP.remove((DongSanPham) e);
//                break;
//            case "SanPham":
//                daoSP.delete(((SanPham) e).getSku());
//                deleteRow(tblSP);
//                listSP.remove((SanPham) e);
//                break;
//        }
//        MsgBox.inform("Xoá thành công");
//    }
//  private void showPanel(String name) {
//        panel = null;
//        switch (name) {
//            case "LoaiHang":
//                panel = new PanelLoaiHang(listLH);
//                panel.setDoneListener(event -> {
//                    Entity entity = (Entity) panel.getForm();
//                    if (entity != null) {
//                        int count = daoLH.insert((LoaiHang) entity);
//                        if (count < 0) {
//                            MsgBox.error("Cập nhật dữ liệu thất bại");
//                            return;
//                        }
////                        insertRow(tblLoaiHang, entity);
////                        listLH.add((LoaiHang) e);
//                        ((JDialog) panel.getTopLevelAncestor()).dispose();
//                    }
//                });
//                break;
//            case "NhaSanXuat":
//                panel = new PanelNhaSanXuat(listNSX);
//                panel.setDoneListener(event -> {
//                    Entity entity = (Entity) panel.getForm();
//                    if (entity != null) {
//                        int count = daoNSX.insert((NhaSanXuat) entity);
//                        if (count < 0) {
//                            MsgBox.error("Cập nhật dữ liệu thất bại");
//                            return;
//                        }
////                        insertRow(tblNSX, entity);
////                        listNSX.add((NhaSanXuat) e);
//                        ((JDialog) panel.getTopLevelAncestor()).dispose();
//                    }
//                });
//                break;
//            case "DongSanPham":
//                panel = new PanelDongSanPham(listDongSP, listNSX, listLH);
//                panel.setDoneListener(event -> {
//                    Entity entity = (Entity) panel.getForm();
//                    if (entity != null) {
//                        int count = daoDongSP.insert((DongSanPham) entity);
//                        if (count < 0) {
//                            MsgBox.error("Cập nhật dữ liệu thất bại");
//                            return;
//                        }
////                        insertRow(tblDongSP, entity);
//                        ((JDialog) panel.getTopLevelAncestor()).dispose();
//                    }
//                });
//                break;
//        }
//        if (panel != null) {
////            panel.setEntity(e);
//            XDialog.getDialog((JFrame) this.getTopLevelAncestor(), panel).setVisible(true);
//        }
//    }
