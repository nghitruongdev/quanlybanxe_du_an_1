package com.edusys.entity;

import com.edusys.util.XDate;
import java.util.Date;

/**
 *
 * @author nghipc
 */
public class KhoaHoc {

    private Integer maKH;
    private String maCD;
    private Double hocPhi;
    private Integer thoiLuong;
    private Date ngayKG;
    private String ghiChu;
    private String maNV;
    private Date ngayTao;

    public KhoaHoc() {
    }

    public KhoaHoc(String maCD, Double hocPhi, Integer thoiLuong, Date ngayKG, String ghiChu, String maNV, Date ngayTao) {
        this(null, maCD, hocPhi, thoiLuong, ngayKG, ghiChu, maNV, ngayTao);
    }

    public KhoaHoc(Integer maKH, String maCD, Double hocPhi, Integer thoiLuong, Date ngayKG, String ghiChu, String maNV, Date ngayTao) {
        this.maKH = maKH;
        this.maCD = maCD;
        this.hocPhi = hocPhi;
        this.thoiLuong = thoiLuong;
        this.ngayKG = ngayKG;
        this.ghiChu = ghiChu;
        this.maNV = maNV;
        this.ngayTao = ngayTao;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Integer getMaKH() {
        return maKH;
    }

    public void setMaKH(Integer maKH) {
        this.maKH = maKH;
    }

    public String getMaCD() {
        return maCD;
    }

    public void setMaCD(String maCD) {
        this.maCD = maCD;
    }

    public Double getHocPhi() {
        return hocPhi;
    }

    public void setHocPhi(Double hocPhi) {
        this.hocPhi = hocPhi;
    }

    public Integer getThoiLuong() {
        return thoiLuong;
    }

    public void setThoiLuong(Integer thoiLuong) {
        this.thoiLuong = thoiLuong;
    }

    public Date getNgayKG() {
        return ngayKG;
    }

    public void setNgayKG(Date ngayKG) {
        this.ngayKG = ngayKG;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", maCD, XDate.toString(ngayKG, "dd-MM-YYYY"));
    }

}
