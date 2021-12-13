package com.ultramotor.ui.nhanvien.kho.nhapkho;

import com.swingx.scrollbar.ScrollBarCustom;
import com.ultramotor.dao.SanPhamDAO;
import com.ultramotor.util.MsgBox;
import com.ultramotor.util.XFile;
import com.ultramotor.util.XPdf;
import com.ultramotor.util.XReport;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import net.miginfocom.swing.MigLayout;
import net.sf.jasperreports.engine.JRException;

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

    private void addItem() {
        Item item = new Item(deleteListener);
//        item.setMaSKU(sku);
        pnlMain.add(item, "gapbottom 10");
        pnlMain.revalidate();
        new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
            }
            JScrollBar sb = scroll.getVerticalScrollBar();
            sb.setValue(sb.getMaximum());
        }).start();

    }

    private void removeItem(Container con) {
        pnlMain.remove(con);
        pnlMain.revalidate();
        pnlMain.repaint();
    }

    private void addListeners() {
        deleteListener = (ActionEvent e) -> {
            removeItem(((Container) e.getSource()).getParent());
        };

        btnAdd.addActionListener((ActionEvent e) -> {
            addItem();
        });
        chkAll.addActionListener((ActionEvent e) -> {
            boolean boo = chkAll.isSelected();
            for (Component comp : pnlMain.getComponents()) {
                if (comp instanceof Item) {
                    ((Item) comp).setSelected(boo);
                }
            }
            chkAll.setText(boo ? "Bỏ chọn tất cả" : "Chọn tất cả");
        });

        btnDelete.addActionListener(event -> {
            if(pnlMain.getComponents().length==0){
                MsgBox.warning("Không có mục cần xoá");
                return;
            }
            List<Item> list = new ArrayList<>();
            for (Component comp : pnlMain.getComponents()) {
                if (comp instanceof Item && ((Item) comp).isSelected()) {
                    list.add((Item) comp);
                }
            }
            if(list.isEmpty()){
                MsgBox.warning("Vui lòng chọn mục cần xoá");
                return;
            }
            list.forEach(item-> item.deleteItem());
            MsgBox.inform("Đã xoá những mục đã chọn");
        });

        btnPrint.addActionListener(e -> export(true));
        btnExportPDF.addActionListener(e -> export(false));
        
          this.addAncestorListener(new AncestorListener() {
            @Override
            public void ancestorAdded(AncestorEvent event) {
                Item.map = new SanPhamDAO().getMaVaTenSP();
            }

            @Override
            public void ancestorRemoved(AncestorEvent event) {
            }

            @Override
            public void ancestorMoved(AncestorEvent event) {
            }
        });
    }

    private void export(boolean isPrint) {
        List<ItemBean> list = new ArrayList<>();
        if (pnlMain.getComponents().length == 0) {
            MsgBox.warning("Vui lòng thêm sản phẩm!");
            return;
        }
        for (Component comp : pnlMain.getComponents()) {
            if (comp instanceof Item) {
                Item item = (Item) comp;
                if (item.getSoLuong() == 0 || item.getMaSKU().isEmpty()) {
                    item.deleteItem();
                    continue;
                }
                ItemBean bean = new ItemBean(item.getMaSKU(), item.getTenSP(item.getMaSKU()), item.getSoLuong());
                list.add(bean);
            }
        }

        File file = XFile.getTempFile(null, ".pdf");
        try {
            XReport.createBarcodeReport(list, file);
            if (isPrint) {
                XPdf.printPDF(file);
            } else {
                File dest = XPdf.showSaveDialog((JFrame) this.getTopLevelAncestor(), "BarcodeSP.pdf");
                if (dest == null) {
                    return;
                }

                Files.copy(file.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
                if (MsgBox.confirm("Xuất file thành công! Bạn có muốn mở file?", false) == 0) {
                    Desktop.getDesktop().open(dest);
                }
            }
        } catch (JRException | IOException ex) {
            MsgBox.error("Không thể xuất barcode sản phẩm! Vui lòng kiểm tra lại hệ thống");
        } catch (PrinterException ex) {
            MsgBox.error("Không thể in file! Vui lòng kiểm tra lại hệ thống");
        }
    }

    private void fixScroll() {
        scroll.getViewport().setBackground(Color.WHITE);
        ScrollBarCustom sb = new ScrollBarCustom();
        scroll.setVerticalScrollBar(sb);
        scroll.setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        scroll.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        scroll.setBorder(new EmptyBorder(5, 10, 5, 10));
        scroll.setViewportView(pnlMain);
        scroll.setWheelScrollingEnabled(true);
        scroll.setAutoscrolls(true);
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            JFrame fr = new JFrame();
//            fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            fr.getContentPane().add(new BarcodePanel());
//            fr.pack();
//            fr.setVisible(true);
//        });
//    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlFooter = new javax.swing.JPanel();
        btnAdd = new com.swingx.Button();
        chkAll = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        btnDelete = new com.swingx.Button();
        btnPrint = new com.swingx.Button();
        btnExportPDF = new com.swingx.Button();
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

        btnDelete.setBackground(new java.awt.Color(0, 174, 114));
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ultramotor/icon/waste_25px.png"))); // NOI18N
        btnDelete.setText("Xoá");
        btnDelete.setRadius(10);

        btnPrint.setBackground(new java.awt.Color(0, 174, 114));
        btnPrint.setForeground(new java.awt.Color(255, 255, 255));
        btnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ultramotor/icon/print_25px.png"))); // NOI18N
        btnPrint.setText("In Ấn");
        btnPrint.setIconTextGap(10);
        btnPrint.setRadius(10);

        btnExportPDF.setBackground(new java.awt.Color(0, 174, 114));
        btnExportPDF.setForeground(new java.awt.Color(255, 255, 255));
        btnExportPDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ultramotor/icon/pdf_25px.png"))); // NOI18N
        btnExportPDF.setText("Xuất PDF");
        btnExportPDF.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnExportPDF.setIconTextGap(10);
        btnExportPDF.setRadius(10);

        javax.swing.GroupLayout pnlFooterLayout = new javax.swing.GroupLayout(pnlFooter);
        pnlFooter.setLayout(pnlFooterLayout);
        pnlFooterLayout.setHorizontalGroup(
            pnlFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFooterLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(chkAll)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnExportPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnExportPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
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
    private com.swingx.Button btnDelete;
    private com.swingx.Button btnExportPDF;
    private com.swingx.Button btnPrint;
    private javax.swing.JCheckBox chkAll;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel pnlFooter;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JScrollPane scroll;
    // End of variables declaration//GEN-END:variables
}
