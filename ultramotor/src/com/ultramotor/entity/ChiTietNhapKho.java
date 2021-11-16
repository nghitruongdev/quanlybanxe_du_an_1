
package com.ultramotor.entity;

public class ChiTietNhapKho {

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
