package com.swingx;

import com.swingx.PictureBox;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.Timer;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class SlideShowPanel extends javax.swing.JLayeredPane {

    private final JPanel panel;
    private final Animator animator;
    private final MigLayout layout;
    private final List<PictureBox> coms;
    private Timer timer;
    private Component compShow;
    private Component compExit;
    private int currentIndex;
    private boolean next;

    public SlideShowPanel() {
        layout = new MigLayout("insets 0");
        panel = new JPanel();
        panel.setLayout(layout);
        currentIndex = -1;
        coms = new ArrayList<>();

        TimingTarget target = getTimingTarget();
        animator = getAnimator(target);

        setLayout(new MigLayout("fill, insets 0", "[fill, center]", "3[fill]3"));
        add(panel, "w 100% -6!");
    }

    public void setAuto(int miliseconds) {
        timer = new Timer(miliseconds, (ActionEvent e) -> {
            next();
            System.out.println("Timer is running");
        });
        timer.start();
    }

    public void stopAuto() {
        if (timer != null || timer.isRunning()) {
            timer.stop();
        }
    }

    public void next() {
        navigate(true);
    }

    public void back() {
        navigate(false);
    }

    public void setImages(Icon... images) {
        coms.clear();
        for (Icon image : images) {
            this.coms.add(new PictureBox(image));
        }
        initSlideShow();
    }

    private void navigate(boolean isNext) {
        if (!animator.isRunning()) {
            compExit = panel.getComponent(checkIndex(currentIndex));
            next = isNext;
            if (isNext) {
                currentIndex = checkIndex(currentIndex + 1);
            } else {
                currentIndex = checkIndex(currentIndex - 1);
            }

            compShow = panel.getComponent(currentIndex);
            animator.start();
            if (timer.isRunning()) {
                timer.restart();
            }
        }

    }

    private int checkIndex(int index) {
        if (index >= panel.getComponentCount()) {
            return 0;
        } else if (index < 0) {
            return panel.getComponentCount() - 1;
        } else {
            return index;
        }
    }

    private void initSlideShow() {
        panel.removeAll();
        if (coms.size() >= 2) {
            for (Component com : coms) {
                com.setVisible(false);
                panel.add(com, "pos 0 0 0 0");
            }

            if (panel.getComponentCount() > 0) {
                compShow = panel.getComponent(0);
                compShow.setVisible(true);
                layout.setComponentConstraints(compShow, "pos 0 0 100% 100%");
            }
        }
    }

    private TimingTarget getTimingTarget() {
        return new TimingTargetAdapter() {

            @Override
            public void end() {
                compExit.setVisible(false);
                layout.setComponentConstraints(compShow, "pos 0 0 100% 100% "); //,width 100%
            }

            @Override
            public void begin() {
                compShow.setVisible(true);
                compExit.setVisible(true);
            }

            @Override
            public void timingEvent(float fraction) {
                double width = panel.getWidth();
                int location = (int) (width * fraction);
                int locationShow = (int) (width * (1f - fraction));
                if (next) {
                    layout.setComponentConstraints(compShow, String.format("pos %d 0 100%% 100%%, w 100%%", locationShow));
                    layout.setComponentConstraints(compExit, String.format("pos -%d 0 %f 100%%", location, width - location));
                } else {
                    layout.setComponentConstraints(compShow, String.format("pos -%d 0 %f 100%%", locationShow, width - locationShow));
                    layout.setComponentConstraints(compExit, String.format("pos %d 0 100%% 100%%, w 100%%", location));
                }
                panel.revalidate();
            }
        };
    }

    private Animator getAnimator(TimingTarget target) {
        Animator animator = new Animator(1000, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        return animator;
    }

    //    private void initPanel() {
//        panel = new JPanel();
//        compShow = new PictureBox();
//        compExit = new PictureBox();
//        compShow.setVisible(false);
//        compExit.setVisible(false);
//        panel.add(compShow, "pos 0 0 0 0");
//        panel.add(compExit, "pos 0 0 0 0");
//    }
    //    public void initSlideShow() {
//        if (images == null || images.size() <= 0) {
//            System.out.println("Hello");
//            throw new RuntimeException("You forget to initialize the list");
//        }
//        compShow.setImage(images.get(0));
//        compShow.setVisible(true);
//        panel.remove(0);
//        layout.setComponentConstraints(compShow, "pos 0 0 100% 100%");
//        currentIndex = 0;
//    }
}
