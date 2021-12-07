package com.ultramotor.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import org.krysalis.barcode4j.ChecksumMode;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.impl.datamatrix.SymbolShapeHint;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

public class XCodeHelper {

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

    public static BufferedImage createQRCode(Serializable data, int width, int height) {
        try {
//            BitMatrix matrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, 50, 50,Collections.singletonMap(EncodeHintType.DATA_MATRIX_SHAPE, SymbolShapeHint.FORCE_SQUARE));
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.MARGIN, 0);
            hints.put(EncodeHintType.DATA_MATRIX_SHAPE, SymbolShapeHint.FORCE_SQUARE);

            BitMatrix matrix = new MultiFormatWriter().encode(serialize(data), BarcodeFormat.QR_CODE, width, height, hints);
            return MatrixToImageWriter.toBufferedImage(matrix);
        } catch (WriterException ex) {
            Logger.getLogger(XCodeHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static String serialize(Serializable o) {
        try (ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
            try (ObjectOutputStream out = new ObjectOutputStream(stream)) {
                out.writeObject(o);
                return Base64.getEncoder().encodeToString(stream.toByteArray());
//                return Arrays.toString(stream.toByteArray()).replaceAll("[\\[\\],]", "");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ABC";
    }

    public static Object deserialize(String s) throws Exception {
        byte[] arr = Base64.getDecoder().decode(s);
        try (ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(arr))) {
            return in.readObject();
        } catch (IOException ex) {
            Logger.getLogger(XCodeHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(XCodeHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String webcamReader(JFrame frame) throws Exception {
        WebcamReader cam = new WebcamReader();
        createWebcamReaderPanel(frame, cam);
        Result result = null;
        while (result == null) {
            try {
                Thread.sleep(100);
                result = cam.getResult();
            } catch (InterruptedException ex) {
            }
        }
        return (String) deserialize(result.getText());
    }

    private static void createWebcamReaderPanel(JFrame frame, WebcamReader cam) {
        JDialog dialog = XDialog.getDialog(frame, cam);
        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowDeactivated(WindowEvent e) {
                cam.closeWebcam();
            }
        });
        dialog.setVisible(true);
    }
}
