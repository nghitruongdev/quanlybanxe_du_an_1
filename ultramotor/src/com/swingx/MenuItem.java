package com.swingx;

import com.swingx.event.EventMenu;
import com.swingx.event.EventMenuSelected;
import com.swingx.model.ModelMenu;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Arrays;
import net.miginfocom.swing.MigLayout;

public class MenuItem extends javax.swing.JPanel {

    private float alpha;
    private ModelMenu model;
    private boolean open;
    private EventMenuSelected eventSelected;
    private int index;

    public MenuItem(ModelMenu model, EventMenu event, EventMenuSelected eventSelected, int index) {
        initComponents();
        this.model = model;
        this.eventSelected = eventSelected;
        this.index = index;
        Font myfont = new Font("Segoe UI", 0, 14);
        setOpaque(false);
        setLayout(new MigLayout("wrap, fillx, insets 0", "[fill]", "[fill, 40!]0[fill, 35!]"));
        MenuButton firstItem = new MenuButton(model.getIcon(), "      " + model.getMenuName());
        firstItem.setFont(myfont);
        firstItem.addActionListener(model.getEvent());
        firstItem.addActionListener(e -> {
            if (model.getSubMenu().length > 0) {
                if (event.menuPressed(MenuItem.this, !open)) {
                    open = !open;
                }
            }
            eventSelected.menuSelected(index, -1);
        });
        add(firstItem);
        int subMenuIndex = -1;
        for (String s : model.getSubMenu()) {
            MenuButton item = new MenuButton(s);
            item.setFont(myfont);
            item.setIndex(++subMenuIndex);
            item.addActionListener(e -> {
                eventSelected.menuSelected(index, item.getIndex());
            });
            add(item);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics grphcs) {
        int width = getWidth();
        int height = getPreferredSize().height;
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(50, 50, 50));
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.fillRect(0, 2, width, 38);
        g2.setComposite(AlphaComposite.SrcOver);
        g2.fillRect(0, 40, width, height - 40);
        g2.setColor(new Color(100, 100, 100));
        g2.drawLine(30, 40, 30, height - 17);
        for (int i = 0; i < model.getSubMenu().length; i++) {
            int y = ((i + 1) * 35 + 40) - 17;
            g2.drawLine(30, y, 38, y);
        }
        if (model.getSubMenu().length > 0) {
            createArrowButton(g2);
        }
        super.paintComponent(grphcs);
    }

    private void createArrowButton(Graphics2D g2) {
        int size = 4;
        int y = 19;
        int x = 205;
        g2.setColor(new Color(230, 230, 230));
        float ay = alpha * size;
        float ay1 = (1f - alpha) * size;
        g2.drawLine(x, (int) (y + ay), x + 4, (int) (y + ay1));
        g2.drawLine(x + 4, (int) (y + ay1), x + 8, (int) (y + ay));
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public ModelMenu getMenu() {
        return model;
    }

    public void setMenu(ModelMenu menu) {
        this.model = menu;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public EventMenuSelected getEventSelected() {
        return eventSelected;
    }

    public void setEventSelected(EventMenuSelected eventSelected) {
        this.eventSelected = eventSelected;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
