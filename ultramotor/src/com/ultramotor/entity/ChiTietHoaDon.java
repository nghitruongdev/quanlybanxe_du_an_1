
package com.ultramotor.entity;


public class ChiTietHoaDon {

    private Integer idCTHD;
    private double donGia;
    private String idHD;
    private String SKU;

    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(Integer idCTHD) {
        this.idCTHD = idCTHD;
    }

    public ChiTietHoaDon(double donGia, String idDV, String idHD, String SKU) {
        this(null, donGia, idHD, SKU);
    }

    public ChiTietHoaDon(Integer idCTHD, double donGia, String idHD, String SKU) {
        this.idCTHD = idCTHD;
        this.donGia = donGia;
        this.idHD = idHD;
        this.SKU = SKU;
    }

    public Integer getIdCTHD() {
        return idCTHD;
    }

    public void setIdCTHD(Integer idCTHD) {
        this.idCTHD = idCTHD;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }


    public String getIdHD() {
        return idHD;
    }

    public void setIdHD(String idHD) {
        this.idHD = idHD;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCTHD != null ? idCTHD.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChiTietHoaDon)) {
            return false;
        }
        ChiTietHoaDon other = (ChiTietHoaDon) object;
        if ((this.idCTHD == null && other.idCTHD != null) || (this.idCTHD != null && !this.idCTHD.equals(other.idCTHD))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ultramotor.entity.ChiTietHoaDon[ idCTHD=" + idCTHD + " ]";
    }

}
