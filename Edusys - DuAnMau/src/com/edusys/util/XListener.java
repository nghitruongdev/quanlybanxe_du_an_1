package com.edusys.util;

import com.edusys.components.MyPasswordField;
import com.edusys.ui.DangNhapJDialog;
import java.awt.Window;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author nghipc
 */
public class XListener {

    public static WindowAdapter dialogLs = new WindowAdapter() {
        @Override
        public void windowOpened(WindowEvent we) {
            if (!Auth.isLogin()) {
                MsgBox.error("Vui lòng đăng nhập để sử dụng chức năng!");
                ((Window) we.getSource()).dispose();
                DangNhapJDialog.getDialog().setVisible(true);
            }
        }
    };

    public static void addVerifyPasswordFocus(MyPasswordField comp, MyPasswordField verifyComp) {
        verifyComp.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent fe) {
                if (!Arrays.equals(comp.getPassword(), verifyComp.getPassword())) {
                    verifyComp.getError().setText("Mật khẩu không trùng khớp");
                    verifyComp.setValidInput(false);
                    verifyComp.repaint();
                    comp.getError().setText("Mật khẩu không trùng khớp");
                    comp.setValidInput(false);
                    comp.repaint();
                }
            }
        });
    }

    public static void addRadioFocus(String titleBorder, JRadioButton... rdos) {
        for (JRadioButton rdo : rdos) {
            rdo.addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent fe) {
                    formatRadioPanelWhenFocus(rdo, false, titleBorder);
                }

                @Override
                public void focusGained(FocusEvent fe) {
                    formatRadioPanelWhenFocus(rdo, true, titleBorder);
                }
            });
        }
    }

    private static void formatRadioPanelWhenFocus(JRadioButton rdo, boolean isFocus, String title) {
        java.awt.Color color = isFocus ? MyConstants.PURPLE_COLOR : MyConstants.BLACK_COLOR;
        ((JPanel) rdo.getParent()).setBorder(javax.swing.BorderFactory.createTitledBorder(
                javax.swing.BorderFactory.createLineBorder(color),
                title,
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                MyConstants.DEFAULT_FONT,
                color));
    }
    
    
}
