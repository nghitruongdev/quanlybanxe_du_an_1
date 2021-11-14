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
@Table(name = "ChiTietSanPham")
@NamedQueries({
    @NamedQuery(name = "ChiTietSanPham.findAll", query = "SELECT c FROM ChiTietSanPham c")})
public class ChiTietSanPham implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_SP")
    private String idSP;
    @Basic(optional = false)
    @Column(name = "soKhung")
    private String soKhung;
    @Basic(optional = false)
    @Column(name = "soMay")
    private String soMay;
    @JoinColumn(name = "id_CTNK", referencedColumnName = "id_CTNK")
    @ManyToOne(optional = false)
    private ChiTietNhapKho idCTNK;
    @JoinColumn(name = "SKU", referencedColumnName = "SKU")
    @ManyToOne(optional = false)
    private SanPham sku;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSP")
    private List<ChiTietHoaDon> chiTietHoaDonList;

    public ChiTietSanPham() {
    }

    public ChiTietSanPham(String idSP) {
        this.idSP = idSP;
    }

    public ChiTietSanPham(String idSP, String soKhung, String soMay) {
        this.idSP = idSP;
        this.soKhung = soKhung;
        this.soMay = soMay;
    }

    public String getIdSP() {
        return idSP;
    }

    public void setIdSP(String idSP) {
        this.idSP = idSP;
    }

    public String getSoKhung() {
        return soKhung;
    }

    public void setSoKhung(String soKhung) {
        this.soKhung = soKhung;
    }

    public String getSoMay() {
        return soMay;
    }

    public void setSoMay(String soMay) {
        this.soMay = soMay;
    }

    public ChiTietNhapKho getIdCTNK() {
        return idCTNK;
    }

    public void setIdCTNK(ChiTietNhapKho idCTNK) {
        this.idCTNK = idCTNK;
    }

    public SanPham getSku() {
        return sku;
    }

    public void setSku(SanPham sku) {
        this.sku = sku;
    }

    public List<ChiTietHoaDon> getChiTietHoaDonList() {
        return chiTietHoaDonList;
    }

    public void setChiTietHoaDonList(List<ChiTietHoaDon> chiTietHoaDonList) {
        this.chiTietHoaDonList = chiTietHoaDonList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSP != null ? idSP.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChiTietSanPham)) {
            return false;
        }
        ChiTietSanPham other = (ChiTietSanPham) object;
        if ((this.idSP == null && other.idSP != null) || (this.idSP != null && !this.idSP.equals(other.idSP))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ultramotor.entity.ChiTietSanPham[ idSP=" + idSP + " ]";
    }
    
}
