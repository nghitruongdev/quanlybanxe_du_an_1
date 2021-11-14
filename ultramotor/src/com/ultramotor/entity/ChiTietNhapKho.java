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
@Table(name = "ChiTietNhapKho")
@NamedQueries({
    @NamedQuery(name = "ChiTietNhapKho.findAll", query = "SELECT c FROM ChiTietNhapKho c")})
public class ChiTietNhapKho implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_CTNK")
    private String idCTNK;
    @Basic(optional = false)
    @Column(name = "soLuong")
    private int soLuong;
    @Basic(optional = false)
    @Column(name = "GIANHAP")
    private double gianhap;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCTNK")
    private List<ChiTietSanPham> chiTietSanPhamList;
    @JoinColumn(name = "id_PN", referencedColumnName = "id_PN")
    @ManyToOne(optional = false)
    private PhieuNhapKho idPN;
    @JoinColumn(name = "SKU", referencedColumnName = "SKU")
    @ManyToOne(optional = false)
    private SanPham sku;

    public ChiTietNhapKho() {
    }

    public ChiTietNhapKho(String idCTNK) {
        this.idCTNK = idCTNK;
    }

    public ChiTietNhapKho(String idCTNK, int soLuong, double gianhap) {
        this.idCTNK = idCTNK;
        this.soLuong = soLuong;
        this.gianhap = gianhap;
    }

    public String getIdCTNK() {
        return idCTNK;
    }

    public void setIdCTNK(String idCTNK) {
        this.idCTNK = idCTNK;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGianhap() {
        return gianhap;
    }

    public void setGianhap(double gianhap) {
        this.gianhap = gianhap;
    }

    public List<ChiTietSanPham> getChiTietSanPhamList() {
        return chiTietSanPhamList;
    }

    public void setChiTietSanPhamList(List<ChiTietSanPham> chiTietSanPhamList) {
        this.chiTietSanPhamList = chiTietSanPhamList;
    }

    public PhieuNhapKho getIdPN() {
        return idPN;
    }

    public void setIdPN(PhieuNhapKho idPN) {
        this.idPN = idPN;
    }

    public SanPham getSku() {
        return sku;
    }

    public void setSku(SanPham sku) {
        this.sku = sku;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCTNK != null ? idCTNK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChiTietNhapKho)) {
            return false;
        }
        ChiTietNhapKho other = (ChiTietNhapKho) object;
        if ((this.idCTNK == null && other.idCTNK != null) || (this.idCTNK != null && !this.idCTNK.equals(other.idCTNK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ultramotor.entity.ChiTietNhapKho[ idCTNK=" + idCTNK + " ]";
    }
    
}
