package com.ultramotor.ui.khachhang;

import java.awt.CardLayout;
import javax.swing.JPanel;


public class KhachHangController {
      public static void navigateCard(JPanel panel, boolean isNext) {
        CardLayout layout = (CardLayout) panel.getLayout();
        if (isNext) {
            layout.next(panel);
        } else {
            layout.previous(panel);
        }
    }
}
