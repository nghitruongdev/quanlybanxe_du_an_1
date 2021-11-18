
package com.ultramotor.ui.khachhang;


public class ProductDetailsPanel extends javax.swing.JPanel {

    public ProductDetailsPanel() {
        initComponents();
        lblProductInfo.setText("<html><div><p>Xin chào,</p>"
                    + "<p>Bạn nhận được email này vì bạn hoặc ai đó đã yêu cầu "
                    + "thay đổi mật khẩu cho tài khoản của bạn trong LapTrinhCity.</p>"
                    + "<p>Email này hoàn toàn có thể bỏ qua nếu bạn không yêu cầu thay đổi mật khẩu.</p>"
                    + "<p>Mã OTP của bạn là: " + String.format("<span style =\"color: red\">%s</span>", 598695 + "</p>"
                    + "<p>Vui lòng không chia sẻ mã OTP cho bất cứ ai.</p>"
                    + "<p>LapTrinhCity Technical Team.</p></div></html>"));
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        card1 = new com.swingx.Card();
        lblProductInfo = new javax.swing.JLabel();

        lblProductInfo.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(card1, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(lblProductInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(card1, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
                    .addComponent(lblProductInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(67, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.swingx.Card card1;
    private javax.swing.JLabel lblProductInfo;
    // End of variables declaration//GEN-END:variables
}
