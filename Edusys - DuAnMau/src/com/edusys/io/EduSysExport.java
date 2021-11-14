package com.edusys.io;

import com.edusys.dao.EduSysDAO;
import com.edusys.dao.KhoaHocDAO;
import com.edusys.entity.ChuyenDe;
import com.edusys.entity.HocVien;
import com.edusys.entity.KhoaHoc;
import com.edusys.entity.NguoiHoc;
import com.edusys.entity.NhanVien;
import com.edusys.util.ExcelHelper;
import com.edusys.util.MsgBox;
import com.edusys.util.XDate;
import com.edusys.util.XLog;
import com.edusys.util.XMail;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.swing.JFileChooser;

/**
 *
 * @author nghipc
 */
public class EduSysExport {

    /**
     * Hiện hộp thoại lưu file excel
     *
     * @param name tên loại dữ liệu
     * @return file nơi dữ liệu sẽ được lưu vào
     */
    public static File showSaveDialog(String name) {
        JFileChooser chooser = new JFileChooser();
        //đề xuất tên mặc định cho file lưu theo tên dữ liệu cần lưu + ngày tháng
        File file = new File("EduSys_" + name.replaceAll(" ", "_") + XDate.toString(new Date(), "_dd_MM_yyyy") + ".xlsx");
        chooser.setSelectedFile(file);

        while (true) {
            if (chooser.showSaveDialog(null) != JFileChooser.APPROVE_OPTION) { //nếu người dùng khôn chọn save, dừng lưu file
                return null;
            } else {
                file = chooser.getSelectedFile();
                if (!ExcelHelper.checkFileValid(file)) { //nếu file không hợp lệ để lưu dữ liệu
                    continue;
                }
                break;
            }
        }
        return file;
    }

    /**
     * Export dữ liệu và gửi vào mail người dùng
     *
     * @param list danh sách dữ liệu cần gửi
     * @param entityName tên loại dữ liệu
     * @param sheetName tên sheet chứa dữ liệu
     * @param email địa chỉ email người nhận
     */
    public static void sendMail(List list, String entityName, String sheetName, String email) {
        //tạo file excel tạm thời chứa dữ liệu
        File temp = exportTempList(list, entityName, sheetName);
        if (temp == null) {
            return;
        }

        //nội dung mail 
        String msg = "EduSys_" + entityName.replaceAll(" ", "_") + XDate.toString(new Date(), "_dd_MM_yyyy");

        //gửi mail
        XMail.sendMail(Collections.singletonMap(email, msg), "[LAPTRINHCITY] HỆ QUẢN LÝ ĐÀO TẠO EDUSYS", temp);

    }

    //export dữ liệu sang excel dưới dạng file tạm
    private static File exportTempList(List list, String entityName, String sheetName) {
        File file = null;
        try {
            file = Files.createTempFile(Paths.get("logs"), "", ".xlsx").toFile(); //tạo file tạm chứa dữ liệu
            file.deleteOnExit(); //xoá file tạm khi đóng chương trình
            exportList(list, entityName, file, sheetName, false); //xuất dữ liệu ra file tạm
        } catch (IOException ex) {
            XLog.saveLog(ex.getMessage());
        }
        return file;
    }

    /**
     * xuất dữ liệu ra file excel có xuất thông báo
     *
     * @param list dữ liệu cần export
     * @param entityName tên loại dữ liệu
     * @param file excel nơi chứa dữ liệu
     * @param sheetName tên sheet
     */
    public static void exportList(List list, String entityName, File file, String sheetName) {
        exportList(list, entityName, file, sheetName, true);
    }

    /**
     * xuất dữ liệu ra file excel có xuất thông báo
     *
     * @param list dữ liệu cần export
     * @param entityName tên loại dữ liệu
     * @param file excel nơi chứa dữ liệu
     * @param sheetName tên sheet
     * @param outputRs option xuất thông báo
     */
    public static void exportList(List list, String entityName, File file, String sheetName, boolean outputRs) {
        List<Object[]> listObject = list; //mặc định dành cho các list thống kê
        String[] cols = getHeader(entityName); //tạo header cho các column
        switch (entityName) {
            case "Chuyên Đề":
            case "Khoá Học":
            case "Người Học":
            case "Học Viên":
            case "Nhân Viên":
                listObject = getInfo(list); //getInfo để lấy dữ liệu theo mảng
                break;
        }
        ExcelHelper.saveSingleSheet(listObject, cols, file, sheetName); //lưu thành file excel chỉ có một sheet
        if (outputRs) { //xuất thông báo và hỏi người dùng mở file
            openRs(file);
        }
    }

    /**
     * Xuất tất cả report về thống kê tổng hợp
     *
     * @param file excel - chứa dữ liệu export
     * @param isManager - nếu true sẽ export kèm theo doanh thu
     */
    public static void exportAllThongKEe(File file, boolean isManager) {
        //tạo tên sheet
        String[] sheetNhanVien = {"Bảng Điểm Theo Khoá", "Lượng Người Học", "Điểm Chuyên Đề"};
        String[] sheetManager = {"Bảng Điểm Theo Khoá", "Lượng Người Học", "Điểm Chuyên Đề", "Doanh Thu Theo Năm"};

        //tạo column header
        String[] colBD = getHeader("Bảng Điểm");
        String[] colNH = getHeader("Lượng Người Học");
        String[] colCD = getHeader("Điểm Chuyên Đề");
        String[] colDT = getHeader("Doanh Thu");

        //lấy danh sách chứa dữ liệu người học
        List<Object[]> listLuongNH = EduSysDAO.THONG_KE_DAO.getLuongNguoiHoc();

        //lấy danh sách chứa dữ liệu điểm chuyên đề
        List<Object[]> listDiemCD = EduSysDAO.THONG_KE_DAO.getDiemChuyenDe();

        //lấy danh sách chứa dữ liệu điểm bảng điểm tất cả khoá học
        List<Object[]> listBD = new ArrayList<>();
        ((KhoaHocDAO) EduSysDAO.KHOA_HOC_DAO).selectAll().forEach((kh) -> {
            List<Object[]> bangDiem = EduSysDAO.THONG_KE_DAO.getBangDiem(kh.getMaKH());
            listBD.addAll(bangDiem);
        });

        //lấy danh sách chứa dữ liệu doanh thu tất cả các năm
        List<Object[]> listDT = new ArrayList<>();
        ((KhoaHocDAO) EduSysDAO.KHOA_HOC_DAO).selectAllYears().forEach((year) -> {
            List<Object[]> doanhThu = EduSysDAO.THONG_KE_DAO.getDoanhThu(year);
            listDT.addAll(doanhThu);
        });

        if (!isManager) {// nếu không phải manager, không xuất doanh thu
            ExcelHelper.saveMultipleSheet(sheetNhanVien, new String[][]{colBD, colNH, colCD}, file, listBD, listLuongNH, listDiemCD);
        } else {
            ExcelHelper.saveMultipleSheet(sheetManager, new String[][]{colBD, colNH, colCD, colDT}, file, listBD, listLuongNH, listDiemCD, listDT);
        }
        openRs(file); //thông báo kết quả
    }

    //lấy mảng dữ liệu theo từng loại entity
    private static Object[] getInfo(Object e) {
        switch (e.getClass().getSimpleName()) {
            case "NhanVien":
                NhanVien nv = (NhanVien) e;
                return new Object[]{
                    nv.getMaNV(),
                    nv.getHoTen(),
                    nv.getVaiTro(),
                    nv.getEmail()
                };
            case "ChuyenDe":
                ChuyenDe cd = (ChuyenDe) e;
                return new Object[]{
                    cd.getMaCD(),
                    cd.getTenCD(),
                    cd.getHocPhi(),
                    cd.getThoiLuong(),
                    cd.getHinh(),
                    cd.getMoTa()
                };
            case "KhoaHoc":
                KhoaHoc kh = (KhoaHoc) e;
                return new Object[]{
                    kh.getMaKH(),
                    kh.getMaCD(),
                    kh.getHocPhi(),
                    kh.getThoiLuong(),
                    kh.getNgayKG(),
                    kh.getGhiChu(),
                    kh.getMaNV(),
                    kh.getNgayTao()
                };
            case "NguoiHoc":
                NguoiHoc nh = (NguoiHoc) e;
                return new Object[]{
                    nh.getMaNH(),
                    nh.getHoTen(),
                    nh.getNgaySinh(),
                    nh.getGioiTinh(),
                    nh.getDienThoai(),
                    nh.getEmail(),
                    nh.getGhiChu(),
                    nh.getMaNV(),
                    nh.getNgayDK()
                };
            case "HocVien":
                HocVien hv = (HocVien) e;
                return new Object[]{
                    hv.getMaHV(),
                    hv.getMaKH(),
                    hv.getMaNH(),
                    hv.getDiem()
                };
        }
        return null;
    }

    //lấy dữ liệu của cả danh sách
    private static List<Object[]> getInfo(List list) {
        List<Object[]> listObject = new ArrayList<>();

        list.forEach((entity) -> {
            listObject.add(getInfo(entity));
        });
        return listObject;
    }

    //tạo header cho column theo từng loại entity
    private static String[] getHeader(String name) {
        String[] header = null;
        switch (name) {
            case "Chuyên Đề":
                header = new String[]{"Mã Chuyên Đề", "Tên Chuyên Đề", "Học Phí", "Thời Lượng", "Hình", "Mô Tả"};
                break;
            case "Khoá Học":
                header = new String[]{"Mã Khoá Học", "Mã Chuyên Đề", "Học Phí", "Thời Lượng", "Ngày Khai Giảng", "Ghi Chú", "Mã Nhân Viên", "Ngày Tạo"};
                break;
            case "Người Học":
                header = new String[]{"Mã Người Học", "Họ Tên", "Ngày Sinh", "Giới Tính", "Điện Thoại", "Email", "Ghi Chú", "Mã Nhân Viên", "Ngày Đăng Ký"};
                break;
            case "Học Viên":
                header = new String[]{"Mã Học Viên", "Mã Khoá Học", "Mã Người Học", "Điểm"};
                break;
            case "Nhân Viên":
                header = new String[]{"Mã Nhân Viên", "Họ Tên", "Vai Trò", "Email"};
                break;
            case "Bảng Điểm":
                header = new String[]{"Mã Người Học", "Họ Tên", "Điểm", "Xếp loại", "Mã Khoá Học"};
                break;
            case "Lượng Người Học":
                header = new String[]{"Năm", "Số Lượng NH", "ĐK Đầu Tiên", "ĐK Cuối Cùng"};
                break;
            case "Điểm Chuyên Đề":
                header = new String[]{"Chuyên Đề", "Số Học Viên", "Điểm Cao Nhất", "Điểm Thấp Nhất", "Điểm Trung Bình"};
                break;
            case "Doanh Thu":
                header = new String[]{"Chuyên Đề", "Số Khoá Học", "Số Học Viên", "Doanh Thu", "HP Cao Nhất", "HP Thấp Nhất", "HP Trung Bình", "Năm"};
                break;

        }
        return header;
    }

    //xuất thông báo kết quả
    private static void openRs(File file) {
        if (Desktop.isDesktopSupported()) {
            //xuất thông báo kèm hỏi mở file
            int k = MsgBox.confirm("Xuất dữ liệu thành công! Bạn có muốn mở file?", false);
            if (k == 0) {
                try {
                    Desktop.getDesktop().open(file);
                } catch (IOException ex) {
                }
            }
        } else {
            //chỉ xuất thông báo nếu Desktop không hỗ trợ mở file
            MsgBox.inform("Xuất dữ liệu thành công!");
        }
    }
}
