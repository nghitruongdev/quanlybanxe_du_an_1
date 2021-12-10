package com.ultramotor.ui.sanPham;

import com.swingx.Button;
import com.swingx.PasswordField;
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
import com.ultramotor.util.MyVerifier;
import com.ultramotor.util.XImage;
import com.ultramotor.util.XValidate;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class SanPhamPanel extends JPanel {

    protected SanPham sp;
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

        txtMaSKU.setEditable(false);
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
        refreshList();
        addListeners();
        setFieldName();
        fillComboBox();
    }

    private void refreshList() {
        this.listNSX = daoNSX.selectAll();
        this.listLH = daoLH.selectAll();
        this.listDongSP = daoDongSP.selectAll();
        this.listSP = daoSP.selectAll();
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

        ActionListener insertLs = (event) -> {
            insertItem(event.getActionCommand());
        };
        ActionListener deleteLs = (event) -> {
            deleteItem(event.getActionCommand());
        };

        
        addComboBtnListener(insertLs, btnAddNSX, btnAddLH, btnAddDongSP);
        addComboBtnListener(deleteLs, btnDeleteNSX, btnDeleteLH, btnDeleteDongSP);
        AutoCompleteDecorator.decorate(cboNSX);
                AutoCompleteDecorator.decorate(cboLH);
        AutoCompleteDecorator.decorate(cboDongSP);

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
                e = !validateNSX("insert") ? null : new NhaSanXuat(getAutoID("NSX"), (String) cbo.getSelectedItem());
                dao = daoNSX;
                break;
            case "LoaiHang":
                e = !validateLH("insert") ? null : new LoaiHang(getAutoID("LH"), (String) cbo.getSelectedItem());
                dao = daoLH;
                break;
            case "DongSanPham":
                e = !validateDongSP("insert") ? null : new DongSanPham(getAutoID("DSP"), (String) cbo.getSelectedItem(), ((LoaiHang) cboLH.getSelectedItem()).getIdLH(), ((NhaSanXuat) cboNSX.getSelectedItem()).getIdNSX());
                dao = daoDongSP;
                break;
        }
        if (e == null) {
            return;
        }
        if (dao.insert(e) > 0) {
            cbo.addItem(e);
            cbo.setSelectedItem(e);
            refreshList();
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
                refreshList();
            }
        }
    }

    private String getAutoID(String name) {
        switch (name) {
            case "NSX":
                return "NSX" + String.format("%02d", listNSX.size()+1);
            case "LH":
                return "LH" + String.format("%02d", listLH.size()+1);
            case "DSP":
                return "DSP" + String.format("%02d", listDongSP.size()+1);
            case "SP":
                return "SP" + String.format("%04d", listSP.size()+1);
        }
        return "";
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
        if (os.length > 0) {
            cbo.setSelectedIndex(0);
        }
    }

    private void reset() {
      JTextComponent[] fields = {txtMaSKU, txtTenSP, txtGiaTien, txtDoiXe, txtDiaChiSX, txtMauSac, txtMoTa};
        for (JTextComponent  field: fields) {
            field.setText("");
        }
        txtMaSKU.setText(getAutoID("SP"));
        XImage.setIcon(null, lblHinh, defaultFile);
        if (sp != null) {
            setForm(sp);
        }
    }

    public void setSanPham(SanPham sp) {
        this.sp = sp;
        reset();
    }

    public void setForm(SanPham sp) {
        if (sp == null || sp.getSku() == null) {
            return;
        }
        txtMaSKU.setText(sp.getSku());
        txtTenSP.setText(sp.getTenSP());
        txtMauSac.setText(sp.getMauSac());
        cboPhanKhoi.setSelectedItem(sp.getPhanKhoi());
        cboBaoHanh.setSelectedItem(String.format("%d tháng", sp.getThoiGianBH()));
        txtDiaChiSX.setText(sp.getDiaChiSX());
        txtGiaTien.setValue(sp.getGiaTien());
        txtMoTa.setText(sp.getMoTa());
        txtDoiXe.setText(String.valueOf(sp.getDoiXe()));
        if (sp.getHinh() != null) {
            XImage.setIcon(new File(parent, sp.getHinh()), lblHinh, defaultFile);
        }
        DongSanPham dsp = findDongSP(sp.getIdDongSP());
        if (dsp != null) {
            cboNSX.setSelectedItem(findNSX(dsp.getIdNSX()));
            cboLH.setSelectedItem(findLH(dsp.getIdLH()));
            cboDongSP.setSelectedItem(dsp);
        }
//        txtMaSKU.setEditable(sp.getSku() == null);
    }

    public SanPham getForm() {
        if (validateForm()) {
            SanPham sanpham=  new SanPham();
            sanpham.setSku(txtMaSKU.getText());
            sanpham.setTenSP(txtTenSP.getText());
            sanpham.setMauSac(txtMauSac.getText());
            sanpham.setPhanKhoi((String) cboPhanKhoi.getSelectedItem());
            sanpham.setThoiGianBH(Integer.parseInt(((String) cboBaoHanh.getSelectedItem()).replaceAll("[^0-9]", "")));
            sanpham.setDiaChiSX(txtDiaChiSX.getText());
            sanpham.setGiaTien(((Number)txtGiaTien.getValue()).doubleValue());
            sanpham.setMoTa(txtMoTa.getText());
            sanpham.setDoiXe(Integer.parseInt(txtDoiXe.getText()));
            sanpham.setHinh(lblHinh.getToolTipText());
            sanpham.setIdDongSP(((DongSanPham) cboDongSP.getSelectedItem()).getIdDongSP());
            sanpham.setIdNV(Auth.user == null ? "NV01" : Auth.user.getIdNV());
            return sanpham;
        }
        return null;
    }

    public void setDoneListener(ActionListener doneListener) {
        btnSave.addActionListener(doneListener);
    }

    public boolean validateForm() {
        if (!validateField(txtMaSKU, txtTenSP, txtDiaChiSX, txtDoiXe, txtGiaTien, txtMauSac)) {
            return false;
        }
        Object o = cboDongSP.getSelectedItem();
        if (o == null || !(o instanceof DongSanPham)) {
            MsgBox.error("Vui lòng kiểm tra lại dòng xe");
            return false;
        }
        return true;
    }

    private boolean validateField(JTextField... fields) {
        for (JTextField field : fields) {
            if (!MyVerifier.SAN_PHAM_VERIFIER.verify(field)) {
                String err = "Vui lòng kiểm tra lại dữ liệu";
                if (field instanceof TextField) {
                    err = ((TextField) field).getErrorText();
                } else if (field instanceof PasswordField) {
                    err = ((TextField) field).getErrorText();
                }
                MsgBox.error(err);
                return false;
            }
        }
        return true;
    }

    private void setFieldName() {
        txtMaSKU.setName("Mã sản phẩm");
        txtTenSP.setName("Tên xe");
        txtDiaChiSX.setName("Địa chỉ sản xuất");
        txtDoiXe.setName("Đời xe");
        txtGiaTien.setName("Đơn giá xe");
        txtMauSac.setName("Màu sắc xe");
        setFieldVerifier(txtMaSKU, txtTenSP, txtDiaChiSX, txtDoiXe, txtGiaTien, txtMauSac);
    }

    private void setFieldVerifier(JTextComponent... comp) {
        for (JTextComponent field : comp) {
            field.setInputVerifier(MyVerifier.SAN_PHAM_VERIFIER);
        }
    }

    public Entity getSanPham() {
        return sp;
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
