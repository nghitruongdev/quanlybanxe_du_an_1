package com.ultramotor.util;

import com.ultramotor.ui.nhanvien.kho.nhapkho.ItemBean;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class XReport {

    final static File PATH = Paths.get("resources").toFile();
    static File logo = new File(PATH, "img/logo.png");
//    static String[] names = {"invoice.jrxml","Staff-report.jrxml","barcode-export.jrxml","product-report.jrxml","card-membership.jrxml"};
//    static{
//        new Thread(()->{
//            for (String name : names) {
//                try {
//                    saveReportToFile(new File(PATH, name));
//                } catch (JRException ex) {
//                }
//            }
//        }).start();
//    }
    
    private static InputStream getStream(String fileName) {
        return XReport.class.getResourceAsStream("/jasper/" + fileName);
    }

    public static void createHoaDonReport(String idHD, File file) throws JRException, FileNotFoundException, IOException {
        String invoice = new File(PATH, "invoice.jasper").getPath();
        try (Connection con = XJdbc.getCon()) {
            Map<String, Object> paras = new HashMap<>();
            paras.put("idHD", idHD);
            paras.put("logo", logo);
            fillReport(invoice, paras, con, file);
        } catch (SQLException ex) {
        }
    }

    public static void createNhanVienReport(File file) throws JRException {
        String jrFile = new File(PATH, "Staff-report.jasper").getPath();

        try (Connection con = XJdbc.getCon()) {
            Map<Object, Object> map = new HashMap<>();
            map.put("logo", logo);
            fillReport(jrFile, map, con, file);
        } catch (SQLException ex) {
        }
    }

    public static void createBarcodeReport(List<ItemBean> list, File file) throws JRException {
        String barcode = new File(PATH, "barcode-export.jasper").getPath();

        JRBeanCollectionDataSource src = new JRBeanCollectionDataSource(list);
        Map<Object, Object> map = new HashMap<>();
        map.put("logo", logo);
        fillReport(barcode, map, src, file);
    }

    public static void createThanhVienCard(List<File> list, File file) throws JRException {
        String card = new File(PATH, "card-membership.jasper").getPath();

        JRBeanCollectionDataSource src = new JRBeanCollectionDataSource(list);
        Map<Object, Object> map = new HashMap<>();
        map.put("logo", logo);
        fillReport(card, map, src, file);
    }

//    private static void fillReport(JasperReport report, Map paras, JRBeanCollectionDataSource src, File file) throws JRException {
//        JasperPrint print = JasperFillManager.fillReport(report, paras, src);
//        exportReport(print, file);
//    }
//
//    private static void fillReport(JasperReport report, Map paras, Connection src, File file) throws JRException {
//        JasperPrint print = JasperFillManager.fillReport(report, paras, src);
//        exportReport(print, file);
//    }

    private static void fillReport(String report, Map paras, Connection src, File file) throws JRException {
        JasperPrint print = JasperFillManager.fillReport(report, paras, src);
        exportReport(print, file);
    }
    
    private static void fillReport(String report, Map paras, JRBeanCollectionDataSource src, File file) throws JRException {
        JasperPrint print = JasperFillManager.fillReport(report, paras, src);
        exportReport(print, file);
    }

    private static JasperReport getReport(InputStream jrFile) throws JRException {
        JasperDesign design = JRXmlLoader.load(jrFile);
        return JasperCompileManager.compileReport(design);
    }

    private static void saveReportToFile(File jrFile) throws JRException {
        JasperDesign design = JRXmlLoader.load(jrFile);
        JasperCompileManager.compileReportToFile(design, new File(PATH, jrFile.getName().substring(0, jrFile.getName().lastIndexOf(".")) + ".jasper").getPath());
    }

    private static void exportReport(JasperPrint print, File file) throws JRException {
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
