/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ultramotor.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.swing.Icon;

public class ModelSanPham implements Serializable {

    private String idModel;
    private String tenModel;
    private int doiXe;
    private String id_dongSP;

    public String getId_dongSP() {
        return id_dongSP;
    }

    public void setId_dongSP(String id_dongSP) {
        this.id_dongSP = id_dongSP;
    }
    private List<SanPham> sanPhamList;

    public ModelSanPham() {
    }

    public ModelSanPham(String idModel) {
        this.idModel = idModel;
    }

    public ModelSanPham(String idModel, String tenModel, int doiXe, String id_dongSP) {
        this.idModel = idModel;
        this.tenModel = tenModel;
        this.doiXe = doiXe;
        this.id_dongSP = id_dongSP;
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

    public String getId_dongSP() {
        return id_dongSP;
    }

    public void setId_dongSP(String id_dongSP) {
        this.id_dongSP = id_dongSP;
    }

//    public Map<String, Icon> getColorHinhMap() {
//        return colorHinhMap;
//    }
//
//    public void setColorHinhMap(Map<String, Icon> colorHinhMap) {
//        this.colorHinhMap = colorHinhMap;
//    }

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
        return String.format("%s %d", tenModel, doiXe);
    }

}
