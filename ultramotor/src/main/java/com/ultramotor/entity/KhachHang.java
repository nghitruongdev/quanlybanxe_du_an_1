/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ultramotor.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class KhachHang extends Entity implements Serializable {

    private final Long serialVersionUID = 1234567L;
    private String idKH;
    private String hoKH;
    private String tenKH;
    private boolean gioiTinh;
    private Date ngaySinh;
    private String diaChi;
    private String sdt;
    private String email;
    private Boolean thanhVien;
    private String ghiChu;
    private transient List<HoaDon> hoaDonList = new ArrayList<>();
    private String maNV;

    public KhachHang() {
    }

    public KhachHang(String idKH) {
        this.idKH = idKH;
    }

    public KhachHang(String idKH, String hoKH, String tenKH, boolean gioiTinh, Date ngaySinh, String diaChi, String sdt, String email, Boolean thanhVien, String ghiChu, String maNV) {
        this.idKH = idKH;
        this.hoKH = hoKH;
        this.tenKH = tenKH;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.email = email;
        this.thanhVien = thanhVien;
        this.ghiChu = ghiChu;
        this.maNV = maNV;
    }

    public String getIdKH() {
        return idKH;
    }

    public void setIdKH(String idKH) {
        this.idKH = idKH;
    }

    public String getHoKH() {
        return hoKH;
    }

    public void setHoKH(String hoKH) {
        this.hoKH = hoKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getHoTenKH() {
        return String.format("%s %s", hoKH, tenKH);
    }

    public boolean getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getThanhVien() {
        return thanhVien;
    }

    public void setThanhVien(Boolean thanhVien) {
        this.thanhVien = thanhVien;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public List<HoaDon> getHoaDonList() {
        return hoaDonList;
    }

    public void setHoaDonList(List<HoaDon> hoaDonList) {
        this.hoaDonList = hoaDonList;
    }

    public double getTongTien() {
        double sum = 0;
        sum = hoaDonList.stream().filter(hd -> hd.getTrangThai().equalsIgnoreCase("HOÀN TẤT")).map(hoaDon -> hoaDon.getTongTien()).reduce(sum, (accumulator, _item) -> accumulator + _item);
        return sum;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idKH != null ? idKH.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KhachHang)) {
            return false;
        }
        KhachHang other = (KhachHang) object;
        if ((this.idKH == null && other.idKH != null) || (this.idKH != null && !this.idKH.equals(other.idKH))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idKH;
    }

}
