package com.edusys.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

/**
 *
 * @author nghipc
 */
public class XLog {

    public static void saveLog(String error) {
        try {
            Files.createDirectories(Paths.get("logs"));
            try (FileOutputStream out = new FileOutputStream("logs\\" + XDate.toString(new Date(), "dd_MM_yyyy") + ".log", true)) {
                try (PrintWriter writer = new PrintWriter(out, true)) {
                        writer.println(XDate.toString(new Date(), "hh:mm:ss")+ " " +error);
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
}
