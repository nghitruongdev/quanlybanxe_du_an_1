package com.ultramotor.ui.khachhang;

import com.swingx.MyScrollBar;
import com.ultramotor.entity.ModelSanPham;
import com.ultramotor.entity.SanPham;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.JButton;
import javax.swing.Timer;
import net.miginfocom.swing.MigLayout;

public class ProductListPanel extends javax.swing.JPanel implements Multilang {

    final String SAP_XEP_VN = "Sắp xếp";
    final String SAP_XEP_EN = "Sort";
    final String GIA_TIEN_VN = "Giá tiền";
    final String GIA_TIEN_EN = "Price";
    final String THINH_HANH_VN = "Thịnh hành";
    final String THINH_HANH_EN = "Popularity";
    private Comparator<ProductCard> compGiaTien;
    private Comparator<ProductCard> compSoLuongBan;
    private Lang lang = Lang.VN;
    public ProductListPanel() {
        initComponents();
        init();
    }
    private List<ProductCard> cardList;
    private Timer timer;
    int count = 0;

    private void init() {
        cardList = new ArrayList<>();
        MigLayout layout = new MigLayout("insets 20, fillx, wrap 4", "[center][center][center][center]", "[]30[]");
        pnlList.setLayout(layout);
        jScrollPane1.setVerticalScrollBar(new MyScrollBar());
        jScrollPane1.setViewportView(pnlBackground);
        initComparator();

        btnAsc.setVisible(false);
        btnDesc.setVisible(false);

        timer = new Timer(500, (ActionEvent e) -> {
            System.out.println("Timer Thread: " + Thread.currentThread().getName());
            fillPanel();
            timer.stop();
        });
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                timer.setCoalesce(true);
                timer.restart();
            }
        });

        rdoGiaTien.addActionListener(event -> {
            sortByGiaTien();
        });
        rdoThinhHanh.addActionListener(event -> {
            sortBySoLuongBan();
        });
    }

    private void sortByGiaTien() {
        Collections.sort(cardList, compGiaTien);
        fillPanel();
        System.out.println("Sorted by Giá Tiền");
    }

    private void sortBySoLuongBan() {
        Collections.sort(cardList, compSoLuongBan);
        fillPanel();
        System.out.println("Sorted By Số Lượng Bán");
    }

    private void addShowDetailsListeners(JButton button, ModelSanPham model) {
        button.addActionListener((ActionEvent e) -> {
            new Thread(() -> {
                KhachHangController.showDetails(this.getParent(), model);
            }).start();
        });
    }

    public void setList(List<ModelSanPham> list) {
        cardList.clear();
        list.forEach((model) -> {
            ProductCard card = new ProductCard(model, lang);
            addShowDetailsListeners(card.getButton(), model);
            this.cardList.add(card);
        });
        cardList.sort(compSoLuongBan);
        fillPanel();
    }

    private synchronized void fillPanel() {
        pnlList.removeAll();
        showPanel("List");
        if (cardList == null) {
            return;
        }
        if (cardList.isEmpty()) {
            showPanel("Empty");
            return;
        }
        final int width = (int) ((getWidth()) * 0.2d);
        cardList.forEach(card -> {
            pnlList.add(card, "w " + width + ", h " + (width * 3 / 2));
        });
    }

    private void showPanel(String name) {
        CardLayout layout = (CardLayout) pnlBackground.getLayout();
        layout.show(pnlBackground, name);
    }

    @Override
    public void setLang(Lang lang) {
        this.lang = lang;
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

    void initComparator() {
        compGiaTien = (ProductCard o1, ProductCard o2) -> {
            double num = o2.getModel().getGiaTien() - o1.getModel().getGiaTien();
            num = num != 0 ? num : o2.getModel().getSoLuongBan() - o1.getModel().getSoLuongBan();
            return (int) num;
        };

        compSoLuongBan = (ProductCard o1, ProductCard o2) -> {
            double num = o2.getModel().getSoLuongBan() - o1.getModel().getSoLuongBan();
            num = num != 0 ? num : o2.getModel().getGiaTien() - o1.getModel().getGiaTien();
            return (int) num;
        };
    }
    
    public void reset(){
        pnlList.removeAll();
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
        pnlBackground = new javax.swing.JPanel();
        pnlList = new javax.swing.JPanel();
        pnlEmpty = new com.swingx.PictureBox();

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
        rdoThinhHanh.setSelected(true);
        rdoThinhHanh.setText("Thịnh Hành");
        rdoThinhHanh.setOpaque(false);

        btnAsc.setBackground(new java.awt.Color(0, 0, 0));
        btnAsc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ultramotor/icon/collapse_arrow_24px.png"))); // NOI18N
        btnAsc.setTransparent(true);

        btnDesc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ultramotor/icon/expand_arrow_24px.png"))); // NOI18N
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

        pnlBackground.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout pnlListLayout = new javax.swing.GroupLayout(pnlList);
        pnlList.setLayout(pnlListLayout);
        pnlListLayout.setHorizontalGroup(
            pnlListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 897, Short.MAX_VALUE)
        );
        pnlListLayout.setVerticalGroup(
            pnlListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 495, Short.MAX_VALUE)
        );

        pnlBackground.add(pnlList, "List");

        pnlEmpty.setBackground(new java.awt.Color(255, 255, 255));
        pnlEmpty.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/ultramotor/img/icon/no-result-found.png"))); // NOI18N
        pnlEmpty.setOpaque(true);
        pnlBackground.add(pnlEmpty, "Empty");

        jScrollPane1.setViewportView(pnlBackground);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgrSort;
    private com.swingx.Button btnAsc;
    private com.swingx.Button btnDesc;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblSapxep;
    private javax.swing.JPanel pnlBackground;
    private com.swingx.PictureBox pnlEmpty;
    private javax.swing.JPanel pnlList;
    private javax.swing.JPanel pnlSearch;
    private javax.swing.JRadioButton rdoGiaTien;
    private javax.swing.JRadioButton rdoThinhHanh;
    // End of variables declaration//GEN-END:variables

}
