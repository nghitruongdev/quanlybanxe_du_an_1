package com.ultramotor.ui.khachhang;

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

    private static void sleepThread(int miliseconds) {
        try {
            Thread.sleep(miliseconds);
        } catch (InterruptedException e) {

        }
    }

    public static void showCard(JPanel panel, String cardName) {
        new Thread(() -> {
            sleepThread(300);
            ((CardLayout) panel.getLayout()).show(panel, cardName);
        }).start();

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
        List<NhaSanXuat> list = new ArrayList<>();
        ///do some database stuff hêre

        cbo.setModel(new DefaultComboBoxModel(list.toArray()));
        cbo.insertItemAt(new NhaSanXuat(null, lang == Lang.VN ? "Tất cả" : "All"), 0);
        cbo.setSelectedIndex(0);
    }

    public static void fillComboLoaiHang(JComboBox cbo, Lang lang) {
        List<LoaiHang> list = new ArrayList<>();
        ///do some database stuff hêre

        cbo.setModel(new DefaultComboBoxModel(list.toArray()));
        cbo.insertItemAt(new LoaiHang(null, lang == Lang.VN ? "Tất cả" : "All"), 0);
        cbo.setSelectedIndex(0);
    }

    public static List<ModelSanPham> search(JTextField field) {
        String text = field.getText();
        List<ModelSanPham> list = new ArrayList<>();
        ///do some database stuff hêre

        return list;
    }

    public static List<ModelSanPham> search(JComboBox cboNSX, JComboBox cboLoaiHang) {
        List<ModelSanPham> list = new ArrayList<>();
        String idNSX;
        String idLH;

        Object o = cboNSX.getSelectedItem();
        if (!(o instanceof NhaSanXuat)) {
            throw new UnsupportedOperationException("Không đúng combobox NSX");
        } else {
            idNSX = ((NhaSanXuat) o).getIdNSX();
        }

        Object o1 = cboLoaiHang.getSelectedItem();
        if (!(o1 instanceof LoaiHang)) {
            throw new UnsupportedOperationException("Không đúng combobox LoaiHang");
        } else {
            idLH = ((LoaiHang) o1).getIdLH();
        }

        //do some database stuff with idNSX va idLH
        //check null idNSX va idLH
        return list;
    }

    public static void showProductList(Container container, List<ModelSanPham> list) {
        for (Component com : container.getComponents()) {
            if (com instanceof ProductListPanel) {
                ((ProductListPanel) com).setList(list);
                showCard((JPanel) container, "ProductList");
            }
        }
    }
    
    public static void showDetails(Container container, ModelSanPham model){
        for (Component com : container.getComponents()) {
            if (com instanceof ProductDetailsPanel) {
                ((ProductDetailsPanel) com).setProductDetails(model);
                showCard((JPanel) container, "ProductDetails");
            }
        }
    }
}
