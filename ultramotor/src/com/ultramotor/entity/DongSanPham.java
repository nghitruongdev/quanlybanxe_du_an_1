/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ultramotor.entity;


public class DongSanPham extends Entity{

    private String idDongSP;
    private String tenDongSP;
    private String idLH;
    private String idNSX;

    public DongSanPham() {
    }

    public DongSanPham(String idDongSP) {
        this.idDongSP = idDongSP;
    }

    public DongSanPham(String idDongSP, String tenDongSP, String idLH, String idNSX) {
        this.idDongSP = idDongSP;
        this.tenDongSP = tenDongSP;
        this.idLH = idLH;
        this.idNSX = idNSX;
    }
    
    public String getIdDongSP() {
        return idDongSP;
    }

    public void setIdDongSP(String idDongSP) {
        this.idDongSP = idDongSP;
    }

    public String getTenDongSP() {
        return tenDongSP;
    }

    public void setTenDongSP(String tenDongSP) {
        this.tenDongSP = tenDongSP;
    }

    public String getIdLH() {
        return idLH;
    }

    public void setIdLH(String idLH) {
        this.idLH = idLH;
    }

    public String getIdNSX() {
        return idNSX;
    }

    public void setIdNSX(String idNSX) {
        this.idNSX = idNSX;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDongSP != null ? idDongSP.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DongSanPham)) {
            return false;
        }
        DongSanPham other = (DongSanPham) object;
        if ((this.idDongSP == null && other.idDongSP != null) || (this.idDongSP != null && !this.idDongSP.equals(other.idDongSP))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ultramotor.entity.DongSanPham[ idDongSP=" + idDongSP + " ]";
    }
    
}
