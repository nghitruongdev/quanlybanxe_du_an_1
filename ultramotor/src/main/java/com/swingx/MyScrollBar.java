/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swingx;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.plaf.basic.BasicScrollBarUI;

/**
 *
 * @author nghipc
 */
public class MyScrollBar extends JScrollBar {

    public MyScrollBar() {
        setUI(new ModernScrollBarUI());
        setPreferredSize(new Dimension(8, 8));
        setForeground(new Color(48, 144, 216));
        setBackground(Color.WHITE);
    }

    class ModernScrollBarUI extends BasicScrollBarUI {

        final int THUMB_SIZE = 50;

        @Override
        protected Dimension getMaximumThumbSize() {
            if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
                return new Dimension(0, THUMB_SIZE);
            } else {
                return new Dimension(THUMB_SIZE, 0);
            }
        }

        @Override
        protected Dimension getMinimumThumbSize() {
            if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
                return new Dimension(0, THUMB_SIZE);
            } else {
                return new Dimension(THUMB_SIZE, 0);
            }
        }

        @Override
        protected void paintTrack(Graphics grphcs, JComponent jc,
                Rectangle rtg
        ) {
            Graphics2D g2 = (Graphics2D) grphcs;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int orientation = scrollbar.getOrientation();
            int size, x, y, width, height;
            if (orientation == JScrollBar.VERTICAL) {
                size = rtg.width / 2;
                x = rtg.x + ((rtg.width - size) / 2);
                y = rtg.y;
                width = size;
                height = rtg.height;
            } else {
                size = rtg.height / 2;
                y = rtg.y + ((rtg.height - size) / 2);
                x = rtg.x;
//            x =0;
                width = rtg.width;
                height = size;
            }
            g2.setColor(new Color(240, 240, 240));
            g2.fillRect(x, y, width, height);
        }

        @Override
        protected void paintThumb(Graphics grphcs, JComponent jc, Rectangle rtg) {
            Graphics2D g2 = (Graphics2D) grphcs;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int x = rtg.x;
            int y = rtg.y;
            int width = rtg.width;
            int height = rtg.height;
            if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
                y += 8;
                height -= 16;
            } else {
                x += 8;
                width -= 16;
            }
            g2.setColor(scrollbar.getForeground());
            g2.fillRoundRect(x, y, width, height, 10, 10);
        }

        @Override
        protected JButton createIncreaseButton(int i) {
            return new ScrollBarButton();
        }

        @Override
        protected JButton createDecreaseButton(int i) {
            return new ScrollBarButton();
        }

        private class ScrollBarButton extends JButton {

            public ScrollBarButton() {
            }

            @Override
            public void paint(Graphics grphcs) {
            }

        }
    }
}
