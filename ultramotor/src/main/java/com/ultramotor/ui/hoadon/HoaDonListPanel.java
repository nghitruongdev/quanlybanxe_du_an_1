/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ultramotor.ui.hoadon;

import com.ultramotor.dao.HoaDonDAO;
import com.ultramotor.dao.KhachHangDAO;
import com.ultramotor.dao.NhanVienDAO;
import com.ultramotor.entity.HoaDon;
import com.ultramotor.entity.KhachHang;
import com.ultramotor.entity.NhanVien;
import com.ultramotor.util.MsgBox;
import com.ultramotor.util.XDate;
import com.ultramotor.util.XDialog;
import com.ultramotor.util.XFile;
import com.ultramotor.util.XImage;
import com.ultramotor.util.XMail;
import com.ultramotor.util.XPdf;
import com.ultramotor.util.XReport;
import com.ultramotor.util.XValidate;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;

public class HoaDonListPanel extends javax.swing.JPanel {

    private HoaDonDAO dao;
    private List<HoaDon> list;
    private List<NhanVien> listNV;
    private List<KhachHang> listKH;

    public HoaDonListPanel() {
        initComponents();
        init();
    }

    private void init() {
        dao = new HoaDonDAO();
        list = dao.selectAll();
        listNV = new NhanVienDAO().selectAll();
        listKH = new KhachHangDAO().selectAll();
        dateFrom.setTextRefernce(txtTo);
        dateTo.setTextRefernce(txtFrom);

        initTable();
        fillTable();
        addListener();
    }

    private void initTable() {
        String[] cols = {"Mã Hoá Đơn", "Tên Khách Hàng", "Giảm Giá", "Tổng Tiền", "Thời Gian", "Nhân Viên"};
        tblHoaDon.initTable(cols);
        tblHoaDon.addRowSorter(txtSearch);
    }

    private void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
        model.setRowCount(0);
        getCurrentList().forEach(hd -> model.addRow(getInfo(hd)));
    }

    private List<HoaDon> getCurrentList() {
        try {
            Date from = XDate.parse(txtFrom.getText(), "dd-MM-yyyy");
            Date to = XDate.parse(txtTo.getText(), "dd-MM-yyyy");
            boolean all = chkAll.isSelected();
            return list.stream()
                    .filter(hd
                            -> all
                    || hd.getThoiGian().after(from)
                    && hd.getThoiGian().before(to))
                    .collect(Collectors.toList());
        } catch (ParseException ex) {
            Logger.getLogger(HoaDonListPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Collections.EMPTY_LIST;
    }

    private Object[] getInfo(HoaDon hd) {
        KhachHang kh = findKhachHang(hd.getIdKH());
        NhanVien nv = findNhanVien(hd.getIdNV());
        return new Object[]{
            hd,
            kh == null ? "" : kh.getHoTenKH(),
            hd.getGiamGia(),
            new DecimalFormat("#,###.## VNĐ").format(hd.getTongTien()),
            XDate.toString(hd.getThoiGian(), "dd/MM/yyyy"),
            nv == null ? "" : nv.getHoTenNV()
        };
    }

    private void addListener() {
        PropertyChangeListener changeEvent = (PropertyChangeEvent evt) -> {
            fillTable();
        };
        txtFrom.addPropertyChangeListener("value", changeEvent);
        txtTo.addPropertyChangeListener("value", changeEvent);
        chkAll.addActionListener(e -> fillTable());

        ActionListener exportEvent = (event) -> {
            new Thread(() -> export(event.getActionCommand())).start();

        };
        btnPreview.addActionListener(exportEvent);
        btnPrint.addActionListener(exportEvent);
        btnMail.addActionListener(exportEvent);
        btnExport.addActionListener(exportEvent);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new HoaDonListPanel());
        frame.setSize(1000, 500);
        frame.pack();
        frame.setVisible(true);
    }

    private String getSelectedHD() {
        int index = tblHoaDon.getSelectedRow();
        if (index >= 0) {
            return ((HoaDon) tblHoaDon.getValueAt(index, 0)).getIdHD();
        }
        return "";
    }

    private NhanVien findNhanVien(String idNV) {
        return listNV.stream().filter(nv -> nv.getIdNV().equalsIgnoreCase(idNV)).findFirst().orElse(null);
    }

    private KhachHang findKhachHang(String idKH) {
        return listKH.stream().filter(kh -> kh.getIdKH().equalsIgnoreCase(idKH)).findFirst().orElse(null);
    }

    private String promptEmail() throws Exception {
        String email = "";
        while (!XValidate.validateEmail(email)) {
            email = MsgBox.input("Vui lòng nhập địa chỉ email");
            if (email == null) {
                throw new Exception();
            }
        }
        return email;
    }

    private File getPDF(String idHD) {
        File file = XFile.getTempFile(null, ".pdf");
        try {
            XReport.createHoaDonReport(idHD, file);
        } catch (JRException | FileNotFoundException ex) {
        }
        return file;
    }

    private void export(String action) {
        String idHD = getSelectedHD();
        if (idHD.isEmpty()) {
            MsgBox.error("Vui lòng chọn một hoá đơn");
            return;
        }
        File file = getPDF(idHD);
        if (file == null) {
            MsgBox.error("Không thể xuất hoá đơn! Vui lòng kiểm tra lại hệ thống");
            return;
        }
        switch (action) {
            case "print": {
                try {
                    XPdf.printPDF(file);
                } catch (IOException | PrinterException ex) {
                    MsgBox.error("Không thể in hoá đơn! Vui lòng kiểm tra lại hệ thống");
                }
            }
            break;
            case "mail":
                try {
                XMail.sendMail(promptEmail(), "", "Hoá Đơn " + idHD, file);
            } catch (Exception ex) {
            }
            break;
            case "preview": {
                try {
                    BufferedImage image = XPdf.saveAsImage(file);
                    JLabel box = new JLabel("");
                    box.setPreferredSize(new Dimension(500, 700));
                    box.setIcon(XImage.resize(new ImageIcon(image), 500, 700));
                    JPanel panel = new JPanel();
                    panel.add(box);
                    XDialog.getDialog((JFrame) this.getTopLevelAncestor(), panel).setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(HoaDonListPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
            case "export":
                saveAs(file, idHD);
                break;
        }
    }

    private void saveAs(File file, String idHD) {
        File selected = XPdf.showSaveDialog((JFrame) this.getTopLevelAncestor(), idHD + ".pdf");
        if (selected == null) {
            return;
        }
        try {
            Files.copy(file.toPath(), selected.toPath(), StandardCopyOption.REPLACE_EXISTING);
            if (MsgBox.confirm("Xuất file thành công, bạn có muốn mở file?", false) == 0) {
                Desktop.getDesktop().open(selected);
            }
        } catch (IOException ex) {
            MsgBox.error("Không thể xuất hoá đơn!");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateFrom = new com.swingx.datechooser.DateChooser();
        dateTo = new com.swingx.datechooser.DateChooser();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblHoaDon = new com.swingx.table.Table();
        txtSearch = new com.swingx.SearchTextField();
        txtTo = new com.swingx.TextField();
        txtFrom = new com.swingx.TextField();
        chkAll = new javax.swing.JCheckBox();
        btnPrint = new com.swingx.Button();
        btnMail = new com.swingx.Button();
        btnExport = new com.swingx.Button();
        btnPreview = new com.swingx.Button();

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

        txtTo.setOnlyField(true);

        txtFrom.setEditable(false);
        txtFrom.setOnlyField(true);

        chkAll.setSelected(true);
        chkAll.setText("Tất Cả");
        chkAll.setOpaque(false);

        btnPrint.setText("Print");
        btnPrint.setActionCommand("print");
        btnPrint.setRadius(10);

        btnMail.setText("Mail");
        btnMail.setActionCommand("mail");
        btnMail.setRadius(10);

        btnExport.setText("Export");
        btnExport.setActionCommand("export");
        btnExport.setRadius(10);

        btnPreview.setText("Preview");
        btnPreview.setActionCommand("preview");
        btnPreview.setRadius(10);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane5))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(txtFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(chkAll)
                        .addGap(18, 18, 18)
                        .addComponent(btnPreview, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnMail, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExport, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtFrom, txtTo});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(chkAll))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnMail, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnExport, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnPreview, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtFrom, txtTo});

    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.swingx.Button btnExport;
    private com.swingx.Button btnMail;
    private com.swingx.Button btnPreview;
    private com.swingx.Button btnPrint;
    private javax.swing.JCheckBox chkAll;
    private com.swingx.datechooser.DateChooser dateFrom;
    private com.swingx.datechooser.DateChooser dateTo;
    private javax.swing.JScrollPane jScrollPane5;
    private com.swingx.table.Table tblHoaDon;
    private com.swingx.TextField txtFrom;
    private com.swingx.SearchTextField txtSearch;
    private com.swingx.TextField txtTo;
    // End of variables declaration//GEN-END:variables
}
