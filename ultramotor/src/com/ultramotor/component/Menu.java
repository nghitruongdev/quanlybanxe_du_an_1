package com.ultramotor.component;

import com.ultramotor.swingx.event.EventMenuSelected;
import com.ultramotor.swingx.model.ModelMenu;
import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

public class Menu extends javax.swing.JPanel {

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    public void setEvent(EventMenuSelected event) {
        this.menuEvent = event;
    }
    private MigLayout layout;
    private JPanel pnlMenu;
    private EventMenuSelected menuEvent;
    private Profile profile;
    public Menu() {
        initComponents();
        init();
    }

    private void init() {
        setOpaque(false);
//        setLayout(new MigLayout("wrap, fillx, insets 0", "[fill]", "5[]0[]push[60]0"));
        setLayout(new MigLayout("wrap, debug, fillx, insets 0", "[fill]"));
        profile = new Profile();
        
        pnlMenu = new JPanel();
        pnlMenu.setOpaque(false);
        pnlMenu.setLayout(new MigLayout("fillx, wrap", "0[fill]0", "0[]3[]0"));

        profile.setBackground(Color.yellow);
        add(profile, "h 70!, wrap, top");
        add(pnlMenu);
    }

    public void addMenu(ModelMenu model) {
        MenuItem item = new MenuItem(model.getIcon(), model.getMenuName(), pnlMenu.getComponentCount());
//        item.addEvent(new EventMenuSelected() {
//            @Override
//            public void selected(int index) {
//                clearMenu(index);
//            }
//        });
//        item.addEvent(menuEvent);
        pnlMenu.add(item, "h 50!");
    }

    private void clearMenu(int currentSelectedIndex) {
        for (Component com : pnlMenu.getComponents()) {
            if (com instanceof MenuItem) {
                MenuItem item = (MenuItem) com;
                if (item.getIndex() != currentSelectedIndex) {
                    item.setSelected(false);
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        GradientPaint gra = new GradientPaint(0, 0, Color.decode("#56CCF2"), 0, getHeight(), Color.decode("#2F80ED"));
        g2.setPaint(gra);
        g2.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(grphcs);
    }
}
