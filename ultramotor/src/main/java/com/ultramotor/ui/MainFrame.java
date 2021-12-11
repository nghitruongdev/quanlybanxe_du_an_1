package com.ultramotor.ui;

import com.swingx.model.ModelMenu;
import com.ultramotor.entity.NhanVien;
import com.ultramotor.entity.TruongPhong;
import com.ultramotor.ui.hoadon.HoaDonListPanel;
import com.ultramotor.ui.hoadon.HoaDonPanel;
import com.ultramotor.ui.login.DangNhapJFrame;
import com.ultramotor.ui.nhanvien.NhanVienPanel;
import com.ultramotor.ui.nhanvien.kho.nhapkho.BarcodePanel;
import com.ultramotor.ui.nhanvien.kho.nhapkho.NhapKhoPanel;
import com.ultramotor.ui.quanlykhachhang.KhachHangPanel;
import com.ultramotor.ui.sanPham.QuanLySanPhamPanel;
import com.ultramotor.ui.thongke.ThongKePanel;
import com.ultramotor.util.Auth;
import com.ultramotor.util.MsgBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.nio.file.Paths;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MainFrame extends javax.swing.JFrame {

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JLayeredPane();
        pnlMain = new com.ultramotor.component.MainForm();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bg.setBackground(new java.awt.Color(245, 245, 245));
        bg.setOpaque(true);

        bg.setLayer(pnlMain, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, 1054, Short.MAX_VALUE)
                .addGap(1, 1, 1))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane bg;
    private com.ultramotor.component.MainForm pnlMain;
    // End of variables declaration//GEN-END:variables
    private NhanVienPanel pnlNhanVien;
    private KhachHangPanel pnlKhachHang;
    private QuanLySanPhamPanel pnlSanPham;
    private NhapKhoPanel pnlNhapKho;
    private BarcodePanel pnlBarcode;
    private HoaDonPanel pnlHoaDon;
    private ThongKePanel pnlThongKe;
    private DangNhapJFrame dangNhap;
    private HoaDonListPanel pnlListHoaDon;

    private MainFrame() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/ultramotor/icon/logo_50px.png")).getImage());
        init();
    }

    private static MainFrame mainFrame = new MainFrame();

    public static MainFrame getFrame() {
        return mainFrame;
    }

    private void init() {
        pnlNhanVien = new NhanVienPanel();
        pnlKhachHang = new KhachHangPanel();
        pnlSanPham = new QuanLySanPhamPanel();
        pnlNhapKho = new NhapKhoPanel();
        pnlBarcode = new BarcodePanel();
        pnlHoaDon = new HoaDonPanel();
        pnlThongKe = new ThongKePanel();
        dangNhap = DangNhapJFrame.getLoginFrame();
        pnlListHoaDon = new HoaDonListPanel();
        setLocationRelativeTo(null);
        addListener();
    }

    private void addListener() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                NhanVien user = Auth.user;
                if (user == null) {
                    MsgBox.error("Vui lòng đăng nhập để sử dụng ứng dụng");
                    MainFrame.this.dispose();
                    dangNhap.setVisible(true);
                    return;
                }

                pnlMain.setUser(user);
                addMenus(user);
            }

        });
    }

    private void addMenus(NhanVien user) {
        ModelMenu mnuThongKe = new ModelMenu("Thống kê", createIcon("refresh_25px.png"), getEvent(pnlThongKe));
        ModelMenu mnuNhanVien = new ModelMenu("Quản lý nhân viên", createIcon("refresh_25px.png"), getEvent(pnlNhanVien));
        ModelMenu mnuHoaDon = new ModelMenu("Tạo đơn hàng", createIcon("refresh_25px.png"), getEvent(pnlHoaDon));
        ModelMenu mnuListHoaDon = new ModelMenu("Quản lý hoá đơn", createIcon("refresh_25px.png"), getEvent(pnlListHoaDon));
        ModelMenu mnuKhachHang = new ModelMenu("Quản lý khách hàng", createIcon("refresh_25px.png"), getEvent(pnlKhachHang));
        ModelMenu mnuSanPham = new ModelMenu("Quản lý sản phẩm", createIcon("refresh_25px.png"), getEvent(pnlSanPham));
        ModelMenu mnuNhapKho = new ModelMenu("Quản lý kho", createIcon("refresh_25px.png"), getEvent(pnlNhapKho));
        ModelMenu mnuBarcode = new ModelMenu("In barcode", createIcon("refresh_25px.png"), getEvent(pnlBarcode));
        ModelMenu[] models = null;

        if (user instanceof TruongPhong) {
            models = new ModelMenu[]{mnuNhanVien, mnuHoaDon, mnuListHoaDon, mnuKhachHang, mnuSanPham, mnuNhapKho, mnuBarcode, mnuThongKe};
        } else if (user instanceof NhanVien) {
            models = new ModelMenu[]{mnuHoaDon, mnuListHoaDon, mnuKhachHang, mnuSanPham, mnuNhapKho, mnuBarcode, mnuThongKe};
        }
        pnlMain.addMenu(models);
    }

    private ActionListener getEvent(JPanel panel) {
        return (ActionEvent e) -> {
            showForm(panel);
        };
    }

    private void showForm(JPanel panel) {
        pnlMain.showForm(panel);
    }

    private ImageIcon createIcon(String name) {
        File iconPath = Paths.get("src", "main", "resources", "ultramotor", "icon").toFile();
        return new ImageIcon(new File(iconPath, name).getPath());
    }
}
