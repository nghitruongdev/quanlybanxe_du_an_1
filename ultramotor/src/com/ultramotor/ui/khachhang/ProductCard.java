package com.ultramotor.ui.khachhang;

import com.swingx.Card;
import com.ultramotor.entity.ModelSanPham;
import com.ultramotor.entity.SanPham;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class ProductCard extends Card {

    private ModelSanPham model;

    public ProductCard() {
    }

    public ProductCard(ModelSanPham model) {
        this.model = model;
        initCard();
    }

    public ModelSanPham getModel() {
        return model;
    }

    public void setModel(ModelSanPham model) {
        this.model = model;
        initCard();
    }

    private void initCard() {
        super.addImagesAndColor(getImageAndColor(model.getSanPhamList()));
        super.setTitle(model.toString());
    }
    
    private Map<String, Icon> getImageAndColor(List<SanPham> list){
        Map<String, Icon> map = new HashMap<>();
        for (SanPham sp : list) {
            try{
            map.put(sp.getMauSac(), new ImageIcon(getClass().getResource("/com/ultramotor/img/sp/" + sp.getHinh())));
            }catch(Exception e){
            }
        }
        return map;
    }
}
