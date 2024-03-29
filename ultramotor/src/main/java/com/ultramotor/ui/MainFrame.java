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
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class MainFrame extends javax.swing.JFrame {

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JLayeredPane();
        pnlMain = new com.ultramotor.component.MainForm();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setOpaque(true);

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));

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
    ModelMenu mnuThongKe;
    ModelMenu mnuNhanVien;
    ModelMenu mnuHoaDon;
    ModelMenu mnuListHoaDon;
    ModelMenu mnuKhachHang;
    ModelMenu mnuSanPham;
    ModelMenu mnuNhapKho;
    ModelMenu mnuBarcode;

    private MainFrame() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/ultramotor/icon/logo_50px.png")).getImage());
        init();
    }

    private static final MainFrame mainFrame = new MainFrame();

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

        addForm();
        mnuThongKe = new ModelMenu("Thống kê", createIcon("report.png"), getEvent(pnlThongKe));
        mnuNhanVien = new ModelMenu("Quản lý nhân viên", createIcon("staff.png"), getEvent(pnlNhanVien));
        mnuHoaDon = new ModelMenu("Tạo đơn hàng", createIcon("receipt_25px.png"), getEvent(pnlHoaDon));
        mnuListHoaDon = new ModelMenu("Quản lý hoá đơn", createIcon("invoice.png"), getEvent(pnlListHoaDon));
        mnuKhachHang = new ModelMenu("Quản lý khách hàng", createIcon("customer.png"), getEvent(pnlKhachHang));
        mnuSanPham = new ModelMenu("Quản lý sản phẩm", createIcon("product.png"), getEvent(pnlSanPham));
        mnuNhapKho = new ModelMenu("Quản lý kho", createIcon("kho.png"), getEvent(pnlNhapKho));
        mnuBarcode = new ModelMenu("In barcode", createIcon("barcode.png"), getEvent(pnlBarcode));

        setLocationRelativeTo(null);
        setTitle("Hệ Thống Quản Lý Bán Xe Ultramotor");
        addListener();
    }

    private void addListener() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                MainFrame.this.setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
        });
    }

    public void setVisible() {
        NhanVien user = Auth.user;
        if (user == null) {
            MsgBox.error("Vui lòng đăng nhập để sử dụng ứng dụng");
            MainFrame.this.dispose();
            dangNhap.setVisible(true);
            return;
        }
        pnlMain.setUser(user);
        addMenus(user);
        setVisible(true);
    }

    private void addMenus(NhanVien user) {

        ModelMenu[] models = null;

        if (user instanceof TruongPhong) {
            models = new ModelMenu[]{mnuNhanVien, mnuHoaDon, mnuListHoaDon, mnuKhachHang, mnuSanPham, mnuNhapKho, mnuBarcode, mnuThongKe};
            showForm(pnlNhanVien);
        } else if (user instanceof NhanVien) {
            models = new ModelMenu[]{mnuHoaDon, mnuListHoaDon, mnuKhachHang, mnuSanPham, mnuNhapKho, mnuBarcode, mnuThongKe};
            showForm(pnlHoaDon);
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

    private void addForm() {
        Map<Component, String> map = new HashMap<>();
        map.put(pnlNhanVien, "NhanVien");
        map.put(pnlKhachHang, "KhachHang");
        map.put(pnlSanPham, "SanPham");
        map.put(pnlNhapKho, "NhapKho");
        map.put(pnlBarcode, "Barcode");
        map.put(pnlHoaDon, "HoaDon");
        map.put(pnlThongKe, "ThongKe");
        map.put(pnlListHoaDon, "ListHoaDon");
        pnlMain.addForm(map);
    }

    private ImageIcon createIcon(String name) {
        return new ImageIcon(getClass().getResource("/ultramotor/icon/" + name));
    }
}
