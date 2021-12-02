/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ultramotor.ui.khachhang;

import com.swingx.SlideShowPanel;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

/**
 *
 * @author nghipc
 */
public class TestFrame extends javax.swing.JFrame {

    /**
     * Creates new form TestFrame
     */
    public TestFrame() {
        initComponents();
        init();
    }

    private void init() {
        pnl.setBackground(Color.red);
        pnl.setOpaque(true);
        pnl.addImages(
                new ImageIcon(getClass().getResource("/com/raven/icon/slide1.jpg")),
                new ImageIcon(getClass().getResource("/com/raven/icon/slide2.jpeg")),
                new ImageIcon(getClass().getResource("/com/raven/icon/slide3.jpg")));
        pnl.setAuto(2000);
        animate();
    }

    private void addImage(SlideShowPanel panel) {
        panel.addImages(
                new ImageIcon(getClass().getResource("/com/raven/icon/slide1.jpg")),
                new ImageIcon(getClass().getResource("/com/raven/icon/slide2.jpeg")),
                new ImageIcon(getClass().getResource("/com/raven/icon/slide3.jpg")));
        panel.setAuto(300);
    }
    private boolean showing = true;

    private void animate() {
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void end() {
                if(!showing){
                   pnl.setAuto(2000);
                }
//                if (showing) {
//                    pnl.removeAll();
////                    pnl = null;
//                    System.out.println("Removed");
//                }
  
                super.end();
            }

            @Override
            public void timingEvent(float fraction) {
                double height = pnl.getHeight();
//                System.out.println("Height: " + height);
//                System.out.println("Location: " + pnlSlideshow.getLocation().y);
//                System.out.println("y: " + pnlSlideshow.getY());
                if (showing) {
                    pnl.setLocation(0, (0 - (int) (height * (fraction))));
                } else {
                    pnl.setLocation(0, (int) (height * (fraction - 1f)));
                }
            }

            @Override
            public void begin() {
                pnl.stopAuto();
                System.out.println("Stopeed auto");
                if (!showing) {
                    System.out.println("Added Image");
                }
            }
        };
        Animator animator = new Animator(1000, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);

        this.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
//                System.out.println("Focus Lost");
//                if(!animator.isRunning())
//                animator.start();
            }

            @Override
            public void focusGained(FocusEvent e) {
//                System.out.println("Focus gained");
            }
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                super.windowActivated(e);
                showing = pnl.getY() == 0;
//                if (!showing) {
//                    Thread t = new Thread(() -> {
//                        addImage(pnl);
//                        pnl.revalidate();
//                    });
//
//                    t.start();
//                    try {
//                        t.join();
////                    pnl = new  SlideShowPanel();
////                    pnl.setBackground(Color.red);
////                    pnl.setOpaque(true);
////                    pnl.addImages(
////                            new ImageIcon(getClass().getResource("/com/raven/icon/slide1.jpg")),
////                            new ImageIcon(getClass().getResource("/com/raven/icon/slide2.jpeg")),
////                            new ImageIcon(getClass().getResource("/com/raven/icon/slide3.jpg")));
////                    pnl.setAuto(300);
//                    } catch (InterruptedException ex) {
//                        Logger.getLogger(TestFrame.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
                Thread t2 = new Thread(() -> {
                    if (!animator.isRunning()) {
                        animator.start();
                    }
                });
                t2.start();
            }

        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl = new com.swingx.SlideShowPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnl, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 564, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TestFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TestFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TestFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TestFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TestFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.swingx.SlideShowPanel pnl;
    // End of variables declaration//GEN-END:variables
}
