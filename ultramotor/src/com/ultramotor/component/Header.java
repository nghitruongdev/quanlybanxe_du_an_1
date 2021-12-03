package com.ultramotor.component;

import com.swingx.Button;
import com.swingx.ImageAvatar;
import com.ultramotor.entity.NhanVien;
import com.ultramotor.ui.login.DangNhapJFrame;
import com.ultramotor.ui.nhanvien.NhanVienInfoPanel;
import com.ultramotor.util.MsgBox;
import com.ultramotor.util.XDialog;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.nio.file.Paths;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
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

    private ActionListener logoutLs;
    private ActionListener viewProfileLs;
    private ActionListener changePwLs;

    private NhanVien user;
    private NhanVienInfoPanel pnlNhanVien;

    private void init() {
        layout = new MigLayout("", "10[left]push[]5[]20");
        setLayout(layout);
        initListeners();
        cmdMenu = new Button();
        cmdMenu.setFocusPainted(false);
        cmdMenu.setBackground(getBackground());
        cmdMenu.setIcon(createIcon("menu.png"));

        lblUsername = new JLabel("Username: ");
        lblVaiTro = new JLabel("Chức vụ: ");
        lblUsername.setFont(new Font("Segoe UI", 0, 14));
        lblVaiTro.setFont(new Font("Segoe UI", 0, 14));
        JSeparator sp = new JSeparator(SwingConstants.VERTICAL);
        pnlInfo = new JPanel(new MigLayout("insets 0", "0[al right]0[]0", "0[]0[]0"));
        pnlInfo.add(lblUsername);
        pnlInfo.add(sp, "w 10!, h 100%!,spany2,gapbefore 10, wrap");
        pnlInfo.add(lblVaiTro);
        pnlInfo.setOpaque(false);

        avatar = new ImageAvatar();

        add(cmdMenu, "w 50!, h 50!");
        add(pnlInfo, "h 50!");
        add(avatar, "w 50!, h 50!, gapright 20");

        popup = new JPopupMenu();
        popup.add(new MenuItem("Xem Hồ Sơ", createIcon("profile_25px.png"), createIcon("profile_white_25px.png"), viewProfileLs));
        popup.add(new MenuItem("Đổi Mật Khẩu", createIcon("change_25px.png"), createIcon("change_white_25px.png"), changePwLs));
        popup.add(new MenuItem("Đăng Xuất", createIcon("logout_25px.png"), createIcon("logout_white_25px.png"), logoutLs));

        avatar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent me) {
                System.out.println("Hello");
                if (SwingUtilities.isLeftMouseButton(me)) {
                    System.out.printf("Avatar: X %d, Y %d\n", avatar.getX(), avatar.getY());
                    System.out.println("X: " + me.getX() + ", Y: " + me.getY());
                    popup.show(avatar, avatar.getWidth() - 150, 55);
                    System.out.println("avatar preferredsize: " + avatar.getWidth());
                }
            }
        });
        pnlNhanVien = new NhanVienInfoPanel();
        pnlNhanVien.removeMailButton();
    }

    private void initListeners() {
        logoutLs = (ActionEvent e) -> {
            if (MsgBox.confirm("Bạn có muốn đăng xuất khỏi ứng dụng?", false) == 0) {
                ((JFrame) this.getTopLevelAncestor()).dispose();
                new DangNhapJFrame().setVisible(true);
            }
        };
        viewProfileLs = (ActionEvent e) -> {
            if (user != null) {
                pnlNhanVien.setForm(user);
                XDialog.getDialog((JFrame) this.getTopLevelAncestor(), pnlNhanVien).setVisible(true);
            }
        };
        changePwLs = (ActionEvent e) -> {
        };
    }

    public void addMenuEvent(ActionListener al) {
        cmdMenu.addActionListener(al);
    }

    public void setUser(NhanVien user) {
        if (user == null) {
            return;
        }
        this.user = user;
        Icon icon = new ImageIcon(new File(Paths.get("logos", "nhanvien").toFile(), user.getHinh()).getPath());
        avatar.setIcon(icon);
        lblUsername.setText(user.getHoTenNV());
        lblVaiTro.setText(user.getVaiTro());
    }

    private ImageIcon createIcon(String name) {
        File iconPath = Paths.get("src", "com", "ultramotor", "img", "icon").toFile();
        return new ImageIcon(new File(iconPath, name).getPath());
    }
}

class MenuItem extends JMenuItem {

    public MenuItem(String text, Icon icon, Icon mouseOverIcon, ActionListener e) {
        super(text, icon);
        setBackground(new Color(250, 250, 250));
        setFont(new Font("Segoe UI", 0, 14));
        setPreferredSize(new Dimension(150, 50));
        setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        this.addActionListener(e);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                setIcon(icon);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setIcon(mouseOverIcon);
            }
        });
    }

}
