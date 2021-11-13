package com.edusys.ui;

import com.edusys.components.MyPasswordField;
import com.edusys.components.MyTable;
import com.edusys.components.MyTextField;
import com.edusys.dao.EduSysDAO;
import com.edusys.util.Auth;
import com.edusys.util.MsgBox;
import com.edusys.util.MyConstants;
import com.edusys.util.XListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

/**
 *
 * @author nghipc
 */
public abstract class MyDialogQuanLy<Entity> extends javax.swing.JDialog {

    protected JTextComponent[] fields;
    protected EduSysDAO dao;
    protected MyTable tblList;
    protected JTabbedPane tabs;
    protected int index = -1;
    protected InputVerifier verifier;

    public MyDialogQuanLy() {
        super(EduSysFrame.getFrame(), true);
        this.addWindowListener(XListener.dialogLs);  //yêu cầu đăng nhập trước khi sử dụng chức năng
    }

    protected abstract void init(); //khởi tạo các thành phần khác của form

    protected abstract void addListeners(); //thêm sự kiện cho các thành phần khác

    protected abstract void updateStatus(); //cập nhật trạng thái form

    protected abstract Entity readForm(); //đọc dữ liệu trên form

    protected abstract Object[] getInfo(Entity e); //lấy dữ liệu từ entiyt

    protected abstract List<Object[]> getInfo(); //lấy dữ liệu từ danh sách các entity

    protected abstract boolean isValidated(String action); //validate dữ liệu trên form khi thực hiện chức năng

    protected abstract void setForm(Entity e); //hiển thị thông tin của entity lên form

    //reset lại form về mặc định
    protected void clearForm() {
        for (JTextComponent field : fields) {
            field.setText("");
        }
        fields[0].requestFocus(); //đặt focus về field đầu tiên
        clearSelectedRow(); //bỏ chọn trên tblList
        updateStatus(); //cập nhật trạng thái form
    }

    //bỏ chọn trên bảng tblList
    protected void clearSelectedRow() {
        index = -1;
        tblList.clearSelection();
    }

    //đặt hàng chọn trên bảng tblList
    protected void setSelectedRow(int index) {
        //kiểm tra index hợp lệ
        if (index < 0 || index > tblList.getRowCount() - 1) {
            return;
        }
        clearForm(); //đặt form về mặc định
        this.index = index;

        tblList.setRowSelectionInterval(index, index); //đật hàng chọn trên bảng
        tblList.scrollRectToVisible(new java.awt.Rectangle(tblList.getCellRect(index, 0, true))); //di chuyển thanh lăn tới vị trí hàng chọn

        Object id = tblList.getValueAt(index, 0);
        Entity e = (Entity) dao.selectById(id);
        setForm(e); //hiển thị thông tin của hàng chọn lên form
    }

    //đổ dữ liệu vào tblList và đặt hàng chọn theo index
    protected void fillTable(int index) {
        //đổ list vào table
        //set form tại vị trí index
        //đặt lại hàng chọn trong table
        //cuộn đến vị trí chọn
        DefaultTableModel model = (DefaultTableModel) tblList.getModel();
        model.setRowCount(0);
        //đổ dữ liệu
        try {
            List<Object[]> list = getInfo();
            if (list.isEmpty()) {
                clearForm();
                return;
            }
            list.forEach((row) -> {
                model.addRow(row);
            });
        } catch (Exception e) {
            MsgBox.error("Lỗi truy vấn dữ liệu!");
        }
        setSelectedRow(index); //đặt hàng chọn
    }

    //thêm dữ liệu trên form vào CSDL
    protected void insert() {
        //kiểm tra dữ liệu trên form khi insert
        if (!isValidated("insert")) {
            return;
        }

        int count = dao.insert(readForm()); //thêm dữ liệu
        outputResult("Thêm mới", count != 0); //xuất thông báo
        if (count != 0) {//nếu thêm thành công, fillTable và đặt lại form
            fillTable(0);
            clearForm();
        }
    }

    //cập nhật dữ liệu trong CSDL
    protected void update() {
        //kiểm tra dữ liệu trên form khi update
        if (!isValidated("update")) {
            return;
        }

        int count = dao.update(readForm()); //cập nhật dữ liệu
        outputResult("Cập nhật", count != 0); //xuất thông báo
        if (count != 0) { //nếu cập nhật thành công, fillTable
            fillTable(index);
        }
    }

    //xoá dữ liệu trong CSDL
    protected void delete() {
        //kiểm tra dữ liệu trên form khi delete
        if (!isValidated("delete")) {
            return;
        }
        
        int k = MsgBox.confirm("Bạn có chắc chắn muốn xoá?", false); //xác nhận với người dùng
        if (k == MsgBox.YES_OPTION) { //người dùng đồng ý
            int count = dao.delete(fields[0].getText()); //xoá dữ liệu
            outputResult("Xoá", count != 0); //xuất thông báo
            if (count != 0) { //nếu xoá thành công, fillTable và đặt lại form
                fillTable(0);
                clearForm();
            }
        }
    }

    //thêm listener cho các button chức năng chính và button điều hướng
    protected void addBtnListeners(String name, JButton... btns) {
        switch (name) {
            case "main":
                for (JButton btn : btns) {
                    btn.addActionListener((ActionEvent e) -> {
                        switch (e.getActionCommand()) {
                            case "new":
                                clearForm();
                                break;
                            case "insert":
                                insert();
                                break;
                            case "update":
                                update();
                                break;
                            case "delete":
                                delete();
                                break;
                        }
                    });
                }
                break;

            case "nav":
                for (JButton btn : btns) {
                    btn.addActionListener((ActionEvent e) -> {
                        switch (e.getActionCommand()) {
                            case "first":
                                navigate(0);
                                break;
                            case "previous":
                                navigate(--index);
                                break;
                            case "next":
                                navigate(++index);
                                break;
                            case "last":
                                navigate(tblList.getRowCount() - 1);
                                break;
                        }
                    });
                }
                break;
        }
    }

    //thêm listener cho bảng khi click
    protected void addTableListener() {
        tblList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    navigate(tblList.getSelectedRow());
                    tabs.setSelectedIndex(0);
                }
            }

        });
    }

    //điều hướng trên bảng tới vị trí index
    protected void navigate(int newIndex) {
        int count = tblList.getRowCount() - 1; //lấy số lượng row trên bảng
        
        //kiểm tra index cần điều hướng
        if (newIndex < 0) {
            index = 0;
        } else if (newIndex > count) {
            index = count;
        } else {
            index = newIndex;
        }
        
        setSelectedRow(index); //đặt hàng chọn trên bảng và hiển thị lên form
    }

    //cập nhật trạng thái cho các button
    protected void updateStatus(JButton... btns) {
        boolean validIndex = (index >= 0);
        boolean first = (index == 0);
        boolean last = (index == tblList.getRowCount() - 1);
        //field ID is non-editable
        fields[0].setEditable(!validIndex);

        for (JButton btn : btns) {
            switch (btn.getActionCommand()) {
                case "new":
                    formatDisabledButton(btn);
                    break;
                case "insert":
                    btn.setEnabled(!validIndex);
                    formatDisabledButton(btn);
                    break;
                case "update":
                    btn.setEnabled(validIndex);
                    formatDisabledButton(btn);
                    break;
                case "delete":
                    btn.setEnabled(Auth.isManager() && validIndex);
                    formatDisabledButton(btn);
                    break;
                case "first":
                    btn.setEnabled(validIndex && !first);
                    break;
                case "previous":
                    btn.setEnabled(validIndex && !first);
                    break;
                case "next":
                    btn.setEnabled(validIndex && !last);
                    break;
                case "last":
                    btn.setEnabled(validIndex && !last);
                    break;

            }
        }
    }

    //kiểm lỗi dữ liệu cho các field
    protected boolean verifyField(JTextComponent... fields) {
        boolean valid = true;
        for (JTextComponent field : fields) {
            valid = verifier.verify(field); //dùng InputVerifier để verify
           
            if (!valid) { //nếu lỗi, xuất thông báo lỗi 
                String err = "";
                if (field instanceof MyTextField) {
                    err = ((MyTextField) field).getError().getText(); //lấy dữ liệu lỗi
                } else if (field instanceof MyPasswordField) {
                    err = ((MyPasswordField) field).getError().getText(); //lấy dữ liệu lỗi
                }
                MsgBox.error(err);
                field.requestFocus();
                break;
            }
        }
        return valid;
    }

    //xuất thông báo result sau khi thực hiện chức năng
    protected void outputResult(String action, boolean result) {
        String name = "";
        switch (dao.getClass().getSimpleName()) {
            case "NhanVienDAO":
                name = "nhân viên";
                break;
            case "ChuyenDeDAO":
                name = "chuyên đề";
                break;
            case "KhoaHocDAO":
                name = "khoá học";
                break;
            case "NguoiHocDAO":
                name = "người học";
                break;
        }

        if (result) {
            MsgBox.inform(String.format("%s %s thành công!", action, name));
        } else {
            MsgBox.inform(String.format("%s %s thất bại!", action, name));
        }
    }

    //định dạng lại button khi bị disabled
    private void formatDisabledButton(JButton button) {
        if (button.isEnabled()) {
            button.setBackground(MyConstants.PURPLE_COLOR);
        } else {
            button.setDisabledIcon(button.getIcon());
            button.setBackground(MyConstants.GRAY_COLOR);
        }
    }
}
