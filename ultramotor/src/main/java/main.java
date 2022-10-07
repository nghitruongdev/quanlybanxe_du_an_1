import com.ultramotor.ui.khachhang.KhachHangFrame;
import com.ultramotor.ui.login.DangNhapJFrame;
import com.ultramotor.util.XImage;
import com.ultramotor.util.XProp;

import java.nio.file.Path;

public class main {

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DangNhapJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        boolean isCustomer = "customer".equalsIgnoreCase(XProp.getProperty("user"));
        if (isCustomer) {
            java.awt.EventQueue.invokeLater(() -> {
                new KhachHangFrame().setVisible(true);
            });
        } else {
            java.awt.EventQueue.invokeLater(() -> {
                DangNhapJFrame.getLoginFrame().setVisible(true);
            });
        }
    }

}