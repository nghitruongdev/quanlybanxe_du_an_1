package com.ultramotor.util;

import com.swingx.ImageAvatar;
import com.swingx.PictureBox;
import java.awt.Container;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
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

    public static void uploadIcon(Container parent, JComponent comp, File defaultImage) {
        JFileChooser chooser = new JFileChooser();
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpeg", "jpg", "png", "gif", "bmp", "webmp"));
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.showOpenDialog(parent);
        setIcon(chooser.getSelectedFile(), comp, defaultImage);
    }

    public static Icon resize(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    public static void setIcon(File file, JComponent comp, File defaultImage) {
        ImageIcon icon = null;
        try {
            if (file == null || !file.isFile() || !file.exists()) {
                throw new Exception("Không tìm thấy file");
            }
            icon = new ImageIcon(ImageIO.read(file));
            if (icon == null) {
                throw new Exception("Không load được hình ảnh");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (defaultImage == null) {
                return;
            }
            file = defaultImage;
            try {
                icon = new ImageIcon(ImageIO.read(file));
            } catch (IOException ex) {
                Logger.getLogger(XImage.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            if (comp instanceof JLabel) {
                JLabel label = (JLabel) comp;
                label.setIcon(XImage.resize(icon, 220, 150));
                label.setToolTipText(file.getName());
            } else if (comp instanceof ImageAvatar) {
                ImageAvatar avt = (ImageAvatar) comp;
                avt.setIcon(icon);
                avt.setToolTipText(file.getName());
            } else if (comp instanceof PictureBox) {
                PictureBox avt = (PictureBox) comp;
                System.out.println("PictureBox is + " + comp);
                avt.setImage(icon);
                avt.setToolTipText(file.getName());
            }
            try {
                File newFile = new File(defaultImage.getParentFile(), file.getName());
                Files.copy(file.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
//                Desktop.getDesktop().open(newFile);
            } catch (IOException ex) {
                System.out.println("Không thể copy files");
            }
        }
    }

}
