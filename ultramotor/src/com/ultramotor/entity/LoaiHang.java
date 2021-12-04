/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ultramotor.entity;

public class LoaiHang extends Entity{

    private String idLH;
    private String tenLoaiHang;

    public LoaiHang() {
    }

    public LoaiHang(String idLH) {
        this.idLH = idLH;
    }

    public LoaiHang(String idLH, String tenLoaiHang) {
        this.idLH = idLH;
        this.tenLoaiHang = tenLoaiHang;
    }

    public String getIdLH() {
        return idLH;
    }

    public void setIdLH(String idLH) {
        this.idLH = idLH;
    }

    public String getTenLoaiHang() {
        return tenLoaiHang;
    }

    public void setTenLoaiHang(String tenLoaiHang) {
        this.tenLoaiHang = tenLoaiHang;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLH != null ? idLH.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LoaiHang)) {
            return false;
        }
        LoaiHang other = (LoaiHang) object;
        return !((this.idLH == null && other.idLH != null) || (this.idLH != null && !this.idLH.equals(other.idLH)));
    }

    @Override
    public String toString() {
        return tenLoaiHang;
    }
    
}
