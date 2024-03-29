package com.ultramotor.util;

import com.swingx.TextField;
import java.text.ParseException;
import javax.swing.JPasswordField;
import javax.swing.text.JTextComponent;

public class XValidate {
//
    //kiểm tra field chưa nhập dữ liệu

    public static boolean isEmpty(JTextComponent comp) {
        //kiểm tra password field
        if (comp instanceof JPasswordField) {
            if (((JPasswordField) comp).getPassword().length == 0) {
                return true;
            }
            //kiểm tra text field
        } else if (comp.getText().trim().isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * Kiểm tra dữ liệu trong comp có hợp lệ
     *
     * @param comp thành phần cần kiểm tra
     * @param pattern dịnh dạng kiểm tra
     * @return tex của thành phần
     * @throws Exception
     */
    private static boolean matchPattern(JTextComponent comp, String pattern) {
        String txt = comp.getText().trim(); //lấy text từ comp
        return txt.matches(pattern);
    }

    //kiểm tra ID
    public static boolean validateID(JTextComponent comp) {
        return matchPattern(comp, MyConstants.ID_PATTERN);
    }

    //kiểm tra tên
    public static boolean validateName(JTextComponent comp) {
        return matchPattern(comp, MyConstants.NAME_PATTERN);
    }

//    //kiểm tra số điện thoại
//    public static boolean validatePhoneNumber(JTextComponent comp) {
//        return matchPattern(comp, MyConstants.PHONE_NUMBER_PATTERN);
//    }
    //kiểm tra Email
    public static boolean validateEmail(JTextComponent comp) {
        return matchPattern(comp, MyConstants.EMAIL_PATTERN);
    }
    
    public static boolean validateEmail(String email){
        return email.trim().matches(MyConstants.EMAIL_PATTERN);
    }
    //kiểm tra địa chỉ
    public static boolean validateAddress(JTextComponent comp) {
        return matchPattern(comp, MyConstants.ADDRESS_PATTERN);
    }

    //kiểm tra số double dương
    public static boolean validateUnsignedDouble(JTextComponent comp) {
        try {
            if (!comp.getText().equals("")) {
                if (Double.parseDouble(comp.getText().replaceAll(",", "")) < 0) {
                    throw new NumberFormatException();
                }
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    //kiểm tra số nguyên dương
    public static boolean validateUnsignedInteger(JTextComponent comp) {
        try {
            if (!comp.getText().equals("")) {
                Integer.parseUnsignedInt(comp.getText().trim().replaceAll(",", ""));
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    //kiểm tra định dạng ngày hợp lệ
    public static boolean validateDate(JTextComponent comp) {
        try {
            XDate.parse(comp.getText(), MyConstants.DATE_PATTERN);
        } catch (ParseException ex) {
            return false;
        }
        return true;
    }

    //kiểm tra độ dài password
    public static boolean validatePassworđ(JTextComponent comp) {
        return ((JPasswordField) comp).getPassword().length >= 3; //sửa lại trong database
    }

    //kiểm tra điểm
    public static boolean validateGrade(String grade) {
        if (!grade.equals("")) {
            try {
                double diem = Double.parseDouble(grade);
                if (diem < 0 || diem > 10) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }

    //kiểm tra mức lương cơ bản
    public static boolean validateSalary(String salary) {
        if (!salary.equals("")) {
            try {
                double sal = Double.parseDouble(salary.replaceAll(",", ""));
                if (sal < 5_000_000) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }

    static boolean validatePhoneNumber(JTextComponent comp) {
        return matchPattern(comp, MyConstants.PHONE_NUMBER_PATTERN);
    }
}
