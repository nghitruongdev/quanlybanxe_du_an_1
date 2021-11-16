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
@Entity
@Table(name = "PhieuXuatKho")
@NamedQueries({
    @NamedQuery(name = "PhieuXuatKho.findAll", query = "SELECT p FROM PhieuXuatKho p")})
public class PhieuXuatKho implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_PX")
    private String idPX;
    @Basic(optional = false)
    @Column(name = "ngayXuat")
    @Temporal(TemporalType.DATE)
    private Date ngayXuat;
    @JoinColumn(name = "id_NV", referencedColumnName = "id_NV")
    @ManyToOne(optional = false)
    private NhanVien nhanVien;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "phieuXuatKho")
    private List<ChiTietXuatKho> chiTietXuatKhoList;

    public PhieuXuatKho() {
    }

    public PhieuXuatKho(String idPX) {
        this.idPX = idPX;
    }

    public PhieuXuatKho(String idPX, Date ngayXuat) {
        this.idPX = idPX;
        this.ngayXuat = ngayXuat;
    }

    public String getIdPX() {
        return idPX;
    }

    public void setIdPX(String idPX) {
        this.idPX = idPX;
    }

    public Date getNgayXuat() {
        return ngayXuat;
    }

    public void setNgayXuat(Date ngayXuat) {
        this.ngayXuat = ngayXuat;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public List<ChiTietXuatKho> getChiTietXuatKhoList() {
        return chiTietXuatKhoList;
    }

    public void setChiTietXuatKhoList(List<ChiTietXuatKho> chiTietXuatKhoList) {
        this.chiTietXuatKhoList = chiTietXuatKhoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPX != null ? idPX.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PhieuXuatKho)) {
            return false;
        }
        PhieuXuatKho other = (PhieuXuatKho) object;
        if ((this.idPX == null && other.idPX != null) || (this.idPX != null && !this.idPX.equals(other.idPX))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ultramotor.entity.PhieuXuatKho[ idPX=" + idPX + " ]";
    }
    
}
