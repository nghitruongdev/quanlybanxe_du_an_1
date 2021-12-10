package com.ultramotor.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

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
    
    /**
     * Hiện hộp thoại lưu file excel
     *
     * @param name tên loại dữ liệu
     * @return file nơi dữ liệu sẽ được lưu vào
     */
    public static JFileChooser getFileChooser(String name) {
        JFileChooser chooser = new JFileChooser();
        //đề xuất tên mặc định cho file lưu theo tên dữ liệu cần lưu + ngày tháng
        File file = new File(name);
        chooser.setSelectedFile(file);
        return chooser;
    }

    public static boolean checkFileValid(File file) {
        if (file == null) {
//            MsgBox.error("Không tìm thấy file");
            return false;
        }

        if (file.getName().isEmpty()) {
            MsgBox.error("Vui lòng không để trống tên file");
            file.delete();
            return false;
        }

        //nếu file đã tồn tại, hỏi xác nhận ghi đè
        if (file.exists()) {
            if (MsgBox.confirm("File đã tồn tại, bạn có muốn ghi đè lên file cũ?", false) != 0) {
                return false;
            }
            if (!file.canWrite()) {
                MsgBox.error("Không thể ghi đè file! Vui lòng kiểm tra lại");
                return false;
            }

        }
        return true;
    }
//    public static void saveTemp(File file) throws IOException {
//        temp = Files.createTempDirectory("cd");
//        temp.toFile().deleteOnExit();
//        File tmpFile = new File(temp.toFile(), file.getName());
//        tmpFile.deleteOnExit();
//        Files.copy(file.toPath(), tmpFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
//    }

//    public static void save(String tmpName, String newName) {
//        File tmp = new File(temp.toFile(), tmpName);
//        if (!tmp.exists()) {
//            return;
//        }
//        try {
//            logos = Files.createDirectories(Paths.get("logos"));
//            String ext = tmpName.substring(tmpName.lastIndexOf("."));
//            File dst = new File(logos.toFile(), newName + ext);
//            Files.move(tmp.toPath(), dst.toPath(), StandardCopyOption.REPLACE_EXISTING);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
}
