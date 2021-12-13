package com.ultramotor.util;

import com.ultramotor.ui.nhanvien.kho.nhapkho.ItemBean;
import java.io.File;
import java.io.FileNotFoundException;
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

    static File path = Paths.get("resources").toFile();

    public static void createHoaDonReport(String idHD, File file) throws JRException, FileNotFoundException {
        String invoice = new File(path, "invoice.jrxml").getPath();
        try (Connection con = XJdbc.getCon()) {
            Map<String, Object> paras = new HashMap<>();
            paras.put("idHD", idHD);
            paras.put("logo", new File(path, "img/logo.png"));
            fillReport(getReport(invoice), paras, con, file);
        } catch (SQLException ex) {
        }
    }

    public static void createNhanVienReport(File file) throws JRException {
        String jrFile = new File(path, "Staff-report.jrxml").getPath();
        try (Connection con = XJdbc.getCon()) {
            Map<Object, Object> map = new HashMap<>();
            map.put("logo", new File(path, "img/logo.png"));
            fillReport(getReport(jrFile), map, con, file);
        } catch (SQLException ex) {
        }
    }

    public static void createBarcodeReport(List<ItemBean> list, File file) throws JRException {
        String barcode = new File(path, "barcode-export.jrxml").getPath();
        JRBeanCollectionDataSource src = new JRBeanCollectionDataSource(list);
        Map<Object, Object> map = new HashMap<>();
        map.put("logo", new File(path, "img/logo.png"));
        fillReport(getReport(barcode), map, src, file);
    }

    public static void createThanhVienCard(List<File> list, File file) throws JRException {
        String card = new File(path, "card-membership.jrxml").getPath();
        JRBeanCollectionDataSource src = new JRBeanCollectionDataSource(list);
        Map<Object, Object> map = new HashMap<>();
        map.put("logo", new File(path, "img/logo.png"));
        fillReport(getReport(card), map, src, file);
    }

    private static void fillReport(JasperReport report, Map paras, JRBeanCollectionDataSource src, File file) throws JRException {
        JasperPrint print = JasperFillManager.fillReport(report, paras, src);
        exportReport(print, file);
    }

    private static void fillReport(JasperReport report, Map paras, Connection src, File file) throws JRException {
        JasperPrint print = JasperFillManager.fillReport(report, paras, src);
        exportReport(print, file);
    }

    private static JasperReport getReport(String jrFile) throws JRException {
        JasperDesign design = JRXmlLoader.load(jrFile);
        return JasperCompileManager.compileReport(design);
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
