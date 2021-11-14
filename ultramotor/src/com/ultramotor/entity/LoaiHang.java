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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author nghipc
 */
@Entity
@Table(name = "LoaiHang")
@NamedQueries({
    @NamedQuery(name = "LoaiHang.findAll", query = "SELECT l FROM LoaiHang l")})
public class LoaiHang implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_LH")
    private String idLH;
    @Basic(optional = false)
    @Column(name = "TenLoaiHang")
    private String tenLoaiHang;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLH")
    private List<DongSanPham> dongSanPhamList;

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

    public List<DongSanPham> getDongSanPhamList() {
        return dongSanPhamList;
    }

    public void setDongSanPhamList(List<DongSanPham> dongSanPhamList) {
        this.dongSanPhamList = dongSanPhamList;
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
        if ((this.idLH == null && other.idLH != null) || (this.idLH != null && !this.idLH.equals(other.idLH))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ultramotor.entity.LoaiHang[ idLH=" + idLH + " ]";
    }
    
}
