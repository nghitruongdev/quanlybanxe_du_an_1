package com.ultramotor.util;

import java.awt.Component;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.*;

/**
 *
 * @author nghipc
 */
public class MsgBox {
    
    public static final int YES_OPTION = 0;
    public static final int NO_OPTION = 1;
    public static final int CANCEL_OPTION = 2;
    
    public static void inform(String message) {
        info(null, message, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void warning(String message) {
        info(null, message, JOptionPane.WARNING_MESSAGE);
    }

    public static void error(String message) {
        info(null, message, JOptionPane.ERROR_MESSAGE);
    }

    public static int confirm(String message, boolean cancelOption) {
        if (cancelOption) {
            return confirm(null, message, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        } else {
            return confirm(null, message, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        }
    }

    public static String input(String message) {
        return input(null, message, JOptionPane.QUESTION_MESSAGE);
    }

    private static void info(Component comp, String message, int type) {
        showMessageDialog(comp, message, "Hệ thống quản lý đào tạo", type);
    }

    private static int confirm(Component comp, String message, int option, int type) {
        int k = showConfirmDialog(comp, message, "Hệ thống quản lý đào tạo", option, type);
        return k;
    }

    private static String input(Component comp, String message, int type) {
        return showInputDialog(comp, message, "Hệ thống quản lý đào tạo", type);
    }
}
