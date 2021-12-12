package com.ultramotor.component;

import com.swingx.Button;
import com.swingx.ImageAvatar;
import com.ultramotor.entity.NhanVien;
import com.ultramotor.ui.login.DangNhapJFrame;
import com.ultramotor.ui.nhanvien.NhanVienInfoPanel;
import com.ultramotor.util.MsgBox;
import com.ultramotor.util.XDialog;
import java.awt.Color;
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
import com.swingx.PopupMenuItem;
import com.ultramotor.dao.NhanVienDAO;
import com.ultramotor.util.Auth;
import java.awt.event.WindowAdapter;
import javax.swing.JDialog;
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
    private ActionListener updateLs;
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
        popup.add(new PopupMenuItem("Xem Hồ Sơ", createIcon("profile_25px.png"), createIcon("profile_white_25px.png"), viewProfileLs));
//        popup.add(new PopupMenuItem("Đổi Mật Khẩu", createIcon("change_25px.png"), createIcon("change_white_25px.png"), changePwLs));
        popup.add(new PopupMenuItem("Đăng Xuất", createIcon("logout_25px.png"), createIcon("logout_white_25px.png"), logoutLs));

        avatar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent me) {
                if (SwingUtilities.isLeftMouseButton(me)) {
                    popup.show(avatar, avatar.getWidth() - 150, 55);
                }
            }
        });
        pnlNhanVien = new NhanVienInfoPanel();
        pnlNhanVien.removeMailButton();
        pnlNhanVien.setUpdateListener(updateLs);
    }

    private void initListeners() {
        logoutLs = (ActionEvent e) -> {
            if (MsgBox.confirm("Bạn có muốn đăng xuất khỏi ứng dụng?", false) == 0) {
                ((JFrame) this.getTopLevelAncestor()).dispose();
//                new DangNhapJFrame().setVisible(true);
                DangNhapJFrame.getLoginFrame().setVisible(true);
            }
        };

        updateLs = (evt) -> {
            NhanVien nv = pnlNhanVien.getForm();
            if (nv != null) {
                if (new NhanVienDAO().update(nv) > 0) {
                    MsgBox.inform("Cập nhật thông tin thành công!");
                    pnlNhanVien.setForm(nv);
                    Auth.user = nv;
                    setUser(nv);
                } else {
                    MsgBox.inform("Cập nhật thông tin thất bại!");
                }
            }
        };

        viewProfileLs = (ActionEvent e) -> {
            if (user != null) {
                pnlNhanVien.setForm(user);
                JDialog dialog = XDialog.getDialog((JFrame) this.getTopLevelAncestor(), pnlNhanVien);
                dialog.setVisible(true);
            }
        };
    }

    public void addMenuEvent(ActionListener al) {
        cmdMenu.addActionListener(al);
    }

    public void setUser(NhanVien nv) {
        if (nv == null) {
            return;
        }
        this.user = nv;
        Icon icon = new ImageIcon(new File(Paths.get("logos", "nhanvien").toFile(), nv.getHinh()).getPath());
        avatar.setIcon(icon);
        lblUsername.setText(nv.getHoTenNV());
        lblVaiTro.setText(nv.getVaiTro());
    }

    private ImageIcon createIcon(String name) {
        return new ImageIcon(getClass().getResource("/ultramotor/icon/" + name));
    }
}
