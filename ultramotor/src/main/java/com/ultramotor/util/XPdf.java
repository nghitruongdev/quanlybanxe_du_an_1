package com.ultramotor.util;

import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import org.apache.pdfbox.rendering.PDFRenderer;

public class XPdf {

    public static void printPDF(File pdfFile) throws IOException, PrinterException {
        try (PDDocument doc = PDDocument.load(pdfFile)) {
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPageable(new PDFPageable(doc));
            if (job.printDialog()) {
                job.print();
            }
        }
    }

    public static BufferedImage saveAsImage(File pdfFile) throws IOException {
        // open the document
        try (PDDocument doc = PDDocument.load(pdfFile)) {
            PDFRenderer ren = new PDFRenderer(doc);
            int count = doc.getNumberOfPages();
            if (count > 0) {
//                return ren.renderImage(0, 300, ImageType.RGB);
                return ren.renderImageWithDPI(0, 300);
            }
        }
        return null;
    }
    
     //kiểm tra file có hợp lệ để lưu file excel
    public static boolean checkFileValid(File file) {
        if (!XFile.checkFileValid(file)) {
            return false;
        }
        //nếu phần mở rộng != xlsx
        if (!file.getName().endsWith(".pdf")) {
            MsgBox.error("Ứng dụng chỉ hỗ trợ file PDF!");
            file.delete();
            return false;
        }
        return true;
    }
    
    //Hiện hộp thoại lưu file excel
    public static File showSaveDialog(JFrame frame, String name) {
        File file = null;
        JFileChooser chooser = XFile.getFileChooser(name);
        while (!XPdf.checkFileValid(file)) {
            if (chooser.showSaveDialog(frame) != JFileChooser.APPROVE_OPTION) { //nếu người dùng khôn chọn save, dừng lưu file
                return null;
            } else {
                file = chooser.getSelectedFile();
            }
        }
        return file;
    }
}
