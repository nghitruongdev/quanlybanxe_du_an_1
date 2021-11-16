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

public class ChiTietNhapKho implements Serializable {

  
    private int idCTNK;
    private int soLuong;
    private double giaNhap;
    private String idPN;
    private String SKU;

    public ChiTietNhapKho() {
    }

    public ChiTietNhapKho(int idCTNK) {
        this.idCTNK = idCTNK;
    }

    public ChiTietNhapKho(int idCTNK, int soLuong, double giaNhap, String idPN, String SKU) {
        this.idCTNK = idCTNK;
        this.soLuong = soLuong;
        this.giaNhap = giaNhap;
        this.idPN = idPN;
        this.SKU = SKU;
    }

    public int getIdCTNK() {
        return idCTNK;
    }

    public void setIdCTNK(int idCTNK) {
        this.idCTNK = idCTNK;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(double giaNhap) {
        this.giaNhap = giaNhap;
    }

    public String getIdPN() {
        return idPN;
    }

    public void setIdPN(String idPN) {
        this.idPN = idPN;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

 

    @Override
    public String toString() {
        return "com.ultramotor.entity.ChiTietNhapKho[ idCTNK=" + idCTNK + " ]";
    }
    
}
