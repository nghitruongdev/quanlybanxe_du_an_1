package com.ultramotor.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;

public class XReport {

    static File path = Paths.get("resources").toFile();

    public static void createHoaDonReport(String idHD, File file) throws JRException, FileNotFoundException {
        String invoice = new File(path, "invoice.jrxml").getPath();
        try (Connection con = XJdbc.getCon()) {
            Map<String, Object> paras = new HashMap<>();
            paras.put("idHD", idHD);
            fillReport(invoice, paras, con, file);
        } catch (SQLException ex) {
        }
    }

    public static void createNhanVienReport(File file) throws JRException {
        String jrFile = new File(path, "Staff-report.jrxml").getPath();
        try (Connection con = XJdbc.getCon()) {
            fillReport(jrFile, new HashMap<>(), con, file);
        } catch (SQLException ex) {
        }
    }

    private static void fillReport(String jrFile, Map paras, Connection con, File file) throws JRException {
        JasperDesign design = JRXmlLoader.load(jrFile);
        JasperReport report = JasperCompileManager.compileReport(design);
        JasperPrint print = JasperFillManager.fillReport(report, paras, con);
        exportReport(print, file);
    }

    private static void exportReport(JasperPrint print, File file) throws JRException {
        System.out.println(file.getPath());
        String ext = file.getPath().substring(file.getPath().lastIndexOf(".") + 1);
        switch (ext) {
            case "pdf":
                JasperExportManager.exportReportToPdfFile(print, file.getPath());
                break;
//            case "xls":
//            case "xlsx":
//                JRXlsExporter exporter = new JRXlsExporter();
//                SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
//                configuration.setOnePagePerSheet(true);
//                configuration.setDetectCellType(true); // Detect cell types (date and etc.)
//                configuration.setWhitePageBackground(false); // No white background!
//                configuration.setFontSizeFixEnabled(false);
//
//                // No spaces between rows and columns 
//                configuration.setRemoveEmptySpaceBetweenRows(true);
//                configuration.setRemoveEmptySpaceBetweenColumns(true);
//
//                exporter.setConfiguration(configuration);
//                print.setProperty("net.sf.jasperreports.export.xls.ignore.graphics", "true");
//                exporter.setExporterInput(new SimpleExporterInput(print));
//                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(file));
//                exporter.exportReport();
//                break;
        }

    }
}
