package com.ultramotor.ui.nhanvien.kho.nhapkho;

import com.ultramotor.util.BarcodeHelper;
import com.ultramotor.util.XFile;
import java.awt.Color;
import java.awt.Desktop;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Item extends javax.swing.JPanel {

    private File file;

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
                createBarcode();
            }

        });
    }

    public void createBarcode() {
        String text = txtMaSKU.getText();
        BarcodeHelper.create39Barcode(file, text);
        try {
            lblBarcode.setIcon(new ImageIcon(ImageIO.read(file).getScaledInstance(lblBarcode.getWidth(), lblBarcode.getHeight(), Image.SCALE_SMOOTH)));
        } catch (IOException ex) {
            Logger.getLogger(Item.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new Item());
        frame.pack();
        frame.setVisible(true);

    }

    public File getFile() {
        return file;
    }

    public void setDeleteListener(ActionListener deleteListener) {
        btnDelete.addActionListener(deleteListener);
    }

    public void setSelected(boolean isSelected){
        chk.setSelected(isSelected);
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnDelete = new com.swingx.Button();
        lblBarcode = new javax.swing.JLabel();
        txtMaSKU = new javax.swing.JTextField();
        jSpinner1 = new javax.swing.JSpinner();
        chk = new javax.swing.JCheckBox();

        setBackground(new java.awt.Color(255, 255, 255));
        setOpaque(false);

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ultramotor/img/icon/delete.png"))); // NOI18N
        btnDelete.setTransparent(true);

        lblBarcode.setBackground(new java.awt.Color(204, 204, 204));
        lblBarcode.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        txtMaSKU.setText("jTextField1");

        chk.setOpaque(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chk, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMaSKU, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 144, Short.MAX_VALUE)
                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(lblBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaSKU, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chk, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 11, Short.MAX_VALUE)
                        .addComponent(lblBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.swingx.Button btnDelete;
    private javax.swing.JCheckBox chk;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JLabel lblBarcode;
    private javax.swing.JTextField txtMaSKU;
    // End of variables declaration//GEN-END:variables

}
