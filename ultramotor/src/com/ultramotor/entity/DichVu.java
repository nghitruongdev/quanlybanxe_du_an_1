/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ultramotor.entity;

import java.util.List;

public class DichVu {

    private String idDV;
    private String tenDV;
    private double donGia;
    private String idNV;
    private List<ChiTietHoaDon> chiTietHoaDonList;

    public DichVu() {
    }

    public DichVu(String idDV) {
        this.idDV = idDV;
    }

    public DichVu(String idDV, String tenDV, double donGia, String idNV) {
        this.idDV = idDV;
        this.tenDV = tenDV;
        this.donGia = donGia;
        this.idNV = idNV;
    }

    public String getIdDV() {
        return idDV;
    }

    public void setIdDV(String idDV) {
        this.idDV = idDV;
    }

    public String getTenDV() {
        return tenDV;
    }

    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public List<ChiTietHoaDon> getChiTietHoaDonList() {
        return chiTietHoaDonList;
    }

    public void setChiTietHoaDonList(List<ChiTietHoaDon> chiTietHoaDonList) {
        this.chiTietHoaDonList = chiTietHoaDonList;
    }

    public String getIdNV() {
        return idNV;
    }

    public void setIdNV(String idNV) {
        this.idNV = idNV;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDV != null ? idDV.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DichVu)) {
            return false;
        }
        DichVu other = (DichVu) object;
        if ((this.idDV == null && other.idDV != null) || (this.idDV != null && !this.idDV.equals(other.idDV))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ultramotor.entity.DichVu[ idDV=" + idDV + " ]";
    }

}
