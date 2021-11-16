package com.ultramotor.component;

import com.swingx.event.EventMenuSelected;
import com.swingx.model.ModelMenu;
import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
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

    public boolean isShowing() {
        return showing;
    }

    public void setShowing(boolean showing) {
        this.showing = showing;
    }

    private MigLayout layout;
    private JPanel pnlMenu;
    private EventMenuSelected menuEvent;
    private Profile profile;
    private boolean showing = true;

    public Menu() {
        initComponents();
        init();
    }

    private void init() {
        setOpaque(false);
//        setLayout(new MigLayout("wrap, fillx, insets 0", "[fill]", "5[]0[]push[60]0"));
        setLayout(new MigLayout("wrap, fillx, insets 0", "[fill]"));
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
        pnlMenu.add(item, "h 50!");
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        GradientPaint gra = new GradientPaint(0, 0, Color.decode("#56CCF2"), 0, getHeight(), Color.decode("#2F80ED"));
        GradientPaint gra = new GradientPaint(0, 0, new Color(33, 105, 249), getWidth(), 0, new Color(93, 58, 196));
        g2.setPaint(gra);
        g2.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(grphcs);
    }

}
