/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ultramotor.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author nghipc
 */

public class PhieuNhapKho implements Serializable {

    private String idPN;
    private Date ngayNhap;
    private String idNV;
    private List<ChiTietNhapKho> chiTietNhapKhoList;

    public PhieuNhapKho() {
    }

    public PhieuNhapKho(String idPN) {
        this.idPN = idPN;
    }

    public PhieuNhapKho(String idPN, Date ngayNhap, String idNV) {
        this.idPN = idPN;
        this.ngayNhap = ngayNhap;
        this.idNV = idNV;
    }

    public String getIdPN() {
        return idPN;
    }

    public void setIdPN(String idPN) {
        this.idPN = idPN;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public String getIdNV() {
        return idNV;
    }

    public void setIdNV(String idNV) {
        this.idNV = idNV;
    }

    public List<ChiTietNhapKho> getChiTietNhapKhoList() {
        return chiTietNhapKhoList;
    }

    public void setChiTietNhapKhoList(List<ChiTietNhapKho> chiTietNhapKhoList) {
        this.chiTietNhapKhoList = chiTietNhapKhoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPN != null ? idPN.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PhieuNhapKho)) {
            return false;
        }
        PhieuNhapKho other = (PhieuNhapKho) object;
        if ((this.idPN == null && other.idPN != null) || (this.idPN != null && !this.idPN.equals(other.idPN))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ultramotor.entity.PhieuNhapKho[ idPN=" + idPN + " ]";
    }
    
}
