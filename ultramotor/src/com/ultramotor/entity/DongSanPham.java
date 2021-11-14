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
@Table(name = "DongSanPham")
@NamedQueries({
    @NamedQuery(name = "DongSanPham.findAll", query = "SELECT d FROM DongSanPham d")})
public class DongSanPham implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_DongSP")
    private String idDongSP;
    @Basic(optional = false)
    @Column(name = "tenDongSP")
    private String tenDongSP;
    @JoinColumn(name = "id_LH", referencedColumnName = "id_LH")
    @ManyToOne(optional = false)
    private LoaiHang idLH;
    @JoinColumn(name = "id_NSX", referencedColumnName = "id_NSX")
    @ManyToOne(optional = false)
    private NhaSanXuat idNSX;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDongSP")
    private List<ModelSanPham> modelSanPhamList;

    public DongSanPham() {
    }

    public DongSanPham(String idDongSP) {
        this.idDongSP = idDongSP;
    }

    public DongSanPham(String idDongSP, String tenDongSP) {
        this.idDongSP = idDongSP;
        this.tenDongSP = tenDongSP;
    }

    public String getIdDongSP() {
        return idDongSP;
    }

    public void setIdDongSP(String idDongSP) {
        this.idDongSP = idDongSP;
    }

    public String getTenDongSP() {
        return tenDongSP;
    }

    public void setTenDongSP(String tenDongSP) {
        this.tenDongSP = tenDongSP;
    }

    public LoaiHang getIdLH() {
        return idLH;
    }

    public void setIdLH(LoaiHang idLH) {
        this.idLH = idLH;
    }

    public NhaSanXuat getIdNSX() {
        return idNSX;
    }

    public void setIdNSX(NhaSanXuat idNSX) {
        this.idNSX = idNSX;
    }

    public List<ModelSanPham> getModelSanPhamList() {
        return modelSanPhamList;
    }

    public void setModelSanPhamList(List<ModelSanPham> modelSanPhamList) {
        this.modelSanPhamList = modelSanPhamList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDongSP != null ? idDongSP.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DongSanPham)) {
            return false;
        }
        DongSanPham other = (DongSanPham) object;
        if ((this.idDongSP == null && other.idDongSP != null) || (this.idDongSP != null && !this.idDongSP.equals(other.idDongSP))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ultramotor.entity.DongSanPham[ idDongSP=" + idDongSP + " ]";
    }
    
}
