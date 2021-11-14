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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "ChiTietHoaDon")
@NamedQueries({
    @NamedQuery(name = "ChiTietHoaDon.findAll", query = "SELECT c FROM ChiTietHoaDon c")})
public class ChiTietHoaDon implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_CTHD")
    private Integer idCTHD;
    @Basic(optional = false)
    @Column(name = "donGia")
    private double donGia;
    @JoinColumn(name = "id_SP", referencedColumnName = "id_SP")
    @ManyToOne(optional = false)
    private ChiTietSanPham idSP;
    @JoinColumn(name = "idDV", referencedColumnName = "idDV")
    @ManyToOne(optional = false)
    private DichVu idDV;
    @JoinColumn(name = "idHD", referencedColumnName = "idHD")
    @ManyToOne(optional = false)
    private HoaDon idHD;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCTHD")
    private List<SoBaoHanh> soBaoHanhList;

    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(Integer idCTHD) {
        this.idCTHD = idCTHD;
    }

    public ChiTietHoaDon(Integer idCTHD, double donGia) {
        this.idCTHD = idCTHD;
        this.donGia = donGia;
    }

    public Integer getIdCTHD() {
        return idCTHD;
    }

    public void setIdCTHD(Integer idCTHD) {
        this.idCTHD = idCTHD;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public ChiTietSanPham getIdSP() {
        return idSP;
    }

    public void setIdSP(ChiTietSanPham idSP) {
        this.idSP = idSP;
    }

    public DichVu getIdDV() {
        return idDV;
    }

    public void setIdDV(DichVu idDV) {
        this.idDV = idDV;
    }

    public HoaDon getIdHD() {
        return idHD;
    }

    public void setIdHD(HoaDon idHD) {
        this.idHD = idHD;
    }

    public List<SoBaoHanh> getSoBaoHanhList() {
        return soBaoHanhList;
    }

    public void setSoBaoHanhList(List<SoBaoHanh> soBaoHanhList) {
        this.soBaoHanhList = soBaoHanhList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCTHD != null ? idCTHD.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChiTietHoaDon)) {
            return false;
        }
        ChiTietHoaDon other = (ChiTietHoaDon) object;
        if ((this.idCTHD == null && other.idCTHD != null) || (this.idCTHD != null && !this.idCTHD.equals(other.idCTHD))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ultramotor.entity.ChiTietHoaDon[ idCTHD=" + idCTHD + " ]";
    }
    
}
