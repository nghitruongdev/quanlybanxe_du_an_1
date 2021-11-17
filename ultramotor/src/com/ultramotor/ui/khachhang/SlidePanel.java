package com.ultramotor.ui.khachhang;

import java.awt.Component;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class SlidePanel extends javax.swing.JLayeredPane {

    private JPanel panel;
    private Animator animator;
    private MigLayout layout;
    private Component componentShow;
    private Component componentExit;
    private int currentIndex;
    private boolean next;

    public SlidePanel() {
        initComponents();
        layout = new MigLayout("insets 0");
        panel = new JPanel();
        TimingTarget target = new TimingTargetAdapter() {

            @Override
            public void end() {
                componentExit.setVisible(false);
                layout.setComponentConstraints(componentShow, "pos 0 0 100% 100% "); //,width 100%
            }

            @Override
            public void begin() {
                componentShow.setVisible(true);
                componentExit.setVisible(true);
            }

            @Override
            public void timingEvent(float fraction) {
                double width = panel.getWidth();
                int location = (int) (width * fraction);
                int locationShow = (int) (width * (1f - fraction));
                if (next) {
                    layout.setComponentConstraints(componentShow, String.format("pos %d 0 100%% 100%%, w 100%%", locationShow));
                    layout.setComponentConstraints(componentExit, String.format("pos -%d 0 %f 100%%", location, width - location));
                } else {
                    layout.setComponentConstraints(componentShow, String.format("pos -%d 0 %f 100%%", locationShow, width - locationShow));
                    layout.setComponentConstraints(componentExit, String.format("pos %d 0 100%% 100%%, w 100%%", location ));

                }

                panel.revalidate();
            }
        };
        animator = new Animator(1000, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        panel.setLayout(layout);
        setLayout(new MigLayout("fill, insets 0, debug", "[fill, center]", "3[fill]3"));
        add(panel, "w 100% -6!");
    }

    public void initSlideShow(Component... coms) {
        if (coms.length >= 2) {
            for (Component com : coms) {
                com.setVisible(false);
                panel.add(com, "pos 0 0 0 0");
            }
            if (panel.getComponentCount() > 0) {
                componentShow = panel.getComponent(0);
                componentShow.setVisible(true);
                layout.setComponentConstraints(componentShow, "pos 0 0 100% 100%");
            }

        }
    }


    public void next() {
        if (!animator.isRunning()) {
            next = true;
            currentIndex = getNext(currentIndex);
            componentShow = panel.getComponent(currentIndex);
            componentExit = panel.getComponent(checkNext(currentIndex - 1));
            animator.start();
        } else {

        }
    }

    private int getNext(int index) {
        if (index == panel.getComponentCount() - 1) {
            return 0;
        } else {
            return index + 1;
        }

    }

    private int checkNext(int index) {
        if (index == -1) {
            return panel.getComponentCount() - 1;
        } else {
            return index;
        }
    }
    
    public void back(){
        if (!animator.isRunning()) {
            next = false;
            currentIndex = getBack(currentIndex);
            componentShow = panel.getComponent(currentIndex);
            componentExit = panel.getComponent(checkBack(currentIndex + 1));
            animator.start();
        } else {

        }
    }
    
    private int getBack(int index){
        if (index == 0) {
            return panel.getComponentCount() - 1;
        } else {
            return index - 1;
        }
    }
    
    private int checkBack(int index){
        if(index == panel.getComponentCount()){
            return 0;
        }else{
            return index;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 470, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 503, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
