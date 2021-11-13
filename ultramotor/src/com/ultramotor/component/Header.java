package com.ultramotor.component;

import com.ultramotor.swingx.Button;
import com.ultramotor.swingx.ImageAvatar;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import net.miginfocom.swing.MigLayout;

public class Header extends JPanel {

    public Header() {
        setOpaque(true);
        setBackground(Color.white);
        init();
    }
    private Button cmdMenu;
    private ImageAvatar avatar;
    private MigLayout layout;
    private JPanel pnlInfo;
    private JPopupMenu popup;
    private void init() {
        layout = new MigLayout("", "10[left]push[]5[]20");
        setLayout(layout);

        cmdMenu = new Button();
        cmdMenu.setFocusPainted(false);
        cmdMenu.setBackground(getBackground());
        cmdMenu.setIcon(new ImageIcon(getClass().getResource("/com/raven/icon/menu.png")));

        pnlInfo = new JPanel(new MigLayout("insets 0", "0[al right]0[]0", "0[]0[]0"));
        pnlInfo.add(new JLabel("Username: "));
        JSeparator sp = new JSeparator(SwingConstants.VERTICAL);
        pnlInfo.add(sp,"w 10!, h 100%!,spany2,gapbefore 10, wrap");
        pnlInfo.add(new JLabel("Chức vụ: "));
        pnlInfo.setOpaque(false);
        
        avatar = new ImageAvatar();
        avatar.setIcon(new ImageIcon(getClass().getResource("/com/raven/icon/profile1.jpg")));

        add(cmdMenu, "w 50!, h 50!");
        add(pnlInfo,"h 50!");
        add(avatar, "w 60!, h 60!");
        
        popup = new JPopupMenu();
        popup.add(new JMenuItem("Đăng Xuất"));
        popup.add(new JMenuItem("Thoát"));
        
        avatar.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseReleased(MouseEvent me) {
                System.out.println("Hello");
                if(SwingUtilities.isLeftMouseButton(me)){
                        System.out.printf("Avatar: X %d, Y %d\n", avatar.getX(), avatar.getY());
                        System.out.println("X: " + me.getX()+ ", Y: " + me.getY());
                        popup.show(me.getComponent(), 0-avatar.getWidth()/2, 65);
                }
            }
            
        });
        addMouseListener(new MouseAdapter(){
            @Override
            public void mouseReleased(MouseEvent me) {
                System.out.printf("Click at: X %d, Y %d\n", me.getX(), me.getY());
            }
            
        });
    }
    
    public void addMenuListener(ActionListener al){
        cmdMenu.addActionListener(al);
    }

   
}
