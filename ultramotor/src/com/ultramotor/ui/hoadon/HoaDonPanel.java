/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ultramotor.ui.hoadon;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerDataTable;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import com.swingx.SearchTextField;
import com.ultramotor.component.table.ModelAction;
import com.ultramotor.component.table.ModelEvent;
import com.ultramotor.component.table.Table;
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
import com.ultramotor.util.Auth;
import com.ultramotor.util.MsgBox;
import com.ultramotor.util.XDate;
import com.ultramotor.util.XDialog;
import com.ultramotor.util.XImage;
import com.ultramotor.util.XJdbc;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.swingx.autocomplete.ObjectToStringConverter;

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
    }

    private void updateStatus() {
        boolean done = cboTrangThai.getSelectedIndex() != 0;
        btnResetPanelKH.setEnabled(!done);
        btnThemCTHD.setEnabled(!done);
        btnThanhToan.setEnabled(!done);
        txtMaKH.setEditable(!done);
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
                deleteCTHD(sp, false);
                setFormSanPham(sp);

            }

            @Override
            public void delete(Object e) {
                deleteCTHD(findSanPham(((ChiTietHoaDon) e).getSKU()), true);
            }
        };

        btnNew.addActionListener(e -> {
            if (currentHD != null & cboTrangThai.getSelectedIndex() == 0) {
                int confirm = MsgBox.confirm("Đơn hàng hiện tại của bạn chưa lưu, bạn có muốn tiếp tục?", false);
                if (confirm != 0) {
                    return;
                }
            }
            resetChiTietHD();
            txtMaHD.setText(getAutoMaHD());
        });
        btnLuu.addActionListener(e -> {
            insertHoaDon(false);
        });

        btnThanhToan.addActionListener(e -> {
            insertHoaDon(true);
        });
        tblSanPham.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                row = tblSanPham.getSelectedRow();
//                editSanPham();
                if (e.getClickCount() == 2) {
                    setFormSanPham(findSanPham(String.valueOf(tblSanPham.getValueAt(tblSanPham.getSelectedRow(), 0))));
                }
            }
        });
        tblHoaDonTheoNgay.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    setFormHD((HoaDon) tblHoaDonTheoNgay.getValueAt(tblHoaDonTheoNgay.getSelectedRow(), 0));
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
                    }
                }
            }

        });
        btnSearchKH.addActionListener(event -> {
            XDialog.getDialog((JFrame) this.getTopLevelAncestor(), createPanelSearchKH()).setVisible(true);
        });

        btnResetPanelKH.addActionListener(event -> {
            reset(pnlKhachHang);
            chkThanhVien.setEnabled(true);
        });

        chkTatCa.addActionListener((e) -> {
            chkConHang.setSelected(true);
            chkHetHang.setSelected(chkTatCa.isSelected());
            fillTable(tblSanPham, getCurrentListSP());
        });

        chkConHang.addActionListener(e -> {
            chkTatCa.setSelected(chkConHang.isSelected() && chkHetHang.isSelected());
            fillTable(tblSanPham, getCurrentListSP());
        });

        chkHetHang.addActionListener(e -> {
            chkTatCa.setSelected(chkConHang.isSelected() && chkHetHang.isSelected());
            fillTable(tblSanPham, getCurrentListSP());
        });

        btnThemCTHD.addActionListener(e -> {
            insertCTHD();
        });

        btnHuyHD.addActionListener(e -> {
            huyHD();
        });
    }

    private void fillTable() {
        fillTable(tblSanPham, getCurrentListSP());
        fillTableHDTheoNgay();
        fillTableHoaDon();
    }

    private void fillTableHDTheoNgay() {
        List<HoaDon> list = mapHD.keySet().stream().filter(hd -> XDate.isTheSameDay(hd.getThoiGian(), new Date())).collect(Collectors.toList());
        fillTable(tblHoaDonTheoNgay, list);
    }

    private void fillTableHoaDon() {
        List<HoaDon> list = mapHD.keySet().stream().filter(hd -> hd.getTrangThai().equalsIgnoreCase("HOÀN TẤT")).collect(Collectors.toList());
        fillTable(tblHoaDon, list);
    }

    void setFormSanPham(SanPham sp) {
        if (sp == null) {
            return;
        }
        if (sp.gettonKho() == 0) {
            MsgBox.error("Sản phẩm đã hết hàng! Chọn sản phẩm khác");
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

    private void fillTable(Table table, List<? extends Entity> list) {
        if (list == null) {
            return;
        }
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        System.out.println("Filling Table");
        list.forEach(e -> {
            model.addRow(getInfo(e));
        });
    }

    private void insertCTHD() {
        if (currentSP == null || !listSP.contains(currentSP)) {
            MsgBox.error("Không tìm thấy sản phẩm");
            return;
        }
        //nếu hoá đơn đã mua, hiển thông tin khách hàng lên lại có được chỉnh sửa không
        //nếu hoá đơn chưa mua, hiển thị thông tin khách hàng như nào
        if (txtMaHD.getText().isEmpty()) {
            txtMaHD.setText(getAutoMaHD());
            txtMaHD.setEditable(false);
        }
        if (currentHD == null) {
            currentHD = new HoaDon(txtMaHD.getText());
        }
        ChiTietHoaDon ct = new ChiTietHoaDon(
                tblChiTiet.getRowCount() + 1,
                currentSP.getGiaTien(),
                currentHD.getIdHD(),
                currentSP.getSku()
        );
        currentHD.addCTHD(ct);
        insertRow(tblChiTiet, ct); //thêm vào bảng hoá đơn chi tiết
        int index = listSP.indexOf(currentSP); //lấy index sản phẩm hiện tại trong danh sách
        listSP.get(index).settonKho(currentSP.gettonKho() - 1); //giảm số lượng tồn kho sp trong listSP
        fillTable(tblSanPham, listSP);
        reset(pnlInfoSanPham);
        currentSP = null;
    }

    private void deleteCTHD(SanPham sp, boolean ask) {
        CellEditor editor = tblChiTiet.getCellEditor();
        if (editor != null) {
            editor.stopCellEditing();
        }
        if (ask) {
            int confirm = MsgBox.confirm("Xoá sản phẩm khỏi đơn hàng?", false);
            if (confirm != 0) {
                return;
            }
        }

        int index = tblChiTiet.getSelectedRow();
        if (index >= 0) {
            DefaultTableModel model = (DefaultTableModel) tblChiTiet.getModel();
            model.removeRow(index);
            if (model.getRowCount() == 0) {
                currentHD = null;
            }
            listSP.get(listSP.indexOf(sp)).settonKho(sp.gettonKho() + 1);
            fillTable(tblSanPham, listSP);
        }
    }

    private void resetChiTietHD() {
        reset(pnlChiTietHD);
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
        if (currentHD != null && cboTrangThai.getSelectedIndex() == 0) {
            int confirm = MsgBox.confirm("Bạn chưa lưu hoá đơn hiện tại. Bạn có muốn tiếp tục?", false);
            if (confirm != 0) {
                return;
            }
        }
        txtMaHD.setText(hd.getIdHD());
        cboTrangThai.setSelectedItem(hd.getTrangThai());
        if (!hd.getLoaiThanhToan().equals("")) {
            cboLoaiThanhToan.setSelectedItem(hd.getLoaiThanhToan());
        }

        fillTable(tblChiTiet, hd.getListCTHD());
        setFormKH(mapHD.get(hd));
        currentHD = hd;
        updateStatus();
    }

    private void huyHD() {
        if (currentHD == null) {
            MsgBox.warning("Vui lòng chọn hoá đơn!");
            return;
        }
        if (cboTrangThai.getSelectedIndex() != 0) {
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
            ((DefaultTableModel) tblHoaDonTheoNgay.getModel()).removeRow(index);
        }
        resetChiTietHD(); //đặt lại thông tin trên panel chi tiết hoá đơn
        MsgBox.inform("Huỷ đơn hàng thành công!");
        //mapHD.re// xoá hoá đơn khỏi MAP
    }

    @SuppressWarnings("empty-statement")
    private void insertHoaDon(boolean thanhToan) {
        if (currentHD == null) {
            MsgBox.error("Không tìm thấy hoá đơn cần lưu!");
            return;
        }
        KhachHang kh = getFormKH();
        if (kh == null) {
            MsgBox.error("Vui lòng nhập thông tin khách hàng!");
            return;
        }

        currentHD.setThoiGian(new Date());
        currentHD.setIdNV(Auth.user == null ? "NV01" : Auth.user.getIdNV());

        if (thanhToan) {
            if (!checkSoLuongChiTietHD(currentHD.getListCTHD())) {
                MsgBox.error("Không thể thanh toán đơn hàng! Vui lòng kiểm tra lại số lượng sản phẩm.");
                return;
            };
            cboTrangThai.setSelectedIndex(1);
        }
        currentHD.setTrangThai((String) cboTrangThai.getSelectedItem());
        currentHD.setLoaiThanhToan(cboTrangThai.getSelectedIndex() == 0 ? "" : (String) cboLoaiThanhToan.getSelectedItem());
        currentHD.setIdKH(kh.getIdKH());

        if (thanhToan) {
            try {
                insertDB(currentHD);
            } catch (SQLException ex) {
                MsgBox.error("Không thể thanh toán hoá đơn! Vui lòng kiểm tra lại dữ liệu.");
                Logger.getLogger(HoaDonPanel.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }
        }
        insertRow(tblHoaDonTheoNgay, currentHD, findIndexHD(currentHD));
        mapHD.put(currentHD, kh);
        currentHD = null;
        reset(pnlKhachHang);
        reset(pnlChiTietHD);
        ((DefaultTableModel) tblChiTiet.getModel()).setRowCount(0);
        if (thanhToan) {
            new Thread(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(HoaDonPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                int confirm = MsgBox.confirm("Đơn hàng đã thanh toán thành công! Bạn có muốn in hoá đơn?", false);
                if (confirm == 0) {

                }
            }).start();
        }
    }

    private void insertDB(HoaDon hd) throws SQLException {
        SQLServerDataTable table = ChiTietHoaDon.getDataServerTable();
        hd.getListCTHD().forEach(ct -> {
            try {
                table.addRow(new Object[]{ct.getIdCTHD(), ct.getDonGia(), ct.getSKU(), ct.getIdHD()});
            } catch (SQLServerException ex) {
                Logger.getLogger(HoaDonPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        hdDAO.insertWithChiTiet(hd, table);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame fr = new JFrame();
            fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            fr.getContentPane().add(new HoaDonPanel());
            fr.pack();
            fr.setVisible(true);
        });
    }

    private int findIndexHD(HoaDon hd) {
        for (int i = 0; i < tblHoaDonTheoNgay.getRowCount(); i++) {
            if (tblHoaDonTheoNgay.getValueAt(i, 0).equals(hd)) {
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
                } else if (comp instanceof JPanel) {
                    reset((JPanel) comp);
                }
            }
        }
        updateStatus();
    }

    private String getAutoMaHD() {
        return String.format("HD%02d", mapHD.size() + 1);
    }

    private boolean checkSoLuongChiTietHD(List<ChiTietHoaDon> list) {
        List<String> skus = new ArrayList<>(list.size());
        list.forEach(ct -> skus.add(ct.getSKU()));
        Set<String> set = spDAO.checkHangTonSP(skus.toArray(new String[skus.size()]));
        return !skus.stream().anyMatch(set::contains);
    }

    private void initTable() {
        tblChiTiet.fixTable((JScrollPane) tblChiTiet.getParent().getParent());
        tblSanPham.fixTable((JScrollPane) tblSanPham.getParent().getParent());
        tblHoaDonTheoNgay.fixTable((JScrollPane) tblHoaDonTheoNgay.getParent().getParent());
        tblHoaDon.fixTable((JScrollPane) tblHoaDon.getParent().getParent());

        addRowSorter(tblSanPham, txtTimKiemSP);
        addRowSorter(tblHoaDonTheoNgay, txtTimKiemHoaDonTheoNgay);
        addRowSorter(tblHoaDon, txtTimKiemHoaDon);
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
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
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
                kh.getThanhVien()?"Thành Viên":"Không phải thành viên",
                kh.getGhiChu()};
        }
        if (e instanceof SanPham) {
            SanPham sp = (SanPham) e;
            return new Object[]{
                sp.getSku(), sp.getTenSP(), numberFormat.format(sp.getGiaTien()), sp.getMauSac(), sp.getPhanKhoi(),
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
                XDate.toString(hd.getThoiGian(), "hh:mm:ss dd/MM/yyyy"), hd.getLoaiThanhToan(),
                nv == null ? "" : nv.getHoTenNV(),
                hd.getTrangThai()
            };
        }
        if (e instanceof ChiTietHoaDon) {
            ChiTietHoaDon ct = (ChiTietHoaDon) e;
            return new Object[]{
                ct.getIdCTHD(),
                ct.getSKU(),
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
        return listKH.stream().filter(kh -> idKH.trim().equalsIgnoreCase(kh.getIdKH())).findFirst().orElse(null);
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
            Logger.getLogger(HoaDonPanel.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgrTrangThai = new javax.swing.ButtonGroup();
        pnlMain = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnlHoaDon = new javax.swing.JPanel();
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
        jPanel1 = new javax.swing.JPanel();
        btnResetPanelKH = new com.swingx.Button();
        pnlTBLHoaDon = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblHoaDonTheoNgay = new com.ultramotor.component.table.Table();
        txtTimKiemHoaDonTheoNgay = new com.swingx.SearchTextField();
        pnlChiTietHD = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblChiTiet = new com.ultramotor.component.table.Table();
        txtMaHD = new com.swingx.TextField();
        cboLoaiThanhToan = new javax.swing.JComboBox<>();
        btnHuyHD = new com.swingx.Button();
        btnLuu = new com.swingx.Button();
        btnThanhToan = new com.swingx.Button();
        cboTrangThai = new com.swingx.ComboBoxSuggestion();
        btnNew = new com.swingx.Button();
        pnlSanPham = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblSanPham = new com.ultramotor.component.table.Table();
        txtTimKiemSP = new com.swingx.SearchTextField();
        chkTatCa = new javax.swing.JCheckBox();
        chkConHang = new javax.swing.JCheckBox();
        chkHetHang = new javax.swing.JCheckBox();
        pnlLDanhSach = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblHoaDon = new com.ultramotor.component.table.Table();
        pnlButton1 = new javax.swing.JPanel();
        btnCapNhat1 = new com.swingx.Button();
        btnReset1 = new com.swingx.Button();
        txtTimKiemHoaDon = new com.swingx.SearchTextField();

        pnlHoaDon.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlInfoSanPham.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin đơn hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 153, 255))); // NOI18N

        txtMaSP.setEditable(false);
        txtMaSP.setLabelText("Mã sản phẩm");
        txtMaSP.setOnlyField(true);

        txtGiaTien.setEditable(false);
        txtGiaTien.setLabelText("Giá bán");
        txtGiaTien.setOnlyField(true);

        txtTenSP.setEditable(false);
        txtTenSP.setLabelText("Tên sản phẩm");
        txtTenSP.setOnlyField(true);

        lblMaSKU.setText("Mã SKU");

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

        btnThemCTHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ultramotor/img/icon/broom_25px.png"))); // NOI18N
        btnThemCTHD.setText("Thêm");

        txtPhanKhoi.setEditable(false);
        txtPhanKhoi.setLabelText("Giá bán");
        txtPhanKhoi.setOnlyField(true);

        lblPhanKhoi.setText("Phân Khối");

        lblHinh.setBorder(javax.swing.BorderFactory.createTitledBorder("Hình"));

        javax.swing.GroupLayout pnlInfoSanPhamLayout = new javax.swing.GroupLayout(pnlInfoSanPham);
        pnlInfoSanPham.setLayout(pnlInfoSanPhamLayout);
        pnlInfoSanPhamLayout.setHorizontalGroup(
            pnlInfoSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfoSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInfoSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInfoSanPhamLayout.createSequentialGroup()
                        .addGroup(pnlInfoSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDiachiSX)
                            .addGroup(pnlInfoSanPhamLayout.createSequentialGroup()
                                .addGroup(pnlInfoSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNSX, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblNSX))
                                .addGap(18, 18, 18)
                                .addGroup(pnlInfoSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblMauSac, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMauSac, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnlInfoSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblBaoHanh, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                                    .addComponent(txtBaoHanh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlInfoSanPhamLayout.createSequentialGroup()
                        .addGroup(pnlInfoSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlInfoSanPhamLayout.createSequentialGroup()
                                .addGroup(pnlInfoSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlInfoSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lblMaSKU)
                                        .addComponent(lblTenSP)
                                        .addComponent(txtGiaTien, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                        .addComponent(txtTenSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtMaSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(lblGiaTien))
                                .addGap(18, 18, 18)
                                .addGroup(pnlInfoSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlInfoSanPhamLayout.createSequentialGroup()
                                        .addGroup(pnlInfoSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblDoiXe)
                                            .addComponent(txtDoiXe, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(10, 10, 10)
                                        .addGroup(pnlInfoSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblPhanKhoi)
                                            .addComponent(txtPhanKhoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addComponent(lblHinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(pnlInfoSanPhamLayout.createSequentialGroup()
                                .addComponent(txtDiachiSX, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnThemCTHD, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)))
                        .addContainerGap())))
        );
        pnlInfoSanPhamLayout.setVerticalGroup(
            pnlInfoSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfoSanPhamLayout.createSequentialGroup()
                .addGroup(pnlInfoSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlInfoSanPhamLayout.createSequentialGroup()
                        .addGroup(pnlInfoSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                .addComponent(lblGiaTien, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtGiaTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInfoSanPhamLayout.createSequentialGroup()
                                .addComponent(lblHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(pnlInfoSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInfoSanPhamLayout.createSequentialGroup()
                                        .addComponent(lblDoiXe)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtDoiXe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInfoSanPhamLayout.createSequentialGroup()
                                        .addComponent(lblPhanKhoi)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtPhanKhoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlInfoSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlInfoSanPhamLayout.createSequentialGroup()
                                .addComponent(lblNSX)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNSX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInfoSanPhamLayout.createSequentialGroup()
                                .addComponent(lblBaoHanh)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtBaoHanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pnlInfoSanPhamLayout.createSequentialGroup()
                        .addComponent(lblMauSac)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDiachiSX)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInfoSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThemCTHD, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(txtDiachiSX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pnlHoaDon.add(pnlInfoSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, 350, 350));

        pnlKhachHang.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin chung", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 153, 255))); // NOI18N

        txtHoTenKH.setLabelText("Họ tên khách hàng");
        txtHoTenKH.setOnlyField(true);

        txtDiaChi.setToolTipText("");
        txtDiaChi.setLabelText("Địa chỉ");
        txtDiaChi.setOnlyField(true);

        txtNgaySinh.setLabelText("Số điện thoại");
        txtNgaySinh.setOnlyField(true);
        txtNgaySinh.setPlaceholder("DD-MM-YYYY");

        txtMaKH.setLabelText("Mã khách hàng");
        txtMaKH.setOnlyField(true);

        txtEmail.setLabelText("Số điện thoại");
        txtEmail.setOnlyField(true);

        txtSDT.setLabelText("Số điện thoại");
        txtSDT.setOnlyField(true);

        chkThanhVien.setText("Đăng ký thành viên");

        cboGioiTinh.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nam", "Nữ" }));

        btnSearchKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ultramotor/img/icon/search_client_25px.png"))); // NOI18N

        lblMaKH.setText("Mã Khách Hàng");

        lblHoTen.setText("Họ Tên ");

        lblNgaySinh.setText("Ngày Sinh");

        lblGioiTinh.setText("Giới Tính");

        lblDiaChi.setText("Địa chỉ");

        lblSoDT.setText("Số Điện Thoại");

        lblEmail.setText("Email");

        jPanel1.setBackground(new java.awt.Color(255, 204, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        btnResetPanelKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ultramotor/img/icon/broom_25px.png"))); // NOI18N
        btnResetPanelKH.setText("Xoá Form");

        javax.swing.GroupLayout pnlKhachHangLayout = new javax.swing.GroupLayout(pnlKhachHang);
        pnlKhachHang.setLayout(pnlKhachHangLayout);
        pnlKhachHangLayout.setHorizontalGroup(
            pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cboGioiTinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtHoTenKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblHoTen)
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNgaySinh)
                    .addComponent(lblGioiTinh)
                    .addComponent(lblMaKH)
                    .addGroup(pnlKhachHangLayout.createSequentialGroup()
                        .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearchKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblDiaChi)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(lblEmail, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSoDT, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSDT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnResetPanelKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(chkThanhVien)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        pnlKhachHangLayout.setVerticalGroup(
            pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKhachHangLayout.createSequentialGroup()
                .addGroup(pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlKhachHangLayout.createSequentialGroup()
                        .addGroup(pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlKhachHangLayout.createSequentialGroup()
                                .addComponent(lblMaKH)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnSearchKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtMaKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblHoTen)
                                .addGap(5, 5, 5)
                                .addComponent(txtHoTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlKhachHangLayout.createSequentialGroup()
                                .addComponent(lblSoDT)
                                .addGap(5, 5, 5)
                                .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlKhachHangLayout.createSequentialGroup()
                                .addComponent(lblNgaySinh)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblGioiTinh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlKhachHangLayout.createSequentialGroup()
                        .addComponent(lblEmail)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlKhachHangLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDiaChi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlKhachHangLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(chkThanhVien)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnResetPanelKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(22, 22, 22))
        );

        pnlKhachHangLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cboGioiTinh, txtSDT});

        pnlHoaDon.add(pnlKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 350, 350));

        pnlTBLHoaDon.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 153, 255))); // NOI18N

        tblHoaDonTheoNgay.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hóa đơn", "Tên Khách Hàng", "Tổng Tiền", "Thời Gian", "Loại Thanh Toán", "Tên Nhân Viên", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(tblHoaDonTheoNgay);

        javax.swing.GroupLayout pnlTBLHoaDonLayout = new javax.swing.GroupLayout(pnlTBLHoaDon);
        pnlTBLHoaDon.setLayout(pnlTBLHoaDonLayout);
        pnlTBLHoaDonLayout.setHorizontalGroup(
            pnlTBLHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTBLHoaDonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTBLHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
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
                .addGap(0, 68, Short.MAX_VALUE))
        );

        pnlHoaDon.add(pnlTBLHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 370, 630, 350));

        pnlChiTietHD.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "HOÁ ĐƠN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 153, 255))); // NOI18N

        tblChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã SKU", "Đơn giá", "Mã Hoá Đơn", "Actions"
            }
        ));
        jScrollPane6.setViewportView(tblChiTiet);

        txtMaHD.setLabelText("Mã hóa đơn");

        cboLoaiThanhToan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiền mặt", "Trả góp" }));
        cboLoaiThanhToan.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Loại thanh toán", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(102, 102, 102))); // NOI18N

        btnHuyHD.setBackground(new java.awt.Color(113, 118, 145));
        btnHuyHD.setForeground(new java.awt.Color(255, 255, 255));
        btnHuyHD.setText("Huỷ Đơn Hàng");
        btnHuyHD.setBorderPainted(false);
        btnHuyHD.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnLuu.setBackground(new java.awt.Color(113, 118, 145));
        btnLuu.setForeground(new java.awt.Color(255, 255, 255));
        btnLuu.setText("Lưu");
        btnLuu.setBorderPainted(false);
        btnLuu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnThanhToan.setBackground(new java.awt.Color(113, 118, 145));
        btnThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.setBorderPainted(false);
        btnThanhToan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "CHƯA THANH TOÁN", "HOÀN TẤT", " " }));
        cboTrangThai.setEnabled(false);
        cboTrangThai.setPreferredSize(new java.awt.Dimension(20, 34));

        btnNew.setBackground(new java.awt.Color(113, 118, 145));
        btnNew.setForeground(new java.awt.Color(255, 255, 255));
        btnNew.setText("Tạo mới");
        btnNew.setBorderPainted(false);
        btnNew.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout pnlChiTietHDLayout = new javax.swing.GroupLayout(pnlChiTietHD);
        pnlChiTietHD.setLayout(pnlChiTietHDLayout);
        pnlChiTietHDLayout.setHorizontalGroup(
            pnlChiTietHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChiTietHDLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlChiTietHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlChiTietHDLayout.createSequentialGroup()
                        .addGroup(pnlChiTietHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlChiTietHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cboTrangThai, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cboLoaiThanhToan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlChiTietHDLayout.createSequentialGroup()
                        .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHuyHD, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10))
        );
        pnlChiTietHDLayout.setVerticalGroup(
            pnlChiTietHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChiTietHDLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlChiTietHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlChiTietHDLayout.createSequentialGroup()
                        .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cboLoaiThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 36, Short.MAX_VALUE))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnlChiTietHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuyHD, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        pnlHoaDon.add(pnlChiTietHD, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 20, 620, 330));

        pnlSanPham.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 153, 255))); // NOI18N

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "SKU", "Tên SP", "Giá tiền", "Màu sắc", "Phân khối", "Hình", "Tồn kho"
            }
        ));
        tblSanPham.setRowHeight(20);
        jScrollPane4.setViewportView(tblSanPham);

        chkTatCa.setText("Tất Cả");

        chkConHang.setSelected(true);
        chkConHang.setText("Còn Hàng");

        chkHetHang.setText("Hết Hàng");

        javax.swing.GroupLayout pnlSanPhamLayout = new javax.swing.GroupLayout(pnlSanPham);
        pnlSanPham.setLayout(pnlSanPhamLayout);
        pnlSanPhamLayout.setHorizontalGroup(
            pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSanPhamLayout.createSequentialGroup()
                .addGroup(pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSanPhamLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(chkTatCa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkConHang)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkHetHang)
                        .addGap(18, 18, 18)
                        .addComponent(txtTimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlSanPhamLayout.setVerticalGroup(
            pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSanPhamLayout.createSequentialGroup()
                .addGroup(pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkTatCa)
                    .addComponent(chkConHang)
                    .addComponent(chkHetHang))
                .addGap(33, 33, 33)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        pnlHoaDon.add(pnlSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 710, 350));
        pnlSanPham.getAccessibleContext().setAccessibleName("");

        jTabbedPane1.addTab("Hóa đơn", pnlHoaDon);

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Hoá Đơn", "Tên Khách Hàng", "Tổng Tiền", "Thời Gian", "Loại Thanh Toán", "Tên Nhân Viên", "Trạng Thái"
            }
        ));
        jScrollPane5.setViewportView(tblHoaDon);

        pnlButton1.setOpaque(false);
        pnlButton1.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        btnCapNhat1.setBackground(new java.awt.Color(113, 118, 145));
        btnCapNhat1.setForeground(new java.awt.Color(255, 255, 255));
        btnCapNhat1.setText("Delete");
        btnCapNhat1.setBorderPainted(false);
        btnCapNhat1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pnlButton1.add(btnCapNhat1);

        btnReset1.setBackground(new java.awt.Color(113, 118, 145));
        btnReset1.setForeground(new java.awt.Color(255, 255, 255));
        btnReset1.setText("Export");
        btnReset1.setBorderPainted(false);
        btnReset1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pnlButton1.add(btnReset1);

        javax.swing.GroupLayout pnlLDanhSachLayout = new javax.swing.GroupLayout(pnlLDanhSach);
        pnlLDanhSach.setLayout(pnlLDanhSachLayout);
        pnlLDanhSachLayout.setHorizontalGroup(
            pnlLDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLDanhSachLayout.createSequentialGroup()
                .addGroup(pnlLDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLDanhSachLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnlButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlLDanhSachLayout.createSequentialGroup()
                        .addGap(1257, 1257, 1257)
                        .addComponent(txtTimKiemHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 271, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(pnlLDanhSachLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlLDanhSachLayout.setVerticalGroup(
            pnlLDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLDanhSachLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(txtTimKiemHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 622, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(97, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Danh sách", pnlLDanhSach);

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 860, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgrTrangThai;
    private com.swingx.Button btnCapNhat1;
    private com.swingx.Button btnHuyHD;
    private com.swingx.Button btnLuu;
    private com.swingx.Button btnNew;
    private com.swingx.Button btnReset1;
    private com.swingx.Button btnResetPanelKH;
    private com.swingx.Button btnSearchKH;
    private com.swingx.Button btnThanhToan;
    private com.swingx.Button btnThemCTHD;
    private com.swingx.ComboBoxSuggestion cboGioiTinh;
    private javax.swing.JComboBox<String> cboLoaiThanhToan;
    private com.swingx.ComboBoxSuggestion cboTrangThai;
    private javax.swing.JCheckBox chkConHang;
    private javax.swing.JCheckBox chkHetHang;
    private javax.swing.JCheckBox chkTatCa;
    private javax.swing.JCheckBox chkThanhVien;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblBaoHanh;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblDiachiSX;
    private javax.swing.JLabel lblDoiXe;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblGiaTien;
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
    private javax.swing.JPanel pnlButton1;
    private javax.swing.JPanel pnlChiTietHD;
    private javax.swing.JPanel pnlHoaDon;
    private javax.swing.JPanel pnlInfoSanPham;
    private javax.swing.JPanel pnlKhachHang;
    private javax.swing.JPanel pnlLDanhSach;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlSanPham;
    private javax.swing.JPanel pnlTBLHoaDon;
    private com.ultramotor.component.table.Table tblChiTiet;
    private com.ultramotor.component.table.Table tblHoaDon;
    private com.ultramotor.component.table.Table tblHoaDonTheoNgay;
    private com.ultramotor.component.table.Table tblSanPham;
    private com.swingx.TextField txtBaoHanh;
    private com.swingx.TextField txtDiaChi;
    private com.swingx.TextField txtDiachiSX;
    private com.swingx.TextField txtDoiXe;
    private com.swingx.TextField txtEmail;
    private com.swingx.TextField txtGiaTien;
    private com.swingx.TextField txtHoTenKH;
    private com.swingx.TextField txtMaHD;
    private com.swingx.TextField txtMaKH;
    private com.swingx.TextField txtMaSP;
    private com.swingx.TextField txtMauSac;
    private com.swingx.TextField txtNSX;
    private com.swingx.TextField txtNgaySinh;
    private com.swingx.TextField txtPhanKhoi;
    private com.swingx.TextField txtSDT;
    private com.swingx.TextField txtTenSP;
    private com.swingx.SearchTextField txtTimKiemHoaDon;
    private com.swingx.SearchTextField txtTimKiemHoaDonTheoNgay;
    private com.swingx.SearchTextField txtTimKiemSP;
    // End of variables declaration//GEN-END:variables

    private JPanel createPanelSearchKH() {
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
        txtMaKH.setText(kh.getIdKH());
        txtHoTenKH.setText(kh.getHoTenKH());
        txtNgaySinh.setText(XDate.toString(kh.getNgaySinh(), "dd-MM-yyyy"));
        cboGioiTinh.setSelectedIndex(kh.getGioiTinh() ? 0 : 1);
        txtDiaChi.setText(kh.getDiaChi());
        txtSDT.setText(kh.getSdt());
        txtEmail.setText(kh.getEmail());
        chkThanhVien.setSelected(kh.getThanhVien());
        chkThanhVien.setEnabled(!kh.getThanhVien());
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

    private boolean validateKH() {

        return true;
    }
}
