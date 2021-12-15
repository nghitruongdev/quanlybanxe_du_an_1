package com.ultramotor.ui.quanlykhachhang;

import com.swingx.table.ModelAction;
import com.swingx.table.ModelEvent;
import com.swingx.table.Table;
import com.ultramotor.dao.HoaDonDAO;
import com.ultramotor.dao.KhachHangDAO;
import com.ultramotor.entity.KhachHang;
import com.ultramotor.ui.hoadon.ThanhVienCard;
import com.ultramotor.ui.nhanvien.SendMailPanel;
import com.ultramotor.util.Auth;
import com.ultramotor.util.MsgBox;
import com.ultramotor.util.UltraExporter;
import com.ultramotor.util.XDate;
import com.ultramotor.util.XDialog;
import com.ultramotor.util.XExcel;
import com.ultramotor.util.XFile;
import com.ultramotor.util.XImage;
import com.ultramotor.util.XPdf;
import com.ultramotor.util.XReport;
import com.ultramotor.util.XValidate;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.CellEditor;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;

public class KhachHangPanel extends javax.swing.JPanel {

    KhachHangDAO dao = new KhachHangDAO();
    ModelEvent event;
    private KhachHangInfoPanel pnlInfo;
    private SendMailPanel pnlSendMail;

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
                if (MsgBox.confirm("Bạn có muốn xoá khách hàng " + e.getHoTenKH() + "?", false) == 0) {
                    deleteKH(e);
                }
            }
        };
        
        addListeners();
    }

    private void initTable() {
        String[] colsKH = {"Select", "ID", "Họ Và Tên", "Giới Tính", "Ngày Sinh", "Địa chỉ", "Số ĐT", "Email", "Thành viên", "Actions"};
        tblKhachHang.initTable(colsKH);
        tblKhachHang.addRowSorter(txtSearchKH);
        tblKhachHang.getColumnModel().getColumn(tblKhachHang.getColumnCount() - 1).setMaxWidth(100);
    }

    private void insert(KhachHang kh) {
        if (kh == null) {
            return;
        }
        if (dao.insert(kh) > 0) {
            ((DefaultTableModel) tblKhachHang.getModel()).addRow(getInfo(kh));
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
            if (editor != null) {
                editor.stopCellEditing();
            }
            ((DefaultTableModel) tblKhachHang.getModel()).removeRow(index);
            ((DefaultTableModel) tblKhachHang.getModel()).insertRow(index, getInfo(kh));
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
            if (editor != null) {
                editor.stopCellEditing();
            }
            ((DefaultTableModel) tblKhachHang.getModel()).removeRow(row);
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
            false, kh, kh.getHoTenKH(),
            kh.getGioiTinh() ? "Nam" : "Nữ", XDate.toString(kh.getNgaySinh(), "dd/MM/yyyy"),
            kh.getDiaChi(),
            kh.getSdt(), kh.getEmail(), kh.getThanhVien() ? "Thành Viên" : "",
            new ModelAction(kh, event)
        };
    }

    void fillTable(Table table, List<KhachHang> list) {
        ((DefaultTableModel) table.getModel()).setRowCount(0); // xóa tất cả các hàng trên jtable
        list.forEach(kh -> {
            ((DefaultTableModel) table.getModel()).addRow(getInfo(kh));
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
            ((JDialog) pnlInfo.getTopLevelAncestor()).dispose();
        });

        btnExport.addActionListener(e -> new Thread(()->export()).start());
        btnExportTV.addActionListener(e -> new Thread(()->exportTV()).start());
        this.addAncestorListener(new AncestorListener() {
            @Override
            public void ancestorAdded(AncestorEvent event) {
                new Thread(()->fillTable(tblKhachHang, dao.selectAll())).start();
            }

            @Override
            public void ancestorRemoved(AncestorEvent event) {
            }

            @Override
            public void ancestorMoved(AncestorEvent event) {
            }
        });
    }

    private int findIndexKhachHang(KhachHang kh) {
        if (kh == null) {
            return -1;
        }
        for (int i = 0; i < tblKhachHang.getRowCount(); i++) {
            if (kh.equals(tblKhachHang.getValueAt(i, 1))) {
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

    private void exportTV() {
        List<Integer> rows = getSelectedRows();
        int index = tblKhachHang.getSelectedRow();
        if (rows.isEmpty()) {
            if (index < 0) {
                MsgBox.error("Vui lòng chọn khách hàng cần tạo thẻ");
                return;
            }
            KhachHang kh = (KhachHang) tblKhachHang.getValueAt(index, 1);
            if (checkTV(kh)) {
                exportTV(kh);
            }
        } else {
            List<KhachHang> listTV = new ArrayList<>(rows.size());
            rows.forEach(i -> listTV.add((KhachHang) tblKhachHang.getValueAt(i, 1)));
            KhachHang[] arrKH = listTV.stream().toArray(KhachHang[]::new);
            if (checkTV(arrKH)) {
                exportTV(arrKH);
            }
        }
    }

    private boolean checkTV(KhachHang... arrKH) {
        for (KhachHang kh : arrKH) {
            if (!kh.getThanhVien()) {
                MsgBox.error("Khách hàng " + kh.getHoTenKH() + " chưa đăng ký thành viên!");
                return false;
            }
        }
        return true;
    }

    private void exportTV(KhachHang... arrKH) {
        File file = XFile.getTempFile(null, ".pdf");
        ThanhVienCard card = new ThanhVienCard();
        XDialog.getDialog((JFrame) this.getTopLevelAncestor(), card);
        try {
            Thread.sleep(300);
        } catch (InterruptedException ex) {
        }
        List<File> list = new ArrayList<>();
        for (KhachHang kh : arrKH) {
            card.setThanhvien(kh);
            try {
                File temp = XFile.getTempFile(null, ".png");
                ImageIO.write(XImage.createImage(card), "png", temp);
                list.add(temp);
            } catch (IOException ex) {
            }
        }
        try {
            XReport.createThanhVienCard(list, file);
            XPdf.printPDF(file);
            if (MsgBox.confirm("Bạn có muốn mở file PDF?", false) == 0) {
                Desktop.getDesktop().open(file);
            }
        } catch (JRException ex) {
            MsgBox.error("Không thể xuất file! Vui lòng kiểm tra lại hệ thống");
        } catch (IOException ex) {
            MsgBox.error("Không thể tìm thấy file! Vui lòng kiểm tra lại hệ thống");
        } catch (PrinterException ex) {
            MsgBox.error("Không thể in file! Vui lòng kiểm tra lại hệ thống");
        }

    }

    private void export() {
        File excel = XExcel.showSaveDialog((JFrame) this.getTopLevelAncestor(), "DanhSachKH.xlsx");
        if (excel == null) {
            return;
        }
        UltraExporter.exportKhachHang(excel);
        if (MsgBox.confirm("Xuất danh sách thành công. Bạn có muốn mở file?", false) == 0) {
            try {
                Desktop.getDesktop().open(excel);
            } catch (IOException ex) {
            }
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlQuanLyNV = new javax.swing.JPanel();
        lblQLKH = new javax.swing.JLabel();
        btnThemMoi = new com.swingx.Button();
        txtSearchKH = new com.swingx.SearchTextField();
        btnExport = new com.swingx.Button();
        btnGuiMail = new com.swingx.Button();
        btnExportTV = new com.swingx.Button();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhachHang = new com.swingx.table.Table();

        setOpaque(false);

        pnlQuanLyNV.setBackground(new java.awt.Color(255, 255, 255));
        pnlQuanLyNV.setName("QLNV"); // NOI18N
        pnlQuanLyNV.setOpaque(false);

        lblQLKH.setBackground(new java.awt.Color(0, 174, 114));
        lblQLKH.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblQLKH.setForeground(new java.awt.Color(0, 174, 114));
        lblQLKH.setText("QUẢN LÝ KHÁCH HÀNG");

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

        btnExportTV.setBackground(new java.awt.Color(0, 174, 114));
        btnExportTV.setForeground(new java.awt.Color(255, 255, 255));
        btnExportTV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ultramotor/icon/Credit Card_25px.png"))); // NOI18N
        btnExportTV.setText("Thẻ Thành Viên");
        btnExportTV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

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
                .addComponent(lblQLKH)
                .addGap(20, 20, 20)
                .addComponent(btnThemMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGuiMail, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnExport, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnExportTV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(txtSearchKH, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(pnlQuanLyNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlQuanLyNVLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1046, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        pnlQuanLyNVLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnExport, btnGuiMail, btnThemMoi});

        pnlQuanLyNVLayout.setVerticalGroup(
            pnlQuanLyNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQuanLyNVLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlQuanLyNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlQuanLyNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlQuanLyNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThemMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnExport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnGuiMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnExportTV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(txtSearchKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblQLKH))
                .addGap(529, 529, 529))
            .addGroup(pnlQuanLyNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlQuanLyNVLayout.createSequentialGroup()
                    .addGap(60, 60, 60)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1071, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(2, 2, 2)
                    .addComponent(pnlQuanLyNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(3, 3, 3)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 609, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlQuanLyNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.swingx.Button btnExport;
    private com.swingx.Button btnExportTV;
    private com.swingx.Button btnGuiMail;
    private com.swingx.Button btnThemMoi;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblQLKH;
    private javax.swing.JPanel pnlQuanLyNV;
    private com.swingx.table.Table tblKhachHang;
    private com.swingx.SearchTextField txtSearchKH;
    // End of variables declaration//GEN-END:variables

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            JFrame fr = new JFrame();
//            fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            fr.getContentPane().add(new KhachHangPanel());
//            fr.pack();
//            fr.setVisible(true);
//        });
//    }
}
