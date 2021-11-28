package com.ultramotor.ui.nhanvien.kho.nhapkho;

import com.swingx.CloseButton;
import com.ultramotor.dao.SanPhamDAO;
import com.ultramotor.util.BarcodeHelper;
import com.ultramotor.util.XFile;
import com.ultramotor.util.XJdbc;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Frame;
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
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

public class Item extends javax.swing.JPanel {

    private File file;
    private SanPhamDAO dao;

    public Item() {
        this(null);
    }

    public Item(ActionListener deleteListener) {
        initComponents();
        dao = new SanPhamDAO();
        file = XFile.getTempFile("bc", ".png");
        btnDelete.addActionListener(deleteListener);
        txtMaSKU.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                createBarcode();
            }
        });

        btnTim.addActionListener((ActionEvent e) -> {
            openSearchDialog();
        });
    }

    private void openSearchDialog() {
        TimSPPanel panel = new TimSPPanel();
        panel.setDoneListener((ActionEvent e) -> {
            setMaSKU(panel.getMaSP());
            ((JDialog) ((JComponent) e.getSource()).getTopLevelAncestor()).dispose();
        });

        new Thread(() -> {
            getDialog(panel).setVisible(true);
        }).start();
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

    public void setSelected(boolean isSelected) {
        chk.setSelected(isSelected);
    }

    private JDialog getDialog(JPanel panel) {
        JDialog dialog = new JDialog((Frame) this.getTopLevelAncestor(), true);
        dialog.setUndecorated(true);
        dialog.setBackground(new Color(255, 255, 255, 0));
        JPanel con = new JPanel() {
            @Override
            public void paint(Graphics grphcs) {
                Graphics2D g2 = (Graphics2D) grphcs;
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), 5, 5);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                super.paint(grphcs);
            }
        };

        con.setOpaque(false);
        con.setBackground(new Color(250, 250, 250));
        con.setLayout(new MigLayout("inset 5 20 20 5", "[center]", "[20!][fill, center, grow]"));
        con.add(new CloseButton(), "al right, wrap");
        con.add(panel, "pushy, center, gapright 15");
//        dialog.setBounds(this.getWidth() / 2, this.getHeight(), this.getWidth() / 4, 0);

        dialog.setSize(this.getWidth() / 2, this.getHeight());
        dialog.getContentPane().add(con);
        dialog.pack();

        dialog.setLocation(this.getWidth() / 4, (this.getHeight() - dialog.getHeight()) / 2);
        dialog.setLocationRelativeTo(this.getParent());
        return dialog;
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
        Object ten = XJdbc.value("SELECT TenSP FROM SanPham WHERE SKU = ?", ma);
        txtMaSKU.setText(ma);
        txtTenSP.setText(ten == null ? "" : String.valueOf(ten));
        new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Item.class.getName()).log(Level.SEVERE, null, ex);
            }
            createBarcode();
        }).start();
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

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ultramotor/img/icon/delete.png"))); // NOI18N
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
        btnTim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ultramotor/img/icon/search_in_list_25px.png"))); // NOI18N
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
