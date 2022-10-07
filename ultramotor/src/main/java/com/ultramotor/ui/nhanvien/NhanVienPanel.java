package com.ultramotor.ui.nhanvien;

import com.microsoft.sqlserver.jdbc.SQLServerDataTable;
import com.swingx.PopupMenuItem;
import com.swingx.table.ModelAction;
import com.swingx.table.ModelEvent;
import com.ultramotor.dao.NhanVienDAO;
import com.ultramotor.entity.NhanVien;
import com.ultramotor.util.Auth;
import com.ultramotor.util.MsgBox;
import com.ultramotor.util.UltraExporter;
import com.ultramotor.util.XDate;
import com.ultramotor.util.XDialog;
import com.ultramotor.util.XExcel;
import com.ultramotor.util.XMail;
import com.ultramotor.util.XPdf;
import com.ultramotor.util.XReport;
import com.ultramotor.util.XValidate;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.CellEditor;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import net.sf.jasperreports.engine.JRException;

public class NhanVienPanel extends javax.swing.JPanel {

    NhanVienDAO dao = new NhanVienDAO();
    //    int row = -1; //vị trí hàng được chọn trên table
    ModelEvent event;
    private NhanVienInfoPanel pnlInfo;
    private SendMailPanel pnlSendMail;
    private JPopupMenu popup;
    private DefaultTableModel model;
    private boolean hasEdit = false;
    private static int sizeNV;
    private ActionListener exportEvent;
    private DecimalFormat numberFormat;

    public NhanVienPanel() {
        initComponents();
        init();
    }

    private void init() {
        new Thread(() -> initTable()).start();
        numberFormat = new DecimalFormat("#,##0.00");
        pnlInfo = new NhanVienInfoPanel();
        pnlSendMail = new SendMailPanel();
        event = new ModelEvent<NhanVien>() {
            @Override
            public void update(NhanVien e) {
                pnlInfo.setForm(e);
                showPanel("info");
                hasEdit = true;
            }

            @Override
            public void delete(NhanVien e) {
                deleteNV(e.getIdNV());
            }
        };
//        fillTable(dao.selectAll());
//        sizeNV = model.getRowCount();
        addListeners();
        popup = new JPopupMenu();
        popup.add(new PopupMenuItem("Xuất PDF", createIcon("profile_25px.png"), createIcon("profile_white_25px.png"), exportEvent));
        popup.add(new PopupMenuItem("Xuất Excel", createIcon("change_25px.png"), createIcon("change_white_25px.png"), exportEvent));
    }

    private void initTable() {
        Object[] columns = {"Select", "ID", "Họ Và Tên", "Ngày Sinh", "Giới Tính", "Địa chỉ", "Số ĐT", "Email", "Lương", "Hình", "Vai Trò", "Actions"};
        model = new DefaultTableModel(columns, 0);
        tblNhanVien.setModel(model);
        tblNhanVien.getColumnModel().getColumn(tblNhanVien.getColumnCount() - 1).setMaxWidth(100);
        TableRowSorter<TableModel> sorter = new TableRowSorter(model);
        tblNhanVien.setRowSorter(sorter);
        txtTimKiem.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String text = txtTimKiem.getText().trim();
                if (text.isEmpty()) {
                    sorter.setRowFilter(null);
                } else {
                    List<RowFilter<Object, Object>> filters = new ArrayList<>();
                    filters.add(RowFilter.regexFilter("(?i)" + text.toUpperCase()));
                    filters.add(RowFilter.regexFilter("(?i)" + text));
                    sorter.setRowFilter(RowFilter.orFilter(filters));
                }
            }
        });
        tblNhanVien.fixTable((JScrollPane) tblNhanVien.getParent().getParent());

    }

    private void save() {
        try {
            SQLServerDataTable dataTable = NhanVien.getDataServerTable();
            for (int i = 0; i < tblNhanVien.getRowCount(); i++) {
                dataTable.addRow(getInfo(i));
            }
            dao.mergeTable(dataTable);
            MsgBox.inform("Lưu dữ liệu thành công");
            hasEdit = false;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void deleteNV(String maNV) {
        if (!Auth.isManager()) {
            MsgBox.inform("Bạn không có quyền xóa nhân viên!");
        } else {
            if (maNV.equals(Auth.user.getIdNV())) {
                MsgBox.inform("Bạn không được xóa chính bạn!");
            } else if (MsgBox.confirm("Bạn thực sự muốn xóa nhân viên này?", false) == 0) {
                int index = getIndexNhanVien(maNV);
                if (index != -1) {
                    CellEditor editor = tblNhanVien.getCellEditor();
                    if (editor != null) {
                        editor.stopCellEditing();
                    }
                    model.removeRow(index);
                    hasEdit = true;
                }
            }
        }
    }

    private void sendMail() {
        if (sizeNV == 0) {
            sendMail("");
            return;
        }
        Set<String> emails = new HashSet<>();
        List<Integer> list = getSelectedRows();
        if (list.isEmpty()) {
            int confirm = MsgBox.confirm("Bạn chưa chọn nhân viên muốn gửi mail. Bạn có muốn gửi mail cho tất cả nhân viên?", true);
            if (confirm == MsgBox.CANCEL_OPTION) {
                return;
            } else if (confirm == MsgBox.NO_OPTION) {
                sendMail("");
                return;
            }
        }
        //nếu người dùng đã chọn nhân viên hoặc chọn gửi cho tất cả nhân viên
        for (int i = 0; i < tblNhanVien.getRowCount(); i++) {
            if (list.isEmpty() || list.contains(i)) {
                String email = (String) tblNhanVien.getValueAt(i, 7);
                emails.add(XValidate.validateEmail(email) ? email : "");
            }
        }
        emails.remove("");
        sendMail(emails.toArray(new String[emails.size()]));
    }

    private void sendMail(String... emails) {
        pnlSendMail.setNguoiGui(Auth.user == null ? "" : Auth.user.getEmail());
        pnlSendMail.setNguoiNhan(emails);
        showPanel("sendMail");
    }

    private Object[] getInfo(int index) {
        NhanVien nv = (NhanVien) ((ModelAction) tblNhanVien.getValueAt(index, tblNhanVien.getColumnCount() - 1)).getEntity();
        return new Object[]{nv.getIdNV(), nv.getHoNV(), nv.getTenNV(),
                XDate.toString(nv.getNgaySinh(), "yyyy-MM-dd"), nv.getGioiTinh(), nv.getDiaChi(),
                nv.getSdt(), nv.getEmail(), nv.getLuong(),
                nv.getHinh(), nv.getVaiTro(), nv.getMatKhau() != null ? nv.getMatKhau() : getRandomPassword(nv),
                nv.getGhiChu()};
    }

    private Object[] getInfo(NhanVien nv) {
        return new Object[]{
                false, nv, nv.getHoNV() + " " + nv.getTenNV(),
                XDate.toString(nv.getNgaySinh(), "dd-MM-yyyy"),
                nv.getGioiTinh() ? "Nam" : "Nữ", nv.getDiaChi(),
                nv.getSdt(), nv.getEmail(), numberFormat.format(nv.getLuong()),
                nv.getHinh(), nv.getVaiTro(),
                new ModelAction(nv, event)
        };
    }

    void fillTable(List<NhanVien> list) {
        model.setRowCount(0); // xóa tất cả các hàng trên jtable
        list.forEach(nv -> {
            model.addRow(getInfo(nv));
        });
    }

    private List<Integer> getSelectedRows() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < tblNhanVien.getRowCount(); i++) {
            if ((boolean) tblNhanVien.getValueAt(i, 0)) {
                list.add(i);
            }
        }
        return list;
    }

    //hiển thị card theo card name
    public void showPanel(String name) {
        new Thread(() -> {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
            }
            JPanel panel = null;
            switch (name) {
                case "info":
                    panel = pnlInfo;
                    break;
                case "sendMail":
                    panel = pnlSendMail;
                    break;
            }
            if (panel != null) {
                XDialog.getDialog((JFrame) this.getTopLevelAncestor(), panel).setVisible(true);
            }
        }).start();
    }

    private void addListeners() {
        btnThemMoi.addActionListener((ActionEvent e) -> {
            pnlInfo.setForm(null);
            showPanel("info");
        });

        btnGuiMail.addActionListener((ActionEvent e) -> {
            sendMail();
        });

        btnSave.addActionListener((ActionEvent e) -> {
            save();
        });
        pnlInfo.setMailListener((ActionEvent e) -> {
            sendMail(pnlInfo.getNhanVien().getEmail());
        });

        pnlInfo.setUpdateListener((ActionEvent e) -> {
            NhanVien nv = pnlInfo.getForm();
            if (nv == null) {
                return;
            }

            pnlInfo.setForm(nv);
            int index = getIndexNhanVien(nv.getIdNV());
            if (index == -1) {
                model.addRow(getInfo(nv));
                MsgBox.inform("Thêm mới nhân viên thành công");
                index = tblNhanVien.getRowCount() - 1;
                addNV();
                ((JDialog) pnlInfo.getTopLevelAncestor()).dispose();
            } else {
                model.removeRow(index);
                model.insertRow(index, getInfo(nv));
                MsgBox.inform("Cập nhật nhân viên thành công");
            }
            tblNhanVien.setRowSelectionInterval(index, index); //đật hàng chọn trên bảng
            tblNhanVien.scrollRectToVisible(new java.awt.Rectangle(tblNhanVien.getCellRect(index, 0, true))); //di chuyển thanh lăn tới vị trí hàng chọn
            hasEdit = true;
        });

        btnExport.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent me) {
                if (SwingUtilities.isLeftMouseButton(me)) {
                    popup.show(btnExport, btnExport.getWidth() - 150, 40);
                }
            }
        });

        exportEvent = (event) -> {
            export(event.getActionCommand());
        };
        this.addAncestorListener(new AncestorListener() {
            @Override
            public void ancestorAdded(AncestorEvent event) {
                new Thread(NhanVienPanel.this::refresh).start();
            }

            @Override
            public void ancestorRemoved(AncestorEvent event) {
            }

            @Override
            public void ancestorMoved(AncestorEvent event) {
            }
        });
    }

    private void refresh() {
        fillTable(dao.selectAll());
        sizeNV = model.getRowCount();
    }

    private void export(String name) {
        if (sizeNV == 0) {
            MsgBox.error("Danh sách nhân viên trống");
            return;
        }
        File file;
        if (name.contains("PDF")) {
            file = XPdf.showSaveDialog((JFrame) this.getTopLevelAncestor(), "DSNV" + ((int) (Math.random() * 1000)) + ".pdf");
            if (file == null) {
                return;
            }
            try {
                XReport.createNhanVienReport(file);
            } catch (JRException ex) {
                MsgBox.error("Xuất danh sách thất bại");
                return;
            }
        } else {
            file = XExcel.showSaveDialog((JFrame) this.getTopLevelAncestor(), "DSNV" + ((int) (Math.random() * 1000)) + ".xlsx");
            if (file == null) {
                return;
            }
            try {
                UltraExporter.exportNhanVien(file);
            } catch (IOException ex) {
                MsgBox.error("Xuất danh sách thất bại");
                return;
            }

        }
        if (MsgBox.confirm("Xuất danh sách thành công! Bạn có muốn mở file?", false) == 0) {
            try {
                Desktop.getDesktop().open(file);
            } catch (IOException ex) {
            }
        }
    }

    private int getIndexNhanVien(String maNV) {
        for (int i = 0; i < tblNhanVien.getRowCount(); i++) {
            if (tblNhanVien.getValueAt(i, 1).toString().equalsIgnoreCase(maNV)) {
                return i;
            }
        }
        return -1;
    }

    public static int getSizeNV() {
        return sizeNV;
    }

    public static void addNV() {
        sizeNV++;
    }

    private static String getRandomPassword(NhanVien nv) {
        //48 ->57, 65->90, 97->122
        StringBuilder sb = new StringBuilder();
        while (sb.length() < 8) {
            int random = (int) (Math.floor(Math.random() * 123)) + 48;
            if (random < 48 || random > 57 && random < 65 || random > 90 && random < 97 || random > 122) {
                continue;
            }
            sb.append((char) random);
        }
        XMail.sendMail(nv.getEmail(), "Mật khẩu đăng nhập ứng dụng Ultramotor của bạn là: " + sb.toString() + ". Vui lòng đổi mật khẩu sau khi đăng nhập.", "MAT KHAU DANG NHAP UNG DUNG");
        return sb.toString();
    }

    private ImageIcon createIcon(String name) {
        return new ImageIcon(getClass().getResource("/ultramotor/icon/" + name));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlQuanLyNV = new javax.swing.JPanel();
        lblQLNV = new javax.swing.JLabel();
        btnThemMoi = new com.swingx.Button();
        txtTimKiem = new com.swingx.SearchTextField();
        btnExport = new com.swingx.Button();
        btnGuiMail = new com.swingx.Button();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhanVien = new com.swingx.table.Table();
        btnSave = new com.swingx.Button();

        setOpaque(false);

        pnlQuanLyNV.setBackground(new java.awt.Color(255, 255, 255));
        pnlQuanLyNV.setName("QLNV"); // NOI18N
        pnlQuanLyNV.setOpaque(false);

        lblQLNV.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblQLNV.setForeground(new java.awt.Color(0, 174, 114));
        lblQLNV.setText("QUẢN LÝ NHÂN VIÊN");

        btnThemMoi.setBackground(new java.awt.Color(0, 174, 114));
        btnThemMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnThemMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ultramotor/icon/icons8-save.png"))); // NOI18N
        btnThemMoi.setText("New");
        btnThemMoi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnExport.setBackground(new java.awt.Color(0, 174, 114));
        btnExport.setForeground(new java.awt.Color(255, 255, 255));
        btnExport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ultramotor/icon/print_25px.png"))); // NOI18N
        btnExport.setText("Export");
        btnExport.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnGuiMail.setBackground(new java.awt.Color(0, 174, 114));
        btnGuiMail.setForeground(new java.awt.Color(255, 255, 255));
        btnGuiMail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ultramotor/icon/icons8-sent.png"))); // NOI18N
        btnGuiMail.setText("Send Mail");
        btnGuiMail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String[]{
                        "Title 1", "Title 2", "Title 3", "Title 4"
                }
        ));
        jScrollPane1.setViewportView(tblNhanVien);

        javax.swing.GroupLayout pnlQuanLyNVLayout = new javax.swing.GroupLayout(pnlQuanLyNV);
        pnlQuanLyNV.setLayout(pnlQuanLyNVLayout);
        pnlQuanLyNVLayout.setHorizontalGroup(
                pnlQuanLyNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlQuanLyNVLayout.createSequentialGroup()
                                .addComponent(lblQLNV)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnThemMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnGuiMail, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnExport, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 334, Short.MAX_VALUE)
                                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                        .addComponent(jScrollPane1)
        );

        pnlQuanLyNVLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[]{btnExport, btnGuiMail, btnThemMoi});

        pnlQuanLyNVLayout.setVerticalGroup(
                pnlQuanLyNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlQuanLyNVLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(pnlQuanLyNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pnlQuanLyNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(btnThemMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(btnExport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(btnGuiMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblQLNV, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE))
        );

        btnSave.setBackground(new java.awt.Color(0, 174, 114));
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setText("Lưu dữ liệu");
        btnSave.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSave.setRadius(10);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(pnlQuanLyNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(pnlQuanLyNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.swingx.Button btnExport;
    private com.swingx.Button btnGuiMail;
    private com.swingx.Button btnSave;
    private com.swingx.Button btnThemMoi;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblQLNV;
    private javax.swing.JPanel pnlQuanLyNV;
    private com.swingx.table.Table tblNhanVien;
    private com.swingx.SearchTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            JFrame fr = new JFrame();
//            fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            fr.getContentPane().add(new NhanVienPanel());
//            fr.pack();
//            fr.setVisible(true);
//        });
//    }
}
