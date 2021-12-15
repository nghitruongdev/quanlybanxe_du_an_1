package com.ultramotor.ui.khachhang;

import com.swingx.Card;
import com.ultramotor.entity.ModelSanPham;
import com.ultramotor.entity.SanPham;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class ProductCard extends Card {

    private ModelSanPham model;

    public ProductCard() {
    }

    public ProductCard(ModelSanPham model, Lang lang) {
        this.model = model;
//        this.setBackground(Color.white);
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
        File path = Paths.get("logos", "sp").toFile();
        list.forEach(sp -> {
            try {
                //            map.put(sp.getMauSac(), new ImageIcon(new File(path, sp.getHinh()).getPath()));
                map.put(sp.getMauSac(), new ImageIcon(ImageIO.read(new File(path, sp.getHinh()))));
            } catch (IOException ex) {
                map.put(sp.getMauSac(), new ImageIcon(new File(path, "default.png").getPath()));
            }

        });
        return map;
    }
}
