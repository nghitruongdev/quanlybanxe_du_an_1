package com.ultramotor.ui.khachhang;

import com.swingx.Card;
import com.swingx.MyScrollBar;
import com.ultramotor.entity.ModelSanPham;
import com.ultramotor.entity.SanPham;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import net.miginfocom.swing.MigLayout;

public class ProductListPanel extends javax.swing.JPanel implements Multilang {
    
    final String SAP_XEP_VN = "Sắp xếp";
    final String SAP_XEP_EN = "Sort";
    final String GIA_TIEN_VN = "Giá tiền";
    final String GIA_TIEN_EN = "Price";
    final String THINH_HANH_VN = "Thịnh hành";
    final String THINH_HANH_EN = "Popularity";
    
    public ProductListPanel() {
        initComponents();
        init();
    }
    private MigLayout layout;
    private List<ModelSanPham> list;
    
    private void init() {
//        double width = 0;
//        System.out.println(width);
        layout = new MigLayout("insets 20, wrap 4, fillx", "", "[]30[]");
        panel.setLayout(layout);
        jScrollPane1.setVerticalScrollBar(new MyScrollBar());
        jScrollPane1.setViewportView(panel);
//        panel.revalidate();
//        panel.setBackground(Color.red);
        panel.repaint();

//        list = getListExample();
//        fillPanel();
//        setLang(((KhachHangFrame) this.getTopLevelAncestor()).getLang());
    }
    
    public void setList(List<ModelSanPham> list) {
        this.list = list;
        if (list.isEmpty()) {
            this.list = getListExample();
        }
        fillPanel();
    }
    
    private void fillPanel() {
        panel.removeAll();
        list.stream().map(model -> {
            Card card = new ProductCard(model);
            card.getButton().addActionListener((ActionEvent e)->{
                KhachHangController.showDetails(this.getParent(), model);
            });
            return card;
        }).forEachOrdered(card -> {
            int width = (int) (panel.getWidth()*0.2);
            panel.add(card, "w "+ width +", h " + (width*3/2 - 15));
        });
//        panel.revalidate();
//        panel.repaint();
//        System.out.println("Panel finish repainted");
    }
    
    @Override
    public void setLang(Lang lang) {
        if (lang.equals(Lang.VN)) {
            lblSapxep.setText(SAP_XEP_VN);
            rdoGiaTien.setText(GIA_TIEN_VN);
            rdoThinhHanh.setText(THINH_HANH_VN);
        } else {
            lblSapxep.setText(SAP_XEP_EN);
            rdoGiaTien.setText(GIA_TIEN_EN);
            rdoThinhHanh.setText(THINH_HANH_EN);
        }
    }
    
    List<ModelSanPham> getListExample() {
        String[] colors = {"Đỏ", "Trắng", "Đen", "Vàng", "Xanh"};
        
        List<ModelSanPham> list = new ArrayList<>();
        for (int i = 2010; i < 2022; i++) {
            ModelSanPham model = new ModelSanPham();
            model.setTenModel("Airblade");
            model.setDoiXe(i);
            List<SanPham> spList = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                spList.add(new SanPham("SP0000" + i + "" + j,
                        "Airblade",
                        j % 2 == 0 ? "slide1.jpg" : "slide3.jpg",
                        colors[(int) Math.floor((Math.random() * 5))],
                        j % 2 == 0 ? "125cc" : "150cc",
                        36, "Vietnam",
                        50000000, "",
                        "", ""
                ));
                model.setSanPhamList(spList);
                
            }
            list.add(model);
        }
        return list;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgrSort = new javax.swing.ButtonGroup();
        pnlSearch = new javax.swing.JPanel();
        lblSapxep = new javax.swing.JLabel();
        rdoGiaTien = new javax.swing.JRadioButton();
        rdoThinhHanh = new javax.swing.JRadioButton();
        btnAsc = new com.swingx.Button();
        btnDesc = new com.swingx.Button();
        jScrollPane1 = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();

        setBackground(new java.awt.Color(242, 242, 242));
        setLayout(new java.awt.BorderLayout());

        pnlSearch.setBackground(new java.awt.Color(250, 250, 250));
        pnlSearch.setMinimumSize(new java.awt.Dimension(0, 30));
        pnlSearch.setName(""); // NOI18N
        pnlSearch.setPreferredSize(new java.awt.Dimension(895, 60));

        lblSapxep.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblSapxep.setText("Sắp Xếp");

        bgrSort.add(rdoGiaTien);
        rdoGiaTien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdoGiaTien.setText("Giá Tiền");
        rdoGiaTien.setOpaque(false);

        bgrSort.add(rdoThinhHanh);
        rdoThinhHanh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdoThinhHanh.setText("Thịnh Hành");
        rdoThinhHanh.setOpaque(false);

        btnAsc.setBackground(new java.awt.Color(0, 0, 0));
        btnAsc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ultramotor/img/icon/collapse_arrow_24px.png"))); // NOI18N
        btnAsc.setTransparent(true);

        btnDesc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ultramotor/img/icon/expand_arrow_24px.png"))); // NOI18N
        btnDesc.setTransparent(true);

        javax.swing.GroupLayout pnlSearchLayout = new javax.swing.GroupLayout(pnlSearch);
        pnlSearch.setLayout(pnlSearchLayout);
        pnlSearchLayout.setHorizontalGroup(
            pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSearchLayout.createSequentialGroup()
                .addContainerGap(517, Short.MAX_VALUE)
                .addComponent(lblSapxep)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rdoGiaTien)
                .addGap(18, 18, 18)
                .addComponent(rdoThinhHanh)
                .addGap(10, 10, 10)
                .addComponent(btnAsc, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
        );
        pnlSearchLayout.setVerticalGroup(
            pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSearchLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdoThinhHanh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAsc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblSapxep, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rdoGiaTien, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pnlSearchLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAsc, btnDesc, lblSapxep, rdoGiaTien, rdoThinhHanh});

        add(pnlSearch, java.awt.BorderLayout.PAGE_START);

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setOpaque(false);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 897, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 495, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(panel);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgrSort;
    private com.swingx.Button btnAsc;
    private com.swingx.Button btnDesc;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblSapxep;
    private javax.swing.JPanel panel;
    private javax.swing.JPanel pnlSearch;
    private javax.swing.JRadioButton rdoGiaTien;
    private javax.swing.JRadioButton rdoThinhHanh;
    // End of variables declaration//GEN-END:variables

}
