package com.edusys.util;

import com.edusys.components.MyPasswordField;
import com.edusys.components.MyTextField;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import static com.edusys.util.XValidate.*;
import java.util.Date;

/**
 *
 * @author nghipc
 */
public abstract class MyVerifier extends javax.swing.InputVerifier {

    @Override
    public boolean shouldYieldFocus(JComponent jc) {
        //kiểm tra field hợp lệ khi mất focus, tự động repaint
        if (!verify(jc)) {
            jc.repaint();
        }
        return true;
    }
    //verifier cho field trên form đăng nhập
    public static final InputVerifier DANG_NHAP_VERIFIER = new MyVerifier() {
        @Override
        public boolean verify(JComponent jc) {
            String name = jc.getName() != null ? jc.getName() : "";
            
            if (jc instanceof MyTextField) {
                MyTextField field = (MyTextField) jc;
                switch (name) {
                    case "Tên đăng nhập":
                        return validate("id", field);
                    case "Email":
                        return validate("email", field);
                }
            } else if (jc instanceof MyPasswordField) {
                return validate((MyPasswordField) jc);
            }
            return true;
        }
    };

    //verifier cho field trên form đổi mật khẩu
    public static final InputVerifier CHANGE_PW_VERIFIER = new MyVerifier() {
        @Override
        public boolean verify(JComponent jc) {
            if (jc instanceof MyPasswordField) {
                MyPasswordField field = (MyPasswordField) jc;
                return validate(field);
            }
            return true;
        }
    };

    //verifier cho field trên form nhân viên 
    public static final InputVerifier NHAN_VIEN_VERIFIER = new MyVerifier() {
        @Override
        public boolean verify(JComponent jc) {
            String name = jc.getName() != null ? jc.getName() : "";
            
            if (jc instanceof MyTextField) {
                MyTextField field = (MyTextField) jc;
                switch (name) {
                    case "Mã nhân viên":
                        return validate("id", field);
                    case "Họ và tên":
                        return validate("name", field);
                    case "Email":
                        return validate("email", field);
                }
            } else if (jc instanceof MyPasswordField) {
                MyPasswordField field = (MyPasswordField) jc;
                return validate(field);
            }
            return true;
        }
    };

    //verifier cho field trên form chuyên đề
    public static final InputVerifier CHUYEN_DE_VERIFIER = new MyVerifier() {
        @Override
        public boolean verify(JComponent jc) {
            String name = jc.getName() != null ? jc.getName() : "";
            
            if (jc instanceof MyTextField) {
                MyTextField field = (MyTextField) jc;
                switch (name) {
                    case "Mã chuyên đề":
                        return validate("id", field);
                    case "Tên chuyên đề":
                        return validate("", field);
                    case "Thời lượng":
                        return validate("integer", field);
                    case "Học phí":
                        return validate("double", field);
                }
            }
            return true;
        }
    };
    
    //verifier cho field trên form khoá học
    public static final InputVerifier KHOA_HOC_VERIFIER = new MyVerifier() {
        @Override
        public boolean verify(JComponent jc) {
            String name = jc.getName() != null ? jc.getName() : "";
            
            if (jc instanceof MyTextField) {
                MyTextField field = (MyTextField) jc;
                switch (name) {
                    case "Học phí":
                        return validate("double", field);
                    case "Thời lượng":
                        return validate("integer", field);
                    case "Người tạo":
                        return validate("id", field);
                    case "Ngày khai giảng":
                        return validate("date", field);
                    case "Ngày tạo":
                        return validate("date", field) && validate("not in future", field);
                }
            }
            return true;
        }
    };
    
    //verifier cho field trên form học viên
    public static final InputVerifier NGUOI_HOC_VERIFIER = new MyVerifier() {
        @Override
        public boolean verify(JComponent jc) {
            String name = jc.getName() != null ? jc.getName() : "";
            
            if (jc instanceof MyTextField) {
                MyTextField field = (MyTextField) jc;
                switch (name) {
                    case "Mã người học":
                        return validate("id", field);
                    case "Họ tên":
                        return validate("name", field);
                    case "Ngày sinh":
                        return validate("date", field) && validate("not in future", field);
                    case "Số điện thoại":
                        return validate("phone number", field);
                    case "Email":
                        return validate("email", field);
                }
            }
            return true;
        }
    };

    //kiểm lỗi text trên field và xuất thông báo lỗi tương ứng
    private static boolean validate(String type, MyTextField field) {
       //kiểm tra field rỗng
        if (isEmpty(field) && !field.isOptional()) {
            field.setValidInput(false);
            field.getError().setText(field.getName() + " không được để trống");
            return false;
        }
        //kiểm tra định dạng field có hợp lệ theo type
        boolean result = true;
        String error = field.getName() + " không hợp lệ";
        switch (type) {
            case "id":
                result = validateID(field);
                break;
            case "name":
                result = validateName(field);
                break;
            case "email":
                result = validateEmail(field);
                break;
            case "phone number":
                String value = field.getText().replaceAll("-", "").replaceAll("#", "").trim();
                result = value.length() == 10;
                break;
            case "integer":
                result = validateUnsignedInteger(field);
                break;
            case "double":
                result = validateUnsignedDouble(field);
                break;
            case "date":
                result = validateDate(field);
                error = "Định dạng ngày tháng không hợp lệ";
                break;
            case "not in future":
                result = XDate.parse(field.getText()).before(new Date());
                error = "Ngày không hợp lệ";
                break;
        }
        if (!result) {
            field.getError().setText(error);
        }
        return result;
    }

    //kiểm lỗi password và thông báo lỗi trên field
    private static boolean validate(MyPasswordField field) {
        //kiểm tra password rỗng
        if (isEmpty(field)) {
            field.setValidInput(false);
            field.getError().setText(field.getName() + " không được để trống");
            return false;
        }
        //kiểm tra định dạng password hợp lệ
        if (!XValidate.validatePassworđ(field)) {
            field.getError().setText(field.getName() + " 8 kí tự trở lên");
            return false;
        }
        return true;
    }
}
