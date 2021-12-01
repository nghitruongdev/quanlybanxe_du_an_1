package com.swingx;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class TextField extends JFormattedTextField {

    public TextField() {
        placeholder = "";
        validInput = true;
        allowEmpty = true;
        onlyField = false;
        
        setBackground(new Color(255, 255, 255, 0));
        setOpaque(false);
        initBorder();
        setSelectionColor(new Color(76, 204, 255));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                mouseOver = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent me) {
                mouseOver = false;
                repaint();
            }
        });
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent fe) {
                showing(false);
            }

            @Override
            public void focusLost(FocusEvent fe) {
                showing(true);
            }
        });
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void begin() {
                animateHinText = getText().equals("");
            }

            @Override
            public void timingEvent(float fraction) {
                location = fraction;
                repaint();
            }

        };
        animator = new Animator(300, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);

    }

    private void initBorder() {
        int left = 10;
        int right = 10;
        int top = 10;
        int bottom = 10;
        if (prefixIcon != null) {
            left += prefixIcon.getIconWidth();
        }
        if (suffixIcon != null) {
            right += suffixIcon.getIconWidth();
        }
        int x = roundBorder ? 10 : 0;

        extra_top = !onlyField ? 25 : 0;
        extra_bottom = !onlyField ? 25 : 0;
        extra = extra_top + extra_bottom;
        setBorder(BorderFactory.createEmptyBorder(top + extra_top, left + x, bottom + extra_bottom, right + x));
    }

    private void showing(boolean action) {
        if (animator.isRunning()) {
            animator.stop();
        } else {
            location = 1;
        }
        animator.setStartFraction(1f - location);
        show = action;
        location = 1f - location;
        animator.start();
    }

    @Override
    public void paint(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        int width = getWidth();
        int height = getHeight();
        int radius = roundBorder ? height - extra : 0;

        g2.setColor(fieldBackground);
        g2.fillRoundRect(0, extra_top, width, height - extra, radius, radius);
        super.paint(grphcs);

        setLineColor(g2, mouseOver, false);
        if (drawLine) {
            g2.fillRect(0, height - extra_bottom - 2, width, 1);
        } else {
            g2.drawRoundRect(0, extra_top, width - 1, height - extra, radius, radius);
            g2.fillRect(roundBorder ? (radius / 2) - 5 : 0, height - extra_bottom - (onlyField ? 1 : 0), width - (roundBorder ? radius - 10 : 2), 1);
        }

        createHintText(g2);
        drawBorder(g2);
        paintIcon(g2);
        g2.dispose();
    }

    private void setLineColor(Graphics2D g2, boolean isMouseOver, boolean isFocus) {
        if (isMouseOver || isFocus) {
            g2.setColor(lineColor);
        } else {
            g2.setColor(new Color(150, 150, 150));
        }
    }

    private void drawBorder(Graphics2D g2) {
        int height = getHeight();
        int radius = roundBorder ? height - extra : 0;
        double width = getWidth() - 4 - (roundBorder && !drawLine ? radius - 10 : 2);
        if (isFocusOwner()) {
            setLineColor(g2, false, true);
            double size;
            if (show) {
                size = width * (1 - location);
            } else {
                size = width * location;
            }
            double x = (width - size) / 2 + (roundBorder && !drawLine ? radius / 2 - 5 : 0);
            if (drawLine) {
                g2.fillRect((int) (x), height - extra_bottom - 2, (int) size + 6, 2);
            } else {
                g2.drawRoundRect(0, extra_top, getWidth() - 2, height - extra, radius, radius);
                g2.drawRoundRect(1, extra_top + 1, getWidth() - 2, height - 1 - extra, radius, radius);
                g2.fillRect((int) (x), height - extra_bottom - 1 - (onlyField ? 1 : 0), (int) size + 6, 2);
            }
        }
    }

    private void createHintText(Graphics2D g2) {
        Insets in = getInsets();
        g2.setColor(hintColor);
        FontMetrics ft = g2.getFontMetrics();
        int fieldSize = (getHeight() - extra);
        double size = extra_top / 2 + fieldSize / 2;
        g2.setColor(getDisabledTextColor());
//        g2.setFont(new java.awt.Font(getFont().getName(), 0, 14));
        if (animateLabel) {
            if (animateHinText) {
                if (show) {
                    size = size * (1 - location);
                } else {
                    size = size * location;
                }
            }
            g2.drawString(label, in.left, (int) (getHeight() - extra_bottom - (fieldSize / 2) + ft.getMaxAscent() / 2 - size - (size != 0 ? 5 : 0)));

        } else {
            g2.drawString(label, in.left, (int) (0 + extra_top - 10));
            if (!"".equals(placeholder) && (getText().equals(""))) {
                g2.drawString(placeholder, in.left, (getHeight() - extra_bottom - (fieldSize / 2) + ft.getMaxAscent() / 2));
            }
        }
        if (!validInput) {
            g2.setColor(Color.red);
            g2.drawString(error, in.left, (getHeight() - extra_bottom / 2 + ft.getMaxAscent() / 2));
        }

    }

    private void paintIcon(Graphics2D g2) {
        if (prefixIcon != null) {
            Image prefix = ((ImageIcon) prefixIcon).getImage();
            int y = (getHeight() - prefixIcon.getIconHeight()) / 2;
            g2.drawImage(prefix, 10, y, this);
        }

        if (suffixIcon != null) {
            Image suffix = ((ImageIcon) suffixIcon).getImage();
            int y = (getHeight() - suffixIcon.getIconHeight()) / 2;
            g2.drawImage(suffix, getWidth() - suffixIcon.getIconWidth() - 10, y, this);
        }
    }

    private final Animator animator;
    private boolean animateHinText = true;
    private float location;
    private boolean show;
    private boolean mouseOver = false;
    private String label = "Label";
    private String error = "";
    private String placeholder = "";
    private boolean onlyField;
    private Color lineColor = new Color(3, 155, 216);
    private Color hintColor = new Color(150, 150, 150);
    private boolean roundBorder;
    private boolean drawLine;
    private boolean animateLabel;
    private boolean validInput;
    private boolean allowEmpty;
    private Color fieldBackground = new Color(255, 255, 255);
    private Icon prefixIcon;
    private Icon suffixIcon;

    private int extra_top = 0;
    private int extra_bottom = 0;
    private int extra = extra_top + extra_bottom;

    @Override
    public void setText(String string) {
        if (!getText().equals(string)) {
            showing(string == null || string.equals(""));
            if ("".equals(string)) {
                setValidInput(true);
            }
        }
        super.setText(string);
    }

    public String getLabelText() {
        return label;
    }

    public void setLabelText(String labelText) {
        this.label = labelText;
        repaint();
    }

    public Color getLineColor() {
        return lineColor;
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

//    public boolean isDrawLine() {
//        return drawLine;
//    }
    public void setDrawLine(boolean drawLine) {
        this.drawLine = drawLine;
    }

    public boolean isValidInput() {
        return validInput;
    }

    public void setValidInput(boolean validInput) {
        this.validInput = validInput;
        repaint();
    }

//    public boolean isAnimateLabel() {
//        return animateLabel;
//    }
    public void setAnimateLabel(boolean animate) {
        this.animateLabel = animate;
    }

//    public boolean isRoundBorder() {
//        return roundBorder;
//    }
    public void setRoundBorder(boolean roundBorder) {
        this.roundBorder = roundBorder;
        initBorder();
    }

    public boolean isAllowEmpty() {
        return allowEmpty;
    }

    public void setAllowEmpty(boolean allowEmpty) {
        this.allowEmpty = allowEmpty;
    }

    public String getErrorText() {
        return error;
    }

    public void setErrorText(String errorText) {
        this.error = errorText;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public Icon getPrefixIcon() {
        return prefixIcon;
    }

    public void setPrefixIcon(Icon prefixIcon) {
        this.prefixIcon = prefixIcon;
        initBorder();
    }

    public Icon getSuffixIcon() {
        return suffixIcon;
    }

    public void setSuffixIcon(Icon suffixIcon) {
        this.suffixIcon = suffixIcon;
        initBorder();
    }

    public boolean isOnlyField() {
        return onlyField;
    }

    public void setOnlyField(boolean onlyField) {
        this.onlyField = onlyField;
        initBorder();
    }

    public Color getHintColor() {
        return hintColor;
    }

    public void setHintColor(Color hintColor) {
        this.hintColor = hintColor;
    }

    
}
