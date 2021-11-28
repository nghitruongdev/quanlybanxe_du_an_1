/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ultramotor.ui.hoadon;

import com.swingx.SearchTextField;
import com.ultramotor.component.table.Table;
import com.ultramotor.dao.HoaDonDAO;
import com.ultramotor.dao.KhachHangDAO;
import com.ultramotor.dao.SanPhamDAO;
import com.ultramotor.entity.ChiTietHoaDon;
import com.ultramotor.entity.HoaDon;
import com.ultramotor.entity.KhachHang;
import com.ultramotor.entity.SanPham;
import com.ultramotor.util.Auth;
import com.ultramotor.util.MsgBox;
import com.ultramotor.util.XDate;
import com.ultramotor.util.XDialog;
import com.ultramotor.util.XImage;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
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
import javax.swing.text.JTextComponent;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.autocomplete.ObjectToStringConverter;

public class HoaDonPanel extends javax.swing.JPanel {

    private HoaDonDAO hdDAO = new HoaDonDAO();
    private SanPhamDAO spDAO = new SanPhamDAO();
    private KhachHangDAO khDAO = new KhachHangDAO();
    private List<KhachHang> listKH;
    private ObjectToStringConverter stringConverter;
    private KeyAdapter khachHangAdapter;

    public HoaDonPanel() {
        initComponents();
        init();
    }

    private void init() {
        listKH = khDAO.selectAll();
//        fillTable();
//        fillTableHoaDonNgay();
//        fillTableSanPham();
        addListeners();
        initStringConverter();
//        cboSearchKH.setModel(new DefaultComboBoxModel(listKH.toArray()));
//        setKhachHangDecorator((JTextComponent) cboSearchKH.getEditor().getEditorComponent(), listKH);

    }

    private void setFormKhachHang(KhachHang kh) {
        txtMaKH.setText(kh.getIdKH());
        txtHoTenKH.setText(kh.getHoKH() + " " + kh.getTenKH());
        txtNgaySinh.setText(XDate.toString(kh.getNgaySinh(), "dd-MM-yyyy"));
        cboGioiTinh.setSelectedIndex(kh.getGioiTinh() ? 0 : 1);
        txtDiaChi.setText(kh.getDiaChi());
        txtSDT.setText(kh.getSdt());
        txtEmail.setText(kh.getEmail());
        chkThanhVien.setSelected(kh.getThanhVien());
        chkThanhVien.setEnabled(!kh.getThanhVien());
    }

    private KhachHang timKhachHang(String idKH) {
        return listKH.stream().filter(kh -> idKH.trim().equalsIgnoreCase(kh.getIdKH())).findFirst().orElse(null);
    }

    private void addListeners() {

        btnLuu.addActionListener((ActionEvent e) -> {
            insert();
        });

        tblSanPham.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                row = tblSanPham.getSelectedRow();
                editSanPham();
            }

        });

        txtMaKH.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String idKH = txtMaKH.getText();
                if (!idKH.isEmpty()) {
                    KhachHang kh = timKhachHang(idKH);
                    if (kh != null) {
                        setFormKhachHang(kh);
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
//        cboSearchKH.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyReleased(KeyEvent e) {
//                new Thread(() -> {
//                    List<KhachHang> list = timKiemKhachHang();
//                    DefaultComboBoxModel model = (DefaultComboBoxModel) cboSearchKH.getModel();
//                    list.forEach(kh -> model.removeElement(kh));
//                }).start();
//            }
//        });

//        txtSearchKH.addKeyListener(new KeyAdapter() {
//            public void keyReleased(KeyEvent e) {
////                setKhachHangDecorator(txtSearchKH, timKiemKhachHang());
//            }
//        });
    }

    private void initStringConverter() {
        stringConverter = new ObjectToStringConverter() {
            @Override
            public String getPreferredStringForItem(Object o) {
                if (o instanceof KhachHang) {
                    KhachHang kh = (KhachHang) o;
                    return String.format("%s %s, %s", kh.getHoKH(), kh.getTenKH(), kh.getSdt());
                }
                return String.valueOf(o);
            }
        };
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
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame fr = new JFrame();
            fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            fr.getContentPane().add(new HoaDonPanel());
//            HoaDonPanel hdPanel = new HoaDonPanel();
//            fr.getContentPane().add(hdPanel.createPanelSearchKH());

            fr.pack();
            fr.setVisible(true);
        });
    }

    private JPanel createPanelSearchKH() {
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout("insets 20, fillx", "[center]"));
        panel.setSize(1000, 500);
        panel.setBackground(new Color(250, 250, 250));
        String[] column = {"ID_Khách Hàng", "Họ Tên", "Ngày Sinh", "Giới Tính", "Địa Chỉ", "Email", "Số Điện Thoại", "Hạng Thành Viên", "Ghi chú"};
        DefaultTableModel model = new DefaultTableModel(column, 0);
        listKH.forEach(kh -> {
            model.addRow(getInfo(kh));
        });
        TableRowSorter<TableModel> sorter = new TableRowSorter(model);
        Table table = new Table();
        table.setModel(model);
        table.createDefaultColumnsFromModel();
        table.setRowSorter(sorter);
        JScrollPane pane = new JScrollPane();
        pane.add(table);
        pane.setBackground(new Color(250, 250, 250));
        SearchTextField field = new SearchTextField();
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

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = table.getSelectedRow();
                    System.out.println(row);
                    if (row >= 0) {
                        setFormKhachHang((KhachHang) table.getValueAt(row, 0));
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

    private static Object[] getInfo(KhachHang kh) {
        return new Object[]{kh,
            String.format("%s %s", kh.getHoKH(), kh.getTenKH()),
            kh.getNgaySinh(),
            kh.getGioiTinh(),
            kh.getDiaChi(),
            kh.getEmail(),
            kh.getSdt(),
            kh.getThanhVien(),
            kh.getGhiChu()};
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgrTrangThai = new javax.swing.ButtonGroup();
        pnlMain = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnlHoaDon = new javax.swing.JPanel();
        pnlTTDH = new javax.swing.JPanel();
        txtMaSP = new com.swingx.TextField();
        txtGiaBan = new com.swingx.TextField();
        txtTenSP = new com.swingx.TextField();
        lblHinh = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
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
        tblHoaDonNgay = new com.ultramotor.component.table.Table();
        txtTimKiemHD = new com.swingx.SearchTextField();
        pnlCTHD = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblSanPham1 = new com.ultramotor.component.table.Table();
        txtMaHD = new com.swingx.TextField();
        cboLoaiThanhToan = new javax.swing.JComboBox<>();
        txtTrangThai = new com.swingx.TextField();
        pnlButton2 = new javax.swing.JPanel();
        btnLuu = new com.swingx.Button();
        btnThanhToan1 = new com.swingx.Button();
        btnReset2 = new com.swingx.Button();
        pnlSanPham = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblSanPham = new com.ultramotor.component.table.Table();
        txtTimKiemSP = new com.swingx.SearchTextField();
        pnlLDanhSach = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblHoaDon = new com.ultramotor.component.table.Table();
        pnlButton1 = new javax.swing.JPanel();
        btnCapNhat1 = new com.swingx.Button();
        btnReset1 = new com.swingx.Button();

        pnlHoaDon.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlTTDH.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin đơn hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 153, 255))); // NOI18N

        txtMaSP.setLabelText("Mã sản phẩm");
        txtMaSP.setOnlyField(true);

        txtGiaBan.setLabelText("Giá bán");
        txtGiaBan.setOnlyField(true);

        txtTenSP.setLabelText("Tên sản phẩm");
        txtTenSP.setOnlyField(true);

        lblHinh.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Hình"));

        jLabel1.setText("Mã Sản Phẩm");

        jLabel2.setText("Tên Sản Phẩm");

        jLabel3.setText("Giá Bán");

        javax.swing.GroupLayout pnlTTDHLayout = new javax.swing.GroupLayout(pnlTTDH);
        pnlTTDH.setLayout(pnlTTDHLayout);
        pnlTTDHLayout.setHorizontalGroup(
            pnlTTDHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTTDHLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTTDHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTTDHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtGiaBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTenSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtMaSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblHinh, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE))
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        pnlTTDHLayout.setVerticalGroup(
            pnlTTDHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTTDHLayout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(9, 9, 9)
                .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblHinh, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                .addGap(85, 85, 85))
        );

        pnlHoaDon.add(pnlTTDH, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, 280, 340));

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
                    .addComponent(chkThanhVien, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlKhachHangLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(btnResetPanelKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

        tblHoaDonNgay.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hóa đơn", "Thời gian", "Loại thanh toán", "Giá tiền (SP + VAT)", "Trạng thái", "Mã khách hàng", "Mã nhân viên", "Mã sản phẩm"
            }
        ));
        jScrollPane7.setViewportView(tblHoaDonNgay);

        javax.swing.GroupLayout pnlTBLHoaDonLayout = new javax.swing.GroupLayout(pnlTBLHoaDon);
        pnlTBLHoaDon.setLayout(pnlTBLHoaDonLayout);
        pnlTBLHoaDonLayout.setHorizontalGroup(
            pnlTBLHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTBLHoaDonLayout.createSequentialGroup()
                .addGap(313, 313, 313)
                .addComponent(txtTimKiemHD, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 137, Short.MAX_VALUE))
            .addGroup(pnlTBLHoaDonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7)
                .addContainerGap())
        );
        pnlTBLHoaDonLayout.setVerticalGroup(
            pnlTBLHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTBLHoaDonLayout.createSequentialGroup()
                .addComponent(txtTimKiemHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 85, Short.MAX_VALUE))
        );

        pnlHoaDon.add(pnlTBLHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 370, 630, 350));

        pnlCTHD.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "HOÁ ĐƠN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 153, 255))); // NOI18N

        tblSanPham1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane6.setViewportView(tblSanPham1);

        txtMaHD.setLabelText("Mã hóa đơn");

        cboLoaiThanhToan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiền mặt", "Trả góp", "ATM" }));
        cboLoaiThanhToan.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Loại thanh toán", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(102, 102, 102))); // NOI18N

        txtTrangThai.setLabelText("Trạng thái");

        pnlButton2.setOpaque(false);
        pnlButton2.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        btnLuu.setBackground(new java.awt.Color(113, 118, 145));
        btnLuu.setForeground(new java.awt.Color(255, 255, 255));
        btnLuu.setText("Lưu");
        btnLuu.setBorderPainted(false);
        btnLuu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pnlButton2.add(btnLuu);

        btnThanhToan1.setBackground(new java.awt.Color(113, 118, 145));
        btnThanhToan1.setForeground(new java.awt.Color(255, 255, 255));
        btnThanhToan1.setText("Thanh toán");
        btnThanhToan1.setBorderPainted(false);
        btnThanhToan1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pnlButton2.add(btnThanhToan1);

        btnReset2.setBackground(new java.awt.Color(113, 118, 145));
        btnReset2.setForeground(new java.awt.Color(255, 255, 255));
        btnReset2.setText("Đặt lại");
        btnReset2.setBorderPainted(false);
        btnReset2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pnlButton2.add(btnReset2);

        javax.swing.GroupLayout pnlCTHDLayout = new javax.swing.GroupLayout(pnlCTHD);
        pnlCTHD.setLayout(pnlCTHDLayout);
        pnlCTHDLayout.setHorizontalGroup(
            pnlCTHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCTHDLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCTHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboLoaiThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlCTHDLayout.createSequentialGroup()
                        .addGroup(pnlCTHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31))
        );
        pnlCTHDLayout.setVerticalGroup(
            pnlCTHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCTHDLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCTHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCTHDLayout.createSequentialGroup()
                        .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboLoaiThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlHoaDon.add(pnlCTHD, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 0, 750, 330));

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

        javax.swing.GroupLayout pnlSanPhamLayout = new javax.swing.GroupLayout(pnlSanPham);
        pnlSanPham.setLayout(pnlSanPhamLayout);
        pnlSanPhamLayout.setHorizontalGroup(
            pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSanPhamLayout.createSequentialGroup()
                .addGroup(pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSanPhamLayout.createSequentialGroup()
                        .addContainerGap(256, Short.MAX_VALUE)
                        .addComponent(txtTimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4))
                .addContainerGap())
        );
        pnlSanPhamLayout.setVerticalGroup(
            pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSanPhamLayout.createSequentialGroup()
                .addComponent(txtTimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlHoaDon.add(pnlSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 520, 350));
        pnlSanPham.getAccessibleContext().setAccessibleName("");

        jTabbedPane1.addTab("Hóa đơn", pnlHoaDon);

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8"
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
                .addContainerGap()
                .addGroup(pnlLDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1716, Short.MAX_VALUE)
                    .addGroup(pnlLDanhSachLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(pnlButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlLDanhSachLayout.setVerticalGroup(
            pnlLDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLDanhSachLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 622, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(94, Short.MAX_VALUE))
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
    private com.swingx.Button btnLuu;
    private com.swingx.Button btnReset1;
    private com.swingx.Button btnReset2;
    private com.swingx.Button btnResetPanelKH;
    private com.swingx.Button btnSearchKH;
    private com.swingx.Button btnThanhToan1;
    private com.swingx.ComboBoxSuggestion cboGioiTinh;
    private javax.swing.JComboBox<String> cboLoaiThanhToan;
    private javax.swing.JCheckBox chkThanhVien;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblHinh;
    private javax.swing.JLabel lblHoTen;
    private javax.swing.JLabel lblMaKH;
    private javax.swing.JLabel lblNgaySinh;
    private javax.swing.JLabel lblSoDT;
    private javax.swing.JPanel pnlButton1;
    private javax.swing.JPanel pnlButton2;
    private javax.swing.JPanel pnlCTHD;
    private javax.swing.JPanel pnlHoaDon;
    private javax.swing.JPanel pnlKhachHang;
    private javax.swing.JPanel pnlLDanhSach;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlSanPham;
    private javax.swing.JPanel pnlTBLHoaDon;
    private javax.swing.JPanel pnlTTDH;
    private com.ultramotor.component.table.Table tblHoaDon;
    private com.ultramotor.component.table.Table tblHoaDonNgay;
    private com.ultramotor.component.table.Table tblSanPham;
    private com.ultramotor.component.table.Table tblSanPham1;
    private com.swingx.TextField txtDiaChi;
    private com.swingx.TextField txtEmail;
    private com.swingx.TextField txtGiaBan;
    private com.swingx.TextField txtHoTenKH;
    private com.swingx.TextField txtMaHD;
    private com.swingx.TextField txtMaKH;
    private com.swingx.TextField txtMaSP;
    private com.swingx.TextField txtNgaySinh;
    private com.swingx.TextField txtSDT;
    private com.swingx.TextField txtTenSP;
    private com.swingx.SearchTextField txtTimKiemHD;
    private com.swingx.SearchTextField txtTimKiemSP;
    private com.swingx.TextField txtTrangThai;
    // End of variables declaration//GEN-END:variables

    int row = -1; //vị trí hàng được chọn trên table

    void setForm(HoaDon hd, ChiTietHoaDon cthd) {
        txtMaHD.setText(hd.getIdHD());
//        txtThoiGian.setText(XDate.toString(hd.getThoiGian()));
        if (hd.getLoaiThanhToan().equals("Tiền mặt")) {
            cboLoaiThanhToan.setSelectedIndex(0);
        } else if (hd.getLoaiThanhToan().equals("Trả góp")) {
            cboLoaiThanhToan.setSelectedIndex(1);
        } else {
            cboLoaiThanhToan.setSelectedIndex(2);
        }
        txtTrangThai.setText(hd.getTrangThai());
        txtHoTenKH.setText(hd.getIdKH());

    }

    HoaDon getForm() {
        HoaDon hd = new HoaDon();
        hd.setIdHD(txtMaHD.getText());
//        hd.setThoiGian(XDate.parse(txtThoiGian.getText()));
        hd.setLoaiThanhToan((String) cboLoaiThanhToan.getSelectedItem());
        hd.setTrangThai(txtTrangThai.getText());
        hd.setIdNV(Auth.user.getIdNV());
        hd.setIdKH(txtHoTenKH.getText());
        return hd;
    }

    ChiTietHoaDon getFormCTHD() {
        ChiTietHoaDon cthd = new ChiTietHoaDon();

        return cthd;
    }

    void insert() {
        HoaDon hd = getForm();
        try {
            hdDAO.insert(hd);
            MsgBox.inform("Thêm mới thành công!");
            fillTableHoaDonNgay();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    ;
    
//    void update(){
//        ChuyenDe cd = getForm();
//        try {
//            dao.update(cd);
//            this.fillTable();
//            this.clearForm();
//            MsgBox.alert(this, "Chỉnh sửa thành công!");
//        } catch (Exception e) {
//            MsgBox.alert(this, "Chỉnh sửa thất bại!");
//        }
//    };
//    
//    void delete(){
//        if(!Auth.isManager()){
//            MsgBox.alert(this,"Bạn không có quyền xóa chuyên đề!");
//        }
//            else if(MsgBox.confirm(this, "Bạn thực sự muốn xóa chuyên đề này?")){
//                try {
//                    dao.delete(txtMaCD.getText());
//                    this.fillTable();
//                    this.clearForm();
//                    MsgBox.alert(this, "Xóa thành công!");
//                } catch (Exception e) {
//                    MsgBox.alert(this, "Xóa thất bại!");
//                }
//            }
//    };
    
    void fillTableHoaDonNgay() {
        DefaultTableModel model = (DefaultTableModel) tblHoaDonNgay.getModel();
        model.setRowCount(0); // xóa tất cả các hàng trên jtable
        try {
            for (HoaDon hd : hdDAO.selectByTime(XDate.toString(XDate.now(), "yyyy-MM-dd"))) {
                Object[] row = {
                    hd.getIdHD(), hd.getThoiGian(), hd.getLoaiThanhToan(),
                    hd.getTrangThai(), hd.getIdKH(), hd.getIdNV()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.error("Lỗi truy vấn dữ liệu");
        }
    }

    void fillTableSanPham() {
        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        model.setRowCount(0); // xóa tất cả các hàng trên jtable
        try {
            for (SanPham e : spDAO.selectAll()) {
                Object[] row = {
                    e.getSku(), e.getTenSP(), e.getGiaTien(), e.getMauSac(), e.getPhanKhoi(),
                    e.getHinh(), e.gettonKho()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.error("Lỗi truy vấn dữ liệu");
        }
    }

    void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
        model.setRowCount(0); // xóa tất cả các hàng trên jtable
        try {
            for (HoaDon hd : hdDAO.selectAll()) {
                Object[] row = {
                    hd.getIdHD(), hd.getThoiGian(), hd.getLoaiThanhToan(),
                    hd.getTrangThai(), hd.getIdKH(), hd.getIdNV()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.error("Lỗi truy vấn dữ liệu");
        }
    }

    void editSanPham() {
        try {
            String SKU = (String) tblSanPham.getValueAt(this.row, 0);
            SanPham sp = spDAO.selectByID(SKU);
            setFormSanPham(sp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    void timKiemKhachHang() {
//        try {
//            String keyword = txtTimKiemKH.getText();
//            List<KhachHang> list = KHDAO.selectByKeyword(keyword); // đọc dữ liệu từ csdl
//            for (KhachHang kh : list) {
//                txtMaKH.setText(kh.getIdKH());
//                txtHoTenKH.setText(kh.getHoKH() + " " + kh.getTenKH());
//                txtSDT.setText(kh.getSdt());
//                txtDiaChi.setText(kh.getDiaChi());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    void setFormSanPham(SanPham sp) {
        txtMaSP.setText(sp.getSku());
        txtTenSP.setText(sp.getTenSP());
        txtGiaBan.setText(String.valueOf(sp.getGiaTien()));
        if (sp.getHinh() != null) {
            lblHinh.setToolTipText(sp.getHinh());
            XImage.setIcon(XImage.read(sp.getHinh()), lblHinh);
        }
    }
//    
//    void fillComboBoxDichVu(){
//        try {
//            List<DichVu> list = DVDAO.selectAll();
//            cboDichVu.setModel(new DefaultComboBoxModel(list.toArray()));
//        } catch (Exception e) {
//        }
//    }

}
