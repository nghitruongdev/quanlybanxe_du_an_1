/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swingx;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JMenuItem;

/**
 *
 * @author nghipc
 */
public class PopupMenuItem extends JMenuItem {

    public PopupMenuItem(String text, Icon icon, Icon mouseOverIcon, ActionListener e) {
        super(text, icon);
        setBackground(new Color(250, 250, 250));
        setFont(new Font("Segoe UI", 0, 14));
        setPreferredSize(new Dimension(150, 40));
        setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        this.addActionListener(e);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                setIcon(icon);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setIcon(mouseOverIcon);
            }
        });
    }

}
