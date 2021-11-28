
package com.ultramotor.entity;

public class SanPham {

    private String sku;
    private String tenSP;
    private String hinh;
    private String mauSac;
    private String phanKhoi;
    private Integer doiXe;
    private Integer thoiGianBH;
    private String diaChiSX;
    private double giaTien;
    private String moTa;
    private Integer tonKho;
    private String idDongSP;
    private String idNV;
    

    public SanPham() {
    }

    public SanPham(String sku) {
        this.sku = sku;
    }

    public SanPham(String sku, String tenSP, String hinh, String mauSac, String phanKhoi,Integer doiXe, Integer thoiGianBH, String diaChiSX, double giaTien, String moTa, Integer tonKho, String idModel, String idNV) {
        this.sku = sku;
        this.tenSP = tenSP;
        this.hinh = hinh;
        this.mauSac = mauSac;
        this.phanKhoi = phanKhoi;
        this.doiXe = doiXe;
        this.thoiGianBH = thoiGianBH;
        this.diaChiSX = diaChiSX;
        this.giaTien = giaTien;
        this.moTa = moTa;
        this.idDongSP = idDongSP;
        this.idNV = idNV;
        this.tonKho = tonKho;
    }

    public Integer gettonKho() {
        return tonKho;
    }

    public void settonKho(Integer TonKho) {
        this.tonKho = TonKho;
    }

    
    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public String getPhanKhoi() {
        return phanKhoi;
    }

    public void setPhanKhoi(String phanKhoi) {
        this.phanKhoi = phanKhoi;
    }

    public Integer getThoiGianBH() {
        return thoiGianBH;
    }

    public void setThoiGianBH(Integer thoiGianBH) {
        this.thoiGianBH = thoiGianBH;
    }

    public String getDiaChiSX() {
        return diaChiSX;
    }

    public void setDiaChiSX(String diaChiSX) {
        this.diaChiSX = diaChiSX;
    }

    public double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(double giaTien) {
        this.giaTien = giaTien;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Integer getDoiXe() {
        return doiXe;
    }

    public String getIdDongSP() {
        return idDongSP;
    }

    public void setDoiXe(Integer doiXe) {
        this.doiXe = doiXe;
    }

    public void setIdDongSP(String idDongSP) {
        this.idDongSP = idDongSP;
    }



    public String getIdNV() {
        return idNV;
    }

    public void setIdNV(String idNV) {
        this.idNV = idNV;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sku != null ? sku.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SanPham)) {
            return false;
        }
        SanPham other = (SanPham) object;
        if ((this.sku == null && other.sku != null) || (this.sku != null && !this.sku.equals(other.sku))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ultramotor.entity.SanPham[ sku=" + sku + " ]";
    }
    
}
