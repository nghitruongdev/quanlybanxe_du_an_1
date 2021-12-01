package com.swingx;

import java.awt.Component;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class MenuAnimation {

    private final MigLayout layout;
    private final MenuItem item;
    private Animator animator;
    private boolean open;

    public MenuAnimation(MigLayout layout, Component comp) {
        this.layout = layout;
        this.item = (MenuItem) comp;
        initAnimator(comp, 200);
    }

    public MenuAnimation(MigLayout layout, Component comp, int duration) {
        this.layout = layout;
        this.item = (MenuItem) comp;
        initAnimator(comp, duration);
    }

    private void initAnimator(Component comp, int duration) {
        int height = comp.getPreferredSize().height;
        TimingTarget target = new TimingTargetAdapter() {

            @Override
            public void timingEvent(float fraction) {
                float h;
                if (open) {
                    h = 40 + ((height - 40) * fraction); //tang dan
                    item.setAlpha(fraction);
                } else {
                    h = 40 + ((height - 40) * (1f - fraction)); //giam dan
                    item.setAlpha(1f - fraction);
                }
                layout.setComponentConstraints(item, "h " + h + "!");
                comp.revalidate();
                comp.repaint();
            }

            @Override
            public void end() {
                super.end();
            }

        };
        animator = new Animator(duration, target);
        animator.setResolution(0);
        animator.setDeceleration(0.5f);
    }

    public void openMenu(boolean open){
        this.open = open;
        animator.start();
    }
    
}
