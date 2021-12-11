package com.ultramotor.ui.nhanvien.kho.nhapkho;

import com.ultramotor.dao.SanPhamDAO;
import com.ultramotor.util.MsgBox;
import com.ultramotor.util.XCodeHelper;
import com.ultramotor.util.XDialog;
import com.ultramotor.util.XFile;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;

public class Item extends javax.swing.JPanel {

    private File file;
    private static Map<String, String> map = new SanPhamDAO().getMaVaTenSP();
    public Item() {
        this(null);
    }
    
    public Item(ActionListener deleteListener) {
        initComponents();
        file = XFile.getTempFile("bc", ".png");
        btnDelete.addActionListener(deleteListener);
        txtMaSKU.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String ma = txtMaSKU.getText();
                if(!map.containsKey(ma)){
                    MsgBox.error("Không tìm thấy mã sản phẩm");
                    reset();
                }else{
                    setMaSKU(ma);
                }
            }
        });

        btnTim.addActionListener((ActionEvent e) -> {
            openSearchDialog();
        });
        
    }

    private void reset(){
        txtTenSP.setText("");
        lblBarcode.setIcon(null);
        spnSoLuong.setValue(0);
        txtMaSKU.setText("");
        txtMaSKU.requestFocus();
    }
    
    private void openSearchDialog() {
        TimSPPanel panel = new TimSPPanel();
        panel.setDoneListener((ActionEvent e) -> {
            try {
                setMaSKU(panel.getMaSP());
            } catch (Exception ex) {
                return;
            }
            ((JDialog) ((JComponent) e.getSource()).getTopLevelAncestor()).dispose();
        });

        new Thread(() -> {
            XDialog.getDialog((JFrame) this.getTopLevelAncestor(),panel).setVisible(true);
        }).start();
    }

    public void createBarcode() {
        String text = txtMaSKU.getText();
        XCodeHelper.create39Barcode(file, text);
        try {
            lblBarcode.setIcon(new ImageIcon(ImageIO.read(file).getScaledInstance(lblBarcode.getWidth(), lblBarcode.getHeight(), Image.SCALE_SMOOTH)));
        } catch (IOException ex) {
        }
    }

    public File getFile() {
        return file;
    }

    public void addDeleteListener(ActionListener deleteListener) {
        btnDelete.addActionListener(deleteListener);
    }

    public void setSelected(boolean isSelected) {
        chk.setSelected(isSelected);
    }

    public boolean isSelected() {
        return chk.isSelected();
    }
    
    public void deleteItem(){
        btnDelete.doClick();
    }
    
    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 50, 50);
        g2.setColor(new Color(109, 109, 109));
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 50, 50);

        super.paintComponent(grphcs);
    }

    public void setMaSKU(String ma) {
        txtMaSKU.setText(ma.toUpperCase());
        txtTenSP.setText(map.getOrDefault(ma, ""));
        new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
            }
            createBarcode();
        }).start();
    }
    
    public int getSoLuong(){
        return (int) spnSoLuong.getValue();
    }
    
    public String getMaSKU(){
        return txtMaSKU.getText();
    }

    public String getTenSP(String maSKU){
        return map.getOrDefault(maSKU, maSKU);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnDelete = new com.swingx.Button();
        lblBarcode = new javax.swing.JLabel();
        txtMaSKU = new javax.swing.JTextField();
        spnSoLuong = new javax.swing.JSpinner();
        chk = new javax.swing.JCheckBox();
        lblTenSP = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        lblMaSKu = new javax.swing.JLabel();
        lblSoLuong = new javax.swing.JLabel();
        btnTim = new com.swingx.Button();

        setBackground(new java.awt.Color(255, 255, 255));
        setOpaque(false);

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ultramotor/icon/delete.png"))); // NOI18N
        btnDelete.setTransparent(true);

        lblBarcode.setBackground(new java.awt.Color(204, 204, 204));
        lblBarcode.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        spnSoLuong.setModel(new javax.swing.SpinnerNumberModel(10, 0, 100, 1));

        chk.setOpaque(false);

        lblTenSP.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblTenSP.setText("Tên Sản Phẩm");

        lblMaSKu.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblMaSKu.setText("Mã SKU");

        lblSoLuong.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblSoLuong.setText("Số Lượng");

        btnTim.setBackground(new java.awt.Color(255, 102, 102));
        btnTim.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        btnTim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ultramotor/icon/menu.png"))); // NOI18N
        btnTim.setTransparent(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chk, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMaSKU, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMaSKu))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTenSP)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtTenSP, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSoLuong)
                    .addComponent(spnSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblSoLuong)
                            .addGap(42, 42, 42))
                        .addComponent(spnSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTenSP)
                            .addComponent(lblMaSKu))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(chk, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtMaSKU)
                                .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtMaSKU, txtTenSP});

    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.swingx.Button btnDelete;
    private com.swingx.Button btnTim;
    private javax.swing.JCheckBox chk;
    private javax.swing.JLabel lblBarcode;
    private javax.swing.JLabel lblMaSKu;
    private javax.swing.JLabel lblSoLuong;
    private javax.swing.JLabel lblTenSP;
    private javax.swing.JSpinner spnSoLuong;
    private javax.swing.JTextField txtMaSKU;
    private javax.swing.JTextField txtTenSP;
    // End of variables declaration//GEN-END:variables

}
