package com.ultramotor.util;

import com.swingx.PasswordField;
import com.swingx.TextField;
import static com.ultramotor.util.XValidate.isEmpty;
import java.util.Date;
import javax.swing.InputVerifier;
import javax.swing.JComponent;

public abstract class MyVerifier extends InputVerifier {

    @Override
    public boolean shouldYieldFocus(JComponent input) {
        if(!verify(input)){
            input.repaint();
        }
        return true;
    }
 //kiểm lỗi text trên field và xuất thông báo lỗi tương ứng
    private static boolean validate(String type, TextField field) {
       //kiểm tra field rỗng
        if (isEmpty(field) && !field.isAllowEmpty()) {
            field.setValidInput(false);
            field.setErrorText(field.getName() + " không được để trống");
            return false;
        }
        //kiểm tra định dạng field có hợp lệ theo type
        boolean result = true;
        String error = field.getName() + " không hợp lệ";
        switch (type) {
            case "id":
                result = XValidate.validateID(field);
                break;
            case "name":
                result = XValidate.validateName(field);
                break;
            case "email":
                result = XValidate.validateEmail(field);
                break;
            case "phone number":
                String value = field.getText().replaceAll("-", "").replaceAll("#", "").trim();
                result = value.length() == 10;
                break;
            case "integer":
                result = XValidate.validateUnsignedInteger(field);
                break;
            case "double":
                result = XValidate.validateUnsignedDouble(field);
                break;
            case "date":
                result = XValidate.validateDate(field);
                error = "Định dạng ngày tháng không hợp lệ";
                break;
            case "not in future":
                result = XDate.parse(field.getText()).before(new Date());
                error = "Ngày không hợp lệ";
                break;
        }
        if (!result) {
            field.setErrorText(error);
            field.setToolTipText(error);
        }
        return result;
    }

    //kiểm lỗi password và thông báo lỗi trên field
    private static boolean validate(PasswordField field) {
        //kiểm tra password rỗng
        if (isEmpty(field)) {
            field.setValidInput(false);
            field.setErrorText(field.getName() + " không được để trống");
            field.setToolTipText(field.getName() + " không được để trống");
            return false;
        }
        //kiểm tra định dạng password hợp lệ
        if (!XValidate.validatePassworđ(field)) {
            field.setErrorText(field.getName() + " 8 kí tự trở lên");
            field.setToolTipText(field.getName() + " 8 kí tự trở lên");
            return false;
        }
        return true;
    }
    
    public static final MyVerifier DANG_NHAP_VERIFIER = new MyVerifier() {
        @Override
        public boolean verify(JComponent jc) {
             String name = jc.getName() != null ? jc.getName() : "";
            
            if (jc instanceof TextField) {
                TextField field = (TextField) jc;
                switch (name) {
                    case "Tên đăng nhập":
                        return validate("id", field);
                    case "Email":
                        return validate("email", field);
                }
            } else if (jc instanceof PasswordField) {
                return validate((PasswordField) jc);
            }
            return true;
        }
    };
    
}
