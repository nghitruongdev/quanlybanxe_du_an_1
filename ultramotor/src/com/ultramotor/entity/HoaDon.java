/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ultramotor.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HoaDon extends Entity {

    private String idHD;
    private Date thoiGian;
    private String loaiThanhToan;
    private String trangThai;
    private String idKH;
    private String idNV;
    private List<ChiTietHoaDon> listCTHD = new ArrayList<>();

    public HoaDon() {
    }

    public HoaDon(String idHD) {
        this.idHD = idHD;
    }

    public HoaDon(String idHD, Date thoiGian, String loaiThanhToan, String trangThai, String idKH, String idNV) {
        this.idHD = idHD;
        this.thoiGian = thoiGian;
        this.loaiThanhToan = loaiThanhToan;
        this.trangThai = trangThai;
        this.idKH = idKH;
        this.idNV = idNV;
    }

    public String getIdHD() {
        return idHD;
    }

    public void setIdHD(String idHD) {
        this.idHD = idHD;
    }

    public Date getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(Date thoiGian) {
        this.thoiGian = thoiGian;
    }

    public String getLoaiThanhToan() {
        return loaiThanhToan;
    }

    public void setLoaiThanhToan(String loaiThanhToan) {
        this.loaiThanhToan = loaiThanhToan;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public List<ChiTietHoaDon> getListCTHD() {
        return listCTHD;
    }

    public void setListCTHD(List<ChiTietHoaDon> listCTHD) {
        this.listCTHD = listCTHD;
    }

    public void addCTHD(ChiTietHoaDon ct) {
        this.listCTHD.add(ct);
    }

    public String getIdKH() {
        return idKH;
    }

    public void setIdKH(String idKH) {
        this.idKH = idKH;
    }

    public String getIdNV() {
        return idNV;
    }

    public void setIdNV(String idNV) {
        this.idNV = idNV;
    }

    public double getTongTien() {
        double sum = 0;
        sum = listCTHD.stream().map(ct -> ct.getDonGia()).reduce(sum, (accumulator, _item) -> accumulator + _item);
        return sum;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHD != null ? idHD.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HoaDon)) {
            return false;
        }
        HoaDon other = (HoaDon) object;
        if ((this.idHD == null && other.idHD != null) || (this.idHD != null && !this.idHD.equals(other.idHD))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idHD;
    }

}
