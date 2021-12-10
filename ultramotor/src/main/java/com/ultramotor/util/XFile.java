package com.ultramotor.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XFile {

    static Path logos;
    static Path temp;

    public static File getTempFile(String prefix, String suffix) {
        File file = null;
        try {
            file = Files.createTempFile(prefix, suffix).toFile();
        } catch (IOException ex) {
            Logger.getLogger(XFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        file.deleteOnExit();
        return file;
    }

    public static void saveTemp(File file) throws IOException {
        temp = Files.createTempDirectory("cd");
        temp.toFile().deleteOnExit();
        File tmpFile = new File(temp.toFile(), file.getName());
        tmpFile.deleteOnExit();
        Files.copy(file.toPath(), tmpFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    public static void save(String tmpName, String newName) {
        File tmp = new File(temp.toFile(), tmpName);
        if (!tmp.exists()) {
            return;
        }
        try {
            logos = Files.createDirectories(Paths.get("logos"));
            String ext = tmpName.substring(tmpName.lastIndexOf("."));
            File dst = new File(logos.toFile(), newName + ext);
            Files.move(tmp.toPath(), dst.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
