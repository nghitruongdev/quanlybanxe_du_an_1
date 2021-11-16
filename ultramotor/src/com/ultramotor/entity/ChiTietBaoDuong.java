/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ultramotor.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author nghipc
 */
@Entity
@Table(name = "ChiTietBaoDuong")
@NamedQueries({
    @NamedQuery(name = "ChiTietBaoDuong.findAll", query = "SELECT c FROM ChiTietBaoDuong c")})
public class ChiTietBaoDuong implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "thoiGian")
    @Temporal(TemporalType.DATE)
    private Date thoiGian;
    @Basic(optional = false)
    @Column(name = "noiDung")
    private String noiDung;
    @JoinColumn(name = "id_NV", referencedColumnName = "id_NV")
    @ManyToOne(optional = false)
    private NhanVien nhanVien;
    @JoinColumn(name = "id_SBH", referencedColumnName = "id_SBH")
    @ManyToOne(optional = false)
    private SoBaoHanh soBaoHanh;

    public ChiTietBaoDuong() {
    }

    public ChiTietBaoDuong(Integer id) {
        this.id = id;
    }

    public ChiTietBaoDuong(Integer id, Date thoiGian, String noiDung) {
        this.id = id;
        this.thoiGian = thoiGian;
        this.noiDung = noiDung;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(Date thoiGian) {
        this.thoiGian = thoiGian;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public SoBaoHanh getSoBaoHanh() {
        return soBaoHanh;
    }

    public void setSoBaoHanh(SoBaoHanh soBaoHanh) {
        this.soBaoHanh = soBaoHanh;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChiTietBaoDuong)) {
            return false;
        }
        ChiTietBaoDuong other = (ChiTietBaoDuong) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ultramotor.entity.ChiTietBaoDuong[ id=" + id + " ]";
    }
    
}
