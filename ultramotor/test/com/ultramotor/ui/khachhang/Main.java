/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ultramotor.ui.khachhang;

import com.ultramotor.dao.ModelSanPhamDAO;
import com.ultramotor.entity.LoaiHang;
import com.ultramotor.entity.ModelSanPham;
import com.ultramotor.entity.NhaSanXuat;

public class Main {
    public static void main(String[] args) {
        ModelSanPhamDAO dao = new ModelSanPhamDAO();
        for (ModelSanPham model : dao.getModelByNSXvaLoaiHang(new NhaSanXuat(null), new LoaiHang(null))) {
            System.out.println(model.toString());
        }
    }
}
