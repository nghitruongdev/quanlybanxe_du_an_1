package com.edusys.util;

import java.awt.Image;
import java.io.File;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author nghipc
 */
public class XImage {

    public static Image getAppIcon() {
        URL url = XImage.class.getResource("/com/edusys/img/logo.png");
        return new ImageIcon(url).getImage();
    }

    public static Icon getIcon(String fileName) {
        URL url = XImage.class.getResource("/com/edusys/img/icon/" + fileName);
        return new ImageIcon(url);
    }

    public static File read(String fileName) {
        return new File("logos", fileName);
    }

    public static void uploadIcon(JLabel label) {
        JFileChooser chooser = new JFileChooser();
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpeg", "jpg", "png", "gif", "bmp", "webmp"));
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.showOpenDialog(null);
        setIcon(chooser.getSelectedFile(), label);
    }

    public static Icon resize(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    public static void setIcon(File file, JLabel label) {
        try {
            if (file == null || !file.isFile() || !file.exists()) {
                throw new Exception();
            }
            javax.swing.ImageIcon icon = new javax.swing.ImageIcon(file.getAbsolutePath());
            if (icon == null) {
                throw new Exception();
            }
            label.setIcon(XImage.resize(icon, 150, 200));
            label.setToolTipText(file.getName());
            XFile.saveTemp(file);
        } catch (Exception e) {
            XImage.setIcon(XImage.read("default.png"), label);
            label.setToolTipText("default.png");
        }
    }

}
