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
@Table(name = "ModelSanPham")
@NamedQueries({
    @NamedQuery(name = "ModelSanPham.findAll", query = "SELECT m FROM ModelSanPham m")})
public class ModelSanPham implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_Model")
    private String idModel;
    @Basic(optional = false)
    @Column(name = "tenModel")
    private String tenModel;
    @Basic(optional = false)
    @Column(name = "doiXe")
    private int doiXe;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "modelSanPham")
    private List<SanPham> sanPhamList;
    @JoinColumn(name = "id_DongSP", referencedColumnName = "id_DongSP")
    @ManyToOne(optional = false)
    private DongSanPham dongSanPham;

    public ModelSanPham() {
    }

    public ModelSanPham(String idModel) {
        this.idModel = idModel;
    }

    public ModelSanPham(String idModel, String tenModel, int doiXe) {
        this.idModel = idModel;
        this.tenModel = tenModel;
        this.doiXe = doiXe;
    }

    public String getIdModel() {
        return idModel;
    }

    public void setIdModel(String idModel) {
        this.idModel = idModel;
    }

    public String getTenModel() {
        return tenModel;
    }

    public void setTenModel(String tenModel) {
        this.tenModel = tenModel;
    }

    public int getDoiXe() {
        return doiXe;
    }

    public void setDoiXe(int doiXe) {
        this.doiXe = doiXe;
    }

    public List<SanPham> getSanPhamList() {
        return sanPhamList;
    }

    public void setSanPhamList(List<SanPham> sanPhamList) {
        this.sanPhamList = sanPhamList;
    }

    public DongSanPham getDongSanPham() {
        return dongSanPham;
    }

    public void setDongSanPham(DongSanPham dongSanPham) {
        this.dongSanPham = dongSanPham;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idModel != null ? idModel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ModelSanPham)) {
            return false;
        }
        ModelSanPham other = (ModelSanPham) object;
        if ((this.idModel == null && other.idModel != null) || (this.idModel != null && !this.idModel.equals(other.idModel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ultramotor.entity.ModelSanPham[ idModel=" + idModel + " ]";
    }
    
}
