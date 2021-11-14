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
@Table(name = "SoBaoHanh")
@NamedQueries({
    @NamedQuery(name = "SoBaoHanh.findAll", query = "SELECT s FROM SoBaoHanh s")})
public class SoBaoHanh implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_SBH")
    private String idSBH;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSBH")
    private List<ChiTietBaoHanh> chiTietBaoHanhList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSBH")
    private List<ChiTietBaoDuong> chiTietBaoDuongList;
    @JoinColumn(name = "id_CTHD", referencedColumnName = "id_CTHD")
    @ManyToOne(optional = false)
    private ChiTietHoaDon idCTHD;

    public SoBaoHanh() {
    }

    public SoBaoHanh(String idSBH) {
        this.idSBH = idSBH;
    }

    public String getIdSBH() {
        return idSBH;
    }

    public void setIdSBH(String idSBH) {
        this.idSBH = idSBH;
    }

    public List<ChiTietBaoHanh> getChiTietBaoHanhList() {
        return chiTietBaoHanhList;
    }

    public void setChiTietBaoHanhList(List<ChiTietBaoHanh> chiTietBaoHanhList) {
        this.chiTietBaoHanhList = chiTietBaoHanhList;
    }

    public List<ChiTietBaoDuong> getChiTietBaoDuongList() {
        return chiTietBaoDuongList;
    }

    public void setChiTietBaoDuongList(List<ChiTietBaoDuong> chiTietBaoDuongList) {
        this.chiTietBaoDuongList = chiTietBaoDuongList;
    }

    public ChiTietHoaDon getIdCTHD() {
        return idCTHD;
    }

    public void setIdCTHD(ChiTietHoaDon idCTHD) {
        this.idCTHD = idCTHD;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSBH != null ? idSBH.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SoBaoHanh)) {
            return false;
        }
        SoBaoHanh other = (SoBaoHanh) object;
        if ((this.idSBH == null && other.idSBH != null) || (this.idSBH != null && !this.idSBH.equals(other.idSBH))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ultramotor.entity.SoBaoHanh[ idSBH=" + idSBH + " ]";
    }
    
}
