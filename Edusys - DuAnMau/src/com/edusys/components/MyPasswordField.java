package com.edusys.components;

import static com.edusys.util.MyConstants.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

/**
 *
 * @author nghipc
 */
public class MyPasswordField extends JPasswordField {

    private JLabel label;
    private JLabel error;
    private boolean validInput;
    private String placeholder;
    private boolean drawLine;

    public MyPasswordField() {
        setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        setFont(DEFAULT_FONT);
        if (error != null) {
            error.setFont(new java.awt.Font(FONT_NAME, 0, 8));
        }
        setEchoChar('\u25cf');
        addListeners();
        validInput = true;
        drawLine = false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintBorders(g);
        paintPlaceHolder(g);
        formatFieldValid(isFocusOwner(), validInput);

    }

    @Override
    public void setText(String string) {
        super.setText(string);
        reset();
    }

    private void paintPlaceHolder(Graphics g) {
        final Graphics2D g2 = (Graphics2D) g;
        if (placeholder == null || placeholder.length() == 0 || getPassword().length > 0 ) {
            return;
        }
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setFont(new java.awt.Font(FONT_NAME, 0, 12));
        g2.setColor(getDisabledTextColor());
        g2.drawString(placeholder, getInsets().left, (getHeight() - g.getFontMetrics().getMaxAscent()));

    }

    private void addListeners() {
        addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusLost(FocusEvent fe) {
               validateField();
            }

            @Override
            public void focusGained(FocusEvent fe) {
                validateField();
                reset();
            }
        });

        addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                validateField();
            }

        });
    }

    private void validateField() {
        if (getInputVerifier() == null) {
            return;
        }
        this.validInput = getInputVerifier().verify(this);
        repaint();
    }

    private void formatFieldValid(boolean isFocus, boolean isValid) {
        if (isFocus) {
            Color color = isValid ? PURPLE_COLOR : RED_COLOR;
            Color errColor = isValid ? WHITE_COLOR : RED_COLOR;
            setForeground(color);
            setCaretColor(color);
            if (label != null) {
                label.setForeground(color);
            }
            if (error != null) {
                error.setForeground(errColor);
            }
        } else {
            Color color = isValid ? BLACK_COLOR : RED_COLOR;
            Color errColor = isValid ? WHITE_COLOR : RED_COLOR;
            setForeground(color);
            setCaretColor(color);
            if (label != null) {
                label.setForeground(color);
            }
            if (error != null) {
                error.setForeground(errColor);
            }
        }
    }

    private void reset() {
        if (getPassword().length == 0) {
            if (error != null) {
                error.setForeground(WHITE_COLOR);
            }
            validInput = true;
            repaint();
        }
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public boolean isValidInput() {
        return validInput;
    }

    public void setValidInput(boolean validInput) {
        this.validInput = validInput;
    }

    public JLabel getError() {
        return error;
    }

    public void setError(JLabel error) {
        this.error = error;
    }

    public boolean isDrawLine() {
        return drawLine;
    }

    public void setDrawLine(boolean drawLine) {
        this.drawLine = drawLine;
    }

    private void paintBorders(Graphics g) {
        if (isFocusOwner() && validInput) {
            initBorder(g, PURPLE_COLOR);
        } else if (validInput) {
            initBorder(g, LIGHT_GRAY_COLOR);
        } else {
            initBorder(g, RED_COLOR);
        }
    }

    private void initBorder(Graphics g, Color color) {
        g.setColor(color);
        if (drawLine) {
            g.drawLine(0, getHeight() - 2, getWidth(), getHeight() - 2);
        } else {
            g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        }
    }

}
