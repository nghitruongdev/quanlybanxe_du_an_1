package com.ultramotor.ui.khachhang;

import com.swingx.SearchTextField;
import com.swingx.SlideShowPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.nio.file.Paths;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.Timer;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class KhachHangFrame extends javax.swing.JFrame implements Multilang {
    
    private SlideShowPanel pnl;
    private boolean showing = true;
    private Timer timer;
    private KhachHangController ctrl;
    private Lang lang = Lang.VN;
    
    public KhachHangFrame() {
        initComponents();
        init();
    }
    
    private void init() {
        iniSlideshow();
        ctrl = new KhachHangController();
        btnBack.setVisible(false);
        btnNext.setVisible(false);
        addListeners();
    }
    
    private void updateStatus() {
        btnBack.setVisible(!pnlWelcome.isShowing());
        reset(pnlWelcome.isShowing());
    }
    
    private void addListeners() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                KhachHangFrame.this.setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
            
        });
        btnBack.addActionListener((ActionEvent e) -> {
            ctrl.navigateCard(pnlMain, false);
            updateStatus();
        });
        
        btnNext.addActionListener((ActionEvent e) -> {
            ctrl.navigateCard(pnlMain, true);
            updateStatus();
        });
        
        for (Component comp : pnlMain.getComponents()) {
            comp.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentShown(ComponentEvent ce) {
                    updateStatus();
                }
                
                @Override
                public void componentHidden(ComponentEvent ce) {
                    updateStatus();
                }
                
            });
        }
    }
    
    public Lang getLang() {
        return lang;
    }
    
    public void setLang(Lang lang) {
        this.lang = lang;
        pnlSearch.setLang(lang);
        pnlProductList.setLang(lang);
        pnlDetails.setLang(lang);
    }
    
    private void iniSlideshow() {
        pnl = new SlideShowPanel();
        pnl.setBackground(Color.white);
        pnl.addImages(
                createIcon("slide1.jpg"),
                createIcon("slide2.jpg"),
                createIcon("slide3.jpg"),
                createIcon("slide5.jpg"),
                createIcon("slide6.jpg"));
        pnl.setAuto(2000);
        bg.add(pnl, JLayeredPane.POPUP_LAYER);
        animate();
    }
    
    private ImageIcon createIcon(String name) {
        File path = Paths.get("src", "main", "resources", "ultramotor", "slide").toFile();
        return new ImageIcon(new File(path, name).getPath());
    }
    
    private void animate() {
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void end() {
                if (!showing) {
                    pnl.setAuto(2000);
                }
                super.end();
            }
            
            @Override
            public void timingEvent(float fraction) {
                double height = pnl.getHeight();
                
                if (showing) {
                    pnl.setLocation(0, (0 - (int) (height * (fraction))));
                } else {
                    pnl.setLocation(0, (int) (height * (fraction - 1f)));
                }
            }
            
            @Override
            public void begin() {
                pnl.stopAuto();
            }
        };
        Animator animator = new Animator(1000, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        
        timer = new Timer(30*1000, (ActionEvent e) -> {
            System.out.println("Timer is starting");
            showing = false;
            pnl.setBounds(bg.getBounds());
            animator.start();
            timer.stop();
            KhachHangController.showCard(pnlMain, "Welcome");
        });
        timer.setCoalesce(true);
        addMouseMotionComponent(this, animator);
    }
    
    private void reset(boolean isFirst) {
        if (isFirst) {
            pnlWelcome.reset();
            pnlSearch.reset();
            pnlProductList.reset();
            pnlDetails.reset();
        }
    }

    private void restartTimer() {
        timer.restart();
    }
    
    private void addMouseMotionComponent(Container con, Animator animator) {
        MouseMotionListener ls = new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                restartTimer();
            }
            
            @Override
            public void mouseMoved(MouseEvent e) {
                if (pnl.getY() == 0 && !animator.isRunning()) {
                    showing = true;
                    animator.start();
                }
                restartTimer();
            }
        };
        
        for (Component comp : con.getComponents()) {
            if (comp instanceof Container) {
                addMouseMotionComponent((Container) comp, animator);
            } else {
                comp.addMouseMotionListener(ls);
            }
        }
        
        if (con instanceof SearchTextField) {
            con.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    restartTimer();
                }
            });
        }
        con.addMouseMotionListener(ls);
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JLayeredPane();
        jPanel1 = new javax.swing.JPanel();
        pnlHeader = new javax.swing.JPanel();
        btnNext = new com.swingx.Button();
        btnBack = new com.swingx.Button();
        jSeparator1 = new javax.swing.JSeparator();
        pnlMain = new javax.swing.JPanel();
        pnlWelcome = new com.ultramotor.ui.khachhang.WelcomePanel();
        pnlSearch = new com.ultramotor.ui.khachhang.SearchPanel();
        pnlProductList = new com.ultramotor.ui.khachhang.ProductListPanel();
        pnlDetails = new com.ultramotor.ui.khachhang.ProductDetailsPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setLayout(new java.awt.BorderLayout());

        pnlHeader.setBackground(new java.awt.Color(255, 255, 255));
        pnlHeader.setMaximumSize(new java.awt.Dimension(32767, 80));
        pnlHeader.setPreferredSize(new java.awt.Dimension(1115, 80));

        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ultramotor/icon/right_50px.png"))); // NOI18N

        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ultramotor/icon/left_50px.png"))); // NOI18N

        javax.swing.GroupLayout pnlHeaderLayout = new javax.swing.GroupLayout(pnlHeader);
        pnlHeader.setLayout(pnlHeaderLayout);
        pnlHeaderLayout.setHorizontalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHeaderLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1053, Short.MAX_VALUE))
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        pnlHeaderLayout.setVerticalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(pnlHeader, java.awt.BorderLayout.NORTH);

        pnlMain.setMinimumSize(new java.awt.Dimension(500, 355));
        pnlMain.setLayout(new java.awt.CardLayout());

        pnlWelcome.setName("ProductList"); // NOI18N
        pnlMain.add(pnlWelcome, "Welcome");
        pnlMain.add(pnlSearch, "ProductSearch");
        pnlMain.add(pnlProductList, "ProductList");
        pnlMain.add(pnlDetails, "ProductDetails");

        jPanel1.add(pnlMain, java.awt.BorderLayout.CENTER);

        bg.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(KhachHangFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KhachHangFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KhachHangFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KhachHangFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new KhachHangFrame().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane bg;
    private com.swingx.Button btnBack;
    private com.swingx.Button btnNext;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private com.ultramotor.ui.khachhang.ProductDetailsPanel pnlDetails;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlMain;
    private com.ultramotor.ui.khachhang.ProductListPanel pnlProductList;
    private com.ultramotor.ui.khachhang.SearchPanel pnlSearch;
    private com.ultramotor.ui.khachhang.WelcomePanel pnlWelcome;
    // End of variables declaration//GEN-END:variables
}
