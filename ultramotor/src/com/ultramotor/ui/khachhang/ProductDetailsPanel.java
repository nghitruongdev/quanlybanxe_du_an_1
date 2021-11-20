package com.ultramotor.ui.khachhang;

import com.ultramotor.entity.ModelSanPham;
import com.ultramotor.entity.SanPham;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductDetailsPanel extends javax.swing.JPanel implements Multilang {

    private Lang lang = Lang.VN;

    public ProductDetailsPanel() {
        initComponents();
        pnlCard.removeButton();
    }

    @Override
    public void setLang(Lang lang) {
        this.lang = lang;
    }

    public void setProductDetails(ModelSanPham model) {
        pnlCard.setModel(model);
        lblProductInfo.setText(getInfo(model));
        repaint();
    }

    private String getInfo(ModelSanPham model) {
        List<SanPham> list = model.getSanPhamList();
        String tenSP = model.getTenModel();
        String doiXe = String.valueOf(model.getDoiXe());
        String nhaSX = "Honda";
        String diaChiSX = "Vietnam";
        String warranty = String.valueOf(list.get(0).getThoiGianBH());
         String colors = toString(getColors(list));
        String phanKhoi = toString(getPhanKhoi(list));
        String giaTien = toString(getGiaTien(list));
        if (lang.equals(Lang.VN)) {
            return String.format("<html> <h1 style=\"font-size: large; color: aqua;padding-left: 20;\">Thông Số Kỹ Thuật</h1>\n"
                    + "  <ul>\n"
                    + "      <li>Tên sản phẩm: %s</li>\n"
                    + "      <br>\n"
                    + "      <li>Màu sắc: %s</li>\n"
                    + "      <br>\n"
                    + "      <li>Phân khối: %s</li>\n"
                    + "      <br>\n"
                    + "      <li>Đời xe: %s</li>\n"
                    + "      <br>\n"
                    + "      <li>Hãng sản xuất: %s</li>\n"
                    + "      <br>\n"
                    + "      <li>Nơi sản xuất: %s</li>\n"
                    + "      <br>\n"
                    + "      <li>Thời gian bảo hành: %s</li>\n"
                    + "      <br>\n"
                    + "      <li><span style=\"color: red;\">Giá Tiền:</span> %s</li>\n"
                    + "  </ul></html>",
                    tenSP,
                    colors,
                    phanKhoi,
                    doiXe,
                    nhaSX,
                    diaChiSX,
                    warranty,
                    giaTien);
        }
        return String.format("<html> <h1 style=\"font-size: large; color: aqua;padding-left: 20;\">SPECIFICATION DETAILS</h1>\n"
                + "  <ul>\n"
                + "      <li>Product Name: %s</li>\n"
                + "      <br>\n"
                + "      <li>Color: %s</li>\n"
                + "      <br>\n"
                + "      <li>Engine: %s</li>\n"
                + "      <br>\n"
                + "      <li>Introduced year: %s</li>\n"
                + "      <br>\n"
                + "      <li>Manufacturer: %s</li>\n"
                + "      <br>\n"
                + "      <li>Made in: %s</li>\n"
                + "      <br>\n"
                + "      <li>Warranty: %s</li>\n"
                + "      <br>\n"
                + "      <li><span style=\"color: red;\">Price-tag:</span> %s</li>\n"
                + "  </ul></html>",
                tenSP,
                    colors,
                    phanKhoi,
                    doiXe,
                    nhaSX,
                    diaChiSX,
                    warranty,
                    giaTien);
    }
    private Set getColors(List<SanPham> list){
        Set<String> colors = new HashSet<>();
        list.forEach(sp -> {
            colors.add(sp.getMauSac());
        });
        return colors;
    }
    
    private Set getPhanKhoi(List<SanPham> list){
        Set<String> set = new HashSet<>();
        list.forEach(sp -> {
            set.add(sp.getPhanKhoi());
        });
        return set;
    }
    
    private Set getGiaTien(List<SanPham> list){
        Set<String> set = new HashSet<>();
        list.forEach(sp -> {
            set.add(String.format("%.2f", sp.getGiaTien()));
        });
        return set;
    }

    private String toString(Collection<String> col){
        StringBuilder sb = new StringBuilder();
        return col.toString();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlCard = new com.ultramotor.ui.khachhang.ProductCard();
        pnlInfo = new javax.swing.JPanel();
        lblProductInfo = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setLayout(new java.awt.GridLayout(1, 0));
        add(pnlCard);

        pnlInfo.setBackground(new java.awt.Color(250, 250, 250));
        pnlInfo.setLayout(new java.awt.BorderLayout());

        lblProductInfo.setBackground(new java.awt.Color(255, 255, 250));
        lblProductInfo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblProductInfo.setText("jLabel1");
        lblProductInfo.setToolTipText("");
        lblProductInfo.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        pnlInfo.add(lblProductInfo, java.awt.BorderLayout.CENTER);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setMinimumSize(new java.awt.Dimension(1, 10));
        jSeparator1.setPreferredSize(new java.awt.Dimension(50, 500));
        pnlInfo.add(jSeparator1, java.awt.BorderLayout.WEST);

        add(pnlInfo);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblProductInfo;
    private com.ultramotor.ui.khachhang.ProductCard pnlCard;
    private javax.swing.JPanel pnlInfo;
    // End of variables declaration//GEN-END:variables

}
