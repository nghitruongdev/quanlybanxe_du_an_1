package com.ultramotor.util;

import com.swingx.ImageAvatar;
import com.swingx.PictureBox;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author nghipc
 */
public class XImage {

    private static final Path LOGO_PATH = Paths.get(System.getProperty("user.dir"), "ultramotor", "logos");
    public static final Path STAFF_PATH = LOGO_PATH.resolve("nhanvien");
    public static final Path PRODUCT_PATH = LOGO_PATH.resolve("sp");
    public static void uploadIcon(Container parent, JComponent comp, File defaultImage) {

        JFileChooser chooser = new JFileChooser();
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpeg", "jpg", "png", "gif", "bmp", "webmp"));
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (chooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION) {
            setIcon(chooser.getSelectedFile(), comp, defaultImage);
        }
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
                avt.setImage(icon);
                avt.setToolTipText(file.getName());
            }
            try {
                File newFile = new File(defaultImage.getParentFile(), file.getName());
                Files.copy(file.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
//                Desktop.getDesktop().open(newFile);
            } catch (IOException ex) {
//                System.out.println("Không thể copy files");
            }
        }
    }

    public static BufferedImage createImage(JPanel panel) {
        int width = panel.getPreferredSize().width;
        int height = panel.getPreferredSize().height;
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bi.createGraphics();
        g.setBackground(Color.white);
        g.setColor(Color.white);
        panel.paint(g);
        g.dispose();
        return bi;
    }

}
