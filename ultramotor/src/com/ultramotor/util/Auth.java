package com.ultramotor.util;

import com.ultramotor.entity.NhanVien;

public class Auth {
   
    public static NhanVien user;
    public static String otp;
    public static boolean isForgotPW = false;

    //clear info của user login
    public static void clear() {
        user = null;
        otp = "";
        isForgotPW = false;
    }

    //kiểm tra đăng nhập
    public static boolean isLogin() {
        return user != null && !isForgotPW;
    }

    //kiểm tra user login là trưởng phòng
    public static boolean isManager() {
        return isLogin() && user.getVaiTro().equals("Trưởng phòng");
    }

    //get mã otp cho quên mật khẩu
    public static String getOTP() {
        return otp;
    }

    //get mã otp mới cho quên mật khẩu
    public static String getNewOTP() {
        otp = String.format("%05d", (int) (Math.random() * 1000000));
        return getOTP();
    }

    //đóng chương trình có xác nhận của người dùng
    public static void close() {
        int k = MsgBox.confirm("Bạn có muốn kết thúc chương trình?", false);
        if (k == MsgBox.YES_OPTION) {
            System.exit(0);
        }

    }
}
