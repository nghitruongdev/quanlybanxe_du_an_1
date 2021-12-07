package com.ultramotor.ui.quanlykhachhang;

import com.swingx.CloseButton;
import com.ultramotor.component.table.ModelAction;
import com.ultramotor.component.table.ModelEvent;
import com.ultramotor.dao.KhachHangDAO;
import com.ultramotor.entity.KhachHang;
import com.ultramotor.util.Auth;
import com.ultramotor.util.MsgBox;
import com.ultramotor.util.XDate;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.miginfocom.swing.MigLayout;

public class KhachHangPanel extends javax.swing.JPanel {

    KhachHangDAO dao = new KhachHangDAO();
    int row = -1; //vị trí hàng được chọn trên table
    ModelEvent event;
    private KhachHangInfoPanel pnlInfo;
    private SendMailPanel pnlSendMail;
    private DefaultTableModel model;
    private boolean hasEdit = false;

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
                pnlInfo.setForm(e);
                showPanel("info");
                hasEdit = true;
            }

            @Override
            public void delete(KhachHang e) {
                deleteNV(e.getIdKH());
            }

        };
        this.fillTable(dao.selectAll());
        addListeners();
    }

    private void initTable() {
        Object[] columns = {"Select", "ID", "Họ Và Tên", "Giới Tính", "Ngày Sinh", "Địa chỉ", "Số ĐT", "Email", "Thành viên", "Ghi Chú", "Actions"};
        model = new DefaultTableModel(columns, 0);
        tblKhachHang.setModel(model);

        TableRowSorter<TableModel> sorter = new TableRowSorter(model);
        tblKhachHang.setRowSorter(sorter);
        txtTimKiem.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String text = txtTimKiem.getText().trim();
//              
                dao.selectByKeyword(text);
                fillTable();
            }
        });
        tblKhachHang.fixTable(jScrollPane4);
    }

    void insert() throws ParseException {
//        if (!Auth.isManager()) {
//            MsgBox.inform("Bạn không có quyền thêm nhân viên!");
//        } else {
//        NhanVien nv = getForm();
        KhachHang kh = pnlInfo.getForm();
//        if (nv.getMatKhau().length() < 3 || nv.getMatKhau().length() > 16) {
//            MsgBox.inform("Vui lòng nhập kí tự mật khẩu từ 3 - 16 kí tự!");
//            return;
//        }
        try {
            dao.insert(kh);
            this.fillTable();
//            this.clearForm();
            MsgBox.inform("Thêm mới thành công!");
        } catch (Exception e) {
            MsgBox.inform("Thêm mới thất bại!");
        }
//    }
    }

    void update() {
        KhachHang kh = pnlInfo.getForm();
        try {
            dao.update(kh);
            this.fillTable();
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
        for (int i = 0; i < tblKhachHang.getRowCount(); i++) {
            if (all || list.contains(i)) {
                emails.add((String) tblKhachHang.getValueAt(i, 7));
            }
        }
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
            kh.getSdt(), kh.getEmail(), kh.getThanhVien(),
            kh.getGhiChu(), new ModelAction(kh, event)
        };
    }

    void fillTable() {
        String keyword = txtTimKiem.getText();
        fillTable(dao.selectByKeyword(keyword)); // đọc dữ liệu từ csdl
    }

    void fillTable(List<KhachHang> list) {
        DefaultTableModel model = (DefaultTableModel) tblKhachHang.getModel();
        model.setRowCount(0); // xóa tất cả các hàng trên jtable
        try {
            for (KhachHang kh : list) {
                model.addRow(getInfo(kh));
            }
        } catch (Exception e) {
            MsgBox.error("Lỗi truy vấn dữ liệu");
        }
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
//        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
//            @Override
//        public void keyReleased(java.awt.event.KeyEvent evt) {
//                //code xử lý keyReleased ở đây.
////                fillTable();
//                fillTable(dao.selectByKeyword(txtTimKiem.getText()));
//            }
//        });

        btnThemMoi.addActionListener((ActionEvent e) -> {
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
             
        });
    }
//    private int getIndexKhachHang(String IdKH) {
//        for (int i = 0; i < tblKhachHang.getRowCount(); i++) {
//            if (tblKhachHang.getValueAt(i, 1).toString().equalsIgnoreCase(IdKH)) {
//                return i;
//            }
//        }
//        return -1;
//    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlQuanLyNV = new javax.swing.JPanel();
        lblQLKH = new javax.swing.JLabel();
        btnThemMoi = new com.swingx.Button();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblKhachHang = new com.ultramotor.component.table.Table();
        txtTimKiem = new com.swingx.SearchTextField();
        btnExport = new com.swingx.Button();
        btnGuiMail = new com.swingx.Button();

        pnlQuanLyNV.setBackground(new java.awt.Color(255, 255, 255));
        pnlQuanLyNV.setName("QLNV"); // NOI18N

        lblQLKH.setBackground(new java.awt.Color(0, 174, 114));
        lblQLKH.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblQLKH.setForeground(new java.awt.Color(0, 174, 114));
        lblQLKH.setText("UltraMotor - Quản lý khách hàng");

        btnThemMoi.setBackground(new java.awt.Color(0, 174, 114));
        btnThemMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnThemMoi.setText("Thêm mới");
        btnThemMoi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnThemMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemMoiActionPerformed(evt);
            }
        });

        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã khách hàng", "Họ khách hàng", "Tên khách hàng", "Giới tính", "Ngày sinh", "Địa chỉ", "Số điện thoại", "Email", "Thành viên", "Ghi chú", "Mã nhân viên"
            }
        ));
        jScrollPane4.setViewportView(tblKhachHang);

        btnExport.setBackground(new java.awt.Color(0, 174, 114));
        btnExport.setForeground(new java.awt.Color(255, 255, 255));
        btnExport.setText("Export");
        btnExport.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnGuiMail.setBackground(new java.awt.Color(0, 174, 114));
        btnGuiMail.setForeground(new java.awt.Color(255, 255, 255));
        btnGuiMail.setText("Gửi Mail");
        btnGuiMail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout pnlQuanLyNVLayout = new javax.swing.GroupLayout(pnlQuanLyNV);
        pnlQuanLyNV.setLayout(pnlQuanLyNVLayout);
        pnlQuanLyNVLayout.setHorizontalGroup(
            pnlQuanLyNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQuanLyNVLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlQuanLyNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(pnlQuanLyNVLayout.createSequentialGroup()
                        .addComponent(lblQLKH)
                        .addGap(45, 45, 45)
                        .addComponent(btnThemMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnGuiMail, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnExport, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 236, Short.MAX_VALUE)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 6, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlQuanLyNVLayout.setVerticalGroup(
            pnlQuanLyNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQuanLyNVLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlQuanLyNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlQuanLyNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblQLKH)
                        .addGroup(pnlQuanLyNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThemMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnExport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnGuiMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void btnThemMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemMoiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemMoiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.swingx.Button btnExport;
    private com.swingx.Button btnGuiMail;
    private com.swingx.Button btnThemMoi;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblQLKH;
    private javax.swing.JPanel pnlQuanLyNV;
    private com.ultramotor.component.table.Table tblKhachHang;
    private com.swingx.SearchTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables

}
