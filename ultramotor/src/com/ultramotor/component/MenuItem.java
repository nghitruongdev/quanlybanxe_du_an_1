package com.ultramotor.component;

import com.ultramotor.swingx.MenuButton;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author nghipc
 */
public class MenuItem extends MenuButton {

    private boolean selected;
    private boolean mouseOver;
//    private MenuButton button;
    public MenuItem(Icon icon, String text, int index) {
        super(icon, text);
        super.setIndex(index);
        init();
    }
    
    private void init(){
        setFocusPainted(false);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent me) {
                mouseOver = false;
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                mouseOver = true;
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                if (SwingUtilities.isLeftMouseButton(me)) {
                    if (mouseOver) {
                        JComponent com = (JComponent) me.getSource();
                        for(Component c : com.getParent().getComponents()){
                            if(c instanceof MenuItem && ((MenuItem)c).isSelected()){
                                    ((MenuItem)c).setSelected(false);
                            }
                        }
                        setSelected(true);
                    }
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        if (selected) {
            Graphics2D g2 = (Graphics2D) grphcs;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(1, 122, 167));
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
            g2.fillRect(0, 0, getWidth(), getHeight());
            g2.setComposite(AlphaComposite.SrcOver);
            g2.setColor(new Color(245, 245, 245));
            g2.fillRect(0, 0, 2, getHeight());
        }
        super.paintComponent(grphcs);
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        repaint();
    }
}
