package com.swingx;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

public class CloseButton extends Button {

    public CloseButton() {
        setText("");
        setIcon(new ImageIcon(CloseButton.class.getResource("/com/swingx/icon/close_window_red_20px.png")));
        setBackground(new Color(255, 102, 102));
        setTransparent(true);
        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseExited(MouseEvent me) {
                setTransparent(true);
                setIcon(new ImageIcon(CloseButton.class.getResource("/com/swingx/icon/close_window_red_20px.png")));
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                setTransparent(false);
                setIcon(new ImageIcon(CloseButton.class.getResource("/com/swingx/icon/close_window_20px.png")));
            }

            @Override
            public void mouseClicked(MouseEvent me) {
//                ((JButton) me.getSource()).setOpaque(true);
//                ((JButton) me.getSource()).setBackground(new Color(180, 42, 51));
                ((JDialog) ((JButton) me.getSource()).getTopLevelAncestor()).dispose();
            }
        });
    }

}
