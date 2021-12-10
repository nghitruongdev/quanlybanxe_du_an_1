package com.ultramotor.ui.hoadon;

import com.microsoft.sqlserver.jdbc.SQLServerDataTable;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import com.swingx.PasswordField;
import com.swingx.PictureBox;
import com.swingx.SearchTextField;
import com.swingx.TextField;
import com.swingx.table.ModelAction;
import com.swingx.table.ModelEvent;
import com.swingx.table.StatusCell;
import com.swingx.table.Table;
import com.ultramotor.dao.HoaDonDAO;
import com.ultramotor.dao.KhachHangDAO;
import com.ultramotor.dao.NhanVienDAO;
import com.ultramotor.dao.SanPhamDAO;
import com.ultramotor.entity.ChiTietHoaDon;
import com.ultramotor.entity.Entity;
import com.ultramotor.entity.HoaDon;
import com.ultramotor.entity.KhachHang;
import com.ultramotor.entity.NhanVien;
import com.ultramotor.entity.SanPham;
import com.ultramotor.ui.hoadon.ThanhVienCard.Membership;
import com.ultramotor.util.Auth;
import com.ultramotor.util.MsgBox;
import com.ultramotor.util.MyVerifier;
import com.ultramotor.util.XCodeHelper;
import com.ultramotor.util.XDate;
import com.ultramotor.util.XDialog;
import com.ultramotor.util.XFile;
import com.ultramotor.util.XImage;
import com.ultramotor.util.XJdbc;
import com.ultramotor.util.XPdf;
import com.ultramotor.util.XReport;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.sql.rowset.CachedRowSet;
import javax.swing.CellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.miginfocom.swing.MigLayout;
import net.sf.jasperreports.engine.JRException;

public class HoaDonPanel extends javax.swing.JPanel {

    private HoaDonDAO hdDAO = new HoaDonDAO();
    private SanPhamDAO spDAO = new SanPhamDAO();
    private KhachHangDAO khDAO = new KhachHangDAO();
    private NhanVienDAO nvDAO = new NhanVienDAO();
    private List<KhachHang> listKH;
    private List<SanPham> listSP;
    private List<NhanVien> listNV;
    private Map<String, String> mapDongSP;
    private Map<HoaDon, KhachHang> mapHD;
    private HoaDon currentHD;
    private SanPham currentSP;

    private ModelEvent chitietEvent;
    private DecimalFormat numberFormat;

    public HoaDonPanel() {
        initComponents();
        init();
    }

    private void init() {
        listKH = khDAO.selectAll();
        listSP = spDAO.selectAll();
        listNV = nvDAO.selectAll();
        initMapHD();
        initMapDongSP();
        numberFormat = new DecimalFormat("#,##0.00");
        initTable();
        addListeners();
        fillTable();
        setFieldNameKH();
    }

    private List<SanPham> getCurrentListSP() {
        if (listSP != null) {
            return listSP.stream().filter(sp
                    -> chkTatCa.isSelected() // lấy tất cả sp
                    || (chkConHang.isSelected() && sp.gettonKho() > 0) //lấy những sản phẩm còn hàng
                    || (chkHetHang.isSelected() && sp.gettonKho() == 0)) //lấy những sản phẩm hết hàng
                    .collect(Collectors.toList());
        }
        return listSP;
    }

    private void addListeners() {
        chitietEvent = new ModelEvent() {
            @Override
            public void update(Object e) {
                SanPham sp = findSanPham(((ChiTietHoaDon) e).getSKU());
                if (Status.getStatus(txtTrangThai.getText()) == Status.CHUA_THANH_TOAN) {
                    deleteCTHD(sp, false);
                }
                setFormSanPham(sp);
            }

            @Override
            public void delete(Object e) {
                if (Status.getStatus(txtTrangThai.getText()) == Status.CHUA_THANH_TOAN) {
                    deleteCTHD(findSanPham(((ChiTietHoaDon) e).getSKU()), true);
                }

            }
        };

        btnNew.addActionListener(e -> {
            if (currentHD != null && Status.getStatus(txtTrangThai.getText()) == Status.CHUA_THANH_TOAN) {
                int confirm = MsgBox.confirm("Đơn hàng hiện tại của bạn chưa lưu, bạn có muốn tiếp tục?", false);
                if (confirm != 0) {
                    return;
                }
            }
            resetCTHD();
            btnResetKH.doClick();
            txtMaHD.setText(getAutoMaHD());
        });

        btnLuu.addActionListener(e -> {
            insertHoaDon(false);
        });

        btnThanhToan.addActionListener(e -> {
            insertHoaDon(true);
        });
        txtMaSP.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String masp = txtMaSP.getText().trim().toUpperCase();
                if (masp.isEmpty()) {
                    resetSP();
                    return;
                }
                SanPham sp = findSanPham(masp);
                if (sp == null) {
                    MsgBox.error("Không tìm thấy sản phẩm");
                    resetSP();
                    txtMaSP.requestFocus();
                    return;
                }
                if (sp.gettonKho() <= 0) {
                    MsgBox.error("Sản phẩm đã hết hàng! Vui lòng chọn sản phẩm khác.");
                    txtMaSP.setText("");
                    txtMaSP.requestFocus();
                    return;
                }
                setFormSanPham(sp);
            }

        });

        tblHoaDon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    setFormHD((HoaDon) tblHoaDon.getValueAt(tblHoaDon.getSelectedRow(), 0));
                }
            }

        });
        txtMaKH.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String idKH = txtMaKH.getText();
                if (!idKH.isEmpty()) {
                    KhachHang kh = findKhachHang(idKH);
                    if (kh != null) {
                        setFormKH(kh);
                    } else {
                        btnResetKH.doClick();
                        txtMaKH.setText(idKH.toUpperCase());
                    }
                }
            }

        });

        btnSearchSP.addActionListener(e -> {
            XDialog.getDialog((JFrame) this.getTopLevelAncestor(), createPanelSP()).setVisible(true);
        });
        btnResetSP.addActionListener(e -> {
            resetSP();
        });
        btnSearchKH.addActionListener(e -> {
            XDialog.getDialog((JFrame) this.getTopLevelAncestor(), createPanelKH()).setVisible(true);
        });

        btnResetKH.addActionListener(e -> {
            resetKH();
        });

        btnQrCode.addActionListener(e -> {
            new Thread(() -> {
                String maKH = "";
                try {
                    maKH = XCodeHelper.webcamReader((JFrame) this.getTopLevelAncestor());
                } catch (Exception ex) {
                    SwingUtilities.invokeLater(() -> {
                        MsgBox.error("Không tìm thấy thông tin khách hàng!");
                    });
                    return;
                }
                KhachHang kh = findKhachHang(maKH);
                if (kh == null) {
                    SwingUtilities.invokeLater(() -> {
                        MsgBox.error("Không tìm thấy thông tin khách hàng!");
                    });
                    return;
                }
                setFormKH(kh);
            }).start();
        });
        btnThemCTHD.addActionListener(e -> {
            insertCTHD();
        });

        btnHuyHD.addActionListener(e -> {
            huyHD();
        });
    }

    private void fillTable() {
//        fillTable(tblSanPham, getCurrentListSP());
        fillTableHDTheoNgay();
//        fillTableHoaDon();
    }

    private void fillTableHDTheoNgay() {
        List<HoaDon> list = mapHD.keySet().stream().filter(hd -> XDate.isTheSameDay(hd.getThoiGian(), new Date())).collect(Collectors.toList());
        fillTable(tblHoaDon, list);
    }

    private void fillTable(Table table, List<? extends Entity> list) {
        if (list == null) {
            return;
        }
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        list.forEach(e -> {
            model.addRow(getInfo(e));
        });
    }

    private void deleteCTHD(SanPham sp, boolean ask) {
        CellEditor editor = tblChiTiet.getCellEditor();
        if (editor != null) {
            editor.stopCellEditing();
        }
        if (ask && MsgBox.confirm("Xoá sản phẩm khỏi đơn hàng?", false) != 0) {
            return;
        }

        int index = tblChiTiet.getSelectedRow();
        if (index >= 0) {
            DefaultTableModel model = (DefaultTableModel) tblChiTiet.getModel();
            model.removeRow(index);
            if (model.getRowCount() == 0) {
                currentHD = null;
            }
            listSP.get(listSP.indexOf(sp)).settonKho(sp.gettonKho() + 1);
        }
    }

    private void resetKH() {
        reset(pnlKhachHang);
        setGiamGia("", currentHD);
        pnlMembership.setThanhvien(null);
        chkThanhVien.setEnabled(true);
        txtMaKH.setEditable(Status.getStatus(txtTrangThai.getText()) != Status.HOAN_TAT);
    }

    private void resetCTHD() {
        reset(pnlChiTietHD);
        JLabel[] txts = {txtMaHD, txtGiamGia, txtThue, txtTongTien, txtTrangThai};
        for (JLabel txt : txts) {
            txt.setText("");
        }
        CellEditor editor = tblChiTiet.getCellEditor();
        if (editor != null) {
            editor.stopCellEditing();
        }
        ((DefaultTableModel) tblChiTiet.getModel()).setRowCount(0);
        currentHD = null;
    }

    void insertRow(Table table, Entity e) {
        insertRow(table, e, -1);
    }

    private void insertRow(Table table, Entity e, int index) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        if (index >= 0) {
            model.removeRow(index);
            model.insertRow(index, getInfo(e));
        } else {
            model.insertRow(0, getInfo(e));
        }
    }

    void setFormHD(HoaDon hd) {
        //kiểm tra đơn hàng hiện tại chưa lưu và đơn hàng này chưa hoàn tất
        if (currentHD != null && Status.getStatus(txtTrangThai.getText()) == Status.CHUA_THANH_TOAN) {
            int confirm = MsgBox.confirm("Bạn chưa lưu hoá đơn hiện tại. Bạn có muốn tiếp tục?", false);
            if (confirm != 0) {
                return;
            }
        }
        currentHD = hd;
        setFormKH(mapHD.get(hd));
        txtMaHD.setText(hd.getIdHD());
        txtTrangThai.setText(hd.getTrangThai());

//        setGiamGia()
//        txtThue.setText(numberFormat.format(hd.getThue()) + " VNĐ");
//        txtTongTien.setText(numberFormat.format(hd.getTongTien() + hd.getThue()) + " VNĐ");
        fillTable(tblChiTiet, hd.getListCTHD());
    }

    private void huyHD() {
        if (currentHD == null) {
            MsgBox.warning("Vui lòng chọn hoá đơn!");
            return;
        }
        if (Status.getStatus(txtTrangThai.getText()) == Status.HOAN_TAT) {
            MsgBox.error("Đơn hàng đã hoàn tất, không thể huỷ đơn hàng!");
            return;
        }
        if (MsgBox.confirm("Bạn có muốn huỷ đơn hàng hiện tại?", false) != 0) {
            return;
        }
        DefaultTableModel model = (DefaultTableModel) tblChiTiet.getModel();
        mapHD.remove(currentHD);
        for (int i = 0; i < model.getRowCount(); i++) {
            ModelAction modelAction = (ModelAction) model.getValueAt(i, tblChiTiet.getColumnCount() - 1);
            SanPham sp = findSanPham(((ChiTietHoaDon) modelAction.getEntity()).getSKU());
            deleteCTHD(sp, false);
        }
        //kiểm tra hoá đơn chưa thanh toán trong bảng hoá đơn theo ngày
        int index = findIndexHD(currentHD);
        if (index >= 0) {
            ((DefaultTableModel) tblHoaDon.getModel()).removeRow(index);
        }
        resetCTHD(); //đặt lại thông tin trên panel chi tiết hoá đơn
        MsgBox.inform("Huỷ đơn hàng thành công!");
        //mapHD.re// xoá hoá đơn khỏi MAP
    }

    private void insertCTHD() {
        if (Status.getStatus(txtTrangThai.getText()) == Status.HOAN_TAT) {
            MsgBox.warning("Đơn hàng đã hoàn tất");
            return;
        }
        if (currentSP == null || !listSP.contains(currentSP)) {
            MsgBox.error("Không tìm thấy sản phẩm");
            return;
        }
        //nếu hoá đơn đã mua, hiển thông tin khách hàng lên lại có được chỉnh sửa không
        //nếu hoá đơn chưa mua, hiển thị thông tin khách hàng như nào
        if (txtMaHD.getText().isEmpty()) {
            txtMaHD.setText(getAutoMaHD());
        }

        if (currentHD == null) {
            currentHD = new HoaDon(txtMaHD.getText());
            txtTrangThai.setText(Status.CHUA_THANH_TOAN.getName());
        }
        ChiTietHoaDon ct = new ChiTietHoaDon(
                tblChiTiet.getRowCount() + 1,
                currentHD.getIdHD(),
                currentSP.getSku()
        );
        ct.setDonGia(currentSP.getGiaTien());

        currentHD.addCTHD(ct);
        setGiamGia(txtGiamGia.getText(), currentHD);
        insertRow(tblChiTiet, ct); //thêm vào bảng hoá đơn chi tiết
        int index = listSP.indexOf(currentSP); //lấy index sản phẩm hiện tại trong danh sách
        listSP.get(index).settonKho(currentSP.gettonKho() - 1); //giảm số lượng tồn kho sp trong listSP
        resetSP();
    }

    @SuppressWarnings("empty-statement")
    private void insertHoaDon(boolean thanhToan) {
        if (currentHD == null) {
            MsgBox.error("Không tìm thấy hoá đơn cần lưu!");
            return;
        }
        if (Status.getStatus(txtTrangThai.getText()) == Status.HOAN_TAT) {
            if (thanhToan) {
                MsgBox.warning("Đơn hàng hiện tại đã được thanh toán!");
                return;
            }
            resetKH();
            resetSP();
            resetCTHD();
            return;
        }
        KhachHang kh = getFormKH();
        if (kh == null) {
//            MsgBox.error("Vui lòng nhập thông tin khách hàng!");
            return;
        }

        currentHD.setThoiGian(new Date());
        currentHD.setIdNV(Auth.user == null ? "NV01" : Auth.user.getIdNV());

        if (thanhToan) {
            if (!checkSoLuongChiTietHD(currentHD.getListCTHD())) {
                MsgBox.error("Không thể thanh toán đơn hàng! Vui lòng kiểm tra lại số lượng sản phẩm.");
                return;
            };
            txtTrangThai.setText(Status.HOAN_TAT.getName());
        }
        currentHD.setTrangThai(txtTrangThai.getText());
        currentHD.setIdKH(kh.getIdKH());

        if (thanhToan) {
            try {
                insertDB(currentHD);
                HoaDon hd = currentHD;
                new Thread(() -> {
                    int confirm = MsgBox.confirm("Đơn hàng đã thanh toán thành công! Bạn có muốn in hoá đơn?", false);
                    if (confirm == 0) {
                        printHD(hd);
                    }
                }).start();
            } catch (SQLException ex) {
                MsgBox.error("Không thể thanh toán hoá đơn! Vui lòng kiểm tra lại dữ liệu.");
                hdDAO.delete(currentHD.getIdHD());
                Logger.getLogger(HoaDonPanel.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }
        }
        mapHD.put(currentHD, kh);
        insertRow(tblHoaDon, currentHD, findIndexHD(currentHD));
        resetCTHD();
        resetSP();
        resetKH();

    }

    private void printHD(HoaDon hd) {
        File file = XFile.getTempFile(null, ".pdf");
        try {
            System.out.println("Printing HoaDon");
            XReport.createHoaDonReport(hd.getIdHD(), file);
            XPdf.printPDF(file);
        } catch (JRException | IOException ex) {
            System.out.println(ex.getMessage());
            MsgBox.error("Không thể xuất hoá đơn! Vui lòng kiểm tra lại hệ thống.");
        } catch (PrinterException ex) {
            if (MsgBox.confirm("Không thể in hoá đơn! Mở hoá đơn bằng ứng dụng thứ ba?", false) == 0) {
                try {
                    Desktop.getDesktop().open(file);
                } catch (IOException ex1) {
                    Logger.getLogger(HoaDonPanel.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }
    }

    private void insertDB(HoaDon hd) throws SQLException {
        SQLServerDataTable table = ChiTietHoaDon.getDataServerTable();
        hd.getListCTHD().forEach(ct -> {
            try {
                table.addRow(new Object[]{ct.getIdCTHD(), ct.getSKU(), ct.getIdHD()});
            } catch (SQLServerException ex) {
                MsgBox.error("Vui lòng kiểm tra lại chi tiết hoá đơn");
            }
        });
        hdDAO.insertWithChiTiet(hd, table);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UIManager.put("Button.disabledText", new ColorUIResource(new Color(250, 250, 250)));
            UIManager.put("ComboBox.disabledText", new ColorUIResource(new Color(109, 109, 109)));
            JFrame fr = new JFrame();
            fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            fr.getContentPane().add(new HoaDonPanel());
            fr.pack();
            fr.setVisible(true);
        });
    }

    private int findIndexHD(HoaDon hd) {
        for (int i = 0; i < tblHoaDon.getRowCount(); i++) {
            if (tblHoaDon.getValueAt(i, 0).equals(hd)) {
                return i;
            }
        }
        return -1;
    }

    private void reset(JPanel... panels) {
        for (JPanel panel : panels) {
            for (Component comp : panel.getComponents()) {
                if (comp instanceof JTextField) {
                    ((JTextField) comp).setText("");
                } else if (comp instanceof JComboBox) {
                    ((JComboBox) comp).setSelectedIndex(0);
                } else if (comp instanceof JCheckBox) {
                    ((JCheckBox) comp).setSelected(false);
                } else if (comp instanceof PictureBox) {
                    ((PictureBox) comp).setImage(null);
                } else if (comp instanceof JPanel) {
                    reset((JPanel) comp);
                }
            }
        }
    }

    private String getAutoMaHD() {
        return String.format("HD%02d", mapHD.size() + 1);
    }

    private boolean checkSoLuongChiTietHD(List<ChiTietHoaDon> list) {
        List<String> skus = new ArrayList<>(list.size());
        list.forEach(ct -> skus.add(ct.getSKU()));
        Map<String, Integer> map = spDAO.checkHangTonSP(skus.toArray(new String[skus.size()]));
        return !map.keySet().stream().anyMatch(key -> map.get(key) <= 0);
    }

    private void initTable() {
        tblChiTiet.fixTable((JScrollPane) tblChiTiet.getParent().getParent());
        tblHoaDon.fixTable((JScrollPane) tblHoaDon.getParent().getParent());

        addRowSorter(tblHoaDon, txtTimKiemHoaDonTheoNgay);
    }

    private void addRowSorter(Table table, JTextField field) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<TableModel> sorter = new TableRowSorter(model);
        table.setRowSorter(sorter);
        field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String text = field.getText().trim();
                if (text.isEmpty()) {
                    sorter.setRowFilter(null);
                } else {
                    List<RowFilter<Object, Object>> filters = new ArrayList<>();
                    filters.add(RowFilter.regexFilter("(?i)" + text));
                    filters.add(RowFilter.regexFilter("(?i)" + text.toUpperCase()));
                    sorter.setRowFilter(RowFilter.orFilter(filters));
                }
            }
        });
    }

    private Object[] getInfo(Entity e) {
        if (e instanceof KhachHang) {
            KhachHang kh = (KhachHang) e;
            return new Object[]{
                kh,
                String.format("%s %s", kh.getHoKH(), kh.getTenKH()),
                XDate.toString(kh.getNgaySinh(), "dd-MM-yyyy"),
                kh.getGioiTinh() ? "Nam" : "Nữ",
                kh.getDiaChi(),
                kh.getEmail(),
                kh.getSdt(),
                kh.getThanhVien() ? "Thành Viên" : "Không phải thành viên",
                kh.getGhiChu()};
        }
        if (e instanceof SanPham) {
            SanPham sp = (SanPham) e;
            return new Object[]{
                sp, sp.getTenSP(), numberFormat.format(sp.getGiaTien()), sp.getMauSac(), sp.getPhanKhoi(),
                sp.getHinh(), sp.gettonKho()
            };
        }
        if (e instanceof HoaDon) {
            HoaDon hd = (HoaDon) e;
            KhachHang kh = findKhachHang(hd.getIdKH());
            NhanVien nv = findNhanVien(hd.getIdNV());
            return new Object[]{
                hd, kh == null ? "" : kh.getHoTenKH(),
                numberFormat.format(hd.getTongTien()),
                nv == null ? "" : nv.getHoTenNV(),
                XDate.toString(hd.getThoiGian(), "hh:mm:ss"),
                new StatusCell(hd.getTrangThai())
            };
        }
        if (e instanceof ChiTietHoaDon) {
            ChiTietHoaDon ct = (ChiTietHoaDon) e;
            return new Object[]{
                ct.getIdCTHD(),
                findSanPham(ct.getSKU()).getTenSP(),
                numberFormat.format(ct.getDonGia()),
                ct.getIdHD(),
                new ModelAction(ct, chitietEvent)
            };
        }
        return null;
    }

    private SanPham findSanPham(String sku) {
        return listSP.stream().filter(sp -> sp.getSku().equals(sku)).findFirst().orElse(null);
    }

    private NhanVien findNhanVien(String idNV) {
        return listNV.stream().filter(nv -> nv.getIdNV().equals(idNV)).findFirst().orElse(null);

    }

    private KhachHang findKhachHang(String idKH) {
        return listKH.stream().filter(kh -> idKH.trim().equalsIgnoreCase(kh.getIdKH())).findFirst().orElse(mapHD.get(currentHD));
    }

    private void initMapHD() {
        mapHD = new HashMap<>();
        hdDAO.selectAll().forEach(hd -> {
            mapHD.put(hd, findKhachHang(hd.getIdKH()));
        });
    }

    private void initMapDongSP() {
        mapDongSP = new HashMap<>();
        try (CachedRowSet rs = XJdbc.query("SELECT dsp.id_DongSP, nsx.tenNSX"
                + " FROM DongSanPham dsp JOIN NhaSanXuat nsx ON dsp.id_NSX = nsx.id_NSX")) {
            while (rs.next()) {
                mapDongSP.put(rs.getString("id_DongSP"), rs.getString("tenNSX"));
            }
        } catch (SQLException ex) {
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        bgrTrangThai = new javax.swing.ButtonGroup();
        chkTatCa = new javax.swing.JCheckBox();
        chkConHang = new javax.swing.JCheckBox();
        chkHetHang = new javax.swing.JCheckBox();
        btnResetSP = new com.swingx.Button();
        pnlHoaDon = new javax.swing.JPanel();
        pnlKhachHang = new javax.swing.JPanel();
        txtHoTenKH = new com.swingx.TextField();
        txtDiaChi = new com.swingx.TextField();
        txtNgaySinh = new com.swingx.TextField();
        txtMaKH = new com.swingx.TextField();
        txtEmail = new com.swingx.TextField();
        txtSDT = new com.swingx.TextField();
        chkThanhVien = new javax.swing.JCheckBox();
        cboGioiTinh = new com.swingx.ComboBoxSuggestion();
        btnSearchKH = new com.swingx.Button();
        lblMaKH = new javax.swing.JLabel();
        lblHoTen = new javax.swing.JLabel();
        lblNgaySinh = new javax.swing.JLabel();
        lblGioiTinh = new javax.swing.JLabel();
        lblDiaChi = new javax.swing.JLabel();
        lblSoDT = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        btnResetKH = new com.swingx.Button();
        pnlMembership = new com.ultramotor.ui.hoadon.ThanhVienCard();
        btnQrCode = new com.swingx.Button();
        pnlTBLHoaDon = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblHoaDon = new com.swingx.table.Table();
        txtTimKiemHoaDonTheoNgay = new com.swingx.SearchTextField();
        pnlChiTietHD = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblChiTiet = new com.swingx.table.Table();
        jPanel1 = new javax.swing.JPanel();
        btnNew = new com.swingx.Button();
        btnLuu = new com.swingx.Button();
        btnThanhToan = new com.swingx.Button();
        btnHuyHD = new com.swingx.Button();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtMaHD = new javax.swing.JLabel();
        lblGiamGia = new javax.swing.JLabel();
        txtGiamGia = new javax.swing.JLabel();
        lblThue = new javax.swing.JLabel();
        txtThue = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JLabel();
        lblTrangThai = new javax.swing.JLabel();
        txtTrangThai = new javax.swing.JLabel();
        pnlInfoSanPham = new javax.swing.JPanel();
        txtMaSP = new com.swingx.TextField();
        txtGiaTien = new com.swingx.TextField();
        txtTenSP = new com.swingx.TextField();
        lblMaSKU = new javax.swing.JLabel();
        lblTenSP = new javax.swing.JLabel();
        lblGiaTien = new javax.swing.JLabel();
        lblDoiXe = new javax.swing.JLabel();
        txtDoiXe = new com.swingx.TextField();
        lblMauSac = new javax.swing.JLabel();
        txtMauSac = new com.swingx.TextField();
        lblNSX = new javax.swing.JLabel();
        txtNSX = new com.swingx.TextField();
        lblDiachiSX = new javax.swing.JLabel();
        txtDiachiSX = new com.swingx.TextField();
        lblBaoHanh = new javax.swing.JLabel();
        txtBaoHanh = new com.swingx.TextField();
        btnThemCTHD = new com.swingx.Button();
        txtPhanKhoi = new com.swingx.TextField();
        lblPhanKhoi = new javax.swing.JLabel();
        lblHinh = new com.swingx.PictureBox();
        btnSearchSP = new com.swingx.Button();

        chkTatCa.setText("Tất Cả");

        chkConHang.setSelected(true);
        chkConHang.setText("Còn Hàng");

        chkHetHang.setText("Hết Hàng");

        btnResetSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ultramotor/icon/broom_25px.png"))); // NOI18N
        btnResetSP.setText("Xoá Form");

        setBackground(new java.awt.Color(245, 245, 245));

        pnlHoaDon.setBackground(new java.awt.Color(245, 245, 245));
        pnlHoaDon.setOpaque(false);

        pnlKhachHang.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "THÔNG TIN KHÁCH HÀNG", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 153, 255))); // NOI18N
        pnlKhachHang.setOpaque(false);

        txtHoTenKH.setErrorColor(new java.awt.Color(255, 255, 255));
        txtHoTenKH.setLabelText("Họ tên khách hàng");
        txtHoTenKH.setOnlyField(true);

        txtDiaChi.setToolTipText("");
        txtDiaChi.setErrorColor(new java.awt.Color(255, 255, 255));
        txtDiaChi.setLabelText("Địa chỉ");
        txtDiaChi.setOnlyField(true);

        txtNgaySinh.setErrorColor(new java.awt.Color(255, 255, 255));
        txtNgaySinh.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd-MM-yyyy"))));
        txtNgaySinh.setLabelText("Số điện thoại");
        txtNgaySinh.setOnlyField(true);
        txtNgaySinh.setPlaceholder("DD-MM-YYYY");

        txtMaKH.setErrorColor(new java.awt.Color(255, 255, 255));
        txtMaKH.setLabelText("Mã khách hàng");
        txtMaKH.setOnlyField(true);

        txtEmail.setErrorColor(new java.awt.Color(255, 255, 255));
        txtEmail.setLabelText("Số điện thoại");
        txtEmail.setOnlyField(true);

        txtSDT.setErrorColor(new java.awt.Color(255, 255, 255));
        txtSDT.setLabelText("Số điện thoại");
        txtSDT.setOnlyField(true);

        chkThanhVien.setText("Đăng ký thành viên");
        chkThanhVien.setOpaque(false);

        cboGioiTinh.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nam", "Nữ" }));

        btnSearchKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ultramotor/icon/search_client_25px.png"))); // NOI18N

        lblMaKH.setText("Mã Khách Hàng");
        lblMaKH.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));

        lblHoTen.setText("Họ Tên ");
        lblHoTen.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));

        lblNgaySinh.setText("Ngày Sinh");
        lblNgaySinh.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));

        lblGioiTinh.setText("Giới Tính");
        lblGioiTinh.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));

        lblDiaChi.setText("Địa chỉ");
        lblDiaChi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));

        lblSoDT.setText("Số Điện Thoại");
        lblSoDT.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));

        lblEmail.setText("Email");
        lblEmail.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));

        btnResetKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ultramotor/icon/broom_25px.png"))); // NOI18N
        btnResetKH.setText("Xoá Form");

        pnlMembership.setBackground(new java.awt.Color(204, 204, 204));
        pnlMembership.setColorGradient(new java.awt.Color(102, 102, 102));

        btnQrCode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ultramotor/icon/qr_code_20px.png"))); // NOI18N

        javax.swing.GroupLayout pnlKhachHangLayout = new javax.swing.GroupLayout(pnlKhachHang);
        pnlKhachHang.setLayout(pnlKhachHangLayout);
        pnlKhachHangLayout.setHorizontalGroup(
            pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKhachHangLayout.createSequentialGroup()
                .addGroup(pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlKhachHangLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cboGioiTinh, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                            .addComponent(txtHoTenKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblHoTen)
                            .addComponent(txtNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblNgaySinh)
                            .addComponent(lblGioiTinh)
                            .addComponent(lblMaKH)
                            .addComponent(lblDiaChi)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMaKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlKhachHangLayout.createSequentialGroup()
                        .addContainerGap(73, Short.MAX_VALUE)
                        .addComponent(btnSearchKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnQrCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)))
                .addGroup(pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlMembership, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlKhachHangLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnResetKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(chkThanhVien))
                            .addComponent(lblEmail, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSDT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSoDT, javax.swing.GroupLayout.Alignment.LEADING))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlKhachHangLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnQrCode, btnSearchKH});

        pnlKhachHangLayout.setVerticalGroup(
            pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKhachHangLayout.createSequentialGroup()
                .addGroup(pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlKhachHangLayout.createSequentialGroup()
                        .addGroup(pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlKhachHangLayout.createSequentialGroup()
                                .addComponent(btnQrCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addComponent(lblMaKH))
                            .addComponent(btnSearchKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlMembership, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE))
                .addGap(14, 14, 14)
                .addGroup(pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlKhachHangLayout.createSequentialGroup()
                        .addGroup(pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblHoTen)
                            .addComponent(lblSoDT))
                        .addGap(2, 2, 2)
                        .addComponent(txtHoTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlKhachHangLayout.createSequentialGroup()
                        .addGroup(pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNgaySinh)
                            .addComponent(lblEmail))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlKhachHangLayout.createSequentialGroup()
                        .addComponent(lblGioiTinh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDiaChi))
                    .addGroup(pnlKhachHangLayout.createSequentialGroup()
                        .addComponent(chkThanhVien)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnResetKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlKhachHangLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cboGioiTinh, txtSDT});

        pnlKhachHangLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnQrCode, btnSearchKH});

        pnlTBLHoaDon.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 153, 255))); // NOI18N
        pnlTBLHoaDon.setOpaque(false);

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hóa đơn", "Tên Khách Hàng", "Tổng Tiền", "Tên Nhân Viên", "Thời Gian", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(tblHoaDon);

        javax.swing.GroupLayout pnlTBLHoaDonLayout = new javax.swing.GroupLayout(pnlTBLHoaDon);
        pnlTBLHoaDon.setLayout(pnlTBLHoaDonLayout);
        pnlTBLHoaDonLayout.setHorizontalGroup(
            pnlTBLHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTBLHoaDonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTBLHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTBLHoaDonLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtTimKiemHoaDonTheoNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlTBLHoaDonLayout.setVerticalGroup(
            pnlTBLHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTBLHoaDonLayout.createSequentialGroup()
                .addComponent(txtTimKiemHoaDonTheoNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlChiTietHD.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "HOÁ ĐƠN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 153, 255))); // NOI18N
        pnlChiTietHD.setOpaque(false);

        tblChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã SKU", "Đơn giá", "Mã Hoá Đơn", "Actions"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblChiTiet.getTableHeader().setReorderingAllowed(false);
        jScrollPane6.setViewportView(tblChiTiet);
        if (tblChiTiet.getColumnModel().getColumnCount() > 0) {
            tblChiTiet.getColumnModel().getColumn(3).setResizable(false);
            tblChiTiet.getColumnModel().getColumn(4).setResizable(false);
            tblChiTiet.getColumnModel().getColumn(4).setPreferredWidth(100);
        }

        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 1, 1, 1));
        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.GridLayout(2, 1, 10, 10));

        btnNew.setBackground(new java.awt.Color(113, 118, 145));
        btnNew.setForeground(new java.awt.Color(255, 255, 255));
        btnNew.setText("Tạo mới");
        btnNew.setBorderPainted(false);
        btnNew.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jPanel1.add(btnNew);

        btnLuu.setBackground(new java.awt.Color(113, 118, 145));
        btnLuu.setForeground(new java.awt.Color(255, 255, 255));
        btnLuu.setText("Lưu");
        btnLuu.setBorderPainted(false);
        btnLuu.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jPanel1.add(btnLuu);

        btnThanhToan.setBackground(new java.awt.Color(113, 118, 145));
        btnThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.setBorderPainted(false);
        btnThanhToan.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jPanel1.add(btnThanhToan);

        btnHuyHD.setBackground(new java.awt.Color(113, 118, 145));
        btnHuyHD.setForeground(new java.awt.Color(255, 255, 255));
        btnHuyHD.setText("Huỷ Đơn Hàng");
        btnHuyHD.setBorderPainted(false);
        btnHuyHD.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jPanel1.add(btnHuyHD);

        jPanel2.setOpaque(false);
        java.awt.GridBagLayout jPanel2Layout = new java.awt.GridBagLayout();
        jPanel2Layout.columnWidths = new int[] {0, 20, 0};
        jPanel2Layout.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0};
        jPanel2.setLayout(jPanel2Layout);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Mã HĐ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        jPanel2.add(jLabel1, gridBagConstraints);

        txtMaHD.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(txtMaHD, gridBagConstraints);

        lblGiamGia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblGiamGia.setText("Giảm Giá");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        jPanel2.add(lblGiamGia, gridBagConstraints);

        txtGiamGia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 105;
        jPanel2.add(txtGiamGia, gridBagConstraints);

        lblThue.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblThue.setText("Thuế VAT");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        jPanel2.add(lblThue, gridBagConstraints);

        txtThue.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 1;
        jPanel2.add(txtThue, gridBagConstraints);

        lblTongTien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTongTien.setText("Tổng Tiền");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        jPanel2.add(lblTongTien, gridBagConstraints);

        txtTongTien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(txtTongTien, gridBagConstraints);

        lblTrangThai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTrangThai.setText("Trạng Thái");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        jPanel2.add(lblTrangThai, gridBagConstraints);

        txtTrangThai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(txtTrangThai, gridBagConstraints);

        javax.swing.GroupLayout pnlChiTietHDLayout = new javax.swing.GroupLayout(pnlChiTietHD);
        pnlChiTietHD.setLayout(pnlChiTietHDLayout);
        pnlChiTietHDLayout.setHorizontalGroup(
            pnlChiTietHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChiTietHDLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlChiTietHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlChiTietHDLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        pnlChiTietHDLayout.setVerticalGroup(
            pnlChiTietHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChiTietHDLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlChiTietHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                    .addGroup(pnlChiTietHDLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pnlInfoSanPham.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "THÔNG TIN SẢN PHẨM", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 153, 255))); // NOI18N
        pnlInfoSanPham.setOpaque(false);

        txtMaSP.setToolTipText("");
        txtMaSP.setLabelText("Mã sản phẩm");
        txtMaSP.setOnlyField(true);

        txtGiaTien.setEditable(false);
        txtGiaTien.setLabelText("Giá bán");
        txtGiaTien.setOnlyField(true);

        txtTenSP.setEditable(false);
        txtTenSP.setLabelText("Tên sản phẩm");
        txtTenSP.setOnlyField(true);

        lblMaSKU.setText("Mã Sản Phẩm");

        lblTenSP.setText("Tên Sản Phẩm");

        lblGiaTien.setText("Giá Tiền");

        lblDoiXe.setText("Đời Xe");

        txtDoiXe.setEditable(false);
        txtDoiXe.setLabelText("Giá bán");
        txtDoiXe.setOnlyField(true);

        lblMauSac.setText("Màu Sắc");

        txtMauSac.setEditable(false);
        txtMauSac.setLabelText("Giá bán");
        txtMauSac.setOnlyField(true);

        lblNSX.setText("Hãng Sản Xuất");

        txtNSX.setEditable(false);
        txtNSX.setLabelText("Giá bán");
        txtNSX.setOnlyField(true);

        lblDiachiSX.setText("Địa Chỉ Sản Xuất");

        txtDiachiSX.setEditable(false);
        txtDiachiSX.setLabelText("Giá bán");
        txtDiachiSX.setOnlyField(true);

        lblBaoHanh.setText("Bảo Hành");

        txtBaoHanh.setEditable(false);
        txtBaoHanh.setLabelText("Giá bán");
        txtBaoHanh.setOnlyField(true);

        btnThemCTHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ultramotor/icon/add_25px.png"))); // NOI18N
        btnThemCTHD.setText("Thêm");

        txtPhanKhoi.setEditable(false);
        txtPhanKhoi.setLabelText("Giá bán");
        txtPhanKhoi.setOnlyField(true);

        lblPhanKhoi.setText("Phân Khối");

        lblHinh.setBorder(javax.swing.BorderFactory.createTitledBorder("Hình"));

        btnSearchSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ultramotor/icon/search_property_25px.png"))); // NOI18N

        javax.swing.GroupLayout pnlInfoSanPhamLayout = new javax.swing.GroupLayout(pnlInfoSanPham);
        pnlInfoSanPham.setLayout(pnlInfoSanPhamLayout);
        pnlInfoSanPhamLayout.setHorizontalGroup(
            pnlInfoSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfoSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInfoSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInfoSanPhamLayout.createSequentialGroup()
                        .addGroup(pnlInfoSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlInfoSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblMaSKU)
                                .addComponent(lblTenSP)
                                .addComponent(txtGiaTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTenSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(pnlInfoSanPhamLayout.createSequentialGroup()
                                    .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnSearchSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lblGiaTien))
                        .addGap(38, 38, 38)
                        .addComponent(lblHinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlInfoSanPhamLayout.createSequentialGroup()
                        .addGroup(pnlInfoSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNSX, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNSX))
                        .addGap(18, 18, 18)
                        .addGroup(pnlInfoSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDoiXe)
                            .addComponent(txtDoiXe, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(pnlInfoSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPhanKhoi)
                            .addComponent(txtPhanKhoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInfoSanPhamLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlInfoSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDiachiSX, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDiachiSX))
                        .addGap(18, 18, 18)
                        .addGroup(pnlInfoSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMauSac)
                            .addComponent(txtMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlInfoSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBaoHanh, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblBaoHanh, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlInfoSanPhamLayout.createSequentialGroup()
                        .addGap(137, 137, 137)
                        .addComponent(btnThemCTHD, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlInfoSanPhamLayout.setVerticalGroup(
            pnlInfoSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfoSanPhamLayout.createSequentialGroup()
                .addGroup(pnlInfoSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInfoSanPhamLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(btnSearchSP, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlInfoSanPhamLayout.createSequentialGroup()
                        .addGroup(pnlInfoSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlInfoSanPhamLayout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(lblMaSKU)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(lblTenSP)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblGiaTien, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblHinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtGiaTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInfoSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInfoSanPhamLayout.createSequentialGroup()
                        .addGroup(pnlInfoSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblDoiXe)
                            .addComponent(lblNSX, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlInfoSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDoiXe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNSX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInfoSanPhamLayout.createSequentialGroup()
                        .addComponent(lblPhanKhoi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPhanKhoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInfoSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInfoSanPhamLayout.createSequentialGroup()
                        .addComponent(lblMauSac)
                        .addGap(40, 40, 40))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInfoSanPhamLayout.createSequentialGroup()
                        .addGroup(pnlInfoSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblDiachiSX)
                            .addComponent(lblBaoHanh))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlInfoSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDiachiSX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBaoHanh, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThemCTHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlHoaDonLayout = new javax.swing.GroupLayout(pnlHoaDon);
        pnlHoaDon.setLayout(pnlHoaDonLayout);
        pnlHoaDonLayout.setHorizontalGroup(
            pnlHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHoaDonLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTBLHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlHoaDonLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(pnlKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pnlInfoSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pnlChiTietHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlHoaDonLayout.setVerticalGroup(
            pnlHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHoaDonLayout.createSequentialGroup()
                .addGroup(pnlHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlInfoSanPham, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlChiTietHD, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlKhachHang, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlTBLHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(pnlHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(pnlHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(21, 21, 21))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgrTrangThai;
    private com.swingx.Button btnHuyHD;
    private com.swingx.Button btnLuu;
    private com.swingx.Button btnNew;
    private com.swingx.Button btnQrCode;
    private com.swingx.Button btnResetKH;
    private com.swingx.Button btnResetSP;
    private com.swingx.Button btnSearchKH;
    private com.swingx.Button btnSearchSP;
    private com.swingx.Button btnThanhToan;
    private com.swingx.Button btnThemCTHD;
    private com.swingx.ComboBoxSuggestion cboGioiTinh;
    private javax.swing.JCheckBox chkConHang;
    private javax.swing.JCheckBox chkHetHang;
    private javax.swing.JCheckBox chkTatCa;
    private javax.swing.JCheckBox chkThanhVien;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JLabel lblBaoHanh;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblDiachiSX;
    private javax.swing.JLabel lblDoiXe;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblGiaTien;
    private javax.swing.JLabel lblGiamGia;
    private javax.swing.JLabel lblGioiTinh;
    private com.swingx.PictureBox lblHinh;
    private javax.swing.JLabel lblHoTen;
    private javax.swing.JLabel lblMaKH;
    private javax.swing.JLabel lblMaSKU;
    private javax.swing.JLabel lblMauSac;
    private javax.swing.JLabel lblNSX;
    private javax.swing.JLabel lblNgaySinh;
    private javax.swing.JLabel lblPhanKhoi;
    private javax.swing.JLabel lblSoDT;
    private javax.swing.JLabel lblTenSP;
    private javax.swing.JLabel lblThue;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JLabel lblTrangThai;
    private javax.swing.JPanel pnlChiTietHD;
    private javax.swing.JPanel pnlHoaDon;
    private javax.swing.JPanel pnlInfoSanPham;
    private javax.swing.JPanel pnlKhachHang;
    private com.ultramotor.ui.hoadon.ThanhVienCard pnlMembership;
    private javax.swing.JPanel pnlTBLHoaDon;
    private com.swingx.table.Table tblChiTiet;
    private com.swingx.table.Table tblHoaDon;
    private com.swingx.TextField txtBaoHanh;
    private com.swingx.TextField txtDiaChi;
    private com.swingx.TextField txtDiachiSX;
    private com.swingx.TextField txtDoiXe;
    private com.swingx.TextField txtEmail;
    private com.swingx.TextField txtGiaTien;
    private javax.swing.JLabel txtGiamGia;
    private com.swingx.TextField txtHoTenKH;
    private javax.swing.JLabel txtMaHD;
    private com.swingx.TextField txtMaKH;
    private com.swingx.TextField txtMaSP;
    private com.swingx.TextField txtMauSac;
    private com.swingx.TextField txtNSX;
    private com.swingx.TextField txtNgaySinh;
    private com.swingx.TextField txtPhanKhoi;
    private com.swingx.TextField txtSDT;
    private com.swingx.TextField txtTenSP;
    private javax.swing.JLabel txtThue;
    private com.swingx.SearchTextField txtTimKiemHoaDonTheoNgay;
    private javax.swing.JLabel txtTongTien;
    private javax.swing.JLabel txtTrangThai;
    // End of variables declaration//GEN-END:variables

    private JPanel createPanelKH() {
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout("insets 20, fillx", "[center]"));
        panel.setSize(1000, 500);
        panel.setBackground(new Color(250, 250, 250));
        String[] column = {"ID_Khách Hàng", "Họ Tên", "Ngày Sinh", "Giới Tính", "Địa Chỉ", "Email", "Số Điện Thoại", "Hạng Thành Viên", "Ghi chú"};
        DefaultTableModel model = new DefaultTableModel(column, 0);
        Table table = new Table();
        table.setModel(model);
        table.createDefaultColumnsFromModel();
        fillTable(table, listKH);
        JScrollPane pane = new JScrollPane();
        pane.add(table);
        pane.setBackground(new Color(250, 250, 250));
        SearchTextField field = new SearchTextField();

        addRowSorter(table, field);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = table.getSelectedRow();
                    System.out.println(row);
                    if (row >= 0) {
                        setFormKH((KhachHang) table.getValueAt(row, 0));
                        ((JDialog) panel.getTopLevelAncestor()).dispose();
                    }
                }
            }
        });
        table.fixTable(pane);
        panel.add(field, "w 200!, right, pushx, wrap");
        panel.add(pane, "pushx, w 100%, pushy");

        return panel;
    }

    private void setFormKH(KhachHang kh) {
        if (kh == null) {
            System.out.println("KhachHang is null");
            return;
        }
        txtMaKH.setText(kh.getIdKH());
        txtHoTenKH.setText(kh.getHoTenKH());
        txtNgaySinh.setText(XDate.toString(kh.getNgaySinh(), "dd-MM-yyyy"));
        cboGioiTinh.setSelectedIndex(kh.getGioiTinh() ? 0 : 1);
        txtDiaChi.setText(kh.getDiaChi());
        txtSDT.setText(kh.getSdt());
        txtEmail.setText(kh.getEmail());
        chkThanhVien.setSelected(kh.getThanhVien());
        chkThanhVien.setEnabled(!kh.getThanhVien());
        if (kh.getThanhVien()) {
            pnlMembership.setThanhvien(kh);
            setGiamGia(pnlMembership.getRank());
        }
        txtMaKH.setEditable(Status.getStatus(txtTrangThai.getText()) != Status.HOAN_TAT);
    }

    private void setGiamGia(Membership mb) {
        String text = "";
        switch (mb) {
            case NORMAL:
                text = "2%";
                break;
            case SILVER:
                text = "3%";
                break;
            case GOLD:
                text = "5%";
                break;
        }
        setGiamGia(text, currentHD);
    }

    private void setGiamGia(String discount, HoaDon hd) {
        txtGiamGia.setText(discount);
        if (hd == null) {
            return;
        }
        hd.setGiamGia(discount.isEmpty() ? 0 : Integer.parseInt(discount.replace("%", "")));
        setTongTienHD(hd);
    }

    private void setTongTienHD(HoaDon hd) {
        txtThue.setText(numberFormat.format(hd.getThue()) + " VNĐ");
        txtTongTien.setText(numberFormat.format(hd.getTongTien() + hd.getThue()) + " VNĐ");
    }

    private KhachHang getFormKH() {
        if (validateKH()) {
            try {
                String hoten = txtHoTenKH.getText().trim();
                int index = hoten.indexOf(" ");
                String ho = index == -1 ? "" : hoten.substring(0, index);
                String ten = index == -1 ? hoten : hoten.substring(index + 1);
                return new KhachHang(
                        txtMaKH.getText(),
                        ho,
                        ten,
                        cboGioiTinh.getSelectedIndex() == 0,
                        XDate.parse(txtNgaySinh.getText(), "dd-MM-yyyy"),
                        txtDiaChi.getText(),
                        txtSDT.getText(),
                        txtEmail.getText(),
                        chkThanhVien.isSelected(),
                        "",
                        Auth.user == null ? "NV01" : Auth.user.getIdNV()
                );
            } catch (Exception ex) {
                Logger.getLogger(HoaDonPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    private void setFieldNameKH() {
        txtMaKH.setName("Mã KH");
        txtHoTenKH.setName("Họ tên khách hàng");
        txtSDT.setName("Số điện thoại");
        txtNgaySinh.setName("Ngày sinh");
        txtEmail.setName("Email");
    }

    private boolean validateField(JTextField... fields) {
        for (JTextField field : fields) {
            if (!MyVerifier.KHACH_HANG_VERIFIER.verify(field)) {
                String err = "Vui lòng kiểm tra lại dữ liệu";
                if (field instanceof TextField) {
                    err = ((TextField) field).getErrorText();
                } else if (field instanceof PasswordField) {
                    err = ((TextField) field).getErrorText();
                }
                MsgBox.error(err);
                return false;
            }
        }
        return true;
    }

    private boolean validateKH() {
        if (!validateField(txtMaKH, txtHoTenKH, txtSDT, txtNgaySinh, txtEmail)) {
            return false;
        }
        return true;
    }

    private JPanel createPanelSP() {
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout("insets 20, fillx", "[center]"));
        panel.setSize(1000, 500);
        panel.setBackground(new Color(250, 250, 250));
        String[] column = {"Mã Sản Phẩm", "Tên Sản Phẩm", "Giá Tiền", "Màu Sắc", "Phân Khối", "Hình", "Tồn Kho", "Dòng Sản Phẩm", "Hãng Sản Xuất"};
        DefaultTableModel model = new DefaultTableModel(column, 0);
        Table table = new Table();
        table.setModel(model);
        table.createDefaultColumnsFromModel();
        fillTable(table, getCurrentListSP());
        JScrollPane pane = new JScrollPane();
        pane.add(table);
        pane.setBackground(new Color(250, 250, 250));
        SearchTextField field = new SearchTextField();

        addRowSorter(table, field);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = table.getSelectedRow();
                    System.out.println(row);
                    if (row >= 0) {
                        SanPham sp = (SanPham) table.getValueAt(row, 0);
                        if (sp.gettonKho() <= 0) {
                            MsgBox.error("Sản phẩm đã hết hàng! Chọn sản phẩm khác");
                            return;
                        }
                        setFormSanPham(sp);
                        ((JDialog) panel.getTopLevelAncestor()).dispose();

                    }
                }
            }
        });
        table.fixTable(pane);

        chkTatCa.setOpaque(false);
        chkConHang.setOpaque(false);
        chkHetHang.setOpaque(false);

        panel.add(chkTatCa, "split 4, right");
        panel.add(chkConHang);
        panel.add(chkHetHang);
        panel.add(field, "w 200!, right, pushx, wrap");
        panel.add(pane, "pushx, w 100%, pushy");

        chkTatCa.addActionListener((e) -> {
            chkConHang.setSelected(true);
            chkHetHang.setSelected(chkTatCa.isSelected());
            fillTable(table, getCurrentListSP());
        });

        chkConHang.addActionListener(e -> {
            chkTatCa.setSelected(chkConHang.isSelected() && chkHetHang.isSelected());
            fillTable(table, getCurrentListSP());
        });

        chkHetHang.addActionListener(e -> {
            chkTatCa.setSelected(chkConHang.isSelected() && chkHetHang.isSelected());
            fillTable(table, getCurrentListSP());
        });
        return panel;
    }

    void setFormSanPham(SanPham sp) {
        if (sp == null) {
            return;
        }
        if (sp.gettonKho() <= 0) {
            return;
        }
        txtMaSP.setText(sp.getSku());
        txtTenSP.setText(sp.getTenSP());
        txtGiaTien.setText(numberFormat.format(sp.getGiaTien()));
        txtNSX.setText(mapDongSP.getOrDefault(sp.getIdDongSP(), ""));
        txtDiachiSX.setText(sp.getDiaChiSX());
        txtDoiXe.setText(String.format("%d", sp.getDoiXe()));
        txtMauSac.setText(sp.getMauSac());
        txtPhanKhoi.setText(sp.getPhanKhoi());
        txtBaoHanh.setText(String.format("%s tháng", sp.getThoiGianBH()));
        if (sp.getHinh() != null) {
            File parent = Paths.get("logos", "sp").toFile();
            XImage.setIcon(new File(parent, sp.getHinh()), lblHinh, new File(parent, "default.png"));
        }
        currentSP = sp;
    }

    private void resetSP() {
        reset(pnlInfoSanPham);
        currentSP = null;
    }

    private enum Status {
        CHUA_THANH_TOAN("CHƯA THANH TOÁN"),
        HOAN_TAT("HOÀN TẤT");

        private String name;

        private Status(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public static Status getStatus(String name) {
            for (Status value : values()) {
                if (value.getName().equalsIgnoreCase(name)) {
                    return value;
                }
            }
            return null;
        }
    }
}
