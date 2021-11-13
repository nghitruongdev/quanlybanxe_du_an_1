package com.edusys.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 *
 * @author nghipc
 * @param <Entity>
 */
public class ExcelHelper {
    
    public static void main(String[] args) {
        saveSingleSheet(null, null, new File("abc.xlsx"), "default");
    }
    
    /**
     * Đọc dữ liệu từ file excel, chỉ đọc một sheet đầu tiên nếu file có nhiều sheet
     * @param file excel chứa dữ liệu cần đọc
     * @return 
     */
    public static List<Object[]> readExcel(File file) {
        List<Object[]> rows = new ArrayList<>();    //tạo ds chứa các record trong file excel
        
        try (FileInputStream inputStream = new FileInputStream(file)) {     //tạo inputstream
            try (XSSFWorkbook wb = new XSSFWorkbook(inputStream)) {  //tạo mới workbook
                Sheet sheet = wb.getSheetAt(0);     //tạo sheet theo sheet đầu tiên trong file excel
                
                int columnCount = sheet.getRow(0).getLastCellNum();     //lấy số lượng cột theo header
                
                for (Row row : sheet) {
                    Object[] cells = new Object[columnCount];       //tạo row theo số lượng column, ràng buộc số lượng phần tử mảng
                  
                    for (int i = 0; i < columnCount; i++) {     // nếu dùng (Cell cell: row) -> cell trống/null sẽ bị bỏ qua --> lỗi đọc dữ liệu
                        cells[i] = getCellValue(row.getCell(i));    //lấy dữ liệu chứa trong cell
//                        System.out.println("Cell" + i + ": " + cells[i]);
                    }
                    rows.add(cells);    //thêm record vào danh sách
                }
            }
        } catch (FileNotFoundException ex) {
            MsgBox.error("Không tìm thấy file");
        } catch (IOException ex) {
            XLog.saveLog(ex.getMessage());
            MsgBox.error("Lỗi file! Vui lòng kiểm tra lại");
        }
        return rows;
    }

   /**
    * Lưu dữ liệu vào file excel, chỉ có 1 sheet
    * @param list data cần lưu
    * @param header tiêu đề các cột
    * @param file excel nơi dữ liệu sẽ được lưu
    * @param sheetName tên sheet mặc định
    */
    public static void saveSingleSheet(List<Object[]> list, String[] header, File file, String sheetName) {
        saveMultipleSheet(new String[]{sheetName}, new String[][]{header}, file, list);
    }

    /**
     * Lưu dữ liệu vào file excel, có thể có nhiều sheet
     * @param sheets tên sheet theo thứ tự
     * @param headers tiêu đề các cột, theo thứ tự sheet
     * @param file excel nơi chứa dữ liệu
     * @param lists các list data cần lưu
     */
    public static void saveMultipleSheet(String[] sheets, String[][] headers, File file, List<Object[]>... lists) {
        //nếu SL lists nhiều hơn sheets, báo lỗi
        if (lists.length > sheets.length) {
            throw new RuntimeException("Không đủ sheet");
        }
        
        //nếu SL list khác với số lượng tiêu đề, báo lỗi
        if (lists.length != headers.length) {
            throw new RuntimeException("Kiểm tra column name hoặc list");
        }
        
        //tạo workbook
        try (XSSFWorkbook wb = new XSSFWorkbook()) {
            CellStyle cs = wb.createCellStyle();   //tạo cellstyle để format cell
            for (int i = 0; i < lists.length; i++) {
                XSSFSheet sheet = wb.createSheet(sheets[i]); //tạo sheet mới
                
                createHeaders(headers[i], sheet); //tạo header 
                
                for (int k = 0; k < lists[i].size(); k++) {
                    //lấy danh sách i, record ở dòng k, lưu vào sheet ở dòng k + 1 (k =0:header)
                    createRow(lists[i].get(k), sheet.createRow(k + 1), cs); 
                }
            }

            //tạo output stream lưu dữ liệu vào file
            try (FileOutputStream outputStream = new FileOutputStream(file)) {
                wb.write(outputStream);
            }
            
        } catch (IOException ex) {
            XLog.saveLog(ex.getMessage());
            if (ex.getMessage().contains("being used")) {
                MsgBox.error("Không thể lưu dữ liệu vì file đang được mở. Vui lòng đóng file và thử lại");
            }
        }
    }

    /**
     * Lấy value chứa trong cell
     * @param cell
     * @return value
     */
    private static Object getCellValue(Cell cell) {
        //kiểm tra cell null
        if (cell == null) {
            return null;
        }
        //lấy value tuỳ theo loại cell
        switch (cell.getCellType()) {
//            case STRING:
//                return cell.getStringCellValue();
//            case NUMERIC:
//                return DateUtil.isCellDateFormatted(cell) ? cell.getDateCellValue() : cell.getNumericCellValue();
//            case BOOLEAN:
//                return cell.getBooleanCellValue();
//            case BLANK:
//            case _NONE:
//            default:
        }
        return null;
    }

    //tạo header cho sheet ở row đầu tiên
    private static void createHeaders(String[] cols, Sheet sheet) {
        Row row = sheet.createRow(0); //tạo dòng đầu tiên
        //tạo header
        int count = 0;
        for (String s : cols) {
            Cell cell = row.createCell(count++);
            cell.setCellValue(s);
        }
    }
    
    //thêm mảng value vào trong file excel
    private static void createRow(Object[] os, Row row, CellStyle cs) {
        int count = 0;
        for (Object o : os) {
            Cell cell = row.createCell(count++); //tạo cell mới
            
            //dữ liệu rỗng, bỏ qua
            if (o == null) {
                continue;
            }

            //thêm value theo từng loại thành phần mảng
            switch (o.getClass().getSimpleName()) {
                case "Integer":
                    cell.setCellValue((Integer) o);
                    break;
                case "Double":
                    cell.setCellValue((Double) o);
                    break;
                case "Boolean":
                    cell.setCellValue((Boolean) o);
                    break;
                case "Date":
                    //format cell hiển thị ngày tháng theo định dạng dd-MM-yyyy, ex: 15-05-2021
                    cs.setDataFormat(cell.getSheet().getWorkbook().getCreationHelper().createDataFormat().getFormat("dd-MM-yyyy"));
                    cell.setCellStyle(cs);
                    cell.setCellValue((Date) o);
                    break;
                case "String":
                    cell.setCellValue((String) o);
            }
        }
    }

    //kiểm tra file có hợp lệ để lưu file excel
    public static boolean checkFileValid(File file) {
        //nếu tên file trống
        if (file.getName().isEmpty()) {
            MsgBox.error("Vui lòng không để trống tên file");
            file.delete();
            return false;
        }
        
        //nếu phần mở rộng != xlsx
        if (!file.getName().endsWith(".xlsx")) {
            MsgBox.error("Ứng dụng chỉ hỗ trợ xuất file xlsx. Vui lòng thử lại");
//            file.delete();
            return false;
        }
        
        //nếu file đã tồn tại, hỏi xác nhận ghi đè
        if (file.exists()) {
            int confirm = MsgBox.confirm("File đã tồn tại, bạn có muốn ghi đè lên file cũ?", false);
            if (confirm != 0) {
                return false;
            }
        }

        return true;
    }
}
