package com.ultramotor.util;

import com.swingx.ImageAvatar;
import com.swingx.PictureBox;
import java.awt.Image;
import java.io.File;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author nghipc
 */
public class XImage {

    public static Icon getIcon(String fileName) {
        URL url = XImage.class.getResource("/com/ultramotor/img/icon/" + fileName);
        return new ImageIcon(url);
    }

    public static File read(String fileName) {
        return new File("./logos/"+ fileName);
    }

    public static void uploadIcon(JComponent comp) {
        JFileChooser chooser = new JFileChooser();
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpeg", "jpg", "png", "gif", "bmp", "webmp"));
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.showOpenDialog(null);
        setIcon(chooser.getSelectedFile(), comp);
    }

    public static Icon resize(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    public static void setIcon(File file, JComponent comp) {
        try {
            if (file == null || !file.isFile() || !file.exists()) {
                throw new Exception("Không tìm thấy file");
            }
            ImageIcon icon = new ImageIcon(file.getAbsolutePath());
            if (icon == null) {
                throw new Exception("Không load được hình ảnh");
            }
            if (comp instanceof JLabel) {
                JLabel label = (JLabel) comp;
                label.setIcon(XImage.resize(icon, 220, 150));
                label.setToolTipText(file.getName());

            } else if (comp instanceof ImageAvatar) {
                ImageAvatar avt = (ImageAvatar) comp;
                avt.setIcon(icon);
                avt.setToolTipText(file.getName());
            }else if(comp instanceof PictureBox){
                PictureBox avt = (PictureBox) comp;
                System.out.println("PictureBox is + " + comp);
                avt.setImage(icon);
                avt.setToolTipText(file.getName());
            }
            XFile.saveTemp(file);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (!(comp instanceof JLabel) && !(comp instanceof ImageAvatar)) {
                return;
            }
            setIcon(XImage.read("default.png"), comp);
            comp.setToolTipText("default.png");
        }
    }
}
