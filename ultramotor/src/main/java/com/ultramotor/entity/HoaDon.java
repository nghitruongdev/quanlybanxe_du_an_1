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
    private int giamGia = 0;
    private String trangThai;
    private String idKH;
    private String idNV;
    private List<ChiTietHoaDon> listCTHD = new ArrayList<>();

    public HoaDon() {
    }

    public HoaDon(String idHD) {
        this.idHD = idHD;
    }

    public HoaDon(String idHD, Date thoiGian, int giamGia, String trangThai, String idNV, String idKH) {
        this.idHD = idHD;
        this.thoiGian = thoiGian;
        this.giamGia = giamGia;
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

    public String getTrangThai() {
        return trangThai;
    }

    public int getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(int giamGia) {
        this.giamGia = giamGia;
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
        return sum - sum * giamGia / 100d;
    }

    public double getThue() {
        return getTongTien() * 10d / 100;
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
