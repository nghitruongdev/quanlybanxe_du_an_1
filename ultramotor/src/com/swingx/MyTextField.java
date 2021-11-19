package com.swingx;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import javax.swing.Icon;
import javax.swing.JFormattedTextField;
import javax.swing.border.EmptyBorder;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class MyTextField extends JFormattedTextField {

    public MyTextField() {
        setBackground(new Color(255, 255, 255, 0));
        setBorder(new EmptyBorder(20, 10, 10, 10));
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
        int radius = roundBorder ? height : 0;

        g2.setColor(fieldBackground);
        g2.fillRoundRect(0, 0, width, height, radius, radius);
        super.paint(grphcs);

        if (mouseOver) {
            g2.setColor(lineColor);
        } else {
            g2.setColor(new Color(150, 150, 150));
        }
        if (drawLine) {
            g2.fillRect(0, height - 1, width, 1);
        } else {
            g2.drawRoundRect(0, 0, width - 1, height, radius, radius);
            g2.fillRect(roundBorder ? (radius / 2) - 5 : 0, height - 1, width - (roundBorder ? radius - 10 : 2), 1);
        }

        createHintText(g2);
        initBorder(g2);

        g2.dispose();
    }

    private void initBorder(Graphics2D g2) {
        int height = getHeight();
        int radius = roundBorder ? height : 0;
        double width = getWidth() - 4 - (roundBorder && !drawLine ? radius - 10 : 2);
        if (isFocusOwner()) {
            g2.setColor(lineColor);
            double size;
            if (show) {
                size = width * (1 - location);
            } else {
                size = width * location;
            }
            double x = (width - size) / 2 + (roundBorder && !drawLine ? radius / 2 - 5 : 0);
            if (drawLine) {
                g2.fillRect((int) (x), height - 2, (int) size + 6, 2);
            } else {
                g2.drawRoundRect(0, 0, getWidth()-2, height, radius, radius);
                g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2, radius, radius);
                g2.fillRect((int) (x), height - 2, (int) size + 6, 2);
            }
        }
    }

    private void createHintText(Graphics2D g2) {
        Insets in = getInsets();
        g2.setColor(new Color(150, 150, 150));
        FontMetrics ft = g2.getFontMetrics();
        Rectangle2D r2 = ft.getStringBounds(labelText, g2);
        double height = getHeight() - in.top - in.bottom;
        double textY = (height - r2.getHeight()) / 2;
        double size = 18;
        g2.setColor(getDisabledTextColor());
//        g2.setFont(new java.awt.Font(getFont().getName(), 0, 14));
        if (animateLabel) {
            if (animateHinText) {
                if (show) {
                    size = 18 * (1 - location);
                } else {
                    size = 18 * location;
                }
            }
            g2.drawString(labelText, in.right, (int) (in.top + textY + ft.getAscent() - size));
        } else {
            g2.drawString(labelText, in.right, (int) (in.top + textY + ft.getAscent() - size));
            if (!"".equals(placeholder) && (getText().equals(""))) {
                g2.drawString(placeholder, getInsets().left, (getHeight() - ft.getMaxAscent()));
            }
        }

    }
    
    private void updateSize(){
        
    }
    private int radius;
    private int width;
    private int height;
    
    private final Animator animator;
    private boolean animateHinText = true;
    private float location;
    private boolean show;
    private boolean mouseOver = false;
    private String labelText = "Label";
    private String errorText = "";
    private String placeholder = "";

    private Color lineColor = new Color(3, 155, 216);

    private boolean roundBorder;
    private boolean drawLine;
    private boolean animateLabel;
    private boolean validInput;
    private boolean allowEmpty;
    private Color fieldBackground = new Color(255, 255, 255);
    private Icon prefixIcon;
    private Icon suffixIcon;

    @Override
    public void setText(String string) {
        if (!getText().equals(string)) {
            showing(string.equals(""));
        }
        super.setText(string);
    }

    public String getLabelText() {
        return labelText;
    }

    public void setLabelText(String labelText) {
        this.labelText = labelText;
    }

    public Color getLineColor() {
        return lineColor;
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    public boolean isDrawLine() {
        return drawLine;
    }

    public void setDrawLine(boolean drawLine) {
        this.drawLine = drawLine;
    }

    public boolean isValidInput() {
        return validInput;
    }

    public void setValidInput(boolean validInput) {
        this.validInput = validInput;
    }

    public boolean isAnimateLabel() {
        return animateLabel;
    }

    public void setAnimateLabel(boolean animate) {
        this.animateLabel = animate;
    }

    public boolean isRoundBorder() {
        return roundBorder;
    }

    public void setRoundBorder(boolean roudBorder) {
        this.roundBorder = roudBorder;
        int x = roundBorder ? 20 : 10;
//        setBorder(new EmptyBorder(20, x, 10, x));
    }

    public boolean isAllowEmpty() {
        return allowEmpty;
    }

    public void setAllowEmpty(boolean allowEmpty) {
        this.allowEmpty = allowEmpty;
    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public Icon getPrefixIcon() {
        return prefixIcon;
    }

    public void setPrefixIcon(Icon prefixIcon) {
        this.prefixIcon = prefixIcon;
    }

    public Icon getSuffixIcon() {
        return suffixIcon;
    }

    public void setSuffixIcon(Icon suffixIcon) {
        this.suffixIcon = suffixIcon;
    }

}
