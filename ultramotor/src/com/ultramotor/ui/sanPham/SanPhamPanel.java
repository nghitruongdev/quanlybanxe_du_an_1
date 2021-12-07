package com.ultramotor.ui.sanPham;

import com.ultramotor.entity.DongSanPham;
import com.ultramotor.entity.NhaSanXuat;
import com.ultramotor.entity.SanPham;
import com.ultramotor.util.Auth;
import com.ultramotor.util.MsgBox;
import com.ultramotor.util.MyConstants;
import com.ultramotor.util.XDate;
import com.ultramotor.util.XImage;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.text.JTextComponent;

public class SanPhamPanel extends MyPanel<SanPham> {

    public SanPhamPanel(List<SanPham> list, List<NhaSanXuat> listNSX, List<DongSanPham> listDongSP) {
        super(list);
        initComponents();
        this.listNSX = listNSX;
        this.listDongSP = listDongSP;
        init();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pblSP = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        cboDongSP = new com.swingx.ComboBoxSuggestion();
        txtMaSKU = new com.swingx.TextField();
        lblVaiTro = new javax.swing.JLabel();
        txtDiaChiSX = new com.swingx.TextField();
        txtTenSP = new com.swingx.TextField();
        txtGiaTien = new com.swingx.TextField();
        lblMota = new javax.swing.JLabel();
        cboDoiXe = new com.swingx.ComboBoxSuggestion();
        lblDoiXe = new javax.swing.JLabel();
        cboBaoHanh = new com.swingx.ComboBoxSuggestion();
        lblBaoHanh = new javax.swing.JLabel();
        lblPhanKhoi = new javax.swing.JLabel();
        cboPhanKhoi = new com.swingx.ComboBoxSuggestion();
        cboColors = new com.swingx.ComboBoxSuggestion();
        lblMauSac = new javax.swing.JLabel();
        cboNSX = new com.swingx.ComboBoxSuggestion();
        lblNSX = new javax.swing.JLabel();
        lblHinh = new javax.swing.JLabel();
        btnReset = new com.swingx.Button();
        btnSave = new com.swingx.Button();
        cboLH = new com.swingx.ComboBoxSuggestion();
        lblLH = new javax.swing.JLabel();

        pblSP.setBackground(new java.awt.Color(255, 255, 255));

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        jScrollPane1.setViewportView(txtMoTa);

        txtMaSKU.setLabelText("SKU");

        lblVaiTro.setForeground(new java.awt.Color(102, 102, 102));
        lblVaiTro.setText("Dòng Sản Phẩm");

        txtDiaChiSX.setLabelText("Địa chỉ SX");

        txtTenSP.setLabelText("Tên SP");

        txtGiaTien.setToolTipText("");
        txtGiaTien.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        txtGiaTien.setLabelText("Giá Tiền");

        lblMota.setForeground(new java.awt.Color(102, 102, 102));
        lblMota.setText("Mô tả");

        lblDoiXe.setForeground(new java.awt.Color(102, 102, 102));
        lblDoiXe.setText("Đời xe");

        cboBaoHanh.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12 tháng", "24 tháng", "36 tháng" }));
        cboBaoHanh.setSelectedIndex(2);

        lblBaoHanh.setForeground(new java.awt.Color(102, 102, 102));
        lblBaoHanh.setText("Thời Gian Bảo Hành");

        lblPhanKhoi.setForeground(new java.awt.Color(102, 102, 102));
        lblPhanKhoi.setText("Phân Khối");

        cboPhanKhoi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "50cc", "75cc", "100cc", "125cc", "150cc" }));

        cboColors.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "50cc", "75cc", "100cc", "125cc", "150cc" }));

        lblMauSac.setForeground(new java.awt.Color(102, 102, 102));
        lblMauSac.setText("Màu Sắc");

        cboNSX.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "50cc", "75cc", "100cc", "125cc", "150cc" }));

        lblNSX.setForeground(new java.awt.Color(102, 102, 102));
        lblNSX.setText("Nhà Sản Xuất");

        lblHinh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHinh.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 102), 1, true));
        lblHinh.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btnReset.setBackground(new java.awt.Color(0, 174, 114));
        btnReset.setText("Reset");

        btnSave.setBackground(new java.awt.Color(0, 174, 114));
        btnSave.setText("Lưu");

        cboLH.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "50cc", "75cc", "100cc", "125cc", "150cc" }));

        lblLH.setForeground(new java.awt.Color(102, 102, 102));
        lblLH.setText("Loại Hàng");

        javax.swing.GroupLayout pblSPLayout = new javax.swing.GroupLayout(pblSP);
        pblSP.setLayout(pblSPLayout);
        pblSPLayout.setHorizontalGroup(
            pblSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pblSPLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pblSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pblSPLayout.createSequentialGroup()
                        .addComponent(cboDoiXe, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                        .addComponent(cboBaoHanh, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pblSPLayout.createSequentialGroup()
                        .addComponent(lblMauSac)
                        .addGap(249, 249, 249)
                        .addComponent(lblPhanKhoi, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pblSPLayout.createSequentialGroup()
                        .addComponent(cboColors, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(101, 101, 101)
                        .addComponent(cboPhanKhoi, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pblSPLayout.createSequentialGroup()
                        .addGroup(pblSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pblSPLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(lblMota))
                            .addComponent(txtDiaChiSX, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(101, 101, 101)
                        .addComponent(txtGiaTien, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pblSPLayout.createSequentialGroup()
                        .addComponent(lblHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addGroup(pblSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboNSX, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboLH, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboDongSP, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pblSPLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(pblSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNSX)
                                    .addComponent(lblLH)
                                    .addComponent(lblVaiTro)))))
                    .addGroup(pblSPLayout.createSequentialGroup()
                        .addGroup(pblSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pblSPLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(lblDoiXe))
                            .addComponent(txtMaSKU, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(101, 101, 101)
                        .addGroup(pblSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pblSPLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(lblBaoHanh))
                            .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))))
            .addGroup(pblSPLayout.createSequentialGroup()
                .addGap(176, 176, 176)
                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pblSPLayout.setVerticalGroup(
            pblSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pblSPLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pblSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pblSPLayout.createSequentialGroup()
                        .addComponent(lblNSX, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(cboNSX, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(lblLH)
                        .addGap(4, 4, 4)
                        .addComponent(cboLH, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(lblVaiTro)
                        .addGap(4, 4, 4)
                        .addComponent(cboDongSP, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(pblSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMaSKU, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pblSPLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addGroup(pblSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDoiXe)
                            .addComponent(lblBaoHanh))))
                .addGap(4, 4, 4)
                .addGroup(pblSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboDoiXe, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboBaoHanh, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(pblSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMauSac)
                    .addComponent(lblPhanKhoi, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pblSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboColors, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboPhanKhoi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(pblSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pblSPLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(lblMota))
                    .addComponent(txtDiaChiSX, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGiaTien, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pblSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pblSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pblSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.swingx.Button btnReset;
    private com.swingx.Button btnSave;
    private com.swingx.ComboBoxSuggestion cboBaoHanh;
    private com.swingx.ComboBoxSuggestion cboColors;
    private com.swingx.ComboBoxSuggestion cboDoiXe;
    private com.swingx.ComboBoxSuggestion cboDongSP;
    private com.swingx.ComboBoxSuggestion cboLH;
    private com.swingx.ComboBoxSuggestion cboNSX;
    private com.swingx.ComboBoxSuggestion cboPhanKhoi;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBaoHanh;
    private javax.swing.JLabel lblDoiXe;
    private javax.swing.JLabel lblHinh;
    private javax.swing.JLabel lblLH;
    private javax.swing.JLabel lblMauSac;
    private javax.swing.JLabel lblMota;
    private javax.swing.JLabel lblNSX;
    private javax.swing.JLabel lblPhanKhoi;
    private javax.swing.JLabel lblVaiTro;
    private javax.swing.JPanel pblSP;
    private com.swingx.TextField txtDiaChiSX;
    private com.swingx.TextField txtGiaTien;
    private com.swingx.TextField txtMaSKU;
    private javax.swing.JTextArea txtMoTa;
    private com.swingx.TextField txtTenSP;
    // End of variables declaration//GEN-END:variables
    private List<DongSanPham> listDongSP;
    private List<NhaSanXuat> listNSX;
    private File parent = Paths.get("logos", "sp").toFile();
    private File defaultFile = new File(parent, "default.png");
    void init() {
        fillComboBox();
        addListeners();
    }

    private void addListeners() {
        lblHinh.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    XImage.uploadIcon(getTopLevelAncestor(),lblHinh, defaultFile);
                }
            }
        });

        btnReset.addActionListener(event -> {
            reset();
        });

        cboNSX.addActionListener(event -> {
            fillComboBoxDongSP();
        });

    }

    private void fillComboBox() {
        fillComboBoxNSX();
        fillComboBoxDongSP();
        fillComboBoxMauSac();
        fillComboBoxDoiXe();
    }

    private void fillComboBoxNSX() {
        fillComboBox(cboNSX, listNSX.toArray());
    }

    private void fillComboBoxDongSP() {
        NhaSanXuat nsx = (NhaSanXuat) cboNSX.getSelectedItem();
        if (nsx != null) {
            List<DongSanPham> newList = listDongSP.stream().filter(dsp -> dsp.getIdNSX().equals(nsx.getIdNSX())).collect(Collectors.toList());
            fillComboBox(cboDongSP, newList.toArray());
        }

    }

    private void fillComboBoxMauSac() {
        Set<String> colors = MyConstants.colorMap.keySet();
        fillComboBox(cboColors, colors.toArray());
    }

    private void fillComboBoxDoiXe() {
        int currentYear = Integer.parseInt(XDate.toString(new Date(), "yyyy"));
        Object[] years = new Object[20];
        for (int i = 0; i < years.length; i++) {
            years[i] = currentYear--;
        }
        fillComboBox(cboDoiXe, years);
    }

    private void fillComboBox(JComboBox cbo, Object[] os) {
        cbo.setModel(new DefaultComboBoxModel(os));
        System.out.println(os.length);
        if (os.length > 0) {
            cbo.setSelectedIndex(0);
        }
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
        if (cboDongSP.getItemCount() > 0) {
            cboDongSP.setSelectedIndex(0);
        }
        XImage.setIcon(null, lblHinh, defaultFile);
    }

    @Override
    public void setForm(SanPham sp) {
        if (sp == null|| sp.getIdDongSP()== null) {
            return;
        }
        txtMaSKU.setText(sp.getSku());
        txtTenSP.setText(sp.getTenSP());
        cboColors.setSelectedItem(sp.getMauSac());
        cboPhanKhoi.setSelectedItem(sp.getPhanKhoi());
        cboBaoHanh.setSelectedItem(String.format("%d tháng", sp.getThoiGianBH()));
        txtDiaChiSX.setText(sp.getDiaChiSX());
        txtGiaTien.setText(new DecimalFormat("#,##0.00").format(sp.getGiaTien()));
        txtMoTa.setText(sp.getMoTa());
        cboDoiXe.setSelectedItem(sp.getDoiXe());
        if (sp.getHinh() != null) {
            XImage.setIcon(new File(parent, sp.getHinh()), lblHinh, defaultFile);
        }
        DongSanPham dsp = findDongSP(sp.getIdDongSP());
        if (dsp != null) {
            cboNSX.setSelectedItem(findNSX(dsp.getIdNSX()));
            cboDongSP.setSelectedItem(dsp);
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
            sp.setMauSac((String) cboColors.getSelectedItem());
            sp.setPhanKhoi((String) cboPhanKhoi.getSelectedItem());
            sp.setThoiGianBH(Integer.parseInt(((String) cboBaoHanh.getSelectedItem()).replaceAll("[^0-9]", "")));
            sp.setDiaChiSX(txtDiaChiSX.getText());
            sp.setGiaTien(Double.valueOf(txtGiaTien.getText()));
            sp.setMoTa(txtMoTa.getText());
            sp.setDoiXe(Integer.valueOf(String.valueOf(cboDoiXe.getSelectedItem())));
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
                boolean exists = list.stream().anyMatch(sp -> sp.getSku().equals(txtMaSKU.getText()));
                if (exists) {
                    MsgBox.error("Đã tồn tại mã sản phẩm");
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private NhaSanXuat findNSX(String idNSX) {
        return listNSX.stream().filter(nsx -> nsx.getIdNSX().equals(idNSX)).findFirst().orElse(null);
    }

    private DongSanPham findDongSP(String idDongSP) {
        return listDongSP.stream().filter(dsp -> dsp.getIdDongSP().equals(idDongSP)).findFirst().orElse(null);
    }
}
