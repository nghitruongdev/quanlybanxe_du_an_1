package com.ultramotor.ui.khachhang;

import com.ultramotor.entity.ModelSanPham;

public class ProductDetailsPanel extends javax.swing.JPanel implements Multilang {

    public ProductDetailsPanel() {
        initComponents();
//        lblProductInfo.setText("<html><div><p>Xin chào,</p>"
//                + "<p>Bạn nhận được email này vì bạn hoặc ai đó đã yêu cầu "
//                + "thay đổi mật khẩu cho tài khoản của bạn trong LapTrinhCity.</p>"
//                + "<p>Email này hoàn toàn có thể bỏ qua nếu bạn không yêu cầu thay đổi mật khẩu.</p>"
//                + "<p>Mã OTP của bạn là: " + String.format("<span style =\"color: red\">%s</span>", 598695 + "</p>"
//                        + "<p>Vui lòng không chia sẻ mã OTP cho bất cứ ai.</p>"
//                        + "<p>LapTrinhCity Technical Team.</p></div></html>"));
//        lblProductInfo.setText(getInfo(null));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlCard = new com.ultramotor.ui.khachhang.ProductCard();
        lblProductInfo = new javax.swing.JLabel();

        setLayout(new java.awt.GridLayout(1, 0));
        add(pnlCard);

        lblProductInfo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblProductInfo.setText("jLabel1");
        add(lblProductInfo);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblProductInfo;
    private com.ultramotor.ui.khachhang.ProductCard pnlCard;
    // End of variables declaration//GEN-END:variables

    @Override
    public void setLang(String lang) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setProductDetails(ModelSanPham model) {
        pnlCard.setModel(model);
        lblProductInfo.setText(getInfo(model));
        repaint();
    }

    private String getInfo(ModelSanPham model) {
       return ("<html> <h1 style=\"font-size: large; color: aqua;padding-left: 20;\">Thông Số Kỹ Thuật</h1>\n"
                + "  <ul>\n"
                + "      <li>Tên sản phẩm: Honda Airblade 2021</li>\n"
                + "      <br>\n"
                + "      <li>Màu sắc: </li>\n"
                + "      <br>\n"
                + "      <li>Phân khối: </li>\n"
                + "      <br>\n"
                + "      <li>Đời xe: </li>\n"
                + "      <br>\n"
                + "      <li>Hãng sản xuất: </li>\n"
                + "      <br>\n"
                + "      <li>Địa chỉ sản xuất: </li>\n"
                + "      <br>\n"
                + "      <li>Thời gian bảo hành: 36</li>\n"
                + "      <br>\n"
                + "      <li><span style=\"color: red;\">Giá Tiền:</span> 250000</li>\n"
                + "      <br>\n"
                + "      <li>SKU: 2020222000</li>\n"
                + "  </ul></html>");
    }
}
