package com.edusys.entity;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author nghipc
 */
public class NguoiHoc {

    private String maNH;
    private String hoTen;
    private Date ngaySinh;
    private Boolean gioiTinh;
    private String dienThoai;
    private String email;
    private String ghiChu;
    private String maNV;
    private Date ngayDK;

    public NguoiHoc() {
    }

    public NguoiHoc(String maNH, String hoTen, Date ngaySinh, Boolean gioiTinh, String dienThoai, String email, String ghiChu, String maNV, Date ngayDK) {
        this.maNH = maNH;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.dienThoai = dienThoai;
        this.email = email;
        this.ghiChu = ghiChu;
        this.maNV = maNV;
        this.ngayDK = ngayDK;
    }

    public Date getNgayDK() {
        return ngayDK;
    }

    public void setNgayDK(Date ngayDK) {
        this.ngayDK = ngayDK;
    }

    public String getMaNH() {
        return maNH;
    }

    public void setMaNH(String maNH) {
        this.maNH = maNH;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public Boolean getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(Boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.maNH);
        hash = 29 * hash + Objects.hashCode(this.hoTen);
        hash = 29 * hash + Objects.hashCode(this.ngaySinh);
        hash = 29 * hash + Objects.hashCode(this.gioiTinh);
        hash = 29 * hash + Objects.hashCode(this.dienThoai);
        hash = 29 * hash + Objects.hashCode(this.email);
        hash = 29 * hash + Objects.hashCode(this.ghiChu);
        hash = 29 * hash + Objects.hashCode(this.maNV);
        hash = 29 * hash + Objects.hashCode(this.ngayDK);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NguoiHoc other = (NguoiHoc) obj;
        if (!Objects.equals(this.maNH, other.maNH)) {
            return false;
        }
        if (!Objects.equals(this.hoTen, other.hoTen)) {
            return false;
        }
        if (!Objects.equals(this.dienThoai, other.dienThoai)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.ghiChu, other.ghiChu)) {
            return false;
        }
        if (!Objects.equals(this.maNV, other.maNV)) {
            return false;
        }
        if (!Objects.equals(this.ngaySinh, other.ngaySinh)) {
            return false;
        }
        if (!Objects.equals(this.gioiTinh, other.gioiTinh)) {
            return false;
        }
        if (!Objects.equals(this.ngayDK, other.ngayDK)) {
            return false;
        }
        return true;
    }

    
}
