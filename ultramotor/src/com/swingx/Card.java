/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swingx;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Map;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author nghipc
 */
public class Card extends JPanel {

    private MigLayout layout;
    private Pagination pag;
    private SlideShowPanel pnlSlide;
    private JLabel title;
    private Button button;

    public Card() {
        setOpaque(false);
        setBackground(new Color(250, 250, 250));
        layout = new MigLayout("insets 10", "[fill]", "[center]0[fill,center]");

        title = new JLabel("Hello World");
        title.setFont(new java.awt.Font("Segoe UI", 0, 14));

        pag = new Pagination();
        pnlSlide = new SlideShowPanel();
        addPagnitationEvent(pag, pnlSlide);
        button = new Button();
        button.setText("Xem thêm");
        button.setForeground(Color.white);
        button.setBackground(Color.decode("#54B75E"));
        button.setFont(new java.awt.Font("Segoe UI", 0, 14));

        JPanel panel = new JPanel(new MigLayout("insets 0", "[right, grow]0[leading]", "[fill]5[fill, center]"));
        panel.setOpaque(false);
        panel.add(pag, "gapleft 5, spanx, gapright 5");
        panel.add(title, " align leading, pushy, gapx 5, wrap");
        panel.add(button, "h 30!, w 50%!, center");

        setLayout(layout);
        add(pnlSlide, "w 100%, h 75%,pushy, wrap");
        add(panel, "w 100%, h 25%:25%:100");
    }

    public void addImagesAndColor(Map<String, Icon> colorMap) {
        int size = colorMap.size();
        String[] colors = new String[size];
        Icon[] hinhs = new Icon[size];
        int i = 0;
        for (Map.Entry<String, Icon> entry : colorMap.entrySet()) {
            colors[i] = entry.getKey();
            hinhs[i] = entry.getValue();
            i++;
        }

        addImages(hinhs);
        setPagnitationColors(colors);
    }

    public void addImages(Icon... images) {
        pnlSlide.addImages(images);
    }

    public void setPagnitationColors(String... colors) {
        pag.setColors(colors);
        pag.setTotalPage(colors.length);
    }

    private static void addPagnitationEvent(Pagination pag, SlideShowPanel panel) {
        PaginationEvent event = new PaginationEvent() {
            @Override
            public void onClick(int pageClick) {
                if (!panel.getAnimator().isRunning()) {
                    pag.setCurrentIndex(pageClick);
                }
                panel.navigate(pageClick);
            }
        };
        pag.setEvent(event);
    }

    @Override
    protected void paintComponent(Graphics grphcs) {

        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        super.paintComponent(grphcs);

    }

    public MigLayout getLayout() {
        return layout;
    }

    public Pagination getPag() {
        return pag;
    }

    public void setPag(Pagination pag) {
        this.pag = pag;
    }

    public SlideShowPanel getPnlSlide() {
        return pnlSlide;
    }

    public void setPnlSlide(SlideShowPanel pnlSlide) {
        this.pnlSlide = pnlSlide;
    }

    public JLabel getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public void removeButton() {
        this.button.getParent().remove(button);
    }
}
