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
        btnSave = new javax.swing.JButton();
        lblHinh = new javax.swing.JLabel();
        cboDongSP = new com.swingx.ComboBoxSuggestion();
        txtMaSKU = new com.swingx.TextField();
        lblVaiTro = new javax.swing.JLabel();
        txtDiaChiSX = new com.swingx.TextField();
        txtTenSP = new com.swingx.TextField();
        txtGiaTien = new com.swingx.TextField();
        lblMota = new javax.swing.JLabel();
        btnReset = new javax.swing.JButton();
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

        pblSP.setBackground(new java.awt.Color(255, 255, 255));

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        jScrollPane1.setViewportView(txtMoTa);

        btnSave.setText("Lưu");

        lblHinh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHinh.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 102), 1, true));
        lblHinh.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

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

        btnReset.setText("Đặt lại");

        lblDoiXe.setText("Đời xe");

        cboBaoHanh.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12 tháng", "24 tháng", "36 tháng" }));
        cboBaoHanh.setSelectedIndex(2);

        lblBaoHanh.setText("Thời Gian Bảo Hành");

        lblPhanKhoi.setText("Phân Khối");

        cboPhanKhoi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "50cc", "75cc", "100cc", "125cc", "150cc" }));

        cboColors.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "50cc", "75cc", "100cc", "125cc", "150cc" }));

        lblMauSac.setText("Màu Sắc");

        cboNSX.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "50cc", "75cc", "100cc", "125cc", "150cc" }));

        lblNSX.setText("Nhà Sản Xuất");

        javax.swing.GroupLayout pblSPLayout = new javax.swing.GroupLayout(pblSP);
        pblSP.setLayout(pblSPLayout);
        pblSPLayout.setHorizontalGroup(
            pblSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pblSPLayout.createSequentialGroup()
                .addGroup(pblSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pblSPLayout.createSequentialGroup()
                        .addGap(457, 457, 457)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pblSPLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pblSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pblSPLayout.createSequentialGroup()
                                .addGroup(pblSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtMaSKU, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtDiaChiSX, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cboBaoHanh, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                                    .addGroup(pblSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(cboColors, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                                        .addComponent(lblMauSac))
                                    .addComponent(txtTenSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                                .addGroup(pblSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pblSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(pblSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblDoiXe)
                                            .addGroup(pblSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(pblSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(cboNSX, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                                                    .addComponent(lblNSX))
                                                .addGroup(pblSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(cboDoiXe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(cboPhanKhoi, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                                                    .addComponent(lblPhanKhoi))))
                                        .addComponent(cboDongSP, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblVaiTro, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pblSPLayout.createSequentialGroup()
                                        .addComponent(txtGiaTien, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(31, 31, 31))))
                            .addGroup(pblSPLayout.createSequentialGroup()
                                .addComponent(lblBaoHanh)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(pblSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblHinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblMota)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pblSPLayout.setVerticalGroup(
            pblSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pblSPLayout.createSequentialGroup()
                .addGroup(pblSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pblSPLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(lblHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(lblMota)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pblSPLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtMaSKU, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addGroup(pblSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pblSPLayout.createSequentialGroup()
                                .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(pblSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pblSPLayout.createSequentialGroup()
                                        .addComponent(lblMauSac)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cboColors, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pblSPLayout.createSequentialGroup()
                                        .addComponent(lblPhanKhoi)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cboPhanKhoi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(55, 55, 55)
                                .addComponent(lblDoiXe)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pblSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pblSPLayout.createSequentialGroup()
                                        .addComponent(txtDiaChiSX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblBaoHanh)
                                        .addGap(18, 18, 18)
                                        .addComponent(cboBaoHanh, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                                        .addGroup(pblSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(pblSPLayout.createSequentialGroup()
                                        .addGroup(pblSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pblSPLayout.createSequentialGroup()
                                                .addComponent(cboDoiXe, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(pblSPLayout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(cboDongSP, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18)
                                        .addComponent(lblVaiTro)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtGiaTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(pblSPLayout.createSequentialGroup()
                                .addComponent(lblNSX)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboNSX, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap(110, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pblSP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pblSP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSave;
    private com.swingx.ComboBoxSuggestion cboBaoHanh;
    private com.swingx.ComboBoxSuggestion cboColors;
    private com.swingx.ComboBoxSuggestion cboDoiXe;
    private com.swingx.ComboBoxSuggestion cboDongSP;
    private com.swingx.ComboBoxSuggestion cboNSX;
    private com.swingx.ComboBoxSuggestion cboPhanKhoi;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBaoHanh;
    private javax.swing.JLabel lblDoiXe;
    private javax.swing.JLabel lblHinh;
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
