package com.ultramotor.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.krysalis.barcode4j.ChecksumMode;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

public class BarcodeHelper {

    public static void create39Barcode(File file, String msg) {
        Code39Bean bean = new Code39Bean();
        bean.setChecksumMode(ChecksumMode.CP_AUTO);
        bean.setHeight(15f);
        bean.setWideFactor(3);
        bean.setQuietZone(5);
        bean.doQuietZone(true);
        bean.setExtendedCharSetEnabled(true);
        try (OutputStream out = new FileOutputStream(file)) {
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(out,
                    "image/x-png", 300, BufferedImage.TYPE_BYTE_GRAY, true, 0);
            bean.generateBarcode(canvas, msg);
            canvas.finish();
        } catch (IOException e) {
            System.out.println("Không tìm thấy file!");
        }
    }
}
