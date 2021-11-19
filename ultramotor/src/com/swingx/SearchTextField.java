package com.swingx;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.Timer;

public class SearchTextField extends MyTextField {

    Timer timer;

    public SearchTextField() {
        setRoundBorder(true);
        timer = new Timer(1000, (ActionEvent e) -> {
            checkState();
            setSuffixIcon(null);
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                ImageIcon icon = new ImageIcon(getClass().getResource("/com/swingx/icon/loading_25px.gif"));
                setSuffixIcon(icon);
            }
            
            @Override
            public void keyReleased(KeyEvent ke) {
                if (!timer.isRunning()) {
                    timer.start();
                }
            }
        });
    }

    private void checkState() {
        if (getSuffixIcon() == null) {
            timer.stop();
        }
    }
}
