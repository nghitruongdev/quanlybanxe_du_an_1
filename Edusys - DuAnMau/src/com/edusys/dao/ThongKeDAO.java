package com.edusys.dao;

import com.edusys.util.XJdbc;
import com.edusys.util.XLog;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nghipc
 */
public class ThongKeDAO {

    private List<Object[]> getListOfArray(String sql, String[] cols, Object... args) {
        List<Object[]> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.query(sql, args)) {
            while (rs.next()) {
                Object[] vals = new Object[cols.length];
                for (int i = 0; i < vals.length; i++) {
                    if (cols[i].isEmpty()) {
                        continue;
                    }
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
        } catch (SQLException ex) {
            XLog.saveLog(ex.getMessage());
        } finally {
            XJdbc.closeCon();
        }
        return list;
    }

    public List<Object[]> getBangDiem(Integer maKH) {
        String sql = "{CALL sp_BangDiem(?)}";
        String[] cols = {"MaNH", "HoTen", "Diem", "",""};
        List<Object[]> list = this.getListOfArray(sql, cols, maKH);
        list.forEach((Object[] os) -> {
            os[3] = xepLoai((Double) os[2]);
            os[4] = maKH;
        });
        return list;
    }

    public List<Object[]> getLuongNguoiHoc() {
        String sql = "{CALL sp_LuongNguoiHoc}";
        String[] cols = {"Nam", "SoLuong", "DauTien", "CuoiCung"};
        return this.getListOfArray(sql, cols);
    }

    public List<Object[]> getDiemChuyenDe() {
        String sql = "{CALL sp_ThongKeDiem}";
        String[] cols = {"ChuyenDe", "SoHV", "CaoNhat", "ThapNhat", "TrungBinh"};
        return this.getListOfArray(sql, cols);
    }

    public List<Object[]> getDoanhThu(int year) {
        String sql = "{CALL sp_ThongKeDoanhThu(?)}";
        String[] cols = {"ChuyenDe", "SoKH", "SoHV", "DoanhThu", "CaoNhat", "ThapNhat", "TrungBinh",""};
        List<Object[]> list = this.getListOfArray(sql, cols, year);
        list.forEach((Object[] os) -> {
            os[7] = year;
        });
        return list;
    }

    private String xepLoai(Double grade) {
        if (grade == null) {
            return "";
        }
        return grade >= 9 ? "Xuất sắc" : (grade >= 7.5 ? "Giỏi" : (grade >= 6.5 ? "Khá" : (grade >= 5 ? "Trung bình" : "Yếu")));
    }
}
