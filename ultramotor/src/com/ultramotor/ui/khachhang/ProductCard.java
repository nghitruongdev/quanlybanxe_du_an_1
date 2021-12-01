package com.ultramotor.ui.khachhang;

import com.swingx.Card;
import com.ultramotor.entity.ModelSanPham;
import com.ultramotor.entity.SanPham;
import java.awt.Image;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class ProductCard extends Card {

    private ModelSanPham model;

    public ProductCard() {
    }

    public ProductCard(ModelSanPham model, Lang lang) {
        this.model = model;
        initCard(lang);
    }

    public ModelSanPham getModel() {
        return model;
    }

    public void setModel(ModelSanPham model, Lang lang) {
        this.model = model;
        initCard(lang);
    }

    private void initCard(Lang lang) {
        super.addImagesAndColor(getImageAndColor(model.getSanPhamList()));
        super.setTitle(model.toString());
        super.setInfo(String.format("%s: %d", lang == Lang.EN ? "Sales" : "Đã bán", model.getSoLuongBan()));
        super.getButton().setText(lang == Lang.EN ? "View more" : "Xem thêm");
    }

    private Map<String, Icon> getImageAndColor(List<SanPham> list) {
        Map<String, Icon> map = new HashMap<>();
        for (SanPham sp : list) {
            ImageIcon icon = null;
            try {
//                new ImageIcon(getClass().getResource("/com/ultramotor/img/sp/" + sp.getHinh()))
                Image image = ImageIO.read(new File("./logos/sp/" + sp.getHinh()));
                icon = new ImageIcon(image);
//                map.put(sp.getMauSac(), new ImageIcon(image.getScaledInstance(1800, 1135, Image.SCALE_SMOOTH)));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            map.put(sp.getMauSac(), icon);

        }
        return map;
    }
}
