package com.swingx;

import com.ultramotor.ui.khachhang.*;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;

public class Pagination extends JComponent {

    private int currentIndex;
    private String[] colors;
    private PaginationEvent event;
    private MigLayout layout;
    public static Map<String, Color> colorMap;

    static {
        colorMap = new HashMap<>();
        colorMap.put("Trắng", Color.white);
        colorMap.put("Đen", Color.black);
        colorMap.put("Xanh", Color.blue);
        colorMap.put("Vàng", Color.yellow);
        colorMap.put("Đỏ", Color.red);
    }

    public void setCurrentIndex(int index) {
        this.currentIndex = index;
        checkSelect();
    }
    public int getCurrentIndex(){
        return currentIndex;
    }
    private void checkSelect() {
        for (int i = 0; i < getComponentCount(); i++) {
            if (i == currentIndex) {
                ((Item) getComponent(i)).setClicked(true);
            } else {
                ((Item) getComponent(i)).setClicked(false);
            }
            ((Item) getComponent(i)).repaint();
            
        }
    }

    public void setTotalPage(int totalPage) {
        removeAll();
        for (int i = 0; i < totalPage; i++) {
            add(new Item(i, colorMap.getOrDefault(colors[i], Color.black), event), "w 25!, h 25!");
        }
        setCurrentIndex(0);
//        repaint();
//        revalidate();
    }

//    public void setAnimation(float alpha) {
//        Item item = (Item) getComponent(index);
//        item.setAlpha(alpha);
//        Item itemOut = (Item) getComponent(currentIndex);
//        itemOut.setAlpha(1f - alpha);
//        if (alpha == 1) {
//            currentIndex = index;
//        }
//    }
//    public int getIndex() {
//        return index;
//    }
//
//    public void setIndex(int index) {
//        this.index = index;
//    }
    public String[] getColors() {
        return colors;
    }

    public void setColors(String... colors) {
        this.colors = colors;
    }

    public PaginationEvent getEvent() {
        return event;
    }

    public void setEvent(PaginationEvent event) {
        this.event = event;
    }

    public static Map<String, Color> getColorMap() {
        return colorMap;
    }

    public static void setColorMap(Map<String, Color> colorMap) {
        Pagination.colorMap = colorMap;
    }

    public Pagination() {
        layout = new MigLayout("insets 5", "[fill, center]0[fill, center]");
        setLayout(layout);
    }
}

interface PaginationEvent {

    void onClick(int pageClick);
}

class Item extends JButton {

    private float alpha;
    private Color color;
    private int index;
    private boolean clicked;

    public void setAlpha(float alpha) {
        this.alpha = alpha;
        repaint();
    }

    public Item(int index, Color color, PaginationEvent event) {
        setFocusPainted(false);
        setContentAreaFilled(false);
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setBackground(Color.WHITE);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        addActionListener((ActionEvent e) -> {
            event.onClick(index);
        });

        this.index = index;
        this.color = color;
    }

    @Override
    public void paint(Graphics grphcs) {
        super.paint(grphcs);
        int width = getWidth();
        int height = getHeight();

        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        g2.setColor(getBackground());
        g2.setColor(color);
        g2.fillOval(6, 6, width-12, height-12);
        if (isClicked()) {
            g2.drawOval(0, 0, width-1, height-1);
        }
        g2.dispose();
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }
}
