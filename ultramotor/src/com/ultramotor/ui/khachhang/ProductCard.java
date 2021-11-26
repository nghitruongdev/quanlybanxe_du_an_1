package com.ultramotor.ui.khachhang;

import com.swingx.Card;
import com.ultramotor.entity.ModelSanPham;
import com.ultramotor.entity.SanPham;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

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
//                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        return map;
    }
    
    public static void main(String[] args) throws IOException {
        JFrame fr = new JFrame();
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setSize(500,300);
        JLabel label = new JLabel();
        label.setIcon(new ImageIcon(ImageIO.read(new File("./src/com/ultramotor/img/sp/" + "abden.jpg"))));
        fr.getContentPane().add(label);
        fr.pack();
        fr.setVisible(true);
    }
}
