/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ultramotor.component;

import com.swingx.model.ModelMenu;
import com.ultramotor.entity.NhanVien;
import java.awt.CardLayout;
import java.awt.Component;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

/**
 *
 * @author nghipc
 */
public class MainForm extends javax.swing.JPanel {

    private MigLayout layout;
    private Header header;
    private Menu menu;
    private MainPanel pnlMain;
    private Animator animator;

    public MainForm() {
        initComponents();
        init();
    }

    private void init() {
        layout = new MigLayout("fill", "0[]0[100%, fill]0", "0[fill, top]0");
        bg.setLayout(layout);
        menu = new Menu();
        header = new Header();
        pnlMain = new MainPanel();
        bg.add(menu, "w 230!, spany 2");
        bg.add(header, "h 60!, wrap");
        bg.add(pnlMain, "w 100%, h 100%");
        menu.addEvent((int menuIndex, int subMenuIndex) -> {
//            System.out.println("Hello world" + menuIndex + ", subMenu: " + subMenuIndex);
        });
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                double width;
                if (menu.isShowMenu()) {
                    width = 60 + (170 * (1f - fraction));
                } else {
                    width = 60 + (170 * fraction);
                }
                layout.setComponentConstraints(menu, String.format("w %f!, spany2", width));
                menu.revalidate();
            }

            @Override
            public void end() {
                menu.setShowMenu(!menu.isShowMenu());
                menu.setEnableMenu(true);
            }

        };
        animator = new Animator(300, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);

        header.addMenuEvent(e -> {
            if (menu.isShowMenu()) {
                menu.hideAllMenu();
            }
            if (!animator.isRunning()) {
                animator.start();
            }
            menu.setEnableMenu(false);
        });
    }

    public void setUser(NhanVien user) {
        header.setUser(user);
    }

    public void addMenu(ModelMenu... models) {
        menu.removeMenu();
        for (ModelMenu model : models) {
            menu.add(model);
        }
        menu.revalidate();
        menu.repaint();
    }

    public void showForm(Component comp) {
        pnlMain.showForm(comp);
    }

    public void addForm(Map<Component, String> map) {
        pnlMain.addForm(map);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JLayeredPane();

        bg.setBackground(new java.awt.Color(250, 250, 250));
        bg.setOpaque(true);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 802, Short.MAX_VALUE)
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 553, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane bg;
    // End of variables declaration//GEN-END:variables
}

class MainPanel extends JPanel {

    private CardLayout layout;
    private Map<Component, String> map;

    public MainPanel() {
        setOpaque(false);
        layout = new CardLayout();
        setLayout(layout);
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }

    void showForm(Component comp) {
     layout.show(this, map.get(comp));
    }

    void addForm(Map<Component, String> map) {
        this.map = map;
        map.keySet().forEach(component -> {
            this.add(component, map.get(component));
        });
    }
}
