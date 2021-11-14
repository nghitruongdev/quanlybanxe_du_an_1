/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ultramotor.entity;

import java.io.Serializable;
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

/**
 *
 * @author nghipc
 */
@Entity
@Table(name = "DichVu")
@NamedQueries({
    @NamedQuery(name = "DichVu.findAll", query = "SELECT d FROM DichVu d")})
public class DichVu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idDV")
    private String idDV;
    @Basic(optional = false)
    @Column(name = "tenDV")
    private String tenDV;
    @Basic(optional = false)
    @Column(name = "donGia")
    private double donGia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDV")
    private List<ChiTietHoaDon> chiTietHoaDonList;
    @JoinColumn(name = "id_NV", referencedColumnName = "id_NV")
    @ManyToOne(optional = false)
    private NhanVien idNV;

    public DichVu() {
    }

    public DichVu(String idDV) {
        this.idDV = idDV;
    }

    public DichVu(String idDV, String tenDV, double donGia) {
        this.idDV = idDV;
        this.tenDV = tenDV;
        this.donGia = donGia;
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

    public NhanVien getIdNV() {
        return idNV;
    }

    public void setIdNV(NhanVien idNV) {
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
