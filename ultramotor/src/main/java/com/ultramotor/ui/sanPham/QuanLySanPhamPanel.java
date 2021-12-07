package com.ultramotor.ui.sanPham;

import com.swingx.Button;
import com.swingx.ComboBoxSuggestion;
import com.swingx.TextField;
import com.swingx.table.ModelAction;
import com.swingx.table.ModelEvent;
import com.swingx.table.Table;
import com.ultramotor.dao.DongSanPhamDAO;
import com.ultramotor.dao.LoaiHangDAO;
import com.ultramotor.dao.NhaSanXuatDAO;
import com.ultramotor.dao.SanPhamDAO;
import com.ultramotor.entity.DongSanPham;
import com.ultramotor.entity.Entity;
import com.ultramotor.entity.LoaiHang;
import com.ultramotor.entity.NhaSanXuat;
import com.ultramotor.entity.SanPham;
import com.ultramotor.util.MsgBox;
import com.ultramotor.util.XDialog;
import java.awt.Color;
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
//        addBtnAddListener(btnAddLH, tblLoaiHang, new LoaiHang());
//        addBtnAddListener(btnAddNSX, tblNSX, new NhaSanXuat());
//        addBtnAddListener(btnAddDongSP, tblDongSP, new DongSanPham());
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

//        initTable(colsLH, tblLoaiHang);
//        initTable(colsNSX, tblNSX);
//        initTable(colsDongSP, tblDongSP);
//        initTable(colsSP, tblSP);
//
//        addRowSorter(tblLoaiHang, txtSearchLH);
//        addRowSorter(tblNSX, txtSearchNSX);
//        addRowSorter(tblSP, txtSearchSP);
//        addRowSorter(tblDongSP, cboNSX, cboLH);
    }

    private void fillComboBox() {
        fillComboBoxNSX();
        fillComboBoxLH();
    }

    private void fillComboBoxNSX() {
        if (listNSX != null) {
//            cboNSX.setModel(new DefaultComboBoxModel(listNSX.toArray()));
//            cboNSX.insertItemAt(new NhaSanXuat(null, "Tất cả"), 0);
//            cboNSX.setSelectedIndex(0);
        }
    }

    private void fillComboBoxLH() {
        if (listLH != null) {
//            cboLH.setModel(new DefaultComboBoxModel(listLH.toArray()));
//            cboLH.insertItemAt(new LoaiHang(null, "Tất cả"), 0);
//            cboLH.setSelectedIndex(0);
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

//        fillTable(tblLoaiHang, listLH);
//        fillTable(tblNSX, listNSX);
//        fillTable(tblDongSP, listDongSP);
        fillTable(tblSP, listSP);

        fillComboBox();

//        txtSearchLH.setText("");
//        txtSearchNSX.setText("");
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
//                deleteRow(tblLoaiHang);
                listLH.remove((LoaiHang) e);
                break;
            case "NhaSanXuat":
                daoNSX.delete(((NhaSanXuat) e).getIdNSX());
//                deleteRow(tblNSX);
                listNSX.remove((NhaSanXuat) e);
                break;
            case "DongSanPham":
                daoDongSP.delete(((DongSanPham) e).getIdDongSP());
//                deleteRow(tblDongSP);
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
//                        insertRow(tblLoaiHang, entity);
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
//                        insertRow(tblNSX, entity);
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
//                        insertRow(tblDongSP, entity);
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

        btnRefresh = new com.swingx.Button();
        pnlMain = new javax.swing.JPanel();
        pnlThongKe_SP = new javax.swing.JPanel();
        panelBorder4 = new com.swingx.PanelBorder();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblSP = new com.swingx.table.Table();
        txtSearchSP = new com.swingx.SearchTextField();
        btnAddSP = new com.swingx.Button();
        jLabel1 = new javax.swing.JLabel();

        btnRefresh.setBackground(new java.awt.Color(51, 204, 0));
        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ultramotor/icon/refresh_25px.png"))); // NOI18N

        pnlThongKe_SP.setBackground(new java.awt.Color(255, 255, 255));

        panelBorder4.setBackground(new java.awt.Color(255, 255, 255));
        panelBorder4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Sản Phẩm", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Papyrus", 1, 20), new java.awt.Color(0, 174, 114))); // NOI18N
        panelBorder4.setMinimumSize(new java.awt.Dimension(100, 100));
        panelBorder4.setName(""); // NOI18N
        panelBorder4.setPreferredSize(new java.awt.Dimension(382, 314));

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

        btnAddSP.setForeground(new java.awt.Color(255, 255, 255));
        btnAddSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ultramotor/icon/edit.png"))); // NOI18N

        javax.swing.GroupLayout panelBorder4Layout = new javax.swing.GroupLayout(panelBorder4);
        panelBorder4.setLayout(panelBorder4Layout);
        panelBorder4Layout.setHorizontalGroup(
            panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1236, Short.MAX_VALUE)
                    .addGroup(panelBorder4Layout.createSequentialGroup()
                        .addComponent(txtSearchSP, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAddSP, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelBorder4Layout.setVerticalGroup(
            panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSearchSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAddSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Serif", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 174, 114));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Quản Lý Sản Phẩm");

        javax.swing.GroupLayout pnlThongKe_SPLayout = new javax.swing.GroupLayout(pnlThongKe_SP);
        pnlThongKe_SP.setLayout(pnlThongKe_SPLayout);
        pnlThongKe_SPLayout.setHorizontalGroup(
            pnlThongKe_SPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongKe_SPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlThongKe_SPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlThongKe_SPLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(panelBorder4, javax.swing.GroupLayout.PREFERRED_SIZE, 1264, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlThongKe_SPLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        pnlThongKe_SPLayout.setVerticalGroup(
            pnlThongKe_SPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlThongKe_SPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBorder4, javax.swing.GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)
                .addGap(165, 165, 165))
        );

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addComponent(pnlThongKe_SP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 504, Short.MAX_VALUE))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlThongKe_SP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.swingx.Button btnAddSP;
    private com.swingx.Button btnRefresh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane4;
    private com.swingx.PanelBorder panelBorder4;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlThongKe_SP;
    private com.swingx.table.Table tblSP;
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
