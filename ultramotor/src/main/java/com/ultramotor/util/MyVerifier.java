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
        if (!verify(input)) {
            input.repaint();
        }
        return true;
    }
    //kiểm lỗi text trên field và xuất thông báo lỗi tương ứng

    private static boolean validate(String type, TextField field) {
        //kiểm tra field rỗng
        if (isEmpty(field) && !field.isAllowEmpty()) {
            field.setValidInput(false);
            field.setErrorText(field.getName() + " trống");
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
                result = XValidate.validatePhoneNumber(field);
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
            case "address":
                result = XValidate.validateAddress(field);
                break;
            case "salary":
                result = XValidate.validateSalary(field.getText().trim());
                error = "Mức lương cơ bản tối thiểu 5,000,000 VNĐ";
                break;
            case "color":
//                result = MyConstants.colorMap.containsKey(field.getText().trim());
                error = "Không tìm thấy bảng màu trong hệ thống";
                break;
            case "nam san xuat":
                if (!validate("integer", field)) {
                    return false;
                }
                int nam = Integer.parseInt(field.getText().trim());
                if (nam < 2000) {
                    error = "Chỉ bán xe sau năm 2000";
                    result = false;
                } else if (nam > Integer.parseInt(XDate.toString(new Date(), "yyyy"))) {
                    error = "Đời xe lớn hơn năm hiện tại";
                    result = false;
                }
        }
        if (!result) {
            field.setErrorText(error);
//            field.setToolTipText(error);
            field.setValidInput(false);
        } else {
            field.setErrorText("");
//            field.setToolTipText("");
            field.setValidInput(true);
        }
        return result;
    }

    //kiểm lỗi password và thông báo lỗi trên field
    private static boolean validate(PasswordField field) {
        //kiểm tra password rỗng
        if (isEmpty(field)) {
            field.setValidInput(false);
            field.setErrorText(field.getName() + " không được để trống");
//            field.setToolTipText(field.getName() + " không được để trống");
            return false;
        }
        //kiểm tra định dạng password hợp lệ
        if (!XValidate.validatePassworđ(field)) {
            field.setErrorText(field.getName() + " 8 kí tự trở lên");
//            field.setToolTipText(field.getName() + " 8 kí tự trở lên");
            return false;
        }
        field.setErrorText("");
//        field.setToolTipText("");
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

    public static final MyVerifier NHAN_VIEN_VERIFIER = new MyVerifier() {
        @Override
        public boolean verify(JComponent jc) {
            String name = jc.getName() != null ? jc.getName() : "";

            if (jc instanceof TextField) {
                TextField field = (TextField) jc;
                switch (name) {
                    case "Mã NV":
                        return validate("id", field);
                    case "Họ NV":
                        return validate("name", field);
                    case "Tên NV":
                        return validate("name", field);
                    case "Ngày sinh NV":
                        return validate("date", field) && validate("not in future", field);
                    case "Địa chỉ NV":
                        return validate("address", field);
                    case "Email NV":
                        return validate("email", field);
                    case "Số điện thoại NV":
                        return validate("phone number", field);
                    case "Lương NV":
                        return validate("double", field) && validate("salary", field);
                }
            } else if (jc instanceof PasswordField) {
                return validate((PasswordField) jc);
            }
            return true;
        }
    };

    public static final MyVerifier KHACH_HANG_VERIFIER = new MyVerifier() {
        @Override
        public boolean verify(JComponent jc) {
            String name = jc.getName() != null ? jc.getName() : "";

            if (jc instanceof TextField) {
                TextField field = (TextField) jc;
                switch (name) {
                    case "Mã KH":
                        return validate("id", field);
                    case "Họ KH":
                        return validate("name", field);
                    case "Tên KH":
                        return validate("name", field);
                    case "Họ tên khách hàng":
                        return validate("name", field);
                    case "Ngày sinh":
                        return validate("date", field) && validate("not in future", field);
//                    case "Địa chỉ":
//                        return validate("address", field);
                    case "Email":
                        return validate("email", field);
                    case "Số điện thoại":
                        return validate("phone number", field);
                }
            } else if (jc instanceof PasswordField) {
                return validate((PasswordField) jc);
            }
            return true;
        }
    };

    public static final MyVerifier SAN_PHAM_VERIFIER = new MyVerifier() {
        @Override
        public boolean verify(JComponent jc) {
            String name = jc.getName() != null ? jc.getName() : "";

            if (jc instanceof TextField) {
                TextField field = (TextField) jc;
                switch (name) {
                    case "Mã sản phẩm":
                        return validate("id", field);
                    case "Tên xe":
                        return validate("", field);
                    case "Địa chỉ sản xuất":
                        return validate("address", field);
                    case "Đời xe":
                        return validate("nam san xuat", field);
                    case "Đơn giá xe":
                        return validate("double", field);
                    case "Màu sắc xe":
                        return validate("color", field);
                }
            }
            return true;
        }

    };
}
