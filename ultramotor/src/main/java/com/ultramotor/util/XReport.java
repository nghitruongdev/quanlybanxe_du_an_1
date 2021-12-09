package com.ultramotor.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class XReport {

    static File path = Paths.get("resources").toFile();

    public static void createHoaDon(String idHD, File file) throws JRException, FileNotFoundException {
        String invoice = new File(path, "invoice.jrxml").getPath();
        try (Connection con = XJdbc.getCon()) {
            Map<String, Object> paras = new HashMap<>();
            paras.put("idHD", "HD01");
            JasperDesign design = JRXmlLoader.load(invoice);
            JasperReport report = JasperCompileManager.compileReport(design);
            JasperPrint print = JasperFillManager.fillReport(report, paras, con);
            JasperExportManager.exportReportToPdfFile(print, file.getPath());
        } catch (SQLException ex) {
            Logger.getLogger(XReport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
