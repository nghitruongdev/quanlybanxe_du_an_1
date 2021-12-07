package com.ultramotor.ui.nhanvien.kho.nhapkho;

import com.swingx.CloseButton;
import com.swingx.MyScrollBar;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;

public class BarcodePanel extends javax.swing.JPanel {
    
    public BarcodePanel() {
        initComponents();
        init();
    }
    ActionListener deleteListener;
    
    private void init() {
        addListeners();
        fixScroll();
        initPnlMain();
        pnlFooter.setBackground(new Color(255, 255, 255, 0));
    }
    
    private void initPnlMain() {
        MigLayout layout = new MigLayout("insets 10 10 10 10, fillx, wrap 1", "[center, fill]");
        pnlMain.setLayout(layout);
    }
    
    private void addItem(String sku) {
        Item item = new Item(deleteListener);
        item.setMaSKU(sku);
        pnlMain.add(item, "gapbottom 10");
        pnlMain.revalidate();
    }
    
    private void removeItem(Container con) {
        pnlMain.remove(con);
    }
    
    private void addListeners() {
        btnAdd.addActionListener((ActionEvent e) -> {
            openSearchDialog();
        });
        
        deleteListener = (ActionEvent e) -> {
            removeItem(((Container) e.getSource()).getParent());
        };
        
        chkAll.addActionListener((ActionEvent e) -> {
            boolean boo = chkAll.isSelected();
            for (Component component : pnlMain.getComponents()) {
                ((Item) component).setSelected(boo);
            }
            chkAll.setText(boo ? "Bỏ chọn tất cả" : "Chọn tất cả");
        });
    }
    
    private void fixScroll() {
        scroll.getViewport().setBackground(Color.WHITE);
        scroll.setVerticalScrollBar(new MyScrollBar());
        scroll.setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        scroll.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        scroll.setBorder(new EmptyBorder(5, 10, 5, 10));
        scroll.setViewportView(pnlMain);
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new BarcodePanel());
        frame.setSize(1000, 500);
        frame.pack();
        frame.setVisible(true);
        
    }

    private void openSearchDialog() {
        TimSPPanel panel = new TimSPPanel();
        panel.setDoneListener((ActionEvent e)->{
            try {
                addItem(panel.getMaSP());
            } catch (Exception ex) {
                return;
            }
            panel.reset();
        });
        new Thread(() -> {
            getDialog(panel).setVisible(true);
        }).start();
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
        con.setBorder(BorderFactory.createLineBorder(new Color(209, 209, 209)));
        con.setBackground(new Color(250, 250, 250));
        con.setLayout(new MigLayout("inset 5 20 20 5", "[center]", "[20!][fill, center, grow]"));
        con.add(new CloseButton(), "al right, wrap");
        con.add(panel, "pushy, center, gapright 15");

        dialog.setSize(this.getWidth() / 2, this.getHeight());
        dialog.getContentPane().add(con);
        dialog.pack();
        
        dialog.setLocation(this.getWidth() / 4, (this.getHeight() - dialog.getHeight()) / 2);
        dialog.setLocationRelativeTo(this.getParent());
        return dialog;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlFooter = new javax.swing.JPanel();
        btnAdd = new com.swingx.Button();
        chkAll = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        btnDeleteAll = new com.swingx.Button();
        btnDeleteAll1 = new com.swingx.Button();
        btnDeleteAll2 = new com.swingx.Button();
        scroll = new javax.swing.JScrollPane();
        pnlMain = new javax.swing.JPanel();

        setBackground(new java.awt.Color(250, 250, 250));

        pnlFooter.setBackground(new java.awt.Color(0, 0, 0));
        pnlFooter.setOpaque(false);

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ultramotor/icon/add_100px.png"))); // NOI18N
        btnAdd.setPreferredSize(new java.awt.Dimension(50, 50));
        btnAdd.setTransparent(true);

        chkAll.setText("Chọn Tất Cả");
        chkAll.setFocusPainted(false);
        chkAll.setOpaque(false);

        btnDeleteAll.setBackground(new java.awt.Color(0, 174, 114));
        btnDeleteAll.setForeground(new java.awt.Color(255, 255, 255));
        btnDeleteAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ultramotor/icon/waste_25px.png"))); // NOI18N
        btnDeleteAll.setText("Xoá Tất Cả");
        btnDeleteAll.setRadius(10);

        btnDeleteAll1.setBackground(new java.awt.Color(0, 174, 114));
        btnDeleteAll1.setForeground(new java.awt.Color(255, 255, 255));
        btnDeleteAll1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ultramotor/icon/print_25px.png"))); // NOI18N
        btnDeleteAll1.setText("In Ấn");
        btnDeleteAll1.setIconTextGap(10);
        btnDeleteAll1.setRadius(10);

        btnDeleteAll2.setBackground(new java.awt.Color(0, 174, 114));
        btnDeleteAll2.setForeground(new java.awt.Color(255, 255, 255));
        btnDeleteAll2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ultramotor/icon/pdf_25px.png"))); // NOI18N
        btnDeleteAll2.setText("Xuất PDF");
        btnDeleteAll2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnDeleteAll2.setIconTextGap(10);
        btnDeleteAll2.setRadius(10);

        javax.swing.GroupLayout pnlFooterLayout = new javax.swing.GroupLayout(pnlFooter);
        pnlFooter.setLayout(pnlFooterLayout);
        pnlFooterLayout.setHorizontalGroup(
            pnlFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFooterLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(chkAll)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDeleteAll, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDeleteAll1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDeleteAll2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFooterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
        );
        pnlFooterLayout.setVerticalGroup(
            pnlFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFooterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlFooterLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(pnlFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnDeleteAll, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnDeleteAll1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnDeleteAll2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(chkAll))
                        .addGap(0, 38, Short.MAX_VALUE)))
                .addContainerGap())
        );

        scroll.setBorder(null);
        scroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setAutoscrolls(true);

        pnlMain.setBackground(new java.awt.Color(250, 250, 250));

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1015, Short.MAX_VALUE)
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
        );

        scroll.setViewportView(pnlMain);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlFooter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(scroll)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(scroll)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlFooter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.swingx.Button btnAdd;
    private com.swingx.Button btnDeleteAll;
    private com.swingx.Button btnDeleteAll1;
    private com.swingx.Button btnDeleteAll2;
    private javax.swing.JCheckBox chkAll;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel pnlFooter;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JScrollPane scroll;
    // End of variables declaration//GEN-END:variables
}
