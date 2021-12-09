package com.ultramotor.ui.quanlykhachhang;

import com.swingx.table.ModelAction;
import com.swingx.table.ModelEvent;
import com.ultramotor.dao.HoaDonDAO;
import com.ultramotor.dao.KhachHangDAO;
import com.ultramotor.entity.KhachHang;
import com.ultramotor.ui.nhanvien.SendMailPanel;
import com.ultramotor.util.Auth;
import com.ultramotor.util.MsgBox;
import com.ultramotor.util.XDate;
import com.ultramotor.util.XDialog;
import com.ultramotor.util.XValidate;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.CellEditor;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class KhachHangPanel extends javax.swing.JPanel {

    KhachHangDAO dao = new KhachHangDAO();
    ModelEvent event;
    private KhachHangInfoPanel pnlInfo;
    private SendMailPanel pnlSendMail;
    private DefaultTableModel model;

    public KhachHangPanel() {
        initComponents();
        init();
    }

    private void init() {
        initTable();
        pnlInfo = new KhachHangInfoPanel();
        pnlSendMail = new SendMailPanel();
        event = new ModelEvent<KhachHang>() {
            @Override
            public void update(KhachHang e) {
                pnlInfo.setSoLuong(tblKhachHang.getRowCount());
                pnlInfo.setForm(e);
                showPanel("info");
            }

            @Override
            public void delete(KhachHang e) {
                if (MsgBox.confirm("Bạn có muốn xoá khách hàng " + e.getHoTenKH() + "?", false) != 0) {
                    deleteKH(e);
                }
            }
        };
        this.fillTable(dao.selectAll());
        addListeners();
    }

    private void initTable() {
        Object[] columns = {"Select", "ID", "Họ Và Tên", "Giới Tính", "Ngày Sinh", "Địa chỉ", "Số ĐT", "Email", "Thành viên", "Actions"};
        model = new DefaultTableModel(columns, 0);
        tblKhachHang.setModel(model);

        TableRowSorter<TableModel> sorter = new TableRowSorter(model);
        tblKhachHang.setRowSorter(sorter);
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
        tblKhachHang.fixTable((JScrollPane) tblKhachHang.getParent().getParent());
    }

    private void insert(KhachHang kh) {
        if (kh == null) {
            return;
        }
        if (dao.insert(kh) > 0) {
            model.addRow(getInfo(kh));
            int index = tblKhachHang.getRowCount() - 1;
            tblKhachHang.setRowSelectionInterval(index, index); //đật hàng chọn trên bảng
            tblKhachHang.scrollRectToVisible(new java.awt.Rectangle(tblKhachHang.getCellRect(index, 0, true))); //di chuyển thanh lăn tới vị trí hàng chọn
            MsgBox.inform("Thêm mới thành công!");
        } else {
            MsgBox.inform("Thêm mới thất bại!");
        }
    }

    private void update(KhachHang kh) {
        if (kh == null) {
            return;
        }
        if (dao.insert(kh) > 0) {
            int index = tblKhachHang.getSelectedRow();
            CellEditor editor = tblKhachHang.getCellEditor();
            if(editor!=null){
                editor.stopCellEditing();
            }
            model.removeRow(index);
            model.insertRow(index, getInfo(kh));
            tblKhachHang.scrollRectToVisible(new java.awt.Rectangle(tblKhachHang.getCellRect(index, 0, true))); //di chuyển thanh lăn tới vị trí hàng chọn
            MsgBox.inform("Cập nhật khách hàng thành công!");
        } else {
            MsgBox.inform("Cập nhật khách hàng thất bại!");
        }
    }

    private void deleteKH(KhachHang kh) {
        if (kh == null) {
            return;
        }
        if (hasHoaDon(kh) && MsgBox.confirm("Khách hàng vẫn còn hoá đơn trong hệ thống. Bạn có muốn xoá?", false) != 0) {
            return;
        }
        if (dao.delete(kh.getIdKH()) > 0) {
            MsgBox.inform("Xoá khách hàng thành công!");
            int row = tblKhachHang.getSelectedRow();
            CellEditor editor = tblKhachHang.getCellEditor();
            if(editor!=null){
                editor.stopCellEditing();
            }
            model.removeRow(row);
        } else {
            MsgBox.inform("Xoá khách hàng thất bại!");
        }
    }

    private void sendMail() {
        Set<String> emails = new HashSet<>();
        List<Integer> list = getSelectedRows();
        if (list.isEmpty()) {
            int confirm = MsgBox.confirm("Bạn chưa chọn khách hàng muốn gửi mail. Bạn có muốn gửi mail cho tất cả khách hàng?", true);
            if (confirm == MsgBox.CANCEL_OPTION) {
                return;
            } else if (confirm == MsgBox.NO_OPTION) {
                sendMail("");
                return;
            }
        }
        //nếu người dùng đã chọn khách hàng hoặc chọn gửi cho tất cả khách hàng
        for (int i = 0; i < tblKhachHang.getRowCount(); i++) {
            if (list.isEmpty() || list.contains(i)) {
                String email = (String) tblKhachHang.getValueAt(i, 7);
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

    Object[] getInfo(KhachHang kh) {
        return new Object[]{
            false, kh.getIdKH(), kh.getHoKH() + " " + kh.getTenKH(),
            kh.getGioiTinh() ? "Nam" : "Nữ", XDate.toString(kh.getNgaySinh(), "dd/MM/yyyy"),
            kh.getDiaChi(),
            kh.getSdt(), kh.getEmail(), kh.getThanhVien() ? "Thành Viên" : "",
            new ModelAction(kh, event)
        };
    }

    void fillTable(List<KhachHang> list) {
        model.setRowCount(0); // xóa tất cả các hàng trên jtable
        list.forEach(kh -> {
            model.addRow(getInfo(kh));
        });
    }

    private List<Integer> getSelectedRows() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < tblKhachHang.getRowCount(); i++) {
            if ((boolean) tblKhachHang.getValueAt(i, 0)) {
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
            pnlInfo.setSoLuong(tblKhachHang.getRowCount());
            pnlInfo.setForm(null);
            showPanel("info");
        });

        btnGuiMail.addActionListener((ActionEvent e) -> {
            sendMail();
        });

        pnlInfo.setMailListener((ActionEvent e) -> {
            sendMail(pnlInfo.getKhachHang().getEmail());
        });

        pnlInfo.setUpdateListener((ActionEvent e) -> {
            KhachHang kh = pnlInfo.getForm();
            if (kh == null) {
                return;
            }
            int index = findIndexKhachHang(kh);
            if (index == -1) {
                insert(kh);
            } else {
                update(kh);
            }
//            ((JDialog) pnlInfo.getTopLevelAncestor()).dispose();
        });
    }

    private int findIndexKhachHang(KhachHang kh) {
        if (kh == null) {
            return -1;
        }
        for (int i = 0; i < tblKhachHang.getRowCount(); i++) {
            if (kh.getIdKH().equalsIgnoreCase((String) tblKhachHang.getValueAt(i, 1))) {
                return i;
            }
        }
        return -1;
    }

    private boolean hasHoaDon(KhachHang kh) {
        if (kh == null) {
            return false;
        }
        return new HoaDonDAO().selectByKhachHang(kh.getIdKH()).size() > 0;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlQuanLyNV = new javax.swing.JPanel();
        lblQLKH = new javax.swing.JLabel();
        btnThemMoi = new com.swingx.Button();
        txtTimKiem = new com.swingx.SearchTextField();
        btnExport = new com.swingx.Button();
        btnGuiMail = new com.swingx.Button();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhachHang = new com.swingx.table.Table();

        pnlQuanLyNV.setBackground(new java.awt.Color(255, 255, 255));
        pnlQuanLyNV.setName("QLNV"); // NOI18N

        lblQLKH.setBackground(new java.awt.Color(0, 174, 114));
        lblQLKH.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblQLKH.setForeground(new java.awt.Color(0, 174, 114));
        lblQLKH.setText("UltraMotor - Quản lý khách hàng");

        btnThemMoi.setBackground(new java.awt.Color(0, 174, 114));
        btnThemMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnThemMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ultramotor/icon/icons8-save.png"))); // NOI18N
        btnThemMoi.setText("Thêm mới");
        btnThemMoi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnExport.setBackground(new java.awt.Color(0, 174, 114));
        btnExport.setForeground(new java.awt.Color(255, 255, 255));
        btnExport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ultramotor/icon/pdf_25px.png"))); // NOI18N
        btnExport.setText("Export");
        btnExport.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnGuiMail.setBackground(new java.awt.Color(0, 174, 114));
        btnGuiMail.setForeground(new java.awt.Color(255, 255, 255));
        btnGuiMail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ultramotor/icon/icons8-sent.png"))); // NOI18N
        btnGuiMail.setText("Gửi Mail");
        btnGuiMail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblKhachHang);

        javax.swing.GroupLayout pnlQuanLyNVLayout = new javax.swing.GroupLayout(pnlQuanLyNV);
        pnlQuanLyNV.setLayout(pnlQuanLyNVLayout);
        pnlQuanLyNVLayout.setHorizontalGroup(
            pnlQuanLyNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQuanLyNVLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlQuanLyNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(pnlQuanLyNVLayout.createSequentialGroup()
                        .addComponent(lblQLKH)
                        .addGap(45, 45, 45)
                        .addComponent(btnThemMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnGuiMail, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnExport, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 189, Short.MAX_VALUE)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlQuanLyNVLayout.setVerticalGroup(
            pnlQuanLyNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQuanLyNVLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlQuanLyNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlQuanLyNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnThemMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnExport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGuiMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblQLKH))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlQuanLyNV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlQuanLyNV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.swingx.Button btnExport;
    private com.swingx.Button btnGuiMail;
    private com.swingx.Button btnThemMoi;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblQLKH;
    private javax.swing.JPanel pnlQuanLyNV;
    private com.swingx.table.Table tblKhachHang;
    private com.swingx.SearchTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame fr = new JFrame();
            fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            fr.getContentPane().add(new KhachHangPanel());
            fr.pack();
            fr.setVisible(true);
        });
    }
}
