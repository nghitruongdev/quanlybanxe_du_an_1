package com.ultramotor.ui.nhanvien;

import com.microsoft.sqlserver.jdbc.SQLServerDataTable;
import com.swingx.CloseButton;
import com.ultramotor.component.table.ModelAction;
import com.ultramotor.component.table.ModelEvent;
import com.ultramotor.dao.NhanVienDAO;
import com.ultramotor.entity.NhanVien;
import com.ultramotor.ui.hoadon.HoaDonPanel;
import com.ultramotor.util.Auth;
import com.ultramotor.util.MsgBox;
import com.ultramotor.util.XDate;
import com.ultramotor.util.XMail;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;

public class NhanVienPanel extends javax.swing.JPanel {

    NhanVienDAO dao = new NhanVienDAO();
    int row = -1; //vị trí hàng được chọn trên table
    ModelEvent event;
    private NhanVienInfoPanel pnlInfo;
    private SendMailPanel pnlSendMail;
    private DefaultTableModel model;

    public NhanVienPanel() {
        initComponents();
        init();
    }

    private void init() {
        initTable();
        pnlInfo = new NhanVienInfoPanel();
        pnlSendMail = new SendMailPanel();
        event = new ModelEvent<NhanVien>() {
            @Override
            public void update(NhanVien e) {
                pnlInfo.setForm(e);
                showPanel("info");
            }

            @Override
            public void delete(NhanVien e) {
                deleteNV(e.getIdNV());
            }

        };
        fillTable();
        addListeners();
    }

    private void initTable() {
        Object[] columns = {"Select", "ID", "Họ Và Tên", "Ngày Sinh", "Giới Tính", "Địa chỉ", "Số ĐT", "Email", "Lương", "Hình", "Vai Trò", "Mật Khẩu", "Ghi Chú", "Actions"};
        model = new DefaultTableModel(columns, 0);
        tblNhanVien.setModel(model);
        tblNhanVien.fixTable(jScrollPane4);
    }

    private void save() {
        try {
            SQLServerDataTable dataTable = NhanVien.getDataServerTable();
            for (int i = 0; i < tblNhanVien.getRowCount(); i++) {
                dataTable.addRow(getInfo(i));
            }
            dao.mergeTable(dataTable);
            MsgBox.inform("Lưu dữ liệu thành công");
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
                    tblNhanVien.getCellEditor().stopCellEditing();
                    model.removeRow(index);
                }
            }
        }
    }

    private void sendMail() {
        List<String> emails = new ArrayList<>();
        List<Integer> list = getSelectedRows();
        boolean all = list.isEmpty();
        for (int i = 0; i < tblNhanVien.getRowCount(); i++) {
            if (all || list.contains(i)) {
                emails.add((String) tblNhanVien.getValueAt(i, 7));
            }
        }
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
            false, nv.getIdNV(), nv.getHoNV() + " " + nv.getTenNV(),
            XDate.toString(nv.getNgaySinh(), "dd/MM/yyyy"),
            nv.getGioiTinh() ? "Nam" : "Nữ", nv.getDiaChi(),
            nv.getSdt(), nv.getEmail(), nv.getLuong(),
            nv.getHinh(), nv.getVaiTro(), nv.getMatKhau(),
            nv.getGhiChu(), new ModelAction(nv, event)
        };
    }

    void fillTable() {
        String keyword = txtTimKiem.getText();
        fillTable(dao.selectByKeyword(keyword)); // đọc dữ liệu từ csdl
    }

    void fillTable(List<NhanVien> list) {
        DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
        model.setRowCount(0); // xóa tất cả các hàng trên jtable
        try {
            for (NhanVien nv : list) {
                model.addRow(getInfo(nv));
            }
        } catch (Exception e) {
            MsgBox.error("Lỗi truy vấn dữ liệu");
        }
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
                JDialog dialog = null;
                switch (name) {
                    case "info":
                        dialog = getDialog(pnlInfo);
                        break;
                    case "sendMail":
                        dialog = getDialog(pnlSendMail);
                        break;
                }
                dialog.setVisible(true);
            } catch (InterruptedException e) {
            }
        }).start();
    }

    private JDialog getDialog(JPanel panel) {
        JDialog dialog = new JDialog((Frame) this.getTopLevelAncestor(), true);
        dialog.setUndecorated(true);
        dialog.setBackground(new Color(255, 255, 255, 0));
        JPanel con = new JPanel() {
            @Override
            public void paint(Graphics grphcs) {
                Graphics2D g2 = (Graphics2D) grphcs;
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), 5, 5);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                super.paint(grphcs);
            }
        };

        con.setOpaque(false);
        con.setBackground(new Color(250, 250, 250));
        con.setLayout(new MigLayout("inset 5 20 20 5", "[center]", "[20!][fill, center, grow]"));
        con.add(new CloseButton(), "al right, wrap");
        con.add(panel, "pushy, center, gapright 15");
//        dialog.setBounds(this.getWidth() / 2, this.getHeight(), this.getWidth() / 4, 0);

        dialog.setSize(this.getWidth() / 2, this.getHeight());
        dialog.getContentPane().add(con);
        dialog.pack();

        dialog.setLocation(this.getWidth() / 4, (this.getHeight() - dialog.getHeight()) / 2);
        dialog.setLocationRelativeTo(this);
        return dialog;
    }

    private void addListeners() {
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                //code xử lý keyReleased ở đây.
                fillTable();
            }
        });

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

            } else {
                model.removeRow(index);
                model.insertRow(index, getInfo(nv));
                MsgBox.inform("Cập nhật nhân viên thành công");
            }
        });

        pnlInfo.setFieldFocus(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                JTextField field = (JTextField) e.getSource();
                String maNV = field.getText().trim();
                int index = getIndexNhanVien(maNV);
                if (index != -1) {
                    MsgBox.error("Mã nhân viên đã tồn tại!");
                    field.setText("");
                    field.requestFocus();
                }
            }
        });
    }

    private int getIndexNhanVien(String maNV) {
        for (int i = 0; i < tblNhanVien.getRowCount(); i++) {
            if (tblNhanVien.getValueAt(i, 1).toString().equalsIgnoreCase(maNV)) {
                return i;
            }
        }
        return -1;
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
        XMail.sendMail(nv.getEmail(), "Mật khẩu đăng nhập ứng dụng Ultramotor của bạn là: " + sb.toString() + ". Vui lòng đổi mật khẩu sau khi đăng nhập.", "MẬT KHẨU ĐĂNG NHẬP ỨNG DỤNG");
        return sb.toString();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlQuanLyNV = new javax.swing.JPanel();
        lblQLNV = new javax.swing.JLabel();
        btnThemMoi = new com.swingx.Button();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblNhanVien = new com.ultramotor.component.table.Table();
        txtTimKiem = new com.swingx.SearchTextField();
        btnExport = new com.swingx.Button();
        btnGuiMail = new com.swingx.Button();
        btnSave = new com.swingx.Button();

        pnlQuanLyNV.setBackground(new java.awt.Color(255, 255, 255));
        pnlQuanLyNV.setName("QLNV"); // NOI18N

        lblQLNV.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblQLNV.setForeground(new java.awt.Color(0, 153, 255));
        lblQLNV.setText("UltraMotor - Quản lý nhân viên");

        btnThemMoi.setBackground(new java.awt.Color(0, 153, 255));
        btnThemMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnThemMoi.setText("Thêm mới");
        btnThemMoi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(tblNhanVien);

        btnExport.setBackground(new java.awt.Color(0, 153, 255));
        btnExport.setForeground(new java.awt.Color(255, 255, 255));
        btnExport.setText("Export");
        btnExport.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnGuiMail.setBackground(new java.awt.Color(0, 153, 255));
        btnGuiMail.setForeground(new java.awt.Color(255, 255, 255));
        btnGuiMail.setText("Gửi Mail");
        btnGuiMail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout pnlQuanLyNVLayout = new javax.swing.GroupLayout(pnlQuanLyNV);
        pnlQuanLyNV.setLayout(pnlQuanLyNVLayout);
        pnlQuanLyNVLayout.setHorizontalGroup(
            pnlQuanLyNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQuanLyNVLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlQuanLyNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlQuanLyNVLayout.createSequentialGroup()
                        .addComponent(lblQLNV)
                        .addGap(18, 18, 18)
                        .addComponent(btnThemMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnGuiMail, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnExport, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(257, 257, 257)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1176, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addComponent(lblQLNV)
                        .addComponent(btnGuiMail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btnSave.setBackground(new java.awt.Color(92, 167, 51));
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
                .addComponent(pnlQuanLyNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(pnlQuanLyNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.swingx.Button btnExport;
    private com.swingx.Button btnGuiMail;
    private com.swingx.Button btnSave;
    private com.swingx.Button btnThemMoi;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblQLNV;
    private javax.swing.JPanel pnlQuanLyNV;
    private com.ultramotor.component.table.Table tblNhanVien;
    private com.swingx.SearchTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
  public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame fr = new JFrame();
            fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            fr.getContentPane().add(new NhanVienPanel());
            fr.pack();
            fr.setVisible(true);
        });
    }
}
