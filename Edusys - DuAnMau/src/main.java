
import com.edusys.ui.DangNhapJDialog;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author nghipc
 */
public class main {

    public static void main(String[] args) {
        try {
            //thay đổi L&F
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            //thay đổi L&F của PRGBAR
            UIManager.put("ProgressBar.foreground", new Color(152, 162, 244));
            UIManager.put("ProgressBar.selectionBackground", new Color(58, 120, 188));
            UIManager.put("ProgressBar.selectionForeground", new Color(255, 255, 255));
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) {
        }
        
        //start main program
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DangNhapJDialog.getDialog().setVisible(true);
            }
        });
    }
}
