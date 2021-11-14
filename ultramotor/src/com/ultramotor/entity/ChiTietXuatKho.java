/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ultramotor.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author nghipc
 */
@Entity
@Table(name = "ChiTietXuatKho")
@NamedQueries({
    @NamedQuery(name = "ChiTietXuatKho.findAll", query = "SELECT c FROM ChiTietXuatKho c")})
public class ChiTietXuatKho implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_CTXK")
    private String idCtxk;
    @Basic(optional = false)
    @Column(name = "soLuong")
    private int soLuong;
    @JoinColumn(name = "id_PX", referencedColumnName = "id_PX")
    @ManyToOne(optional = false)
    private PhieuXuatKho idPX;
    @JoinColumn(name = "SKU", referencedColumnName = "SKU")
    @ManyToOne(optional = false)
    private SanPham sku;

    public ChiTietXuatKho() {
    }

    public ChiTietXuatKho(String idCtxk) {
        this.idCtxk = idCtxk;
    }

    public ChiTietXuatKho(String idCtxk, int soLuong) {
        this.idCtxk = idCtxk;
        this.soLuong = soLuong;
    }

    public String getIdCtxk() {
        return idCtxk;
    }

    public void setIdCtxk(String idCtxk) {
        this.idCtxk = idCtxk;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public PhieuXuatKho getIdPX() {
        return idPX;
    }

    public void setIdPX(PhieuXuatKho idPX) {
        this.idPX = idPX;
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
        hash += (idCtxk != null ? idCtxk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChiTietXuatKho)) {
            return false;
        }
        ChiTietXuatKho other = (ChiTietXuatKho) object;
        if ((this.idCtxk == null && other.idCtxk != null) || (this.idCtxk != null && !this.idCtxk.equals(other.idCtxk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ultramotor.entity.ChiTietXuatKho[ idCtxk=" + idCtxk + " ]";
    }
    
}
