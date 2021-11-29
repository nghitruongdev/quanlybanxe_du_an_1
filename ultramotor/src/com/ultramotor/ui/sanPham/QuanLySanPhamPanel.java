package com.ultramotor.ui.sanPham;

import com.swingx.Button;
import com.swingx.ComboBoxSuggestion;
import com.swingx.TextField;
import com.ultramotor.component.table.ModelAction;
import com.ultramotor.component.table.ModelEvent;
import com.ultramotor.component.table.Table;
import com.ultramotor.dao.DongSanPhamDAO;
import com.ultramotor.dao.LoaiHangDAO;
import com.ultramotor.dao.NhaSanXuatDAO;
import com.ultramotor.dao.SanPhamDAO;
import com.ultramotor.entity.DongSanPham;
import com.ultramotor.entity.Entity;
import com.ultramotor.entity.LoaiHang;
import com.ultramotor.entity.NhaSanXuat;
import com.ultramotor.entity.SanPham;
import com.ultramotor.ui.sanPham.SanPhamPanel;
import com.ultramotor.util.MsgBox;
import com.ultramotor.util.XDialog;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.CellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.miginfocom.swing.MigLayout;

public class QuanLySanPhamPanel extends javax.swing.JPanel {

    private LoaiHangDAO daoLH;
    private DongSanPhamDAO daoDongSP;
    private NhaSanXuatDAO daoNSX;
    private SanPhamDAO daoSP;

    private List<LoaiHang> listLH;
    private List<NhaSanXuat> listNSX;
    private List<DongSanPham> listDongSP;
    private List<SanPham> listSP;

    private ModelEvent modelEvent;
    private MyPanel panel = null;

    public QuanLySanPhamPanel() {
        initComponents();
        init();
    }

    void init() {
        daoLH = new LoaiHangDAO();
        daoDongSP = new DongSanPhamDAO();
        daoNSX = new NhaSanXuatDAO();
        daoSP = new SanPhamDAO();

        initTable();
        addListeners();
        refresh(false);

    }

    private void addListeners() {
        modelEvent = new ModelEvent() {
            @Override
            public void update(Object e) {
                showPanel((Entity) e);
            }

            @Override
            public void delete(Object e) {
                deleteRow((Entity) e);
            }

        };
        addBtnAddListener(btnAddLH, tblLoaiHang, new LoaiHang());
        addBtnAddListener(btnAddNSX, tblNSX, new NhaSanXuat());
        addBtnAddListener(btnAddDongSP, tblDongSP, new DongSanPham());
        addBtnAddListener(btnAddSP, tblSP, new SanPham());

        btnRefresh.addActionListener(event -> {
            refresh(true);
        });
    }

    private void addBtnAddListener(JButton btn, Table table, Entity e) {
        btn.addActionListener(event -> {
            table.clearSelection();
            showPanel(e);
        });
    }

    private void initTable() {
        String[] colsLH = {"id_LH", "Tên Loại Hàng", "Actions"};
        String[] colsNSX = {"id_NSX", "Tên Nhà Sản Xuất", "Actions"};
        String[] colsDongSP = {"id_DongSP", "Tên Dòng Sản Phẩm", "Tên NSX", "Tên Loại Hàng", "Actions"};
        String[] colsSP = {"Mã SKU", "Tên Sản Phẩm", "Màu Sắc", "Phân Khối", "Thời Gian BH", "Địa chỉ SX", "Giá Tiền", "Đời Xe", "Hình", "id_DongSP", "id_NV", "Actions"};

        initTable(colsLH, tblLoaiHang);
        initTable(colsNSX, tblNSX);
        initTable(colsDongSP, tblDongSP);
        initTable(colsSP, tblSP);

        addRowSorter(tblLoaiHang, txtSearchLH);
        addRowSorter(tblNSX, txtSearchNSX);
        addRowSorter(tblSP, txtSearchSP);
        addRowSorter(tblDongSP, cboNSX, cboLH);
    }

    private void fillComboBox() {
        fillComboBoxNSX();
        fillComboBoxLH();
    }

    private void fillComboBoxNSX() {
        if (listNSX != null) {
            cboNSX.setModel(new DefaultComboBoxModel(listNSX.toArray()));
            cboNSX.insertItemAt(new NhaSanXuat(null, "Tất cả"), 0);
            cboNSX.setSelectedIndex(0);
        }
    }

    private void fillComboBoxLH() {
        if (listLH != null) {
            cboLH.setModel(new DefaultComboBoxModel(listLH.toArray()));
            cboLH.insertItemAt(new LoaiHang(null, "Tất cả"), 0);
            cboLH.setSelectedIndex(0);
        }

    }

    private void initTable(String[] cols, Table table, Integer... columnEditable) {
        DefaultTableModel model = new DefaultTableModel(cols, 0);
        table.setModel(model);
        table.setShowVerticalLines(true);
        table.getColumnModel().getColumn(0).setMaxWidth(200);
        table.getColumnModel().getColumn(table.getColumnCount() - 1).setMaxWidth(200);
        JScrollPane pane = (JScrollPane) table.getParent().getParent();
        table.fixTable(pane);

        pane.setBorder(null);
        table.addColumnEditable(columnEditable);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CellEditor editor = table.getCellEditor();
                if (editor != null) {
                    table.getCellEditor().stopCellEditing();
                }
            }
        });
    }

    private void addRowSorter(Table table, JTextField field) {
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
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

    private void addRowSorter(Table table, JComboBox... cbos) {
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);
        ActionListener cboSort = (ActionEvent e) -> {

            List<RowFilter<Object, Object>> listFilters = new ArrayList<>();
            List<String> ids = new ArrayList<>();

            for (JComboBox cb : cbos) {
                Object o = cb.getSelectedItem();

                if (o instanceof NhaSanXuat) {
                    NhaSanXuat nsx = (NhaSanXuat) o;
                    if (nsx.getIdNSX() != null) {
                        ids.add(nsx.getTenNSX());
                    }

                } else if (o instanceof LoaiHang) {
                    LoaiHang lh = (LoaiHang) o;
                    if (lh.getIdLH() != null) {
                        ids.add(lh.getTenLoaiHang());
                    }
                }
            }
            ids.forEach(id -> {
                listFilters.add(RowFilter.regexFilter("(?i)" + id));
            });
            sorter.setRowFilter(RowFilter.andFilter(listFilters));
        };

        for (JComboBox cbo : cbos) {
            cbo.addActionListener(cboSort);
        }
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

    private void refresh(boolean notify) {
        listLH = daoLH.selectAll();
        listNSX = daoNSX.selectAll();
        listDongSP = daoDongSP.selectAll();
        listSP = daoSP.selectAll();

        fillTable(tblLoaiHang, listLH);
        fillTable(tblNSX, listNSX);
        fillTable(tblDongSP, listDongSP);
        fillTable(tblSP, listSP);

        fillComboBox();

        txtSearchLH.setText("");
        txtSearchNSX.setText("");
        txtSearchSP.setText("");

        if (notify) {
            MsgBox.inform("Đã cập nhật lại toàn bộ dữ liệu");
        }
    }

    private void insertRow(Table table, Entity e) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int index = table.getSelectedRow();
        if (index >= 0) {
            model.removeRow(index);
            model.insertRow(index, getInfo(e));
            MsgBox.inform("Cập nhật thành công");
        } else {
            model.addRow(getInfo(e));
            MsgBox.inform("Thêm mới thành công");
        }
    }

    private void deleteRow(Entity e) {
        int confirm = MsgBox.confirm("Bạn có thực sự muốn xoá?", false);
        if (confirm != 0) {
            return;
        }
        switch (e.getClass().getSimpleName()) {
            case "LoaiHang":
                daoLH.delete(((LoaiHang) e).getIdLH());
                deleteRow(tblLoaiHang);
                listLH.remove((LoaiHang) e);
                break;
            case "NhaSanXuat":
                daoNSX.delete(((NhaSanXuat) e).getIdNSX());
                deleteRow(tblNSX);
                listNSX.remove((NhaSanXuat) e);
                break;
            case "DongSanPham":
                daoDongSP.delete(((DongSanPham) e).getIdDongSP());
                deleteRow(tblDongSP);
                listDongSP.remove((DongSanPham) e);
                break;
            case "SanPham":
                daoSP.delete(((SanPham) e).getSku());
                deleteRow(tblSP);
                listSP.remove((SanPham) e);
                break;
        }
        MsgBox.inform("Xoá thành công");
    }

    private void deleteRow(Table table) {
        CellEditor editor = table.getCellEditor();
        if (editor != null) {
            table.getCellEditor().stopCellEditing();
        }
        int index = table.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        if (index >= 0) {
            model.removeRow(index);
        }
    }

    private void showPanel(Entity e) {
        switch (e.getClass().getSimpleName()) {
            case "LoaiHang":
                panel = new PanelLoaiHang(listLH);
                panel.setDoneListener(event -> {
                    Entity entity = (Entity) panel.getForm();
                    if (entity != null) {
                        int count = daoLH.insert((LoaiHang) entity);
                        if (count < 0) {
                            MsgBox.error("Cập nhật dữ liệu thất bại");
                            return;
                        }
                        insertRow(tblLoaiHang, entity);
                        listLH.add((LoaiHang) e);
                        ((JDialog) panel.getTopLevelAncestor()).dispose();
                    }
                });
                break;
            case "NhaSanXuat":
                panel = new PanelNhaSanXuat(listNSX);
                panel.setDoneListener(event -> {
                    Entity entity = (Entity) panel.getForm();
                    if (entity != null) {
                        int count = daoNSX.insert((NhaSanXuat) entity);
                        if (count < 0) {
                            MsgBox.error("Cập nhật dữ liệu thất bại");
                            return;
                        }
                        insertRow(tblNSX, entity);
                        listNSX.add((NhaSanXuat) e);
                        ((JDialog) panel.getTopLevelAncestor()).dispose();
                    }
                });
                break;
            case "DongSanPham":
                panel = new PanelDongSanPham(listDongSP, listNSX, listLH);
                panel.setDoneListener(event -> {
                    Entity entity = (Entity) panel.getForm();
                    if (entity != null) {
                        int count = daoDongSP.insert((DongSanPham) entity);
                        if (count < 0) {
                            MsgBox.error("Cập nhật dữ liệu thất bại");
                            return;
                        }
                        insertRow(tblDongSP, entity);
                        ((JDialog) panel.getTopLevelAncestor()).dispose();
                    }
                });
                break;
            case "SanPham":
                panel = new SanPhamPanel(listSP,listNSX, listDongSP);
                break;
        }
        if (panel != null) {
            panel.setEntity(e);
            XDialog.getDialog((JFrame) this.getTopLevelAncestor(), panel).setVisible(true);
        }
    }

    private Object[] getInfo(Entity e) {
        if (e instanceof LoaiHang) {
            LoaiHang lh = (LoaiHang) e;
            return new Object[]{
                lh.getIdLH(),
                lh.getTenLoaiHang(),
                new ModelAction(lh, modelEvent)
            };
        }
        if (e instanceof NhaSanXuat) {
            NhaSanXuat nsx = (NhaSanXuat) e;
            return new Object[]{
                nsx.getIdNSX(),
                nsx.getTenNSX(),
                new ModelAction(nsx, modelEvent)
            };
        }
        if (e instanceof DongSanPham) {
            DongSanPham dsp = (DongSanPham) e;
            NhaSanXuat nsx = findNSX(dsp.getIdNSX());
            LoaiHang lh = findLH(dsp.getIdLH());
            return new Object[]{
                dsp.getIdDongSP(),
                dsp.getTenDongSP(),
                nsx == null ? "" : nsx.getTenNSX(),
                lh == null ? "" : lh.getTenLoaiHang(),
                new ModelAction(dsp, modelEvent)
            };
        }
        if (e instanceof SanPham) {
            DecimalFormat format = new DecimalFormat("#,##0.00 VND");
            SanPham sp = (SanPham) e;
            return new Object[]{
                sp.getSku(),
                sp.getTenSP(),
                sp.getMauSac(),
                sp.getPhanKhoi(),
                sp.getThoiGianBH() + " tháng",
                sp.getDiaChiSX(),
                format.format(sp.getGiaTien()),
                sp.getDoiXe(),
                sp.getHinh(),
                sp.getIdDongSP(),
                sp.getIdNV(),
                new ModelAction(sp, modelEvent)
            };
        }
        return null;
    }

    private NhaSanXuat findNSX(String idNSX) {
        return listNSX.stream()
                .filter(nsx -> nsx.getIdNSX().equals(idNSX))
                .findFirst().orElse(null);
    }

    private LoaiHang findLH(String idLH) {
        return listLH.stream()
                .filter(lh -> lh.getIdLH().equals(idLH))
                .findFirst().orElse(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        pnlThongKe_SP = new javax.swing.JPanel();
        lblLoaiHang = new javax.swing.JLabel();
        lblNSX = new javax.swing.JLabel();
        lblDongSP = new javax.swing.JLabel();
        lblSanPham = new javax.swing.JLabel();
        btnAddSP = new javax.swing.JButton();
        btnAddLH = new com.swingx.Button();
        btnAddNSX = new com.swingx.Button();
        btnAddDongSP = new com.swingx.Button();
        txtSearchSP = new com.swingx.SearchTextField();
        txtSearchLH = new com.swingx.SearchTextField();
        txtSearchNSX = new com.swingx.SearchTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLoaiHang = new com.ultramotor.component.table.Table();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblNSX = new com.ultramotor.component.table.Table();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblSP = new com.ultramotor.component.table.Table();
        cboLH = new com.swingx.ComboBoxSuggestion();
        cboNSX = new com.swingx.ComboBoxSuggestion();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblDongSP = new com.ultramotor.component.table.Table();
        btnRefresh = new com.swingx.Button();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlThongKe_SP.setBackground(new java.awt.Color(255, 255, 255));

        lblLoaiHang.setText("Loại hàng");

        lblNSX.setText("Nhà sản xuất");

        lblDongSP.setText("Dòng sản phẩm");

        lblSanPham.setText("Sản phẩm");

        btnAddSP.setText("Tạo mới");

        btnAddLH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/plus.png"))); // NOI18N
        btnAddLH.setText("Add");

        btnAddNSX.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/plus.png"))); // NOI18N
        btnAddNSX.setText("Add");

        btnAddDongSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/plus.png"))); // NOI18N
        btnAddDongSP.setText("Add");

        tblLoaiHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Id_LH", "Tên Loại Hàng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblLoaiHang);

        tblNSX.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Id_NSX", "Tên NSX"
            }
        ));
        jScrollPane3.setViewportView(tblNSX);

        tblSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "SKU", "Tên SP", "Màu Sắc", "Phân Khối", "Thời gian BH", "Địa chỉ SX", "Giá Tiền", "Mô tả", "Đời xe", "Hình", "Id_DSP", "Id_NV"
            }
        ));
        jScrollPane4.setViewportView(tblSP);

        tblDongSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Id_NSX", "Tên NSX"
            }
        ));
        jScrollPane6.setViewportView(tblDongSP);

        btnRefresh.setBackground(new java.awt.Color(51, 204, 0));
        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ultramotor/img/icon/refresh_25px.png"))); // NOI18N

        javax.swing.GroupLayout pnlThongKe_SPLayout = new javax.swing.GroupLayout(pnlThongKe_SP);
        pnlThongKe_SP.setLayout(pnlThongKe_SPLayout);
        pnlThongKe_SPLayout.setHorizontalGroup(
            pnlThongKe_SPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlThongKe_SPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlThongKe_SPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlThongKe_SPLayout.createSequentialGroup()
                        .addGroup(pnlThongKe_SPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlThongKe_SPLayout.createSequentialGroup()
                                .addGroup(pnlThongKe_SPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlThongKe_SPLayout.createSequentialGroup()
                                        .addGroup(pnlThongKe_SPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pnlThongKe_SPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                .addGroup(pnlThongKe_SPLayout.createSequentialGroup()
                                                    .addComponent(lblLoaiHang, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(119, 119, 119)
                                                    .addComponent(txtSearchLH, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(pnlThongKe_SPLayout.createSequentialGroup()
                                                .addGap(113, 113, 113)
                                                .addComponent(btnAddLH, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(pnlThongKe_SPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlThongKe_SPLayout.createSequentialGroup()
                                                .addGroup(pnlThongKe_SPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlThongKe_SPLayout.createSequentialGroup()
                                                        .addComponent(lblNSX)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(txtSearchNSX, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(18, 18, 18))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlThongKe_SPLayout.createSequentialGroup()
                                                .addComponent(btnAddDongSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(182, 182, 182))))
                                    .addGroup(pnlThongKe_SPLayout.createSequentialGroup()
                                        .addGap(358, 358, 358)
                                        .addComponent(lblDongSP)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(pnlThongKe_SPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlThongKe_SPLayout.createSequentialGroup()
                                        .addGap(148, 148, 148)
                                        .addComponent(btnAddNSX, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlThongKe_SPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlThongKe_SPLayout.createSequentialGroup()
                                            .addComponent(cboNSX, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(cboLH, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(49, 49, 49))
                                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(51, 51, 51))
                            .addGroup(pnlThongKe_SPLayout.createSequentialGroup()
                                .addComponent(lblSanPham)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnAddSP)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(60, 60, 60))
                    .addGroup(pnlThongKe_SPLayout.createSequentialGroup()
                        .addGroup(pnlThongKe_SPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtSearchSP, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1178, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(67, Short.MAX_VALUE))))
        );
        pnlThongKe_SPLayout.setVerticalGroup(
            pnlThongKe_SPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlThongKe_SPLayout.createSequentialGroup()
                .addGroup(pnlThongKe_SPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlThongKe_SPLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(pnlThongKe_SPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNSX)
                            .addComponent(txtSearchLH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblLoaiHang, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlThongKe_SPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboLH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cboNSX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtSearchNSX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThongKe_SPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlThongKe_SPLayout.createSequentialGroup()
                        .addGroup(pnlThongKe_SPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlThongKe_SPLayout.createSequentialGroup()
                                .addGroup(pnlThongKe_SPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(btnAddLH, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlThongKe_SPLayout.createSequentialGroup()
                                .addGap(186, 186, 186)
                                .addComponent(btnAddDongSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(16, 16, 16)
                        .addComponent(lblDongSP, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addGroup(pnlThongKe_SPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlThongKe_SPLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(pnlThongKe_SPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblSanPham)
                                    .addComponent(btnAddSP))
                                .addGap(13, 13, 13))
                            .addGroup(pnlThongKe_SPLayout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addComponent(txtSearchSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pnlThongKe_SPLayout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(btnAddNSX, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(167, 167, 167)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(150, 150, 150))
        );

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(pnlThongKe_SP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlThongKe_SP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        add(pnlMain, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.swingx.Button btnAddDongSP;
    private com.swingx.Button btnAddLH;
    private com.swingx.Button btnAddNSX;
    private javax.swing.JButton btnAddSP;
    private com.swingx.Button btnRefresh;
    private com.swingx.ComboBoxSuggestion cboLH;
    private com.swingx.ComboBoxSuggestion cboNSX;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel lblDongSP;
    private javax.swing.JLabel lblLoaiHang;
    private javax.swing.JLabel lblNSX;
    private javax.swing.JLabel lblSanPham;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlThongKe_SP;
    private com.ultramotor.component.table.Table tblDongSP;
    private com.ultramotor.component.table.Table tblLoaiHang;
    private com.ultramotor.component.table.Table tblNSX;
    private com.ultramotor.component.table.Table tblSP;
    private com.swingx.SearchTextField txtSearchLH;
    private com.swingx.SearchTextField txtSearchNSX;
    private com.swingx.SearchTextField txtSearchSP;
    // End of variables declaration//GEN-END:variables

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame fr = new JFrame();
            fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            fr.getContentPane().add(new QuanLySanPhamPanel());
            fr.pack();
            fr.setVisible(true);
        });
    }
}

abstract class MyPanel<Entity> extends JPanel {

    protected Entity entity;

    protected List<? extends Entity> list;

    public MyPanel(List<? extends Entity> list) {
        this.list = list;
    }

    public abstract void setForm(Entity e);

    public abstract Entity getForm();

    public abstract void setDoneListener(ActionListener doneListener);

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
        if (entity != null) {
            setForm(entity);
        }
    }

    protected boolean validate(JTextField... fields) {
        for (JTextField field : fields) {
            if (field.getText().isEmpty()) {
                MsgBox.error(String.format("Không được để trống %s", field.getName()));
                field.requestFocus();
                return false;
            }
        }
        return true;
    }
}

class PanelLoaiHang extends MyPanel<LoaiHang> {

    private final JLabel lblTitle;
    private final JLabel lblMaLH;
    private final JLabel lblTenLH;

    private final TextField txtMaLH;
    private final TextField txtTenLH;

    private final Button btn;

    public PanelLoaiHang(List<LoaiHang> list) {
        super(list);
        setLayout(new MigLayout("insets 40 60 40 60, fillx, wrap 1", "[center, fill]", "10[center]10[center]10"));
        lblTitle = new JLabel("LOẠI HÀNG");
        lblTitle.setFont(new Font("Segoe UI", 0, 24));

        lblMaLH = new JLabel("Mã Loại Hàng");
        lblTenLH = new JLabel("Tên Loại Hàng");

        txtMaLH = new TextField();
        txtTenLH = new TextField();

        txtMaLH.setOnlyField(true);
        txtTenLH.setOnlyField(true);

        txtMaLH.setName("mã loại hàng");
        txtTenLH.setName("tên loại hàng");

        btn = new Button();
        btn.setText("Xong");
        btn.setRadius(15);
        btn.setBackground(new Color(200, 200, 200));
        this.add(lblTitle, " w 200!, gapbottom 10");
        this.add(lblMaLH, "w 200!");
        this.add(txtMaLH, "w 200!");
        this.add(lblTenLH, "w 200!");
        this.add(txtTenLH, "w 200!");
        this.add(btn, "w 100!, h 40!, gaptop 20, gapbottom 20");
        this.setBackground(new Color(250, 250, 250));
        this.setSize(200, 200);
    }

    @Override
    public void setForm(LoaiHang e) {
        txtMaLH.setText(e.getIdLH());
        txtTenLH.setText(e.getTenLoaiHang());
        txtMaLH.setEditable(e.getIdLH() == null);
    }

    @Override
    public LoaiHang getForm() {
        if (validateForm()) {
            return new LoaiHang(txtMaLH.getText(), txtTenLH.getText());
        }
        return null;
    }

    public boolean validateForm() {
        if (validate(txtMaLH, txtTenLH)) {
            if (entity.getIdLH() == null) {
                boolean exists = list.stream().anyMatch(lh -> lh.getIdLH().equals(txtMaLH.getText()));
                if (exists) {
                    MsgBox.error("Mã loại hàng đã tồn tại!");
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public void setDoneListener(ActionListener doneListener) {
        btn.addActionListener(doneListener);
    }
}

class PanelNhaSanXuat extends MyPanel<NhaSanXuat> {

    private final JLabel lblTitle;
    private final JLabel lblMaNSX;
    private final JLabel lblTenNSX;

    private final TextField txtMaNSX;
    private final TextField txtTenNSX;

    private final Button btn;

    public PanelNhaSanXuat(List<NhaSanXuat> list) {
        super(list);
        setLayout(new MigLayout("insets 40 60 40 60, fillx, wrap 1", "[center, fill]", "10[center]10[center]10"));
        lblTitle = new JLabel("NHÀ SẢN XUẤT");
        lblTitle.setFont(new Font("Segoe UI", 0, 24));

        lblMaNSX = new JLabel("Mã Nhà Sản Xuất");
        lblTenNSX = new JLabel("Tên Nhà Sản Xuất");

        txtMaNSX = new TextField();
        txtTenNSX = new TextField();

        txtMaNSX.setOnlyField(true);
        txtTenNSX.setOnlyField(true);

        btn = new Button();
        btn.setText("Xong");
        btn.setRadius(15);
        btn.setBackground(new Color(200, 200, 200));
        this.add(lblTitle, " w 200!, gapbottom 10");
        this.add(lblMaNSX, "w 200!");
        this.add(txtMaNSX, "w 200!");
        this.add(lblTenNSX, "w 200!");
        this.add(txtTenNSX, "w 200!");
        this.add(btn, "w 100!, h 40!, gaptop 20, gapbottom 20");
        this.setBackground(new Color(250, 250, 250));
        this.setSize(200, 200);
    }

    @Override
    public void setForm(NhaSanXuat e) {
        txtMaNSX.setText(e.getIdNSX());
        txtTenNSX.setText(e.getTenNSX());
        txtMaNSX.setEditable(e.getIdNSX() == null);
    }

    @Override
    public NhaSanXuat getForm() {
        if (validateForm()) {
            return new NhaSanXuat(txtMaNSX.getText(), txtTenNSX.getText());
        }
        return null;
    }

    public boolean validateForm() {
        if (validate(txtMaNSX, txtTenNSX)) {
            if (entity.getIdNSX() == null) {
                boolean exists = list.stream().anyMatch(nsx -> nsx.getIdNSX().equals(txtMaNSX.getText()));
                if (exists) {
                    MsgBox.error("Mã nhà sản xuất đã tồn tại!");
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public void setDoneListener(ActionListener doneListener) {
        btn.addActionListener(doneListener);
    }
}

class PanelDongSanPham extends MyPanel<DongSanPham> {

    private final JLabel lblTitle;
    private final JLabel lblMaDongSP;
    private final JLabel lblTenDongSP;
    private final JLabel lblNSX;
    private final JLabel lblLH;
    private final TextField txtMaDongSP;
    private final TextField txtTenDongSP;
    private final ComboBoxSuggestion cboNSX;
    private final ComboBoxSuggestion cboLH;
    private List<NhaSanXuat> listNSX;
    private List<LoaiHang> listLH;
    private final Button btn;

    public PanelDongSanPham(List<DongSanPham> list) {
        super(list);
        setLayout(new MigLayout("insets 40 60 40 60, fillx, wrap 2", "[center, fill]30[center, fill]", "10[center]10[center]10"));
        lblTitle = new JLabel("DÒNG SẢN PHẨM");
        lblTitle.setFont(new Font("Segoe UI", 0, 24));

        lblMaDongSP = new JLabel("Mã Dòng Sản Phẩm");
        lblTenDongSP = new JLabel("Tên Dòng Sản Phẩm");

        txtMaDongSP = new TextField();
        txtTenDongSP = new TextField();

        txtMaDongSP.setOnlyField(true);
        txtTenDongSP.setOnlyField(true);

        lblNSX = new JLabel("Nhà Sản Xuất");
        lblLH = new JLabel("Loại Hàng");

        cboNSX = new ComboBoxSuggestion();
        cboLH = new ComboBoxSuggestion();

        cboNSX.setSize(txtMaDongSP.getSize());
        cboLH.setSize(txtMaDongSP.getSize());

        btn = new Button();
        btn.setText("Xong");
        btn.setRadius(15);
        btn.setBackground(new Color(200, 200, 200));
        this.add(lblTitle, " w 200!, gapbottom 10, spanx 2");

        this.add(lblMaDongSP, "w 200!");
        this.add(lblNSX, "w 200!");
        this.add(txtMaDongSP, "w 200!");
        this.add(cboNSX, "w 200!, h " + txtMaDongSP.getPreferredSize().height);

        this.add(lblTenDongSP, "w 200!");
        this.add(lblLH, "w 200!");
        this.add(txtTenDongSP, "w 200!");
        this.add(cboLH, "w 200!, h " + txtMaDongSP.getPreferredSize().height);

        this.add(btn, "w 100!, h 40!, gaptop 20, gapbottom 20, spanx 2");
        this.setBackground(new Color(250, 250, 250));
        this.setSize(200, 200);
    }

    public PanelDongSanPham(List<DongSanPham> list, List<NhaSanXuat> listNSX, List<LoaiHang> listLH) {
        this(list);
        this.listNSX = listNSX;
        this.listLH = listLH;
        fillComboBox();

    }

    private void fillComboBox() {
        cboNSX.setModel(new DefaultComboBoxModel(listNSX.toArray()));
        cboLH.setModel(new DefaultComboBoxModel(listLH.toArray()));
    }

    @Override
    public void setForm(DongSanPham e) {
        txtMaDongSP.setText(e.getIdNSX());
        txtTenDongSP.setText(e.getTenDongSP());
        txtMaDongSP.setEditable(e.getIdNSX() == null);
        if (listNSX != null) {
            cboNSX.setSelectedItem(
                    listNSX
                            .stream()
                            .filter(nsx -> nsx.getIdNSX().equals(e.getIdNSX()))
                            .findFirst()
                            .orElse(listNSX.get(0)));
        }
        if (listLH != null) {
            cboLH.setSelectedItem(
                    listLH
                            .stream()
                            .filter(lh -> lh.getIdLH().equals(e.getIdLH()))
                            .findFirst()
                            .orElse(listLH.get(0)));
        }
    }

    @Override
    public DongSanPham getForm() {
        if (validateForm()) {
            return new DongSanPham(
                    txtMaDongSP.getText(),
                    txtTenDongSP.getText(),
                    ((NhaSanXuat) cboNSX.getSelectedItem()).getIdNSX(),
                    ((LoaiHang) cboLH.getSelectedItem()).getIdLH());
        }
        return null;
    }

    public boolean validateForm() {
        if (validate(txtMaDongSP, txtTenDongSP)) {
            if (entity.getIdDongSP() == null) {
                boolean exists = list.stream().anyMatch(dsp -> dsp.getIdDongSP().equals(txtMaDongSP.getText()));
                if (exists) {
                    MsgBox.error("Mã dòng sản phẩm đã tồn tại!");
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public void setDoneListener(ActionListener doneListener) {
        btn.addActionListener(doneListener);
    }
}
