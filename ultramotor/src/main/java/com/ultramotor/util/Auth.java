package com.ultramotor.util;

import com.ultramotor.entity.NhanVien;
import com.ultramotor.entity.TruongPhong;
import java.awt.event.ActionEvent;
import javax.swing.Timer;

public class Auth {

    public static NhanVien user;
    private static String otp;
    public static boolean forgotPW = false;
    private static Timer timer;

    //clear info của user login
    public static void clear() {
        user = null;
        otp = "";
        forgotPW = false;
    }

    //kiểm tra đăng nhập
    public static boolean isLogin() {
        return user != null && !forgotPW;
    }

    //kiểm tra user login là trưởng phòng
    public static boolean isManager() {
        return isLogin() && Auth.user instanceof TruongPhong;
    }

    //get mã otp cho quên mật khẩu
    public static String getOTP() {
        return otp;
    }

    //get mã otp mới cho quên mật khẩu
    public static String getNewOTP() {
        otp = String.format("%06d", (int) (Math.random() * 1000000));
        System.out.println(otp);
        if (timer == null) {
            timer = new Timer(5 * 60 * 1000, (ActionEvent e) -> {
                otp = "";
                timer.stop();
                System.out.println("Timer stopped");
            });
        }
        if (timer.isRunning()) {
            timer.stop();
        }
        timer.start();
        return getOTP();
    }

    //đóng chương trình có xác nhận của người dùng
    public static void close() {
        int k = MsgBox.confirm("Bạn có muốn kết thúc chương trình?", false);
        if (k == MsgBox.YES_OPTION) {
            System.exit(0);
        }
    }

    public static boolean isForgotPW() {
        return forgotPW;
    }

//    public static void setForgotPW(boolean forgotPW) {
//        Auth.forgotPW = forgotPW;
//        if (!forgotPW) {
//            timer.stop();
//        }
//    }
}
