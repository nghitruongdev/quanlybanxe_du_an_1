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

public class ChiTietXuatKho implements Serializable {

    private int idCtxk;
    private int soLuong;
    private String idPX;
    private String SKU;

    public ChiTietXuatKho() {
    }

    public ChiTietXuatKho(int idCtxk) {
        this.idCtxk = idCtxk;
    }

    public ChiTietXuatKho(int idCtxk, int soLuong, String idPX, String SKU) {
        this.idCtxk = idCtxk;
        this.soLuong = soLuong;
        this.idPX = idPX;
        this.SKU = SKU;
    }

    public int getIdCtxk() {
        return idCtxk;
    }

    public void setIdCtxk(int idCtxk) {
        this.idCtxk = idCtxk;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getIdPX() {
        return idPX;
    }

    public void setIdPX(String idPX) {
        this.idPX = idPX;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    @Override
    public String toString() {
        return "com.ultramotor.entity.ChiTietXuatKho[ idCtxk=" + idCtxk + " ]";
    }
    
}
