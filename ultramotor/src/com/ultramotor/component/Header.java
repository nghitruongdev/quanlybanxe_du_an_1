package com.ultramotor.component;

import com.swingx.Button;
import com.swingx.ImageAvatar;
import com.ultramotor.entity.NhanVien;
import com.ultramotor.util.XImage;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import javax.imageio.ImageIO;
import javax.swing.Icon;
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
    private JLabel lblUsername;
    private JLabel lblVaiTro;
    
    private void init() {
        layout = new MigLayout("", "10[left]push[]5[]20");
        setLayout(layout);

        cmdMenu = new Button();
        cmdMenu.setFocusPainted(false);
        cmdMenu.setBackground(getBackground());
        cmdMenu.setIcon(new ImageIcon(getClass().getResource("/com/raven/icon/menu.png")));

        lblUsername = new JLabel("Username: ");
        lblVaiTro = new JLabel("Chức vụ: ");
        JSeparator sp = new JSeparator(SwingConstants.VERTICAL);
        pnlInfo = new JPanel(new MigLayout("insets 0", "0[al right]0[]0", "0[]0[]0"));
        pnlInfo.add(lblUsername);
        pnlInfo.add(sp, "w 10!, h 100%!,spany2,gapbefore 10, wrap");
        pnlInfo.add(lblVaiTro);
        pnlInfo.setOpaque(false);

        avatar = new ImageAvatar();
//        avatar.setIcon(new ImageIcon(getClass().getResource("/com/raven/icon/profile1.jpg")));

        add(cmdMenu, "w 50!, h 50!");
        add(pnlInfo, "h 50!");
        add(avatar, "w 50!, h 50!");

        popup = new JPopupMenu();
        popup.add(new JMenuItem("Đăng Xuất"));
        popup.add(new JMenuItem("Thoát"));

        avatar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent me) {
                System.out.println("Hello");
                if (SwingUtilities.isLeftMouseButton(me)) {
                    System.out.printf("Avatar: X %d, Y %d\n", avatar.getX(), avatar.getY());
                    System.out.println("X: " + me.getX() + ", Y: " + me.getY());
                    popup.show(me.getComponent(), 0 - avatar.getWidth() / 2, 65);
                }
            }

        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent me) {
                System.out.printf("Click at: X %d, Y %d\n", me.getX(), me.getY());
            }

        });
    }

    public void addMenuEvent(ActionListener al) {
        cmdMenu.addActionListener(al);
    }

    public void setUser(NhanVien user) {
        if (user == null) {
            return;
        }
        Icon icon = null;
        try {
            icon = new ImageIcon(ImageIO.read(new File(Paths.get("logos", "nhanvien").toFile(), user.getHinh())));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        avatar.setIcon(icon);
        lblUsername.setText(user.getHoTenNV());
        lblVaiTro.setText(user.getVaiTro());
    }
}
