package com.edusys.io;

import com.edusys.dao.EduSysDAO;
import com.edusys.entity.ChuyenDe;
import com.edusys.entity.HocVien;
import com.edusys.entity.KhoaHoc;
import com.edusys.entity.NguoiHoc;
import com.edusys.util.ExcelHelper;
import com.edusys.util.MsgBox;
import com.edusys.util.XDate;
import com.edusys.util.XLog;
import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author nghipc
 */
public class EduSysImport {

    /**
     * Hiện hộp thoại chọn file để import dữ liệu
     * @return file excel chứa dữ liệu cần import
     */
    public static File showOpenDialog() {
        JFileChooser chooser = new JFileChooser();
        
        chooser.setAcceptAllFileFilterUsed(false); //không chấp nhận All files, chỉ lấy file excel
        chooser.setFileFilter(new FileNameExtensionFilter("Excel files", "xlsx"));
        
        if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
            return null;
        }
        return chooser.getSelectedFile();
    }

    //Import dữ liệu chuyên đề
    public static void importChuyenDe(File file) {
        int count = 0; //đếm số bản ghi thành công
        int err = 0; //đếm số bản ghi bị lỗi
        List<Object[]> rows = ExcelHelper.readExcel(file); //đọc file excel
        
        //kiểm tra header có trùng khớp như khi export
        if (!checkHeader(rows.get(0), "Chuyên Đề")) {
            MsgBox.error("File sao lưu không hợp lệ! Vui lòng kiểm tra lại dữ liệu.");
            return;
        }
        
        //tiến hành import dữ liệu
        for (int i = 1; i < rows.size(); i++) {
            try {
                Object[] cells = rows.get(i); //lấy ra các cell chứa dữ liệu theo hàng
                String maCD = (String) cells[0];
                String tenCD = (String) cells[1];
                Double hocPhi = ((Number) cells[2]).doubleValue();
                Integer thoiLuong = ((Number) cells[3]).intValue();
                String hinh = (String) cells[4];
                String moTa = (String) cells[5];
                ChuyenDe cd = new ChuyenDe(maCD, tenCD, hocPhi, thoiLuong, hinh, moTa);
                
                int num = EduSysDAO.CHUYEN_DE_DAO.insert(cd); //tiến hành thêm dữ liệu vào CSDL
                
                if (num != 0) { //ghi nhận lại bản ghi
                    count++;
                } else {
                    err++;
                }
            } catch (Exception e) {
                XLog.saveLog(e.getMessage());
                err++;
            }
        }
        outputResult(rows.size() - 1, count, err, "chuyên đề"); //xuất thông báo kết quả
    }

    //Import dữ liệu khoá học
    public static void importKhoaHoc(File file) {
        int count = 0; //bản ghi thành công
        int err = 0; //bản ghi lỗi
        
        //đọc file excel
        List<Object[]> rows = ExcelHelper.readExcel(file);
        //kiểm tra header trùng khớp khi export
        if (!checkHeader(rows.get(0), "Khoá Học")) {
            MsgBox.error("File sao lưu không hợp lệ! Vui lòng kiểm tra lại dữ liệu.");
            return;
        }
        
        //tiến hành import dữ liệu
        for (int i = 1; i < rows.size(); i++) {
            try {
                Object[] cells = rows.get(i); //lấy ra các cell chứa dữ liệu theo hàng
                Integer maKH = cells[0] == null ? null : ((Number) cells[0]).intValue(); //kiểm tra cell trống hay không
                String maCD = (String) cells[1];
                Double hocPhi = ((Number) cells[3]).doubleValue();
                Integer thoiLuong = ((Number) cells[3]).intValue();
                Date ngayKG = XDate.parse((String) cells[4]);
                String ghiChu = (String) cells[5];
                String maNV = (String) cells[6];
                Date ngayTao = XDate.parse((String) cells[7]);
                KhoaHoc kh = new KhoaHoc(maKH, maCD, hocPhi, thoiLuong, ngayKG, ghiChu, maNV, ngayTao);
              
                int num = EduSysDAO.KHOA_HOC_DAO.insert(kh); //thêm dữ liệu vào CSDL
                if (num != 0) { //ghi nhận lại bản ghi
                    count++;
                } else {
                    err++;
                }
            } catch (Exception e) {
                XLog.saveLog(e.getMessage());
                err++;
            }
        }
        outputResult(rows.size() - 1, count, err, "khoá học"); //xuất thông báo kết quả
    }

    //import dữ liệu người học
    public static void importNguoiHoc(File file) {
        int count = 0; //số bản ghi thành công
        int err = 0; //số bản ghi lỗi 
        
        //đọc file excel
        List<Object[]> rows = ExcelHelper.readExcel(file);
        //kiểm tra header trùng khớp như khi export
        if (!checkHeader(rows.get(0), "Người Học")) {
            MsgBox.error("File sao lưu không hợp lệ! Vui lòng kiểm tra lại dữ liệu.");
            return;
        }
        
        //tiến hành import dữ liệu
        for (int i = 1; i < rows.size(); i++) {
            try {
                Object[] cells = rows.get(i); //lấy cell chứa dữ liệu theo hàng
                String maNH = (String) cells[0];
                String hoTen = (String) cells[1];
                Date ngaySinh = (Date) cells[2];
                Boolean gioiTinh = (Boolean) cells[3];
                String dienThoai = (String) cells[4];
                String email = (String) cells[5];
                String ghiChu = (String) cells[6];
                String maNV = (String) cells[7];
                Date ngayDK = (Date) cells[8];
                NguoiHoc nh = new NguoiHoc(maNH, hoTen, ngaySinh, gioiTinh, dienThoai, email, ghiChu, maNV, ngayDK);
                
                int num = EduSysDAO.NGUOI_HOC_DAO.insert(nh); //tiến hành import dữ liệu
                if (num != 0) { //ghi nhận kết quả
                    count++;
                } else {
                    err++;
                }
            } catch (Exception e) {
                XLog.saveLog(e.getMessage());
                err++;
            }
        }
        outputResult(rows.size() - 1, count, err, "người học"); //xuất thông báo kết quả
    }

    //import dữ liệu học viên
    public static void importHocVien(File file) {
        int count = 0; //số bản ghi thành công
        int err = 0; //số bản ghi bị lỗi
        
        //đọc file excel
        List<Object[]> rows = ExcelHelper.readExcel(file);
        //kiểm tra header trùng khớp như kkhi export dữ liệu
        if (!checkHeader(rows.get(0), "Học Viên")) {
            MsgBox.error("File sao lưu không hợp lệ! Vui lòng kiểm tra lại dữ liệu.");
            return;
        }
        
        //tiến hành import dữ liệu
        for (int i = 1; i < rows.size(); i++) {
            try {
                Object[] cells = rows.get(i); //lấy các cell chứa dữ liệu theo hàng
                Integer maHV = cells[0] == null ? null : ((Number) cells[0]).intValue(); //kiểm tra cell rỗng
                Integer maKH = ((Number) cells[1]).intValue();
                String maNH = (String) cells[2];
                Double diem = cells[3] == null ? null : ((Number) cells[3]).doubleValue(); //kiểm tra cell rỗng
                HocVien hv = new HocVien(maHV, maKH, maNH, diem);
               
                int num = EduSysDAO.HOC_VIEN_DAO.insert(hv); //thêm dữ liệu vào CSDL
                if (num != 0) { //ghi nhận bản ghi
                    count++;
                } else {
                    err++;
                }
            } catch (Exception e) {
                XLog.saveLog(e.getMessage());
                err++;
            }
        }
        outputResult(rows.size() - 1, count, err, "học viên"); //xuất thông báo
    }

    
    //kiểm tra header của file import có trùng với kiểu file khi export không
    private static boolean checkHeader(Object[] header, String name) {
        String[] cols = {};
        switch (name) {
            case "Chuyên Đề":
                cols = new String[]{"Mã Chuyên Đề", "Tên Chuyên Đề", "Học Phí", "Thời Lượng", "Hình", "Mô Tả"};
                break;
            case "Khoá Học":
                cols = new String[]{"Mã Khoá Học", "Mã Chuyên Đề", "Học Phí", "Thời Lượng", "Ngày Khai Giảng", "Ghi Chú", "Mã Nhân Viên", "Ngày Tạo"};
                break;
            case "Người Học":
                cols = new String[]{"Mã Người Học", "Họ Tên", "Ngày Sinh", "Giới Tính", "Điện Thoại", "Email", "Ghi Chú", "Mã Nhân Viên", "Ngày Đăng Ký"};
                break;
            case "Học Viên":
                cols = new String[]{"Mã Học Viên", "Mã Khoá Học", "Mã Người Học", "Điểm"};
                break;
        }
        return Arrays.equals(header, cols);
    }

    //xuất thông báo kết quả
    private static void outputResult(int total, int count, int err, String name) {
        MsgBox.inform(String.format("Tổng cộng có %d %s. Đã thêm %d %s và lỗi %d %s.", total, name, count, name, err, name));
    }
}
