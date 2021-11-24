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

    private static final NhaSanXuatDAO daoNSX;
    private static final LoaiHangDAO daoLH;
    private static final ModelSanPhamDAO daoModel;

    static {
        daoNSX = new NhaSanXuatDAO();
        daoLH = new LoaiHangDAO();
        daoModel = new ModelSanPhamDAO();
    }

    public static void showCard(JPanel panel, String cardName) {
        ((CardLayout) panel.getLayout()).show(panel, cardName);
//        new Thread(() -> {
//            sleepThread(300);
//            
//        }).start();
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
        List<NhaSanXuat> list = daoNSX.selectAll();
        ///do some database stuff hêre

        cbo.setModel(new DefaultComboBoxModel(list.toArray()));
        cbo.insertItemAt(new NhaSanXuat(null, lang == Lang.VN ? "Tất cả" : "All"), 0);
        cbo.setSelectedIndex(0);
    }

    public static void fillComboLoaiHang(JComboBox cbo, Lang lang) {
        List<LoaiHang> list = daoLH.selectAll();
        ///do some database stuff hêre

        cbo.setModel(new DefaultComboBoxModel(list.toArray()));
        cbo.insertItemAt(new LoaiHang(null, lang == Lang.VN ? "Tất cả" : "All"), 0);
        cbo.setSelectedIndex(0);
    }

    public static List<ModelSanPham> search(JTextField field) {
        String text = field.getText();
        List<ModelSanPham> list = daoModel.selectAll();
        List<ModelSanPham> newList = new ArrayList<>();
        list.stream().filter(modelSanPham -> (modelSanPham.toString().contains(text))).forEachOrdered(modelSanPham -> {
            newList.add(modelSanPham);
        });
        return newList;
    }

    public static List<ModelSanPham> search(JComboBox cboNSX, JComboBox cboLoaiHang) {
        NhaSanXuat nsx = (NhaSanXuat) cboNSX.getSelectedItem();
        LoaiHang lh = (LoaiHang) cboLoaiHang.getSelectedItem();
        return daoModel.getModelByNSXvaLoaiHang(nsx, lh);
    }

    public static void showProductList(Container container, List<ModelSanPham> list) {
       for (Component com : container.getComponents()) {
                if (com instanceof ProductListPanel) {
                    ((ProductListPanel) com).setList(list);
                    showCard((JPanel) container, "ProductList");
                }
            }
    }

    public static void showDetails(Container container, ModelSanPham model) {
       for (Component com : container.getComponents()) {
                if (com instanceof ProductDetailsPanel) {
                    ((ProductDetailsPanel) com).setProductDetails(model);
                    showCard((JPanel) container, "ProductDetails");
                }
            }
    }

    public static void sleepThread(int miliseconds) {
        try {
            Thread.sleep(miliseconds);
        } catch (InterruptedException e) {

        }
    }

}
