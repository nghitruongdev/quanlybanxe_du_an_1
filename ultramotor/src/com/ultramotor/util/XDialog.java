package com.ultramotor.util;

import com.swingx.CloseButton;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

public class XDialog {

    public static JDialog getDialog(JFrame frame, JPanel panel) {
        JDialog dialog = new JDialog(frame, true);
        dialog.setUndecorated(true);
//        dialog.setBackground(new Color(255, 255, 255, 0));
        JPanel con = new JPanel() {
            @Override
            public void paint(Graphics grphcs) {
                Graphics2D g2 = (Graphics2D) grphcs;
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), 5, 5);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                super.paint(grphcs);
            }
        };

        con.setOpaque(false);
        con.setBackground(new Color(250, 250, 250));
        con.setLayout(new MigLayout("inset 5 20 20 5", "[center]", "[20!][fill, center, grow]"));
        con.add(new CloseButton(), "al right, wrap");
        con.add(panel, "pushy, center, gapright 15");
//        dialog.setBounds(this.getWidth() / 2, this.getHeight(), this.getWidth() / 4, 0);

//        dialog.setSize(frame.getWidth() / 2, frame.getHeight());
        dialog.getContentPane().add(con);
        dialog.pack();

        dialog.setLocation(frame.getWidth() / 4, (frame.getHeight() - dialog.getHeight()) / 2);
        dialog.setLocationRelativeTo(frame);
        return dialog;
    }
}
