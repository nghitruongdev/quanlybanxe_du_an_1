package com.ultramotor.ui;

import com.ultramotor.component.Header;
import com.ultramotor.component.Menu;
import com.ultramotor.swingx.model.ModelMenu;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class MainFrame extends JFrame {

    public static void main(String[] args) {
        JFrame frame = new MainFrame();
//        frame.pack();
        frame.setVisible(true);
    }
    private ActionListener menuListener;
    private MigLayout layout;
    private JLayeredPane bg;
    private Menu menu;
    private Header header;
    private MainPanel body;
    private Animator animator;

    public MainFrame() {
        setLocationRelativeTo(null);
        this.setSize(1200, 800);
//        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
    }

    private void init() {
        initListeners();
        layout = new MigLayout("fill", "0[]0[100%, fill]0", "0[fill, top]0");
        bg = new JLayeredPane();
        bg.setLayout(layout);

        menu = new Menu();
        initMenu(menu);

        header = new Header();
        header.addMenuListener(menuListener);

        body = new MainPanel();

        bg.add(menu, "w 230!, spany 2");
        bg.add(header, "h 70!, wrap");
        bg.add(body, "w 100%, h 100%");
        add(bg);
    }

    private void initListeners() {
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void end() {
                menu.setShowing(!menu.isShowing());
            }

            @Override
            public void timingEvent(float fraction) {
                double width;
                if (menu.isShowing()) {
                    width = 70 + (160 * (1f - fraction)); //giam dan
                } else {
                    width = 70 + (160 * fraction); //tang dan
                }
                layout.setComponentConstraints(menu, String.format("w %f!, spany2", width));
                menu.revalidate();
                revalidate();

            }

        };
        animator = new Animator(400, target);
        animator.setResolution(0);
        animator.setDeceleration(0.5f);
        animator.setAcceleration(0.5f);
        menuListener = (ActionEvent ae) -> {
            if (!animator.isRunning()) {
                animator.start();
            }
        };
    }

    public void initMenu(Menu menu) {
        menu.addMenu(new ModelMenu("Profile", new ImageIcon(getClass().getResource("/com/raven/icon/user.png"))));
        menu.addMenu(new ModelMenu("Message", new ImageIcon(getClass().getResource("/com/raven/icon/message.png"))));
        menu.addMenu(new ModelMenu("Report", new ImageIcon(getClass().getResource("/com/raven/icon/report.png"))));
        menu.addMenu(new ModelMenu("Setting", new ImageIcon(getClass().getResource("/com/raven/icon/setting.png"))));
        menu.addMenu(new ModelMenu("Key", new ImageIcon(getClass().getResource("/com/raven/icon/key.png"))));
    }
}
