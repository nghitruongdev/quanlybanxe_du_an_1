package com.ultramotor.entity;

import java.util.Date;

public class ChiTietBaoDuong {

    private Integer id;
    private Date thoiGian;
    private String noiDung;
    private String idNhanVien;
    private String idSBH;

    public ChiTietBaoDuong() {
    }

    public ChiTietBaoDuong(Integer id) {
        this.id = id;
    }

    public ChiTietBaoDuong(Integer id, Date thoiGian, String noiDung, String idNhanVien, String idSBH) {
        this.id = id;
        this.thoiGian = thoiGian;
        this.noiDung = noiDung;
        this.idNhanVien = idNhanVien;
        this.idSBH = idSBH;
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

    public String getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(String idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public String getIdSBH() {
        return idSBH;
    }

    public void setIdSBH(String idSBH) {
        this.idSBH = idSBH;
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
