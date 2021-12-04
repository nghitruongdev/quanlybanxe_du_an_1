package com.ultramotor.entity;

import java.util.Date;

public class ChiTietBaoHanh  {

  
    private Integer id;
    private Date thoiGian;
    private String noiDung;
    private String maNV;
    private String idSBH;

    public ChiTietBaoHanh() {
    }

    public ChiTietBaoHanh(Integer id) {
        this.id = id;
    }

    public ChiTietBaoHanh(Integer id, Date thoiGian, String noiDung, String maNV, String idSBH) {
        this.id = id;
        this.thoiGian = thoiGian;
        this.noiDung = noiDung;
        this.maNV = maNV;
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

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
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
        if (!(object instanceof ChiTietBaoHanh)) {
            return false;
        }
        ChiTietBaoHanh other = (ChiTietBaoHanh) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ultramotor.entity.ChiTietBaoHanh[ id=" + id + " ]";
    }
    
}
