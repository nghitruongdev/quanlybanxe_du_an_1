package com.ultramotor.util;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import javax.swing.JDialog;
import javax.swing.JPanel;

public class WebcamReader extends JPanel implements Runnable, ThreadFactory {

    private static final long serialVersionUID = 6441489157408381878L;
    private Executor executor = Executors.newSingleThreadExecutor(this);
    private Webcam webcam = null;
    private WebcamPanel panel = null;
    Result result = null;

    public WebcamReader() {
        super();
        setLayout(new FlowLayout());
        Dimension size = WebcamResolution.QVGA.getSize();

        webcam = Webcam.getWebcams().get(0);
        System.out.println(webcam.close());
        webcam.setViewSize(size);

        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);
//        textarea = new JTextArea();
//        textarea.setEditable(false);
//        textarea.setPreferredSize(size);
        add(panel);
//        add(textarea);

//        pack();
        setVisible(true);

        executor.execute(this);
    }

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            BufferedImage image = null;

            if (webcam.isOpen()) {

                if ((image = webcam.getImage()) == null) {
                    continue;
                }

                LuminanceSource source = new BufferedImageLuminanceSource(image);
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

                try {
                    result = new MultiFormatReader().decode(bitmap);
                    if (result != null) {
                        webcam.close();
                        ((JDialog) this.getTopLevelAncestor()).dispose();
                    }
                } catch (NotFoundException e) {
                    // fall thru, it means there is no QR code in image
                }
            }
        } while (result == null);
    }

    public Result getResult() {
        return result;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "example-runner");
        t.setDaemon(true);
        return t;
    }

    public void closeWebcam() {
        webcam.close();
    }
}
