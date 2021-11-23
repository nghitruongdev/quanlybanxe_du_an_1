package com.ultramotor.ui.nhanvien;

import com.ultramotor.ui.*;
import com.swingx.CloseButton;
import com.ultramotor.component.table.ModelAction;
import com.ultramotor.component.table.ModelEvent;
import com.ultramotor.dao.NhanVienDAO;
import com.ultramotor.entity.NhanVien;
import com.ultramotor.util.Auth;
import com.ultramotor.util.MsgBox;
import com.ultramotor.util.XDate;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;

public class NhanVienPanel extends javax.swing.JPanel {

    NhanVienDAO dao = new NhanVienDAO();
    int row = -1; //vị trí hàng được chọn trên table
    ModelEvent event;
    private NhanVienInfoPanel pnlInfo;
    private SendMailPanel pnlSendMail;

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
        this.fillTable(getList());
        addListeners();
    }

    private List<NhanVien> getList() {
        List<NhanVien> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new NhanVien("PS1900" + i,
                    "Lê", "Thanh Tú",
                    XDate.parse("24-10-1999"),
                    (i % 2 == 0),
                    "Hồ Chí Minh",
                    "0921850113",
                    "tultps18884@fpt.edu.vn",
                    5000000d,
                    "",
                    "Nhân Viên",
                    "",
                    "")
            );
        }
        return list;
    }

    private void initTable() {
        Object[] columns = {"Select", "ID", "Họ Và Tên", "Ngày Sinh", "Giới Tính", "Địa chỉ", "Số ĐT", "Email", "Lương", "Hình", "Vai Trò", "Mật Khẩu", "Ghi Chú", "Actions"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        tblNhanVien.setModel(model);
        tblNhanVien.fixTable(jScrollPane4);
    }

    void insert() throws ParseException {
//        if (!Auth.isManager()) {
//            MsgBox.inform("Bạn không có quyền thêm nhân viên!");
//        } else {
//        NhanVien nv = getForm();
        NhanVien nv = null;
        if (nv.getMatKhau().length() < 3 || nv.getMatKhau().length() > 16) {
            MsgBox.inform("Vui lòng nhập kí tự mật khẩu từ 3 - 16 kí tự!");
            return;
        }
        try {
            dao.insert(nv);
//            this.fillTable();
//            this.clearForm();
            MsgBox.inform("Thêm mới thành công!");
        } catch (Exception e) {
            MsgBox.inform("Thêm mới thất bại!");
        }
//    }
    }

    void update() {
//        NhanVien nv = getForm();
        try {
//            dao.update(nv);
//            this.fillTable();
//            this.clearForm();
            MsgBox.inform("Cập nhật thành công!");
        } catch (Exception e) {
            MsgBox.inform("Cập nhật thất bại!");
        }
    }

    private void deleteNV(String maNV) {
        if (!Auth.isManager()) {
            MsgBox.inform("Bạn không có quyền xóa nhân viên!");
        } else {
//            String manv = nv.getIdNV();
            if (maNV.equals(Auth.user.getIdNV())) {
                MsgBox.inform("Bạn không được xóa chính bạn!");
            } else if (MsgBox.confirm("Bạn thực sự muốn xóa nhân viên này?", false) == 0) {
                try {
                    dao.delete(maNV);
                    this.fillTable();
                    MsgBox.inform("Xóa thành công!");
                } catch (Exception e) {
                    MsgBox.inform("Xóa thất bại!");
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

    Object[] getInfo(NhanVien nv) {
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
//                fillTable();
                fillTable(getList());
            }
        });

        btnThemMoi.addActionListener((ActionEvent e) -> {
            pnlInfo.setForm(null);
            showPanel("info");
        });

        btnGuiMail.addActionListener((ActionEvent e) -> {
            sendMail();
        });

        pnlInfo.setMailListener((ActionEvent e) -> {
            sendMail(pnlInfo.getNhanVien().getEmail());
        });
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlQuanLyNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(pnlQuanLyNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.swingx.Button btnExport;
    private com.swingx.Button btnGuiMail;
    private com.swingx.Button btnThemMoi;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblQLNV;
    private javax.swing.JPanel pnlQuanLyNV;
    private com.ultramotor.component.table.Table tblNhanVien;
    private com.swingx.SearchTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
