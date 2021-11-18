package com.ultramotor.ui.khachhang;

import com.swingx.Card;
import com.swingx.MyScrollBar;
import com.ultramotor.entity.ModelSanPham;
import java.util.List;
import net.miginfocom.swing.MigLayout;

public class ProductListPanel extends javax.swing.JPanel {

    public ProductListPanel() {
        initComponents();
        init();
    }
    private MigLayout layout;
    private List<ModelSanPham> list;

    private void init() {
//        panel.setLayout(new WrapLayout(WrapLayout.LEADING));
//        double width = (getWidth() * 0.2);
        double width = 0;
        System.out.println(width);
        layout = new MigLayout("insets 20, wrap 4, fillx", "", "[]20[]");
//        layout = new MigLayout("insets 10, wrap 4, fillx", String.format("%f[fill]%f[fill]%f[fill]%f[fill]%f", width, width, width, width, width));
        panel.setLayout(layout);
        jScrollPane1.setVerticalScrollBar(new MyScrollBar());
        panel.revalidate();
        panel.repaint();
    }

    public void setList(List<ModelSanPham> list) {
        this.list = list;
        fillPanel();
    }

    private void fillPanel() {
        removeAll();
        list.stream().map(model -> {
            Card card = new ProductCard(model);
            return card;
        }).forEachOrdered(card -> {
            panel.add(card, "w 20%, h 230!");
        });
        panel.revalidate();
        panel.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();

        setBackground(new java.awt.Color(242, 242, 242));

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        panel.setBackground(new java.awt.Color(230, 230, 230));

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 878, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 555, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(panel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables
}
