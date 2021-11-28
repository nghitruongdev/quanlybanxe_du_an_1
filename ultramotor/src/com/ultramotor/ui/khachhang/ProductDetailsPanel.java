package com.ultramotor.ui.khachhang;

import com.ultramotor.entity.ModelSanPham;
import com.ultramotor.entity.SanPham;
import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JLabel;

public class ProductDetailsPanel extends javax.swing.JPanel implements Multilang {
    
    private Lang lang = Lang.VN;
    
    public ProductDetailsPanel() {
        initComponents();
        pnlCard.removeButton();
        JLabel title = pnlCard.getTitle();
        title.setFont(new Font("Segoe UI", 1, 24));
        title.setForeground(Color.GREEN);
    }
    
    public void setProductDetails(ModelSanPham model) {
        pnlCard.setModel(model, lang);
        lblProductInfo.setText(getInfo(model));
        repaint();
    }
    
    private String getInfo(ModelSanPham model) {
        List<SanPham> list = model.getSanPhamList();
        String tenSP = model.toString();
        String doiXe = String.valueOf(model.getDoiXe());
        String nhaSX = model.getTenNSX();
        String diaChiSX = model.getDiachiSX();
        String warranty = String.valueOf(model.getThoigianBH());
        String colors = getColors(list);
        String phanKhoi = model.getPhanKhoi();
        DecimalFormat format = new DecimalFormat("#,##0.00 VNĐ");
        String giaTien = format.format(model.getGiaTien());
//        String giaTien = String.format("%#,###.2f VNĐ", model.getGiaTien());
        if (lang.equals(Lang.VN)) {
            return String.format("<html> <h1 style=\"font-size:36;font-weight: bold; color: green;padding-left: 20;\">THÔNG SỐ KỸ THUẬT</h1>\n"
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
                    + "      <li>Thời gian bảo hành: %s tháng</li>\n"
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
                + "      <li>Warranty: %s months</li>\n"
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
    
    private String getColors(List<SanPham> list) {
        Set<String> colors = new HashSet<>();
        list.forEach(sp -> {
            colors.add(sp.getMauSac());
        });
        return colors.toString().replaceAll("[\\[\\]]", "");
    }
    
    @Override
    public void setLang(Lang lang) {
        this.lang = lang;
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
        lblProductInfo.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        lblProductInfo.setText("jLabel1");
        lblProductInfo.setToolTipText("");
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
