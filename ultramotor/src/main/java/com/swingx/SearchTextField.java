package com.swingx;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.Timer;

public class SearchTextField extends TextField {

    Timer timer;

    public SearchTextField() {
        init();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                ImageIcon icon = new ImageIcon(getClass().getResource("/swingx/icon/loading_25px.gif"));
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

    private void init() {
        setOnlyField(true);
        setRoundBorder(true);
        setPrefixIcon(new ImageIcon(getClass().getResource("/swingx/icon/search_25px.png")));
        timer = new Timer(1000, (ActionEvent e) -> {
            checkState();
            setSuffixIcon(null);
        });
    }

    private void checkState() {
        if (getSuffixIcon() == null) {
            timer.stop();
        }
    }
}
