package com.ultramotor.util;

import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
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

    public static BufferedImage saveAsImage(File file) throws IOException {
        // open the document
        try (PDDocument doc = PDDocument.load(file)) {
            PDFRenderer ren = new PDFRenderer(doc);
            int count = doc.getNumberOfPages();
            if (count > 0) {
//                return ren.renderImage(0, 300, ImageType.RGB);
                return ren.renderImageWithDPI(0, 300);
            }
        }
        return null;
    }
}
