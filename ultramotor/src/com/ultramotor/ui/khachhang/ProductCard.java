/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ultramotor.ui.khachhang;

import com.swingx.Card;
import com.ultramotor.entity.ModelSanPham;

/**
 *
 * @author nghipc
 */
public class ProductCard extends Card {

    private ModelSanPham model;

    public ProductCard(ModelSanPham model) {
        super.addImagesAndColor(model.getColorHinhMap());
        super.setTitle(model.toString());
        this.model = model;

    }

    public ModelSanPham getModel() {
        return model;
    }

    public void setModel(ModelSanPham model) {
        this.model = model;
    }

}
