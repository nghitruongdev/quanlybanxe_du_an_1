package com.ultramotor.util;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

public class XPdf {

    public static void printPDF(File pdfFile) throws IOException, PrinterException {
        PDDocument doc = PDDocument.load(pdfFile);
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPageable(new PDFPageable(doc));
        if (job.printDialog()) {
            job.print();
        }
    }
}
