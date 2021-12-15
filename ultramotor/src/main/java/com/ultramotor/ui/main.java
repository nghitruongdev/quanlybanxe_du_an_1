package com.ultramotor.ui;

import com.ultramotor.ui.khachhang.KhachHangFrame;
import com.ultramotor.ui.login.DangNhapJFrame;

public class main {

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DangNhapJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        boolean isNhanVien = true;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("user") && args[i + 1].equals("customer")) {
                isNhanVien = false;
            }
        }
        if (isNhanVien) {
            java.awt.EventQueue.invokeLater(() -> {
                DangNhapJFrame.getLoginFrame().setVisible(true);
            });
        } else {
            java.awt.EventQueue.invokeLater(() -> {
                new KhachHangFrame().setVisible(true);
            });
        }
    }
}
