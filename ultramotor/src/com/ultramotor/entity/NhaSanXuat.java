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
@Table(name = "NhaSanXuat")
@NamedQueries({
    @NamedQuery(name = "NhaSanXuat.findAll", query = "SELECT n FROM NhaSanXuat n")})
public class NhaSanXuat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_NSX")
    private String idNSX;
    @Basic(optional = false)
    @Column(name = "TENNSX")
    private String tennsx;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idNSX")
    private List<DongSanPham> dongSanPhamList;

    public NhaSanXuat() {
    }

    public NhaSanXuat(String idNSX) {
        this.idNSX = idNSX;
    }

    public NhaSanXuat(String idNSX, String tennsx) {
        this.idNSX = idNSX;
        this.tennsx = tennsx;
    }

    public String getIdNSX() {
        return idNSX;
    }

    public void setIdNSX(String idNSX) {
        this.idNSX = idNSX;
    }

    public String getTennsx() {
        return tennsx;
    }

    public void setTennsx(String tennsx) {
        this.tennsx = tennsx;
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
        hash += (idNSX != null ? idNSX.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NhaSanXuat)) {
            return false;
        }
        NhaSanXuat other = (NhaSanXuat) object;
        if ((this.idNSX == null && other.idNSX != null) || (this.idNSX != null && !this.idNSX.equals(other.idNSX))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ultramotor.entity.NhaSanXuat[ idNSX=" + idNSX + " ]";
    }
    
}
