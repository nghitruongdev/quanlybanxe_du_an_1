package com.ultramotor.ui;

import com.ultramotor.component.Header;
import com.ultramotor.component.Menu;
import com.ultramotor.swingx.model.ModelMenu;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import net.miginfocom.swing.MigLayout;

public class MainFrame extends JFrame {

    public static void main(String[] args) {
        JFrame frame = new MainFrame();
//        frame.pack();
        frame.setVisible(true);
    }
    
    private MigLayout layout;
    private JLayeredPane bg;
    private Menu menu;
    private Header header;
    private MainPanel body;
    
    public MainFrame() {
        this.setSize(1200, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
    }

    private void init() {
        layout = new MigLayout("fill, debug", "0[]0[100%, fill]0", "0[fill, top]0");
        bg = new JLayeredPane();
        bg.setLayout(layout);
        
        menu = new Menu();
        initMenu(menu);
        
        
        header = new Header();
        header.setBackground(Color.CYAN);
        

        body = new MainPanel();
        
        bg.add(menu, "w 230!, spany 2");
        bg.add(header, "h 50!, wrap");
        bg.add(body, "w 100%, h 100%");
        add(bg);
    }

    public void initMenu(Menu menu) {
        menu.addMenu(new ModelMenu("Profile", new ImageIcon(getClass().getResource("/com/raven/icon/user.png"))));
        menu.addMenu(new ModelMenu("Message", new ImageIcon(getClass().getResource("/com/raven/icon/message.png"))));
        menu.addMenu(new ModelMenu("Report", new ImageIcon(getClass().getResource("/com/raven/icon/report.png"))));
        menu.addMenu(new ModelMenu("Setting", new ImageIcon(getClass().getResource("/com/raven/icon/setting.png"))));
        menu.addMenu(new ModelMenu("Key", new ImageIcon(getClass().getResource("/com/raven/icon/key.png"))));
    }
}
