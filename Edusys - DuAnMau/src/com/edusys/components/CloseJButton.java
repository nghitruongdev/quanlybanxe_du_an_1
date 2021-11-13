package com.edusys.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

/**
 *
 * @author nghipc
 */
public class CloseJButton extends JButton {

    public CloseJButton() {
        super(new ImageIcon(CloseJButton.class.getResource("/com/edusys/img/icon/close_window_24px.png")));
        setBorderPainted(false);
        setOpaque(false);
        setContentAreaFilled(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseExited(MouseEvent me) {
                ((JButton) me.getSource()).setOpaque(false);
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                ((JButton) me.getSource()).setOpaque(true);
                ((JButton) me.getSource()).setBackground(new Color(240, 42, 51));
            }

            @Override
            public void mouseClicked(MouseEvent me) {
                ((JButton) me.getSource()).setOpaque(true);
                ((JButton) me.getSource()).setBackground(new Color(180, 42, 51));
                ((JDialog) ((JButton) me.getSource()).getTopLevelAncestor()).dispose();
            }
        });
    }

}
