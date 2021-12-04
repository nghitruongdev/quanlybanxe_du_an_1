package com.ultramotor.ui.khachhang;

import com.ultramotor.dao.LoaiHangDAO;
import com.ultramotor.dao.ModelSanPhamDAO;
import com.ultramotor.dao.NhaSanXuatDAO;
import com.ultramotor.entity.LoaiHang;
import com.ultramotor.entity.ModelSanPham;
import com.ultramotor.entity.NhaSanXuat;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class KhachHangController {

    private static final List<NhaSanXuat> listNSX = new NhaSanXuatDAO().selectAll();
    private static final List<LoaiHang> listLH = new LoaiHangDAO().selectAll();
    private static final ModelSanPhamDAO daoModel = new ModelSanPhamDAO();

    public static void showCard(JPanel panel, String cardName) {
        ((CardLayout) panel.getLayout()).show(panel, cardName);
    }

    public static void navigateCard(JPanel panel, boolean isNext) {
        new Thread(() -> {
            sleepThread(300);
            CardLayout layout = (CardLayout) panel.getLayout();
            if (isNext) {
                layout.next(panel);
            } else {
                layout.previous(panel);
            }
        }).start();
    }

    public static void fillComboNSX(JComboBox cbo, Lang lang) {
        cbo.setModel(new DefaultComboBoxModel(listNSX.toArray()));
        cbo.insertItemAt(new NhaSanXuat(null, lang == Lang.EN ? "All" : "Tất cả"), 0);
        cbo.setSelectedIndex(0);
    }

    public static void fillComboLoaiHang(JComboBox cbo, Lang lang) {
        if (lang == Lang.EN) {
            List<LoaiHang> listEN = new ArrayList<>(listLH.size());
            listLH.forEach(lh -> listEN.add(new LoaiHang(lh.getIdLH(), getNameCategory(lh.getTenLoaiHang()))));
            cbo.setModel(new DefaultComboBoxModel(listEN.toArray()));
            cbo.insertItemAt(new LoaiHang(null, "All"), 0);
            cbo.setSelectedIndex(0);
            return;
        }
        cbo.setModel(new DefaultComboBoxModel(listLH.toArray()));
        cbo.insertItemAt(new LoaiHang(null, "Tất cả"), 0);
        cbo.setSelectedIndex(0);
    }

    private static String getNameCategory(String tenLH) {
        switch (tenLH) {
            case "Xe ga":
                return "Scooter";
            case "Xe côn":
                return "Manual Motorcycle";
            case "Xe số":
                return "Automatic Motorcycle";
        }
        return "";
    }

    public static List<ModelSanPham> search(JTextField field) {
        List<ModelSanPham> list = daoModel.selectAll();
        if (field.getText().isEmpty()) {
            return list;
        }

        String[] text = field.getText().trim().toLowerCase().split(" ");
        list.removeIf(model
                -> !containsWord(model.getInfo().trim().toLowerCase(), text));
        return list;
    }

    private static boolean containsWord(String s, String[] words) {
        for (String word : words) {
            if (!s.contains(word)) {
                return false;
            }
        }
        return true;
    }

    public static List<ModelSanPham> search(JComboBox cboNSX, JComboBox cboLoaiHang) {
        NhaSanXuat nsx = (NhaSanXuat) cboNSX.getSelectedItem();
        LoaiHang lh = (LoaiHang) cboLoaiHang.getSelectedItem();

        String tenNSX = nsx.getIdNSX() != null ? nsx.getTenNSX() : "";
        String tenLH = lh.getIdLH() != null ? lh.getTenLoaiHang() : "";

        List<ModelSanPham> list = daoModel.selectAll();
        if (tenNSX.isEmpty() && tenLH.isEmpty()) {
            return list;
        }
        list.removeIf(model
                -> (!tenNSX.isEmpty() && !tenNSX.equalsIgnoreCase(model.getTenNSX()))
                || (!tenLH.isEmpty() && !lh.getIdLH().equalsIgnoreCase(listLH.stream()
                .filter(loai -> model.getTenLH().equalsIgnoreCase(loai.getTenLoaiHang()))
                .findFirst().orElse(null).getIdLH()))
        );
//        System.out.println("--------Print Result-----------");
//        for (ModelSanPham model : list) {
//            System.out.println("Ten NSX: " + model.getTenNSX());
//            System.out.println("Ten LH: " + model.getTenLH());
//
//        }
        return list;
    }

    public static void showProductList(Container container, List<ModelSanPham> list) {
        for (Component com : container.getComponents()) {
            if (com instanceof ProductListPanel) {
                ((ProductListPanel) com).setList(list);
                showCard((JPanel) container, "ProductList");
                return;
            }
        }
    }

    public static void showDetails(Container container, ModelSanPham model) {
        for (Component com : container.getComponents()) {
            if (com instanceof ProductDetailsPanel) {
                ((ProductDetailsPanel) com).setProductDetails(model);
                showCard((JPanel) container, "ProductDetails");
                return;
            }
        }
    }

    private static void sleepThread(int miliseconds) {
        try {
            Thread.sleep(miliseconds);
        } catch (InterruptedException e) {

        }
    }

}
